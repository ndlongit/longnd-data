package org.java.demo.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.java.demo.constant.AppConstants;
import org.java.demo.util.AppUtil;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractTimestampEntity implements Timestampable {

    private Date createdDate;
    private Date modifiedDate;

    @Override
    @Column(nullable = true, insertable = true, updatable = false)
    public Date getCreatedDate() {
        return this.createdDate;
    }

    @Override
    public void setCreatedDate(Date date) {
        this.createdDate = date;
    }

    @Override
    @Column(nullable = true, insertable = true, updatable = true)
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    @Override
    public void setModifiedDate(Date date) {
        this.modifiedDate = date;
    }

    @Transient
    @PrePersist
    public void prePersist() {
        this.setCreatedDate(new Date());
        this.setModifiedDate(new Date());
    }

    @Transient
    @PreUpdate
    public void preUpdate() {
        this.setModifiedDate(new Date());
    }

    @Transient
    public String getModifiedDateString() {
        return AppUtil.dateToString(getModifiedDate(), AppConstants.DATETIME_FORMAT);
    }

    @Transient
    public String getCreatedDateString() {
        return AppUtil.dateToString(getCreatedDate(), AppConstants.DATETIME_FORMAT);
    }
}
