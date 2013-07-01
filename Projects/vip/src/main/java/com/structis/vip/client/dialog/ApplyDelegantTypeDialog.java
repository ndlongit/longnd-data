package com.structis.vip.client.dialog;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.GXT;
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
import com.structis.vip.client.event.RefreshDelegantGridEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurTypeServiceAsync;
import com.structis.vip.shared.model.CollaborateurTypeModel;
import com.structis.vip.shared.model.DelegationMdlModel;
import com.structis.vip.shared.model.EntiteModel;

public class ApplyDelegantTypeDialog extends Window {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 550;
    private final int HEIGHT = 370;

    private SimpleEventBus bus;
    private ClientCollaborateurTypeServiceAsync clientCollaborateurTypeServiceAsync = ClientCollaborateurTypeServiceAsync.Util.getInstance();

    private ListStore<CollaborateurTypeModel> store = new ListStore<CollaborateurTypeModel>();
    private CheckBoxListView<CollaborateurTypeModel> view;
    // private ControlModel control;

    private Button btnSave;
    private Button btnCancel;

    public ApplyDelegantTypeDialog(SimpleEventBus bus) {
        this.bus = bus;
        initUI();
    }

    public void initUI() {
        setHeading("Delegant Types");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setModal(true);
        setButtonAlign(HorizontalAlignment.RIGHT);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(300);

        view = new CheckBoxListView<CollaborateurTypeModel>();
        view.setStore(store);
        // view.setDisplayProperty(CollaborateurTypeModel.COT_NAME);
        view.setTemplate(createTemplate());

        view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        btnSave = new Button(messages.commonApplybutton());
        btnCancel = new Button(messages.commonAnnulerButton());

        btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                // if ((control != null) && (control.getId() != 0)) {

                List<CollaborateurTypeModel> eccs = new ArrayList<CollaborateurTypeModel>();
                for (CollaborateurTypeModel mdl : view.getChecked()) {
                    eccs.add(mdl);
                }

                RefreshDelegantGridEvent recge = new RefreshDelegantGridEvent();
                recge.setDelegantTypes(eccs);
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

    private String createTemplate() {
        String spacing = GXT.isIE && !GXT.isStrict ? "0" : "3";
        String template = "<tpl for=\".\"><div class='x-view-item x-view-item-check'><table cellspacing='"
                + spacing
                + "' cellpadding=0><tr><td><input class=\"x-view-item-checkbox\" type=\"checkbox\" /></td><td><td>{name} | {description}</td></tr></table></div></tpl>";
        return template;
    }

    public void setData(EntiteModel entiteModel, final List<DelegationMdlModel> models) {

        clientCollaborateurTypeServiceAsync.getCollaborateurTypeByEntite(entiteModel.getEntId(),
                new AsyncCallback<List<CollaborateurTypeModel>>() {

                    @Override
                    public void onFailure(Throwable arg0) {
                    }

                    @Override
                    public void onSuccess(List<CollaborateurTypeModel> arg0) {
                        store.removeAll();
                        store.add(arg0);
                        for (DelegationMdlModel ecc : models) {
                            for (int i = 0; i < store.getCount(); i++) {
                                if (store.getAt(i).getId().equals(ecc.getCollaborateurType().getId())) {
                                    view.setChecked(store.getAt(i), true);
                                }
                            }
                        }

                    }
                });
    }
}
