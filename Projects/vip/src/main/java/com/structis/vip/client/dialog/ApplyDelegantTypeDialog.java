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
import com.structis.vip.client.service.ClientDelegationModelServiceAsync;
import com.structis.vip.shared.model.CollaborateurTypeModel;
import com.structis.vip.shared.model.DelegationMdlModel;
import com.structis.vip.shared.model.EntiteModel;

public class ApplyDelegantTypeDialog extends Window {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 550;
    private final int HEIGHT = 370;

    private SimpleEventBus bus;
    private ClientCollaborateurTypeServiceAsync clientCollaborateurTypeServiceAsync = ClientCollaborateurTypeServiceAsync.Util.getInstance();
    private ClientDelegationModelServiceAsync clientDelegationModelServiceAsync = ClientDelegationModelServiceAsync.Util.getInstance();

    private ListStore<CollaborateurTypeModel> store = new ListStore<CollaborateurTypeModel>();
    private CheckBoxListView<CollaborateurTypeModel> view;
    // private ControlModel control;

    private Button btnSave;
    private Button btnCancel;

    public ApplyDelegantTypeDialog(SimpleEventBus bus) {
        this.bus = bus;
        this.initUI();
    }

    public void initUI() {
        this.setHeading("Delegant Types");
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setResizable(false);
        this.setModal(true);
        this.setButtonAlign(HorizontalAlignment.RIGHT);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(300);

        this.view = new CheckBoxListView<CollaborateurTypeModel>();
        this.view.setStore(this.store);
        // view.setDisplayProperty(CollaborateurTypeModel.COT_NAME);
        this.view.setTemplate(this.createTemplate());

        this.view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.btnSave = new Button(this.messages.commonApplybutton());
        this.btnCancel = new Button(this.messages.commonAnnulerButton());

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                // if ((control != null) && (control.getId() != 0)) {

                List<CollaborateurTypeModel> eccs = new ArrayList<CollaborateurTypeModel>();
                for (CollaborateurTypeModel mdl : ApplyDelegantTypeDialog.this.view.getChecked()) {
                    eccs.add(mdl);
                }

                RefreshDelegantGridEvent recge = new RefreshDelegantGridEvent();
                recge.setDelegantTypes(eccs);
                ApplyDelegantTypeDialog.this.bus.fireEvent(recge);
                ApplyDelegantTypeDialog.this.hide();
            }
        });

        this.btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ApplyDelegantTypeDialog.this.hide();
            }
        });

        this.addButton(this.btnSave);
        this.addButton(this.btnCancel);

        main.add(this.view);
        this.add(main);
    }

    private String createTemplate() {
        String spacing = GXT.isIE && !GXT.isStrict ? "0" : "3";
        String template = "<tpl for=\".\"><div class='x-view-item x-view-item-check'><table cellspacing='"
                + spacing
                + "' cellpadding=0><tr><td><input class=\"x-view-item-checkbox\" type=\"checkbox\" /></td><td><td>{name} | {description}</td></tr></table></div></tpl>";
        return template;
    }

    public void setData(EntiteModel entiteModel, final List<DelegationMdlModel> models) {

        this.clientCollaborateurTypeServiceAsync.getCollaborateurTypeByEntite(entiteModel.getEntId(),
                new AsyncCallback<List<CollaborateurTypeModel>>() {

                    @Override
                    public void onFailure(Throwable arg0) {
                    }

                    @Override
                    public void onSuccess(List<CollaborateurTypeModel> arg0) {
                        ApplyDelegantTypeDialog.this.store.removeAll();
                        ApplyDelegantTypeDialog.this.store.add(arg0);
                        for (DelegationMdlModel ecc : models) {
                            for (int i = 0; i < ApplyDelegantTypeDialog.this.store.getCount(); i++) {
                                if (ApplyDelegantTypeDialog.this.store.getAt(i).getId().equals(ecc.getCollaborateurType().getId())) {
                                    ApplyDelegantTypeDialog.this.view.setChecked(ApplyDelegantTypeDialog.this.store.getAt(i), true);
                                }
                            }
                        }

                    }
                });
    }
}
