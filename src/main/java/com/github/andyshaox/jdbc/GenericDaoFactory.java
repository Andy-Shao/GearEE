package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.github.andyshao.asm.Version;
import com.github.andyshao.lang.ClassAssembly;
import com.github.andyshao.lang.StringOperation;
import com.github.andyshao.reflect.ClassOperation;
import com.github.andyshao.reflect.FieldOperation;
import com.github.andyshao.reflect.SignatureDetector;
import com.github.andyshao.reflect.SignatureDetector.ClassSignature;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 9, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class GenericDaoFactory implements DaoFactory {
    private final String processFieldDesc = "Lcom/github/andyshaox/jdbc/SqlExcution;";
    private final String processFieldName = "sqlExcution";
    private SqlExecution SqlExecution;

    @Override
    public Object getDao(final Dao dao) {
        final Class<?> interfaceClass = dao.getDefineClass();
        if (!interfaceClass.isInterface()) throw new JdbcProcessException("Defining class should be a interface");
        final String targetName = interfaceClass.getName() + "Entity";
        final String classDesc = targetName.replace('.' , '/');
        final ClassSignature csig = new SignatureDetector(Opcodes.ASM5).getSignature(interfaceClass);
        String classSignature = null;
        if (csig.classSignature != null) {
            String tail = StringOperation.replaceAll(csig.classSignature , "Ljava/lang/Object" , "");
            String[] parts = tail.split(";");
            for (String part : parts) {
                part = StringOperation.replaceAll(part , "<" , "");
                part = StringOperation.replaceAll(part , ">" , "");
                part = StringOperation.replaceAll(part , ":" , "");
                if (!part.isEmpty()) tail = StringOperation.replaceFirst(tail , ":" , part);
            }
            tail = "L" + interfaceClass.getName().replace('.' , '/') + tail;
            classSignature = csig.classSignature + tail;
        }
        final ClassWriter cw = new ClassWriter(0);
        cw.visit(Version.V1_8.getVersion() , Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER , classDesc , classSignature ,
            "java/lang/Object" , new String[] { interfaceClass.getName().replace('.' , '/') });
        FieldVisitor fv =
            cw.visitField(Opcodes.ACC_PRIVATE , this.processFieldName , this.processFieldDesc , null , null);
        fv.visitEnd();
        fv = cw.visitField(Opcodes.ACC_PRIVATE + Opcodes.ACC_FINAL , "dao" , "Lcom/github/andyshaox/jdbc/Dao;" , null ,
            null);
        fv.visitEnd();
        MethodVisitor mv = null;
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC , "<init>" , "(Lcom/github/andyshaox/jdbc/Dao)V" , null , null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD , 0);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL , "java/lang/Object" , "<init>" , "()V" , false);
            mv.visitVarInsn(Opcodes.ALOAD , 0);
            mv.visitVarInsn(Opcodes.ALOAD , 1);
            mv.visitFieldInsn(Opcodes.PUTFIELD , "com/github/andyshaox/jdbc/UserDaoEntity" , "dao" ,
                "Lcom/github/andyshaox/jdbc/Dao;");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(2 , 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC , "setSqlExecution" , "(Lcom/github/andyshaox/jdbc/SqlExecution;)V" ,
                null , null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD , 0);
            mv.visitVarInsn(Opcodes.ALOAD , 1);
            mv.visitFieldInsn(Opcodes.PUTFIELD , "com/github/andyshaox/jdbc/UserDaoEntity" , "sqlExecution" ,
                "Lcom/github/andyshaox/jdbc/SqlExecution;");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(2 , 2);
            mv.visitEnd();
        }
        Method[] methods = interfaceClass.getMethods();
        for (Method method : methods) {
            if (method.isDefault() || Modifier.isStatic(method.getModifiers())) continue;
            Class<?>[] exceptions = method.getExceptionTypes();
            String[] exceptionDescriptions = new String[exceptions.length];
            for (int i = 0 ; i < exceptions.length ; i++)
                exceptionDescriptions[i] = exceptions[i].getName().replace('.' , '/');
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC , method.getName() , Type.getType(method).getDescriptor() ,
                csig.methodSignatures.get(method) , exceptionDescriptions);
            Class<?> returnType = method.getReturnType();
            mv.visitCode();
            if (int.class.isAssignableFrom(returnType) || byte.class.isAssignableFrom(returnType)
                || char.class.isAssignableFrom(returnType) || short.class.isAssignableFrom(returnType)
                || boolean.class.isAssignableFrom(returnType)) {
                if (dao.getSqls().containsKey(method)) {
                    //TODO ...
                } else {
                    mv.visitInsn(Opcodes.ICONST_0);
                    mv.visitInsn(Opcodes.IRETURN);
                    mv.visitMaxs(1 , 1);
                }
            } else if (double.class.isAssignableFrom(returnType)) {
                if (dao.getSqls().containsKey(method)) {
                    //TODO ...
                } else {
                    mv.visitInsn(Opcodes.DCONST_0);
                    mv.visitInsn(Opcodes.DRETURN);
                    mv.visitMaxs(2 , 1);
                }
            } else if (float.class.isAssignableFrom(returnType)) {
                if (dao.getSqls().containsKey(method)) {
                    //TODO...
                } else {
                    mv.visitInsn(Opcodes.FCONST_0);
                    mv.visitInsn(Opcodes.FRETURN);
                    mv.visitMaxs(1 , 1);
                }
            } else if (long.class.isAssignableFrom(returnType)) {
                if (dao.getSqls().containsKey(method)) {
                    //TODO...
                } else {
                    mv.visitInsn(Opcodes.LCONST_0);
                    mv.visitInsn(Opcodes.LRETURN);
                    mv.visitMaxs(2 , 1);
                }
            } else if (void.class.isAssignableFrom(returnType) || Void.class.isAssignableFrom(returnType)) {
                if (dao.getSqls().containsKey(method)) {
                    mv.visitVarInsn(Opcodes.ALOAD , 0);
                    mv.visitFieldInsn(Opcodes.GETFIELD , classDesc , this.processFieldName , this.processFieldDesc);
                    mv.visitVarInsn(Opcodes.AALOAD , 0);
                    mv.visitFieldInsn(Opcodes.GETFIELD , classDesc , "dao" , "Lcom/github/andyshaox/jdbc/Dao;");
                    mv.visitVarInsn(Opcodes.AALOAD , 0);
                    mv.visitMethodInsn(Opcodes.INVOKEINTERFACE , "com/github/andyshaox/jdbc/Dao" , "getDefineClass" ,
                        "()Ljava/lang/Class;" , true);
                    mv.visitLdcInsn("delete");
                    mv.visitInsn(Opcodes.ICONST_1);
                    mv.visitTypeInsn(Opcodes.ANEWARRAY , "java/lang/Class");
                    mv.visitInsn(Opcodes.DUP);
                    mv.visitInsn(Opcodes.ICONST_0);
                    mv.visitLdcInsn(Type.getType("Lcom/github/andyshaox/jdbc/User;"));
                    mv.visitInsn(Opcodes.AASTORE);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC , "com/github/andyshao/reflect/MethodOperation" , "getMethod" ,
                        "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;" , false);
                    mv.visitInsn(Opcodes.ACONST_NULL);
                    mv.visitInsn(Opcodes.ICONST_1);
                    mv.visitTypeInsn(Opcodes.ANEWARRAY , "java/lang/Object");
                    mv.visitInsn(Opcodes.DUP);
                    mv.visitInsn(Opcodes.ICONST_0);
                    mv.visitVarInsn(Opcodes.ALOAD , 1);
                    mv.visitInsn(Opcodes.AASTORE);
                    mv.visitMethodInsn(Opcodes.INVOKEINTERFACE , "com/github/andyshaox/jdbc/SqlExecution" , "invoke" ,
                        "(Lcom/github/andyshaox/jdbc/Dao;Ljava/lang/reflect/Method;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;" ,
                        true);
                    mv.visitInsn(Opcodes.POP);
                    mv.visitInsn(Opcodes.RETURN);
                    mv.visitMaxs(8 , 2);
                    //TODO...
                } else {
                    mv.visitInsn(Opcodes.RETURN);
                    mv.visitMaxs(0 , 1);
                }
            } else if (dao.getSqls().containsKey(method)) {
                //TODO
            } else {
                mv.visitInsn(Opcodes.ACONST_NULL);
                mv.visitInsn(Opcodes.ARETURN);
                mv.visitMaxs(1 , 1);
            }
            mv.visitEnd();
        }
        cw.visitEnd();
        byte[] bs = cw.toByteArray();
        Object result = ClassOperation.newInstance(ClassAssembly.DEFAULT.assemble(targetName , bs) , dao);
        FieldOperation.setValueBySetMethod(result , this.processFieldName , SqlExecution.class , this.SqlExecution);
        return result;
    }

    public void setSqlExecution(SqlExecution sqlExecution) {
        this.SqlExecution = sqlExecution;
    }
}
