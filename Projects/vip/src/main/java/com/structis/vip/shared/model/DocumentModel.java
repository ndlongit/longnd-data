package com.structis.vip.shared.model;

import java.util.Date;

public class DocumentModel extends BaseModelDataActivable {
	private static final long serialVersionUID = -2256867506534020595L;
	public static final String DOC_ID = "id";
	public static final String DOC_NAME = "name";
	public static final String DOC_LINK = "link";
	public static final String DOC_COMMENT = "comment";
	public static final String DOC_CATEGORY = "category";
	public static final String DOC_DATE = "date";
			
	@SuppressWarnings("unused")
	private CategoryModel categoryModel;
	
	public Integer getId() {
		return get (DOC_ID);
	}

	public void setId(Integer id) {
		set(DOC_ID, id);
	}

	public String getName() {
		return get (DOC_NAME);
	}

	public void setName(String name) {
		set(DOC_NAME, name);
	}	

	public String getLink() {
		return get(DOC_LINK);
	}

	public void setLink(String link) {
		set(DOC_LINK, link);
	}		
	
	public String getComment() {
		return get(DOC_COMMENT);
	}

	public void setComment(String version) {
		set(DOC_COMMENT, version);
	}

	public CategoryModel getCategory() {
		return get(DOC_CATEGORY);
	}

	public void setCategory(CategoryModel category) {
		set(DOC_CATEGORY, category);
	}	
	
	public Date getDate() {
		return get(DOC_DATE);
	}

	public void setDate(Date date) {
		set(DOC_DATE, date);
	}
}
