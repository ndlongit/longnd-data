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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.ModifyNatureEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.service.ClientDelegationNatureServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.NatureException;
import com.structis.vip.shared.model.DelegationNatureModel;

public class NatureListPanel extends AbstractPanel {

    private ListStore<DelegationNatureModel> store = new ListStore<DelegationNatureModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<DelegationNatureModel> grid;
    private PagingLoader<PagingLoadResult<DelegationNatureModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientDelegationNatureServiceAsync clientDelegationNatureService = ClientDelegationNatureServiceAsync.Util.getInstance();

    public NatureListPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.setLayout(new FlowLayout(10));
        this.setScrollMode(Scroll.AUTO);

        this.initUI();
        this.initEvent();
        this.addHandler();
    }

    private void addHandler() {
        this.bus.addHandler(LoadDocumentEvent.getType(), new LoadDocumentHandler() {

            @Override
            public void onLoadAction(LoadDocumentEvent event) {
                disableEvents(true);
                initData();
                disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                disableEvents(true);
                initData();
                disableEvents(false);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(messages.commonloadingdata());
        this.clientDelegationNatureService.findNatureByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<DelegationNatureModel>>() {

                    @Override
                    public void onSuccess(List<DelegationNatureModel> arg0) {
                        proxy.setData(arg0);
                        loader.load(0, 50);
                        store = new ListStore<DelegationNatureModel>(loader);
                        grid.unmask();
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        grid.unmask();
                    }
                });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DelegationNatureModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<DelegationNatureModel> se) {
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
                    final DelegationNatureModel model = grid.getSelectionModel().getSelectedItem();
                    clientDelegationNatureService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            initData();
                            Info.display(messages.commoninfo(), messages.naturemessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof NatureException) {
                                details = ExceptionMessageHandler.getErrorMessage(((NatureException) caught).getCode());
                            }
                            Info.display(messages.commonerror(), details);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_NATURE_CREATE_FORM);
                ModifyNatureEvent subEvent = new ModifyNatureEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_NATURE_CREATE_FORM);
                ModifyNatureEvent subEvent = new ModifyNatureEvent();
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DelegationNatureModel model = grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(messages.commonDeleteMessage(model.getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonNon());
                    box.show();
                }
            }
        });
    }

    private void initUI() {
        PagingToolBar toolBar = new PagingToolBar(50);
        ToolBar topToolBar = new ToolBar();

        this.btnAdd = new Button(messages.commonCreerbutton());
        this.btnAdd.setStyleAttribute("margin-left", "10px");
        this.btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

        this.btnModifer = new Button(messages.commonmodifierbutton());
        this.btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
        this.btnModifer.setEnabled(false);

        this.btnSupprimer = new Button(messages.commonSupprimer());
        this.btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
        this.btnSupprimer.setEnabled(false);

        topToolBar.add(this.btnAdd);
        topToolBar.add(this.btnModifer);
        topToolBar.add(this.btnSupprimer);

        ColumnConfig name = new ColumnConfig(DelegationNatureModel.NAME, messages.naturenom(), WIDTH - 50);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<DelegationNatureModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<DelegationNatureModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<DelegationNatureModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<DelegationNatureModel>(this.store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(DelegationNatureModel.NAME);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(messages.naturelistedesnatures());
        panel.setBottomComponent(toolBar);
        panel.setTopComponent(topToolBar);
        panel.setCollapsible(true);
        panel.setFrame(true);
        panel.setSize(WIDTH, HEIGHT);
        panel.setLayout(new FitLayout());
        panel.add(this.grid);
        this.grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");

        this.add(panel);
    }
}
