package com.structis.vip.shared;

import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DelegationStatusModel;
import com.structis.vip.shared.model.DelegationTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public class DelegationFilter extends BasePagingLoadConfig implements java.io.Serializable {

    private static final long serialVersionUID = 3727392202555197890L;
    private EntiteModel entite;
    private PerimetreModel perimetre;
    private DelegationNatureModel nature;
    private CollaborateurModel delegant;
    private CollaborateurModel delegataire;
    private DelegationTypeModel type;
    private DelegationStatusModel status;

    private List<DelegationNatureModel> natures;
    private List<CollaborateurModel> delegants;
    private List<CollaborateurModel> delegataires;
    private List<DelegationTypeModel> types;
    private List<DelegationStatusModel> statuses;

    private PerimetreTreeModel perimetreTreeModel;

    private Boolean isDisplayAllLevel;
    private Boolean sep;
    private Boolean conjointe;
    private Date startDate;
    private Date endDate;

    private List<UserRoleModel> userRoles;

    public EntiteModel getEntite() {
        return this.entite;
    }

    public void setEntite(EntiteModel entite) {
        this.entite = entite;
    }

    public PerimetreModel getPerimetre() {
        return this.perimetre;
    }

    public void setPerimetre(PerimetreModel perimetre) {
        this.perimetre = perimetre;
    }

    public DelegationNatureModel getNature() {
        return this.nature;
    }

    public void setNature(DelegationNatureModel nature) {
        this.nature = nature;
    }

    public CollaborateurModel getDelegant() {
        return this.delegant;
    }

    public void setDelegant(CollaborateurModel delegant) {
        this.delegant = delegant;
    }

    public CollaborateurModel getDelegataire() {
        return this.delegataire;
    }

    public void setDelegataire(CollaborateurModel delegataire) {
        this.delegataire = delegataire;
    }

    public DelegationTypeModel getType() {
        return this.type;
    }

    public void setType(DelegationTypeModel type) {
        this.type = type;
    }

    public DelegationStatusModel getStatus() {
        return this.status;
    }

    public void setStatus(DelegationStatusModel status) {
        this.status = status;
    }

    public Boolean getIsDisplayAllLevel() {
        return this.isDisplayAllLevel;
    }

    public void setIsDisplayAllLevel(Boolean isDisplayAllLevel) {
        this.isDisplayAllLevel = isDisplayAllLevel;
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

    public List<DelegationNatureModel> getNatures() {
        return this.natures;
    }

    public void setNatures(List<DelegationNatureModel> natures) {
        this.natures = natures;
    }

    public List<CollaborateurModel> getDelegants() {
        return this.delegants;
    }

    public void setDelegants(List<CollaborateurModel> delegants) {
        this.delegants = delegants;
    }

    public List<CollaborateurModel> getDelegataires() {
        return this.delegataires;
    }

    public void setDelegataires(List<CollaborateurModel> delegataires) {
        this.delegataires = delegataires;
    }

    public List<DelegationTypeModel> getTypes() {
        return this.types;
    }

    public void setTypes(List<DelegationTypeModel> types) {
        this.types = types;
    }

    public List<DelegationStatusModel> getStatuses() {
        return this.statuses;
    }

    public void setStatuses(List<DelegationStatusModel> statuses) {
        this.statuses = statuses;
    }

    public PerimetreTreeModel getPerimetreTreeModel() {
        return this.perimetreTreeModel;
    }

    public void setPerimetreTreeModel(PerimetreTreeModel perimetreTreeModel) {
        this.perimetreTreeModel = perimetreTreeModel;
    }

    public List<UserRoleModel> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(List<UserRoleModel> userRoles) {
        this.userRoles = userRoles;
    }
}
