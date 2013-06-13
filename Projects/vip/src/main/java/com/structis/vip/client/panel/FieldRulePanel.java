package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupingView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.structis.vip.client.event.FieldRuleEvent;
import com.structis.vip.client.event.FieldRuleHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientFieldRuleServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.FieldRuleModel;

public class FieldRulePanel extends ContentPanel {

    private final Messages messages = GWT.create(Messages.class);
    private ClientFieldRuleServiceAsync clientFieldRuleService = ClientFieldRuleServiceAsync.Util.getInstance();

    GroupingStore<FieldRuleModel> store = new GroupingStore<FieldRuleModel>();
    private SimpleEventBus bus;
    private Grid<FieldRuleModel> grid;
    private Button btnSave;

    public FieldRulePanel(SimpleEventBus bus) {
        this.bus = bus;

        setHeading(messages.fieldrulesheader());
        setLayout(new FitLayout());
        setFrame(true);
        setAutoHeight(true);
        setButtonAlign(HorizontalAlignment.LEFT);
        setBodyBorder(false);
        setBorders(false);
        setWidth(800);

        initUI();

        bus.addHandler(FieldRuleEvent.getType(), new FieldRuleHandler() {

            @Override
            public void onLoadAction(final FieldRuleEvent event) {
                disableEvents(true);

                grid.mask(messages.commonloadingdata());
                if ((event.getGroup() != null) && (event.getGroup() != 0)) {
                    clientFieldRuleService.getRulesByDemGroup(event.getGroup(), new AsyncCallbackWithErrorResolution<List<FieldRuleModel>>() {

                        @Override
                        public void onSuccess(List<FieldRuleModel> arg0) {
                            store.removeAll();
                            store.add(arg0);
                            grid.unmask();
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                            grid.unmask();
                        }
                    });
                } else {
                    grid.unmask();
                }
                disableEvents(false);
            }
        });
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);
        GWT.log(getClass().getName() + ":onRender");
    }

    private void initUI() {
        store.groupBy("field.group");

        ColumnConfig label = new ColumnConfig("field.label", messages.fieldruleslabel(), 40);
        ColumnConfig dbField = new ColumnConfig("field.dbField", messages.fieldrulesdbfield(), 20);
        ColumnConfig displayed = new ColumnConfig("isDisplayed", messages.fieldrulesdisplayed(), 20);
        displayed.setAlignment(HorizontalAlignment.CENTER);
        ColumnConfig required = new ColumnConfig("isRequired", messages.fieldrulesrequired(), 20);
        required.setAlignment(HorizontalAlignment.CENTER);

        GridCellRenderer<FieldRuleModel> displayedRender = new GridCellRenderer<FieldRuleModel>() {

            @Override
            public Object render(final FieldRuleModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    ListStore<FieldRuleModel> store, Grid<FieldRuleModel> grid) {
                final CheckBox cb = new CheckBox();
                cb.setValue((model != null && model.getIsDisplayed() != null && model.getIsDisplayed() == 1));
                cb.addListener(Events.OnClick, new Listener<BaseEvent>() {

                    @Override
                    public void handleEvent(BaseEvent be) {
                        model.setIsDisplayed((cb.getValue()) ? 1 : 0);
                    }
                });
                return cb;
            }
        };

        GridCellRenderer<FieldRuleModel> requiredRender = new GridCellRenderer<FieldRuleModel>() {

            @Override
            public Object render(final FieldRuleModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    ListStore<FieldRuleModel> store, Grid<FieldRuleModel> grid) {
                final CheckBox cb = new CheckBox();
                cb.setValue((model != null && model.getIsRequired() != null && model.getIsRequired() == 1));
                cb.addListener(Events.OnClick, new Listener<BaseEvent>() {

                    @Override
                    public void handleEvent(BaseEvent be) {
                        model.setIsRequired((cb.getValue()) ? 1 : 0);
                    }
                });
                return cb;
            }
        };

        displayed.setRenderer(displayedRender);
        required.setRenderer(requiredRender);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(label);
        config.add(dbField);
        config.add(displayed);
        config.add(required);

        final ColumnModel cm = new ColumnModel(config);

        GroupingView view = new GroupingView();
        view.setForceFit(true);
        view.setAutoFill(true);
        view.setShowGroupedColumn(false);
        view.setGroupRenderer(new GridGroupRenderer() {

            @Override
            public String render(GroupColumnData data) {
                return data.group;
            }
        });

        grid = new Grid<FieldRuleModel>(store, cm);
        grid.setView(view);
        grid.setAutoHeight(true);
        grid.setBorders(true);
        grid.setLoadMask(true);
        WindowResizeBinder.bind(grid);

        add(grid);
        grid.getAriaSupport().setLabelledBy(getHeader().getId() + "-label");

        btnSave = new Button(messages.fieldrulessave());
        btnSave.setIcon(IconHelper.createPath("html/save-icon.png"));
        btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ListStore<FieldRuleModel> curStore = grid.getStore();
                if (curStore != null && curStore.getCount() > 0) {
                    clientFieldRuleService.updateList(curStore.getModels(), new AsyncCallbackWithErrorResolution<List<FieldRuleModel>>() {

                        @Override
                        public void onSuccess(List<FieldRuleModel> arg0) {
                            Info.display(messages.commoninfo(), messages.delegationmodelsavesuccess());
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                            Info.display(messages.commonerror(), messages.delegationmodelsavefailed());
                        }
                    });
                }
            }
        });

        addButton(btnSave);
    }

    public Grid<FieldRuleModel> getGrid() {
        return grid;
    }

    public void setGrid(Grid<FieldRuleModel> grid) {
        this.grid = grid;
    }
}
