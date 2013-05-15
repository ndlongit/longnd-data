package com.structis.vip.server.bean.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;


@Entity
@Table(name = "CTL_CONTROL") 
public class Control extends AbstractShowAbleBean implements
Identifiable<Integer> {
	@Id 
	@GeneratedValue
	@Column(name = "ctl_id", unique = true, nullable = false) 
	private Integer id;
	@Column(name = "ctl_codeProject")
	private String codeProject;	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "per_id", nullable = true)
	private Perimetre perimetre = new Perimetre();	
	@Column(name = "ctl_perimetreParent")
	private String perimetreParent;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "con_id", nullable = true)
	private ControlType controlType;
	
	@Column(name = "ctl_date", nullable = true)
	private Date date;
	
	@Column(name = "ctl_character", nullable = true)
	private String character;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "col_id", nullable = true)	
	private Collaborateur collaborateur;
	
	@Column(name = "ctl_rapport")
	private String rapport;
	
	@Transient
	private String extControlNames;
	
	@Transient
	private Boolean canModified;	
	
	@Transient
	private Boolean canViewed;

	@Transient
	private Boolean canDeleted;
	
	@Transient
	private Boolean canExported;
	
	@Override
	public Integer getPrimaryKey() {
		return this.getId() ;
	}	

	@Override
	public boolean isPrimaryKeySet() {
		return (this.getId() != null);
	}

	@Override
	public void setPrimaryKey(Integer id) {
		this.setId(id);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}		
		
	public String getCodeProject() {
		return codeProject;
	}

	public void setCodeProject(String codeProject) {
		this.codeProject = codeProject;
	}

	public Perimetre getPerimetre() {
		return perimetre;
	}

	public void setPerimetre(Perimetre perimetre) {
		this.perimetre = perimetre;
	}

	public String getPerimetreParent() {
		return perimetreParent;
	}

	public void setPerimetreParent(String perimetreParent) {
		this.perimetreParent = perimetreParent;
	}

	public ControlType getControlType() {
		return controlType;
	}

	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

	public String getRapport() {
		return rapport;
	}

	public void setRapport(String rapport) {
		this.rapport = rapport;
	}
	
	

	public Boolean getCanModified() {
		return canModified;
	}

	public void setCanModified(Boolean canModified) {
		this.canModified = canModified;
	}

	public Boolean getCanViewed() {
		return canViewed;
	}

	public void setCanViewed(Boolean canViewed) {
		this.canViewed = canViewed;
	}

	public Boolean getCanDeleted() {
		return canDeleted;
	}

	public void setCanDeleted(Boolean canDeleted) {
		this.canDeleted = canDeleted;
	}

	public Boolean getCanExported() {
		return canExported;
	}

	public void setCanExported(Boolean canExported) {
		this.canExported = canExported;
	}

	
	public String getExtControlNames() {
		return extControlNames;
	}

	public void setExtControlNames(String extControlNames) {
		this.extControlNames = extControlNames;
	}

	
	@Override
	protected void beanToString(StringBuffer sb) {
	}
	
}
