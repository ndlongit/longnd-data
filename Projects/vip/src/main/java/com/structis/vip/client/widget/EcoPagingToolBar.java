package com.structis.vip.client.widget;

import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.core.client.GWT;
import com.structis.vip.client.message.ConstantMessages;

/**
 * Class regroupant le traitement commun d'un paging toolbar utilis� pour les panels
 * @author b.brotosumpeno
 *
 */
public class EcoPagingToolBar extends PagingToolBar {
	
	private final ConstantMessages constantMessages = GWT.create(ConstantMessages.class);

	public EcoPagingToolBar(int pageSize) {
		super(pageSize);
		remove(refresh);
		String commonDisp = constantMessages.commonPagingDisplayMessages();
		getMessages().setDisplayMsg(commonDisp);
	}

}
