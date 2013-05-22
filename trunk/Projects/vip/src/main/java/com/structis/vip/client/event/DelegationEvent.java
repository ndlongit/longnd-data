package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DelegationTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;

public class DelegationEvent extends GwtEvent<DelegationEventHandler> {

    public static final int MODE_IS_NEW = 1;
    public static final int MODE_IS_VIEW = 2;
    public static final int MODE_IS_EDIT = 3;
    public static final int MODE_IS_PRINT_DOCUMENT = 4;
    public static final int MODE_IS_ADD_DOCUMENT_SIGNEE = 5;
    public static final int MODE_IS_ADD_DOCUMENT = 6;
    public static final int MODE_IS_CREATE_SUB_DELEGATION = 7;
    public static final int MODE_IS_RENEW_DELEGATION = 8;
    public static final int MODE_IS_REPLACE_DELEGANT_OR_DELEGATAIRE = 9;
    public static final int MODE_IS_CREATE_TEM_DELEGATION = 10;
    public static final int MODE_IS_UPDATED_DOCUMENT = 11;

    private static Type<DelegationEventHandler> TYPE = new Type<DelegationEventHandler>();

    private int mode;

    private Integer delegationId;

    private PerimetreModel perimetreModel;

    private DelegationTypeModel delegationTypeModel;

    private EntiteModel entiteModel;

    private Boolean isModification = false;

    private DelegationModel delegationModel;

    public Integer getDelegationId() {
        return this.delegationId;
    }

    public void setDelegationId(Integer delegationId) {
        this.delegationId = delegationId;
    }

    public PerimetreModel getPerimetreModel() {
        return this.perimetreModel;
    }

    public void setPerimetreModel(PerimetreModel perimetreModel) {
        this.perimetreModel = perimetreModel;
    }

    public DelegationTypeModel getDelegationTypeModel() {
        return this.delegationTypeModel;
    }

    public void setDelegationTypeModel(DelegationTypeModel delegationTypeModel) {
        this.delegationTypeModel = delegationTypeModel;
    }

    public EntiteModel getEntiteModel() {
        return this.entiteModel;
    }

    public void setEntiteModel(EntiteModel entiteModel) {
        this.entiteModel = entiteModel;
    }

    public static Type<DelegationEventHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<DelegationEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DelegationEventHandler handler) {
        handler.onLoadAction(this);
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Boolean getIsModification() {
        return this.isModification;
    }

    public void setIsModification(Boolean isModification) {
        this.isModification = isModification;
    }

    public DelegationModel getDelegationModel() {
        return this.delegationModel;
    }

    public void setDelegationModel(DelegationModel delegationModel) {
        this.delegationModel = delegationModel;
    }

}
