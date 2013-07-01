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
import com.structis.vip.client.event.AdministrationTreeEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.service.ClientPerimetreTypeServiceAsync;
import com.structis.vip.shared.SharedConstant;
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
        isChanged = false;
        initUI();
    }

    public void initData(final String parentId) {
        this.parentId = parentId;
        final Window thisWin = this;
        clientPerimetreTypeServiceAsync.getPerimetreTypes(SharedConstant.ENTITE_ID_ETDE, new AsyncCallback<List<PerimetreTypeModel>>() {

            @Override
            public void onSuccess(List<PerimetreTypeModel> arg0) {
                if (parentId != null) {
                    Info.display(messages.commoninfo(), messages.perimetretypechoosesyncstart());
                    perimetreGrid.mask(messages.perimetretypechoosesyncprogress());
                    btnCancel.setEnabled(false);
                    clientPerimetreServiceAsync.sync(SharedConstant.ENTITE_ID_ETDE, parentId, arg0,
                            new AsyncCallback<Map<String, List<PerimetreModel>>>() {

                                @Override
                                public void onSuccess(Map<String, List<PerimetreModel>> arg0) {
                                    perimetres.removeAll();
                                    perimetres.add(arg0.get(SharedConstant.SUCCESS_LIST));
                                    Info.display(messages.commoninfo(),
                                            messages.perimetretypechoosesyncsuccess() + " " + arg0.get(SharedConstant.SUCCESS_LIST).size() + " "
                                                    + messages.perimetretypechoosesyncinfo());
                                    List<PerimetreModel> errorList = arg0.get(SharedConstant.ERROR_LIST);

                                    displayErrorSyncList(errorList);

                                    perimetreGrid.unmask();
                                    btnCancel.setEnabled(true);
                                    if (arg0.size() != 0) {
                                        isChanged = true;
                                    }
                                }

                                private void displayErrorSyncList(List<PerimetreModel> errorList) {
                                    if (errorList != null && errorList.size() > 0) {
                                        errorMessage.setValue(messages.perimetresyncerror(getErrorNames(errorList)));
                                        errorMessagePanel.setVisible(true);
                                        thisWin.setHeight(thisWin.getHeight() + 150);

                                    } else {
                                        errorMessagePanel.setVisible(false);
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
                                    Info.display(messages.commoninfo(), messages.perimetretypechoosesyncfailed());
                                    perimetreGrid.unmask();
                                    btnCancel.setEnabled(true);
                                }
                            });
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
                Info.display(messages.commoninfo(), messages.perimetretypechoosesyncfailed());
                hide();
            }
        });
    }

    private native void copyToClipboard(String message) /*-{
		$wnd.clipboardData.setData("Text", message);
    }-*/;

    private void initUI() {
        setHeading(messages.perimetretypechooseheading());
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setClosable(false);
        setModal(true);
        setButtonAlign(HorizontalAlignment.RIGHT);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(250);

        btnCancel = new Button(messages.commonValiderButton());

        btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });

        addButton(btnCancel);

        // setup grid for document view
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        // Column documents
        ColumnConfig column = new ColumnConfig();
        column.setId(PerimetreModel.PERIMETRE_NAME);
        column.setHeader(messages.perimetretypechooseperimetre());
        column.setRowHeader(true);
        column.setSortable(false);
        configs.add(column);

        // setup column model
        ColumnModel cm = new ColumnModel(configs);

        perimetreGrid = new Grid<PerimetreModel>(perimetres, cm);
        perimetreGrid.setStyleAttribute("borderTop", "none");
        perimetreGrid.setAutoExpandColumn(PerimetreModel.PERIMETRE_NAME);
        perimetreGrid.setBorders(false);
        perimetreGrid.setStripeRows(true);
        perimetreGrid.setColumnLines(true);
        perimetreGrid.setColumnReordering(true);

        main.add(perimetreGrid);

        add(main);
        errorMessage = new TextArea();
        errorMessage.setReadOnly(true);
        errorMessage.setWidth("100%");
        errorMessage.setHeight("120");
        btnCopier = new Button("Copier");
        btnCopier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                copyToClipboard(errorMessage.getValue());
            }
        });
        errorMessagePanel = new ContentPanel();
        errorMessagePanel.setAutoWidth(true);
        errorMessagePanel.setHeight("150");
        errorMessagePanel.setLayout(new FitLayout());
        errorMessagePanel.setHeaderVisible(false);
        errorMessagePanel.add(errorMessage);
        errorMessagePanel.setButtonAlign(HorizontalAlignment.CENTER);
        errorMessagePanel.addButton(btnCopier);
        add(errorMessagePanel);

        errorMessagePanel.setVisible(false);
        addWindowListener(new WindowListener() {

            @Override
            public void windowHide(WindowEvent we) {
                if (isChanged) {
                    AdministrationTreeEvent event = new AdministrationTreeEvent();
                    event.setParentId(parentId);
                    bus.fireEvent(event);
                }
            }
        });
    }
}
