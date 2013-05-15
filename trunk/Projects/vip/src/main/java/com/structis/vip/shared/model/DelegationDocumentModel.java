package com.structis.vip.shared.model;

public class DelegationDocumentModel extends BaseModelDataActivable {
	
	private static final long serialVersionUID = -2256867506534020595L;
	public static final String ODD_ID = "id";
	public static final String ODD_FILENAME = "fileName";
	public static final String ODD_DESCRIPTION = "description";
	public static final String ODD_DELEGATION = "delegation";

	@SuppressWarnings("unused")
	private DelegationModel delegationModel;

	public Integer getId() {
		return get(ODD_ID);
	}

	public void setId(Integer id) {
		set(ODD_ID, id);
	}

	public String getFileName() {
		return get(ODD_FILENAME);
	}

	public void setFileName(String fileName) {
		set(ODD_FILENAME, fileName);
	}

	public String getDescription() {
		return get(ODD_DESCRIPTION);
	}

	public void setDescription(String description) {
		set(ODD_DESCRIPTION, description);
	}

	public DelegationModel getDelegation() {
		return get(ODD_DELEGATION);
	}

	public void setDelegation(DelegationModel delegation) {
		set(ODD_DELEGATION, delegation);
	}
}
