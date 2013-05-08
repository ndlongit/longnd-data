package com.structis.fichesst.server.service.exportpdf;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.structis.fichesst.shared.util.Constants;


public class ExportPdfFichest extends ExportPdfManager {
	
	//Grid 1
	private String generaleInformation;
	private String conditionsparticulieres;
	private String prestations;
	private String informationscomplementaires;
	private String cautionFournies;
	
	//Grid 2
	private List<String> gestions;
	private List<String> totalgestions;
	private String alltotalgestion;
	private String summary;
	private String budget;
	
	//Grid 3
	private String processes;
	private String totalsituation;
	private String grid2;
	private String grid3;
	private String commentaire;
	private String etatAvancement;
	
	private String deductions;
	private String totaldeduction;
	private String penaltys;
	private String amount;
	private String information;
	private String deductionComment1;
	private String deductionComment2;
	
	private ResourceBundleMessageSource messageSource;
	
	public ExportPdfFichest(OutputStream stream,ResourceBundleMessageSource messageSource,Locale locale)
			throws DocumentException, IOException {
		super(stream, locale);
		this.messageSource = messageSource;
		//landscape
		document.setPageSize(PageSize.A4.rotate());
		//document.setMargins(15, 15, 130, 80);
		//document.setMargins(32, 32, 90, 72);
		this.messageSource = messageSource;
		contentBoldGrayFont.setSize(12);
		contentItalicFont.setSize(12);
		titleL1BoldFont.setSize(12);
		titleL2BoldFont.setSize(12);
		contentBoldFont.setSize(9);
		contentNormalFont.setSize(7f);
		contentNormalGrayFont.setSize(8.5f);
	}
	
	@Override
	public void render() throws DocumentException {
			addAllFichestDto();
	}
	
	private void addAllFichestDto() throws DocumentException {
		//Title
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		
		PdfPCell headerCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.fichedesuividusoustraitant", null, localtion), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		headerTable.addCell(headerCell);
		
		addContent(headerTable);
		
		//Information Generals
		headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		headerCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.generalInformation", null, localtion), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		headerTable.addCell(headerCell);

		addContent(headerTable);
	
		// Information Details
		String arrGeneraleInformation[]= generaleInformation.split(Constants.SEPRATE);
		PdfPTable generaleInformationTable = new PdfPTable(6);
		PdfPCell generaleInformationCell;
		generaleInformationTable.setWidthPercentage(100f);
		String [] messages = {messageSource.getMessage("FicheST.chantier",null, localtion) + " : ",messageSource.getMessage("FicheST.lot", null, localtion) + " : ",
				messageSource.getMessage("FicheST.foreman", null, localtion) + " : ",
				messageSource.getMessage("pdf.synthese.societe", null, localtion) + " : ",messageSource.getMessage("FicheST.lotType", null, localtion) + " : ",
				messageSource.getMessage("pdf.gestiondto.montantobjectif",null,localtion) + " : "};
		for(int i=0;i<arrGeneraleInformation.length;i++){
			switch (i) {
				case 0:	
					generaleInformationCell = new PdfPCell(new Phrase(messages[0], contentBoldFont));
					generaleInformationCell.setBorder(Rectangle.NO_BORDER);
					generaleInformationCell.enableBorderSide(Rectangle.TOP);
					generaleInformationCell.enableBorderSide(Rectangle.LEFT);
					generaleInformationTable.addCell(generaleInformationCell);
					
					break;
				case 1:	
					generaleInformationCell = new PdfPCell(new Phrase(messages[1], contentBoldFont));
					generaleInformationCell.setBorder(Rectangle.NO_BORDER);
					generaleInformationCell.enableBorderSide(Rectangle.TOP);
					generaleInformationTable.addCell(generaleInformationCell);
					break;
				case 2:	
					generaleInformationCell = new PdfPCell(new Phrase(messages[2], contentBoldFont));
					generaleInformationCell.setBorder(Rectangle.NO_BORDER);
					generaleInformationCell.enableBorderSide(Rectangle.TOP);
					generaleInformationTable.addCell(generaleInformationCell);
					break;
				case 3:	
					generaleInformationCell = new PdfPCell(new Phrase(messages[3], contentBoldFont));
					generaleInformationCell.setBorder(Rectangle.NO_BORDER);
					generaleInformationCell.enableBorderSide(Rectangle.BOTTOM);
					generaleInformationCell.enableBorderSide(Rectangle.LEFT);
					generaleInformationTable.addCell(generaleInformationCell);
					break;
				case 4:	
					generaleInformationCell = new PdfPCell(new Phrase(messages[4], contentBoldFont));
					generaleInformationCell.setBorder(Rectangle.NO_BORDER);
					generaleInformationCell.enableBorderSide(Rectangle.BOTTOM);
					generaleInformationTable.addCell(generaleInformationCell);
					break;
				case 5:	
					generaleInformationCell = new PdfPCell(new Phrase(messages[5], contentBoldFont));
					generaleInformationCell.setBorder(Rectangle.NO_BORDER);
					generaleInformationCell.enableBorderSide(Rectangle.BOTTOM);
					generaleInformationTable.addCell(generaleInformationCell);
					break;
			}
			generaleInformationCell = new PdfPCell(new Phrase(arrGeneraleInformation[i].equals("null") ? "" : arrGeneraleInformation[i], contentNormalFont));
			if(i == 0 || i == 1 || i == 2){
				generaleInformationCell.setBorder(Rectangle.NO_BORDER);
				generaleInformationCell.enableBorderSide(Rectangle.TOP);
				if(i == 2)
					generaleInformationCell.enableBorderSide(Rectangle.RIGHT);
				generaleInformationTable.addCell(generaleInformationCell);
			}
			if(i == 3 || i == 4 || i == 5){
				generaleInformationCell.setBorder(Rectangle.NO_BORDER);
				generaleInformationCell.enableBorderSide(Rectangle.BOTTOM);
				if(i == 5)
					generaleInformationCell.enableBorderSide(Rectangle.RIGHT);
				generaleInformationTable.addCell(generaleInformationCell);
			}
		}
		float[] wfGeneraleInformation = {
				3,
				6,
				3,
				6,
				7,
				6
			};
		generaleInformationTable.setWidths(wfGeneraleInformation);
		addContent(generaleInformationTable);
		lineBreak();
		
		PdfPCell leftCell,spaceCell1,middleCell,spaceCell2,rightCell;
		PdfPTable parent = new PdfPTable(5);
		parent.setSpacingBefore(0f);
		parent.setWidthPercentage(100f);
		float[] wfParent = {
				25,
				5,//Libellé risque codification 
				25,
				5,//Origine de détection 
				40
			};
		parent.setWidths(wfParent);

		//Add Condition
		leftCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.conditions", null, localtion),contentBoldFont));
		leftCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(leftCell);
		
		spaceCell1 = new PdfPCell();
		spaceCell1.setBorder(Rectangle.NO_BORDER);
		parent.addCell(spaceCell1);
		
		middleCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.prestations", null, localtion),contentBoldFont));
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);
		
		spaceCell2 = new PdfPCell();
		spaceCell2.setBorder(Rectangle.NO_BORDER);
		parent.addCell(spaceCell2);

		rightCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.informationComplementaries", null, localtion), contentBoldFont));
		rightCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(rightCell);
		
		//Add content
		PdfPTable conditionTable = addConditions();
		leftCell = new PdfPCell(conditionTable);
		parent.addCell(leftCell);
		
		spaceCell1 = new PdfPCell();
		spaceCell1.setBorder(Rectangle.NO_BORDER);
		parent.addCell(spaceCell1);
		
		PdfPTable prestations = addPrestations();
		middleCell = new PdfPCell(prestations);
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);
		
		spaceCell2 = new PdfPCell();
		spaceCell2.setBorder(Rectangle.NO_BORDER);
		parent.addCell(spaceCell2);
		
		PdfPTable caution = addCaution();
		rightCell = new PdfPCell(caution);
		parent.addCell(rightCell);

		addContent(parent);	
		lineBreak();
		
		addGestionDto();
		lineBreak();
		
		addProcessDto();
		lineBreak();
		
		addDeductionDto();
		
		
	}
	
	private PdfPTable addConditions() throws DocumentException {
		
		PdfPTable conditionsTable = new PdfPTable(2);
		PdfPCell conditionsCell = new PdfPCell();
		
		//Add Content
		String[] tmp = conditionsparticulieres.split(Constants.SEPRATE);
		String [] messages = {messageSource.getMessage("pdf.allfichest.payment", null, localtion) + " : ",messageSource.getMessage("FicheST.rg", null, localtion) + " : ",
				messageSource.getMessage("pdf.allfichest.decenalenecessaire", null, localtion) + " : ",messageSource.getMessage("pdf.allfichest.demandedagrement", null, localtion) + " : ",
				messageSource.getMessage("pdf.allfichest.dgdpresente",null, localtion) + " : ",messageSource.getMessage("FicheST.date", null, localtion) + " : "};
		for(int i=0;i<tmp.length;i++){
			switch (i) {
			case 0:
				conditionsCell = new PdfPCell(new Phrase(messages[0], contentBoldFont));
				conditionsCell.setBorder(Rectangle.NO_BORDER);
				conditionsTable.addCell(conditionsCell);
				break;
			case 1:
				conditionsCell = new PdfPCell(new Phrase(messages[1], contentBoldFont));
				conditionsCell.setBorder(Rectangle.NO_BORDER);
				conditionsTable.addCell(conditionsCell);
				break;
			case 2:
				conditionsCell = new PdfPCell(new Phrase(messages[2], contentBoldFont));
				conditionsCell.setBorder(Rectangle.NO_BORDER);
				conditionsTable.addCell(conditionsCell);
				break;
			case 3:
				conditionsCell = new PdfPCell(new Phrase(messages[3], contentBoldFont));
				conditionsCell.setBorder(Rectangle.NO_BORDER);
				conditionsTable.addCell(conditionsCell);
				break;
			case 4:
				conditionsCell = new PdfPCell(new Phrase(messages[4], contentBoldFont));
				conditionsCell.setBorder(Rectangle.NO_BORDER);
				conditionsTable.addCell(conditionsCell);
				break;
			case 5:
				conditionsCell = new PdfPCell(new Phrase(messages[5], contentBoldFont));
				conditionsCell.setBorder(Rectangle.NO_BORDER);
				conditionsTable.addCell(conditionsCell);
				break;
			}
			conditionsCell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : tmp[i] , contentNormalFont));
			conditionsCell.setBorder(Rectangle.NO_BORDER);
			conditionsTable.addCell(conditionsCell);
		}
		float[] wf = {
				10, 
				10
			};
		conditionsTable.setWidthPercentage(30f);
		conditionsTable.setWidths(wf);
		return conditionsTable;
	}
	
	private PdfPTable addPrestations() throws DocumentException {
		
		PdfPTable prestationsTable = new PdfPTable(2);
		PdfPCell prestationsCell = new PdfPCell();
		
		//Add Content
		String[] tmp = prestations.split(Constants.SEPRATE);
		String [] messages = {messageSource.getMessage("pdf.allfichest.prestations", null,localtion) + " : ",messageSource.getMessage("pdf.allfichest.assurances", null, localtion) + " : ",
				messageSource.getMessage("pdf.allfichest.prodata", null, localtion) + " : ",messageSource.getMessage("pdf.allfichest.canto",null, localtion) + " : ",
				messageSource.getMessage("pdf.allfichest.badge", null, localtion) + " : ",messageSource.getMessage("pdf.allfichest.grue", null, localtion) + " : ",
				messageSource.getMessage("pdf.allfichest.lift", null, localtion) + " : ",messageSource.getMessage("pdf.allfichest.benne", null, localtion) + " : ",
				messageSource.getMessage("pdf.allfichest.netoyage", null, localtion) + " : "};
		for(int i=0;i<tmp.length;i++){
			switch (i) {
			case 0:
				prestationsCell = new PdfPCell(new Phrase(messages[0], contentBoldFont));
				prestationsCell.setBorder(Rectangle.NO_BORDER);
				prestationsCell.enableBorderSide(Rectangle.TOP);
				prestationsCell.enableBorderSide(Rectangle.LEFT);
				prestationsTable.addCell(prestationsCell);
				break;
			case 1:
				prestationsCell = new PdfPCell(new Phrase(messages[1], contentBoldFont));
				prestationsCell.setBorder(Rectangle.NO_BORDER);
				prestationsCell.enableBorderSide(Rectangle.LEFT);
				prestationsTable.addCell(prestationsCell);
				break;
			case 2:
				prestationsCell = new PdfPCell(new Phrase(messages[2], contentBoldFont));
				prestationsCell.setBorder(Rectangle.NO_BORDER);
				prestationsCell.enableBorderSide(Rectangle.LEFT);
				prestationsTable.addCell(prestationsCell);
				break;
			case 3:
				prestationsCell = new PdfPCell(new Phrase(messages[3], contentBoldFont));
				prestationsCell.setBorder(Rectangle.NO_BORDER);
				prestationsCell.enableBorderSide(Rectangle.LEFT);
				prestationsTable.addCell(prestationsCell);
				break;
			case 4:
				prestationsCell = new PdfPCell(new Phrase(messages[4], contentBoldFont));
				prestationsCell.setBorder(Rectangle.NO_BORDER);
				prestationsCell.enableBorderSide(Rectangle.LEFT);
			
				prestationsTable.addCell(prestationsCell);
				break;
			case 5:
				prestationsCell = new PdfPCell(new Phrase(messages[5], contentBoldFont));
				prestationsCell.setBorder(Rectangle.NO_BORDER);
				prestationsCell.enableBorderSide(Rectangle.LEFT);
				prestationsTable.addCell(prestationsCell);
				break;
			case 6:
				prestationsCell = new PdfPCell(new Phrase(messages[6], contentBoldFont));
				prestationsCell.setBorder(Rectangle.NO_BORDER);
				prestationsCell.enableBorderSide(Rectangle.LEFT);
				prestationsTable.addCell(prestationsCell);
				break;
			case 7:
				prestationsCell = new PdfPCell(new Phrase(messages[7], contentBoldFont));
				prestationsCell.setBorder(Rectangle.NO_BORDER);
				prestationsCell.enableBorderSide(Rectangle.LEFT);
				prestationsTable.addCell(prestationsCell);
				break;
			case 8:
				prestationsCell = new PdfPCell(new Phrase(messages[8], contentBoldFont));
				prestationsCell.setBorder(Rectangle.NO_BORDER);
				prestationsCell.enableBorderSide(Rectangle.LEFT);
				prestationsCell.enableBorderSide(Rectangle.BOTTOM);
				prestationsTable.addCell(prestationsCell);
				break;
			}
			prestationsCell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : tmp[i], contentNormalFont));
			prestationsCell.setBorder(Rectangle.NO_BORDER);
			if(i == 0)
				prestationsCell.enableBorderSide(Rectangle.TOP);
			if(i == (tmp.length -1))
				prestationsCell.enableBorderSide(Rectangle.BOTTOM);
			prestationsCell.enableBorderSide(Rectangle.RIGHT);
			prestationsTable.addCell(prestationsCell);
		}
		float[] wf = {
				10, 
				10
			};
		prestationsTable.setWidthPercentage(30f);
		prestationsTable.setWidths(wf);
		return prestationsTable;
	}
	
	private PdfPTable addInformations() throws DocumentException {
	
		PdfPTable informationsTable = new PdfPTable(4);
		PdfPCell informatrionsCell = new PdfPCell();		
		//Add Content
		String[] tmp = informationscomplementaires.split(Constants.SEPRATE);
		
		String [] messages = {messageSource.getMessage("FicheST.conducteur", null, localtion) + " : ",messageSource.getMessage("pdf.allfichest.dateOfMarket", null, localtion) + " : "};
		for(int i=0;i<tmp.length;i++){
			switch (i) {
			case 0:
				informatrionsCell = new PdfPCell(new Phrase(messages[0], contentBoldFont));
				informatrionsCell.setBorder(Rectangle.NO_BORDER);
				informationsTable.addCell(informatrionsCell);
				break;
			case 1:
				informatrionsCell = new PdfPCell(new Phrase(messages[1], contentBoldFont));
				informatrionsCell.setBorder(Rectangle.NO_BORDER);
				informationsTable.addCell(informatrionsCell);
				break;
			}
			informatrionsCell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : tmp[i] , contentNormalFont));
			informatrionsCell.setBorder(Rectangle.NO_BORDER);
			informationsTable.addCell(informatrionsCell);
		}
		float[] wf = {
				10, 
				10,
				20,
				10
			};
		informationsTable.setWidthPercentage(30f);
		informationsTable.setWidths(wf);
		return informationsTable;
	}
	
	private PdfPTable addCaution() throws DocumentException {	
		
		PdfPTable informations = addInformations();
		PdfPCell [] informationHeader = new PdfPCell[3];
		informationHeader[0] = new PdfPCell(informations);
		informationHeader[0].setBorder(Rectangle.NO_BORDER);
		informationHeader[0].enableBorderSide(Rectangle.TOP);
		informationHeader[0].enableBorderSide(Rectangle.LEFT);
		informationHeader[0].enableBorderSide(Rectangle.RIGHT);
		
		informationHeader[1] = new PdfPCell();
		informationHeader[1].setBorder(Rectangle.NO_BORDER);
		informationHeader[2] = new PdfPCell();
		informationHeader[2].setBorder(Rectangle.NO_BORDER);
		informationHeader[0].setColspan(2);
		
		
		PdfPCell [] cautionHeader = new PdfPCell[2];
		cautionHeader[0] = new PdfPCell(new Phrase("Date", contentBoldFont));
		cautionHeader[1] = new PdfPCell(new Phrase("Montant", contentBoldFont));
		for (PdfPCell pdfPCell : cautionHeader) {
			//pdfPCell.setBorder(Rectangle.NO_BORDER);
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		PdfPTable cautionTable = new PdfPTable(2);
		addCellsToTable(cautionTable, informationHeader);
		addCellsToTable(cautionTable, cautionHeader);
		PdfPCell cautionCell = new PdfPCell();		
		//Add Content
		String[] tmp = cautionFournies.split(Constants.SEPRATE);
		for(int i=0;i<tmp.length;i++){
			cautionCell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : tmp[i], contentNormalFont));
			cautionTable.addCell(cautionCell);
		}
		float[] wf = {
				10, 
				10
			};
		cautionTable.setWidthPercentage(30f);
		cautionTable.setWidths(wf);
		return cautionTable;
	}
	
	

	public void setGeneraleInformation(String generaleInformation) {
		this.generaleInformation = generaleInformation;
	}

	public String getGeneraleInformation() {
		return generaleInformation;
	}

	public void setConditionsparticulieres(String conditionsparticulieres) {
		this.conditionsparticulieres = conditionsparticulieres;
	}

	public String getConditionsparticulieres() {
		return conditionsparticulieres;
	}

	public void setPrestations(String prestations) {
		this.prestations = prestations;
	}

	public String getPrestations() {
		return prestations;
	}

	public void setInformationscomplementaires(
			String informationscomplementaires) {
		this.informationscomplementaires = informationscomplementaires;
	}

	public String getInformationscomplementaires() {
		return informationscomplementaires;
	}

	public void setCautionFournies(String cautionFournies) {
		this.cautionFournies = cautionFournies;
	}

	public String getCautionFournies() {
		return cautionFournies;
	}

	public void setGestions(List<String> gestions) {
		this.gestions = gestions;
	}

	public List<String> getGestions() {
		return gestions;
	}

	public void setTotalgestions(List<String> totalgestions) {
		this.totalgestions = totalgestions;
	}

	public List<String> getTotalgestions() {
		return totalgestions;
	}

	public void setAlltotalgestion(String alltotalgestion) {
		this.alltotalgestion = alltotalgestion;
	}

	public String getAlltotalgestion() {
		return alltotalgestion;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return summary;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getBudget() {
		return budget;
	}

	public void setProcesses(String processes) {
		this.processes = processes;
	}

	public String getProcesses() {
		return processes;
	}

	public void setTotalsituation(String totalsituation) {
		this.totalsituation = totalsituation;
	}

	public String getTotalsituation() {
		return totalsituation;
	}

	public void setGrid2(String grid2) {
		this.grid2 = grid2;
	}

	public String getGrid2() {
		return grid2;
	}

	public void setGrid3(String grid3) {
		this.grid3 = grid3;
	}

	public String getGrid3() {
		return grid3;
	}
	
	//Add Gestion Report
	private void addGestionDto() throws DocumentException {
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);

		PdfPCell headerCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.gestion",null, localtion), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		addContent(headerTable);
	
		//Add Group Header
		PdfPCell[] groupHeaderCell = new PdfPCell[6];
		groupHeaderCell[0] = new PdfPCell();
		groupHeaderCell[0].setBorder(Rectangle.NO_BORDER);
		groupHeaderCell[1] = new PdfPCell(new Phrase("TRAITE", contentBoldFont));
		groupHeaderCell[1].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));

		groupHeaderCell[2] = new PdfPCell(new Phrase("A TRAITER", contentBoldFont));
		groupHeaderCell[2].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		groupHeaderCell[3] = new PdfPCell(new Phrase("BUDGET Conforme", contentBoldFont));
		groupHeaderCell[3].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		groupHeaderCell[4] = new PdfPCell();
		groupHeaderCell[4].setBorder(Rectangle.NO_BORDER);
		groupHeaderCell[5] = new PdfPCell();
		groupHeaderCell[5].setBorder(Rectangle.NO_BORDER);
		
		float[] wfGroupHeader = {
				41,
				18,
				10,
				22,
				17,
				7
			};
		for (PdfPCell pdfPCell : groupHeaderCell) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		PdfPTable groupHeaderTable = new PdfPTable(6);
		groupHeaderTable.setWidthPercentage(100f);
		groupHeaderTable.setWidths(wfGroupHeader);
		addCellsToTable(groupHeaderTable,groupHeaderCell);
		addContent(groupHeaderTable);
		
		//Content Header
		PdfPCell[] riskTableHeader = new PdfPCell[16];
		riskTableHeader[0] = new PdfPCell(new Phrase( messageSource.getMessage("FicheST.devis", null, localtion), contentBoldFont));
		riskTableHeader[1] = new PdfPCell(new Phrase( messageSource.getMessage("FicheST.status", null, localtion), contentBoldFont));
		riskTableHeader[2] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.libelle", null, localtion), contentBoldFont));
		riskTableHeader[3] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.commentaires", null, localtion), contentBoldFont));
		riskTableHeader[4] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.montant", null, localtion), contentBoldFont));
		riskTableHeader[5] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.marche_avenants", null, localtion), contentBoldFont));
		riskTableHeader[6] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.arrete", null, localtion), contentBoldFont));
		riskTableHeader[7] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.nonarrete", null, localtion), contentBoldFont));		
		riskTableHeader[8] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.provision", null, localtion), contentBoldFont));		
		riskTableHeader[9] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.devisrefuse", null, localtion), contentBoldFont));		
		riskTableHeader[10] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.totalfdc", null, localtion), contentBoldFont));		
		riskTableHeader[11] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.activitereel", null, localtion), contentBoldFont));		
		riskTableHeader[12] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.type", null, localtion), contentBoldFont));		
		riskTableHeader[13] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.libelle_", null, localtion), contentBoldFont));		
		riskTableHeader[14] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.montant", null, localtion), contentBoldFont));		
		riskTableHeader[15] = new PdfPCell(new Phrase( messageSource.getMessage("pdf.gestiondto.content.ecart", null, localtion), contentBoldFont));
		for (PdfPCell pdfPCell : riskTableHeader) {
			pdfPCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		//End
		
		PdfPTable gestionDtoTable = new PdfPTable(16);
		gestionDtoTable.setWidthPercentage(100f);
		addCellsToTable(gestionDtoTable, riskTableHeader);
		float[] wf = {
				7,	 
				7,  
				7,	
				15,	
				5,
				18,
				5,
				5,
				5,
				5,
				7, 
				5,
				5,
				7, 
				5,
				7
			};
		
		gestionDtoTable.setWidthPercentage(100f);
		gestionDtoTable.setWidths(wf);
		
		//Add Header
		addContent(gestionDtoTable);

		gestionDtoTable.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		
		String tmp;
		for (int j = 0 ; j < gestions.size();j++) {
			gestionDtoTable = new PdfPTable(16);
			String param = gestions.get(j);
			if(param != null && param.length() > 0){
				String arr[] = param.split(Constants.SEPRATE);
				for(int i=0;i < arr.length;i++){
					tmp = arr[i] ;//== null ? "" : arr[i]; 
					gestionDtoTable.addCell(new PdfPCell(new Phrase(tmp.equals("null") ? "" : tmp, contentNormalFont)));
				}
			}
			gestionDtoTable.setWidthPercentage(100f);
			gestionDtoTable.setWidths(wf);
			gestionDtoTable.setSpacingAfter(3f);
			addContent(gestionDtoTable);
			
			PdfPTable totalSumTable = addtotalSum(totalgestions.get(j));
			totalSumTable.setWidthPercentage(100f);
			totalSumTable.setSpacingAfter(3f);
			addContent(totalSumTable);
				
		}
		
		PdfPTable alltotalSumTable = addalltotalSum(alltotalgestion);
		try {
			alltotalSumTable.setWidthPercentage(100f);
			alltotalSumTable.setSpacingAfter(3f);
			addContent(alltotalSumTable);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		PdfPTable sumarryTable = addSummary(summary);
		try {
			sumarryTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
			sumarryTable.setSpacingAfter(3f);
			addContent(sumarryTable);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		PdfPTable budgetTable = addBudget(budget);
		try {
			budgetTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
			addContent(budgetTable);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private PdfPTable addtotalSum(String totalgestions) throws DocumentException {
		PdfPTable gestionDtoTable = new PdfPTable(11);
		PdfPCell gestionDtoCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.gestiondto.soustotaldumarche", null, localtion), contentBoldFont));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
		gestionDtoTable.addCell(gestionDtoCell);
		if(totalgestions != null && totalgestions.length() > 0){
			String[] tmp = totalgestions.split(Constants.SEPRATE);
			
			gestionDtoCell = new PdfPCell(new Phrase(tmp[0] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
			
			gestionDtoCell = new PdfPCell(new Phrase(tmp[1] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
			
			gestionDtoCell = new PdfPCell(new Phrase(tmp[2] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
			
			gestionDtoCell = new PdfPCell(new Phrase(tmp[3] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
			
			gestionDtoCell = new PdfPCell(new Phrase(tmp[4] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
			
			gestionDtoCell = new PdfPCell(new Phrase(tmp[5] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
			
			gestionDtoCell = new PdfPCell(new Phrase(tmp[6] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
			
			gestionDtoCell = new PdfPCell(new Phrase(tmp[7] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
			
			gestionDtoCell = new PdfPCell(new Phrase(tmp[8] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
			
			gestionDtoCell = new PdfPCell(new Phrase(tmp[9] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
			gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			gestionDtoTable.addCell(gestionDtoCell);
	
			float[] wf = {
					36,	 //CR 3 
					5,	// Niveau de risque 5
					18,	// Phase 
					5,	 //Statut 
					5,	// Date de création 
					5,	// Date de levée 
					5,	// Responsable  
					7,	// Cout UO 
					5,	// Cout ST 
					17,	// Vu
					7
				};
			gestionDtoTable.setWidths(wf);
		}

		return gestionDtoTable;
	}
	
	private PdfPTable addalltotalSum(String alltotalgestions) throws DocumentException {
		PdfPTable gestionDtoTable = new PdfPTable(11);
		
		PdfPCell gestionDtoCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.rexAmount",null,localtion), contentBoldFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
		gestionDtoTable.addCell(gestionDtoCell);
		
		String[] tmp = alltotalgestions.split(Constants.SEPRATE);
		

		gestionDtoCell = new PdfPCell(new Phrase(tmp[0] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(new Phrase(tmp[1] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(new Phrase(tmp[2] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(new Phrase(tmp[3] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(new Phrase(tmp[4] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(new Phrase(tmp[5] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(new Phrase(tmp[6] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(new Phrase(tmp[7] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(new Phrase(tmp[8] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(new Phrase(tmp[9] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont));
		gestionDtoCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		gestionDtoTable.addCell(gestionDtoCell);
		float[] wf = {
				36,	
				5,
				18,
				5,
				5,
				5, 
				5,
				7,
				5,
				17,
				7
			};
		gestionDtoTable.setWidths(wf);
		return gestionDtoTable;
	}
	
	private PdfPTable addSummary(String sumarry){
		
		PdfPTable sumaryTable = new PdfPTable(2);
		String[] tmp = sumarry.split(Constants.SEPRATE);
		
		sumaryTable.addCell(new PdfPCell(new Phrase(messageSource.getMessage("pdf.gestiondto.summary.objectif",null,localtion), contentBoldFont)));
		sumaryTable.addCell(new PdfPCell(new Phrase(tmp[0] + messageSource.getMessage("pdf.gestiondto.content.euro",null,localtion), contentNormalFont)));
		
		
		sumaryTable.addCell(new PdfPCell(new Phrase(messageSource.getMessage("pdf.gestiondto.summary.transferts",null,localtion), contentBoldFont)));
		sumaryTable.addCell(new PdfPCell(new Phrase(tmp[1] + messageSource.getMessage("pdf.gestiondto.content.euro",null,localtion), contentNormalFont)));
		
		sumaryTable.addCell(new PdfPCell(new Phrase(messageSource.getMessage("pdf.gestiondto.summary.rexettediverses",null,localtion), contentBoldFont)));
		sumaryTable.addCell(new PdfPCell(new Phrase(tmp[2] + messageSource.getMessage("pdf.gestiondto.content.euro",null,localtion), contentNormalFont)));
		
		sumaryTable.addCell(new PdfPCell(new Phrase(messageSource.getMessage("pdf.gestiondto.summary.trxsupp",null,localtion), contentBoldFont)));
		sumaryTable.addCell(new PdfPCell(new Phrase(tmp[3] + messageSource.getMessage("pdf.gestiondto.content.euro",null,localtion), contentNormalFont)));

		
		PdfPTable gestionDtoTable = new PdfPTable(3);
		PdfPCell gestionDtoCell;
	
		gestionDtoCell = new PdfPCell();
		gestionDtoCell.setBorder(Rectangle.NO_BORDER);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(sumaryTable);
		gestionDtoCell.setBorder(Rectangle.NO_BORDER);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell();
		gestionDtoCell.setBorder(Rectangle.NO_BORDER);
		gestionDtoTable.addCell(gestionDtoCell);
	
		float[] wf = {
				91,
				17,
				7
			};
		try {
			gestionDtoTable.setWidthPercentage(100f);
			gestionDtoTable.setWidths(wf);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return gestionDtoTable;
	}
	
	private PdfPTable addBudget(String budget){
		PdfPTable budgetTable = new PdfPTable(2);
		PdfPCell budgetCell;
		String[] tmp = budget.split(Constants.SEPRATE);
		float[] wf = {
				12,	 //CR 3 
				17,	// Niveau de risque 5
			};
		try {
			budgetTable.setWidths(wf);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	
		budgetCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.gestiondto.content.budgetinitial",null,localtion), contentNormalFont));
		budgetCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		budgetTable.addCell(budgetCell);
		budgetTable.addCell(new PdfPCell(new Phrase(tmp[0].equals("null") ? "" : tmp[0] , contentNormalFont)));
		
		budgetCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.gestiondto.content.ecartdernierpoint",null,localtion), contentNormalFont));
		budgetCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		budgetTable.addCell(budgetCell);
		budgetTable.addCell(new PdfPCell(new Phrase(tmp[1].equals("null") ? "" : tmp[1], contentNormalFont)));
		
		budgetCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.gestiondto.content.datedernier",null,localtion), contentNormalFont));
		budgetCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		budgetTable.addCell(budgetCell);
		budgetTable.addCell(new PdfPCell(new Phrase(tmp[2].equals("null") ? "" : tmp[2], contentNormalFont)));
		budgetTable.setWidthPercentage(50f);
		
		
		PdfPTable gestionDtoTable = new PdfPTable(3);
		PdfPCell gestionDtoCell;
	
		gestionDtoCell = new PdfPCell();
		gestionDtoCell.setBorder(Rectangle.NO_BORDER);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell(budgetTable);
		gestionDtoCell.setBorder(Rectangle.NO_BORDER);
		gestionDtoTable.addCell(gestionDtoCell);
		
		gestionDtoCell = new PdfPCell();
		gestionDtoCell.setBorder(Rectangle.NO_BORDER);
		gestionDtoTable.addCell(gestionDtoCell);
	
		float[] wf1 = {
				79,
				29,
				7
			};
		try {
			gestionDtoTable.setWidthPercentage(100f);
			gestionDtoTable.setWidths(wf1);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return gestionDtoTable;
	}
	
	//End
	
	
	//Add Process Report
	private void addProcessDto() throws DocumentException {		

		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		PdfPCell headerCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.suvidesavancements", null, localtion), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		headerTable.setSpacingAfter(0f);
		addContent(headerTable);
		
		headerTable = new PdfPTable(2);
		headerTable.setWidthPercentage(40f);
		headerTable.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
		headerTable.setSpacingAfter(15f);
		float[] wf1 = {
				5,10 };
		headerCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.etatdavancement", null, localtion) + " : ", contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		
		headerCell = new PdfPCell(new Phrase(etatAvancement + " " + messageSource.getMessage("pdf.process.titlebox.percentavancementmarche", null, localtion), contentNormalFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		headerTable.setSpacingAfter(0f);
		headerTable.setWidths(wf1);
		addContent(headerTable);
		
		PdfPTable parent = new PdfPTable(3);
		parent.setSpacingBefore(0f);
		parent.setWidthPercentage(100f);
		float[] wfParent = {
				70,
				5,
				25
			};
		parent.setWidths(wfParent);
		PdfPCell cellLeft,cellMiddle,cellRight;
		
		/////////
		//Add Group Header
		PdfPCell[] groupHeaderCell = new PdfPCell[3];
		groupHeaderCell[0] = new PdfPCell();
		groupHeaderCell[0].setBorder(Rectangle.NO_BORDER);
		
		groupHeaderCell[1] = new PdfPCell(new Phrase("ADVANCEMENT", contentBoldFont));
		groupHeaderCell[1].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));

		groupHeaderCell[2] = new PdfPCell(new Phrase("RETENUES", contentBoldFont));
		groupHeaderCell[2].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		float[] wfGroupHeader = {
				25,
				20,
				20
			};
		for (PdfPCell pdfPCell : groupHeaderCell) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		PdfPTable groupHeaderTable = new PdfPTable(3);
		groupHeaderTable.setWidthPercentage(100f);
		groupHeaderTable.setWidths(wfGroupHeader);
		addCellsToTable(groupHeaderTable,groupHeaderCell);
		
		cellLeft = new PdfPCell(groupHeaderTable);
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);
		
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		
		cellRight = new PdfPCell(new Phrase("Commentaires / Informations : ", contentBoldFont));
		cellRight.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellRight);
		
		//Header
		//create table header
		PdfPCell[] processTableHeader = new PdfPCell[7];
		processTableHeader[0] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.number", null, localtion), contentBoldFont));
		processTableHeader[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.libelle", null, localtion), contentBoldFont));
		processTableHeader[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.date", null, localtion), contentBoldFont));
		processTableHeader[3] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.cumule", null, localtion), contentBoldFont));
		processTableHeader[4] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.mois", null, localtion), contentBoldFont));
		processTableHeader[5] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.cumule", null, localtion), contentBoldFont));
		processTableHeader[6] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.mois", null, localtion), contentBoldFont));
		
		for (PdfPCell pdfPCell : processTableHeader) {
			pdfPCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		//End
		PdfPTable processDtoTable = new PdfPTable(7);
		processDtoTable.setWidthPercentage(100f);
		addCellsToTable(processDtoTable, processTableHeader);
		
		float[] wf = {
				5,	 //No 3 
				10,  //Libellé risque codification 
				10,	 //Origine de détection 
				10,	 //CR 3 
				10,	// Niveau de risque 5
				10,
				10
			};
		processDtoTable.setWidthPercentage(70f);
		processDtoTable.setWidths(wf);
		processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);

		cellLeft = new PdfPCell(processDtoTable);
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);
		
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		
		parent.addCell(cellMiddle);
		
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.TOP); 
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		processDtoTable.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		
		String tmp;
		processDtoTable = new PdfPTable(7);
		if(processes !=null && processes.length() > 0){
			String arr[] = processes.split(Constants.SEPRATE);
			for(int i=0;i < arr.length;i++){
				tmp = arr[i]; 
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp, contentNormalFont)));
			}
			processDtoTable.setWidthPercentage(70f);
			processDtoTable.setWidths(wf);
			processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		}
		cellLeft = new PdfPCell(processDtoTable);
		parent.addCell(cellLeft);
		
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		
	
		PdfPTable commanttaire = addProcessCommantaire();
		commanttaire.setWidthPercentage(70f);
		
		cellRight = new PdfPCell(commanttaire);
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		//Total Situation
		processDtoTable = addtotalsituation(totalsituation);
		processDtoTable.setWidthPercentage(70f);
		processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		cellLeft = new PdfPCell(processDtoTable);
		parent.addCell(cellLeft);
		
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		
		//Space
		cellLeft = new PdfPCell();
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		
		cellLeft = new PdfPCell();
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		
		cellLeft = new PdfPCell();
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		//End
		
		
		cellLeft = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.detaildesretenuesappliques", null,localtion),contentBoldFont));
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);
		
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		
		processDtoTable = addGrid2(grid2);
		processDtoTable.setWidthPercentage(70f);
		processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		cellLeft = new PdfPCell(processDtoTable);
		parent.addCell(cellLeft);
		
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		
		cellLeft = new PdfPCell();
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);
		
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		//Add Sapce
		//Space
		cellLeft = new PdfPCell();
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		
		cellLeft = new PdfPCell();
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		
		cellLeft = new PdfPCell();
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);
		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		parent.addCell(cellRight);
		//End
		
		//End
		
		processDtoTable = addGrid3(grid3);
		processDtoTable.setWidthPercentage(70f);
		processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		cellLeft = new PdfPCell(processDtoTable);
		parent.addCell(cellLeft);

		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);
		
		cellRight = new PdfPCell();
		cellRight.setBorder(Rectangle.NO_BORDER);
		cellRight.enableBorderSide(Rectangle.RIGHT); 
		cellRight.enableBorderSide(Rectangle.LEFT); 
		cellRight.enableBorderSide(Rectangle.BOTTOM); 
		parent.addCell(cellRight);
		addContent(parent);
		
	}
	
	private PdfPTable addtotalsituation(String totalsituation) throws DocumentException{
		PdfPTable processDtoTable = new PdfPTable(5);
		processDtoTable.addCell(new PdfPCell(new Phrase(messageSource.getMessage("FicheST.totalSituation", null, localtion), contentBoldFont)));
		boolean isEmpty = true;
		if(totalsituation != null && totalsituation.length() > 0){
			String[] tmp = totalsituation.split(Constants.SEPRATE);
			if(tmp != null && tmp.length > 3){
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp[0], contentNormalFont)));
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp[1] != null ? tmp[1] : "", contentNormalFont)));
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp[2] != null ? tmp[2] : "", contentNormalFont)));
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp[3] != null ? tmp[3] : "", contentNormalFont)));
			}
		}
		if(isEmpty){
			processDtoTable.addCell(new PdfPCell(new Phrase("", contentNormalFont)));
			processDtoTable.addCell(new PdfPCell(new Phrase("", contentNormalFont)));
			processDtoTable.addCell(new PdfPCell(new Phrase("", contentNormalFont)));
			processDtoTable.addCell(new PdfPCell(new Phrase("", contentNormalFont)));
		}
		float[] wf = {
				25,
				10,
				10,
				10,
				10
			};
		processDtoTable.setWidths(wf);
		return processDtoTable;
	}
	
	private PdfPTable addGrid2(String grid2){
		//create table header
		PdfPCell[] grid2TableHeader = new PdfPCell[3];
		
		grid2TableHeader[0] = new PdfPCell(new Phrase("", contentBoldFont));

		grid2TableHeader[1] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.amount2", null, localtion), contentBoldFont));
		grid2TableHeader[1].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));

		grid2TableHeader[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.pourcentageDuRegularise", null, localtion), contentBoldFont));
		grid2TableHeader[2].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		for (PdfPCell pdfPCell : grid2TableHeader) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		
		PdfPTable grid2Table = new PdfPTable(3);
		PdfPCell grid2Cell;
		
		addCellsToTable(grid2Table, grid2TableHeader);
		String[] tmp = grid2.split(Constants.SEPRATE);
		String[] messages = {messageSource.getMessage("pdf.process.accomptes.refacturations", null, localtion),
				messageSource.getMessage("pdf.synthese.prorata", null, localtion),
				messageSource.getMessage("FicheST.RefacturationsDontProrata", null, localtion),
				messageSource.getMessage("FicheST.RefacturationsDontProrataEtAutre", null,localtion),
				messageSource.getMessage("pdf.process.titlebox.penalites", null, localtion)};
		for(int i=0; i < tmp.length ; i++){
			switch (i) {
			case 0:
				grid2Cell = new PdfPCell(new Phrase(messages[0], contentBoldFont));
				grid2Cell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
				grid2Table.addCell(grid2Cell);
				break;
			case 2:
				grid2Cell = new PdfPCell(new Phrase(messages[1], contentBoldFont));
				grid2Cell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
				grid2Table.addCell(grid2Cell);
				break;
			case 4:
				grid2Cell = new PdfPCell(new Phrase(messages[2], contentBoldFont));
				grid2Cell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
				grid2Table.addCell(grid2Cell);
				break;
			case 6:
				grid2Cell = new PdfPCell(new Phrase(messages[3], contentBoldFont));
				grid2Cell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
				grid2Table.addCell(grid2Cell);
				break;
			case 8:
				grid2Cell = new PdfPCell(new Phrase(messages[4], contentBoldFont));
				grid2Cell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
				grid2Table.addCell(grid2Cell);
				break;
			}
			grid2Table.addCell(new PdfPCell(new Phrase(tmp[i], contentBoldFont)));
		}	
		float[] wf = {
				25,
				10,
				20
			};
		try {
			grid2Table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			grid2Table.setWidths(wf);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return grid2Table;
	}
	
	private PdfPTable addGrid3(String grid3) throws DocumentException {
		//create table header
		PdfPCell[] grid3TableHeader = new PdfPCell[8];
		   
		grid3TableHeader[0] = new PdfPCell(new Phrase("", contentBoldFont));
		grid3TableHeader[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.canto", null, localtion), contentBoldFont));
		grid3TableHeader[1].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		grid3TableHeader[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.badge", null, localtion), contentBoldFont));
		grid3TableHeader[2].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));

		grid3TableHeader[3] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.grue", null, localtion), contentBoldFont));
		grid3TableHeader[3].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		grid3TableHeader[4] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.lift", null, localtion), contentBoldFont));
		grid3TableHeader[4].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		grid3TableHeader[5] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.benne", null, localtion), contentBoldFont));
		grid3TableHeader[5].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		grid3TableHeader[6] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.nettoy", null, localtion), contentBoldFont));
		grid3TableHeader[6].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		grid3TableHeader[7] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.autres", null, localtion), contentBoldFont));
		grid3TableHeader[7].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		
		for (PdfPCell pdfPCell : grid3TableHeader) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		
		PdfPTable grid3Table = new PdfPTable(8);
		PdfPCell grid3Cell;
		
		addCellsToTable(grid3Table, grid3TableHeader);
			
		String[] tmp = grid3.split(Constants.SEPRATE);
		
		String[] messages = {messageSource.getMessage("FicheST.nombre", null, localtion),messageSource.getMessage("FicheST.amount2", null, localtion)};
		for(int i=0; i < tmp.length ; i++){
			switch (i) {
			case 0:
				grid3Cell = new PdfPCell(new Phrase(messages[0], contentBoldFont));
				grid3Cell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
				grid3Table.addCell(grid3Cell);
				break;
			case 7:
				grid3Cell = new PdfPCell(new Phrase(messages[1], contentBoldFont));
				grid3Cell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
				grid3Table.addCell(grid3Cell);
				break;
			}
			grid3Table.addCell(new PdfPCell(new Phrase(tmp[i], contentNormalFont)));
		}	
		float[] wf = {
				15,
				10,
				10,
				10,
				10,
				10,10,
				10
			};
		try {
			grid3Table.setWidths(wf);
			grid3Table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return grid3Table;
	}
	
	private PdfPTable addProcessCommantaire() throws DocumentException{
		PdfPTable commantaireTable = new PdfPTable(1);
		PdfPCell pdfPCell = new PdfPCell(new Phrase(commentaire.equals("null") ? "" : commentaire, contentNormalFont));
		pdfPCell.setBorder(Rectangle.NO_BORDER);
		commantaireTable.addCell(pdfPCell);	
		float[] wf = {
				15
			};
		commantaireTable.setWidths(wf);
		commantaireTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		return commantaireTable;
	}
	//End
	
	//Add Deduction Report
	private void addDeductionDto() throws DocumentException {

		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		//Add deduction
		PdfPTable deductionTable = addDeductionTable();
		addContent(deductionTable);
		//Add total deduction
		PdfPTable totalTable = addTotalDeduction();
		addContent(totalTable);
		lineBreak();
		
		PdfPCell leftCell,middleCell,rightCell;
		PdfPTable parent = new PdfPTable(3);
		parent.setSpacingBefore(0f);
		parent.setWidthPercentage(100f);
		float[] wfParent = {
				70,  //Libellé risque codification 
				5,	 //Origine de détection 
				25
			};
		parent.setWidths(wfParent);

		//Add Penalties
		leftCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.penalites", null, localtion),contentBoldFont));
		leftCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(leftCell);
		
		middleCell = new PdfPCell();
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);
		
		rightCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.comment", null, localtion),contentBoldFont));
		rightCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(rightCell);
		
		
		PdfPTable penaltyTable = addPenaltiesTable();
		leftCell = new PdfPCell(penaltyTable);
		parent.addCell(leftCell);
		
		middleCell = new PdfPCell();
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);
		
		PdfPTable commentairePdfPTable = addDeductionCommantaire();
		rightCell = new PdfPCell(commentairePdfPTable);
		parent.addCell(rightCell);
		
		leftCell = new PdfPCell(addAmountTable());
		parent.addCell(leftCell);
		
		middleCell = new PdfPCell();
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);
		
		rightCell = new PdfPCell();
		rightCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(rightCell);
	
		addContent(parent);
		
	}

	/*private PdfPTable addInformationTable() throws DocumentException {
		
		PdfPTable informationTable = new PdfPTable(6);
		PdfPCell informationCell = new PdfPCell();
		
		//Add Content
		String[] tmp = information.split(Constants.SEPRATE);
		String [] messages = {"Chantier : ","Responsable do lot : ","Société : "};
		for(int i=0;i<tmp.length;i++){
			switch (i) {
			case 0:
				informationCell = new PdfPCell(new Phrase(messages[0], contentBoldFont));
				informationCell.setBorder(Rectangle.NO_BORDER);
				informationTable.addCell(informationCell);
				break;
			case 1:
				informationCell = new PdfPCell(new Phrase(messages[1], contentBoldFont));
				informationCell.setBorder(Rectangle.NO_BORDER);
				informationTable.addCell(informationCell);
				break;
			case 2:
				informationCell = new PdfPCell(new Phrase(messages[2], contentBoldFont));
				informationCell.setBorder(Rectangle.NO_BORDER);
				informationTable.addCell(informationCell);
				break;
			}
			informationCell = new PdfPCell(new Phrase(tmp[i], contentNormalFont));
			informationCell.setBorder(Rectangle.NO_BORDER);
			informationTable.addCell(informationCell);
		}
		float[] wf = {
				3,  //Libellé risque codification 
				10,	 //Origine de détection 
				5,	 //CR 3 
				10,	// Niveau de risque 5
				2,
				10
			};
		informationTable.setWidthPercentage(100f);
		informationTable.setWidths(wf);
		return informationTable;
	}*/
	
	private PdfPTable addDeductionTable() throws DocumentException {
		//Title
		PdfPCell[] groupTitleCell = new PdfPCell[1];
		groupTitleCell[0] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.retenuesEffectuees", null, localtion),contentBoldFont));	
		float[] wfTitle = {
				100
			};
		for (PdfPCell pdfPCell : groupTitleCell) {
			pdfPCell.setBorder(Rectangle.NO_BORDER);
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		}

		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setWidthPercentage(100f);
		titleTable.setWidths(wfTitle);
		addCellsToTable(titleTable,groupTitleCell);
		addContent(titleTable);
		
		//Add Group Header
		PdfPCell[] groupHeaderCell = new PdfPCell[3];
		groupHeaderCell[0] = new PdfPCell();
		groupHeaderCell[0].setBorder(Rectangle.NO_BORDER);
		groupHeaderCell[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.quante", null, localtion), contentBoldFont));
		groupHeaderCell[1].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));

		groupHeaderCell[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.montant", null, localtion), contentBoldFont));
		groupHeaderCell[2].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		float[] wfGroupHeader = {
				10,
				70,
				20
			};
		for (PdfPCell pdfPCell : groupHeaderCell) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		PdfPTable groupHeaderTable = new PdfPTable(3);
		groupHeaderTable.setWidthPercentage(100f);
		groupHeaderTable.setWidths(wfGroupHeader);
		addCellsToTable(groupHeaderTable,groupHeaderCell);
		addContent(groupHeaderTable);
		
		
		//create table header
		String[] arrPrestations = prestations.split(Constants.SEPRATE);
		PdfPCell[] deductionTableHeader = new PdfPCell[10];
		deductionTableHeader[0] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.date",null,localtion), contentBoldFont));
		deductionTableHeader[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.canto",null,localtion) + arrPrestations[0], contentBoldFont));
		deductionTableHeader[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.badge",null,localtion) + arrPrestations[1], contentBoldFont));
		deductionTableHeader[3] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.grue",null,localtion) + arrPrestations[2], contentBoldFont));	
		deductionTableHeader[4] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.lift",null,localtion) + arrPrestations[3], contentBoldFont));		
		deductionTableHeader[5] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.bene",null,localtion) + arrPrestations[4], contentBoldFont));	
		deductionTableHeader[6] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.nettoyage",null,localtion) + arrPrestations[5], contentBoldFont));
		deductionTableHeader[7] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.autres",null,localtion), contentBoldFont));
		deductionTableHeader[8] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.prorata",null,localtion) + arrPrestations[6], contentBoldFont));
		deductionTableHeader[9] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.refacturations",null,localtion), contentBoldFont));
		
		for (PdfPCell pdfPCell : deductionTableHeader) {
			pdfPCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		PdfPTable deductionTable = new PdfPTable(10);
		
		//Add Header
		addCellsToTable(deductionTable, deductionTableHeader);
		
		//Add Content
		if(deductions!=null && deductions.length() > 0){
			deductions = deductions.substring(0,deductions.length() - 1);
		}
		String[] tmp = deductions.split(Constants.SEPRATE);
		for(int i=0;i<tmp.length;i++){
			deductionTable.addCell(new PdfPCell(new Phrase(tmp[i], contentNormalFont)));
		}

		float[] wf = {
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				10,
				10
			};
		deductionTable.setWidthPercentage(100f);
		deductionTable.setWidths(wf);

		return deductionTable;
	}
	

	private PdfPTable addTotalDeduction() throws DocumentException {
		
		PdfPTable totalDeduction = new PdfPTable(10);
		String[] tmp = totaldeduction.split(Constants.SEPRATE);
		for(int i=0; i < tmp.length ; i++){
			if(i == 0){
				totalDeduction.addCell(new PdfPCell(new Phrase(messageSource.getMessage("FicheST.total", null, localtion), contentBoldFont)));
			}
			totalDeduction.addCell(new PdfPCell(new Phrase(tmp[i], contentBoldFont)));
		}
		float[] wf = {
				10,
				10,
				10,
				10,	
				10,
				10,
				10,
				10,
				10,
				10
			};
		totalDeduction.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		totalDeduction.setWidths(wf);
		totalDeduction.setWidthPercentage(100f);

		return totalDeduction;
	}
	
	private PdfPTable addPenaltiesTable() throws DocumentException {
		//create table header
		
		PdfPCell[] grid3TableHeader = new PdfPCell[3];
		grid3TableHeader[0] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.date",null,localtion), contentBoldFont));	
		grid3TableHeader[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.penalties.montant",null,localtion), contentBoldFont));
		grid3TableHeader[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.penalties.commentaires",null,localtion), contentBoldFont));
		
		for (PdfPCell pdfPCell : grid3TableHeader) {
			pdfPCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		PdfPTable penlatyTable = new PdfPTable(3);
		addCellsToTable(penlatyTable, grid3TableHeader);
		
		//penaltys = penaltys.replace("?"," ");
		String[] tmp = penaltys.split(Constants.SEPRATE);
		
		for(int i=0; i < tmp.length ; i++){
			penlatyTable.addCell(new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : tmp[i], contentNormalFont)));
		}	
		float[] wf = {
				15,
				15,
				15
			};
	
		penlatyTable.setWidths(wf);
		penlatyTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		return penlatyTable;
	}
	
	private PdfPTable addAmountTable() throws DocumentException {

		PdfPTable amountTable = new PdfPTable(3);
		PdfPCell amountCell;
		String[] tmp = amount.split(Constants.SEPRATE);
		
		for(int i=0; i < tmp.length ; i++){
			if(i == 0){
				amountCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.total", null, localtion), contentBoldFont));
				amountCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
				amountTable.addCell(amountCell);
			}
			amountCell = new PdfPCell(new Phrase(tmp[i], contentNormalFont));
			amountCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
			amountTable.addCell(amountCell);
			
			amountCell = new PdfPCell();
			amountCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
			amountTable.addCell(amountCell);
		}	
		float[] wf = {
				15,
				15,
				15
			};
	
		amountTable.setWidths(wf);
		amountTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		return amountTable;
	}
	
	private PdfPTable addDeductionCommantaire() throws DocumentException{
		//create table header
		PdfPCell[] commantaireTableHeader = new PdfPCell[1];
		commantaireTableHeader[0] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.comment2", null,localtion)+" : ", contentBoldFont));
		for (PdfPCell pdfPCell : commantaireTableHeader) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
	
		PdfPTable commantaireTable = new PdfPTable(1);
		deductionComment1 = deductionComment1.equals("null") ? "" : deductionComment1;
		deductionComment2 = deductionComment2.equals("null") ? "" : deductionComment2;
		PdfPCell pdfPCell = new PdfPCell(new Phrase(deductionComment1 + "\n" + deductionComment2, contentNormalFont));
		pdfPCell.setBorder(Rectangle.NO_BORDER);
		commantaireTable.addCell(pdfPCell);	
		float[] wf = {
				15
			};
		commantaireTable.setWidths(wf);
		commantaireTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		return commantaireTable;
	}
	//End

	public void setDeductions(String deductions) {
		this.deductions = deductions;
	}

	public String getDeductions() {
		return deductions;
	}

	public void setTotaldeduction(String totaldeduction) {
		//if(totaldeduction!=null && totaldeduction.length() > 0)
			//totaldeduction = totaldeduction.replace("?"," ");
		this.totaldeduction = totaldeduction;
	}

	public String getTotaldeduction() {
		return totaldeduction;
	}

	public void setPenaltys(String penaltys) {
		this.penaltys = penaltys;
	}

	public String getPenaltys() {
		return penaltys;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmount() {
		return amount;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getInformation() {
		return information;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setEtatAvancement(String etatAvancement) {
		this.etatAvancement = etatAvancement;
	}

	public String getEtatAvancement() {
		return etatAvancement;
	}

	public void setDeductionComment1(String deductionComment1) {
		this.deductionComment1 = deductionComment1;
	}

	public String getDeductionComment1() {
		return deductionComment1;
	}

	public void setDeductionComment2(String deductionComment2) {
		this.deductionComment2 = deductionComment2;
	}

	public String getDeductionComment2() {
		return deductionComment2;
	}
}
