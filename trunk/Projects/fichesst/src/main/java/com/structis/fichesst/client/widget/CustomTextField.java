package com.structis.fichesst.client.widget;

import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;

public class CustomTextField<D> extends TextField<D> {
	
	public CustomTextField() {
		super();
		
		//default maxLength
		setMaxLength(50);
	}

	@Override
	public void setMaxLength(int max) {
		super.setMaxLength(max);
		if( rendered ) {
			getInputEl().setElementAttribute("maxLength", max);
		}
	}

	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
		getInputEl().setElementAttribute("maxLength", getMaxLength());
	}
}
