package com.structis.vip.client.ecran;

import static com.structis.vip.client.navigation.Action.ACTION_ACCEUIL;

import com.google.gwt.event.shared.SimpleEventBus;
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
	
	private SimpleEventBus bus = new SimpleEventBus();
	private AccueilCenterPanel center = new AccueilCenterPanel(bus);
	public AccueilEcran() {
		
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		initTab(center, ACTION_ACCEUIL);
	}

	public void onLoadApplication(NavigationEvent event) {
		//bus.fireEvent(new LoadActionEvent(null));
		//center.onLoadPanel();
		//tabSet.setSelection(tabSet.getItem(0));
	}
	
}
