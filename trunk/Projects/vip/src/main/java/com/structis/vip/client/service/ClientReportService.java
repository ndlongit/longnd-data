package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.ReportModel;

@RemoteServiceRelativePath("springGwtServices/clientReportService")
public interface ClientReportService extends RemoteService {

    List<ReportModel> getReports();
}
