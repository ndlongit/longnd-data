package com.structis.fichesst.client.navigation;

import java.util.Map;

public class ItemFilAriane {

	private Action action;
	
	private Map<String, String> parametres;

	public ItemFilAriane(Action action, Map<String, String> parametres) {
		super();
		this.action = action;
		this.parametres = parametres;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Map<String, String> getParametres() {
		return parametres;
	}

	public void setParametres(Map<String, String> parametres) {
		this.parametres = parametres;
	}

	@Override
	public String toString() {
		return "ItemFilAriane [action=" + action + "]";
	}
	
}
