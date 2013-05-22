package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.UserModel;

public class ModifyUserEvent extends GwtEvent<ModifyUserHandler> {

    public static final int MODE_IS_VIEW = 1;

    public static final int MODE_IS_UPDATE_PERIMETRE = 2;

    private static Type<ModifyUserHandler> TYPE = new Type<ModifyUserHandler>();

    private UserModel model;

    private String perId;

    private int mode;

    public static Type<ModifyUserHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ModifyUserHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ModifyUserHandler handler) {
        handler.onLoadAction(this);
    }

    public UserModel getModel() {
        return this.model;
    }

    public void setModel(UserModel model) {
        this.model = model;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getPerId() {
        return this.perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }
}
