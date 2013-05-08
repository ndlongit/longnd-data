package com.structis.fichesst.shared.dto;

import java.util.Date;
import java.util.List;

public class FicheStDto extends AbstractDto {

	public static final String ACPT_COMMENTAIRES = "acptCommentaires";

	public static final String ACPT_COMMENTAIRES_INTERNES = "acptCommentairesInternes";

	public static final String AVCT_COMMENTAIRES = "avctCommentaires";

	public static final String DGDPRESENTE = "refDgdPresente";

	public static final String DATEDGDPRESENTE = "dateDgdPresente";

	public static final String DATEMARCHEBASE = "dateMarcheBase";

	public static final String MODIFIEDDATE = "modifiedDate";

	public static final String GEST_BUDGET_INITIAL = "gestBudgetInitial";

	public static final String GEST_DATE_DERNIER_PT = "gestDateDernierPt";

	public static final String GEST_ECART_DERNIER_PT = "gestEcartDernierPt";

	public static final String PRESTAASSURANCES = "prestaAssurances";

	public static final String PRESTABADGE = "prestaBadge";

	public static final String PRESTABENNE = "prestaBenne";

	public static final String PRESTACANTO = "prestaCanto";

	public static final String PRESTAGRUE = "prestaGrue";

	public static final String PRESTALIFT = "prestaLift";

	public static final String PRESTANETTOYAGE = "prestaNettoyage";

	public static final String PRESTAPILOTAGE = "prestaPilotage";

	public static final String SOCIETE = "societe";

	public static final String PRESTA_PRORATA = "prestaProrata";

	public static final String RG = "rg";

	public static final String GESTIONS = "gestions";

	public static final String CAUTION_FOURNIE_LIST = "cautionFournies";

	public static final String PAYMENT_MODE = "paymentMode";

	public static final String LOT = "lot";

	public static final String LOT_TYPE = "lotType";

	public static final String REFDDEAGREMENT = "refDdeAgrement";

	public static final String REFDECENALE = "refDecenale";

	public static final String CONDUCTOR = "conductor";

	public static final String DEDUCTIONS = "deductions";

	public static final String PENALTIES = "penalties";

	public static final String PROGRESSES = "progresses";

	public static final String SYNTHECARTDERPT = "synthEcartDerPt";

	public static final String SYNTHECARTM1 = "synthEcartM1";

	public static final String OBJECTIF = "objectif";

	public static final String OBJ = "obj";

	public static final String TRANSFERTS = "transferts";

	public static final String RD = "rd";

	public static final String TS = "ts";

	public static final String TRAITE = "traite";

	public static final String ARRETE = "arrete";

	public static final String NON_ARRETE = "nonArrete";

	public static final String PROVISION = "provision";

	public static final String DEVISREFUSE = "devisRefuse";

	public static final String TOTAL = "total";

	public static final String FINAL_TOTAL = "finalTotal";

	public static final String ECART_M = "EcartM";

	public static final String ECARTM1 = "EcartM1";

	public static final String VARIATION_M = "VariationM";

	public static final String ECART_DERNIER_POINT = "EcartDernierPoint";

	public static final String VARIATION_ECART_POINT_M = "VariationEcartPointM";

	public static final String AVCT_BA_PERCENTAGE = "AvctBAPercentage";

	public static final String AVCT_REEL_PERCENTAGE = "AvctReelPercentage";

	public static final String MONTANT_REEL = "montantReel";//TRAITE;

	public static final String TOTAL2 = "total2";

	public static final String PRESTATION_TRAITE_PERCENTAGE = "PrestationTraitePercentage";

	public static final String TOTAL_PENALTY = "PenaliteFacture";

	public static final String PRORATA_APPLIQUE_ST = PRESTA_PRORATA;

	public static final String PRORATA_MARCHE = "ProrataMarche";

	public static final String PRORATA_SUR_RAD = "ProrataSurRAD";

	public static final String MARCHES_RESTANT_A_TRAITER_PERCENTAGE = "MarchesRestantATraiterPercentage";

