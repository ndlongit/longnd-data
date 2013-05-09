package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.AggregationRowConfig;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.structis.fichesst.client.event.ConducteurEvent;
import com.structis.fichesst.client.event.DeductionGridUpdateEvent;
import com.structis.fichesst.client.event.ExportSuiviDesAccomptesEvent;
import com.structis.fichesst.client.event.PenaltyGridUpdateEvent;
import com.structis.fichesst.client.event.PrestationEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.client.event.SocieteEvent;
import com.structis.fichesst.client.handler.ConducteurHandler;
import com.structis.fichesst.client.handler.PrestationHandler;
import com.structis.fichesst.client.handler.SocieteHandler;
import com.structis.fichesst.client.widget.CustomEditorGrid;
import com.structis.fichesst.client.widget.CustomFieldSet;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.shared.dto.AbstractDto;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.util.Constants;

public class AccomptesPanel extends AbstractPanel {

	private FicheStDto ficheStDto;

	private EditorGrid<DeductionDto> deductionGrid = null;

	private EditorGrid<PenaltyDto> penaltyGrid = null;
	// For Report
	private String totaldeduction;
	private final TextField<String> txtTotalDeduction;

	private final HTML saveButton;
	private final HTML addRow = new HTML("<img src='./images/ajouter.png'/> " + messages.addRow(), true);
	private final HTML addRow2 = new HTML("<img src='./images/ajouter.png'/> " + messages.addRow(), true);
	private final TextArea comment = new TextArea();
	private final TextArea internalComment = new TextArea();

	private final RoleModel role;
	private final UtilisateurGrpModel user;

	private final double[] ratioList = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
	private final FormPanel formPanel = new CustomFormPanel();

	private final HTML chantierHtml;
	private String chantierName;

	private final HTML responsable;

	private final HTML societe;

	private final Integer ficheStId;

