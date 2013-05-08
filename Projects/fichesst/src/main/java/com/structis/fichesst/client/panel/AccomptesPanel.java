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
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
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
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.structis.fichesst.client.constant.ConstantClient;
import com.structis.fichesst.client.event.ConducteurEvent;
import com.structis.fichesst.client.event.DeductionGridUpdateEvent;
import com.structis.fichesst.client.event.ExportSuiviDesAccomptesPanelEvent;
import com.structis.fichesst.client.event.PenaltyGridUpdateEvent;
import com.structis.fichesst.client.event.PrestationEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.client.event.SocieteEvent;
import com.structis.fichesst.client.handler.ConducteurHandler;
import com.structis.fichesst.client.handler.ExportSuiviDesAccomptesPanelHandler;
import com.structis.fichesst.client.handler.PrestationHandler;
import com.structis.fichesst.client.handler.SocieteHandler;
import com.structis.fichesst.client.util.NameValuePair;
import com.structis.fichesst.client.util.ReportUtil;
import com.structis.fichesst.client.widget.CustomEditorGrid;
import com.structis.fichesst.client.widget.CustomFieldSet;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.shared.dto.AbstractDto;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.LotTypeDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.util.Constants;

public class AccomptesPanel extends AbstractPanel {

	private EditorGrid<DeductionDto> deductionGrid = null;

	private EditorGrid<PenaltyDto> penaltyGrid = null;
	// For Report
	private String totaldeduction;
	private final String chantier;
	private String responsables;
	private final String societe;
	private final TextField<String> txtTotalDeduction;

	private final Integer ficheStId;
	private final HTML saveButton;
	private final HTML addRow = new HTML("<img src='./images/ajouter.png'/> " + messages.addRow(), true);
	private final HTML addRow2 = new HTML("<img src='./images/ajouter.png'/> " + messages.addRow(), true);
	private final TextArea comment = new TextArea();
	private final TextArea internalComment = new TextArea();

	private final RoleModel role;
	private final UtilisateurGrpModel user;

	private final double[] ratioList = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	private final FormPanel formPanel = new CustomFormPanel();

