package com.structis.vip.client.navigation;

import static com.structis.vip.client.constant.ConstantClient.CONTENT;
import static com.structis.vip.client.navigation.Action.ACTION_ACCEUIL;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.ecran.EcranLoadable;
import com.structis.vip.shared.config.ApplicationContext;
import com.structis.vip.shared.security.Role;

/**
 * La classe abstract qui contient le m�chanisme : de lar�direction de widget
 * sur l'application et de la cr�ation de fil d'ariane. 
 * 
 * Il utilise une map pour stocker la correspondant entre une action et son �cran 
 * Il fournie la m�thode initNavigation qui initialises cette map.
 * 
 * @author b.brotosumpeno
 * 
 */
public abstract class NavigationAbstract implements NavigationService { 

	private boolean init = false;
	private Action actuelle = null;
	protected List<Role> roles = new ArrayList<Role>();
	protected ApplicationContext context = new ApplicationContext();
	private SimpleEventBus bus = new SimpleEventBus();
	
	protected Map<Action, Ecran> mapNavigation = new HashMap<Action, Ecran>();
	
	/**
	 * Initialiser le mapping entre l'action et la navigation
	 */
	protected abstract void initNavigation();
	
	/**
	 * Naviguer vers l'action
	 * @param action
	 */
	public void goToEcran(Action action){
		goToEcran(action, new NavigationEvent());
	}
	
	public Action getActionActuelle(){
		return actuelle;
	}
	
	/**
	 * Voir {@link NavigationService#goToEcran(Action)}
	 */
	public void goToEcran(Action action, NavigationEvent event) {
		// Tester si la m�thode init est d�j� appell�
		if (!init) {
			initNavigation();
			init = true;
		}
		
		// Test credentialiter
		Ecran ecran = null;
		if (roles.contains(action.getRole())){
			ecran =  mapNavigation.get(action);
		}
		
		// S'il n'a pas d'�cran => ACTION ACCEUIL
		if (null == ecran) {		
			GWT.log("null ecran "+action.getLabel() );
			ecran = mapNavigation.get(ACTION_ACCEUIL);
		}
		
		// Cr�ation fil ariane
		//String title = createFilAriane(action);
		
		// R�cuperation widget
		EcranLoadable comEcran = ecran.getEcran();
		
		// Suppression des composants dans le root panel
		//RootPanel.get(FIL_ARIANE).clear();
		RootPanel.get(CONTENT).clear();
		
		// Rajouter des composants avec le nouveau �cran
		//RootPanel.get(FIL_ARIANE).add(hPanel);
		try {
			RootPanel.get(CONTENT).add(comEcran);
		}
		catch(ConcurrentModificationException e) {
			// TODO demander un patch � sencha pour corriger ce probl�me
			GWT.log("Attention error ConcurrentModificationException" + e);
			RootPanel.get(CONTENT).add(comEcran);
		}
		
		// Mettre � jour l'actuelle
		actuelle = action;
		
		// Charger l'application
		comEcran.onLoadApplication(event);
		
		// Mettre � jour l'histoire
		if (event.getParameters() != null &&
				event.getParameters().get(ConstantClient.CANCEL_HISTORY) == null){
			HistoryHelper.newItem(action.getLabel(), event.getParameters());
		}
		HistoryHelper.pushItem(action, event.getParameters());
	}
	
	public void setApplicationContext(ApplicationContext context) {
		this.context.setAdmin(context.getAdmin());
		this.context.setDate(context.getDate());
		this.context.setRoles(context.getRoles());
		this.roles = context.getRoles();
	}
	
	public ApplicationContext getContext(){
		return context;
	}

	public SimpleEventBus getBus() {
		return bus;
	}
}
