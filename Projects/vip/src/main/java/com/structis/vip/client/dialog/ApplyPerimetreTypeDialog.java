package com.structis.vip.client.dialog;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.RefreshPerimetreTypeGridEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationModelServiceAsync;
import com.structis.vip.client.service.ClientPerimetreTypeServiceAsync;
import com.structis.vip.shared.model.DelegationMdlModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class ApplyPerimetreTypeDialog extends Window {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 550;
    private final int HEIGHT = 370;

    private SimpleEventBus bus;
    private ClientPerimetreTypeServiceAsync clientPerimetreTypeServiceAsync = ClientPerimetreTypeServiceAsync.Util.getInstance();
    private ClientDelegationModelServiceAsync clientDelegationModelServiceAsync = ClientDelegationModelServiceAsync.Util.getInstance();

    private ListStore<PerimetreTypeModel> store = new ListStore<PerimetreTypeModel>();
    private CheckBoxListView<PerimetreTypeModel> view;
    // private ControlModel control;

    private Button btnSave;
    private Button btnCancel;

    public ApplyPerimetreTypeDialog(SimpleEventBus bus) {
        this.bus = bus;
        this.initUI();
    }

    public void initUI() {
        this.setHeading("Perimetre Types");
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setResizable(false);
        this.setModal(true);
        this.setButtonAlign(HorizontalAlignment.RIGHT);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(300);

        this.view = new CheckBoxListView<PerimetreTypeModel>();
        this.view.setStore(this.store);
        this.view.setDisplayProperty(PerimetreTypeModel.PERIMETRE_TYPE_NAME);
        // view.setTemplate( createTemplate());

        this.view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.btnSave = new Button(this.messages.commonApplybutton());
        this.btnCancel = new Button(this.messages.commonAnnulerButton());

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                // if ((control != null) && (control.getId() != 0)) {

                List<PerimetreTypeModel> eccs = new ArrayList<PerimetreTypeModel>();
                for (PerimetreTypeModel mdl : ApplyPerimetreTypeDialog.this.view.getChecked()) {
                    eccs.add(mdl);
                }

                RefreshPerimetreTypeGridEvent recge = new RefreshPerimetreTypeGridEvent();
                recge.setPerimetreTypes(eccs);
                ApplyPerimetreTypeDialog.this.bus.fireEvent(recge);
                ApplyPerimetreTypeDialog.this.hide();
            }
        });

        this.btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ApplyPerimetreTypeDialog.this.hide();
            }
        });

        this.addButton(this.btnSave);
        this.addButton(this.btnCancel);

        main.add(this.view);
        this.add(main);
    }

    public void setData(EntiteModel entiteModel, final List<DelegationMdlModel> models) {

        this.clientPerimetreTypeServiceAsync.getPerimetreTypes(entiteModel.getEntId(), new AsyncCallback<List<PerimetreTypeModel>>() {

            @Override
            public void onFailure(Throwable arg0) {
            }

            @Override
            public void onSuccess(List<PerimetreTypeModel> arg0) {
                ApplyPerimetreTypeDialog.this.store.removeAll();
                ApplyPerimetreTypeDialog.this.store.add(arg0);
                for (DelegationMdlModel ecc : models) {
                    for (int i = 0; i < ApplyPerimetreTypeDialog.this.store.getCount(); i++) {
                        if (ecc.getPerimetreType() != null
                                && ApplyPerimetreTypeDialog.this.store.getAt(i).getPtyId().equals(ecc.getPerimetreType().getPtyId())) {
                            ApplyPerimetreTypeDialog.this.view.setChecked(ApplyPerimetreTypeDialog.this.store.getAt(i), true);
                        }
                    }
                }

            }
        });
    }
}
