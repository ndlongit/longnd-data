package com.structis.vip.shared.model;

import java.util.Date;
import java.util.List;

import com.structis.vip.server.bean.domain.ExtControllerControl;


public class ControlModel extends BaseModelDataActivable {
	
	private static final long serialVersionUID = 1;
	public static final String CTL_ID = "id";
	public static final String CTL_CODEPROJECT = "codeProject";
	public static final String CTL_PERIEMTRE = "perimetre";
	public static final String CTL_PERIEMTRE_PARENT = "perimetreParent";
	public static final String CTL_CONTROL_TYPE = "controlType";
	public static final String CTL_DATE = "date";
	public static final String CTL_CHARACTER = "character";
	public static final String CTL_COLLABORATEUR = "collaborateur";
	public static final String CTL_REPORT = "rapport";	
	
	public static final String CAN_MODIFIED = "canModified";
	public static final String CAN_VIEWED = "canViewed";
	public static final String CAN_DELETED = "canDeleted";
	public static final String CAN_EXPORTED = "canExported";
	
	public static final String INTERNAL = "interne";
	public static final String EXTERNAL = "externe";
	public static final String EXTERNAL_CONTROL_NAMES = "extControlNames";
	

	@SuppressWarnings("unused")
	private PerimetreModel perModel;
	@SuppressWarnings("unused")
	private ControlTypeModel controlTypeModel;
	@SuppressWarnings("unused")
	private CollaborateurModel collaborateurModel;
	
	private List<ExtControllerControlModel> externalControllers;
		
	public Integer getId() {
		return get(CTL_ID);
	}

	public void setId(Integer id) {
		set(CTL_ID, id);
	}

	public String getCodeProject() {
		return get(CTL_CODEPROJECT);
	}

	public void setCodeProject(String codeProject) {
		set(CTL_CODEPROJECT, codeProject);
	}

	public PerimetreModel getPerimetre() {
		return get(CTL_PERIEMTRE);
	}

	public void setPerimetre(PerimetreModel perimetre) {
		set(CTL_PERIEMTRE, perimetre);
	}

	public String getPerimetreParent() {
		return get(CTL_PERIEMTRE_PARENT);
	}

	public void setPerimetreParent(String perimetreParent) {
		set(CTL_PERIEMTRE_PARENT, perimetreParent);
	}

	public ControlTypeModel getControlType() {
		return get(CTL_CONTROL_TYPE);
	}

	public void setControlType(ControlTypeModel controlType) {
		set(CTL_CONTROL_TYPE, controlType);
	}

	public Date getDate() {
		return get(CTL_DATE);
	}

	public void setDate(Date date) {
		set(CTL_DATE, date);
	}

	public String getCharacter() {
		return get(CTL_CHARACTER);
	}

	public void setCharacter(String character) {
		set(CTL_CHARACTER, character);
	}

	public CollaborateurModel getCollaborateur() {
		return get(CTL_COLLABORATEUR);
	}

	public void setCollaborateur(CollaborateurModel collaborateur) {
		set(CTL_COLLABORATEUR, collaborateur);
	}

	public String getRapport() {
		return get(CTL_REPORT);
	}

	public void setRapport(String rapport) {
		set(CTL_REPORT, rapport);
	}
	
	
	public Boolean getCanModified() {
		return get(CAN_MODIFIED);
	}

	public void setCanModified(Boolean canModified) {
		set(CAN_MODIFIED, canModified);
	}

	public Boolean getCanViewed() {
		return get(CAN_VIEWED);
	}

	public void setCanViewed(Boolean canViewed) {
		set(CAN_VIEWED, canViewed);
	}

	public Boolean getCanDeleted() {
		return get(CAN_DELETED);
	}

	public void setCanDeleted(Boolean canDeleted) {
		set(CAN_DELETED, canDeleted);
	}

	public Boolean getCanExported() {
		return get(CAN_EXPORTED);
	}

	public void setCanExported(Boolean canExported) {
		set(CAN_EXPORTED, canExported);
	}

	public void removeUnusedOnList() {
		setRapport(null);
		setCodeProject(null);
		getCollaborateur().removeUnusedOnList();
		setCollaborateur(null);
		getControlType().removeUnusedOnList();
		setControlType(null);
		setDate(null);
		setDateSuppr(null);
		setLibelle(null);
		getPerimetre().removeUnusedOnList();
		setPerimetre(null);
		setPerimetreParent(null);				
	}

	public void setExternControllers(List<ExtControllerControlModel> models) {
		this.externalControllers = models;		
	}
	
	public List<ExtControllerControlModel> getExternControllers() {
		return this.externalControllers;		
	}
	public String getExtControlNames() {
		return get(EXTERNAL_CONTROL_NAMES);
	}

	public void setExtControlNames(String extControlNames) {
		set(EXTERNAL_CONTROL_NAMES, extControlNames);
	}

	public void updateExtControllerNames() {
		StringBuffer extNames = new StringBuffer();
		if (externalControllers != null) {
			for (ExtControllerControlModel e: externalControllers) {
				extNames.append("<br>").append(e.getExternalController().getName());
			}
			if (extNames != null && extNames.length() > 0) {
				set(EXTERNAL_CONTROL_NAMES, extNames.toString().substring(4));
			}		
		} else {
			set(EXTERNAL_CONTROL_NAMES, "");
		}
	}
	public void setPermissionByRole(boolean isView, boolean isModify) {						
			this.setCanViewed(isView);				
			this.setCanExported(isView);
			this.setCanDeleted(isModify);
			this.setCanModified(isModify);
	}
}
