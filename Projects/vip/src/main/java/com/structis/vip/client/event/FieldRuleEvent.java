package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.EntiteModel;

public class FieldRuleEvent extends GwtEvent<FieldRuleHandler> {

    private static Type<FieldRuleHandler> TYPE = new Type<FieldRuleHandler>();
    private Integer group;
    private EntiteModel entite;

    public static Type<FieldRuleHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<FieldRuleHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(FieldRuleHandler handler) {
        handler.onLoadAction(this);
    }

    public Integer getGroup() {
        return this.group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public EntiteModel getEntite() {
        return this.entite;
    }

    public void setEntite(EntiteModel entite) {
        this.entite = entite;
    }
}
