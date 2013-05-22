package com.structis.vip.client.ecran;

import com.google.gwt.user.client.ui.IsWidget;
import com.structis.vip.client.navigation.NavigationEvent;

/**
 * Interface qui doit �tre pour tous les �crans de l'application Il fournit la m�thode {@link EcranLoadable#onLoadApplication()} qui est appel� chaque
 * fois l'�cran est affich�
 * 
 * @author b.brotosumpeno
 * 
 */
public interface EcranLoadable extends IsWidget {

    /**
     * La m�thode appel� pendant le chargement de l'�cran
     */
    public abstract void onLoadApplication(NavigationEvent event);
}
