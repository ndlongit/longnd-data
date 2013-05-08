// [LICENCE-HEADER]
//
package vn.pyco.commons.model.impl;

import java.io.Serializable;

import vn.pyco.commons.model.Identifiable;

/**
 * Abstract entity with equals/hashCode is calculated based on Identifiable.getId()
 *
 */
public abstract class AbstractEntity<T extends Serializable> implements Identifiable<T> {

	private static final long serialVersionUID = 2246441558976590996L;

	@SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if(obj == null) {
        	return false;
        }
        
        if(getId() == null) {
        	return false;
        }
        
        if (obj instanceof Identifiable) {
            Identifiable<T> other = (Identifiable<T>) obj;
            return getId().equals(other.getId());
        }

        return false;
    }
}
