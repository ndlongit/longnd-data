package com.structis.vip.client.popup;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.UserModel;

public class ChangePasswordPopup extends Window {

    private final Messages messages = GWT.create(Messages.class);
    private TextField<String> currentPassword;
    private TextField<String> newPassword;
    private TextField<String> confirmPassword;
    private Button saveBtn;
    private LayoutContainer errorLayout = new LayoutContainer();
    private Label lblErrorMessage;

    public ChangePasswordPopup(SimpleEventBus bus) {
        this.setTitle(this.messages.changepasswordpopuptitle());
        this.setHeading(this.messages.changepasswordpopuptitle());
        this.setWidth(350);
        this.setHeight(160);
        this.setLayout(new FitLayout());

        FormPanel panel = new FormPanel();
        panel.setStyleAttribute("margin", "0px");
        panel.setHeaderVisible(false);
        panel.setBodyBorder(false);
        panel.setBorders(false);
        this.errorLayout = new LayoutContainer();
        this.errorLayout.setHeight(30);

        FormData formData = new FormData("100%");
        FormLayout formLayout = new FormLayout();
        formLayout.setLabelAlign(LabelAlign.RIGHT);
        formLayout.setLabelWidth(100);
        panel.setLayout(formLayout);
        panel.setButtonAlign(HorizontalAlignment.CENTER);

        this.lblErrorMessage = new Label("");
        this.lblErrorMessage.setStyleName("errorMessage");
        this.errorLayout.add(this.lblErrorMessage);
        this.errorLayout.setVisible(false);
        panel.add(this.errorLayout);

        this.currentPassword = new TextField<String>();
        this.currentPassword.setPassword(true);
        this.currentPassword.setFieldLabel("Current password");

        panel.add(this.currentPassword, formData);

        this.newPassword = new TextField<String>();
        this.newPassword.setPassword(true);
        this.newPassword.setFieldLabel("New password");
        panel.add(this.newPassword, formData);

        this.confirmPassword = new TextField<String>();
        this.confirmPassword.setPassword(true);
        this.confirmPassword.setFieldLabel("Confirm password");
        panel.add(this.confirmPassword, formData);

        this.saveBtn = new Button();
        this.saveBtn.setText(this.messages.commonModifierButton());
        this.saveBtn.addListener(Events.OnClick, new Listener<DomEvent>() {

            @Override
            public void handleEvent(DomEvent be) {
                ChangePasswordPopup.this.changePassword();
            }
        });
        Button fermerBtn = new Button();
        fermerBtn.setText(this.messages.commonFermerButton());
        fermerBtn.addListener(Events.OnClick, new Listener<DomEvent>() {

            @Override
            public void handleEvent(DomEvent be) {
                ChangePasswordPopup.this.hide();
            }
        });
        panel.addButton(this.saveBtn);
        panel.addButton(fermerBtn);
        this.add(panel);
    }

    protected void changePassword() {
        String currentValue = this.currentPassword.getValue();
        String confirmValue = this.confirmPassword.getValue();
        String newValue = this.newPassword.getValue();
        if ((newValue != null && !newValue.equals(confirmValue)) || (confirmValue != null && !confirmValue.equals(newValue))) {
            this.setHeight(300 + 30);
            this.lblErrorMessage.setText(this.messages.passwordandconfirmdifferent());
            this.errorLayout.show();
        } else {
            UserModel user = SessionServiceImpl.getInstance().getUserContext();
            if (user != null) {
                ClientUserServiceAsync.Util.getInstance().changePassword(user.getId(), currentValue, newValue, new AsyncCallback<ExceptionType>() {

                    @Override
                    public void onFailure(Throwable caught) {

                    }

                    @Override
                    public void onSuccess(ExceptionType result) {
                        if (result == null) {
                            ChangePasswordPopup.this.setHeight(160 + 30);
                            ChangePasswordPopup.this.lblErrorMessage.setText(ChangePasswordPopup.this.messages.userpasswordchanged());
                            ChangePasswordPopup.this.errorLayout.show();
                        } else if (result == ExceptionType.USER_PASSWORD_INCORRECT) {
                            ChangePasswordPopup.this.setHeight(160 + 30);
                            ChangePasswordPopup.this.lblErrorMessage.setText(ChangePasswordPopup.this.messages.userpasswordinvalid());
                            ChangePasswordPopup.this.errorLayout.show();
                        }
                    }
                });
            }
        }
    }

    @Override
    public void show() {
        super.show();
    }

}
