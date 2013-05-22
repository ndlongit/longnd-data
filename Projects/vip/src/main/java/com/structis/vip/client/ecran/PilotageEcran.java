package com.structis.vip.client.ecran;

import java.util.List;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Frame;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.service.ClientReportServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.ReportModel;

public class PilotageEcran extends AbstractTabEcran implements EcranLoadable {

    private SimpleEventBus bus = new SimpleEventBus();
    private final Messages messages = GWT.create(Messages.class);

    private ComboBox<ReportModel> cbReport;
    private ListStore<ReportModel> reports = new ListStore<ReportModel>();

    private ClientReportServiceAsync clientReportService = ClientReportServiceAsync.Util.getInstance();

    Frame frame;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        this.frame = new Frame();
        this.frame.setUrl("");
        this.frame.setWidth("100%");
        this.frame.setHeight("100%");
        this.frame.getElement().getStyle().setBorderStyle(BorderStyle.NONE);

        HorizontalPanel hpPanel;
        hpPanel = new HorizontalPanel();
        // set attributes
        hpPanel.setStyleAttribute("padding-top", "10px");
        hpPanel.setStyleAttribute("padding-left", "10px");
        hpPanel.setBorders(false);

        Label label = new Label(this.messages.reportchoose() + ":");
        label.setStyleAttribute("margin-left", "5px");

        this.reports = new ListStore<ReportModel>();

        // init commbobox report
        this.cbReport = new ComboBox<ReportModel>();
        this.cbReport.setStyleAttribute("margin-left", "5px");
        this.cbReport.setLabelSeparator("");
        this.cbReport.setStore(this.reports);
        this.cbReport.setDisplayField(ReportModel.REPORT_NAME);
        this.cbReport.setTriggerAction(TriggerAction.ALL);
        this.cbReport.setWidth(400);
        hpPanel.add(label);
        hpPanel.add(this.cbReport);

        LayoutContainer container = new LayoutContainer();
        container.setStyleAttribute("marginBottom", "10px");
        container.setLayout(new BorderLayout());

        BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 50);
        northData.setHideCollapseTool(true);
        container.add(hpPanel, northData);

        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0, 0, 0, 0));
        centerData.setSplit(false);

        container.add(this.frame, centerData);
        this.initTab(container, Action.ACTION_PILOTAGE);
        this.initData();

        this.cbReport.addSelectionChangedListener(new SelectionChangedListener<ReportModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<ReportModel> se) {
                ReportModel mdl = se.getSelectedItem();
                if ((mdl != null) && (mdl.getUrl() != null) && (!"".equals(mdl.getUrl().trim()))) {
                    PilotageEcran.this.frame.setUrl(mdl.getUrl().trim() + "&Entite_Nom="
                            + SessionServiceImpl.getInstance().getEntiteContext().getEntId() + "&Usr_id="
                            + SessionServiceImpl.getInstance().getUserContext().getId());
                } else {
                    PilotageEcran.this.frame.setUrl("");
                }
            }
        });
        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(DelegationListProjectEvent event) {
                PilotageEcran.this.disableEvents(true);
                PilotageEcran.this.frame.setUrl("");
                PilotageEcran.this.initData();
                PilotageEcran.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        this.clientReportService.getReports(new AsyncCallback<List<ReportModel>>() {

            @Override
            public void onSuccess(List<ReportModel> result) {
                PilotageEcran.this.reports.removeAll();
                PilotageEcran.this.cbReport.clear();
                for (ReportModel mdl : result) {
                    switch (mdl.getId().intValue()) {
                    case 1:
                        mdl.setName(PilotageEcran.this.messages.report1name());
                        break;
                    case 2:
                        mdl.setName(PilotageEcran.this.messages.report2name());
                        break;
                    case 3:
                        mdl.setName(PilotageEcran.this.messages.report3name());
                        break;
                    case 4:
                        mdl.setName(PilotageEcran.this.messages.report4name());
                        break;
                    case 5:
                        mdl.setName(PilotageEcran.this.messages.report5name());
                        break;
                    case 6:
                        mdl.setName(PilotageEcran.this.messages.report6name());
                        break;
                    case 7:
                        mdl.setName(PilotageEcran.this.messages.report7name());
                        break;
                    case 8:
                        mdl.setName(PilotageEcran.this.messages.report8name());
                        break;
                    }
                }
                PilotageEcran.this.reports.add(result);
            }

            @Override
            public void onFailure(Throwable caught) {
            }
        });
    }

    @Override
    public void onLoadApplication(NavigationEvent event) {
        this.resetTab();
        this.bus.fireEvent(new DelegationListProjectEvent(null, null));
    }
}
