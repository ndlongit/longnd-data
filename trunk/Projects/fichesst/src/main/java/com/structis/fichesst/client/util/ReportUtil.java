package com.structis.fichesst.client.util;


import com.google.gwt.core.client.JavaScriptObject;

public class ReportUtil {

    private ReportUtil() {
    }
   
    public static void showReport(String action, NameValuePair[] values) {
    	try {
    		openReportWindow(action, JavaScriptObjectHelper.convertToJavaScriptArray(values));
    	} catch (Exception e) {
			// TODO: handle exception
		}
    }
   
    private static native void openReportWindow(String action, JavaScriptObject values) /*-{
 
        var form = document.createElement("form");
        form.setAttribute("method", "post");
       	form.setAttribute("action", action);
        // setting form target to a window named 'formresult'
        form.setAttribute("target", "_blank");

        for (var i=0; i<values.length; i++) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("name", values[i].name);
            hiddenField.setAttribute("value",values[i].value);
            form.appendChild(hiddenField);
        }
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild("form");
    }-*/;
    
    public static void downloadFile(String action) {
    	try {
    		openFileWindow(action);
    	} catch (Exception e) {
			// TODO: handle exception
		}
    }
   
    private static native void openFileWindow(String action) /*-{
        var form = document.createElement("form");
        form.setAttribute("method", "get");
        form.setAttribute("action", action);

        // setting form target to a window named 'formresult'
        form.setAttribute("target", "_blank");

        document.body.appendChild(form);

        form.submit();
        document.body.removeChild("form");
    }-*/;

}
