package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.structis.fichesst.client.message.Messages;
import com.structis.fichesst.shared.dto.ProgressDto;

public class AddAvancementForm extends AbstractDataForm<ProgressDto> {
	
	private static final String ANCHOR_SPEC = "100%";
	
	private static Messages	 messages	= GWT.create(Messages.class);
	
	public AddAvancementForm() {
		final FormPanel formPanel = new FormPanel();
		formPanel.setBorders(false);
		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		FormLayout fl_formPanel = new FormLayout();
		fl_formPanel.setLabelWidth(150);
		formPanel.setLayout(fl_formPanel);
		
		TextField<String> label = new TextField<String>();
		label.setMaxLength(MAX_LENGTH_1);
		label.setName(ProgressDto.LABEL);
		label.setFieldLabel(messages.label());
		formPanel.add(label, new FormData(ANCHOR_SPEC));
		
		DateField date = new DateField();
		date.setName(ProgressDto.DATE);
		date.setFieldLabel(messages.date());
		formPanel.add(date, new FormData(ANCHOR_SPEC));
		
		NumberField cumule1 = createNumberField(messages.cumule() + " avancement");
		cumule1.setName(ProgressDto.CUMULE);
		formPanel.add(cumule1, new FormData(ANCHOR_SPEC));
		
		NumberField cumule2 = createNumberField(messages.cumule() + " reteneus");
		cumule2.setName(ProgressDto.CUMULE2);
		formPanel.add(cumule2, new FormData(ANCHOR_SPEC));
		
		add(formPanel);
		
		ProgressDto dataModel = new ProgressDto();
		bindModel(formPanel, dataModel);
		
		setDefaultBackgroundColor();
	}
}
