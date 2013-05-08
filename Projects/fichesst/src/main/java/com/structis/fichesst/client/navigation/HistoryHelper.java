package com.structis.fichesst.client.navigation;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.gwt.user.client.History;

public class HistoryHelper {
	
	private static Stack<ItemFilAriane> items = new Stack<ItemFilAriane>();
	
	public static void newItem(String item){
		History.newItem(item);
	}
	
	public static void newItem(String item, Map<String, String> parameters) {
		newItem(item, parameters, false);
	}
	
	public static void newItem(String item, String key, String value,
			boolean issueEvent) {
		StringBuffer buffer = new StringBuffer(item);
		buffer.append("&").append(key).append("=").append(value);
		History.newItem(buffer.toString(), issueEvent);
	}

	public static void newItem(String item, Map<String, String> parameters, boolean issueEvent){
		StringBuffer buffer = new StringBuffer(item);
		if (null != parameters) {
			Set<String> keys = parameters.keySet();
			for (String key : keys) {
				buffer.append("&").append(key).append("=").append(parameters.get(key));
			}
		}
		History.newItem(buffer.toString(), issueEvent);
	}

	public static Stack<ItemFilAriane> getItems() {
		return items;
	}
	
	public static void pushItem(Action action, Map<String, String> parameters) {
		ItemFilAriane item = new ItemFilAriane(action, parameters);
		items.push(item);
	}
	
	public static void clearItems(){
		items.clear();
	}
	
	public static boolean isEmpty(){
		return items.isEmpty();
	}

}
