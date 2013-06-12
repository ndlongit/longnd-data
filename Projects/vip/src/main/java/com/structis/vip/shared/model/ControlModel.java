package com.structis.vip.shared.model;

import java.util.Date;
import java.util.List;

public class ControlModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1;
    public static final String CTL_CODEPROJECT = "codeProject";
    public static final String CTL_PERIEMTRE = "perimetre";
    public static final String CTL_PERIEMTRE_PARENT = "perimetreParent";
    public static final String CTL_CONTROL_TYPE = "controlType";
    public static final String CTL_DATE = "date";
    public static final String CTL_CHARACTER = "character";
    public static final String CTL_COLLABORATEUR = "collaborateur";
    public static final String CTL_REPORT = "rapport";

    public static final String CAN_MODIFIED = "canModified";
    public static final String CAN_VIEWED = "canViewed";
    public static final String CAN_DELETED = "canDeleted";
    public static final String CAN_EXPORTED = "canExported";

    public static final String INTERNAL = "interne";
    public static final String EXTERNAL = "externe";
    public static final String EXTERNAL_CONTROL_NAMES = "extControlNames";

    @SuppressWarnings("unused")
    private PerimetreModel perModel;
    @SuppressWarnings("unused")
    private ControlTypeModel controlTypeModel;
    @SuppressWarnings("unused")
    private CollaborateurModel collaborateurModel;

    private List<ExtControllerControlModel> externalControllers;

    public String getCodeProject() {
        return this.get(CTL_CODEPROJECT);
    }

    public void setCodeProject(String codeProject) {
        this.set(CTL_CODEPROJECT, codeProject);
    }

    public PerimetreModel getPerimetre() {
        return this.get(CTL_PERIEMTRE);
    }

    public void setPerimetre(PerimetreModel perimetre) {
        this.set(CTL_PERIEMTRE, perimetre);
    }

    public String getPerimetreParent() {
        return this.get(CTL_PERIEMTRE_PARENT);
    }

    public void setPerimetreParent(String perimetreParent) {
        this.set(CTL_PERIEMTRE_PARENT, perimetreParent);
    }

    public ControlTypeModel getControlType() {
        return this.get(CTL_CONTROL_TYPE);
    }

    public void setControlType(ControlTypeModel controlType) {
        this.set(CTL_CONTROL_TYPE, controlType);
    }

    public Date getDate() {
        return this.get(CTL_DATE);
    }

    public void setDate(Date date) {
        this.set(CTL_DATE, date);
    }

    public String getCharacter() {
        return this.get(CTL_CHARACTER);
    }

    public void setCharacter(String character) {
        this.set(CTL_CHARACTER, character);
    }

    public CollaborateurModel getCollaborateur() {
        return this.get(CTL_COLLABORATEUR);
    }

    public void setCollaborateur(CollaborateurModel collaborateur) {
        this.set(CTL_COLLABORATEUR, collaborateur);
    }

    public String getRapport() {
        return this.get(CTL_REPORT);
    }

    public void setRapport(String rapport) {
        this.set(CTL_REPORT, rapport);
    }

    public Boolean getCanModified() {
        return this.get(CAN_MODIFIED);
    }

    public void setCanModified(Boolean canModified) {
        this.set(CAN_MODIFIED, canModified);
    }

    public Boolean getCanViewed() {
        return this.get(CAN_VIEWED);
    }

    public void setCanViewed(Boolean canViewed) {
        this.set(CAN_VIEWED, canViewed);
    }

    public Boolean getCanDeleted() {
        return this.get(CAN_DELETED);
    }

    public void setCanDeleted(Boolean canDeleted) {
        this.set(CAN_DELETED, canDeleted);
    }

    public Boolean getCanExported() {
        return this.get(CAN_EXPORTED);
    }

    public void setCanExported(Boolean canExported) {
        this.set(CAN_EXPORTED, canExported);
    }

    public void removeUnusedOnList() {
        this.setRapport(null);
        this.setCodeProject(null);
        this.getCollaborateur().removeUnusedOnList();
        this.setCollaborateur(null);
        this.getControlType().removeUnusedOnList();
        this.setControlType(null);
        this.setDate(null);
        this.setDateSuppr(null);
        this.setLibelle(null);
        this.getPerimetre().removeUnusedOnList();
        this.setPerimetre(null);
        this.setPerimetreParent(null);
    }

    public void setExternControllers(List<ExtControllerControlModel> models) {
        this.externalControllers = models;
    }

    public List<ExtControllerControlModel> getExternControllers() {
        return this.externalControllers;
    }

    public String getExtControlNames() {
        return this.get(EXTERNAL_CONTROL_NAMES);
    }

    public void setExtControlNames(String extControlNames) {
        this.set(EXTERNAL_CONTROL_NAMES, extControlNames);
    }

    public void updateExtControllerNames() {
        StringBuffer extNames = new StringBuffer();
        if (this.externalControllers != null) {
            for (ExtControllerControlModel e : this.externalControllers) {
                extNames.append("<br>").append(e.getExternalController().getName());
            }
            if (extNames != null && extNames.length() > 0) {
                this.set(EXTERNAL_CONTROL_NAMES, extNames.toString().substring(4));
            }
        } else {
            this.set(EXTERNAL_CONTROL_NAMES, "");
        }
    }

    public void setPermissionByRole(boolean isView, boolean isModify) {
        this.setCanViewed(isView);
        this.setCanExported(isView);
        this.setCanDeleted(isModify);
        this.setCanModified(isModify);
    }
}
