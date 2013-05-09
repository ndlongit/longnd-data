package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.structis.fichesst.client.message.Messages;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.shared.dto.DeductionDto;

public class AddAccomptesForm extends AbstractDataForm<DeductionDto> {

	private static Messages messages = GWT.create(Messages.class);

	private static final String ANCHOR_SPEC = "100%";

	private final FormPanel formPanel;

	public AddAccomptesForm() {
		formPanel = new CustomFormPanel();
		FormLayout fl_formPanel = new FormLayout();
		fl_formPanel.setLabelWidth(110);
		formPanel.setLayout(fl_formPanel);

		DateField date = new DateField();
		date.setName(DeductionDto.DATE);

		date.setFieldLabel(messages.date());
		formPanel.add(date, new FormData(ANCHOR_SPEC));

		NumberField canto = createIntegerField(messages.canto());
		canto.setName(DeductionDto.CANTO);
		formPanel.add(canto, new FormData(ANCHOR_SPEC));

		NumberField badge = createIntegerField(messages.badge());
		badge.setName(DeductionDto.BADGE);
		formPanel.add(badge, new FormData(ANCHOR_SPEC));

		NumberField grue = createIntegerField(messages.grue());
		grue.setName(DeductionDto.GRUE);
		formPanel.add(grue, new FormData(ANCHOR_SPEC));

		NumberField lift = createIntegerField(messages.lift());
		lift.setName(DeductionDto.LIFT);
		formPanel.add(lift, new FormData(ANCHOR_SPEC));

		NumberField benne = createIntegerField(messages.benne());
		benne.setName(DeductionDto.BENNE);
		formPanel.add(benne, new FormData(ANCHOR_SPEC));

		NumberField nettoyage = createIntegerField(messages.nettoyage());
		nettoyage.setName(DeductionDto.NETTOYAGE);
		formPanel.add(nettoyage, new FormData(ANCHOR_SPEC));

		NumberField autres = createNumberField(messages.autres());
		autres.setName(DeductionDto.AUTRES);
		formPanel.add(autres, new FormData(ANCHOR_SPEC));

		NumberField prorata = createNumberField(messages.prorata() + " (%)");
		prorata.setName(DeductionDto.PRORATA);
		prorata.setMinValue(0);
		// prorata.setMaxValue(100);
		formPanel.add(prorata, new FormData(ANCHOR_SPEC));

		NumberField refacturations = createNumberField(messages.refacturations());
		refacturations.setName(DeductionDto.REFACTURATIONS);
		formPanel.add(refacturations, new FormData(ANCHOR_SPEC));
		add(formPanel);

		DeductionDto dataModel = new DeductionDto();
		dataModel.initData();
		bindModel(formPanel, dataModel);

		setDefaultBackgroundColor();
	}

	public boolean isValid() {
		return formPanel != null && formPanel.isValid();
	}
}
