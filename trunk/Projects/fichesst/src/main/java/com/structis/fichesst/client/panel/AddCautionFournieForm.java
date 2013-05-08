package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.structis.fichesst.shared.dto.CautionFournieDto;

public class AddCautionFournieForm extends AbstractDataForm<CautionFournieDto> {
	
	private static final String ANCHOR_SPEC = "100%";
	
	public AddCautionFournieForm() {
		final FormPanel formPanel = new FormPanel();
		formPanel.setBorders(false);
		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		FormLayout fl_formPanel = new FormLayout();
		fl_formPanel.setLabelWidth(100);
		formPanel.setLayout(fl_formPanel);
		
		DateField date = new DateField();
		date.setName(CautionFournieDto.DATE);
		date.setFieldLabel(messages.date());
		formPanel.add(date, new FormData(ANCHOR_SPEC));
		
		NumberField amount = createNumberField(messages.amount());
		amount.setName(CautionFournieDto.AMOUNT);
		formPanel.add(amount, new FormData(ANCHOR_SPEC));
		
		add(formPanel);
		
		CautionFournieDto dataModel = new CautionFournieDto();
		bindModel(formPanel, dataModel);
		
		setDefaultBackgroundColor();
	}
}
