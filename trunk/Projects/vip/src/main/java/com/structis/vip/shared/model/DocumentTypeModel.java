package com.structis.vip.shared.model;

import com.structis.vip.shared.model.core.SimpleModel;

public class DocumentTypeModel extends SimpleModel {

    private static final long serialVersionUID = 1L;

    public DocumentTypeModel() {
    }

    public DocumentTypeModel(String code) {
        this.set(NAME, code);
    }
}
