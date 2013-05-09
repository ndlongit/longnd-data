package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HideMode;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
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
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.structis.fichesst.client.event.EtatAvancementEvent;
import com.structis.fichesst.client.event.ExportGestionEvent;
import com.structis.fichesst.client.event.GestionGridUpdateEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.service.ClientRefTypeMchAvServiceAsync;
import com.structis.fichesst.client.service.ClientStatusServiceAsync;
import com.structis.fichesst.client.service.ClientTypeServiceAsync;
import com.structis.fichesst.client.widget.ComboToolTipTemplate;
import com.structis.fichesst.client.widget.CustomComboBox;
import com.structis.fichesst.client.widget.CustomEditorGrid;
import com.structis.fichesst.client.widget.CustomFieldSet;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.shared.dto.AbstractDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.SimpleDto;
import com.structis.fichesst.shared.dto.SimpleFigDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class GestionPanel extends AbstractPanel {

	private static final int WIDTH_3 = 69;

	private static final int WIDTH_4 = WIDTH_3 - 10;

	private static final int[] COLUMNS_WIDTH_2 = { DELETE_BUTTON_WIDTH + 5, 40, WIDTH_3, WIDTH_4 - 10, WIDTH_3, 90, WIDTH_3, 50, WIDTH_3 + 30,
			WIDTH_3, WIDTH_3, 80, WIDTH_4, WIDTH_4, 55, WIDTH_4, WIDTH_3, WIDTH_3, 50 };

	private CustomEditorGrid<GestionDto> gestionGrid;

	private double totalObj = 0.0;

	private double totalTF = 0.0;

	private double totalTS = 0.0;

	private double totalRD = 0.0;

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

		LayoutContainer buttonPanel = new LayoutContainer();
		buttonPanel.setId("buttonPanel");

		HBoxLayout hBoxLayout = new HBoxLayout();
		hBoxLayout.setPadding(new Padding(PADDING));
		buttonPanel.setLayout(hBoxLayout);

		saveButton = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm(), false);
		saveButton.setStyleName(ACTION_HTML);

		HBoxLayoutData flex = new HBoxLayoutData();
		flex.setFlex(1);
		buttonPanel.add(new Text(), flex);
		buttonPanel.add(saveButton);

		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new SaveFicheStEvent());
			}
		});

		buttonPanel.add(new HTML(LINKS_SPACE, false));
		HTML print = new HTML("<img src='./images/imprimer.png'/> " + messages.print(), false);
		print.setStyleName(ACTION_HTML);
		print.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				bus.fireEvent(new ExportGestionEvent());
			}
		});
		buttonPanel.add(print);
		gestionFieldSet.add(buttonPanel);
		gestionFieldSet.add(new HTML("<br>"));

		createGestionGrid();

		gestionFieldSet.add(gestionGrid);

		LayoutContainer layoutContainer_12 = new LayoutContainer();
		TableLayout tl_layoutContainer_12 = new TableLayout(2);
		tl_layoutContainer_12.setCellPadding(0);
		tl_layoutContainer_12.setWidth("100.2%");
		layoutContainer_12.setLayout(tl_layoutContainer_12);
		gestionFieldSet.add(layoutContainer_12);
		layoutContainer_12.setBorders(false);

		FlexTable ft1 = new FlexTable();
		int commonWidth = 260;
		ft1.setWidget(0, 0, new HTML(SPACES_4));

		addFicheST = new HTML("<img src='./images/ajouter.png'/> " + messages.addRow(), false);
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
		tl_layoutContainer_13.setCellPadding(0);
		tl_layoutContainer_13.setCellHorizontalAlign(HorizontalAlignment.RIGHT);
		tl_layoutContainer_13.setWidth("100%");
		layoutContainer_13.setLayout(tl_layoutContainer_13);

		FlexTable ft2 = new FlexTable();
		inforGrid = createInforGrid();
		int commonWidth2 = 100;
		inforGrid.setHeight(commonWidth2);
		LayoutContainer layoutContainer_25 = new LayoutContainer();
		layoutContainer_25.setHeight(commonWidth2);

		layoutContainer_25.add(new HTML("<label>" + messages.dont() + ":<label> "));
		ft2.setWidget(0, 0, layoutContainer_25);
		ft2.setWidget(0, 1, inforGrid);
		layoutContainer_13.add(ft2);
		ft2.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);

		summary = new CustomFormPanel();
		summary.setPadding(0);
		TableLayout tl_summary = new TableLayout(2);
		summary.setLayout(tl_summary);
		layoutContainer_13.add(summary);
		HTML budgetLabel = new HTML("&nbsp;" + messages.budget());
		budgetLabel.setWordWrap(false);
		TableData td_budgetPanel = new TableData();
		td_budgetPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		LayoutContainer budgetPanel = new LayoutContainer();
		budgetPanel.setStyleAttribute("background-color", HEADER_BACKGROUND_COLOR);
		budgetPanel.add(budgetLabel);
		summary.add(budgetPanel, td_budgetPanel);
		NumberField budget = createNumberField(null);
		budget.setBorders(true);
		budget.setStyleAttribute("border-color", "#938E8E");
		budget.setName(FicheStDto.GEST_BUDGET_INITIAL);
		budget.setId("GESTIONAL_PANEL_BUDGET_ID");
		budget.setWidth(commonWidth);

		LayoutContainer layoutContainer_28 = createPaddingPanel();
		layoutContainer_28.add(budget);
		summary.add(layoutContainer_28);
		HTML ecardDernierLabel = new HTML("&nbsp;" + messages.ecardDernier() + "&nbsp;");

		TableData td_ecardDernierPanel = new TableData();
		td_ecardDernierPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		LayoutContainer ecardDernierPanel = new LayoutContainer();
		ecardDernierPanel.setStyleAttribute("background-color", HEADER_BACKGROUND_COLOR);
		ecardDernierPanel.add(ecardDernierLabel);
		summary.add(ecardDernierPanel, td_ecardDernierPanel);
		NumberField ecardDernier = createNumberField(null);
		ecardDernier.setBorders(true);
		ecardDernier.setStyleAttribute("border-color", "#938E8E");
		ecardDernier.setName(FicheStDto.GEST_ECART_DERNIER_PT);
		ecardDernier.setId("GESTIONAL_PANEL_ECARDDERNIER_ID");
		ecardDernier.setWidth(commonWidth);

		LayoutContainer layoutContainer_27 = createPaddingPanel();
		layoutContainer_27.add(ecardDernier);
		summary.add(layoutContainer_27);
		HTML dateDernierLabel = new HTML("&nbsp;" + messages.dernierDate());

		LayoutContainer dateDernierPanel = new LayoutContainer();
		dateDernierPanel.setStyleAttribute("background-color", HEADER_BACKGROUND_COLOR);
		dateDernierPanel.add(dateDernierLabel);
		TableData td_dateDernierPanel = new TableData();
		td_dateDernierPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		summary.add(dateDernierPanel, td_dateDernierPanel);
		DateField dateDernier = createDateField();
		dateDernier.setBorders(true);
		dateDernier.setStyleAttribute("border-color", "#938E8E");
		dateDernier.setName(FicheStDto.GEST_DATE_DERNIER_PT);
		dateDernier.setId("GESTION_PANEL_DATEDERNIER_ID");
		dateDernier.setWidth(commonWidth);

		LayoutContainer layoutContainer_26 = createPaddingPanel();
		layoutContainer_26.add(dateDernier);
		summary.add(layoutContainer_26);

		layoutContainer_12.add(layoutContainer_13);
		gestionFieldSet.setHeading(messages.gestion());
		add(gestionFieldSet);
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

	private static LayoutContainer createPaddingPanel() {
		LayoutContainer c = new LayoutContainer();
		// c.setStyleAttribute("padding-left", "2px");
		c.setStyleAttribute("padding-right", "3px");

		return c;
	}

	private void bindModel(FicheStDto ficheStDto) {
		if (ficheStDto == null) {
			return;
		}

		FormBinding binding = new FormBinding(summary);

		// Auto binding fields
		binding.autoBind();
		binding.bind(ficheStDto);
	}

	public void setModel(FicheStDto ficheStDto) {
		super.setModel(ficheStDto);

		if (ficheStDto == null) {
			return;
		}

		setGestionDtoList(ficheStDto.getGestions());
		bindModel(ficheStDto);
	}

	public GridCellRenderer<GestionDto> createLockableTextAreaRenderer(final int fieldWidth, final int fieldHeight) {
		GridCellRenderer<GestionDto> textFieldRenderer = new GridCellRenderer<GestionDto>() {
			@Override
			public Object render(final GestionDto model, final String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<GestionDto> store, Grid<GestionDto> grid) {
				String value = (String) model.get(property);
				if (value == null) {
					value = "";
				}

				final TextArea field = new TextArea();
				field.setValue(value);
				field.setBorders(true);
				field.setStyleAttribute("border-color", "#938E8E");
				if (fieldWidth > 0) {
					field.setWidth(fieldWidth);
				}
				if (fieldHeight > 0) {
					field.setHeight(fieldHeight);
				}
				Boolean lock = model.getLock();
				if (Boolean.TRUE.equals(lock)) {
					field.disable();
				} else {
					field.enable();
				}

				field.addListener(Events.Blur, new KeyListener() {
					@Override
					public void handleEvent(ComponentEvent e) {
						model.set(property, field.getValue());
					}
				});

				return field;
			}
		};

		return textFieldRenderer;
	}

	@SuppressWarnings("unchecked")
	private void createGestionGrid() {
		String[] ids = { GestionDto.ID, GestionDto.LOCK, GestionDto.DEVIS, GestionDto.STATUT, GestionDto.LABEL, GestionDto.COMMENT,
				GestionDto.AMOUNT, GestionDto.MARCHE, GestionDto.TRAITE, GestionDto.ARRETE, GestionDto.NON_ARRETE, GestionDto.PROVISION,
				GestionDto.DEVIS_REFUSE, GestionDto.TOTAL_FDC, GestionDto.REEL_ACTIVITIVE, GestionDto.TYPE, GestionDto.LABEL2, GestionDto.AMOUNT2,
				GestionDto.ECART };
		String[] headers = { "<br/><br/>" + messages.del(), "<br><br>" + messages.freezeRow(), "<br><br>" + messages.devis(),
				"<br><br>" + messages.status(), "<br><br>" + messages.label(), "<br><br>" + messages.comment(), "<br><br>" + messages.amount(),
				messages.marche(), messages.avenants(), messages.arrete() + " (&euro;)", messages.nonArrete(),
				"<br><br>" + messages.provision() + " (&euro;)", "<br>" + messages.devisRefuse() + " (&euro;)", "<br>" + messages.totalFDC(),
				"<br>" + messages.reelActivitive(), messages.type(), messages.label(), messages.amount(), "<br><br>" + messages.ecart() };
		List<String> verticalTextColumn = Arrays.asList(GestionDto.AMOUNT, GestionDto.ARRETE, GestionDto.NON_ARRETE, GestionDto.DEVIS_REFUSE,
				GestionDto.TOTAL_FDC, GestionDto.REEL_ACTIVITIVE, GestionDto.AMOUNT2, GestionDto.ECART);

		// Total summary columns
		List<String> totalSummaryColumns = Arrays
				.asList(GestionDto.AMOUNT, GestionDto.TRAITE, GestionDto.ARRETE, GestionDto.NON_ARRETE, GestionDto.PROVISION,
						GestionDto.DEVIS_REFUSE, GestionDto.TOTAL_FDC, GestionDto.REEL_ACTIVITIVE, GestionDto.AMOUNT2, GestionDto.ECART);

		AggregationRowConfig<GestionDto> totalSummary = new AggregationRowConfig<GestionDto>();
		totalSummary.setHtml(GestionDto.COMMENT, messages.rexAmount());
		totalSummary.setCellStyle(GestionDto.COMMENT, "cellStyleAggRow1");
		totalSummary.setHtml(GestionDto.LOCK, "");
		totalSummary.setCellStyle(GestionDto.LOCK, "cellStyleAggRow1");
		totalSummary.setHtml(GestionDto.DEVIS, "");
		totalSummary.setCellStyle(GestionDto.DEVIS, "cellStyleAggRow1");
		totalSummary.setHtml(GestionDto.STATUT, "");
		totalSummary.setCellStyle(GestionDto.STATUT, "cellStyleAggRow1");
		totalSummary.setHtml(GestionDto.LABEL, "");
		totalSummary.setCellStyle(GestionDto.LABEL, "cellStyleAggRow1");
		totalSummary.setHtml(GestionDto.MARCHE, "");
		totalSummary.setCellStyle(GestionDto.MARCHE, "cellStyleAggRow");
		totalSummary.setHtml(GestionDto.TYPE, "");
		totalSummary.setCellStyle(GestionDto.TYPE, "cellStyleAggRow");
		totalSummary.setHtml(GestionDto.LABEL2, "");
		totalSummary.setCellStyle(GestionDto.LABEL2, "cellStyleAggRow");

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		for (int i = 0; i < ids.length; i++) {
			final String columnId = ids[i];
			@SuppressWarnings("rawtypes")
			final SummaryColumnConfig column = new SummaryColumnConfig(columnId, headers[i], COLUMNS_WIDTH_2[i]);
			column.setHeader("<center>" + headers[i] + "</center>");
			if (i < 6) {
				column.setAlignment(HorizontalAlignment.CENTER);
			} else {
				if (GestionDto.MARCHE.equals(columnId) || GestionDto.TYPE.equals(columnId) || GestionDto.LABEL2.equals(columnId)) {
					column.setAlignment(HorizontalAlignment.CENTER);
				} else {
					column.setAlignment(HorizontalAlignment.RIGHT);
				}
			}

			column.setMenuDisabled(true);
			columns.add(column);
			if (totalSummaryColumns.contains(columnId)) {

				// Summary columns
				column.setSummaryType(SummaryType.SUM);
				column.setSummaryFormat(NUMBER_FORMAT);
				// Total summary columns
				totalSummary.setSummaryType(columnId, SummaryType.SUM);
				totalSummary.setSummaryFormat(columnId, NUMBER_FORMAT);
				totalSummary.setCellStyle(columnId, "cellStyleAggRow");
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
		combobox0.setWidth(10);
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

		editor.setWidth(10);
		columns.get(1).setEditor(editor);

		GridCellRenderer<AbstractDto> textFieldRenderer = createTextFieldRenderer();
		columns.get(2).setRenderer(textFieldRenderer);
		columns.get(2).setEditor(new CellEditor(createTextField(MAX_LENGTH_1)));

		columns.get(4).setRenderer(textFieldRenderer);
		columns.get(4).setEditor(new CellEditor(new TextField<Object>()));

		ColumnConfig commentColumn = columns.get(5);
		GridCellRenderer<GestionDto> textAreaRenderer = createLockableTextAreaRenderer(commentColumn.getWidth() - 10, 33);
		// Comment column
		commentColumn.setRenderer(textAreaRenderer);
		commentColumn.setEditor(createTextAreaEditor());

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
		combobox2.addListener(Events.Change, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// gestionGrid.getView().refresh(true);
				gestionGrid.getStore().addStoreListener(new StoreListener<GestionDto>() {
					@Override
					public void handleEvent(StoreEvent<GestionDto> e) {
						gestionGrid.getView().refresh(false);
					}
				});
			}
		});
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
		columns.get(13).setNumberFormat(NUMBER_FORMAT);
		// columns.get(13).setRenderer(createNumberRenderer());
		columns.get(18).setNumberFormat(NUMBER_FORMAT);
		// columns.get(13).setRenderer(createNumberRenderer());

		ColumnModel cm = new ColumnModel(columns);
		addHeaderGroups(cm);
		cm.addAggregationRow(totalSummary);

		ColumnConfig label2Col = cm.getColumnById(GestionDto.LABEL2);
		label2Col.setEditor(new CellEditor(createTextField(MAX_LENGTH_1)));

		GroupingStore<GestionDto> store = new GroupingStore<GestionDto>();
		store.setStoreSorter(new StoreSorter<GestionDto>() {
			@Override
			public int compare(Store<GestionDto> store, GestionDto m1, GestionDto m2, String property) {
				return m1.getMarche().getOrder().compareTo(m2.getMarche().getOrder());
			}
		});
		final String groupedColumn = GestionDto.MARCHE;
		store.groupBy(groupedColumn);

		GroupSummaryView view = new GroupSummaryView() {
			@Override
			protected void refreshSummary(String groupField, String group) {
				if (groupedColumn.equalsIgnoreCase(groupField)) {
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
		// view.setAutoFill(true);

		gestionGrid = new CustomEditorGrid<GestionDto>(store, cm, role, user);
		gestionGrid.setAutoHeight(true);
		gestionGrid.setHideMode(HideMode.OFFSETS);
		gestionGrid.setView(view);
		gestionGrid.setAutoExpandColumn(GestionDto.ECART);
		gestionGrid.setAutoExpandMax(900);
		gestionGrid.setAutoExpandMin(115);
		// gestionGrid.getView().setForceFit(true);
		gestionGrid.getStore().addStoreListener(new StoreListener<GestionDto>() {
			@Override
			public void handleEvent(StoreEvent<GestionDto> e) {
				// gestionGrid.getView().refresh(false);
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

	private void addHeaderGroups(ColumnModel cm) {
		cm.addHeaderGroup(0, 7, new HeaderGroupConfig(messages.traite().toUpperCase(), 1, 2));
		cm.addHeaderGroup(0, 9, new HeaderGroupConfig(messages.traiter().toUpperCase(), 1, 2));
		cm.addHeaderGroup(0, 15, new HeaderGroupConfig(messages.budgetConforme().toUpperCase(), 1, 3));
	}

	private Object buildTypeColumn(final ModelData model) {
		Boolean lock = model.get(GestionDto.LOCK);
		if (Boolean.TRUE.equals(lock)) {
			return "X";
		} else {
			return "";
		}
	}

	private void setNumberFormatColumns(List<ColumnConfig> columns) {
		formatColumn(columns.get(6), false);
		formatColumn(columns.get(8), false);
		formatColumn(columns.get(9), false);
		formatColumn(columns.get(10), false);
		formatColumn(columns.get(11), false);
		formatColumn(columns.get(12), false);
		// formatColumn(columns.get(13), false);
		formatColumn(columns.get(14), true);
		formatColumn(columns.get(17), false);
		// formatColumn(columns.get(18), false);
	}

	private void formatColumn(ColumnConfig column, boolean percentage) {
		int width = column.getWidth();
		GridCellRenderer<AbstractDto> numberFieldRenderer = createNumberRenderer();
		column.setRenderer(numberFieldRenderer);
		column.setNumberFormat(NUMBER_FORMAT);
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
		SummaryColumnConfig column = new SummaryColumnConfig();
		column.setId(SimpleDto.LABEL);
		column.setMenuDisabled(true);
		column.setWidth(160);
		column.setAlignment(HorizontalAlignment.LEFT);
		columns.add(column);

		column = new SummaryColumnConfig();
		column.setId(SimpleDto.VALUE);
		column.setMenuDisabled(true);
		column.setWidth(95);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setNumberFormat(NumberFormat.getCurrencyFormat());
		columns.add(column);

		ColumnModel cm = new ColumnModel(columns);
		ListStore<SimpleDto> store = new ListStore<SimpleDto>();
		store.add(createInforModelList(0.0, 0.0, 0.0, 0.0));
		Grid grid = new CustomEditorGrid<SimpleDto>(store, cm);
		grid.setColumnLines(true);
		grid.setStripeRows(true);
		grid.setHideHeaders(true);
		grid.getView().setForceFit(true);
		return grid;
	}

	private static List<SimpleDto> createInforModelList(double totalObj, double totalTF, double totalTS, double totalRD) {
		List<SimpleDto> results = new ArrayList<SimpleDto>();

		SimpleDto model = new SimpleDto();
		model.setLabel(messages.objective());
		model.setValue(NUMBER_FORMAT.format(totalObj));
		results.add(model);

		model = new SimpleDto();
		model.setLabel(messages.transferts());
		model.setValue(NUMBER_FORMAT.format(totalTF));
		results.add(model);

		model = new SimpleDto();
		model.setLabel(messages.rd());
		model.setValue(NUMBER_FORMAT.format(totalTS));
		results.add(model);

		model = new SimpleDto();
		model.setLabel(messages.ts());
		model.setValue(NUMBER_FORMAT.format(totalRD));
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
}
