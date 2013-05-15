package com.structis.vip.client.navigation;

import static com.structis.vip.client.navigation.Action.ACTION_ACCEUIL;
import static com.structis.vip.client.navigation.Action.ACTION_ADMIN;

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.user.client.Window;
import com.structis.vip.client.ecran.AccueilEcran;
import com.structis.vip.client.ecran.AdministrationEcran;
import com.structis.vip.client.ecran.ChooseEntityEcran;
import com.structis.vip.client.ecran.ControlEcran;
import com.structis.vip.client.ecran.DelegationEcran;
import com.structis.vip.client.ecran.DocumentEcran;
import com.structis.vip.client.ecran.LoginEcran;
import com.structis.vip.client.ecran.PilotageEcran;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.security.Role;

/**
 * Classe implementation de navigation service
 * Voir {@link NavigationService}
 * 
 * @author b.brotosumpeno
 *
 */
public class NavigationManager extends NavigationAbstract implements
		NavigationService {
	
	protected NavigationManager(){
	}

	@Override
	protected void initNavigation() {

		manageRole();
		
		// Init la navigation
		// Navigation par d�faut
		
		mapNavigation.put(Action.ACTION_LOGIN, new Ecran(new LoginEcran()));
		mapNavigation.put(ACTION_ACCEUIL, new Ecran(new AccueilEcran()));
		mapNavigation.put(ACTION_ADMIN, new Ecran(new AdministrationEcran()));	
		mapNavigation.put(Action.ACTION_PILOTAGE, new Ecran(new PilotageEcran()));	
		mapNavigation.put(Action.ACTION_DELEGATION, new Ecran(new DelegationEcran()));
		mapNavigation.put(Action.ACTION_CHOOSE_ENTITE, new Ecran(new ChooseEntityEcran()));
		mapNavigation.put(Action.ACTION_CONTROL, new Ecran(new ControlEcran()));
		mapNavigation.put(Action.ACTION_DOCUMENT, new Ecran(new DocumentEcran()));
		
		
		Window.addCloseHandler(new com.google.gwt.event.logical.shared.CloseHandler<Window>() {
			@Override
			public void onClose(CloseEvent<Window> closeEvent) {
				SessionServiceImpl.getInstance().killSession();
			}
		});
	}
	
	private void manageRole() {
		//Init les roles de base
		roles.add(Role.ANONYMOUS);
	}
}
