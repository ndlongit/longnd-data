package com.structis.vip.client.util;

public class ParserResponseUtil {
	public static String parseResponseString(String str) {
		String strReturn = str;
		strReturn = strReturn.replace("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">", "");
		strReturn = strReturn.replace("<pre>", "");
		strReturn = strReturn.replace("</pre>", "");
		return strReturn;
	}
}
