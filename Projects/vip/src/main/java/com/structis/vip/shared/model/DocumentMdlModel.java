package com.structis.vip.shared.model;

public class DocumentMdlModel extends BaseModelDataActivable {

    private static final long serialVersionUID = -2256867506534020595L;
    public static final String DOM_NAME = "name";
    public static final String DOM_TYPE = "type";
    public static final String DOM_LANGUAGE = "language";
    public static final String DOM_ENTITE = "entite";
    public static final String DOM_FILENAME = "filename";
    public static final String DOM_TEMP_FILENAME = "tempFilename";
    public static final String SUB_DEL_FILENAME = "subDelFilename";
    public static final String DOM_SIGNED_FILENAME = "signedFilename";
    public static final String DOM_VERSION = "version";

    @SuppressWarnings("unused")
    private EntiteModel entiteModel;

    @SuppressWarnings("unused")
    private LanguageModel languageModel;

    public String getName() {
        return this.get(DOM_NAME);
    }

    public void setName(String name) {
        this.set(DOM_NAME, name);
    }

    public String getType() {
        return this.get(DOM_TYPE);
    }

    public void setType(String type) {
        this.set(DOM_TYPE, type);
    }

    public LanguageModel getLanguage() {
        return this.get(DOM_LANGUAGE);
    }

    public void setLanguage(LanguageModel language) {
        this.set(DOM_LANGUAGE, language);
    }

    public EntiteModel getEntite() {
        return this.get(DOM_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(DOM_ENTITE, entite);
    }

    public String getFilename() {
        return this.get(DOM_FILENAME);
    }

    public void setFilename(String filename) {
        this.set(DOM_FILENAME, filename);
    }

    public String getTempFilename() {
        return this.get(DOM_TEMP_FILENAME);
    }

    public void setTempFilename(String tempFilename) {
        this.set(DOM_TEMP_FILENAME, tempFilename);
    }

    public String getSubDelFilename() {
        return get(SUB_DEL_FILENAME);
    }

    public void setSubDelFilename(String subDelFilename) {
        set(SUB_DEL_FILENAME, subDelFilename);
    }

    public String getVersion() {
        return this.get(DOM_VERSION);
    }

    public void setVersion(String version) {
        this.set(DOM_VERSION, version);
    }

    public String getSignedFilename() {
        return this.get(DOM_SIGNED_FILENAME);
    }

    public void setSignedFilename(String signedFilename) {
        this.set(DOM_SIGNED_FILENAME, signedFilename);
    }
}
