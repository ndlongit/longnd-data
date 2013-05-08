// [LICENCE-HEADER]
//
package vn.pyco.commons.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class ChainedInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = 1L;

    private Set<Interceptor> _interceptors;

    public ChainedInterceptor() {
        this(new HashSet<Interceptor>());
    }

    @Autowired
    public ChainedInterceptor(Set<Interceptor> interceptors) {
        super();
        _interceptors = interceptors;
    }
    
    /**
     * @return the interceptors
     */
    public Set<Interceptor> getInterceptors() {
        return _interceptors;
    }
    
    /**
     * @param interceptors the interceptors to set
     */
    public void setInterceptors(Set<Interceptor> interceptors) {
        _interceptors = interceptors;
    }
    
    /*
     * @see org.hibernate.EmptyInterceptor#onLoad(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state,
                                    String[] propertyNames, Type[] types) {
        boolean result = false;
        for (Iterator iterator = _interceptors.iterator(); iterator.hasNext();) {
            Interceptor interceptor = (Interceptor) iterator.next();
            if (interceptor.onLoad(entity, id, state, propertyNames, types)) {
                /* Returns true if one interceptor in the chain has 
                 * modified the object state 
                 */
                result = true;
            }
        }
        return result;
    }
    
    /*
     * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onFlushDirty(Object entity, Serializable id,
                                    Object[] currentState,
                                    Object[] previousState,
                                    String[] propertyNames, Type[] types) {
        boolean result = false;
        for (Iterator iterator = _interceptors.iterator(); iterator.hasNext();) {
            Interceptor interceptor = (Interceptor) iterator.next();
            if (interceptor.onFlushDirty(entity, id, currentState, previousState, propertyNames, types)) {
                 /* Returns true if one interceptor in the chain has modified 
                  * the object current state result = true;
                  */
                result = true;
             }
        }
        return result;
    }
    
    /*
     * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,
                                    String[] propertyNames, Type[] types) {
        boolean result = false;
        for (Iterator iterator = _interceptors.iterator(); iterator.hasNext();) {
            Interceptor interceptor = (Interceptor) iterator.next();
            if (interceptor.onSave(entity, id, state, propertyNames, types)) {
                /* Returns true if one interceptor in the chain has
                 * modified the object state result = true;
                 */
                result = true;
            }
        }
        return result;
    }
    
    /*
     * @see org.hibernate.EmptyInterceptor#onDelete(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public void onDelete(Object entity, Serializable id, Object[] state,
                                    String[] propertyNames, Type[] types) {
        for (Iterator iterator = _interceptors.iterator(); iterator.hasNext();) {
            Interceptor interceptor = (Interceptor) iterator.next();
            interceptor.onDelete(entity, id, state, propertyNames, types);
        }
    }
    
    /*
     * @see org.hibernate.EmptyInterceptor#findDirty(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public int[] findDirty(Object entity, Serializable id,
                                    Object[] currentState,
                                    Object[] previousState,
                                    String[] propertyNames, Type[] types) {
        int[] result = null;
        for (Iterator iterator = _interceptors.iterator(); iterator.hasNext();) {
            Interceptor interceptor = (Interceptor) iterator.next();
            result =
                interceptor.findDirty(entity, id, currentState, previousState, propertyNames, types);
            if (result != null) {
                /* If any interceptor has returned something not null,
                 * stop the chain
                 */
                break;
            }
        }
        return result;
    }
    
    /*
     * @see org.hibernate.EmptyInterceptor#preFlush(java.util.Iterator)
     */
    @Override
    public void preFlush(Iterator entities) {
        List entityList = createList(entities);
        for (Iterator iterator = _interceptors.iterator(); iterator.hasNext();) {
            Interceptor interceptor = (Interceptor) iterator.next();
            interceptor.preFlush(entityList.iterator());
        }
    }
    
    /*
     * @see org.hibernate.EmptyInterceptor#postFlush(java.util.Iterator)
     */
    @Override
    public void postFlush(Iterator entities) {
        List entityList = createList(entities);
        for (Iterator iterator = _interceptors.iterator(); iterator.hasNext();) {
            Interceptor interceptor = (Interceptor) iterator.next();
            interceptor.postFlush(entityList.iterator());
        }
    }
    
    @SuppressWarnings("unchecked")
    private List createList(Iterator iterator) {
        List list = new ArrayList();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}
