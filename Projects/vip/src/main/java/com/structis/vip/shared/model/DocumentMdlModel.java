package com.structis.vip.shared.model;

public class DocumentMdlModel extends BaseModelDataActivable {
	private static final long serialVersionUID = -2256867506534020595L;
	public static final String DOM_ID = "id";
	public static final String DOM_NAME = "name";
	public static final String DOM_TYPE = "type";
//	public static final String DOM_VARIABLES = "variables";
	public static final String DOM_LANGUAGE = "language";
	public static final String DOM_ENTITE = "entite";
	public static final String DOM_FILENAME = "filename";
	public static final String DOM_TEMP_FILENAME = "tempFilename";
	public static final String DOM_SIGNED_FILENAME = "signedFilename";
	public static final String DOM_VERSION = "version";
		
	@SuppressWarnings("unused")
	private EntiteModel entiteModel;
	
	@SuppressWarnings("unused")
	private LanguageModel languageModel;	
	
	public Integer getId() {
		return get (DOM_ID);
	}

	public void setId(Integer id) {
		set(DOM_ID, id);
	}

	public String getName() {
		return get (DOM_NAME);
	}

	public void setName(String name) {
		set(DOM_NAME, name);
	}

	public String getType() {
		return get (DOM_TYPE);
	}

	public void setType(String type) {
		set(DOM_TYPE, type);
	}

//	public String getVariables() {
//		return get (DOM_VARIABLES);
//	}
//
//	public void setVariables(String variables) {
//		set(DOM_VARIABLES, variables);
//	}

	public LanguageModel getLanguage() {
		return get (DOM_LANGUAGE);
	}

	public void setLanguage(LanguageModel language) {
		set(DOM_LANGUAGE, language);
	}

	public EntiteModel getEntite() {
		return get (DOM_ENTITE);
	}

	public void setEntite(EntiteModel entite) {
		set(DOM_ENTITE, entite);
	}

	public String getFilename() {
		return get(DOM_FILENAME);
	}

	public void setFilename(String filename) {
		set(DOM_FILENAME, filename);
	}		
	
	public String getTempFilename() {
		return get(DOM_TEMP_FILENAME);
	}

	public void setTempFilename(String tempFilename) {
		set(DOM_TEMP_FILENAME, tempFilename);
	}
	
	public String getVersion() {
		return get(DOM_VERSION);
	}

	public void setVersion(String version) {
		set(DOM_VERSION, version);
	}
	
	public String getSignedFilename() {
		return get(DOM_SIGNED_FILENAME);
	}

	public void setSignedFilename(String signedFilename) {
		set(DOM_SIGNED_FILENAME, signedFilename);
	}
}
