package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.LoadUserEvent;
import com.structis.vip.client.event.ModifyUserEvent;
import com.structis.vip.client.event.ModifyUserHandler;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDomainServiceAsync;
import com.structis.vip.client.service.ClientRoleServiceAsync;
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.exception.UserException;
import com.structis.vip.shared.model.DomainModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.PerimetreTypeModel;
import com.structis.vip.shared.model.RoleModel;
import com.structis.vip.shared.model.UserModel;
import com.structis.vip.shared.model.UserRoleModel;

public class UserFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("95%");
    private final static int WIDTH = 700;
    private final static int HEIGHT = -1;

    private ClientUserServiceAsync clientUserServiceAsync = ClientUserServiceAsync.Util.getInstance();
    private ClientRoleServiceAsync clientRoleServiceAsync = ClientRoleServiceAsync.Util.getInstance();
    private ClientDomainServiceAsync clientDomainServiceAsync = ClientDomainServiceAsync.Util.getInstance();

    private SimpleEventBus bus;

    private ListStore<UserRoleModel> store = new ListStore<UserRoleModel>();
    private EditorGrid<UserRoleModel> grid;

    private ListStore<RoleModel> roles = new ListStore<RoleModel>();

    private TextField<String> tfUserName;
    private TextField<String> tfFirstName;
    private TextField<String> tfLastName;

    // private TextField<String> tfPassword;
    // private TextField<String> tfPasswordConfirm;

    private ComboBox<DomainModel> cbDomain;
    private ListStore<DomainModel> lsDomain = new ListStore<DomainModel>();

    private FormPanel panel;
    private Button btnAnnuler;
    private Button btnModifier;
    private Button btnAdd;
    private Button btnApply;
    private Button btnSupprimer;

    private UserModel model;
    private boolean isEdit = true;
    private boolean isView = false;

    public UserFormPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.setLayout(new FlowLayout(10));
        this.setScrollMode(Scroll.AUTO);
        this.setStyleAttribute("paddingBottom", "20px");
        this.setStyleAttribute("paddingRight", "10px");
        this.setWidth(WIDTH);

        this.initUI();
        this.initEvent();
        this.addHandler();
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setLabelWidth(180);
        this.panel.setFrame(true);
        this.panel.setHeading(this.messages.userform());
        this.panel.setBorders(false);
        this.panel.setCollapsible(false);
        this.panel.setLayout(new FlowLayout());
        this.panel.setSize(WIDTH, -1);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);

        this.initBackLink();
        this.initForm();

        LayoutContainer lcLine = new LayoutContainer();
        lcLine.setSize(WIDTH, HEIGHT);
        lcLine.setLayout(new ColumnLayout());
        lcLine.add(new HTML("<hr width='670px'/>"));
        this.panel.add(lcLine);

        this.initGrid();

        this.btnAnnuler = new Button(this.messages.commonAnnulerButton());
        this.btnModifier = new Button(this.messages.commonValiderButton());

        this.panel.addButton(this.btnAnnuler);
        this.panel.addButton(this.btnModifier);

        this.add(this.panel);
    }

    private void initEvent() {
        this.btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                AppUtil.removeAdminInEditMode();
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST);
                UserFormPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (UserFormPanel.this.panel.isValid()) {
                    UserFormPanel.this.save();
                }
            }
        });
    }

    private void addHandler() {
        this.bus.addHandler(ModifyUserEvent.getType(), new ModifyUserHandler() {

            @Override
            public void onLoadAction(ModifyUserEvent event) {

                if (event.getMode() == ModifyUserEvent.MODE_IS_VIEW) {
                    UserFormPanel.this.setEnabledAll(false);
                    UserFormPanel.this.isView = true;
                } else {
                    AppUtil.putInAdminEditMode();
                    UserFormPanel.this.setEnabledAll(true);
                    UserFormPanel.this.isView = false;
                }

                UserFormPanel.this.initData();
                if (event.getModel() != null) {
                    UserFormPanel.this.isEdit = true;
                    UserFormPanel.this.clientUserServiceAsync.findUserById(event.getModel().getId(), new AsyncCallback<UserModel>() {

                        @Override
                        public void onSuccess(UserModel arg0) {
                            if (arg0 != null) {
                                UserFormPanel.this.model = arg0;
                                UserFormPanel.this.applyData();
                            }
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });
                } else {
                    UserFormPanel.this.isEdit = false;
                    UserFormPanel.this.model = new UserModel();
                    UserFormPanel.this.model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                    UserFormPanel.this.applyData();
                }
            }
        });
    }

    private void applyData() {
        this.store.removeAll();
        if (this.isEdit) {
            this.tfUserName.setValue(this.model.getUserName());
            this.tfUserName.setEnabled(false);
            if (this.model.getCollaborateur() == null) {
                this.tfFirstName.setValue(this.model.getFirstName());
                this.tfLastName.setValue(this.model.getLastName());
            } else {
                this.tfFirstName.setValue(this.model.getCollaborateur().getFirstname());
                this.tfLastName.setValue(this.model.getCollaborateur().getLastname());
                this.tfFirstName.setEnabled(false);
                this.tfLastName.setEnabled(false);
            }
            // tfPassword.setValue(model.getPassword());
            this.store.add(this.model.getUserRoles());
            this.cbDomain.setValue(this.model.getDomain());
        } else {
            this.tfUserName.setEnabled(true);
            this.tfFirstName.setEnabled(true);
            this.tfLastName.setEnabled(true);
        }
    }

    private void setEnabledAll(boolean enable) {
        for (Field<?> field : this.panel.getFields()) {
            field.setEnabled(enable);
        }
        this.btnModifier.setVisible(enable);
        this.btnAdd.setVisible(enable);
        this.btnApply.setVisible(enable);
        this.btnSupprimer.setVisible(enable);
    }

    private void initBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(WIDTH, -1);
        Label lblBack = new Label(this.messages.userback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST);
                    UserFormPanel.this.bus.fireEvent(contentEvent);
                }
            }
        });

        this.add(backLink);
    }

    private void initForm() {
        LayoutContainer lcInformation = new LayoutContainer();
        lcInformation.setSize(WIDTH - 10, HEIGHT);
        lcInformation.setLayout(new ColumnLayout());

        LayoutContainer lcLeft = new LayoutContainer();
        FormLayout flLeft = new FormLayout();
        flLeft.setLabelAlign(LabelAlign.RIGHT);
        flLeft.setLabelWidth(120);
        lcLeft.setLayout(flLeft);

        this.tfUserName = new TextField<String>();
        this.tfUserName.setMaxLength(30);
        this.tfUserName.setAllowBlank(false);
        this.tfUserName.setFieldLabel(this.messages.userusername());
        lcLeft.add(this.tfUserName, this.formData);

        this.tfFirstName = new TextField<String>();
        this.tfFirstName.setMaxLength(30);
        this.tfFirstName.setAllowBlank(true);
        this.tfFirstName.setFieldLabel(this.messages.userfirstname());
        lcLeft.add(this.tfFirstName, this.formData);

        this.tfLastName = new TextField<String>();
        this.tfLastName.setMaxLength(30);
        this.tfLastName.setAllowBlank(true);
        this.tfLastName.setFieldLabel(this.messages.userlastname());
        lcLeft.add(this.tfLastName, this.formData);

        // setup right layout
        LayoutContainer lcRight = new LayoutContainer();
        FormLayout flRight = new FormLayout();
        flRight.setLabelAlign(LabelAlign.RIGHT);
        flRight.setLabelWidth(130);
        lcRight.setLayout(flRight);

        this.lsDomain = new ListStore<DomainModel>();
        this.cbDomain = new ComboBox<DomainModel>();
        this.cbDomain.setTriggerAction(TriggerAction.ALL);
        this.cbDomain.setFieldLabel("Domaine");
        this.cbDomain.setEditable(false);
        this.cbDomain.setStore(this.lsDomain);
        this.cbDomain.setDisplayField(DomainModel.NAME);
        lcRight.add(this.cbDomain, this.formData);

        // tfPassword = new TextField<String>();
        // tfPassword.setMaxLength(30);
        // tfPassword.setAllowBlank(true);
        // tfPassword.setFieldLabel(messages.userloginpasseword());
        // tfPassword.setPassword(true);
        // lcRight.add(tfPassword, formData);

        // tfPasswordConfirm = new TextField<String>();
        // tfPasswordConfirm.setMaxLength(30);
        // tfPasswordConfirm.setAllowBlank(true);
        // tfPasswordConfirm.setFieldLabel(messages.userpassewordconfirm());
        // tfPasswordConfirm.setPassword(true);
        // lcRight.add(tfPasswordConfirm, formData);

        lcInformation.add(lcLeft, new ColumnData(.5));
        lcInformation.add(lcRight, new ColumnData(.5));

        this.panel.add(lcInformation);
    }

    @SuppressWarnings("unchecked")
    private TreePanel<PerimetreTreeModel> getAdminTree() {
        TreePanel<PerimetreTreeModel> component = (TreePanel<PerimetreTreeModel>) ComponentManager.get().get(ClientConstant.ADMIN_TREE_ID);
        return component;
    }

    private void initGrid() {
        this.panel.add(new Label(this.messages.userrolesheader()));

        ToolBar topToolBar = new ToolBar();

        this.btnAdd = new Button(this.messages.commonAddButton());
        this.btnAdd.setStyleAttribute("margin-left", "10px");
        this.btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

        this.btnSupprimer = new Button(this.messages.commonSupprimer());
        this.btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
        this.btnSupprimer.setEnabled(false);

        this.btnApply = new Button(this.messages.userrolesapply());
        this.btnApply.setEnabled(false);

        topToolBar.add(this.btnAdd);
        topToolBar.add(this.btnSupprimer);
        topToolBar.add(this.btnApply);

        GridCellRenderer<UserRoleModel> roleRender = new GridCellRenderer<UserRoleModel>() {

            @Override
            public Object render(UserRoleModel model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex,
                    int colIndex, ListStore<UserRoleModel> store, Grid<UserRoleModel> grid) {
                return UserFormPanel.this.buildRoleColumn(model);
            }
        };

        ColumnConfig clRole = new ColumnConfig("role.name", this.messages.userrolesrole(), 300);
        clRole.setRenderer(roleRender);

        ColumnConfig clPerimetre = new ColumnConfig("perimetre.name", this.messages.userrolesperimetre(), 370);

        this.store = new ListStore<UserRoleModel>();

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(clRole);
        config.add(clPerimetre);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new EditorGrid<UserRoleModel>(this.store, cm);

        this.grid.setBorders(true);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        this.grid.setColumnLines(true);
        this.grid.setSelectionModel(new GridSelectionModel<UserRoleModel>());
        this.grid.setStripeRows(true);

        ContentPanel gridPanel = new ContentPanel();
        gridPanel.setHeaderVisible(false);
        gridPanel.setTopComponent(topToolBar);
        gridPanel.setCollapsible(false);
        gridPanel.setFrame(false);
        gridPanel.setStyleAttribute("marginTop", "10px");
        gridPanel.setSize(WIDTH - 30, 200);
        gridPanel.setLayout(new FitLayout());
        gridPanel.add(this.grid);
        gridPanel.setCollapsible(false);
        this.grid.getAriaSupport().setLabelledBy(gridPanel.getHeader().getId() + "-label");

        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<UserRoleModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<UserRoleModel> se) {
                if (se.getSelectedItem() != null) {
                    UserFormPanel.this.btnApply.setEnabled(true);
                    UserFormPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    UserFormPanel.this.btnApply.setEnabled(false);
                    UserFormPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        this.btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                UserRoleModel userRole = new UserRoleModel();
                userRole.setUser(UserFormPanel.this.model);
                UserFormPanel.this.store.add(userRole);
            }
        });

        final Listener<MessageBoxEvent> lSupprimer = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final UserRoleModel model = UserFormPanel.this.grid.getSelectionModel().getSelectedItem();
                    UserFormPanel.this.store.remove(model);
                } else {
                }
            }
        };

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                MessageBox box = new MessageBox();
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.INFO);
                box.setTitle(UserFormPanel.this.messages.commonConfirmation());
                box.addCallback(lSupprimer);
                box.setMessage(UserFormPanel.this.messages.userrolesremoveconfirm());
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(UserFormPanel.this.messages.commonOui());
                ((Button) box.getDialog().getButtonBar().getItem(1)).setText(UserFormPanel.this.messages.commonNon());
                box.show();
            }
        });

        final Listener<MessageBoxEvent> lApply = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    if (UserFormPanel.this.grid.getSelectionModel().getSelectedItem() != null) {
                        TreePanel<PerimetreTreeModel> perimetreTree = UserFormPanel.this.getAdminTree();
                        PerimetreTreeModel selectedPerimetre = null;
                        if (perimetreTree != null) {
                            selectedPerimetre = perimetreTree.getSelectionModel() == null ? null : (PerimetreTreeModel) perimetreTree
                                    .getSelectionModel().getSelectedItem();
                            if (selectedPerimetre != null) {
                                if (true == selectedPerimetre.getIsUoAdmin()) {
                                    PerimetreModel perModel = null;
                                    if (!selectedPerimetre.getIsEntite()) {
                                        perModel = new PerimetreModel();
                                        perModel.setPerId(selectedPerimetre.getPerId());
                                        perModel.setName(selectedPerimetre.getName());

                                        PerimetreTypeModel perTypeModel = new PerimetreTypeModel();
                                        perTypeModel.setPtyId(selectedPerimetre.getType());
                                        perTypeModel.setName(selectedPerimetre.getName());

                                        perModel.setType(perTypeModel);
                                    }
                                    UserFormPanel.this.grid.getSelectionModel().getSelectedItem().setPerimetre(perModel);
                                    UserFormPanel.this.grid.getStore().update(UserFormPanel.this.grid.getSelectionModel().getSelectedItem());
                                } else {
                                    Info.display(UserFormPanel.this.messages.commoninfo(), UserFormPanel.this.messages.commonnopermissionperimetre());
                                }
                            } else {
                                Info.display(UserFormPanel.this.messages.commoninfo(), UserFormPanel.this.messages.admintreeselect());
                            }
                        }
                    }
                }
            }
        };

        this.btnApply.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if ((UserFormPanel.this.grid.getSelectionModel().getSelectedItem() != null)
                        && (!UserFormPanel.this.grid.getSelectionModel().getSelectedItem().getRole().isApplicationAdmin())) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(UserFormPanel.this.messages.commonConfirmation());
                    box.addCallback(lApply);
                    box.setMessage(UserFormPanel.this.messages.userrolesapplyconfirm());
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(UserFormPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(UserFormPanel.this.messages.commonNon());
                    box.show();
                } else {
                    Info.display(UserFormPanel.this.messages.commoninfo(), UserFormPanel.this.messages.userrolescannotapply());
                }
            }
        });

        this.panel.add(gridPanel);
    }

    private Object buildRoleColumn(final UserRoleModel model) {
        ComboBox<RoleModel> cbRole = new ComboBox<RoleModel>();

        cbRole.setWidth(290);
        cbRole.setForceSelection(true);
        cbRole.setTriggerAction(TriggerAction.ALL);
        cbRole.setDisplayField(PerimetreTypeModel.PERIMETRE_TYPE_NAME);
        cbRole.setEditable(false);
        if (this.isView) {
            cbRole.setEnabled(false);
        }
        cbRole.setStore(this.roles);
        if (model.getRole() != null) {
            cbRole.setValue(model.getRole());
        } else if (this.roles != null && this.roles.getCount() != 0) {
            cbRole.setValue(this.roles.getAt(0));
            model.setRole(this.roles.getAt(0));
            if (!this.roles.getAt(0).isApplicationAdmin()) {
                Info.display(this.messages.commoninfo(), this.messages.userrolesaddmessage());
            }
        }

        if (cbRole.isEnabled()) {
            cbRole.addSelectionChangedListener(new SelectionChangedListener<RoleModel>() {

                @Override
                public void selectionChanged(SelectionChangedEvent<RoleModel> se) {
                    UserFormPanel.this.grid.getSelectionModel().select(model, false);
                    UserFormPanel.this.grid.getSelectionModel().getSelectedItem().setRole(se.getSelectedItem());
                    if ((UserFormPanel.this.grid.getSelectionModel().getSelectedItem() != null)
                            && ((UserFormPanel.this.grid.getSelectionModel().getSelectedItem().getRole().isApplicationAdmin()) || (UserFormPanel.this.grid
                                    .getSelectionModel().getSelectedItem().getRole().isUoAdmin()))) {
                        UserFormPanel.this.grid.getSelectionModel().getSelectedItem().setPerimetre(null);
                    }
                    if ((UserFormPanel.this.grid.getSelectionModel().getSelectedItem() != null)
                            && (!UserFormPanel.this.grid.getSelectionModel().getSelectedItem().getRole().isApplicationAdmin())) {
                        Info.display(UserFormPanel.this.messages.commoninfo(), UserFormPanel.this.messages.userrolesaddmessage());
                    }
                    UserFormPanel.this.grid.getStore().update(UserFormPanel.this.grid.getSelectionModel().getSelectedItem());
                }
            });
        }

        return cbRole;
    }

    private void initData() {
        this.panel.reset();
        this.clientRoleServiceAsync.getRoles(SessionServiceImpl.getInstance().getUserContext(), new AsyncCallback<List<RoleModel>>() {

            @Override
            public void onSuccess(List<RoleModel> arg0) {
                UserFormPanel.this.roles.removeAll();
                UserFormPanel.this.roles.add(arg0);
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });

        this.clientDomainServiceAsync.getDomains(new AsyncCallback<List<DomainModel>>() {

            @Override
            public void onSuccess(List<DomainModel> result) {
                UserFormPanel.this.lsDomain.removeAll();
                UserFormPanel.this.lsDomain.add(result);
            }

            @Override
            public void onFailure(Throwable caught) {
            }
        });
    }

    private void save() {
        if ((this.tfUserName == null) || ("".equals(this.tfUserName.getValue().trim()))) {
            Info.display(this.messages.commonerror(), this.messages.userusernamenotblank());
            return;
        }
        if (this.model == null) {
            this.model = new UserModel();
            this.model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
        }

        this.model.setUserName(this.tfUserName.getValue().trim());
        this.model.setLastName(this.tfLastName.getValue());
        this.model.setFirstName(this.tfFirstName.getValue());
        // model.setPassword(tfPassword.getValue());

        this.model.setUserRoles(this.store.getModels());
        this.model.setDomain(this.cbDomain.getValue());

        if (this.isEdit) {
            this.clientUserServiceAsync.update(this.model, new AsyncCallback<UserModel>() {

                @Override
                public void onSuccess(UserModel arg0) {
                    if (arg0 != null) {
                        ContentEvent contentEvent = new ContentEvent();
                        contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST);
                        contentEvent.setEvent(new LoadUserEvent());
                        UserFormPanel.this.bus.fireEvent(contentEvent);
                        AppUtil.removeAdminInEditMode();
                        Info.display(UserFormPanel.this.messages.commoninfo(), UserFormPanel.this.messages.userupdatesuccess());
                    } else {
                        Info.display(UserFormPanel.this.messages.commonerror(), UserFormPanel.this.messages.userupdatefailed());
                    }
                }

                @Override
                public void onFailure(Throwable arg0) {
                    String details = arg0.getMessage();
                    if (arg0 instanceof UserException) {
                        details = ExceptionMessageHandler.getErrorMessage(((UserException) arg0).getCode());
                    }
                    Info.display(UserFormPanel.this.messages.commonerror(), details);
                }
            });
        } else {
            this.clientUserServiceAsync.insert(this.model, new AsyncCallback<UserModel>() {

                @Override
                public void onSuccess(UserModel arg0) {
                    if (arg0 != null) {
                        ContentEvent contentEvent = new ContentEvent();
                        contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST);
                        contentEvent.setEvent(new LoadUserEvent());
                        UserFormPanel.this.bus.fireEvent(contentEvent);
                        AppUtil.removeAdminInEditMode();
                        Info.display(UserFormPanel.this.messages.commoninfo(), UserFormPanel.this.messages.usersavesuccess());
                    } else {
                        Info.display(UserFormPanel.this.messages.commonerror(), UserFormPanel.this.messages.usersavefailed());
                    }
                }

                @Override
                public void onFailure(Throwable arg0) {
                    String details = arg0.getMessage();
                    if (arg0 instanceof UserException) {
                        details = ExceptionMessageHandler.getErrorMessage(((UserException) arg0).getCode());
                    }
                    Info.display(UserFormPanel.this.messages.commonerror(), details);
                }
            });
        }
    }
}
