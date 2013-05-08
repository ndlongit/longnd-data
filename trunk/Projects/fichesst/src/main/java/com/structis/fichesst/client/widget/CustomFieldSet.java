package com.structis.fichesst.client.widget;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.util.Constants;

public class CustomFieldSet extends FieldSet {

    protected static final String DEFAULT_BACKGROUD_COLOR = Constants.DEFAULT_BACKGROUD_COLOR;

    private static void setBackgroundColor(Component c, String color) {
	c.setStyleAttribute("background-color", color);
    }

    public CustomFieldSet() {
	super();
	setCollapsible(true);
	setDefaultBackgroundColor();
	setWidth(GuiUtil.getScreenWidth() - 60);
    }

    private void setDefaultBackgroundColor() {
	setBackgroundColor(this, DEFAULT_BACKGROUD_COLOR);
    }

    @Override
    public void setHeading(String text) {
	if (text != null) {
	    text = text.toUpperCase();
	}
	super.setHeading(text);
    }
}
