// [LICENCE-HEADER]
//
package vn.pyco.commons.model.impl;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.tapestry5.beaneditor.NonVisual;

import vn.pyco.commons.model.Activable;
import vn.pyco.commons.model.Undeletable;

/**
 * Entity with full status: Identifiable, Activable, Hiddenable and Timestampable
 *
 */
@MappedSuperclass
public class StatefulEntity extends TimestampEntity implements Activable, Undeletable {
    private static final long serialVersionUID = 1L;
    
    private boolean _active = true;
    
    private boolean _deleted = false;

    /*
     * (non-Javadoc)
     * @see vn.pyco.commons.model.Activable#setActive(boolean)
     */
    @Override
    public void setActive(boolean active) {
        _active = active;
    }
    	
	@Override
	@Column(name="IS_ACTIVE")
	public boolean isActive() {
		return _active;
	}

	@Override
	@Column(name="IS_DELETED")
	@NonVisual
	public boolean isDeleted() {
		return _deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		_deleted=deleted;
	}
    
    /*
     * (non-Javadoc)
     * @see vn.pyco.commons.model.impl.BasicEntity#toString()
     */
    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        return String.format("%s{id: %s; active: %s; createdDate: %s; createdBy: %s; updatedDate: %s; modifiedBy: %s; deleted: %s}", 
                        className, getId(), isActive(), getCreatedDate(), getCreatedBy(), getUpdatedDate(), getUpdatedBy(), isDeleted());
    }

}
