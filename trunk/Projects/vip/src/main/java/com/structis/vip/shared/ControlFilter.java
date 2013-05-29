package com.structis.vip.shared;

import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.KeyValueModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public class ControlFilter extends BasePagingLoadConfig implements java.io.Serializable {

    private static final long serialVersionUID = 3727392202555197890L;
    private EntiteModel entite;
    private PerimetreModel perimetre;

    private List<ControlTypeModel> types;
    private PerimetreTreeModel perimetreTreeModel;

    private Date startDate;
    private Date endDate;
    private String codeProject;
    private List<KeyValueModel> caracteres;
    private String controllerName;

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

    public List<ControlTypeModel> getTypes() {
        return this.types;
    }

    public void setTypes(List<ControlTypeModel> types) {
        this.types = types;
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

    public String getCodeProject() {
        return this.codeProject;
    }

    public void setCodeProject(String codeProject) {
        this.codeProject = codeProject;
    }

    public List<KeyValueModel> getCaracteres() {
        return this.caracteres;
    }

    public void setCaracteres(List<KeyValueModel> caracteres) {
        this.caracteres = caracteres;
    }

    public String getControllerName() {
        return this.controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
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
