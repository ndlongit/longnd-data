package com.structis.fichesst.server.bean.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;
import com.structis.fichesst.server.bean.domain.core.Timestampable;
import com.structis.fichesst.server.util.AppUtil;

@Entity
@Table(name = "FICHE_ST")
@AttributeOverride(name = "id", column = @Column(name = "ID_FICHE_ST"))
public class FicheSt extends NumericIdEntity implements Timestampable {

	private static final long serialVersionUID = 1L;

	private String acptCommentaires;

	private String acptCommentairesInternes;

	private String avctCommentaires;

	private Date dateDgdPresente;

	private Date dateMarcheBase;

	private Date modifiedDate;

	private float gestBudgetInitial;

	private Date gestDateDernierPt;

	private Double gestEcartDernierPt;

	private Conductor conductor;

	private Lot lot;

	private RefDdeAgrement refDdeAgrement;

	private RefDecenale refDecenale;

	private RefDgdPresente refDgdPresente;

	private LotType lotType;

	private String idSiTravaux;

	private Double objectif;

	private Double prestaAssurances;

	private Double prestaBadge;

	private Double prestaBenne;

	private Double prestaCanto;

	private Double prestaGrue;

	private Double prestaLift;

	private Double prestaNettoyage;

	private Double prestaPilotage;

	private Double prestaProrata;

	private Double rg;

	// private Societe societe;
	private String societe;

	private List<CautionFournie> cautionFournies;

	private List<Gestion> gestions;

	private List<Deduction> deductions;

	private List<Penalty> penalties;

	private List<Progress> progresses;

	private RefModePaiement paymentMode;

	private Double obj;

	private Double transferts;

	private Double rd;

	private Double ts;

	private Double traite;

	private Double arrete;

	private Double nonArrete;

	private Double provision;

	private Double devisRefuse;

	private Double ecartM1;

	private Double ecartDernierPoint;

	//Transient fields - Begin
	private Double totalCanto;

	private Double totalBadge;

	private Double totalGrue;

	private Double totalLift;

	private Double totalBenne;

	private Double totalNettoy;

	private Double totalAutres;

	private Double totalProrata;

	private Double totalCumule;

	private Double totalReelActivitive;

	private Double totalPenalty;

	//Transient fields - End

	public FicheSt() {
	}

	@Lob()
	@Column(name = "acpt_commentaires")
	public String getAcptCommentaires() {
		return acptCommentaires;
	}

	public void setAcptCommentaires(String acptCommentaires) {
		this.acptCommentaires = acptCommentaires;
	}

	@Column(name = "acpt_commentaires_internes")
	public String getAcptCommentairesInternes() {
		return acptCommentairesInternes;
	}

	public void setAcptCommentairesInternes(String acptCommentairesInternes) {
		this.acptCommentairesInternes = acptCommentairesInternes;
	}

	@Lob()
	@Column(name = "avct_commentaires")
	public String getAvctCommentaires() {
		return avctCommentaires;
	}

	public void setAvctCommentaires(String avctCommentaires) {
		this.avctCommentaires = avctCommentaires;
	}

	@Column(name = "date_dgd_presente")
	public Date getDateDgdPresente() {
		return dateDgdPresente;
	}

	public void setDateDgdPresente(Date dateDgdPresente) {
		this.dateDgdPresente = dateDgdPresente;
	}

	@Column(name = "date_marche_base")
	public Date getDateMarcheBase() {
		return dateMarcheBase;
	}

	public void setDateMarcheBase(Date dateMarcheBase) {
		this.dateMarcheBase = dateMarcheBase;
	}

	@Column(name = "gest_budget_initial")
	public float getGestBudgetInitial() {
		return gestBudgetInitial;
	}

	public void setGestBudgetInitial(float gestBudgetInitial) {
		this.gestBudgetInitial = gestBudgetInitial;
	}

	@Column(name = "gest_date_dernier_pt")
	public Date getGestDateDernierPt() {
		return gestDateDernierPt;
	}

	public void setGestDateDernierPt(Date gestDateDernierPt) {
		this.gestDateDernierPt = gestDateDernierPt;
	}

	@Column(name = "gest_ecart_dernier_pt")
	public Double getGestEcartDernierPt() {
		return gestEcartDernierPt;
	}

	public void setGestEcartDernierPt(Double gestEcartDernierPt) {
		this.gestEcartDernierPt = gestEcartDernierPt;
	}

	@Column(name = "presta_assurances")
	public Double getPrestaAssurances() {
		return prestaAssurances;
	}

	public void setPrestaAssurances(Double prestaAssurances) {
		this.prestaAssurances = prestaAssurances;
	}

	@Column(name = "presta_badge")
	public Double getPrestaBadge() {
		return prestaBadge;
	}

