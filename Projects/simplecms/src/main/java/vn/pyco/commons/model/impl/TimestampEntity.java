package vn.pyco.commons.model.impl;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.tapestry5.beaneditor.NonVisual;

import vn.pyco.commons.model.Timestampable;

@MappedSuperclass
public class TimestampEntity extends NumericIdEntity implements Timestampable {

	private static final long serialVersionUID = 4417851839047350630L;

	private Date _createdDate;
	private Date _updatedDate;
	private String _createdBy;
	private String _updatedBy;
	
	/*
	 * @see vn.pyco.commons.model.Timestampable#getCreatedDate()
	 */
	@Override
	@Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_DATE", nullable=false)
	public Date getCreatedDate() {
	    return _createdDate;
	}
	
	/*
	 * @see vn.pyco.commons.model.Timestampable#setCreatedDate(java.util.Date)
	 */
	@Override
    @NonVisual
	public void setCreatedDate(Date createdDate) {
	    _createdDate = createdDate;
	}
	
	/*
	 * @see vn.pyco.commons.model.Timestampable#getCreatedBy()
	 */
	@Override
	@Column(name="CREATED_BY", length=24)
	public String getCreatedBy() {
	    return _createdBy;
	}
	
	/*
	 * @see vn.pyco.commons.model.Timestampable#setCreatedBy(java.lang.String)
	 */
	@Override
	@NonVisual
	public void setCreatedBy(String createdBy) {
	    _createdBy = createdBy;
	}
	
	/*
	 * @see vn.pyco.commons.model.Timestampable#getUpdatedDate()
	 */
	@Override
	@Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATED_DATE", nullable=false)
	public Date getUpdatedDate() {
	    return _updatedDate;
	}
	
	/*
	 * @see vn.pyco.commons.model.Timestampable#setUpdatedDate(java.util.Date)
	 */
	@Override
	@NonVisual
	public void setUpdatedDate(Date updatedDate) {
	    _updatedDate = updatedDate;
	}
	
	/*
	 * @see vn.pyco.commons.model.Timestampable#getUpdatedBy()
	 */
	@Override
	@Column(name="UPDATED_BY", length=24)
	public String getUpdatedBy() {
	    return _updatedBy;
	}
	
	/*
	 * @see vn.pyco.commons.model.Timestampable#setUpdatedBy(java.lang.String)
	 */
	@Override
	@NonVisual
	public void setUpdatedBy(String updatedBy) {
	    _updatedBy = updatedBy;
	}
}
