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
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.ModifyFormationEvent;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientFormationServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.CollaborateurFormationModel;
import com.structis.vip.shared.model.FormationModel;

public class FormationListPanel extends AbstractPanel {

    private ListStore<FormationModel> store = new ListStore<FormationModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<FormationModel> grid;
    private PagingLoader<PagingLoadResult<FormationModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientCollaborateurServiceAsync clientCollaboratureService = ClientCollaborateurServiceAsync.Util.getInstance();
    private ClientFormationServiceAsync clientFormationService = ClientFormationServiceAsync.Util.getInstance();

    public FormationListPanel(SimpleEventBus bus) {
        this.bus = bus;

        setLayout(new FlowLayout(10));
        setScrollMode(Scroll.AUTO);

        initUI();
        initEvent();
        addHandler();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
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
        clientFormationService.findByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<FormationModel>>() {

                    @Override
                    public void onSuccess(List<FormationModel> arg0) {
                        proxy.setData(arg0);
                        loader.load(0, 50);
                        store = new ListStore<FormationModel>(loader);
                        grid.unmask();
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        grid.unmask();
                    }
                });
    }

    private void initEvent() {
        grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<FormationModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<FormationModel> se) {
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
                    final FormationModel model = grid.getSelectionModel().getSelectedItem();

                    clientCollaboratureService.findByFormationId(model.getId(),
                            new AsyncCallbackWithErrorResolution<List<CollaborateurFormationModel>>() {

                                @Override
                                public void onSuccess(List<CollaborateurFormationModel> arg0) {
                                    if (arg0.size() == 0) {
                                        clientFormationService.delete(model, new AsyncCallbackWithErrorResolution<Boolean>() {

                                            @Override
                                            public void onSuccess(Boolean arg0) {
                                                initData();
                                                Info.display(messages.commoninfo(), messages.formationmessagedeletesuccessfully());
                                            }
                                        });
                                    } else {
                                        Info.display(messages.commonerror(), messages.formationerror());
                                    }
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_CREATE_FORM);
                ModifyFormationEvent subEvent = new ModifyFormationEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_CREATE_FORM);
                ModifyFormationEvent subEvent = new ModifyFormationEvent();
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                FormationModel model = grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(messages.commonDeleteMessage(model.getLabel()));
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

        ColumnConfig name = new ColumnConfig(FormationModel.FOR_LABEL, messages.formationnom(), 250);
        ColumnConfig description = new ColumnConfig(FormationModel.FOR_DESCRIPTION, messages.formationdescription(), 530);

        proxy = new PagingModelMemoryProxy(new ArrayList<FormationModel>());
        loader = new BasePagingLoader<PagingLoadResult<FormationModel>>(proxy);
        loader.setRemoteSort(true);
        store = new ListStore<FormationModel>(loader);
        toolBar.bind(loader);
        loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(description);

        final ColumnModel cm = new ColumnModel(config);

        grid = new Grid<FormationModel>(store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(FormationModel.FOR_LABEL);
        filters.addFilter(nameFilter);

        grid.setBorders(true);
        grid.addPlugin(filters);
        grid.setLoadMask(true);
        grid.getView().setAutoFill(true);
        grid.getView().setForceFit(true);
        WindowResizeBinder.bind(grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(messages.formationlistedesformations());
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
