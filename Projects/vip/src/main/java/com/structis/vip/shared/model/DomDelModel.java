package com.structis.vip.shared.model;

import java.util.Date;

public class DomDelModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String DDM_ID = "id";
	public static final String DDM_COMMENT = "comment";
	public static final String DDM_SINGED_DATE = "signedDate";
	public static final String DDM_SINGED_FILENAME = "signedFilename";
	public static final String DDM_DELEGATION = "delegation";
	public static final String DDM_DOCUMENT_MODEL = "documentMdl";
	public static final String DDM_HEMERA_LIEN = "hemeraLien";
	

	@SuppressWarnings("unused")
	private DelegationModel delegation;

	@SuppressWarnings("unused")
	private DocumentMdlModel documentMdl;

	public Integer getId() {
		return get(DDM_ID);
	}

	public void setId(Integer id) {
		set(DDM_ID, id);
	}

	public String getComment() {
		return get(DDM_COMMENT);
	}

	public void setComment(String comment) {
		set(DDM_COMMENT, comment);
	}

	public DelegationModel getDelegation() {
		return get(DDM_DELEGATION);
	}

	public void setDelegation(DelegationModel delegation) {
		set(DDM_DELEGATION, delegation);
	}

	public DocumentMdlModel getDocumentMdl() {
		return get(DDM_DOCUMENT_MODEL);
	}

	public void setDocumentMdl(DocumentMdlModel documentMdl) {
		set(DDM_DOCUMENT_MODEL, documentMdl);
	}

	public Date getSignedDate() {
		return get(DDM_SINGED_DATE);
	}

	public void setSignedDate(Date signedDate) {
		set(DDM_SINGED_DATE, signedDate);
	}
	
	public String getSignedFilename() {
		return get(DDM_SINGED_FILENAME);
	}

	public void setSignedFilename(String signedFilename) {
		set(DDM_SINGED_FILENAME, signedFilename);
	}
	
	public String getHemeraLien() {
		return get(DDM_HEMERA_LIEN);
	}

	public void setHemeraLien(String hemeraLien) {		
		set(DDM_HEMERA_LIEN, hemeraLien);
	}
}
