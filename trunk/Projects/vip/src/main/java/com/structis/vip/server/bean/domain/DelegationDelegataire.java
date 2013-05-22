package com.structis.vip.server.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "DED_DELEGATION_DELEGATAIRE")
public class DelegationDelegataire extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "ded_id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "del_id")
    private Integer delId;

    @Column(name = "col_id")
    private Integer colId;

    @Transient
    private String colName;

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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDelId() {
        return this.delId;
    }

    public void setDelId(Integer delId) {
        this.delId = delId;
    }

    public Integer getColId() {
        return this.colId;
    }

    public void setColId(Integer colId) {
        this.colId = colId;
    }

    public String getColName() {
        return this.colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public DelegationDelegataire() {
        super();
    }

    public DelegationDelegataire(Integer id, Integer delId, Integer colId, String colName) {
        super();
        this.id = id;
        this.delId = delId;
        this.colId = colId;
        this.colName = colName;
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }

}
