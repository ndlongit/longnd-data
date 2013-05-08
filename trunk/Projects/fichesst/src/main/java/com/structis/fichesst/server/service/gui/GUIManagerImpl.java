package com.structis.fichesst.server.service.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("guiManager")
public class GUIManagerImpl implements GUIManager {

	private HashMap<String, List<String>> guiVisibilityRules;

	private HashMap<String, List<String>> guiEditableRules;

	private HashMap<String, List<String>> guiInputMandarotyRules;

	@Override
	public HashMap<String, Boolean> getVisibilityRules(String screenID, String profile, String status) {

		List<String> rules = this.getGuiVisibilityRules().get(screenID);

		HashMap<String, Boolean> ret = parseGuiRules(profile, status, rules);
		return ret;
	}

	@Override
	public HashMap<String, Boolean> getVisibilityRulesMultipleRole(String screenID, String[] profile, String status) {

		List<String> rules = this.getGuiVisibilityRules().get(screenID);

		HashMap<String, Boolean> ret = parseGuiRulesMultipleRole(profile, status, rules);
		return ret;
	}

	@Override
	public HashMap<String, Boolean> getEditableRules(String screenID, String profile, String status) {

		List<String> rules = this.getGuiEditableRules().get(screenID);

		HashMap<String, Boolean> ret = parseGuiRules(profile, status, rules);
		return ret;
	}

	@Override
	public HashMap<String, Boolean> getEditableRulesMultipleRole(String screenID, String[] profile, String status) {

		List<String> rules = this.getGuiEditableRules().get(screenID);

		HashMap<String, Boolean> ret = parseGuiRulesMultipleRole(profile, status, rules);
		return ret;
	}

	@Override
	public HashMap<String, Boolean> getInputMandatoryRules(String screenID, String profile, String action) {
		List<String> rules = this.getGuiInputMandarotyRules().get(screenID);

		HashMap<String, Boolean> ret = parseMandatoryGuiRules(action, rules);
		return ret;
	}

	private HashMap<String, Boolean> parseGuiRules(String profile, String status, List<String> rules) {
		HashMap<String, Boolean> ret = new HashMap<String, Boolean>();

		if( rules == null ) {
			return ret;
		}

		for( String rule : rules ) {

			String[] elements = rule.split("\t");
			if( elements.length < 2 ) {
				continue;
			}

			String componentID = elements[0];
			String objectStatus = elements[1];

			List<String> profiles = new ArrayList<String>();

			if( elements.length > 2 ) {
				profiles = Arrays.asList(elements[2].split("\\|"));
			}

			if( ret.get(componentID) == null ) {
				ret.put(componentID, false);
			}

			if( status.equalsIgnoreCase(objectStatus) && profiles.contains(profile) ) {
				ret.put(componentID, true);
			}
		}
		return ret;
	}

	private HashMap<String, Boolean> parseMandatoryGuiRules(String action, List<String> rules) {
		HashMap<String, Boolean> ret = new HashMap<String, Boolean>();

		if( rules == null ) {
			return ret;
		}

		for( String rule : rules ) {

			String[] elements = rule.split("\t");
			if( elements.length < 2 ) {
				continue;
			}

			String componentID = elements[0];
			String act = elements[1];

			if( action.equalsIgnoreCase(act) ) {
				ret.put(componentID, true);
			}
		}
		return ret;
	}

	private HashMap<String, Boolean> parseGuiRulesMultipleRole(String[] profile, String status, List<String> rules) {

		HashMap<String, Boolean> ret = new HashMap<String, Boolean>();

		List<String> listProfiles = Arrays.asList(profile);

		if( rules == null ) {
			return ret;
		}

		for( String rule : rules ) {

			String[] elements = rule.split("\t");
			if( elements.length < 2 ) {
				continue;
			}

			String componentID = elements[0];
			String objectStatus = elements[1];

			List<String> profiles = new ArrayList<String>();

			if( elements.length > 2 ) {
				profiles = Arrays.asList(elements[2].split("\\|"));
			}

			if( ret.get(componentID) == null ) {
				ret.put(componentID, false);
			}

			if( status.equalsIgnoreCase(objectStatus) && !Collections.disjoint(profiles, listProfiles) ) {
				ret.put(componentID, true);
			}
		}

		return ret;
	}

	public HashMap<String, List<String>> getGuiVisibilityRules() {
		return guiVisibilityRules;
	}

	public void setGuiVisibilityRules(HashMap<String, List<String>> guiVisibilityRules) {
		this.guiVisibilityRules = guiVisibilityRules;
	}

	public HashMap<String, List<String>> getGuiEditableRules() {
		return guiEditableRules;
	}

	public void setGuiEditableRules(HashMap<String, List<String>> guiEditableRules) {
		this.guiEditableRules = guiEditableRules;
	}

	public HashMap<String, List<String>> getGuiInputMandarotyRules() {
		return guiInputMandarotyRules;
	}

	public void setGuiInputMandarotyRules(HashMap<String, List<String>> guiInputMandarotyRules) {
		this.guiInputMandarotyRules = guiInputMandarotyRules;
	}

}
