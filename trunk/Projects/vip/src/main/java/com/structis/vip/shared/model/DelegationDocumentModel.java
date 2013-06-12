package com.structis.vip.shared.model;

public class DelegationDocumentModel extends BaseModelDataActivable {

    private static final long serialVersionUID = -2256867506534020595L;
    public static final String ODD_FILENAME = "fileName";
    public static final String ODD_DESCRIPTION = "description";
    public static final String ODD_DELEGATION = "delegation";

    @SuppressWarnings("unused")
    private DelegationModel delegationModel;

    public String getFileName() {
        return this.get(ODD_FILENAME);
    }

    public void setFileName(String fileName) {
        this.set(ODD_FILENAME, fileName);
    }

    public String getDescription() {
        return this.get(ODD_DESCRIPTION);
    }

    public void setDescription(String description) {
        this.set(ODD_DESCRIPTION, description);
    }

    public DelegationModel getDelegation() {
        return this.get(ODD_DELEGATION);
    }

    public void setDelegation(DelegationModel delegation) {
        this.set(ODD_DELEGATION, delegation);
    }
}
