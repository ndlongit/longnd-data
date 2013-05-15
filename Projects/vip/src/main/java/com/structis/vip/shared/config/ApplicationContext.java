package com.structis.vip.shared.config;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.structis.vip.shared.model.AdministrateurModel;
import com.structis.vip.shared.security.Role;

public class ApplicationContext implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// Load application
	private List<Role> roles;
	private AdministrateurModel admin;
	private Date date;

	//tdo
	private Boolean isInActionEdit = false;	
	private Boolean isInAdminEdit = false;
	private Boolean isInPerimetreEdit = false;
	
	public ApplicationContext(){
	}

	public ApplicationContext(List<Role> roles, 
			AdministrateurModel admin, Date date) {
		super();
		this.roles = roles;
		this.admin = admin;
		this.date = date;
	}

	// ### Load application
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setAdmin(AdministrateurModel admin) {
		this.admin = admin;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public AdministrateurModel getAdmin() {
		return admin;
	}
	
	public Date getDate(){
		return date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean isComplete(){
		boolean complete = true;
		return complete;
	}
	//tdo

	public Boolean getIsInActionEdit() {
		return isInActionEdit;
	}

	public void setIsInActionEdit(Boolean isInActionEdit) {
		this.isInActionEdit = isInActionEdit;
	}

	public Boolean getIsInAdminEdit() {
		return isInAdminEdit;
	}

	public void setIsInAdminEdit(Boolean isInAdminEdit) {
		this.isInAdminEdit = isInAdminEdit;
	}

	public Boolean getIsInPerimetreEdit() {
		return isInPerimetreEdit;
	}

	public void setIsInPerimetreEdit(Boolean isInPerimetreEdit) {
		this.isInPerimetreEdit = isInPerimetreEdit;
	}
	
}
