package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.DelegationTreeEvent;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class DelegationLeftPanel extends ContentPanel {

    private TreeLoader<PerimetreTreeModel> loader;
    private TreeStore<PerimetreTreeModel> store = new TreeStore<PerimetreTreeModel>();
    private TreePanel<PerimetreTreeModel> tree = new TreePanel<PerimetreTreeModel>(this.store);

    private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();

    private Label titleTreeLabel;
    private SimpleEventBus bus;
    private EntiteModel selectedEntiteModel;
    private PerimetreModel selectedPerimetreModel;
    private LayoutContainer top;

    public DelegationLeftPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.setStyleAttribute("paddingBottom", "0px");
        this.setBodyBorder(true);
        this.setScrollMode(Scroll.AUTO);
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        this.buildPanel();
    }

    private void buildPanel() {
        this.top = new LayoutContainer();
        this.titleTreeLabel = new Label("");
        this.titleTreeLabel.setStyleAttribute("marginLeft", "20px");
        this.titleTreeLabel.setStyleAttribute("text-align", "center");

        this.top.setStyleAttribute("paddingTop", "2px");
        this.top.setWidth(420);
        this.top.add(this.titleTreeLabel);
        this.top.setLayout(new FlowLayout());

        this.titleTreeLabel.addListener(Events.OnClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (!AppUtil.checkToShowWarningInEditMode()) {
                    DelegationLeftPanel.this.tree.getSelectionModel().deselectAll();

                    DelegationLeftPanel.this.top.addStyleName("x-ftree2-selected");

                    DelegationListProjectEvent event = new DelegationListProjectEvent(DelegationLeftPanel.this.selectedEntiteModel,
                            DelegationLeftPanel.this.selectedPerimetreModel);
                    event.setMode(DelegationListProjectEvent.DELEGATION_FILTER_FROM_DELEGATION_FILTER);
                    DelegationLeftPanel.this.bus.fireEvent(event);
                }
            }
        });

        this.titleTreeLabel.addListener(Events.OnMouseOver, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                DelegationLeftPanel.this.top.addStyleName("x-ftree2-node-over");
            }
        });

        this.titleTreeLabel.addListener(Events.OnMouseOut, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                DelegationLeftPanel.this.top.removeStyleName("x-ftree2-node-over");
            }
        });

        ContentPanel panel = new ContentPanel();
        panel.setLayout(new FlowLayout());
        panel.setHeaderVisible(false);
        panel.setBodyBorder(false);
        panel.setHeight(-1);
        panel.setStyleAttribute("paddingTop", "2px");

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(DelegationListProjectEvent event) {
                DelegationLeftPanel.this.disableEvents(true);
                DelegationLeftPanel.this.selectedEntiteModel = event.getEntiteModel();
                DelegationLeftPanel.this.selectedPerimetreModel = event.getPerimetreModel();

                DelegationLeftPanel.this.titleTreeLabel.setText(event.getPerimetreModel().getName());
                DelegationLeftPanel.this.tree.getSelectionModel().deselectAll();

                DelegationLeftPanel.this.top.addStyleName("x-ftree2-selected");

                if (DelegationListProjectEvent.DELEGATION_FILTER_FROM_DELEGATION_FILTER != event.getMode()) {
                    DelegationLeftPanel.this.store.removeAll();
                    DelegationLeftPanel.this.loader.load();
                }
                DelegationLeftPanel.this.disableEvents(false);
            }
        });

        RpcProxy<List<PerimetreTreeModel>> proxy = new RpcProxy<List<PerimetreTreeModel>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<List<PerimetreTreeModel>> callback) {
                PerimetreTreeModel model = (PerimetreTreeModel) loadConfig;
                if (DelegationLeftPanel.this.selectedEntiteModel != null && DelegationLeftPanel.this.selectedPerimetreModel != null) {
                    if (model == null) {
                        model = new PerimetreTreeModel(DelegationLeftPanel.this.selectedPerimetreModel, SessionServiceImpl.getInstance()
                                .getUserContext().getUserRoles());
                        model.setEntiteId(DelegationLeftPanel.this.selectedEntiteModel.getEntId());
                        model.setLevel(0);
                        model.setPath(DelegationLeftPanel.this.selectedPerimetreModel.getName());
                        model.setIsEntite(false);
                    }
                    model.setName(SafeHtmlUtils.htmlEscape(model.getName()));
                    DelegationLeftPanel.this.clientPerimetreService.getTreeModelByParent(DelegationLeftPanel.this.selectedEntiteModel.getEntId(),
                            SessionServiceImpl.getInstance().getUserContext().getUserRoles(), model, callback);
                }
            }
        };

        this.loader = new BaseTreeLoader<PerimetreTreeModel>(proxy) {

            @Override
            public boolean hasChildren(PerimetreTreeModel parent) {
                return parent.getIsParent();
            }
        };

        this.store = new TreeStore<PerimetreTreeModel>(this.loader);

        this.tree = new TreePanel<PerimetreTreeModel>(this.store);

        this.tree.setIconProvider(new ModelIconProvider<PerimetreTreeModel>() {

            @Override
            public AbstractImagePrototype getIcon(PerimetreTreeModel model) {
                if ((model.getIsLectureDelegation() != null) && (model.getIsLectureDelegation())) {
                    return IconHelper.createPath("html/icon/check.png");
                } else {
                    return null;
                }
            }

        });
        this.tree.setDisplayProperty(PerimetreModel.PERIMETRE_NAME);
        this.tree.addListener(Events.OnClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                final PerimetreTreeModel node = DelegationLeftPanel.this.tree.getSelectionModel().getSelectedItem();
                if (node != null) {
                    DelegationLeftPanel.this.top.removeStyleName("x-ftree2-selected");
                    DelegationLeftPanel.this.bus.fireEvent(new DelegationTreeEvent(node));

                }
            }
        });
        this.tree.setDisplayProperty(PerimetreModel.PERIMETRE_NAME);
        this.tree.setStateful(true);
        this.tree.getStyle().setJointCollapsedIcon(IconHelper.createPath("html/icon/plus.jpg"));
        this.tree.getStyle().setJointExpandedIcon(IconHelper.createPath("html/icon/minus.jpg"));
        this.tree.getStyle().setNodeCloseIcon(null);
        this.tree.getStyle().setNodeOpenIcon(null);

        panel.add(this.tree);
        this.top.add(panel);
        this.add(this.top);
    }

    public TreePanel<PerimetreTreeModel> getTreePanel() {
        return this.tree;
    }
}
