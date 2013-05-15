package com.structis.vip.client.tools;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	public static native String getAvailHeight() /*-{
		var screenHeight = screen.availHeight + "";
		return screenHeight;
	}-*/;

	public static native String getAvailWidth() /*-{
		var screenWidth = screen.availWidth + "";
			return screenWidth;
	}-*/;

	public static float getWidthPerCentValue(int percent, int width) {
		float result = (width*percent)/100;
		return result;
	}

	public static float getHeightPerCentValue(int percent, int height) {
		float result = (height*percent)/100;
		return result;
	}
	
	public static int getWidthPerCentValueInt(int percent, int width) {
		int result = (width*percent)/100;
		return result;
	}

	public static int getHeightPerCentValueInt(int percent, int height) {
		int result = (height*percent)/100;
		return result;
	}
	
	
	public static void main(String[] args) {/*
		List<DataTranferDTO> list = new ArrayList<DataTranferDTO>(); 
		for (int i = 0; i < 10; i++) {
			int result = 1;
			if(!list.contains(result)){
				DataTranferDTO rection = new DataTranferDTO("1","1");
				list.add(rection);
			}
		}
		System.out.println(list.size());
		
		
	*/}
}
