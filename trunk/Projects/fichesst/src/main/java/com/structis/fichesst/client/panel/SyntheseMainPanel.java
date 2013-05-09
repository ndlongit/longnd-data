package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.store.StoreSorter;
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
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
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
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.structis.fichesst.client.ecran.FicheSTEcran;
import com.structis.fichesst.client.ecran.TransfertppEcran;
import com.structis.fichesst.client.event.ExportAllFicheStRefracEvent;
import com.structis.fichesst.client.event.ExportAllFichesstEvent;
import com.structis.fichesst.client.event.ExportSyntheseEvent;
import com.structis.fichesst.client.event.ModificationEvent;
import com.structis.fichesst.client.event.SyntheseEvent;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.handler.ExportAllFichesstEventHandler;
import com.structis.fichesst.client.handler.ExportAllFichestRefracEventHandler;
import com.structis.fichesst.client.handler.ExportSyntheseHandler;
import com.structis.fichesst.client.handler.ModificationHandler;
import com.structis.fichesst.client.handler.SyntheseHandler;
import com.structis.fichesst.client.popup.FicheSTSelectionPopup;
import com.structis.fichesst.client.service.ClientExportService;
import com.structis.fichesst.client.service.ClientExportServiceAsync;
import com.structis.fichesst.client.service.ClientFicheStService;
import com.structis.fichesst.client.service.ClientFicheStServiceAsync;
import com.structis.fichesst.client.service.ClientFicheTransfertppServiceAsync;
import com.structis.fichesst.client.service.ClientLotTypeServiceAsync;
import com.structis.fichesst.client.service.ClientRefTransfertppServiceAsync;
import com.structis.fichesst.client.util.CookieUtil;
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
import com.structis.fichesst.shared.dto.RefTransfertPPDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.TransfertPpSummaryDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.util.Constants;

public class SyntheseMainPanel extends AbstractPanel {

	private static final String HONORAIRES_LOT_TYPE_NAME = "Honoraires";
	public ScrollPanel scrollpane;
	private SyntheseButtonsPanel buttons;;

	private HTML save;

	private LayoutContainer saveLayout;

	private HTML save2;

	private LayoutContainer saveLayout2;

	private final ChantierModel chantier;

	private final ClientFicheStServiceAsync clientFicheStService = GWT.create(ClientFicheStService.class);
	private final ClientExportServiceAsync clientExportService = GWT.create(ClientExportService.class);
	private final ClientLotTypeServiceAsync clientLotTypeService = ClientLotTypeServiceAsync.Util.getInstance();

	private CustomEditorGrid<FicheStDto> ficheStGrid1;

	private CustomEditorGrid<FicheStDto> ficheStGrid2;

	private CustomEditorGrid<FicheStDto> ficheStSummaryGrid;

	private FormPanel formPanel;

	private FormPanel formPanel2;

	boolean isEdit = false;

	private NumberField prorataTheorique;

	private final RoleModel role;

	private CustomEditorGrid<TransfertPpSummaryDto> transfertPpGrid;
	List<FicheStDto> listFicheStDto = new ArrayList<FicheStDto>();
	private final UtilisateurGrpModel user;

