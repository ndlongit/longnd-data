package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
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
import com.structis.vip.client.event.LoadDelegationTypeEvent;
import com.structis.vip.client.event.LoadDelegationTypeHandler;
import com.structis.vip.client.event.ModifyDelegationTypeEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationTypeServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.DelegationTypeModel;

public class DelegationTypeListPanel extends AbstractPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
    private ListStore<DelegationTypeModel> store = new ListStore<DelegationTypeModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Grid<DelegationTypeModel> grid;
    private PagingLoader<PagingLoadResult<DelegationTypeModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientDelegationTypeServiceAsync clientDelegationTypeService = ClientDelegationTypeServiceAsync.Util.getInstance();

    public DelegationTypeListPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(LoadDelegationTypeEvent.getType(), new LoadDelegationTypeHandler() {

            @Override
            public void onLoadAction(LoadDelegationTypeEvent event) {
                DelegationTypeListPanel.this.disableEvents(true);
                DelegationTypeListPanel.this.initData();
                DelegationTypeListPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                DelegationTypeListPanel.this.disableEvents(true);
                DelegationTypeListPanel.this.initData();
                DelegationTypeListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientDelegationTypeService.getAllTypes(new AsyncCallback<List<DelegationTypeModel>>() {

            @Override
            public void onSuccess(List<DelegationTypeModel> arg0) {
                DelegationTypeListPanel.this.proxy.setData(arg0);
                DelegationTypeListPanel.this.loader.load(0, 50);
                DelegationTypeListPanel.this.store = new ListStore<DelegationTypeModel>(DelegationTypeListPanel.this.loader);
                DelegationTypeListPanel.this.grid.unmask();
            }

            @Override
            public void onFailure(Throwable arg0) {
                DelegationTypeListPanel.this.grid.unmask();
            }
        });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DelegationTypeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<DelegationTypeModel> se) {
                if (se.getSelectedItem() != null) {
                    DelegationTypeListPanel.this.btnModifer.setEnabled(true);
                } else {
                    DelegationTypeListPanel.this.btnModifer.setEnabled(false);
                }
            }
        });

        this.btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_CREATE_FORM);
                ModifyDelegationTypeEvent subEvent = new ModifyDelegationTypeEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                DelegationTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_CREATE_FORM);
                ModifyDelegationTypeEvent subEvent = new ModifyDelegationTypeEvent();
                subEvent.setModel(DelegationTypeListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                DelegationTypeListPanel.this.bus.fireEvent(event);
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

        // topToolBar.add(btnAdd);
        topToolBar.add(this.btnModifer);

        ColumnConfig name = new ColumnConfig(DelegationTypeModel.DELEGATION_TYPE_NAME, this.messages.delegationtypenom(), 200);
        ColumnConfig code = new ColumnConfig(DelegationTypeModel.DELEGATION_TYPE_DESCRIPTION, this.messages.delegationtypedescription(), 580);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<DelegationTypeModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<DelegationTypeModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<DelegationTypeModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(code);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<DelegationTypeModel>(this.store, cm);

        this.grid.setBorders(true);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.delegationtypelist());
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
