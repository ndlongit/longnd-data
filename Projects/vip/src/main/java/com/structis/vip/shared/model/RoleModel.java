package com.structis.vip.shared.model;

import com.structis.vip.shared.model.core.SimpleModel;

public class RoleModel extends SimpleModel {

    private static final long serialVersionUID = 1L;

    public static final String ADMINISTRATEUR_SUPER = "ADMINISTRATEUR_SUPER";
    public static final String ADMINISTRATEUR_APPLICATIF = "ADMINISTRATEUR_APPLICATIF";
    public static final String ADMINISTRATEUR_UO = "ADMINISTRATEUR_UO";
    public static final String DELEGATION_LECTURE = "LECTURE (DEL)";
    public static final String DELEGATION_MODIFICATION = "CREATION/MODIFICATION (DEL)";
    public static final String DELEGATION_VALIDATION = "VALIDATION (DEL)";

    public static final String CONTROL_LECTURE = "LECTURE (CTL)";
    public static final String CONTROL_MODIFICATION = "CREATION/MODIFICATION (CTL)";

    public static final String ROLE_TYPE = "type";

    public String getType() {
        return this.get(ROLE_TYPE);
    }

    public void setType(String type) {
        this.set(ROLE_TYPE, type);
    }

    public boolean isAdministrateur() {
        if (RoleModel.ADMINISTRATEUR_APPLICATIF.equals(this.getName()) || (RoleModel.ADMINISTRATEUR_UO.equals(this.getName()))
                || (RoleModel.ADMINISTRATEUR_SUPER.equals(this.getName()))) {
            return true;
        }
        return false;
    }

    public boolean isSuperAdmin() {
        if (RoleModel.ADMINISTRATEUR_SUPER.equals(this.getName())) {
            return true;
        }
        return false;
    }

    public boolean isApplicationAdmin() {
        if (RoleModel.ADMINISTRATEUR_APPLICATIF.equals(this.getName()) || this.isSuperAdmin()) {
            return true;
        }
        return false;
    }

    public boolean isUoAdmin() {
        if (RoleModel.ADMINISTRATEUR_UO.equals(this.getName()) || this.isApplicationAdmin()) {
            return true;
        }
        return false;
    }

    public boolean isModificationDelegation() {
        if (RoleModel.DELEGATION_MODIFICATION.equals(this.getName()) || this.isAdministrateur()) {
            return true;
        }
        return false;
    }

    public boolean isValidationDelegation() {
        if (RoleModel.DELEGATION_VALIDATION.equals(this.getName()) || this.isAdministrateur()) {
            return true;
        }
        return false;
    }

    public boolean isLectureDelegation() {
        if (RoleModel.DELEGATION_LECTURE.equals(this.getName()) || this.isModificationDelegation() || this.isValidationDelegation()
                || this.isAdministrateur()) {
            return true;
        }
        return false;
    }

    public boolean isModificationControl() {
        if (RoleModel.CONTROL_MODIFICATION.equals(this.getName()) || this.isAdministrateur()) {
            return true;
        }
        return false;
    }

    public boolean isLectureControl() {
        if (RoleModel.CONTROL_LECTURE.equals(this.getName()) || this.isModificationControl() || this.isValidationDelegation()
                || this.isAdministrateur()) {
            return true;
        }
        return false;
    }
}