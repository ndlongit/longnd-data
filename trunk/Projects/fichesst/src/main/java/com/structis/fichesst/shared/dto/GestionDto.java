package com.structis.fichesst.shared.dto;

public class GestionDto extends AbstractDto implements Comparable<GestionDto> {

	private static final long serialVersionUID = 1L;

	public static final String LOCK = "lock";

	public static final String DEVIS = "devis";

	public static final String STATUT = "statut";

	public static final String LABEL = "label";

	public static final String COMMENT = "comment";

	public static final String ARRETE = "arrete";

	public static final String NON_ARRETE = "nonArrete";

	public static final String DEVIS_REFUSE = "devisRefuse";

	public static final String TOTAL_FDC = "totalFdc";

	public static final String PROVISION = "provision";

	public static final String REEL_ACTIVITIVE = "activiteReel";

	public static final String TYPE = "type";

	public static final String LABEL2 = "label2";

	public static final String AMOUNT2 = "amount2";

	public static final String ECART = "ecart";

	public static final String AMOUNT = "amount";

	public static final String MARCHE = "marche";

	public static final String TRAITE = "montantMchAv";

	public static final String GROUPING = "grouping";

	public GestionDto() {
		/*SimpleDto lock = new SimpleDto();
		lock.setId(0);
		lock.setLabel("Non");*/
	}

	@Override
	public void initTestData() {
		setLabel("Label 1");
		SimpleDto marche = new SimpleDto();
		marche.setId(3);
		marche.setLabel("MCH2");
		this.setMarche(marche);
		setAmount(1.2);
		setTraite(4.5);
		setReelActivitive(3.1);

		SimpleDto status = new SimpleDto();
		status.setId(3);
		status.setLabel("En cours");
		setStatut(status);

		SimpleDto type = new SimpleDto();
		type.setId(2);
		type.setLabel("+/-");
		this.setType(type);

		setDevis("Devis 1");
		setDevisRefuse(9.3);
		setArrete(11.0);
		setNonArrete(5.0);
		setProvision(5.0);
		setAmount2(2.3);
		setLabel2("Label 2");
	}

	public Boolean getLock() {
		return get(LOCK, false);
	}

	public void setLock(Boolean lock) {
		set(LOCK, lock);
	}

	public String getDevis() {
		return get(DEVIS);
	}

	public void setDevis(String devis) {
		set(DEVIS, devis);
	}

	public SimpleDto getStatut() {
		return get(STATUT);
	}

	public void setStatut(SimpleDto statut) {
		set(STATUT, statut);
	}

	public String getLabel() {
		return get(LABEL);
	}

	public void setLabel(String label) {
		set(LABEL, label);
	}

	public String getComment() {
		return get(COMMENT);
	}

	public void setComment(String comment) {
		set(COMMENT, comment);
	}

	public Double getAmount() {
		Object obj = get(AMOUNT);
		if( obj == null ) {
			return 0.0;
		}
		return get(AMOUNT);
	}

	public void setAmount(Double amount) {
		set(AMOUNT, amount);
	}

	public SimpleDto getMarche() {
		return get(MARCHE);
	}

	public void setMarche(SimpleDto marche) {
		set(MARCHE, marche);
	}

	public Double getArrete() {
		return get(ARRETE, 0.0);
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

	public void setNonArrete(Double nonArrete) {
		set(NON_ARRETE, nonArrete);
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
		Object obj = get(DEVIS_REFUSE);
		if( obj == null ) {
			return 0.0;
		}
		return get(DEVIS_REFUSE);
	}

	public void setDevisRefuse(Double devisRefuse) {
		set(DEVIS_REFUSE, devisRefuse);
	}

	public Double getReelActivitive() {
		Object obj = get(REEL_ACTIVITIVE);
		if( obj == null ) {
			return 0.0;
		}
		return get(REEL_ACTIVITIVE);
	}

	public void setReelActivitive(Double reelActivitive) {
		set(REEL_ACTIVITIVE, reelActivitive);
	}

	public SimpleDto getType() {
		return get(TYPE);
	}

	public void setType(SimpleDto type) {
		set(TYPE, type);
	}

	public String getLabel2() {
		return get(LABEL2);
	}

	public void setLabel2(String label2) {
		set(LABEL2, label2);
	}

	public Double getAmount2() {
		Object obj = get(AMOUNT2);
		if( obj == null ) {
			return 0.0;
		}
		return get(AMOUNT2);
	}

	public void setAmount2(Double amount2) {
		set(AMOUNT2, amount2);
	}

	public Double getTraite() {
		return get(TRAITE, 0.0);
	}

	public void setTraite(Double montantMchAv) {
		set(TRAITE, montantMchAv);
	}

	@Override
	public int compareTo(GestionDto obj) {
		if( this == obj ) {
			return 0;
		}

		SimpleDto march1 = this.getMarche();
		SimpleDto march2 = obj.getMarche();

		if( march1 != null && march2 != null ) {
			return march1.compareTo(march2);
		}
		else {
			if( march1 == null ) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}
	
	//Calculated columns - Begin
	public Double getTotalFdc() {
		return getTraite() + getArrete() + getNonArrete() + getProvision();
	}

	public Double getEcart() {
		return getAmount2() - getTotalFdc();
	}

	//Calculated columns - End

	@SuppressWarnings("unchecked")
	@Override
	public <X> X get(String property) {
		Object result = null;
		if( TOTAL_FDC.equals(property) ) {
			result = getTotalFdc();
		}
		else if( ECART.equals(property) ) {
			result = getEcart();
		}
		else {
			result = super.get(property);
		}

		return (X) result;
	}
}
