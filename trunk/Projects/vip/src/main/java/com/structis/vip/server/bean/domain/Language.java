package com.structis.vip.server.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

/**
 * The persistent class for the LAG_LANGUAGE database table.
 * 
 */
@Entity
@Table(name = "LAG_LANGUAGE")
public class Language extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "lag_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "lag_code")
    private String code;

    @Column(name = "lag_name")
    private String name;

    @Column(name = "lag_isDefault")
    private Integer isDefault;

    public Language() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer lagId) {
        this.id = lagId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String lagCode) {
        this.code = lagCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String lagName) {
        this.name = lagName;
    }

    public Integer getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public Integer getPrimaryKey() {
        return this.getId();
    }

    @Override
    public boolean isPrimaryKeySet() {
        return (this.getId() != null);
    }

    @Override
    public void setPrimaryKey(Integer id) {
        this.setId(id);
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }
}
