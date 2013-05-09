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
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.AggregationRowConfig;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
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
import com.structis.fichesst.client.event.DeductionGridUpdateEvent;
import com.structis.fichesst.client.event.EtatAvancementEvent;
import com.structis.fichesst.client.event.ExportAvancementEvent;
import com.structis.fichesst.client.event.PrestationEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.client.handler.DeductionGridUpdateHandler;
import com.structis.fichesst.client.handler.EtatAvancementHandler;
import com.structis.fichesst.client.handler.PrestationHandler;
import com.structis.fichesst.client.widget.CustomEditorGrid;
import com.structis.fichesst.client.widget.CustomFieldSet;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.shared.dto.AnonymousDto;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.ProgressDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.util.Constants;

public class AvancementsPanel extends AbstractPanel {

	private static final String[] COLUMNS_WIDTH_3 = { "55%", "2%", "43%" };

	private FicheStDto ficheStDto;

	private EditorGrid<ProgressDto> progressGrid;

	private EditorGrid<AnonymousDto> penaltyGrid;

	private EditorGrid<AnonymousDto> grid_3;

	private String mois = "";

	private String detail_des_retenues_appliques;

	private String grid_3_report;

	private TextField<String> txtdetail_des_retenues_appliques;

	private TextField<String> txtgrid_3_report;

	private TextField<String> txt_advancement_mois;

	private TextField<String> txttotalsituation;

	private TextField<String> txtEtatAvancement;

	private final FormPanel formPanel = new CustomFormPanel();

	// End

	private List<GestionDto> listGestion;

	private final HTML saveButton;

	private final HTML addRow;

	private final TextArea comment = new TextArea();

	private final HTML progressLabel;

	private final RoleModel role;

	private final UtilisateurGrpModel user;

	private double etatAvancement;

	public List<ProgressDto> progressDtos;