	public static final String MARCHES_RESTANT_A_TRAITER = "MarchesRestantATraiter";

	public static final String VARIATION_DU_AUX_TRANSFERTS = "VariationDuAuxTransferts";

	public static final String MANQUE_AGAGNERST_SANS_PRORATA = "ManqueAGagnerSTSansProrata";

	public static final String TOTAL_CANTO = "totalCanto";

	public static final String TOTAL_BADGE = "totalBadge";

	public static final String TOTAL_GRUE = "totalGrue";

	public static final String TOTAL_LIFT = "totalLift";

	public static final String TOTAL_BENNE = "totalBenne";

	public static final String TOTAL_NETTOY = "totalNettoy";

	public static final String TOTAL_AUTRES = "totalAutres";

	public static final String TOTAL_PRORATA = "totalProrata";

	public static final String TOTAL_DEDUCTION = "totalDeduction";

	public static final String TOTAL_CUMULE = "totalCumule";

	public static final String TOTAL_REEL_ACTIVITIVE = "totalReelActivitive";

	@SuppressWarnings("unused")
	private SimpleDto paymentMode;

	@SuppressWarnings("unused")
	private LotDto lot;

	@SuppressWarnings("unused")
	private LotTypeDto lotType;

	@SuppressWarnings("unused")
	private SimpleDto refDecenale;

	@SuppressWarnings("unused")
	private SimpleDto refDdeAgrement;

	@SuppressWarnings("unused")
	private SimpleDto refDgdPresente;

	@SuppressWarnings("unused")
	private ConductorDto conductor;

	@SuppressWarnings("unused")
	private List<CautionFournieDto> cautionFournies;

	@SuppressWarnings("unused")
	private List<DeductionDto> deductions;

	@SuppressWarnings("unused")
	private List<GestionDto> gestions;

	@SuppressWarnings("unused")
	private List<PenaltyDto> penalties;

	@SuppressWarnings("unused")
	private List<ProgressDto> progresses;

	//	@SuppressWarnings("unused")
	//	private SocieteDto societe;

	public FicheStDto() {
		LotDto lotDto = new LotDto();
		setLot(lotDto);
		//		SocieteDto societe = new SocieteDto();
		//		setSociete(societe);
	}

	public String getAcptCommentaires() {
		return get(ACPT_COMMENTAIRES);
	}

	public void setAcptCommentaires(String acptCommentaires) {
		set(ACPT_COMMENTAIRES, acptCommentaires);
	}

	public String getAcptCommentairesInternes() {
		return get(ACPT_COMMENTAIRES_INTERNES);
	}

	public void setAcptCommentairesInternes(String acptCommentairesInternes) {
		set(ACPT_COMMENTAIRES_INTERNES, acptCommentairesInternes);
	}

	public String getAvctCommentaires() {
		return get(AVCT_COMMENTAIRES);
	}

	public void setAvctCommentaires(String avctCommentaires) {
		set(AVCT_COMMENTAIRES, avctCommentaires);
	}

	public Date getDateDgdPresente() {
		return get(DATEDGDPRESENTE);
	}

	public void setDateDgdPresente(Date dateDgdPresente) {
		set(DATEDGDPRESENTE, dateDgdPresente);
	}

	public Date getDateMarcheBase() {
		return get(DATEMARCHEBASE);
	}

	public void setDateMarcheBase(Date dateMarcheBase) {
		set(DATEMARCHEBASE, dateMarcheBase);
	}

	public Date getModifiedDate() {
		return get(MODIFIEDDATE);
	}

	public void setModifiedDate(Date modifiedDate) {
		set(MODIFIEDDATE, modifiedDate);
	}

	public Double getGestBudgetInitial() {
		Object obj = get(GEST_BUDGET_INITIAL);
		if( obj == null ) {
			return 0.0;
		}
		return get(GEST_BUDGET_INITIAL);
	}

	public void setGestBudgetInitial(Double gestBudgetInitial) {
		set(GEST_BUDGET_INITIAL, gestBudgetInitial);
	}

