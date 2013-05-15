package com.structis.vip.shared.model;

public class DocumentTypeModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String DOC_TYPE_ID = "id";
	public static final String DOC_TYPE_NAME = "name";
	public static final String DOC_TYPE_DESC = "description";
	public DocumentTypeModel(){
		
	}
	
	public DocumentTypeModel(String code) {
		set(DOC_TYPE_NAME, code);
	}

	public String getName() {
		return get(DOC_TYPE_NAME);
	}

	public void setName(String name) {
		set(DOC_TYPE_NAME, name);
	}
	
	public Integer getId() {
		return get(DOC_TYPE_ID);
	}

	public void setId(String id) {
		set(DOC_TYPE_ID, id);
	}
	public String getDescription() {
		return get(DOC_TYPE_DESC);
	}

	public void setDescription(String desc) {
		set(DOC_TYPE_DESC, desc);
	}


}
