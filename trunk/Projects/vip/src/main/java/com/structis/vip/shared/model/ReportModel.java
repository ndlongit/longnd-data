package com.structis.vip.shared.model;

public class ReportModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String REPORT_NAME = "name";
    public static final String REPORT_URL = "url";

    public String getName() {
        return this.get(REPORT_NAME);
    }

    public void setName(String name) {
        this.set(REPORT_NAME, name);
    }

    public String getUrl() {
        return this.get(REPORT_URL);
    }

    public void setUrl(String url) {
        this.set(REPORT_URL, url);
    }

}