	public String getGestDateDernierPt() {
		return get(GEST_DATE_DERNIER_PT);
	}

	public void setGestDateDernierPt(Double gestDateDernierPt) {
		set(GEST_DATE_DERNIER_PT, gestDateDernierPt);
	}

	public Double getGestEcartDernierPt() {
		Object obj = get(GEST_ECART_DERNIER_PT);
		if( obj == null ) {
			return 0.0;
		}
		return get(GEST_ECART_DERNIER_PT);
	}

	public void setGestEcartDernierPt(Double gestEcartDernierPt) {
		set(GEST_ECART_DERNIER_PT, gestEcartDernierPt);
	}

	public Double getPrestaAssurances() {
		return get(PRESTAASSURANCES);
	}

	public void setPrestaAssurances(Double prestaAssurances) {
		set(PRESTAASSURANCES, prestaAssurances);
	}

	public Double getPrestaBadge() {
		Object obj = get(PRESTABADGE);
		if( obj == null ) {
			return 0.0;
		}
		return get(PRESTABADGE);
	}

	public void setPrestaBadge(Double prestaBadge) {
		set(PRESTABADGE, prestaBadge);
	}

	public Double getPrestaBenne() {
		Object obj = get(PRESTABENNE);
		if( obj == null ) {
			return 0.0;
		}
		return get(PRESTABENNE);
	}

	public void setPrestaBenne(Double prestaBenne) {
		set(PRESTABENNE, prestaBenne);
	}

	public Double getPrestaCanto() {
		Object obj = get(PRESTACANTO);
		if( obj == null ) {
			return 0.0;
		}
		return get(PRESTACANTO);
	}

	public void setPrestaCanto(Double prestaCanto) {
		set(PRESTACANTO, prestaCanto);
	}

	public Double getPrestaGrue() {
		Object obj = get(PRESTAGRUE);
		if( obj == null ) {
			return 0.0;
		}
		return get(PRESTAGRUE);
	}

	public void setPrestaGrue(Double prestaGrue) {
		set(PRESTAGRUE, prestaGrue);
	}

	public Double getPrestaLift() {
		Object obj = get(PRESTALIFT);
		if( obj == null ) {
			return 0.0;
		}
		return get(PRESTALIFT);
	}

	public void setPrestaLift(Double prestaLift) {
		set(PRESTALIFT, prestaLift);
	}

	public Double getPrestaNettoyage() {
		Object obj = get(PRESTANETTOYAGE);
		if( obj == null ) {
			return 0.0;
		}
		return get(PRESTANETTOYAGE);
	}

	public void setPrestaNettoyage(Double prestaNettoyage) {
		set(PRESTANETTOYAGE, prestaNettoyage);
	}

	public Double getPrestaPilotage() {
		Object obj = get(PRESTAPILOTAGE);
		if( obj == null ) {
			return 0.0;
		}
		return get(PRESTAPILOTAGE);
	}

	public void setPrestaPilotage(Double prestaPilotage) {
		set(PRESTAPILOTAGE, prestaPilotage);
	}

	public Double getPrestaProrata() {
		Object obj = get(PRESTA_PRORATA);
		if( obj == null ) {
			return 0.0;
		}
		return get(PRESTA_PRORATA);
	}

	public void setPrestaProrata(Double prestaProrata) {
		set(PRESTA_PRORATA, prestaProrata);
	}

	public Double getRg() {
		Object obj = get(RG);
		if( obj == null ) {
			return 0.0;
		}
		return get(RG);
	}

	public void setRg(Double rg) {
		set(RG, rg);
	}

	public String getSociete() {
		return get(SOCIETE);
	}

	public void setSociete(String societe) {
		set(SOCIETE, societe);
	}

	public String getSynthEcartDerPt() {
		return get(SYNTHECARTDERPT);
	}

	public void setSynthEcartDerPt(String synthEcartDerPt) {
		set(SYNTHECARTDERPT, synthEcartDerPt);
	}

