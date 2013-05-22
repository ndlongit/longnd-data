package com.structis.vip.server.util;

import org.springframework.beans.BeanUtils;

public class DataCopier {

    public static String ID_FIELD = "id";

    public static <T> void copyNotIdFields(final T source, T target) throws Exception {
        copyFields(source, target, ID_FIELD);
    }

    public static <T> void copyNotIdFields(final T source, T target, String idField) throws Exception {
        if (idField != null) {
            copyFields(source, target, idField);
        } else {
            copyFields(source, target, ID_FIELD);
        }
    }

    public static <T> void copyFields(final T source, T target, String exceptField) throws Exception {
        String[] excludes = { exceptField };
        BeanUtils.copyProperties(source, target, excludes);

    }
}
