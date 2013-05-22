package com.structis.vip.server.core;

/**
 * L'interface utilis� pour l'appel callBack de manager
 * 
 * @author b.brotosumpeno
 * 
 */
public interface ManagerCallBack {

    /**
     * Cette methode doit contenir l'appel au manager
     * 
     * @param inputs
     *            les donn�es mapp�s pour l'appel manager
     * @return le resultat de l'appel manager
     */
    Object execute(Object... inputs);
}
