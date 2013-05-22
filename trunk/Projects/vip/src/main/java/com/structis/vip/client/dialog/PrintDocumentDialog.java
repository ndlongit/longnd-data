package com.structis.vip.client.dialog;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationDocumentServiceAsync;
import com.structis.vip.client.service.ClientDelegationModelServiceAsync;
import com.structis.vip.client.service.ClientDemDomServiceAsync;
import com.structis.vip.client.service.ClientDocumentMdlServiceAsync;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.shared.model.DelegationDocumentModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DemDomModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DomDelModel;
import com.structis.vip.shared.model.LanguageModel;

public class PrintDocumentDialog extends Window {

    private final Messages messages = GWT.create(Messages.class);

    private ClientDelegationModelServiceAsync clientDelegationModelServiceAsync = ClientDelegationModelServiceAsync.Util.getInstance();
    private ClientDocumentMdlServiceAsync clientDocumentMdlService = ClientDocumentMdlServiceAsync.Util.getInstance();
    private ClientDemDomServiceAsync clientDemDomServiceAsync = ClientDemDomServiceAsync.Util.getInstance();
    private ClientDelegationDocumentServiceAsync clientDelegationDocumentServiceAsync = ClientDelegationDocumentServiceAsync.Util.getInstance();

    private ListStore<DocumentMdlModel> documentMdlModels;
    private Grid<DocumentMdlModel> documentGrid;
    private ContentPanel cpDocumentGrid;
    private ColumnModel columnModelDocument;

    private ListStore<DelegationDocumentModel> delegationDocumentModels;
    private Grid<DelegationDocumentModel> delegationDocumentGrid;
    private ContentPanel cpDelegationDocumentGrid;
    private ColumnModel columnModelOther;

    private Button btnClose;

    private DelegationModel delegationModel;

    public PrintDocumentDialog(DelegationModel delegationModel) {
        this.delegationModel = delegationModel;
        this.initUI();
        this.initData();
    }

