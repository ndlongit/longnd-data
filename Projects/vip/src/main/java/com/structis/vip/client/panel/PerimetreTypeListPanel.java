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
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
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
import com.structis.vip.client.event.ModifyPerimetreTypeEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientPerimetreTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.PerimetreTypeException;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class PerimetreTypeListPanel extends AbstractPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
    private ListStore<PerimetreTypeModel> store = new ListStore<PerimetreTypeModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<PerimetreTypeModel> grid;
    private ColumnModel columnModel;
    private PagingLoader<PagingLoadResult<PerimetreTypeModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientPerimetreTypeServiceAsync clientPerimetreTypeService = ClientPerimetreTypeServiceAsync.Util.getInstance();

    public PerimetreTypeListPanel(SimpleEventBus bus) {
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
                PerimetreTypeListPanel.this.disableEvents(true);
                PerimetreTypeListPanel.this.initData();
                PerimetreTypeListPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                PerimetreTypeListPanel.this.disableEvents(true);
                PerimetreTypeListPanel.this.initData();
                PerimetreTypeListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        // add BYTP
        // if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
        if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
            this.columnModel.setHidden(1, false);
        } else {
            this.columnModel.setHidden(1, true);
        }

        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientPerimetreTypeService.getPerimetreTypes(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<PerimetreTypeModel>>() {

                    @Override
                    public void onSuccess(List<PerimetreTypeModel> arg0) {
                        PerimetreTypeListPanel.this.proxy.setData(arg0);
                        PerimetreTypeListPanel.this.loader.load(0, 50);
                        PerimetreTypeListPanel.this.store = new ListStore<PerimetreTypeModel>(PerimetreTypeListPanel.this.loader);
                        PerimetreTypeListPanel.this.grid.unmask();
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        PerimetreTypeListPanel.this.grid.unmask();
                    }
                });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<PerimetreTypeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<PerimetreTypeModel> se) {
                if (se.getSelectedItem() != null) {
                    PerimetreTypeListPanel.this.btnModifer.setEnabled(true);
                    PerimetreTypeListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    PerimetreTypeListPanel.this.btnModifer.setEnabled(false);
                    PerimetreTypeListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final PerimetreTypeModel model = PerimetreTypeListPanel.this.grid.getSelectionModel().getSelectedItem();
                    PerimetreTypeListPanel.this.clientPerimetreTypeService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            PerimetreTypeListPanel.this.initData();
                            Info.display(PerimetreTypeListPanel.this.messages.commoninfo(),
                                    PerimetreTypeListPanel.this.messages.perimetretypemessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof PerimetreTypeException) {
                                details = ExceptionMessageHandler.getErrorMessage(((PerimetreTypeException) caught).getCode());
                            }
                            Info.display(PerimetreTypeListPanel.this.messages.commonerror(), details);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_CREATE_FORM);
                ModifyPerimetreTypeEvent subEvent = new ModifyPerimetreTypeEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                PerimetreTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_CREATE_FORM);
                ModifyPerimetreTypeEvent subEvent = new ModifyPerimetreTypeEvent();
                subEvent.setModel(PerimetreTypeListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                PerimetreTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                MessageBox box = new MessageBox();
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.INFO);
                box.setTitle(PerimetreTypeListPanel.this.messages.commonConfirmation());
                box.addCallback(l);
                box.setMessage(PerimetreTypeListPanel.this.messages.delegationmodeldeleteconfirm());
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(PerimetreTypeListPanel.this.messages.commonOui());
                ((Button) box.getDialog().getButtonBar().getItem(1)).setText(PerimetreTypeListPanel.this.messages.commonNon());
                box.show();
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

        ColumnConfig name = new ColumnConfig(PerimetreTypeModel.PERIMETRE_TYPE_NAME, this.messages.perimetretypename(), 200);

        ColumnConfig isSubdelegable = new ColumnConfig(PerimetreTypeModel.PERIMETRE_TYPE_IS_SUBDELEGABLE, this.messages.perimetretypeSubdelegable(),
                150);

        GridCellRenderer<PerimetreTypeModel> isSubdelegableRenderer = new GridCellRenderer<PerimetreTypeModel>() {

            @Override
            public Object render(final PerimetreTypeModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    final ListStore<PerimetreTypeModel> store, Grid<PerimetreTypeModel> grid) {
                final Label lbl = new Label();
                if (model.getIsSubdelegable() != null) {
                    lbl.setText((model.getIsSubdelegable() == 1) ? PerimetreTypeListPanel.this.messages.commonOui()
                            : PerimetreTypeListPanel.this.messages.commonNon());
                }
                return lbl;
            }
        };
        isSubdelegable.setRenderer(isSubdelegableRenderer);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<PerimetreTypeModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<PerimetreTypeModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<PerimetreTypeModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);

        config.add(isSubdelegable);

        this.columnModel = new ColumnModel(config);

        this.grid = new Grid<PerimetreTypeModel>(this.store, this.columnModel);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(PerimetreTypeModel.PERIMETRE_TYPE_NAME);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.perimetretypelistedestypes());
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
