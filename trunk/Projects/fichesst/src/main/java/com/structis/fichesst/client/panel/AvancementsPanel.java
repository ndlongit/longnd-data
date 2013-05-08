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
import com.structis.fichesst.client.event.DeductionGridUpdateEvent;
import com.structis.fichesst.client.event.EtatAvancementEvent;
import com.structis.fichesst.client.event.ExportAvancementPanelEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.client.handler.DeductionGridUpdateHandler;
import com.structis.fichesst.client.handler.EtatAvancementHandler;
import com.structis.fichesst.client.handler.ExportAvancementPanelHandler;
import com.structis.fichesst.client.util.NameValuePair;
import com.structis.fichesst.client.util.ReportUtil;
import com.structis.fichesst.client.widget.CustomEditorGrid;
import com.structis.fichesst.client.widget.CustomFieldSet;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.shared.dto.AnonymousDto;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.LotTypeDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.ProgressDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.util.Constants;

public class AvancementsPanel extends AbstractPanel {

	private static final String[] COLUMNS_WIDTH_3 = { "55%", "2%", "43%" };

	private EditorGrid<ProgressDto> progressGrid;
	
	private EditorGrid<AnonymousDto> penaltyGrid;

	private EditorGrid<AnonymousDto> grid_3;
	
	//Total Situation ,report
	private String cumule;
	private String mois = "";
	private String detail_des_retenues_appliques;
	private String grid_3_report;
	private TextField<String> txtdetail_des_retenues_appliques;
	private TextField<String> txtgrid_3_report;
	private TextField<String> txt_advancement_mois;
	private TextField<String> txttotalsituation;
	private TextField<String> txtEtatAvancement;
	private FormPanel formPanel = new CustomFormPanel();
	//End
	
	private List<GestionDto> listGestion;
	
	private HTML saveButton;
	private HTML addRow;
	private TextArea comment = new TextArea();

	private HTML progressLabel;
	
	private RoleModel role;
	private UtilisateurGrpModel user;
	
	private double etatAvancement;
	
