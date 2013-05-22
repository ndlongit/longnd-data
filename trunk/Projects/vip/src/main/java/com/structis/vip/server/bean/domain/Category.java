package com.structis.vip.server.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

/**
 * The persistent class for the CAT_CATEGORY database table.
 * 
 */
@Entity
@Table(name = "CAT_CATEGORY")
public class Category extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "cat_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "cat_name")
    private String name;

    public Category() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer lagId) {
        this.id = lagId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String lagName) {
        this.name = lagName;
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
