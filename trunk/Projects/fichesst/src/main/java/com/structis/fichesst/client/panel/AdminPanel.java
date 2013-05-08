package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.structis.fichesst.client.ecran.AcceuilEcran;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.navigation.NavigationFactory;
import com.structis.fichesst.client.navigation.NavigationService;
import com.structis.fichesst.client.service.ClientChantierService;
import com.structis.fichesst.client.service.ClientChantierServiceAsync;
import com.structis.fichesst.client.service.ClientUtilsateurGrpService;
import com.structis.fichesst.client.service.ClientUtilsateurGrpServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class AdminPanel extends AbstractPanel {

    Listener<BaseEvent> addListener;
    // list user in role
    List<UtilisateurGrpModel> allUserInRole = new ArrayList<UtilisateurGrpModel>();
    MessageBox box;
    Button btnAdd;
    Button btnAjouter;
    Button btnAnnuler;
    Button btnValider;
    @SuppressWarnings("rawtypes")
    SimpleComboBox cbbChantier;
    ChantierModel ch;
    private CheckBox chkBox1;
    private CheckBox chkBox2;
    private ColumnConfig column_1;
    private CheckColumnConfig column_2;
    private CheckColumnConfig column_3;
    Grid<UtilisateurGrpModel> gridAdmin;
    EditorGrid<UtilisateurGrpModel> gridUserInChantier;
    private Integer idChantier;
    int index;
    boolean isAdded = false;
    Boolean isContributeur = false;
    boolean isDelete = false;
    Boolean isLecteur = false;
    boolean isStoreChanged;
    boolean isUpdate = false;
    List<ChantierModel> listChantier = new ArrayList<ChantierModel>();
    List<UtilisateurGrpModel> listUserAdminDelete = new ArrayList<UtilisateurGrpModel>();
    List<UtilisateurGrpModel> listUserBefore = new ArrayList<UtilisateurGrpModel>();
    List<UtilisateurGrpModel> listUserDeleteInChantier = new ArrayList<UtilisateurGrpModel>();
    List<UtilisateurGrpModel> listUserEnd = new ArrayList<UtilisateurGrpModel>();
    List<UtilisateurGrpModel> listUserInChantierAfter = new ArrayList<UtilisateurGrpModel>();
    List<UtilisateurGrpModel> listUserInChantierBefore = new ArrayList<UtilisateurGrpModel>();
    Map<Integer, UtilisateurGrpModel> map = new HashMap<Integer, UtilisateurGrpModel>();
    Map<Integer, UtilisateurGrpModel> map1 = new HashMap<Integer, UtilisateurGrpModel>();
    Map<Integer, List<UtilisateurGrpModel>> mapListUserDelete = new HashMap<Integer, List<UtilisateurGrpModel>>();
    private final NavigationService navigation = NavigationFactory.getNavigation();
    List<UtilisateurGrpModel> roleModelList = new ArrayList<UtilisateurGrpModel>();
    // service chantier
    private final ClientUtilsateurGrpServiceAsync service = GWT.create(ClientUtilsateurGrpService.class);
    // service user
    private final ClientChantierServiceAsync serviceChantier = GWT.create(ClientChantierService.class);
    ListStore<UtilisateurGrpModel> storeChange = new ListStore<UtilisateurGrpModel>();
    ListStore<ChantierModel> storeChantier;
    // Store user in chantier
    ListStore<UtilisateurGrpModel> storeRole;
    TextField<String> txtAdmin;
    private TextField<String> txtChantier;
    List<UtilisateurGrpModel> users = new ArrayList<UtilisateurGrpModel>();
    List<UtilisateurGrpModel> users1 = new ArrayList<UtilisateurGrpModel>();
    List<UtilisateurGrpModel> usersByChantier = new ArrayList<UtilisateurGrpModel>();

    ListStore<UtilisateurGrpModel> userStore;

    ListStore<UtilisateurGrpModel> userStore1;

    public AdminPanel() {
	com.google.gwt.user.client.ui.Grid grid = new com.google.gwt.user.client.ui.Grid(1, 2);
	loadAdminFieldset();
	loadChantierToComboBox();
	setLayout(new FitLayout());
	/* setScrollMode(Scroll.AUTO); */
	setStyleAttribute("backgroundColor", "white");

	setBorders(false);
	TableData column_left = new TableData();
	column_left.setWidth("10%");
	TableData column_right = new TableData();
	column_right.setWidth("10%");
	TableData column1 = new TableData();
	column1.setHorizontalAlign(HorizontalAlignment.LEFT);
	FieldSet adminFieldset = createAdminFieldSet();
	adminFieldset.setWidth(GuiUtil.getScreenWidth() / 2 - 25);
	FieldSet chantierFieldset = CreateChantierFieldset();
	chantierFieldset.setWidth(GuiUtil.getScreenWidth() / 2 - 25);
	LayoutContainer layoucent = new LayoutContainer();
	layoucent.setWidth(5);
	btnValider = new Button(messages.validate());
	btnAnnuler = new Button(messages.cancel());
	FormPanel contentForm = new FormPanel();
	contentForm.setLayout(new TableLayout(5));
	contentForm.setAutoHeight(true);
	contentForm.setBodyBorder(false);
	contentForm.setHeaderVisible(false);
	contentForm.setButtonAlign(HorizontalAlignment.CENTER);
	grid.setWidget(0, 0, adminFieldset);
	grid.setWidget(0, 1, chantierFieldset);
	contentForm.add(grid);
	contentForm.addButton(btnValider);
	contentForm.addButton(btnAnnuler);
	service.findAll(new AsyncCallbackWithErrorResolution<List<UtilisateurGrpModel>>() {
	    @Override
	    public void onSuccess(List<UtilisateurGrpModel> result) {
	    }
	});
	add(contentForm);
	addEventListener();
    }

    public SelectionListener<ButtonEvent> AddButtonEvent() {
	SelectionListener<ButtonEvent> add = new SelectionListener<ButtonEvent>() {

	    @Override
	    public void componentSelected(ButtonEvent ce) {

		UtilisateurGrpModel admin = new UtilisateurGrpModel();
		admin.set("identifiant", txtAdmin.getValue());
		admin.set("badmin", true);
		if (isExistIdentifiant(txtAdmin.getValue())) {
		    userStore.add(admin);
		    userStore.commitChanges();
		    listUserBefore.add(admin);
		    txtAdmin.clear();
		    btnAjouter.setEnabled(false);
		} else {
		    MessageBox.alert("Alert", messages.existAdmin(), null);
		    txtAdmin.clear();
		    txtAdmin.focus();
		    btnAjouter.setEnabled(false);
		}
		users.add(admin);

	    }
	};
	return add;
    }

    public SelectionListener<ButtonEvent> AddButtonEventGridRole() {
	SelectionListener<ButtonEvent> add = new SelectionListener<ButtonEvent>() {

	    @Override
	    public void componentSelected(ButtonEvent ce) {
		UtilisateurGrpModel user = new UtilisateurGrpModel();
		user.set("identifiant", txtChantier.getValue());
		user.set("idChantier", idChantier);
		if (isExistIdentifiantGridRole(txtChantier.getValue())) {
		    storeRole.add(user);
		    storeRole.commitChanges();
		    storeChantier.removeAll();
		    isAdded = true;
		    txtChantier.clear();
		    btnAdd.setEnabled(false);
		} else {
		    MessageBox.alert("Alert", messages.existUser(), null);
		    txtChantier.clear();
		    txtChantier.focus();
		    btnAdd.setEnabled(false);
		}
		map.put(user.getId(), user);
		roleModelList.add(user);

	    }
	};
	return add;
    }

    public void addEventListener() {
	btnValider.addListener(Events.OnClick, new Listener<DomEvent>() {
	    @Override
	    public void handleEvent(DomEvent be) {
		Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
		    @Override
		    public void handleEvent(MessageBoxEvent be) {
			Button btt = be.getButtonClicked();
			if (Dialog.OK.equals(btt.getItemId())) {
			    listUserEnd = userStore.getModels();
			    listUserInChantierAfter = storeRole.getModels();
			    service.updateListUser(listUserEnd, listUserAdminDelete, listUserInChantierAfter, listUserDeleteInChantier, idChantier, new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable arg0) {
				}

				@Override
				public void onSuccess(Void arg0) {
				    Window.Location.reload();

				    GuiUtil.gotoEcran(new AcceuilEcran());
				}
			    });
			} else {
			    userStore.removeAll();
			    userStore.add(listUserBefore);
			    userStore.commitChanges();
			    storeRole.removeAll();
			    storeRole.add(listUserInChantierBefore);
			    storeRole.commitChanges();
			    cbbChantier.removeListener(Events.OnClick, addListener);
			}
		    }
		};
		createConfirmBox(callback, messages.deleteConfirmTitle(), messages.confirmSupprimer());
	    }
	});
	btnAnnuler.addListener(Events.OnClick, new Listener<DomEvent>() {
	    @Override
	    public void handleEvent(DomEvent be) {
		Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
		    @Override
		    public void handleEvent(MessageBoxEvent be) {
			Button btt = be.getButtonClicked();
			if (Dialog.OK.equals(btt.getItemId())) {
			    GuiUtil.gotoEcran(new AcceuilEcran());
			}
		    }
		};
		createConfirmBox(callback, messages.deleteConfirmTitle(), messages.annuler());
	    }
	});
    }

    public FieldSet createAdminFieldSet() {
	userStore = new ListStore<UtilisateurGrpModel>();
	GridCellRenderer<UtilisateurGrpModel> deleteBtn = deleteButton();
	List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
	ColumnConfig column = new ColumnConfig("supprimer", "Supprimer", 75);
	column.setSortable(false);
	column.setMenuDisabled(true);
	column.setGroupable(false);
	column.setFixed(true);
	column.setResizable(false);
	column.setRenderer(deleteBtn);
	column.setAlignment(HorizontalAlignment.LEFT);
	configs.add(column);
	column_1 = new ColumnConfig();// id
	column_1.setId("identifiant");
	column_1.setHeader("Utilisateur/Groupe");
	column_1.setSortable(false);
	column_1.setMenuDisabled(true);
	column_1.setResizable(false);
	column_1.setAlignment(HorizontalAlignment.LEFT);
	column_1.setRenderer(new GridCellRenderer<UtilisateurGrpModel>() {

	    @Override
	    public Object render(UtilisateurGrpModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<UtilisateurGrpModel> store, Grid<UtilisateurGrpModel> grid) {
		String value = model.get(property).toString();
		if (value != null) {
		    StringBuilder html = new StringBuilder();
		    html.append("<span title='");
		    html.append(value);
		    html.append("' >");
		    html.append(value);
		    html.append("</span>");
		    value = html.toString();
		}

		return value;

	    }
	});
	configs.add(column_1);

	ColumnModel cm = new ColumnModel(configs);
	gridAdmin = new Grid<UtilisateurGrpModel>(userStore, cm);
	gridAdmin.setAutoExpandMax(800);
	gridAdmin.setAutoExpandMin(300);
	gridAdmin.setColumnReordering(true);
	gridAdmin.setColumnLines(true);
	gridAdmin.setAutoExpandColumn("identifiant");
	gridAdmin.setBorders(true);
	gridAdmin.setStripeRows(true);
	gridAdmin.setHeight(345);

	FieldSet fieldSet = new FieldSet();
	fieldSet.setStyleAttribute("backgroundColor", "#EDF5EA");
	fieldSet.setHeading("Droits d'administration");
	fieldSet.setHeight(460);
	// fieldSet.setWidth(RootPanel.get().getOffsetWidth() / 2 - 20);
	RowLayout rl_fieldSet = new RowLayout(Orientation.VERTICAL);

	rl_fieldSet.setAdjustForScroll(true);
	fieldSet.setLayout(rl_fieldSet);

	LayoutContainer layoutContainer = new LayoutContainer();
	layoutContainer.setLayout(new ColumnLayout());
	layoutContainer.setBorders(false);

	LabelField lblfldNouveauxAdministrateur = new LabelField(messages.newAdmin());

	layoutContainer.add(lblfldNouveauxAdministrateur);
	layoutContainer.add(new Html("&nbsp"));
	txtAdmin = new TextField();

	txtAdmin.addKeyListener(new KeyListener() {

	    @Override
	    public void componentKeyUp(ComponentEvent event) {
		if (txtAdmin.getValue() != null && !txtAdmin.getValue().trim().isEmpty() && txtAdmin.getValue().toLowerCase().matches("\\b.{0,60}\\\\+.{1,60}")) {
		    btnAjouter.setEnabled(true);
		} else {
		    btnAjouter.setEnabled(false);

		}

	    }
	});

	txtAdmin.setWidth("45%");

	txtAdmin.setToolTip("Hint:domain\\loginname");

	com.extjs.gxt.ui.client.widget.layout.ColumnData cd_txtfldNewTextfield = new com.extjs.gxt.ui.client.widget.layout.ColumnData();
	cd_txtfldNewTextfield.setWidth(0.65);
	layoutContainer.add(txtAdmin, cd_txtfldNewTextfield);
	txtAdmin.setFieldLabel("New TextField");
	Html space = new Html("&nbsp");
	Html space1 = new Html("&nbsp");
	layoutContainer.add(space);
	btnAjouter = new Button(messages.commonAdd());
	btnAjouter.setEnabled(false);
	SelectionListener<ButtonEvent> add = AddButtonEvent();
	btnAjouter.addSelectionListener(add);
	layoutContainer.add(btnAjouter, new com.extjs.gxt.ui.client.widget.layout.ColumnData(100.0));

	layoutContainer.setBorders(false);
	LayoutContainer contentTop = new LayoutContainer();
	contentTop.setHeight(30);
	fieldSet.add(contentTop);
	fieldSet.add(gridAdmin);
	fieldSet.add(space1);
	fieldSet.add(layoutContainer);
	return fieldSet;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public FieldSet CreateChantierFieldset() {
	new Listener<MessageBoxEvent>() {
	    @Override
	    public void handleEvent(MessageBoxEvent be) {
		Button btn = be.getButtonClicked();
		if (btn.getText() == "Yes") {
		    storeRole.rejectChanges();
		}

	    }
	};
	LayoutContainer layoutChantier = new LayoutContainer();
	List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
	GridCellRenderer<UtilisateurGrpModel> deleteBtn = deleteButtonGridRole();
	ColumnConfig column = new ColumnConfig("supprimer", "Supprimer", 75);
	column.setSortable(false);
	column.setMenuDisabled(true);
	column.setGroupable(false);
	column.setFixed(true);
	column.setResizable(false);
	column.setRenderer(deleteBtn);
	column.setAlignment(HorizontalAlignment.LEFT);
	configs.add(column);
	column_1 = new ColumnConfig();// id
	column_1.setHeader("Utilisateur/Groupe");
	column_1.setId("identifiant");
	column_1.setSortable(false);
	column_1.setMenuDisabled(true);
	column_1.setResizable(false);
	column_1.setAlignment(HorizontalAlignment.LEFT);
	configs.add(column_1);

	chkBox1 = new CheckBox();

	CellEditor checkboxEditor1 = new CellEditor(chkBox1);

	column_2 = new CheckColumnConfig(UtilisateurGrpModel.B_Lecteur, "Lecteur", 100) {
	};
	column_2.setEditor(checkboxEditor1);
	column_2.setAlignment(HorizontalAlignment.LEFT);// id
	column_2.setSortable(false);
	column_2.setMenuDisabled(true);
	column_2.setResizable(false);
	configs.add(column_2);
	chkBox2 = new CheckBox();
	CellEditor checkboxEditor2 = new CellEditor(chkBox2);
	column_3 = new CheckColumnConfig(UtilisateurGrpModel.B_Contributeur, "Contributeur", 100) {
	};
	column_3.setAlignment(HorizontalAlignment.LEFT);
	column_3.setEditor(checkboxEditor2);
	column_3.setSortable(false);
	column_3.setMenuDisabled(true);
	column_3.setResizable(false);
	configs.add(column_3); // model,header
	storeRole = new ListStore<UtilisateurGrpModel>();
	ColumnModel cm = new ColumnModel(configs);
	gridUserInChantier = new EditorGrid<UtilisateurGrpModel>(storeRole, cm);
	gridUserInChantier.setColumnReordering(true);
	gridUserInChantier.setColumnLines(true);
	gridUserInChantier.setAutoExpandColumn("identifiant");
	gridUserInChantier.setAutoExpandMax(500);
	gridUserInChantier.setAutoExpandMin(200);
	gridUserInChantier.setBorders(true);
	gridUserInChantier.setStripeRows(true);
	gridUserInChantier.setHeight(345);
	gridUserInChantier.addPlugin(column_2);
	gridUserInChantier.addPlugin(column_3);
	gridUserInChantier.getStore().addStoreListener(new StoreListener<UtilisateurGrpModel>() {
	    @Override
	    public void storeDataChanged(StoreEvent<UtilisateurGrpModel> se) {
		super.storeDataChanged(se);
	    }

	    @Override
	    public void storeRemove(StoreEvent<UtilisateurGrpModel> se) {
		super.storeRemove(se);
		isDelete = true;
	    }

	    @Override
	    public void storeUpdate(StoreEvent<UtilisateurGrpModel> se) {
		super.storeUpdate(se);
		isUpdate = true;
	    }
	});

	FieldSet fieldSet = new FieldSet();
	fieldSet.setStyleAttribute("backgroundColor", "#EDF5EA");
	fieldSet.setHeading("Droits d’accès aux chantiers ");
	fieldSet.setHeight(460);
	fieldSet.setWidth(RootPanel.get().getOffsetWidth() / 2 - 20);
	LayoutContainer layoutContainer = new LayoutContainer();
	layoutContainer.setLayout(new ColumnLayout());
	layoutContainer.setBorders(false);
	layoutContainer.setAutoWidth(true);
	LabelField lblfldNouveauxAdministrateur = new LabelField("Nouveaux:");
	layoutContainer.add(lblfldNouveauxAdministrateur);
	layoutContainer.add(new Html("&nbsp"));
	txtChantier = new TextField();
	com.extjs.gxt.ui.client.widget.layout.ColumnData cd_txtChantier = new com.extjs.gxt.ui.client.widget.layout.ColumnData();
	cd_txtChantier.setWidth(0.85);
	layoutContainer.add(txtChantier, cd_txtChantier);
	txtChantier.setFieldLabel("New TextField");
	txtChantier.setWidth("80%");
	txtChantier.addKeyListener(new KeyListener() {

	    @Override
	    public void componentKeyUp(ComponentEvent event) {
		if (txtChantier.getValue() != null && !txtChantier.getValue().trim().isEmpty() && txtChantier.getValue().toLowerCase().matches("\\b.{0,60}\\\\+.{1,60}")) {
		    btnAdd.setEnabled(true);
		} else {
		    btnAdd.setEnabled(false);
		}

	    }
	});

	txtChantier.setWidth("35%");

	txtChantier.setToolTip("Hint:domain\\loginname");

	SelectionListener<ButtonEvent> add = AddButtonEventGridRole();
	btnAdd = new Button("Ajouter");
	btnAdd.addSelectionListener(add);
	btnAdd.setEnabled(false);
	layoutContainer.add(new Html("&nbsp"));
	layoutContainer.add(btnAdd, new com.extjs.gxt.ui.client.widget.layout.ColumnData(100.0));

	RowLayout rl_fieldSet = new RowLayout(Orientation.VERTICAL);
	rl_fieldSet.setAdjustForScroll(true);
	fieldSet.setLayout(rl_fieldSet);

	LayoutContainer layoutContainer_1 = new LayoutContainer();
	layoutContainer_1.setLayout(new ColumnLayout());
	layoutContainer_1.setBorders(false);
	layoutContainer_1.setAutoWidth(true);
	LabelField lblfldChantier = new LabelField("Chantier:");
	layoutContainer_1.add(lblfldChantier);
	storeChantier = new ListStore<ChantierModel>();
	cbbChantier = new SimpleComboBox();
	cbbChantier.setEditable(false);
	cbbChantier.setDisplayField("nom");
	cbbChantier.setStore(storeChantier);
	cbbChantier.setStyleAttribute("marginLeft", "20px");
	cbbChantier.setTriggerAction(TriggerAction.ALL);
	cbbChantier.setAllowBlank(false);
	cbbChantier.setShadow(false);
	cbbChantier.setLazyRender(false);
	cbbChantier.setSelectOnFocus(true);
	addListener = new Listener<BaseEvent>() {
	    @Override
	    public void handleEvent(BaseEvent be) {
		if (isUpdate || isDelete || isAdded) {
		    MessageBox box = new MessageBox();
		    box.alert("", "Il faut valider d'abord", null);
		}
	    }
	};
	cbbChantier.addListener(Events.OnClick, addListener);
	cbbChantier.addSelectionChangedListener(new SelectionChangedListener<ChantierModel>() {

	    @Override
	    public void selectionChanged(SelectionChangedEvent<ChantierModel> se) {
		ch = se.getSelectedItem();
		idChantier = ch.getId();
		service.findAllUserByChantier(idChantier, new AsyncCallback<List<UtilisateurGrpModel>>() {
		    @Override
		    public void onFailure(Throwable arg0) {

		    }

		    @Override
		    public void onSuccess(List<UtilisateurGrpModel> result) {
			storeRole.removeAll();
			storeRole.add(result);
			storeRole.commitChanges();
			listUserInChantierBefore = storeRole.getModels();
		    }
		});
	    }
	});
	cbbChantier.disableEvents(true);
	cbbChantier.getStore().addStoreListener(new StoreListener<ChantierModel>() {
	    @Override
	    public void storeAdd(StoreEvent<ChantierModel> se) {
		cbbChantier.setValue(storeChantier.getAt(0));

	    }
	});
	cbbChantier.enableEvents(true);
	layoutContainer_1.add(cbbChantier, new com.extjs.gxt.ui.client.widget.layout.ColumnData(0.80));
	cbbChantier.setFieldLabel("New ComboBox");
	fieldSet.add(layoutContainer_1, new RowData(RootPanel.get().getOffsetWidth() / 2 - 50, 30.0, new Margins()));
	fieldSet.add(gridUserInChantier);
	fieldSet.add(new Html("&nbsp;"));
	fieldSet.add(layoutContainer);

	layoutChantier.add(fieldSet);
	return fieldSet;
    }

    public GridCellRenderer<UtilisateurGrpModel> deleteButton() {
	GridCellRenderer<UtilisateurGrpModel> deleteBtn = new GridCellRenderer<UtilisateurGrpModel>() {

	    @Override
	    public Object render(final UtilisateurGrpModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<UtilisateurGrpModel> store, Grid<UtilisateurGrpModel> grid) {
		Image img = new Image("./images/supprimer.png");
		img.setTitle(messages.delete());
		img.addClickHandler(new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent arg0) {
			if (userStore.getModels().size() > 1) {
			    gridAdmin.getStore().remove(model);
			    listUserAdminDelete.add(model);
			} else {
			    MessageBox.alert("", messages.adminDeletedError(), null);
			}
			users1.add(model);
		    }
		});
		return img;

	    }
	};
	return deleteBtn;
    }

    public GridCellRenderer<UtilisateurGrpModel> deleteButtonGridRole() {
	GridCellRenderer<UtilisateurGrpModel> deleteBtn = new GridCellRenderer<UtilisateurGrpModel>() {
	    @Override
	    public Object render(final UtilisateurGrpModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<UtilisateurGrpModel> store, Grid<UtilisateurGrpModel> grid) {
		Image img = new Image("./images/supprimer.png");
		img.setTitle(messages.delete());
		img.addClickHandler(new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent arg0) {
			if (storeRole.getModels().size() > 1) {
			    gridUserInChantier.getStore().remove(model);
			    listUserDeleteInChantier.add(model);
			    MessageBox.alert("Alert", messages.userDeleted(), null);
			} else {
			    MessageBox.alert("", messages.userDeletedError(), null);
			}
		    }
		});
		return img;
	    }
	};
	return deleteBtn;
    }

    private boolean isExistIdentifiant(String identifant) {
	boolean added = true;
	for (UtilisateurGrpModel model : gridAdmin.getStore().getModels()) {
	    if (model.getIdentifiant().equalsIgnoreCase(identifant)) {
		added = false;
	    }
	}
	return added;
    }

    private boolean isExistIdentifiantGridRole(String identifant) {
	boolean added = true;
	for (UtilisateurGrpModel model : gridUserInChantier.getStore().getModels()) {
	    if (model.getIdentifiant().equalsIgnoreCase(identifant)) {
		added = false;
	    }
	}
	return added;
    }

    public void loadAdminFieldset() {
	service.findUserAdmin(new AsyncCallback<List<UtilisateurGrpModel>>() {

	    @Override
	    public void onFailure(Throwable arg0) {
	    }

	    @Override
	    public void onSuccess(List<UtilisateurGrpModel> results) {
		if (results != null && results.size() > 0) {
		    java.util.Collections.sort(results, results.get(0));
		    userStore.add(results);
		    for (UtilisateurGrpModel user : results) {
			listUserBefore.add(user);
		    }
		}
	    }
	});
    }

    public void loadChantierToComboBox() {
	serviceChantier.findAll(new AsyncCallback<List<ChantierModel>>() {
	    @Override
	    public void onFailure(Throwable arg0) {
	    }

	    @Override
	    public void onSuccess(List<ChantierModel> result) {
		storeChantier.add(result);
		storeChange.setSortField("identifiant");
	    }
	});
    }
}
