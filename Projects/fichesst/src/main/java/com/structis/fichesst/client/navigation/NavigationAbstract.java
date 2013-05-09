package com.structis.fichesst.client.navigation;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.structis.fichesst.client.constant.ConstantClient;
import com.structis.fichesst.client.ecran.EcranLoadable;
import com.structis.fichesst.client.message.ActionMessages;
import com.structis.fichesst.shared.config.ApplicationContext;
import com.structis.fichesst.shared.security.Droits;

/**
 * La classe abstract qui contient le m�chanisme : de lar�direction de widget sur l'application et de la cr�ation de fil d'ariane.
 * 
 * Il utilise une map pour stocker la correspondant entre une action et son �cran Il fournie la m�thode initNavigation qui initialises cette map.
 * 
 * @author b.brotosumpeno
 * 
 */
public abstract class NavigationAbstract implements NavigationService {
	private final HorizontalPanel hPanel = new HorizontalPanel();
	private boolean init = false;
	private Action actuelle = null;
	protected List<Droits> droits = new ArrayList<Droits>();
	protected ApplicationContext context = new ApplicationContext();
	private final SimpleEventBus bus = new SimpleEventBus();
	protected Map<Action, Ecran> mapNavigation = new HashMap<Action, Ecran>();

	protected abstract void initNavigation();

	private final ActionMessages actionMessages = GWT.create(ActionMessages.class);
	public static final String CONTENT = "appContent";

	@Override
	public void goToEcran(Action action) {
		goToEcran(action, new NavigationEvent());
	}

	@Override
	public Action getActionActuelle() {
		return actuelle;
	}

	@Override
	public void goToEcran(Action action, NavigationEvent event) {
		if (!init) {
			initNavigation();
			init = true;
		}

		// Test credentialiter

		Ecran ecran = null;

		/* EcranLoadable comEcran = null; */// ecran.getEcran();
		// S'il n'a pas d'�cran => ACTION ACCEUIL

		if (null == ecran) {
			GWT.log("null ecran " + action.getLabel());
			ecran = mapNavigation.get(action.ACTION_ACCEUIL);
		}

		/*
		 * if (action==Action.ACTION_SYNTHESE) { ecran=mapNavigation.get(Action.ACTION_SYNTHESE); }
		 */
		ecran = mapNavigation.get(action);
		EcranLoadable comEcran = ecran.getEcran();
		if (History.getToken().equalsIgnoreCase(Action.ACTION_ACCEUIL.getLabel())) {
			RootPanel.get("appContent").clear();
			RootPanel.get("appContent").add(comEcran);
		}
		try {
			GWT.log("change content " + action.getLabel());
			RootPanel.get(CONTENT).clear();
			RootPanel.get("appContent").add(comEcran);
		} catch (ConcurrentModificationException e) {
			GWT.log("Attention error ConcurrentModificationException" + e);
			RootPanel.get("appContent").add(comEcran);
		}

		// Mettre � jour l'actuelle
		actuelle = action;
		// Charger l'application
		comEcran.onLoadApplication(event);

		// Mettre � jour l'histoire
		if (event.getParameters() != null && event.getParameters().get(ConstantClient.CANCEL_HISTORY) == null) {
			HistoryHelper.newItem(action.getLabel(), event.getParameters());
		}
		HistoryHelper.pushItem(action, event.getParameters());
	}

	@Override
	public void setApplicationContext(ApplicationContext context) {
		// this.context.setUser(context.getUser());

		// this.context.setRole(context.getRole());

		this.context.setUserModel(context.getUserModel());
		this.context.setCurrentChantier(context.getCurrentChantier());
	}

	@Override
	public ApplicationContext getContext() {
		return context;
	}

	@Override
	public SimpleEventBus getBus() {
		return bus;
	}

}
