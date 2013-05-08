package com.structis.fichesst.client.navigation;

import java.util.HashMap;
import java.util.Map;

public class ActionHelper {
	
	private static Map<String, Action> map = new HashMap<String, Action>();
	
	static {
		for(int i=0; i< Action.values().length; i++) {
			String label = Action.values()[i].getLabel();
			map.put(label, Action.values()[i]);
		}
	}
	
	public static Action getActionFromLabel(String label){
		return map.get(label);
	}
}
