package com.structis.vip.shared.model;

public class ReportModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String REPORT_ID = "id";
	public static final String REPORT_NAME = "name";
	public static final String REPORT_URL = "url";

	public Integer getId() {
		return get(REPORT_ID);
	}

	public void setId(Integer id) {
		set(REPORT_ID, id);
	}

	public String getName() {
		return get(REPORT_NAME);
	}

	public void setName(String name) {
		set(REPORT_NAME, name);
	}

	public String getUrl() {
		return get(REPORT_URL);
	}

	public void setUrl(String url) {
		set(REPORT_URL, url);
	}

}