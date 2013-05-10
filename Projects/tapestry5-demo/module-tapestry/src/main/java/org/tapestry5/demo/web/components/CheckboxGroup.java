package org.tapestry5.demo.web.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.base.AbstractField;
import org.springframework.util.Assert;

public class CheckboxGroup extends AbstractField {

    @Parameter
    private List<OptionModel> _model = new ArrayList<OptionModel>();

    @Parameter(required = true)
    private List<Object> _values = new ArrayList<Object>();

    @Parameter(value = "false")
    private boolean _selectAll;

    @Parameter
    @Property
    private OptionModel _item;

    @Persist
    private List<OptionModel> _savedModel;

    @Persist
    private List<Object> _savedValues;

    @SetupRender
    public void setupRender() {
        _savedModel = _model;
        _savedValues = new ArrayList<Object>();

        if (_selectAll) {
            for (OptionModel item : _model) {
                _savedValues.add(item.getValue());
            }
        } else {
            if (getValues() != null) {
                for (Object value : getValues()) {
                    _savedValues.add(value);
                }
            }
        }
    }

    public List<Object> getValues() {
        return _values;
    }

    public void setValues(List<Object> values) {
        _values = values;
    }

    public boolean isSelected() {
        return _savedValues.contains(_item.getValue());
    }

    public void setSelected(boolean selected) {
        Object value = null;
        if (_savedModel == null) {
            return;
        }
        for (OptionModel item : _savedModel) {
            if (item.getValue().toString().equals(_item.getValue().toString())) {
                value = item.getValue();
                break;
            }
        }
        Assert.notNull(value);

        if (selected) {
            if (!_savedValues.contains(value)) {
                _savedValues.add(value);
            }
        } else {
            _savedValues.remove(value);
        }

        setValues(_savedValues);
    }

    public List<OptionModel> getModel() {
        return _model;
    }

    public void setModel(List<OptionModel> model) {
        _model = model;
    }

    @Override
    protected void processSubmission(String elementName) {
    }
}
