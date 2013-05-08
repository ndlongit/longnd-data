// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.interceptors;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.pyco.commons.model.Timestampable;
import vn.pyco.tinycms.services.SecurityService;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Repository("timestampInterceptor")
public class TimestampInterceptorImpl extends EmptyInterceptor {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private SecurityService _securityService;

    /*
     * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Timestampable) {
            Date dateModified = new Date();
            String username = _securityService.getCurrentUsername();
            modifyProperty(Timestampable.PROP_CREATED_DATE, dateModified, state, propertyNames);
            modifyProperty(Timestampable.PROP_CREATED_BY, username, state, propertyNames);
            modifyProperty(Timestampable.PROP_UPDATED_DATE, dateModified, state, propertyNames);
            modifyProperty(Timestampable.PROP_UPDATED_BY, username, state, propertyNames);
            return  true;
        }
        
        return false;
    }
    
    /*
     * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
                                    String[] propertyNames, Type[] types) {
        if (entity instanceof Timestampable) {
            Date dateModified = new Date();
            modifyProperty(Timestampable.PROP_UPDATED_DATE, dateModified, currentState, propertyNames);
            modifyProperty(Timestampable.PROP_UPDATED_BY, _securityService.getCurrentUsername(), currentState, propertyNames);
            
            return  true;
        }
        
        return false;
    }
    
    private boolean modifyProperty(String prop, Object value, Object[] state, String[] propertyNames) {
        boolean modified = false;
        for(int i = 0; i < propertyNames.length; i++) {
            if(propertyNames[i].equals(prop)) {
                if((state[i] == null) || (state[i] != value)) {
                    state[i] = value;
                    modified = true;
                    break;
                }
            }
        }
        return modified;
    }
}