	public String getSynthEcartM1() {
		return get(SYNTHECARTM1);
	}

	public void setSynthEcartM1(String synthEcartM1) {
		set(SYNTHECARTM1, synthEcartM1);
	}

	public List<GestionDto> getGestions() {
		return get(GESTIONS);
	}

	public void setGestions(List<GestionDto> gestions) {
		set(GESTIONS, gestions);
	}

	public List<CautionFournieDto> getCautionFournies() {
		return get(CAUTION_FOURNIE_LIST);
	}

	public void setCautionFournies(List<CautionFournieDto> cautionFournieList) {
		set(CAUTION_FOURNIE_LIST, cautionFournieList);
	}

	public SimpleDto getPaymentMode() {
		return get(PAYMENT_MODE);
	}

	public void setPaymentMode(SimpleDto paymentMode) {
		set(PAYMENT_MODE, paymentMode);
	}

	public LotTypeDto getLotType() {
		return get(LOT_TYPE);
	}

	public void setLotType(LotTypeDto lotType) {
		set(LOT_TYPE, lotType);
	}

	public LotDto getLot() {
		return get(LOT);
	}

	public void setLot(LotDto lot) {
		set(LOT, lot);
	}

	public SimpleDto getRefDgdPresente() {
		return get(DGDPRESENTE);
	}

	public void setRefDgdPresente(SimpleDto dgdPresente) {
		set(DGDPRESENTE, dgdPresente);
	}

	public SimpleDto getRefDdeAgrement() {
		return get(REFDDEAGREMENT);
	}

	public void setRefDdeAgrement(SimpleDto refDdeAgrement) {
		set(REFDDEAGREMENT, refDdeAgrement);
	}

	public SimpleDto getRefDecenale() {
		return get(REFDECENALE);
	}

	public void setRefDecenale(SimpleDto refDecenale) {
		set(REFDECENALE, refDecenale);
	}

	public ConductorDto getConductor() {
		return get(CONDUCTOR);
	}

	public void setConductor(ConductorDto conductor) {
		set(CONDUCTOR, conductor);
	}

	public List<DeductionDto> getDeductions() {
		return get(DEDUCTIONS);
	}

	public void setDeductions(List<DeductionDto> deductions) {
		set(DEDUCTIONS, deductions);
	}

	public List<PenaltyDto> getPenalties() {
		return get(PENALTIES);
	}

	public void setPenalties(List<PenaltyDto> penalties) {
		set(PENALTIES, penalties);
	}

	public List<ProgressDto> getProgresses() {
		return get(PROGRESSES);
	}

	public void setProgresses(List<ProgressDto> progresses) {
		set(PROGRESSES, progresses);
	}

	public Double getObjectif() {
		Object obj = get(OBJECTIF);
		if( obj == null ) {
			return 0.0;
		}

		return get(OBJECTIF);
	}

	public void setObjectif(Double objectif) {
		set(OBJECTIF, objectif);
	}

	public Double getObj() {
		Object obj = get(OBJ);
		if( obj == null ) {
			return 0.0;
		}

		return get(OBJ);
	}

	public void setObj(Double obj) {
		set(OBJ, obj);
	}

	public Double getTransferts() {
		Object obj = get(TRANSFERTS);
		if( obj == null ) {
			return 0.0;
		}

		return get(TRANSFERTS);
	}

	public void setTransferts(Double transferts) {
		set(TRANSFERTS, transferts);
	}

	public Double getRd() {
		Object obj = get(RD);
		if( obj == null ) {
			return 0.0;
		}

		return get(RD);
	}

	public void setRd(Double rd) {
		set(RD, rd);
	}

	public Double getTs() {
		Object obj = get(TS);
		if( obj == null ) {
			return 0.0;
		}

		return get(TS);
	}

	public void setTs(Double ts) {
		set(TS, ts);
	}

	public Double getTraite() {
		return get(TRAITE, 0.0);
	}

	public void setTraite(Double traite) {
		set(TRAITE, traite);
	}