	public SyntheseMainPanel(SimpleEventBus b, ChantierModel c, RoleModel r, UtilisateurGrpModel u) {
		super();
		this.bus = b;
		this.chantier = c;
		this.role = r;
		this.user = u;

		navigation.getContext().setCurrentChantier(chantier);
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
		formPanel.add(chatierName);
		chatierName.addListener(Events.OnKeyUp, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (!formPanel.isValid()) {
					return;
				}

				isEdit = true;
				bus.fireEvent(new ModificationEvent(isEdit));
			}
		});

		prorataTheorique = createIntegerField("Prorata Théorique", true);
		prorataTheorique.setName(ChantierModel.PRORATA_THEORIQUE);
		prorataTheorique.addListener(Events.OnKeyUp, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (!formPanel2.isValid()) {
					return;
				}
				isEdit = true;
				bus.fireEvent(new ModificationEvent(isEdit));
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
		topPanel.setHeight(27);
		topPanel.setLayout(new BorderLayout());
		topPanel.add(linksPanel, new BorderLayoutData(LayoutRegion.EAST, 635));
		topPanel.add(formPanel2, new BorderLayoutData(LayoutRegion.WEST, 500));
		fldstNewFieldset_1.add(topPanel);
		fldstNewFieldset_1.add(new HTML("<br>"));
		saveLayout = new LayoutContainer();

		save = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveSynthese(), false);
		save.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		save.setStyleName(ACTION_HTML4);
		save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String styleName = save.getStyleName();
				if (ACTION_HTML.equalsIgnoreCase(styleName)) {
					bus.fireEvent(new SyntheseEvent());
					bus.fireEvent(new ModificationEvent(false));
					save.removeStyleName(ACTION_HTML);
					save.addStyleName(ACTION_HTML4);
				}
			}
		});

		saveLayout.add(save);
		saveLayout.setEnabled(false);
		linksPanel.add(saveLayout);
		if (isAdminOrContributor(role, user)) {
			save.setVisible(true);
			chatierName.enable();
			prorataTheorique.enable();
		} else {
			save.setVisible(false);
			chatierName.disable();
			prorataTheorique.disable();
		}
		// synthese button panel
		bus.addHandler(ExportAllFichesstEvent.TYPE, new ExportAllFichesstEventHandler() {

			@Override
			public void onloadEvent(ExportAllFichesstEvent event) {
				List<FicheStDto> allModels = addListFichest();
				showFicheStReport(bus, allModels, true, "Impression de plusieurs sous-traitants");
			}
		});
		bus.addHandler(ExportAllFicheStRefracEvent.TYPE, new ExportAllFichestRefracEventHandler() {

			@Override
			public void onloadEvent(ExportAllFicheStRefracEvent event) {
				List<FicheStDto> allModels = addListFichest();
				showFicheStReport(bus, allModels, true, "Impression de plusieurs sous-traitants");
			}
		});

		HTML printSubcontractor = new HTML("<img src='./images/imprimer.png'/> " + messages.printSubcontractor(), false);
		printSubcontractor.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		printSubcontractor.setStyleName(ACTION_HTML);
		linksPanel.add(new HTML(LINKS_SPACE, false));
		linksPanel.add(printSubcontractor);
		printSubcontractor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				List<FicheStDto> allModels = addListFichest();
				showFicheStReport(bus, allModels, true, "Impression de plusieurs refacturations");
			}

		});

		HTML printRefacturations = new HTML("<img src='./images/imprimer.png'/> " + messages.printRefacturations(), false);
		printRefacturations.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		printRefacturations.setStyleName(ACTION_HTML);
		linksPanel.add(new HTML(LINKS_SPACE, false));
		linksPanel.add(printRefacturations);
		printRefacturations.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				List<FicheStDto> allModels = addListFichest();
				showFicheStReport(bus, allModels, false, "Impression de plusieurs refacturations");
			}
		});

		ficheStGrid1 = createFicheStGrid(messages.totalSt());
		ficheStGrid1.setId("fichestGrid1");
		ficheStGrid1.addListener(com.extjs.gxt.ui.client.event.Events.BodyScroll, new Listener<BaseEvent>() {
			@SuppressWarnings("rawtypes")
			@Override
			public void handleEvent(BaseEvent be) {
				El scroller = ficheStGrid2.getView().getScroller();
				scroller.scrollTo("left", ((EditorGrid) be.getSource()).getView().getScroller().getScrollLeft());
			}
		});

		fldstNewFieldset_1.add(ficheStGrid1);
		fldstNewFieldset_1.add(new Html("<br/>"));

		ficheStGrid2 = createFicheStGrid(HONORAIRES_LOT_TYPE_NAME);
		ficheStGrid2.setHideHeaders(true);
		ficheStGrid2.setId("fichestGrid2");
		ficheStGrid2.addListener(com.extjs.gxt.ui.client.event.Events.BodyScroll, new Listener<BaseEvent>() {
			@SuppressWarnings("rawtypes")
			@Override
			public void handleEvent(BaseEvent be) {
				El scroller = ficheStGrid1.getView().getScroller();
				scroller.scrollTo("left", ((EditorGrid) be.getSource()).getView().getScroller().getScrollLeft());
			}
		});

		ficheStGrid2.hide();
		fldstNewFieldset_1.add(ficheStGrid2);
		loadFicheStData();
		if (isAdminOrContributor(role, user)) {
			FlexTable ft1 = new FlexTable();
			ft1.setWidget(0, 0, new HTML(SPACES_4));
			HTML addRow = new HTML("<img src='./images/ajouter.png'/> " + messages.newSubcontractor(), false);
			addRow.setStyleName("actionHTML2");
			ft1.setWidget(0, 1, addRow);
			addRow.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem("newfichest");
					GuiUtil.gotoEcran(new FicheSTEcran());
				}
			});

			fldstNewFieldset_1.add(ft1);
		}

		FieldSet transferPpFieldSet = new CustomFieldSet();
		transferPpFieldSet.setHeading("SYNTHESE DES TRANSFERTS PP");
		transferPpFieldSet.setLayout(new BorderLayout());
		setDefaultBackgroundColor(transferPpFieldSet);
		saveLayout2 = new LayoutContainer();
		save2 = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveSynthese(), false);
		save2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		save2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String styleName = save2.getStyleName();
				if (ACTION_HTML.equalsIgnoreCase(styleName)) {
					if (!formPanel2.isValid()) {
						return;
					}

					bus.fireEvent(new SyntheseEvent());
					disableSaveButtons();
				}
			}
		});

		saveLayout2.add(save2);
		saveLayout2.setEnabled(false);
		transferPpFieldSet.add(saveLayout2, new BorderLayoutData(LayoutRegion.EAST));
		if (isAdminOrContributor(role, user)) {
			save2.setVisible(true);
		} else {
			save2.setVisible(false);
		}

		createTransfertPpGrid(role, user, chantier);
		transferPpFieldSet.add(transfertPpGrid, new BorderLayoutData(LayoutRegion.WEST, 1000));
		add(transferPpFieldSet);
		transferPpFieldSet.setHeight(450);

		transfertPpGrid.addListener(Events.BeforeEdit, new Listener<GridEvent<TransfertPpSummaryDto>>() {
			@Override
			public void handleEvent(GridEvent<TransfertPpSummaryDto> be) {
				@SuppressWarnings("unused")
				TransfertPpSummaryDto transfertPpSummaryDto = transfertPpGrid.getSelectionModel().getSelectedItem();
				if (isAdminOrContributor(role, user)) {
					be.setCancelled(false);
				} else {
					be.setCancelled(true);
				}
			}
		});
		transfertPpGrid.addListener(Events.AfterEdit, new Listener<GridEvent<TransfertPpSummaryDto>>() {
			@Override
			public void handleEvent(GridEvent<TransfertPpSummaryDto> be) {
				isEdit = true;
				bus.fireEvent(new ModificationEvent(isEdit));
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
		bus.fireEvent(new ModificationEvent(isEdit));
		bus.addHandler(SyntheseEvent.TYPE, new SyntheseHandler() {
			@Override
			public void onEvent(SyntheseEvent event) {
				if (!isValid()) {
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
						bus.fireEvent(new ModificationEvent(false));
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
				for (TransfertPpSummaryDto transfertPpSummary : transfertPpSummaryList) {
					FicheTransfertppDto ficheTransfertppDto = new FicheTransfertppDto();
					ficheTransfertppDto.setObjectif(transfertPpSummary.getObjective());
					ficheTransfertppDto.setChantier(SyntheseMainPanel.this.chantier);
					RefTransfertPPDto refTransfertPp = new RefTransfertPPDto();
					refTransfertPp.setId(transfertPpSummary.getRefTransfertPpId());
					ficheTransfertppDto.setRefTransfertPp(refTransfertPp);
					ficheTransfertppDtoList.add(ficheTransfertppDto);
				}

				clientFicheStService.updateSynthese(chantier, ficheStList, ficheTransfertppDtoList, callback);
			}
		});

		bus.addHandler(ExportSyntheseEvent.TYPE, new ExportSyntheseHandler() {

			@Override
			public void onExportSyntheseEcran(ExportSyntheseEvent event) {

				// Information Chantier
				String chantierN = chatierName.getValue();
				chantier.setNom(chantierN);

				if (prorataTheorique.getValue() != null) {
					Integer prorata = prorataTheorique.getValue().intValue();
					chantier.setProrataTheorique(prorata);
				}
				// Synthese des fiches st
				// + Grid 1
				List<FicheStDto> fichestGrid1 = ficheStGrid1.getStore().getModels();
				List<FicheStDto> fichestGrid2 = ficheStGrid2.getStore().getModels();
				List<FicheStDto> fichestGrid4 = ficheStSummaryGrid.getStore().getModels();
				List<TransfertPpSummaryDto> listTransfertPpGrid = transfertPpGrid.getStore().getModels();
				clientExportService.exportSyntheseData(chantier, fichestGrid1, fichestGrid2, fichestGrid4, listTransfertPpGrid,
						new AsyncCallbackWithErrorResolution<String>() {
							@Override
							public void onSuccess(String arg0) {
								showDownloadDialog(arg0);
							}
						});

			}
		});

		disableSaveButtons();

		bus.addHandler(ModificationEvent.TYPE, new ModificationHandler() {
			@Override
			public void onload(ModificationEvent event) {
				if (Boolean.TRUE.equals(event.getIsEdit())) {
					enableSaveButtons();
				} else {
					disableSaveButtons();
				}
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

		if (value == null) {
			model.set(ChantierModel.PRORATA_THEORIQUE, 0); // show 0 if no value
			// is specified
		}

		// Auto binding fields
		binding2.autoBind();
		binding2.bind(model);
	}

	private void calculateValues() {
		if (ficheStSummaryGrid == null) {
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
		List<TransfertPpSummaryDto> list3 = transfertPpGrid.getStore().getModels();
		for (FicheStDto ficheStDto : allModels) {
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

			totalEcartDernierPoint += ficheStDto.getGestEcartDernierPt();
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
		for (TransfertPpSummaryDto transfertPpSummaryDto : list3) {
			totalObjective += transfertPpSummaryDto.getObjective();
		}

		ficheStSummaryGrid.getStore().removeAll();
		FicheStDto model = new FicheStDto();
		model.set("temp01", "TOTAL CHANTIER");
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

		model.setGestEcartDernierPt(totalEcartDernierPoint);
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
		GridCellRenderer<FicheStDto> actionButtonsRenderer = new GridCellRenderer<FicheStDto>() {
			@Override
			public Object render(final FicheStDto model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex,
					int colIndex, final ListStore<FicheStDto> store, Grid<FicheStDto> grid) {
				Integer id = model.getId();
				if (id == null || id <= 0) {
					return "";
				} else {
					LayoutContainer layoutcell = new LayoutContainer();
					layoutcell.setLayout(new ColumnLayout());

					Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
						@Override
						public void handleEvent(MessageBoxEvent be) {
							Button btt = be.getButtonClicked();
							if (Dialog.OK.equals(btt.getItemId())) {
								if (store == null) {
									return;
								}

								Integer id = model.getId();
								if (id == null) {
									store.remove(model);
								} else {
									clientFicheStService.delete(id, new AsyncCallbackWithErrorResolution<Void>() {
										@Override
										public void onFailure(Throwable caught) {
											super.onFailure(caught);
										}

										@Override
										public void onSuccess(Void result) {
											store.remove(model);
											ficheStGrid2.getView().refresh(true);
											isEdit = true;
											bus.fireEvent(new ModificationEvent(isEdit));
										};
									});
								}
							}
						}
					};
					// todo:delete button
					Widget deleteButton = createDeleteButton(callback);
					if (isAdminOrContributor(role, user)) {
						layoutcell.add(deleteButton);
					} else {
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
							// GuiUtil.gotoEcran(new FicheSTEcran(chantier,
							// model.getId(), role, user));
							// bus.fireEvent(new
							// LoadFichestEcranEvent(chantier,model));
							CookieUtil.setCurrentFichest(model.getId().toString());
							navigation.getContext().setSocieteName(model.getSociete());
							navigation.getContext().setFichestId(model.getId());
							navigation.getContext().setCurrentChantier(chantier);
							History.newItem(model.getId() + "&fichest");
							GuiUtil.gotoEcran(new FicheSTEcran());
						}
					});

					layoutcell.add(new Html(BUTTONS_SPACE));
					layoutcell.add(exportButton);
					layoutcell.add(new Html(BUTTONS_SPACE));
					layoutcell.add(viewButton);
					return layoutcell;
				}
			}
		};

		return actionButtonsRenderer;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private CustomEditorGrid<FicheStDto> createFicheStGrid(String label) {
		String[] headers = { "", messages.lot(), "Objectif", messages.societe(), "Obj", "+/-", "RD", "TS", messages.total(), messages.traite(),
				messages.arrete(), messages.traiter(), messages.provision(), "Total Final".replaceAll(" ", "<br>"),
				messages.devisRefuse().replaceAll(" ", "<br>"), "Ecart M", "Ecart M-1", "Variation M", "Ecart dernier point",
				"Variation Ecart point / M", "% Avct BA", "Montant BA", "% Avct Réel", "Montant Réel", "Canto", "Badge", "Grue", "Lift", "Benne",
				"Nettoy", "Autres", "Prorata", "Total", "% Prestation / Traité", "Pénalité Facturé", "Prorata appliqué ST", "Prorata marché",
				"Prorata sur RAD", "Marchés restant à traiter (%)", "Marchés restant à traiter ", "Variation dû aux transferts",
				"Manque à gagner ST sans prorata" };
		String lotNameCol = combineProps(FicheStDto.LOT, LotDto.NAME);
		// String societeNameCol = combineProps(FicheStDto.SOCIETE,
		// SocieteDto.NOM); // It maybe used in near future
		String societeNameCol = FicheStDto.SOCIETE;
		String[] ids = { FicheStDto.ID, lotNameCol, FicheStDto.OBJECTIF, societeNameCol, FicheStDto.OBJ, FicheStDto.TRANSFERTS, FicheStDto.RD,
				FicheStDto.TS, FicheStDto.TOTAL, FicheStDto.TRAITE, FicheStDto.ARRETE, FicheStDto.NON_ARRETE, FicheStDto.PROVISION,
				FicheStDto.FINAL_TOTAL, FicheStDto.DEVISREFUSE, FicheStDto.ECART_M, FicheStDto.ECARTM1, FicheStDto.VARIATION_M,
				FicheStDto.ECART_DERNIER_POINT, FicheStDto.VARIATION_ECART_POINT_M, FicheStDto.AVCT_BA_PERCENTAGE, FicheStDto.TOTAL_CUMULE,
				FicheStDto.AVCT_REEL_PERCENTAGE, FicheStDto.MONTANT_REEL, FicheStDto.TOTAL_CANTO, FicheStDto.TOTAL_BADGE, FicheStDto.TOTAL_GRUE,
				FicheStDto.TOTAL_LIFT, FicheStDto.TOTAL_BENNE, FicheStDto.TOTAL_NETTOY, FicheStDto.TOTAL_AUTRES, FicheStDto.TOTAL_PRORATA,
				FicheStDto.TOTAL2, FicheStDto.PRESTATION_TRAITE_PERCENTAGE, FicheStDto.TOTAL_PENALTY, FicheStDto.PRORATA_APPLIQUE_ST,
				FicheStDto.PRORATA_MARCHE, FicheStDto.PRORATA_SUR_RAD, FicheStDto.MARCHES_RESTANT_A_TRAITER_PERCENTAGE,
				FicheStDto.MARCHES_RESTANT_A_TRAITER, FicheStDto.VARIATION_DU_AUX_TRANSFERTS, FicheStDto.MANQUE_AGAGNERST_SANS_PRORATA };
		int commonWidth1 = 120;
		int commonWidth2 = 80;
		int[] columnsWidth = { 90, commonWidth1, commonWidth2, commonWidth1, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2,
				commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2,
				commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2,
				commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth2, commonWidth1, commonWidth1, commonWidth1,
				commonWidth1, commonWidth1, commonWidth1 + 50, commonWidth1 + 30, commonWidth1 + 30, commonWidth1 + 70 };

		List<String> nonNumberColumns = Arrays.asList(FicheStDto.ID, lotNameCol, societeNameCol, FicheStDto.AVCT_BA_PERCENTAGE,
				FicheStDto.MARCHES_RESTANT_A_TRAITER_PERCENTAGE, FicheStDto.AVCT_REEL_PERCENTAGE);
		List<String> inputedColumns = Arrays.asList(FicheStDto.OBJECTIF, FicheStDto.ECARTM1, FicheStDto.ECART_DERNIER_POINT);

		AggregationRowConfig<GestionDto> totalSummary = new AggregationRowConfig<GestionDto>();
		totalSummary.setHtml(FicheStDto.ID, "");
		totalSummary.setCellStyle(FicheStDto.ID, "cellStyleAggRow1");
		totalSummary.setHtml(lotNameCol, label.toUpperCase());
		totalSummary.setCellStyle(lotNameCol, "cellStyleAggRow1");
		totalSummary.setHtml(societeNameCol, "");
		totalSummary.setCellStyle(societeNameCol, "cellStyleAggRow");

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		for (int i = 0; i < ids.length; i++) {
			String columnId = ids[i];
			SummaryColumnConfig column = new SummaryColumnConfig(columnId, headers[i], columnsWidth[i]);
			column.setMenuDisabled(true);
			column.setToolTip(headers[i]);
			column.setHeader("<center>" + headers[i] + "</center>");
			column.setNumberFormat(NUMBER_FORMAT);
			if (i == 0) {
				column.setAlignment(HorizontalAlignment.CENTER);
			} else if (FicheStDto.SOCIETE.equals(columnId) || lotNameCol.equals(columnId)) {
				column.setAlignment(HorizontalAlignment.CENTER);
			} else {
				column.setAlignment(HorizontalAlignment.RIGHT);
			}

			if (!nonNumberColumns.contains(columnId)) {

				// Summary columns
				column.setSummaryType(SummaryType.SUM);
				column.setSummaryFormat(NUMBER_FORMAT);

				// Total summary columns
				totalSummary.setSummaryType(columnId, SummaryType.SUM);
				totalSummary.setSummaryFormat(columnId, NUMBER_FORMAT);

				if (inputedColumns.contains(columnId)) {
					GridCellRenderer<AbstractDto> numberRenderer = createNumberRendererWithPermission(role, user);
					column.setRenderer(numberRenderer);
					column.setEditor(new CellEditor(createLockNumberFieldWithPermission(null, role, user)));
				}
			}

			if (!lotNameCol.equalsIgnoreCase(columnId) && !societeNameCol.equalsIgnoreCase(columnId) && !FicheStDto.ID.equalsIgnoreCase(columnId)) {
				totalSummary.setCellStyle(columnId, "cellStyleAggRow");
			}
			columns.add(column);
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
		store.setStoreSorter(new StoreSorter<FicheStDto>() {
			@Override
			public int compare(Store<FicheStDto> store, FicheStDto m1, FicheStDto m2, String property) {
				return m1.getLotType().compareTo(m2.getLotType());
			}
		});

		GroupSummaryView view = new GroupSummaryView();
		view.setShowGroupedColumn(false);

		final CustomEditorGrid<FicheStDto> grid = new CustomEditorGrid<FicheStDto>(store, cm);
		grid.setAutoHeight(true);
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
				if (isAdminOrContributor(role, user)) {
					be.setCancelled(false);
				} else {
					be.setCancelled(true);
				}
			}
		});

		grid.addListener(Events.AfterEdit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				isEdit = true;
				bus.fireEvent(new ModificationEvent(isEdit));
			}
		});

		return grid;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createFicheStSummaryGrid() {
		String[] headers = { "", "Objectif", "Obj", "+/-", "RD", "TS", messages.total(), messages.traite(), messages.arrete(), messages.traiter(),
				messages.provision(), "Total Final".replaceAll(" ", "<br>"), messages.devisRefuse().replaceAll(" ", "<br>"), "Ecart M", "Ecart M-1",
				"Variation M", "Ecart dernier point", "Variation Ecart point / M", "% Avct BA", "Montant BA", "% Avct Réel", "Montant Réel", "Canto",
				"Badge", "Grue", "Lift", "Benne", "Nettoyage", "Autres", "Prorata", "Total", "% Prestation / Traité", "Pénalité Facturé",
				"Prorata appliqué ST", "Prorata marché", "Prorata sur RAD", "Marchés restant à traiter", "Variation dû aux transferts",
				"Manque à gagner ST sans prorata" };
		String[] ids = { "temp01", FicheStDto.OBJECTIF, FicheStDto.OBJ, FicheStDto.TRANSFERTS, FicheStDto.RD, FicheStDto.TS, FicheStDto.TOTAL,
				FicheStDto.TRAITE, FicheStDto.ARRETE, FicheStDto.NON_ARRETE, FicheStDto.PROVISION, FicheStDto.FINAL_TOTAL, FicheStDto.DEVISREFUSE,
				FicheStDto.ECART_M, FicheStDto.ECARTM1, FicheStDto.VARIATION_M, FicheStDto.ECART_DERNIER_POINT, FicheStDto.VARIATION_ECART_POINT_M,
				FicheStDto.AVCT_BA_PERCENTAGE, FicheStDto.TOTAL_CUMULE, FicheStDto.AVCT_REEL_PERCENTAGE, FicheStDto.MONTANT_REEL,
				FicheStDto.TOTAL_CANTO, FicheStDto.TOTAL_BADGE, FicheStDto.TOTAL_GRUE, FicheStDto.TOTAL_LIFT, FicheStDto.TOTAL_BENNE,
				FicheStDto.TOTAL_NETTOY, FicheStDto.TOTAL_AUTRES, FicheStDto.TOTAL_PRORATA, FicheStDto.TOTAL2,
				FicheStDto.PRESTATION_TRAITE_PERCENTAGE, FicheStDto.TOTAL_PENALTY, FicheStDto.PRORATA_APPLIQUE_ST, FicheStDto.PRORATA_MARCHE,
				FicheStDto.PRORATA_SUR_RAD, FicheStDto.MARCHES_RESTANT_A_TRAITER, FicheStDto.VARIATION_DU_AUX_TRANSFERTS,
				FicheStDto.MANQUE_AGAGNERST_SANS_PRORATA };
		int commonWidth = 75;
		int commonWidth2 = 110;
		int[] columnsWidth = { 150, commonWidth2, commonWidth, commonWidth2, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth,
				commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth,
				commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth,
				commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth, commonWidth,
				commonWidth, commonWidth2 };
		HorizontalAlignment[] horizontalAlignments = { HorizontalAlignment.CENTER };

		List<String> nonNumberColumns = Arrays.asList(FicheStDto.ID);

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		for (int i = 0; i < ids.length; i++) {
			String columnId = ids[i];
			SummaryColumnConfig column = new SummaryColumnConfig(columnId, headers[i], columnsWidth[i]);
			column.setAlignment(horizontalAlignments[0]);
			column.setMenuDisabled(true);
			column.setToolTip(headers[i]);
			// column.setHeader("<center>" + headers[i] + "</center>");
			if (i == 0) {
				column.setAlignment(HorizontalAlignment.CENTER);
			} else {
				column.setAlignment(HorizontalAlignment.RIGHT);
			}

			if (!nonNumberColumns.contains(columnId)) {
				column.setNumberFormat(INTEGER_FORMAT);

				// Summary columns
				column.setSummaryType(SummaryType.SUM);
				column.setSummaryFormat(CURRENCY_FORMAT);
			}
			columns.add(column);
		}

		ColumnModel cm = new ColumnModel(columns);
		cm.getColumn(0).setRenderer(new GridCellRenderer<FicheStDto>() {
			@Override
			public Object render(FicheStDto model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<FicheStDto> store,
					Grid<FicheStDto> grid) {
				config.style = "background-color: " + HEADER_BACKGROUND_COLOR + ";width:147px";
				return "<center>" + model.get(property) + "</center>";
			}

		});

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
		ficheStSummaryGrid.setHeight(110);
		// ficheStSummaryGrid.getView().setForceFit(true);
		// ficheStSummaryGrid.setAutoExpandColumn(FicheStDto.MANQUE_AGAGNERST_SANS_PRORATA);
		calculateValues();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createTransfertPpGrid(final RoleModel role, final UtilisateurGrpModel user, final ChantierModel chantier) {
		int commonWidth = 105;
		String[] headers = { "", messages.label(), "Objectif", "Obj", "+/-", "RD", "TS", messages.total() };
		String[] ids = { TransfertPpSummaryDto.ID, TransfertPpSummaryDto.LABEL, TransfertPpSummaryDto.OBJECTIVE, TransfertPpSummaryDto.OBJ,
				TransfertPpSummaryDto.DEVERS, TransfertPpSummaryDto.RD, TransfertPpSummaryDto.TS, TransfertPpSummaryDto.TOTAL };
		int[] columnsWidth = { 80, 150, 150, commonWidth, commonWidth, commonWidth, commonWidth, 240 };

		AggregationRowConfig<TransfertPpSummaryDto> totalSummary = new AggregationRowConfig<TransfertPpSummaryDto>();
		totalSummary.setHtml(TransfertPpSummaryDto.LABEL, "Total PP".toUpperCase());

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		for (int i = 0; i < ids.length; i++) {
			String columnId = ids[i];
			SummaryColumnConfig column = new SummaryColumnConfig(columnId, headers[i], columnsWidth[i]);
			column.setMenuDisabled(true);
			column.setToolTip(headers[i]);
			column.setHeader("<center>" + headers[i] + "</center>");
			columns.add(column);
			if (i < 2) {
				column.setAlignment(HorizontalAlignment.CENTER);
			} else {
				column.setAlignment(HorizontalAlignment.RIGHT);
			}
		}

		ColumnModel cm = new ColumnModel(columns);
		transfertPpGrid = new CustomEditorGrid(new ListStore<TransfertPpSummaryDto>(), cm);

		List<String> summaryColumns = Arrays.asList(TransfertPpSummaryDto.OBJECTIVE, TransfertPpSummaryDto.OBJ, TransfertPpSummaryDto.DEVERS,
				TransfertPpSummaryDto.RD, TransfertPpSummaryDto.TS, TransfertPpSummaryDto.TOTAL);

		for (String columnId : summaryColumns) {
			ColumnConfig column = cm.getColumnById(columnId);
			column.setNumberFormat(NUMBER_FORMAT);
			totalSummary.setCellStyle(columnId, "cellStyleAggRow");
			totalSummary.setSummaryType(columnId, SummaryType.SUM);
			totalSummary.setSummaryFormat(columnId, NumberFormat.getFormat(Constants.NUMBER_PATTERN));
		}
		cm.addAggregationRow(totalSummary);

		cm.getColumnById(TransfertPpSummaryDto.ID).setRenderer(new GridCellRenderer<TransfertPpSummaryDto>() {
			@Override
			public Object render(final TransfertPpSummaryDto model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config,
					int rowIndex, int colIndex, ListStore<TransfertPpSummaryDto> store, Grid<TransfertPpSummaryDto> grid) {

				Image viewButton = createViewButton();
				viewButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						navigation.getContext().setCurrentChantier(chantier);
						navigation.getContext().setRoleModel(role);
						navigation.getContext().setTransfertppId(model.getRefTransfertPpId());
						CookieUtil.setCurrentTransfertpp(model.getRefTransfertPpId().toString());
						History.newItem(model.getRefTransfertPpId().toString() + "&transfertpp");
						GuiUtil.gotoEcran(new TransfertppEcran());
					}
				});
				return viewButton;
			}
		});

		ColumnConfig objectiveColumn = cm.getColumnById(TransfertPpSummaryDto.OBJECTIVE);
		objectiveColumn.setRenderer(createNumberRendererWithPermission(role, user));
		objectiveColumn.setEditor(new CellEditor(createLockNumberFieldWithPermission(null, role, user)));
		transfertPpGrid.setAutoHeight(true);
		transfertPpGrid.setAutoExpandColumn(TransfertPpSummaryDto.TOTAL);
		transfertPpGrid.setAutoExpandMin(195);
		transfertPpGrid.setAutoExpandMax(1000);
		// transfertPpGrid.getView().setForceFit(true);
		transfertPpGrid.getStore().addStoreListener(new StoreListener<TransfertPpSummaryDto>() {
			@Override
			public void handleEvent(StoreEvent<TransfertPpSummaryDto> e) {
				calculateValues();
				// transfertPpGrid.getView().refresh(true);
			}
		});
		loadTransfertPpData();
	}

	protected boolean isValid() {
		return (formPanel != null && formPanel.isValid());
	}

	private void loadFicheStData() {
		showLoading(ficheStGrid1);
		showLoading(ficheStGrid2);

		clientLotTypeService.findAll(new AsyncCallbackWithErrorResolution<List<LotTypeDto>>() {
			@Override
			public void onSuccess(final List<LotTypeDto> allLotTypes) {
				if (allLotTypes == null) {
					return;
				}

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
						String prestationValue = "";
						for (FicheStDto ficheStDto : results) {

							// Note: Do not use append() here
							prestationValue = ficheStDto.getPrestaCanto() + Constants.SEPRATE + ficheStDto.getPrestaBadge() + Constants.SEPRATE
									+ ficheStDto.getPrestaGrue() + Constants.SEPRATE + ficheStDto.getPrestaLift() + Constants.SEPRATE
									+ ficheStDto.getPrestaBenne() + Constants.SEPRATE + ficheStDto.getPrestaNettoyage() + Constants.SEPRATE
									+ ficheStDto.getPrestaProrata();
							mapPrestations.put(ficheStDto.getId(), prestationValue);

							LotTypeDto lotType = ficheStDto.getLotType();
							if (lotType != null && HONORAIRES_LOT_TYPE_NAME.equalsIgnoreCase(lotType.getName())) {
								list2.add(ficheStDto);
							} else {
								list1.add(ficheStDto);
							}
						}

						navigation.getContext().setMapPrestations(mapPrestations);

						// Sort the FicheSt list1 by LotType
						Collections.sort(list1, new Comparator<FicheStDto>() {
							@Override
							public int compare(FicheStDto o1, FicheStDto o2) {
								return o1.getLotType().compareTo(o2.getLotType());
							}
						});

						addData(ficheStGrid1, list1);
						if (list2 != null && list2.size() > 0) {
							ficheStGrid2.show();
							addData(ficheStGrid2, list2);
						}

						List<FicheStDto> allFicheStDto = new ArrayList<FicheStDto>();

						allFicheStDto.addAll(list1);
						allFicheStDto.addAll(list2);
					}

					private void addData(EditorGrid<FicheStDto> ficheStGrid, List<FicheStDto> results) {
						if (ficheStGrid != null) {
							ficheStGrid.getStore().removeAll();
							ficheStGrid.getStore().add(results);
							ficheStGrid.getView().refresh(false);
							ficheStGrid.unmask();
						}
					}
				});
			}

		});
	}

	private void loadTransfertPpData() {
		showLoading(transfertPpGrid);

		ClientRefTransfertppServiceAsync clientRefTransfertppService = ClientRefTransfertppServiceAsync.Util.getInstance();
		clientRefTransfertppService.findAll(new AsyncCallbackWithErrorResolution<List<RefTransfertPPDto>>() {
			@Override
			public void onFailure(Throwable caught) {
				transfertPpGrid.unmask();
				super.onFailure(caught);
			}

			@Override
			public void onSuccess(List<RefTransfertPPDto> refTransfertPpList) {
				transfertPpGrid.unmask();

				final List<FicheTransfertppDto> ficheTransfertPpList2 = new ArrayList<FicheTransfertppDto>();
				final Map<FicheTransfertppDto, List<LigTransfertppModel>> map = new HashMap<FicheTransfertppDto, List<LigTransfertppModel>>();
				for (RefTransfertPPDto refTransfertPp : refTransfertPpList) {
					FicheTransfertppDto ficheTransfertPp = new FicheTransfertppDto();
					ficheTransfertPp.setRefTransfertPp(refTransfertPp);
					ficheTransfertPp.setChantier(chantier);

					map.put(ficheTransfertPp, new ArrayList<LigTransfertppModel>());
					ficheTransfertPpList2.add(ficheTransfertPp);
				}
				ClientFicheTransfertppServiceAsync clientFicheTransfertppService = ClientFicheTransfertppServiceAsync.Util.getInstance();
				clientFicheTransfertppService.findByChantierId(chantier.getId(), new AsyncCallbackWithErrorResolution<List<FicheTransfertppDto>>() {
					@Override
					public void onFailure(Throwable caught) {
						transfertPpGrid.unmask();
						super.onFailure(caught);
					}

					@Override
					public void onSuccess(List<FicheTransfertppDto> ligTransfertPpList) {
						for (FicheTransfertppDto ficheTransfertppDto : ligTransfertPpList) {
							map.remove(ficheTransfertppDto);
							map.put(ficheTransfertppDto, ficheTransfertppDto.getLigTransfertPps());
						}
						// for (int i = 0; i < 13; i++) {
						// if (ligTransfertPpList.get(i) == null) {
						// ficheTransfertPpList2.get(i).setObjectif(0.0);
						// } else {
						// ficheTransfertPpList2.get(i).setObjectif(ligTransfertPpList.get(i).getObjectif());
						// }
						//
						// }
						for (int i = 0; i < ligTransfertPpList.size(); i++) {
							for (FicheTransfertppDto ficheTransfertppDto : ficheTransfertPpList2) {
								if (ligTransfertPpList.get(i).getRefTransfertPp().getId().equals(ficheTransfertppDto.getRefTransfertPp().getId())) {
									ficheTransfertppDto.setObjectif(ligTransfertPpList.get(i).getObjectif());
								}

							}
						}
						List<TransfertPpSummaryDto> transfertPpSummaryList = new ArrayList<TransfertPpSummaryDto>();
						for (FicheTransfertppDto ficheTransfertpp : ficheTransfertPpList2) {
							TransfertPpSummaryDto transfertPpSummary = new TransfertPpSummaryDto(ficheTransfertpp.getChantier().getId(),
									ficheTransfertpp.getRefTransfertPp().getId(), ficheTransfertpp.getRefTransfertPp().getLabel());
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

	private static void showFicheStReport(SimpleEventBus bus, List<FicheStDto> listFicheSt, boolean hasGestion, String title) {
		FicheSTSelectionPopup popUpReport = new FicheSTSelectionPopup(bus, listFicheSt, hasGestion, title);
		popUpReport.setModal(true);
		popUpReport.setBlinkModal(true);
		popUpReport.show();
	}

	public void disableSaveButtons() {
		saveLayout.setEnabled(false);
		save.removeStyleName(ACTION_HTML);
		save.addStyleName(ACTION_HTML4);
		saveLayout2.setEnabled(false);
		save2.removeStyleName(ACTION_HTML);
		save2.setStyleName(ACTION_HTML4);
	}

	public void enableSaveButtons() {
		saveLayout.setEnabled(true);
		save.setStyleName(ACTION_HTML);

		saveLayout2.setEnabled(true);
		save2.setStyleName(ACTION_HTML);
	}

	public List<FicheStDto> addListFichest() {
		List<FicheStDto> listFicheSt1 = ficheStGrid1.getStore().getModels();
		List<FicheStDto> listFicheSt2 = ficheStGrid2.getStore().getModels();
		List<FicheStDto> allModels = new ArrayList<FicheStDto>();
		for (FicheStDto ficheStDto1 : listFicheSt1) {
			if (ficheStDto1.getId() != null) {
				allModels.add(ficheStDto1);
			}
		}
		for (FicheStDto ficheStDto2 : listFicheSt2) {
			if (ficheStDto2.getId() != null) {
				allModels.add(ficheStDto2);
			}
		}
		return allModels;
	}

}
