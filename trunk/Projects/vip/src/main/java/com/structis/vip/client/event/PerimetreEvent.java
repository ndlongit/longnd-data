package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class PerimetreEvent extends GwtEvent<PerimetreHandler> {

    public static final int MODE_IS_CREATE = 1;
    public static final int MODE_IS_EDIT = 2;
    public static final int MODE_IS_VIEW = 3;
    public static final int MODE_IS_NOT_SELECTED = 4;

    private String perimetreId;

    private String perimetreParentId;

    private Boolean isUoAdmin;

    private int mode;

    private String path;

    private static Type<PerimetreHandler> TYPE = new Type<PerimetreHandler>();

    public PerimetreEvent() {
    }

    public static Type<PerimetreHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<PerimetreHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PerimetreHandler handler) {
        handler.onLoadAction(this);
    }

    public String getPerimetreId() {
        return this.perimetreId;
    }

    public void setPerimetreId(String perimetreId) {
        this.perimetreId = perimetreId;
    }

    public String getPerimetreParentId() {
        return this.perimetreParentId;
    }

    public void setPerimetreParentId(String perimetreParentId) {
        this.perimetreParentId = perimetreParentId;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getIsUoAdmin() {
        return this.isUoAdmin;
    }

    public void setIsUoAdmin(Boolean isUoAdmin) {
        this.isUoAdmin = isUoAdmin;
    }
}
