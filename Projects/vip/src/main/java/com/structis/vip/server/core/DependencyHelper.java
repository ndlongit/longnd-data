package com.structis.vip.server.core;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class DependencyHelper {

    protected static Logger logger = Logger.getLogger(DependencyHelper.class);

    /**
     * Cherche tous les champs contenant l'annotation Autowired et le setter avec le bean portan le m�me nom
     */
    public static void doDependencyInjection(ServletContext context, Object object) {
        for (Field field : getFieldsToDependencyInject(object)) {
            try {
                boolean isFieldAccessible = field.isAccessible();
                if (!isFieldAccessible) {
                    field.setAccessible(true);
                }
                field.set(object, SpringGetter.getBean(context, field.getName()));
                if (!isFieldAccessible) {
                    field.setAccessible(false);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("Dependency injection successful: " + object.getClass().getName() + "." + field.getName());
                }
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Set<Field> getFieldsToDependencyInject(Object object) {
        Set<Field> fieldsToInject = new HashSet<Field>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Autowired.class) != null) {
                fieldsToInject.add(field);
            }
        }
        return fieldsToInject;
    }
}
