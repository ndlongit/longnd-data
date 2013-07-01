package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.LoadUserEvent;
import com.structis.vip.client.event.LoadUserHandler;
import com.structis.vip.client.event.ModifyUserEvent;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.UserModel;
import com.structis.vip.shared.model.UserRoleModel;

public class UserListPanel extends AbstractPanel {

    private ListStore<UserModel> store = new ListStore<UserModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Button btnConsulter;

    private Grid<UserModel> grid;
    private PagingLoader<PagingLoadResult<UserModel>> loader;
    private RpcProxy<PagingLoadResult<UserModel>> proxy;
    private PagingToolBar toolBar;
    private ToolBar topToolBar;

    private ClientUserServiceAsync clientUserServiceAsync = ClientUserServiceAsync.Util.getInstance();

    public UserListPanel(SimpleEventBus bus) {
        this.bus = bus;

        setLayout(new FlowLayout(10));
        setScrollMode(Scroll.AUTO);

        initUI();
        initEvent();
        addHandler();
    }

    private void addHandler() {
        bus.addHandler(LoadUserEvent.getType(), new LoadUserHandler() {

            @Override
            public void onLoadAction(LoadUserEvent event) {
                disableEvents(true);

                loader.load(0, ClientConstant.DEFAULT_PAGE_SIZE);

                disableEvents(false);
            }
        });
    }

    private void initData() {
        PagingLoadConfig config = new BasePagingLoadConfig();
        config.setOffset(0);
        config.setLimit(ClientConstant.DEFAULT_PAGE_SIZE);

        Map<String, Object> state = grid.getState();
        if (state.containsKey("offset")) {
            int offset = (Integer) state.get("offset");
            int limit = (Integer) state.get("limit");
            config.setOffset(offset);
            config.setLimit(limit);
        }

        if (state.containsKey("sortField")) {
            config.setSortField((String) state.get("sortField"));
            config.setSortDir(SortDir.valueOf((String) state.get("sortDir")));
        }

        loader.load(config);
    }

