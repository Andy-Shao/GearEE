package com.github.andyshaox.jdbc;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.github.andyshao.lang.StringOperation;
import com.github.andyshao.reflect.ClassOperation;
import com.github.andyshao.reflect.FieldOperation;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 10, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class LoadingArgs implements SqlAssembly {
    private SqlAssembly sqlAssembly = SqlAssembly.DEFAUL;

    @Override
    public String assemble(Dao dao , Method method , Object... args) {
        Sql sql = dao.getSqls().get(method);
        String rt = this.sqlAssembly.assemble(dao , method , args);
        if (args == null) return rt;
        String[] parameterNames = sql.getParameterNames();
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0 ; i < args.length ; i++) {
            final Object arg = args[i];
            if (ClassOperation.isPrimitiveType(arg.getClass()) || String.class.isInstance(arg)) {
                String key = "{" + parameterNames[i] + "}";
                rt = this.replaceArgs(key , args[i] , rt);
            } else if (arg.getClass().isArray()) {
                int length = Array.getLength(args[i]);
                for (int j = 0 ; j < length ; j++) {
                    String key = "{" + parameterNames[i] + "[" + j + "]}";
                    rt = this.replaceArgs(key , Array.get(args[i] , j) , rt);
                }
            } else if (arg instanceof List) {
                List<?> list = (List<?>) args[i];
                for (int j = 0 ; j < list.size() ; j++) {
                    String key = "{" + parameterNames[i] + "[" + j + "]}";
                    rt = this.replaceArgs(key , list.get(j) , rt);
                }
            } else if (arg instanceof Map) {
                Map<? , ?> map = (Map<? , ?>) args[i];
                for (Object key : map.keySet()) {
                    String argName = "{" + parameterNames[i] + "[" + key.toString() + "]}";
                    rt = this.replaceArgs(argName , map.get(key) , rt);
                }
            } else {
                Set<Field> fields = new HashSet<>();
                fields.addAll(Arrays.asList(FieldOperation.superGetDeclaredFields(parameterTypes[i])));
                fields.addAll(Arrays.asList(parameterTypes[i].getFields()));
                for (Field field : fields) {
                    String key = "{" + parameterNames[i] + "." + field.getName() + "}";
                    rt = this.replaceArgs(key , FieldOperation.getValueByGetMethod(arg , field.getName()) , rt);
                }
            }
        }
        return rt;
    }

    String replaceArgs(String argName , Object argValue , String sql) {
        String result = sql;
        String value = "";
        if (ClassOperation.isPrimitiveType(argValue.getClass()) || String.class.isInstance(argValue)) value = Objects.toString(argValue);
        else {
            value = Objects.toString(value);
            if (!value.equals("null")) value = "'" + value + "'";
        }
        while (result.indexOf(argName) != -1)
            result = StringOperation.replaceAll(result , argName , value);
        return result;
    }

    public void setSqlAssembly(SqlAssembly sqlAssembly) {
        this.sqlAssembly = sqlAssembly;
    }

}
