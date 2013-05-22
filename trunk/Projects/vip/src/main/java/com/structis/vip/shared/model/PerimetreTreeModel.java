package com.structis.vip.shared.model;

import java.util.List;

public class PerimetreTreeModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String PERIMETRE_TREE_ID = "id";
    public static final String PERIMETRE_TREE_NAME = "name";
    public static final String PERIMETRE_TREE_UO_NAME = "uoname";
    public static final String PERIMETRE_TREE_PATH = "path";
    public static final String PERIMETRE_TREE_TYPE = "type";
    public static final String PERIMETRE_TREE_TYPE_NAME = "typeName";
    public static final String PERIMETRE_TREE_ENTITE_ID = "entiteId";
    public static final String PERIMETRE_TREE_IS_PARENT = "isParent";
    public static final String PERIMETRE_TREE_LEVEL = "level";
    public static final String PERIMETRE_TREE_IS_ENTITE = "isEntite";
    public static final String PERIMETRE_TREE_PARENT = "parent";
    public static final String PERIMETRE_TREE_IS_UO_ADMIN = "isUoAdmin";
    public static final String PERIMETRE_TREE_IS_MODIFICATION_DELEGATION = "isModificationDelegation";
    public static final String PERIMETRE_TREE_IS_LECTURE_DELEGATION = "isLectureDelegation";
    public static final String PERIMETRE_TREE_IS_VALIDATION_DELEGATION = "isValidationDelegation";
    public static final String PERIMETRE_TREE_PARENT_NAME = "parentName";
    public static final String PERIMETRE_TREE_IS_MODIFICATION_CONTROL = "isModificationControl";
    public static final String PERIMETRE_TREE_IS_LECTURE_CONTROL = "isLectureControl";

    public PerimetreTreeModel() {
        this.setIsUoAdmin(false);
        this.setIsLectureDelegation(false);
        this.setIsModificationDelegation(false);
        this.setIsValidationDelegation(false);
    }

    public PerimetreTreeModel(PerimetreModel perimetre, List<UserRoleModel> userRoles) {
        this.setIsUoAdmin(false);
        this.setIsLectureDelegation(false);
        this.setIsModificationDelegation(false);
        this.setIsValidationDelegation(false);
        this.setPerId(perimetre.getPerId());
        this.setName(perimetre.getName());
        if (perimetre.getParent() != null) {
            this.setParentName(perimetre.getParent().getName());
        }
        for (UserRoleModel userRole : userRoles) {
            if (userRole.getRole().isApplicationAdmin()) {
                this.setPermissionByRole(userRole.getRole());
            } else if ((userRole.getPerimetre() != null) && (userRole.getPerimetre().getPerId().equals(perimetre.getPerId()))) {
                this.setPermissionByRole(userRole.getRole());
            }
        }
    }

    public String getPerId() {
        return this.get(PERIMETRE_TREE_ID);
    }

    public void setPerId(String id) {
        this.set(PERIMETRE_TREE_ID, id);
    }

    public String getName() {
        return this.get(PERIMETRE_TREE_NAME);
    }

    public void setName(String name) {
        this.set(PERIMETRE_TREE_NAME, name);
    }

    public String getUoname() {
        return this.get(PERIMETRE_TREE_UO_NAME);
    }

    public void setUoname(String uoname) {
        this.set(PERIMETRE_TREE_UO_NAME, uoname);
    }

    public String getPath() {
        return this.get(PERIMETRE_TREE_PATH);
    }

    public void setPath(String path) {
        this.set(PERIMETRE_TREE_PATH, path);
    }

    public String getType() {
        return this.get(PERIMETRE_TREE_TYPE);
    }

    public void setType(String type) {
        this.set(PERIMETRE_TREE_TYPE, type);
    }

    public String getTypeName() {
        return this.get(PERIMETRE_TREE_TYPE_NAME);
    }

    public void setTypeName(String typeName) {
        this.set(PERIMETRE_TREE_TYPE_NAME, typeName);
    }

    public String getEntiteId() {
        return this.get(PERIMETRE_TREE_ENTITE_ID);
    }

    public void setEntiteId(String entiteId) {
        this.set(PERIMETRE_TREE_ENTITE_ID, entiteId);
    }

    public Boolean getIsParent() {
        return this.get(PERIMETRE_TREE_IS_PARENT);
    }

    public void setIsParent(Boolean isParent) {
        this.set(PERIMETRE_TREE_IS_PARENT, isParent);
    }

    public Integer getLevel() {
        return this.get(PERIMETRE_TREE_LEVEL);
    }

    public void setLevel(Integer level) {
        this.set(PERIMETRE_TREE_LEVEL, level);
    }

    public Boolean getIsEntite() {
        return this.get(PERIMETRE_TREE_IS_ENTITE);
    }

    public void setIsEntite(Boolean isEntite) {
        this.set(PERIMETRE_TREE_IS_ENTITE, isEntite);
    }

    public String getParent() {
        return this.get(PERIMETRE_TREE_PARENT);
    }

    public void setParent(String parent) {
        this.set(PERIMETRE_TREE_PARENT, parent);
    }

    public String getParentName() {
        return this.get(PERIMETRE_TREE_PARENT_NAME);
    }

    public void setParentName(String parent) {
        this.set(PERIMETRE_TREE_PARENT_NAME, parent);
    }

    public Boolean getIsUoAdmin() {
        return this.get(PERIMETRE_TREE_IS_UO_ADMIN);
    }

    public void setIsUoAdmin(Boolean isUoAdmin) {
        this.set(PERIMETRE_TREE_IS_UO_ADMIN, isUoAdmin);
    }

    public Boolean getIsModificationDelegation() {
        return this.get(PERIMETRE_TREE_IS_MODIFICATION_DELEGATION);
    }

    public void setIsModificationDelegation(Boolean isModificationDelegation) {
        this.set(PERIMETRE_TREE_IS_MODIFICATION_DELEGATION, isModificationDelegation);
    }

    public Boolean getIsLectureDelegation() {
        return this.get(PERIMETRE_TREE_IS_LECTURE_DELEGATION);
    }

    public void setIsLectureDelegation(Boolean isLectureDelegation) {
        this.set(PERIMETRE_TREE_IS_LECTURE_DELEGATION, isLectureDelegation);
    }

    public Boolean getIsValidationDelegation() {
        return this.get(PERIMETRE_TREE_IS_VALIDATION_DELEGATION);
    }

    public void setIsValidationDelegation(Boolean isValidationDelegation) {
        this.set(PERIMETRE_TREE_IS_VALIDATION_DELEGATION, isValidationDelegation);
    }

    public Boolean getIsModificationControl() {
        Boolean ret = this.get(PERIMETRE_TREE_IS_MODIFICATION_CONTROL);
        if (ret == null)
            ret = false;
        return ret;
    }

    public void setIsModificationControl(Boolean isModificationControl) {
        this.set(PERIMETRE_TREE_IS_MODIFICATION_CONTROL, isModificationControl);
    }

    public Boolean getIsLectureControl() {
        Boolean ret = this.get(PERIMETRE_TREE_IS_LECTURE_CONTROL);
        if (ret == null)
            ret = false;
        return ret;
    }

    public void setIsLectureControl(Boolean isLectureControl) {
        this.set(PERIMETRE_TREE_IS_LECTURE_CONTROL, isLectureControl);
    }

    public void setPermissionByRole(RoleModel role) {
        if (role.isUoAdmin()) {
            this.setIsUoAdmin(true);
        }
        if (role.isLectureDelegation()) {
            this.setIsLectureDelegation(true);
        }
        if (role.isModificationDelegation()) {
            this.setIsModificationDelegation(true);
        }
        if (role.isValidationDelegation()) {
            this.setIsValidationDelegation(true);
        }
        if (role.isLectureControl()) {
            this.setIsLectureControl(true);
        }
        if (role.isModificationControl()) {
            this.setIsModificationControl(true);
        }
    }

    public void setPermissionByParent(PerimetreTreeModel parent) {
        if (parent.getIsUoAdmin()) {
            this.setIsUoAdmin(true);
        }
        if (parent.getIsLectureDelegation()) {
            this.setIsLectureDelegation(true);
        }
        if (parent.getIsModificationDelegation()) {
            this.setIsModificationDelegation(true);
        }
        if (parent.getIsValidationDelegation()) {
            this.setIsValidationDelegation(true);
        }
        if (parent.getIsLectureControl()) {
            this.setIsLectureControl(true);
        }
        if (parent.getIsModificationControl()) {
            this.setIsModificationControl(true);
        }
    }
}
