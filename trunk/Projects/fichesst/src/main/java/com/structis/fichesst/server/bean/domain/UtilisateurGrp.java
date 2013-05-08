package com.structis.fichesst.server.bean.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

@Entity
@Table(name = "UTILISATEUR_GRP")
@AttributeOverride(name = "id", column = @Column(name = "id_utilisateur_grp"))
public class UtilisateurGrp extends NumericIdEntity implements Serializable {
	
	public static final String PROP_IDENTIFIANT ="identifiant";
	
	private Boolean badmin;

	private String identifiant;

	private List<Role> roles;

	@Column(name = "identifiant")
	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	@Column(name = "b_admin")
	public Boolean getBadmin() {
		return badmin;
	}

	public void setBadmin(Boolean badmin) {
		this.badmin = badmin;
	}

	public UtilisateurGrp() {
	}

	@OneToMany(mappedBy = "utilisateurGrp",fetch=FetchType.EAGER)
	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public UtilisateurGrp(String identifiant, Boolean badmin) {

		this.identifiant = identifiant;
		this.badmin = badmin;
	}

	public UtilisateurGrp(UtilisateurGrpModel userModel) {
		setId(userModel.getId());
		setIdentifiant(userModel.getIdentifiant());
		setBadmin(userModel.getBadmin());

	}

}
