package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.AggregationRowConfig;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupSummaryView;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.structis.fichesst.client.ecran.FicheSTEcran;
import com.structis.fichesst.client.ecran.TransfertppEcran;
import com.structis.fichesst.client.event.ExportSyntheseEcranEvent;
import com.structis.fichesst.client.event.IsEditEvent;
import com.structis.fichesst.client.event.SyntheseEvent;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.handler.ExportSyntheseEcranHandler;
import com.structis.fichesst.client.handler.SyntheseHandler;
import com.structis.fichesst.client.popup.CreateFicheSTPopUpReportWindow;
import com.structis.fichesst.client.service.ClientFicheStServiceAsync;
import com.structis.fichesst.client.service.ClientFicheTransfertppServiceAsync;
import com.structis.fichesst.client.service.ClientRefTransfertppServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.client.util.NameValuePair;
import com.structis.fichesst.client.util.ReportUtil;
import com.structis.fichesst.client.widget.CustomEditorGrid;
import com.structis.fichesst.client.widget.CustomFieldSet;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.client.widget.CustomTextField;
import com.structis.fichesst.shared.dto.AbstractDto;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.FicheTransfertppDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.LigTransfertppModel;
import com.structis.fichesst.shared.dto.LotDto;
import com.structis.fichesst.shared.dto.LotTypeDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.SimpleDto;
import com.structis.fichesst.shared.dto.TransfertPpSummaryDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.util.Constants;

public class SyntheseMainPanel extends AbstractPanel {

	private SyntheseButtonsPanel buttons;;

	private ChantierModel chantier;

	private final ClientFicheStServiceAsync clientFicheStService = ClientFicheStServiceAsync.Util.getInstance();

	private CustomEditorGrid<FicheStDto> ficheStGrid1;

	private CustomEditorGrid<FicheStDto> ficheStGrid2;

	private CustomEditorGrid<FicheStDto> ficheStSummaryGrid;

	private FormPanel formPanel;

	private FormPanel formPanel2;

	boolean isEdit = false;

	private NumberField prorataTheorique;

	private RoleModel role;

	LayoutContainer saveLayout;

	LayoutContainer saveLayout2;

	private CustomEditorGrid<TransfertPpSummaryDto> transfertPpGrid;

	private UtilisateurGrpModel user;