	public Double getArrete() {
		Object obj = get(ARRETE);
		if( obj == null ) {
			return 0.0;
		}

		return get(ARRETE);
	}

	public void setArrete(Double arrete) {
		set(ARRETE, arrete);
	}

	public Double getNonArrete() {
		Object obj = get(NON_ARRETE);
		if( obj == null ) {
			return 0.0;
		}

		return get(NON_ARRETE);
	}

	public void setNonArrete(Double traiter) {
		set(NON_ARRETE, traiter);
	}

	public Double getProvision() {
		Object obj = get(PROVISION);
		if( obj == null ) {
			return 0.0;
		}

		return get(PROVISION);
	}

	public void setProvision(Double provision) {
		set(PROVISION, provision);
	}

	public Double getDevisRefuse() {
		Object obj = get(DEVISREFUSE);
		if( obj == null ) {
			return 0.0;
		}

		return get(DEVISREFUSE);
	}

	public void setDevisRefuse(Double devisRefuse) {
		set(DEVISREFUSE, devisRefuse);
	}

	public Double getEcartM1() {
		Object obj = get(ECARTM1);
		if( obj == null ) {
			return 0.0;
		}

		return get(ECARTM1);
	}

	public void setEcartM1(Double EcartM1) {
		set(ECARTM1, EcartM1);
	}

	public Double getEcartDernierPoint() {
		return get(ECART_DERNIER_POINT, 0.0);
	}

	public void setEcartDernierPoint(Double EcartDernierPoint) {
		set(ECART_DERNIER_POINT, EcartDernierPoint);
	}

	public Double getTotalPenalty() {
		return get(TOTAL_PENALTY, 0.0);
	}

	public void setTotalPenalty(Double PenaliteFacture) {
		set(TOTAL_PENALTY, PenaliteFacture);
	}

	public Double getProrataAppliqueST() {
		return get(PRORATA_APPLIQUE_ST, 0.0);
	}

	public void setProrataAppliqueST(Double ProrataAppliqueST) {
		set(PRORATA_APPLIQUE_ST, ProrataAppliqueST);
	}

	public Double getTotalCanto() {
		return get(TOTAL_CANTO, 0.0);
	}

	public void setTotalCanto(Double totalCanto) {
		set(TOTAL_CANTO, totalCanto);
	}

	public Double getTotalBadge() {
		return get(TOTAL_BADGE, 0.0);
	}

	public void setTotalBadge(Double totalBadge) {
		set(TOTAL_BADGE, totalBadge);
	}

	public Double getTotalGrue() {
		return get(TOTAL_GRUE, 0.0);
	}

	public void setTotalGrue(Double totalGrue) {
		set(TOTAL_GRUE, totalGrue);
	}

	public Double getTotalLift() {
		return get(TOTAL_LIFT, 0.0);
	}

	public void setTotalLift(Double totalLift) {
		set(TOTAL_LIFT, totalLift);
	}

	public Double getTotalBenne() {
		return get(TOTAL_BENNE, 0.0);
	}

	public void setTotalBenne(Double totalBenne) {
		set(TOTAL_BENNE, totalBenne);
	}

	public Double getTotalNettoy() {
		return get(TOTAL_NETTOY, 0.0);
	}

	public void setTotalNettoy(Double totalNettoy) {
		set(TOTAL_NETTOY, totalNettoy);
	}

	public Double getTotalAutres() {
		return get(TOTAL_AUTRES, 0.0);
	}

	public void setTotalAutres(Double totalAutres) {
		set(TOTAL_AUTRES, totalAutres);
	}

	public Double getTotalProrata() {
		return get(TOTAL_PRORATA, 0.0);
	}

	public void setTotalProrata(Double totalProrata) {
		set(TOTAL_PRORATA, totalProrata);
	}

	public Double getTotalDeduction() {
		return get(TOTAL_DEDUCTION, 0.0);
	}

	public void setTotalDeduction(Double totalDeduction) {
		set(TOTAL_DEDUCTION, totalDeduction);
	}

