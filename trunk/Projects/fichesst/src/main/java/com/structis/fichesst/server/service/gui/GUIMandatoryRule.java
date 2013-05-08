package com.structis.fichesst.server.service.gui;

import java.util.ArrayList;
import java.util.List;

public class GUIMandatoryRule {

	private String componentID;

	private String action;

	private List<String> profiles;

	public GUIMandatoryRule(String componentID, String action, List<String> profiles) {
		this.componentID = componentID;
		this.action = action;
		this.profiles = profiles;
	}

	public GUIMandatoryRule(String componentID, String action) {
		this.componentID = componentID;
		this.action = action;
		this.profiles = new ArrayList<String>();

	}

	public String getComponentID() {
		return componentID;
	}

	public void setComponentID(String componentID) {
		this.componentID = componentID;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<String> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}
}