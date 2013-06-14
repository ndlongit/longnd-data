package com.structis.vip.client.panel.base;

import java.util.logging.Logger;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.UserModel;

public abstract class AbstractContentPanel extends ContentPanel {
    
    protected static final Messages messages = GWT.create(Messages.class);

    protected SimpleEventBus bus;
    protected static UserModel currentUser;

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    protected AbstractContentPanel(SimpleEventBus bus) {
        this.bus = bus;
        this.setStyleAttribute("paddingBottom", "0px");
        this.setBodyBorder(true);
        this.setScrollMode(Scroll.AUTO);
    }

    @Override
    protected void onRender(Element parent, int pos) {
        GWT.log(this.getClass().getName() + ": onRender");
        logger.info(this.getClass().getName() + ": onRender");
        currentUser = SessionServiceImpl.getInstance().getUserContext();
        super.onRender(parent, pos);
    }
}
