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
import com.structis.vip.client.event.ModifyControlTypeEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientControlTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.model.ControlTypeModel;

public class ControlTypeListPanel extends AbstractPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
    private ListStore<ControlTypeModel> store = new ListStore<ControlTypeModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<ControlTypeModel> grid;
    private PagingLoader<PagingLoadResult<ControlTypeModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientControlTypeServiceAsync clientControlTypeService = ClientControlTypeServiceAsync.Util.getInstance();

    public ControlTypeListPanel(SimpleEventBus bus) {
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
                ControlTypeListPanel.this.disableEvents(true);
                ControlTypeListPanel.this.initData();
                ControlTypeListPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                ControlTypeListPanel.this.disableEvents(true);
                ControlTypeListPanel.this.initData();
                ControlTypeListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientControlTypeService.findByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<ControlTypeModel>>() {

                    @Override
                    public void onSuccess(List<ControlTypeModel> arg0) {
                        ControlTypeListPanel.this.proxy.setData(arg0);
                        ControlTypeListPanel.this.loader.load(0, 50);
                        ControlTypeListPanel.this.store = new ListStore<ControlTypeModel>(ControlTypeListPanel.this.loader);
                        ControlTypeListPanel.this.grid.unmask();
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        ControlTypeListPanel.this.grid.unmask();
                    }
                });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ControlTypeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<ControlTypeModel> se) {
                if (se.getSelectedItem() != null) {
                    ControlTypeListPanel.this.btnModifer.setEnabled(true);
                    ControlTypeListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    ControlTypeListPanel.this.btnModifer.setEnabled(false);
                    ControlTypeListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final ControlTypeModel model = ControlTypeListPanel.this.grid.getSelectionModel().getSelectedItem();
                    ControlTypeListPanel.this.clientControlTypeService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            ControlTypeListPanel.this.initData();
                            Info.display(ControlTypeListPanel.this.messages.commoninfo(),
                                    ControlTypeListPanel.this.messages.controltypemessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof DelegationException) {
                                details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());
                            }
                            Info.display(ControlTypeListPanel.this.messages.commonerror(), details);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_CREATE_FORM);
                ModifyControlTypeEvent subEvent = new ModifyControlTypeEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                ControlTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_CREATE_FORM);
                ModifyControlTypeEvent subEvent = new ModifyControlTypeEvent();
                subEvent.setModel(ControlTypeListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                ControlTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ControlTypeModel model = ControlTypeListPanel.this.grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(ControlTypeListPanel.this.messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(ControlTypeListPanel.this.messages.commonDeleteMessage(model.getLabel()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(ControlTypeListPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(ControlTypeListPanel.this.messages.commonNon());
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

        ColumnConfig name = new ColumnConfig(ControlTypeModel.CON_LABEL, this.messages.controltypenom(), 250);
        ColumnConfig description = new ColumnConfig(ControlTypeModel.CON_DESCRIPTION, this.messages.controltypedescription(), 530);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<ControlTypeModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<ControlTypeModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<ControlTypeModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(description);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<ControlTypeModel>(this.store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(ControlTypeModel.CON_LABEL);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.controltypelistedestypedecontrol());
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
