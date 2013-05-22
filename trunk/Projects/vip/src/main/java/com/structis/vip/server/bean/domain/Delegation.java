package com.structis.vip.server.bean.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "DEL_DELEGATION")
public class Delegation extends AbstractShowAbleBean implements Identifiable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "del_id", unique = true)
    private Integer id;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "dty_id", nullable = false)
    private DelegationType delegationType = new DelegationType();

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "dst_id", nullable = false)
    private DelegationStatus delegationStatus = new DelegationStatus();

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "dna_id", nullable = false)
    private DelegationNature delegationNature = new DelegationNature();

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "per_id", nullable = false)
    private Perimetre perimeter = new Perimetre();

    @Column(name = "del_startDate")
    private Date startDate;

    @Column(name = "del_endDate")
    private Date endDate;

    @Column(name = "del_isSigned")
    private Integer isSigned;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "del_delegant_id", nullable = false)
    private Collaborateur delegant = new Collaborateur();

    @Column(name = "del_delegantFirstname")
    private String delegantFirstname;

    @Column(name = "del_delegantLastname")
    private String delegantLastname;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "del_delegataire_id", nullable = true)
    private Collaborateur delegataire = new Collaborateur();

    @Column(name = "del_delegataireFirstname")
    private String delegataireFirstname;

    @Column(name = "del_delegataireLastname")
    private String delegataireLastname;

    @Column(name = "del_demandeurFirstname")
    private String demandeurFirstname;

    @Column(name = "del_demandeurLastname")
    private String demandeurLastname;

    @Column(name = "del_delegationConjointe")
    private Integer delegationConjointe;

    @Column(name = "del_date1")
    private Date date1;

    @Column(name = "del_place1")
    private String place1;

    @Column(name = "del_date2")
    private Date date2;

    @Column(name = "del_place2")
    private String place2;

    @Column(name = "del_date3")
    private Date date3;

    @Column(name = "del_place3")
    private String place3;

    @Column(name = "del_amount1")
    private Float amount1;

    @Column(name = "del_amount2")
    private Float amount2;

    @Column(name = "del_amount3")
    private Float amount3;

    @Column(name = "del_amount4")
    private Float amount4;

    @Column(name = "del_amount5")
    private Float amount5; // add more 1 fields for BYETD ()

    @Column(name = "del_comment1")
    private String comment1;

    @Column(name = "del_comment2")
    private String comment2;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ent_id", nullable = true)
    private Entite entite;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "del_etj_id", nullable = true)
    private EntiteJuridique entiteJuridique = new EntiteJuridique();

    @Column(name = "del_etj_name")
    private String etjName;

    @Column(name = "del_etj_statut")
    private String etjStatut;

    @Column(name = "del_etj_capital")
    private String etjCapital;

    @Column(name = "del_etj_registrationId")
    private String etjRegistrationId;

    @Column(name = "del_etj_registrationAddress")
    private String etjRegistrationAddress;

    @Column(name = "del_delegataireQualite")
    private String delegataireQualite;

    @Column(name = "del_delegantQualite")
    private String delegantQualite;

    @Column(name = "del_delegantTitle")
    private String delegantTitle;

    @Column(name = "del_per_chantierName")
    private String perChantierName;

    @Column(name = "del_per_chantierCity")
    private String perChantierCity;

    @Column(name = "del_per_chantierID")
    private String perChantierID;

    @Column(name = "del_per_chantierStartDate")
    private Date perChantierStartDate;

    @Column(name = "del_per_chantierPlannedEndDate")
    private Date perChantierPlannedEndDate;

    @Column(name = "del_per_chantierEndDate")
    private Date perChantierEndDate;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "del_parent_id", nullable = true)
    private Delegation parent;

    @Column(name = "del_partyDelegee")
    private String partyDelegee;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "del_demandeur_id", nullable = true)
    private Collaborateur demandeur = new Collaborateur();

    @Column(name = "del_etj_address")
    private String etjAddress;

    @Column(name = "del_description")
    private String description;

    @Column(name = "del_delegant_niveauHierarchique")
    private String delegantNiveauHierarchique;

    @Column(name = "del_delegant_lieuNaissance")
    private String delegantLieuNaissance;

    @Column(name = "del_delegant_dateNaissance")
    private Date delegantDateNaissance;

    @Column(name = "del_delegataire_niveauHierarchique")
    private String delegataireNiveauHierarchique;

    @Column(name = "del_delegataire_lieuNaissance")
    private String delegataireLieuNaissance;

    @Column(name = "del_delegataire_dateNaissance")
    private Date delegataireDateNaissance;

    @Column(name = "del_delegant_nationality")
    private String delegantNationality;

    @Column(name = "del_delegataire_nationality")
    private String delegataireNationalite;

    @Column(name = "del_delegataireStatut")
    private String delegataireStatut;

    @Column(name = "del_delegataireAddress")
    private String delegataireAddress;

    @Column(name = "del_delegantStatut")
    private String delegantStatut;

    @Column(name = "del_zone")
    private String zone;

    @Column(name = "del_operations")
    private String operations;

    @Column(name = "del_delegantDateEffet")
    private Date delegantDateEffet;

    @Column(name = "del_delegantDateConseil")
    private Date delegantDateConseil;

    @Column(name = "del_delegantStatutConseil")
    private String delegantStatutConseil;

    @Column(name = "del_dateDelegation")
    private Date dateDelegation;

    @Column(name = "del_delegantNom1")
    private String delegantNom1;

    @Column(name = "del_delegantPrenom1")
    private String delegantPrenom1;

    @Column(name = "del_delegantQualite1")
    private String delegantQualite1;

    // @Column(name = "del_isSubdelegable")
    // private Integer isSubdelegable;

    @Transient
    private Boolean isModification;

    @Transient
    private Boolean isValidation;

    @Transient
    private Boolean isLecture;

    @Transient
    private Boolean isCanRenew;

    @Override
    public Integer getPrimaryKey() {
        return this.getId();
    }

    @Override
    public void setPrimaryKey(Integer id) {
        this.setId(id);
    }

    @Override
    public boolean isPrimaryKeySet() {
        return (this.getId() != null);
    }

    @Override
    protected void beanToString(StringBuffer sb) {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DelegationType getDelegationType() {
        return this.delegationType;
    }

    public void setDelegationType(DelegationType delegationType) {
        this.delegationType = delegationType;
    }

    public DelegationStatus getDelegationStatus() {
        return this.delegationStatus;
    }

    public void setDelegationStatus(DelegationStatus delegationStatus) {
        this.delegationStatus = delegationStatus;
    }

    public DelegationNature getDelegationNature() {
        return this.delegationNature;
    }

    public void setDelegationNature(DelegationNature delegationNature) {
        this.delegationNature = delegationNature;
    }

    public Perimetre getPerimeter() {
        return this.perimeter;
    }

    public void setPerimeter(Perimetre perimeter) {
        this.perimeter = perimeter;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getIsSigned() {
        return this.isSigned;
    }

    public void setIsSigned(Integer isSigned) {
        this.isSigned = isSigned;
    }

    public Collaborateur getDelegant() {
        return this.delegant;
    }

    public void setDelegant(Collaborateur delegant) {
        this.delegant = delegant;
    }

    public String getDelegantFirstname() {
        return this.delegantFirstname;
    }

    public void setDelegantFirstname(String delegantFirstname) {
        this.delegantFirstname = delegantFirstname;
    }

    public String getDelegantLastname() {
        return this.delegantLastname;
    }

    public void setDelegantLastname(String delegantLastname) {
        this.delegantLastname = delegantLastname;
    }

    public Collaborateur getDelegataire() {
        return this.delegataire;
    }

    public void setDelegataire(Collaborateur delegataire) {
        this.delegataire = delegataire;
    }

    public String getDelegataireFirstname() {
        return this.delegataireFirstname;
    }

    public void setDelegataireFirstname(String delegataireFirstname) {
        this.delegataireFirstname = delegataireFirstname;
    }

    public String getDelegataireLastname() {
        return this.delegataireLastname;
    }

    public void setDelegataireLastname(String delegataireLastname) {
        this.delegataireLastname = delegataireLastname;
    }

    public Collaborateur getDemandeur() {
        return this.demandeur;
    }

    public void setDemandeur(Collaborateur demandeur) {
        this.demandeur = demandeur;
    }

    public String getDemandeurFirstname() {
        return this.demandeurFirstname;
    }

    public void setDemandeurFirstname(String demandeurFirstname) {
        this.demandeurFirstname = demandeurFirstname;
    }

    public String getDemandeurLastname() {
        return this.demandeurLastname;
    }

    public void setDemandeurLastname(String demandeurLastname) {
        this.demandeurLastname = demandeurLastname;
    }

    public Integer getDelegationConjointe() {
        return this.delegationConjointe;
    }

    public void setDelegationConjointe(Integer delegationConjoint) {
        this.delegationConjointe = delegationConjoint;
    }

    public Date getDate1() {
        return this.date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public String getPlace1() {
        return this.place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public Date getDate2() {
        return this.date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String getPlace2() {
        return this.place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public Date getDate3() {
        return this.date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public String getPlace3() {
        return this.place3;
    }

    public void setPlace3(String place3) {
        this.place3 = place3;
    }

    public Float getAmount2() {
        return this.amount2;
    }

    public Float getAmount1() {
        return this.amount1;
    }

    public void setAmount1(Float amount1) {
        this.amount1 = amount1;
    }

    public void setAmount2(Float amount2) {
        this.amount2 = amount2;
    }

    public Float getAmount3() {
        return this.amount3;
    }

    public void setAmount3(Float amount3) {
        this.amount3 = amount3;
    }

    public Float getAmount4() {
        return this.amount4;
    }

    public void setAmount4(Float amount4) {
        this.amount4 = amount4;
    }

    public String getComment1() {
        return this.comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return this.comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public EntiteJuridique getEntiteJuridique() {
        return this.entiteJuridique;
    }

    public void setEntiteJuridique(EntiteJuridique entiteJuridique) {
        this.entiteJuridique = entiteJuridique;
    }

    public Entite getEntite() {
        return this.entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public String getEtjName() {
        return this.etjName;
    }

    public void setEtjName(String etjName) {
        this.etjName = etjName;
    }

    public String getEtjCapital() {
        return this.etjCapital;
    }

    public void setEtjCapital(String etjCapital) {
        this.etjCapital = etjCapital;
    }

    public String getEtjRegistrationId() {
        return this.etjRegistrationId;
    }

    public void setEtjRegistrationId(String etjRegistrationId) {
        this.etjRegistrationId = etjRegistrationId;
    }

    public String getEtjRegistrationAddress() {
        return this.etjRegistrationAddress;
    }

    public void setEtjRegistrationAddress(String etjRegistrationAddress) {
        this.etjRegistrationAddress = etjRegistrationAddress;
    }

    public String getDelegataireQualite() {
        return this.delegataireQualite;
    }

    public void setDelegataireQualite(String delegataireQualite) {
        this.delegataireQualite = delegataireQualite;
    }

    public String getDelegantQualite() {
        return this.delegantQualite;
    }

    public void setDelegantQualite(String delegantQualite) {
        this.delegantQualite = delegantQualite;
    }

    public String getPerChantierName() {
        return this.perChantierName;
    }

    public void setPerChantierName(String perChantierName) {
        this.perChantierName = perChantierName;
    }

    public String getPerChantierID() {
        return this.perChantierID;
    }

    public void setPerChantierID(String perChantierID) {
        this.perChantierID = perChantierID;
    }

    public Date getPerChantierStartDate() {
        return this.perChantierStartDate;
    }

    public void setPerChantierStartDate(Date perChantierStartDate) {
        this.perChantierStartDate = perChantierStartDate;
    }

    public Date getPerChantierPlannedEndDate() {
        return this.perChantierPlannedEndDate;
    }

    public void setPerChantierPlannedEndDate(Date perChantierPlannedEndDate) {
        this.perChantierPlannedEndDate = perChantierPlannedEndDate;
    }

    public Date getPerChantierEndDate() {
        return this.perChantierEndDate;
    }

    public void setPerChantierEndDate(Date perChantierEndDate) {
        this.perChantierEndDate = perChantierEndDate;
    }

    public Delegation getParent() {
        return this.parent;
    }

    public void setParent(Delegation parent) {
        this.parent = parent;
    }

    public String getPartyDelegee() {
        return this.partyDelegee;
    }

    public void setPartyDelegee(String partyDelegee) {
        this.partyDelegee = partyDelegee;
    }

    public Float getAmount5() {
        return this.amount5;
    }

    public void setAmount5(Float amount5) {
        this.amount5 = amount5;
    }

    public String getEtjAddress() {
        return this.etjAddress;
    }

    public void setEtjAddress(String etjAddress) {
        this.etjAddress = etjAddress;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtjStatut() {
        return this.etjStatut;
    }

    public void setEtjStatut(String etjStatut) {
        this.etjStatut = etjStatut;
    }

    public String getDelegantNiveauHierarchique() {
        return this.delegantNiveauHierarchique;
    }

    public void setDelegantNiveauHierarchique(String delegantNiveauHierarchique) {
        this.delegantNiveauHierarchique = delegantNiveauHierarchique;
    }

    public String getDelegantLieuNaissance() {
        return this.delegantLieuNaissance;
    }

    public void setDelegantLieuNaissance(String delegantLieuNaissance) {
        this.delegantLieuNaissance = delegantLieuNaissance;
    }

    public Date getDelegantDateNaissance() {
        return this.delegantDateNaissance;
    }

    public void setDelegantDateNaissance(Date delegantDateNaissance) {
        this.delegantDateNaissance = delegantDateNaissance;
    }

    public String getDelegataireNiveauHierarchique() {
        return this.delegataireNiveauHierarchique;
    }

    public void setDelegataireNiveauHierarchique(String delegataireNiveauHierarchique) {
        this.delegataireNiveauHierarchique = delegataireNiveauHierarchique;
    }

    public String getDelegataireLieuNaissance() {
        return this.delegataireLieuNaissance;
    }

    public void setDelegataireLieuNaissance(String delegataireLieuNaissance) {
        this.delegataireLieuNaissance = delegataireLieuNaissance;
    }

    public Date getDelegataireDateNaissance() {
        return this.delegataireDateNaissance;
    }

    public void setDelegataireDateNaissance(Date delegataireDateNaissance) {
        this.delegataireDateNaissance = delegataireDateNaissance;
    }

    public String getDelegantNationality() {
        return this.delegantNationality;
    }

    public void setDelegantNationality(String delegantNationality) {
        this.delegantNationality = delegantNationality;
    }

    // public String getDelegataireNationality() {
    public String getDelegataireNationalite() {
        return this.delegataireNationalite;
    }

    // public void setDelegataireNationality(String delegataireNationality) {
    public void setDelegataireNationalite(String delegataireNationality) {
        this.delegataireNationalite = delegataireNationality;
    }

    public String getPerChantierCity() {
        return this.perChantierCity;
    }

    public void setPerChantierCity(String perChantierCity) {
        this.perChantierCity = perChantierCity;
    }

    public String getDelegantTitle() {
        return this.delegantTitle;
    }

    public void setDelegantTitle(String delegantTitle) {
        this.delegantTitle = delegantTitle;
    }

    public Boolean getIsModification() {
        return this.isModification;
    }

    public void setIsModification(Boolean isModification) {
        this.isModification = isModification;
    }

    public Boolean getIsValidation() {
        return this.isValidation;
    }

    public void setIsValidation(Boolean isValidation) {
        this.isValidation = isValidation;
    }

    public Boolean getIsLecture() {
        return this.isLecture;
    }

    public void setIsLecture(Boolean isLecture) {
        this.isLecture = isLecture;
    }

    public Boolean getIsCanRenew() {
        return this.isCanRenew;
    }

    public void setIsCanRenew(Boolean isCanRenew) {
        this.isCanRenew = isCanRenew;
    }

    public String getDelegataireStatut() {
        return this.delegataireStatut;
    }

    public void setDelegataireStatut(String delegataireStatut) {
        this.delegataireStatut = delegataireStatut;
    }

    public String getDelegataireAddress() {
        return this.delegataireAddress;
    }

    public void setDelegataireAddress(String delegataireAddress) {
        this.delegataireAddress = delegataireAddress;
    }

    public String getDelegantStatut() {
        return this.delegantStatut;
    }

    public void setDelegantStatut(String delegantStatut) {
        this.delegantStatut = delegantStatut;
    }

    public String getZone() {
        return this.zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getOperations() {
        return this.operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public void setDelegantStatutConseil(String delegantStatutConseil) {
        this.delegantStatutConseil = delegantStatutConseil;
    }

    public String getDelegantStatutConseil() {
        return this.delegantStatutConseil;
    }

    public void setDelegantDateEffet(Date delegantDateEffet) {
        this.delegantDateEffet = delegantDateEffet;
    }

    public Date getDelegantDateEffet() {
        return this.delegantDateEffet;
    }

    public void setDelegantDateConseil(Date delegantDateConseil) {
        this.delegantDateConseil = delegantDateConseil;
    }

    public Date getDelegantDateConseil() {
        return this.delegantDateConseil;
    }

    public void setDateDelegation(Date dateDelegation) {
        this.dateDelegation = dateDelegation;
    }

    public Date getDateDelegation() {
        return this.dateDelegation;
    }

    public void setDelegantNom1(String delegantNom1) {
        this.delegantNom1 = delegantNom1;
    }

    public String getDelegantNom1() {
        return this.delegantNom1;
    }

    public void setDelegantPrenom1(String delegantPrenom1) {
        this.delegantPrenom1 = delegantPrenom1;
    }

    public String getDelegantPrenom1() {
        return this.delegantPrenom1;
    }

    public void setDelegantQualite1(String delegantQualite1) {
        this.delegantQualite1 = delegantQualite1;
    }

    public String getDelegantQualite1() {
        return this.delegantQualite1;
    }

    // public Integer getIsSubdelegable() {
    // return isSubdelegable;
    // }
    //
    // public void setIsSubdelegable(Integer isSubdelegable) {
    // this.isSubdelegable = isSubdelegable;
    // }

}
