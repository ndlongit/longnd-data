package com.structis.vip.client.image;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;

public class ImagesHelper {
	
	private static Images images = GWT.create(Images.class);
	
	public static Image getImage(String extension){
		Image result = images.waitIcon().createImage();
		
		return result;
	}

}
