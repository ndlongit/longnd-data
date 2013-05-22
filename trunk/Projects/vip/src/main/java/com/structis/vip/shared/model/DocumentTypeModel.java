package com.structis.vip.shared.model;

public class DocumentTypeModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String DOC_TYPE_ID = "id";
    public static final String DOC_TYPE_NAME = "name";
    public static final String DOC_TYPE_DESC = "description";

    public DocumentTypeModel() {

    }

    public DocumentTypeModel(String code) {
        this.set(DOC_TYPE_NAME, code);
    }

    public String getName() {
        return this.get(DOC_TYPE_NAME);
    }

    public void setName(String name) {
        this.set(DOC_TYPE_NAME, name);
    }

    @Override
    public Integer getId() {
        return this.get(DOC_TYPE_ID);
    }

    public void setId(String id) {
        this.set(DOC_TYPE_ID, id);
    }

    public String getDescription() {
        return this.get(DOC_TYPE_DESC);
    }

    public void setDescription(String desc) {
        this.set(DOC_TYPE_DESC, desc);
    }

}
