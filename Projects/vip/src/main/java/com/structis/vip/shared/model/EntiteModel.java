package com.structis.vip.shared.model;

public class EntiteModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 6807380804987897136L;
	public static final String ENTITE_ID = "entId";
	public static final String ENTITE_NAME = "name";
	public static final String ENTITE_DEFAULT_LANG = "language";
	public static final String ENTITE_PERIMETRES = "perimetres";
	
	@SuppressWarnings("unused")
	private LanguageModel languageModel;

	public String getEntId() {
		return get(ENTITE_ID);
	}

	public void setEntId(String id) {
		set(ENTITE_ID, id);
	}

	public String getName() {
		return get(ENTITE_NAME);
	}

	public void setName(String name) {
		set(ENTITE_NAME, name);
	}

	public LanguageModel getLanguage() {
		return get(ENTITE_DEFAULT_LANG);
	}

	public void setLanguage(LanguageModel language) {
		set(ENTITE_DEFAULT_LANG, language);
	}
}