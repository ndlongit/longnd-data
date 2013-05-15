package com.structis.vip.shared.model;

public class FieFieldModel extends BaseModelDataActivable {
	
	private static final long serialVersionUID = 6807380804987897136L;
	public static final String FIE_ID = "id";	
	public static final String FIE_FORM_FIELD = "formFieldId";
	public static final String FIE_LABEL = "label";
	public static final String FIE_LANGUAGE = "language";
	public static final String FIE_ENTITE = "entite";
	public static final String FIE_DB_FIELD = "dbField";	
	public static final String FIE_GROUP = "group";	

	@SuppressWarnings("unused")
	private LanguageModel languageModel;
	
	@SuppressWarnings("unused")
	private EntiteModel entiteModel;
	
	public Integer getId() {
		return get(FIE_ID);
	}
	public void setId(Integer id) {
		set(FIE_ID, id);
	}
	public String getFormFieldId() {
		return get(FIE_FORM_FIELD);
	}
	public void setFormFieldId(String formFieldId) {
		set(FIE_FORM_FIELD, formFieldId);
	}
	public String getLabel() {
		return get(FIE_LABEL);
	}
	public void setLabel(String label) {
		set(FIE_LABEL, label);
	}
	public LanguageModel getLanguage() {
		return get(FIE_LANGUAGE);
	}
	public void setLanguage(LanguageModel language) {
		set(FIE_LANGUAGE, language);
	}
	public EntiteModel getEntite() {
		return get(FIE_ENTITE);
	}
	public void setEntite(EntiteModel entite) {
		set(FIE_ENTITE, entite);
	}
	public String getDbField() {
		return get(FIE_DB_FIELD);
	}
	public void setDbField(String dbField) {
		set(FIE_DB_FIELD, dbField);
	}
	
	public String getGroup() {
		return get(FIE_GROUP);
	}
	public void setGroup(String group) {
		set(FIE_GROUP, group);
	}
}