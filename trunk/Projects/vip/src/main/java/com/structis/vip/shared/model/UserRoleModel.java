package com.structis.vip.shared.model;

public class UserRoleModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 5567311460418598088L;
	
	public static final String USER_ROLE_ID = "id";
	public static final String USER_ROLE_USER = "user";
	public static final String USER_ROLE_ROLE = "role";
	public static final String USER_ROLE_PERIMETRE = "perimetre";

	@SuppressWarnings("unused")
	private UserModel userModel;

	@SuppressWarnings("unused")
	private RoleModel roleModel;

	@SuppressWarnings("unused")
	private PerimetreModel perimetreModel;

	public Integer getId() {
		return get(USER_ROLE_ID);
	}

	public void setId(Integer id) {
		set(USER_ROLE_ID, id);
	}

	public UserModel getUser() {
		return get(USER_ROLE_USER);
	}

	public void setUser(UserModel user) {
		set(USER_ROLE_USER, user);
	}

	public RoleModel getRole() {
		return get(USER_ROLE_ROLE);
	}

	public void setRole(RoleModel role) {
		set(USER_ROLE_ROLE, role);
	}

	public PerimetreModel getPerimetre() {
		return get(USER_ROLE_PERIMETRE);
	}

	public void setPerimetre(PerimetreModel perimetre) {
		set(USER_ROLE_PERIMETRE, perimetre);
	}
}