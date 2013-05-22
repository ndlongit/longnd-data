package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.structis.vip.client.message.Messages;

public class DelegationDetailResult extends ContentPanel {

    private HorizontalPanel horizontalPanelDown = new HorizontalPanel();
    private CaptionPanel captionPanel;

    private final Messages messages = GWT.create(Messages.class);

    private LayoutContainer left;
    private LayoutContainer middle;
    private LayoutContainer right;

    private Label typeDeDelegationLabel;
    private Label delegationPrincipaleLabel;
    private Label delegationSigneeLabel;
    private Label dateDeSignatureLabel;
    private Label lieuDeSignatureLabel;
    private Label dateDeDemandeLabel;
    private Label lieuDeDemandeLabel;
    private Label dateDacceptationLabel;
    private Label lieuDeDacceptationLabel;
    private Label dateDeRecommandationLabel;
    private Label lieuDeRecommandationLabel;
    private Label delegantLabel;
    private Label dateDeDebutLabel;
    private Label dateDeFinLabel;
    private Label delegataireLabel;
    private Label conjointeLabel;
    private Label demandeurLabel;
    private Label statusLabel;
    private Button retoursauxButton;
    private Button printerButton;
    private Button modifierButton;

    /**
     * @author nam.le
     * @param bus
     */
    public DelegationDetailResult(SimpleEventBus bus) {
        this.buildPanel();
    }

    /* Define panel */
    protected void buildPanel() {

        this.captionPanel = new CaptionPanel(this.messages.delegationdetailcaption());

        this.setBorders(false);
        this.setAutoHeight(true);
        this.setWidth("100%");

        this.setHeaderVisible(false);
        this.setLayout(new FlowLayout());

        ContentPanel horizontalPanel = new ContentPanel();
        horizontalPanel.setHeaderVisible(false);
        TableLayout layout = new TableLayout();
        layout.setColumns(2);
        horizontalPanel.setLayout(layout);

        this.retoursauxButton = new Button(this.messages.delegationdetailbuttonretoursaux());
        this.printerButton = new Button(this.messages.delegationdetailbuttonprinter());

        horizontalPanel.add(this.retoursauxButton, new TableData(HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE));
        horizontalPanel.add(this.printerButton, new TableData(HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE));

        ContentPanel mainPanel = this.createMainPanel();

        this.horizontalPanelDown.setLayout(new TableLayout(2));
        this.modifierButton = new Button(this.messages.delegationdetailbuttonModifier());
        this.horizontalPanelDown.add(this.modifierButton, new TableData(HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE));

        this.captionPanel.add(mainPanel);

        this.add(horizontalPanel);
        this.add(this.captionPanel);
        this.add(this.horizontalPanelDown);
    }

