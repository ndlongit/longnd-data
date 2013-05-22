package com.structis.vip.client.widget;

import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.util.BaseEventPreview;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ListModelPropertyEditor;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.structis.vip.client.message.Messages;

public class XComboBox<D extends ModelData> extends ComboBox<D> {

    private final Messages appMessages = GWT.create(Messages.class);
    private final int WIDTH = 270;
    private String valueFieldSeparator = ";";
    private String rawSeparator = ", ";

    LayoutContainer actionPanel;

    public XComboBox() {
        this.messages = new ComboBoxMessages();
        this.setView(new CheckBoxListView<D>());
        this.setPropertyEditor(new ListModelPropertyEditor<D>());
        this.monitorWindowResize = true;
        this.windowResizeDelay = 0;
        this.initComponent();
        this.setTriggerAction(TriggerAction.ALL);
        this.setForceSelection(false);
    }

    private void bindStore(ListStore<D> store, boolean initial) {
        if (this.store != null && !initial) {
            this.store.removeStoreListener(this.getStoreListener());
            if (store == null) {
                this.store = null;
                if (this.getView() != null) {
                    this.getView().setStore(null);
                }
            }
        }
        if (store != null) {
            this.store = store;
            if (store.getLoader() == null) {
                this.setMode("local");
            }
            if (this.getView() != null) {
                this.getView().setStore(store);
            }
            store.addStoreListener(this.getStoreListener());
        }
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        // Need to update the text value in order to
        // display the proper values when the data was set pre-render
        if (this.getSelection() != null) {
            for (D d : this.getSelection()) {
                ((CheckBoxListView<D>) this.getView()).setChecked(d, true);
            }
        }
        this.updateTextValue();
    }

    @Override
    public void expand() {
        super.expand();
    }

    @Override
    public void collapse() {
        super.collapse();
        this.updateTextValue();
        this.updateHiddenValue();
        this.processOnChanges(this.getSelection());

    }

    /**
     * Update the text value displayed when the ComboBox is collapsed.
     */
    private void updateTextValue() {
        String text = "";
        for (D d : this.getSelection()) {
            if (text.length() > 0) {
                text += this.rawSeparator;
            }
            text += d.get(this.getDisplayField());
        }
        this.setRawValue(text.isEmpty() ? this.getEmptyText() : text);
    }

    private void createList(boolean remove, LayoutContainer list) {
        RootPanel.get().add(list);
        this.setInitialized(true);
        if (this.getPagingToolBar() != null) {
            this.setFooter(list.el().createChild("<div class='" + this.getListStyle() + "-ft'></div>"));
            this.getPagingToolBar().setBorders(false);
            this.getPagingToolBar().render(this.getFooter().dom);
        }
        if (remove) {
            RootPanel.get().remove(list);
        }
    }

    @Override
    protected void doForce() {
        return;
    }

    public String getRawSeparator() {
        return this.rawSeparator;
    }

    @Override
    public List<D> getSelection() {
        return ((CheckBoxListView<D>) this.getView()).getChecked();
    }

    @Override
    public D getValue() {
        return null;
    }

    public String getValueFieldSeparator() {
        return this.valueFieldSeparator;
    }

