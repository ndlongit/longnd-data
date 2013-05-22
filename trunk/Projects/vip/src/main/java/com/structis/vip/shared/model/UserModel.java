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

    @Override
    public Integer getId() {
        return this.get(USER_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(USER_ID, id);
    }

    public String getUserName() {
        return this.get(USER_NAME);
    }

    public void setUserName(String userName) {
        this.set(USER_NAME, userName);
    }

    public String getFirstName() {
        return this.get(USER_FIRST_NAME);
    }

    public void setFirstName(String firstName) {
        this.set(USER_FIRST_NAME, firstName);
    }

    public String getLastName() {
        return this.get(USER_LAST_NAME);
    }

    public void setLastName(String lastName) {
        this.set(USER_LAST_NAME, lastName);
    }

    public EntiteModel getEntite() {
        return this.get(USER_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(USER_ENTITE, entite);
    }

    public List<UserRoleModel> getUserRoles() {
        return this.get(USER_USER_ROLES);
    }

    public void setUserRoles(List<UserRoleModel> userRoles) {
        this.set(USER_USER_ROLES, userRoles);
    }

    public CollaborateurModel getCollaborateur() {
        return this.get(USER_COLLABORATEUR);
    }

    public void setCollaborateur(CollaborateurModel collaborateur) {
        this.set(USER_COLLABORATEUR, collaborateur);
    }

    public DomainModel getDomain() {
        return this.get(USER_DOMAIN);
    }

    public void setDomain(DomainModel domain) {
        this.set(USER_DOMAIN, domain);
    }

    public String getRoles() {
        return this.get(USER_ROLES);
    }

    public void setRoles() {
        // String ret = "";
        // boolean first = true;
        // for (UserRoleModel userRoleModel : getUserRoles()) {
        // if (userRoleModel.getRole() != null) {
        // if (ret.indexOf(userRoleModel.getRole().getName()) == -1) {
        // if (first) {
        // ret += userRoleModel.getRole().getName();
        // first = false;
        // } else {
        // ret += ", " + userRoleModel.getRole().getName();
        // }
        // }
        // }
        // }
        // set(USER_ROLES, ret);
    }

    public Boolean isSuperUser() {
        for (UserRoleModel userRole : this.getUserRoles()) {
            if (userRole.getRole().isSuperAdmin()) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdministrateur() {
        for (UserRoleModel userRole : this.getUserRoles()) {
            if (userRole.getRole().isAdministrateur()) {
                return true;
            }
        }
        return false;
    }

    public Boolean isApplicationAdmin() {
        for (UserRoleModel userRole : this.getUserRoles()) {
            if (userRole.getRole().isApplicationAdmin()) {
                return true;
            }
        }
        return false;
    }

    public Boolean isUoAdmin() {
        for (UserRoleModel userRole : this.getUserRoles()) {
            if (userRole.getRole().isUoAdmin()) {
                return true;
            }
        }
        return false;
    }

    public Integer getSessionTimeout() {
        return this.sessionTimeout;
    }

    public void setSessionTimeout(final Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Long getLastAccessedTime() {
        return this.lastAccessedTime;
    }

    public void setLastAccessedTime(final Long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public boolean isSessionExpired(final Date date) {
        if (this.lastAccessedTime != null && this.sessionTimeout != null) {
            if (date.getTime() - this.lastAccessedTime > this.sessionTimeout) {
                return true;
            }
            this.lastAccessedTime = date.getTime();
        }
        return false;
    }

    public String getPassword() {
        return this.get(USER_USER_PASSWORD);
    }

    public void setPassword(String password) {
        this.set(USER_USER_PASSWORD, password);
    }

    public PerimetreModel getPerimetre() {
        return this.get(USER_PERIMETRE);
    }

    public void setPerimetre(PerimetreModel perimetre) {
        this.set(USER_PERIMETRE, perimetre);
    }

    public boolean exists() {
        return (this.getId() != null);
    }
}
