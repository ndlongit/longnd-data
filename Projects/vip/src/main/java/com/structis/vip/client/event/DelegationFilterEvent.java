package com.structis.vip.client.event;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DelegationStatusModel;
import com.structis.vip.shared.model.DelegationTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class DelegationFilterEvent extends GwtEvent<DelegationFilterHandler> {

    private static Type<DelegationFilterHandler> TYPE = new Type<DelegationFilterHandler>();

    private EntiteModel entiteModel;
    private PerimetreTreeModel perimetreTreeModel;
    private List<DelegationNatureModel> natureModel;
    private List<DelegationStatusModel> statusModel;
    private List<DelegationTypeModel> typeModel;
    private List<CollaborateurModel> delegant;
    private List<CollaborateurModel> delegataire;
    private Date startDate;
    private Date endDate;

    private Boolean sep;
    private Boolean conjointe;
    private Boolean showLevel;
    private int pageSize;

    public DelegationFilterEvent() {
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<DelegationFilterHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<DelegationFilterHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DelegationFilterHandler handler) {
        handler.onLoadAction(this);
    }

    public EntiteModel getEntiteModel() {
        return this.entiteModel;
    }

    public void setEntiteModel(EntiteModel entiteModel) {
        this.entiteModel = entiteModel;
    }

    public PerimetreTreeModel getPerimetreTreeModel() {
        return this.perimetreTreeModel;
    }

    public void setPerimetreTreeModel(PerimetreTreeModel perimetreTreeModel) {
        this.perimetreTreeModel = perimetreTreeModel;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<DelegationNatureModel> getNatureModel() {
        return this.natureModel;
    }

    public void setNatureModel(List<DelegationNatureModel> natureModel) {
        this.natureModel = natureModel;
    }

    public List<DelegationStatusModel> getStatusModel() {
        return this.statusModel;
    }

    public void setStatusModel(List<DelegationStatusModel> statusModel) {
        this.statusModel = statusModel;
    }

    public List<DelegationTypeModel> getTypeModel() {
        return this.typeModel;
    }

    public void setTypeModel(List<DelegationTypeModel> typeModel) {
        this.typeModel = typeModel;
    }

    public List<CollaborateurModel> getDelegant() {
        return this.delegant;
    }

    public void setDelegant(List<CollaborateurModel> delegant) {
        this.delegant = delegant;
    }

    public List<CollaborateurModel> getDelegataire() {
        return this.delegataire;
    }

    public void setDelegataire(List<CollaborateurModel> delegataire) {
        this.delegataire = delegataire;
    }

    public Boolean getSep() {
        return this.sep;
    }

    public void setSep(Boolean sep) {
        this.sep = sep;
    }

    public Boolean getConjointe() {
        return this.conjointe;
    }

    public void setConjointe(Boolean conjointe) {
        this.conjointe = conjointe;
    }

    public Boolean getShowLevel() {
        return this.showLevel;
    }

    public void setShowLevel(Boolean showLevel) {
        this.showLevel = showLevel;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
