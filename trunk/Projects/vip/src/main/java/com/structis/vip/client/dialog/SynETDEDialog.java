package com.structis.vip.client.dialog;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.SynETDEEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientSyncServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.SynETDEModel;

public class SynETDEDialog extends Window {
	private Button btnValider;
	private Button btnAnuler;
	private SimpleEventBus bus;
	private EntiteModel entite;

	private final Messages messages = GWT.create(Messages.class);
	private ListStore<SynETDEModel> store = new ListStore<SynETDEModel>();
	private ClientSyncServiceAsync clientSyncService = ClientSyncServiceAsync.Util.getInstance();
	private Grid<SynETDEModel> grid;
	
	public SynETDEDialog(SimpleEventBus bus, EntiteModel entite){
		this.bus = bus;
		this.entite = entite;
		setLayout(new FitLayout());	
		setSize(400, 300);
	    setHeading(messages.commonchoosetosyn());
	    
		initUI();
		initData();
	}
	
	private void initData() {
		grid.mask(messages.commonloadingdata());
		clientSyncService.getRubsiCodesName(entite.getEntId(),entite.getName(), new AsyncCallback<List<SynETDEModel>>() {
			@Override
			public void onSuccess(List<SynETDEModel> arg0) {
				store.add(arg0);
				grid.unmask();
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				grid.unmask();
			}
		});
	}
	
	private void initUI(){
	    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
	  
	    final CheckBoxSelectionModel<SynETDEModel> sm = new CheckBoxSelectionModel<SynETDEModel>();   
	    sm.setSelectionMode(SelectionMode.MULTI);
	    configs.add(sm.getColumn());   
	  
	    ColumnConfig column = new ColumnConfig();   
	    column.setId(SynETDEModel.SYNC_ETDE_CODE);   
	    column.setHeader(messages.commoncode());   
	    column.setWidth(100);   
	    configs.add(column);   
	  
	    column = new ColumnConfig();   
	    column.setId(SynETDEModel.SYNC_ETDE_NAME);   
	    column.setHeader(messages.commonnom());   
	    column.setWidth(300);
	    configs.add(column); 
	  
	    ColumnModel cm = new ColumnModel(configs);   
	    
	    store = new ListStore<SynETDEModel>(); 
	    
	    grid = new Grid<SynETDEModel>(store, cm);
	    grid.getView().setAutoFill(true);
		grid.getView().setForceFit(true);
		WindowResizeBinder.bind(grid);
		
	    grid.setSelectionModel(sm);   
	    grid.setBorders(true);  
	    grid.setColumnLines(true);
	    grid.setLoadMask(true);
	    grid.setStripeRows(true);
	    grid.setColumnReordering(true);
	    grid.addPlugin(sm);   
   
	    btnValider = new Button(messages.commonValiderButton());
	    btnValider.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				List<SynETDEModel> seleted = grid.getSelectionModel().getSelectedItems();
				if (seleted != null && seleted.isEmpty() == false) {
					SynETDEEvent event = new SynETDEEvent();
					event.setModels(seleted);
					bus.fireEvent(event);
					hide();
				}
			}
		});
	    btnAnuler = new Button(messages.commonAnnulerButton());
	    btnAnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
	    	@Override
		    public void componentSelected(ButtonEvent ce) {
	    		hide();
		    }
		});
	    
	    setButtonAlign(HorizontalAlignment.RIGHT);
	    addButton(btnAnuler);
	    addButton(btnValider);
	    
	    add(grid);
	}
}
