package com.structis.fichesst.client.popup;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.fichesst.client.util.NameValuePair;
import com.structis.fichesst.client.util.ReportUtil;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.LotTypeDto;
import com.structis.fichesst.shared.util.Constants;

public class CreateFicheSTPopUpReportWindow extends Window {
	public static final String				 SCREEN_ID = "CreateFicheSTPopUpReportWindow";
	
	private Button							 btnAnnuler;
	
	private Button							 btnImprimer;
	
	@SuppressWarnings("unused")
	private final SimpleEventBus			   bus;
	
	private CheckBoxSelectionModel<FicheStDto> checkSelection1;
	
	private CheckBoxSelectionModel<FicheStDto> checkSelection2;
	
	private final List<FicheStDto>			 ficheStDtos;
	
	private EditorGrid<FicheStDto>			 ficheStGrid1;
	
	private EditorGrid<FicheStDto>			 ficheStGrid2;
	private ListStore<FicheStDto>			  ficheStStore1;
	private ListStore<FicheStDto>			  ficheStStore2;
	private final Boolean					  isHasGestion;
	
	public CreateFicheSTPopUpReportWindow(SimpleEventBus bus, List<FicheStDto> ficheStDtos, Boolean isHasGestion) {
		super();
		this.bus = bus;
		this.ficheStDtos = ficheStDtos;
		this.isHasGestion = isHasGestion;
		removeDuplicateTypeDeLot();
		this.setId(SCREEN_ID);
		buildField();
	}
	
