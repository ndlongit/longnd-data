package com.structis.vip.client.widget;

import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;

/**
 * Class pour binder l'evenement resize et un widget
 * @author b.brotosumpeno
 *
 */
public class WindowResizeBinder {
	
	/**
	 * Attacher un événement resize et grid
	 * @param grid à attacher
	 */
	public static void bind(final Grid<?> grid) {
		
		Window.addResizeHandler(new ResizeHandler() {
			
			public void onResize(ResizeEvent event) {
				if (null != grid
						&& null != grid.getView()
						&& null != grid.getView().getHeader()) {
					
					grid.getView().getHeader().refresh();
					grid.getView().refresh(false);
				}
			}
		});
		
	}

}
