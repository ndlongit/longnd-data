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

	
	private SimpleEventBus bus;
	
	private HorizontalPanel horizontalPanelDown = new HorizontalPanel();
	private CaptionPanel captionPanel;
	
	private final Messages messages = GWT.create(Messages.class);
	
	private LayoutContainer left;
	private LayoutContainer middle;
	private LayoutContainer right;
	
	private Label typeDeDelegationLabel;
	private Label delegationPrincipaleLabel;
	private Label delegationSigneeLabel;
	private Label dateDeSignatureLabel ;
	private Label lieuDeSignatureLabel;
	private Label dateDeDemandeLabel ;
	private Label lieuDeDemandeLabel ;
	private Label dateDacceptationLabel ;
	private Label lieuDeDacceptationLabel ;
	private Label dateDeRecommandationLabel ;
	private Label lieuDeRecommandationLabel ;
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
		this.bus = bus;
		buildPanel();
	}


	
	/*Define  panel*/
	protected void buildPanel() {
		
		captionPanel = new CaptionPanel(messages.delegationdetailcaption());
		
		setBorders(false);
		setAutoHeight(true);
		setWidth("100%");
		
		setHeaderVisible(false);
		setLayout(new FlowLayout());
		
		ContentPanel horizontalPanel = new ContentPanel();
		horizontalPanel.setHeaderVisible(false);
		TableLayout layout = new TableLayout();
		layout.setColumns(2);
		horizontalPanel.setLayout(layout);
		
		retoursauxButton = new Button(messages.delegationdetailbuttonretoursaux());
		printerButton = new Button(messages.delegationdetailbuttonprinter());
		
		horizontalPanel.add(retoursauxButton, new TableData(HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE));
		horizontalPanel.add(printerButton, new TableData(HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE));
		
		ContentPanel mainPanel = createMainPanel();
		
		horizontalPanelDown.setLayout(new TableLayout(2));
		modifierButton = new Button(messages.delegationdetailbuttonModifier());
		horizontalPanelDown.add(modifierButton,new TableData(HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE));
		
		captionPanel.add(mainPanel);
		
		add(horizontalPanel);
		add(captionPanel);
		add(horizontalPanelDown);
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
		
		initLayoutContainer();
		
		HorizontalPanel left_p1 = new HorizontalPanel();
		left_p1.setStyleAttribute("padding-right", "5px");
		left_p1.add(new Label(messages.delegationdetaillabeltypededelegation()));
		
		typeDeDelegationLabel = new Label();
		left_p1.add(typeDeDelegationLabel);
		
		HorizontalPanel middle_p1 = new HorizontalPanel();
		middle_p1.setStyleAttribute("padding", "8px");
		middle_p1.add(new Label(messages.delegationdetaillabeldemandeur()));
		
		demandeurLabel = new Label();
		middle_p1.add(demandeurLabel);
		
		HorizontalPanel right_p1 = new HorizontalPanel();
		right_p1.setStyleAttribute("padding-right", "5px");
		right_p1.add(new Label(messages.delegationdetaillabelstatus()));
		
		statusLabel = new Label();
		right_p1.add(statusLabel);
		
		left.add(left_p1);
		middle.add(middle_p1);
		right.add(right_p1);
		
		panelTop.add(left, new ColumnData(.4));
		panelTop.add(middle,new ColumnData(.4));
		panelTop.add(right,new ColumnData(.2));
		Html hr = new Html("<hr/>");
		
		
		panelMiddle.add(hr);
		
		initLayoutContainer();
		addComponentIntoLeft();
		addComponentIntoMiddle();
		addComponentIntoRight();
		
		panelBottom.add(left, new ColumnData(.4));
		panelBottom.add(middle,new ColumnData(.15));
		panelBottom.add(right,new ColumnData(.35));
		
		main.add(panelTop);
		main.add(hr);
		main.add(panelBottom);
		
		return main;
	}

	/**
	 * initLayoutContainer
	 */
	private void initLayoutContainer() {
		left = new LayoutContainer();
	    left.setStyleAttribute("padding-right", "5px");
	    FormLayout layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.RIGHT);
	    left.setLayout(layout);
	    
	    
	    middle = new LayoutContainer();
	    middle.setStyleAttribute("padding-right", "5px");
	    FormLayout layout1 = new FormLayout();
		layout1.setLabelAlign(LabelAlign.RIGHT);
		middle.setLayout(layout1);
	    
	    right = new LayoutContainer();
	    right.setStyleAttribute("padding-right", "5px");
	    FormLayout layout2 = new FormLayout();
		layout2.setLabelAlign(LabelAlign.RIGHT);
		right.setLayout(layout2);
	}
	
	/**
	 * addComponentIntoRight
	 */
	private void addComponentIntoRight() {
		
		delegationSigneeLabel = new Label();
		dateDeSignatureLabel = new Label();
		lieuDeSignatureLabel = new Label();
		dateDeDemandeLabel = new Label();
		 lieuDeDemandeLabel = new Label();
		 dateDacceptationLabel = new Label();
		 lieuDeDacceptationLabel = new Label();
		 dateDeRecommandationLabel = new Label();
		 lieuDeRecommandationLabel = new Label();
		
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
		
		h1.add(delegationSigneeLabel);
		h2.add(dateDeSignatureLabel);
		h3.add(lieuDeSignatureLabel);
		h4.add(dateDeDemandeLabel);
		h5.add(lieuDeDemandeLabel);
		h6.add(dateDacceptationLabel);
		h7.add(lieuDeDacceptationLabel);
		h8.add(dateDeRecommandationLabel);
		h9.add(lieuDeRecommandationLabel);
		
		right.add(h1);
		right.add(h2);
		right.add(h3);
		right.add(h4);
		right.add(h5);
		right.add(h6);
		right.add(h7);
		right.add(h8);
		right.add(h9);
	}

	/**
	 * addComponentIntoMiddle
	 */
	private void addComponentIntoMiddle() {
		
		Label label1 = new Label(messages.delegationdetaillabeldelegationsignee());
		Label label2 = new Label(messages.delegationdetaillabeldatedesignature());
		Label label3 = new Label(messages.delegationdetaillabellieudesignature());
		Label label4 = new Label(messages.delegationdetaillabeldatededemande());
		Label label5 = new Label(messages.delegationdetaillabellieudedemande());
		Label label6 = new Label(messages.delegationdetaillabeldatedacceptation());
		Label label7 = new Label(messages.delegationdetaillabellieudedacceptation());
		Label label8 = new Label(messages.delegationdetaillabeldatederecommandation());
		Label label9 = new Label(messages.delegationdetaillabellieuderecommandation());
		 
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
		
		middle.add(h1);
		middle.add(h2);
		middle.add(h3);
		middle.add(h4);
		middle.add(h5);
		middle.add(h6);
		middle.add(h7);
		middle.add(h8);
		middle.add(h9);
	}
	
	/**
	 * 
	 */
	private void addComponentIntoLeft() {
		CaptionPanel p1 = new CaptionPanel("Partie déléguée");
		p1.setHeight("60px");
		
		HorizontalPanel p2 = new HorizontalPanel();
		p2.setStyleAttribute("padding-right", "10px");
		p2.add(new Label(messages.delegationdetaillabeldelegationprincipale()));
		
		
		delegationPrincipaleLabel = new Label();
		p2.add(delegationPrincipaleLabel);
		
		HorizontalPanel p3 = new HorizontalPanel();
		p3.setStyleAttribute("padding-right", "10px");
		p3.add(new Label(messages.delegationdetaillabeldelagant()));
		delegantLabel = new Label();
		p3.add(delegantLabel);
		
		HorizontalPanel p4 = new HorizontalPanel();
		p4.setStyleAttribute("padding-right", "10px");
		p4.add(new Label(messages.delegationdetaillabeldelegataire()));
		delegataireLabel = new Label();
		p4.add(delegataireLabel);
		
		HorizontalPanel p5 = new HorizontalPanel();
		p5.setStyleAttribute("padding-right", "10px");
		p5.add(new Label(messages.delegationdetaillabeldatededebut()));
		dateDeDebutLabel = new Label();
		p5.add(dateDeDebutLabel);
		
		HorizontalPanel p6 = new HorizontalPanel();
		p6.setStyleAttribute("padding-right", "10px");
		p6.add(new Label(messages.delegationdetaillabeldatedefin()));
		dateDeFinLabel = new Label();
		p6.add(dateDeFinLabel);
		
		HorizontalPanel p7 = new HorizontalPanel();
		p7.setStyleAttribute("padding-right", "10px");
		p7.add(new Label(messages.delegationdetaillabelsep()));
		
		RadioGroup sepGroup = new RadioGroup();
		Radio ouiRadio = new Radio();
		Radio nonRadio = new Radio();
		
		ouiRadio.setBoxLabel(messages.delegationdetailradiooui());
		nonRadio.setBoxLabel(messages.delegationdetailradionon());
		
		sepGroup.setStyleAttribute("padding-left", "20px");

		sepGroup.add(ouiRadio);
		sepGroup.add(nonRadio);
		p7.add(sepGroup);
		
		HorizontalPanel p8 = new HorizontalPanel();
		p8.setStyleAttribute("padding-right", "10px");
		p8.add(new Label(messages.delegationdetaillabelconjounte()));
		conjointeLabel = new Label();
		p8.add(conjointeLabel);
		
		HorizontalPanel p9 = new HorizontalPanel();
		p9.setStyleAttribute("padding-right", "10px");
		p9.add(new Label(messages.delegationdetaillabeldocuments()));
		
		HorizontalPanel p10 = new HorizontalPanel();
		EditorGrid<Stock> libelleGrid = createGrid();
		
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
		
		left.add(p1);
		left.add(p2);
		left.add(p3);
		left.add(p4);
		left.add(p5);
		left.add(p6);
		left.add(p7);
		left.add(p8);
		left.add(p9);
		left.add(p10);
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
        store.add(getStock());  
      
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
			results.add(new Stock("document"+i));
		}
		return results;
	}


	class Stock extends BeanModel{
		
		public static final String STOCK_STOCKLIBELLE = "libelle";
		
		public Stock(){}
		
		public Stock(String string) {
			setLibelle(string);
		}

		public String getLibelle() {
			return get(STOCK_STOCKLIBELLE);
		}
		public void setLibelle(String Libelle) {
			set(STOCK_STOCKLIBELLE, Libelle);
		}
		
	}
}
