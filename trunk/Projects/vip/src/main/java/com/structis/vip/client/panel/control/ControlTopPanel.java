package com.structis.vip.client.panel.control;

import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
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
import com.structis.vip.client.event.control.RefreshTreeEvent;
import com.structis.vip.client.event.control.RefreshTreeHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class ControlTopPanel extends HorizontalPanel {
	private final Messages messages = GWT.create(Messages.class);

	private ListStore<PerimetreModel> perimetres; 
	
	private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();

	private SimpleEventBus bus;
	
	private Label cbEntite;
	private ComboBox<PerimetreModel> cbPerimetre;
	private Label lblPeremitre;        
	private Button buttonValider;
	
	private UserModel currentUser;
	boolean isSuperUser = false;
	
	private EntiteModel selectedEntiteModel;
	private PerimetreModel selectedPerimetreModel;
	
	public ControlTopPanel(SimpleEventBus bus){
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
		
		cbEntite = new Label();
		cbEntite.setText(messages.delegationentite());
		
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

//		add(lblEntite);
//		add(lblEntiteValue);
		add(cbEntite);
		add(lblPeremitre);
		add(cbPerimetre);
		add(buttonValider);
		
		// set attributes
		setStyleAttribute("padding-top", "10px");
		setStyleAttribute("padding-left", "10px");
		setBorders(false);
	}
	
	private void addHandler() {
		// catch event listener for Valider button
		buttonValider.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				EntiteModel entiteModel = selectedEntiteModel;
//				if (isSuperUser) {
//					entiteModel = cbEntite.getValue();
//				}
				
				if (cbPerimetre.validate()) {
					// fire event
					PerimetreModel perimetreModel = cbPerimetre.getValue();
					RefreshTreeEvent event = new RefreshTreeEvent(entiteModel, perimetreModel);
					//DelegationListProjectEvent event = new DelegationListProjectEvent(entiteModel, perimetreModel);
					bus.fireEvent(event);
					SessionServiceImpl.getInstance().setPerimetreContext(perimetreModel);
					//bus.fireEvent(controlFilterEvent);
				} 
			}
		});
		
		// add handler when click on Valider button
		bus.addHandler(RefreshTreeEvent.getType(), new RefreshTreeHandler(){

			@Override
			public void onLoadAction(RefreshTreeEvent event) {
				disableEvents(true);
				selectedEntiteModel = event.getEntiteModel();
				selectedPerimetreModel = event.getPerimetreModel();
//				Window.alert(selectedEntiteModel.getName());
//				lblEntiteValue.setText(event.getEntiteModel().getName());
				cbEntite.setText(messages.delegationentite() + " : " + selectedEntiteModel.getName());
				disableEvents(false);
			}	
		});
	}
	
	private void getStoreForPerimetreCombo(String emId) {
		perimetres = new ListStore<PerimetreModel>();
		cbPerimetre.clear();
		clientPerimetreService.findFirstLevelPerimetreByUserRoles(emId, false, SessionServiceImpl.getInstance().getUserContext().getUserRoles(), new AsyncCallbackWithErrorResolution<List<PerimetreModel>>() {
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
	public void onLoadPanel() {
		disableEvents(true);
		selectedEntiteModel = SessionServiceImpl.getInstance().getEntiteContext();
		selectedPerimetreModel = SessionServiceImpl.getInstance().getPerimetreContext();				
		cbEntite.setText(messages.delegationentite() + " : " + selectedEntiteModel.getName());
		perimetres.removeAll();
		cbPerimetre.clear();
		if (null != selectedEntiteModel) {
			refreshDataForPerimetre(selectedEntiteModel.getEntId());
		}
		disableEvents(false);
	}
}
