package com.structis.vip.client.ecran;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.LoadActionEvent;
import com.structis.vip.client.event.LoadActionHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.service.ClientEntiteServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class ChooseEntityEcran extends AbstractTabEcran implements EcranLoadable {

    private SimpleEventBus bus = new SimpleEventBus();
    private final Messages messages = GWT.create(Messages.class);

    private ComboBox<EntiteModel> cbEntity;
    private LabelField txtEntity;
    private ListStore<EntiteModel> entites = new ListStore<EntiteModel>();

    private ComboBox<PerimetreModel> cbPerimetre;
    private ListStore<PerimetreModel> perimetres = new ListStore<PerimetreModel>();
    private FormPanel frPanel;

    private Button btnValidate;
    private UserModel currentUser;
    private EntiteModel currentEntite;
    boolean isSuperUser = false;

    private ClientEntiteServiceAsync clientEntiteService = ClientEntiteServiceAsync.Util.getInstance();
    private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();
    private ClientUserServiceAsync clientUserService = ClientUserServiceAsync.Util.getInstance();

    public ChooseEntityEcran() {
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        LayoutContainer container = new LayoutContainer();
        container.setLayout(new CenterLayout());

        this.frPanel = new FormPanel();
        this.frPanel.setFrame(true);
        this.frPanel.setWidth(340);
        this.frPanel.setButtonAlign(HorizontalAlignment.CENTER);
        this.frPanel.setHeading(this.messages.chooseentityheader());

        // init commbobox entite
        this.cbEntity = new ComboBox<EntiteModel>();
        this.cbEntity.setFieldLabel(this.messages.delegationentite());
        this.cbEntity.setLabelSeparator("");
        this.cbEntity.setStore(this.entites);
        this.cbEntity.setDisplayField(EntiteModel.ENTITE_NAME);
        this.cbEntity.setTriggerAction(TriggerAction.ALL);
        this.cbEntity.setVisible(false);
        this.cbEntity.setAllowBlank(false);

        // init text field entite
        this.txtEntity = new LabelField();
        this.txtEntity.setFieldLabel(this.messages.delegationentite());
        this.txtEntity.setReadOnly(true);
        this.txtEntity.setVisible(false);

        this.frPanel.add(this.cbEntity);
        this.frPanel.add(this.txtEntity);

        // init commbobox perimetre
        this.cbPerimetre = new ComboBox<PerimetreModel>();
        this.cbPerimetre.setFieldLabel(this.messages.chooseentityperimetre());
        this.cbPerimetre.setLabelSeparator("");
        this.cbPerimetre.setStore(this.perimetres);
        this.cbPerimetre.setDisplayField(PerimetreModel.PERIMETRE_NAME);
        this.cbPerimetre.setTriggerAction(TriggerAction.ALL);
        this.cbPerimetre.setAllowBlank(false);
        this.cbPerimetre.setSimpleTemplate("<span title=\"{" + this.cbPerimetre.getDisplayField() + "}\">{" + this.cbPerimetre.getDisplayField()
                + "}</span>");
        this.cbPerimetre.setValidator(new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (field == ChooseEntityEcran.this.cbPerimetre && ChooseEntityEcran.this.cbPerimetre.getValue() == null) {
                    ChooseEntityEcran.this.btnValidate.setEnabled(false);
                    return ChooseEntityEcran.this.messages.chooseentiteformperimetreerror();
                } else {
                    ChooseEntityEcran.this.btnValidate.setEnabled(true);
                }
                return null;
            }

        });

        this.btnValidate = new Button(this.messages.commonValiderButton());
        this.btnValidate.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {

                if (ChooseEntityEcran.this.frPanel.isValid()) {
                    EntiteModel entiteModel = null;
                    if (ChooseEntityEcran.this.isSuperUser) {
                        entiteModel = ChooseEntityEcran.this.cbEntity.getValue();
                    } else {
                        entiteModel = ChooseEntityEcran.this.currentEntite;
                    }
                    PerimetreModel perimetreModel = ChooseEntityEcran.this.cbPerimetre.getValue();
                    SessionServiceImpl.getInstance().setEntiteContext(entiteModel);
                    SessionServiceImpl.getInstance().setPerimetreContext(perimetreModel);
                    SessionServiceImpl.getInstance().getUserContext().setPerimetre(perimetreModel);
                    SessionServiceImpl.getInstance().getUserContext().setEntite(entiteModel); // tdo 12 Dec

                    ChooseEntityEcran.this.clientUserService.updateNoRoles(SessionServiceImpl.getInstance().getUserContext(),
                            new AsyncCallback<UserModel>() {

                                @Override
                                public void onSuccess(UserModel result) {
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                }
                            });

                    NavigationEvent e = new NavigationEvent(new DelegationListProjectEvent(entiteModel, perimetreModel));

                    if (SessionServiceImpl.getInstance().getActionContext() != null) {
                        ChooseEntityEcran.this.navigation.goToEcran(SessionServiceImpl.getInstance().getActionContext(), e);
                    } else {
                        ChooseEntityEcran.this.navigation.goToEcran(Action.ACTION_DELEGATION, e);
                    }
                } else if (ChooseEntityEcran.this.perimetres.getCount() == 0) {
                    if (!ChooseEntityEcran.this.isSuperUser) {
                        EntiteModel entiteModel = null;
                        if (ChooseEntityEcran.this.isSuperUser) {
                            entiteModel = ChooseEntityEcran.this.cbEntity.getValue();
                        } else {
                            entiteModel = ChooseEntityEcran.this.currentEntite;
                        }
                        MessageBox box = new MessageBox();
                        box.setButtons(MessageBox.OK);
                        box.setIcon(MessageBox.ERROR);
                        box.setTitle(ChooseEntityEcran.this.messages.commonErreurHeader());
                        box.setMessage(ChooseEntityEcran.this.messages.commonnopermissionentite(entiteModel.getName()));
                        ((Button) box.getDialog().getButtonBar().getItem(0)).setText(ChooseEntityEcran.this.messages.commonClosebutton());
                        box.show();
                    }

                }
            }
        });

        this.frPanel.add(this.cbPerimetre);
        this.frPanel.addButton(this.btnValidate);

        container.add(this.frPanel);

        Viewport viewport = new Viewport();
        final BorderLayout layout = new BorderLayout();
        viewport.setLayout(layout);
        viewport.setStyleAttribute("padding", "0px");
        viewport.setBorders(true);
        viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH, 150));
        viewport.setStyleAttribute("background", "white");
        viewport.add(container, new BorderLayoutData(LayoutRegion.CENTER));
        this.add(viewport);

        this.bus.addHandler(LoadActionEvent.getType(), new LoadActionHandler() {

            @Override
            public void onLoadAction(LoadActionEvent event) {
                ChooseEntityEcran.this.displayLabelOrCombo();
            }
        });

        this.currentUser = this.getUserContext();
        if (this.currentUser != null) {
            // check super user
            this.isSuperUser = this.currentUser.isSuperUser();
            this.bus.fireEvent(new LoadActionEvent(null));
        }
    }

    private void displayLabelOrCombo() {
        if (this.isSuperUser) {
            this.frPanel.setHeading(this.messages.chooseentitysuperheader());
            this.getStoresForCombos();
            this.cbEntity.setVisible(true);
            this.txtEntity.setVisible(false);
            this.cbEntity.setValidator(new Validator() {

                @Override
                public String validate(Field<?> field, String value) {
                    if (field == ChooseEntityEcran.this.cbEntity && ChooseEntityEcran.this.cbEntity.getValue() == null) {
                        ChooseEntityEcran.this.btnValidate.setEnabled(false);
                        return ChooseEntityEcran.this.messages.choosentiteformentiteerror();
                    } else {
                        ChooseEntityEcran.this.btnValidate.setEnabled(true);
                    }
                    return null;
                }

            });
            this.cbEntity.addSelectionChangedListener(new SelectionChangedListener<EntiteModel>() {

                @Override
                public void selectionChanged(SelectionChangedEvent<EntiteModel> se) {
                    EntiteModel selected = se.getSelectedItem();
                    ChooseEntityEcran.this.perimetres.removeAll();
                    ChooseEntityEcran.this.cbPerimetre.clear();
                    if (null != selected) {
                        ChooseEntityEcran.this.refreshDataForPerimetre(selected.getEntId());
                    }
                }
            });
        } else {

            // txtEntity.el().firstChild().setStyleName("textLikeLabel");// x-fieldset-noborder");
            this.txtEntity.setVisible(true);
            this.cbEntity.setAllowBlank(true);
            this.cbEntity.setVisible(false);
            this.getEntiteForUser(this.currentUser);
        }
        this.frPanel.repaint();

    }

    private void getEntiteForUser(UserModel currentUser) {
        this.currentEntite = new EntiteModel();
        this.clientEntiteService.getEntiteByUser(currentUser, new AsyncCallback<EntiteModel>() {

            @Override
            public void onSuccess(EntiteModel arg0) {
                ChooseEntityEcran.this.currentEntite = arg0;
                ChooseEntityEcran.this.txtEntity.setValue(ChooseEntityEcran.this.currentEntite.getName());
                ChooseEntityEcran.this.getStoreForPerimetreCombo(ChooseEntityEcran.this.currentEntite.getEntId());
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
    }

    @Override
    public void onLoadApplication(NavigationEvent event) {
        if (event.getObject() instanceof LoadActionEvent) {
            this.bus.fireEvent((LoadActionEvent) event.getObject());
        }
    }

    private void getStoresForCombos() {
        // entites = new ListStore<EntiteModel>();
        this.clientEntiteService.getAllEntites(new AsyncCallback<List<EntiteModel>>() {

            @Override
            public void onSuccess(List<EntiteModel> arg0) {
                ChooseEntityEcran.this.entites.removeAll();
                ChooseEntityEcran.this.entites.add(arg0);
                ChooseEntityEcran.this.cbEntity.setStore(ChooseEntityEcran.this.entites);
                // set first entity
                EntiteModel entiteModel = null;
                if (SessionServiceImpl.getInstance().getEntiteContext() != null) {
                    for (EntiteModel entiteMdl : ChooseEntityEcran.this.entites.getModels()) {
                        if (entiteMdl.getEntId().equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                            entiteModel = entiteMdl;
                        }
                    }
                }
                if (entiteModel == null) {
                    if (arg0 != null && arg0.size() > 0) {
                        EntiteModel em = arg0.get(0);
                        ChooseEntityEcran.this.cbEntity.select(0);
                        ChooseEntityEcran.this.cbEntity.setValue(em);
                    }
                } else {
                    ChooseEntityEcran.this.cbEntity.setValue(entiteModel);
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
    }

    private void getStoreForPerimetreCombo(String emId) {
        this.perimetres.removeAll();
        this.cbPerimetre.clear();
        boolean isAdmin = false;
        if (Action.ACTION_ADMIN.equals(SessionServiceImpl.getInstance().getActionContext())) {
            isAdmin = true;
        }
        this.clientPerimetreService.findFirstLevelPerimetreByUserRoles(emId, isAdmin, SessionServiceImpl.getInstance().getUserContext()
                .getUserRoles(), new AsyncCallback<List<PerimetreModel>>() {

            @Override
            public void onSuccess(List<PerimetreModel> arg0) {
                ChooseEntityEcran.this.perimetres.removeAll();
                ChooseEntityEcran.this.perimetres.add(arg0);
                ChooseEntityEcran.this.cbPerimetre.setStore(ChooseEntityEcran.this.perimetres);
                PerimetreModel perimetreModel = null;
                if ((SessionServiceImpl.getInstance().getUserContext() != null)
                        && (SessionServiceImpl.getInstance().getUserContext().getPerimetre() != null)) {
                    for (PerimetreModel perMdl : ChooseEntityEcran.this.perimetres.getModels()) {
                        if (perMdl.getPerId().equals(SessionServiceImpl.getInstance().getUserContext().getPerimetre().getPerId())) {
                            perimetreModel = perMdl;
                        }
                    }
                }

                if (perimetreModel != null) {
                    ChooseEntityEcran.this.cbPerimetre.setValue(perimetreModel);
                } else {
                    if (arg0 != null && arg0.size() > 0) {
                        PerimetreModel pm = arg0.get(0);
                        ChooseEntityEcran.this.cbPerimetre.select(0);
                        ChooseEntityEcran.this.cbPerimetre.setValue(pm);
                    }
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
}
