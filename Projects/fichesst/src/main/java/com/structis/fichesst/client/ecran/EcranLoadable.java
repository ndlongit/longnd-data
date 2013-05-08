package com.structis.fichesst.client.ecran;

import com.google.gwt.user.client.ui.IsWidget;
import com.structis.fichesst.client.navigation.NavigationEvent;

public interface EcranLoadable extends IsWidget {

	public abstract void onLoadApplication(NavigationEvent event);

}
