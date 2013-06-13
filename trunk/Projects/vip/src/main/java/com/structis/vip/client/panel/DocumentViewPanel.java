package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyDocumentEvent;
import com.structis.vip.client.event.ModifyDocumentHandler;
import com.structis.vip.client.service.ClientDocTypeServiceAsync;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DocumentTypeModel;
import com.structis.vip.shared.model.LanguageModel;

public class DocumentViewPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ListStore<DocumentTypeModel> documentTypeStore = new ListStore<DocumentTypeModel>();

    private ListStore<LanguageModel> languageStore = new ListStore<LanguageModel>();
    private ClientLanguageServiceAsync clientLanguageService = ClientLanguageServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private LabelField tfName;
    private LabelField tfVersion;
    private LabelField ffFile;
    private LabelField ffTemp;
    private LabelField cbType;
    private LabelField cbLanguage;
    private Button btnAmnuler;
    private Button btnModify;
    private DocumentMdlModel model;

    public DocumentViewPanel(SimpleEventBus bus) {
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
        initUI();
        initEvent();
    }

    private void addHandler() {
        bus.addHandler(ModifyDocumentEvent.getType(), new ModifyDocumentHandler() {

            @Override
            public void onLoadAction(ModifyDocumentEvent event) {
                if (ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_VIEW_DOCUMENT == event.getMode()) {
                    if (event.getModel() != null) {
                        model = event.getModel();
                        tfName.setValue(model.getName());
                        cbLanguage.setValue(model.getLanguage().getName());
                        tfVersion.setValue(model.getVersion());
                        cbType.setValue(getDocType(model.getType()));
                        // if (DOC_TYPE_DP.equals(model.getType().trim())) {
                        // cbType.setValue(documentTypeStore.getAt(0).getName());
                        // } else {
                        // cbType.setValue(documentTypeStore.getAt(1).getName());
                        // }

                        ffFile.setValue(model.getFilename());
                        ffTemp.setValue(model.getTempFilename());
                    }
                }
            }
        });
    }

    private String getDocType(String type) {
        if (type == null) {
            return null;
        } else {
            for (DocumentTypeModel dt : documentTypeStore.getModels()) {
                if (dt.getName().trim().equals(type.trim())) {
                    return dt.getName();
                }
            }

            return type;
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
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
        // documentTypeStore.removeAll();
        // documentTypeStore.add(new DocumentTypeModel(DOC_TYPE_DP));
        // documentTypeStore.add(new DocumentTypeModel(DOC_TYPE_LR));
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

        tfName = new LabelField();
        tfName.setFieldLabel(messages.documentname());
        tfName.setName("name");
        panel.add(tfName, formData);

        ffFile = new LabelField();
        ffFile.addStyleName("x-link-item");
        ffFile.setFieldLabel(messages.documentfile());
        panel.add(ffFile, formData);

        ffTemp = new LabelField();
        ffTemp.addStyleName("x-link-item");
        ffTemp.setFieldLabel(messages.documentfiletemp());
        panel.add(ffTemp, formData);

        cbType = new LabelField();
        cbType.setFieldLabel(messages.documenttype());
        panel.add(cbType, formData);

        cbLanguage = new LabelField();
        cbLanguage.setFieldLabel(messages.documentlanguage());
        panel.add(cbLanguage, formData);

        tfVersion = new LabelField();
        tfVersion.setFieldLabel(messages.documentversion());
        panel.add(tfVersion, formData);

        btnAmnuler = new Button(messages.commonAnnulerButton());
        btnModify = new Button(messages.commonModifierButton());

        panel.addButton(btnAmnuler);
        panel.addButton(btnModify);

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
                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
                bus.fireEvent(contentEvent);
            }
        });

        add(backLink);
    }

    private void initEvent() {
        btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
                bus.fireEvent(event);
            }
        });

        btnModify.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);

                ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
                subEvent.setModel(model);
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);

                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        ffFile.addListener(Events.OnClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                String reportUrl = GWT.getHostPageBaseURL() + ".printTemplateDocumentServiceServlet";
                List<NameValuePair> values = new ArrayList<NameValuePair>();
                String fileName = ffFile.getText();

                fileName = URL.encode(ffFile.getText());
                values.add(new NameValuePair("fileName", fileName));
                ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
            }
        });

        ffTemp.addListener(Events.OnClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                String reportUrl = GWT.getHostPageBaseURL() + ".printTemplateDocumentServiceServlet";
                List<NameValuePair> values = new ArrayList<NameValuePair>();
                values.add(new NameValuePair("fileName", ffTemp.getText()));
                ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
            }
        });
    }
}
