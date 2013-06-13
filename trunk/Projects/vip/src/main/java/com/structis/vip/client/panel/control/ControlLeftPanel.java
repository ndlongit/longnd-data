package com.structis.vip.client.panel.control;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ComponentManager;
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
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.control.ControlFilterEvent;
import com.structis.vip.client.event.control.RefreshTreeEvent;
import com.structis.vip.client.event.control.RefreshTreeHandler;
import com.structis.vip.client.panel.base.AbstractContentPanel;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public class ControlLeftPanel extends AbstractContentPanel {

    private TreeLoader<PerimetreTreeModel> loader;
    private TreeStore<PerimetreTreeModel> store = new TreeStore<PerimetreTreeModel>();
    private TreePanel<PerimetreTreeModel> tree = new TreePanel<PerimetreTreeModel>(this.store);

    private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();

    private Label titleTreeLabel;
    private EntiteModel selectedEntiteModel;
    private PerimetreModel selectedPerimetreModel;
    private LayoutContainer top;
    private PerimetreTreeModel selectedPerimetreTreeModel;

    public ControlLeftPanel(SimpleEventBus bus) {
        super(bus);
        this.setId(ClientConstant.CONTROL_TREE_PANEL_ID);
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
                    tree.getSelectionModel().deselectAll();

                    top.addStyleName("x-ftree2-selected");
                    PerimetreModel perimetreModel = selectedPerimetreModel;
                    RefreshTreeEvent event = new RefreshTreeEvent(selectedEntiteModel, perimetreModel);
                    bus.fireEvent(event);
                }
            }
        });

        this.titleTreeLabel.addListener(Events.OnMouseOver, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                top.addStyleName("x-ftree2-node-over");
            }
        });

        this.titleTreeLabel.addListener(Events.OnMouseOut, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                top.removeStyleName("x-ftree2-node-over");
            }
        });

        ContentPanel panel = new ContentPanel();
        panel.setLayout(new FlowLayout());
        panel.setHeaderVisible(false);
        panel.setBodyBorder(false);
        panel.setHeight(-1);
        panel.setStyleAttribute("paddingTop", "2px");

        this.bus.addHandler(RefreshTreeEvent.getType(), new RefreshTreeHandler() {

            @Override
            public void onLoadAction(RefreshTreeEvent event) {
                disableEvents(true);
                selectedEntiteModel = event.getEntiteModel();
                selectedPerimetreModel = event.getPerimetreModel();
                selectedPerimetreTreeModel = new PerimetreTreeModel();
                selectedPerimetreTreeModel.setPerId(selectedPerimetreModel.getPerId());
                selectedPerimetreTreeModel.setName(selectedPerimetreModel.getName());
                selectedPerimetreTreeModel.setPath(selectedPerimetreModel.getName());
                selectedPerimetreTreeModel.setParent(selectedPerimetreModel.getParent() == null ? null : selectedPerimetreModel.getParent().getName());
                // 27 Nov
                List<UserRoleModel> roles = currentUser.getUserRoles();
                for (UserRoleModel r : roles) {
                    selectedPerimetreTreeModel.setPermissionByRole(r.getRole());
                }
                titleTreeLabel.setText(event.getPerimetreModel().getName());
                tree.getSelectionModel().deselectAll();

                top.addStyleName("x-ftree2-selected");

                store.removeAll();
                loader.load();
                disableEvents(false);
            }
        });

        RpcProxy<List<PerimetreTreeModel>> proxy = new RpcProxy<List<PerimetreTreeModel>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<List<PerimetreTreeModel>> callback) {
                PerimetreTreeModel model = (PerimetreTreeModel) loadConfig;
                if (selectedEntiteModel != null && selectedPerimetreModel != null) {
                    List<UserRoleModel> userRoles = currentUser.getUserRoles();
                    if (model == null) {
                        model = new PerimetreTreeModel(selectedPerimetreModel, userRoles);
                        model.setEntiteId(selectedEntiteModel.getEntId());
                        model.setLevel(0);
                        model.setPath(selectedPerimetreModel.getName());
                        model.setIsEntite(false);
                    }
                    model.setName(SafeHtmlUtils.htmlEscape(model.getName()));
                    clientPerimetreService.getTreeModelByParent(selectedEntiteModel.getEntId(), userRoles, model, callback);
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
        this.tree.setId(ClientConstant.CONTROL_TREE_ID);
        this.tree.setIconProvider(new ModelIconProvider<PerimetreTreeModel>() {

            @Override
            public AbstractImagePrototype getIcon(PerimetreTreeModel model) {
                if ((model.getIsLectureControl() != null) && (model.getIsLectureControl())) {
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
                final PerimetreTreeModel node = tree.getSelectionModel().getSelectedItem();
                if (node != null) {
                    top.removeStyleName("x-ftree2-selected");
                    selectedPerimetreTreeModel = node;
                    String path = selectedPerimetreTreeModel.getPath();
                    if (!path.equals("")) {
                        path = " UO > " + path;
                    }
                    Label contentPathLabel = (Label) ComponentManager.get().get(ClientConstant.PATH_LABEL_ID);
                    contentPathLabel.setText(path);

                    ControlFilterEvent controlFilterEvent = new ControlFilterEvent();
                    controlFilterEvent.setPerimetreTreeModel(selectedPerimetreTreeModel);
                    controlFilterEvent.setEntiteModel(SessionServiceImpl.getInstance().getEntiteContext());
                    controlFilterEvent.setTypeModel(null);
                    controlFilterEvent.setStartDate(null);
                    controlFilterEvent.setEndDate(null);

                    // fire event delegation filter
                    bus.fireEvent(controlFilterEvent);
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

    public void onLoadPanel() {
        this.disableEvents(true);
        this.selectedEntiteModel = SessionServiceImpl.getInstance().getEntiteContext();
        this.selectedPerimetreModel = SessionServiceImpl.getInstance().getPerimetreContext();
        this.selectedPerimetreTreeModel = new PerimetreTreeModel();
        this.selectedPerimetreTreeModel.setPerId(this.selectedPerimetreModel.getPerId());
        this.selectedPerimetreTreeModel.setName(this.selectedPerimetreModel.getName());
        this.selectedPerimetreTreeModel.setPath(this.selectedPerimetreModel.getName());
        this.selectedPerimetreTreeModel.setParent(this.selectedPerimetreModel.getParent() == null ? null : this.selectedPerimetreModel.getParent()
                .getName());
        // 27 Nov
        List<UserRoleModel> roles = currentUser.getUserRoles();
        for (UserRoleModel r : roles) {
            this.selectedPerimetreTreeModel.setPermissionByRole(r.getRole());
        }
        this.titleTreeLabel.setText(this.selectedPerimetreModel.getName());
        this.tree.getSelectionModel().deselectAll();

        this.top.addStyleName("x-ftree2-selected");

        String path = this.selectedPerimetreTreeModel.getPath();
        if (!path.equals("")) {
            path = " UO > " + path;
        }
        Label contentPathLabel = (Label) ComponentManager.get().get(ClientConstant.PATH_LABEL_ID);
        if (contentPathLabel != null) {
            contentPathLabel.setText(path);
        }

        this.store.removeAll();
        this.loader.load();
        this.disableEvents(false);
    }

    public PerimetreTreeModel getSelectedPerimetreTreeModel() {
        return this.selectedPerimetreTreeModel;
    }
}
