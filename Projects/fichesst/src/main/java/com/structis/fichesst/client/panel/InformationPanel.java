package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.binding.FieldBinding;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.structis.fichesst.client.event.ConducteurEvent;
import com.structis.fichesst.client.event.PrestationEvent;
import com.structis.fichesst.client.event.SocieteEvent;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.handler.ConducteurHandler;
import com.structis.fichesst.client.service.ClientLotTypeServiceAsync;
import com.structis.fichesst.client.service.ClientRefDdeAgrementServiceAsync;
import com.structis.fichesst.client.service.ClientRefDecenaleServiceAsync;
import com.structis.fichesst.client.service.ClientRefDgdPresenteServiceAsync;
import com.structis.fichesst.client.service.ClientRefModePaiementServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.client.widget.CustomComboBox;
import com.structis.fichesst.client.widget.CustomEditorGrid;
import com.structis.fichesst.client.widget.CustomFieldSet;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.client.widget.CustomTextField;
import com.structis.fichesst.shared.dto.AbstractDto;
import com.structis.fichesst.shared.dto.CautionFournieDto;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.LotDto;
import com.structis.fichesst.shared.dto.LotTypeDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.SimpleDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class InformationPanel extends AbstractPanel {

	private static final String ANCHOR_SPEC_1 = "100%";

	private static final String HEIGHT_1 = "290px";

	private static final String[] COLUMNS_WIDTH = { "29%", "2%", "27%", "2%", "34%" };

	private CustomComboBox<LotTypeDto> lotType = null;

	private CustomComboBox<SimpleDto> dgdPresente = null;

	private CustomComboBox<SimpleDto> payment = null;

	private CustomComboBox<SimpleDto> decennaleNecessaire = null;

	private CustomComboBox<SimpleDto> demandeDagrement = null;

	private EditorGrid<CautionFournieDto> cautionFournieGrid = null;

	private final TextField<String> lot;

	private final TextField<String> societe;

	private final TextField<String> idSiTravaux;

	private final NumberField objectif;

	private final LabelField chantierName;

	private final FormPanel formPanel;

	private NumberField prorata;

	private NumberField canto;

	private NumberField badge;

	private NumberField grue;

	private NumberField lift;

	private NumberField benne;

	private NumberField netoyage;

	private final HTML addRow;

	private final FieldSet mainFieldSet;

	private final RoleModel role;

	private final UtilisateurGrpModel user;

	private FicheStDto ficheStDto;

	private final FieldSet prestationsFieldSet;

	private final LabelField conducteur;

	public InformationPanel(SimpleEventBus b, RoleModel roleModel, UtilisateurGrpModel utilisateurGrpModel) {
		super();
		this.bus = b;
		this.role = roleModel;
		this.user = utilisateurGrpModel;
		int screenwidth = GuiUtil.getScreenWidth() - 30;
		int panel1 = (screenwidth * 33) / 100;
		int panel2 = (screenwidth * 25) / 100;
		int panel3 = (screenwidth * 35) / 100;
		formPanel = new CustomFormPanel();

		LayoutContainer lc1 = new LayoutContainer();
		formPanel.add(lc1);
		TableLayout tablelayout = new TableLayout(3);
		tablelayout.setHeight("100%");
		tablelayout.setWidth("100%");
		lc1.setLayout(tablelayout);

		LayoutContainer layoutContainer_0 = new LayoutContainer();
		FormLayout fl_layoutContainer_0 = new FormLayout();
		fl_layoutContainer_0.setLabelWidth(70);
		layoutContainer_0.setLayout(fl_layoutContainer_0);

		chantierName = new LabelField();
		chantierName.setFieldLabel(messages.chantier() + ":");
		layoutContainer_0.add(chantierName, new FormData("100%"));

		societe = new CustomTextField<String>();
		societe.setName(FicheStDto.SOCIETE);
		societe.setMaxLength(MAX_LENGTH_1);
		societe.addListener(Events.KeyUp, new KeyListener() {
			@Override
			public void handleEvent(ComponentEvent e) {
				bus.fireEvent(new SocieteEvent(societe.getValue()));
			}
		});

		societe.setId("INFORMATION_PANEL_SOCIETE_ID");
		societe.setAllowBlank(false);
		societe.setFieldLabel(messages.societe());
		layoutContainer_0.add(societe, new FormData("100%"));
		lc1.add(layoutContainer_0);
		layoutContainer_0.setWidth("300px");

		LayoutContainer layoutContainer_1 = new LayoutContainer();
		FormLayout fl_layoutContainer_1 = new FormLayout();
		fl_layoutContainer_1.setLabelWidth(90);
		layoutContainer_1.setLayout(fl_layoutContainer_1);

		lot = new CustomTextField<String>();
		lot.setId("INFORMATION_PANEL_LOT_ID");
		lot.setMaxLength(MAX_LENGTH_3);
		lot.setFieldLabel(messages.lot());
		lot.setAllowBlank(false);
		layoutContainer_1.add(lot, new FormData("100%"));

		initLotTypeData();

		lotType.setId("INFORMATION_PANEL_LOT_TYPE_ID");
		layoutContainer_1.add(lotType, new FormData("100%"));
		layoutContainer_1.setWidth("300px");
		lc1.add(layoutContainer_1, new TableData(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE));

		LayoutContainer layoutContainer_2 = new LayoutContainer();
		FormLayout fl_layoutContainer_2 = new FormLayout();
		fl_layoutContainer_2.setLabelWidth(160);
		layoutContainer_2.setLayout(fl_layoutContainer_2);

		idSiTravaux = new CustomTextField<String>();
		idSiTravaux.setMaxLength(30);
		idSiTravaux.setAllowBlank(false);
		idSiTravaux.addListener(Events.OnKeyUp, new KeyListener() {
			@Override
			public void handleEvent(ComponentEvent e) {
				bus.fireEvent(new ConducteurEvent(idSiTravaux.getValue()));
			}
		});

		idSiTravaux.setId("INFORMATION_PANEL_SITRAVAUX_ID");
		idSiTravaux.setName(FicheStDto.ID_SI_TRAVAUX);
		idSiTravaux.setFieldLabel(messages.foreman());
		layoutContainer_2.add(idSiTravaux, new FormData("100%"));

		objectif = createNumberField(messages.montant());
		objectif.setId("INFORMATION_PANEL_MONTANT_ID");
		objectif.setName(FicheStDto.OBJECTIF);
		layoutContainer_2.add(objectif, new FormData("100%"));
		lc1.add(layoutContainer_2, new TableData(HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE));
		layoutContainer_2.setWidth("350px");

		LayoutContainer informationFieldSet = new LayoutContainer();
		TableLayout tablelayout2 = new TableLayout(5);
		tablelayout2.setHeight("100%");
		tablelayout2.setWidth("100%");
		informationFieldSet.setLayout(tablelayout2);

		FieldSet conditionFieldSet = new FieldSet();
		conditionFieldSet.setWidth(panel1);
		TableData layoutData = new TableData();
		layoutData.setVerticalAlign(VerticalAlignment.MIDDLE);
		layoutData.setWidth(COLUMNS_WIDTH[0]);
		conditionFieldSet.setLayout(new RowLayout(Orientation.VERTICAL));

		LayoutContainer layoutContainer_4 = new LayoutContainer();
		FormLayout fl_layoutContainer_4 = new FormLayout();
		fl_layoutContainer_4.setLabelWidth(180);
		layoutContainer_4.setLayout(fl_layoutContainer_4);

		initPaymentData();
		layoutContainer_4.add(payment, new FormData(ANCHOR_SPEC_1));

		NumberField rg = createNumberField(messages.rg());
		rg.setName(FicheStDto.RG);
		rg.setMinValue(0);
		rg.setMaxValue(100);
		rg.setId("INFORMATIONAL_PANEL_RG_ID");
		rg.setInputStyleAttribute("textAlign", "left");
		layoutContainer_4.add(rg, new FormData("70%"));

		initDecennaleNecessaireData();
		layoutContainer_4.add(decennaleNecessaire, new FormData(ANCHOR_SPEC_1));

		initDemandeDagrementData();
		layoutContainer_4.add(demandeDagrement, new FormData(ANCHOR_SPEC_1));
		conditionFieldSet.add(layoutContainer_4);

		HTML newLine = new HTML("<br>", true);
		conditionFieldSet.add(newLine);

		LayoutContainer layoutContainer_6 = new LayoutContainer();
		TableLayout tl3 = new TableLayout(3);
		tl3.setWidth("90%");
		layoutContainer_6.setLayout(tl3);

		LayoutContainer layoutContainer_5 = new LayoutContainer();
		FormLayout fl_layoutContainer_5 = new FormLayout();
		fl_layoutContainer_5.setLabelWidth(110);
		layoutContainer_5.setLayout(fl_layoutContainer_5);

		initDgdPresenteData();
		layoutContainer_5.add(dgdPresente, new FormData("65%"));
		dgdPresente.setWidth(50);
		layoutContainer_6.add(layoutContainer_5);

		HTML space4 = new HTML(SPACES_3);
		layoutContainer_6.add(space4);

		LayoutContainer layoutContainer_7 = new LayoutContainer();
		FormLayout fl_layoutContainer_7 = new FormLayout();
		fl_layoutContainer_7.setLabelWidth(50);
		layoutContainer_7.setLayout(fl_layoutContainer_7);
		DateField dgdPresenteDate = new DateField();
		dgdPresenteDate.setId("INFORMATIONAL_PANEL_DGDPRESENTEDATE_ID");
		dgdPresenteDate.setName(FicheStDto.DATEDGDPRESENTE);
		dgdPresenteDate.setWidth(100);
		layoutContainer_7.add(dgdPresenteDate, new FormData("90%"));
		dgdPresenteDate.setFieldLabel(messages.date());
		layoutContainer_6.add(layoutContainer_7);
		conditionFieldSet.add(layoutContainer_6);
		conditionFieldSet.setStyleAttribute("height", HEIGHT_1);
		conditionFieldSet.setHeading(messages.conditions());
		informationFieldSet.add(conditionFieldSet, layoutData);

		HTML space0 = new HTML(SPACES_3);
		TableData tableData2 = new TableData();
		tableData2.setWidth(COLUMNS_WIDTH[1]);
		informationFieldSet.add(space0, tableData2);

		if (RootPanel.get().getOffsetWidth() <= 1900) {// Constants.MIN_WIDTH){
			panel3 -= 50;
		}
		prestationsFieldSet = new FieldSet();
		prestationsFieldSet.setWidth(panel2);

		prestationsFieldSet.setStyleAttribute("height", HEIGHT_1);
		FormLayout fl_prestationsFieldSet = new FormLayout();
		fl_prestationsFieldSet.setLabelWidth(120);
		prestationsFieldSet.setLayout(fl_prestationsFieldSet);

		NumberField pilotage = addPrestaField(messages.pilotage(), FicheStDto.PRESTAPILOTAGE, "INFORMATIONAL_PANEL_PILOTAGE_ID", true);

		NumberField assurances = addPrestaField(messages.assurances(), FicheStDto.PRESTAASSURANCES, "INFORMATIONAL_PANEL_ASSURANCES_ID", true);

		try {
			prorata = addPrestaField(messages.prorata() + " (%)", FicheStDto.PRESTA_PRORATA, "INFORMATIONAL_PANEL_PRORATE_ID", true);
			prorata.addListener(Events.KeyUp, new KeyListener() {
				@Override
				public void handleEvent(ComponentEvent e) {

					// TODO check if prorata.getValue() is NULL;
					ficheStDto.setPrestaProrata(prorata.getValue().doubleValue());
					bus.fireEvent(new PrestationEvent());
				}
			});

			canto = addPrestaField(messages.canto() + " (&euro;)", FicheStDto.PRESTACANTO, "INFORMATIONAL_PANEL_CANTO_ID", false);
			canto.addListener(Events.KeyUp, new KeyListener() {
				@Override
				public void handleEvent(ComponentEvent e) {
					ficheStDto.setPrestaCanto(canto.getValue().doubleValue());
					bus.fireEvent(new PrestationEvent());
				}
			});

			badge = addPrestaField(messages.badge() + " (&euro;)", FicheStDto.PRESTABADGE, "INFORMATIONAL_PANEL_BADGE_ID", false);
			badge.addListener(Events.KeyUp, new KeyListener() {
				@Override
				public void handleEvent(ComponentEvent e) {
					ficheStDto.setPrestaBadge(badge.getValue().doubleValue());
					bus.fireEvent(new PrestationEvent());
				}
			});

			grue = addPrestaField(messages.grue() + " (&euro;)", FicheStDto.PRESTAGRUE, "INFORMATIONAL_PANEL_GRUE_ID", false);
			grue.addListener(Events.KeyUp, new KeyListener() {
				@Override
				public void handleEvent(ComponentEvent e) {
					ficheStDto.setPrestaGrue(grue.getValue().doubleValue());
					bus.fireEvent(new PrestationEvent());
				}
			});

			lift = addPrestaField(messages.lift() + " (&euro;)", FicheStDto.PRESTALIFT, "INFORMATIONAL_PANEL_LIFT_ID", false);
			lift.addListener(Events.KeyUp, new KeyListener() {
				@Override
				public void handleEvent(ComponentEvent e) {
					ficheStDto.setPrestaLift(lift.getValue().doubleValue());
					bus.fireEvent(new PrestationEvent());
				}
			});

			benne = addPrestaField(messages.benne() + " (&euro;)", FicheStDto.PRESTABENNE, "INFORMATIONAL_PANEL_BENNE_ID", false);
			benne.addListener(Events.KeyUp, new KeyListener() {
				@Override
				public void handleEvent(ComponentEvent e) {
					ficheStDto.setPrestaBenne(benne.getValue().doubleValue());
					bus.fireEvent(new PrestationEvent());
				}
			});

			netoyage = addPrestaField(messages.nettoyage() + " (&euro;)", FicheStDto.PRESTANETTOYAGE, "INFORMATIONAL_PANEL_NETOYAGE_ID", false);
			netoyage.addListener(Events.KeyUp, new KeyListener() {
				@Override
				public void handleEvent(ComponentEvent e) {
					ficheStDto.setPrestaNettoyage(netoyage.getValue().doubleValue());
					bus.fireEvent(new PrestationEvent());
				}
			});
		} catch (Exception e1) {
		}

		TableData tableData6 = new TableData();
		tableData6.setVerticalAlign(VerticalAlignment.MIDDLE);
		tableData6.setWidth(COLUMNS_WIDTH[2]);
		prestationsFieldSet.setHeading(messages.prestations());
		informationFieldSet.add(prestationsFieldSet, tableData6);

		HTML space1 = new HTML(SPACES_3);
		TableData tableData7 = new TableData();
		tableData7.setWidth(COLUMNS_WIDTH[3]);
		informationFieldSet.add(space1, tableData7);

		FieldSet complementariesInfo = new CustomFieldSet();
		complementariesInfo.setCollapsible(false);
		complementariesInfo.setHeading(messages.informationComplementaries());
		complementariesInfo.setWidth(panel3 + 30);
		complementariesInfo.setHeight(85);
		TableLayout tl_informationComplementaries = new TableLayout(1);
		tl_informationComplementaries.setWidth("100%");
		complementariesInfo.setLayout(tl_informationComplementaries);

		conducteur = new LabelField();
		conducteur.setId("INFORMATIONAL_PANEL_CONDUCTEUR_ID");
		conducteur.setFieldLabel(messages.conducteur() + ":");
		bus.addHandler(ConducteurEvent.TYPE, new ConducteurHandler() {
			@Override
			public void onChangeConducteur(ConducteurEvent conducteurEvent) {
				conducteur.setValue(conducteurEvent.getConducter());
			}
		});

		LayoutContainer layoutContainer_8 = new LayoutContainer();
		FormLayout fl_layoutContainer_8 = new FormLayout();
		fl_layoutContainer_8.setLabelWidth(165);
		layoutContainer_8.setLayout(fl_layoutContainer_8);
		layoutContainer_8.add(conducteur, new FormData("50%")); // issue here

		DateField dateOfMarket = new DateField();
		dateOfMarket.setName(FicheStDto.DATEMARCHEBASE);
		dateOfMarket.setWidth(100);
		dateOfMarket.setId("INFORMATIONAL_PANEL_DATEOFMARKET_ID");
		layoutContainer_8.add(dateOfMarket, new FormData("75%"));
		dateOfMarket.setFieldLabel(messages.dateOfMarket());
		TableData layoutData5 = new TableData();
		layoutData5.setWidth("100%");
		complementariesInfo.add(layoutContainer_8, layoutData5);

		LayoutContainer layoutContainer_3 = new LayoutContainer();
		layoutContainer_3.setHeight("315px");
		layoutContainer_3.setLayout(new RowLayout(Orientation.VERTICAL));
		layoutContainer_3.setStyleAttribute("height", HEIGHT_1);
		layoutContainer_3.add(complementariesInfo);
		TableData layoutData3 = new TableData();
		layoutData3.setVerticalAlign(VerticalAlignment.MIDDLE);
		layoutData3.setWidth(COLUMNS_WIDTH[4]);

		FieldSet cautionFieldset = new FieldSet();
		cautionFieldset.setLayout(new RowLayout(Orientation.VERTICAL));

		layoutContainer_3.add(cautionFieldset);
		cautionFieldset.setHeight("205px");
		cautionFieldset.setHeading(messages.cautionFournie());
		informationFieldSet.add(layoutContainer_3, layoutData3);

		lc1.setId("informationFieldSet");
		prestationsFieldSet.setId("prestationsFieldSet");
		conditionFieldSet.setId("conditionFieldSet");
		complementariesInfo.setId("informationComplementaries");

		formPanel.add(new HTML("<br>"));
		formPanel.add(informationFieldSet);

		int[] columnWidth5 = { DELETE_BUTTON_WIDTH + 5, 145, 178 };
		GridCellRenderer<AbstractDto> dateRenderer = createDateRendererWithPermision(columnWidth5[1] - PADDING_2, role, user);// 25);
		GridCellRenderer<AbstractDto> numberRenderer = createNumberRendererWithPermission(role, user);// 25);

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column1 = new ColumnConfig();
		column1.setId(CautionFournieDto.ID);
		column1.setAlignment(HorizontalAlignment.CENTER);
		column1.setMenuDisabled(true);
		column1.setResizable(false);
		column1.setFixed(true);
		column1.setHeader(messages.del());
		column1.setWidth(columnWidth5[0]);
		column1.setRenderer(createDeleteButtonRenderer());
		configs.add(column1);

		ColumnConfig column2 = new ColumnConfig();
		column2.setId(CautionFournieDto.DATE);
		column2.setMenuDisabled(true);
		column2.setHeader(messages.date());
		column2.setResizable(false);
		column2.setWidth(columnWidth5[1]);
		column2.setDateTimeFormat(DATE_FORMAT);
		configs.add(column2);
		column2.setRenderer(dateRenderer);

		ColumnConfig column3 = new ColumnConfig();
		column3.setId(CautionFournieDto.AMOUNT);
		column3.setAlignment(HorizontalAlignment.RIGHT);
		column3.setResizable(false);
		column3.setMenuDisabled(true);
		column3.setHeader(messages.amount());
		column3.setWidth(columnWidth5[2]);
		column3.setNumberFormat(NUMBER_FORMAT);
		NumberField numberField = createNumberField(null);
		column3.setEditor(new CellEditor(numberField));
		column3.setRenderer(numberRenderer);
		configs.add(column3);

		ColumnModel cm = new ColumnModel(configs);

		ListStore<CautionFournieDto> data = new ListStore<CautionFournieDto>();
		cautionFournieGrid = new CustomEditorGrid<CautionFournieDto>(data, cm);
		cautionFournieGrid.setAutoExpandColumn(CautionFournieDto.AMOUNT);
		cautionFournieGrid.setAutoExpandMax(700);
		cautionFournieGrid.setAutoExpandMin(178);
		cautionFournieGrid.setColumnReordering(false);
		cautionFournieGrid.setColumnResize(false);

		cautionFieldset.add(cautionFournieGrid, new RowData(Style.DEFAULT, 145.0, new Margins()));

		cautionFournieGrid.addListener(Events.BeforeEdit, new Listener<GridEvent<CautionFournieDto>>() {
			@Override
			public void handleEvent(final GridEvent<CautionFournieDto> be) {
				if ((user.getBadmin() != null && user.getBadmin()) || (role != null && role.getBcontributeur() != null && role.getBcontributeur())) {
					be.setCancelled(false);
				} else {
					be.setCancelled(true);
				}
			}
		});

		LayoutContainer layoutContainer_9 = new LayoutContainer();
		layoutContainer_9.setLayout(new TableLayout(1));

		FlexTable flexTable = new FlexTable();
		layoutContainer_9.add(flexTable);
		HTML space6 = new HTML(SPACES_4);
		flexTable.setWidget(0, 0, space6);
		addRow = new HTML("<img src='./images/ajouter.png'/> " + messages.addRow(), false);
		addRow.setStyleName("actionHTML2");
		addRow.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				showAddDialog();
			}

			private void showAddDialog() {
				Dialog dialog = new Dialog();
				dialog.setHeading(messages.titlePopup());
				dialog.setLayout(new BorderLayout());
				dialog.setWidth(450);
				dialog.setHeight(180);
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

				final AddCautionFournieForm addCautionFournieForm = new AddCautionFournieForm();
				dialog.add(addCautionFournieForm, new BorderLayoutData(LayoutRegion.CENTER));

				okButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						CautionFournieDto model = addCautionFournieForm.getDataModel();
						cautionFournieGrid.getStore().add(model);
					}
				});

				dialog.show();
			}
		});

		flexTable.setWidget(0, 1, addRow);
		cautionFieldset.add(layoutContainer_9);

		mainFieldSet = new CustomFieldSet();
		mainFieldSet.setHeading(messages.generalInformation());
		mainFieldSet.add(formPanel);
		add(mainFieldSet);

		// Set IDs for applying CSS
		layoutContainer_9.setId("layoutContainer_9");

		// Set background color to green
		setDefaultBackgroundColor(lc1);
		setDefaultBackgroundColor(layoutContainer_0);
		setDefaultBackgroundColor(layoutContainer_1);
		setDefaultBackgroundColor(layoutContainer_2);
		setDefaultBackgroundColor(prestationsFieldSet);
		setDefaultBackgroundColor(conditionFieldSet);
		setDefaultBackgroundColor(layoutContainer_4);
		setDefaultBackgroundColor(complementariesInfo);
		setDefaultBackgroundColor(cautionFieldset);
		setDefaultBackgroundColor(layoutContainer_9);
		setDefaultBackgroundColor(informationFieldSet);
		setDefaultBackgroundColor(mainFieldSet);

		if (isAdminOrContributor(role, user)) {
			// Information General
			societe.enable();
			lot.enable();
			lotType.enable();
			idSiTravaux.enable();
			objectif.enable();

			// arrPrestation
			pilotage.enable();
			assurances.enable();
			prorata.enable();
			canto.enable();
			badge.enable();
			grue.enable();
			lift.enable();
			benne.enable();
			netoyage.enable();

			// arrCondition
			payment.enable();
			rg.enable();
			decennaleNecessaire.enable();
			demandeDagrement.enable();
			dgdPresente.enable();
			dgdPresenteDate.enable();

			// arrCommemlaire
			conducteur.enable();
			dateOfMarket.enable();
			addRow.setVisible(true);
			cautionFournieGrid.getColumnModel().setHidden(0, false);
		} else {
			// Information General
			societe.disable();
			lot.disable();
			lotType.disable();
			idSiTravaux.disable();
			objectif.disable();

			// arrPrestation
			pilotage.disable();
			assurances.disable();
			prorata.disable();
			canto.disable();
			badge.disable();
			grue.disable();
			lift.disable();
			benne.disable();
			netoyage.disable();

			// arrCondition
			payment.disable();
			rg.disable();
			decennaleNecessaire.disable();
			demandeDagrement.disable();
			dgdPresente.disable();
			dgdPresenteDate.disable();

			// arrCommemlaire
			conducteur.disable();
			dateOfMarket.disable();
			addRow.setVisible(false);
			cautionFournieGrid.getColumnModel().setHidden(0, true);
		}
	}

	private void bindModel(FicheStDto model) {
		if (model == null) {
			return;
		}

		FormBinding binding = new FormBinding(formPanel);

		// Auto binding fields
		binding.autoBind();
		binding.bind(model);

		// manually binding
		binding.addFieldBinding(new FieldBinding(lot, combineProps(FicheStDto.LOT, LotDto.NAME)));
		// binding.addFieldBinding(new FieldBinding(societe, combineProps(FicheStDto.SOCIETE, SocieteDto.NOM)));
		binding.addFieldBinding(new FieldBinding(chantierName, combineProps(FicheStDto.LOT, LotDto.CHANTIER, ChantierModel.NOM)));
	}

	public void setModel(FicheStDto model) {
		ficheStDto = model;
		if (ficheStDto != null) {
			conducteur.setValue(ficheStDto.getIdSiTravaux());
			setCautionFournieDtoList(ficheStDto.getCautionFournies());

			bindModel(ficheStDto);
		}
	}

	private NumberField addPrestaField(String fieldLabel, String fieldName, String fieldId, boolean percentage) {
		NumberField numberField = createNumberField(fieldLabel);
		numberField.setName(fieldName);
		numberField.setId(fieldId);
		numberField.setWidth(70);
		prestationsFieldSet.add(numberField, new FormData(ANCHOR_SPEC_1));
		if (percentage) {
			numberField.setMinValue(0);
			numberField.setMaxValue(100);
		}
		return numberField;
	}

	private void initLotTypeData() {
		lotType = new CustomComboBox<LotTypeDto>();
		lotType.setName(FicheStDto.LOT_TYPE);
		lotType.setFieldLabel(messages.lotType());
		lotType.setDisplayField(LotTypeDto.NAME);
		lotType.setValueField(LotTypeDto.ID);
		lotType.setStore(new ListStore<LotTypeDto>());

		ClientLotTypeServiceAsync clientService = ClientLotTypeServiceAsync.Util.getInstance();
		clientService.findAll(new AsyncCallbackWithErrorResolution<List<LotTypeDto>>() {
			@Override
			public void onSuccess(final List<LotTypeDto> results) {
				Collections.sort(results);
				ListStore<LotTypeDto> list = new ListStore<LotTypeDto>();
				list.add(results);
				lotType.setStore(list);
			}
		});
	}

	private void initDgdPresenteData() {
		dgdPresente = new CustomComboBox<SimpleDto>();
		dgdPresente.setStyleAttribute("width", "100%");
		dgdPresente.setId("INFORMATIONAL_PANEL_DGDPRESENTE_ID");
		dgdPresente.setName(FicheStDto.DGDPRESENTE);
		dgdPresente.setFieldLabel(messages.dgdPresente());
		dgdPresente.setStore(new ListStore<SimpleDto>());

		ClientRefDgdPresenteServiceAsync clientService = ClientRefDgdPresenteServiceAsync.Util.getInstance();
		clientService.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
			@Override
			public void onSuccess(final List<SimpleDto> results) {
				Collections.sort(results);
				ListStore<SimpleDto> list = new ListStore<SimpleDto>();
				list.add(results);
				dgdPresente.setStore(list);
			}
		});
	}

	private void initPaymentData() {
		payment = new CustomComboBox<SimpleDto>();
		payment.setId("INFORMATION_PANEL_PAYMENT_ID");
		payment.setName(FicheStDto.PAYMENT_MODE);
		payment.setFieldLabel(messages.payment());
		payment.setStore(new ListStore<SimpleDto>());

		ClientRefModePaiementServiceAsync clientService = ClientRefModePaiementServiceAsync.Util.getInstance();
		clientService.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
			@Override
			public void onSuccess(final List<SimpleDto> results) {
				Collections.sort(results);
				ListStore<SimpleDto> list = new ListStore<SimpleDto>();
				list.add(results);
				payment.setStore(list);
			}
		});
	}

	private void initDecennaleNecessaireData() {
		decennaleNecessaire = new CustomComboBox<SimpleDto>();
		decennaleNecessaire.setId("INFORMATIONAL_PANEL_DECENNALENECESSAIRE_ID");
		decennaleNecessaire.setName(FicheStDto.REFDECENALE);
		decennaleNecessaire.setFieldLabel(messages.decennaleNecessaire());
		decennaleNecessaire.setStore(new ListStore<SimpleDto>());

		ClientRefDecenaleServiceAsync clientService = ClientRefDecenaleServiceAsync.Util.getInstance();
		clientService.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
			@Override
			public void onSuccess(final List<SimpleDto> results) {
				Collections.sort(results);
				ListStore<SimpleDto> list = new ListStore<SimpleDto>();
				list.add(results);
				decennaleNecessaire.setStore(list);
			}
		});
	}

	private void initDemandeDagrementData() {
		demandeDagrement = new CustomComboBox<SimpleDto>();
		demandeDagrement.setId("INFORMATIONAL_PANEL_DEMANDEDAGREMENT_ID");
		demandeDagrement.setName(FicheStDto.REFDDEAGREMENT);
		demandeDagrement.setFieldLabel(messages.demandeDagrement());
		demandeDagrement.setStore(new ListStore<SimpleDto>());
		ClientRefDdeAgrementServiceAsync clientService = ClientRefDdeAgrementServiceAsync.Util.getInstance();
		clientService.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
			@Override
			public void onSuccess(final List<SimpleDto> results) {
				Collections.sort(results);
				ListStore<SimpleDto> list = new ListStore<SimpleDto>();
				list.add(results);
				demandeDagrement.setStore(list);
			}
		});
	}

	public List<CautionFournieDto> getCautionFournieDtoList() {
		if (cautionFournieGrid == null) {
			return new ArrayList<CautionFournieDto>();
		} else {
			return cautionFournieGrid.getStore().getModels();
		}
	}

	public void setCautionFournieDtoList(List<CautionFournieDto> list) {
		cautionFournieGrid.getStore().removeAll();
		cautionFournieGrid.getStore().add(list);
	}

	public boolean isValid() {
		return (formPanel != null && formPanel.isValid());
	}

	@Override
	public void commitDataChange() {
		commitDataGrids(cautionFournieGrid);
	}
}
