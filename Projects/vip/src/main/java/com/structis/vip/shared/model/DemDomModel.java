package com.structis.vip.shared.model;

public class DemDomModel extends BaseModelDataActivable {

    private static final long serialVersionUID = -2256867506534020595L;
    public static final String DEM_DOM_GROUP = "group";
    public static final String DEM_DOM_DOCUMENT_MODEL = "documentMdl";

    @SuppressWarnings("unused")
    private DocumentMdlModel documentMdl;

    public Integer getGroup() {
        return this.get(DEM_DOM_GROUP);
    }

    public void setGroup(Integer group) {
        this.set(DEM_DOM_GROUP, group);
    }

    public DocumentMdlModel getDocumentMdl() {
        return this.get(DEM_DOM_DOCUMENT_MODEL);
    }

    public void setDocumentMdl(DocumentMdlModel documentMdl) {
        this.set(DEM_DOM_DOCUMENT_MODEL, documentMdl);
    }
}
