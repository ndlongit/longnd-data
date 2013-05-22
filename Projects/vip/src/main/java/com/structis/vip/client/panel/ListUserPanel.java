package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.VipEvents;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.UserModel;

public class ListUserPanel extends AbstractRefRemoteContentPanel<UserModel> {

    private final Messages messages = GWT.create(Messages.class);

    ClientUserServiceAsync userService = ClientUserServiceAsync.Util.getInstance();
    private ListStore<BeanModel> userStore;
    private Grid<BeanModel> userGrid;
    private int colRankWidth = 40;
    private TextField<String> catLabelTextfield;
    private Button btnAdd;

    public ListUserPanel(SimpleEventBus bus) {
        this.buildPanel();
    }

    public void onLoadPanel() {
        this.userGrid.getStore().getLoader().load();
    }

    @Override
    protected void loadPaging(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<UserModel>> callback) {
        // userService.getAllUser1(callback);
        System.out.println("pagin");
    }

    @Override
    protected void initProperty() {
        super.initProperty();
        this.pagingSize = 50;

        this.panelHeigh = 600;
    }

    @Override
    protected void createContent() {
        this.setHeaderVisible(false);
        this.setBodyBorder(false);
        this.setBorders(false);
        this.setFrame(false);
        // Initilisation du formulaire
        this.catLabelTextfield = new TextField<String>();
        this.catLabelTextfield.setEmptyText("user...");
        this.catLabelTextfield.setWidth(200);
        this.catLabelTextfield.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == KeyCodes.KEY_ENTER) {
                    // addProjectCategory();
                }

            }

        });
        this.btnAdd = new Button("Add");
        this.btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                // addProjectCategory();
                ListUserPanel.this.fireEvent(VipEvents.Success, new BaseEvent(null));
            }
        });
        Button bDelete = new Button("Delete", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                for (BeanModel model : ListUserPanel.this.userStore.getModels()) {
                    if ((Boolean) model.get("delete")) {
                    }
                }
            }
        });
        ToolBar userTB = new ToolBar();
        // userTB.add(catLabelTextfield);
        userTB.add(this.btnAdd);
        userTB.add(bDelete);
        RpcProxy<List<UserModel>> userProxy = new RpcProxy<List<UserModel>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<List<UserModel>> callback) {
                ListUserPanel.this.userService.findUsers(callback);
            }
        };

        ModelReader catReader = new ModelReader();
        // loader and store
        final ListLoader<ListLoadResult<UserModel>> userLoader = new BaseListLoader<ListLoadResult<UserModel>>(userProxy, catReader);
        this.userStore = new ListStore<BeanModel>(userLoader);

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        RowNumberer r = new RowNumberer();
        r.setHeader("Id");
        r.setId("id");
        r.setSortable(true);
        r.setMenuDisabled(true);
        r.setWidth(this.colRankWidth);
        configs.add(r);

        ColumnConfig firstNameColumn = new ColumnConfig();
        firstNameColumn.setId("firstName");
        firstNameColumn.setHeader("First Name");
        firstNameColumn.setSortable(false);
        firstNameColumn.setMenuDisabled(true);
        firstNameColumn.setWidth(200);
        configs.add(firstNameColumn);

        ColumnConfig lastNameColumn = new ColumnConfig();
        lastNameColumn.setId("lastName");
        lastNameColumn.setHeader("Last Name");
        lastNameColumn.setSortable(false);
        lastNameColumn.setMenuDisabled(true);
        lastNameColumn.setWidth(200);
        configs.add(lastNameColumn);
        CheckColumnConfig checkColumn = new CheckColumnConfig();
        checkColumn.setId("delete");
        checkColumn.setHeader("Delete");
        checkColumn.setWidth(90);
        CellEditor checkBoxEditor = new CellEditor(new CheckBox());
        checkColumn.setEditor(checkBoxEditor);
        configs.add(checkColumn);

        ColumnModel cm = new ColumnModel(configs);

        for (ColumnConfig columnConfig : configs) {
            columnConfig.setMenuDisabled(true);
        }

        this.userGrid = new Grid<BeanModel>(this.userStore, cm);
        this.userGrid.setLoadMask(true);
        this.userGrid.setColumnLines(true);
        this.userGrid.setStripeRows(true);
        this.userGrid.setBorders(true);
        this.userGrid.setAutoExpandMax(1000);
        this.userGrid.setAutoExpandColumn("lastName");
        this.userGrid.setBorders(false);
        this.userGrid.addPlugin(checkColumn);
        this.userGrid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.userGrid.setHeight(400);
        WindowResizeBinder.bind(this.userGrid);
        ContentPanel userGridPanel = new ContentPanel();
        // catGridPanel.setBodyBorder(true);
        userGridPanel.setLayout(new FitLayout());
        userGridPanel.setHeading("Demo Grid");

        userGridPanel.add(this.userGrid);
        // userGridPanel.setTopComponent(userGrid);

        this.add(userGridPanel);
        this.setTopComponent(userGridPanel);
        this.userGrid.getStore().getLoader().load();
        this.addListener(VipEvents.Success, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                Info.display(ListUserPanel.this.messages.commonInfoHeader(), ListUserPanel.this.messages.commonMajSucces());
            }
        });
    }

    @Override
    protected void onCallUpdateListe() {

    }
}
