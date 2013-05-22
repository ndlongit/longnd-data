package com.structis.vip.shared;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;

public class DocumentFilter extends BasePagingLoadConfig implements java.io.Serializable {

    private static final long serialVersionUID = 3727392202555197890L;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
