package com.structis.vip.client.dialog;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.SynETDEEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientSyncServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.SynETDEModel;

public class SynETDEDialog extends Window {

    private Button btnValider;
    private Button btnAnuler;
    private SimpleEventBus bus;
    private EntiteModel entite;

    private final Messages messages = GWT.create(Messages.class);
    private ListStore<SynETDEModel> store = new ListStore<SynETDEModel>();
    private ClientSyncServiceAsync clientSyncService = ClientSyncServiceAsync.Util.getInstance();
    private Grid<SynETDEModel> grid;

    public SynETDEDialog(SimpleEventBus bus, EntiteModel entite) {
        this.bus = bus;
        this.entite = entite;
        this.setLayout(new FitLayout());
        this.setSize(400, 300);
        this.setHeading(this.messages.commonchoosetosyn());

        this.initUI();
        this.initData();
    }

    private void initData() {
        this.grid.mask(this.messages.commonloadingdata());
        this.clientSyncService.getRubsiCodesName(this.entite.getEntId(), this.entite.getName(), new AsyncCallback<List<SynETDEModel>>() {

            @Override
            public void onSuccess(List<SynETDEModel> arg0) {
                SynETDEDialog.this.store.add(arg0);
                SynETDEDialog.this.grid.unmask();
            }

            @Override
            public void onFailure(Throwable arg0) {
                SynETDEDialog.this.grid.unmask();
            }
        });
    }

    private void initUI() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        final CheckBoxSelectionModel<SynETDEModel> sm = new CheckBoxSelectionModel<SynETDEModel>();
        sm.setSelectionMode(SelectionMode.MULTI);
        configs.add(sm.getColumn());

        ColumnConfig column = new ColumnConfig();
        column.setId(SynETDEModel.SYNC_ETDE_CODE);
        column.setHeader(this.messages.commoncode());
        column.setWidth(100);
        configs.add(column);

        column = new ColumnConfig();
        column.setId(SynETDEModel.SYNC_ETDE_NAME);
        column.setHeader(this.messages.commonnom());
        column.setWidth(300);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        this.store = new ListStore<SynETDEModel>();

        this.grid = new Grid<SynETDEModel>(this.store, cm);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        this.grid.setSelectionModel(sm);
        this.grid.setBorders(true);
        this.grid.setColumnLines(true);
        this.grid.setLoadMask(true);
        this.grid.setStripeRows(true);
        this.grid.setColumnReordering(true);
        this.grid.addPlugin(sm);

        this.btnValider = new Button(this.messages.commonValiderButton());
        this.btnValider.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                List<SynETDEModel> seleted = SynETDEDialog.this.grid.getSelectionModel().getSelectedItems();
                if (seleted != null && seleted.isEmpty() == false) {
                    SynETDEEvent event = new SynETDEEvent();
                    event.setModels(seleted);
                    SynETDEDialog.this.bus.fireEvent(event);
                    SynETDEDialog.this.hide();
                }
            }
        });
        this.btnAnuler = new Button(this.messages.commonAnnulerButton());
        this.btnAnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                SynETDEDialog.this.hide();
            }
        });

        this.setButtonAlign(HorizontalAlignment.RIGHT);
        this.addButton(this.btnAnuler);
        this.addButton(this.btnValider);

        this.add(this.grid);
    }
}
