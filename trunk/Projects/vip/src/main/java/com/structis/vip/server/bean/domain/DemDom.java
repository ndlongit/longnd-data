package com.structis.vip.server.bean.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "DEM_DOM")
public class DemDom extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "dem_dom_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "dem_group", nullable = false)
    private Integer group;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "dom_id", nullable = false)
    DocumentMdl documentMdl;

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

    public DocumentMdl getDocumentMdl() {
        return this.documentMdl;
    }

    public void setDocumentMdl(DocumentMdl documentMdl) {
        this.documentMdl = documentMdl;
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }

    public Integer getGroup() {
        return this.group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }
}
