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
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.ModifyChantierTypeEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientChantierTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.ChantierTypeException;
import com.structis.vip.shared.model.ChantierTypeModel;

public class ChantierTypeListPanel extends LayoutContainer {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
    private ListStore<ChantierTypeModel> store = new ListStore<ChantierTypeModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<ChantierTypeModel> grid;
    private ColumnModel columnModel;
    private PagingLoader<PagingLoadResult<ChantierTypeModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientChantierTypeServiceAsync clientChantierTypeService = ClientChantierTypeServiceAsync.Util.getInstance();

    public ChantierTypeListPanel(SimpleEventBus bus) {
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
                ChantierTypeListPanel.this.disableEvents(true);
                ChantierTypeListPanel.this.initData();
                ChantierTypeListPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                ChantierTypeListPanel.this.disableEvents(true);
                ChantierTypeListPanel.this.initData();
                ChantierTypeListPanel.this.disableEvents(false);
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
        this.clientChantierTypeService.findChantierByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<ChantierTypeModel>>() {

                    @Override
                    public void onSuccess(List<ChantierTypeModel> arg0) {
                        ChantierTypeListPanel.this.proxy.setData(arg0);
                        ChantierTypeListPanel.this.loader.load(0, 50);
                        ChantierTypeListPanel.this.store = new ListStore<ChantierTypeModel>(ChantierTypeListPanel.this.loader);
                        ChantierTypeListPanel.this.grid.unmask();
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        ChantierTypeListPanel.this.grid.unmask();
                    }
                });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ChantierTypeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<ChantierTypeModel> se) {
                if (se.getSelectedItem() != null) {
                    ChantierTypeListPanel.this.btnModifer.setEnabled(true);
                    ChantierTypeListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    ChantierTypeListPanel.this.btnModifer.setEnabled(false);
                    ChantierTypeListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final ChantierTypeModel model = ChantierTypeListPanel.this.grid.getSelectionModel().getSelectedItem();
                    ChantierTypeListPanel.this.clientChantierTypeService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            ChantierTypeListPanel.this.initData();
                            Info.display(ChantierTypeListPanel.this.messages.commoninfo(),
                                    ChantierTypeListPanel.this.messages.chantiertypemessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof ChantierTypeException) {
                                details = ExceptionMessageHandler.getErrorMessage(((ChantierTypeException) caught).getCode());
                            }
                            Info.display(ChantierTypeListPanel.this.messages.commonerror(), details);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_CREATE_FORM);
                ModifyChantierTypeEvent subEvent = new ModifyChantierTypeEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                ChantierTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_CREATE_FORM);
                ModifyChantierTypeEvent subEvent = new ModifyChantierTypeEvent();
                subEvent.setModel(ChantierTypeListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                ChantierTypeListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                MessageBox box = new MessageBox();
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.INFO);
                box.setTitle(ChantierTypeListPanel.this.messages.commonConfirmation());
                box.addCallback(l);
                box.setMessage(ChantierTypeListPanel.this.messages.delegationmodeldeleteconfirm());
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(ChantierTypeListPanel.this.messages.commonOui());
                ((Button) box.getDialog().getButtonBar().getItem(1)).setText(ChantierTypeListPanel.this.messages.commonNon());
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

        ColumnConfig label = new ColumnConfig(ChantierTypeModel.CTY_LABEL, this.messages.chantiertypelabel(), 200);
        ColumnConfig endDate = new ColumnConfig(ChantierTypeModel.CTY_ENDDATE, this.messages.chantiertypeendDate(), 150);

        GridCellRenderer<ChantierTypeModel> endDateRenderer = new GridCellRenderer<ChantierTypeModel>() {

            @Override
            public Object render(final ChantierTypeModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    final ListStore<ChantierTypeModel> store, Grid<ChantierTypeModel> grid) {
                final Label lbl = new Label();
                if (model.getEndDate() != null) {
                    lbl.setText(DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT_DDMM).format(model.getEndDate()));
                }
                return lbl;
            }
        };
        endDate.setRenderer(endDateRenderer);

        ColumnConfig isSubdelegable = new ColumnConfig(ChantierTypeModel.CTY_IS_SUBDELEGABLE, this.messages.chantiertypechantierSubdelegable(), 150);

        GridCellRenderer<ChantierTypeModel> isSubdelegableRenderer = new GridCellRenderer<ChantierTypeModel>() {

            @Override
            public Object render(final ChantierTypeModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    final ListStore<ChantierTypeModel> store, Grid<ChantierTypeModel> grid) {
                final Label lbl = new Label();
                if (model.getIsSubdelegable() != null) {
                    lbl.setText((model.getIsSubdelegable() == 1) ? ChantierTypeListPanel.this.messages.commonOui()
                            : ChantierTypeListPanel.this.messages.commonNon());
                }
                return lbl;
            }
        };
        isSubdelegable.setRenderer(isSubdelegableRenderer);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<ChantierTypeModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<ChantierTypeModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<ChantierTypeModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(label);
        config.add(endDate);
        config.add(isSubdelegable);

        this.columnModel = new ColumnModel(config);

        this.grid = new Grid<ChantierTypeModel>(this.store, this.columnModel);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(ChantierTypeModel.CTY_LABEL);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.chantiertypelistedeschantiers());
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