	public void setPrestaBadge(Double prestaBadge) {
		this.prestaBadge = prestaBadge;
	}

	@Column(name = "presta_benne")
	public Double getPrestaBenne() {
		return prestaBenne;
	}

	public void setPrestaBenne(Double prestaBenne) {
		this.prestaBenne = prestaBenne;
	}

	@Column(name = "presta_canto")
	public Double getPrestaCanto() {
		return prestaCanto;
	}

	public void setPrestaCanto(Double prestaCanto) {
		this.prestaCanto = prestaCanto;
	}

	@Column(name = "presta_grue")
	public Double getPrestaGrue() {
		return prestaGrue;
	}

	public void setPrestaGrue(Double prestaGrue) {
		this.prestaGrue = prestaGrue;
	}

	@Column(name = "presta_lift")
	public Double getPrestaLift() {
		return prestaLift;
	}

	public void setPrestaLift(Double prestaLift) {
		this.prestaLift = prestaLift;
	}

	@Column(name = "presta_nettoyage")
	public Double getPrestaNettoyage() {
		return prestaNettoyage;
	}

	public void setPrestaNettoyage(Double prestaNettoyage) {
		this.prestaNettoyage = prestaNettoyage;
	}

	@Column(name = "presta_pilotage")
	public Double getPrestaPilotage() {
		return prestaPilotage;
	}

	public void setPrestaPilotage(Double prestaPilotage) {
		this.prestaPilotage = prestaPilotage;
	}

	@Column(name = "presta_prorata")
	public Double getPrestaProrata() {
		return prestaProrata;
	}

	public void setPrestaProrata(Double prestaProrata) {
		this.prestaProrata = prestaProrata;
	}

	@Column(name = "rg")
	public Double getRg() {
		return rg;
	}

	public void setRg(Double rg) {
		this.rg = rg;
	}