	public Double getTotalCumule() {
		return get(TOTAL_CUMULE, 0.0);
	}

	public void setTotalCumule(Double totalCumule) {
		set(TOTAL_CUMULE, totalCumule);
	}

	public Double getTotalReelActivitive() {
		return get(TOTAL_REEL_ACTIVITIVE, 0.0);
	}

	public void setTotalReelActivitive(Double totalReelActivitive) {
		set(TOTAL_REEL_ACTIVITIVE, totalReelActivitive);
	}

	//Calculated columns - Begin
	private Double getTotal() {
		return getObj() + getTransferts() + getRd() + getTs();
	}

	private double getFinalTotal() {
		return getTraite() + getArrete() + getNonArrete() + getProvision();
	}

	public double getEcartM() {
		double finalTotal = getFinalTotal();
		double total = getTotal();

		return finalTotal - total;
	}

	public Double getTotal2() {
		return getTotalCanto() + getTotalBadge() + getTotalGrue() + getTotalLift() + getTotalBenne() + getTotalNettoy() + getTotalAutres() + getTotalProrata();
	}

	public Double getAvctBAPercentage() {

		double totalCumule = getTotalCumule();
		double totalMch = getTraite();
		double result;

		if( totalMch == 0 ) {
			result = 0.0;
		}
		else {
			if( totalCumule <= 0 ) {
				result = 0.0;
			}
			else {
				result = totalCumule * 1.0 / totalMch * 100;
			}
		}

		return Math.round(result) * 1.0;
	}

	public Double getProrataMarche() {
		double prestaProrata = getPrestaProrata();
		double totalTraite = getTraite();
		double arrete = getArrete();

		return prestaProrata * (totalTraite + arrete);
	}

	public Double getProrataSurRAD() {
		double prestaProrata = getPrestaProrata();
		Double nonArrete = getNonArrete();
		Double provision = getProvision();

		return prestaProrata * (nonArrete + provision);
	}

	public Double getMarchesRestantATraiterPercentage() {
		double prorataAppliqueST = getProrataAppliqueST();
		double traite = getTraite();
		if( prorataAppliqueST == 0 && traite == 0 ) {
			return getProrataTheorique() * 1.0;
		}
		else {
			return 0.0;
		}
	}

	public Double getMarchesRestantATraiter() {
		return getTotalCumule() * getProrataTheorique();
	}

	private Integer getProrataTheorique() {
		try {
			return getLot().getChantier().getProrataTheorique();
		}
		catch( Exception e ) {
			return 0;
		}
	}

	public Double getAvctReelPercentage() {
		double result;
		Double totalReelActivitive = getTotalReelActivitive();
		Double totalTraite = getTraite();
		if( totalTraite == 0 ) {
			result = 0.0;
		}
		else {
			result = totalReelActivitive / totalTraite * 100;
		}
		return Math.round(result) * 1.0;
	}

	public Double getPrestationTraitePercentage() {
		double result;
		double traite = getTraite();
		double total = getTotal();
		if( total == 0 ) {
			result = 0.0;
		}
		else {
			result = traite / total * 100;
		}
		
		return Math.round(result) * 1.0;
	}

	public Double getVariationDuAuxTransferts() {
		double marchesRestantATraiterPercentage = getMarchesRestantATraiterPercentage();
		double prorataAppliqueST = getProrataAppliqueST();
		double transferts = getTransferts(); // +/-
		if( marchesRestantATraiterPercentage > 0 || prorataAppliqueST > 0 ) {
			return transferts * (marchesRestantATraiterPercentage + prorataAppliqueST);
		}
		else {
			return transferts * getProrataTheorique();
		}
	}
	
	public Double getManqueAGagnerSTSansProrata() {
		double marchesRestantATraiterPercentage = getMarchesRestantATraiterPercentage();
		double prorataAppliqueST = getProrataAppliqueST();
		if( marchesRestantATraiterPercentage == 0 || prorataAppliqueST == 0 ) {
			return getFinalTotal() * getProrataTheorique();
		}
		else {
			return 0.0;
		}
	}