    private ContentPanel createMainPanel() {

        ContentPanel main = new ContentPanel();
        main.setHeight(600);
        main.setHeaderVisible(false);
        main.setLayout(new FlowLayout());
        main.setBodyBorder(false);
        main.setStyleAttribute("margin", "5px");

        FieldSet panelTop = new FieldSet();
        HorizontalPanel panelMiddle = new HorizontalPanel();
        FieldSet panelBottom = new FieldSet();

        panelMiddle.setWidth("100%");
        panelTop.setWidth("100%");
        panelBottom.setWidth("100%");

        panelTop.setBorders(false);
        panelBottom.setBorders(false);
        panelTop.setLayout(new ColumnLayout());
        panelBottom.setLayout(new ColumnLayout());

        this.initLayoutContainer();

        HorizontalPanel left_p1 = new HorizontalPanel();
        left_p1.setStyleAttribute("padding-right", "5px");
        left_p1.add(new Label(this.messages.delegationdetaillabeltypededelegation()));

        this.typeDeDelegationLabel = new Label();
        left_p1.add(this.typeDeDelegationLabel);

        HorizontalPanel middle_p1 = new HorizontalPanel();
        middle_p1.setStyleAttribute("padding", "8px");
        middle_p1.add(new Label(this.messages.delegationdetaillabeldemandeur()));

        this.demandeurLabel = new Label();
        middle_p1.add(this.demandeurLabel);

        HorizontalPanel right_p1 = new HorizontalPanel();
        right_p1.setStyleAttribute("padding-right", "5px");
        right_p1.add(new Label(this.messages.delegationdetaillabelstatus()));

        this.statusLabel = new Label();
        right_p1.add(this.statusLabel);

        this.left.add(left_p1);
        this.middle.add(middle_p1);
        this.right.add(right_p1);

        panelTop.add(this.left, new ColumnData(.4));
        panelTop.add(this.middle, new ColumnData(.4));
        panelTop.add(this.right, new ColumnData(.2));
        Html hr = new Html("<hr/>");

        panelMiddle.add(hr);

        this.initLayoutContainer();
        this.addComponentIntoLeft();
        this.addComponentIntoMiddle();
        this.addComponentIntoRight();

        panelBottom.add(this.left, new ColumnData(.4));
        panelBottom.add(this.middle, new ColumnData(.15));
        panelBottom.add(this.right, new ColumnData(.35));

        main.add(panelTop);
        main.add(hr);
        main.add(panelBottom);

        return main;
    }

    /**
     * initLayoutContainer
     */
    private void initLayoutContainer() {
        this.left = new LayoutContainer();
        this.left.setStyleAttribute("padding-right", "5px");
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.RIGHT);
        this.left.setLayout(layout);

        this.middle = new LayoutContainer();
        this.middle.setStyleAttribute("padding-right", "5px");
        FormLayout layout1 = new FormLayout();
        layout1.setLabelAlign(LabelAlign.RIGHT);
        this.middle.setLayout(layout1);

