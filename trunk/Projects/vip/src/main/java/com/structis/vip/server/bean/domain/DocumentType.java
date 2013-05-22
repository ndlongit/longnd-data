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
@Table(name = "DOT_DOCUMENT_TYPE")
public class DocumentType extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "dot_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "dot_code")
    private String code;

    @Column(name = "dot_description")
    private String description;

    public DocumentType() {
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

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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
