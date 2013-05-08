package com.structis.fichesst.client.widget;

public class ComboToolTipTemplate {
	public static native String getStatutTemplate() /*-{
	return [
			'<tpl for=".">',
			'<div class="x-combo-list-item" title="{LABEL}" >{LABEL}</div>',
			'</tpl>' ].join("");
	}-*/;
}