        this.right = new LayoutContainer();
        this.right.setStyleAttribute("padding-right", "5px");
        FormLayout layout2 = new FormLayout();
        layout2.setLabelAlign(LabelAlign.RIGHT);
        this.right.setLayout(layout2);
    }

    /**
     * addComponentIntoRight
     */
    private void addComponentIntoRight() {

        this.delegationSigneeLabel = new Label();
        this.dateDeSignatureLabel = new Label();
        this.lieuDeSignatureLabel = new Label();
        this.dateDeDemandeLabel = new Label();
        this.lieuDeDemandeLabel = new Label();
        this.dateDacceptationLabel = new Label();
        this.lieuDeDacceptationLabel = new Label();
        this.dateDeRecommandationLabel = new Label();
        this.lieuDeRecommandationLabel = new Label();

        HorizontalPanel h1 = new HorizontalPanel();
        HorizontalPanel h2 = new HorizontalPanel();
        HorizontalPanel h3 = new HorizontalPanel();
        HorizontalPanel h4 = new HorizontalPanel();
        HorizontalPanel h5 = new HorizontalPanel();
        HorizontalPanel h6 = new HorizontalPanel();
        HorizontalPanel h7 = new HorizontalPanel();
        HorizontalPanel h8 = new HorizontalPanel();
        HorizontalPanel h9 = new HorizontalPanel();

        h4.setStyleAttribute("padding-top", "15px");
        h6.setStyleAttribute("padding-top", "15px");
        h8.setStyleAttribute("padding-top", "15px");
        h2.setStyleAttribute("padding-top", "5px");
        h3.setStyleAttribute("padding-top", "5px");
        h5.setStyleAttribute("padding-top", "5px");
        h7.setStyleAttribute("padding-top", "5px");
        h9.setStyleAttribute("padding-top", "5px");

        h1.add(this.delegationSigneeLabel);
        h2.add(this.dateDeSignatureLabel);
        h3.add(this.lieuDeSignatureLabel);
        h4.add(this.dateDeDemandeLabel);
        h5.add(this.lieuDeDemandeLabel);
        h6.add(this.dateDacceptationLabel);
        h7.add(this.lieuDeDacceptationLabel);
        h8.add(this.dateDeRecommandationLabel);
        h9.add(this.lieuDeRecommandationLabel);

        this.right.add(h1);
        this.right.add(h2);
        this.right.add(h3);
        this.right.add(h4);
        this.right.add(h5);
        this.right.add(h6);
        this.right.add(h7);
        this.right.add(h8);
        this.right.add(h9);
    }

    /**
     * addComponentIntoMiddle
     */
    private void addComponentIntoMiddle() {

        Label label1 = new Label(this.messages.delegationdetaillabeldelegationsignee());
        Label label2 = new Label(this.messages.delegationdetaillabeldatedesignature());
        Label label3 = new Label(this.messages.delegationdetaillabellieudesignature());
        Label label4 = new Label(this.messages.delegationdetaillabeldatededemande());
        Label label5 = new Label(this.messages.delegationdetaillabellieudedemande());
        Label label6 = new Label(this.messages.delegationdetaillabeldatedacceptation());
        Label label7 = new Label(this.messages.delegationdetaillabellieudedacceptation());
        Label label8 = new Label(this.messages.delegationdetaillabeldatederecommandation());
        Label label9 = new Label(this.messages.delegationdetaillabellieuderecommandation());

        HorizontalPanel h1 = new HorizontalPanel();
        HorizontalPanel h2 = new HorizontalPanel();
        HorizontalPanel h3 = new HorizontalPanel();
        HorizontalPanel h4 = new HorizontalPanel();
        HorizontalPanel h5 = new HorizontalPanel();
        HorizontalPanel h6 = new HorizontalPanel();
        HorizontalPanel h7 = new HorizontalPanel();
        HorizontalPanel h8 = new HorizontalPanel();
        HorizontalPanel h9 = new HorizontalPanel();

        h4.setStyleAttribute("padding-top", "15px");
        h6.setStyleAttribute("padding-top", "15px");
        h8.setStyleAttribute("padding-top", "15px");
        h2.setStyleAttribute("padding-top", "5px");
        h3.setStyleAttribute("padding-top", "5px");
        h5.setStyleAttribute("padding-top", "5px");
        h7.setStyleAttribute("padding-top", "5px");
        h9.setStyleAttribute("padding-top", "5px");

        h1.add(label1);
        h2.add(label2);
        h3.add(label3);
        h4.add(label4);
        h5.add(label5);
        h6.add(label6);
        h7.add(label7);
        h8.add(label8);
        h9.add(label9);

        this.middle.add(h1);
        this.middle.add(h2);
        this.middle.add(h3);
        this.middle.add(h4);
        this.middle.add(h5);
        this.middle.add(h6);
        this.middle.add(h7);
        this.middle.add(h8);
        this.middle.add(h9);
    }

    /**
	 * 
	 */
    private void addComponentIntoLeft() {
        CaptionPanel p1 = new CaptionPanel("Partie déléguée");
        p1.setHeight("60px");

        HorizontalPanel p2 = new HorizontalPanel();
        p2.setStyleAttribute("padding-right", "10px");
        p2.add(new Label(this.messages.delegationdetaillabeldelegationprincipale()));

        this.delegationPrincipaleLabel = new Label();
        p2.add(this.delegationPrincipaleLabel);

        HorizontalPanel p3 = new HorizontalPanel();
        p3.setStyleAttribute("padding-right", "10px");
        p3.add(new Label(this.messages.delegationdetaillabeldelagant()));
        this.delegantLabel = new Label();
        p3.add(this.delegantLabel);

        HorizontalPanel p4 = new HorizontalPanel();
        p4.setStyleAttribute("padding-right", "10px");
        p4.add(new Label(this.messages.delegationdetaillabeldelegataire()));
        this.delegataireLabel = new Label();
        p4.add(this.delegataireLabel);

        HorizontalPanel p5 = new HorizontalPanel();
        p5.setStyleAttribute("padding-right", "10px");
        p5.add(new Label(this.messages.delegationdetaillabeldatededebut()));
        this.dateDeDebutLabel = new Label();
        p5.add(this.dateDeDebutLabel);

        HorizontalPanel p6 = new HorizontalPanel();
        p6.setStyleAttribute("padding-right", "10px");
        p6.add(new Label(this.messages.delegationdetaillabeldatedefin()));
        this.dateDeFinLabel = new Label();
        p6.add(this.dateDeFinLabel);

        HorizontalPanel p7 = new HorizontalPanel();
        p7.setStyleAttribute("padding-right", "10px");
        p7.add(new Label(this.messages.delegationdetaillabelsep()));

        RadioGroup sepGroup = new RadioGroup();
        Radio ouiRadio = new Radio();
        Radio nonRadio = new Radio();

        ouiRadio.setBoxLabel(this.messages.delegationdetailradiooui());
        nonRadio.setBoxLabel(this.messages.delegationdetailradionon());

        sepGroup.setStyleAttribute("padding-left", "20px");

        sepGroup.add(ouiRadio);
        sepGroup.add(nonRadio);
        p7.add(sepGroup);

        HorizontalPanel p8 = new HorizontalPanel();
        p8.setStyleAttribute("padding-right", "10px");
        p8.add(new Label(this.messages.delegationdetaillabelconjounte()));
        this.conjointeLabel = new Label();
        p8.add(this.conjointeLabel);

        HorizontalPanel p9 = new HorizontalPanel();
        p9.setStyleAttribute("padding-right", "10px");
        p9.add(new Label(this.messages.delegationdetaillabeldocuments()));

        HorizontalPanel p10 = new HorizontalPanel();
        EditorGrid<Stock> libelleGrid = this.createGrid();

        p10.add(libelleGrid);

        p6.setStyleAttribute("padding-top", "5px");
        p5.setStyleAttribute("padding-top", "5px");
        p4.setStyleAttribute("padding-top", "5px");
        p7.setStyleAttribute("padding-top", "10px");
        p8.setStyleAttribute("padding-top", "5px");
        p2.setStyleAttribute("padding-top", "5px");
        p3.setStyleAttribute("padding-top", "10px");
        p9.setStyleAttribute("padding-top", "10px");
        p10.setStyleAttribute("padding-top", "5px");

        this.left.add(p1);
        this.left.add(p2);
        this.left.add(p3);
        this.left.add(p4);
        this.left.add(p5);
        this.left.add(p6);
        this.left.add(p7);
        this.left.add(p8);
        this.left.add(p9);
        this.left.add(p10);
    }

    private EditorGrid<Stock> createGrid() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setId("liblle");
        column.setHeader("liblle");
        column.setWidth(200);
        configs.add(column);

        ListStore<Stock> store = new ListStore<Stock>();
        store.setMonitorChanges(true);
        store.add(this.getStock());

        ColumnModel cm = new ColumnModel(configs);

        EditorGrid<Stock> grid = new EditorGrid<Stock>(store, cm);
        grid.setBorders(false);
        grid.setAutoExpandColumn("name");
        grid.setBorders(true);

        return grid;
    }

    private List<Stock> getStock() {
        List<Stock> results = new ArrayList<DelegationDetailResult.Stock>();
        for (int i = 1; i < 6; i++) {
            results.add(new Stock("document" + i));
        }
        return results;
    }

    class Stock extends BeanModel {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public static final String STOCK_STOCKLIBELLE = "libelle";

        public Stock() {
        }

        public Stock(String string) {
            this.setLibelle(string);
        }

        public String getLibelle() {
            return this.get(STOCK_STOCKLIBELLE);
        }

        public void setLibelle(String Libelle) {
            this.set(STOCK_STOCKLIBELLE, Libelle);
        }

    }
}
