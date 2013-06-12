package com.structis.vip.shared.model;

public class EntiteModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 6807380804987897136L;
    public static final String ENTITE_NAME = "name";
    public static final String ENTITE_DEFAULT_LANG = "language";
    public static final String ENTITE_PERIMETRES = "perimetres";

    @SuppressWarnings("unused")
    private LanguageModel languageModel;

    public String getEntId() {
        return this.get(BASE_ID);
    }

    public void setEntId(String id) {
        this.set(BASE_ID, id);
    }

    public String getName() {
        return this.get(ENTITE_NAME);
    }

    public void setName(String name) {
        this.set(ENTITE_NAME, name);
    }

    public LanguageModel getLanguage() {
        return this.get(ENTITE_DEFAULT_LANG);
    }

    public void setLanguage(LanguageModel language) {
        this.set(ENTITE_DEFAULT_LANG, language);
    }
}
