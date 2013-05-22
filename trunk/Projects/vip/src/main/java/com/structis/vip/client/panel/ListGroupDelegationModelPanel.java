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
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationModelEvent;
import com.structis.vip.client.event.LoadGroupDelegationModelEvent;
import com.structis.vip.client.event.LoadGroupDelegationModelHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationModelServiceAsync;
import com.structis.vip.client.service.ClientDemDomServiceAsync;
import com.structis.vip.client.service.ClientFieldRuleServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.DelegationMdlModel;
import com.structis.vip.shared.model.GroupDelegationMdlModel;

public class ListGroupDelegationModelPanel extends LayoutContainer {

    private static int WIDTH = 800;
    private static int PAGING = 50;
    private final Messages messages = GWT.create(Messages.class);

    private SimpleEventBus bus;

    private ListStore<GroupDelegationMdlModel> store;
    private EditorGrid<GroupDelegationMdlModel> grid;

    private ClientDelegationModelServiceAsync clientDelegationModelServiceAsync = ClientDelegationModelServiceAsync.Util.getInstance();
    private ClientDemDomServiceAsync clientDemDomServiceAsync = ClientDemDomServiceAsync.Util.getInstance();
    private ClientFieldRuleServiceAsync clientFieldRuleServiceAsync = ClientFieldRuleServiceAsync.Util.getInstance();

    private PagingLoader<PagingLoadResult<GroupDelegationMdlModel>> loader;
    private PagingModelMemoryProxy proxy;

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;

    public ListGroupDelegationModelPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.setLayout(new FlowLayout(10));
        this.setScrollMode(Scroll.AUTO);

        this.initGrid();
        this.initEvent();

