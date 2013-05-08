package com.structis.fichesst.client.widget;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.structis.fichesst.client.message.Messages;
import com.structis.fichesst.shared.dto.SimpleDto;

public class CustomComboBox<M extends BaseModel> extends ComboBox<M> {

	private static Messages messages = GWT.create(Messages.class);

	public CustomComboBox() {
		super();
		setLabelSeparator(":");
		setEditable(false);
		setAllowBlank(false);
		setTriggerAction(TriggerAction.ALL);

		setEmptyText(messages.select());
		setDisplayField(SimpleDto.LABEL);
		setValueField(SimpleDto.ID);
	}

	@Override
	public void focus() {
		super.triggerBlur(null);
		super.focus();
	}
}
