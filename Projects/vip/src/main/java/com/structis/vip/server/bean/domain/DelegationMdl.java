package com.structis.vip.server.bean.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "DEM_DELEGATION_MODEL")
public class DelegationMdl extends AbstractShowAbleBean implements Identifiable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dem_id", unique = true)
	private Integer id;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "lag_id", nullable = false)
	private Language language = new Language();

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "pty_id", nullable = true)
	private PerimetreType perimetreType = new PerimetreType();

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "dna_id", nullable = false)
	private DelegationNature delegationNature = new DelegationNature();

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ent_id", nullable = false)
	private Entite entite = new Entite();

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "dem_col_type", nullable = true)
	private CollaborateurType collaborateurType = new CollaborateurType();

	@Column(name = "dem_group", nullable = false)
	private Integer group;
	
	@Column(name = "dem_has_multiple_delegation", nullable = true)
	private Integer hasMultipleDelegation;
	
	@Column(name = "dem_has_multiple_delegataire", nullable = true)
	private Integer hasMultipleDelegataire;
	
	@Column(name = "dem_sub_delegation", nullable = true)
	private Integer subDelegation;

	@Override
	public Integer getPrimaryKey() {
		return getId();
	}

	@Override
	public void setPrimaryKey(Integer id) {
		setId(id);
	}

	@Override
	public boolean isPrimaryKeySet() {
		return (this.getId() != null);
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public PerimetreType getPerimetreType() {
		return perimetreType;
	}

	public void setPerimetreType(PerimetreType perimetreType) {
		this.perimetreType = perimetreType;
	}

	public DelegationNature getDelegationNature() {
		return delegationNature;
	}

	public void setDelegationNature(DelegationNature delegationNature) {
		this.delegationNature = delegationNature;
	}

	public CollaborateurType getCollaborateurType() {
		return collaborateurType;
	}

	public void setCollaborateurType(CollaborateurType collaborateurType) {
		this.collaborateurType = collaborateurType;
	}

	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public Integer getDemId() {
		return id;
	}

	public void setDemId(Integer demId) {
		this.id = demId;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getHasMultipleDelegation() {
		return hasMultipleDelegation;
	}

	public void setHasMultipleDelegation(Integer hasMultipleDelegation) {
		this.hasMultipleDelegation = hasMultipleDelegation;
	}

	public void setHasMultipleDelegataire(Integer hasMultipleDelegataire) {
		this.hasMultipleDelegataire = hasMultipleDelegataire;
	}

	public Integer getHasMultipleDelegataire() {
		return hasMultipleDelegataire;
	}

	public Integer getSubDelegation() {
		return subDelegation;
	}

	public void setSubDelegation(Integer subDelegation) {
		this.subDelegation = subDelegation;
	}
	
	
}
