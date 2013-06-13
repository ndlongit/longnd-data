package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.UserModeEvent;
import com.structis.vip.client.event.UserModeHandler;

public class UserModePanel extends AbstractPanel {

    private Label lblUserMode;

    public UserModePanel(SimpleEventBus bus) {
        this.bus = bus;
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        initUI();

        if (currentUser != null && currentUser.isAdministrateur()) {
            lblUserMode.setText(messages.headermenuswitchdelegation());

        } else {
            lblUserMode.setText(messages.headermenuswitchadmin());
        }
        addHandler();
    }

    private void initUI() {
        setLayout(new FlowLayout());

        VerticalPanel vp = new VerticalPanel();
        vp.setHorizontalAlign(HorizontalAlignment.RIGHT);

        lblUserMode = new Label();

        HorizontalPanel hpBottom = new HorizontalPanel();
        hpBottom.setStyleAttribute("margin-right", "5px");

        hpBottom.add(lblUserMode);
        vp.add(hpBottom);

        add(vp);
    }

    private void addHandler() {
        // add handler for Filter button
        bus.addHandler(UserModeEvent.getType(), new UserModeHandler() {

            @Override
            public void onLoadAction(final UserModeEvent event) {
                lblUserMode.setText(event.getLblUserMode());
            }
        });
    }

}
