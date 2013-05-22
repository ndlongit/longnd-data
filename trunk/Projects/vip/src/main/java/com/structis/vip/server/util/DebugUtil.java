package com.structis.vip.server.util;

import java.lang.reflect.Field;

public class DebugUtil {

    public static <T> void printFields(T source) throws Exception {
        Class clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("Number of fields = " + fields.length);
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName() + "=" + field.get(source));

        }

    }
}
