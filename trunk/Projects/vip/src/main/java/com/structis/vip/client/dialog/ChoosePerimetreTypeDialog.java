package com.structis.vip.client.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextArea;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.AdministrationTreeEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.service.ClientPerimetreTypeServiceAsync;
import com.structis.vip.shared.Constants;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class ChoosePerimetreTypeDialog extends Window {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 550;
    private final int HEIGHT = 320;

    private SimpleEventBus bus;
    private ClientPerimetreTypeServiceAsync clientPerimetreTypeServiceAsync = ClientPerimetreTypeServiceAsync.Util.getInstance();
    private ClientPerimetreServiceAsync clientPerimetreServiceAsync = ClientPerimetreServiceAsync.Util.getInstance();

    private ListStore<PerimetreModel> perimetres = new ListStore<PerimetreModel>();
    private Grid<PerimetreModel> perimetreGrid;
    private TextArea errorMessage;
    private Button btnCancel;
    private Boolean isChanged;
    private String parentId;
    private ContentPanel errorMessagePanel;
    private Button btnCopier;

    public ChoosePerimetreTypeDialog(SimpleEventBus bus) {
        this.bus = bus;
        this.isChanged = false;
        this.initUI();
    }

    public void initData(final String parentId) {
        this.parentId = parentId;
        final Window thisWin = this;
        this.clientPerimetreTypeServiceAsync.getPerimetreTypes(ConstantClient.ENTITE_ID_IS_ETDE, new AsyncCallback<List<PerimetreTypeModel>>() {

            @Override
            public void onSuccess(List<PerimetreTypeModel> arg0) {
                if (parentId != null) {
                    Info.display(ChoosePerimetreTypeDialog.this.messages.commoninfo(),
                            ChoosePerimetreTypeDialog.this.messages.perimetretypechoosesyncstart());
                    ChoosePerimetreTypeDialog.this.perimetreGrid.mask(ChoosePerimetreTypeDialog.this.messages.perimetretypechoosesyncprogress());
                    ChoosePerimetreTypeDialog.this.btnCancel.setEnabled(false);
                    ChoosePerimetreTypeDialog.this.clientPerimetreServiceAsync.sync(ConstantClient.ENTITE_ID_IS_ETDE, parentId, arg0,
                            new AsyncCallback<Map<String, List<PerimetreModel>>>() {

                                @Override
                                public void onSuccess(Map<String, List<PerimetreModel>> arg0) {
                                    ChoosePerimetreTypeDialog.this.perimetres.removeAll();
                                    ChoosePerimetreTypeDialog.this.perimetres.add(arg0.get(Constants.SUCCESS_LIST));
                                    Info.display(
                                            ChoosePerimetreTypeDialog.this.messages.commoninfo(),
                                            ChoosePerimetreTypeDialog.this.messages.perimetretypechoosesyncsuccess() + " "
                                                    + arg0.get(Constants.SUCCESS_LIST).size() + " "
                                                    + ChoosePerimetreTypeDialog.this.messages.perimetretypechoosesyncinfo());
                                    List<PerimetreModel> errorList = arg0.get(Constants.ERROR_LIST);

                                    this.displayErrorSyncList(errorList);

                                    ChoosePerimetreTypeDialog.this.perimetreGrid.unmask();
                                    ChoosePerimetreTypeDialog.this.btnCancel.setEnabled(true);
                                    if (arg0.size() != 0) {
                                        ChoosePerimetreTypeDialog.this.isChanged = true;
                                    }
                                }

                                private void displayErrorSyncList(List<PerimetreModel> errorList) {
                                    if (errorList != null && errorList.size() > 0) {
                                        ChoosePerimetreTypeDialog.this.errorMessage.setValue(ChoosePerimetreTypeDialog.this.messages
                                                .perimetresyncerror(this.getErrorNames(errorList)));
                                        ChoosePerimetreTypeDialog.this.errorMessagePanel.setVisible(true);
                                        thisWin.setHeight(thisWin.getHeight() + 150);

                                    } else {
                                        ChoosePerimetreTypeDialog.this.errorMessagePanel.setVisible(false);
                                    }
                                }

                                private String getErrorNames(List<PerimetreModel> errorList) {
                                    StringBuffer ret = new StringBuffer("");
                                    for (PerimetreModel p : errorList) {
                                        ret.append("\n").append(p.getName());
                                    }
                                    return ret.toString();
                                }

                                @Override
                                public void onFailure(Throwable arg0) {
                                    Info.display(ChoosePerimetreTypeDialog.this.messages.commoninfo(),
                                            ChoosePerimetreTypeDialog.this.messages.perimetretypechoosesyncfailed());
                                    ChoosePerimetreTypeDialog.this.perimetreGrid.unmask();
                                    ChoosePerimetreTypeDialog.this.btnCancel.setEnabled(true);
                                }
                            });
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
                Info.display(ChoosePerimetreTypeDialog.this.messages.commoninfo(),
                        ChoosePerimetreTypeDialog.this.messages.perimetretypechoosesyncfailed());
                ChoosePerimetreTypeDialog.this.hide();
            }
        });
    }

    private native void copyToClipboard(String message) /*-{
                                                        $wnd.clipboardData.setData("Text", message);
                                                        }-*/;

    private void initUI() {
        this.setHeading(this.messages.perimetretypechooseheading());
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setResizable(false);
        this.setClosable(false);
        this.setModal(true);
        this.setButtonAlign(HorizontalAlignment.RIGHT);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(250);

        this.btnCancel = new Button(this.messages.commonValiderButton());

        this.btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ChoosePerimetreTypeDialog.this.hide();
            }
        });

        this.addButton(this.btnCancel);

        // setup grid for document view
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        // Column documents
        ColumnConfig column = new ColumnConfig();
        column.setId(PerimetreModel.PERIMETRE_NAME);
        column.setHeader(this.messages.perimetretypechooseperimetre());
        column.setRowHeader(true);
        column.setSortable(false);
        configs.add(column);

        // setup column model
        ColumnModel cm = new ColumnModel(configs);

        this.perimetreGrid = new Grid<PerimetreModel>(this.perimetres, cm);
        this.perimetreGrid.setStyleAttribute("borderTop", "none");
        this.perimetreGrid.setAutoExpandColumn(PerimetreModel.PERIMETRE_NAME);
        this.perimetreGrid.setBorders(false);
        this.perimetreGrid.setStripeRows(true);
        this.perimetreGrid.setColumnLines(true);
        this.perimetreGrid.setColumnReordering(true);

        main.add(this.perimetreGrid);

        this.add(main);
        this.errorMessage = new TextArea();
        this.errorMessage.setReadOnly(true);
        this.errorMessage.setWidth("100%");
        this.errorMessage.setHeight("120");
        this.btnCopier = new Button("Copier");
        this.btnCopier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ChoosePerimetreTypeDialog.this.copyToClipboard(ChoosePerimetreTypeDialog.this.errorMessage.getValue());
            }
        });
        this.errorMessagePanel = new ContentPanel();
        this.errorMessagePanel.setAutoWidth(true);
        this.errorMessagePanel.setHeight("150");
        this.errorMessagePanel.setLayout(new FitLayout());
        this.errorMessagePanel.setHeaderVisible(false);
        this.errorMessagePanel.add(this.errorMessage);
        this.errorMessagePanel.setButtonAlign(HorizontalAlignment.CENTER);
        this.errorMessagePanel.addButton(this.btnCopier);
        this.add(this.errorMessagePanel);

        this.errorMessagePanel.setVisible(false);
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowHide(WindowEvent we) {
                if (ChoosePerimetreTypeDialog.this.isChanged) {
                    AdministrationTreeEvent event = new AdministrationTreeEvent();
                    event.setParentId(ChoosePerimetreTypeDialog.this.parentId);
                    ChoosePerimetreTypeDialog.this.bus.fireEvent(event);
                }
            }
        });
    }
}
