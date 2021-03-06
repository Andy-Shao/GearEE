package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.github.andyshao.asm.ClassVisitorOperation;
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
    private static final String DAO_DESC = "Lcom/github/andyshaox/jdbc/Dao;";
    private static final String DAO_NAME = "dao";
    private static final String SQLEXCUTION_DESC = "Lcom/github/andyshaox/jdbc/SqlExecution;";
    private static final String SQLEXCUTION_NAME = "sqlExecution";

    static void doProcess(final String classDesc , MethodVisitor mv , Method method , Consumer<MethodVisitor> doReturn) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        mv.visitVarInsn(Opcodes.ALOAD , 0);
        mv.visitFieldInsn(Opcodes.GETFIELD , classDesc , GenericDaoFactory.SQLEXCUTION_NAME , GenericDaoFactory.SQLEXCUTION_DESC);
        mv.visitVarInsn(Opcodes.ALOAD , 0);
        mv.visitFieldInsn(Opcodes.GETFIELD , classDesc , GenericDaoFactory.DAO_NAME , GenericDaoFactory.DAO_DESC);
        mv.visitVarInsn(Opcodes.ALOAD , 0);
        mv.visitFieldInsn(Opcodes.GETFIELD , classDesc , GenericDaoFactory.DAO_NAME , GenericDaoFactory.DAO_DESC);
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE , "com/github/andyshaox/jdbc/Dao" , "getDefineClass" , "()Ljava/lang/Class;" , true);
        mv.visitLdcInsn(method.getName());
        if (parameterTypes.length == 0) mv.visitInsn(Opcodes.ICONST_0);
        else if (parameterTypes.length == 1) mv.visitInsn(Opcodes.ICONST_1);
        else if (parameterTypes.length == 2) mv.visitInsn(Opcodes.ICONST_2);
        else if (parameterTypes.length == 3) mv.visitInsn(Opcodes.ICONST_3);
        else if (parameterTypes.length == 4) mv.visitInsn(Opcodes.ICONST_4);
        else if (parameterTypes.length == 5) mv.visitInsn(Opcodes.ICONST_5);
        else mv.visitIntInsn(Opcodes.BIPUSH , parameterTypes.length);
        mv.visitTypeInsn(Opcodes.ANEWARRAY , "java/lang/Class");
        for (int i = 0 ; i < parameterTypes.length ; i++) {
            mv.visitInsn(Opcodes.DUP);
            if (i == 0) mv.visitInsn(Opcodes.ICONST_0);
            else if (i == 1) mv.visitInsn(Opcodes.ICONST_1);
            else if (i == 2) mv.visitInsn(Opcodes.ICONST_2);
            else if (i == 3) mv.visitInsn(Opcodes.ICONST_3);
            else if (i == 4) mv.visitInsn(Opcodes.ICONST_4);
            else if (i == 5) mv.visitInsn(Opcodes.ICONST_5);
            else mv.visitIntInsn(Opcodes.BIPUSH , i);
            if (parameterTypes[i].isPrimitive()) {
                String owner = "";
                final Class<?> parameter = parameterTypes[i];
                if (int.class.isAssignableFrom(parameter)) owner = "java/lang/Integer";
                else if (char.class.isAssignableFrom(parameter)) owner = "java/lang/Character";
                else if (byte.class.isAssignableFrom(parameter)) owner = "java/lang/Byte";
                else if (short.class.isAssignableFrom(parameter)) owner = "java/lang/Short";
                else if (long.class.isAssignableFrom(parameter)) owner = "java/lang/Long";
                else if (float.class.isAssignableFrom(parameter)) owner = "java/lang/Float";
                else if (double.class.isAssignableFrom(parameter)) owner = "java/lang/Double";
                else if (boolean.class.isAssignableFrom(parameter)) owner = "java/lang/Boolean";
                mv.visitFieldInsn(Opcodes.GETSTATIC , owner , "TYPE" , "Ljava/lang/Class;");
            } else mv.visitLdcInsn(Type.getType(parameterTypes[i]));
            mv.visitInsn(Opcodes.AASTORE);
        }
        mv.visitMethodInsn(Opcodes.INVOKESTATIC , "com/github/andyshao/reflect/MethodOperation" , "getMethod" , "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;" ,
            false);
        mv.visitInsn(Opcodes.ACONST_NULL);
        if (parameterTypes.length == 0) mv.visitInsn(Opcodes.ICONST_0);
        else if (parameterTypes.length == 1) mv.visitInsn(Opcodes.ICONST_1);
        else if (parameterTypes.length == 2) mv.visitInsn(Opcodes.ICONST_2);
        else if (parameterTypes.length == 3) mv.visitInsn(Opcodes.ICONST_3);
        else if (parameterTypes.length == 4) mv.visitInsn(Opcodes.ICONST_4);
        else if (parameterTypes.length == 5) mv.visitInsn(Opcodes.ICONST_5);
        else mv.visitIntInsn(Opcodes.BIPUSH , parameterTypes.length);
        mv.visitTypeInsn(Opcodes.ANEWARRAY , "java/lang/Object");
        for (int i = 0 ; i < parameterTypes.length ; i++) {
            mv.visitInsn(Opcodes.DUP);
            if (i == 0) mv.visitInsn(Opcodes.ICONST_0);
            else if (i == 1) mv.visitInsn(Opcodes.ICONST_1);
            else if (i == 2) mv.visitInsn(Opcodes.ICONST_2);
            else if (i == 3) mv.visitInsn(Opcodes.ICONST_3);
            else if (i == 4) mv.visitInsn(Opcodes.ICONST_4);
            else if (i == 5) mv.visitInsn(Opcodes.ICONST_5);
            else mv.visitIntInsn(Opcodes.BIPUSH , i);
            if (parameterTypes[i].isPrimitive()) {
                final Class<?> parameter = parameterTypes[i];
                if (int.class.isAssignableFrom(parameter)) {
                    mv.visitVarInsn(Opcodes.ILOAD , i + 1);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC , "java/lang/Integer" , "valueOf" , "(I)Ljava/lang/Integer;" , false);
                } else if (char.class.isAssignableFrom(parameter)) {
                    mv.visitVarInsn(Opcodes.ILOAD , i + 1);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC , "java/lang/Character" , "valueOf" , "(C)Ljava/lang/Character;" , false);
                } else if (byte.class.isAssignableFrom(parameter)) {
                    mv.visitVarInsn(Opcodes.ILOAD , i + 1);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC , "java/lang/Byte" , "valueOf" , "(B)Ljava/lang/Byte;" , false);
                } else if (short.class.isAssignableFrom(parameter)) {
                    mv.visitVarInsn(Opcodes.ILOAD , i + 1);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC , "java/lang/Byte" , "valueOf" , "(S)Ljava/lang/Byte;" , false);
                } else if (long.class.isAssignableFrom(parameter)) {
                    mv.visitVarInsn(Opcodes.LLOAD , i + 1);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC , "java/lang/Long" , "valueOf" , "(J)Ljava/lang/Long;" , false);
                } else if (float.class.isAssignableFrom(parameter)) {
                    mv.visitVarInsn(Opcodes.FLOAD , i + 1);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC , "java/lang/Float" , "valueOf" , "(F)Ljava/lang/Float;" , false);
                } else if (double.class.isAssignableFrom(parameter)) {
                    mv.visitVarInsn(Opcodes.DLOAD , i + 1);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC , "java/lang/Double" , "valueOf" , "(D)Ljava/lang/Double;" , false);
                } else if (boolean.class.isAssignableFrom(parameter)) {
                    mv.visitVarInsn(Opcodes.ILOAD , i + 1);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC , "java/lang/Boolean" , "valueOf" , "(Z)Ljava/lang/Boolean;" , false);
                }
            } else mv.visitVarInsn(Opcodes.ALOAD , i + 1);
            mv.visitInsn(Opcodes.AASTORE);
        }
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE , "com/github/andyshaox/jdbc/SqlExecution" , "invoke" ,
            "(Lcom/github/andyshaox/jdbc/Dao;Ljava/lang/reflect/Method;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;" , true);
        doReturn.accept(mv);
        int maxStack = 8;
        for (Class<?> parameter : parameterTypes)
            if (long.class.isAssignableFrom(parameter) || double.class.isAssignableFrom(parameter)) maxStack++;
        mv.visitMaxs(maxStack , ClassVisitorOperation.countBasicLocal(method));
    }

    private SqlExecution sqlExecution;

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
        cw.visit(Version.V1_8.getVersion() , Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER , classDesc , classSignature , "java/lang/Object" , new String[] { interfaceClass.getName().replace('.' , '/') });
        {
            FieldVisitor fv = cw.visitField(Opcodes.ACC_PRIVATE , GenericDaoFactory.SQLEXCUTION_NAME , GenericDaoFactory.SQLEXCUTION_DESC , null , null);
            fv.visitEnd();
            fv = cw.visitField(Opcodes.ACC_PRIVATE , GenericDaoFactory.DAO_NAME , GenericDaoFactory.DAO_DESC , null , null);
            fv.visitEnd();
        }
        MethodVisitor mv = null;
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC , "<init>" , "()V" , null , null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD , 0);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL , "java/lang/Object" , "<init>" , "()V" , false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1 , 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC , "setSqlExecution" , "(Lcom/github/andyshaox/jdbc/SqlExecution;)V" , null , null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD , 0);
            mv.visitVarInsn(Opcodes.ALOAD , 1);
            mv.visitFieldInsn(Opcodes.PUTFIELD , classDesc , GenericDaoFactory.SQLEXCUTION_NAME , GenericDaoFactory.SQLEXCUTION_DESC);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(2 , 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC , "setDao" , "(Lcom/github/andyshaox/jdbc/Dao;)V" , null , null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD , 0);
            mv.visitVarInsn(Opcodes.ALOAD , 1);
            mv.visitFieldInsn(Opcodes.PUTFIELD , classDesc , GenericDaoFactory.DAO_NAME , GenericDaoFactory.DAO_DESC);
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
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC , method.getName() , Type.getType(method).getDescriptor() , csig.methodSignatures.get(method) , exceptionDescriptions);
            Class<?> returnType = method.getReturnType();
            mv.visitCode();
            if (int.class.isAssignableFrom(returnType)) this.returnInt(dao , classDesc , mv , method);
            else if (short.class.isAssignableFrom(returnType)) this.returnShort(dao , classDesc , mv , method);
            else if (byte.class.isAssignableFrom(returnType)) this.returnByte(dao , classDesc , mv , method);
            else if (boolean.class.isAssignableFrom(returnType)) this.returnBoolean(dao , classDesc , mv , method);
            else if (char.class.isAssignableFrom(returnType)) this.returnChar(dao , classDesc , mv , method);
            else if (double.class.isAssignableFrom(returnType)) this.returnDouble(dao , classDesc , mv , method);
            else if (float.class.isAssignableFrom(returnType)) this.returnFloat(dao , classDesc , mv , method);
            else if (long.class.isAssignableFrom(returnType)) this.returnLong(dao , classDesc , mv , method);
            else if (void.class.isAssignableFrom(returnType)) this.returnViod(dao , classDesc , mv , method);
            else this.returnObject(dao , classDesc , mv , method , returnType);
            mv.visitEnd();
        }
        cw.visitEnd();
        Object result = ClassOperation.newInstance(ClassAssembly.DEFAULT.assemble(targetName , cw.toByteArray()));
        FieldOperation.setValueBySetMethod(result , GenericDaoFactory.SQLEXCUTION_NAME , SqlExecution.class , this.sqlExecution);
        FieldOperation.setValueBySetMethod(result , GenericDaoFactory.DAO_NAME , Dao.class , dao);
        return result;
    }

    void returnBoolean(final Dao dao , final String classDesc , MethodVisitor mv , Method method) {
        if (dao.getSqls().containsKey(method)) GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST , "java/lang/Boolean");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL , "java/lang/Boolean" , "booleanValue" , "()Z" , false);
            methodVisitor.visitInsn(Opcodes.IRETURN);
        });
        else {
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(1 , ClassVisitorOperation.countBasicLocal(method));
        }
    }

    void returnByte(final Dao dao , final String classDesc , MethodVisitor mv , Method method) {
        if (dao.getSqls().containsKey(method)) GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST , "java/lang/Byte");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL , "java/lang/Byte" , "byteValue" , "()B" , false);
            methodVisitor.visitInsn(Opcodes.IRETURN);
        });
        else {
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(1 , ClassVisitorOperation.countBasicLocal(method));
        }
    }

    void returnChar(final Dao dao , final String classDesc , MethodVisitor mv , Method method) {
        if (dao.getSqls().containsKey(method)) GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST , "java/lang/Character");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL , "java/lang/Character" , "charValue" , "()C" , false);
            methodVisitor.visitInsn(Opcodes.IRETURN);
        });
        else {
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(1 , ClassVisitorOperation.countBasicLocal(method));
        }
    }

    void returnDouble(final Dao dao , final String classDesc , MethodVisitor mv , Method method) {
        if (dao.getSqls().containsKey(method)) GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST , "java/lang/Double");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL , "java/lang/Double" , "doubleValue" , "()D" , false);
            methodVisitor.visitInsn(Opcodes.DRETURN);
        });
        else {
            mv.visitInsn(Opcodes.DCONST_0);
            mv.visitInsn(Opcodes.DRETURN);
            mv.visitMaxs(2 , ClassVisitorOperation.countBasicLocal(method));
        }
    }

    void returnFloat(final Dao dao , final String classDesc , MethodVisitor mv , Method method) {
        if (dao.getSqls().containsKey(method)) GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST , "java/lang/Float");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL , "java/lang/Float" , "floatValue" , "()F" , false);
            methodVisitor.visitInsn(Opcodes.FRETURN);
        });
        else {
            mv.visitInsn(Opcodes.FCONST_0);
            mv.visitInsn(Opcodes.FRETURN);
            mv.visitMaxs(1 , ClassVisitorOperation.countBasicLocal(method));
        }
    }

    void returnInt(final Dao dao , final String classDesc , MethodVisitor mv , Method method) {
        if (dao.getSqls().containsKey(method)) GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST , "java/lang/Integer");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL , "java/lang/Integer" , "intValue" , "()I" , false);
            methodVisitor.visitInsn(Opcodes.IRETURN);
        });
        else {
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(1 , ClassVisitorOperation.countBasicLocal(method));
        }
    }

    void returnLong(final Dao dao , final String classDesc , MethodVisitor mv , Method method) {
        if (dao.getSqls().containsKey(method)) GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST , "java/lang/Long");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL , "java/lang/Long" , "longValue" , "()J" , false);
            methodVisitor.visitInsn(Opcodes.LRETURN);
        });
        else {
            mv.visitInsn(Opcodes.LCONST_0);
            mv.visitInsn(Opcodes.LRETURN);
            mv.visitMaxs(2 , ClassVisitorOperation.countBasicLocal(method));
        }
    }

    void returnObject(final Dao dao , final String classDesc , MethodVisitor mv , Method method , Class<?> returnType) {
        if (dao.getSqls().containsKey(method)) {
            if (Void.class.isAssignableFrom(returnType)) GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
                methodVisitor.visitInsn(Opcodes.POP);
                methodVisitor.visitInsn(Opcodes.ACONST_NULL);
                methodVisitor.visitInsn(Opcodes.ARETURN);
            });
            else GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
                methodVisitor.visitTypeInsn(Opcodes.CHECKCAST , returnType.getName().replace('.' , '/'));
                methodVisitor.visitInsn(Opcodes.ARETURN);
            });
        } else {
            mv.visitInsn(Opcodes.ACONST_NULL);
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(1 , ClassVisitorOperation.countBasicLocal(method));
        }
    }

    void returnShort(final Dao dao , final String classDesc , MethodVisitor mv , Method method) {
        if (dao.getSqls().containsKey(method)) GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST , "java/lang/Short");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL , "java/lang/Short" , "shortValue" , "()S" , false);
            methodVisitor.visitInsn(Opcodes.IRETURN);
        });
        else {
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(1 , ClassVisitorOperation.countBasicLocal(method));
        }
    }

    void returnViod(final Dao dao , final String classDesc , MethodVisitor mv , Method method) {
        if (dao.getSqls().containsKey(method)) GenericDaoFactory.doProcess(classDesc , mv , method , (methodVisitor) -> {
            methodVisitor.visitInsn(Opcodes.POP);
            methodVisitor.visitInsn(Opcodes.RETURN);
        });
        else {
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0 , ClassVisitorOperation.countBasicLocal(method));
        }
    }

    public void setSqlExecution(SqlExecution sqlExecution) {
        this.sqlExecution = sqlExecution;
    }
}
