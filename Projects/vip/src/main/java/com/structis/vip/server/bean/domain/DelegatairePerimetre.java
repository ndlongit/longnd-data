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

/**
 * The persistent class for the LAG_LANGUAGE database table.
 * 
 */
@Entity
@Table(name = "DRP_DELEGATAIRE_PERIMETRE")
public class DelegatairePerimetre extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "drp_id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "col_id", nullable = false)
    private Collaborateur delegataire;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "per_id", nullable = false)
    private Perimetre perimetre;

    public DelegatairePerimetre() {
    }

    @Override
    public Integer getPrimaryKey() {
        return this.getId();
    }

    @Override
    public void setPrimaryKey(Integer id) {
        this.setId(id);
    }

    @Override
    public boolean isPrimaryKeySet() {
        return (this.getId() != null);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collaborateur getDelegataire() {
        return this.delegataire;
    }

    public void setDelegataire(Collaborateur delegataire) {
        this.delegataire = delegataire;
    }

    public Perimetre getPerimetre() {
        return this.perimetre;
    }

    public void setPerimetre(Perimetre perimetre) {
        this.perimetre = perimetre;
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }
}
