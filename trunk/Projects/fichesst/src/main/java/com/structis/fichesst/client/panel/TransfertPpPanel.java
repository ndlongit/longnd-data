package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.structis.fichesst.client.ecran.SyntheseEcran;
import com.structis.fichesst.client.service.ClientChantierService;
import com.structis.fichesst.client.service.ClientChantierServiceAsync;
import com.structis.fichesst.client.service.ClientLigTransfertppService;
import com.structis.fichesst.client.service.ClientLigTransfertppServiceAsync;
import com.structis.fichesst.client.service.ClientRefTransfertppService;
import com.structis.fichesst.client.service.ClientRefTransfertppServiceAsync;
import com.structis.fichesst.client.service.ClientRefTypeBudjService;
import com.structis.fichesst.client.service.ClientRefTypeBudjServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.shared.dto.AbstractDto;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.LigTransfertppModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.SimpleDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class TransfertPpPanel extends AbstractPanel {

    public HTML addFicheST;

    Button btnRetour;

    private ColumnConfig column;

    private ColumnConfig column1;

    @SuppressWarnings("rawtypes")
    SimpleComboBox combo;

    SimpleComboBox<String> comboDeVers;

    private final FlexTable flexTable;

    private final FormPanel formDetails;

    private final FieldSet fsTransfertPp;

    private EditorGrid<LigTransfertppModel> gridligModel;

    private Grid<LigTransfertppModel> gridligTotal;

    private final FieldSet inforFieldset;

    Boolean isContributeur = false;

    boolean isSave = false;

    private final Label labelChantier;

    private final Label labelRefTransfertpp;

    private List<LigTransfertppModel> ligModels = new ArrayList<LigTransfertppModel>();

    private final List<LigTransfertppModel> ligModelsBefore = new ArrayList<LigTransfertppModel>();

    private final List<SimpleDto> listBudj = new ArrayList<SimpleDto>();

    private final HTML saveButton;

    final ClientRefTypeBudjServiceAsync service = GWT.create(ClientRefTypeBudjService.class);

    final ClientChantierServiceAsync serviceChantier = GWT.create(ClientChantierService.class);

    final ClientLigTransfertppServiceAsync serviceLig = GWT.create(ClientLigTransfertppService.class);

    final ClientRefTransfertppServiceAsync serviceRefTransfert = GWT.create(ClientRefTransfertppService.class);

    private ListStore<SimpleDto> storeBudj;

    GroupingStore<LigTransfertppModel> storeLig;

    ListStore<LigTransfertppModel> storeTotal;

    private TextField<String> textLot1;

    private TextField<String> textLot2;

    public TransfertPpPanel(final ChantierModel chantier, final Integer transfertPpId, RoleModel role, UtilisateurGrpModel user) {
	loadBudj();
	loadLig(chantier, transfertPpId, role, user);
	createDetailsTransferPp(role, user);
	createTotalPP();
	inforFieldset = new FieldSet();
	inforFieldset.setHeading("INFORMATION GENERALES");
	LayoutContainer layoutContainer_0 = new LayoutContainer();
	FormLayout fl_layoutContainer_0 = new FormLayout();
	fl_layoutContainer_0.setLabelWidth(70);
	layoutContainer_0.setLayout(new TableLayout(2));
	TableData col1 = new TableData();
	col1.setWidth("600px");
	TableData col2 = new TableData();
	col2.setWidth("600px");
	labelChantier = new Label();
	labelChantier.setText("Chantier:" + chantier.getNom());
	labelRefTransfertpp = new Label();
	labelRefTransfertpp.setVisible(false);
	layoutContainer_0.add(labelChantier, col1);
	layoutContainer_0.add(labelRefTransfertpp, col2);
	setDefaultBackgroundColor(layoutContainer_0);
	setDefaultBackgroundColor(inforFieldset);
	inforFieldset.add(layoutContainer_0);
	formDetails = new CustomFormPanel();
	formDetails.add(inforFieldset);
	fsTransfertPp = new FieldSet();
	fsTransfertPp.setHeading("TRANSFERTS PP");
	fsTransfertPp.setLayout(new RowLayout(Orientation.VERTICAL));
	LayoutContainer layoutContainer_10 = new LayoutContainer();
	layoutContainer_10.setLayout(new BorderLayout());
	LayoutContainer buttonPanel = new LayoutContainer();
	buttonPanel.setId("buttonPanel");
	buttonPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
	Label label = new Label();
	label.setWidth("135px");
	buttonPanel.add(label);
	HTML space3 = new HTML(SPACES_3);
	buttonPanel.add(space3);
	saveButton = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm(), false);
	saveButton.setStyleName("actionHTML");
	saveButton.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	saveButton.setVisible(true);
	saveButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent arg0) {
		Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
		    @Override
		    public void handleEvent(MessageBoxEvent be) {
			Button btt = be.getButtonClicked();
			if (Dialog.OK.equals(btt.getItemId())) {
			    if (isSave == false) {
				saveAction(chantier.getId(), transfertPpId);
				isSave = true;
			    }
			}
		    }
		};
		createConfirmBox(callback, messages.deleteConfirmTitle(), messages.confirmSupprimer());
	    }
	});
	buttonPanel.add(saveButton);
	layoutContainer_10.add(buttonPanel, new BorderLayoutData(LayoutRegion.EAST, 320.0f));
	layoutContainer_10.setHeight("30");
	fsTransfertPp.add(layoutContainer_10);
	LayoutContainer layoutContainer_11 = new LayoutContainer();
	Label label1 = new Label();
	label1.setText("Details des transferts PP:");
	Label label2 = new Label();
	label2.setText("Total des transferts PP");
	fsTransfertPp.add(label2);
	fsTransfertPp.add(gridligTotal);
	fsTransfertPp.add(new Html("<br/><br/><br/>"));
	fsTransfertPp.add(label1);
	fsTransfertPp.add(layoutContainer_11);
	addFicheST = new HTML("<img src='./images/ajouter.png'/> " + messages.addRow(), false);
	addFicheST.setStyleName("actionHTML2");
	addFicheST.setVisible(false);
	addFicheST.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		showAddDialog();
	    }

	    private void showAddDialog() {
		Dialog dialog = new Dialog();
		dialog.setHeading(messages.titlePopup());
		dialog.setLayout(new BorderLayout());
		dialog.setWidth(600);
		dialog.setHeight(350);
		dialog.setBodyBorder(false);
		dialog.setHideOnButtonClick(true);
		dialog.setButtons(Dialog.OKCANCEL);
		dialog.setButtonAlign(HorizontalAlignment.CENTER);
		dialog.setScrollMode(Scroll.AUTO);
		dialog.setModal(true);
		dialog.setBlinkModal(true);
		Button cancelButton = dialog.getButtonById(Dialog.CANCEL);
		cancelButton.setText(messages.cancel());
		Button okButton = dialog.getButtonById(Dialog.OK);
		okButton.setText(messages.ok());
		final AddTransfertPpForm addTransferPpForm = new AddTransfertPpForm();
		dialog.add(addTransferPpForm, new BorderLayoutData(LayoutRegion.CENTER));
		okButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
		    @Override
		    public void componentSelected(ButtonEvent ce) {
			LigTransfertppModel model = addTransferPpForm.getDataModel();
			gridligModel.getStore().add(model);
			isSave = false;
		    }
		});
		dialog.show();
	    }
	});
	layoutContainer_11.add(gridligModel);
	btnRetour = new Button();
	btnRetour.setId("btnValider");
	btnRetour.setText("Retour");
	flexTable = new FlexTable();
	addFicheST.setStyleName("actionHTML2");
	flexTable.setWidget(0, 0, addFicheST);
	fsTransfertPp.add(flexTable);
	formDetails.setButtonAlign(HorizontalAlignment.CENTER);
	formDetails.addButton(btnRetour);
	formDetails.add(fsTransfertPp);
	fsTransfertPp.setStyleAttribute("backgroundColor", "#EDF5EA");
	layoutContainer_10.setStyleAttribute("backgroundColor", "#EDF5EA");
	add(formDetails);
	addEventListener(chantier, transfertPpId, role, user);
    }

    public void addEventListener(final ChantierModel chantier, final Integer transfertPpId, final RoleModel role, final UtilisateurGrpModel user) {
	btnRetour.addListener(Events.OnClick, new Listener<DomEvent>() {
	    @Override
	    public void handleEvent(DomEvent be) {
		if ((user.getBadmin() != null && user.getBadmin() == true) || (role.getBcontributeur() != null && role.getBcontributeur() == true)) {
		    Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {

			@Override
			public void handleEvent(MessageBoxEvent be) {
			    Button btt = be.getButtonClicked();
			    if (Dialog.OK.equals(btt.getItemId())) {
				if (isSave == false) {
				    saveAction(chantier.getId(), transfertPpId);
				    GuiUtil.gotoEcran(new SyntheseEcran(chantier, role, user));
				} else {
				    GuiUtil.gotoEcran(new SyntheseEcran(chantier, role, user));
				}
			    }
			}
		    };
		    createConfirmBox(callback, messages.deleteConfirmTitle(), messages.confirmSupprimer());
		}

	    }
	});
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void createDetailsTransferPp(RoleModel role, UtilisateurGrpModel user) {
	storeBudj = new ListStore<SimpleDto>();
	textLot1 = createTextField(MAX_LENGTH_4);
	textLot1.setWidth(150);
	textLot2 = createTextField(MAX_LENGTH_4);
	combo = new SimpleComboBox();
	combo.setForceSelection(true);
	combo.setTriggerAction(TriggerAction.ALL);
	combo.setStore(storeBudj);
	combo.setDisplayField(SimpleDto.LABEL);
	combo.setValueField(SimpleDto.ID);
	CellEditor editor = new CellEditor(combo) {
	};
	comboDeVers = new SimpleComboBox();
	comboDeVers.add("de");
	comboDeVers.add("vers");
	comboDeVers.setForceSelection(true);
	comboDeVers.setTriggerAction(TriggerAction.ALL);
	CellEditor editor1 = new CellEditor(comboDeVers) {
	    @Override
	    public Object postProcessValue(Object value) {
		if (value == null) {
		    return value;
		}
		return ((ModelData) value).get("value");
	    }

	    @Override
	    public Object preProcessValue(Object value) {
		if (value == null) {
		    return value;
		}
		return comboDeVers.findModel(value.toString());
	    }
	};
	List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
	@SuppressWarnings("unused")
	GridCellRenderer<AbstractDto> comboBoxRenderer = createComboBoxRenderer();
	GridCellRenderer<LigTransfertppModel> deleteBtn = deleteButton(user, role);
	column = new ColumnConfig();
	column.setFixed(true);
	column.setAlignment(HorizontalAlignment.CENTER);
	column.setSortable(false);
	column.setMenuDisabled(true);
	column.setResizable(false);
	column.setId("supprimer");
	column.setHeader("Supprimer");
	column.setWidth(66);
	column.setRenderer(deleteBtn);
	configs.add(column);
	column = new ColumnConfig();
	column.setAlignment(HorizontalAlignment.CENTER);
	column.setWidth(100);
	column.setFixed(true);
	column.setSortable(false);
	column.setMenuDisabled(true);
	column.setResizable(false);
	column.setId(LigTransfertppModel.TYPE);
	column.setHeader("Type");
	column.setEditor(editor);
	column.setRenderer(new GridCellRenderer<AbstractDto>() {

	    @Override
	    public Object render(AbstractDto model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<AbstractDto> store, Grid<AbstractDto> grid) {
		SimpleDto value = (SimpleDto) model.get(property);
		if (value == null) {
		    return null;
		} else {
		    return value.getLabel();
		}
	    }
	});
	configs.add(column);
	column = new ColumnConfig();
	column.setAlignment(HorizontalAlignment.CENTER);
	column.setWidth(225);
	column.setId(LigTransfertppModel.LOT1);
	column.setEditor(new CellEditor(textLot1));

	column.setHeader("Lot1");
	column.setSortable(false);
	column.setMenuDisabled(true);
	column.setResizable(true);
	configs.add(column);
	column = new ColumnConfig();
	column.setAlignment(HorizontalAlignment.CENTER);
	column.setWidth(80);
	column.setId(LigTransfertppModel.DEVERS);
	column.setHeader("De/Vers");
	column.setFixed(true);
	column.setSortable(false);
	column.setMenuDisabled(true);
	column.setResizable(true);
	column.setEditor(editor1);
	configs.add(column);

	column = new ColumnConfig();
	column.setAlignment(HorizontalAlignment.CENTER);
	column.setId(LigTransfertppModel.LOT2);
	column.setEditor(new CellEditor(textLot2));
	column.setAlignment(HorizontalAlignment.CENTER);
	column.setHeader("Lot2");
	column.setSortable(false);
	column.setMenuDisabled(true);
	column.setResizable(true);
	column.setWidth(225);
	configs.add(column);
	column = new ColumnConfig();
	column.setId("montant");
	column.setHeader("Montant");
	column.setAlignment(HorizontalAlignment.CENTER);
	column.setSortable(false);
	column.setMenuDisabled(true);
	column.setResizable(true);
	column.setWidth(150);
	SummaryColumnConfig<Double> montant = new SummaryColumnConfig<Double>("montant", "Montant", 100);
	montant.setMenuDisabled(true);
	montant.setAlignment(HorizontalAlignment.CENTER);
	montant.setSummaryFormat(NumberFormat.getCurrencyFormat());
	montant.setSummaryType(new SummaryType<Double>() {
	    @Override
	    public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
		if (v == null) {
		    v = 0d;
		}
		LigTransfertppModel lig = (LigTransfertppModel) m;

		return ((Double) v).doubleValue() + (lig.getQuantite() * lig.getPu());
	    }

	});
	montant.setRenderer(new GridCellRenderer<LigTransfertppModel>() {
	    @Override
	    public String render(LigTransfertppModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<LigTransfertppModel> store, Grid<LigTransfertppModel> grid) {
		LigTransfertppModel lig = model;
		if (lig.getDeVers().equalsIgnoreCase("vers")) {
		    return NumberFormat.getCurrencyFormat().format(lig.getPu() * lig.getQuantite() * (-1));
		} else {
		    return NumberFormat.getCurrencyFormat().format(lig.getPu() * lig.getQuantite());
		}

	    }
	});
	configs.add(montant);

	SummaryColumnConfig<Integer> quantity = new SummaryColumnConfig<Integer>(LigTransfertppModel.QUANTITY, "Quantite", 100);
	NumberField nfQuantity = createIntegerField(null, true);
	nfQuantity.setAutoValidate(true);
	nfQuantity.setAllowNegative(false);
	nfQuantity.setAllowDecimals(false);
	quantity.setSummaryType(new SummaryType<Integer>() {

	    @Override
	    public Integer render(Object v, ModelData m, String field, Map<String, Object> data) {
		if (v == null) {
		    v = 1;
		}
		return ((Integer) v).intValue();
	    }
	});
	CellEditor ce1 = new CellEditor(nfQuantity);
	ce1.setCancelOnEsc(true);
	quantity.setMenuDisabled(true);
	quantity.setAlignment(HorizontalAlignment.CENTER);
	quantity.setSortable(false);
	quantity.setEditor(ce1);
	quantity.setSummaryType(SummaryType.COUNT);

	configs.add(quantity);

	SummaryColumnConfig<Double> pu = new SummaryColumnConfig<Double>(LigTransfertppModel.PU, "Pu", 100);
	pu.setSortable(false);
	pu.setMenuDisabled(true);
	pu.setAlignment(HorizontalAlignment.CENTER);
	pu.setSummaryFormat(NumberFormat.getCurrencyFormat());
	pu.setNumberFormat(NumberFormat.getCurrencyFormat());
	pu.setSummaryType(new SummaryType<Double>() {

	    @Override
	    public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
		if (v == null) {
		    v = 0d;
		}
		return ((Double) v).doubleValue();
	    }
	});
	/* pu.setAlignment(HorizontalAlignment.RIGHT); */
	NumberField nf = new NumberField();
	nf.setAutoValidate(true);
	CellEditor ce = new CellEditor(nf);
	ce.setCancelOnEsc(true);
	pu.setEditor(ce);
	pu.setAlignment(HorizontalAlignment.CENTER);
	configs.add(pu);
	column = new ColumnConfig();
	/* column.setWidth(300); */
	column.setId(LigTransfertppModel.COMMENTAIRES);
	column.setHeader("Commentaire");
	column.setAlignment(HorizontalAlignment.LEFT);
	column.setFixed(true);
	column.setSortable(false);
	column.setMenuDisabled(true);
	column.setResizable(false);

	TextArea comment = new TextArea();
	comment.setHeight(100);
	comment.setMaxLength(MAX_LENGTH_5);
	column.setEditor(new CellEditor(comment));
	column.setRenderer(createTextAreaRenderer());
	configs.add(column);
	ColumnModel cm = new ColumnModel(configs);
	storeLig = new GroupingStore<LigTransfertppModel>();
	gridligModel = new EditorGrid<LigTransfertppModel>(storeLig, cm);
	gridligModel.setStripeRows(true);
	/* gridligModel.setWidth(1100); */
	// gridligModel.setEnabled(false);
	gridligModel.setHeight(230);
	gridligModel.setAutoWidth(true);
	gridligModel.setBorders(true);
	gridligModel.setAutoExpandColumn(LigTransfertppModel.COMMENTAIRES);
	gridligModel.setAutoExpandMax(700);
	gridligModel.setAutoExpandMin(150);
	gridligModel.setColumnReordering(true);
	gridligModel.setColumnLines(true);
	// WindowResizeBinder.bind(gridligModel);
	gridligModel.getStore().addStoreListener(new StoreListener<LigTransfertppModel>() {
	    @Override
	    public void handleEvent(StoreEvent<LigTransfertppModel> e) {
		double totalObjPositive = 0.0;
		double totalObjNegative = 0.0;
		double amountObj = 0.0;
		double totalDeversPositive = 0.0;
		double totalDeversNegative = 0.0;
		double amountDevers = 0.0;
		double totalTsPositive = 0.0;
		double totalTsNegative = 0.0;
		double amountTs = 0.0;
		double totalRDPositive = 0.0;
		double totalRDNegative = 0.0;
		double amountRD = 0.0;
		List<LigTransfertppModel> ligModels = gridligModel.getStore().getModels();

		for (LigTransfertppModel lig : ligModels) {
		    if (lig.getRefTypeBudjConf().getId() == 1 && lig.getDeVers().equalsIgnoreCase("vers")) {
			totalObjNegative += lig.getPu() * lig.getQuantite() * (-1);
		    }
		    if (lig.getRefTypeBudjConf().getId() == 1 && lig.getDeVers().equalsIgnoreCase("de")) {
			totalObjPositive += lig.getPu() * lig.getQuantite();
		    }
		    if (lig.getRefTypeBudjConf().getId() == 2 && lig.getDeVers().equalsIgnoreCase("vers")) {
			totalDeversNegative += lig.getPu() * lig.getQuantite() * (-1);
		    }
		    if (lig.getRefTypeBudjConf().getId() == 2 && lig.getDeVers().equalsIgnoreCase("de")) {
			totalDeversPositive += lig.getPu() * lig.getQuantite();
		    }
		    if (lig.getRefTypeBudjConf().getId() == 3 && lig.getDeVers().equalsIgnoreCase("vers")) {
			totalRDNegative += lig.getPu() * lig.getQuantite() * (-1);
		    }
		    if (lig.getRefTypeBudjConf().getId() == 3 && lig.getDeVers().equalsIgnoreCase("de")) {
			totalRDPositive += lig.getPu() * lig.getQuantite();
		    }
		    if (lig.getRefTypeBudjConf().getId() == 4 && lig.getDeVers().equalsIgnoreCase("vers")) {
			totalTsNegative += lig.getPu() * lig.getQuantite() * (-1);
		    }
		    if (lig.getRefTypeBudjConf().getId() == 4 && lig.getDeVers().equalsIgnoreCase("de")) {
			totalTsPositive += lig.getPu() * lig.getQuantite();
		    }

		}
		amountObj = totalObjNegative + totalObjPositive;
		amountDevers = totalDeversNegative + totalDeversPositive;
		amountRD = totalRDNegative + totalRDPositive;
		amountTs = totalTsNegative + totalTsPositive;
		LigTransfertppModel ligModel = new LigTransfertppModel();
		ligModel.setTotalDevers(amountDevers);
		ligModel.setTotalObj(amountObj);
		ligModel.setTotalTs(amountTs);
		ligModel.setTotalRd(amountRD);
		storeTotal.removeAll();
		// gridligTotal.getStore().add(ligModel);
		storeTotal.add(ligModel);
	    }
	});
    }

    public void createTotalPP() {
	List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
	column1 = new ColumnConfig();
	column1.setId(LigTransfertppModel.TotalObj);
	column1.setHeader("&nbsp;&nbsp;&nbsp;&nbsp" + "Obj" + "<br/>" + "(Objectif)");
	column1.setAlignment(HorizontalAlignment.CENTER);
	column1.setFixed(true);
	column1.setSortable(false);
	column1.setMenuDisabled(true);
	column1.setResizable(false);
	column1.setWidth(150);
	column1.setRenderer(new GridCellRenderer<LigTransfertppModel>() {
	    @Override
	    public String render(LigTransfertppModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<LigTransfertppModel> store, Grid<LigTransfertppModel> grid) {
		return model.get(property) + "€";
	    }
	});
	configs.add(column1);
	column1 = new ColumnConfig();
	column1.setId(LigTransfertppModel.TotalDeVers);
	column1.setHeader("&nbsp;&nbsp;&nbsp" + "+/-" + "<br/>" + "(Transferts)");
	column1.setAlignment(HorizontalAlignment.CENTER);
	column1.setFixed(true);
	column1.setSortable(false);
	column1.setMenuDisabled(true);
	column1.setResizable(false);
	column1.setWidth(150);
	column1.setRenderer(new GridCellRenderer<LigTransfertppModel>() {
	    @Override
	    public String render(LigTransfertppModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<LigTransfertppModel> store, Grid<LigTransfertppModel> grid) {
		return model.get(property) + "€";
	    }
	});
	configs.add(column1);
	column1 = new ColumnConfig();
	column1.setId(LigTransfertppModel.TotalRD);
	column1.setHeader("&nbsp;&nbsp;&nbsp;&nbsp" + "RD" + "<br/>" + "(Recente Decenale)");
	column1.setAlignment(HorizontalAlignment.CENTER);
	column1.setFixed(true);
	column1.setFixed(true);
	column1.setSortable(false);
	column1.setMenuDisabled(true);
	column1.setResizable(false);
	column1.setWidth(150);
	column1.setRenderer(new GridCellRenderer<LigTransfertppModel>() {
	    @Override
	    public String render(LigTransfertppModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<LigTransfertppModel> store, Grid<LigTransfertppModel> grid) {
		return model.get(property) + "€";
	    }
	});
	configs.add(column1);
	column1 = new ColumnConfig();
	column1.setId(LigTransfertppModel.TotalTS);
	column1.setHeader("&nbsp;&nbsp;&nbsp" + "TS" + "<br/>" + "(Tri supp)");
	column1.setAlignment(HorizontalAlignment.CENTER);
	column1.setFixed(true);
	column1.setFixed(true);
	column1.setSortable(false);
	column1.setMenuDisabled(true);
	column1.setResizable(false);
	column1.setWidth(150);
	column1.setRenderer(new GridCellRenderer<LigTransfertppModel>() {
	    @Override
	    public String render(LigTransfertppModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<LigTransfertppModel> store, Grid<LigTransfertppModel> grid) {
		return model.get(property) + "€";
	    }
	});
	configs.add(column1);
	storeTotal = new ListStore<LigTransfertppModel>();
	ColumnModel cm = new ColumnModel(configs);
	gridligTotal = new Grid<LigTransfertppModel>(storeTotal, cm);
	gridligTotal.setAutoHeight(true);
	gridligTotal.setWidth(610);
	gridligTotal.setColumnLines(true);
	gridligTotal.setAutoExpandColumn(LigTransfertppModel.TotalTS);
    }

    public GridCellRenderer<LigTransfertppModel> deleteButton(final UtilisateurGrpModel user, final RoleModel role) {
	GridCellRenderer<LigTransfertppModel> deleteBtn = new GridCellRenderer<LigTransfertppModel>() {
	    @Override
	    public Object render(final LigTransfertppModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<LigTransfertppModel> store, Grid<LigTransfertppModel> grid) {

		Image img = new Image("./images/supprimer.png");
		img.setTitle("Supprimer");
		if ((user.getBadmin() != null && user.getBadmin() == true) || (role.getBcontributeur() != null && role.getBcontributeur() == true)) {
		    img.setVisible(true);

		} else {
		    img.setVisible(false);
		}
		img.addClickHandler(new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent arg0) {
			Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {

			    @Override
			    public void handleEvent(MessageBoxEvent be) {
				Button btt = be.getButtonClicked();
				if (Dialog.OK.equals(btt.getItemId())) {
				    isSave = false;
				    gridligModel.getStore().remove(model);
				}
			    }
			};
			createConfirmBox(callback, messages.deleteConfirmTitle(), messages.rowDelete());
		    }
		});
		return img;
	    }
	};
	return deleteBtn;
    }

    public void loadBudj() {
	service.findAll(new AsyncCallback<List<SimpleDto>>() {

	    @Override
	    public void onFailure(Throwable arg0) {
	    }

	    @Override
	    public void onSuccess(List<SimpleDto> arg0) {
		storeBudj.add(arg0);
		listBudj.addAll(arg0);
	    }
	});
    }

    public void loadLig(ChantierModel chantier, Integer transfertPpId, final RoleModel role, final UtilisateurGrpModel user) {
	serviceRefTransfert.findById(transfertPpId, new AsyncCallback<SimpleDto>() {
	    @Override
	    public void onFailure(Throwable arg0) {
	    }

	    @Override
	    public void onSuccess(SimpleDto arg0) {
		labelRefTransfertpp.setText("Libelle:" + arg0.getLabel());
		labelRefTransfertpp.setVisible(true);
	    }
	});
	serviceLig.findByID(transfertPpId, chantier.getId(), new AsyncCallback<List<LigTransfertppModel>>() {
	    @Override
	    public void onFailure(Throwable arg0) {
	    }

	    @Override
	    public void onSuccess(List<LigTransfertppModel> arg0) {
		storeLig.add(arg0);
		ligModelsBefore.addAll(arg0);
		if (role != null && (role.getBlecteur() != false) && (role.getBcontributeur() == false || role.getBcontributeur() == null)
			&& ((user.getBadmin() != null && user.getBadmin() == false) || user.getBadmin() == null)) {
		    btnRetour.setVisible(false);
		}
		if ((role != null && role.getBcontributeur() == true) || (user.getBadmin() != null && user.getBadmin() == true)) {
		    gridligModel.setEnabled(true);
		    fsTransfertPp.setEnabled(true);
		    addFicheST.setVisible(true);

		} else {
		    gridligModel.removeAllListeners();
		    saveButton.setVisible(false);
		}
	    }
	});
    }

    public void onLoadPanel() {
    }

    private void saveAction(Integer idChantier, Integer transfertPpId) {
	ligModels = storeLig.getModels();
	serviceLig.saveAllLig(ligModels, idChantier, transfertPpId, new AsyncCallback<Void>() {
	    @Override
	    public void onFailure(Throwable arg0) {
	    }

	    @Override
	    public void onSuccess(Void arg0) {
	    }
	});
	if (ligModelsBefore.size() > 0) {
	    serviceLig.deleteLig(ligModelsBefore, idChantier, transfertPpId, new AsyncCallback<Void>() {

		@Override
		public void onFailure(Throwable arg0) {
		}

		@Override
		public void onSuccess(Void arg0) {
		}
	    });
	}
    }
}
