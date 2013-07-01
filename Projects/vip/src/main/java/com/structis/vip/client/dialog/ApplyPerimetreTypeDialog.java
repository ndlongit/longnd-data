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

    private ListStore<PerimetreTypeModel> store = new ListStore<PerimetreTypeModel>();
    private CheckBoxListView<PerimetreTypeModel> view;
    // private ControlModel control;

    private Button btnSave;
    private Button btnCancel;

    public ApplyPerimetreTypeDialog(SimpleEventBus bus) {
        this.bus = bus;
        initUI();
    }

    public void initUI() {
        setHeading("Perimetre Types");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setModal(true);
        setButtonAlign(HorizontalAlignment.RIGHT);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(300);

        view = new CheckBoxListView<PerimetreTypeModel>();
        view.setStore(store);
        view.setDisplayProperty(PerimetreTypeModel.PERIMETRE_TYPE_NAME);
        // view.setTemplate( createTemplate());

        view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        btnSave = new Button(messages.commonApplybutton());
        btnCancel = new Button(messages.commonAnnulerButton());

        btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                // if ((control != null) && (control.getId() != 0)) {

                List<PerimetreTypeModel> eccs = new ArrayList<PerimetreTypeModel>();
                for (PerimetreTypeModel mdl : view.getChecked()) {
                    eccs.add(mdl);
                }

                RefreshPerimetreTypeGridEvent recge = new RefreshPerimetreTypeGridEvent();
                recge.setPerimetreTypes(eccs);
                bus.fireEvent(recge);
                hide();
            }
        });

        btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });

        addButton(btnSave);
        addButton(btnCancel);

        main.add(view);
        add(main);
    }

    public void setData(EntiteModel entiteModel, final List<DelegationMdlModel> models) {

        clientPerimetreTypeServiceAsync.getPerimetreTypes(entiteModel.getEntId(), new AsyncCallback<List<PerimetreTypeModel>>() {

            @Override
            public void onFailure(Throwable arg0) {
            }

            @Override
            public void onSuccess(List<PerimetreTypeModel> arg0) {
                store.removeAll();
                store.add(arg0);
                for (DelegationMdlModel ecc : models) {
                    for (int i = 0; i < store.getCount(); i++) {
                        if (ecc.getPerimetreType() != null
                                && store.getAt(i).getPtyId().equals(ecc.getPerimetreType().getPtyId())) {
                            view.setChecked(store.getAt(i), true);
                        }
                    }
                }

            }
        });
    }
}