	@OneToMany(mappedBy = "ficheSt", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	public List<Gestion> getGestions() {
		return gestions;
	}

	public void setGestions(List<Gestion> gestions) {
		this.gestions = gestions;
	}

	@OneToMany(mappedBy = "ficheSt", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	public List<CautionFournie> getCautionFournies() {
		return cautionFournies;
	}

	public void setCautionFournies(List<CautionFournie> cautionFournies) {
		this.cautionFournies = cautionFournies;
	}

	@OneToMany(mappedBy = "ficheSt", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Deduction> getDeductions() {
		return deductions;
	}

	public void setDeductions(List<Deduction> deductions) {
		this.deductions = deductions;
	}

	@OneToMany(mappedBy = "ficheSt", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Penalty> getPenalties() {
		return penalties;
	}

	public void setPenalties(List<Penalty> penalties) {
		this.penalties = penalties;
	}

	@OneToMany(mappedBy = "ficheSt", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Progress> getProgresses() {
		return progresses;
	}

	public void setProgresses(List<Progress> progresses) {
		this.progresses = progresses;
	}

	@ManyToOne
	@JoinColumn(name = "ID_MODE_PAIEMENT")
	public RefModePaiement getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(RefModePaiement paymentMode) {
		this.paymentMode = paymentMode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TYPE_LOT")
	public LotType getLotType() {
		return lotType;
	}

	public void setLotType(LotType lotType) {
		this.lotType = lotType;
	}

	@Column(name = "ID_REF_SI_TRAVAUX")
	public String getIdSiTravaux() {
		return idSiTravaux;
	}

	public void setIdSiTravaux(String idSiTravaux) {
		this.idSiTravaux = idSiTravaux;
	}

	@ManyToOne
	@JoinColumn(name = "id_dde_agrement")
	public RefDdeAgrement getRefDdeAgrement() {
		return refDdeAgrement;
	}

	public void setRefDdeAgrement(RefDdeAgrement refDdeAgrement) {
		this.refDdeAgrement = refDdeAgrement;
	}

	@ManyToOne
	@JoinColumn(name = "id_decenale")
	public RefDecenale getRefDecenale() {
		return refDecenale;
	}

	public void setRefDecenale(RefDecenale refDecenale) {
		this.refDecenale = refDecenale;
	}

	@ManyToOne
	@JoinColumn(name = "id_dgd_presente")
	public RefDgdPresente getRefDgdPresente() {
		return refDgdPresente;
	}

	public void setRefDgdPresente(RefDgdPresente refDgdPresente) {
		this.refDgdPresente = refDgdPresente;
	}

	@ManyToOne
	@JoinColumn(name = "ID_CONDUCTEUR")
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

	//	@ManyToOne
	//	@JoinColumn(name = "id_societe")
	//	public Societe getSociete() {
	//		return societe;
	//	}
	//
	//	public void setSociete(Societe societe) {
	//		this.societe = societe;
	//	}

	@Column(name = "societe")
	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	@Column(name = "objectif")
	public Double getObjectif() {
		return objectif;
	}

	public void setObjectif(Double objectif) {
		this.objectif = objectif;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_lot")
	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	@Column(name = "synth_ecart_m1")
	public Double getEcartM1() {
		return this.ecartM1;
	}

	public void setEcartM1(Double ecartM1) {
		this.ecartM1 = ecartM1;
	}

	@Column(name = "synth_ecart_der_pt")
	public Double getEcartDernierPoint() {
		return this.ecartDernierPoint;
	}

	public void setEcartDernierPoint(Double ecartDernierPoint) {
		this.ecartDernierPoint = ecartDernierPoint;
	}

	@Override
	@Column(name = "date_modif")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Transient
	public Double getObj() {
		return obj;
	}

	public void setObj(Double obj) {
		this.obj = obj;
	}

	@Transient
	public Double getTransferts() {
		return transferts;
	}

	public void setTransferts(Double transferts) {
		this.transferts = transferts;
	}

	@Transient
	public Double getRd() {
		return rd;
	}

	public void setRd(Double rd) {
		this.rd = rd;
	}

	@Transient
	public Double getTs() {
		return ts;
	}

	public void setTs(Double ts) {
		this.ts = ts;
	}

	@Transient
	public Double getTraite() {
		return traite;
	}

	public void setTraite(Double traite) {
		this.traite = traite;
	}

	@Transient
	public Double getArrete() {
		return arrete;
	}

	public void setArrete(Double arrete) {
		this.arrete = arrete;
	}

	@Transient
	public Double getNonArrete() {
		return nonArrete;
	}

	public void setNonArrete(Double nonArrete) {
		this.nonArrete = nonArrete;
	}

	@Transient
	public Double getProvision() {
		return provision;
	}

	public void setProvision(Double provision) {
		this.provision = provision;
	}

	@Transient
	public Double getDevisRefuse() {
		return devisRefuse;
	}

	public void setDevisRefuse(Double devisRefuse) {
		this.devisRefuse = devisRefuse;
	}

	@Transient
	public Double getTotalCanto() {
		return this.totalCanto;
	}

	public void setTotalCanto(Double totalCanto) {
		this.totalCanto = totalCanto;
	}

	@Transient
	public Double getTotalBadge() {
		return this.totalBadge;
	}

	public void setTotalBadge(Double totalBadge) {
		this.totalBadge = totalBadge;
	}

	@Transient
	public Double getTotalGrue() {
		return this.totalGrue;
	}

	public void setTotalGrue(Double totalGrue) {
		this.totalGrue = totalGrue;
	}

	@Transient
	public Double getTotalLift() {
		return this.totalLift;
	}

	public void setTotalLift(Double totalLift) {
		this.totalLift = totalLift;
	}

	@Transient
	public Double getTotalBenne() {
		return this.totalBenne;
	}

	public void setTotalBenne(Double totalBenne) {
		this.totalBenne = totalBenne;
	}

	@Transient
	public Double getTotalNettoy() {
		return this.totalNettoy;
	}

	public void setTotalNettoy(Double totalNettoy) {
		this.totalNettoy = totalNettoy;
	}

	@Transient
	public Double getTotalAutres() {
		return this.totalAutres;
	}

	public void setTotalAutres(Double totalAutres) {
		this.totalAutres = totalAutres;
	}

	@Transient
	public Double getTotalProrata() {
		return this.totalProrata;
	}

	public void setTotalProrata(Double totalProrata) {
		this.totalProrata = totalProrata;
	}

	@Transient
	public Double getTotalCumule() {
		return this.totalCumule;
	}

	public void setTotalCumule(Double totalCumule) {
		this.totalCumule = totalCumule;
	}

	@Transient
	public Double getTotalReelActivitive() {
		return this.totalReelActivitive;
	}

	public void setTotalReelActivitive(Double totalReelActivitive) {
		this.totalReelActivitive = totalReelActivitive;
	}

	@Transient
	public Double getTotalPenalty() {
		return this.totalPenalty;
	}

	public void setTotalPenalty(Double totalPenalty) {
		this.totalPenalty = totalPenalty;
	}

	// Calculate transient fields
	@Transient
	public void calculateValues() {
		calculateGestionValues();
		calculateProgressValues();
		calculateDeductionValues();
		calculatePenaltyValue();
	}

	@Transient
	private void calculateGestionValues() {
		List<Gestion> gestionList = getGestions();

		double totalObj = 0.0;
		double totalTF = 0.0;
		double totalTS = 0.0;
		double totalRD = 0.0;

		double totalTraite = 0.0;
		double totalArrete = 0.0;
		double totalNonArrete = 0.0;
		double totalProvision = 0.0;
		double totalDevisRefuse = 0.0;
		double totalReelActivitive = 0.0;

		if( !AppUtil.isNullOrEmpty(gestionList) ) {
			for( Gestion gestion : gestionList ) {
				if( gestion == null ) {
					continue;
				}

				totalTraite += gestion.getTraite();
				totalArrete += gestion.getArrete();
				totalNonArrete += gestion.getNonArrete();
				totalProvision += gestion.getProvision();
				totalDevisRefuse += gestion.getDevisRefuse();
				totalReelActivitive += gestion.getReelActivitive();

				RefTypeBudjConf type = gestion.getType();
				if( type == null ) {
					continue;
				}

				Integer typeId = type.getId();
				double value = gestion.getAmount2();
				switch( typeId ) {
					case 1 :
						totalObj += value;
						break;
					case 2 :
						totalTF += value;
						break;
					case 3 :
						totalRD += value;
						break;
					case 4 :
						totalTS += value;
						break;
					default :
						break;
				}
			}
		}

		setObj(totalObj);
		setTransferts(totalTF);
		setTs(totalTS);
		setRd(totalRD);

		setTraite(totalTraite);
		setArrete(totalArrete);
		setNonArrete(totalNonArrete);
		setProvision(totalProvision);
		setDevisRefuse(totalDevisRefuse);
		setTotalReelActivitive(totalReelActivitive);
	}

	@Transient
	private void calculateProgressValues() {
		double totalCumule = 0.0;

		List<Progress> progressList = getProgresses();
		if( !AppUtil.isNullOrEmpty(progressList) ) {
			for( Progress progress : progressList ) {
				Double cumule = progress.getCumule();
				if( cumule == null ) {
					cumule = 0.0;
				}

				totalCumule += cumule;
			}
		}

		setTotalCumule(totalCumule);
	}

	@Transient
	private void calculateDeductionValues() {
		double totalCanto = 0.0;
		double totalBadge = 0.0;
		double totalGrue = 0.0;
		double totalLift = 0.0;
		double totalBenne = 0.0;
		double totalNettoy = 0.0;
		double totalAutres = 0.0;
		double totalProrata = 0.0;

		List<Deduction> deductionList = getDeductions();
		if( !AppUtil.isNullOrEmpty(deductionList) ) {
			double canto = getPrestaCanto();
			if( canto == 0 ) {
				canto = 1;
			}
			double badge = getPrestaBadge();
			if( badge == 0 ) {
				badge = 1;
			}
			double grue = getPrestaGrue();
			if( grue == 0 ) {
				grue = 1;
			}
			double lift = getPrestaLift();
			if( lift == 0 ) {
				lift = 1;
			}
			double benne = getPrestaBenne();
			if( benne == 0 ) {
				benne = 1;
			}
			double nettoy = getPrestaNettoyage();
			if( nettoy == 0 ) {
				nettoy = 1;
			}

			for( Deduction deduction : deductionList ) {
				Integer cantoQuantity = deduction.getCanto();
				if( cantoQuantity == null ) {
					cantoQuantity = 1;
				}
				Integer badgeQuantity = deduction.getBadge();
				if( badgeQuantity == null ) {
					badgeQuantity = 1;
				}
				Integer grueQuantity = deduction.getGrue();
				if( grueQuantity == null ) {
					grueQuantity = 1;
				}
				Integer liftQuantity = deduction.getLift();
				if( liftQuantity == null ) {
					liftQuantity = 1;
				}
				Integer benneQuantity = deduction.getBenne();
				if( benneQuantity == null ) {
					benneQuantity = 1;
				}
				Integer nettoyageQuantity = deduction.getNettoyage();
				if( nettoyageQuantity == null ) {
					nettoyageQuantity = 1;
				}

				totalCanto += cantoQuantity * canto;
				totalBadge += badgeQuantity * badge;
				totalGrue += grueQuantity * grue;
				totalLift += liftQuantity * lift;
				totalBenne += benneQuantity * benne;
				totalNettoy += nettoyageQuantity * nettoy;
				totalAutres += deduction.getAutres();
				totalProrata += deduction.getProrata();
			}
		}

		setTotalCanto(totalCanto);
		setTotalBadge(totalBadge);
		setTotalGrue(totalGrue);
		setTotalLift(totalLift);
		setTotalBenne(totalBenne);
		setTotalNettoy(totalNettoy);
		setTotalAutres(totalAutres);
		setTotalProrata(totalProrata);
	}

	@Transient
	private void calculatePenaltyValue() {
		double totalPenalty = 0.0;
		List<Penalty> penaltyList = getPenalties();
		if( !AppUtil.isNullOrEmpty(penaltyList) ) {
			for( Penalty penalty : penaltyList ) {
				totalPenalty += penalty.getAmount();
			}
		}
		setTotalPenalty(totalPenalty);
	}
}
