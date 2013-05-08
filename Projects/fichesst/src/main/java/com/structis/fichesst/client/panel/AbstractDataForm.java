package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.structis.fichesst.shared.dto.AbstractDto;

public abstract class AbstractDataForm<MODEL extends AbstractDto> extends AbstractPanel {

	private MODEL dataModel;

	public MODEL getDataModel() {
		return dataModel;
	}

	public void setDataModel(MODEL dataModel) {
		this.dataModel = dataModel;
	}

	protected void bindModel(final FormPanel formPanel, final MODEL dataModel) {
		FormBinding binding = new FormBinding(formPanel);
		setDataModel(dataModel);

		//Auto binding fields
		binding.autoBind();
		binding.bind(dataModel);
		//		dataModel.addChangeListener(new ChangeListener() {
		//			public void modelChanged(ChangeEvent event) {
		//			}
		//		});
	}
}
