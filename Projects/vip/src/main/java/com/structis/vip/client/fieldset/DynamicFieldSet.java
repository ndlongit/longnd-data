package com.structis.vip.client.fieldset;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.message.Messages;

public abstract class DynamicFieldSet extends FieldSet {

    public static final String PROP_GROUP_NAME = "groupName";

    protected static final Messages messages = GWT.create(Messages.class);
    protected SimpleEventBus bus;
    private final FormData formData = new FormData("95%");

    public DynamicFieldSet() {
        this(null, null);
    }

    public DynamicFieldSet(SimpleEventBus bus, String heading) {
        this.bus = bus;
        this.setHeading(heading);
        this.setCollapsible(true);
        this.setWidth(683);

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(200);
        layout.setLabelAlign(LabelAlign.LEFT);
        this.setLayout(layout);
    }

    protected TextField<String> addTextField(String id, String groupName) {
        TextField<String> textField = new TextField<String>();
        this.addFieldToForm(textField, id, groupName);
        textField.setMaxLength(80);
        return textField;
    }

    protected LabelField addLabelField(String id, String groupName) {
        LabelField field = new LabelField();
        this.addFieldToForm(field, id, groupName);
        return field;
    }

    private void addFieldToForm(Field<?> field, String id, String group) {
        if (field != null) {
            field.setId(id);
            field.setFieldLabel(ConstantClient.EMPTY);
            field.setData(PROP_GROUP_NAME, group);
        }
        super.add(field, this.formData);
    }

    /**
     * invisible all fields in this
     */
    public void invisibleAllFields() {
        for (Component field : this.getItems()) {
            field.setVisible(false);
        }
    }
}
