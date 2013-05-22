package com.structis.vip.shared.model;

public class ExtControllerControlModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1;
    public static final String CEC_ID = "id";
    public static final String CEC_CONTROL = "control";
    public static final String CEC_EXTERNAL_CONTROLLER = "externalController";

    public static final String INTERNAL = "interne";
    public static final String EXTERNAL = "externe";

    @SuppressWarnings("unused")
    private ControlModel control;

    @SuppressWarnings("unused")
    private ExternControllerModel externalController;

    @Override
    public Integer getId() {
        return this.get(CEC_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(CEC_ID, id);
    }

    public ControlModel getControl() {
        return this.get(CEC_CONTROL);
    }

    public void setControl(ControlModel control) {
        this.set(CEC_CONTROL, control);
    }

    public ExternControllerModel getExternalController() {
        return this.get(CEC_EXTERNAL_CONTROLLER);
    }

    public void setExternalController(ExternControllerModel externalController) {
        this.set(CEC_EXTERNAL_CONTROLLER, externalController);
    }

}
