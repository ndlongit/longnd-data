package com.structis.fichesst.server.service.gui;

import java.util.ArrayList;
import java.util.List;

public class GUIRule {

	private String componentID;

	private String status;

	private List<String> profiles;

	public GUIRule(String componentID, String status, List<String> profiles) {
		this.componentID = componentID;
		this.status = status;
		this.profiles = profiles;

	}

	public GUIRule(String componentID, String status) {
		this.componentID = componentID;
		this.status = status;
		this.profiles = new ArrayList<String>();

	}

	public String getComponentID() {
		return componentID;
	}

	public void setComponentID(String componentID) {
		this.componentID = componentID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}
}
