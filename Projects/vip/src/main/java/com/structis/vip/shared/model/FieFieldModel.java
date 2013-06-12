package com.structis.vip.shared.model;

public class FieFieldModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 6807380804987897136L;
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

    public String getFormFieldId() {
        return this.get(FIE_FORM_FIELD);
    }

    public void setFormFieldId(String formFieldId) {
        this.set(FIE_FORM_FIELD, formFieldId);
    }

    public String getLabel() {
        return this.get(FIE_LABEL);
    }

    public void setLabel(String label) {
        this.set(FIE_LABEL, label);
    }

    public LanguageModel getLanguage() {
        return this.get(FIE_LANGUAGE);
    }

    public void setLanguage(LanguageModel language) {
        this.set(FIE_LANGUAGE, language);
    }

    public EntiteModel getEntite() {
        return this.get(FIE_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(FIE_ENTITE, entite);
    }

    public String getDbField() {
        return this.get(FIE_DB_FIELD);
    }

    public void setDbField(String dbField) {
        this.set(FIE_DB_FIELD, dbField);
    }

    public String getGroup() {
        return this.get(FIE_GROUP);
    }

    public void setGroup(String group) {
        this.set(FIE_GROUP, group);
    }
}
