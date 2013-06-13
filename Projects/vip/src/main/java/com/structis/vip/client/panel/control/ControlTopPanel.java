package com.structis.vip.client.panel.control;

import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
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
import com.structis.vip.client.event.control.RefreshTreeEvent;
import com.structis.vip.client.event.control.RefreshTreeHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class ControlTopPanel extends HorizontalPanel {

    private final Messages messages = GWT.create(Messages.class);

    private ListStore<PerimetreModel> perimetres;

    private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();

    private SimpleEventBus bus;

    private Label cbEntite;
    private ComboBox<PerimetreModel> cbPerimetre;
    private Label lblPeremitre;
    private Button buttonValider;

    private UserModel currentUser;
    boolean isSuperUser = false;

    private EntiteModel selectedEntiteModel;
    private PerimetreModel selectedPerimetreModel;

    public ControlTopPanel(SimpleEventBus bus) {
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

        this.cbEntite = new Label();
        this.cbEntite.setText(messages.delegationentite());

        // Perimetre
        this.lblPeremitre = new Label(messages.commonPerimetre() + " : ");
        this.lblPeremitre.setStyleAttribute("margin-left", "60px");

        this.perimetres = new ListStore<PerimetreModel>();
        this.cbPerimetre = new ComboBox<PerimetreModel>();
        this.cbPerimetre.setFieldLabel(messages.commonPerimetre());
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
        this.buttonValider = new Button(messages.commonValiderButton());
        this.buttonValider.setStyleAttribute("margin-left", "30px");

        // add(lblEntite);
        // add(lblEntiteValue);
        this.add(this.cbEntite);
        this.add(this.lblPeremitre);
        this.add(this.cbPerimetre);
        this.add(this.buttonValider);

        // set attributes
        this.setStyleAttribute("padding-top", "10px");
        this.setStyleAttribute("padding-left", "10px");
        this.setBorders(false);
    }

    private void addHandler() {
        // catch event listener for Valider button
        this.buttonValider.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                EntiteModel entiteModel =selectedEntiteModel;
                // if (isSuperUser) {
                // entiteModel = cbEntite.getValue();
                // }

                if (ControlTopPanel.this.cbPerimetre.validate()) {
                    // fire event
                    PerimetreModel perimetreModel =cbPerimetre.getValue();
                    RefreshTreeEvent event = new RefreshTreeEvent(entiteModel, perimetreModel);
                    // DelegationListProjectEvent event = new DelegationListProjectEvent(entiteModel, perimetreModel);
                   bus.fireEvent(event);
                    SessionServiceImpl.getInstance().setPerimetreContext(perimetreModel);
                    // bus.fireEvent(controlFilterEvent);
                }
            }
        });

        // add handler when click on Valider button
        this.bus.addHandler(RefreshTreeEvent.getType(), new RefreshTreeHandler() {

            @Override
            public void onLoadAction(RefreshTreeEvent event) {
               disableEvents(true);
               selectedEntiteModel = event.getEntiteModel();
               selectedPerimetreModel = event.getPerimetreModel();
                // Window.alert(selectedEntiteModel.getName());
                // lblEntiteValue.setText(event.getEntiteModel().getName());
               cbEntite.setText(messages.delegationentite() + " : "
                        +selectedEntiteModel.getName());
               disableEvents(false);
            }
        });
    }

    private void getStoreForPerimetreCombo(String emId) {
        this.perimetres = new ListStore<PerimetreModel>();
        this.cbPerimetre.clear();
        this.clientPerimetreService.findFirstLevelPerimetreByUserRoles(emId, false, SessionServiceImpl.getInstance().getUserContext().getUserRoles(),
                new AsyncCallbackWithErrorResolution<List<PerimetreModel>>() {

                    @Override
                    public void onSuccess(List<PerimetreModel> arg0) {
                       perimetres.add(arg0);
                       cbPerimetre.setStore(ControlTopPanel.this.perimetres);

                        PerimetreModel perimetreModel = null;

                        if (ControlTopPanel.this.selectedPerimetreModel != null) {
                            for (PerimetreModel perMdl :perimetres.getModels()) {
                                if (perMdl.getPerId().equals(ControlTopPanel.this.selectedPerimetreModel.getPerId())) {
                                    perimetreModel = perMdl;
                                }
                            }
                        }

                        if (perimetreModel == null) {
                            if (arg0 != null && arg0.size() > 0) {
                                PerimetreModel pm = arg0.get(0);
                               cbPerimetre.select(0);
                               cbPerimetre.setValue(pm);
                            }
                        } else {
                           cbPerimetre.setValue(perimetreModel);
                        }
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

    public void onLoadPanel() {
        this.disableEvents(true);
        this.selectedEntiteModel = SessionServiceImpl.getInstance().getEntiteContext();
        this.selectedPerimetreModel = SessionServiceImpl.getInstance().getPerimetreContext();
        this.cbEntite.setText(messages.delegationentite() + " : " + this.selectedEntiteModel.getName());
        this.perimetres.removeAll();
        this.cbPerimetre.clear();
        if (null != this.selectedEntiteModel) {
            this.refreshDataForPerimetre(this.selectedEntiteModel.getEntId());
        }
        this.disableEvents(false);
    }
}