	public AvancementsPanel(SimpleEventBus b,RoleModel roleModel,UtilisateurGrpModel utilisateurGrpModel) {
		super();
		this.bus = b;
		this.role = roleModel;
		this.user = utilisateurGrpModel;
		FieldSet avancementsFieldSet = new CustomFieldSet();
		LayoutContainer layoutContainer_1 = new LayoutContainer();
		layoutContainer_1.setBorders(false);
		TableLayout layout3 = new TableLayout(3);
		layout3.setWidth("100%");
		layoutContainer_1.setLayout(layout3);
		
		initFieldReport();
		
		progressLabel = new HTML("", false);
		setProgressLabelText(0.0);
		bus.addHandler(EtatAvancementEvent.TYPE, new EtatAvancementHandler() {
			@Override
			public void onGetEtatAvancement(EtatAvancementEvent event) {
				double cumuleSum = 0.0;
				double totalTraite = 0.0;
				if( event.getListGestion() != null && event.getListGestion().size() > 0 ) {
					listGestion = event.getListGestion();
					GestionDto gestionDto = null;
					for( int i = 0 ; i < listGestion.size() ; i++ ) {
						gestionDto = listGestion.get(i);
						totalTraite += gestionDto.getTraite();
					}
				}

				List<ProgressDto> listProcess = null;
				if( event.getListProcess() == null ) {
					listProcess = progressGrid.getStore().getModels();
				}
				else
					listProcess = event.getListProcess();
				if( listProcess != null && listProcess.size() > 0 ) {
					ProgressDto progressDto = null;
					for( int i = 0 ; i < listProcess.size() ; i++ ) {
						progressDto = listProcess.get(i);
						cumuleSum = progressDto.getCumule();
					}
				}

				if( totalTraite == 0 ) {
					etatAvancement = 0.0;
				}
				else {
					etatAvancement = (cumuleSum / totalTraite) * 100;
				}
				setProgressLabelText(etatAvancement);
			}
		});

		TableData td_htmlNewHtml = new TableData();
		td_htmlNewHtml.setHorizontalAlign(HorizontalAlignment.LEFT);
		layoutContainer_1.add(progressLabel, td_htmlNewHtml);

		saveButton = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm(), false);
		saveButton.setStyleName("actionHTML");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new SaveFicheStEvent());
			}
		});
		
		TableData td_saveButton = new TableData();
		td_saveButton.setHorizontalAlign(HorizontalAlignment.RIGHT);
		layoutContainer_1.add(saveButton, td_saveButton);
		
		bus.addHandler(ExportAvancementPanelEvent.TYPE, new ExportAvancementPanelHandler() {
			
			@Override
			public void onExport(ExportAvancementPanelEvent event) {
				exportAvancementsPanel();
			}
		}); 
		
		HTML print2 = new HTML("<img src='./images/imprimer.png'/> " + messages.printPromotions(), false);
		print2.setStyleName("actionHTML");
		print2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				exportAvancementsPanel();
			}
		});
		TableData td_print2 = new TableData();
		td_print2.setWidth("250");
		td_print2.setHorizontalAlign(HorizontalAlignment.RIGHT);
		layoutContainer_1.add(print2, td_print2);

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
				dialog.setWidth(500);
				dialog.setHeight(220);
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
					public void componentSelected(ButtonEvent ce) {
						ProgressDto model = addDataForm.getDataModel();
						progressGrid.getStore().add(model);
						progressGrid.getView().refresh(true);
						//bus.fireEvent(new EtatAvancementEvent(listGestion,progressGrid.getStore().getModels()));
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
		comment.setFieldLabel(messages.comment2());
		formPanel.add(comment, new FormData("100%"));
		rightPanel.add(formPanel);
		layoutContainer_3.add(rightPanel, td_rightPanel);

		bus.addHandler(DeductionGridUpdateEvent.TYPE, new DeductionGridUpdateHandler() {
			@Override
			public void onSave(DeductionGridUpdateEvent event) {
				List<DeductionDto> deductionDtoList = event.getDeductionDtoList();
				int cantoQuantity = 0;
				int badgeQuantity = 0;
				int grueQuantity = 0;
				int liftQuantity = 0;
				int benneQuantity = 0;
				int nettoyageQuantity = 0;
				double autres = 0.0;
				for( DeductionDto deductionDto : deductionDtoList ) {
					cantoQuantity += deductionDto.getCanto();
					badgeQuantity += deductionDto.getBadge();
					grueQuantity += deductionDto.getGrue();
					liftQuantity += deductionDto.getLift();
					benneQuantity += deductionDto.getBenne();
					nettoyageQuantity += deductionDto.getNettoyage();
					autres += deductionDto.getAutres();
				}
				grid_3.getStore().removeAll();

				List<AnonymousDto> models = new ArrayList<AnonymousDto>();
				AnonymousDto model = null;

				String[] column1s = { messages.nombre(), messages.amount() };	
				
				NumberField canto = (NumberField)ComponentManager.get().get("INFORMATIONAL_PANEL_CANTO_ID");
				NumberField badge = (NumberField)ComponentManager.get().get("INFORMATIONAL_PANEL_BADGE_ID");				
				NumberField grue = (NumberField)ComponentManager.get().get("INFORMATIONAL_PANEL_GRUE_ID");
				NumberField lift = (NumberField)ComponentManager.get().get("INFORMATIONAL_PANEL_LIFT_ID");
				NumberField benne = (NumberField)ComponentManager.get().get("INFORMATIONAL_PANEL_BENNE_ID");			
				NumberField netoyage = (NumberField)ComponentManager.get().get("INFORMATIONAL_PANEL_NETOYAGE_ID");
				
				double cantoValue = 0.0;
				double badgeValue = 0.0;
				double grueValue = 0.0;
				double liftValue = 0.0;
				double benneValue = 0.0;
				double netoyageValue = 0.0;
				if(canto!=null && canto.getValue()!=null)
					cantoValue = canto.getValue().doubleValue();
				if(badge!=null && badge.getValue()!=null)
					badgeValue = badge.getValue().doubleValue();
				if(grue!=null && grue.getValue()!=null)
					grueValue = grue.getValue().doubleValue();
				if(lift!=null && lift.getValue()!=null)
					liftValue = lift.getValue().doubleValue();
				if(benne!=null && benne.getValue()!=null)
					benneValue = benne.getValue().doubleValue();
				if(netoyage!=null && netoyage.getValue()!=null)
					netoyageValue = netoyage.getValue().doubleValue();
				double[] column2s = { cantoQuantity, (cantoQuantity * cantoValue) };
				double[] column3s = { badgeQuantity, (badgeQuantity * badgeValue) };
				double[] column4s = { grueQuantity, (grueQuantity * grueValue) };
				double[] column5s = { liftQuantity, (liftQuantity * liftValue) };
				double[] column6s = { benneQuantity, (benneQuantity * benneValue) };
				double[] column7s = { nettoyageQuantity,(nettoyageQuantity * netoyageValue) };
				double[] column8s = { 0, autres };
				grid_3_report = "";
				for( int i = 0 ; i < 2 ; i++ ) {
					model = new AnonymousDto();
					model.set(AnonymousDto.COLUMN_1, column1s[i]);
					model.set(AnonymousDto.COLUMN_2, column2s[i]);
					model.set(AnonymousDto.COLUMN_3, column3s[i]);
					model.set(AnonymousDto.COLUMN_4, column4s[i]);
					model.set(AnonymousDto.COLUMN_5, column5s[i]);
					model.set(AnonymousDto.COLUMN_6, column6s[i]);
					model.set(AnonymousDto.COLUMN_7, column7s[i]);
					model.set(AnonymousDto.COLUMN_8, column8s[i]);
					//For report
					grid_3_report +=  column2s[i] + Constants.SEPRATE + column3s[i] + Constants.SEPRATE + column4s[i] + 
					Constants.SEPRATE + column5s[i] + Constants.SEPRATE + column6s[i] + Constants.SEPRATE + column7s[i] + Constants.SEPRATE + column8s[i] + Constants.SEPRATE; 
					models.add(model);
				}
				//For Report
				if(grid_3_report!=""){
					grid_3_report.substring(0,grid_3_report.length() - Constants.SEPRATE.length());
				}
				
				txtgrid_3_report.setValue(grid_3_report);
				txtgrid_3_report.setId("AVANCEMENTS_PANEL_TXTGRID3REPORT_ID");
				txtgrid_3_report.hide();

				grid_3.getStore().add(models);
				grid_3.getView().refresh(true);
			}
		});
		if(isAdminOrContributor(roleModel, utilisateurGrpModel)){
			saveButton.setVisible(true);
			addRow.setVisible(true);
			comment.enable();
			progressGrid.getColumnModel().setHidden(0,false);
		}else{
			saveButton.setVisible(false);
			addRow.setVisible(false);
			comment.disable();
			progressGrid.getColumnModel().setHidden(0,true);
		}
	}

	public void setProgressLabelText(double value) {
		try{
			value = new Double(value);
		}catch(NumberFormatException numberFormatException){
		}
		if( progressLabel != null ) {
			NumberFormat numberFormat = NumberFormat.getFormat(NUMBER_FORMAT);
			progressLabel.setHTML("<label>" + messages.progress() + "</label>: " + numberFormat.format(value) + "% AVANCEMENT / MARCHE");
			txtEtatAvancement.setValue(numberFormat.format(value));
		}
	}

	public void setProgressDtoList(List<ProgressDto> progressDtos) {
		progressGrid.getStore().removeAll();
		progressGrid.getStore().add(progressDtos);
	}
	
	@SuppressWarnings({ "rawtypes" })
	private EditorGrid<ProgressDto> createProgressGrid() {
		String[] headers = { "<br><br>" + messages.del(), "<br><br>" + messages.number(), "<br><br>" + messages.label(), "<br><br>" + messages.date(), messages.cumule(), messages.mois(), messages.cumule(), messages.mois() };
		String[] ids = { ProgressDto.ID, ProgressDto.ORDER, ProgressDto.LABEL, ProgressDto.DATE, ProgressDto.CUMULE, ProgressDto.MOIS, ProgressDto.CUMULE2, ProgressDto.MOIS2 };
		int width = 120;
		int[] columnsWidth = { DELETE_BUTTON_WIDTH, 45, width + 10, width - 10, width, width, width, width };
		HorizontalAlignment[] horizontalAlignments = { HorizontalAlignment.CENTER };

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

		AggregationRowConfig<ProgressDto> totalSummary = new AggregationRowConfig<ProgressDto>();
		totalSummary.setHtml(ProgressDto.DATE, messages.totalSituation());
		
		for( int i = 0 ; i < ids.length ; i++ ) {
			SummaryColumnConfig column = new SummaryColumnConfig();
			final String columnId = ids[i];
			column.setId(columnId);
			column.setHeader(headers[i]);
			column.setAlignment(horizontalAlignments[0]);
			column.setMenuDisabled(true);
			column.setWidth(columnsWidth[i]);
			columns.add(column);
			if( ProgressDto.MOIS.equalsIgnoreCase(columnId) || ProgressDto.MOIS2.equalsIgnoreCase(columnId) ) {
				
				//Mois = Giá trị zone culmulé ( cùng dòng ) - giá tri zone cumulé của dòng truớc nó 
				column.setRenderer(new GridCellRenderer<ProgressDto>() {
					@Override
					public Object render(ProgressDto model, String property, ColumnData config, int rowIndex, int colIndex,
							ListStore<ProgressDto> store, Grid<ProgressDto> grid) {
						double result = 0.0;
						if( rowIndex == 0 ) {
							if( ProgressDto.MOIS.equalsIgnoreCase(columnId) ) {
								result = model.getCumule();
							}
							else {
								result = model.getCumule2();
							}
							if(ProgressDto.MOIS.equalsIgnoreCase(columnId))
								progressGrid.getStore().getModels().get(rowIndex).setMois(result);
							else
								progressGrid.getStore().getModels().get(rowIndex).setMois2(result);
						}
						else {
							ProgressDto previousModel = grid.getStore().getAt(rowIndex - 1);
							if( ProgressDto.MOIS.equalsIgnoreCase(columnId) ) {
								result = model.getCumule() - previousModel.getCumule();
							}
							else {
								result = model.getCumule2() - previousModel.getCumule2();
							}
							if(ProgressDto.MOIS.equalsIgnoreCase(columnId))
								progressGrid.getStore().getModels().get(rowIndex).setMois(result);
							else
								progressGrid.getStore().getModels().get(rowIndex).setMois2(result);
						}
						return result;
					}			
				});
				//Total summary columns
				totalSummary.setSummaryFormat(columnId, NumberFormat.getCurrencyFormat());
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						double sumMois = 0.0;
						double sumMois2 = 0.0;
						double sumCumule = 0.0;
						double sumCumule2 = 0.0;
						ProgressDto progressDto = null;
						for(int i = 0 ; i < progressGrid.getStore().getModels().size() ; i++){
							progressDto = progressGrid.getStore().getModels().get(i);
							sumMois += progressDto.getMois();
							sumMois2 += progressDto.getMois2();
							sumCumule = progressDto.getCumule();
							sumCumule2 = progressDto.getCumule2();
							cumule = progressDto.getCumule() + Constants.SEPRATE + progressDto.getCumule2();
							
						}
						//For Report
						mois = sumMois + Constants.SEPRATE + sumMois2;
						txt_advancement_mois.setValue(mois);
						txttotalsituation.setValue(sumCumule + Constants.SEPRATE + sumMois + Constants.SEPRATE + sumCumule2 + Constants.SEPRATE + sumMois2);
						//End
						if(columnId.equalsIgnoreCase(ProgressDto.MOIS))
							return sumMois;
						return sumMois2;
					}
				});
				//totalSummary.setSummaryType(columnId, SummaryType.SUM);
			}
			else if( ProgressDto.CUMULE.equalsIgnoreCase(columnId) || ProgressDto.CUMULE2.equalsIgnoreCase(columnId) ) { //Display value of last row only
				totalSummary.setSummaryFormat(columnId, NumberFormat.getCurrencyFormat());
				totalSummary.setSummaryType(columnId, new SummaryType<Double>() {
					@Override
					public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
						Object obj = m.get(field);
						if( obj != null ) {
							return ((Number) obj).doubleValue();
						}
						return 0.0;
					}
				});
			}
		}
		
		//End

		ColumnModel cm = new ColumnModel(columns);
		cm.addAggregationRow(totalSummary);

		ColumnConfig column = cm.getColumn(0);
		column.setRenderer(createDeleteButtonRenderer());

		column = cm.getColumn(1);
		column.setRenderer(new GridCellRenderer<ProgressDto>() {
			@Override
			public Object render(ProgressDto model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<ProgressDto> store, Grid<ProgressDto> grid) {
				return rowIndex + 1;
			}
		});

		column = cm.getColumn(2);
		column.setRenderer(createTextFieldRenderer(column.getWidth() - PADDING_2));
		column.setEditor(new CellEditor(createTextField(MAX_LENGTH_3)));

		column = cm.getColumn(3);
		column.setRenderer(createDateRenderer(column.getWidth() - PADDING_2));

		column = cm.getColumn(4);
		column.setRenderer(createNumberRenderer(column.getWidth() - PADDING_2));
		column.setEditor(new CellEditor(createNumberField(null)));

		column = cm.getColumn(5);
		column.setNumberFormat(NumberFormat.getFormat(NUMBER_FORMAT));

		column = cm.getColumn(6);
		column.setRenderer(createNumberRenderer(column.getWidth() - PADDING_2));
		column.setEditor(new CellEditor(createNumberField(null)));

		column = cm.getColumn(7);
		column.setNumberFormat(NumberFormat.getFormat(NUMBER_FORMAT));

		cm.addHeaderGroup(0, 4, new HeaderGroupConfig(messages.avancement(), 1, 2));
		cm.addHeaderGroup(0, 6, new HeaderGroupConfig(messages.retenues(), 1, 2));
		
		ListStore<ProgressDto> store = new ListStore<ProgressDto>();
		progressGrid = new CustomEditorGrid<ProgressDto>(store, cm);
		progressGrid.setAutoHeight(true);
		progressGrid.getView().setForceFit(true);
		progressGrid.getStore().addStoreListener(new StoreListener<ProgressDto>() {
			public void handleEvent(StoreEvent<ProgressDto> e) {
				progressGrid.getView().refresh(true);
				bus.fireEvent(new EtatAvancementEvent(listGestion,progressGrid.getStore().getModels()));
			}
		});
		
		progressGrid.addListener(Events.BeforeEdit, new Listener<GridEvent<ProgressDto>>() {
			public void handleEvent(final GridEvent<ProgressDto> be) {
				if(isAdminOrContributor(role, user)){
					be.setCancelled(false);
				}else{
					be.setCancelled(true);
				}
			}
		});
		return progressGrid;
	}

	private void createPenaltiesGrid() {
		String[] headers = { "", messages.amount(), messages.pourcentageDuRegularise() };
		String[] ids = { AnonymousDto.COLUMN_1, AnonymousDto.COLUMN_2, AnonymousDto.COLUMN_3 };
		int[] columnsWidth = { 250, 200, 280 };
		HorizontalAlignment[] horizontalAlignments = { HorizontalAlignment.CENTER, HorizontalAlignment.RIGHT, HorizontalAlignment.RIGHT };
		ColumnModel cm = createColumnModel(headers, ids, columnsWidth, horizontalAlignments);

		cm.getColumn(0).setRenderer(new GridCellRenderer<AnonymousDto>() {
			@Override
			public Object render(AnonymousDto model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<AnonymousDto> store, Grid<AnonymousDto> grid) {
				config.style = "background-color: " + HEADER_BACKGROUND_COLOR + ";";
				return "<center><label>" + model.get(property) + "</label></center>";
			}
		});

		cm.getColumn(1).setNumberFormat(NumberFormat.getFormat(NUMBER_FORMAT));
		cm.getColumn(2).setNumberFormat(NumberFormat.getPercentFormat());

		ListStore<AnonymousDto> store = new ListStore<AnonymousDto>();
		penaltyGrid = new CustomEditorGrid<AnonymousDto>(store, cm);
		penaltyGrid.setAutoHeight(true);
		penaltyGrid.getView().setForceFit(true);
	}

	private void createGrid3() {
		String[] headers = { "", messages.canto(), messages.badge(), messages.grue(), messages.lift(), messages.benne(), messages.nettoyage(), messages.autres() };
		String[] ids = { AnonymousDto.COLUMN_1, AnonymousDto.COLUMN_2, AnonymousDto.COLUMN_3, AnonymousDto.COLUMN_4, AnonymousDto.COLUMN_5, AnonymousDto.COLUMN_6, AnonymousDto.COLUMN_7, AnonymousDto.COLUMN_8 };
		int width = 90;
		int[] columnsWidth = { 150, width, width, width, width, width, width, width };
		HorizontalAlignment[] horizontalAlignments = { HorizontalAlignment.CENTER };
		ColumnModel cm = createColumnModel(headers, ids, columnsWidth, horizontalAlignments);

		cm.getColumn(0).setRenderer(new GridCellRenderer<AnonymousDto>() {
			@Override
			public Object render(AnonymousDto model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<AnonymousDto> store, Grid<AnonymousDto> grid) {
				config.style = "background-color: " + HEADER_BACKGROUND_COLOR + ";";
				return "<label>" + model.get(property) + "</label>";
			}
		});

		ListStore<AnonymousDto> store = new ListStore<AnonymousDto>();
		grid_3 = new CustomEditorGrid<AnonymousDto>(store, cm);
		grid_3.setHeight(70);
		grid_3.getView().setForceFit(true);
	}

	public List<ProgressDto> getProgressDtoList() {
		if( progressGrid == null ) {
			return new ArrayList<ProgressDto>();
		}
		else {
			return progressGrid.getStore().getModels();
		}
	}

	@Override
	public void commitDataChange() {
		commitDataGrids(progressGrid, penaltyGrid, grid_3);
	}

	public void updateDataGrid(List<GestionDto> gestionDtoList, List<DeductionDto> deductionDtoList,
			List<PenaltyDto> penaltyDtoList) {
		double totalAvenants = 0.0;
		for( GestionDto gestionDto : gestionDtoList ) {
			totalAvenants += gestionDto.getTraite();
		}

		int cantoQuantity = 0;
		int badgeQuantity = 0;
		int grueQuantity = 0;
		int liftQuantity = 0;
		int benneQuantity = 0;
		int nettoyageQuantity = 0;
		double totalProrata = 0.0;
		double autres = 0.0;
		for( DeductionDto deductionDto : deductionDtoList ) {
			cantoQuantity += deductionDto.getCanto();
			badgeQuantity += deductionDto.getBadge();
			grueQuantity += deductionDto.getGrue();
			liftQuantity += deductionDto.getLift();
			benneQuantity += deductionDto.getBenne();
			nettoyageQuantity += deductionDto.getNettoyage();
			totalProrata += deductionDto.getProrata();
			autres += deductionDto.getAutres();
		}

		double totalRefacturation = cantoQuantity * 500 + badgeQuantity * 100 + grueQuantity * 2000 + liftQuantity * 600 + benneQuantity * 1500 + nettoyageQuantity * 900;
		double refacturationPercentage = 0.0;
		if( totalAvenants != 0 ) {
			refacturationPercentage = totalRefacturation / totalAvenants;
		}

		double prorataPercentage = 0.0;
		if( totalAvenants != 0 ) {
			prorataPercentage = totalProrata / totalAvenants;
		}

		double totalRefacturationDontProrata = totalRefacturation + totalProrata;
		double refacturationDontProrataPercentage = 0.0;
		if( totalAvenants != 0 ) {
			refacturationDontProrataPercentage = totalRefacturationDontProrata / totalAvenants;
		}

		double refacturationsDontProrataEtAutre = totalRefacturationDontProrata + autres;
		double refacturationsDontProrataEtAutrePercentage = 0.0;
		if( totalAvenants != 0 ) {
			refacturationsDontProrataEtAutrePercentage = refacturationsDontProrataEtAutre / totalAvenants;
		}

		double totalPenalty = 0.0;
		for( PenaltyDto penaltyDto : penaltyDtoList ) {
			totalPenalty += penaltyDto.getAmount();
		}

		double penaltyPercentage = 0.0;
		if( totalAvenants != 0 ) {
			penaltyPercentage = totalPenalty / totalAvenants;
		}

		penaltyGrid.getStore().removeAll();

		List<AnonymousDto> models = new ArrayList<AnonymousDto>();

		AnonymousDto model = null;

		String[] column1s = { messages.refacturations(), messages.prorata2(), messages.refacturationsDontProrata(), messages.refacturationsDontProrataEtAutre(), messages.penalites() };
		double[] column2s = { totalRefacturation, totalProrata, totalRefacturationDontProrata, refacturationsDontProrataEtAutre, totalPenalty };
		double[] column3s = { refacturationPercentage, prorataPercentage, refacturationDontProrataPercentage, refacturationsDontProrataEtAutrePercentage, penaltyPercentage };
		//For report
		detail_des_retenues_appliques ="";
		NumberFormat numberFormat = NumberFormat.getFormat(Constants.NUMBER_FORMAT);
		for( int i = 0 ; i < 5 ; i++ ) {
			model = new AnonymousDto();
			model.set(AnonymousDto.COLUMN_1, column1s[i]);
			model.set(AnonymousDto.COLUMN_2, column2s[i]);
			model.set(AnonymousDto.COLUMN_3, column3s[i]);
			//For report
			detail_des_retenues_appliques += numberFormat.format(column2s[i]) + Constants.SEPRATE + (numberFormat.format(column3s[i] * 100) + "%") + Constants.SEPRATE;
			models.add(model);
		}
		//For Report
		if(detail_des_retenues_appliques != null && detail_des_retenues_appliques.length() > 0){
			detail_des_retenues_appliques = detail_des_retenues_appliques.substring(0,detail_des_retenues_appliques.length() - Constants.SEPRATE.length());
		}

		txtdetail_des_retenues_appliques.setValue(detail_des_retenues_appliques);
		txtdetail_des_retenues_appliques.hide();

		penaltyGrid.getStore().add(models);
	}
	
	@SuppressWarnings("unchecked")
	private void exportAvancementsPanel(){
		String chainter = navigation.getContext().getCurrentChantier().getNom();
		TextField<String> societte = (TextField<String>)ComponentManager.get().get("INFORMATION_PANEL_SOCIETE_ID");
		TextField<String> lot = (TextField<String>)ComponentManager.get().get("INFORMATION_PANEL_LOT_ID");
		ComboBox<LotTypeDto> lotType =  (ComboBox<LotTypeDto>)ComponentManager.get().get("INFORMATION_PANEL_LOT_TYPE_ID");
		TextField<String> sitravaux = (TextField<String>)ComponentManager.get().get("INFORMATION_PANEL_SITRAVAUX_ID");
		NumberField montant = (NumberField)ComponentManager.get().get("INFORMATION_PANEL_MONTANT_ID");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();	
		List<ProgressDto> list = progressGrid.getStore().getModels();		
		
		ProgressDto progressDto = null;
		String process = "";
		String totalsituation = "";
		//Grid 1
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(Constants.DATE_FORMAT);
		NumberFormat numberFormat = NumberFormat.getFormat(Constants.NUMBER_FORMAT);
		String process_date = "";
		//String [] arrMois = mois.split(Constants.SEPRATE);
		for (int j = 0; j < list.size(); j++) {		
			progressDto = list.get(j);
			process_date = progressDto.getDate() != null ? dateTimeFormat.format(progressDto.getDate()) : "";
			process += (j+1) + Constants.SEPRATE + progressDto.getLabel() + Constants.SEPRATE +
			process_date + Constants.SEPRATE + numberFormat.format(progressDto.getCumule()) + Constants.SEPRATE + numberFormat.format(progressDto.getMois()) + 
			Constants.SEPRATE + numberFormat.format(progressDto.getCumule2()) + Constants.SEPRATE + numberFormat.format(progressDto.getMois2()) + Constants.SEPRATE;
		}

		String generaleInformation = chainter + Constants.SEPRATE + lot.getValue() + Constants.SEPRATE + sitravaux.getValue() + Constants.SEPRATE + 
		societte.getValue() + Constants.SEPRATE + lotType.getValue().getName() + Constants.SEPRATE + montant.getValue();
		params.add(new NameValuePair("generaleInformation", generaleInformation));
		
		params.add(new NameValuePair(ConstantClient.PROCESSDTO_ID_STR, process));
		
		if((cumule != null && cumule.length() > 0) && (mois !=null && mois.length() > 0)){
			String [] arrCumule = cumule.split(Constants.SEPRATE);
			String [] arrMois = mois.split(Constants.SEPRATE);
			totalsituation = numberFormat.format(new Double(arrCumule[0])) + Constants.SEPRATE + numberFormat.format(new Double(arrMois[0])) + Constants.SEPRATE + 
							numberFormat.format(new Double(arrCumule[1])) + Constants.SEPRATE + numberFormat.format(new Double(arrMois[1]));
			params.add(new NameValuePair("totalsituation",totalsituation));
		}
		//Grid 2
		params.add(new NameValuePair("detail_des_retenues_appliques",detail_des_retenues_appliques));
		
		//Grid 3
		params.add(new NameValuePair("grid_3_report",grid_3_report));
		params.add(new NameValuePair("etatAvancement",numberFormat.format(etatAvancement)));
		String srcComment = comment.getValue();
		params.add(new NameValuePair("commentaire",srcComment));
		String exportPdfUrl = GWT.getHostPageBaseURL() + "list_processdto.pdf";	
		ReportUtil.showReport(exportPdfUrl, params.toArray(new NameValuePair[params.size()]));		
	}
	
	public void setComment(String value) {
		comment.setValue(value);
	}
	
	private void bindModel(FicheStDto ficheStDto) {
		if( ficheStDto == null ) {
			return;
		}

		FormBinding binding = new FormBinding(formPanel);

		//Auto binding fields
		binding.autoBind();
		binding.bind(ficheStDto);
	}

	public void setModel(FicheStDto ficheStDto) {
		if(ficheStDto == null) {
			return;
		}
		
		setProgressDtoList(ficheStDto.getProgresses());
		setComment(ficheStDto.getAvctCommentaires());
		bindModel(ficheStDto);
	}
	
	/**
	 * Init parameters for report
	 */
	private void initFieldReport(){
		//Report set hide TextField
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
		//End
	}
}
