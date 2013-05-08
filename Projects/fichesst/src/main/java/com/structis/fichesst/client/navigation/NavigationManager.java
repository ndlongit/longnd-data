package com.structis.fichesst.client.navigation;

import static com.structis.fichesst.client.navigation.Action.ACTION_ACCEUIL;
import static com.structis.fichesst.client.navigation.Action.ACTION_ADMIN;

import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.fichesst.client.ecran.AcceuilEcran;
import com.structis.fichesst.client.ecran.AdminEcran;
import com.structis.fichesst.client.ecran.ErrorEcran;
import com.structis.fichesst.client.ecran.SyntheseEcran;
import com.structis.fichesst.shared.config.ApplicationContext;
import com.structis.fichesst.shared.security.Droits;

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

		// Init les roles de base
	/*	droits.add(Droits.ANONYMOUS);*/
	/*	Droits.add(Droits.ANONYMOUS);*/
		droits.add(Droits.anonymous);
		// Init la navigation
		// Navigation par d�faut
		mapNavigation.put(ACTION_ACCEUIL, new Ecran(new AcceuilEcran()));
//		mapNavigation.put(ACTION_ADMIN,new Ecran(new AdminEcran()));
		mapNavigation.put(Action.ACTION_ERROR,new Ecran(new ErrorEcran()));
	/*	mapNavigation.put(Action.ACTION_SYNTHESE, new Ecran(new SyntheseEcran()));*/
		/*mapNavigation.put(ACTION_ADMIN, new Ecran(new AdminEcran()));	*/		
		/*mapNavigation.put(Action.ACTION_CREATE_RISK, new Ecran(new CreateRiskEcran()));
		mapNavigation.put(Action.ACTION_RISQUE, new Ecran(new RisqueEcran()));
		mapNavigation.put(Action.ACTION_COMPE_RENDU, new Ecran(new CompteRenduEcran()));
		mapNavigation.put(Action.ACTION_RISQUEDETAIL_NCR, new Ecran(new CompteRenduEcran()));
		mapNavigation.put(Action.ACTION_RISQUEDETAIL_LIBELLERISQUE, new Ecran(new CreateRiskEcran()));
		mapNavigation.put(Action.ACTION_PROJECT_SELECT, new Ecran(new ProjectEcran()));
		mapNavigation.put(Action.ACTION_DETECTION_CREATE, new Ecran(new CreateDetectionEcran()));
		mapNavigation.put(Action.ACTION_UO_SELECT, new Ecran(new UoEcran()));
		mapNavigation.put(Action.ACTION_USER, new Ecran(new UserEcran()));
		mapNavigation.put(Action.ACTION_CREATE_PROJECT, new Ecran(new CreateProjectEcran()));
		mapNavigation.put(Action.ACTION_MANAGE_SERVICE, new Ecran(new ServiceEcran()));
		mapNavigation.put(Action.ACTION_MANAGE_REFERENCE, new Ecran(new ReferenceEcran()));
		mapNavigation.put(Action.ACTION_MANAGE_PHASE, new Ecran(new ManagePhaseEcran()));*/
	}

	/*@Override
	public void goToEcran(Action action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToEcran(Action action, NavigationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Action getActionActuelle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ApplicationContext getContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimpleEventBus getBus() {
		// TODO Auto-generated method stub
		return null;
	}
*/

}
