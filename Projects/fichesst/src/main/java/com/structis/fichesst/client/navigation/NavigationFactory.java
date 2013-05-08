package com.structis.fichesst.client.navigation;

/**
 * Classe de fabrication de NavigationManager
 * 
 * @author b.brotosumpeno
 *
 */
public class NavigationFactory {
	
	private static NavigationManager instance = new NavigationManager();

	public static NavigationService getNavigation() {
		return instance;
	}

}
