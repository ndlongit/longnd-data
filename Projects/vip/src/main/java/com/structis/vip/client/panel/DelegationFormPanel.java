package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientEntiteServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class DelegationFormPanel extends HorizontalPanel {
	private final Messages messages = GWT.create(Messages.class);

	private ListStore<EntiteModel> entites;
	private ListStore<PerimetreModel> perimetres; 
	
	private ClientEntiteServiceAsync clientEntiteService =  ClientEntiteServiceAsync.Util.getInstance();
	private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();

	private SimpleEventBus bus;

	private Label lblEntite;
	private Label lblEntiteValue;	
	private ComboBox<EntiteModel> cbEntite;
	private ComboBox<PerimetreModel> cbPerimetre;
	private Label lblPeremitre;        
	private Button buttonValider;
	
	private UserModel currentUser;
	private boolean isSuperUser = false;
	
	private EntiteModel selectedEntiteModel;
	private PerimetreModel selectedPerimetreModel;
	
	public DelegationFormPanel(SimpleEventBus bus){
		this.bus = bus;
	}
	
	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		
		currentUser = SessionServiceImpl.getInstance().getUserContext();
		if (currentUser != null) {
			isSuperUser = currentUser.isSuperUser();
		}
		
		initUI();
		
		addHandler();
	}
	
	public void initUI() {
		entites = new ListStore<EntiteModel>();
		clientEntiteService.getAllEntites(new AsyncCallback<List<EntiteModel>>() {
			@Override
			public void onSuccess(List<EntiteModel> arg0) {				
				entites.add(arg0);
				cbEntite.setStore(entites);				
			}
			@Override
			public void onFailure(Throwable arg0) {				
			}
		});		
		
		// Entite
		lblEntite = new Label(messages.delegationentite() + " : ");

		lblEntiteValue = new Label("");
		lblEntiteValue.setStyleAttribute("margin-left", "5px");
		
		cbEntite = new ComboBox<EntiteModel>();
		cbEntite.setStyleAttribute("margin-left", "5px");
		cbEntite.setEditable(false);
		cbEntite.setLabelSeparator("");
		cbEntite.setStore(entites);
		cbEntite.setDisplayField(EntiteModel.ENTITE_NAME);	
		cbEntite.setTriggerAction(TriggerAction.ALL);
		cbEntite.setVisible(false);		
		cbEntite.setAllowBlank(false);
		
		cbEntite.addSelectionChangedListener(new SelectionChangedListener<EntiteModel>() {			
			@Override
			public void selectionChanged(SelectionChangedEvent<EntiteModel> se) {
				disableEvents(true);
				EntiteModel selected = se.getSelectedItem();				
				perimetres.removeAll();
				cbPerimetre.clear();
				if (null != selected) {
					refreshDataForPerimetre(selected.getEntId());
				}
				disableEvents(false);
			}
		});
		
		// Perimetre
		lblPeremitre = new Label(messages.commonPerimetre() + " : ");    
		lblPeremitre.setStyleAttribute("margin-left", "60px");
		
		perimetres = new ListStore<PerimetreModel>();
		cbPerimetre = new ComboBox<PerimetreModel>();
		
		cbPerimetre.setFieldLabel(messages.commonPerimetre());
		cbPerimetre.setStore(perimetres);
		cbPerimetre.setDisplayField(PerimetreModel.PERIMETRE_NAME);
		cbPerimetre.setTriggerAction(TriggerAction.ALL);
		cbPerimetre.setStyleAttribute("margin-left", "5px");
		cbPerimetre.setEditable(false);
		cbPerimetre.setAllowBlank(false);
		cbPerimetre.setWidth(400);
		cbPerimetre.setSimpleTemplate("<span title=\"{"+cbPerimetre.getDisplayField()+"}\">{"+cbPerimetre.getDisplayField()+"}</span>");
		
		//buttonValider
		buttonValider = new Button(messages.commonValiderButton());
		buttonValider.setStyleAttribute("margin-left","30px");

		add(lblEntite);
		add(lblEntiteValue);
		add(cbEntite);
		add(lblPeremitre);
		add(cbPerimetre);
		add(buttonValider);
		
		// set attributes
		setStyleAttribute("padding-top", "10px");
		setStyleAttribute("padding-left", "10px");
		setBorders(false);
		
		if (isSuperUser) {
			cbEntite.setVisible(true);
			lblEntiteValue.setVisible(false);
		} else {
			cbEntite.setVisible(false);
			lblEntiteValue.setVisible(true);
		}
	}
	
	private void addHandler() {
		// catch event listener for Valider button
		buttonValider.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				EntiteModel entiteModel = selectedEntiteModel;
				if (isSuperUser) {
					entiteModel = cbEntite.getValue();
				}
				
				if (cbPerimetre.validate()) {
					// fire event
					PerimetreModel perimetreModel = cbPerimetre.getValue();
					DelegationListProjectEvent event = new DelegationListProjectEvent(entiteModel, perimetreModel);
					bus.fireEvent(event);
					SessionServiceImpl.getInstance().setPerimetreContext(perimetreModel);
				} 
			}
		});
		
		// add handler when click on Valider button
		bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler(){
			public void onLoadAction(final DelegationListProjectEvent event) {
				disableEvents(true);
				selectedEntiteModel = event.getEntiteModel();
				selectedPerimetreModel = event.getPerimetreModel();
				lblEntiteValue.setText(event.getEntiteModel().getName());				
				cbEntite.setValue(event.getEntiteModel());
				cbPerimetre.setValue(selectedPerimetreModel);
				disableEvents(false);
			}
		});
	}
	
	private void getStoreForPerimetreCombo(String emId) {
		perimetres = new ListStore<PerimetreModel>();
		cbPerimetre.clear();
		clientPerimetreService.findFirstLevelPerimetreByUserRoles(emId, false, SessionServiceImpl.getInstance().getUserContext().getUserRoles(), new AsyncCallback<List<PerimetreModel>>() {
			@Override
			public void onSuccess(List<PerimetreModel> arg0) {
				perimetres.add(arg0);
				cbPerimetre.setStore(perimetres);
				
				PerimetreModel perimetreModel = null;
				
				if (selectedPerimetreModel != null) {
					for (PerimetreModel perMdl : perimetres.getModels()) {
						if (perMdl.getPerId().equals(selectedPerimetreModel.getPerId())) {
							perimetreModel = perMdl;
						}
					}
				}
				
				if (perimetreModel == null) {
					if (arg0 != null && arg0.size() > 0) {
						PerimetreModel pm = arg0.get(0);					
						cbPerimetre.select(0);					
						cbPerimetre.setValue(pm);					
					}
				} else {
					cbPerimetre.setValue(perimetreModel);
				}
			}
			@Override
			public void onFailure(Throwable arg0) {				
			}
		});	
	}
	
	protected void refreshDataForPerimetre(final String emId) {		
		getStoreForPerimetreCombo(emId);			
	}
	
	public void setEnableForm(boolean enabled) {
		this.cbEntite.setEnabled(enabled);
		this.cbPerimetre.setEnabled(enabled);
		this.buttonValider.setEnabled(enabled);
	}
}
