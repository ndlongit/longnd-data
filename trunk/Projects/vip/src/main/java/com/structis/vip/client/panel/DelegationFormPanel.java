package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientEntiteServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class DelegationFormPanel extends HorizontalPanel {

    private final Messages messages = GWT.create(Messages.class);

    private ListStore<EntiteModel> entites;
    private ListStore<PerimetreModel> perimetres;

    private ClientEntiteServiceAsync clientEntiteService = ClientEntiteServiceAsync.Util.getInstance();
    private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();

    private SimpleEventBus bus;

    private Label lblEntite;
    private Label lblEntiteValue;
    private ComboBox<EntiteModel> cbEntite;
    private ComboBox<PerimetreModel> cbPerimetre;
    private Label lblPeremitre;
    private Button buttonValider;

    private UserModel currentUser;
    private boolean isSuperUser = false;

    private EntiteModel selectedEntiteModel;
    private PerimetreModel selectedPerimetreModel;

    public DelegationFormPanel(SimpleEventBus bus) {
        this.bus = bus;
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        this.currentUser = SessionServiceImpl.getInstance().getUserContext();
        if (this.currentUser != null) {
            this.isSuperUser = this.currentUser.isSuperUser();
        }

        this.initUI();

        this.addHandler();
    }

    public void initUI() {
        this.entites = new ListStore<EntiteModel>();
        this.clientEntiteService.getAllEntites(new AsyncCallback<List<EntiteModel>>() {

            @Override
            public void onSuccess(List<EntiteModel> arg0) {
                DelegationFormPanel.this.entites.add(arg0);
                DelegationFormPanel.this.cbEntite.setStore(DelegationFormPanel.this.entites);
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });

        // Entite
        this.lblEntite = new Label(this.messages.delegationentite() + " : ");

        this.lblEntiteValue = new Label("");
        this.lblEntiteValue.setStyleAttribute("margin-left", "5px");

        this.cbEntite = new ComboBox<EntiteModel>();
        this.cbEntite.setStyleAttribute("margin-left", "5px");
        this.cbEntite.setEditable(false);
        this.cbEntite.setLabelSeparator("");
        this.cbEntite.setStore(this.entites);
        this.cbEntite.setDisplayField(EntiteModel.ENTITE_NAME);
        this.cbEntite.setTriggerAction(TriggerAction.ALL);
        this.cbEntite.setVisible(false);
        this.cbEntite.setAllowBlank(false);

        this.cbEntite.addSelectionChangedListener(new SelectionChangedListener<EntiteModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<EntiteModel> se) {
                DelegationFormPanel.this.disableEvents(true);
                EntiteModel selected = se.getSelectedItem();
                DelegationFormPanel.this.perimetres.removeAll();
                DelegationFormPanel.this.cbPerimetre.clear();
                if (null != selected) {
                    DelegationFormPanel.this.refreshDataForPerimetre(selected.getEntId());
                }
                DelegationFormPanel.this.disableEvents(false);
            }
        });

        // Perimetre
        this.lblPeremitre = new Label(this.messages.commonPerimetre() + " : ");
        this.lblPeremitre.setStyleAttribute("margin-left", "60px");

        this.perimetres = new ListStore<PerimetreModel>();
        this.cbPerimetre = new ComboBox<PerimetreModel>();

        this.cbPerimetre.setFieldLabel(this.messages.commonPerimetre());
        this.cbPerimetre.setStore(this.perimetres);
        this.cbPerimetre.setDisplayField(PerimetreModel.PERIMETRE_NAME);
        this.cbPerimetre.setTriggerAction(TriggerAction.ALL);
        this.cbPerimetre.setStyleAttribute("margin-left", "5px");
        this.cbPerimetre.setEditable(false);
        this.cbPerimetre.setAllowBlank(false);
        this.cbPerimetre.setWidth(400);
        this.cbPerimetre.setSimpleTemplate("<span title=\"{" + this.cbPerimetre.getDisplayField() + "}\">{" + this.cbPerimetre.getDisplayField()
                + "}</span>");

        // buttonValider
        this.buttonValider = new Button(this.messages.commonValiderButton());
        this.buttonValider.setStyleAttribute("margin-left", "30px");

        this.add(this.lblEntite);
        this.add(this.lblEntiteValue);
        this.add(this.cbEntite);
        this.add(this.lblPeremitre);
        this.add(this.cbPerimetre);
        this.add(this.buttonValider);

        // set attributes
        this.setStyleAttribute("padding-top", "10px");
        this.setStyleAttribute("padding-left", "10px");
        this.setBorders(false);

        if (this.isSuperUser) {
            this.cbEntite.setVisible(true);
            this.lblEntiteValue.setVisible(false);
        } else {
            this.cbEntite.setVisible(false);
            this.lblEntiteValue.setVisible(true);
        }
    }

    private void addHandler() {
        // catch event listener for Valider button
        this.buttonValider.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                EntiteModel entiteModel = DelegationFormPanel.this.selectedEntiteModel;
                if (DelegationFormPanel.this.isSuperUser) {
                    entiteModel = DelegationFormPanel.this.cbEntite.getValue();
                }

                if (DelegationFormPanel.this.cbPerimetre.validate()) {
                    // fire event
                    PerimetreModel perimetreModel = DelegationFormPanel.this.cbPerimetre.getValue();
                    DelegationListProjectEvent event = new DelegationListProjectEvent(entiteModel, perimetreModel);
                    DelegationFormPanel.this.bus.fireEvent(event);
                    SessionServiceImpl.getInstance().setPerimetreContext(perimetreModel);
                }
            }
        });

        // add handler when click on Valider button
        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                DelegationFormPanel.this.disableEvents(true);
                DelegationFormPanel.this.selectedEntiteModel = event.getEntiteModel();
                DelegationFormPanel.this.selectedPerimetreModel = event.getPerimetreModel();
                DelegationFormPanel.this.lblEntiteValue.setText(event.getEntiteModel().getName());
                DelegationFormPanel.this.cbEntite.setValue(event.getEntiteModel());
                DelegationFormPanel.this.cbPerimetre.setValue(DelegationFormPanel.this.selectedPerimetreModel);
                DelegationFormPanel.this.disableEvents(false);
            }
        });
    }

    private void getStoreForPerimetreCombo(String emId) {
        this.perimetres = new ListStore<PerimetreModel>();
        this.cbPerimetre.clear();
        this.clientPerimetreService.findFirstLevelPerimetreByUserRoles(emId, false, SessionServiceImpl.getInstance().getUserContext().getUserRoles(),
                new AsyncCallback<List<PerimetreModel>>() {

                    @Override
                    public void onSuccess(List<PerimetreModel> arg0) {
                        DelegationFormPanel.this.perimetres.add(arg0);
                        DelegationFormPanel.this.cbPerimetre.setStore(DelegationFormPanel.this.perimetres);

                        PerimetreModel perimetreModel = null;

                        if (DelegationFormPanel.this.selectedPerimetreModel != null) {
                            for (PerimetreModel perMdl : DelegationFormPanel.this.perimetres.getModels()) {
                                if (perMdl.getPerId().equals(DelegationFormPanel.this.selectedPerimetreModel.getPerId())) {
                                    perimetreModel = perMdl;
                                }
                            }
                        }

                        if (perimetreModel == null) {
                            if (arg0 != null && arg0.size() > 0) {
                                PerimetreModel pm = arg0.get(0);
                                DelegationFormPanel.this.cbPerimetre.select(0);
                                DelegationFormPanel.this.cbPerimetre.setValue(pm);
                            }
                        } else {
                            DelegationFormPanel.this.cbPerimetre.setValue(perimetreModel);
                        }
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                    }
                });
    }

    protected void refreshDataForPerimetre(final String emId) {
        this.getStoreForPerimetreCombo(emId);
    }

    public void setEnableForm(boolean enabled) {
        this.cbEntite.setEnabled(enabled);
        this.cbPerimetre.setEnabled(enabled);
        this.buttonValider.setEnabled(enabled);
    }
}
