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
import com.structis.vip.client.event.ModifyDelegantTypeGroupEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegantTypeGroupServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.LanguageException;
import com.structis.vip.shared.model.DelegantTypeGroupModel;

public class DelegantTypeGroupListPanel extends LayoutContainer {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
    private ListStore<DelegantTypeGroupModel> store = new ListStore<DelegantTypeGroupModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<DelegantTypeGroupModel> grid;
    private PagingLoader<PagingLoadResult<DelegantTypeGroupModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientDelegantTypeGroupServiceAsync clientDelegantTypeGroupService = ClientDelegantTypeGroupServiceAsync.Util.getInstance();

    public DelegantTypeGroupListPanel(SimpleEventBus bus) {
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
                DelegantTypeGroupListPanel.this.disableEvents(true);
                DelegantTypeGroupListPanel.this.initData();
                DelegantTypeGroupListPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                DelegantTypeGroupListPanel.this.disableEvents(true);
                DelegantTypeGroupListPanel.this.initData();
                DelegantTypeGroupListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientDelegantTypeGroupService.getDelegantTypeGroups(new AsyncCallback<List<DelegantTypeGroupModel>>() {

            @Override
            public void onSuccess(List<DelegantTypeGroupModel> arg0) {
                DelegantTypeGroupListPanel.this.proxy.setData(arg0);
                DelegantTypeGroupListPanel.this.loader.load(0, 50);
                DelegantTypeGroupListPanel.this.store = new ListStore<DelegantTypeGroupModel>(DelegantTypeGroupListPanel.this.loader);
                DelegantTypeGroupListPanel.this.grid.unmask();
            }

            @Override
            public void onFailure(Throwable arg0) {
                DelegantTypeGroupListPanel.this.grid.unmask();
            }
        });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DelegantTypeGroupModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<DelegantTypeGroupModel> se) {
                if (se.getSelectedItem() != null) {
                    DelegantTypeGroupListPanel.this.btnModifer.setEnabled(true);
                    DelegantTypeGroupListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    DelegantTypeGroupListPanel.this.btnModifer.setEnabled(false);
                    DelegantTypeGroupListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final DelegantTypeGroupModel model = DelegantTypeGroupListPanel.this.grid.getSelectionModel().getSelectedItem();
                    DelegantTypeGroupListPanel.this.clientDelegantTypeGroupService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            DelegantTypeGroupListPanel.this.initData();
                            Info.display(DelegantTypeGroupListPanel.this.messages.commoninfo(),
                                    DelegantTypeGroupListPanel.this.messages.deleganttypegroupmessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof LanguageException) {
                                details = ExceptionMessageHandler.getErrorMessage(((LanguageException) caught).getCode());
                            }
                            Info.display(DelegantTypeGroupListPanel.this.messages.commonerror(), details);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_CREATE_FORM);
                ModifyDelegantTypeGroupEvent subEvent = new ModifyDelegantTypeGroupEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                DelegantTypeGroupListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_CREATE_FORM);
                ModifyDelegantTypeGroupEvent subEvent = new ModifyDelegantTypeGroupEvent();
                subEvent.setModel(DelegantTypeGroupListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                DelegantTypeGroupListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DelegantTypeGroupModel model = DelegantTypeGroupListPanel.this.grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(DelegantTypeGroupListPanel.this.messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(DelegantTypeGroupListPanel.this.messages.commonDeleteMessage(model.getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(DelegantTypeGroupListPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(DelegantTypeGroupListPanel.this.messages.commonNon());
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

        ColumnConfig group = new ColumnConfig(DelegantTypeGroupModel.DELEGANT_TYPE_GROUP_GROUP, this.messages.deleganttypegroupgroup(), 200);
        ColumnConfig name = new ColumnConfig(DelegantTypeGroupModel.DELEGANT_TYPE_GROUP_NAME, this.messages.deleganttypegroupnom(), 200);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<DelegantTypeGroupModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<DelegantTypeGroupModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<DelegantTypeGroupModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(group);
        config.add(name);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<DelegantTypeGroupModel>(this.store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(DelegantTypeGroupModel.DELEGANT_TYPE_GROUP_NAME);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.deleganttypegrouplistedesdeleganttypegroups());
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
