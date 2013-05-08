package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.HideMode;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.AggregationRowConfig;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupSummaryView;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.structis.fichesst.client.constant.ConstantClient;
import com.structis.fichesst.client.event.EtatAvancementEvent;
import com.structis.fichesst.client.event.ExportGestionPanelEvent;
import com.structis.fichesst.client.event.GestionGridUpdateEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.handler.ExportGestionPanelHandler;
import com.structis.fichesst.client.service.ClientRefTypeMchAvServiceAsync;
import com.structis.fichesst.client.service.ClientStatusServiceAsync;
import com.structis.fichesst.client.service.ClientTypeServiceAsync;
import com.structis.fichesst.client.util.NameValuePair;
import com.structis.fichesst.client.util.ReportUtil;
import com.structis.fichesst.client.widget.ComboToolTipTemplate;
import com.structis.fichesst.client.widget.CustomComboBox;
import com.structis.fichesst.client.widget.CustomEditorGrid;
import com.structis.fichesst.client.widget.CustomFieldSet;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.client.widget.TextAreaEditor;
import com.structis.fichesst.shared.dto.AbstractDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.LotTypeDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.SimpleDto;
import com.structis.fichesst.shared.dto.SimpleFigDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.util.Constants;

public class GestionPanel extends AbstractPanel {

    private static final int WIDTH_3 = 67;

    private static final int WIDTH_4 = WIDTH_3 - 10;

    private static final int[] COLUMNS_WIDTH_2 = { DELETE_BUTTON_WIDTH, 35, WIDTH_3, WIDTH_3 + 10, WIDTH_3, 80, WIDTH_3, 50, WIDTH_3, WIDTH_3, WIDTH_3, 60, WIDTH_4, WIDTH_4, 50, WIDTH_4, WIDTH_3,
	    WIDTH_3, 50 };

    private CustomEditorGrid<GestionDto> gestionGrid;

    double totalObj = 0.0;
    double totalTF = 0.0;
    double totalTS = 0.0;
    double totalRD = 0.0;

    String fdcField = "";

    String budgetInitial;
    String dernierPoint;
    String dernier;

    @SuppressWarnings("rawtypes")
    private final Grid inforGrid;
    private HTML saveButton = null;
    private final HTML addFicheST;
    private final FormPanel summary;
    private final RoleModel role;
    private final UtilisateurGrpModel user;