	public Double getVariationM() {
		return getEcartM() - getEcartM1();
	}

	public Double getVariationEcartPointM() {
		return getEcartM() - getEcartDernierPoint();
	}

	//Calculated columns - End

	@SuppressWarnings("unchecked")
	@Override
	public <X> X get(String property) {
		Object result = null;
		if( TOTAL.equals(property) ) {
			result = getTotal();
		}
		else if( FINAL_TOTAL.equals(property) ) {
			result = getFinalTotal();
		}
		else if( VARIATION_M.equals(property) ) {
			result = getVariationM();
		}
		else if( ECART_M.equals(property) ) {
			result = getEcartM();
		}
		else if( VARIATION_ECART_POINT_M.equals(property) ) {
			result = getVariationEcartPointM();
		}
		else if( TOTAL2.equals(property) ) {
			result = getTotal2();
		}
		else if( AVCT_BA_PERCENTAGE.equals(property) ) {
			result = getAvctBAPercentage();
		}
		else if( PRORATA_MARCHE.equals(property) ) {
			result = getProrataMarche();
		}
		else if( PRORATA_SUR_RAD.equals(property) ) {
			result = getProrataSurRAD();
		}
		else if( MARCHES_RESTANT_A_TRAITER_PERCENTAGE.equals(property) ) {
			result = getMarchesRestantATraiterPercentage();
		}
		else if( MARCHES_RESTANT_A_TRAITER.equals(property) ) {
			result = getMarchesRestantATraiter();
		}
		else if( AVCT_REEL_PERCENTAGE.equals(property) ) {
			result = getAvctReelPercentage();
		}
		else if( PRESTATION_TRAITE_PERCENTAGE.equals(property) ) {
			result = getPrestationTraitePercentage();
		}
		else if( VARIATION_DU_AUX_TRANSFERTS.equals(property) ) {
			result = getVariationDuAuxTransferts();
		}
		else if( MANQUE_AGAGNERST_SANS_PRORATA.equals(property) ) {
			result = getManqueAGagnerSTSansProrata();
		}
		else if( MONTANT_REEL.equals(property) ) {
			result = getTraite();
		}
		else {
			result = super.get(property);
		}

		return (X) result;
	}

	@Override
	public void initTestData() {
		setDateDgdPresente(new Date());

		SimpleDto paymentMode = new SimpleDto();
		paymentMode.setId(2);
		paymentMode.setLabel("45 jours fin de mois");
		setPaymentMode(paymentMode);

		LotTypeDto lotType = new LotTypeDto();
		lotType.setId(5);
		lotType.setName("Autre");
		setLotType(lotType);

		SimpleDto refDecenale = new SimpleDto();
		refDecenale.setId(3);
		refDecenale.setLabel("Décennale reçue");
		setRefDecenale(refDecenale);

		SimpleDto refDdeAgrement = new SimpleDto();
		refDdeAgrement.setId(1);
		refDdeAgrement.setLabel("A faire");
		setRefDdeAgrement(refDdeAgrement);

		SimpleDto dgdPresente = new SimpleDto();
		dgdPresente.setId(1);
		dgdPresente.setLabel("Non");
		setRefDgdPresente(dgdPresente);

		setIdSiTravaux("abc");

//		ConductorDto conductor1 = new ConductorDto();
//		conductor1.setPreName("Nguyen");
//		conductor1.setName("Long");
//		conductor1.setId(5);
//		conductor1.setIdChantier(10);
		//		setConductor(conductor1);

		setPrestaAssurances(2.0);
		setPrestaBadge(1.5);
		setPrestaBenne(1.0);
		setPrestaCanto(0.5);
		setPrestaGrue(2.2);
		setPrestaLift(1.1);
		setPrestaNettoyage(3.0);
		setPrestaPilotage(1.2);
		setPrestaProrata(4.5);
		setSociete("Societe 01");
		getLot().setName("Lot 10");
	}
}
