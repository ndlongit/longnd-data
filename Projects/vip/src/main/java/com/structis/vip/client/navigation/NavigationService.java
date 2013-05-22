package com.structis.vip.client.navigation;

import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.vip.shared.config.ApplicationContext;

/**
 * L'interface de navigation entre �cran
 * 
 * @author b.brotosumpeno
 * 
 */
public interface NavigationService {

    /**
     * Pour naviger vers l'�cran en fontion de l'action en parametre
     * 
     * @param action
     */
    void goToEcran(Action action);

    void goToEcran(Action action, NavigationEvent event);

    Action getActionActuelle();

    void setApplicationContext(ApplicationContext context);

    ApplicationContext getContext();

    SimpleEventBus getBus();
}
