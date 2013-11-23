package org.java.demo.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * Configures and provides access to Hibernate sessions, tied to the current thread of execution. Follows the Thread Local Session pattern, see
 * {@link http ://hibernate.org/42.html }.
 */
@SuppressWarnings("deprecation")
public class HibernateUtil {

    private static final String ARCHE_TYPE_ARTIFACT_ID = "maven-archetype-webapp";
    private static final String ARCHE_TYPE_ARTIFACT_VERSION = "5.0.3";

    private static final String ARTIFACT_ID = "my-webapp";

    private static final String GROUP_ID = "com.mycompany";

    private static final String PACKAGE_NAME = "com.mycompany.app";

    private static final String ARTIFACT_GROUP_ID = "com.mycompany.app";

    /**
     * Location of hibernate.cfg.xml file. Location should be on the classpath as Hibernate uses #resourceAsStream style lookup for its configuration
     * file. The default classpath location of the hibernate config file is in the default package. Use #setConfigFile() to update the location of the
     * configuration file for the current session.
     */
    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";

    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

     private static Configuration configuration = new Configuration();
     //FIXME Use AnnotationConfiguration in stead
//    private static Configuration configuration = new AnnotationConfiguration();

    // configuration = new AnnotationConfiguration();

    private static org.hibernate.SessionFactory sessionFactory;

    private static String configFile = CONFIG_FILE_LOCATION;

    static {
        String s = "mvn archetype:create -DarchetypeGroupId="
                + ARTIFACT_GROUP_ID
                + " -DarchetypeArtifactId="
                + ARCHE_TYPE_ARTIFACT_ID
                + " -DarchetypeVersion="
                + ARCHE_TYPE_ARTIFACT_VERSION
                + " -DgroupId="
                + GROUP_ID
                + " -DartifactId="
                + ARTIFACT_ID
                + " -DpackageName="
                + PACKAGE_NAME;
        try {
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }

    private HibernateUtil() {
    }

    /**
     * Returns the ThreadLocal Session instance. Lazy initialize the <code>SessionFactory</code> if needed.
     * 
     * @return Session
     * @throws HibernateException
     */
    @SuppressWarnings("cast")
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {
                rebuildSessionFactory();
            }
            session = (sessionFactory != null) ? sessionFactory.openSession() : null;
            threadLocal.set(session);
        }

        return session;
    }

    /**
     * Rebuild hibernate session factory
     * 
     */
    public static void rebuildSessionFactory() {
        try {
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }

    /**
     * Close the single hibernate session instance.
     * 
     * @throws HibernateException
     */
    @SuppressWarnings("cast")
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

    /**
     * return session factory
     * 
     */
    public static org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * return session factory
     * 
     * session factory will be rebuilded in the next call
     */
    public static void setConfigFile(String configFile) {
        HibernateUtil.configFile = configFile;
        sessionFactory = null;
    }

    /**
     * return hibernate configuration
     * 
     */
    public static Configuration getConfiguration() {
        return configuration;
    }

}