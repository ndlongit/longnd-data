package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
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
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
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
import com.google.gwt.user.client.ui.HTML;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.control.ModifyExternControllerEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientExternControllerServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.ExternControllerException;
import com.structis.vip.shared.model.ExternControllerModel;

public class ExternalControllerListPanel extends LayoutContainer {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
    private ListStore<ExternControllerModel> store = new ListStore<ExternControllerModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<ExternControllerModel> grid;
    private PagingLoader<PagingLoadResult<ExternControllerModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientExternControllerServiceAsync clientExternControllerService = ClientExternControllerServiceAsync.Util.getInstance();

    public ExternalControllerListPanel(SimpleEventBus bus) {
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
                ExternalControllerListPanel.this.disableEvents(true);
                ExternalControllerListPanel.this.initData();
                ExternalControllerListPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                ExternalControllerListPanel.this.disableEvents(true);
                ExternalControllerListPanel.this.initData();
                ExternalControllerListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientExternControllerService.findAll(new AsyncCallback<List<ExternControllerModel>>() {

            @Override
            public void onSuccess(List<ExternControllerModel> arg0) {
                ExternalControllerListPanel.this.proxy.setData(arg0);
                ExternalControllerListPanel.this.loader.load(0, 50);
                ExternalControllerListPanel.this.store = new ListStore<ExternControllerModel>(ExternalControllerListPanel.this.loader);
                ExternalControllerListPanel.this.grid.unmask();
            }

            @Override
            public void onFailure(Throwable arg0) {
                ExternalControllerListPanel.this.grid.unmask();
            }
        });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ExternControllerModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<ExternControllerModel> se) {
                if (se.getSelectedItem() != null) {
                    ExternalControllerListPanel.this.btnModifer.setEnabled(true);
                    ExternalControllerListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    ExternalControllerListPanel.this.btnModifer.setEnabled(false);
                    ExternalControllerListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final ExternControllerModel model = ExternalControllerListPanel.this.grid.getSelectionModel().getSelectedItem();
                    ExternalControllerListPanel.this.clientExternControllerService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            ExternalControllerListPanel.this.initData();
                            Info.display(ExternalControllerListPanel.this.messages.commoninfo(),
                                    ExternalControllerListPanel.this.messages.externcontrollermessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof ExternControllerException) {
                                details = ExceptionMessageHandler.getErrorMessage(((ExternControllerException) caught).getCode());
                            }
                            Info.display(ExternalControllerListPanel.this.messages.commonerror(), details);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_CREATE_FORM);
                ModifyExternControllerEvent subEvent = new ModifyExternControllerEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                ExternalControllerListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_CREATE_FORM);
                ModifyExternControllerEvent subEvent = new ModifyExternControllerEvent();
                subEvent.setModel(ExternalControllerListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                ExternalControllerListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ExternControllerModel model = ExternalControllerListPanel.this.grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(ExternalControllerListPanel.this.messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(ExternalControllerListPanel.this.messages.commonDeleteMessage(model.getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(ExternalControllerListPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(ExternalControllerListPanel.this.messages.commonNon());
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

        // ColumnConfig fname = new ColumnConfig(ExternControllerModel.EXC_FIRSTNAME, messages.adminexccontrollerlname(), 150);
        ColumnConfig lname = new ColumnConfig(ExternControllerModel.EXC_NAME, this.messages.adminexccontrollerlname(), 100);
        lname.setAlignment(HorizontalAlignment.LEFT);
        // ColumnConfig nationality = new ColumnConfig(ExternControllerModel.EXC_NATIONALITY, messages.adminexccontrollernationality(), 100);
        // nationality.setAlignment(HorizontalAlignment.CENTER);
        // ColumnConfig address = new ColumnConfig(ExternControllerModel.EXC_ADDRESS, messages.adminexccontrolleraddress(), 150);
        // address.setAlignment(HorizontalAlignment.CENTER);

        GridCellRenderer<ExternControllerModel> fullnameRender = new GridCellRenderer<ExternControllerModel>() {

            @Override
            public Object render(final ExternControllerModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    final ListStore<ExternControllerModel> store, Grid<ExternControllerModel> grid) {
                String fullname = model.getName();
                return new HTML(fullname);
            }
        };

        lname.setRenderer(fullnameRender);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<ExternControllerModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<ExternControllerModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<ExternControllerModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(lname);
        // config.add(nationality);
        // config.add(address);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<ExternControllerModel>(this.store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(ExternControllerModel.EXC_NAME);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.extcontrollerheading());
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
