package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyDocumentEvent;
import com.structis.vip.client.event.ModifyDocumentHandler;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDocTypeServiceAsync;
import com.structis.vip.client.service.ClientDocumentMdlServiceAsync;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.exception.DocumentMdlException;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DocumentTypeModel;
import com.structis.vip.shared.model.LanguageModel;

public class DocumentFormPanel extends LayoutContainer {
	
	private final Messages messages = GWT.create(Messages.class);
	private final FormData formData = new FormData("98%");
	private final int WIDTH = 680;
	
	private ListStore<DocumentTypeModel> documentTypeStore = new ListStore<DocumentTypeModel>();
	
	private ListStore<LanguageModel> languageStore = new ListStore<LanguageModel>();
	private ClientLanguageServiceAsync clientLanguageService = ClientLanguageServiceAsync.Util.getInstance();
	private ClientDocumentMdlServiceAsync clientDocumentMdlService = ClientDocumentMdlServiceAsync.Util.getInstance();
	
	private SimpleEventBus bus;
	private FormPanel panel;
	private TextField<String> tfName;
	private TextField<String> tfVersion;
	private FileUploadField ffFile;
	private FileUploadField ffTemp;
	private Button btnReset;
	private ComboBox<DocumentTypeModel> cbType;
	private ComboBox<LanguageModel> cbLanguage;
	private Button btnAmnuler;
	private Button btnSave;
	private DocumentMdlModel model;
	private boolean isEdit = true;
	
	private Label lblErrorMessage;
	private LayoutContainer errorLayout;
	
	public DocumentFormPanel(SimpleEventBus bus) {
		this.bus = bus;
		
		setLayout(new FlowLayout(10));
		setScrollMode(Scroll.AUTO);
		setWidth(WIDTH);
		
		addHandler();
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		
		
		initData();
		initBackLink();		
		addErrorLabel();
		
		initUI();
		initEvent();
	}
	
	private void addErrorLabel() {
		errorLayout = new LayoutContainer();
		errorLayout.setHeight(30);			
						
		lblErrorMessage = new Label();
		lblErrorMessage.setStyleName("errorMessage");
		errorLayout.add(lblErrorMessage);
		this.add(errorLayout);
		errorLayout.setVisible(false);
	}
	
	private void showErrorLabel(Boolean isShow, String message) {
		lblErrorMessage.setText(message);
		errorLayout.setVisible(isShow);
	}
	
	private void addHandler() {
		bus.addHandler(ModifyDocumentEvent.getType(), new ModifyDocumentHandler() {
			@Override
			public void onLoadAction(ModifyDocumentEvent event) {				
				if (ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM == event.getMode()) {
					AppUtil.putInAdminEditMode();
					if (event.getModel() != null) {
						isEdit = true;
						model = event.getModel();
						tfName.setValue(model.getName());
						tfName.setToolTip(model.getName());
						cbLanguage.setValue(model.getLanguage());
						tfVersion.setValue(model.getVersion());
						cbType.setValue(getDocType(model.getType()));						
						
						ffFile.setValue(model.getFilename());
						ffFile.setToolTip(model.getFilename());
						ffTemp.setValue(model.getTempFilename());
						if (model.getTempFilename() != null && model.getTempFilename().length()>0) {
							ffTemp.setToolTip(model.getTempFilename());
						}
						
					} else {
						model = null;
						isEdit = false;
						panel.reset();
						panel.clear();
					}
				}
			}

			
		});
	}
	private DocumentTypeModel getDocType(String type) {
		if (type == null) {
			return null;
		} else {
			for (DocumentTypeModel dt : documentTypeStore.getModels()) {
				if (dt.getName().trim().equals(type.trim())) {							
					return dt;
				}
			}
			DocumentTypeModel newDoc = new DocumentTypeModel();
			newDoc.setName(type);
			return newDoc;
		}
		
	}
	
