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
import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyEntiteJuridiqueEvent;
import com.structis.vip.client.event.ModifyEntiteJuridiqueHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.service.ClientEntiteJuridiqueServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.EntiteJuridiqueException;
import com.structis.vip.shared.model.EntiteJuridiqueModel;
import com.structis.vip.shared.model.LanguageModel;

public class EntiteJuridiqueListPanel extends AbstractPanel {

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

        setLayout(new FlowLayout(10));
        setScrollMode(Scroll.AUTO);

        initUI();
        initEvent();
        addHandler();
    }

    private void addHandler() {
        bus.addHandler(ModifyEntiteJuridiqueEvent.getType(), new ModifyEntiteJuridiqueHandler() {

            @Override
            public void onLoadAction(ModifyEntiteJuridiqueEvent event) {
                disableEvents(true);
                initData();
                disableEvents(false);
            }
        });
    }

    private void initData() {
        store.removeAll();
        grid.mask(messages.commonloadingdata());
        clientEntiteJuridiqueService.findByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<EntiteJuridiqueModel>>() {

                    @Override
                    public void onSuccess(List<EntiteJuridiqueModel> arg0) {
                        proxy.setData(arg0);
                        loader.load(0, 50);
                        store = new ListStore<EntiteJuridiqueModel>(loader);
                        grid.unmask();
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        grid.unmask();
                    }
                });
    }

    private void initEvent() {
        grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<EntiteJuridiqueModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<EntiteJuridiqueModel> se) {
                boolean enabled = se.getSelectedItem() != null;
                btnView.setEnabled(enabled);
                btnModifer.setEnabled(enabled);
                btnSupprimer.setEnabled(enabled);
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final EntiteJuridiqueModel model = grid.getSelectionModel().getSelectedItem();
                    clientEntiteJuridiqueService.delete(model, new AsyncCallbackWithErrorResolution<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            initData();
                            Info.display(messages.commoninfo(), messages.languagemessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof EntiteJuridiqueException) {
                                details = ExceptionMessageHandler.getErrorMessage(((EntiteJuridiqueException) caught).getCode());
                            }
                            Info.display(messages.commonerror(), details);
                        }
                    });
                } else {
                }
            }
        };

        btnView.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_VIEW_FORM);

                ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_VIEW_FORM);
                event.setEvent(subEvent);

                bus.fireEvent(event);
            }
        });

        btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);

                ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);
                subEvent.setModel(null);
                event.setEvent(subEvent);

                bus.fireEvent(event);
            }
        });

        btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);

                ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);

                bus.fireEvent(event);
            }
        });

        btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                EntiteJuridiqueModel model = grid.getSelectionModel().getSelectedItem();
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

        btnView = new Button(messages.commonConsulterbutton());
        btnView.setIcon(IconHelper.createPath("html/view-icon.png"));
        btnView.setEnabled(false);

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
        topToolBar.add(btnView);
        topToolBar.add(btnModifer);
        topToolBar.add(btnSupprimer);

        ColumnConfig name = new ColumnConfig(EntiteJuridiqueModel.ENTITE_JURIDIQUE_NAME, messages.entiteJuridiqueName(), 50);
        ColumnConfig address = new ColumnConfig(EntiteJuridiqueModel.ENTITE_JURIDIQUE_ADDRESS, messages.entiteJuridiqueAddress(), 150);
        ColumnConfig isDefault = new ColumnConfig(EntiteJuridiqueModel.ENTITE_JURIDIQUE_IS_DEFAULT, messages.languagedefault(), 50);
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
                            clientEntiteJuridiqueService.update(item, new AsyncCallbackWithErrorResolution<EntiteJuridiqueModel>() {

                                @Override
                                public void onSuccess(EntiteJuridiqueModel arg0) {
                                }

                                @Override
                                public void onFailure(Throwable arg0) {

                                }
                            });
                        }

                        clientEntiteJuridiqueService.update(model, new AsyncCallbackWithErrorResolution<EntiteJuridiqueModel>() {

                            @Override
                            public void onSuccess(EntiteJuridiqueModel arg0) {
                                initData();
                            }
                        });
                    }
                });
                return cb;
            }
        };

        isDefault.setRenderer(defaultRender);

        proxy = new PagingModelMemoryProxy(new ArrayList<EntiteJuridiqueModel>());
        loader = new BasePagingLoader<PagingLoadResult<EntiteJuridiqueModel>>(proxy);
        loader.setRemoteSort(true);
        store = new ListStore<EntiteJuridiqueModel>(loader);
        toolBar.bind(loader);
        loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(address);
        config.add(isDefault);

        final ColumnModel cm = new ColumnModel(config);

        grid = new Grid<EntiteJuridiqueModel>(store, cm);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(LanguageModel.LAG_NAME);
        filters.addFilter(nameFilter);

        grid.setBorders(true);
        grid.addPlugin(filters);
        grid.setLoadMask(true);
        grid.getView().setAutoFill(true);
        grid.getView().setForceFit(true);
        WindowResizeBinder.bind(grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(messages.entiteJuridiqueList());
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
