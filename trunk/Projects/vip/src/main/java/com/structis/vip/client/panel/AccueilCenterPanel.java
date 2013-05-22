package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.vip.client.message.Messages;

public class AccueilCenterPanel extends FormPanel {

    private final Messages messages = GWT.create(Messages.class);
    @SuppressWarnings("unused")
    private SimpleEventBus bus;

    public AccueilCenterPanel(SimpleEventBus bus) {
        this.bus = bus;
        this.buildPanel();
    }

    private void buildPanel() {
        this.setHeaderVisible(false);
        this.setBodyBorder(false);
        this.setBorders(false);
        this.setFrame(false);
        this.setLabelAlign(LabelAlign.TOP);

        LayoutContainer top = new LayoutContainer();
        Label label = new Label("Home Page");
        top.add(label);
        top.setStyleAttribute("padding-bottom", "15px");
        top.setStyleAttribute("text-align", "center");
        top.setStyleAttribute("color", "#006BB4");
        top.setStyleAttribute("font-family", "sans-serif");
        top.setStyleAttribute("font-weight", "bold");
        this.add(top);

    }

    public void onLoadPanel() {

    }

}
