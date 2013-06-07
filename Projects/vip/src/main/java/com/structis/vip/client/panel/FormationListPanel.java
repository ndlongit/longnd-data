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
import com.structis.vip.client.event.ModifyFormationEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientFormationServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.model.CollaborateurFormationModel;
import com.structis.vip.shared.model.FormationModel;

public class FormationListPanel extends AbstractPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
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
                FormationListPanel.this.disableEvents(true);
                FormationListPanel.this.initData();
                FormationListPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                FormationListPanel.this.disableEvents(true);
                FormationListPanel.this.initData();
                FormationListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientFormationService.findByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<FormationModel>>() {

                    @Override
                    public void onSuccess(List<FormationModel> arg0) {
                        FormationListPanel.this.proxy.setData(arg0);
                        FormationListPanel.this.loader.load(0, 50);
                        FormationListPanel.this.store = new ListStore<FormationModel>(FormationListPanel.this.loader);
                        FormationListPanel.this.grid.unmask();
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        FormationListPanel.this.grid.unmask();
                    }
                });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<FormationModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<FormationModel> se) {
                if (se.getSelectedItem() != null) {
                    FormationListPanel.this.btnModifer.setEnabled(true);
                    FormationListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    FormationListPanel.this.btnModifer.setEnabled(false);
                    FormationListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final FormationModel model = FormationListPanel.this.grid.getSelectionModel().getSelectedItem();

                    FormationListPanel.this.clientCollaboratureService.findByFormationId(model.getId(),
                            new AsyncCallback<List<CollaborateurFormationModel>>() {

                                @Override
                                public void onSuccess(List<CollaborateurFormationModel> arg0) {
                                    if (arg0.size() == 0) {
                                        FormationListPanel.this.clientFormationService.delete(model, new AsyncCallback<Boolean>() {

                                            @Override
                                            public void onSuccess(Boolean arg0) {
                                                FormationListPanel.this.initData();
                                                Info.display(FormationListPanel.this.messages.commoninfo(),
                                                        FormationListPanel.this.messages.formationmessagedeletesuccessfully());
                                            }

                                            @Override
                                            public void onFailure(Throwable caught) {
                                                String details = caught.getMessage();
                                                if (caught instanceof DelegationException) {
                                                    details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());
                                                }
                                                Info.display(FormationListPanel.this.messages.commonerror(), details);
                                            }
                                        });
                                    } else {
                                        Info.display(FormationListPanel.this.messages.commonerror(),
                                                FormationListPanel.this.messages.formationerror());
                                    }
                                }

                                @Override
                                public void onFailure(Throwable arg0) {
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_CREATE_FORM);
                ModifyFormationEvent subEvent = new ModifyFormationEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                FormationListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_CREATE_FORM);
                ModifyFormationEvent subEvent = new ModifyFormationEvent();
                subEvent.setModel(FormationListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                FormationListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                FormationModel model = FormationListPanel.this.grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(FormationListPanel.this.messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(FormationListPanel.this.messages.commonDeleteMessage(model.getLabel()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(FormationListPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(FormationListPanel.this.messages.commonNon());
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

        ColumnConfig name = new ColumnConfig(FormationModel.FOR_LABEL, this.messages.formationnom(), 250);
        ColumnConfig description = new ColumnConfig(FormationModel.FOR_DESCRIPTION, this.messages.formationdescription(), 530);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<FormationModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<FormationModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<FormationModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(description);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<FormationModel>(this.store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(FormationModel.FOR_LABEL);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.formationlistedesformations());
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
