package com.structis.vip.client.util;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;

public class ReportUtil {

    private ReportUtil() {
    }
   
    public static void showReport(String action, NameValuePair[] values) {
        openReportWindow(action, JavaScriptObjectHelper.convertToJavaScriptArray(values));
    }
    
    public static void submitMultipart(String action, NameValuePair[] values) {
    	submitMultipart(action, JavaScriptObjectHelper.convertToJavaScriptArray(values));
    }
   
    private static native void openReportWindow(String action, JavaScriptObject values) /*-{
    	try {
	        var form = document.createElement("form");
	        form.setAttribute("method", "post");
	        form.setAttribute("action", action);
	
	        // setting form target to a window named 'formresult'
	        form.setAttribute("target", "_blank");
	
	        for (var i=0; i<values.length; i++) {
	            var hiddenField = document.createElement("input");
	            hiddenField.setAttribute("name", values[i].name);
	            hiddenField.setAttribute("value", values[i].value);
	            form.appendChild(hiddenField);
	        }
	
	        document.body.appendChild(form);
	
	        form.submit();
	        document.body.removeChild("form");
    	} catch (e) {}
    }-*/;
    
    private static native void submitMultipart(String action, JavaScriptObject values) /*-{
		try {
	        var form = document.createElement("form");
	        form.setAttribute("enctype", "multipart/form-data");
	        form.setAttribute("method", "post");
	        form.setAttribute("action", action);
	
	        for (var i=0; i<values.length; i++) {
	            var hiddenField = document.createElement("input");
	            hiddenField.setAttribute("name", values[i].name);
	            hiddenField.setAttribute("value", values[i].value);
	            form.appendChild(hiddenField);
	        }
	
	        document.body.appendChild(form);
	
	        form.submit();
	        document.body.removeChild("form");
		} catch (e) {}
	}-*/;

    /**
	  * Opens a new windows with a specified URL..
	  * 
	  * @param name String with the name of the window.
	  * @param url String with your URL.
	  */
	  public static void openNewWindow(String name, String url, List<NameValuePair> values) {
		  StringBuffer params = new StringBuffer();
		  if (values != null && !values.isEmpty()) {
			  for (NameValuePair i : values) {
				  params.append(i.name + "=" + i.value + "&");
			  }
		  }
		  if (!"".equalsIgnoreCase(params.toString())) {
			  url += "?" + params.toString();
		  }
	      com.google.gwt.user.client.Window.open(url, name.replace(" ", "_"),
	    		 "height=700, width=800," +
	             "menubar=no," + 
	             "location=no," + 
	             "resizable=no," + 
	             "scrollbars=yes," + 
	             "status=no");
	  }
}
