package com.structis.vip.server.service.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientReportService;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.util.CatalinaPropertiesUtil;
import com.structis.vip.shared.model.ReportModel;

@Service("clientReportService")
public class ClientReportServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientReportService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientReportServiceImpl.class);

    @Override
    public List<ReportModel> getReports() {
        List<ReportModel> lst = new ArrayList<ReportModel>();
        ReportModel mdl = new ReportModel();
        mdl.setId(1);
        mdl.setUrl(CatalinaPropertiesUtil.getReport1Url());
        lst.add(mdl);

        mdl = new ReportModel();
        mdl.setId(2);
        mdl.setUrl(CatalinaPropertiesUtil.getReport2Url());
        lst.add(mdl);

        mdl = new ReportModel();
        mdl.setId(3);
        mdl.setUrl(CatalinaPropertiesUtil.getReport3Url());
        lst.add(mdl);

        mdl = new ReportModel();
        mdl.setId(4);
        mdl.setUrl(CatalinaPropertiesUtil.getReport4Url());
        lst.add(mdl);

        mdl = new ReportModel();
        mdl.setId(5);
        mdl.setUrl(CatalinaPropertiesUtil.getReport5Url());
        lst.add(mdl);

        mdl = new ReportModel();
        mdl.setId(6);
        mdl.setUrl(CatalinaPropertiesUtil.getReport6Url());
        lst.add(mdl);

        mdl = new ReportModel();
        mdl.setId(7);
        mdl.setUrl(CatalinaPropertiesUtil.getReport7Url());
        lst.add(mdl);

        mdl = new ReportModel();
        mdl.setId(8);
        mdl.setUrl(CatalinaPropertiesUtil.getReport8Url());
        lst.add(mdl);

        return lst;
    }

}
