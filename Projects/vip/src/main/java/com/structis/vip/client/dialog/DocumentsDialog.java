package com.structis.vip.client.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DomDelModel;

public class DocumentsDialog extends Window {
	private List<DocumentMdlModel> documents;
	private ListStore<DomDelModel> dataStore;
	
	private Button btnSelect;
	private Button btnCancel;
	
	List<DocumentMdlModel> selectedDocuments = null;
	
	
	public DocumentsDialog(List<DocumentMdlModel> documents, ListStore<DomDelModel> dataStore){
		this.documents = documents;
		this.dataStore = dataStore;
		refineDocuments();
		initUI();
	}
	
	
	
	private void refineDocuments() {
		if (dataStore.getModels().size() == 0) {
			return;
		}
		HashMap<Integer, DocumentMdlModel> refinedDocuments = new HashMap<Integer, DocumentMdlModel>();
		for (int index = 0; index < documents.size(); index++) {
			refinedDocuments.put(documents.get(index).getId(), documents.get(index));
		}
		
		List<DomDelModel> data = dataStore.getModels();
		for (int i = 0; i < data.size(); i++){
			DomDelModel document = data.get(i);
			if (refinedDocuments.get(document.getId()) != null) {
				refinedDocuments.remove(document.getId());
			}
		}
		if (refinedDocuments.values().size() >0) {
			documents = new ArrayList<DocumentMdlModel>(refinedDocuments.values());	
		}else {
			documents = new ArrayList<DocumentMdlModel>();
		}
		
	}



	private void initUI(){
		LayoutContainer main = new LayoutContainer();
		main.setStyleAttribute("margin", "20px");
		FitLayout flLeft = new FitLayout();
		main.setLayout(flLeft);
		
		  
	    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
	  
	    final CheckBoxSelectionModel<DocumentMdlModel> sm = new CheckBoxSelectionModel<DocumentMdlModel>();   
	    sm.setSelectionMode(SelectionMode.MULTI);
	    configs.add(sm.getColumn());   
	  
	    ColumnConfig column = new ColumnConfig();   
	    column.setId(DocumentMdlModel.DOM_NAME);   
	    column.setHeader("Name");   
	    column.setWidth(600);   
	    configs.add(column);   
	  
	    column = new ColumnConfig();   
	    column.setId(DocumentMdlModel.DOM_TYPE);   
	    column.setHeader("Type");   
	    column.setWidth(100);
	    configs.add(column); 
	  
	    ListStore<DocumentMdlModel> store = new ListStore<DocumentMdlModel>();   
	    store.add(documents);   
	  
	    ColumnModel cm = new ColumnModel(configs);   
	  
	    ContentPanel cp = new ContentPanel();   
	    cp.setHeaderVisible(false);
	    cp.setFrame(true);   
//	    cp.setIcon(Resources.ICONS.table());   
	    cp.setLayout(new FitLayout()); 
	    cp.setButtonAlign(HorizontalAlignment.RIGHT);
	    cp.setSize(700, 400);   
	    final Grid<DocumentMdlModel> grid = new Grid<DocumentMdlModel>(store, cm);   
	    grid.setSelectionModel(sm);   
	    grid.setBorders(true);  
	    grid.setStripeRows(true);
	    grid.setColumnReordering(true);
	    grid.getAriaSupport().setLabelledBy(cp.getHeader().getId() + "-label");   
	    grid.addPlugin(sm);   
   
	    cp.add(grid);   
	    
	    btnSelect = new Button("Select");
	    btnSelect.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				selectedDocuments = grid.getSelectionModel().getSelectedItems();
				if (selectedDocuments.size() > 0) {
					List<DomDelModel> domDels = new ArrayList<DomDelModel>();
					for (int i = 0; i < selectedDocuments.size(); i++ ) {
						DocumentMdlModel template = selectedDocuments.get(i);
						DomDelModel domdel = new DomDelModel();
						domdel.setDocumentMdl(template);
						domDels.add(domdel);
					}
					dataStore.add(domDels);
				}
				hide();
			}
		});
	    btnCancel = new Button("Cancel");
	    btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
	    	@Override
		    public void componentSelected(ButtonEvent ce) {
	    		selectedDocuments = null;
	    		hide();
		    	
		    }
		});
	    
	    cp.addButton(btnSelect);
	    cp.addButton(btnCancel);
	    cp.setButtonAlign(HorizontalAlignment.CENTER);
	    main.add(cp);
	    add(main);
	    setSize(800, 500);
	    setHeading("Document Selection Dialog");
	}

}