	private void buildField() {
		setHeading("FICHES-ST : Impression de plusieurs sous-traitants");
		setSize(450, 350);
		ficheStStore1 = new ListStore<FicheStDto>();
		ficheStStore1.add(ficheStDtos);
		
		checkSelection1 = new CheckBoxSelectionModel<FicheStDto>();
		checkSelection1.setSelectionMode(SelectionMode.MULTI);
		checkSelection1.bind(ficheStStore1);
		
		ficheStGrid1 = new EditorGrid<FicheStDto>(ficheStStore1, createColumnModelGrid1());
		ficheStStore1.setSortDir(SortDir.NONE);
		ficheStGrid1.setHeight(90);
		ficheStGrid1.setBorders(false);
		ficheStGrid1.setBorders(true);
		ficheStGrid1.setStripeRows(true);
		ficheStGrid1.setLoadMask(true);
		ficheStGrid1.setColumnLines(true);
		ficheStGrid1.addPlugin(checkSelection1);
		ficheStGrid1.setSelectionModel(checkSelection1);
		ficheStGrid1.getColumnModel().getColumns().add(0, checkSelection1.getColumn());
		ficheStGrid1.getView().setForceFit(true);
		
		FieldSet fieldSetGrid1 = new FieldSet();
		fieldSetGrid1.setHeading("Liste des sous-traitants à imprimer");
		fieldSetGrid1.add(ficheStGrid1);
		add(fieldSetGrid1);
		
		checkSelection2 = new CheckBoxSelectionModel<FicheStDto>();
		checkSelection2.setSelectionMode(SelectionMode.MULTI);
		checkSelection2.bind(ficheStStore2);
		
		ficheStGrid2 = new EditorGrid<FicheStDto>(ficheStStore2, createColumnModelGrid2());
		ficheStStore2.setSortDir(SortDir.NONE);
		ficheStGrid2.setHeight(60);
		ficheStGrid2.setBorders(false);
		ficheStGrid2.setBorders(true);
		ficheStGrid2.setStripeRows(true);
		ficheStGrid2.setLoadMask(true);
		ficheStGrid2.setColumnLines(true);
		ficheStGrid2.addPlugin(checkSelection2);
		ficheStGrid2.setSelectionModel(checkSelection2);
		ficheStGrid2.getColumnModel().getColumns().add(0, checkSelection2.getColumn());
		ficheStGrid2.getView().setForceFit(true);
		FieldSet fieldSetGrid2 = new FieldSet();
		fieldSetGrid2.setHeading("Choix par type de lot");
		fieldSetGrid2.add(ficheStGrid2);
		add(fieldSetGrid2);
		btnImprimer = new Button("Imprimer");
		btnImprimer.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				CreateFicheSTPopUpReportWindow lisRisquePanel = (CreateFicheSTPopUpReportWindow) ComponentManager.get().get(SCREEN_ID);
				List<FicheStDto> listFis1 = lisRisquePanel.getCheckSelection1().getSelectedItems();
				List<FicheStDto> listFis2 = lisRisquePanel.getCheckSelection2().getSelectedItems();
				List<FicheStDto> listAllFis = new ArrayList<FicheStDto>();
				listAllFis.addAll(listFis1);
				listAllFis.addAll(listFis2);
				String ids = convertToStringId(listAllFis);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new NameValuePair("fichestIds", ids));
				params.add(new NameValuePair("isHasGestion", isHasGestion + ""));
				String action = GWT.getHostPageBaseURL() + "custom_fichest.pdf";
				if (ids != null && ids.length() > 0)
					ReportUtil.showReport(action, params.toArray(new NameValuePair[params.size()]));
			};
		});
		btnAnnuler = new Button("Annuler");
		btnAnnuler.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				hide();
			}
		});
		addButton(btnAnnuler);
		addButton(btnImprimer);
		setButtonAlign(HorizontalAlignment.CENTER);
	}
	
	private String combineProps(String... properties) {
		String result = "";
		if (properties != null) {
			for (int i = 0; i < properties.length; i++) {
				
				if (result == "") {
					result = properties[i];
				} else {
					result += "." + properties[i];
				}
			}
		}
		
		return result;
	}
	
	/**
	 * @param list
	 * @return
	 */
	private String convertToStringId(List<FicheStDto> list) {
		String ids = "";
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getId() == list.get(j).getId()) {
					list.remove(list.get(j));
				}
			}
		}
		for (FicheStDto ficheStDto : list) {
			ids += ficheStDto.getId() + Constants.SEPRATE;
		}
		return ids;
	}
	
	public ColumnModel createColumnModelGrid1() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig column = new ColumnConfig();
		column.setHeader("ID");
		column.setId(FicheStDto.ID);
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setWidth(150);
		column.setHidden(true);
		configs.add(column);
		
		column = new ColumnConfig();
		column.setMenuDisabled(true);
		column.setSortable(false);
		column.setHeader("Societé");
		column.setId(FicheStDto.SOCIETE);
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setWidth(420);
		configs.add(column);
		return new ColumnModel(configs);
	}
	
	public ColumnModel createColumnModelGrid2() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig column = new ColumnConfig();
		column.setHeader("ID");
		column.setId(FicheStDto.ID);
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setWidth(150);
		column.setHidden(true);
		configs.add(column);
		
		column = new ColumnConfig();
		column.setSortable(false);
		column.setMenuDisabled(true);
		column.setHeader("Type de lot");
		String lotname = combineProps(FicheStDto.LOT_TYPE, LotTypeDto.NAME);
		column.setId(lotname);
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setWidth(420);
		configs.add(column);
		
		return new ColumnModel(configs);
	}
	
	public CheckBoxSelectionModel<FicheStDto> getCheckSelection1() {
		return checkSelection1;
	}
	
	public CheckBoxSelectionModel<FicheStDto> getCheckSelection2() {
		return checkSelection2;
	}
	
	/**
	 * Remove duplicate object
	 */
	private void removeDuplicateTypeDeLot() {
		List<FicheStDto> list = new ArrayList<FicheStDto>();
		String src = "";
		for (FicheStDto ficheStDto : ficheStDtos) {
			if (ficheStDto != null && ficheStDto.getLotType() != null && src.indexOf(ficheStDto.getLotType().getName()) == -1) {
				list.add(ficheStDto);
				src += ficheStDto.getLotType().getName();
			}
		}
		ficheStStore2 = new ListStore<FicheStDto>();
		ficheStStore2.add(list);
	}
	
	public void setCheckSelection1(CheckBoxSelectionModel<FicheStDto> checkSelection1) {
		this.checkSelection1 = checkSelection1;
	}
	
	public void setCheckSelection2(CheckBoxSelectionModel<FicheStDto> checkSelection2) {
		this.checkSelection2 = checkSelection2;
	}
	
}
