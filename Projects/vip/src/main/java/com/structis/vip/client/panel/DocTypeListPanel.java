package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
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
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.ModifyDocTypeEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDocTypeServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.LanguageException;
import com.structis.vip.shared.model.DocumentTypeModel;

public class DocTypeListPanel extends AbstractPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
    private ListStore<DocumentTypeModel> store = new ListStore<DocumentTypeModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<DocumentTypeModel> grid;
    private PagingLoader<PagingLoadResult<DocumentTypeModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientDocTypeServiceAsync clientDocTypeService = ClientDocTypeServiceAsync.Util.getInstance();

    public DocTypeListPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(LoadDocumentEvent.getType(), new LoadDocumentHandler() {

            @Override
            public void onLoadAction(LoadDocumentEvent event) {
                DocTypeListPanel.this.disableEvents(true);
                DocTypeListPanel.this.initData();
                DocTypeListPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                DocTypeListPanel.this.disableEvents(true);
                DocTypeListPanel.this.initData();
                DocTypeListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientDocTypeService.getDocTypes(new AsyncCallback<List<DocumentTypeModel>>() {

            @Override
            public void onSuccess(List<DocumentTypeModel> arg0) {
                DocTypeListPanel.this.proxy.setData(arg0);
                DocTypeListPanel.this.loader.load(0, 50);
                DocTypeListPanel.this.store = new ListStore<DocumentTypeModel>(DocTypeListPanel.this.loader);
                DocTypeListPanel.this.grid.unmask();
            }

            @Override
            public void onFailure(Throwable arg0) {
                DocTypeListPanel.this.grid.unmask();
            }
        });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DocumentTypeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<DocumentTypeModel> se) {
                if (se.getSelectedItem() != null) {
                    DocTypeListPanel.this.btnModifer.setEnabled(true);
                    DocTypeListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    DocTypeListPanel.this.btnModifer.setEnabled(false);
                    DocTypeListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final DocumentTypeModel model = DocTypeListPanel.this.grid.getSelectionModel().getSelectedItem();
                    DocTypeListPanel.this.clientDocTypeService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            DocTypeListPanel.this.initData();
                            Info.display(DocTypeListPanel.this.messages.commoninfo(),
                                    DocTypeListPanel.this.messages.doctypemessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof LanguageException) {
                                details = ExceptionMessageHandler.getErrorMessage(((LanguageException) caught).getCode());
                            }
                            Info.display(DocTypeListPanel.this.messages.commonerror(), details);
                        }
                    });
                } else {
                }
            }
        };

        this.btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_CREATE_FORM);
                ModifyDocTypeEvent subEvent = new ModifyDocTypeEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                DocTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_CREATE_FORM);
                ModifyDocTypeEvent subEvent = new ModifyDocTypeEvent();
                subEvent.setModel(DocTypeListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                DocTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DocumentTypeModel model = DocTypeListPanel.this.grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(DocTypeListPanel.this.messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(DocTypeListPanel.this.messages.commonDeleteMessage(model.getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(DocTypeListPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(DocTypeListPanel.this.messages.commonNon());
                    box.show();
                }
            }
        });
    }

    private void initUI() {
        PagingToolBar toolBar = new PagingToolBar(50);
        ToolBar topToolBar = new ToolBar();

        this.btnAdd = new Button(this.messages.commonCreerbutton());
        this.btnAdd.setStyleAttribute("margin-left", "10px");
        this.btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

        this.btnModifer = new Button(this.messages.commonmodifierbutton());
        this.btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
        this.btnModifer.setEnabled(false);

        this.btnSupprimer = new Button(this.messages.commonSupprimer());
        this.btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
        this.btnSupprimer.setEnabled(false);

        topToolBar.add(this.btnAdd);
        topToolBar.add(this.btnModifer);
        topToolBar.add(this.btnSupprimer);

        ColumnConfig name = new ColumnConfig(DocumentTypeModel.DOC_TYPE_NAME, this.messages.doctypenom(), 100);
        ColumnConfig desc = new ColumnConfig(DocumentTypeModel.DOC_TYPE_DESC, this.messages.doctypedesc(), 100);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<DocumentTypeModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<DocumentTypeModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<DocumentTypeModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(desc);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<DocumentTypeModel>(this.store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(DocumentTypeModel.DOC_TYPE_NAME);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.doctypelistheader());
        panel.setBottomComponent(toolBar);
        panel.setTopComponent(topToolBar);
        panel.setCollapsible(true);
        panel.setFrame(true);
        panel.setSize(this.WIDTH, this.HEIGHT);
        panel.setLayout(new FitLayout());
        panel.add(this.grid);
        this.grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");

        this.add(panel);
    }
}
