// [LICENCE-HEADER]
//
package vn.pyco.commons.dao.hibernate;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.HibernatePersistence;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class ConfigurableHibernatePersistence extends HibernatePersistence {
    private Interceptor _interceptor;
    private Ejb3Configuration _configuration;
        
    /**
     * @return the interceptor
     */
    public Interceptor getInterceptor() {
        return _interceptor;
    }
    
    /**
     * @param interceptor the interceptor to set
     */
    public void setInterceptor(Interceptor interceptor) {
        _interceptor = interceptor;
    }
    
    /**
     * @param configuration the configuration to set
     */
    public void setConfiguration(Ejb3Configuration configuration) {
        _configuration = configuration;
    }
    
    /**
     * @return the configuration
     */
    public Ejb3Configuration getConfiguration() {
        return _configuration;
    }
    
    /*
     * @see org.hibernate.ejb.HibernatePersistence#createContainerEntityManagerFactory(javax.persistence.spi.PersistenceUnitInfo, java.util.Map)
     */
    @Override
    public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map map) {
        Ejb3Configuration cfg = new Ejb3Configuration();
        Ejb3Configuration configured = cfg.configure(info, map);
        postProcessConfiguration(info, map, configured);
        
        return configured != null ? configured.buildEntityManagerFactory() : super.createContainerEntityManagerFactory(info, map);
    }
    
    private void postProcessConfiguration(PersistenceUnitInfo info, Map map, Ejb3Configuration configured) {
        _configuration = configured;
        
        if (_interceptor != null) {
            if (configured.getInterceptor()==null || EmptyInterceptor.class.equals(configured.getInterceptor().getClass())) {
                configured.setInterceptor(_interceptor);
                return;
            }
            throw new IllegalStateException("Hibernate interceptor already set in persistence.xml (" + configured.getInterceptor() + ")");
        }
    }
}
