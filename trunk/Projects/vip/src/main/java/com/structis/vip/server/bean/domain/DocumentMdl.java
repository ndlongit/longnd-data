package com.structis.vip.server.bean.domain;
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

/**
 * The persistent class for the DOM_DOCUMENT_MODEL database table.
 * 
 */
@Entity
@Table(name="DOM_DOCUMENT_MODEL")
public class DocumentMdl  extends AbstractShowAbleBean implements Identifiable<Integer> {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	@Column(name = "dom_id", unique = true, nullable = false) 
	private Integer id;

	@Column(name="dom_name")
	private String name;

	@Column(name="dom_type")
	private String type;
	
	@Column(name="dom_version", nullable = true)
	private String version;
	
	@Column(name="dom_filename")
	private String filename;	

	@Column(name="dom_temp_filename")
	private String tempFilename;	

//	@Column(name="dom_variables")
//	private String variables;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "lag_id", nullable = false)
	private Language language;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ent_id", nullable = true)
	private Entite entite;	
	
	@Transient
	private String signedFilename;

    public DocumentMdl() {
    }

    @Override
	public Integer getPrimaryKey() {
		return this.getId();
	}	

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public String getVariables() {
//		return variables;
//	}
//
//	public void setVariables(String variables) {
//		this.variables = variables;
//	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
		
	public String getTempFilename() {
		return tempFilename;
	}

	public void setTempFilename(String tempFilename) {
		this.tempFilename = tempFilename;
	}

	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSignedFilename() {
		return signedFilename;
	}

	public void setSignedFilename(String signedFilename) {
		this.signedFilename = signedFilename;
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}
}