    private void initEvent() {
        grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<UserModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<UserModel> se) {
                btnModifer.setEnabled(se.getSelectedItem() != null);
                btnSupprimer.setEnabled(se.getSelectedItem() != null);
                btnConsulter.setEnabled(se.getSelectedItem() != null);
            }
        });

        grid.addListener(Events.RowDoubleClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM);
                ModifyUserEvent subEvent = new ModifyUserEvent();
                subEvent.setMode(ModifyUserEvent.MODE_IS_VIEW);
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final UserModel model = grid.getSelectionModel().getSelectedItem();
                    clientUserServiceAsync.delete(model, new AsyncCallbackWithErrorResolution<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            if (arg0) {
                                initData();
                            }

                            Info.display(messages.commoninfo(), messages.userdeletesuccess());
                        }
                    });
                }
            }
        };

        btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                MessageBox box = new MessageBox();
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.INFO);
                box.setTitle(messages.commonConfirmation());
                box.addCallback(l);
                box.setMessage(messages.userdeletecomfirm());
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonOui());
                ((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonNon());
                box.show();
            }
        });

        btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM);
                ModifyUserEvent subEvent = new ModifyUserEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM);
                ModifyUserEvent subEvent = new ModifyUserEvent();
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        btnConsulter.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM);
                ModifyUserEvent subEvent = new ModifyUserEvent();
                subEvent.setMode(ModifyUserEvent.MODE_IS_VIEW);
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });
    }

    private void initUI() {

        proxy = new RpcProxy<PagingLoadResult<UserModel>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<PagingLoadResult<UserModel>> callback) {
                EntiteModel entiteModel = SessionServiceImpl.getInstance().getEntiteContext();
                UserModel userModel = SessionServiceImpl.getInstance().getUserContext();
                if (entiteModel != null && entiteModel.getEntId() != null && userModel != null) {
                    PagingLoadConfig config = (PagingLoadConfig) loadConfig;
                    clientUserServiceAsync.findUsersByEntiteRemote(config, entiteModel.getEntId(), userModel, callback);
                    toolBar.setEnabled(true);
                }
            }
        };

        loader = new BasePagingLoader<PagingLoadResult<UserModel>>(proxy);
        loader.setRemoteSort(true);

        store = new ListStore<UserModel>(loader);

        toolBar = new PagingToolBar(ClientConstant.DEFAULT_PAGE_SIZE);
        toolBar.bind(loader);

        btnAdd = new Button(messages.commonCreerbutton());
        btnAdd.setStyleAttribute("margin-left", "10px");
        btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

        btnModifer = new Button(messages.commonmodifierbutton());
        btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
        btnModifer.setEnabled(false);

        btnSupprimer = new Button(messages.commonSupprimer());
        btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
        btnSupprimer.setEnabled(false);

        btnConsulter = new Button(messages.commonConsulterbutton());
        btnConsulter.setIcon(IconHelper.createPath("html/view-icon.png"));
        btnConsulter.setEnabled(false);

        topToolBar = new ToolBar();
        topToolBar.add(btnConsulter);
        topToolBar.add(btnAdd);
        topToolBar.add(btnModifer);
        topToolBar.add(btnSupprimer);

        ColumnConfig name = new ColumnConfig(UserModel.USER_NAME, messages.userusername(), 200);
        ColumnConfig firstName = new ColumnConfig(UserModel.USER_FIRST_NAME, messages.userfirstname(), 100);
        ColumnConfig lastName = new ColumnConfig(UserModel.USER_LAST_NAME, messages.userlastname(), 100);
        // ColumnConfig roles = new ColumnConfig(UserModel.USER_ROLES,
        // messages.userrolesheader(), 360);
        ColumnConfig roles = new ColumnConfig();

        GridCellRenderer<UserModel> rolesRender = new GridCellRenderer<UserModel>() {

            @SuppressWarnings("unchecked")
            @Override
            public Object render(final UserModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<UserModel> store,
                    Grid<UserModel> pGrid) {
                StringBuffer sb = new StringBuffer();
                for (UserRoleModel s : (List<UserRoleModel>) model.get(UserModel.USER_USER_ROLES)) {
                    sb.append("<span qtip='" + s.getRole().getName() + "'>" + s.getRole().getName() + "</span>");
                    sb.append("<BR>");
                }
                return new Html(sb.toString());
            }
        };
        roles.setHeader(messages.userrole());
        roles.setId("roles");
        roles.setWidth(285);
        roles.setRenderer(rolesRender);

        ColumnConfig pers = new ColumnConfig();

        GridCellRenderer<UserModel> persRender = new GridCellRenderer<UserModel>() {

            @SuppressWarnings("unchecked")
            @Override
            public Object render(final UserModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<UserModel> store,
                    Grid<UserModel> pGrid) {
                StringBuffer sb = new StringBuffer();
                for (UserRoleModel s : (List<UserRoleModel>) model.get(UserModel.USER_USER_ROLES)) {
                    if (s.getPerimetre() != null) {
                        sb.append("<span qtip='" + s.getPerimetre().getName() + "'>" + s.getPerimetre().getName() + "</span>");
                    }
                    sb.append("<BR>");

                }
                return new Html(sb.toString());
            }
        };
        pers.setHeader(messages.userperimetre());
        pers.setId("Perimetres");
        pers.setWidth(285);
        pers.setRenderer(persRender);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(firstName);
        config.add(lastName);
        config.add(roles);
        config.add(pers);

        final ColumnModel cm = new ColumnModel(config);
        cm.addHeaderGroup(0, 3, new HeaderGroupConfig(messages.userrolesheader(), 1, 2));

        grid = new Grid<UserModel>(store, cm);

        grid.setStateId("pagingGridUser");
        grid.setStateful(true);

        grid.addListener(Events.Attach, new Listener<GridEvent<UserModel>>() {

            @Override
            public void handleEvent(GridEvent<UserModel> be) {
                initData();
            }
        });

        grid.setBorders(true);
        grid.setLoadMask(true);
        grid.getView().setAutoFill(true);
        // grid.getView().setForceFit(true);
        grid.setColumnLines(true);
        grid.setSelectionModel(new GridSelectionModel<UserModel>());
        grid.setStripeRows(true);
        WindowResizeBinder.bind(grid);

        ContentPanel panel = new ContentPanel();
        panel.setScrollMode(Scroll.AUTO);
        panel.setHeading(messages.userheader());
        panel.setBottomComponent(toolBar);
        panel.setTopComponent(topToolBar);
        panel.setCollapsible(true);
        panel.setFrame(true);
        panel.setSize(WIDTH, HEIGHT);
        panel.setLayout(new FitLayout());
        panel.add(grid);
        panel.setCollapsible(false);
        grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");

        add(panel);
        new QuickTip(grid);

    }
}
