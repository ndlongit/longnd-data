package com.structis.vip.client.ecran;

import static com.structis.vip.client.navigation.Action.ACTION_ACCEUIL;

import com.google.gwt.user.client.Element;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.panel.AccueilCenterPanel;

/**
 * L'�cran d'acceuil
 * 
 * @author b.brotosumpeno
 * 
 */
public class AccueilEcran extends AbstractTabEcran implements EcranLoadable {

    private AccueilCenterPanel center = new AccueilCenterPanel(this.bus);

    public AccueilEcran() {
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        this.initTab(this.center, ACTION_ACCEUIL);
    }

    @Override
    public void onLoadApplication(NavigationEvent event) {
    }
}
