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
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.service.ClientLigTransfertppService;
import com.structis.fichesst.client.service.ClientLigTransfertppServiceAsync;
import com.structis.fichesst.client.service.ClientRefTransfertppService;
import com.structis.fichesst.client.service.ClientRefTransfertppServiceAsync;
import com.structis.fichesst.client.service.ClientRefTypeBudjService;
import com.structis.fichesst.client.service.ClientRefTypeBudjServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.client.widget.CustomEditorGrid;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.shared.dto.AbstractDto;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.LigTransfertppModel;
import com.structis.fichesst.shared.dto.RefTransfertPPDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.SimpleDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class TransfertPpPanel extends AbstractPanel {

	private final HTML addItem;

	@SuppressWarnings("rawtypes")
	private SimpleComboBox combo;

	private SimpleComboBox<String> comboDeVers;

	private final FlexTable flexTable;

	private final FormPanel formDetails;

	private final FieldSet fsTransfertPp;

	private EditorGrid<LigTransfertppModel> gridligModel;

	private Grid<LigTransfertppModel> gridligTotal;

	private final FieldSet inforFieldset;

	private boolean isSave = false;

	private final Label labelChantier;

	private final Label labelRefTransfertpp;

	private List<LigTransfertppModel> ligModels = new ArrayList<LigTransfertppModel>();

	private final List<LigTransfertppModel> ligModelsBefore = new ArrayList<LigTransfertppModel>();

	private final List<SimpleDto> listBudj = new ArrayList<SimpleDto>();

	private final HTML saveButton;

	private final ClientRefTypeBudjServiceAsync service = GWT.create(ClientRefTypeBudjService.class);

	private final ClientLigTransfertppServiceAsync serviceLig = GWT.create(ClientLigTransfertppService.class);

	private final ClientRefTransfertppServiceAsync serviceRefTransfert = GWT.create(ClientRefTransfertppService.class);

	private ListStore<SimpleDto> storeBudj;

	private GroupingStore<LigTransfertppModel> storeLig;

	private ListStore<LigTransfertppModel> storeTotal;

	private TextField<String> textLot1;

	private TextField<String> textLot2;

	public TransfertPpPanel(final ChantierModel chantier, final Integer transfertPpId, final RoleModel role, final UtilisateurGrpModel user) {
		loadBudj();
		loadLig(chantier, transfertPpId, role, user);
		createDetailsTransferPp(role, user);
		createTotalPP();
		setWidth(GuiUtil.getScreenWidth() - 30);
		inforFieldset = new FieldSet();
		inforFieldset.setHeading("INFORMATION GENERALES");
		inforFieldset.setWidth(GuiUtil.getScreenWidth() - 50);
		LayoutContainer layoutContainer_0 = new LayoutContainer();
		FormLayout fl_layoutContainer_0 = new FormLayout();
		fl_layoutContainer_0.setLabelWidth(70);
		layoutContainer_0.setLayout(new TableLayout(2));
		TableData col1 = new TableData();
		col1.setWidth("600px");
		TableData col2 = new TableData();
		col2.setWidth("600px");
		labelChantier = new Label();
		labelChantier.setText("Chantier: " + chantier.getNom());
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
		fsTransfertPp.setWidth(GuiUtil.getScreenWidth() - 50);
		fsTransfertPp.setHeading("TRANSFERTS PP");
		fsTransfertPp.setLayout(new RowLayout(Orientation.VERTICAL));
		LayoutContainer layoutContainer_10 = new LayoutContainer();
		layoutContainer_10.setLayout(new BorderLayout());
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
								saveAction(chantier.getId(), transfertPpId, chantier, false);
								isSave = true;
							}
						}
					}
				};
				createConfirmBox(callback, messages.deleteConfirmTitle(), messages.confirmSupprimer()); // confirm saving
			}
		});

		layoutContainer_10.add(saveButton, new BorderLayoutData(LayoutRegion.EAST, 320.0f));
		layoutContainer_10.setHeight(30);
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
		addItem = new HTML("<img src='./images/ajouter.png'/> " + messages.addRow(), false);
		addItem.setStyleName("actionHTML2");
		addItem.setVisible(false);
		addItem.addClickHandler(new ClickHandler() {
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
						gridligModel.getView().refresh(true);
						gridligModel.getStore().add(model);

						isSave = false;
					}
				});
				dialog.show();
			}
		});

		layoutContainer_11.add(gridligModel);
		flexTable = new FlexTable();
		flexTable.setWidget(0, 0, addItem);
		fsTransfertPp.add(flexTable);
		formDetails.setButtonAlign(HorizontalAlignment.CENTER);
		formDetails.add(fsTransfertPp);
		setDefaultBackgroundColor(fsTransfertPp);
		setDefaultBackgroundColor(layoutContainer_10);
		add(formDetails);

		FormPanel buttonPanel2 = new CustomFormPanel();
		buttonPanel2.setButtonAlign(HorizontalAlignment.CENTER);
		if (isAdminOrContributor(role, user)) {
			Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
				@Override
				public void handleEvent(MessageBoxEvent be) {
					Button btt = be.getButtonClicked();
					if (Dialog.OK.equals(btt.getItemId())) {
						History.newItem(chantier.getId().toString());
						Window.Location.reload();
					}
				}
			};
			buttonPanel2.addButton(createCancelButton(callback));

			buttonPanel2.addButton(new Button(messages.saveChanges(), new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					if (isSave == false) {
						saveAction(chantier.getId(), transfertPpId, chantier, true);
						isSave = true;
					} else {
						Window.Location.reload();
						History.newItem(chantier.getId().toString());
					}

				}
			}));
		} else {
			buttonPanel2.addButton(new Button("Retour", new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					Window.Location.reload();
					History.newItem(chantier.getId().toString());
				}
			}));
		}

		add(buttonPanel2);
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
		combo.setValueField(AbstractDto.ID);
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
		GridCellRenderer<AbstractDto> deleteBtn = null;
		if (isAdminOrContributor(role, user)) {
			deleteBtn = createDeleteButtonRenderer();
		}
		ColumnConfig column = new ColumnConfig();
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
			public Object render(AbstractDto model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<AbstractDto> store,
					Grid<AbstractDto> grid) {
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
		column.setWidth(205);
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
		column.setWidth(205);
		configs.add(column);

		SummaryColumnConfig<Double> montant = new SummaryColumnConfig<Double>("montant", "Montant", 120);
		montant.setHeader("<center>" + messages.montant() + "</center>");
		montant.setAlignment(HorizontalAlignment.RIGHT);
		montant.setMenuDisabled(true);
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
			public String render(LigTransfertppModel model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<LigTransfertppModel> store, Grid<LigTransfertppModel> grid) {
				LigTransfertppModel lig = model;
				int temp;
				if (lig.getDeVers().equalsIgnoreCase("vers")) {
					temp = -1;
				} else {
					temp = 1;
				}

				return NUMBER_FORMAT.format(lig.getPu() * lig.getQuantite() * temp);
			}
		});
		configs.add(montant);

		SummaryColumnConfig<Integer> quantity = new SummaryColumnConfig<Integer>(LigTransfertppModel.QUANTITY, "Quantite", 120);
		quantity.setHeader("<center>" + messages.quantity() + "</center>");
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
		quantity.setAlignment(HorizontalAlignment.RIGHT);
		quantity.setSortable(false);
		quantity.setEditor(ce1);
		quantity.setSummaryType(SummaryType.COUNT);
		quantity.setNumberFormat(INTEGER_FORMAT);
		configs.add(quantity);

		SummaryColumnConfig<Double> pu = new SummaryColumnConfig<Double>(LigTransfertppModel.PU, "Pu", 100);
		pu.setHeader("<center>Pu</center>");
		pu.setSortable(false);
		pu.setMenuDisabled(true);
		pu.setNumberFormat(NumberFormat.getCurrencyFormat());

		NumberField nf = createNumberField(null);
		nf.setAutoValidate(true);
		CellEditor ce = new CellEditor(nf);
		ce.setCancelOnEsc(true);
		pu.setEditor(ce);
		pu.setAlignment(HorizontalAlignment.RIGHT);
		configs.add(pu);
		column = new ColumnConfig(LigTransfertppModel.COMMENTAIRES, 150);
		column.setHeader("Commentaire");
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setFixed(true);
		column.setSortable(false);
		column.setMenuDisabled(true);
		column.setResizable(false);

		column.setEditor(createTextAreaEditor());
		column.setRenderer(createTextAreaRenderer(column.getWidth() - PADDING, 33));
		configs.add(column);
		ColumnModel cm = new ColumnModel(configs);
		storeLig = new GroupingStore<LigTransfertppModel>();
		gridligModel = new CustomEditorGrid<LigTransfertppModel>(storeLig, cm);
		gridligModel.setHeight(230);
		gridligModel.setAutoWidth(true);
		gridligModel.setAutoExpandMax(700);
		gridligModel.setAutoExpandMin(150);
		gridligModel.getView().setForceFit(true);
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
					int ratio;
					if ("vers".equalsIgnoreCase(lig.getDeVers())) {
						ratio = -1;
					} else {
						ratio = 1;
					}

					Integer typeBudjId = lig.getRefTypeBudjConf().getId();
					if (typeBudjId == 1) {
						if (ratio == -1) {
							totalObjNegative += lig.getPu() * lig.getQuantite() * (-1);
						} else {
							totalObjPositive += lig.getPu() * lig.getQuantite();
						}
					}

					else if (typeBudjId == 2) {
						if (ratio == -1) {
							totalDeversNegative += lig.getPu() * lig.getQuantite() * (-1);
						} else {
							totalDeversPositive += lig.getPu() * lig.getQuantite();
						}
					} else if (typeBudjId == 3) {
						if (ratio == -1) {
							totalRDNegative += lig.getPu() * lig.getQuantite() * (-1);
						} else {
							totalRDPositive += lig.getPu() * lig.getQuantite();
						}
					} else if (typeBudjId == 4) {
						if (ratio == -1) {
							totalTsNegative += lig.getPu() * lig.getQuantite() * (-1);
						} else {
							totalTsPositive += lig.getPu() * lig.getQuantite();
						}
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
				storeTotal.add(ligModel);
			}
		});
	}

	public void createTotalPP() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig column = createColumnGrid();
		column.setId(LigTransfertppModel.TotalObj);
		column.setHeader("<center>Obj<br/>(Objectif)</center>");
		configs.add(column);

		column = createColumnGrid();
		column.setId(LigTransfertppModel.TotalDeVers);
		column.setHeader("<center>+/-<br/>(Transferts)</center>");
		configs.add(column);

		column = createColumnGrid();
		column.setId(LigTransfertppModel.TotalRD);
		column.setHeader("<center>RD<br/>(Recente Decenale)</center>");
		configs.add(column);

		column = createColumnGrid();
		column.setId(LigTransfertppModel.TotalTS);
		column.setHeader("<center>TS<br/>(Tri supp)</center>");
		configs.add(column);

		storeTotal = new ListStore<LigTransfertppModel>();
		ColumnModel cm = new ColumnModel(configs);
		gridligTotal = new Grid<LigTransfertppModel>(storeTotal, cm);
		gridligTotal.setAutoHeight(true);
		gridligTotal.setWidth(610);
		gridligTotal.setColumnLines(true);
		gridligTotal.setAutoExpandColumn(LigTransfertppModel.TotalTS);
	}

	public ColumnConfig createColumnGrid() {
		ColumnConfig column = new ColumnConfig();

		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setFixed(true);
		column.setFixed(true);
		column.setSortable(false);
		column.setMenuDisabled(true);
		column.setResizable(false);
		column.setWidth(150);
		column.setNumberFormat(CURRENCY_FORMAT);
		return column;
	}

	public void loadBudj() {
		service.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
			@Override
			public void onSuccess(List<SimpleDto> arg0) {
				storeBudj.add(arg0);
				listBudj.addAll(arg0);
			}
		});
	}

	public void loadLig(ChantierModel chantier, Integer transfertPpId, final RoleModel role, final UtilisateurGrpModel user) {
		serviceRefTransfert.findById(transfertPpId, new AsyncCallbackWithErrorResolution<RefTransfertPPDto>() {
			@Override
			public void onSuccess(RefTransfertPPDto arg0) {
				labelRefTransfertpp.setText(messages.label() + ": " + arg0.getLabel());
				labelRefTransfertpp.setVisible(true);
			}
		});
		serviceLig.findByID(transfertPpId, chantier.getId(), new AsyncCallbackWithErrorResolution<List<LigTransfertppModel>>() {
			@Override
			public void onSuccess(List<LigTransfertppModel> arg0) {
				storeLig.add(arg0);
				ligModelsBefore.addAll(arg0);
				if (isAdminOrContributor(role, user)) {
					gridligModel.setEnabled(true);
					fsTransfertPp.setEnabled(true);
					addItem.setVisible(true);
				} else {
					gridligModel.removeAllListeners();
					saveButton.setVisible(false);
				}
			}
		});
	}

	public void onLoadPanel() {
	}

	private void saveAction(Integer idChantier, Integer transfertPpId, final ChantierModel chantier, final boolean gotoNextPage) {
		ligModels = gridligModel.getStore().getModels();
		serviceLig.saveAllLig(ligModels, idChantier, transfertPpId, new AsyncCallbackWithErrorResolution<Void>() {
			@Override
			public void onSuccess(Void arg0) {
				commitDataChange();
				GuiUtil.showSuccessInfo();
				if (gotoNextPage) {
					Window.Location.reload();
					History.newItem(chantier.getId().toString());
				}
			}
		});
		if (ligModelsBefore.size() > 0) {
			serviceLig.deleteLig(ligModelsBefore, idChantier, transfertPpId, new AsyncCallbackWithErrorResolution<Void>() {
				@Override
				public void onSuccess(Void arg0) {
				}
			});
		}
	}

	@Override
	protected void commitDataChange() {
		commitDataGrids(gridligModel);
	}
}
