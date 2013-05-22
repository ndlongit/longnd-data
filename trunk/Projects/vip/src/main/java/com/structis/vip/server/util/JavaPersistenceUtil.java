package com.structis.vip.server.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JavaPersistenceUtil {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("vip");
    }

    public static EntityManagerFactory getEMF() {
        return emf;
    }

}