    public GestionPanel(SimpleEventBus b, RoleModel roleModel, UtilisateurGrpModel utilisateurGrpModel) {
	super();
	this.bus = b;
	this.role = roleModel;
	this.user = utilisateurGrpModel;
	FieldSet gestionFieldSet = new CustomFieldSet();
	gestionFieldSet.setCollapsible(true);
	gestionFieldSet.setLayout(new RowLayout(Orientation.VERTICAL));

	LayoutContainer layoutContainer_10 = new LayoutContainer();
	layoutContainer_10.setLayout(new BorderLayout());

	LayoutContainer buttonPanel = new LayoutContainer();
	buttonPanel.setId("buttonPanel");
	buttonPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));

	saveButton = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm(), false);
	saveButton.setStyleName("actionHTML");
	buttonPanel.add(saveButton);
	saveButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		bus.fireEvent(new SaveFicheStEvent());
	    }
	});

	buttonPanel.add(new HTML(LINKS_SPACE, false));
	bus.addHandler(ExportGestionPanelEvent.TYPE, new ExportGestionPanelHandler() {
	    @Override
	    public void onExport(ExportGestionPanelEvent event) {
		exportGestion();
	    }
	});

	HTML print = new HTML("<img src='./images/imprimer.png'/> " + messages.print(), false);
	print.setStyleName("actionHTML");
	print.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent arg0) {
		exportGestion();
	    }
	});
	buttonPanel.add(print);
	layoutContainer_10.add(buttonPanel, new BorderLayoutData(LayoutRegion.EAST, 350.0f));
	gestionFieldSet.add(layoutContainer_10);
	layoutContainer_10.setHeight("30");

	addFicheST = new HTML("<img src='./images/ajouter.png'/> " + messages.addRow(), false);

	LayoutContainer layoutContainer_11 = new LayoutContainer();

	createGestionGrid();

	layoutContainer_11.add(gestionGrid);

	gestionFieldSet.add(layoutContainer_11);

	LayoutContainer layoutContainer_12 = new LayoutContainer();
	TableLayout tl_layoutContainer_12 = new TableLayout(2);
	tl_layoutContainer_12.setWidth("100%");
	layoutContainer_12.setLayout(tl_layoutContainer_12);
	gestionFieldSet.add(layoutContainer_12);
	layoutContainer_12.setBorders(false);

	FlexTable ft1 = new FlexTable();
	int commonWidth = 265;
	ft1.setWidget(0, 0, new HTML(SPACES_4));

	addFicheST.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		showAddDialog();
	    }

	    private void showAddDialog() {
		final Dialog dialog = new Dialog();
		dialog.setHeading(messages.titlePopup());
		dialog.setLayout(new BorderLayout());
		dialog.setWidth(600);
		dialog.setHeight(550);
		dialog.setBodyBorder(false);
		dialog.setHideOnButtonClick(false);
		dialog.setButtons(Dialog.OKCANCEL);
		dialog.setButtonAlign(HorizontalAlignment.CENTER);
		dialog.setScrollMode(Scroll.AUTO);
		dialog.setModal(true);
		dialog.setBlinkModal(true);
		Button cancelButton = dialog.getButtonById(Dialog.CANCEL);
		cancelButton.setText(messages.cancel());
		Button okButton = dialog.getButtonById(Dialog.OK);
		okButton.setText(messages.ok());

		final AddGestionForm addGestionForm = new AddGestionForm();
		dialog.add(addGestionForm, new BorderLayoutData(LayoutRegion.CENTER));

		okButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
		    @Override
		    public void componentSelected(ButtonEvent ce) {
			if (addGestionForm.isValid()) {
			    GestionDto model = addGestionForm.getDataModel();
			    gestionGrid.getStore().add(model);

			    bus.fireEvent(new EtatAvancementEvent(gestionGrid.getStore().getModels(), null));
			    dialog.hide();
			}
		    }
		});

		cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
		    @Override
		    public void componentSelected(ButtonEvent ce) {
			dialog.hide();
		    }
		});

		dialog.show();
	    }
	});

	addFicheST.setStyleName("actionHTML2");
	ft1.setWidget(0, 1, addFicheST);
	TableData td_ft1 = new TableData();
	td_ft1.setVerticalAlign(VerticalAlignment.TOP);
	layoutContainer_12.add(ft1, td_ft1);
	ft1.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);

	LayoutContainer layoutContainer_13 = new LayoutContainer();

	TableLayout tl_layoutContainer_13 = new TableLayout(1);
	tl_layoutContainer_13.setCellHorizontalAlign(HorizontalAlignment.RIGHT);
	tl_layoutContainer_13.setWidth("100%");
	layoutContainer_13.setLayout(tl_layoutContainer_13);

	FlexTable ft2 = new FlexTable();
	ft2.setBorderWidth(2);
	inforGrid = createInforGrid();

	ft2.setWidget(0, 0, new HTML("<label>" + messages.dont() + ":<label> "));
	ft2.setWidget(0, 1, inforGrid);

	layoutContainer_13.add(ft2);
	ft2.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);

	summary = new CustomFormPanel();
	TableLayout tl_summary = new TableLayout(2);
	tl_summary.setBorder(1);
	summary.setLayout(tl_summary);

	TableData td_summary = new TableData();
	td_summary.setPadding(5);
	layoutContainer_13.add(summary, td_summary);
	HTML budgetLabel = new HTML(messages.budget());
	TableData td_budgetPanel = new TableData();
	td_budgetPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
	LayoutContainer budgetPanel = new LayoutContainer();
	budgetPanel.setStyleAttribute("background-color", HEADER_BACKGROUND_COLOR);
	budgetPanel.add(budgetLabel);
	summary.add(budgetPanel, td_budgetPanel);
	NumberField budget = createNumberField(null);
	budget.setName(FicheStDto.GEST_BUDGET_INITIAL);
	budget.setId("GESTIONAL_PANEL_BUDGET_ID");
	budget.setWidth(commonWidth);
	summary.add(budget);
	if (budget != null) {
	    budgetInitial = budget.getValue() + "";
	}
	HTML ecardDernierLabel = new HTML(messages.ecardDernier());

	TableData td_ecardDernierPanel = new TableData();
	td_ecardDernierPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
	LayoutContainer ecardDernierPanel = new LayoutContainer();
	ecardDernierPanel.setStyleAttribute("background-color", HEADER_BACKGROUND_COLOR);
	ecardDernierPanel.add(ecardDernierLabel);
	summary.add(ecardDernierPanel, td_ecardDernierPanel);
	NumberField ecardDernier = createNumberField(null);
	ecardDernier.setName(FicheStDto.GEST_ECART_DERNIER_PT);
	ecardDernier.setId("GESTIONAL_PANEL_ECARDDERNIER_ID");
	ecardDernier.setWidth(commonWidth);
	summary.add(ecardDernier);
	if (ecardDernier != null) {
	    dernierPoint = ecardDernier.getValue() + "";
	}
	HTML dateDernierLabel = new HTML(messages.dernierDate());

	LayoutContainer dateDernierPanel = new LayoutContainer();
	dateDernierPanel.setStyleAttribute("background-color", HEADER_BACKGROUND_COLOR);
	dateDernierPanel.add(dateDernierLabel);
	TableData td_dateDernierPanel = new TableData();
	td_dateDernierPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
	summary.add(dateDernierPanel, td_dateDernierPanel);
	DateField dateDernier = new DateField();
	dateDernier.setName(FicheStDto.GEST_DATE_DERNIER_PT);
	dateDernier.setId("GESTION_PANEL_DATEDERNIER_ID");
	dateDernier.setWidth(commonWidth);
	summary.add(dateDernier);

	if (dateDernier != null) {
	    dernier = dateDernier.getValue() + "";
	}

	layoutContainer_12.add(layoutContainer_13);
	gestionFieldSet.setHeading(messages.gestion());
	add(gestionFieldSet);

	// Set background color to green
	setDefaultBackgroundColor(layoutContainer_10);
	setDefaultBackgroundColor(gestionFieldSet);

	if (isAdminOrContributor(roleModel, utilisateurGrpModel)) {
	    saveButton.setVisible(true);
	    addFicheST.setVisible(true);
	    gestionGrid.getColumnModel().setHidden(0, false);
	} else {
	    saveButton.setVisible(false);
	    addFicheST.setVisible(false);
	    gestionGrid.getColumnModel().setHidden(0, true);
	}
    }

    private GridCellRenderer<GestionDto> createLockableTextAreaRenderer(final int fieldWidth, final int fieldHeight) {
		GridCellRenderer<GestionDto> textFieldRenderer = new GridCellRenderer<GestionDto>() {
			@Override
			public Object render(GestionDto model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<GestionDto> store, Grid<GestionDto> grid) {
				String value = (String) model.get(property);
				if(value == null) {
					value = "";
				}
				
//				TextArea field = new TextArea();
//				if( fieldWidth > 0 ) {
//					field.setWidth(fieldWidth);
//				}
//				if( fieldHeight > 0 ) {
//					field.setHeight(fieldHeight);
//				}
//				Boolean lock = model.getLock();
//				if( lock != null && lock )
//					field.disable();
//				else
//					field.enable();
//				if( user.getBadmin() != null && user.getBadmin() ) {
//					// field.enable();
//				}
//				else if( role != null && role.getBcontributeur() != null && role.getBcontributeur() ) {
//					if( lock != null && lock )
//						field.disable();
//					else
//						field.enable();
//				}
//				else
//					field.disable();
//				field.setValue(value);
				//		return field;
				
				return "<div style='text-align:left; display:inline-block; height:" + fieldHeight + "px; width: " + fieldWidth + "px'>" + value + "</div>";
			}
		};

	return textFieldRenderer;
    }

    @SuppressWarnings("unchecked")
    private void createGestionGrid() {
	String[] ids = { GestionDto.ID, GestionDto.LOCK, GestionDto.DEVIS, GestionDto.STATUT, GestionDto.LABEL, GestionDto.COMMENT, GestionDto.AMOUNT, GestionDto.MARCHE, GestionDto.TRAITE,
		GestionDto.ARRETE, GestionDto.NON_ARRETE, GestionDto.PROVISION, GestionDto.DEVIS_REFUSE, GestionDto.TOTAL_FDC, GestionDto.REEL_ACTIVITIVE, GestionDto.TYPE, GestionDto.LABEL2,
		GestionDto.AMOUNT2, GestionDto.ECART };
	String[] headers = { "<br><br>" + messages.del(), "<br><br>" + messages.freezeRow(), "<br><br>" + messages.devis(), "<br><br>" + messages.status(), "<br><br>" + messages.label(),
		"<br><br>" + messages.comment(), "<br><br>" + messages.amount(), "", "", messages.arrete() + " (&euro;)", messages.nonArrete(), "<br><br>" + messages.provision(),
		"<br>" + messages.devisRefuse() + " (&euro;)", "<br>" + messages.totalFDC(), "<br>" + messages.reelActivitive(), messages.type(), messages.label(), messages.amount(),
		"<br><br>" + messages.ecart() };
	HorizontalAlignment[] alignments = { HorizontalAlignment.CENTER };

	List<String> verticalTextColumn = Arrays.asList(GestionDto.AMOUNT, GestionDto.ARRETE, GestionDto.NON_ARRETE, GestionDto.DEVIS_REFUSE, GestionDto.TOTAL_FDC, GestionDto.REEL_ACTIVITIVE,
		GestionDto.AMOUNT2, GestionDto.ECART);

	// Total summary columns
	List<String> totalSummaryColumns = Arrays.asList(GestionDto.AMOUNT, GestionDto.TRAITE, GestionDto.ARRETE, GestionDto.NON_ARRETE, GestionDto.PROVISION, GestionDto.DEVIS_REFUSE,
		GestionDto.TOTAL_FDC, GestionDto.REEL_ACTIVITIVE, GestionDto.AMOUNT2, GestionDto.ECART);

	AggregationRowConfig<GestionDto> totalSummary = new AggregationRowConfig<GestionDto>();
	totalSummary.setHtml(GestionDto.COMMENT, messages.rexAmount());

	List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
	for (int i = 0; i < ids.length; i++) {
	    final String columnId = ids[i];
	    @SuppressWarnings("rawtypes")
	    SummaryColumnConfig column = new SummaryColumnConfig(columnId, headers[i], COLUMNS_WIDTH_2[i]);
	    column.setAlignment(alignments[0]);
	    column.setMenuDisabled(true);
	    columns.add(column);
	    if (totalSummaryColumns.contains(columnId)) {

		// Summary columns
		column.setSummaryType(SummaryType.SUM);
		column.setSummaryFormat(NumberFormat.getCurrencyFormat());

		// Total summary columns
		totalSummary.setSummaryType(columnId, SummaryType.SUM);
		totalSummary.setSummaryFormat(columnId, NumberFormat.getCurrencyFormat());
	    }

	    if (verticalTextColumn.contains(columnId)) {
		column.setHeader(column.getHeader().replaceAll(" ", "<br>"));
	    }
	}
	columns.get(0).setRenderer(createDeleteButtonRenderer());
	GridCellRenderer<AbstractDto> comboBoxRenderer = createComboBoxRenderer();
	final ComboBox<SimpleFigDto> combobox0 = new CustomComboBox<SimpleFigDto>();
	final ListStore<SimpleFigDto> store0 = new ListStore<SimpleFigDto>();
	store0.add(buildNonOuiOptions());
	combobox0.setStore(store0);
	@SuppressWarnings("rawtypes")
	GridCellRenderer gridCell = new GridCellRenderer() {
	    @Override
	    public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
		return buildTypeColumn(model);
	    }
	};
	columns.get(1).setRenderer(gridCell);
	final CellEditor editor = new CellEditor(combobox0) {
	    @Override
	    public Object preProcessValue(Object value) {
		if (value == null) {
		    return value;
		}
		return store0.findModel(value.toString());
	    }

	    @Override
	    public Object postProcessValue(Object value) {
		if (value == null) {
		    return value;
		}
		return combobox0.getValue().getValue();
	    }
	};
	columns.get(1).setEditor(editor);

	GridCellRenderer<AbstractDto> textFieldRenderer = createTextFieldRenderer(WIDTH_3 - PADDING);
	columns.get(2).setRenderer(textFieldRenderer);
	columns.get(2).setEditor(new CellEditor(createTextField(MAX_LENGTH_1)));

	columns.get(4).setRenderer(textFieldRenderer);
	columns.get(4).setEditor(new CellEditor(new TextField<Object>()));

	ColumnConfig commentColumn = columns.get(5);
	GridCellRenderer<GestionDto> textAreaRenderer = createLockableTextAreaRenderer(commentColumn.getWidth() - PADDING, 33);
	
	//Comment column
	commentColumn.setRenderer(textAreaRenderer);
	CellEditor editor2 = new TextAreaEditor(new TextArea());
	commentColumn.setEditor(editor2);
	editor2.setCompleteOnEnter(false);
	
	columns.get(16).setRenderer(textFieldRenderer);
	setNumberFormatColumns(columns);
	columns.get(3).setRenderer(comboBoxRenderer);

	final ListStore<SimpleDto> statusStore = new ListStore<SimpleDto>();
	ClientStatusServiceAsync statusService = ClientStatusServiceAsync.Util.getInstance();
	statusService.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
	    @Override
	    public void onSuccess(final List<SimpleDto> results) {
		statusStore.add(results);
	    }
	});

	ComboBox<SimpleDto> combobox1 = new CustomComboBox<SimpleDto>();
	combobox1.setStore(statusStore);
	combobox1.setTemplate(ComboToolTipTemplate.getStatutTemplate());
	columns.get(3).setEditor(new CellEditor(combobox1));

	final ListStore<SimpleDto> store2 = new ListStore<SimpleDto>();
	ClientRefTypeMchAvServiceAsync service = ClientRefTypeMchAvServiceAsync.Util.getInstance();
	service.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
	    @Override
	    public void onSuccess(final List<SimpleDto> results) {
		store2.add(results);
	    }
	});

	ComboBox<SimpleDto> combobox2 = new CustomComboBox<SimpleDto>();
	combobox2.setStore(store2);
	columns.get(7).setEditor(new CellEditor(combobox2));
	columns.get(7).setRenderer(comboBoxRenderer);

	columns.get(15).setRenderer(comboBoxRenderer);

	final ListStore<SimpleDto> store3 = new ListStore<SimpleDto>();
	ClientTypeServiceAsync typeService = ClientTypeServiceAsync.Util.getInstance();
	typeService.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
	    @Override
	    public void onSuccess(final List<SimpleDto> results) {
		store3.add(results);
	    }
	});
	ComboBox<SimpleDto> combobox3 = new CustomComboBox<SimpleDto>();
	combobox3.setStore(store3);
	columns.get(15).setEditor(new CellEditor(combobox3));

	ColumnModel cm = new ColumnModel(columns);
	cm.addHeaderGroup(0, 7, new HeaderGroupConfig(messages.traite().toUpperCase(), 1, 2));
	cm.addHeaderGroup(0, 9, new HeaderGroupConfig(messages.traiter().toUpperCase(), 1, 2));
	cm.addHeaderGroup(0, 15, new HeaderGroupConfig(messages.budgetConforme().toUpperCase(), 1, 3));
	cm.addHeaderGroup(1, 7, new HeaderGroupConfig(messages.marcheAndAvenants(), 1, 2));
	cm.addAggregationRow(totalSummary);

	ColumnConfig label2Col = cm.getColumnById(GestionDto.LABEL2);
	label2Col.setEditor(new CellEditor(createTextField(MAX_LENGTH_1)));

	GroupingStore<GestionDto> store = new GroupingStore<GestionDto>();
	store.groupBy(GestionDto.MARCHE);

	GroupSummaryView view = new GroupSummaryView() {
	    @Override
	    protected void refreshSummary(String groupField, String group) {
		if (GestionDto.MARCHE.equalsIgnoreCase(groupField)) {
		    return;
		}

		super.refreshSummary(groupField, group);
	    }
	};

	view.setGroupRenderer(new GridGroupRenderer() {
	    @Override
	    public String render(GroupColumnData data) {
		SimpleDto obj = (SimpleDto) data.gvalue;
		if (obj == null) {
		    return "";
		}
		return obj.getLabel();
	    }
	});

	view.setShowGroupedColumn(true);
	view.setForceFit(true);

	gestionGrid = new CustomEditorGrid<GestionDto>(store, cm, role, user);
	gestionGrid.setAutoHeight(true);
	gestionGrid.setHideMode(HideMode.OFFSETS);
	gestionGrid.setView(view);
	gestionGrid.getStore().addStoreListener(new StoreListener<GestionDto>() {
	    @Override
	    public void handleEvent(StoreEvent<GestionDto> e) {
		gestionGrid.getView().refresh(false);
		List<GestionDto> models = gestionGrid.getStore().getModels();
		Collections.sort(models);
		totalObj = 0.0;
		totalTF = 0.0;
		totalTS = 0.0;
		totalRD = 0.0;
		for (GestionDto gestionDto : models) {
		    Integer typeId = gestionDto.getType() != null ? gestionDto.getType().getId() : -1;
		    double value = gestionDto.getAmount2();
		    switch (typeId) {
		    case 1:
			totalObj += value;
			break;
		    case 2:
			totalTF += value;
			break;
		    case 3:
			totalTS += value;
			break;
		    case 4:
			totalRD += value;
			break;
		    default:
			break;
		    }
		}

		List<SimpleDto> newModels = createInforModelList(totalObj, totalTF, totalTS, totalRD);

		inforGrid.getStore().removeAll();
		inforGrid.getStore().add(newModels);

		bus.fireEvent(new EtatAvancementEvent(gestionGrid.getStore().getModels(), null));
		// update other grids as well
		bus.fireEvent(new GestionGridUpdateEvent(models));
	    }
	});
    }

    private Object buildTypeColumn(final ModelData model) {
	Boolean lock = model.get(GestionDto.LOCK);
	if (lock == null || !lock)
	    return messages.no();
	return messages.yes();
    }

    private void setNumberFormatColumns(List<ColumnConfig> columns) {
	formatColumn(columns.get(6), false);
	formatColumn(columns.get(8), false);
	formatColumn(columns.get(9), false);
	formatColumn(columns.get(10), false);
	formatColumn(columns.get(11), false);
	formatColumn(columns.get(12), false);
	formatColumn(columns.get(14), true);
	formatColumn(columns.get(17), false);
    }

    private void formatColumn(ColumnConfig column, boolean percentage) {
	int width = column.getWidth();
	GridCellRenderer<AbstractDto> numberFieldRenderer = createNumberRenderer(width - PADDING);
	column.setRenderer(numberFieldRenderer);
	column.setNumberFormat(NumberFormat.getFormat(NUMBER_FORMAT));
	NumberField numberField = createNumberField(null);
	if (percentage) {
	    numberField.setMinValue(0);
	    numberField.setMaxValue(100);
	}
	numberField.setWidth(width - PADDING);
	CellEditor cellEditor = new CellEditor(numberField);
	cellEditor.setAlignment("right");
	column.setEditor(cellEditor);
    }

    @SuppressWarnings("rawtypes")
    private Grid createInforGrid() {
	List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
	ColumnConfig column = new ColumnConfig();
	column.setId(SimpleDto.LABEL);
	column.setMenuDisabled(true);
	column.setWidth(160);
	column.setAlignment(HorizontalAlignment.LEFT);
	column.setNumberFormat(NumberFormat.getCurrencyFormat());
	columns.add(column);

	column = new ColumnConfig();
	column.setId(SimpleDto.VALUE);
	column.setMenuDisabled(true);
	column.setWidth(200);
	column.setAlignment(HorizontalAlignment.RIGHT);
	columns.add(column);
	ColumnModel cm = new ColumnModel(columns);
	ListStore<SimpleDto> store = new ListStore<SimpleDto>();
	store.add(createInforModelList(0.0, 0.0, 0.0, 0.0));
	Grid grid = new Grid<SimpleDto>(store, cm);
	grid.setColumnLines(true);
	grid.setStripeRows(true);
	grid.setHideHeaders(true);

	return grid;
    }

    private static List<SimpleDto> createInforModelList(double totalObj, double totalTF, double totalTS, double totalRD) {
	NumberFormat numberFormat = NumberFormat.getFormat(Constants.NUMBER_FORMAT);
	List<SimpleDto> results = new ArrayList<SimpleDto>();

	SimpleDto model = new SimpleDto();
	model.setLabel("<label>" + messages.objective() + "</label>");
	model.setValue("<label>" + numberFormat.format(totalObj) + " &euro;</label>");
	results.add(model);

	model = new SimpleDto();
	model.setLabel("<label>" + messages.transferts() + "</label>");
	model.setValue("<label>" + numberFormat.format(totalTF) + " &euro;</label>");
	results.add(model);

	model = new SimpleDto();
	model.setLabel("<label>" + messages.rd() + "</label>");
	model.setValue("<label>" + numberFormat.format(totalTS) + " &euro;</label>");
	results.add(model);

	model = new SimpleDto();
	model.setLabel("<label>" + messages.ts() + "</label>");
	model.setValue("<label>" + numberFormat.format(totalRD) + " &euro;</label>");
	results.add(model);

	return results;
    }

    public void setGestionDtoList(List<GestionDto> gestions) {
	gestionGrid.getStore().removeAll();
	gestionGrid.getStore().add(gestions);
    }

    public List<GestionDto> getGestionDtoList() {
	return gestionGrid.getStore().getModels();
    }

    public boolean isValid() {
	return (summary != null && summary.isValid());
    }

    @Override
    public void commitDataChange() {
	commitDataGrids(gestionGrid);
    }

    @SuppressWarnings("unchecked")
    private void exportGestion() {
	String chainter = navigation.getContext().getCurrentChantier().getNom();
	TextField<String> societte = (TextField<String>) ComponentManager.get().get("INFORMATION_PANEL_SOCIETE_ID");
	TextField<String> lot = (TextField<String>) ComponentManager.get().get("INFORMATION_PANEL_LOT_ID");
	ComboBox<LotTypeDto> lotType = (ComboBox<LotTypeDto>) ComponentManager.get().get("INFORMATION_PANEL_LOT_TYPE_ID");
	TextField<String> sitravaux = (TextField<String>) ComponentManager.get().get("INFORMATION_PANEL_SITRAVAUX_ID");
	NumberField montant = (NumberField) ComponentManager.get().get("INFORMATION_PANEL_MONTANT_ID");

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	List<GestionDto> list = gestionGrid.getStore().getModels();

	Map<String, String> listgestion = new HashMap<String, String>();
	Map<String, String> listtotalgestion = new HashMap<String, String>();
	List<String> lstKeys = new ArrayList<String>();
	String keys = "";
	for (int i = 0; i < list.size(); i++) {
	    if (list.get(i).getMarche() != null && !lstKeys.contains(list.get(i).getMarche().getLabel())) {
		lstKeys.add(list.get(i).getMarche().getLabel());
		keys += list.get(i).getMarche().getLabel() + Constants.SEPRATE;
	    }
	}
	GestionDto gestionDto = null;
	String alltotalgestion = "";

	double allamount = 0.0;
	double allavenants = 0.0;
	double allarrete = 0.0;
	double allnonarrete = 0.0;
	double allprovision = 0.0;
	double alldevisrefuse = 0.0;
	double allreelactivitive = 0.0;
	double allamount2 = 0.0;
	double alltotalfdc = 0.0;
	double allTotalecart = 0.0;

	String summary = "";
	String budget = "";
	for (int i = 0; i < lstKeys.size(); i++) {
	    String gestion = "";
	    String totalgestion = "";
	    double amount = 0.0;
	    double avenants = 0.0;
	    double arrete = 0.0;
	    double nonarrete = 0.0;
	    double provision = 0.0;
	    double devisrefuse = 0.0;
	    double reelactivitive = 0.0;
	    double amount2 = 0.0;
	    String gestionComment = "";
	    double ecartSum = 0.0;
	    double totalfdcSum = 0.0;

	    for (int j = 0; j < list.size(); j++) {
		gestionDto = list.get(j);
		if (lstKeys.get(i).equalsIgnoreCase(gestionDto.getMarche().getLabel())) {

		    String tmp = gestionDto.getMarche().getLabel() + "                                          " + gestionDto.getTraite();
		    gestionComment = gestionDto.getComment() != null ? gestionDto.getComment() : "";
		    gestion += append(gestionDto.getDevis(), gestionDto.getStatut().getLabel(), gestionDto.getLabel(), gestionComment, gestionDto.getAmount(), tmp, gestionDto.getArrete(),
			    gestionDto.getNonArrete(), gestionDto.getProvision(), gestionDto.getDevisRefuse(), gestionDto.getTotalFdc(), gestionDto.getReelActivitive(), gestionDto.getType()
				    .getLabel(), gestionDto.getLabel2(), gestionDto.getAmount2(), gestionDto.getEcart());

		    // Sum Group
		    amount += gestionDto.getAmount();
		    avenants += gestionDto.getTraite();
		    arrete += gestionDto.getArrete();
		    nonarrete += gestionDto.getNonArrete();
		    provision += gestionDto.getProvision();
		    devisrefuse += gestionDto.getDevisRefuse();
		    reelactivitive += gestionDto.getReelActivitive();
		    amount2 += gestionDto.getAmount2();
		    ecartSum += gestionDto.getEcart();
		    totalfdcSum += gestionDto.getTotalFdc();
		}
	    }
	    // Sum Gestion
	    allamount += amount;
	    allavenants += avenants;
	    allarrete += arrete;
	    allnonarrete += nonarrete;
	    allprovision += provision;
	    alldevisrefuse += devisrefuse;
	    allreelactivitive += reelactivitive;
	    allamount2 += amount2;
	    allTotalecart += ecartSum;
	    alltotalfdc += totalfdcSum;
	    listgestion.put(lstKeys.get(i), gestion);
	    totalgestion += append(amount, avenants, arrete, nonarrete, provision, devisrefuse, totalfdcSum, reelactivitive, amount2, ecartSum);
	    listtotalgestion.put(lstKeys.get(i), totalgestion);
	}
	if (keys.length() > 0) {
	    keys = keys.substring(0, keys.length() - (Constants.SEPRATE.length()));
	}
	params.add(new NameValuePair(ConstantClient.GESTIONDTO_ID_STR, listgestion.toString()));
	alltotalgestion += append(allamount, allavenants, allarrete, allnonarrete, allprovision, alldevisrefuse, alltotalfdc, allreelactivitive, allamount2, allTotalecart);
	params.add(new NameValuePair("alltotalgestion", alltotalgestion));
	String generaleInformation = append(chainter, lot.getValue(), sitravaux.getValue(), societte.getValue(), lotType.getValue().getName(), montant.getValue());
	params.add(new NameValuePair("generaleInformation", generaleInformation));
	params.add(new NameValuePair("totalgestion", listtotalgestion.toString()));
	summary += append(totalObj, totalTF, totalTS, totalRD);
	params.add(new NameValuePair("summary", summary));
	budget += append(budgetInitial, dernierPoint, dernier);
	params.add(new NameValuePair("budget", budget));
	DateField dernier = (DateField) ComponentManager.get().get("GESTION_PANEL_DATEDERNIER_ID");
	params.add(new NameValuePair("dernier", dernier + ""));
	params.add(new NameValuePair("keys", keys));
	String exportPdfUrl = GWT.getHostPageBaseURL() + "list_gestiondto.pdf";
	ReportUtil.showReport(exportPdfUrl, params.toArray(new NameValuePair[params.size()]));
    }
}
