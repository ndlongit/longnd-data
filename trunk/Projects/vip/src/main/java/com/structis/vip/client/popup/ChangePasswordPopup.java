package com.structis.vip.client.popup;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.UserModel;

public class ChangePasswordPopup extends Window {
	private final Messages messages = GWT.create(Messages.class);
	private SimpleEventBus bus; 
	private TextField<String> currentPassword;
	private TextField<String> newPassword;
	private TextField<String> confirmPassword;
	private Button saveBtn;	
	private LayoutContainer errorLayout = new LayoutContainer();
	private Label lblErrorMessage;
	
	public ChangePasswordPopup(SimpleEventBus bus) {
		setTitle(messages.changepasswordpopuptitle());		
		setHeading(messages.changepasswordpopuptitle());
		this.bus = bus;
		setWidth(350);
		setHeight(160);
		setLayout(new FitLayout());
		
		FormPanel panel = new FormPanel();
		panel.setStyleAttribute("margin", "0px");   
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setBorders(false);
		errorLayout = new LayoutContainer();
		errorLayout.setHeight(30);
		
		FormData formData = new FormData("100%");
		FormLayout formLayout = new FormLayout();
		formLayout.setLabelAlign(LabelAlign.RIGHT);
		formLayout.setLabelWidth(100);		
		panel.setLayout(formLayout);
		panel.setButtonAlign(HorizontalAlignment.CENTER);
						
		lblErrorMessage = new Label("");
		lblErrorMessage.setStyleName("errorMessage");		
		errorLayout.add(lblErrorMessage);		
		errorLayout.setVisible(false);
		panel.add(errorLayout);
		
		currentPassword = new TextField<String>();
		currentPassword.setPassword(true);
		currentPassword.setFieldLabel("Current password");
		
		panel.add(currentPassword, formData);
		
		newPassword = new TextField<String>();
		newPassword.setPassword(true);
		newPassword.setFieldLabel("New password");		
		panel.add(newPassword, formData);
		
		confirmPassword = new TextField<String>();
		confirmPassword.setPassword(true);
		confirmPassword.setFieldLabel("Confirm password");		
		panel.add(confirmPassword,formData);
		
		
		saveBtn = new Button();
		saveBtn.setText(messages.commonModifierButton());
		saveBtn.addListener(Events.OnClick, new Listener<DomEvent>() {
			public void handleEvent(DomEvent be) {
				changePassword();
			}
		});
		Button fermerBtn = new Button();
		fermerBtn.setText(messages.commonFermerButton());
		fermerBtn.addListener(Events.OnClick, new Listener<DomEvent>() {
			public void handleEvent(DomEvent be) {
				hide();
			}
		});		
		panel.addButton(saveBtn);
		panel.addButton(fermerBtn);
		add(panel);
	}		
	
		
	protected void changePassword() {
		System.out.println("user ===" +  SessionServiceImpl.getInstance().getUserContext().getId());
		String currentValue = currentPassword.getValue();
		String confirmValue = confirmPassword.getValue();
		String newValue = newPassword.getValue();		
		if ((newValue != null && !newValue.equals(confirmValue)) || (confirmValue != null && !confirmValue.equals(newValue))) {		
			setHeight(300+30);			
			lblErrorMessage.setText(messages.passwordandconfirmdifferent());
			errorLayout.show();				
		} else {
			UserModel user = SessionServiceImpl.getInstance().getUserContext();
			if (user != null) {
				ClientUserServiceAsync.Util.getInstance().changePassword(user.getId(), currentValue,
						newValue, new AsyncCallback<ExceptionType>() {
					@Override
					public void onFailure(Throwable caught) {
								
					}
					@Override
					public void onSuccess(ExceptionType result) {
						if (result == null) {
							setHeight(160+30);
							lblErrorMessage.setText(messages.userpasswordchanged());
							errorLayout.show();	
						} else if (result == ExceptionType.USER_PASSWORD_INCORRECT){
							setHeight(160+30);
							lblErrorMessage.setText(messages.userpasswordinvalid());
							errorLayout.show();		
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