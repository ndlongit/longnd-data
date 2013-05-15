package com.structis.vip.client.util;

import com.google.gwt.core.client.JavaScriptObject;

public class JavaScriptNameValuePair extends JavaScriptObject {

	protected JavaScriptNameValuePair() {
	}

	public static native JavaScriptNameValuePair create(String name, String value) /*-{
		return {
			name : name,
			value : value
		};
	}-*/;

	public final native String getName() /*-{
		return this.name;
	}-*/;

	public final native String getValue() /*-{
		return this.value;
	}-*/;
}
