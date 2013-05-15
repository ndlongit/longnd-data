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

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name="CFO_COLLABORATEUR_FORMATION")
public class CollaborateurFormation extends AbstractShowAbleBean implements
Identifiable<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "cfo_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "for_id", nullable = false)
	private Formation formation;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "col_id", nullable = false)
	private Collaborateur collaborateur;
	
	@Column(name="cfo_date")
	private Date date;

    public CollaborateurFormation() {
    }

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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null) return false;
		
		if (arg0 instanceof CollaborateurFormation) {
			CollaborateurFormation that = (CollaborateurFormation) arg0;
			if (that.getCollaborateur().getId() == this.getCollaborateur().getId()
					&& that.getFormation().getId() == this.getFormation().getId()) {
				return true;
			}
		}
		return false;
	}
}

