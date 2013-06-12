package com.structis.vip.shared.model;

import java.util.Date;

public class DomDelModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

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

    public String getComment() {
        return this.get(DDM_COMMENT);
    }

    public void setComment(String comment) {
        this.set(DDM_COMMENT, comment);
    }

    public DelegationModel getDelegation() {
        return this.get(DDM_DELEGATION);
    }

    public void setDelegation(DelegationModel delegation) {
        this.set(DDM_DELEGATION, delegation);
    }

    public DocumentMdlModel getDocumentMdl() {
        return this.get(DDM_DOCUMENT_MODEL);
    }

    public void setDocumentMdl(DocumentMdlModel documentMdl) {
        this.set(DDM_DOCUMENT_MODEL, documentMdl);
    }

    public Date getSignedDate() {
        return this.get(DDM_SINGED_DATE);
    }

    public void setSignedDate(Date signedDate) {
        this.set(DDM_SINGED_DATE, signedDate);
    }

    public String getSignedFilename() {
        return this.get(DDM_SINGED_FILENAME);
    }

    public void setSignedFilename(String signedFilename) {
        this.set(DDM_SINGED_FILENAME, signedFilename);
    }

    public String getHemeraLien() {
        return this.get(DDM_HEMERA_LIEN);
    }

    public void setHemeraLien(String hemeraLien) {
        this.set(DDM_HEMERA_LIEN, hemeraLien);
    }
}