        // add handler when click on Valider button
        bus.addHandler(LoadGroupDelegationModelEvent.getType(), new LoadGroupDelegationModelHandler() {

            @Override
            public void onLoadAction(LoadGroupDelegationModelEvent event) {
                ListGroupDelegationModelPanel.this.disableEvents(true);
                ListGroupDelegationModelPanel.this.initData();
                ListGroupDelegationModelPanel.this.disableEvents(false);
            }
        });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<GroupDelegationMdlModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<GroupDelegationMdlModel> se) {
                if (se.getSelectedItem() != null) {
                    ListGroupDelegationModelPanel.this.btnModifer.setEnabled(true);
                    ListGroupDelegationModelPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    ListGroupDelegationModelPanel.this.btnModifer.setEnabled(false);
                    ListGroupDelegationModelPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        this.btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ListGroupDelegationModelPanel.this.disableEvents(true);
                try {
                    DelegationModelEvent event = new DelegationModelEvent();
                    event.setMode(DelegationModelEvent.MODE_IS_NEW);
                    event.setGroup(0);

                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DELEGATION_MODEL_ADMIN_FORM);
                    contentEvent.setEvent(event);
                    ListGroupDelegationModelPanel.this.bus.fireEvent(contentEvent);
                } catch (Exception e) {
                }
                ListGroupDelegationModelPanel.this.disableEvents(false);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ListGroupDelegationModelPanel.this.disableEvents(true);
                try {
                    final GroupDelegationMdlModel model = ListGroupDelegationModelPanel.this.grid.getSelectionModel().getSelectedItem();
                    ContentEvent contentEvent = new ContentEvent();

                    DelegationModelEvent event = new DelegationModelEvent();
                    event.setGroup(model.getGroup());
                    event.setLanguageModel(model.getLanguage());
                    event.setEntiteModel(model.getEntite());
                    event.setNatureModel(model.getDelegationNature());
                    event.setMode(DelegationModelEvent.MODE_IS_EDIT);
                    event.setHasMultipleDelegation(model.getHasMultipleDelegation());
                    event.setHasMultipleDelegataire(model.getHasMultipleDelegataire());
                    event.setSubDelegation(model.getSubDelegation());

                    contentEvent.setEvent(event);
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DELEGATION_MODEL_ADMIN_FORM);

                    ListGroupDelegationModelPanel.this.bus.fireEvent(contentEvent);
                } catch (Exception e) {
                }
                ListGroupDelegationModelPanel.this.disableEvents(false);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

                    @Override
                    public void handleEvent(MessageBoxEvent ce) {
                        Button btn = ce.getButtonClicked();
                        String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                        if (txtReturn.equals(btn.getText())) {
                            final GroupDelegationMdlModel model = ListGroupDelegationModelPanel.this.grid.getSelectionModel().getSelectedItem();

                            ListGroupDelegationModelPanel.this.clientDelegationModelServiceAsync.deleteByGroup(model.getGroup(),
                                    new AsyncCallback<Boolean>() {

                                        @Override
                                        public void onSuccess(Boolean arg0) {
                                            ListGroupDelegationModelPanel.this.clientDemDomServiceAsync.deleteByGroup(model.getGroup(),
                                                    new AsyncCallback<Boolean>() {

                                                        @Override
                                                        public void onSuccess(Boolean arg0) {
                                                        }

                                                        @Override
                                                        public void onFailure(Throwable arg0) {
                                                        }
                                                    });
                                            ListGroupDelegationModelPanel.this.clientFieldRuleServiceAsync.deleteByGroup(model.getGroup(),
                                                    new AsyncCallback<Boolean>() {

                                                        @Override
                                                        public void onSuccess(Boolean arg0) {
                                                        }

                                                        @Override
                                                        public void onFailure(Throwable arg0) {
                                                        }
                                                    });
                                            ListGroupDelegationModelPanel.this.initData();
                                        }

                                        @Override
                                        public void onFailure(Throwable arg0) {
                                        }
                                    });
                        }
                    }
                };

                GroupDelegationMdlModel model = ListGroupDelegationModelPanel.this.grid.getSelectionModel().getSelectedItem();

                MessageBox box = new MessageBox();
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.INFO);
                box.setTitle(ListGroupDelegationModelPanel.this.messages.commonConfirmation());
                box.addCallback(l);
                box.setMessage(ListGroupDelegationModelPanel.this.messages.commonDeleteMessage(model.getDelegationNature().getName()));
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(ListGroupDelegationModelPanel.this.messages.commonOui());
                ((Button) box.getDialog().getButtonBar().getItem(1)).setText(ListGroupDelegationModelPanel.this.messages.commonNon());
                box.show();
            }
        });
    }

    private void initGrid() {
        PagingToolBar pagingBar = new PagingToolBar(PAGING);
        ToolBar toolBar = new ToolBar();

        this.btnAdd = new Button(this.messages.delegationmodeladdbutton());
        this.btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

        this.btnModifer = new Button(this.messages.commonmodifierbutton());
        this.btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
        this.btnModifer.setEnabled(false);

        this.btnSupprimer = new Button(this.messages.commonSupprimer());
        this.btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
        this.btnSupprimer.setEnabled(false);

        toolBar.add(this.btnAdd);
        toolBar.add(this.btnModifer);
        toolBar.add(this.btnSupprimer);

        ColumnConfig cfEntite = new ColumnConfig("entite.name", this.messages.delegationentite(), 50);
        ColumnConfig cfLanguage = new ColumnConfig("language.name", this.messages.commonlanguage(), 70);
        ColumnConfig cfNature = new ColumnConfig("nature.name", this.messages.nature(), 250);
        ColumnConfig cfPerimetreType = new ColumnConfig("perimetreType", this.messages.delegationmodelperimetretype(), 250);
        ColumnConfig cfDelegantType = new ColumnConfig("delegantType", this.messages.delegationmodeldeleganttype(), 130);

        // GridCellRenderer<GroupDelegationMdlModel> actionRender = new GridCellRenderer<GroupDelegationMdlModel>() {
        //
        // @Override
        // public Object render(GroupDelegationMdlModel model, String property,
        // com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex,
        // ListStore<GroupDelegationMdlModel> store, Grid<GroupDelegationMdlModel> grid) {
        // return buildActionColumn(model);
        // }
        // };
        // ColumnConfig cfAction = new ColumnConfig("action", messages.delegationmodelaction(), 70);
        // cfAction.setRenderer(actionRender);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(cfEntite);
        config.add(cfNature);
        config.add(cfLanguage);
        config.add(cfPerimetreType);
        config.add(cfDelegantType);
        // config.add(cfAction);

        final ColumnModel cm = new ColumnModel(config);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<GroupDelegationMdlModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<GroupDelegationMdlModel>>(this.proxy);
        this.loader.setRemoteSort(false);
        this.loader.setSortField("nature.name");
        this.store = new ListStore<GroupDelegationMdlModel>(this.loader);
        pagingBar.bind(this.loader);
        this.loader.load(0, PAGING);

        this.grid = new EditorGrid<GroupDelegationMdlModel>(this.store, cm);
        this.grid.setBorders(true);
        this.grid.setColumnLines(true);
        this.grid.setSelectionModel(new GridSelectionModel<GroupDelegationMdlModel>());
        this.grid.setStripeRows(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.delegationmodelheading());
        panel.setTopComponent(toolBar);
        panel.setBottomComponent(pagingBar);
        panel.setSize(WIDTH, 480);
        panel.setFrame(true);
        panel.setLayout(new FitLayout());
        panel.add(this.grid);
        this.grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");
        this.add(panel);
    }

    public void initData() {
        this.grid.mask(this.messages.commonloadingdata());
        this.clientDelegationModelServiceAsync.getAllDelegationModelsByEntite(SessionServiceImpl.getInstance().getEntiteContext(),
                new AsyncCallback<List<DelegationMdlModel>>() {

                    @Override
                    public void onSuccess(List<DelegationMdlModel> arg0) {
                        ListGroupDelegationModelPanel.this.store.removeAll();
                        List<GroupDelegationMdlModel> groups = new ArrayList<GroupDelegationMdlModel>();
                        for (DelegationMdlModel mdl : arg0) {
                            boolean isCreate = true;
                            for (GroupDelegationMdlModel group : groups) {
                                if (group.getGroup().equals(mdl.getGroup())) {
                                    isCreate = false;
                                    if (mdl.getPerimetreType() != null) {
                                        String str = group.getPerimetreType();
                                        if (str == null) {
                                            str = "";
                                        }
                                        // PDG, DG, DGD
                                        if (mdl.getPerimetreType() != null && !this.checkExistIn(str, mdl.getPerimetreType().getName())) {
                                            if (str.length() > 0) {
                                                str += ", ";
                                            }
                                            str += mdl.getPerimetreType().getName();
                                            group.setPerimetreType(str);
                                        }
                                    }
                                    if (mdl.getCollaborateurType() != null) {
                                        String str = group.getDelegantType();
                                        if (str == null) {
                                            str = "";
                                        }
                                        if (mdl.getCollaborateurType() != null && !this.checkExistIn(str, mdl.getCollaborateurType().getName())) {
                                            if (str.length() > 0) {
                                                str += ", ";
                                            }
                                            str += mdl.getCollaborateurType().getName();
                                            group.setDelegantType(str);
                                        }
                                    }
                                    break;
                                }
                            }
                            if (isCreate) {
                                GroupDelegationMdlModel groupDelegationMdlModel = new GroupDelegationMdlModel();
                                groupDelegationMdlModel.setEntite(mdl.getEntite());
                                groupDelegationMdlModel.setDelegationNature(mdl.getDelegationNature());
                                groupDelegationMdlModel.setLanguage(mdl.getLanguage());
                                groupDelegationMdlModel.setGroup(mdl.getGroup());
                                groupDelegationMdlModel.setHasMultipleDelegation(mdl.getHasMultipleDelegation());
                                groupDelegationMdlModel.setHasMultipleDelegataire(mdl.getHasMultipleDelegataire());
                                groupDelegationMdlModel.setSubDelegation(mdl.getSubDelegation());
                                if (mdl.getPerimetreType() != null) {
                                    groupDelegationMdlModel.setPerimetreType(mdl.getPerimetreType().getName());
                                }
                                if (mdl.getCollaborateurType() != null) {
                                    groupDelegationMdlModel.setDelegantType(mdl.getCollaborateurType().getName());
                                }
                                groups.add(groupDelegationMdlModel);
                            }
                        }
                        ListGroupDelegationModelPanel.this.proxy.setData(groups);
                        ListGroupDelegationModelPanel.this.loader.load(0, PAGING);
                        ListGroupDelegationModelPanel.this.store = new ListStore<GroupDelegationMdlModel>(ListGroupDelegationModelPanel.this.loader);
                        ListGroupDelegationModelPanel.this.grid.unmask();
                    }

                    private boolean checkExistIn(String str, String name) {
                        if (str != null) {
                            String[] list = str.split(", ");
                            for (String item : list) {
                                if (name.equalsIgnoreCase(item)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        ListGroupDelegationModelPanel.this.grid.unmask();
                    }
                });
    }

    private class ActionMenu extends SelectionListener<MenuEvent> {

        private int index;
        private GroupDelegationMdlModel model;

        public ActionMenu(int index, GroupDelegationMdlModel model) {
            this.index = index;
            this.model = model;
        }

        @Override
        public void componentSelected(MenuEvent ce) {
            final ContentEvent contentEvent = new ContentEvent();
            final DelegationModelEvent event = new DelegationModelEvent();
            event.setGroup(this.model.getGroup());
            event.setLanguageModel(this.model.getLanguage());
            event.setEntiteModel(this.model.getEntite());
            event.setNatureModel(this.model.getDelegationNature());

            switch (this.index) {
            case 1:
                event.setMode(DelegationModelEvent.MODE_IS_EDIT);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DELEGATION_MODEL_ADMIN_FORM);
                break;
            case 2:
                final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

                    @Override
                    public void handleEvent(MessageBoxEvent ce) {
                        Button btn = ce.getButtonClicked();
                        String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                        if (txtReturn.equals(btn.getText())) {
                            ListGroupDelegationModelPanel.this.clientDelegationModelServiceAsync.deleteByGroup(ActionMenu.this.model.getGroup(),
                                    new AsyncCallback<Boolean>() {

                                        @Override
                                        public void onSuccess(Boolean arg0) {
                                            ListGroupDelegationModelPanel.this.clientDemDomServiceAsync.deleteByGroup(
                                                    ActionMenu.this.model.getGroup(), new AsyncCallback<Boolean>() {

                                                        @Override
                                                        public void onSuccess(Boolean arg0) {
                                                        }

                                                        @Override
                                                        public void onFailure(Throwable arg0) {
                                                        }
                                                    });
                                            ListGroupDelegationModelPanel.this.clientFieldRuleServiceAsync.deleteByGroup(
                                                    ActionMenu.this.model.getGroup(), new AsyncCallback<Boolean>() {

                                                        @Override
                                                        public void onSuccess(Boolean arg0) {
                                                        }

                                                        @Override
                                                        public void onFailure(Throwable arg0) {
                                                        }
                                                    });
                                            ListGroupDelegationModelPanel.this.initData();
                                        }

                                        @Override
                                        public void onFailure(Throwable arg0) {
                                        }
                                    });
                        }
                    }
                };

                MessageBox box = new MessageBox();
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.INFO);
                box.setTitle(ListGroupDelegationModelPanel.this.messages.commonConfirmation());
                box.addCallback(l);
                box.setMessage(ListGroupDelegationModelPanel.this.messages.delegationmodeldeleteconfirm());
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(ListGroupDelegationModelPanel.this.messages.commonOui());
                ((Button) box.getDialog().getButtonBar().getItem(1)).setText(ListGroupDelegationModelPanel.this.messages.commonNon());
                box.show();

                break;
            }
            contentEvent.setEvent(event);
            ListGroupDelegationModelPanel.this.bus.fireEvent(contentEvent);
        }
    }
}
