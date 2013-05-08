package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.structis.fichesst.shared.dto.PenaltyDto;

public class AddPenaltyForm extends AbstractDataForm<PenaltyDto> {

	private static final String ANCHOR_SPEC = "100%";

	public AddPenaltyForm() {
		final FormPanel formPanel = new FormPanel();
		formPanel.setBorders(false);
		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		FormLayout fl_formPanel = new FormLayout();
		fl_formPanel.setLabelWidth(100);
		formPanel.setLayout(fl_formPanel);

		DateField date = new DateField();
		date.setName(PenaltyDto.DATE);
		date.setFieldLabel(messages.date());
		formPanel.add(date, new FormData(ANCHOR_SPEC));

		NumberField amount = createNumberField(messages.amount());
		amount.setName(PenaltyDto.AMOUNT);
		formPanel.add(amount, new FormData(ANCHOR_SPEC));

		TextArea comment = new TextArea();
		comment.setFieldLabel(messages.comment());
		comment.setName(PenaltyDto.COMMENT);
		formPanel.add(comment, new FormData(ANCHOR_SPEC));

		add(formPanel);

		PenaltyDto dataModel = new PenaltyDto();
		dataModel.initData();
		bindModel(formPanel, dataModel);

		setDefaultBackgroundColor();
	}
}
