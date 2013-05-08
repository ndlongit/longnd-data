// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.interceptors;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import vn.pyco.tinycms.model.Node;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Repository("nodeInterceptor")
public class NodeInterceptorImpl extends EmptyInterceptor {
    private static final long serialVersionUID = 1L;

    /*
     * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Node) {
            setPathField((Node) entity, state, propertyNames);
            return true;
        }
        
        return false;
    }
    
    /*
     * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
                                    String[] propertyNames, Type[] types) {
        if (entity instanceof Node) {
            setPathField((Node) entity, currentState, propertyNames);
            return true;
        }
        
        return false;
    }
    
    private void setPathField(Node node, Object[] state, String[] propertyNames) {
        String pathProperty = Node.PROP_PATH;
        String value = "";
        if (node.getParent() != null) {
            value += node.getParent().getPath();
        }
        value += "/" + node.getAlias();
        
        for(int i = 0; i < propertyNames.length; i++) {
            if(propertyNames[i].equals(pathProperty)) {
                if((state[i] == null) || (state[i] != value)) {
                    state[i] = value;
                    break;
                }
            }
        }
    }
}