	public AccomptesPanel(SimpleEventBus b, Integer ficheStId, RoleModel roleModel, UtilisateurGrpModel utilisateurGrpModel) {
		super();
		this.bus = b;
		this.ficheStId = ficheStId;
		this.role = roleModel;
		this.user = utilisateurGrpModel;
		FieldSet paymentFieldSet = new CustomFieldSet();
		paymentFieldSet.setCollapsible(true);
		setDefaultBackgroundColor(paymentFieldSet);
		paymentFieldSet.setHeading(messages.accomptes());
		TableLayout tl12 = new TableLayout(1);
		tl12.setWidth("100%");
		paymentFieldSet.setLayout(tl12);

		LayoutContainer accomptesPanel1 = new LayoutContainer();
		TableLayout tl_accomptesPanel1 = new TableLayout(2);
		tl_accomptesPanel1.setWidth("98%");
		tl_accomptesPanel1.setCellHorizontalAlign(HorizontalAlignment.RIGHT);
		accomptesPanel1.setLayout(tl_accomptesPanel1);
		saveButton = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm());
		saveButton.setStyleName("actionHTML");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new SaveFicheStEvent());
			}
		});
		// Report
		txtTotalDeduction = new TextField<String>();
		txtTotalDeduction.setId("ACCOMPTES_PANEL_TOTALDEDUCTION_ID");
		txtTotalDeduction.setValue(totaldeduction);
		txtTotalDeduction.hide();
		add(txtTotalDeduction);

		accomptesPanel1.add(saveButton);

		bus.addHandler(ExportSuiviDesAccomptesPanelEvent.TYPE, new ExportSuiviDesAccomptesPanelHandler() {

			@Override
			public void onExport(ExportSuiviDesAccomptesPanelEvent event) {
				exportAccomptesPanel();
			}
		});

		HTML printPayment = new HTML("<img src='./images/imprimer.png'/> " + messages.printAcomptes());
		printPayment.setStyleName("actionHTML");
		printPayment.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				exportAccomptesPanel();
			}
		});

		TableData td = new TableData();
		td.setWidth("230px");
		accomptesPanel1.add(printPayment, td);
		paymentFieldSet.add(accomptesPanel1);

		LayoutContainer accomptesPanel2 = new LayoutContainer();
		TableLayout tl3 = new TableLayout(3);
		tl3.setWidth("100%");
		tl3.setCellHorizontalAlign(HorizontalAlignment.LEFT);
		accomptesPanel2.setLayout(tl3);
		HTML chantier2 = new HTML("<label>" + messages.chantier() + ":</label>" + SPACES + "Chantier 1", true);
		// Report
		chantier = "Chantier 1";
		TableData td2 = new TableData();
		td2.setWidth("300px");
		accomptesPanel2.add(chantier2, td2);

		String conducter = navigation.getContext().getMapConducteurdetravaux().get(ficheStId);
		if (conducter == null)
			conducter = "";
		final HTML responsable = new HTML("<label>" + messages.responsable() + ":</label>" + SPACES + conducter, true);
		bus.addHandler(ConducteurEvent.TYPE, new ConducteurHandler() {
			@Override
			public void onChangeConducteur(ConducteurEvent conducteurEvent) {
				String conducter = conducteurEvent.getConducter() != null ? conducteurEvent.getConducter() : "";
				responsable.setHTML("<label>" + messages.responsable() + ":</label>" + SPACES + conducter);
				responsables = conducteurEvent.getConducter();
			}
		});
		// Report
		responsables = "";
		TableData td3 = new TableData();
		td3.setWidth("400px");
		accomptesPanel2.add(responsable, td3);

		String societe_ = navigation.getContext().getMapSociete().get(ficheStId);
		if (societe_ == null)
			societe_ = "";
		final HTML societe2 = new HTML("<label>" + messages.societe() + ":</label>" + SPACES + societe_, true);
		bus.addHandler(SocieteEvent.TYPE, new SocieteHandler() {
			@Override
			public void onChangeSociete(SocieteEvent societeEvent) {
				String societe = societeEvent.getSociete() != null ? societeEvent.getSociete() : "";
				societe2.setHTML("<label>" + messages.societe() + ":</label>" + SPACES + societe);
				societe = societeEvent.getSociete();

			}
		});
		societe = "";

		accomptesPanel2.add(societe2);
		paymentFieldSet.add(accomptesPanel2);

		FieldSet deductionsPaymentsFieldSet = new FieldSet();
		deductionsPaymentsFieldSet.setWidth("100%");
		deductionsPaymentsFieldSet.setCollapsible(true);
		setBackgroundColor(deductionsPaymentsFieldSet, BACKGROUD_COLOR_2);
		TableLayout tl1 = new TableLayout(1);
		tl1.setWidth("100%");
		deductionsPaymentsFieldSet.setLayout(tl1);
		deductionsPaymentsFieldSet.setHeading(messages.retenuesEffectuees());

		createDeductionGrid();
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
							// bus.fireEvent(new
							// DeductionGridUpdateEvent(deductionGrid.getStore().getModels()));
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
		// penaltyFielSet.setWidth("80%");
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
		// internalComment.setWidth("120%");
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

		if ((user.getBadmin() != null && user.getBadmin()) || (role.getBcontributeur() != null && role.getBcontributeur())) {
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

	private void createPenaltyGrid() {
		String[] headers = { messages.del(), messages.date(), messages.amount().replaceAll(" ", "<br>"), messages.comment() };
		String[] ids = { PenaltyDto.ID, PenaltyDto.DATE, PenaltyDto.AMOUNT, PenaltyDto.COMMENT };
		final int width1 = 170;
		final int width2 = 140;
		final int width3 = 380;
		int[] columnsWidth = { DELETE_BUTTON_WIDTH + 5, width1, width2, width3 };
		HorizontalAlignment[] horizontalAlignments = { HorizontalAlignment.CENTER };
		ColumnModel cm = createColumnModel(headers, ids, columnsWidth, horizontalAlignments);

		AggregationRowConfig<PenaltyDto> totalSummary = new AggregationRowConfig<PenaltyDto>();
		totalSummary.setHtml(PenaltyDto.DATE, messages.total());

		// Total summary
		totalSummary.setSummaryType(PenaltyDto.AMOUNT, SummaryType.SUM);
		totalSummary.setSummaryFormat(PenaltyDto.AMOUNT, NumberFormat.getCurrencyFormat());
		cm.addAggregationRow(totalSummary);

		GridCellRenderer<AbstractDto> textAreaRenderer = createTextAreaRendererWithPermission(width3 - PADDING_2, 38, role, user);

		cm.getColumn(0).setRenderer(createDeleteButtonRenderer());

		cm.getColumn(1).setRenderer(createDateRendererWithPermision(width1 - PADDING_2, role, user));
//		cm.getColumn(1).setEditor(new CellEditor(createDateField()));

		cm.getColumn(2).setRenderer(createNumberRendererWithPermission(width2 - PADDING_2, role, user));
		NumberField numberField = createNumberField(null);
		cm.getColumn(2).setEditor(new CellEditor(numberField));

		cm.getColumn(3).setRenderer(textAreaRenderer);
		cm.getColumn(3).setEditor(new CellEditor(new TextArea()));

		ListStore<PenaltyDto> store = new ListStore<PenaltyDto>();
		penaltyGrid = new CustomEditorGrid<PenaltyDto>(store, cm);
		penaltyGrid.getView().setAutoFill(true);
		penaltyGrid.setHeight("210px");

		penaltyGrid.getStore().addStoreListener(new StoreListener<PenaltyDto>() {
			@Override
			public void handleEvent(StoreEvent<PenaltyDto> e) {
				penaltyGrid.getView().refresh(true);
				bus.fireEvent(new PenaltyGridUpdateEvent(penaltyGrid.getStore().getModels()));
			}
		});

		penaltyGrid.addListener(Events.BeforeEdit, new Listener<GridEvent<PenaltyDto>>() {
			@Override
			public void handleEvent(final GridEvent<PenaltyDto> be) {
				if ((user.getBadmin() != null && user.getBadmin()) || (role != null && role.getBcontributeur() != null && role.getBcontributeur())) {

					be.setCancelled(false);
				} else {
					be.setCancelled(true);
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createDeductionGrid() {
		final NumberFormat numberFormat = NumberFormat.getFormat(NUMBER_FORMAT);
		final String[] headers = { "<br><br>" + messages.del(), "<br><br>" + messages.date(),
				messages.canto() + "<br>" + numberFormat.format(ratioList[0]), messages.badge() + "<br>" + numberFormat.format(ratioList[1]),
				messages.grue() + "<br>" + numberFormat.format(ratioList[2]), messages.lift() + "<br>" + numberFormat.format(ratioList[3]),
				messages.benne() + "<br>" + numberFormat.format(ratioList[4]), messages.nettoyage() + "<br>" + numberFormat.format(ratioList[5]),
				messages.autres() + "<br>-", messages.prorata() + "<br>" + numberFormat.format(ratioList[6]), "<br><br>" + messages.refacturations() };
		bus.addHandler(PrestationEvent.TYPE, new PrestationHandler() {
			@Override
			public void onChangePrestation(PrestationEvent prestationEvent) {
				switch (prestationEvent.getIndex()) {
				case 0: // messages.canto()
					ratioList[0] = prestationEvent.getValue();
					deductionGrid.getColumnModel().setColumnHeader(2, messages.canto() + "<br>" + numberFormat.format(ratioList[0]));
					deductionGrid.getView().refresh(true);
					break;
				case 1: // messages.badge()
					ratioList[1] = prestationEvent.getValue();
					deductionGrid.getColumnModel().setColumnHeader(3, messages.badge() + "<br>" + numberFormat.format(ratioList[1]));
					deductionGrid.getView().refresh(true);
					break;

				case 2: // messages.grue()
					ratioList[2] = prestationEvent.getValue();
					deductionGrid.getColumnModel().setColumnHeader(4, messages.grue() + "<br>" + numberFormat.format(ratioList[2]));
					deductionGrid.getView().refresh(true);
					break;
				case 3: // messages.lift()
					ratioList[3] = prestationEvent.getValue();
					deductionGrid.getColumnModel().setColumnHeader(5, messages.lift() + "<br>" + numberFormat.format(ratioList[3]));
					deductionGrid.getView().refresh(true);
					break;
				case 4: // messages.benne()
					ratioList[4] = prestationEvent.getValue();
					deductionGrid.getColumnModel().setColumnHeader(6, messages.benne() + "<br>" + numberFormat.format(ratioList[4]));
					deductionGrid.getView().refresh(true);
					break;
				case 5: // messages.nettoyage()
					ratioList[5] = prestationEvent.getValue();
					deductionGrid.getColumnModel().setColumnHeader(7, messages.nettoyage() + "<br>" + numberFormat.format(ratioList[5]));
					deductionGrid.getView().refresh(true);
					break;
				case 6: // messages.prorata()
					ratioList[6] = prestationEvent.getValue();
					deductionGrid.getColumnModel().setColumnHeader(9, messages.prorata() + "<br>" + numberFormat.format(ratioList[6]));
					deductionGrid.getView().refresh(true);
					break;
				}
			}
		});
		if (ratioList[0] == 1 && ratioList[1] == 1 && ratioList[2] == 1 && ratioList[3] == 1 && ratioList[4] == 1 && ratioList[5] == 1
				&& ratioList[6] == 1) {
			Map<Integer, String> mapPrestations = navigation.getContext().getMapPrestations();
			String prestationValue = mapPrestations.get(ficheStId);
			if (prestationValue != null && prestationValue.length() > 0) {
				String arrRatio[] = prestationValue.split(Constants.SEPRATE);
				ratioList[0] = new Double(arrRatio[0]);
				ratioList[1] = new Double(arrRatio[1]);
				ratioList[2] = new Double(arrRatio[2]);
				ratioList[3] = new Double(arrRatio[3]);
				ratioList[4] = new Double(arrRatio[4]);
				ratioList[5] = new Double(arrRatio[5]);
				ratioList[6] = new Double(arrRatio[6]);

				headers[2] = messages.canto() + "<br>" + numberFormat.format(ratioList[0]);
				headers[3] = messages.badge() + "<br>" + numberFormat.format(ratioList[1]);
				headers[4] = messages.grue() + "<br>" + numberFormat.format(ratioList[2]);
				headers[5] = messages.lift() + "<br>" + numberFormat.format(ratioList[3]);
				headers[6] = messages.benne() + "<br>" + numberFormat.format(ratioList[4]);
				headers[7] = messages.nettoyage() + "<br>" + numberFormat.format(ratioList[5]);
				headers[9] = messages.prorata() + "<br>" + numberFormat.format(ratioList[6]);
			}
		}
		final String[] ids = { DeductionDto.ID, DeductionDto.DATE, DeductionDto.CANTO, DeductionDto.BADGE, DeductionDto.GRUE, DeductionDto.LIFT,
				DeductionDto.BENNE, DeductionDto.NETTOYAGE, DeductionDto.AUTRES, DeductionDto.PRORATA, DeductionDto.REFACTURATIONS };
		int commonWidth = 112;
		int[] columnsWidth = { DELETE_BUTTON_WIDTH, commonWidth + 20, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth,
				commonWidth, commonWidth, commonWidth };
		HorizontalAlignment[] horizontalAlignments = { HorizontalAlignment.CENTER };

		AggregationRowConfig<DeductionDto> totalSummary = new AggregationRowConfig<DeductionDto>();
		totalSummary.setHtml(DeductionDto.DATE, messages.total());
		// For Report
		totaldeduction = "";
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		for (int i = 0; i < ids.length; i++) {
			String columnId = ids[i];
			SummaryColumnConfig column = new SummaryColumnConfig(columnId, headers[i], columnsWidth[i]);
			column.setAlignment(horizontalAlignments[0]);
			column.setMenuDisabled(true);

			columns.add(column);

			if (i == 0) {
				column.setRenderer(createDeleteButtonRenderer());
			} else if (i == 1) {
				column.setRenderer(createDateRendererWithPermision(column.getWidth() - PADDING_2, role, user));
//				column.setEditor(new CellEditor(createDateField()));
			} else if (i <= 7) {
				column.setRenderer(createIntegerRendererWithPermission(commonWidth - PADDING, role, user));
				NumberField numberField = createIntegerField(null);
				numberField.setPropertyEditorType(Integer.class);
				column.setEditor(new CellEditor(numberField));
			} else {
				column.setRenderer(createNumberRendererWithPermission(commonWidth - PADDING, role, user));
				NumberField numberField = createNumberField(null);
				if (i == 9) {
					numberField.setMinValue(0);
					numberField.setMaxValue(100);
					column.setEditor(new CellEditor(numberField));
				}
			}

			if (i > 1 && i != 9 && i != 10) {
				// Total summary columns
				totalSummary.setSummaryFormat(columnId, NumberFormat.getFormat(NUMBER_FORMAT));
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						if (v == null) {
							v = 0.0;
						}
						Object obj = m.get(field);
						if (obj != null) {
							double ratio = findRatio(field);
							totaldeduction += numberFormat.format(((Double) v) + ((Number) obj).doubleValue() * ratio) + Constants.SEPRATE;
							return ((Double) v) + ((Number) obj).doubleValue() * ratio;
						}
						totaldeduction += numberFormat.format(((Double) v)) + Constants.SEPRATE;
						return ((Double) v);
					}

					private double findRatio(String field) {
						for (int i = 0; i <= ids.length; i++) {
							String columnId = ids[i];
							if (columnId.equalsIgnoreCase(field)) {
								if (DeductionDto.AUTRES.equalsIgnoreCase(field)) {
									return 1.0;
								} else {
									return ratioList[i - 2];
								}

							}
						}
						return 0.0;
					}
				});
			}
			if (i == 9) {
				// Total summary columns
				totalSummary.setSummaryFormat(columnId, NumberFormat.getFormat(NUMBER_FORMAT));
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						if (v == null) {
							v = 0.0;
						}
						Object obj = m.get(field);
						if (obj != null) {
							totaldeduction += numberFormat.format(((Double) v) + ((Number) obj).doubleValue()) + Constants.SEPRATE;
							return ((Double) v) + ((Number) obj).doubleValue() * ratioList[6];
						}
						totaldeduction += numberFormat.format(((Double) v)) + Constants.SEPRATE;
						return ((Double) v);
					}
				});

			}
			if (i == 10) {
				// Total summary columns
				totalSummary.setSummaryFormat(columnId, NumberFormat.getFormat(NUMBER_FORMAT));
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						if (v == null) {
							v = 0.0;
						}
						Object obj = m.get(field);
						if (obj != null) {
							totaldeduction += numberFormat.format(((Double) v) + ((Number) obj).doubleValue()) + Constants.SEPRATE;
							return ((Double) v) + ((Number) obj).doubleValue();
						}
						totaldeduction += numberFormat.format(((Double) v)) + Constants.SEPRATE;
						return ((Double) v);
					}
				});
			}
		}

		// For Report
		txtTotalDeduction.setId("ACCOMPTES_PANEL_TOTALDEDUCTION_ID");
		txtTotalDeduction.setValue(totaldeduction);
		ColumnModel cm = new ColumnModel(columns);
		cm.addHeaderGroup(0, 2, new HeaderGroupConfig(messages.quantity().toUpperCase(), 1, 6));
		cm.addHeaderGroup(0, 8, new HeaderGroupConfig(messages.amount2().toUpperCase(), 1, 2));
		cm.addAggregationRow(totalSummary);

		ListStore<DeductionDto> store = new ListStore<DeductionDto>();
		deductionGrid = new CustomEditorGrid(store, cm);
		deductionGrid.setId("ACCOMPTES_PANEL_DEDUCTION_GRID_ID");
		deductionGrid.setHeight(240);
		deductionGrid.getStore().addStoreListener(new StoreListener<DeductionDto>() {
			@Override
			public void handleEvent(StoreEvent<DeductionDto> e) {
				deductionGrid.getView().refresh(true);
				bus.fireEvent(new DeductionGridUpdateEvent(deductionGrid.getStore().getModels()));
			}
		});

		deductionGrid.addListener(Events.BeforeEdit, new Listener<GridEvent<DeductionDto>>() {
			@Override
			public void handleEvent(final GridEvent<DeductionDto> be) {
				if ((user.getBadmin() != null && user.getBadmin()) || (role.getBcontributeur() != null && role.getBcontributeur())) {
					be.setCancelled(false);
				} else {
					be.setCancelled(true);
				}
			}
		});
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

	@SuppressWarnings("unchecked")
	private void exportAccomptesPanel() {
		TextField<String> societte = (TextField<String>) ComponentManager.get().get("INFORMATION_PANEL_SOCIETE_ID");
		TextField<String> lot = (TextField<String>) ComponentManager.get().get("INFORMATION_PANEL_LOT_ID");
		ComboBox<LotTypeDto> lotType = (ComboBox<LotTypeDto>) ComponentManager.get().get("INFORMATION_PANEL_LOT_TYPE_ID");
		TextField<String> sitravaux = (TextField<String>) ComponentManager.get().get("INFORMATION_PANEL_SITRAVAUX_ID");
		NumberField montant = (NumberField) ComponentManager.get().get("INFORMATION_PANEL_MONTANT_ID");
		List<NameValuePair> values = new ArrayList<NameValuePair>();

		List<DeductionDto> listDeduction = deductionGrid.getStore().getModels();
		DeductionDto deductionDto = null;
		String deductions = "";
		double amount = 0.0;
		String information = "";
		// Grid 1
		NumberFormat numberFormat = NumberFormat.getFormat(NUMBER_FORMAT);
		double canto = 0.0;
		double badge = 0.0;
		double grue = 0.0;
		double lift = 0.0;
		double benne = 0.0;
		double nettoyage = 0.0;
		double autres = 0.0; // khong co nhan
		double prorata = 0.0;
		double refacturations = 0.0;// khong co nhan
		for (int j = 0; j < listDeduction.size(); j++) {
			deductionDto = listDeduction.get(j);
			/*
			 * deductions += DateTimeFormat.getFormat(Constants.DATE_FORMAT).format (deductionDto.getDate()) + Constants.SEPRATE +
			 * numberFormat.format(deductionDto.getCanto()) + Constants.SEPRATE + numberFormat.format(deductionDto.getBadge()) + Constants.SEPRATE +
			 * numberFormat.format(deductionDto.getGrue()) + Constants.SEPRATE + numberFormat.format(deductionDto.getLift()) + Constants.SEPRATE +
			 * numberFormat.format(deductionDto.getBenne()) + Constants.SEPRATE + numberFormat.format(deductionDto.getNettoyage()) + Constants.SEPRATE
			 * + numberFormat.format(deductionDto.getAutres()) + Constants.SEPRATE + numberFormat.format(deductionDto.getProrata()) +
			 * Constants.SEPRATE + numberFormat.format(deductionDto.getRefacturations()) + Constants.SEPRATE;
			 */
			deductions += append(deductionDto.getDate(), deductionDto.getCanto(), deductionDto.getBadge(), deductionDto.getGrue(),
					deductionDto.getLift(), deductionDto.getBenne(), deductionDto.getNettoyage(), deductionDto.getAutres(),
					deductionDto.getProrata(), deductionDto.getRefacturations());

			canto += deductionDto.getCanto();
			badge += deductionDto.getBadge();
			grue += deductionDto.getGrue();
			lift += deductionDto.getLift();
			benne += deductionDto.getBenne();
			nettoyage += deductionDto.getNettoyage();
			autres += deductionDto.getAutres();
			prorata += deductionDto.getProrata();
			refacturations += deductionDto.getRefacturations();
		}
		canto = canto * ratioList[0];
		badge = badge * ratioList[1];
		grue = grue * ratioList[2];
		lift = lift * ratioList[3];
		benne = benne * ratioList[4];
		nettoyage = nettoyage * ratioList[5];
		prorata = prorata * ratioList[6];
		values.add(new NameValuePair(ConstantClient.DEDUCTIONDTO_ID_STR, deductions));
		String paramPrestation = append(ratioList[0], ratioList[1], ratioList[2], ratioList[3], ratioList[4], ratioList[5], ratioList[6]);
		values.add(new NameValuePair("paramPrestation", paramPrestation));
		canto += deductionDto.getCanto();
		badge += deductionDto.getBadge();
		grue += deductionDto.getGrue();
		lift += deductionDto.getLift();
		benne += deductionDto.getBenne();
		nettoyage += deductionDto.getNettoyage();
		autres += deductionDto.getAutres();
		prorata += deductionDto.getProrata();

		information += chantier + Constants.SEPRATE + responsables + Constants.SEPRATE + societe;
		values.add(new NameValuePair("information", information));

		String generaleInformation = chantier + Constants.SEPRATE + lot.getValue() + Constants.SEPRATE + sitravaux.getValue() + Constants.SEPRATE
				+ societte.getValue() + Constants.SEPRATE + lotType.getValue().getName() + Constants.SEPRATE + montant.getValue();
		values.add(new NameValuePair("generaleInformation", generaleInformation));
		/*
		 * if(totaldeduction != "") totaldeduction = totaldeduction.substring(0, totaldeduction.length() - Constants.SEPRATE.length());
		 */
		totaldeduction = append(canto, badge, grue, lift, benne, nettoyage, autres, prorata, refacturations);
		values.add(new NameValuePair("totaldeduction", totaldeduction));

		List<PenaltyDto> listPenalty = penaltyGrid.getStore().getModels();
		PenaltyDto penaltyDto = null;
		String penaltys = "";
		String penaltyDate = "";
		// Grid 1
		for (int j = 0; j < listPenalty.size(); j++) {
			penaltyDto = listPenalty.get(j);
			penaltyDate = penaltyDto.getDate() != null ? DateTimeFormat.getFormat(Constants.DATE_FORMAT).format(penaltyDto.getDate()) : "";
			penaltys += penaltyDate + Constants.SEPRATE + numberFormat.format(penaltyDto.getAmount()) + Constants.SEPRATE + penaltyDto.getComment()
					+ Constants.SEPRATE;
			amount += penaltyDto.getAmount();
		}
		if (penaltys != null && penaltys.length() > 0)
			penaltys = penaltys.substring(0, penaltys.length() - Constants.SEPRATE.length());
		values.add(new NameValuePair(ConstantClient.PENALTYDTO_ID_STR, penaltys));

		values.add(new NameValuePair("amount", amount + ""));
		values.add(new NameValuePair("commentaire", comment.getValue()));
		values.add(new NameValuePair("internalCommentaire", internalComment.getValue()));
		String exportPdfUrl = GWT.getHostPageBaseURL() + "list_accomptesdto.pdf";
		ReportUtil.showReport(exportPdfUrl, values.toArray(new NameValuePair[values.size()]));
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

		FormBinding binding = new FormBinding(formPanel);

		// Auto binding fields
		binding.autoBind();
		binding.bind(ficheStDto);
	}

	public void setModel(FicheStDto ficheStDto) {
		if (ficheStDto == null) {
			return;
		}

		setDeductionDtoList(ficheStDto.getDeductions());
		setPenaltyDtoList(ficheStDto.getPenalties());
		setComment(ficheStDto.getAcptCommentaires());
		setInternalComment(ficheStDto.getAcptCommentairesInternes());
		bindModel(ficheStDto);
	}
}
