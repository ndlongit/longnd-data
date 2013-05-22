package com.structis.vip.shared.model;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

/**
 * Base model pour le tree model
 * 
 * @author b.brotosumpeno
 * 
 */
public abstract class BaseTreeModelDataActivable extends BaseTreeModel implements ModelActivable {

    private static final long serialVersionUID = 1L;

    @Override
    public Integer getId() {
        return this.get(BASE_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(BASE_ID, id);
    }

    @Override
    public String getLibelle() {
        return this.get(BASE_LIBELLE);
    }

    @Override
    public void setLibelle(String libelle) {
        this.set(BASE_LIBELLE, libelle);
    }

    @Override
    public Boolean getActive() {
        return this.get(BASE_ACTIVE);
    }

    @Override
    public void setActive(Boolean active) {
        this.set(BASE_ACTIVE, active);
    }

    @Override
    public void setDateSuppr(Date dateSuppr) {
        this.set(BASE_DATESUPPR, dateSuppr);
    }

    @Override
    public Date getDateSuppr() {
        return this.get(BASE_DATESUPPR);
    }

}
