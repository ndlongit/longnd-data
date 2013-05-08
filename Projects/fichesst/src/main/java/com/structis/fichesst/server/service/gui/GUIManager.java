package com.structis.fichesst.server.service.gui;

import java.util.HashMap;
import java.util.List;

public interface GUIManager {

	/**
	 * @param screenID: Id of the screen
	 * @param profile: Profile of the current user
	 * @param status: status of the risk page
	 * @return
	 */
	public HashMap<String, Boolean> getVisibilityRules(String screenID, String profile, String status);

	public HashMap<String, Boolean> getEditableRules(String screenID, String profile, String status);

	public HashMap<String, Boolean> getInputMandatoryRules(String screenID, String profile, String action);

	public HashMap<String, List<String>> getGuiVisibilityRules();

	public HashMap<String, Boolean> getVisibilityRulesMultipleRole(String screenID, String[] profile, String status);

	public HashMap<String, Boolean> getEditableRulesMultipleRole(String screenID, String[] profile, String status);
}
