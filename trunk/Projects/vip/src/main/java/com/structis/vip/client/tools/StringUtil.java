package com.structis.vip.client.tools;

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
        float result = (width * percent) / 100;
        return result;
    }

    public static float getHeightPerCentValue(int percent, int height) {
        float result = (height * percent) / 100;
        return result;
    }

    public static int getWidthPerCentValueInt(int percent, int width) {
        int result = (width * percent) / 100;
        return result;
    }

    public static int getHeightPerCentValueInt(int percent, int height) {
        int result = (height * percent) / 100;
        return result;
    }
}
