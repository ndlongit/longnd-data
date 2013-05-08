package com.structis.fichesst.shared.dto;

import java.util.Comparator;

public class LigTransfertppModel extends AbstractDto implements Comparator<LigTransfertppModel> {
	
	public static final String IDChantier="idChantier";
	public static final String IDBudj="idBudj";
	public static final String IDTransfertPP="idTransfertpp";
	public static final String IDLIG="id";
	public static final String COMMENTAIRES="commentaires";
	public static final String DEVERS="devers";
	public static final String LOT1="lot1";
	public static final String LOT2="lot2";
	public static final String QUANTITY="quantite";
	public static final String PU="pu";
	public static final String DATEMODIF="dateModif";
	public static final String TYPE="refTypeBudjConf";
	public static final String REFTRANSFERTPP="refTransfertpp";
	public static final String FICHETRANSFERTPP="ficheTransfertPp";
	public static final String TotalObj="totalObj";
	public static final String TotalDeVers="totalDevers";
	public static final String TotalTS="totalTs";
	public static final String TotalRD="totalRd";
	private SimpleDto refTypeBudjConf;
	private SimpleDto refTransfertpp;
	private FicheTransfertppDto ficheTransfertpp;
	public Double getTotalObj(){
		return this.get(TotalObj);
	}
	public void setTotalObj(Double totalObj){
		this.set(TotalObj, totalObj);
	}
	public Double getTotalDevers(){
		return this.get(TotalDeVers);
	}
	public void setTotalDevers(Double totalObj){
		this.set(TotalDeVers, totalObj);
	}
	public Double getTotalRd(){
		return this.get(TotalRD);
	}
	public void setTotalRd(Double totalObj){
		this.set(TotalRD, totalObj);
	}
	public Double getTotalTs(){
		return this.get(TotalTS);
	}
	public void setTotalTs(Double totalTs){
		this.set(TotalTS, totalTs);
	}
	public SimpleDto getRefTypeBudjConf() {
		return get(TYPE);
	}

	public void setRefTypeBudjConf(SimpleDto refTypeBudjConf) {
		set(TYPE, refTypeBudjConf);
	}
	public SimpleDto getReftransfertPp() {
		return get(REFTRANSFERTPP);
	}

	public void setReftransfertPp(SimpleDto reftransfertPp) {
		set(REFTRANSFERTPP, reftransfertPp);
	}
	public Integer getIdChantier(){
		return this.get(IDChantier);
	}
	public void setIdChantier(Integer idChantier){
		this.set(IDChantier, idChantier);
	}
	public Integer getIdBudj(){
		return this.get(IDBudj);
	}
	public void setIdBudj(Integer idBudj){
		this.set(IDBudj,idBudj);
	}
	public Integer getIdTransfertpp(){
		return this.get(IDTransfertPP);
	}
	public void setIdTransfertpp(Integer idTransfertpp){
		this.set(IDTransfertPP,idTransfertpp);
	}
	public Integer getQuantite(){
		return this.get(QUANTITY, 1);
	}
	public void setQuantite(Integer quantity){
		this.set(QUANTITY, quantity);
	}
	public String  getCommentaires(){
		return this.get(COMMENTAIRES);
	}
	public void setCommentaires(String comment){
		this.set(COMMENTAIRES,comment);
	}
	
	public String getDeVers(){
		return this.get(DEVERS);
	}
	public void setDeVers(String devers){
		this.set(DEVERS,devers);
	}
	public String  getLot1(){
		return this.get(LOT1);
	}
	public void setLot1(String lot1){
		this.set(LOT1,lot1);
	}
	public String  getLot2(){
		return this.get(LOT2);
	}
	public void setLot2(String lot2){
		this.set(LOT2,lot2);
	}
	public String getDateModif(){
		return this.get(DATEMODIF);
	}
	public void setDateModif(String dateModif){
		this.set(DATEMODIF,dateModif);
	}

	public Double getPu(){
		return this.get(PU, 0.0);
	}
	public void setPu(Double pu){
		this.set(PU, pu);
	}

	public FicheTransfertppDto getFicheTransfertPp() {
		return get(FICHETRANSFERTPP);
	}

	public void setFicheTransfertPp(FicheTransfertppDto ficheTransfertPp) {
		set(FICHETRANSFERTPP, ficheTransfertPp);
	}

	@Override
	public int compare(LigTransfertppModel o1, LigTransfertppModel o2) {
		return 0;
	}
	public LigTransfertppModel(){}
	public void initData(){
		SimpleDto typeBudj=new SimpleDto();
		typeBudj.setLabel("Obj");
		typeBudj.setId(0);
		this.setRefTypeBudjConf(typeBudj);	
	}
	public LigTransfertppModel(SimpleDto refTypebudj,String devers,String lot1,String lot2,String commentaires,Integer quantity,Double pu){
		setRefTypeBudjConf(refTypebudj);
		setDeVers(devers);
		setLot1(lot1);
		setLot2(lot2);
		setCommentaires(commentaires);
		setQuantite(quantity);
		setPu(pu);
	}
	public LigTransfertppModel(SimpleDto refTypebudj,String devers,Integer quantity,Double pu){
		setRefTypeBudjConf(refTypebudj);
		setDeVers(devers);
		setQuantite(quantity);
		setPu(pu);
	}
	public LigTransfertppModel(SimpleDto refTypebudj,String devers){
		setRefTypeBudjConf(refTypebudj);
		setDeVers(devers);
	}
	public LigTransfertppModel(SimpleDto refTypebudj) {
		setRefTypeBudjConf(refTypebudj);
	}
}
