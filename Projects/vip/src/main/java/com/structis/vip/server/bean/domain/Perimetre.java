package com.structis.vip.server.bean.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "PER_PERIMETRE")
public class Perimetre extends AbstractShowAbleBean implements Identifiable<String> {

    @Id
    @Column(name = "per_id", unique = true, nullable = false)
    private String perId;

    @Column(name = "per_name")
    private String name;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ent_id", nullable = false)
    private Entite entite = new Entite();

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "etj_id", nullable = true)
    private EntiteJuridique entiteJuridique = new EntiteJuridique();

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "per_parent_id", nullable = true)
    private Perimetre parent;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "pty_id", nullable = true)
    private PerimetreType type;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "lag_id", nullable = true)
    private Language language;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "cty_id", nullable = true)
    private ChantierType chantierType;

    @Column(name = "per_city")
    private String city;

    @Column(name = "per_address")
    private String address;

    @Column(name = "per_chantierSEP")
    private Integer chantierSEP;

    @Column(name = "per_chantierID")
    private String chantierID;

    @Column(name = "per_chantierStartDate")
    private Date chantierStartDate;

    @Column(name = "per_chantierPlannedEndDate")
    private Date chantierPlannedEndDate;

    @Column(name = "per_chantierEndDate")
    private Date chantierEndDate;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    @Column(name = "argos_name")
    private String argosName;

    // R02
    @Column(name = "per_chantierNumeroDeProjet")
    private String chantierNumeroDeProjet;

    @Column(name = "per_chantierGroupement")
    private Integer chantierGroupement;

    @Column(name = "per_chantierPartenaires")
    private String chantierPartenaires;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "per_created_by", nullable = true)
    private User createdBy;

    @Column(name = "per_isSubdelegable")
    private Integer isSubdelegable;

    @Override
    public String getPrimaryKey() {
        return this.getPerId();
    }

    @Override
    public boolean isPrimaryKeySet() {
        return (this.getPerId() != null);
    }

    @Override
    public void setPrimaryKey(String id) {
        this.setPerId(id);
    }

    public String getPerId() {
        return this.perId;
    }

    public void setPerId(String id) {
        this.perId = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entite getEntite() {
        return this.entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public Perimetre getParent() {
        return this.parent;
    }

    public void setParent(Perimetre parent) {
        this.parent = parent;
    }

    public PerimetreType getType() {
        return this.type;
    }

    public void setType(PerimetreType type) {
        this.type = type;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getChantierSEP() {
        return this.chantierSEP;
    }

    public void setChantierSEP(Integer chantierSEP) {
        this.chantierSEP = chantierSEP;
    }

    public String getChantierID() {
        return this.chantierID;
    }

    public void setChantierID(String per_chantierID) {
        this.chantierID = per_chantierID;
    }

    public Date getChantierStartDate() {
        return this.chantierStartDate;
    }

    public void setChantierStartDate(Date chantierStartDate) {
        this.chantierStartDate = chantierStartDate;
    }

    public Date getChantierPlannedEndDate() {
        return this.chantierPlannedEndDate;
    }

    public void setChantierPlannedEndDate(Date chantierPlannedEndDate) {
        this.chantierPlannedEndDate = chantierPlannedEndDate;
    }

    public Date getChantierEndDate() {
        return this.chantierEndDate;
    }

    public void setChantierEndDate(Date chantierEndDate) {
        this.chantierEndDate = chantierEndDate;
    }

    public Integer getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getArgosName() {
        return this.argosName;
    }

    public void setArgosName(String argosName) {
        this.argosName = argosName;
    }

    public ChantierType getChantierType() {
        return this.chantierType;
    }

    public void setChantierType(ChantierType chantierType) {
        this.chantierType = chantierType;
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }

    public EntiteJuridique getEntiteJuridique() {
        return this.entiteJuridique;
    }

    public void setEntiteJuridique(EntiteJuridique entiteJuridique) {
        this.entiteJuridique = entiteJuridique;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChantierNumeroDeProjet() {
        return this.chantierNumeroDeProjet;
    }

    public void setChantierNumeroDeProjet(String chantierNumeroDeProjet) {
        this.chantierNumeroDeProjet = chantierNumeroDeProjet;
    }

    public Integer getChantierGroupement() {
        return this.chantierGroupement;
    }

    public void setChantierGroupement(Integer chantierGroupement) {
        this.chantierGroupement = chantierGroupement;
    }

    public String getChantierPartenaires() {
        return this.chantierPartenaires;
    }

    public void setChantierPartenaires(String chantierPartenaires) {
        this.chantierPartenaires = chantierPartenaires;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getIsSubdelegable() {
        return this.isSubdelegable;
    }

    public void setIsSubdelegable(Integer isSubdelegable) {
        this.isSubdelegable = isSubdelegable;
    }
    
    @Override
    public String toString() {
        return getPrimaryKey() + "; " +  getName();
    }
}
