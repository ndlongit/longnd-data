// [LICENCE-HEADER]
//
package vn.pyco.commons.model.impl;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import vn.pyco.commons.model.Undeletable;

/**
 * An entity that is deletable
 *
 */
@MappedSuperclass
public class UndeletableEntity extends NumericIdEntity implements Undeletable {
	private static final long serialVersionUID = 2476722155425802235L;

	private boolean _deleted;
    
    /*
     * (non-Javadoc)
     * @see vn.pyco.commons.model.Hiddenable#isHidden()
     */
    @Override
    @Column(name = "IS_DELETED")
    public boolean isDeleted() {
        return _deleted;
    }
    
    /*
     * (non-Javadoc)
     * @see vn.pyco.commons.model.Hiddenable#setHidden(boolean)
     */
    @Override
    public void setDeleted(boolean deleted) {
        _deleted = deleted;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        return String.format("%s{id: %s; deleted: %s}", className, getId(), isDeleted());
    }
}
