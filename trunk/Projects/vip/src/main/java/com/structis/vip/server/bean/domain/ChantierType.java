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

@Entity
@Table(name = "CTY_CHANTIER_TYPE")
public class ChantierType extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "cty_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "cty_label")
    private String label;

    @Column(name = "cty_endDate")
    private Date endDate;

    @Column(name = "cty_isSubdelegable")
    private Integer isSubdelegable;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ent_id", nullable = false)
    private Entite entite = new Entite();

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

    public Entite getEntite() {
        return this.entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getIsSubdelegable() {
        return this.isSubdelegable;
    }

    public void setIsSubdelegable(Integer isSubdelegable) {
        this.isSubdelegable = isSubdelegable;
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }

}
