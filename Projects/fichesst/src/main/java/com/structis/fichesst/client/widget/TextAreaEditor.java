package com.structis.fichesst.client.widget;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.google.gwt.event.dom.client.KeyCodes;

public class TextAreaEditor extends CellEditor {

	public TextAreaEditor(TextArea field) {
		super(field);
	}

	//	@Override
	//	protected void doAutoSize() {
	//		setSize(this.getBoundEl().getWidth(), this.getHeight());
	//	}

	@Override
	protected void onSpecialKey(FieldEvent fe) {
		int key = fe.getKeyCode();

		if( key == KeyCodes.KEY_ENTER ) {
			//Do Nothing
		}
		else if( this.isCancelOnEsc() && key == KeyCodes.KEY_ESCAPE ) {
			cancelEdit();
		}
		else {
			fireEvent(Events.SpecialKey, fe);
		}
	}

	@Override
	public Object preProcessValue(Object value) {
		return processValue(value);
	}

	@Override
	public Object postProcessValue(Object value) {
		return processValue(value);
	}

	private static String processValue(Object value) {
		if( value == null ) {
			return "";
		}

		String value2 = value.toString().replace("<br>", "\n");
		return value2;

	}

	//Hack to get super.boundEl since its private
	protected native El getBoundEl()/*-{
		return this.@com.extjs.gxt.ui.client.widget.Editor::boundEl;
	}-*/;
}
