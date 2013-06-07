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
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.UserModel;

public class UserModePanel extends AbstractPanel {

	Label lblUserMode;

	private UserModel userModel;
	private SimpleEventBus bus;

	public UserModePanel(SimpleEventBus bus) {
		this.bus = bus;
		this.userModel = SessionServiceImpl.getInstance().getUserContext();
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		this.initUI();

		if (this.userModel.isAdministrateur()) {
			lblUserMode.setText(messages.headermenuswitchdelegation());

		} else {
			lblUserMode.setText(messages.headermenuswitchadmin());
		}
		this.addHandler();

	}

	private void initUI() {
		this.setLayout(new FlowLayout());

		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.RIGHT);

		this.lblUserMode = new Label();

		HorizontalPanel hpBottom = new HorizontalPanel();
		hpBottom.setStyleAttribute("margin-right", "5px");

		hpBottom.add(this.lblUserMode);
		vp.add(hpBottom);

		this.add(vp);
	}

	private void addHandler() {
		// add handler for Filter button
		this.bus.addHandler(UserModeEvent.getType(), new UserModeHandler() {

			@Override
			public void onLoadAction(final UserModeEvent event) {
				lblUserMode.setText(event.getLblUserMode());
			}
		});

	}

}
