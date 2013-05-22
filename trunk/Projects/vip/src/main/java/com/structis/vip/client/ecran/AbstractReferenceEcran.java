package com.structis.vip.client.ecran;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

/**
 * Class abstract for tab ecran
 * 
 * @author v.tong thanh
 * 
 */
public abstract class AbstractReferenceEcran extends LayoutContainer {

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        this.setLayout(new FlowLayout(10));
    }

}
