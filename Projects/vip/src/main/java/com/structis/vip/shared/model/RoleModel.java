package com.structis.vip.shared.model;

public class RoleModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String ADMINISTRATEUR_SUPER = "ADMINISTRATEUR_SUPER";
	public static final String ADMINISTRATEUR_APPLICATIF = "ADMINISTRATEUR_APPLICATIF";
	public static final String ADMINISTRATEUR_UO = "ADMINISTRATEUR_UO";
	public static final String DELEGATION_LECTURE = "LECTURE (DEL)";
	public static final String DELEGATION_MODIFICATION = "CREATION/MODIFICATION (DEL)";
	public static final String DELEGATION_VALIDATION = "VALIDATION (DEL)";
	
	public static final String CONTROL_LECTURE = "LECTURE (CTL)";
	public static final String CONTROL_MODIFICATION = "CREATION/MODIFICATION (CTL)";	

	public static final String ROLE_ID = "id";
	public static final String ROLE_DESCRIPTION = "description";
	public static final String ROLE_NAME = "name";
	public static final String ROLE_TYPE = "type";

	public Integer getId() {
		return get(ROLE_ID);
	}

	public void setId(Integer id) {
		set(ROLE_ID, id);
	}

	public String getDescription() {
		return get(ROLE_DESCRIPTION);
	}

	public void setDescription(String description) {
		set(ROLE_DESCRIPTION, description);
	}

	public String getName() {
		return get(ROLE_NAME);
	}

	public void setName(String name) {
		set(ROLE_NAME, name);
	}

	public String getType() {
		return get(ROLE_TYPE);
	}

	public void setType(String type) {
		set(ROLE_TYPE, type);
	}

	public boolean isAdministrateur() {
		if (RoleModel.ADMINISTRATEUR_APPLICATIF.equals(getName()) || (RoleModel.ADMINISTRATEUR_UO.equals(getName()))
				|| (RoleModel.ADMINISTRATEUR_SUPER.equals(getName()))) {
			return true;
		}
		return false;
	}

	public boolean isSuperAdmin() {
		if (RoleModel.ADMINISTRATEUR_SUPER.equals(getName())) {
			return true;
		}
		return false;
	}

	public boolean isApplicationAdmin() {
		if (RoleModel.ADMINISTRATEUR_APPLICATIF.equals(getName()) || isSuperAdmin()) {
			return true;
		}
		return false;
	}

	public boolean isUoAdmin() {
		if (RoleModel.ADMINISTRATEUR_UO.equals(getName()) || isApplicationAdmin()) {
			return true;
		}
		return false;
	}

	public boolean isModificationDelegation() {
		if (RoleModel.DELEGATION_MODIFICATION.equals(getName()) || isAdministrateur()) {
			return true;
		}
		return false;
	}

	public boolean isValidationDelegation() {
		if (RoleModel.DELEGATION_VALIDATION.equals(getName()) || isAdministrateur()) {
			return true;
		}
		return false;
	}

	public boolean isLectureDelegation() {
		if (RoleModel.DELEGATION_LECTURE.equals(getName()) || isModificationDelegation() || isValidationDelegation()
				|| isAdministrateur()) {
			return true;
		}
		return false;
	}	

	public boolean isModificationControl() {
		if (RoleModel.CONTROL_MODIFICATION.equals(getName()) || isAdministrateur()) {
			return true;
		}
		return false;
	}
	
	public boolean isLectureControl() {
		if (RoleModel.CONTROL_LECTURE.equals(getName()) || isModificationControl() || isValidationDelegation()
				|| isAdministrateur()) {
			return true;
		}
		return false;
	}
}