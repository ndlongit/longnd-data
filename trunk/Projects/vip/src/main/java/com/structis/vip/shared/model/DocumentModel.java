package com.structis.vip.shared.model;

import java.util.Date;

public class DocumentModel extends BaseModelDataActivable {

    private static final long serialVersionUID = -2256867506534020595L;
    public static final String DOC_NAME = "name";
    public static final String DOC_LINK = "link";
    public static final String DOC_COMMENT = "comment";
    public static final String DOC_CATEGORY = "category";
    public static final String DOC_DATE = "date";

    @SuppressWarnings("unused")
    private CategoryModel categoryModel;

    public String getName() {
        return this.get(DOC_NAME);
    }

    public void setName(String name) {
        this.set(DOC_NAME, name);
    }

    public String getLink() {
        return this.get(DOC_LINK);
    }

    public void setLink(String link) {
        this.set(DOC_LINK, link);
    }

    public String getComment() {
        return this.get(DOC_COMMENT);
    }

    public void setComment(String version) {
        this.set(DOC_COMMENT, version);
    }

    public CategoryModel getCategory() {
        return this.get(DOC_CATEGORY);
    }

    public void setCategory(CategoryModel category) {
        this.set(DOC_CATEGORY, category);
    }

    public Date getDate() {
        return this.get(DOC_DATE);
    }

    public void setDate(Date date) {
        this.set(DOC_DATE, date);
    }
}
