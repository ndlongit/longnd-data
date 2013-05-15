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
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
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
import com.structis.vip.client.constant.ConstantClient;
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

public class UserFormPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
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
	
	//private TextField<String> tfPassword;
	//private TextField<String> tfPasswordConfirm;

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

		setLayout(new FlowLayout(10));
		setScrollMode(Scroll.AUTO);
		setStyleAttribute("paddingBottom", "20px");
		setStyleAttribute("paddingRight", "10px");
		setWidth(WIDTH);

		initUI();
		initEvent();
		addHandler();
	}

	private void initUI() {
		panel = new FormPanel();
		panel.setLabelWidth(180);
		panel.setFrame(true);
		panel.setHeading(messages.userform());
		panel.setBorders(false);
		panel.setCollapsible(false);
		panel.setLayout(new FlowLayout());
		panel.setSize(WIDTH, -1);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);

		initBackLink();
		initForm();

		LayoutContainer lcLine = new LayoutContainer();
		lcLine.setSize(WIDTH, HEIGHT);
		lcLine.setLayout(new ColumnLayout());
		lcLine.add(new HTML("<hr width='670px'/>"));
		panel.add(lcLine);

		initGrid();

		btnAnnuler = new Button(messages.commonAnnulerButton());
		btnModifier = new Button(messages.commonValiderButton());

		panel.addButton(btnAnnuler);
		panel.addButton(btnModifier);

		add(panel);
	}

	private void initEvent() {
		btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				AppUtil.removeAdminInEditMode();
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST);
				bus.fireEvent(event);
			}
		});

		btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (panel.isValid()) {
					save();
				}
			}
		});
	}

	private void addHandler() {
		bus.addHandler(ModifyUserEvent.getType(), new ModifyUserHandler() {
			@Override
			public void onLoadAction(ModifyUserEvent event) {

				if (event.getMode() == ModifyUserEvent.MODE_IS_VIEW) {
					setEnabledAll(false);
					isView = true;
				} else {
					AppUtil.putInAdminEditMode();
					setEnabledAll(true);
					isView = false;
				}

				initData();
				if (event.getModel() != null) {
					isEdit = true;
					clientUserServiceAsync.findUserById(event.getModel().getId(), new AsyncCallback<UserModel>() {

						@Override
						public void onSuccess(UserModel arg0) {
							if (arg0 != null) {
								model = arg0;
								applyData();
							}
						}

						@Override
						public void onFailure(Throwable arg0) {
						}
					});
				} else {
					isEdit = false;
					model = new UserModel();
					model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
					applyData();
				}
			}
		});
	}

	private void applyData() {
		store.removeAll();
		if (isEdit) {
			tfUserName.setValue(model.getUserName());
			tfUserName.setEnabled(false);
			if (model.getCollaborateur() == null) {
				tfFirstName.setValue(model.getFirstName());
				tfLastName.setValue(model.getLastName());
			} else {
				tfFirstName.setValue(model.getCollaborateur().getFirstname());
				tfLastName.setValue(model.getCollaborateur().getLastname());
				tfFirstName.setEnabled(false);
				tfLastName.setEnabled(false);
			}
			//tfPassword.setValue(model.getPassword());
			store.add(model.getUserRoles());
			cbDomain.setValue(model.getDomain());
		} else {
			tfUserName.setEnabled(true);
			tfFirstName.setEnabled(true);
			tfLastName.setEnabled(true);
		}
	}

	private void setEnabledAll(boolean enable) {
		for (Field<?> field : panel.getFields()) {
			field.setEnabled(enable);
		}
		btnModifier.setVisible(enable);
		btnAdd.setVisible(enable);
		btnApply.setVisible(enable);
		btnSupprimer.setVisible(enable);
	}
	
	private void initBackLink() {
		LayoutContainer backLink = new LayoutContainer();
		backLink.setSize(WIDTH, -1);
		Label lblBack = new Label(messages.userback());

		lblBack.setStyleName("x-link-item");
		backLink.setStyleAttribute("margin-bottom", "20px");
		backLink.add(lblBack);

		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST);
					bus.fireEvent(contentEvent);
				}
			}
		});

		add(backLink);
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

		tfUserName = new TextField<String>();
		tfUserName.setMaxLength(30);
		tfUserName.setAllowBlank(false);
		tfUserName.setFieldLabel(messages.userusername());
		lcLeft.add(tfUserName, formData);

		tfFirstName = new TextField<String>();
		tfFirstName.setMaxLength(30);
		tfFirstName.setAllowBlank(true);
		tfFirstName.setFieldLabel(messages.userfirstname());
		lcLeft.add(tfFirstName, formData);

		tfLastName = new TextField<String>();
		tfLastName.setMaxLength(30);
		tfLastName.setAllowBlank(true);
		tfLastName.setFieldLabel(messages.userlastname());
		lcLeft.add(tfLastName, formData);

		// setup right layout
		LayoutContainer lcRight = new LayoutContainer();
		FormLayout flRight = new FormLayout();
		flRight.setLabelAlign(LabelAlign.RIGHT);
		flRight.setLabelWidth(130);
		lcRight.setLayout(flRight);

		lsDomain = new ListStore<DomainModel>();
		cbDomain = new ComboBox<DomainModel>();
		cbDomain.setTriggerAction(TriggerAction.ALL);
		cbDomain.setFieldLabel("Domaine");
		cbDomain.setEditable(false);
		cbDomain.setStore(lsDomain);
		cbDomain.setDisplayField(DomainModel.DOMAIN_NAME);
		lcRight.add(cbDomain, formData);

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

		panel.add(lcInformation);
	}

	@SuppressWarnings("unchecked")
	private TreePanel<PerimetreTreeModel> getAdminTree() {
		TreePanel<PerimetreTreeModel> component = (TreePanel<PerimetreTreeModel>) ComponentManager.get().get(
				ConstantClient.ADMIN_TREE_ID);
		return component;
	}
	
	private void initGrid() {
		panel.add(new Label(messages.userrolesheader()));

		ToolBar topToolBar = new ToolBar();

		btnAdd = new Button(messages.commonAddButton());
		btnAdd.setStyleAttribute("margin-left", "10px");
		btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

		btnSupprimer = new Button(messages.commonSupprimer());
		btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
		btnSupprimer.setEnabled(false);

		btnApply = new Button(messages.userrolesapply());
		btnApply.setEnabled(false);

		topToolBar.add(btnAdd);
		topToolBar.add(btnSupprimer);
		topToolBar.add(btnApply);

		GridCellRenderer<UserRoleModel> roleRender = new GridCellRenderer<UserRoleModel>() {

			@Override
			public Object render(UserRoleModel model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex,
					ListStore<UserRoleModel> store, Grid<UserRoleModel> grid) {
				return buildRoleColumn(model);
			}
		};

		ColumnConfig clRole = new ColumnConfig("role.name", messages.userrolesrole(), 300);
		clRole.setRenderer(roleRender);

		ColumnConfig clPerimetre = new ColumnConfig("perimetre.name", messages.userrolesperimetre(), 370);
		
		store = new ListStore<UserRoleModel>();

		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(clRole);
		config.add(clPerimetre);

		final ColumnModel cm = new ColumnModel(config);

		grid = new EditorGrid<UserRoleModel>(store, cm);

		grid.setBorders(true);
		grid.setLoadMask(true);
		grid.getView().setAutoFill(true);
		grid.getView().setForceFit(true);
		grid.setColumnLines(true);
		grid.setSelectionModel(new GridSelectionModel<UserRoleModel>());
		grid.setStripeRows(true);

		ContentPanel gridPanel = new ContentPanel();
		gridPanel.setHeaderVisible(false);
		gridPanel.setTopComponent(topToolBar);
		gridPanel.setCollapsible(false);
		gridPanel.setFrame(false);
		gridPanel.setStyleAttribute("marginTop", "10px");
		gridPanel.setSize(WIDTH - 30, 200);
		gridPanel.setLayout(new FitLayout());
		gridPanel.add(grid);
		gridPanel.setCollapsible(false);
		grid.getAriaSupport().setLabelledBy(gridPanel.getHeader().getId() + "-label");

		grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<UserRoleModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<UserRoleModel> se) {
				if (se.getSelectedItem() != null) {
					btnApply.setEnabled(true);
					btnSupprimer.setEnabled(true);
				} else {
					btnApply.setEnabled(false);
					btnSupprimer.setEnabled(false);
				}
			}
		});

		btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				UserRoleModel userRole = new UserRoleModel();
				userRole.setUser(model);
				store.add(userRole);
			}
		});

		final Listener<MessageBoxEvent> lSupprimer = new Listener<MessageBoxEvent>() {
			public void handleEvent(MessageBoxEvent ce) {
				Button btn = ce.getButtonClicked();
				String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
				if (txtReturn.equals(btn.getText())) {
					final UserRoleModel model = grid.getSelectionModel().getSelectedItem();
					store.remove(model);
				} else {
				}
			}
		};

		btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				MessageBox box = new MessageBox();
				box.setButtons(MessageBox.YESNO);
				box.setIcon(MessageBox.INFO);
				box.setTitle(messages.commonConfirmation());
				box.addCallback(lSupprimer);
				box.setMessage(messages.userrolesremoveconfirm());
				((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonOui());
				((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonNon());
				box.show();
			}
		});

		final Listener<MessageBoxEvent> lApply = new Listener<MessageBoxEvent>() {
			public void handleEvent(MessageBoxEvent ce) {
				Button btn = ce.getButtonClicked();
				String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
				if (txtReturn.equals(btn.getText())) {
					if (grid.getSelectionModel().getSelectedItem() != null) {
						TreePanel<PerimetreTreeModel> perimetreTree = getAdminTree();
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
									grid.getSelectionModel().getSelectedItem().setPerimetre(perModel);
									grid.getStore().update(grid.getSelectionModel().getSelectedItem());
								} else {
									Info.display(messages.commoninfo(), messages.commonnopermissionperimetre());
								}
							} else {
								Info.display(messages.commoninfo(), messages.admintreeselect());
							}
						} 
					}
				}
			}
		};

		btnApply.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if ((grid.getSelectionModel().getSelectedItem() != null)
						&& (!grid.getSelectionModel().getSelectedItem().getRole().isApplicationAdmin())) {
					MessageBox box = new MessageBox();
					box.setButtons(MessageBox.YESNO);
					box.setIcon(MessageBox.INFO);
					box.setTitle(messages.commonConfirmation());
					box.addCallback(lApply);
					box.setMessage(messages.userrolesapplyconfirm());
					((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonOui());
					((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonNon());
					box.show();
				} else {
					Info.display(messages.commoninfo(), messages.userrolescannotapply());
				}
			}
		});

		panel.add(gridPanel);
	}

	private Object buildRoleColumn(final UserRoleModel model) {
		ComboBox<RoleModel> cbRole = new ComboBox<RoleModel>();

		cbRole.setWidth(290);
		cbRole.setForceSelection(true);
		cbRole.setTriggerAction(TriggerAction.ALL);
		cbRole.setDisplayField(PerimetreTypeModel.PERIMETRE_TYPE_NAME);
		cbRole.setEditable(false);
		if (isView) {
			cbRole.setEnabled(false);
		}
		cbRole.setStore(roles);
		if (model.getRole() != null) {
			cbRole.setValue(model.getRole());
		} else if (roles != null && roles.getCount() != 0) {
			cbRole.setValue(roles.getAt(0));
			model.setRole(roles.getAt(0));
			if (!roles.getAt(0).isApplicationAdmin()) {
				Info.display(messages.commoninfo(), messages.userrolesaddmessage());
			}
		}

		if (cbRole.isEnabled()) {
			cbRole.addSelectionChangedListener(new SelectionChangedListener<RoleModel>() {
				@Override
				public void selectionChanged(SelectionChangedEvent<RoleModel> se) {
					grid.getSelectionModel().select(model, false);
					grid.getSelectionModel().getSelectedItem().setRole(se.getSelectedItem());
					if ((grid.getSelectionModel().getSelectedItem() != null)
							&& ((grid.getSelectionModel().getSelectedItem().getRole().isApplicationAdmin()) || (grid
									.getSelectionModel().getSelectedItem().getRole().isUoAdmin()))) {
						grid.getSelectionModel().getSelectedItem().setPerimetre(null);
					}
					if ((grid.getSelectionModel().getSelectedItem() != null)
							&& (!grid.getSelectionModel().getSelectedItem().getRole().isApplicationAdmin())) {
						Info.display(messages.commoninfo(), messages.userrolesaddmessage());
					}
					grid.getStore().update(grid.getSelectionModel().getSelectedItem());
				}
			});
		}

		return cbRole;
	}

	private void initData() {
		panel.reset();
		clientRoleServiceAsync.getRoles(SessionServiceImpl.getInstance().getUserContext(), new AsyncCallback<List<RoleModel>>() {
			@Override
			public void onSuccess(List<RoleModel> arg0) {
				roles.removeAll();
				roles.add(arg0);
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});

		clientDomainServiceAsync.getDomains(new AsyncCallback<List<DomainModel>>() {

			@Override
			public void onSuccess(List<DomainModel> result) {
				lsDomain.removeAll();
				lsDomain.add(result);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	private void save() {
		if ((tfUserName == null) || ("".equals(tfUserName.getValue().trim()))) {
			Info.display(messages.commonerror(), messages.userusernamenotblank());
			return;
		}
		if (model == null) {
			model = new UserModel();
			model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
		}

		model.setUserName(tfUserName.getValue().trim());
		model.setLastName(tfLastName.getValue());
		model.setFirstName(tfFirstName.getValue());
		//model.setPassword(tfPassword.getValue());
		
		model.setUserRoles(store.getModels());
		model.setDomain(cbDomain.getValue());

		if (isEdit) {
			clientUserServiceAsync.update(model, new AsyncCallback<UserModel>() {

				@Override
				public void onSuccess(UserModel arg0) {
					if (arg0 != null) {
						ContentEvent contentEvent = new ContentEvent();
						contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST);
						contentEvent.setEvent(new LoadUserEvent());
						bus.fireEvent(contentEvent);
						AppUtil.removeAdminInEditMode();
						Info.display(messages.commoninfo(), messages.userupdatesuccess());
					} else {
						Info.display(messages.commonerror(), messages.userupdatefailed());
					}
				}

				@Override
				public void onFailure(Throwable arg0) {
					String details = arg0.getMessage();
					if (arg0 instanceof UserException) {
						details = ExceptionMessageHandler.getErrorMessage(((UserException) arg0).getCode());
					}
					Info.display(messages.commonerror(), details);
				}
			});
		} else {
			clientUserServiceAsync.insert(model, new AsyncCallback<UserModel>() {
				@Override
				public void onSuccess(UserModel arg0) {
					if (arg0 != null) {
						ContentEvent contentEvent = new ContentEvent();
						contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST);
						contentEvent.setEvent(new LoadUserEvent());
						bus.fireEvent(contentEvent);
						AppUtil.removeAdminInEditMode();
						Info.display(messages.commoninfo(), messages.usersavesuccess());
					} else {
						Info.display(messages.commonerror(), messages.usersavefailed());
					}
				}

				@Override
				public void onFailure(Throwable arg0) {
					String details = arg0.getMessage();
					if (arg0 instanceof UserException) {
						details = ExceptionMessageHandler.getErrorMessage(((UserException) arg0).getCode());
					}
					Info.display(messages.commonerror(), details);
				}
			});
		}
	}
}
