package com.structis.fichesst.client.navigation;

import com.structis.fichesst.client.ecran.EcranLoadable;

/**
 * Classe qui �ncapsule l'widget d'application, il contient le widget et le fil d'ariane
 * 
 * @author b.brotosumpeno
 *
 */
public class Ecran {
	
	/**
	 * Le widget
	 */
	private EcranLoadable ecran;
	
	/**
	 * 
	 * @param ecran � charger
	 * @param arianes les fils d'arianes
	 */
	public Ecran(EcranLoadable ecran) {
		this.ecran = ecran;
	}

	public EcranLoadable getEcran() {
		return ecran;
	}


}
