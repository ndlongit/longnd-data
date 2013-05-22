package com.structis.vip.shared.model;

public class ExternControllerModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String EXC_ID = "id";
    public static final String EXC_NAME = "name";
    public static final String EXC_ADDRESS = "address";

    @Override
    public Integer getId() {
        return this.get(EXC_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(EXC_ID, id);
    }

    public String getName() {
        return this.get(EXC_NAME);
    }

    public void setName(String name) {
        this.set(EXC_NAME, name);
    }

    public String getAddress() {
        return this.get(EXC_ADDRESS);
    }

    public void setAddress(String address) {
        this.set(EXC_ADDRESS, address);
    }

}
