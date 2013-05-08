package com.structis.fichesst.server.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlTableStyleParser {
	public static String BORDER_BOTTOM = "BORDER-BOTTOM";
	public static String BORDER_LEFT  = "BORDER-LEFT";
	public static String PADDING_BOTTOM  = "PADDING-BOTTOM"; 
	public static String WIDTH  = "WIDTH";
	public static String BACKGROUND  = "BACKGROUND";
	public static String BACKGROUNDCOLOR  = "BACKGROUND-COLOR";
	public static String HEIGHT  = "HEIGHT";
	public static String BORDER_TOP  = "BORDER-TOP";
	public static String BORDER_RIGHT  = "BORDER-RIGHT";
	public static String PADDING_TOP  = "PADDING-TOP";
	public static List<Map<String,String>> getStyle(String inputHTML){
		/*try {
			HtmlParser.parse(document, tableContent);
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		int count = 0;
		try {
			Parser parser = new Parser();
			parser.setInputHTML(inputHTML);
			parser.setEncoding("UTF-8");    
			NodeList nl = parser.parse(null); 
			NodeList trs = nl.extractAllNodesThatMatch(new TagNameFilter("tr"),true);
			String regex = "([a-z]+) *= *\"?((?:(?! [a-z]+ *=|/? *>|\").)+)";
		    Pattern p = Pattern.compile(regex, Pattern.DOTALL);
		    for(int i=0;i<trs.size();i++) {
			    NodeList nodes = trs.elementAt(i).getChildren();
			    NodeList tds  = nodes.extractAllNodesThatMatch(new TagNameFilter("td"),true);
			    for(int j=0;j<tds.size();j++) {
			    	count++;
			    	String content = tds.elementAt(j).toHtml();
			    	Matcher fit =  p.matcher(content);
				    if (fit.find()) {
				    	String[] attributes = fit.group(2).replace("'", "").split(";");
				    	Map<String,String> mapAtt = new HashMap<String, String>();
				    	if(attributes.length > 1){
					    	for(String a:attributes){
					    		String[] attribute = a.split(":"); 
					    		mapAtt.put(attribute[0].trim().toUpperCase(), attribute[1].trim());
					    	}
				    	}
				    	result.add(mapAtt);
				    }
			    }
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static Color hex2Rgb(String colorStr) {
	    if(colorStr != null ){
	    	if(colorStr.length() >= 7 && colorStr.contains("#")){
		    	colorStr = colorStr.substring(0,7);
				return new Color(
			            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
			            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
			            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	    	}else{
	    		if(colorStr.toString().equalsIgnoreCase("black")){
	    			return Color.black;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("lightGray")){
	    			return Color.lightGray;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("blue")){
	    			return Color.blue;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("magenta")){
	    			return Color.magenta;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("cyan")){
	    			return Color.cyan;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("orange")){
	    			return Color.orange;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("darkGray")){
	    			return Color.darkGray;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("pink")){
	    			return Color.pink;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("gray")){
	    			return Color.gray;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("red")){
	    			return Color.red;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("green")){
	    			return Color.green;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("white")){
	    			return Color.white;
	    		}
	    		if(colorStr.toString().equalsIgnoreCase("yellow")){
	    			return Color.yellow;
	    		}

	    	}
	    }
	    return new Color(
	            Integer.valueOf(255),
	            Integer.valueOf(255),
	            Integer.valueOf(255));
	}
}
