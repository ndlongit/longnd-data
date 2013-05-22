package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
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
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDocumentMdlServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.DocumentMdlException;
import com.structis.vip.shared.model.DocumentMdlModel;

public class DocumentListPanel extends LayoutContainer {

    public SimpleEventBus bus;
    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private Grid<DocumentMdlModel> grid;
    private PagingLoader<PagingLoadResult<DocumentMdlModel>> loader;
    private PagingModelMemoryProxy proxy;
    private Button btnAdd;
    private Button btnConsulter;
    private Button btnModifer;
    private Button btnSupprimer;

    private ListStore<DocumentMdlModel> store = new ListStore<DocumentMdlModel>();
    private ClientDocumentMdlServiceAsync clientDocumentMdlService = ClientDocumentMdlServiceAsync.Util.getInstance();

    public DocumentListPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.setLayout(new FlowLayout(10));
        this.setScrollMode(Scroll.AUTO);

        this.initUI();
        this.initEvent();
        this.addHandler();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
    }

    private void addHandler() {
        this.bus.addHandler(ModifyDocumentEvent.getType(), new ModifyDocumentHandler() {

            @Override
            public void onLoadAction(ModifyDocumentEvent event) {
                DocumentListPanel.this.disableEvents(true);
                DocumentListPanel.this.initData();
                DocumentListPanel.this.disableEvents(false);
            }
        });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DocumentMdlModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<DocumentMdlModel> se) {
                if (se.getSelectedItem() != null) {
                    DocumentListPanel.this.btnConsulter.setEnabled(true);
                    DocumentListPanel.this.btnModifer.setEnabled(true);
                    DocumentListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    DocumentListPanel.this.btnConsulter.setEnabled(false);
                    DocumentListPanel.this.btnModifer.setEnabled(false);
                    DocumentListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final DocumentMdlModel model = DocumentListPanel.this.grid.getSelectionModel().getSelectedItem();
                    DocumentListPanel.this.clientDocumentMdlService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            DocumentListPanel.this.initData();
                            Info.display(DocumentListPanel.this.messages.commoninfo(), DocumentListPanel.this.messages.delegationmodeldeleted());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof DocumentMdlException) {
                                details = ExceptionMessageHandler.getErrorMessage(((DocumentMdlException) caught).getCode());
                            }
                            Info.display(DocumentListPanel.this.messages.commonerror(), details);
                        }
                    });
                } else {
                }
            }
        };

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DocumentMdlModel model = DocumentListPanel.this.grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(DocumentListPanel.this.messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(DocumentListPanel.this.messages.commonDeleteMessage(model.getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(DocumentListPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(DocumentListPanel.this.messages.commonNon());
                    box.show();
                }
            }
        });

        this.btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);

                ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
                subEvent.setModel(null);
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);

                event.setEvent(subEvent);
                DocumentListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnConsulter.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_VIEW_DOCUMENT);

                ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
                subEvent.setModel(DocumentListPanel.this.grid.getSelectionModel().getSelectedItem());
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_VIEW_DOCUMENT);

                event.setEvent(subEvent);
                DocumentListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);

                ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
                subEvent.setModel(DocumentListPanel.this.grid.getSelectionModel().getSelectedItem());
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);

                event.setEvent(subEvent);
                DocumentListPanel.this.bus.fireEvent(event);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientDocumentMdlService.getAllDocumentModelsByEntite(SessionServiceImpl.getInstance().getEntiteContext(),
                new AsyncCallback<List<DocumentMdlModel>>() {

                    @Override
                    public void onSuccess(List<DocumentMdlModel> arg0) {
                        DocumentListPanel.this.proxy.setData(arg0);
                        DocumentListPanel.this.loader.load(0, 50);
                        DocumentListPanel.this.store = new ListStore<DocumentMdlModel>(DocumentListPanel.this.loader);
                        DocumentListPanel.this.grid.getView().refresh(true);
                        DocumentListPanel.this.grid.unmask();
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        DocumentListPanel.this.grid.unmask();
                    }
                });
    }

    private void initUI() {
        PagingToolBar toolBar = new PagingToolBar(50);
        ToolBar topToolBar = new ToolBar();

        this.btnAdd = new Button(this.messages.documentcreate());
        this.btnAdd.setStyleAttribute("margin-left", "10px");
        this.btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

        this.btnConsulter = new Button(this.messages.commonConsulterbutton());
        this.btnConsulter.setStyleAttribute("margin-left", "10px");
        this.btnConsulter.setIcon(IconHelper.createPath("html/view-icon.png"));
        this.btnConsulter.setEnabled(false);

        this.btnModifer = new Button(this.messages.commonmodifierbutton());
        this.btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
        this.btnModifer.setEnabled(false);

        this.btnSupprimer = new Button(this.messages.commonSupprimer());
        this.btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
        this.btnSupprimer.setEnabled(false);

        topToolBar.add(this.btnAdd);
        topToolBar.add(this.btnConsulter);
        topToolBar.add(this.btnModifer);
        topToolBar.add(this.btnSupprimer);

        ColumnConfig name = new ColumnConfig(DocumentMdlModel.DOM_NAME, this.messages.documentname(), 180);

        GridCellRenderer<DocumentMdlModel> nameRender = new GridCellRenderer<DocumentMdlModel>() {

            @Override
            public Object render(final DocumentMdlModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    final ListStore<DocumentMdlModel> store, Grid<DocumentMdlModel> grid) {
                final Label label = new Label();
                label.setStyleName("x-link-item");
                label.setText(model.getName());

                label.addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent arg0) {
                        String reportUrl = GWT.getHostPageBaseURL() + ".printTemplateDocumentServiceServlet";
                        List<NameValuePair> values = new ArrayList<NameValuePair>();
                        values.add(new NameValuePair("fileName", URL.encode(model.getFilename())));
                        ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
                    }
                });
                return label;
            }
        };
        name.setRenderer(nameRender);

        ColumnConfig fileName = new ColumnConfig(DocumentMdlModel.DOM_FILENAME, this.messages.documentfilename(), 180);
        ColumnConfig type = new ColumnConfig(DocumentMdlModel.DOM_TYPE, this.messages.documenttype(), 40);
        type.setAlignment(HorizontalAlignment.CENTER);
        ColumnConfig language = new ColumnConfig("language.name", this.messages.documentlanguage(), 80);
        language.setAlignment(HorizontalAlignment.CENTER);
        ColumnConfig version = new ColumnConfig(DocumentMdlModel.DOM_VERSION, this.messages.documentversion(), 40);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(fileName);
        config.add(type);
        config.add(language);
        config.add(version);

        final ColumnModel cm = new ColumnModel(config);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<DocumentMdlModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<DocumentMdlModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<DocumentMdlModel>(this.loader);
        this.loader.load(0, 50);
        toolBar.bind(this.loader);

        this.grid = new Grid<DocumentMdlModel>(this.store, cm);
        this.grid.setWidth(500);
        GridSelectionModel<DocumentMdlModel> selectionMode = new GridSelectionModel<DocumentMdlModel>();
        selectionMode.setSelectionMode(SelectionMode.SINGLE);
        this.grid.setSelectionModel(selectionMode);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(DocumentMdlModel.DOM_NAME);
        filters.addFilter(nameFilter);
        // LiveGridView liveView = new LiveGridView();
        // liveView.setEmptyText("No rows available on the server.");

        // grid.setView(liveView);
        this.grid.setColumnLines(true);
        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setAutoExpandColumn(DocumentMdlModel.DOM_NAME);

        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.documentlistdocuments());
        panel.setTopComponent(topToolBar);
        panel.setBottomComponent(toolBar);
        panel.setCollapsible(true);
        panel.setFrame(true);
        panel.setSize(this.WIDTH, this.HEIGHT);
        panel.setLayout(new FitLayout());
        panel.add(this.grid);
        this.grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");

        this.add(panel);
    }
}
