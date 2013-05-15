package com.structis.vip.client.constant;

import java.util.Date;

import com.google.gwt.user.client.Window;

public class Helper {

	  public static boolean isExplorer() {
		    String test = Window.Location.getPath();
		    if (test.indexOf("pages") != -1) {
		      return false;
		    }
		    return true;
	  }
	  
	@SuppressWarnings("deprecation")
	public static Date getBYEFEEndDate() {
		//set to 31-may
		Date cal = new Date();		
		cal.setMonth(4);
		cal.setDate(31);
		if (cal.before(new Date())) {
			cal.setYear(cal.getYear()+1);
		}
		return cal;		  
	}
}
