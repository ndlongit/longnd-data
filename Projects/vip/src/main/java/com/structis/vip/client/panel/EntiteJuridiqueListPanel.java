package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
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
import com.extjs.gxt.ui.client.widget.form.Radio;
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
import com.structis.vip.client.event.ModifyEntiteJuridiqueEvent;
import com.structis.vip.client.event.ModifyEntiteJuridiqueHandler;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientEntiteJuridiqueServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.EntiteJuridiqueException;
import com.structis.vip.shared.model.EntiteJuridiqueModel;
import com.structis.vip.shared.model.LanguageModel;

public class EntiteJuridiqueListPanel extends AbstractPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
    private ListStore<EntiteJuridiqueModel> store = new ListStore<EntiteJuridiqueModel>();

    private Button btnView;
    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<EntiteJuridiqueModel> grid;
    private PagingLoader<PagingLoadResult<EntiteJuridiqueModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientEntiteJuridiqueServiceAsync clientEntiteJuridiqueService = ClientEntiteJuridiqueServiceAsync.Util.getInstance();

    public EntiteJuridiqueListPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(ModifyEntiteJuridiqueEvent.getType(), new ModifyEntiteJuridiqueHandler() {

            @Override
            public void onLoadAction(ModifyEntiteJuridiqueEvent event) {
                EntiteJuridiqueListPanel.this.disableEvents(true);
                EntiteJuridiqueListPanel.this.initData();
                EntiteJuridiqueListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientEntiteJuridiqueService.findByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<EntiteJuridiqueModel>>() {

                    @Override
                    public void onSuccess(List<EntiteJuridiqueModel> arg0) {
                        EntiteJuridiqueListPanel.this.proxy.setData(arg0);
                        EntiteJuridiqueListPanel.this.loader.load(0, 50);
                        EntiteJuridiqueListPanel.this.store = new ListStore<EntiteJuridiqueModel>(EntiteJuridiqueListPanel.this.loader);
                        EntiteJuridiqueListPanel.this.grid.unmask();
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        EntiteJuridiqueListPanel.this.grid.unmask();
                    }
                });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<EntiteJuridiqueModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<EntiteJuridiqueModel> se) {
                if (se.getSelectedItem() != null) {
                    EntiteJuridiqueListPanel.this.btnView.setEnabled(true);
                    EntiteJuridiqueListPanel.this.btnModifer.setEnabled(true);
                    EntiteJuridiqueListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    EntiteJuridiqueListPanel.this.btnView.setEnabled(false);
                    EntiteJuridiqueListPanel.this.btnModifer.setEnabled(false);
                    EntiteJuridiqueListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final EntiteJuridiqueModel model = EntiteJuridiqueListPanel.this.grid.getSelectionModel().getSelectedItem();
                    EntiteJuridiqueListPanel.this.clientEntiteJuridiqueService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            EntiteJuridiqueListPanel.this.initData();
                            Info.display(EntiteJuridiqueListPanel.this.messages.commoninfo(),
                                    EntiteJuridiqueListPanel.this.messages.languagemessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof EntiteJuridiqueException) {
                                details = ExceptionMessageHandler.getErrorMessage(((EntiteJuridiqueException) caught).getCode());
                            }
                            Info.display(EntiteJuridiqueListPanel.this.messages.commonerror(), details);
                        }
                    });
                } else {
                }
            }
        };

        this.btnView.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_VIEW_FORM);

                ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                subEvent.setModel(EntiteJuridiqueListPanel.this.grid.getSelectionModel().getSelectedItem());
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_VIEW_FORM);
                event.setEvent(subEvent);

                EntiteJuridiqueListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);

                ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);
                subEvent.setModel(null);
                event.setEvent(subEvent);

                EntiteJuridiqueListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);

                ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);
                subEvent.setModel(EntiteJuridiqueListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);

                EntiteJuridiqueListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                EntiteJuridiqueModel model = EntiteJuridiqueListPanel.this.grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(EntiteJuridiqueListPanel.this.messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(EntiteJuridiqueListPanel.this.messages.commonDeleteMessage(model.getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(EntiteJuridiqueListPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(EntiteJuridiqueListPanel.this.messages.commonNon());
                    box.show();
                }
            }
        });
    }

    private void initUI() {
        PagingToolBar toolBar = new PagingToolBar(50);
        ToolBar topToolBar = new ToolBar();

        this.btnView = new Button(this.messages.commonConsulterbutton());
        this.btnView.setIcon(IconHelper.createPath("html/view-icon.png"));
        this.btnView.setEnabled(false);

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
        topToolBar.add(this.btnView);
        topToolBar.add(this.btnModifer);
        topToolBar.add(this.btnSupprimer);

        ColumnConfig name = new ColumnConfig(EntiteJuridiqueModel.ENTITE_JURIDIQUE_NAME, this.messages.entiteJuridiqueName(), 50);
        ColumnConfig address = new ColumnConfig(EntiteJuridiqueModel.ENTITE_JURIDIQUE_ADDRESS, this.messages.entiteJuridiqueAddress(), 150);
        ColumnConfig isDefault = new ColumnConfig(EntiteJuridiqueModel.ENTITE_JURIDIQUE_IS_DEFAULT, this.messages.languagedefault(), 50);
        isDefault.setAlignment(HorizontalAlignment.CENTER);

        GridCellRenderer<EntiteJuridiqueModel> defaultRender = new GridCellRenderer<EntiteJuridiqueModel>() {

            @Override
            public Object render(final EntiteJuridiqueModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    final ListStore<EntiteJuridiqueModel> store, Grid<EntiteJuridiqueModel> grid) {
                final Radio cb = new Radio();
                cb.setValue((model != null && model.getIsDefault() != null && model.getIsDefault() == 1) ? true : false);
                cb.addListener(Events.OnClick, new Listener<BaseEvent>() {

                    @Override
                    public void handleEvent(BaseEvent be) {
                        model.setIsDefault((cb.getValue()) ? 1 : 0);

                        for (EntiteJuridiqueModel item : store.getModels()) {
                            if (item.getId() == model.getId())
                                continue;

                            item.setIsDefault(0);
                            EntiteJuridiqueListPanel.this.clientEntiteJuridiqueService.update(item, new AsyncCallback<EntiteJuridiqueModel>() {

                                @Override
                                public void onSuccess(EntiteJuridiqueModel arg0) {
                                }

                                @Override
                                public void onFailure(Throwable arg0) {

                                }
                            });
                        }

                        EntiteJuridiqueListPanel.this.clientEntiteJuridiqueService.update(model, new AsyncCallback<EntiteJuridiqueModel>() {

                            @Override
                            public void onSuccess(EntiteJuridiqueModel arg0) {
                                EntiteJuridiqueListPanel.this.initData();
                            }

                            @Override
                            public void onFailure(Throwable arg0) {

                            }
                        });
                    }
                });
                return cb;
            }
        };

        isDefault.setRenderer(defaultRender);

        this.proxy = new PagingModelMemoryProxy(new ArrayList<EntiteJuridiqueModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<EntiteJuridiqueModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<EntiteJuridiqueModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(address);
        config.add(isDefault);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<EntiteJuridiqueModel>(this.store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(LanguageModel.LAG_NAME);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.addPlugin(filters);
        this.grid.setLoadMask(true);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(this.messages.entiteJuridiqueList());
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
