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
import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.ModifyDelegantTypeGroupEvent;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.service.ClientDelegantTypeGroupServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.DelegantTypeGroupModel;

public class DelegantTypeGroupListPanel extends AbstractPanel {

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

        setLayout(new FlowLayout(10));
        setScrollMode(Scroll.AUTO);

        initUI();
        initEvent();
        addHandler();
    }

    private void addHandler() {
        bus.addHandler(LoadDocumentEvent.getType(), new LoadDocumentHandler() {

            @Override
            public void onLoadAction(LoadDocumentEvent event) {
                disableEvents(true);
                initData();
                disableEvents(false);
            }
        });

        bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                disableEvents(true);
                initData();
                disableEvents(false);
            }
        });
    }

    private void initData() {
        store.removeAll();
        grid.mask(messages.commonloadingdata());
        clientDelegantTypeGroupService.getDelegantTypeGroups(new AsyncCallbackWithErrorResolution<List<DelegantTypeGroupModel>>() {

            @Override
            public void onSuccess(List<DelegantTypeGroupModel> arg0) {
                proxy.setData(arg0);
                loader.load(0, 50);
                store = new ListStore<DelegantTypeGroupModel>(loader);
                grid.unmask();
            }

            @Override
            public void onFailure(Throwable arg0) {
                grid.unmask();
            }
        });
    }

    private void initEvent() {
        grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DelegantTypeGroupModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<DelegantTypeGroupModel> se) {
                if (se.getSelectedItem() != null) {
                    btnModifer.setEnabled(true);
                    btnSupprimer.setEnabled(true);
                } else {
                    btnModifer.setEnabled(false);
                    btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final DelegantTypeGroupModel model = grid.getSelectionModel().getSelectedItem();
                    clientDelegantTypeGroupService.delete(model, new AsyncCallbackWithErrorResolution<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            initData();
                            Info.display(DelegantTypeGroupListPanel.messages.commoninfo(), messages.deleganttypegroupmessagedeletesuccessfully());
                        }
                    });
                } else {
                }
            }
        };

        btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_CREATE_FORM);
                ModifyDelegantTypeGroupEvent subEvent = new ModifyDelegantTypeGroupEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_CREATE_FORM);
                ModifyDelegantTypeGroupEvent subEvent = new ModifyDelegantTypeGroupEvent();
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DelegantTypeGroupModel model = grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(DelegantTypeGroupListPanel.messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(DelegantTypeGroupListPanel.messages.commonDeleteMessage(model.getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(DelegantTypeGroupListPanel.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(DelegantTypeGroupListPanel.messages.commonNon());
                    box.show();
                }
            }
        });
    }

    private void initUI() {
        PagingToolBar toolBar = new PagingToolBar(50);
        ToolBar topToolBar = new ToolBar();

        btnAdd = new Button(messages.commonCreerbutton());
        btnAdd.setStyleAttribute("margin-left", "10px");
        btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

        btnModifer = new Button(messages.commonmodifierbutton());
        btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
        btnModifer.setEnabled(false);

        btnSupprimer = new Button(messages.commonSupprimer());
        btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
        btnSupprimer.setEnabled(false);

        topToolBar.add(btnAdd);
        topToolBar.add(btnModifer);
        topToolBar.add(btnSupprimer);

        ColumnConfig group = new ColumnConfig(DelegantTypeGroupModel.DELEGANT_TYPE_GROUP_GROUP, messages.deleganttypegroupgroup(), 200);
        ColumnConfig name = new ColumnConfig(DelegantTypeGroupModel.DELEGANT_TYPE_GROUP_NAME, messages.deleganttypegroupnom(), 200);

        proxy = new PagingModelMemoryProxy(new ArrayList<DelegantTypeGroupModel>());
        loader = new BasePagingLoader<PagingLoadResult<DelegantTypeGroupModel>>(proxy);
        loader.setRemoteSort(true);
        store = new ListStore<DelegantTypeGroupModel>(loader);
        toolBar.bind(loader);
        loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(group);
        config.add(name);

        final ColumnModel cm = new ColumnModel(config);

        grid = new Grid<DelegantTypeGroupModel>(store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(DelegantTypeGroupModel.DELEGANT_TYPE_GROUP_NAME);
        filters.addFilter(nameFilter);

        grid.setBorders(true);
        grid.addPlugin(filters);
        grid.setLoadMask(true);
        grid.getView().setAutoFill(true);
        grid.getView().setForceFit(true);
        WindowResizeBinder.bind(grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(messages.deleganttypegrouplistedesdeleganttypegroups());
        panel.setBottomComponent(toolBar);
        panel.setTopComponent(topToolBar);
        panel.setCollapsible(true);
        panel.setFrame(true);
        panel.setSize(WIDTH, HEIGHT);
        panel.setLayout(new FitLayout());
        panel.add(grid);
        grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");

        add(panel);
    }
}
