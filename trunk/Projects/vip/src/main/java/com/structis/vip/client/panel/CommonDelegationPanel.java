package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.vip.client.fieldset.ChantierFieldSet;
import com.structis.vip.client.fieldset.DelegantFieldSet;
import com.structis.vip.client.fieldset.DelegataireFieldSet;
import com.structis.vip.client.fieldset.DynamicFieldSet;
import com.structis.vip.client.fieldset.SocieteFieldSet;
import com.structis.vip.client.message.Messages;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.FieldRuleModel;

public abstract class CommonDelegationPanel extends FormPanel {

	protected final static int WIDTH = 700;
	protected final static int HEIGHT = -1;

	protected final Messages messages = GWT.create(Messages.class);
	protected final FormData formData = new FormData("95%");
	protected SimpleEventBus bus;
	
	protected DelegantFieldSet delegantFieldSet;
	protected DelegataireFieldSet delegataireFieldSet;
	protected SocieteFieldSet societeFieldSet;
	protected ChantierFieldSet chantierFieldSet;

	/**
	 * init field sets
	 */
	protected void initFieldSets() {
		delegantFieldSet = new DelegantFieldSet();
		addFieldSet(delegantFieldSet);

		delegataireFieldSet = new DelegataireFieldSet(bus);
		addFieldSet(delegataireFieldSet);

		societeFieldSet = new SocieteFieldSet(bus);
		addFieldSet(societeFieldSet);
		
		chantierFieldSet = new ChantierFieldSet(bus);
		addFieldSet(chantierFieldSet);
	}

	private void addFieldSet(DynamicFieldSet fieldSet) {
		if (fieldSet != null) {
			fieldSet.collapse();
			this.add(fieldSet, formData);
		}
	}

	protected static void resetFieldSets(DynamicFieldSet... fieldSets) {
		for (DynamicFieldSet fieldSet : fieldSets) {
			if (fieldSet != null) {
				fieldSet.setVisible(false);
				fieldSet.invisibleAllFields();
			}
		}
	}

	protected void processFieldRules(final DelegationModel delegationModel, List<FieldRuleModel> fieldRules) {
		CollaborateurModel collaborateurModel = delegationModel.getDelegant();
		
		delegantFieldSet.applyInformation(delegationModel, collaborateurModel);

		for (Field<?> field : getFields()) {
			Object groupName = field.getData(DynamicFieldSet.PROP_GROUP_NAME);
			for (FieldRuleModel fieldRuleModel : fieldRules) {
				if (field.getId() == null) {
					continue;
				}

				// Because field IDs in different groups can be duplicated, so if a group name is specified, compare by both ID and group name
				if (field.getId().equals(fieldRuleModel.getField().getFormFieldId())) {
					if (groupName != null && groupName.toString().trim() != "") {
						String dbGroupName = fieldRuleModel.getField().getGroup();
						if (groupName.toString().equalsIgnoreCase(dbGroupName)) {
							setProperties(field, fieldRuleModel);
							break;
						}
					} else {
						
						// compare by id only
						setProperties(field, fieldRuleModel);
						break;
					}
				}
			}
		}
		processSpecificFields(delegationModel);
	}

	protected void setProperties(Field<?> field, FieldRuleModel fieldRuleModel) {
		field.setVisible((1 == fieldRuleModel.getIsDisplayed().intValue()));
		field.setData("visible", "" + fieldRuleModel.getIsDisplayed().intValue());
		field.setFieldLabel(fieldRuleModel.getField().getLabel());

		if (field instanceof TextField<?>) {
			TextField<?> txtField = (TextField<?>) field;
			txtField.setAllowBlank((1 != fieldRuleModel.getIsRequired().intValue()));
		}
	}

	protected boolean isCirmad(String selectedItem) {
		return (selectedItem != null && selectedItem.toUpperCase().startsWith("CIRMAD"));
	}
	
	protected abstract void processSpecificFields(DelegationModel delegationModel);
}
