package com.structis.vip.shared.model;

import java.util.Date;
import java.util.List;

public class UserModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String USER_ID = "id";
	public static final String USER_NAME = "userName";
	public static final String USER_FIRST_NAME = "firstName";
	public static final String USER_LAST_NAME = "lastName";
	public static final String USER_ENTITE = "entite";
	public static final String USER_COLLABORATEUR = "collaborateur";
	public static final String USER_DOMAIN = "domain";
	public static final String USER_USER_ROLES = "userRoles";
	public static final String USER_USER_PASSWORD = "password";
	public static final String USER_ROLES = "roles";
	public static final String USER_PERIMETRE = "perimetre";
	
	@SuppressWarnings("unused")
	private EntiteModel entiteModel;

	@SuppressWarnings("unused")
	private CollaborateurModel collaborateurModel;

	@SuppressWarnings("unused")
	private List<UserRoleModel> userRoleModels;

	@SuppressWarnings("unused")
	private DomainModel domainModel;

	@SuppressWarnings("unused")
	private PerimetreModel perimetreModel;
	
	private Integer sessionTimeout = new Integer(0);
	private Long lastAccessedTime = new Long(0);

	public UserModel() {
	}

	public Integer getId() {
		return get(USER_ID);
	}

	public void setId(Integer id) {
		set(USER_ID, id);
	}

	public String getUserName() {
		return get(USER_NAME);
	}

	public void setUserName(String userName) {
		set(USER_NAME, userName);
	}

	public String getFirstName() {
		return get(USER_FIRST_NAME);
	}

	public void setFirstName(String firstName) {
		set(USER_FIRST_NAME, firstName);
	}

	public String getLastName() {
		return get(USER_LAST_NAME);
	}

	public void setLastName(String lastName) {
		set(USER_LAST_NAME, lastName);
	}

	public EntiteModel getEntite() {
		return get(USER_ENTITE);
	}

	public void setEntite(EntiteModel entite) {
		set(USER_ENTITE, entite);
	}

	public List<UserRoleModel> getUserRoles() {
		return get(USER_USER_ROLES);
	}

	public void setUserRoles(List<UserRoleModel> userRoles) {
		set(USER_USER_ROLES, userRoles);
	}

	public CollaborateurModel getCollaborateur() {
		return get(USER_COLLABORATEUR);
	}

	public void setCollaborateur(CollaborateurModel collaborateur) {
		set(USER_COLLABORATEUR, collaborateur);
	}

	public DomainModel getDomain() {
		return get(USER_DOMAIN);
	}

	public void setDomain(DomainModel domain) {
		set(USER_DOMAIN, domain);
	}
	
	public String getRoles() {
		return get(USER_ROLES);
	}

	public void setRoles() {
//		String ret = "";
//		boolean first = true;
//		for (UserRoleModel userRoleModel : getUserRoles()) {
//			if (userRoleModel.getRole() != null) {
//				if (ret.indexOf(userRoleModel.getRole().getName()) == -1) {
//					if (first) {
//						ret += userRoleModel.getRole().getName();
//						first = false;
//					} else {
//						ret += ", " + userRoleModel.getRole().getName();
//					}
//				}
//			}
//		}
//		set(USER_ROLES, ret);
	}
	
	public Boolean isSuperUser() {
		for (UserRoleModel userRole : getUserRoles()) {
			if (userRole.getRole().isSuperAdmin()) {
				return true;
			}
		}
		return false;
	}


	public boolean isAdministrateur() {
		for (UserRoleModel userRole : getUserRoles()) {
			if (userRole.getRole().isAdministrateur()) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean isApplicationAdmin() {
		for (UserRoleModel userRole : getUserRoles()) {
			if (userRole.getRole().isApplicationAdmin()) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean isUoAdmin() {
		for (UserRoleModel userRole : getUserRoles()) {
			if (userRole.getRole().isUoAdmin()) {
				return true;
			}
		}
		return false;
	}

	public Integer getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(final Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public Long getLastAccessedTime() {
		return lastAccessedTime;
	}

	public void setLastAccessedTime(final Long lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public boolean isSessionExpired(final Date date) {
		if (lastAccessedTime != null && sessionTimeout != null) {
			if (date.getTime() - lastAccessedTime > sessionTimeout) {
				return true;
			}
			lastAccessedTime = date.getTime();
		}
		return false;
	}
	
	public String getPassword() {
		return get(USER_USER_PASSWORD);
	}

	public void setPassword(String password) {
		set(USER_USER_PASSWORD, password);
	}

	public PerimetreModel getPerimetre() {
		return get(USER_PERIMETRE);
	}

	public void setPerimetre(PerimetreModel perimetre) {
		set(USER_PERIMETRE, perimetre);
	}
	
	public boolean exists() {
		return (this.getId() != null);
	}
}