	private void initData() {
		languageStore.removeAll();
		clientLanguageService.getLanguages(new AsyncCallback<List<LanguageModel>>() {
			@Override
			public void onSuccess(List<LanguageModel> arg0) {
				languageStore.add(arg0);
			}
			
			@Override
			public void onFailure(Throwable arg0) {
			}
		});
		
		ClientDocTypeServiceAsync.Util.getInstance().getDocTypes(new AsyncCallback<List<DocumentTypeModel>>() {
			@Override
			public void onSuccess(List<DocumentTypeModel> arg0) {
				synchronized (this) {
					documentTypeStore.removeAll();
					documentTypeStore.add(arg0);
					cbType.setValue(getDocType(model.getType()));
				}
			}
			
			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}
	
	private void initUI() {
		panel = new FormPanel();
		panel.setHeading(messages.documentform());
		panel.setAction(GWT.getHostPageBaseURL() + ".uploadDocumentServiceServlet");
		panel.setFrame(true);
		panel.setEncoding(Encoding.MULTIPART);
		panel.setMethod(Method.POST);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);
		panel.setLabelWidth(110);
		panel.setWidth(WIDTH);
		
		tfName = new TextField<String>();
		tfName.setFieldLabel(messages.documentname());
		tfName.setName("name");		
		tfName.setMaxLength(150);
		tfName.setAllowBlank(false);
		panel.add(tfName, formData);

		HorizontalPanel p0 = new HorizontalPanel();
		p0.setBorders(false);
		p0.setTableWidth("100%");
		
		ffFile = new FileUploadField();
		ffFile.setAllowBlank(false);
		ffFile.setWidth("460");
		ffFile.setName("uploadedfile");
		//ffFile.setFieldLabel(messages.documentfile());
		ffFile.setLabelStyle("x-form-item-label");		
		ffFile.getMessages().setBrowseText(messages.documentBrowseText());
		Button btnReset0 = new Button(messages.documentclearuploadfile());
		btnReset0.setWidth(60);
		btnReset0.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ffFile.reset();
			}
		});
		Label lblTmpFile0 = new Label(messages.documentfile() + ":");
		lblTmpFile0.setStyleName("x-form-item-label");
		lblTmpFile0.setWidth("115");
		p0.add(lblTmpFile0);
		p0.add(ffFile);
		p0.add(btnReset0);
		panel.add(p0, formData);
		
		//panel.add(ffFile, formData);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setBorders(false);
		p.setTableWidth("100%");
		
		ffTemp = new FileUploadField();
		ffTemp.setName("temporaryfile");
		ffTemp.setAllowBlank(true);		
		//ffTemp.setFieldLabel(messages.documentfiletemp());
		ffTemp.setLabelStyle("x-form-item-label");
		ffTemp.setWidth("460");
		
		ffTemp.getMessages().setBrowseText(messages.documentBrowseText());
		btnReset = new Button(messages.documentclearuploadfile());
		btnReset.setWidth(60);
		btnReset.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ffTemp.reset();
			}
		});
		Label lblTmpFile = new Label(messages.documentfiletemp() + ":");
		lblTmpFile.setStyleName("x-form-item-label");
		lblTmpFile.setWidth("115");
		p.add(lblTmpFile);
		p.add(ffTemp);
		p.add(btnReset);
		panel.add(p, formData);
		
		cbType = new ComboBox<DocumentTypeModel>();
		cbType.setStore(documentTypeStore);
		cbType.setFieldLabel(messages.documenttype());
		cbType.setDisplayField(DocumentTypeModel.DOC_TYPE_NAME);
		cbType.setName("type");
		cbType.setEditable(false);
		cbType.setAllowBlank(false);
		cbType.setTriggerAction(TriggerAction.ALL);
		panel.add(cbType, formData);
		
		cbLanguage = new ComboBox<LanguageModel>();
		cbLanguage.setAllowBlank(false);
		cbLanguage.setEditable(false);
		cbLanguage.setFieldLabel(messages.documentlanguage());
		cbLanguage.setName("language");
		cbLanguage.setDisplayField(LanguageModel.LAG_NAME);
		cbLanguage.setTriggerAction(TriggerAction.ALL);
		cbLanguage.setStore(languageStore);
		panel.add(cbLanguage, formData);
		
		tfVersion = new TextField<String>();
		tfVersion.setFieldLabel(messages.documentversion());
		tfVersion.setName("version");
		tfVersion.setMaxLength(50);
		tfVersion.setAllowBlank(true);
		panel.add(tfVersion, formData);
		
		btnAmnuler = new Button(messages.commonAnnulerButton());
		btnSave = new Button(messages.commonValiderButton()); 
		
		panel.addButton(btnAmnuler);
		panel.addButton(btnSave);
		
		panel.getButtonBar().setStyleAttribute("padding-right", "16px");
		
		add(panel);
	}
	
	private void initBackLink() {
		LayoutContainer backLink = new LayoutContainer();
		backLink.setSize(WIDTH, -1);
		Label lblBack = new Label(messages.documentback());
		
		lblBack.setStyleName("x-link-item");
		backLink.setStyleAttribute("margin-bottom", "20px");
		backLink.add(lblBack);
		
		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
					bus.fireEvent(contentEvent);
				}
			}			
		});		
		
		add(backLink);			
	}
	
	private void initEvent() {
		btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent contentEvent = new ContentEvent();
				contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
				contentEvent.setEvent(new ModifyDocumentEvent());
				bus.fireEvent(contentEvent);
				AppUtil.removeAdminInEditMode();
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
		
		
		panel.addListener(Events.BeforeSubmit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				showErrorLabel(false, "");	
				
				if (ffFile.getValue() != null) {
					String fileName = ffFile.getValue(); 
				
					if (fileName != null && !"".equals(fileName)) {
						int lastDot = fileName.lastIndexOf(".");
						String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
						if (!ConstantClient.DOC_EXTENSION_FILE.equals(extFile)) {
							showErrorLabel(true, "Document doit être un fichier doc");
							be.setCancelled(true);
						}
					}
				}
				
				if (ffTemp.getValue() != null) {
					String fileName = ffTemp.getValue(); 
				
					if (fileName != null && !"".equals(fileName)) {
						int lastDot = fileName.lastIndexOf(".");
						String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
						if (!ConstantClient.DOC_EXTENSION_FILE.equals(extFile)) {
							showErrorLabel(true, "Document doit être un fichier doc");
							be.setCancelled(true);
						}
					}
				}
			}
		});

		
		panel.addListener(Events.Submit, new Listener<FormEvent>() {
			@Override
			public void handleEvent(FormEvent be) {
				String fileName = null;
				String tempFileName = null;
				if ((ffFile.getValue() != null) && (!"".equals(ffFile.getValue()))) {
					fileName = ffFile.getValue();
				}
				if ((ffTemp.getValue() != null) && (!"".equals(ffTemp.getValue()))) {
					tempFileName = ffTemp.getValue();
				}
				save(fileName, tempFileName);
			}
		});
	}
	
	private void save(String fileName, String tempFileName) {
		if (model == null)  {
			model = new DocumentMdlModel();
		}
		model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
		model.setName(tfName.getValue());
		model.setType(cbType.getValue().getName());
		model.setLanguage(cbLanguage.getValue());
		model.setVersion(tfVersion.getValue());
		
//		if (ConstantClient.ENTITE_ID_IS_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
//			model.setVariables("etjStatut, delegataireAddress, etjRegistrationAddress, etjCapital, delegataireFirstname, delegataireTitle, endDate, amount1, etjRegistrationId, place1, amount3, startDate, etjAddress, etjName, amount2, delegataireLastname, amount4, delegantFirstname, comment1, delegantLastname, delegantTitle, delegantStatut, delegataireStatut");
//		} else {
//			model.setVariables("amount1,amount2,amount3,amount4,amount5,date1,date2,date3,dateDelegation,delegantDateConseil,delegantDateEffet,delegantFirstname,delegantLastname,delegantNom1,delegantPrenom1,delegantQualite,delegantQualite1,delegantStatutConseil,delegataireAddress,delegataireDateFormation,delegataireDateNaissance,delegataireFirstname,delegataireIntituleFormation,delegataireLastname,delegataireLieuNaissance,delegataireNationalite,delegataireQualite,demandeurFirstname,demandeurLastname,endDate,etjAddress,etjCapital,etjName,etjRegistrationAddress,etjRegistrationId,etjStatut,operations,perChantierCity,perChantierEndDate,perChantierID,perChantierName,perChantierPlannedEndDate,perChantierStartDate,place1,place2,place3,startDate,zone");
//		}
		
		if (null != fileName) {
			model.setFilename(fileName);
		}
		
		if (null != tempFileName) {
			model.setTempFilename(tempFileName);
		}
		
		if (isEdit == false) {
			clientDocumentMdlService.insert(model, new AsyncCallback<DocumentMdlModel>() {
				
				@Override
				public void onSuccess(DocumentMdlModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
					
					ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
					subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
					
					contentEvent.setEvent(subEvent);
					bus.fireEvent(contentEvent);
					AppUtil.removeAdminInEditMode();
				}
				
				@Override
				public void onFailure(Throwable caught) {
					String details = caught.getMessage();
					if (caught instanceof DocumentMdlException) {
						details = ExceptionMessageHandler.getErrorMessage(((DocumentMdlException) caught).getCode());
					}
					Info.display(messages.commonerror(), details);
				}
			});
		} else {
			clientDocumentMdlService.update(model, new AsyncCallback<DocumentMdlModel>() {
				@Override
				public void onSuccess(DocumentMdlModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
					
					ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
					subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
					
					contentEvent.setEvent(subEvent);
					bus.fireEvent(contentEvent);
					AppUtil.removeAdminInEditMode();
				}
				
				@Override
				public void onFailure(Throwable caught) {
					String details = caught.getMessage();
					if (caught instanceof DocumentMdlException) {
						details = ExceptionMessageHandler.getErrorMessage(((DocumentMdlException) caught).getCode());
					}
					Info.display(messages.commonerror(), details);
				}
			});
		}
	}
}