	public SyntheseMainPanel(SimpleEventBus b, ChantierModel c, RoleModel r, UtilisateurGrpModel u) {
		super();
		this.bus = b;
		this.chantier = c;
		this.role = r;
		this.user = u;
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		TableLayout layout = new TableLayout(1);
		layout.setWidth("100%");
		setLayout(layout);
		CustomFieldSet chantierInfo = new CustomFieldSet();
		chantierInfo.setCollapsible(false);
		FormLayout fl_fldstNewFieldset_0 = new FormLayout();
		fl_fldstNewFieldset_0.setLabelWidth(130);
		chantierInfo.setLayout(fl_fldstNewFieldset_0);

		formPanel = new CustomFormPanel();
		formPanel.setLabelWidth(135);

		final TextField<String> chatierName = new CustomTextField<String>();
		chatierName.setName(ChantierModel.NOM);
		chatierName.setFieldLabel(messages.nameChantier());
		chatierName.addListener(Events.OnKeyUp, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if( !formPanel.isValid() ) {
					return;
				}

				isEdit = true;
				bus.fireEvent(new IsEditEvent(isEdit));
			}
		});
		formPanel.add(chatierName);

		prorataTheorique = createIntegerField("Prorata Théorique", true);
		prorataTheorique.setName(ChantierModel.PRORATA_THEORIQUE);
		prorataTheorique.addListener(Events.OnKeyUp, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if( !formPanel2.isValid() ) {
					return;
				}

				saveLayout.setEnabled(true);
				isEdit = true;
				bus.fireEvent(new IsEditEvent(isEdit));
			}
		});

		formPanel2 = new CustomFormPanel();
		formPanel2.setLabelWidth(135);
		formPanel2.add(prorataTheorique);

		chantierInfo.add(formPanel, new FormData("35%"));
		add(chantierInfo);
		chantierInfo.setHeading("INFORMATIONS CHANTIER");

		CustomFieldSet fldstNewFieldset_1 = new CustomFieldSet();
		fldstNewFieldset_1.setLayout(new RowLayout(Orientation.VERTICAL));
		add(fldstNewFieldset_1);
		fldstNewFieldset_1.setHeading("SYNTHESE DES FICHES ST");

		LayoutContainer linksPanel = new LayoutContainer();
		linksPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
		LayoutContainer topPanel = new LayoutContainer();
		topPanel.setHeight(25);
		topPanel.setLayout(new BorderLayout());
		topPanel.add(linksPanel, new BorderLayoutData(LayoutRegion.EAST, 645));
		topPanel.add(formPanel2, new BorderLayoutData(LayoutRegion.WEST, 500));
		fldstNewFieldset_1.add(topPanel);
		fldstNewFieldset_1.add(new HTML("<br>"));
		saveLayout = new LayoutContainer();

		HTML save = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveSynthese(), false);
		save.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		save.setStyleName("actionHTML");
		saveLayout.add(save);
		saveLayout.setEnabled(false);
		save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new SyntheseEvent());
				saveLayout.setEnabled(false);
				bus.fireEvent(new IsEditEvent(false));
			}
		});
		linksPanel.add(saveLayout);
		if( isAdminOrContributor(role, user) ) {
			save.setVisible(true);
			chatierName.enable();
			prorataTheorique.enable();
		}
		else {
			save.setVisible(false);
			chatierName.disable();
			prorataTheorique.disable();
		}

		HTML printSubcontractor = new HTML("<img src='./images/imprimer.png'/> " + messages.printSubcontractor(), false);
		printSubcontractor.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		printSubcontractor.setStyleName("actionHTML");
		linksPanel.add(new HTML(LINKS_SPACE, false));
		linksPanel.add(printSubcontractor);
		printSubcontractor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				List<FicheStDto> allModels = new ArrayList<FicheStDto>();
				allModels.addAll(ficheStGrid1.getStore().getModels());
				allModels.addAll(ficheStGrid2.getStore().getModels());
				CreateFicheSTPopUpReportWindow popUpReport = new CreateFicheSTPopUpReportWindow(bus, allModels, true);
				popUpReport.show();
			}
		});

		HTML printRefacturations = new HTML("<img src='./images/imprimer.png'/> " + messages.printRefacturations(), false);
		printRefacturations.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		printRefacturations.setStyleName("actionHTML");
		linksPanel.add(new HTML(LINKS_SPACE, false));
		linksPanel.add(printRefacturations);
		printRefacturations.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				List<FicheStDto> allModels = new ArrayList<FicheStDto>();
				allModels.addAll(ficheStGrid1.getStore().getModels());
				allModels.addAll(ficheStGrid2.getStore().getModels());
				CreateFicheSTPopUpReportWindow popUpReport = new CreateFicheSTPopUpReportWindow(bus, allModels, false);
				popUpReport.show();
			}
		});

		ficheStGrid1 = createFicheStGrid(messages.totalSt());
		fldstNewFieldset_1.add(ficheStGrid1);

		// Honoraires lot type
		ficheStGrid2 = createFicheStGrid("Honoraires");
		ficheStGrid2.hideHeader();

		// Hide it. Only show it if having data (check at loadFicheStData() method)
		ficheStGrid2.hide();
		fldstNewFieldset_1.add(ficheStGrid2);
		loadFicheStData();
		if( isAdminOrContributor(role, user) ) {
			FlexTable ft1 = new FlexTable();
			ft1.setWidget(0, 0, new HTML(SPACES_4));
			HTML addRow = new HTML("<img src='./images/ajouter.png'/> " + messages.newSubcontractor(), false);
			addRow.setStyleName("actionHTML2");
			ft1.setWidget(0, 1, addRow);
			addRow.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					GuiUtil.gotoEcran(new FicheSTEcran(chantier, null, role, user));
					/* bus.fireEvent(new LoadFichestEcranEvent(chantier)); */
				}
			});

			fldstNewFieldset_1.add(ft1);
		}

		FieldSet transferPpFieldSet = new CustomFieldSet();
		transferPpFieldSet.setHeading("SYNTHESE DES TRANSFERT PP");
		transferPpFieldSet.setLayout(new BorderLayout());
		setDefaultBackgroundColor(transferPpFieldSet);
		saveLayout2 = new LayoutContainer();
		HTML save2 = new HTML("<img src='./images/sauvegarder.png'/> Sauvegarder la synthèse", false);
		save2.setStyleName("actionHTML");
		save2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		save2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if( !formPanel2.isValid() ) {
					return;
				}

				bus.fireEvent(new SyntheseEvent());
				saveLayout2.setEnabled(false);
			}
		});
		saveLayout2.add(save2);
		saveLayout2.setEnabled(false);
		transferPpFieldSet.add(saveLayout2, new BorderLayoutData(LayoutRegion.EAST));
		if( isAdminOrContributor(role, user) ) {
			save2.setVisible(true);
		}
		else {
			save2.setVisible(false);
		}

		createTransfertPpGrid(role, user, chantier);
		transferPpFieldSet.add(transfertPpGrid, new BorderLayoutData(LayoutRegion.WEST, 1000));
		add(transferPpFieldSet);
		transferPpFieldSet.setHeight("430");

		transfertPpGrid.addListener(Events.BeforeEdit, new Listener<GridEvent<TransfertPpSummaryDto>>() {
			@Override
			public void handleEvent(GridEvent<TransfertPpSummaryDto> be) {
				@SuppressWarnings("unused")
				TransfertPpSummaryDto transfertPpSummaryDto = transfertPpGrid.getSelectionModel().getSelectedItem();
				if(isAdminOrContributor(role, user)) {
					be.setCancelled(false);
				}
				else {
					be.setCancelled(true);
				}
			}
		});
		transfertPpGrid.addListener(Events.AfterEdit, new Listener<GridEvent<TransfertPpSummaryDto>>() {
			@Override
			public void handleEvent(GridEvent<TransfertPpSummaryDto> be) {
				saveLayout2.setEnabled(true);
				isEdit = true;
				bus.fireEvent(new IsEditEvent(isEdit));
			}
		});

		FieldSet fldstNewFieldset_3 = new CustomFieldSet();
		fldstNewFieldset_3.setHeading("TOTAL CHANTIER");
		fldstNewFieldset_3.setLayout(new BorderLayout());
		fldstNewFieldset_3.setHeight(175);
		add(fldstNewFieldset_3);

		createFicheStSummaryGrid();
		fldstNewFieldset_3.add(ficheStSummaryGrid);

		buttons = new SyntheseButtonsPanel(bus, role, user);
		add(buttons);

		bindModel(formPanel, this.chantier);
		bus.fireEvent(new IsEditEvent(isEdit));
		bus.addHandler(SyntheseEvent.TYPE, new SyntheseHandler() {
			@Override
			public void onEvent(SyntheseEvent event) {
				if( !isValid() ) {
					return;
				}

				showSaving(SyntheseMainPanel.this);
				AsyncCallbackWithErrorResolution<Void> callback = new AsyncCallbackWithErrorResolution<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						unmaskAll();
						super.onFailure(caught);
					}

					@Override
					public void onSuccess(Void result) {
						unmaskAll();
						SyntheseMainPanel.this.commitDataChange();
						GuiUtil.showSuccessInfo();
						saveLayout.setEnabled(false);
						saveLayout2.setEnabled(false);
					}

					private void unmaskAll() {
						SyntheseMainPanel.this.unmask();
					}
				};

				updateSynthese(callback);
			}

			private void updateSynthese(AsyncCallbackWithErrorResolution<Void> callback) {
				List<FicheStDto> ficheStList = new ArrayList<FicheStDto>();
				ficheStList.addAll(ficheStGrid1.getStore().getModels());
				ficheStList.addAll(ficheStGrid2.getStore().getModels());
				List<TransfertPpSummaryDto> transfertPpSummaryList = transfertPpGrid.getStore().getModels();
				List<FicheTransfertppDto> ficheTransfertppDtoList = new ArrayList<FicheTransfertppDto>();
				for( TransfertPpSummaryDto transfertPpSummary : transfertPpSummaryList ) {
					FicheTransfertppDto ficheTransfertppDto = new FicheTransfertppDto();
					ficheTransfertppDto.setObjectif(transfertPpSummary.getObjective());
					ficheTransfertppDto.setChantier(SyntheseMainPanel.this.chantier);
					SimpleDto refTransfertPp = new SimpleDto();
					refTransfertPp.setId(transfertPpSummary.getRefTransfertPpId());
					ficheTransfertppDto.setRefTransfertPp(refTransfertPp);
					ficheTransfertppDtoList.add(ficheTransfertppDto);
				}

				clientFicheStService.updateSynthese(chantier, ficheStList, ficheTransfertppDtoList, callback);
			}
		});

		bus.addHandler(ExportSyntheseEcranEvent.TYPE, new ExportSyntheseEcranHandler() {

			@Override
			public void onExportSyntheseEcran(ExportSyntheseEcranEvent event) {

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// Information Chantier
				String chantier = chatierName.getValue();
				params.add(new NameValuePair("paramChantier", chantier));
				if( prorataTheorique.getValue() != null ) {
					Double prorata = prorataTheorique.getValue().doubleValue();
					params.add(new NameValuePair("paramProrata", prorata + ""));
				}
				// Synthese des fiches st
				// + Grid 1
				List<FicheStDto> fichestGrid1 = ficheStGrid1.getStore().getModels();
				List<String> lstKeyGrid1 = new ArrayList<String>();
				String keyGrid1 = "";
				for( int i = 0 ; i < fichestGrid1.size() ; i++ ) {
					if( fichestGrid1.get(i).getLotType() != null && !lstKeyGrid1.contains(fichestGrid1.get(i).getLotType().getName()) ) {
						lstKeyGrid1.add(fichestGrid1.get(i).getLotType().getName());
						keyGrid1 += fichestGrid1.get(i).getLotType().getName() + Constants.SEPRATE;
					}
				}
				if( keyGrid1 != null && keyGrid1.length() > 0 ) {
					keyGrid1 = keyGrid1.substring(0, keyGrid1.length() - Constants.SEPRATE.length());
					params.add(new NameValuePair("paramKeyGrid1", keyGrid1));
				}
				Map<String, String> mapGrid1 = new HashMap<String, String>();
				Map<String, String> mapTotalGroupGrid1 = new HashMap<String, String>();
				String totalSumGrid1 = createGridReport(fichestGrid1, lstKeyGrid1, mapGrid1, mapTotalGroupGrid1);
				params.add(new NameValuePair("paramMapGrid1", mapGrid1.toString()));
				params.add(new NameValuePair("paramMapTotalGroupGrid1", mapTotalGroupGrid1.toString()));
				params.add(new NameValuePair("paramTotalSumGrid1", totalSumGrid1));

				// +Grid 2
				List<FicheStDto> fichestGrid2 = ficheStGrid2.getStore().getModels();
				Map<String, String> mapGrid2 = new HashMap<String, String>();
				Map<String, String> mapTotalGroupGrid2 = new HashMap<String, String>();
				List<String> lstKeyGrid2 = new ArrayList<String>();
				String keyGrid2 = "";
				for( int i = 0 ; i < fichestGrid2.size() ; i++ ) {
					if( fichestGrid2.get(i).getLotType() != null && !lstKeyGrid2.contains(fichestGrid2.get(i).getLotType().getName()) ) {
						lstKeyGrid2.add(fichestGrid2.get(i).getLotType().getName());
						keyGrid2 += fichestGrid2.get(i).getLotType().getName() + Constants.SEPRATE;
					}
				}
				if( keyGrid2 != null && keyGrid2.length() > 0 ) {
					keyGrid2 = keyGrid2.substring(0, keyGrid2.length() - Constants.SEPRATE.length());
					params.add(new NameValuePair("paramKeyGrid2", keyGrid2));
				}
				String totalSumGrid2 = createGridReport(fichestGrid2, lstKeyGrid2, mapGrid2, mapTotalGroupGrid2);
				params.add(new NameValuePair("paramMapGrid2", mapGrid2.toString()));
				params.add(new NameValuePair("paramMapTotalGroupGrid2", mapTotalGroupGrid2.toString()));
				params.add(new NameValuePair("paramTotalSumGrid2", totalSumGrid2));

				// Synthese des transpert pp
				List<TransfertPpSummaryDto> listTransfertPpGrid = transfertPpGrid.getStore().getModels();
				TransfertPpSummaryDto transfertPpSummaryDto = null;
				String grid3 = "";
				double sumColumnObjectif = 0.0;
				double sumColumnObj = 0.0;
				double sumColumnTransfert = 0.0;
				double sumColumnRd = 0.0;
				double sumColumnTs = 0.0;
				double sumRow = 0.0;
				double sumSumRow = 0.0;
				NumberFormat numberFormat = NumberFormat.getFormat(Constants.NUMBER_FORMAT);
				for( int i = 0 ; i < listTransfertPpGrid.size() ; i++ ) {
					transfertPpSummaryDto = listTransfertPpGrid.get(i);
					sumColumnObjectif += transfertPpSummaryDto.getObjective();
					sumColumnObj += transfertPpSummaryDto.getObj();
					sumColumnTransfert += transfertPpSummaryDto.getDevers();
					sumColumnRd += transfertPpSummaryDto.getRd();
					sumColumnTs += transfertPpSummaryDto.getTs();
					sumRow = transfertPpSummaryDto.getObj() + transfertPpSummaryDto.getDevers() + transfertPpSummaryDto.getRd() + transfertPpSummaryDto.getTs();
					sumSumRow += sumRow;
					grid3 += createTotalSumReport(
							numberFormat, transfertPpSummaryDto.getLabel(), transfertPpSummaryDto.getObjective(),
							transfertPpSummaryDto.getObj(), transfertPpSummaryDto.getDevers(),
							transfertPpSummaryDto.getRd(), transfertPpSummaryDto.getTs(), sumRow);

				}
				params.add(new NameValuePair("paramGrid3", grid3));
				String totalSumGrid3 = createTotalSumReport(
						numberFormat, "Total PP", sumColumnObjectif, sumColumnObj, sumColumnTransfert, sumColumnRd,
						sumColumnTs, sumSumRow);
				params.add(new NameValuePair("paramTotalSumGrid3", totalSumGrid3));

				// ficheStSummaryGrid
				// +Grid 4
				List<FicheStDto> fichestGrid4 = ficheStSummaryGrid.getStore().getModels();
				String[] arr = createGridReportSummary(fichestGrid4);
				params.add(new NameValuePair("paramGrid4", arr[0]));
				params.add(new NameValuePair("paramTotalSumGrid4", arr[1]));

				String exportPdfUrl = GWT.getHostPageBaseURL() + "synthese.pdf";
				ReportUtil.showReport(exportPdfUrl, params.toArray(new NameValuePair[params.size()]));

			}
		});
	}

	private void bindModel(FormPanel formPanel, ChantierModel model) {
		FormBinding binding = new FormBinding(formPanel);

		// Auto binding fields
		binding.autoBind();
		binding.bind(model);

		FormBinding binding2 = new FormBinding(formPanel2);
		Integer value = model.get(ChantierModel.PRORATA_THEORIQUE);
		
		if(value == null) {
			model.set(ChantierModel.PRORATA_THEORIQUE, 0); // show 0 if no value is specified
		}
		
		// Auto binding fields
		binding2.autoBind();
		binding2.bind(model);
	}

	private void calculateValues() {
		if( ficheStSummaryGrid == null ) {
			return;
		}

		double totalObjective = 0.0;
		double totalObj = 0.0;
		double totalTransferts = 0.0;
		double totalRd = 0.0;
		double totalTs = 0.0;

		double totalTraite = 0.0;
		double totalArrete = 0.0;
		double totalNonArrete = 0.0;
		double totalProvision = 0.0;
		double totalDevisRefuse = 0.0;

		double totalEcartM1 = 0.0;
		double totalEcartDernierPoint = 0.0;
		double totalCumule = 0.0;

		double totalCanto = 0.0;
		double totalBadge = 0.0;
		double totalGrue = 0.0;
		double totalLift = 0.0;
		double totalBenne = 0.0;
		double totalNettoy = 0.0;
		double totalAutres = 0.0;
		double totalPenalty = 0.0;
		double totalProrataAppliqueST = 0.0;

		List<FicheStDto> allModels = new ArrayList<FicheStDto>();
		allModels.addAll(ficheStGrid1.getStore().getModels());
		allModels.addAll(ficheStGrid2.getStore().getModels());

		for( FicheStDto ficheStDto : allModels ) {
			totalObjective += ficheStDto.getObjectif();
			totalObj += ficheStDto.getObj();
			totalTransferts += ficheStDto.getTransferts();
			totalRd += ficheStDto.getRd();
			totalTs += ficheStDto.getTs();

			totalTraite += ficheStDto.getTraite();
			totalArrete += ficheStDto.getArrete();
			totalNonArrete += ficheStDto.getNonArrete();
			totalProvision += ficheStDto.getProvision();
			totalDevisRefuse += ficheStDto.getDevisRefuse();
			totalEcartM1 += ficheStDto.getEcartM1();

			totalEcartDernierPoint += ficheStDto.getEcartDernierPoint();
			totalCumule += ficheStDto.getTotalCumule();
			totalCanto += ficheStDto.getTotalCanto();
			totalBadge += ficheStDto.getTotalBadge();
			totalGrue += ficheStDto.getTotalGrue();

			totalLift += ficheStDto.getTotalLift();
			totalBenne += ficheStDto.getTotalBenne();
			totalNettoy += ficheStDto.getTotalNettoy();
			totalAutres += ficheStDto.getTotalAutres();
			totalPenalty += ficheStDto.getTotalPenalty();
			totalProrataAppliqueST += ficheStDto.getProrataAppliqueST();
		}

		List<TransfertPpSummaryDto> list3 = transfertPpGrid.getStore().getModels();
		for( TransfertPpSummaryDto transfertPpSummaryDto : list3 ) {
			totalObjective += transfertPpSummaryDto.getObjective();
		}

		ficheStSummaryGrid.getStore().removeAll();
		FicheStDto model = new FicheStDto();
		model.setObjectif(totalObjective);
		model.setObj(totalObj);
		model.setTransferts(totalTransferts);
		model.setRd(totalRd);
		model.setTs(totalTs);

		model.setTraite(totalTraite);
		model.setArrete(totalArrete);
		model.setNonArrete(totalNonArrete);
		model.setProvision(totalProvision);
		model.setDevisRefuse(totalDevisRefuse);
		model.setEcartM1(totalEcartM1);

		model.setEcartDernierPoint(totalEcartDernierPoint);
		model.setTotalCumule(totalCumule);

		model.setTotalCanto(totalCanto);
		model.setTotalBadge(totalBadge);
		model.setTotalGrue(totalGrue);
		model.setTotalLift(totalLift);
		model.setTotalBenne(totalBenne);
		model.setTotalNettoy(totalNettoy);
		model.setTotalAutres(totalAutres);
		model.setTotalPenalty(totalPenalty);

		model.setProrataAppliqueST(totalProrataAppliqueST);
		ficheStSummaryGrid.getStore().add(model);
	}

	@Override
	public void commitDataChange() {
		commitDataGrids(ficheStGrid1, ficheStGrid2, transfertPpGrid);
	}

	private GridCellRenderer<FicheStDto> createActionButtonsRenderer() {
		GridCellRenderer<FicheStDto> deleteBtn = new GridCellRenderer<FicheStDto>() {
			@Override
			public Object render(final FicheStDto model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex,
					final ListStore<FicheStDto> store, Grid<FicheStDto> grid) {
				LayoutContainer layoutcell = new LayoutContainer();
				layoutcell.setLayout(new ColumnLayout());

				Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btt = be.getButtonClicked();
						if( Dialog.OK.equals(btt.getItemId()) ) {
							if( store == null ) {
								return;
							}

							Integer id = model.getId();
							if( id == null ) {
								store.remove(model);
							}
							else {
								clientFicheStService.delete(id, new AsyncCallbackWithErrorResolution<Void>() {
									@Override
									public void onFailure(Throwable caught) {
										super.onFailure(caught);
									}

									@Override
									public void onSuccess(Void result) {
										store.remove(model);
										saveLayout.setEnabled(true);
										isEdit = true;
										bus.fireEvent(new IsEditEvent(isEdit));
									};
								});
							}
						}
					}
				};

				Widget deleteButton = createDeleteButton(callback);
				if( isAdminOrContributor(role, user) ) {
					layoutcell.add(deleteButton);
				}
				else {
					layoutcell.remove(deleteButton);
				}

				Image exportButton = createPrintButton();
				exportButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new NameValuePair("fichestIds", model.getId() + Constants.SEPRATE));
						params.add(new NameValuePair("isHasGestion", "true"));

						String action = GWT.getHostPageBaseURL() + "custom_fichest.pdf";
						ReportUtil.showReport(action, params.toArray(new NameValuePair[params.size()]));
					}
				});
				Image viewButton = createViewButton();
				viewButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						GuiUtil.gotoEcran(new FicheSTEcran(chantier, model.getId(), role, user));
						// bus.fireEvent(new LoadFichestEcranEvent(chantier,model));
					}
				});

				layoutcell.add(new Html(BUTTONS_SPACE));
				layoutcell.add(exportButton);
				layoutcell.add(new Html(BUTTONS_SPACE));
				layoutcell.add(viewButton);
				return layoutcell;
			}
		};

		return deleteBtn;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private CustomEditorGrid<FicheStDto> createFicheStGrid(String label) {
		String[] headers = { "", messages.lot(), "Objectif", messages.societe(), "Obj", "+/-", "RD", "TS", messages.total(), messages.traite(), messages.arrete(), messages.traiter(), messages.provision(), "Total Final".replaceAll(
				" ", "<br>"), messages.devisRefuse().replaceAll(" ", "<br>"), "Ecart M", "Ecart M-1", "Variation M", "Ecart dernier point", "Variation Ecart point / M", "% Avct BA", "Montant BA", "% Avct Réel", "Montant Réel", "Canto", "Badge", "Grue", "Lift", "Benne", "Nettoy", "Autres", "Prorata", "Total", "% Prestation / Traité", "Pénalité Facturé", "Prorata appliqué ST", "Prorata marché", "Prorata sur RAD", "Marchés restant à traiter (%)", "Marchés restant à traiter (€)", "Variation dû aux transferts", "Manque à gagner ST sans prorata" };
		String lotNameCol = combineProps(FicheStDto.LOT, LotDto.NAME);
		// String societeNameCol = combineProps(FicheStDto.SOCIETE, SocieteDto.NOM);
		String societeNameCol = FicheStDto.SOCIETE;
		String[] ids = { FicheStDto.ID, lotNameCol, FicheStDto.OBJECTIF, societeNameCol, FicheStDto.OBJ, FicheStDto.TRANSFERTS, FicheStDto.RD, FicheStDto.TS, FicheStDto.TOTAL, FicheStDto.TRAITE, FicheStDto.ARRETE, FicheStDto.NON_ARRETE, FicheStDto.PROVISION, FicheStDto.FINAL_TOTAL, FicheStDto.DEVISREFUSE, FicheStDto.ECART_M, FicheStDto.ECARTM1, FicheStDto.VARIATION_M, FicheStDto.ECART_DERNIER_POINT, FicheStDto.VARIATION_ECART_POINT_M, FicheStDto.AVCT_BA_PERCENTAGE, FicheStDto.TOTAL_CUMULE/*
																																																																																																																											 */, FicheStDto.AVCT_REEL_PERCENTAGE, FicheStDto.MONTANT_REEL, FicheStDto.TOTAL_CANTO, FicheStDto.TOTAL_BADGE, FicheStDto.TOTAL_GRUE, FicheStDto.TOTAL_LIFT, FicheStDto.TOTAL_BENNE, FicheStDto.TOTAL_NETTOY, FicheStDto.TOTAL_AUTRES, FicheStDto.TOTAL_PRORATA, FicheStDto.TOTAL2, FicheStDto.PRESTATION_TRAITE_PERCENTAGE, FicheStDto.TOTAL_PENALTY, FicheStDto.PRORATA_APPLIQUE_ST, FicheStDto.PRORATA_MARCHE, FicheStDto.PRORATA_SUR_RAD, FicheStDto.MARCHES_RESTANT_A_TRAITER_PERCENTAGE, FicheStDto.MARCHES_RESTANT_A_TRAITER, FicheStDto.VARIATION_DU_AUX_TRANSFERTS, FicheStDto.MANQUE_AGAGNERST_SANS_PRORATA };
		int commonWidth1 = 120;
		int commonWidth2 = 80;
		int[] columnsWidth = { 90, commonWidth1, commonWidth2, commonWidth1, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth1, commonWidth1, commonWidth1, commonWidth1, commonWidth1, commonWidth1 + 50, commonWidth1 + 30, commonWidth1 + 30, commonWidth1 + 70 };
		HorizontalAlignment[] horizontalAlignments = { HorizontalAlignment.CENTER };

		List<String> nonNumberColumns = Arrays.asList(
				FicheStDto.ID, lotNameCol, societeNameCol, FicheStDto.AVCT_BA_PERCENTAGE,
				FicheStDto.MARCHES_RESTANT_A_TRAITER_PERCENTAGE, FicheStDto.AVCT_REEL_PERCENTAGE);
		List<String> inputedColumns = Arrays.asList(FicheStDto.OBJECTIF, FicheStDto.ECARTM1, FicheStDto.ECART_DERNIER_POINT);

		AggregationRowConfig<GestionDto> totalSummary = new AggregationRowConfig<GestionDto>();
		totalSummary.setHtml(lotNameCol, label.toUpperCase());

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		for( int i = 0 ; i < ids.length ; i++ ) {
			String columnId = ids[i];
			SummaryColumnConfig column = new SummaryColumnConfig(columnId, headers[i], columnsWidth[i]);
			column.setAlignment(horizontalAlignments[0]);
			column.setMenuDisabled(true);
			column.setToolTip(headers[i]);
			columns.add(column);

			if( !nonNumberColumns.contains(columnId) ) {
				column.setNumberFormat(NumberFormat.getFormat(NUMBER_FORMAT));

				// Summary columns
				column.setSummaryType(SummaryType.SUM);
				column.setSummaryFormat(NumberFormat.getCurrencyFormat());

				// Total summary columns
				totalSummary.setSummaryType(columnId, SummaryType.SUM);
				totalSummary.setSummaryFormat(columnId, NumberFormat.getCurrencyFormat());

				if( inputedColumns.contains(columnId) ) {
					GridCellRenderer<AbstractDto> numberRenderer = createNumberRendererWithPermission(
							columnsWidth[i] - PADDING_2, role, user);
					column.setRenderer(numberRenderer);
					column.setEditor(new CellEditor(createLockNumberFieldWithPermission(null, role, user)));
				}
			}
		}

		ColumnModel cm = new ColumnModel(columns);
		cm.addAggregationRow(totalSummary);

		// Heading groups
		cm.addHeaderGroup(0, 1, new HeaderGroupConfig(messages.sousTraitant().toUpperCase(), 1, 3));
		cm.addHeaderGroup(0, 4, new HeaderGroupConfig(messages.budgetConforme().toUpperCase(), 1, 5));
		cm.addHeaderGroup(0, 9, new HeaderGroupConfig(messages.suiviTraite().toUpperCase(), 1, 6));
		cm.addHeaderGroup(0, 15, new HeaderGroupConfig("ECARTS", 1, 5));
		cm.addHeaderGroup(0, 20, new HeaderGroupConfig("AVANCEMENT / MARCHE", 1, 4));
		cm.addHeaderGroup(0, 24, new HeaderGroupConfig("RETENUES APPLIQUEES", 1, 11));
		cm.addHeaderGroup(0, 35, new HeaderGroupConfig("PRORATA / VARIATION", 1, 7));
		SummaryColumnConfig actionsColumn = (SummaryColumnConfig) cm.getColumnById(FicheStDto.ID);
		actionsColumn.setRenderer(createActionButtonsRenderer());

		String groupingColumnId = combineProps(FicheStDto.LOT_TYPE, LotTypeDto.NAME);
		SummaryColumnConfig<Number> groupingColumn = new SummaryColumnConfig<Number>(groupingColumnId, "Lot<br>type", 50);
		cm.getColumns().add(groupingColumn);

		GroupingStore<FicheStDto> store = new GroupingStore<FicheStDto>();
		store.groupBy(groupingColumnId);

		GroupSummaryView view = new GroupSummaryView();
		view.setShowGroupedColumn(false);

		final CustomEditorGrid<FicheStDto> grid = new CustomEditorGrid<FicheStDto>(store, cm);
		grid.setHeight(300);
		grid.setView(view);
		grid.getStore().addStoreListener(new StoreListener<FicheStDto>() {
			@Override
			public void handleEvent(StoreEvent<FicheStDto> e) {
				calculateValues();
				grid.getView().refresh(true);
			}
		});

		grid.addListener(Events.BeforeEdit, new Listener<GridEvent<FicheStDto>>() {
			@Override
			public void handleEvent(GridEvent<FicheStDto> be) {
				if(isAdminOrContributor(role, user)) {
					be.setCancelled(false);
				}
				else {
					be.setCancelled(true);
				}
			}
		});
		
		grid.addListener(Events.AfterEdit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				saveLayout.setEnabled(true);
				isEdit = true;
				bus.fireEvent(new IsEditEvent(isEdit));
			}
		});

		return grid;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createFicheStSummaryGrid() {
		String[] headers = { "", "Objectif", "Obj", "+/-", "RD", "TS", messages.total(), messages.traite(), messages.arrete(), messages.traiter(), messages.provision(), "Total Final".replaceAll(
				" ", "<br>"), messages.devisRefuse().replaceAll(" ", "<br>"), "Ecart M", "Ecart M-1", "Variation M", "Ecart dernier point", "Variation Ecart point / M", "% Avct BA", "Montant BA", "% Avct Réel", "Montant Réel", "Canto", "Badge", "Grue", "Lift", "Benne", "Nettoy", "Autres", "Prorata", "Total", "% Prestation / Traité", "Pénalité Facturé", "Prorata appliqué ST", "Prorata marché", "Prorata sur RAD", "Marchés restant à traiter", "Variation dû aux transferts", "Manque à gagner ST sans prorata" };
		String[] ids = { FicheStDto.ID, FicheStDto.OBJECTIF, FicheStDto.OBJ, FicheStDto.TRANSFERTS, FicheStDto.RD, FicheStDto.TS, FicheStDto.TOTAL, FicheStDto.TRAITE, FicheStDto.ARRETE, FicheStDto.NON_ARRETE, FicheStDto.PROVISION, FicheStDto.FINAL_TOTAL, FicheStDto.DEVISREFUSE, FicheStDto.ECART_M, FicheStDto.ECARTM1, FicheStDto.VARIATION_M, FicheStDto.ECART_DERNIER_POINT, FicheStDto.VARIATION_ECART_POINT_M, FicheStDto.AVCT_BA_PERCENTAGE, FicheStDto.TOTAL_CUMULE, FicheStDto.AVCT_REEL_PERCENTAGE, FicheStDto.MONTANT_REEL, FicheStDto.TOTAL_CANTO, FicheStDto.TOTAL_BADGE, FicheStDto.TOTAL_GRUE, FicheStDto.TOTAL_LIFT, FicheStDto.TOTAL_BENNE, FicheStDto.TOTAL_NETTOY, FicheStDto.TOTAL_AUTRES, FicheStDto.TOTAL_PRORATA, FicheStDto.TOTAL2, FicheStDto.PRESTATION_TRAITE_PERCENTAGE, FicheStDto.TOTAL_PENALTY, FicheStDto.PRORATA_APPLIQUE_ST, FicheStDto.PRORATA_MARCHE, FicheStDto.PRORATA_SUR_RAD, FicheStDto.MARCHES_RESTANT_A_TRAITER, FicheStDto.VARIATION_DU_AUX_TRANSFERTS, FicheStDto.MANQUE_AGAGNERST_SANS_PRORATA };
		int commonWidth = 80;
		int commonWidth2 = 120;
		int[] columnsWidth = { 150, commonWidth2, commonWidth, commonWidth2, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth };
		HorizontalAlignment[] horizontalAlignments = { HorizontalAlignment.CENTER };

		List<String> nonNumberColumns = Arrays.asList(FicheStDto.ID);

		AggregationRowConfig<GestionDto> totalSummary = new AggregationRowConfig<GestionDto>();
		totalSummary.setHtml(FicheStDto.ID, "TOTAL CHANTIER");

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		for( int i = 0 ; i < ids.length ; i++ ) {
			String columnId = ids[i];
			SummaryColumnConfig column = new SummaryColumnConfig(columnId, headers[i], columnsWidth[i]);
			column.setAlignment(horizontalAlignments[0]);
			column.setMenuDisabled(true);
			column.setToolTip(headers[i]);
			columns.add(column);

			if( !nonNumberColumns.contains(columnId) ) {
				column.setNumberFormat(NumberFormat.getFormat(INTEGER_FORMAT));

				// Summary columns
				column.setSummaryType(SummaryType.SUM);
				column.setSummaryFormat(NumberFormat.getCurrencyFormat());

				// Total summary columns
				totalSummary.setSummaryType(columnId, SummaryType.SUM);
				totalSummary.setSummaryFormat(columnId, NumberFormat.getCurrencyFormat());
			}
		}

		ColumnModel cm = new ColumnModel(columns);
		cm.addAggregationRow(totalSummary);

		// Heading groups
		cm.addHeaderGroup(0, 1, new HeaderGroupConfig("Objectif".toUpperCase(), 1, 1));
		cm.addHeaderGroup(0, 2, new HeaderGroupConfig(messages.budgetConforme().toUpperCase(), 1, 5));
		cm.addHeaderGroup(0, 7, new HeaderGroupConfig(messages.suiviTraite().toUpperCase(), 1, 6));
		cm.addHeaderGroup(0, 13, new HeaderGroupConfig("ECARTS", 1, 5));
		cm.addHeaderGroup(0, 18, new HeaderGroupConfig("AVANCEMENT / MARCHE", 1, 4));
		cm.addHeaderGroup(0, 22, new HeaderGroupConfig("RETENUES APPLIQUEES", 1, 11));
		cm.addHeaderGroup(0, 33, new HeaderGroupConfig("PRORATA / VARIATION", 1, 6));

		GroupingStore<FicheStDto> store = new GroupingStore<FicheStDto>();
		GroupSummaryView view = new GroupSummaryView();
		view.setShowGroupedColumn(false);

		ficheStSummaryGrid = new CustomEditorGrid<FicheStDto>(store, cm);
		ficheStSummaryGrid.setView(view);
		ficheStSummaryGrid.setHeight(120);
		calculateValues();
	}

	private String createGridReport(List<FicheStDto> grid, List<String> lstKeyGrid, Map<String, String> mapGrid,
			Map<String, String> mapTotalGroupGrid) {
		FicheStDto ficheStDto = null;
		double sumtotalObjectif = 0.0;
		double sumtotalObj = 0.0;
		double sumtotalTransfert = 0.0;
		double sumtotalRd = 0.0;
		double sumtotalTs = 0.0;
		double sumtotaltotal = 0.0;
		double sumtotalTraite = 0.0;
		double sumtotalArrete = 0.0;
		double sumtotalNonArete = 0.0;
		double sumtotalProvision = 0.0;
		double sumtotalTotalFinal = 0.0;
		double sumtotalDevisRefuse = 0.0;
		double sumtotalEcartM = 0.0;
		double sumtotalEcratM1 = 0.0;
		double sumtotalVariationM = 0.0;
		double sumtotalEcartDernierPoint = 0.0;
		double sumtotalVariationEcartPoint = 0.0;
		double summontantBA = 0.0;
		double summontantReel = 0.0;
		double sumtotalCanto = 0.0;
		double sumtotalBadge = 0.0;
		double sumtotalGrue = 0.0;
		double sumtotlaLift = 0.0;
		double sumtotalBenne = 0.0;
		double sumtotalNettoy = 0.0;
		double sumtotalAutres = 0.0;
		double sumtotalProrata = 0.0;
		double sumtotalTotal = 0.0;
		double sumtotalPrestationTraite = 0.0;
		double sumtotalPenaliteFacture = 0.0;
		double sumtotalProrataAppliqueSt = 0.0;
		double sumtotalProrataMarche = 0.0;
		double sumtotalProrataSuRad = 0.0;
		double sumtotalMarcheRestantATraiter = 0.0;
		double sumtotalVariationDuAuxTransferts = 0.0;
		double sumtotalManqueAGagnerStsansProrata = 0.0;

		double cellts = 0.0;
		double celltotlafinal = 0.0;

		NumberFormat numberFormat = NumberFormat.getFormat(Constants.NUMBER_FORMAT);
		for( int i = 0 ; i < lstKeyGrid.size() ; i++ ) {
			String grid1 = "";
			String sumTotalGroup1 = "";
			double totalObjectif = 0.0;
			double totalObj = 0.0;
			double totalTransfert = 0.0;
			double totalRd = 0.0;
			double totalTs = 0.0;
			double totaltotal = 0.0;
			double totalTraite = 0.0;
			double totalArrete = 0.0;
			double totalNonArete = 0.0;
			double totalProvision = 0.0;
			double totalTotalFinal = 0.0;
			double totalDevisRefuse = 0.0;
			double totalEcartM = 0.0;
			double totalEcratM1 = 0.0;
			double totalVariationM = 0.0;
			double totalEcartDernierPoint = 0.0;
			double totalVariationEcartPoint = 0.0;
			double montantBA = 0.0;
			double montantReel = 0.0;
			double totalCanto = 0.0;
			double totalBadge = 0.0;
			double totalGrue = 0.0;
			double totlaLift = 0.0;
			double totalBenne = 0.0;
			double totalNettoy = 0.0;
			double totalAutres = 0.0;
			double totalProrata = 0.0;
			double totalTotal = 0.0;
			double totalPrestationTraite = 0.0;
			double totalPenaliteFacture = 0.0;
			double totalProrataAppliqueSt = 0.0;
			double totalProrataMarche = 0.0;
			double totalProrataSuRad = 0.0;
			double totalMarcheRestantATraiterPercentage = 0.0;
			double totalMarcheRestantATraiter = 0.0;
			double totalVariationDuAuxTransferts = 0.0;
			double totalManqueAGagnerStsansProrata = 0.0;
			for( int j = 0 ; j < grid.size() ; j++ ) {
				ficheStDto = grid.get(j);
				if( lstKeyGrid.get(i).equalsIgnoreCase(ficheStDto.getLotType().getName()) ) {
					cellts = ficheStDto.getObj() + ficheStDto.getTransferts() + ficheStDto.getRd() + ficheStDto.getTs();
					celltotlafinal = ficheStDto.getTraite() + ficheStDto.getArrete() + ficheStDto.getNonArrete() + ficheStDto.getProvision();
					totalObjectif += ficheStDto.getObjectif();
					totalObj += ficheStDto.getObj();
					totalTransfert += ficheStDto.getTransferts();
					totalRd += ficheStDto.getRd();
					totalTs += ficheStDto.getTs();
					totaltotal += cellts;
					totalTraite += ficheStDto.getTraite();
					totalArrete += ficheStDto.getArrete();
					totalNonArete += ficheStDto.getNonArrete();
					totalProvision += ficheStDto.getProvision();
					totalTotalFinal += celltotlafinal;
					totalDevisRefuse += ficheStDto.getDevisRefuse();
					totalEcartM += ficheStDto.getEcartM();
					totalEcratM1 += ficheStDto.getEcartM1();
					totalVariationM += ficheStDto.getVariationM();
					totalEcartDernierPoint += ficheStDto.getEcartDernierPoint();
					totalVariationEcartPoint += ficheStDto.getVariationEcartPointM();
					montantBA += ficheStDto.getTotalCumule();
					montantReel += ficheStDto.getTraite();
					totalCanto += ficheStDto.getTotalCanto();
					totalBadge += ficheStDto.getTotalBadge();
					totalGrue += ficheStDto.getTotalGrue();
					totlaLift += ficheStDto.getTotalLift();
					totalBenne += ficheStDto.getTotalBenne();
					totalNettoy += ficheStDto.getTotalNettoy();
					totalAutres += ficheStDto.getTotalAutres();
					totalProrata += ficheStDto.getTotalProrata();
					totalTotal += ficheStDto.getTotal2();
					totalPrestationTraite += ficheStDto.getPrestationTraitePercentage();
					totalPenaliteFacture += ficheStDto.getTotalPenalty();
					totalProrataAppliqueSt += ficheStDto.getProrataAppliqueST();
					totalProrataMarche += ficheStDto.getProrataMarche();
					totalProrataSuRad += ficheStDto.getProrataSurRAD();
					totalMarcheRestantATraiterPercentage += ficheStDto.getMarchesRestantATraiterPercentage();
					totalMarcheRestantATraiter += ficheStDto.getMarchesRestantATraiter();
					totalVariationDuAuxTransferts += ficheStDto.getVariationDuAuxTransferts();
					totalManqueAGagnerStsansProrata += ficheStDto.getManqueAGagnerSTSansProrata();
					// Create row report
					grid1 += createRowReport(ficheStDto, cellts, celltotlafinal, numberFormat);
				}

			}
			// Calculate sum group 1
			sumTotalGroup1 += createTotalSumReport(
					numberFormat, "null", totalObjectif, null, totalObj, totalTransfert, totalRd, totalTs, totaltotal,
					totalTraite, totalArrete, totalNonArete, totalProvision, totalTotalFinal, totalDevisRefuse, totalEcartM,
					totalEcratM1, totalVariationM, totalEcartDernierPoint, totalVariationEcartPoint, null, montantBA, null,
					montantReel, totalCanto, totalBadge, totalGrue, totlaLift, totalBenne, totalNettoy, totalAutres,
					totalProrata, totalTotal, totalPrestationTraite, totalPenaliteFacture, totalProrataAppliqueSt,
					totalProrataMarche, totalProrataSuRad, totalMarcheRestantATraiterPercentage, totalMarcheRestantATraiter,
					totalVariationDuAuxTransferts, totalManqueAGagnerStsansProrata);

			mapGrid.put(lstKeyGrid.get(i), grid1);
			mapTotalGroupGrid.put(lstKeyGrid.get(i), sumTotalGroup1);

			sumtotalObjectif += totalObjectif;
			sumtotalObj += totalObj;
			sumtotalTransfert += totalTransfert;
			sumtotalRd += totalRd;
			sumtotalTs += totalTs;
			sumtotaltotal += totaltotal;
			sumtotalTraite += totalTraite;
			sumtotalArrete += totalArrete;
			sumtotalNonArete += totalNonArete;
			sumtotalProvision += totalProvision;
			sumtotalTotalFinal += totalTotalFinal;
			sumtotalDevisRefuse += totalDevisRefuse;
			sumtotalEcartM += totalEcartM;
			sumtotalEcratM1 += totalEcratM1;
			sumtotalVariationM += totalVariationM;
			sumtotalEcartDernierPoint += totalEcartDernierPoint;
			sumtotalVariationEcartPoint += totalVariationEcartPoint;
			summontantBA += montantBA;
			summontantReel += montantReel;
			sumtotalCanto += totalCanto;
			sumtotalBadge += totalBadge;
			sumtotalGrue += totalGrue;
			sumtotlaLift += totlaLift;
			sumtotalBenne += totalBenne;
			sumtotalNettoy += totalNettoy;
			sumtotalAutres += totalAutres;
			sumtotalProrata += totalProrata;
			sumtotalTotal += totalTotal;
			sumtotalPrestationTraite += totalPrestationTraite;
			sumtotalPenaliteFacture += totalPenaliteFacture;
			sumtotalProrataAppliqueSt += totalProrataAppliqueSt;
			sumtotalProrataMarche += totalProrataMarche;
			sumtotalProrataSuRad += totalProrataSuRad;
			sumtotalMarcheRestantATraiter += totalMarcheRestantATraiter;
			sumtotalVariationDuAuxTransferts += totalVariationDuAuxTransferts;
			sumtotalManqueAGagnerStsansProrata += totalManqueAGagnerStsansProrata;
		}
		String totalSumGrid = createTotalSumReport(
				numberFormat, "TOTAL ST", sumtotalObjectif, null, sumtotalObj, sumtotalTransfert, sumtotalRd, sumtotalTs,
				sumtotaltotal, sumtotalTraite, sumtotalArrete, sumtotalNonArete, sumtotalProvision, sumtotalTotalFinal,
				sumtotalDevisRefuse, sumtotalEcartM, sumtotalEcratM1, sumtotalVariationM, sumtotalEcartDernierPoint,
				sumtotalVariationEcartPoint, null, summontantBA, null, summontantReel, sumtotalCanto, sumtotalBadge,
				sumtotalGrue, sumtotlaLift, sumtotalBenne, sumtotalNettoy, sumtotalAutres, sumtotalProrata, sumtotalTotal,
				sumtotalPrestationTraite, sumtotalPenaliteFacture, sumtotalProrataAppliqueSt, sumtotalProrataMarche,
				sumtotalProrataSuRad, null, sumtotalMarcheRestantATraiter, sumtotalVariationDuAuxTransferts,
				sumtotalManqueAGagnerStsansProrata);
		return totalSumGrid;
	}

	private String[] createGridReportSummary(List<FicheStDto> grid) {

		FicheStDto ficheStDto = null;
		double cellts = 0.0;
		double celltotlafinal = 0.0;
		double totalObjectif = 0.0;
		double totalObj = 0.0;
		double totalTransfert = 0.0;
		double totalRd = 0.0;
		double totalTs = 0.0;
		double totaltotal = 0.0;
		double totalTraite = 0.0;
		double totalArrete = 0.0;
		double totalNonArete = 0.0;
		double totalProvision = 0.0;
		double totalTotalFinal = 0.0;
		double totalDevisRefuse = 0.0;
		double totalEcartM = 0.0;
		double totalEcratM1 = 0.0;
		double totalVariationM = 0.0;
		double totalEcartDernierPoint = 0.0;
		double totalVariationEcartPoint = 0.0;
		double totalAvctBA = 0.0;
		double montantBA = 0.0;
		double montantReel = 0.0;
		double totalCanto = 0.0;
		double totalBadge = 0.0;
		double totalGrue = 0.0;
		double totlaLift = 0.0;
		double totalBenne = 0.0;
		double totalNettoy = 0.0;
		double totalAutres = 0.0;
		double totalProrata = 0.0;
		double totalTotal = 0.0;
		double totalPrestationTraite = 0.0;
		double totalPenaliteFacture = 0.0;
		double totalProrataAppliqueSt = 0.0;
		double totalProrataMarche = 0.0;
		double totalProrataSuRad = 0.0;
		double totalMarcheRestantATraiterPercentage = 0.0;
		double totalMarcheRestantATraiter = 0.0;
		double totalVariationDuAuxTransferts = 0.0;
		double totalManqueAGagnerStsansProrata = 0.0;
		String grid1 = "";
		NumberFormat numberFormat = NumberFormat.getFormat(Constants.NUMBER_FORMAT);
		for( int i = 0 ; i < grid.size() ; i++ ) {
			ficheStDto = grid.get(i);
			cellts = ficheStDto.getObj() + ficheStDto.getTransferts() + ficheStDto.getRd() + ficheStDto.getTs();
			celltotlafinal = ficheStDto.getTraite() + ficheStDto.getArrete() + ficheStDto.getNonArrete() + ficheStDto.getProvision();
			totalObjectif += ficheStDto.getObjectif();
			totalObj += ficheStDto.getObj();
			totalTransfert += ficheStDto.getTransferts();
			totalRd += ficheStDto.getRd();
			totalTs += ficheStDto.getTs();
			totaltotal += cellts;
			totalTraite += ficheStDto.getTraite();
			totalArrete += ficheStDto.getArrete();
			totalNonArete += ficheStDto.getNonArrete();
			totalProvision += ficheStDto.getProvision();
			totalTotalFinal += celltotlafinal;
			totalDevisRefuse += ficheStDto.getDevisRefuse();
			totalEcartM += ficheStDto.getEcartM();
			totalEcratM1 += ficheStDto.getEcartM1();
			totalVariationM += ficheStDto.getVariationM();
			totalEcartDernierPoint += ficheStDto.getEcartDernierPoint();
			totalVariationEcartPoint += ficheStDto.getVariationEcartPointM();
			totalAvctBA += ficheStDto.getAvctBAPercentage();
			montantBA += ficheStDto.getTotalCumule();
			montantReel += ficheStDto.getTraite();
			totalCanto += ficheStDto.getTotalCanto();
			totalBadge += ficheStDto.getTotalBadge();
			totalGrue += ficheStDto.getTotalGrue();
			totlaLift += ficheStDto.getTotalLift();
			totalBenne += ficheStDto.getTotalBenne();
			totalNettoy += ficheStDto.getTotalNettoy();
			totalAutres += ficheStDto.getTotalAutres();
			totalProrata += ficheStDto.getTotalProrata();
			totalTotal += ficheStDto.getTotal2();
			totalPrestationTraite += ficheStDto.getPrestationTraitePercentage();
			totalPenaliteFacture += ficheStDto.getTotalPenalty();
			totalProrataAppliqueSt += ficheStDto.getProrataAppliqueST();
			totalProrataMarche += ficheStDto.getProrataMarche();
			totalProrataSuRad += ficheStDto.getProrataSurRAD();
			totalMarcheRestantATraiterPercentage += ficheStDto.getMarchesRestantATraiterPercentage();
			totalMarcheRestantATraiter += ficheStDto.getMarchesRestantATraiter();
			totalVariationDuAuxTransferts += ficheStDto.getVariationDuAuxTransferts();
			totalManqueAGagnerStsansProrata += ficheStDto.getManqueAGagnerSTSansProrata();
			grid1 += createRowReport(ficheStDto, cellts, celltotlafinal, numberFormat);
		}
		String totalSumGrid = createTotalSumReport(
				numberFormat, "TOTAL CHANTIER", totalObjectif, null, totalObj, totalTransfert, totalRd, totalTs, totaltotal,
				totalTraite, totalArrete, totalNonArete, totalProvision, totalTotalFinal, totalDevisRefuse, totalEcartM,
				totalEcratM1, totalVariationM, totalEcartDernierPoint, totalVariationEcartPoint, totalAvctBA, montantBA,
				null, montantReel, totalCanto, totalBadge, totalGrue, totlaLift, totalBenne, totalNettoy, totalAutres,
				totalProrata, totalTotal, totalPrestationTraite, totalPenaliteFacture, totalProrataAppliqueSt,
				totalProrataMarche, totalProrataSuRad, totalMarcheRestantATraiterPercentage, totalMarcheRestantATraiter,
				totalVariationDuAuxTransferts, totalManqueAGagnerStsansProrata);

		String[] arr = new String[2];
		arr[0] = grid1;
		arr[1] = totalSumGrid;
		return arr;
	}

	private String createRowReport(FicheStDto ficheStDto, double cellts, double celltotlafinal, NumberFormat numberFormat) {
		String row = ficheStDto.getLot().getName() + Constants.SEPRATE + numberFormat.format(ficheStDto.getObjectif()) + Constants.SEPRATE + ficheStDto.getSociete() + Constants.SEPRATE + numberFormat.format(ficheStDto.getObj()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTransferts()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getRd()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTs()) + Constants.SEPRATE + numberFormat.format(cellts) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTraite()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getArrete()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getNonArrete()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getProvision()) + Constants.SEPRATE + numberFormat.format(celltotlafinal) + Constants.SEPRATE + numberFormat.format(ficheStDto.getDevisRefuse()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getEcartM()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getEcartM1()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getVariationM()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getEcartDernierPoint()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getVariationEcartPointM()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getAvctBAPercentage()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotalCumule()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getAvctReelPercentage()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTraite()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotalCanto()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotalBadge()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotalGrue()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotalLift()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotalBenne()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotalNettoy()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotalAutres()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotalProrata()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotal2()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getPrestationTraitePercentage()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getTotalPenalty()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getProrataAppliqueST()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getProrataMarche()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getProrataSurRAD()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getMarchesRestantATraiterPercentage()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getMarchesRestantATraiter()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getVariationDuAuxTransferts()) + Constants.SEPRATE + numberFormat.format(ficheStDto.getManqueAGagnerSTSansProrata()) + Constants.SEPRATE;
		return row;
	}

	/**
	 * Creat Total Sum Report
	 * 
	 * @param numberFormat
	 * @param name
	 * @param params
	 * @return
	 */
	private String createTotalSumReport(NumberFormat numberFormat, String name, Double... params) {
		String totalSumGrid = name + Constants.SEPRATE;
		for( Double param : params ) {
			if( param != null ) {
				totalSumGrid += numberFormat.format(param);
			}
			else
				totalSumGrid += "null";
			totalSumGrid += Constants.SEPRATE;
		}
		return totalSumGrid;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createTransfertPpGrid(final RoleModel role, final UtilisateurGrpModel user, final ChantierModel chantier) {
		int commonWidth = 90;
		String[] headers = { "", messages.label(), "Objectif", "Obj", "+/-", "RD", "TS", messages.total() };
		String[] ids = { TransfertPpSummaryDto.ID, TransfertPpSummaryDto.LABEL, TransfertPpSummaryDto.OBJECTIVE, TransfertPpSummaryDto.OBJ, TransfertPpSummaryDto.DEVERS, TransfertPpSummaryDto.RD, TransfertPpSummaryDto.TS, TransfertPpSummaryDto.TOTAL };
		int[] columnsWidth = { 80, 150, 150, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth };
		HorizontalAlignment[] horizontalAlignments = { HorizontalAlignment.CENTER };

		AggregationRowConfig<TransfertPpSummaryDto> totalSummary = new AggregationRowConfig<TransfertPpSummaryDto>();
		totalSummary.setHtml(TransfertPpSummaryDto.LABEL, "Total PP".toUpperCase());

		ColumnModel cm = createColumnModel(headers, ids, columnsWidth, horizontalAlignments);
		transfertPpGrid = new CustomEditorGrid(new ListStore<TransfertPpSummaryDto>(), cm);
		transfertPpGrid.getView().setAutoFill(true);

		List<String> summaryColumns = Arrays.asList(
				TransfertPpSummaryDto.OBJECTIVE, TransfertPpSummaryDto.OBJ, TransfertPpSummaryDto.DEVERS,
				TransfertPpSummaryDto.RD, TransfertPpSummaryDto.TS, TransfertPpSummaryDto.TOTAL);

		for( String columnId : summaryColumns ) {
			ColumnConfig column = cm.getColumnById(columnId);
			column.setNumberFormat(NumberFormat.getFormat(NUMBER_FORMAT));
			totalSummary.setSummaryType(columnId, SummaryType.SUM);
			totalSummary.setSummaryFormat(columnId, NumberFormat.getCurrencyFormat());
		}
		cm.addAggregationRow(totalSummary);

		cm.getColumnById(TransfertPpSummaryDto.ID).setRenderer(new GridCellRenderer<TransfertPpSummaryDto>() {
			@Override
			public Object render(final TransfertPpSummaryDto model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex,
					ListStore<TransfertPpSummaryDto> store, Grid<TransfertPpSummaryDto> grid) {

				Image viewButton = createViewButton();
				viewButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// bus.fireEvent(new LoadTransfertppSummaryDtoEvent(model));
						GuiUtil.gotoEcran(new TransfertppEcran(chantier, model.getRefTransfertPpId(), role, user));
					}
				});
				return viewButton;
			}
		});

		ColumnConfig objectiveColumn = cm.getColumnById(TransfertPpSummaryDto.OBJECTIVE);
		objectiveColumn.setRenderer(createNumberRendererWithPermission(objectiveColumn.getWidth() - PADDING, role, user));
		objectiveColumn.setEditor(new CellEditor(createLockNumberFieldWithPermission(null, role, user)));
		transfertPpGrid.setAutoHeight(true);
		loadTransfertPpData();
	}

	protected boolean isValid() {
		return(formPanel != null && formPanel.isValid());
	}

	private void loadFicheStData() {
		showLoading(ficheStGrid1);
		showLoading(ficheStGrid2);
		clientFicheStService.findByChantierId(chantier.getId(), new AsyncCallbackWithErrorResolution<List<FicheStDto>>() {
			@Override
			public void onFailure(Throwable caught) {
				ficheStGrid1.unmask();
				ficheStGrid2.unmask();
				super.onFailure(caught);
			}

			@Override
			public void onSuccess(final List<FicheStDto> results) {
				List<FicheStDto> list1 = new ArrayList<FicheStDto>();
				List<FicheStDto> list2 = new ArrayList<FicheStDto>();
				Map<Integer, String> mapPrestations = new HashMap<Integer, String>();
				Map<Integer, String> mapConducteurdetravaux = new HashMap<Integer, String>();
				Map<Integer, String> mapSociete = new HashMap<Integer, String>();
				String prestationValue = "";
				for( FicheStDto ficheStDto : results ) {
					mapConducteurdetravaux.put(ficheStDto.getId(), ficheStDto.getIdSiTravaux());

					mapSociete.put(ficheStDto.getId(), ficheStDto.getSociete());

					prestationValue = ficheStDto.getPrestaCanto() + Constants.SEPRATE + ficheStDto.getPrestaBadge() + Constants.SEPRATE + ficheStDto.getPrestaGrue() + Constants.SEPRATE + ficheStDto.getPrestaLift() + Constants.SEPRATE + ficheStDto.getPrestaBenne() + Constants.SEPRATE + ficheStDto.getPrestaNettoyage() + Constants.SEPRATE + ficheStDto.getPrestaProrata();
					mapPrestations.put(ficheStDto.getId(), prestationValue);
					LotTypeDto lotType = ficheStDto.getLotType();
					if( lotType != null && "Honoraires".equalsIgnoreCase(lotType.getName()) ) {
						list2.add(ficheStDto);
					}
					else {
						list1.add(ficheStDto);
					}
				}
				navigation.getContext().setCurrentChantier(chantier);
				navigation.getContext().setMapConducteurdetravaux(mapConducteurdetravaux);
				navigation.getContext().setMapSociete(mapSociete);
				navigation.getContext().setMapPrestations(mapPrestations);

				addData(ficheStGrid1, list1);
				if( list2 != null && list2.size() > 0 ) {
					ficheStGrid2.show();
					addData(ficheStGrid2, list2);
				}
				List<FicheStDto> allFicheStDto = new ArrayList<FicheStDto>();
				allFicheStDto.addAll(list1);
				allFicheStDto.addAll(list2);
				buttons.setListFicheSt(allFicheStDto);
			}

			private void addData(EditorGrid<FicheStDto> ficheStGrid, List<FicheStDto> results) {
				if( ficheStGrid != null ) {
					ficheStGrid.getStore().removeAll();
					ficheStGrid.getStore().add(results);
					ficheStGrid.getView().refresh(false);
					ficheStGrid.unmask();
				}
			}

		});
	}

	private void loadTransfertPpData() {
		showLoading(transfertPpGrid);

		ClientRefTransfertppServiceAsync clientRefTransfertppService = ClientRefTransfertppServiceAsync.Util.getInstance();
		clientRefTransfertppService.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
			@Override
			public void onFailure(Throwable caught) {
				transfertPpGrid.unmask();
				super.onFailure(caught);
			}

			@Override
			public void onSuccess(List<SimpleDto> refTransfertPpList) {
				transfertPpGrid.unmask();

				// Sort by Id
				Collections.sort(refTransfertPpList, new Comparator<SimpleDto>() {
					@Override
					public int compare(SimpleDto o1, SimpleDto o2) {
						return o1.getId().compareTo(o2.getId());
					}
				});

				final List<FicheTransfertppDto> ficheTransfertPpList2 = new ArrayList<FicheTransfertppDto>();
				final Map<FicheTransfertppDto, List<LigTransfertppModel>> map = new HashMap<FicheTransfertppDto, List<LigTransfertppModel>>();
				for( SimpleDto refTransfertPp : refTransfertPpList ) {
					FicheTransfertppDto ficheTransfertPp = new FicheTransfertppDto();
					ficheTransfertPp.setRefTransfertPp(refTransfertPp);
					ficheTransfertPp.setChantier(chantier);

					map.put(ficheTransfertPp, new ArrayList<LigTransfertppModel>());
					ficheTransfertPpList2.add(ficheTransfertPp);
				}
				ClientFicheTransfertppServiceAsync clientFicheTransfertppService = ClientFicheTransfertppServiceAsync.Util.getInstance();
				clientFicheTransfertppService.findByChantierId(
						chantier.getId(), new AsyncCallbackWithErrorResolution<List<FicheTransfertppDto>>() {
							@Override
							public void onFailure(Throwable caught) {
								transfertPpGrid.unmask();
								super.onFailure(caught);
							}

							@Override
							public void onSuccess(List<FicheTransfertppDto> ligTransfertPpList) {
								for( FicheTransfertppDto ficheTransfertppDto : ligTransfertPpList ) {
									map.remove(ficheTransfertppDto);
									map.put(ficheTransfertppDto, ficheTransfertppDto.getLigTransfertPps());
								}

								List<TransfertPpSummaryDto> transfertPpSummaryList = new ArrayList<TransfertPpSummaryDto>();
								Set<FicheTransfertppDto> keys = map.keySet();
								Iterator<FicheTransfertppDto> iter = keys.iterator();
								while( iter.hasNext() ) {
									FicheTransfertppDto ficheTransfertpp = iter.next();
									TransfertPpSummaryDto transfertPpSummary = new TransfertPpSummaryDto(
											ficheTransfertpp.getChantier().getId(),
											ficheTransfertpp.getRefTransfertPp().getId(),
											ficheTransfertpp.getRefTransfertPp().getLabel());
									transfertPpSummary.setObjective(ficheTransfertpp.getObjectif());
									transfertPpSummary.setLigTransfertPps(map.get(ficheTransfertpp));

									transfertPpSummaryList.add(transfertPpSummary);
								}

								transfertPpGrid.getStore().removeAll();
								transfertPpGrid.getStore().add(transfertPpSummaryList);
							}
						});
			}
		});
	}
}
