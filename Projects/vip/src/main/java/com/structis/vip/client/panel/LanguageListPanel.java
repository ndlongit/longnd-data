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
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.ModifyLanguageEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.LanguageException;
import com.structis.vip.shared.model.LanguageModel;

public class LanguageListPanel extends LayoutContainer {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 800;
    private final int HEIGHT = 480;

    private SimpleEventBus bus;
    private ListStore<LanguageModel> store = new ListStore<LanguageModel>();

    private Button btnAdd;
    private Button btnModifer;
    private Button btnSupprimer;
    private Grid<LanguageModel> grid;
    private PagingLoader<PagingLoadResult<LanguageModel>> loader;
    private PagingModelMemoryProxy proxy;

    private ClientLanguageServiceAsync clientLanguageService = ClientLanguageServiceAsync.Util.getInstance();

    public LanguageListPanel(SimpleEventBus bus) {
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
                LanguageListPanel.this.disableEvents(true);
                LanguageListPanel.this.initData();
                LanguageListPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                LanguageListPanel.this.disableEvents(true);
                LanguageListPanel.this.initData();
                LanguageListPanel.this.disableEvents(false);
            }
        });
    }

    private void initData() {
        this.store.removeAll();
        this.grid.mask(this.messages.commonloadingdata());
        this.clientLanguageService.getLanguages(new AsyncCallback<List<LanguageModel>>() {

            @Override
            public void onSuccess(List<LanguageModel> arg0) {
                LanguageListPanel.this.proxy.setData(arg0);
                LanguageListPanel.this.loader.load(0, 50);
                LanguageListPanel.this.store = new ListStore<LanguageModel>(LanguageListPanel.this.loader);
                LanguageListPanel.this.grid.unmask();
            }

            @Override
            public void onFailure(Throwable arg0) {
                LanguageListPanel.this.grid.unmask();
            }
        });
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<LanguageModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<LanguageModel> se) {
                if (se.getSelectedItem() != null) {
                    LanguageListPanel.this.btnModifer.setEnabled(true);
                    LanguageListPanel.this.btnSupprimer.setEnabled(true);
                } else {
                    LanguageListPanel.this.btnModifer.setEnabled(false);
                    LanguageListPanel.this.btnSupprimer.setEnabled(false);
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final LanguageModel model = LanguageListPanel.this.grid.getSelectionModel().getSelectedItem();
                    LanguageListPanel.this.clientLanguageService.delete(model, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            LanguageListPanel.this.initData();
                            Info.display(LanguageListPanel.this.messages.commoninfo(),
                                    LanguageListPanel.this.messages.languagemessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof LanguageException) {
                                details = ExceptionMessageHandler.getErrorMessage(((LanguageException) caught).getCode());
                            }
                            Info.display(LanguageListPanel.this.messages.commonerror(), details);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_LANGUAGE_CREATE_FORM);
                ModifyLanguageEvent subEvent = new ModifyLanguageEvent();
                subEvent.setModel(null);
                event.setEvent(subEvent);
                LanguageListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_LANGUAGE_CREATE_FORM);
                ModifyLanguageEvent subEvent = new ModifyLanguageEvent();
                subEvent.setModel(LanguageListPanel.this.grid.getSelectionModel().getSelectedItem());
                event.setEvent(subEvent);
                LanguageListPanel.this.bus.fireEvent(event);
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                LanguageModel model = LanguageListPanel.this.grid.getSelectionModel().getSelectedItem();
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(LanguageListPanel.this.messages.commonConfirmation());
                    box.addCallback(l);
                    box.setMessage(LanguageListPanel.this.messages.commonDeleteMessage(model.getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(LanguageListPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(LanguageListPanel.this.messages.commonNon());
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

        ColumnConfig name = new ColumnConfig(LanguageModel.LAG_NAME, this.messages.languagenom(), 200);
        ColumnConfig code = new ColumnConfig(LanguageModel.LAG_CODE, this.messages.languagecode(), 100);
        code.setAlignment(HorizontalAlignment.CENTER);
        ColumnConfig isDefault = new ColumnConfig(LanguageModel.LAG_IS_DEFAULT, this.messages.languagedefault(), 100);
        isDefault.setAlignment(HorizontalAlignment.CENTER);

        GridCellRenderer<LanguageModel> defaultRender = new GridCellRenderer<LanguageModel>() {

            @Override
            public Object render(final LanguageModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    final ListStore<LanguageModel> store, Grid<LanguageModel> grid) {
                final Radio cb = new Radio();
                cb.setValue((model != null && model.getIsDefault() != null && model.getIsDefault() == 1) ? true : false);
                cb.addListener(Events.OnClick, new Listener<BaseEvent>() {

                    @Override
                    public void handleEvent(BaseEvent be) {
                        model.setIsDefault((cb.getValue()) ? 1 : 0);

                        for (LanguageModel item : store.getModels()) {
                            if (item.getId() == model.getId())
                                continue;

                            item.setIsDefault(0);
                            LanguageListPanel.this.clientLanguageService.update(item, new AsyncCallback<LanguageModel>() {

                                @Override
                                public void onSuccess(LanguageModel arg0) {
                                }

                                @Override
                                public void onFailure(Throwable arg0) {

                                }
                            });
                        }

                        LanguageListPanel.this.clientLanguageService.update(model, new AsyncCallback<LanguageModel>() {

                            @Override
                            public void onSuccess(LanguageModel arg0) {
                                LanguageListPanel.this.initData();
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

        this.proxy = new PagingModelMemoryProxy(new ArrayList<LanguageModel>());
        this.loader = new BasePagingLoader<PagingLoadResult<LanguageModel>>(this.proxy);
        this.loader.setRemoteSort(true);
        this.store = new ListStore<LanguageModel>(this.loader);
        toolBar.bind(this.loader);
        this.loader.load(0, 50);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(code);
        config.add(isDefault);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<LanguageModel>(this.store, cm);

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
        panel.setHeading(this.messages.languagelistedeslangues());
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
