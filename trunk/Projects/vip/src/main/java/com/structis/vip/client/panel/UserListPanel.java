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
import com.extjs.gxt.ui.client.widget.LayoutContainer;
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
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.LoadUserEvent;
import com.structis.vip.client.event.LoadUserHandler;
import com.structis.vip.client.event.ModifyUserEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.UserModel;
import com.structis.vip.shared.model.UserRoleModel;

public class UserListPanel extends LayoutContainer {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
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

        this.setLayout(new FlowLayout(10));
        this.setScrollMode(Scroll.AUTO);

        this.initUI();
        this.initEvent();
        this.addHandler();
    }

    private void addHandler() {
        this.bus.addHandler(LoadUserEvent.getType(), new LoadUserHandler() {

            @Override
            public void onLoadAction(LoadUserEvent event) {
                UserListPanel.this.disableEvents(true);

                UserListPanel.this.loader.load(0, ConstantClient.DEFAULT_PAGE_SIZE_50);

                UserListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        PagingLoadConfig config = new BasePagingLoadConfig();
        config.setOffset(0);
        config.setLimit(ConstantClient.DEFAULT_PAGE_SIZE_50);

        Map<String, Object> state = this.grid.getState();
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

        this.loader.load(config);
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<UserModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<UserModel> se) {
                if (se.getSelectedItem() != null) {
                    UserListPanel.this.btnModifer.setEnabled(true);
                    UserListPanel.this.btnSupprimer.setEnabled(true);
                    UserListPanel.this.btnConsulter.setEnabled(true);
                } else {
                    UserListPanel.this.btnModifer.setEnabled(false);
                    UserListPanel.this.btnSupprimer.setEnabled(false);
                    UserListPanel.this.btnConsulter.setEnabled(false);
                }
            }
        });

        this.grid.addListener(Events.RowDoubleClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM);
                ModifyUserEvent subEvent = new ModifyUserEvent();
                subEvent.setMode(ModifyUserEvent.MODE_IS_VIEW);
                subEvent.setModel(UserListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                UserListPanel.this.bus.fireEvent(event);
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final UserModel model = UserListPanel.this.grid.getSelectionModel().getSelectedItem();
                    UserListPanel.this.clientUserServiceAsync.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            if (arg0) {
                                UserListPanel.this.initData();
                                Info.display(UserListPanel.this.messages.commoninfo(), UserListPanel.this.messages.userdeletesuccess());
                            } else {
                                Info.display(UserListPanel.this.messages.commonerror(), UserListPanel.this.messages.userdeletefailed());
                            }
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                            Info.display(UserListPanel.this.messages.commonerror(), UserListPanel.this.messages.userdeletefailed());
                        }
                    });
                } else {
                }
            }
        };

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                MessageBox box = new MessageBox();
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.INFO);
                box.setTitle(UserListPanel.this.messages.commonConfirmation());
                box.addCallback(l);
                box.setMessage(UserListPanel.this.messages.userdeletecomfirm());
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(UserListPanel.this.messages.commonOui());
                ((Button) box.getDialog().getButtonBar().getItem(1)).setText(UserListPanel.this.messages.commonNon());
                box.show();
            }
        });

        this.btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM);
                ModifyUserEvent subEvent = new ModifyUserEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                UserListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM);
                ModifyUserEvent subEvent = new ModifyUserEvent();
                subEvent.setModel(UserListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                UserListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnConsulter.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM);
                ModifyUserEvent subEvent = new ModifyUserEvent();
                subEvent.setMode(ModifyUserEvent.MODE_IS_VIEW);
                subEvent.setModel(UserListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                UserListPanel.this.bus.fireEvent(event);
            }
        });
    }

    private void initUI() {

        this.proxy = new RpcProxy<PagingLoadResult<UserModel>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<PagingLoadResult<UserModel>> callback) {
                EntiteModel entiteModel = SessionServiceImpl.getInstance().getEntiteContext();
                UserModel userModel = SessionServiceImpl.getInstance().getUserContext();
                if (entiteModel != null && entiteModel.getEntId() != null && userModel != null) {
                    PagingLoadConfig config = (PagingLoadConfig) loadConfig;
                    UserListPanel.this.clientUserServiceAsync.findUsersByEntiteRemote(config, entiteModel.getEntId(), userModel, callback);
                    UserListPanel.this.toolBar.setEnabled(true);
                }
            }
        };

        this.loader = new BasePagingLoader<PagingLoadResult<UserModel>>(this.proxy);
        this.loader.setRemoteSort(true);

        this.store = new ListStore<UserModel>(this.loader);

        this.toolBar = new PagingToolBar(ConstantClient.DEFAULT_PAGE_SIZE_50);
        this.toolBar.bind(this.loader);

        this.btnAdd = new Button(this.messages.commonCreerbutton());
        this.btnAdd.setStyleAttribute("margin-left", "10px");
        this.btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

        this.btnModifer = new Button(this.messages.commonmodifierbutton());
        this.btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
        this.btnModifer.setEnabled(false);

        this.btnSupprimer = new Button(this.messages.commonSupprimer());
        this.btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
        this.btnSupprimer.setEnabled(false);

        this.btnConsulter = new Button(this.messages.commonConsulterbutton());
        this.btnConsulter.setIcon(IconHelper.createPath("html/view-icon.png"));
        this.btnConsulter.setEnabled(false);

        this.topToolBar = new ToolBar();
        this.topToolBar.add(this.btnConsulter);
        this.topToolBar.add(this.btnAdd);
        this.topToolBar.add(this.btnModifer);
        this.topToolBar.add(this.btnSupprimer);

        ColumnConfig name = new ColumnConfig(UserModel.USER_NAME, this.messages.userusername(), 200);
        ColumnConfig firstName = new ColumnConfig(UserModel.USER_FIRST_NAME, this.messages.userfirstname(), 100);
        ColumnConfig lastName = new ColumnConfig(UserModel.USER_LAST_NAME, this.messages.userlastname(), 100);
        // ColumnConfig roles = new ColumnConfig(UserModel.USER_ROLES, messages.userrolesheader(), 360);
        ColumnConfig roles = new ColumnConfig();

        GridCellRenderer<UserModel> rolesRender = new GridCellRenderer<UserModel>() {

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
        roles.setHeader(this.messages.userrole());
        roles.setId("roles");
        roles.setWidth(285);
        roles.setRenderer(rolesRender);

        ColumnConfig pers = new ColumnConfig();

        GridCellRenderer<UserModel> persRender = new GridCellRenderer<UserModel>() {

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
        pers.setHeader(this.messages.userperimetre());
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
        cm.addHeaderGroup(0, 3, new HeaderGroupConfig(this.messages.userrolesheader(), 1, 2));

        this.grid = new Grid<UserModel>(this.store, cm);

        this.grid.setStateId("pagingGridUser");
        this.grid.setStateful(true);

        this.grid.addListener(Events.Attach, new Listener<GridEvent<UserModel>>() {

            @Override
            public void handleEvent(GridEvent<UserModel> be) {
                UserListPanel.this.initData();
            }
        });

        this.grid.setBorders(true);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        // grid.getView().setForceFit(true);
        this.grid.setColumnLines(true);
        this.grid.setSelectionModel(new GridSelectionModel<UserModel>());
        this.grid.setStripeRows(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setScrollMode(Scroll.AUTO);
        panel.setHeading(this.messages.userheader());
        panel.setBottomComponent(this.toolBar);
        panel.setTopComponent(this.topToolBar);
        panel.setCollapsible(true);
        panel.setFrame(true);
        panel.setSize(this.WIDTH, this.HEIGHT);
        panel.setLayout(new FitLayout());
        panel.add(this.grid);
        panel.setCollapsible(false);
        this.grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");

        this.add(panel);
        new QuickTip(this.grid);

    }
}
