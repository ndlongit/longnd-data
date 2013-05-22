package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.ReportModel;

public interface ClientReportServiceAsync {

    public static class Util {

        private static ClientReportServiceAsync instance = GWT.create(ClientReportService.class);

        public static ClientReportServiceAsync getInstance() {
            return instance;
        }
    }

    void getReports(AsyncCallback<List<ReportModel>> callback);
}
