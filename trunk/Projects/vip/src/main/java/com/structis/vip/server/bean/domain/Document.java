package com.structis.vip.server.bean.domain;

import java.util.Date;

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

/**
 * The persistent class for the DOC_DOCUMENT database table.
 * 
 */
@Entity
@Table(name = "DOC_DOCUMENT")
public class Document extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "doc_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "doc_name")
    private String name;

    @Column(name = "doc_link")
    private String link;

    @Column(name = "doc_comments", nullable = true)
    private String comment;

    @Column(name = "doc_date", nullable = true)
    private Date date;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "cat_id", nullable = false)
    Category category;

    public Document() {
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String filename) {
        this.link = filename;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }
}