	public AvancementsPanel(SimpleEventBus b, RoleModel roleModel, UtilisateurGrpModel utilisateurGrpModel) {
		super();
		this.bus = b;
		this.role = roleModel;
		this.user = utilisateurGrpModel;
		FieldSet avancementsFieldSet = new CustomFieldSet();
		LayoutContainer layoutContainer_1 = new LayoutContainer();

		HBoxLayout hBoxLayout = new HBoxLayout();
		hBoxLayout.setPadding(new Padding(PADDING));
		layoutContainer_1.setLayout(hBoxLayout);

		initFieldReport();

		progressLabel = new HTML("", false);
		setProgressLabelText(0.0);
		bus.addHandler(EtatAvancementEvent.TYPE, new EtatAvancementHandler() {
			@Override
			public void onGetEtatAvancement(EtatAvancementEvent event) {
				double cumuleSum = 0.0;
				double totalTraite = 0.0;
				if (event.getListGestion() != null && event.getListGestion().size() > 0) {
					listGestion = event.getListGestion();
					GestionDto gestionDto = null;
					for (int i = 0; i < listGestion.size(); i++) {
						gestionDto = listGestion.get(i);
						totalTraite += gestionDto.getTraite();
					}
				}

				List<ProgressDto> listProcess = null;
				if (event.getListProcess() == null) {
					listProcess = progressGrid.getStore().getModels();
				} else
					listProcess = event.getListProcess();
				if (listProcess != null && listProcess.size() > 0) {
					ProgressDto progressDto = null;
					for (int i = 0; i < listProcess.size(); i++) {
						progressDto = listProcess.get(i);
						cumuleSum = progressDto.getCumule();
					}
				}

				if (totalTraite == 0) {
					etatAvancement = 0.0;
				} else {
					etatAvancement = (cumuleSum / totalTraite) * 100;
				}
				setProgressLabelText(etatAvancement);
			}
		});

		layoutContainer_1.add(progressLabel, new HBoxLayoutData());

		HBoxLayoutData flex = new HBoxLayoutData();
		flex.setFlex(1);
		layoutContainer_1.add(new Text(), flex);

		saveButton = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm(), false);
		saveButton.setStyleName("actionHTML");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new SaveFicheStEvent());
			}
		});

		layoutContainer_1.add(saveButton, new HBoxLayoutData());
		layoutContainer_1.add(new HTML(LINKS_SPACE, false), new HBoxLayoutData());

		HTML print2 = new HTML("<img src='./images/imprimer.png'/> " + messages.printPromotions(), false);
		print2.setStyleName("actionHTML");
		print2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				bus.fireEvent(new ExportAvancementEvent());
			}
		});
		layoutContainer_1.add(print2, new HBoxLayoutData());

		avancementsFieldSet.add(layoutContainer_1);
		avancementsFieldSet.add(new HTML("<br>", true));
		avancementsFieldSet.setCollapsible(true);
		avancementsFieldSet.setHeading(messages.avancement());
		TableLayout avancementsTableLayout = new TableLayout(1);
		avancementsTableLayout.setWidth("100%");
		avancementsFieldSet.setLayout(avancementsTableLayout);

		FieldSet fldstNewFieldset_1 = new FieldSet();
		fldstNewFieldset_1.setCollapsible(true);
		fldstNewFieldset_1.setHeading(messages.deductionsDetails());

		createPenaltiesGrid();
		fldstNewFieldset_1.add(penaltyGrid);

		fldstNewFieldset_1.add(new HTML("<br>"));

		createGrid3();
		fldstNewFieldset_1.add(grid_3);

		FlexTable ft11 = new FlexTable();
		ft11.setWidget(0, 0, new HTML(SPACES_4));
		addRow = new HTML("<img src='./images/ajouter.png'/> " + messages.addRow(), false);
		addRow.setStyleName("actionHTML2");
		ft11.setWidget(0, 1, addRow);

		addRow.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				showAddDialog();
			}

			private void showAddDialog() {
				Dialog dialog = new Dialog();
				dialog.setHeading(messages.titlePopup());
				dialog.setLayout(new BorderLayout());
				dialog.setWidth(550);
				dialog.setHeight(240);
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

				final AddAvancementForm addDataForm = new AddAvancementForm();
				dialog.add(addDataForm, new BorderLayoutData(LayoutRegion.CENTER));

				okButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						ProgressDto model = addDataForm.getDataModel();
						progressGrid.getStore().add(model);
						progressGrid.getView().refresh(true);
					}
				});

				dialog.show();
			}
		});

		LayoutContainer leftPanel = new LayoutContainer();
		leftPanel.setLayout(new TableLayout(1));

		leftPanel.add(new HTML("<br>"));

		createProgressGrid();
		leftPanel.add(progressGrid);

		leftPanel.add(ft11);

		leftPanel.add(new HTML("<br>"));
		leftPanel.add(fldstNewFieldset_1);
		TableData layoutData9 = new TableData();
		layoutData9.setWidth(COLUMNS_WIDTH_3[1]);
		setLayout(new RowLayout(Orientation.VERTICAL));
		add(avancementsFieldSet);

		setDefaultBackgroundColor(avancementsFieldSet);

		LayoutContainer layoutContainer_3 = new LayoutContainer();
		TableLayout tl3 = new TableLayout(3);
		tl3.setWidth("100%");
		layoutContainer_3.setLayout(tl3);
		avancementsFieldSet.add(layoutContainer_3);

		LayoutContainer rightPanel = new LayoutContainer();
		layoutContainer_3.add(leftPanel);

		TableData td2 = new TableData();
		td2.setWidth(COLUMNS_WIDTH_3[1]);
		layoutContainer_3.add(new HTML("&nbsp;"), td2);
		FormLayout fl_rightPanel = new FormLayout();
		fl_rightPanel.setLabelAlign(LabelAlign.TOP);
		rightPanel.setLayout(fl_rightPanel);

		TableData td_rightPanel = new TableData();
		td_rightPanel.setWidth(COLUMNS_WIDTH_3[2]);
		comment.setMaxLength(MAX_LENGTH_5);
		comment.setName(FicheStDto.AVCT_COMMENTAIRES);
		comment.setId("AVANCEMENTSPANEL_COMMENT");
		comment.setHeight("460px");
		comment.setFieldLabel("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ messages.comment2());
		formPanel.add(comment, new FormData("100%"));
		rightPanel.add(formPanel);
		layoutContainer_3.add(rightPanel, td_rightPanel);

		bus.addHandler(PrestationEvent.TYPE, new PrestationHandler() {
			@Override
			public void onChangePrestation(PrestationEvent event) {
				updateDeductionGrid(AvancementsPanel.this.ficheStDto);
			}
		});

		bus.addHandler(DeductionGridUpdateEvent.TYPE, new DeductionGridUpdateHandler() {
			@Override
			public void onSave(DeductionGridUpdateEvent event) {
				ficheStDto.setDeductions(event.getDeductionDtoList());
				updateDeductionGrid(AvancementsPanel.this.ficheStDto);
			}
		});
		if (isAdminOrContributor(roleModel, utilisateurGrpModel)) {
			saveButton.setVisible(true);
			addRow.setVisible(true);
			comment.enable();
			progressGrid.getColumnModel().setHidden(0, false);
		} else {
			saveButton.setVisible(false);
			addRow.setVisible(false);
			comment.disable();
			progressGrid.getColumnModel().setHidden(0, true);
		}
	}

	private void updateDeductionGrid(FicheStDto ficheStDto) {
		int cantoQuantity = 0;
		int badgeQuantity = 0;
		int grueQuantity = 0;
		int liftQuantity = 0;
		int benneQuantity = 0;
		int nettoyageQuantity = 0;
		double autres = 0.0;

		List<DeductionDto> deductionList = ficheStDto.getDeductions();
		if (deductionList != null) {
			for (DeductionDto deductionDto : deductionList) {
				cantoQuantity += deductionDto.getCanto();
				badgeQuantity += deductionDto.getBadge();
				grueQuantity += deductionDto.getGrue();
				liftQuantity += deductionDto.getLift();
				benneQuantity += deductionDto.getBenne();
				nettoyageQuantity += deductionDto.getNettoyage();
				autres += deductionDto.getAutres();
			}
		}
		grid_3.getStore().removeAll();

		List<AnonymousDto> models = new ArrayList<AnonymousDto>();
		AnonymousDto model = null;

		String[] column1s = { messages.nombre(), messages.amount() };
		double cantoValue = 0.0;
		double badgeValue = 0.0;
		double grueValue = 0.0;
		double liftValue = 0.0;
		double benneValue = 0.0;
		double netoyageValue = 0.0;
		cantoValue = ficheStDto.getPrestaCanto();
		badgeValue = ficheStDto.getPrestaBadge();
		grueValue = ficheStDto.getPrestaGrue();
		liftValue = ficheStDto.getPrestaLift();
		benneValue = ficheStDto.getPrestaBenne();
		netoyageValue = ficheStDto.getPrestaNettoyage();
		double[] column2s = { cantoQuantity, (cantoQuantity * cantoValue) };
		double[] column3s = { badgeQuantity, (badgeQuantity * badgeValue) };
		double[] column4s = { grueQuantity, (grueQuantity * grueValue) };
		double[] column5s = { liftQuantity, (liftQuantity * liftValue) };
		double[] column6s = { benneQuantity, (benneQuantity * benneValue) };
		double[] column7s = { nettoyageQuantity, (nettoyageQuantity * netoyageValue) };
		double[] column8s = { 0, autres };
		grid_3_report = "";
		for (int i = 0; i < 2; i++) {
			model = new AnonymousDto();
			model.set(AnonymousDto.COLUMN_1, column1s[i]);
			model.set(AnonymousDto.COLUMN_2, column2s[i]);
			model.set(AnonymousDto.COLUMN_3, column3s[i]);
			model.set(AnonymousDto.COLUMN_4, column4s[i]);
			model.set(AnonymousDto.COLUMN_5, column5s[i]);
			model.set(AnonymousDto.COLUMN_6, column6s[i]);
			model.set(AnonymousDto.COLUMN_7, column7s[i]);
			model.set(AnonymousDto.COLUMN_8, column8s[i]);
			// For report
			grid_3_report += column2s[i] + Constants.SEPRATE + column3s[i] + Constants.SEPRATE + column4s[i] + Constants.SEPRATE + column5s[i]
					+ Constants.SEPRATE + column6s[i] + Constants.SEPRATE + column7s[i] + Constants.SEPRATE + column8s[i] + Constants.SEPRATE;
			models.add(model);
		}
		// For Report
		if (grid_3_report != "") {
			grid_3_report.substring(0, grid_3_report.length() - Constants.SEPRATE.length());
		}

		txtgrid_3_report.setValue(grid_3_report);
		txtgrid_3_report.setId("AVANCEMENTS_PANEL_TXTGRID3REPORT_ID");
		txtgrid_3_report.hide();

		grid_3.getStore().add(models);
		grid_3.getView().refresh(true);
	}

	public void setProgressLabelText(double value) {
		try {
			value = new Double(value);
		} catch (NumberFormatException numberFormatException) {
		}
		if (progressLabel != null) {
			progressLabel.setHTML("<label>" + messages.progress() + "</label>: " + NUMBER_FORMAT.format(value) + "% AVANCEMENT / MARCHE");
			txtEtatAvancement.setValue(NUMBER_FORMAT.format(value));
		}
	}

	public void setProgressDtoList(List<ProgressDto> progressDtos) {
		this.progressDtos = progressDtos;
		progressGrid.getStore().removeAll();
		progressGrid.getStore().add(this.progressDtos = progressDtos);
	}

	@SuppressWarnings({ "rawtypes" })
	private EditorGrid<ProgressDto> createProgressGrid() {
		String[] headers = { "<br><br>" + messages.del(), "<br><br>" + messages.number(), "<br><br>" + messages.label(),
				"<br><br>" + messages.date(), messages.cumule(), messages.mois(), messages.cumule2(), messages.mois() };
		String[] ids = { ProgressDto.ID, ProgressDto.ORDER, ProgressDto.LABEL, ProgressDto.DATE, ProgressDto.CUMULE, ProgressDto.MOIS,
				ProgressDto.CUMULE2, ProgressDto.MOIS2 };
		int width = 110;
		int[] columnsWidth = { DELETE_BUTTON_WIDTH + 10, 45, width + 20, width, width + 40, width - 35, width, width - 35 };

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

		AggregationRowConfig<ProgressDto> totalSummary = new AggregationRowConfig<ProgressDto>();
		totalSummary.setHtml(ProgressDto.DATE, messages.totalSituation());
		totalSummary.setCellStyle(ProgressDto.DATE, "cellStyleAggRow");
		for (int i = 0; i < ids.length; i++) {
			final String columnId = ids[i];
			SummaryColumnConfig column = new SummaryColumnConfig<Number>(columnId, columnId, columnsWidth[i]);
			column.setToolTip(headers[i]);
			column.setHeader("<center>" + headers[i] + "</center>");
			column.setMenuDisabled(true);
			if (i < 4) {
				column.setAlignment(HorizontalAlignment.CENTER);
			} else {
				column.setAlignment(HorizontalAlignment.RIGHT);
			}
			columns.add(column);
			if (ProgressDto.MOIS.equalsIgnoreCase(columnId) || ProgressDto.MOIS2.equalsIgnoreCase(columnId)) {

				// Mois = Giá trị zone culmulé ( cùng dòng ) - giá tri zone cumulé của dòng truớc nó
				column.setRenderer(new GridCellRenderer<ProgressDto>() {
					@Override
					public Object render(ProgressDto model, String property, ColumnData config, int rowIndex, int colIndex,
							ListStore<ProgressDto> store, Grid<ProgressDto> grid) {
						double result = 0.0;
						List<ProgressDto> progressModels = progressGrid.getStore().getModels();
						if (rowIndex == 0) {
							if (ProgressDto.MOIS.equalsIgnoreCase(columnId)) {
								result = model.getCumule();
							} else {
								result = model.getCumule2();
							}
							if (ProgressDto.MOIS.equalsIgnoreCase(columnId))
								progressModels.get(rowIndex).setMois(result);
							else
								progressModels.get(rowIndex).setMois2(result);
						} else {
							ProgressDto previousModel = grid.getStore().getAt(rowIndex - 1);
							if (ProgressDto.MOIS.equalsIgnoreCase(columnId)) {
								result = model.getCumule() - previousModel.getCumule();
							} else {
								result = model.getCumule2() - previousModel.getCumule2();
							}
							if (ProgressDto.MOIS.equalsIgnoreCase(columnId))
								progressModels.get(rowIndex).setMois(result);
							else
								progressModels.get(rowIndex).setMois2(result);
						}

						return NUMBER_FORMAT.format(result);
					}
				});
				// Total summary columns
				totalSummary.setCellStyle(columnId, "cellStyleAggRow");
				totalSummary.setSummaryFormat(columnId, NUMBER_FORMAT);
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						double sumMois = 0.0;
						double sumMois2 = 0.0;
						double sumCumule = 0.0;
						double sumCumule2 = 0.0;
						ProgressDto progressDto = null;
						for (int i = 0; i < progressGrid.getStore().getModels().size(); i++) {
							progressDto = progressGrid.getStore().getModels().get(i);
							sumMois += progressDto.getMois();
							sumMois2 += progressDto.getMois2();
							sumCumule = progressDto.getCumule();
							sumCumule2 = progressDto.getCumule2();
						}
						// For Report
						mois = sumMois + Constants.SEPRATE + sumMois2;
						txt_advancement_mois.setValue(mois);
						txttotalsituation.setValue(append(sumCumule, sumMois, sumCumule2, sumMois2));
						// End
						if (columnId.equalsIgnoreCase(ProgressDto.MOIS))
							return sumMois;
						return sumMois2;
					}
				});
			} else if (ProgressDto.CUMULE.equalsIgnoreCase(columnId) || ProgressDto.CUMULE2.equalsIgnoreCase(columnId)) { // Display value of last row
				totalSummary.setCellStyle(columnId, "cellStyleAggRow"); // only
				totalSummary.setSummaryFormat(columnId, NUMBER_FORMAT);
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						Object obj = m.get(field);
						if (obj != null) {
							return ((Number) obj).doubleValue();
						}
						return 0.0;
					}
				});
			}
		}

		// End
		ColumnModel cm = new ColumnModel(columns);
		cm.addAggregationRow(totalSummary);

		ColumnConfig column = cm.getColumn(0);
		column.setRenderer(createDeleteButtonRenderer());

		column = cm.getColumn(1);
		column.setRenderer(new GridCellRenderer<ProgressDto>() {
			@Override
			public Object render(ProgressDto model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<ProgressDto> store,
					Grid<ProgressDto> grid) {
				return rowIndex + 1;
			}
		});

		column = cm.getColumn(2);
		column.setRenderer(createTextFieldRenderer());
		column.setEditor(new CellEditor(createTextField(MAX_LENGTH_3)));

		column = cm.getColumn(3);
		column.setDateTimeFormat(DATE_FORMAT);
		column.setRenderer(createDateRenderer(column.getWidth() - PADDING_2));

		column = cm.getColumn(4);
		column.setRenderer(createNumberRenderer());
		column.setEditor(new CellEditor(createNumberField(null)));

		column = cm.getColumn(6);
		column.setRenderer(createNumberRenderer());
		column.setEditor(new CellEditor(createNumberField(null)));

		cm.addHeaderGroup(0, 4, new HeaderGroupConfig(messages.avancement(), 1, 2));
		cm.addHeaderGroup(0, 6, new HeaderGroupConfig(messages.retenues(), 1, 2));

		ListStore<ProgressDto> store = new ListStore<ProgressDto>();
		progressGrid = new CustomEditorGrid<ProgressDto>(store, cm);
		progressGrid.setId("progressId");
		progressGrid.setHeight(150);
		progressGrid.setAutoExpandColumn(ProgressDto.MOIS2);
		progressGrid.setAutoExpandMax(200);
		progressGrid.setAutoExpandMin(118);
		progressGrid.getStore().addStoreListener(new StoreListener<ProgressDto>() {
			@Override
			public void handleEvent(StoreEvent<ProgressDto> e) {
				// progressGrid.getView().refresh(true);
				bus.fireEvent(new EtatAvancementEvent(listGestion, progressGrid.getStore().getModels()));
			}
		});

		progressGrid.addListener(Events.BeforeEdit, new Listener<GridEvent<ProgressDto>>() {
			@Override
			public void handleEvent(final GridEvent<ProgressDto> be) {
				if (isAdminOrContributor(role, user)) {
					be.setCancelled(false);
				} else {
					be.setCancelled(true);
				}
			}
		});
		return progressGrid;
	}

	private void createPenaltiesGrid() {
		String[] headers = { "", messages.amount(), messages.pourcentageDuRegularise() };
		String[] ids = { AnonymousDto.COLUMN_1, AnonymousDto.COLUMN_2, AnonymousDto.COLUMN_3 };
		int[] columnsWidth = { 260, 200, 280 };
		HorizontalAlignment[] horizontalAlignments = { HorizontalAlignment.CENTER, HorizontalAlignment.RIGHT, HorizontalAlignment.RIGHT };
		ColumnModel cm = createColumnModel(headers, ids, columnsWidth, horizontalAlignments);
		cm.getColumn(0).setRenderer(new GridCellRenderer<AnonymousDto>() {
			@Override
			public Object render(AnonymousDto model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<AnonymousDto> store,
					Grid<AnonymousDto> grid) {
				config.style = "background-color: " + HEADER_BACKGROUND_COLOR + ";";
				return "<center><label>" + model.get(property) + "</label></center>";
			}
		});

		cm.getColumn(1).setNumberFormat(NUMBER_FORMAT);
		cm.getColumn(2).setNumberFormat(PERCENTAGE_FORMAT);

		ListStore<AnonymousDto> store = new ListStore<AnonymousDto>();
		penaltyGrid = new CustomEditorGrid<AnonymousDto>(store, cm);
		penaltyGrid.setAutoHeight(true);
		penaltyGrid.setAutoExpandColumn(AnonymousDto.COLUMN_3);
		penaltyGrid.setAutoExpandMin(300);
		penaltyGrid.setAutoExpandMax(400);
	}

	@SuppressWarnings("rawtypes")
	private void createGrid3() {
		// grid canto badge total
		String[] headers = { "", messages.canto(), messages.badge(), messages.grue(), messages.lift(), messages.benne(), messages.nettoyage(),
				messages.autres() };
		String[] ids = { AnonymousDto.COLUMN_1, AnonymousDto.COLUMN_2, AnonymousDto.COLUMN_3, AnonymousDto.COLUMN_4, AnonymousDto.COLUMN_5,
				AnonymousDto.COLUMN_6, AnonymousDto.COLUMN_7, AnonymousDto.COLUMN_8 };
		int width = 90;
		int[] columnsWidth = { 150, width, width, width, width, width, width, width };
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		SummaryColumnConfig column = null;
		for (int i = 0; i < headers.length; i++) {
			column = new SummaryColumnConfig(ids[i], ids[i], columnsWidth[i]);
			column.setToolTip(headers[i]);
			column.setHeader("<center>" + headers[i] + "</center>");
			column.setMenuDisabled(true);
			if (AnonymousDto.COLUMN_1.equals(ids[i])) {
				column.setAlignment(HorizontalAlignment.CENTER);
			} else {
				column.setAlignment(HorizontalAlignment.RIGHT);
			}
			if (i > 0) {
				column.setNumberFormat(NUMBER_FORMAT);
			}
			columns.add(column);
		}

		ColumnModel cm = new ColumnModel(columns);

		cm.getColumn(0).setRenderer(new GridCellRenderer<AnonymousDto>() {
			@Override
			public Object render(AnonymousDto model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<AnonymousDto> store,
					Grid<AnonymousDto> grid) {
				config.style = "background-color: " + HEADER_BACKGROUND_COLOR + ";";
				return "<center><label>" + model.get(property) + "</label></center>";
			}
		});

		ListStore<AnonymousDto> store = new ListStore<AnonymousDto>();
		grid_3 = new CustomEditorGrid<AnonymousDto>(store, cm);
		grid_3.setHeight(70);
		grid_3.getView().setForceFit(true);
	}

	public List<ProgressDto> getProgressDtoList() {
		if (progressGrid == null) {
			return new ArrayList<ProgressDto>();
		} else {
			return progressGrid.getStore().getModels();
		}
	}

	@Override
	public void commitDataChange() {
		commitDataGrids(progressGrid, penaltyGrid, grid_3);
	}

	public void updateDataGrid(List<GestionDto> gestionDtoList, List<DeductionDto> deductionDtoList, List<PenaltyDto> penaltyDtoList,
			FicheStDto ficheStDto) {
		this.ficheStDto = ficheStDto;

		double totalAvenants = 0.00;
		if (gestionDtoList != null) {
			for (GestionDto gestionDto : gestionDtoList) {
				totalAvenants += gestionDto.getTraite();
			}
		}

		int cantoQuantity = 0;
		int badgeQuantity = 0;
		int grueQuantity = 0;
		int liftQuantity = 0;
		int benneQuantity = 0;
		int nettoyageQuantity = 0;
		double totalProrata = 0.00;
		double autres = 0.00;
		if (deductionDtoList != null) {
			for (DeductionDto deductionDto : deductionDtoList) {
				cantoQuantity += deductionDto.getCanto();
				badgeQuantity += deductionDto.getBadge();
				grueQuantity += deductionDto.getGrue();
				liftQuantity += deductionDto.getLift();
				benneQuantity += deductionDto.getBenne();
				nettoyageQuantity += deductionDto.getNettoyage();
				totalProrata += deductionDto.getProrata();
				autres += deductionDto.getAutres();
			}
		}
		double totalRefacturation = cantoQuantity * ficheStDto.getPrestaCanto() + badgeQuantity * ficheStDto.getPrestaBadge() + grueQuantity
				* ficheStDto.getPrestaGrue() + liftQuantity * ficheStDto.getPrestaLift() + benneQuantity * ficheStDto.getPrestaBenne()
				+ nettoyageQuantity * ficheStDto.getPrestaNettoyage();
		double refacturationPercentage = 0.00;
		if (totalAvenants != 0) {
			refacturationPercentage = totalRefacturation / totalAvenants;
		}

		double prorataPercentage = 0.00;
		if (totalAvenants != 0) {
			prorataPercentage = totalProrata / totalAvenants;
		}

		double totalRefacturationDontProrata = totalRefacturation + totalProrata;
		double refacturationDontProrataPercentage = 0.00;
		if (totalAvenants != 0) {
			refacturationDontProrataPercentage = totalRefacturationDontProrata / totalAvenants;
		}

		double refacturationsDontProrataEtAutre = totalRefacturationDontProrata + autres;
		double refacturationsDontProrataEtAutrePercentage = 0.00;
		if (totalAvenants != 0) {
			refacturationsDontProrataEtAutrePercentage = refacturationsDontProrataEtAutre / totalAvenants;
		}

		double totalPenalty = 0.00;
		if (penaltyDtoList != null) {
			for (PenaltyDto penaltyDto : penaltyDtoList) {
				totalPenalty += penaltyDto.getAmount();
			}
		}
		double penaltyPercentage = 0.00;
		if (totalAvenants != 0) {
			penaltyPercentage = totalPenalty / totalAvenants;
		}

		penaltyGrid.getStore().removeAll();

		List<AnonymousDto> models = new ArrayList<AnonymousDto>();

		AnonymousDto model = null;

		String[] column1s = { messages.refacturations(), messages.prorata(), messages.refacturationsDontProrata(),
				messages.refacturationsDontProrataEtAutre(), messages.penalites() };
		double[] column2s = { totalRefacturation, totalProrata, totalRefacturationDontProrata, refacturationsDontProrataEtAutre, totalPenalty };
		double[] column3s = { refacturationPercentage, prorataPercentage, refacturationDontProrataPercentage,
				refacturationsDontProrataEtAutrePercentage, penaltyPercentage };
		// For report
		detail_des_retenues_appliques = "";
		for (int i = 0; i < 5; i++) {
			model = new AnonymousDto();
			model.set(AnonymousDto.COLUMN_1, column1s[i]);
			model.set(AnonymousDto.COLUMN_2, column2s[i]);
			model.set(AnonymousDto.COLUMN_3, column3s[i]);
			// For report
			detail_des_retenues_appliques += append(column2s[i], (NUMBER_FORMAT.format(column3s[i] * 100) + "%"));
			models.add(model);
		}
		// For Report
		if (detail_des_retenues_appliques != null && detail_des_retenues_appliques.length() > 0) {
			detail_des_retenues_appliques = detail_des_retenues_appliques.substring(0,
					detail_des_retenues_appliques.length() - Constants.SEPRATE.length());
		}

		txtdetail_des_retenues_appliques.setValue(detail_des_retenues_appliques);
		txtdetail_des_retenues_appliques.hide();

		penaltyGrid.getStore().add(models);
	}

	public void setComment(String value) {
		comment.setValue(value);
	}

	private void bindModel(FicheStDto ficheStDto) {
		if (ficheStDto == null) {
			return;
		}

		FormBinding binding = new FormBinding(formPanel);

		// Auto binding fields
		binding.autoBind();
		binding.bind(ficheStDto);
	}

	public void setModel(FicheStDto model) {
		this.ficheStDto = model;
		if (model == null) {
			return;
		}

		setProgressLabelText(this.ficheStDto.getAvctBAPercentage());
		setProgressDtoList(this.ficheStDto.getProgresses());
		setComment(this.ficheStDto.getAvctCommentaires());
		updateDeductionGrid(this.ficheStDto);

		bindModel(this.ficheStDto);
	}

	/**
	 * Init parameters for report
	 */
	private void initFieldReport() {
		// Report set hide TextField
		txtEtatAvancement = new TextField<String>();
		txtEtatAvancement.setId("AVANCEMENTS_PANEL_TXT_ETATAVANCEMENT_ID");
		txtEtatAvancement.hide();
		add(txtEtatAvancement);

		txttotalsituation = new TextField<String>();
		txttotalsituation.setId("AVANCEMENTS_PANEL_TXTTOTALSITUATION_ID");
		txttotalsituation.hide();
		add(txttotalsituation);

		txtdetail_des_retenues_appliques = new TextField<String>();
		txtdetail_des_retenues_appliques.setId("AVANCEMENTS_PANEL_TXTDETAIL_DES_RETENUES_APPLIQUES_ID");
		txtdetail_des_retenues_appliques.hide();
		add(txtdetail_des_retenues_appliques);

		txtgrid_3_report = new TextField<String>();
		txtgrid_3_report.setValue(grid_3_report);
		txtgrid_3_report.setId("AVANCEMENTS_PANEL_TXTGRID3REPORT_ID");
		txtgrid_3_report.hide();
		add(txtgrid_3_report);

		txt_advancement_mois = new TextField<String>();
		txt_advancement_mois.setId("AVANCEMENTS_PANEL_TXT_MOIS_ID");
		txt_advancement_mois.hide();
		add(txt_advancement_mois);
		// End
	}
}
