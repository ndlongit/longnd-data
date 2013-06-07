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
import com.structis.vip.client.event.ModifyCollaborateurTypeEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.LanguageException;
import com.structis.vip.shared.model.CollaborateurTypeModel;

public class CollaborateurTypeListPanel extends AbstractPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
    private ListStore<CollaborateurTypeModel> store = new ListStore<CollaborateurTypeModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<CollaborateurTypeModel> grid;
    private PagingLoader<PagingLoadResult<CollaborateurTypeModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientCollaborateurTypeServiceAsync clientCollaborateurTypeService = ClientCollaborateurTypeServiceAsync.Util.getInstance();

    public CollaborateurTypeListPanel(SimpleEventBus bus) {
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
                CollaborateurTypeListPanel.this.disableEvents(true);
                CollaborateurTypeListPanel.this.initData();
                CollaborateurTypeListPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                CollaborateurTypeListPanel.this.disableEvents(true);
                CollaborateurTypeListPanel.this.initData();
                CollaborateurTypeListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientCollaborateurTypeService.getCollaborateurTypeByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<CollaborateurTypeModel>>() {

                    @Override
                    public void onSuccess(List<CollaborateurTypeModel> arg0) {
                        CollaborateurTypeListPanel.this.proxy.setData(arg0);
                        CollaborateurTypeListPanel.this.loader.load(0, 50);
                        CollaborateurTypeListPanel.this.store = new ListStore<CollaborateurTypeModel>(CollaborateurTypeListPanel.this.loader);
                        CollaborateurTypeListPanel.this.grid.unmask();
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        CollaborateurTypeListPanel.this.grid.unmask();
                    }
                });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<CollaborateurTypeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<CollaborateurTypeModel> se) {
                if (se.getSelectedItem() != null) {
                    CollaborateurTypeListPanel.this.btnModifer.setEnabled(true);
                    CollaborateurTypeListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    CollaborateurTypeListPanel.this.btnModifer.setEnabled(false);
                    CollaborateurTypeListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final CollaborateurTypeModel model = CollaborateurTypeListPanel.this.grid.getSelectionModel().getSelectedItem();
                    CollaborateurTypeListPanel.this.clientCollaborateurTypeService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            CollaborateurTypeListPanel.this.initData();
                            Info.display(CollaborateurTypeListPanel.this.messages.commoninfo(),
                                    CollaborateurTypeListPanel.this.messages.collaborateurtypemessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof LanguageException) {
                                details = ExceptionMessageHandler.getErrorMessage(((LanguageException) caught).getCode());
                            }
                            Info.display(CollaborateurTypeListPanel.this.messages.commonerror(), details);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_CREATE_FORM);
                ModifyCollaborateurTypeEvent subEvent = new ModifyCollaborateurTypeEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                CollaborateurTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_CREATE_FORM);
                ModifyCollaborateurTypeEvent subEvent = new ModifyCollaborateurTypeEvent();
                subEvent.setModel(CollaborateurTypeListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                CollaborateurTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                CollaborateurTypeModel model = CollaborateurTypeListPanel.this.grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(CollaborateurTypeListPanel.this.messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(CollaborateurTypeListPanel.this.messages.commonDeleteMessage(model.getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(CollaborateurTypeListPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(CollaborateurTypeListPanel.this.messages.commonNon());
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

        ColumnConfig group = new ColumnConfig("group.name", this.messages.collaborateurtypegroup(), 200);
        ColumnConfig name = new ColumnConfig(CollaborateurTypeModel.COT_NAME, this.messages.collaborateurtypenom(), 200);
        new ColumnConfig(CollaborateurTypeModel.COT_DESCRIPTION, this.messages.collaborateurtypedesc(), 200);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<CollaborateurTypeModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<CollaborateurTypeModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<CollaborateurTypeModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(group);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<CollaborateurTypeModel>(this.store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(CollaborateurTypeModel.COT_NAME);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.collaborateurtypelistedescollaborateurtypes());
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