	public AccomptesPanel(SimpleEventBus b, RoleModel roleModel, UtilisateurGrpModel utilisateurGrpModel, Integer fId) {
		super();
		this.bus = b;
		this.role = roleModel;
		this.user = utilisateurGrpModel;
		this.ficheStId = fId;
		resetRotio();
		FieldSet paymentFieldSet = new CustomFieldSet();
		paymentFieldSet.setCollapsible(true);
		setDefaultBackgroundColor(paymentFieldSet);
		paymentFieldSet.setHeading(messages.accomptes());
		TableLayout tl12 = new TableLayout(1);
		tl12.setWidth("100%");
		paymentFieldSet.setLayout(tl12);

		LayoutContainer accomptesPanel1 = new LayoutContainer();
		HBoxLayout hBoxLayout = new HBoxLayout();
		hBoxLayout.setPadding(new Padding(17));
		accomptesPanel1.setLayout(hBoxLayout);

		HBoxLayoutData flex = new HBoxLayoutData();
		flex.setFlex(1);
		accomptesPanel1.add(new Text(), flex);
		saveButton = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm());
		saveButton.setStyleName(ACTION_HTML);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new SaveFicheStEvent());
			}
		});
		txtTotalDeduction = new TextField<String>();
		txtTotalDeduction.setId("ACCOMPTES_PANEL_TOTALDEDUCTION_ID");
		txtTotalDeduction.setValue(totaldeduction);
		txtTotalDeduction.hide();
		add(txtTotalDeduction);

		accomptesPanel1.add(saveButton, new HBoxLayoutData());
		accomptesPanel1.add(new HTML(LINKS_SPACE, false), new HBoxLayoutData());

		HTML printPayment = new HTML("<img src='./images/imprimer.png'/> " + messages.printAcomptes());
		printPayment.setStyleName(ACTION_HTML);
		printPayment.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				bus.fireEvent(new ExportSuiviDesAccomptesEvent());
			}
		});

		TableData td = new TableData();
		td.setWidth("230px");
		accomptesPanel1.add(printPayment, new HBoxLayoutData());

		paymentFieldSet.add(accomptesPanel1);

		LayoutContainer accomptesPanel2 = new LayoutContainer();
		TableLayout tl3 = new TableLayout(3);
		tl3.setWidth("100%");
		tl3.setCellHorizontalAlign(HorizontalAlignment.LEFT);
		accomptesPanel2.setLayout(tl3);
		chantierHtml = new HTML("", true);
		setChantier(null);
		// Report
		TableData td2 = new TableData();
		td2.setWidth("300px");
		accomptesPanel2.add(chantierHtml, td2);

		responsable = new HTML("", true);
		bus.addHandler(ConducteurEvent.TYPE, new ConducteurHandler() {
			@Override
			public void onChangeConducteur(ConducteurEvent conducteurEvent) {
				String conducter = conducteurEvent.getConducter() != null ? conducteurEvent.getConducter() : "";
				responsable.setHTML("<label>" + messages.responsable() + ":</label>" + SPACES + conducter);
			}
		});
		// Report
		TableData td3 = new TableData();
		td3.setWidth("400px");
		accomptesPanel2.add(responsable, td3);

		societe = new HTML("", true);
		bus.addHandler(SocieteEvent.TYPE, new SocieteHandler() {
			@Override
			public void onChangeSociete(SocieteEvent societeEvent) {
				String value = societeEvent.getSociete() != null ? societeEvent.getSociete() : "";
				societe.setHTML("<label>" + messages.societe() + ":</label>" + SPACES + value);
				value = societeEvent.getSociete();
			}
		});

		accomptesPanel2.add(societe);
		paymentFieldSet.add(accomptesPanel2);
		paymentFieldSet.add(new Html("<br/>"));

		FieldSet deductionsPaymentsFieldSet = new FieldSet();
		deductionsPaymentsFieldSet.setWidth("100%");
		deductionsPaymentsFieldSet.setCollapsible(true);
		setBackgroundColor(deductionsPaymentsFieldSet, BACKGROUD_COLOR_2);
		TableLayout tl1 = new TableLayout(1);
		tl1.setWidth("100%");
		deductionsPaymentsFieldSet.setLayout(tl1);
		deductionsPaymentsFieldSet.setHeading(messages.retenuesEffectuees());

		createDeductionGrid();
		processPrestationEvent();
		deductionsPaymentsFieldSet.add(deductionGrid);

		FlexTable flexTable = new FlexTable();
		HTML space = new HTML(SPACES_4);
		flexTable.setWidget(0, 0, space);
		addRow.setStyleName("actionHTML2");
		addRow.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				showAddAccomptesDialog();
			}

			private void showAddAccomptesDialog() {
				final Dialog dialog = new Dialog();
				dialog.setHeading(messages.titlePopup());
				dialog.setLayout(new BorderLayout());
				dialog.setWidth(600);
				dialog.setHeight(400);
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

				final AddAccomptesForm addDataForm = new AddAccomptesForm();
				dialog.add(addDataForm, new BorderLayoutData(LayoutRegion.CENTER));

				okButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						if (addDataForm.isValid()) {
							DeductionDto model = addDataForm.getDataModel();
							deductionGrid.getStore().add(model);
							deductionGrid.getView().refresh(true);
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

		flexTable.setWidget(0, 1, addRow);
		deductionsPaymentsFieldSet.add(flexTable);

		paymentFieldSet.add(deductionsPaymentsFieldSet);

		LayoutContainer accomptesPanel3 = new LayoutContainer();
		accomptesPanel3.setWidth("75%");
		TableLayout tl2 = new TableLayout(3);
		tl2.setWidth("100%");
		accomptesPanel3.setLayout(tl2);

		FieldSet penaltyFielSet = new FieldSet();
		setBackgroundColor(penaltyFielSet, BACKGROUD_COLOR_2);
		penaltyFielSet.setHeading(messages.penalites().toUpperCase());
		TableData td4 = new TableData();
		td4.setWidth("55%");

		createPenaltyGrid();
		penaltyFielSet.add(penaltyGrid);

		FlexTable flexTable2 = new FlexTable();
		HTML space2 = new HTML(SPACES_4);
		flexTable2.setWidget(0, 0, space2);
		addRow2.setStyleName("actionHTML2");
		addRow2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				showAddPenaltyDialog();
			}

			private void showAddPenaltyDialog() {
				Dialog dialog = new Dialog();
				dialog.setHeading(messages.titlePopup());
				dialog.setLayout(new BorderLayout());
				dialog.setWidth(500);
				dialog.setHeight(260);
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

				final AddPenaltyForm addDataForm = new AddPenaltyForm();
				dialog.add(addDataForm, new BorderLayoutData(LayoutRegion.CENTER));

				okButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						PenaltyDto model = addDataForm.getDataModel();
						penaltyGrid.getStore().add(model);
						penaltyGrid.getView().refresh(true);
					}
				});

				dialog.show();
			}
		});

		flexTable2.setWidget(0, 1, addRow2);

		penaltyFielSet.add(flexTable2);
		accomptesPanel3.add(penaltyFielSet, td4);

		LayoutContainer lc1 = new LayoutContainer();
		lc1.setWidth("100%");
		setDefaultBackgroundColor(lc1);
		lc1.setHeight(300);
		TableData td5 = new TableData();
		td5.setWidth("50px");
		accomptesPanel3.add(new HTML(SPACES + SPACES), td5);

		LayoutContainer layoutContainer = new LayoutContainer();
		layoutContainer.setWidth("100%");
		setDefaultBackgroundColor(layoutContainer);
		FormLayout fl_layoutContainer = new FormLayout();
		fl_layoutContainer.setLabelAlign(LabelAlign.TOP);
		layoutContainer.setLayout(fl_layoutContainer);

		comment.setName(FicheStDto.ACPT_COMMENTAIRES);
		comment.setId("ACCOMPTESPANEL_COMMENT");
		int height = 110;
		TableLayout tl10 = new TableLayout(1);
		tl10.setWidth("100%");
		setLayout(tl10);
		lc1.setLayout(new RowLayout(Orientation.VERTICAL));
		comment.setHeight(height);
		comment.setFieldLabel(messages.comment());
		layoutContainer.add(comment, new FormData("390%"));
		lc1.add(layoutContainer);
		lc1.add(new HTML("<br>"));
		LayoutContainer layoutContainer_1 = new LayoutContainer();
		layoutContainer_1.setWidth("100%");
		layoutContainer_1.setHeight(123);
		setDefaultBackgroundColor(layoutContainer_1);
		FormLayout fl_layoutContainer_1 = new FormLayout();
		fl_layoutContainer_1.setLabelAlign(LabelAlign.TOP);
		layoutContainer_1.setLayout(fl_layoutContainer_1);
		internalComment.setName(FicheStDto.ACPT_COMMENTAIRES_INTERNES);
		internalComment.setId("ACCOMPTESPANEL_INTERNAL_COMMENT");
		internalComment.setHeight(height);
		internalComment.setFieldLabel(messages.internalComment());
		layoutContainer_1.add(internalComment, new FormData("390%"));
		lc1.add(layoutContainer_1);

		formPanel.add(lc1);
		accomptesPanel3.add(formPanel);
		paymentFieldSet.add(accomptesPanel3);
		add(paymentFieldSet);

		if (isAdminOrContributor(this.role, this.user)) {
			saveButton.setVisible(true);
			addRow.setVisible(true);
			comment.enable();
			internalComment.enable();
			addRow2.setVisible(true);
			penaltyGrid.getColumnModel().setHidden(0, false);
			deductionGrid.getColumnModel().setHidden(0, false);
		} else {
			saveButton.setVisible(false);
			addRow.setVisible(false);
			comment.enable();
			internalComment.disable();
			addRow2.setVisible(false);
			penaltyGrid.getColumnModel().setHidden(0, true);
			deductionGrid.getColumnModel().setHidden(0, true);
		}
	}

	private void resetRotio() {
		for (int i = 0; i < ratioList.length; i++) {
			ratioList[i] = 0.0;
		}
	}

	@SuppressWarnings("rawtypes")
	private void createPenaltyGrid() {
		String[] headers = { messages.del(), messages.date(), messages.amount().replaceAll(" ", "<br>"), messages.comment() };
		String[] ids = { PenaltyDto.ID, PenaltyDto.DATE, PenaltyDto.AMOUNT, PenaltyDto.COMMENT };
		final int width1 = 170;
		final int width2 = 140;
		final int width3 = 380;
		int[] columnsWidth = { DELETE_BUTTON_WIDTH + 5, width1, width2, width3 };
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		SummaryColumnConfig column = null;
		for (int i = 0; i < headers.length; i++) {
			column = new SummaryColumnConfig<Number>(ids[i], ids[i], columnsWidth[i]);
			column.setHeader("<center>" + headers[i] + "</center>");
			column.setToolTip(headers[i]);
			column.setMenuDisabled(true);
			if (i < 2) {
				column.setAlignment(HorizontalAlignment.CENTER);
			} else {
				column.setAlignment(HorizontalAlignment.RIGHT);
			}
			columns.add(column);
		}

		ColumnModel cm = new ColumnModel(columns);

		AggregationRowConfig<PenaltyDto> totalSummary = new AggregationRowConfig<PenaltyDto>();
		totalSummary.setHtml(PenaltyDto.DATE, messages.total());
		totalSummary.setCellStyle(PenaltyDto.DATE, "cellStyleAggRow");
		totalSummary.setCellStyle(PenaltyDto.AMOUNT, "cellStyleAggRow");

		// Total summary
		totalSummary.setSummaryType(PenaltyDto.AMOUNT, SummaryType.SUM);
		totalSummary.setSummaryFormat(PenaltyDto.AMOUNT, NUMBER_FORMAT);
		cm.addAggregationRow(totalSummary);
		cm.getColumn(0).setRenderer(createDeleteButtonRenderer());

		cm.getColumn(1).setRenderer(createDateRendererWithPermision(cm.getColumn(1).getWidth() - 12, role, user));

		cm.getColumn(2).setRenderer(createNumberRendererWithPermission(role, user));
		NumberField numberField = createNumberField(null);
		cm.getColumn(2).setEditor(new CellEditor(numberField));

		GridCellRenderer<AbstractDto> textAreaRenderer = createTextAreaRendererWithPermission(cm.getColumn(3).getWidth() - 18, 38, role, user);
		cm.getColumn(3).setRenderer(textAreaRenderer);
		cm.getColumn(3).setEditor(createTextAreaEditor());

		ListStore<PenaltyDto> store = new ListStore<PenaltyDto>();
		penaltyGrid = new CustomEditorGrid<PenaltyDto>(store, cm);
		penaltyGrid.getView().setAutoFill(true);
		penaltyGrid.setHeight(210);

		penaltyGrid.getStore().addStoreListener(new StoreListener<PenaltyDto>() {
			@Override
			public void handleEvent(StoreEvent<PenaltyDto> e) {
				// penaltyGrid.getView().refresh(true);
				bus.fireEvent(new PenaltyGridUpdateEvent(penaltyGrid.getStore().getModels()));
			}
		});

		penaltyGrid.addListener(Events.BeforeEdit, new Listener<GridEvent<PenaltyDto>>() {
			@Override
			public void handleEvent(final GridEvent<PenaltyDto> be) {
				if (isAdminOrContributor(role, user)) {
					be.setCancelled(false);
				} else {
					be.setCancelled(true);
				}
			}
		});

		penaltyGrid.getView().setForceFit(true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createDeductionGrid() {
		final String[] headers = buildHeaders();
		final String[] ids = { DeductionDto.ID, DeductionDto.DATE, DeductionDto.CANTO, DeductionDto.BADGE, DeductionDto.GRUE, DeductionDto.LIFT,
				DeductionDto.BENNE, DeductionDto.NETTOYAGE, DeductionDto.AUTRES, DeductionDto.PRORATA, DeductionDto.REFACTURATIONS };
		int commonWidth = 112;
		int[] columnsWidth = { DELETE_BUTTON_WIDTH, commonWidth + 20, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth,
				commonWidth, commonWidth, commonWidth };

		AggregationRowConfig<DeductionDto> totalSummary = new AggregationRowConfig<DeductionDto>();
		totalSummary.setHtml(DeductionDto.DATE, messages.total());
		totalSummary.setCellStyle(DeductionDto.DATE, "cellStyleAggRow");
		// For Report
		totaldeduction = "";
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		for (int i = 0; i < ids.length; i++) {
			String columnId = ids[i];
			SummaryColumnConfig column = new SummaryColumnConfig(columnId, headers[i], columnsWidth[i]);
			column.setHeader("<center>" + headers[i] + "</center>");
			column.setMenuDisabled(true);
			if( i < 2 ) {
				column.setAlignment(HorizontalAlignment.CENTER);
			}
			else {
				column.setAlignment(HorizontalAlignment.RIGHT);
			}

			if (i == 0) {
				column.setRenderer(createDeleteButtonRenderer());
			} else if (i == 1) {
				column.setRenderer(createDateRendererWithPermision(column.getWidth() - PADDING_2, role, user));
			} else if (i <= 7) {
				column.setRenderer(createIntegerRendererWithPermission(role, user));
				NumberField numberField = createIntegerField(null);
				numberField.setPropertyEditorType(Integer.class);
				column.setEditor(new CellEditor(numberField));
			} else {
				column.setRenderer(createNumberRendererWithPermission(role, user));
				NumberField numberField = createNumberField(null);
				if (i == 9) {
					numberField.setMinValue(0);
				}
				column.setEditor(new CellEditor(numberField));
			}

			if (i > 1 && i != 8 && i != 9 && i != 10) {
				// Total summary columns
				totalSummary.setCellStyle(ids[i], "cellStyleAggRow");
				totalSummary.setSummaryFormat(columnId, INTEGER_FORMAT);
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						if (v == null) {
							v = 0.0;
						}
						Object obj = m.get(field);
						if (obj != null) {
							double ratio = findRatio(field);
							totaldeduction += append(((Double) v) + ((Number) obj).doubleValue() * ratio);
							return ((Double) v) + ((Number) obj).doubleValue() * ratio;
						}
						totaldeduction += append(((Double) v));
						return ((Double) v);
					}

					private double findRatio(String field) {
						for (int i = 0; i <= ids.length; i++) {
							String columnId = ids[i];
							if (columnId.equalsIgnoreCase(field)) {
								return ratioList[i - 2];
							}
						}
						return 0.0;
					}
				});
			}
			if (i == 8) {

				// Total summary columns
				totalSummary.setCellStyle(ids[i], "cellStyleAggRow");
				totalSummary.setSummaryFormat(columnId, NUMBER_FORMAT);
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						if (v == null) {
							v = 0.0;
						}
						Object obj = m.get(field);
						if (obj != null) {
							totaldeduction += append(((Double) v) + ((Number) obj).doubleValue());
							return ((Double) v) + ((Number) obj).doubleValue() * 1;
						}
						totaldeduction += append(((Double) v));
						return ((Double) v);
					}
				});

			}
			if (i == 9) {
				// Total summary columns
				totalSummary.setCellStyle(ids[i], "cellStyleAggRow");
				totalSummary.setSummaryFormat(columnId, NUMBER_FORMAT);
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						if (v == null) {
							v = 0.0;
						}
						Object obj = m.get(field);
						if (obj != null) {
							totaldeduction += append(((Double) v) + ((Number) obj).doubleValue());
							return ((Double) v) + ((Number) obj).doubleValue() * 1;
						}
						totaldeduction += append(((Double) v));
						return ((Double) v);
					}
				});

			}

			if (i == 10) {
				// Total summary columns
				totalSummary.setCellStyle(ids[i], "cellStyleAggRow");
				totalSummary.setSummaryFormat(columnId, NUMBER_FORMAT);
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						if (v == null) {
							v = 0.0;
						}
						Object obj = m.get(field);
						if (obj != null) {
							totaldeduction += append(((Double) v) + ((Number) obj).doubleValue());
							return ((Double) v) + ((Number) obj).doubleValue();
						}
						totaldeduction += append(((Double) v));
						return ((Double) v);
					}
				});
			}
			
			columns.add(column);
		}

		// For Report
		txtTotalDeduction.setValue(totaldeduction);
		ColumnModel cm = new ColumnModel(columns);
		cm.addHeaderGroup(0, 2, new HeaderGroupConfig(messages.quantity().toUpperCase(), 1, 6));
		cm.addHeaderGroup(0, 8, new HeaderGroupConfig(messages.amount2().toUpperCase(), 1, 2));
		cm.addAggregationRow(totalSummary);

		ListStore<DeductionDto> store = new ListStore<DeductionDto>();
		deductionGrid = new CustomEditorGrid(store, cm);
		deductionGrid.setId("ACCOMPTES_PANEL_DEDUCTION_GRID_ID");
		deductionGrid.setHeight(240);
		deductionGrid.setAutoExpandColumn(DeductionDto.REFACTURATIONS);
		deductionGrid.setAutoExpandMin(90);
		deductionGrid.setAutoExpandMax(1000);
		deductionGrid.getStore().addStoreListener(new StoreListener<DeductionDto>() {
			@Override
			public void handleEvent(StoreEvent<DeductionDto> e) {
				// deductionGrid.getView().refresh(true);
				bus.fireEvent(new DeductionGridUpdateEvent(deductionGrid.getStore().getModels()));
			}
		});

		deductionGrid.addListener(Events.BeforeEdit, new Listener<GridEvent<DeductionDto>>() {
			@Override
			public void handleEvent(final GridEvent<DeductionDto> be) {
				if (isAdminOrContributor(role, user)) {
					be.setCancelled(false);
				} else {
					be.setCancelled(true);
				}
			}
		});
	}

	private void processPrestationEvent() {
		bus.addHandler(PrestationEvent.TYPE, new PrestationHandler() {
			@Override
			public void onChangePrestation(PrestationEvent prestationEvent) {
				updateDeductionGrid();
			}
		});
	}

	private void updateDeductionGrid() {
		if (ficheStDto != null) {
			int i = 0;
			double ratioValue = ficheStDto.getPrestaCanto();
			ratioList[i++] = ratioValue;

			ratioValue = ficheStDto.getPrestaBadge();
			ratioList[i++] = ratioValue;

			ratioValue = ficheStDto.getPrestaGrue();
			ratioList[i++] = ratioValue;

			ratioValue = ficheStDto.getPrestaLift();
			ratioList[i++] = ratioValue;

			ratioValue = ficheStDto.getPrestaBenne();
			ratioList[i++] = ratioValue;

			ratioValue = ficheStDto.getPrestaNettoyage();
			ratioList[i++] = ratioValue;

			// i=6
			ratioList[i++] = 1;

			ratioValue = ficheStDto.getPrestaProrata();
			ratioList[i++] = ratioValue;

			// i=8
			ratioList[i++] = 1;

			ColumnModel columnModel = deductionGrid.getColumnModel();
			String[] headers = buildHeaders();
			for (int j = 2; j < headers.length; j++) {
				String header = headers[j];
				columnModel.setColumnHeader(j, header);
			}

			deductionGrid.getView().refresh(true);
		}
	}

	public List<DeductionDto> getDeductionDtoList() {
		if (deductionGrid == null) {
			return new ArrayList<DeductionDto>();
		}
		return deductionGrid.getStore().getModels();
	}

	public void setDeductionDtoList(List<DeductionDto> deductionDtos) {
		deductionGrid.getStore().add(deductionDtos);
	}

	public List<PenaltyDto> getPenaltyDtoList() {
		if (penaltyGrid == null) {
			return new ArrayList<PenaltyDto>();
		}
		return penaltyGrid.getStore().getModels();
	}

	public void setPenaltyDtoList(List<PenaltyDto> penaltyDtos) {
		penaltyGrid.getStore().add(penaltyDtos);
	}

	@Override
	public void commitDataChange() {
		commitDataGrids(deductionGrid, penaltyGrid);
	}

	public void setComment(String value) {
		comment.setValue(value);
	}

	public void setInternalComment(String value) {
		internalComment.setValue(value);
	}

	private void bindModel(FicheStDto ficheStDto) {
		if (ficheStDto == null) {
			return;
		}

		setChantier(ficheStDto);
		FormBinding binding = new FormBinding(formPanel);

		// Auto binding field(s)
		binding.autoBind();
		binding.bind(ficheStDto);
	}

	public void setModel(FicheStDto model) {
		this.ficheStDto = model;
		if (this.ficheStDto == null) {
			return;
		}

		setDeductionDtoList(this.ficheStDto.getDeductions());
		setPenaltyDtoList(this.ficheStDto.getPenalties());
		setComment(this.ficheStDto.getAcptCommentaires());
		setInternalComment(this.ficheStDto.getAcptCommentairesInternes());
		responsable.setHTML("<label>" + messages.responsable() + ":</label>" + SPACES + ficheStDto.getIdSiTravaux());
		societe.setHTML("<label>" + messages.societe() + ":</label>" + SPACES + ficheStDto.getSociete());

		bindModel(this.ficheStDto);

	}

	private void setChantier(FicheStDto ficheStDto) {
		try {
			if (ficheStDto == null) {
				chantierName = "";
			} else {
				chantierName = ficheStDto.getLot().getChantier().getNom();
				chantierHtml.setHTML("<label>" + messages.chantier() + ":</label>" + SPACES + chantierName);
			}
		} catch (Exception e) {
			chantierName = "";
		}
	}

	private String[] buildHeaders() {
		if (isNew(ratioList)) {
			Map<Integer, String> mapPrestations = navigation.getContext().getMapPrestations();
			if (mapPrestations != null) {
				String prestationValue = mapPrestations.get(ficheStId);
				if (prestationValue != null && prestationValue.length() > 0) {
					String arrRatio[] = prestationValue.split(Constants.SEPRATE);
					ratioList[0] = new Double(arrRatio[0]);
					ratioList[1] = new Double(arrRatio[1]);
					ratioList[2] = new Double(arrRatio[2]);
					ratioList[3] = new Double(arrRatio[3]);
					ratioList[4] = new Double(arrRatio[4]);
					ratioList[5] = new Double(arrRatio[5]);
					ratioList[6] = 1;
					ratioList[7] = new Double(arrRatio[6]);
					ratioList[8] = 1;
				}
			}
		}
		String[] results = { "<br><br>" + messages.del(), "<br><br>" + messages.date(),
				messages.canto() + "<br>" + CURRENCY_FORMAT.format(ratioList[0]), messages.badge() + "<br>" + CURRENCY_FORMAT.format(ratioList[1]),
				messages.grue() + "<br>" + CURRENCY_FORMAT.format(ratioList[2]), messages.lift() + "<br>" + CURRENCY_FORMAT.format(ratioList[3]),
				messages.benne() + "<br>" + CURRENCY_FORMAT.format(ratioList[4]),
				messages.nettoyage() + "<br>" + CURRENCY_FORMAT.format(ratioList[5]), messages.autres() + "<br>" + "&euro;",
				messages.prorata() + "<br>" + NUMBER_FORMAT.format(ratioList[7]) + "%", "<br><br>" + messages.refacturations() };

		return results;
	}

	private static boolean isNew(double[] ratioList) {
		for (int i = 0; i < ratioList.length; i++) {
			if (ratioList[i] != 0.0) {
				return false;
			}
		}
		return true;
	}
}
