package com.structis.fichesst.client.navigation;

import static com.structis.fichesst.client.navigation.Action.ACTION_ACCEUIL;

import com.structis.fichesst.client.ecran.AcceuilEcran;
import com.structis.fichesst.client.ecran.ErrorEcran;
import com.structis.fichesst.shared.security.Droits;

/**
 * Classe implementation de navigation service Voir {@link NavigationService}
 * 
 * @author b.brotosumpeno
 * 
 */
public class NavigationManager extends NavigationAbstract implements NavigationService {

	protected NavigationManager() {
	}

	@Override
	protected void initNavigation() {

		// Init les roles de base
		/* droits.add(Droits.ANONYMOUS); */
		/* Droits.add(Droits.ANONYMOUS); */
		droits.add(Droits.anonymous);
		// Init la navigation
		// Navigation par d�faut
		mapNavigation.put(ACTION_ACCEUIL, new Ecran(new AcceuilEcran()));
		// mapNavigation.put(ACTION_ADMIN,new Ecran(new AdminEcran()));
		mapNavigation.put(Action.ACTION_ERROR, new Ecran(new ErrorEcran()));

	}

}
