package com.structis.vip.client.panel;

import java.util.logging.Logger;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.structis.vip.client.message.ActionMessages;
import com.structis.vip.client.message.ConstantMessages;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.UserModel;

public abstract class AbstractPanel extends LayoutContainer {

    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 480;

    protected static final Messages messages = GWT.create(Messages.class);
    protected ActionMessages actionMessages = GWT.create(ActionMessages.class);
    protected static final ConstantMessages config = GWT.create(ConstantMessages.class);

    protected Logger logger = Logger.getLogger(this.getClass().getName());
    protected NavigationService navigation = NavigationFactory.getNavigation();

    protected UserModel currentUser;
    protected SimpleEventBus bus = new SimpleEventBus();

    @Override
    protected void onRender(Element parent, int pos) {
        GWT.log(this.getClass().getName() + ": onRender");
        logger.info(this.getClass().getName() + ": onRender");
        currentUser = SessionServiceImpl.getInstance().getUserContext();
        super.onRender(parent, pos);
    }
}
