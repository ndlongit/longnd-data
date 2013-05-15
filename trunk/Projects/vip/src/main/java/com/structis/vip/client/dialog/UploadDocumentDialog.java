package com.structis.vip.client.dialog;

import java.util.Date;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.DelegationEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationDocumentServiceAsync;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.DelegationDocumentModel;
import com.structis.vip.shared.model.DelegationModel;

public class UploadDocumentDialog extends Window {

	private final Messages messages = GWT.create(Messages.class);
	private final FormData formData = new FormData("95%");

	private final int WIDTH = 400;

	private final ClientDelegationDocumentServiceAsync clientDelegationDocumentServiceAsync = ClientDelegationDocumentServiceAsync.Util
			.getInstance();

	private FormPanel panel;
	private TextField<String> tfDescription;
	private FileUploadField ffFile;
	private Button btnAmnuler;
	private Button btnSave;

	private DelegationModel delegationModel;
	private SimpleEventBus bus;
	private String generateFileName = "";

	private Label lblErrorMessage;

	public UploadDocumentDialog(SimpleEventBus bus, DelegationModel delegationModel) {
		Date now = new Date();
		generateFileName = now.getTime() + CommonUtils.randomString(12) + ".pdf";
		this.delegationModel = delegationModel;
		this.bus = bus;
		initUI();
		addErrorLabel();				
	}

	private void addErrorLabel() {
		lblErrorMessage = new Label("");
		lblErrorMessage.setStyleName("errorMessage");
		this.add(lblErrorMessage, new FlowData(new Margins(0, 0, 10, 10)));
	}

	public void initUI() {
		setHeading(messages.delegationdocumentdialogheading());
		setSize(WIDTH, -1);
		setResizable(false);
		setModal(true);
		setButtonAlign(HorizontalAlignment.RIGHT);

		panel = new FormPanel();
		panel.setHeaderVisible(false);
		panel.setAction(GWT.getHostPageBaseURL() + ".uploadDelegationDocumentServiceServlet");
		panel.setFrame(false);
		panel.setBorders(false);
		panel.setBodyBorder(false);
		panel.setEncoding(Encoding.MULTIPART);
		panel.setMethod(Method.POST);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);
		panel.setWidth(WIDTH);

		tfDescription = new TextField<String>();
		tfDescription.setFieldLabel(messages.delegationdocumentdialogdescription());
		tfDescription.setName("Description");
		tfDescription.setMaxLength(150);
		tfDescription.setAllowBlank(false);
		panel.add(tfDescription, formData);

		ffFile = new FileUploadField();
		ffFile.setAllowBlank(false);
		ffFile.setName(generateFileName);
		ffFile.setFieldLabel(messages.delegationdocumentdialogfile());
		panel.add(ffFile, formData);

		btnAmnuler = new Button(messages.commonAnnulerButton());
		btnSave = new Button(messages.commonValiderButton());

		add(panel);
		addButton(btnAmnuler);
		addButton(btnSave);

		btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				hide();
			}
		});

		panel.addListener(Events.BeforeSubmit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				String fileName = ffFile.getFileInput().getValue();
				if (fileName != null && !"".equals(fileName)) {
					int lastDot = fileName.lastIndexOf(".");
					String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
					if (!ConstantClient.PDF_EXTENSION_FILE.equals(extFile)) {
						lblErrorMessage.setText(messages.delegationdocumentdialogmsgacceptpdf());
						be.setCancelled(true);						
					}
				}
			}
		});
		
		panel.addListener(Events.Submit, new Listener<FormEvent>() {
			@Override
			public void handleEvent(FormEvent be) {
				save(generateFileName);
			}
		});

		btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (panel.isValid()) {
					panel.submit();
				}
			}
		});
	}

	private void save(String fileName) {
		DelegationDocumentModel model = new DelegationDocumentModel();
		model.setDelegation(delegationModel);
		model.setDescription(tfDescription.getValue());
		model.setFileName(fileName);
		clientDelegationDocumentServiceAsync.insert(model, new AsyncCallback<DelegationDocumentModel>() {

			@Override
			public void onSuccess(DelegationDocumentModel result) {
				if (result != null) {
					Info.display(messages.commoninfo(), messages.delegationdocumentdialogcreatesuccess());
					DelegationEvent event = new DelegationEvent();
					event.setMode(DelegationEvent.MODE_IS_UPDATED_DOCUMENT);
					bus.fireEvent(event);
					hide();
				} else {
					lblErrorMessage.setText(messages.delegationdocumentdialogcreatefailed());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				lblErrorMessage.setText(messages.delegationdocumentdialogcreatefailed());
			}
		});

	}
}
