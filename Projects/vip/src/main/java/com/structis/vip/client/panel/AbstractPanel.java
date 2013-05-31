package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.structis.vip.client.message.Messages;

public abstract class AbstractPanel extends LayoutContainer {

    protected static Messages messages = GWT.create(Messages.class);
    protected SimpleEventBus bus;

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);
        GWT.log(this.getClass().getName() + ":onRender");
    }
}