    private void initUI() {
        this.setHeading(this.messages.delegationcomboboxaction4());
        this.setSize(695, -1);
        this.setResizable(false);
        this.setModal(true);

        this.add(this.createDocumentView());
        if (ConstantClient.DELEGATION_STATUS_IS_P != this.delegationModel.getDelegationStatus().getId().intValue()) {
            this.add(this.createOtherDocumentView());
            this.columnModelDocument.setHidden(0, false);
            this.columnModelDocument.setHidden(1, false);
        } else {
            this.columnModelDocument.setHidden(0, false);
            this.columnModelDocument.setHidden(1, true);
        }

        this.btnClose = new Button();
        this.btnClose.setText(this.messages.commonClosebutton());
        this.btnClose.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                PrintDocumentDialog.this.hide();
            }
        });

        this.setButtonAlign(HorizontalAlignment.RIGHT);
        this.addButton(this.btnClose);
    }

    /**
     * create document view
     */
    private ContentPanel createDocumentView() {
        // setup grid for document view
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        GridCellRenderer<DocumentMdlModel> documentRender = new GridCellRenderer<DocumentMdlModel>() {

            @Override
            public Object render(final DocumentMdlModel model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex,
                    int colIndex, ListStore<DocumentMdlModel> store, Grid<DocumentMdlModel> grid) {
                final com.google.gwt.user.client.ui.Label label = new com.google.gwt.user.client.ui.Label();
                label.setStyleName("x-link-item");
                int delegationType = PrintDocumentDialog.this.delegationModel.getDelegationType().getId();
                if (delegationType == ConstantClient.DELEGATION_TYPE_IS_TEMPORAIRE) {
                    label.setText(model.getName() + " " + PrintDocumentDialog.this.messages.commontemporary());
                    label.setTitle(model.getName() + " " + PrintDocumentDialog.this.messages.commontemporary());
                } else {
                    label.setText(model.getName());
                    label.setTitle(model.getName());
                }

                label.addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent arg0) {
                        String reportUrl = GWT.getHostPageBaseURL() + ".printDocumentServiceServlet";
                        List<NameValuePair> values = new ArrayList<NameValuePair>();
                        values.add(new NameValuePair("domId", model.getId().toString()));
                        values.add(new NameValuePair("delId", PrintDocumentDialog.this.delegationModel.getId().toString()));
                        values.add(new NameValuePair("isPrint", "true"));
                        ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
                    }
                });

                return label;
            }
        };

        // Column documents
        ColumnConfig name = new ColumnConfig();
        name.setId("documentMdl.name");
        name.setHeader(this.messages.delegationformlesdocuments());
        name.setWidth(340);
        name.setRowHeader(true);
        name.setRenderer(documentRender);
        name.setSortable(false);

        ColumnConfig signed = new ColumnConfig("signed", this.messages.delegationformdocumentsigned(), 315);

        GridCellRenderer<DocumentMdlModel> signedRender = new GridCellRenderer<DocumentMdlModel>() {

            @Override
            public Object render(final DocumentMdlModel model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex,
                    int colIndex, ListStore<DocumentMdlModel> store, Grid<DocumentMdlModel> grid) {
                final com.google.gwt.user.client.ui.Label lbl = new com.google.gwt.user.client.ui.Label();
                lbl.setStyleName("x-link-item");

                PrintDocumentDialog.this.clientDocumentMdlService.getDocumentsByDelegation(PrintDocumentDialog.this.delegationModel.getId(),
                        new AsyncCallback<List<DomDelModel>>() {

                            @Override
                            public void onSuccess(List<DomDelModel> arg0) {
                                if (arg0.size() == 0) {
                                } else {
                                    for (DomDelModel domDelModel : arg0) {
                                        if (domDelModel.getDocumentMdl().getId() == model.getId()) {
                                            lbl.setText(domDelModel.getSignedFilename());
                                            lbl.setTitle(domDelModel.getSignedFilename());
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Throwable arg0) {
                            }
                        });

                lbl.addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent arg0) {
                        String reportUrl = GWT.getHostPageBaseURL() + ".printSignedDocumentServiceServlet";
                        List<NameValuePair> values = new ArrayList<NameValuePair>();
                        values.add(new NameValuePair("fileName", lbl.getText()));
                        values.add(new NameValuePair("delId", PrintDocumentDialog.this.delegationModel.getId().toString()));
                        values.add(new NameValuePair("isPrint", "true"));
                        ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
                    }
                });

                return lbl;
            }
        };
        signed.setRenderer(signedRender);

        configs.add(name);
        configs.add(signed);

        // setup column model
        this.columnModelDocument = new ColumnModel(configs);

        // Content panel with header
        this.cpDocumentGrid = new ContentPanel();
        this.cpDocumentGrid.setBodyBorder(true);
        this.cpDocumentGrid.setHeaderVisible(false);
        this.cpDocumentGrid.setButtonAlign(HorizontalAlignment.CENTER);
        this.cpDocumentGrid.setLayout(new FitLayout());
        this.cpDocumentGrid.setSize(680, 180);
        this.cpDocumentGrid.setButtonAlign(HorizontalAlignment.RIGHT);
        this.cpDocumentGrid.setStyleAttribute("padding", "10px");

        // Grid
        this.documentMdlModels = new ListStore<DocumentMdlModel>();

        this.documentGrid = new Grid<DocumentMdlModel>(this.documentMdlModels, this.columnModelDocument);
        this.documentGrid.setStyleAttribute("borderTop", "none");
        this.documentGrid.setHeight(100);
        this.documentGrid.setBorders(false);
        this.documentGrid.setStripeRows(true);
        this.documentGrid.setColumnLines(true);
        this.documentGrid.setColumnReordering(true);

        this.documentGrid.getAriaSupport().setLabelledBy(this.cpDocumentGrid.getHeader().getId() + "-label");
        this.cpDocumentGrid.add(this.documentGrid);

        return this.cpDocumentGrid;
    }

    /**
     * create other document view
     */
    private ContentPanel createOtherDocumentView() {
        // setup grid for document view
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        // Column documents
        ColumnConfig ccDescription = new ColumnConfig();
        ccDescription.setId(DelegationDocumentModel.ODD_DESCRIPTION);
        ccDescription.setHeader(this.messages.delegationdocumentdescription());
        ccDescription.setWidth(340);
        ccDescription.setRowHeader(true);
        ccDescription.setSortable(false);

        GridCellRenderer<DelegationDocumentModel> fileNameRender = new GridCellRenderer<DelegationDocumentModel>() {

            @Override
            public Object render(final DelegationDocumentModel model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config,
                    int rowIndex, int colIndex, ListStore<DelegationDocumentModel> store, Grid<DelegationDocumentModel> grid) {
                final com.google.gwt.user.client.ui.Label label = new com.google.gwt.user.client.ui.Label();
                label.setStyleName("x-link-item");
                label.setText(model.getFileName());
                label.setTitle(model.getFileName());

                label.addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent arg0) {
                        String reportUrl = GWT.getHostPageBaseURL() + ".printDelegationDocumentServiceServlet";
                        List<NameValuePair> values = new ArrayList<NameValuePair>();
                        values.add(new NameValuePair("fileName", model.getFileName()));
                        values.add(new NameValuePair("isPrint", "true"));
                        ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
                    }
                });

                return label;
            }
        };

        // Column documents
        ColumnConfig ccFileName = new ColumnConfig();
        ccFileName.setId(DelegationDocumentModel.ODD_FILENAME);
        ccFileName.setHeader(this.messages.delegationdocumentfile());
        ccFileName.setWidth(315);
        ccFileName.setRowHeader(true);
        ccFileName.setRenderer(fileNameRender);
        ccFileName.setSortable(false);

        configs.add(ccDescription);
        configs.add(ccFileName);

        // setup column model
        this.columnModelOther = new ColumnModel(configs);

        // Content panel with header
        this.cpDelegationDocumentGrid = new ContentPanel();
        this.cpDelegationDocumentGrid.setBodyBorder(true);
        this.cpDelegationDocumentGrid.setHeaderVisible(false);
        this.cpDelegationDocumentGrid.setButtonAlign(HorizontalAlignment.CENTER);
        this.cpDelegationDocumentGrid.setLayout(new FitLayout());
        this.cpDelegationDocumentGrid.setSize(680, 180);
        this.cpDelegationDocumentGrid.setButtonAlign(HorizontalAlignment.RIGHT);
        this.cpDelegationDocumentGrid.setStyleAttribute("padding", "10px");

        // Grid
        this.delegationDocumentModels = new ListStore<DelegationDocumentModel>();

        this.delegationDocumentGrid = new Grid<DelegationDocumentModel>(this.delegationDocumentModels, this.columnModelOther);
        this.delegationDocumentGrid.setStyleAttribute("borderTop", "none");
        this.delegationDocumentGrid.setHeight(100);
        this.delegationDocumentGrid.setBorders(false);
        this.delegationDocumentGrid.setStripeRows(true);
        this.delegationDocumentGrid.setColumnLines(true);
        this.delegationDocumentGrid.setColumnReordering(true);

        this.delegationDocumentGrid.getAriaSupport().setLabelledBy(this.cpDelegationDocumentGrid.getHeader().getId() + "-label");
        this.cpDelegationDocumentGrid.add(this.delegationDocumentGrid);

        return this.cpDelegationDocumentGrid;
    }

    private void initData() {
        // TODO Hard code apply Language
        LanguageModel lang = new LanguageModel();
        lang.setId(1);
        this.clientDelegationModelServiceAsync.getGroup(lang, this.delegationModel.getPerimeter().getType(),
                this.delegationModel.getDelegationNature(), null, new AsyncCallback<Integer>() {

                    @Override
                    public void onSuccess(Integer arg0) {
                        if (arg0 != 0) {
                            PrintDocumentDialog.this.changeDocumentTable(arg0);
                        }
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                    }
                });
        if (ConstantClient.DELEGATION_STATUS_IS_P != this.delegationModel.getDelegationStatus().getId().intValue()) {
            this.changeOtherDocumentTable(this.delegationModel.getId());
        }
    }

    private void changeDocumentTable(Integer group) {
        this.clientDemDomServiceAsync.getAllDemDomsByDemGroup(group, new AsyncCallback<List<DemDomModel>>() {

            @Override
            public void onSuccess(List<DemDomModel> arg0) {
                PrintDocumentDialog.this.documentMdlModels.removeAll();
                for (DemDomModel demDom : arg0) {
                    PrintDocumentDialog.this.documentMdlModels.add(demDom.getDocumentMdl());
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
    }

    private void changeOtherDocumentTable(Integer delegationId) {
        this.clientDelegationDocumentServiceAsync.getDelegationDocuments(delegationId, new AsyncCallback<List<DelegationDocumentModel>>() {

            @Override
            public void onSuccess(List<DelegationDocumentModel> result) {
                PrintDocumentDialog.this.delegationDocumentModels.removeAll();
                PrintDocumentDialog.this.delegationDocumentModels.add(result);
            }

            @Override
            public void onFailure(Throwable caught) {
            }
        });
    }
}
