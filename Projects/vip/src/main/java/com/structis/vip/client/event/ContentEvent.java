package com.structis.vip.client.event;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.event.shared.GwtEvent;

public class ContentEvent extends GwtEvent<ContentEventHandler> {

    public static final int CHANGE_MODE_TO_NEW_DELEGATION_FORM = 1;
    public static final int CHANGE_MODE_TO_DETAIL_DELEGATION_FORM = 2;
    public static final int CHANGE_MODE_TO_GRID_DELEGATION_PANEL = 3;
    public static final int CHANGE_MODE_TO_GROUP_DELEGATION_MODEL_ADMIN_FORM = 4;
    public static final int CHANGE_MODE_TO_RULE_ADMIN_FORM = 5;
    public static final int CHANGE_MODE_TO_DELEGATION_MODEL_ADMIN_FORM = 6;

    public static final int CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM = 7;
    public static final int CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT = 8;
    public static final int CHANGE_MODE_TO_ADMIN_DOCUMENT_VIEW_DOCUMENT = 88;

    public static final int CHANGE_MODE_TO_PERIMETRE_FORM = 9;

    public static final int CHANGE_MODE_TO_ADMIN_NATURE_LIST = 10;
    public static final int CHANGE_MODE_TO_ADMIN_NATURE_CREATE_FORM = 11;

    public static final int CHANGE_MODE_TO_ADMIN_LANGUAGE_LIST = 12;
    public static final int CHANGE_MODE_TO_ADMIN_LANGUAGE_CREATE_FORM = 13;

    public static final int CHANGE_MODE_TO_ADMIN_STATUS_LIST = 14;
    public static final int CHANGE_MODE_TO_ADMIN_STATUS_CREATE_FORM = 15;

    public static final int CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_LIST = 16;
    public static final int CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_CREATE_FORM = 17;

    public static final int CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST = 18;
    public static final int CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_CREATE_FORM = 19;

    public static final int CHANGE_MODE_TO_ADMIN_FORMATION_LIST = 20;
    public static final int CHANGE_MODE_TO_ADMIN_FORMATION_CREATE_FORM = 21;

    public static final int CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST = 22;
    public static final int CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM = 23;
    public static final int CHANGE_MODE_TO_ADMIN_COLLABORATURE_VIEW_FORM = 33;

    public static final int CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_LIST = 24;
    public static final int CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_CREATE_FORM = 25;

    public static final int CHANGE_MODE_TO_ADMIN_USER_LIST = 26;
    public static final int CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM = 27;

    public static final int CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_VIEW_FORM = 28;
    public static final int CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM = 29;
    public static final int CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM = 30;

    public static final int CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_LIST = 31;
    public static final int CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_CREATE_FORM = 32;

    public static final int CHANGE_MODE_TO_ADMIN_DOC_LIST = 35;
    public static final int CHANGE_MODE_TO_ADMIN_DOC_CREATE_FORM = 36;

    public static final int CHANGE_MODE_TO_ADMIN_CATEGORY_LIST = 37;
    public static final int CHANGE_MODE_TO_ADMIN_CATEGORY_CREATE_FORM = 38;
    public static final int CHANGE_MODE_TO_ADMIN_DOCTYPE_CREATE_FORM = 39;
    public static final int CHANGE_MODE_TO_ADMIN_DOCTYPE_LIST = 40;
    public static final int CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_CREATE_FORM = 41;
    public static final int CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_LIST = 42;
    public static final int CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST = 43;
    public static final int CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_CREATE_FORM = 44;
    public static final int CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_CREATE_FORM = 45;
    public static final int CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST = 46;

    private static Type<ContentEventHandler> TYPE = new Type<ContentEventHandler>();

    private GwtEvent event;
    private LayoutContainer previousContent;
    private ModelData model;
    private boolean reload = false;

    private int mode;

    public ContentEvent() {
    }

    public static Type<ContentEventHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ContentEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ContentEventHandler handler) {
        handler.onLoadAction(this);
    }

    public GwtEvent getEvent() {
        return this.event;
    }

    public void setEvent(GwtEvent event) {
        this.event = event;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public LayoutContainer getPreviousContent() {
        return this.previousContent;
    }

    public void setPreviousContent(LayoutContainer previousContent) {
        this.previousContent = previousContent;
    }

    public ModelData getModel() {
        return this.model;
    }

    public void setModel(ModelData model) {
        this.model = model;
    }

    public boolean isReload() {
        return this.reload;
    }

    public void setReload(boolean reload) {
        this.reload = reload;
    }
}
