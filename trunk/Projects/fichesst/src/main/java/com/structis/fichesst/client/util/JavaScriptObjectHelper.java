package com.structis.fichesst.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JavaScriptObjectHelper {

	public static JavaScriptObject convertToJavaScriptArray(NameValuePair[] values) {
		JsArray<JavaScriptNameValuePair> result = JavaScriptObject.createArray().cast();
		for (int i = 0; i < values.length; i++) {
			GWT.log(values[i].getName()+"="+values[i].getValue());
			result.set(i, JavaScriptNameValuePair.create(values[i].getName(), values[i].getValue()));
		}
		return result;
	}

}