    @Override
    protected void initList() {
        ListView<D> listView = this.getView();
        if (listView == null) {
            this.setView(new CheckBoxListView<D>());
        }

        final String style = this.getListStyle();
        listView.setStyleAttribute("overflowX", "hidden");
        listView.setStyleName(style + "-inner");
        listView.setStyleAttribute("padding", "0px");
        listView.setSelectOnOver(true);
        listView.setBorders(false);
        listView.setStyleAttribute("backgroundColor", "white");
        listView.setSelectStyle(this.getSelectedStyle());
        listView.setLoadingText(this.getLoadingText());

        this.setTemplate(XTemplate.create("<tpl for=\".\"><div class='x-view-item x-view-item-check'><table cellspacing='1"
                + "' cellpadding='1'><tr><td><input class=\"x-view-item-checkbox\" type=\"checkbox\" /><td><td><span title=\"{"
                + this.getDisplayField() + "}\">{" + this.getDisplayField() + "}</span></td></tr></table></div></tpl>"));

        if (this.getTemplate() == null) {
            listView.setDisplayProperty(this.getDisplayField());
        } else {
            listView.setTemplate(this.getTemplate());
        }

        LayoutContainer listContainer = new LayoutContainer() {

            @Override
            protected void onRender(Element parent, int index) {
                super.onRender(parent, index);
                XComboBox.this.getEventPreview().getIgnoreList().add(this.getElement());
            }
        };
        listContainer.setLayout(new FlowLayout(0));

        listContainer.setScrollMode(Scroll.NONE);
        listContainer.setShim(true);
        listContainer.setShadow(true);
        listContainer.setBorders(true);
        listContainer.setStyleName(style);
        listContainer.hide();

        assert this.store != null : "ComboBox needs a store";

        this.actionPanel = new LayoutContainer();
        this.actionPanel.setWidth(this.WIDTH);
        this.actionPanel.setStyleAttribute("border-top", "1px solid #99BBE8");
        HBoxLayout boxLayout = new HBoxLayout();
        boxLayout.setPadding(new Padding(5, 0, 5, 0));
        boxLayout.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
        this.actionPanel.setLayout(boxLayout);

        Button btnValider = new Button(this.appMessages.commonvaliderlaselection());
        btnValider.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                XComboBox.this.collapse();
            }
        });

        Label lbAll = new Label(this.appMessages.commontoutcocher());
        lbAll.setStyleName("x-link-item");
        lbAll.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                List<D> checkedList = XComboBox.this.getSelection();
                for (int i = 0; i < checkedList.size(); i++) {
                    ((CheckBoxListView<D>) XComboBox.this.getView()).setChecked(checkedList.get(i), false);
                }
                ((CheckBoxListView<D>) XComboBox.this.getView()).setChecked(XComboBox.this.store.getAt(0), true);
            }
        });

        Label lbUnAll = new Label(this.appMessages.commontoutdecocher());
        lbUnAll.setStyleName("x-link-item");
        lbUnAll.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                XComboBox.this.clearSelections();
            }
        });

        this.actionPanel.add(lbAll, new HBoxLayoutData(new Margins(3, 5, 0, 0)));
        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        flex.setFlex(1);
        this.actionPanel.add(new Text(), flex);
        this.actionPanel.add(lbUnAll, new HBoxLayoutData(new Margins(3, 5, 0, 0)));
        flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        flex.setFlex(1);
        this.actionPanel.add(new Text(), flex);
        this.actionPanel.add(btnValider, new HBoxLayoutData(new Margins(0, 5, 0, 0)));

        listContainer.add(listView, new FlowData(0, 5, 5, 0));
        listContainer.add(this.actionPanel, new FlowData(0, 5, 5, 0));
        this.setList(listContainer);

        if (this.getPageSize() > 0) {
            PagingToolBar pageTb = new PagingToolBar(this.getPageSize());
            pageTb.bind((PagingLoader) this.store.getLoader());
            this.setPagingToolBar(pageTb);
        }

        this.bindStore(this.store, true);
        if (!this.isLazyRender()) {
            this.createList(true, listContainer);
        }

        listView.render(listContainer.getElement());
        listView.addListener(Events.Select, new Listener<ListViewEvent<D>>() {

            @Override
            public void handleEvent(ListViewEvent<D> le) {
                List<D> checkedList = XComboBox.this.getSelection();

                InputElement ie = null;
                NodeList<Element> nodes = XComboBox.this.getView().el().select(".x-view-item-checkbox");
                int index = XComboBox.this.store.indexOf(le.getModel());
                if (index != -1) {
                    Element e = nodes.getItem(index);
                    if (com.google.gwt.dom.client.Element.is(e)) {
                        ie = InputElement.as(e);
                        if (ie.isChecked() == false) {
                            checkedList.add(le.getModel());
                        }
                    }
                }
                if (XComboBox.this.store.getCount() > 0 && checkedList != null && !checkedList.isEmpty()) {
                    if ((checkedList.size() == XComboBox.this.store.getCount() - 1) && !checkedList.contains(XComboBox.this.store.getAt(0))) {
                        ((CheckBoxListView<D>) XComboBox.this.getView()).setChecked(XComboBox.this.store.getAt(0), true);
                        for (int i = 1; i < XComboBox.this.store.getCount(); i++) {
                            ((CheckBoxListView<D>) XComboBox.this.getView()).setChecked(XComboBox.this.store.getAt(i), false);
                        }
                        ie.setChecked(true);
                    } else {
                        ((CheckBoxListView<D>) XComboBox.this.getView()).setChecked(XComboBox.this.store.getAt(0), false);
                    }
                    if (le.getModel().equals(XComboBox.this.store.getAt(0))) {
                        for (int i = 0; i < checkedList.size(); i++) {
                            if (!checkedList.get(i).equals(le.getModel())) {
                                ((CheckBoxListView<D>) XComboBox.this.getView()).setChecked(checkedList.get(i), false);
                            }
                        }
                    }
                }
            }
        });
    }

    protected void processOnChanges(List<D> selection) {

    }

    private native BaseEventPreview getEventPreview() /*-{
                                                      return this.@com.extjs.gxt.ui.client.widget.form.ComboBox::eventPreview;
                                                      }-*/;

    private native El getFooter() /*-{
                                  return this.@com.extjs.gxt.ui.client.widget.form.ComboBox::footer;
                                  }-*/;

    private native InputElement getHiddenInput() /*-{
                                                 return this.@com.extjs.gxt.ui.client.widget.form.ComboBox::hiddenInput;
                                                 }-*/;

    private native StoreListener<D> getStoreListener() /*-{
                                                       return this.@com.extjs.gxt.ui.client.widget.form.ComboBox::storeListener;
                                                       }-*/;

    private native void setFooter(El footer) /*-{
                                             this.@com.extjs.gxt.ui.client.widget.form.ComboBox::footer = footer;

                                             }-*/;

    private native void setInitialized(boolean initialized) /*-{
                                                            this.@com.extjs.gxt.ui.client.widget.form.ComboBox::initialized = initialized;
                                                            }-*/;

    private native void setList(LayoutContainer list)/*-{
                                                     this.@com.extjs.gxt.ui.client.widget.form.ComboBox::list = list;
                                                     }-*/;

    private native LayoutContainer getList()/*-{
                                            return this.@com.extjs.gxt.ui.client.widget.form.ComboBox::list;
                                            }-*/;

    private native void setMode(String mode)/*-{
                                            this.@com.extjs.gxt.ui.client.widget.form.ComboBox::mode = mode;
                                            }-*/;

    private native void setPagingToolBar(PagingToolBar pageTb)/*-{
                                                              this.@com.extjs.gxt.ui.client.widget.form.ComboBox::pageTb = pageTb;
                                                              }-*/;

    public void setRawSeparator(String rawSeparator) {
        this.rawSeparator = rawSeparator;
    }

    public void setValueFieldSeparator(String valueFieldSeparator) {
        this.valueFieldSeparator = valueFieldSeparator;
    }

    @Override
    public void setSelection(List<D> selection) {
        if (selection != null) {
            for (D d : selection) {
                ((CheckBoxListView<D>) this.getView()).setChecked(d, true);
            }
            super.setSelection(selection);
        }
    }

    @Override
    public void clearSelections() {
        List<D> selection = this.getSelection();
        for (D d : selection) {
            ((CheckBoxListView<D>) this.getView()).setChecked(d, false);
        }
        super.clear();
        super.clearSelections();
    }

    private void updateHiddenValue() {
        if (this.getHiddenInput() != null) {
            String v = "";
            for (D d : this.getSelection()) {
                if (v.length() > 0) {
                    v += this.valueFieldSeparator;
                }
                v += d.get(this.getValueField());
            }
            this.getHiddenInput().setValue(v);
        }
    }

    @Override
    protected void restrict() {
        super.restrict();
        this.getList().setHeight(this.getView().getHeight() + 50);
        this.actionPanel.setHeight(this.getView().getHeight() + 50);
    }
}
