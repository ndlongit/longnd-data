package com.structis.fichesst.server.service.exportpdf;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.structis.fichesst.server.bean.domain.CautionFournie;
import com.structis.fichesst.server.bean.domain.Deduction;
import com.structis.fichesst.server.bean.domain.FicheSt;
import com.structis.fichesst.server.bean.domain.Gestion;
import com.structis.fichesst.server.bean.domain.Penalty;
import com.structis.fichesst.server.bean.domain.Progress;
import com.structis.fichesst.shared.util.Constants;

public class ExportPdfCustomFichest extends ExportPdfManager {
	
	private List<FicheSt> listFicheSt;
	//Grid 1
	private String generaleInformation;
	private String conditionsparticulieres;
	private String prestations;
	private String informationscomplementaires;
	private String cautionFournies;
	
	//Gestion
	private List<String> gestions;
	private List<String> totalgestions;
	private String alltotalgestion;
	private String summary;
	private String budget;
	
	//Progress
	private String processes;
	private String totalsituation;
	private String grid2;
	private String grid3;
	private String etatAvancement;
	
	//Duduction
	private String deductions;
	private String totaldeduction;
	private String penaltys;
	private String amount;
	private String information;
	private String commentaire;
	private String internalCommentaire;
	
	private ResourceBundleMessageSource messageSource;
	
	private NumberFormat numberFormat;
	private DateFormat dateFormat;
	private String hasGestion;
	
	public ExportPdfCustomFichest(OutputStream stream,ResourceBundleMessageSource messageSource,Locale locale)
			throws DocumentException, IOException {
		super(stream, locale);
		this.messageSource = messageSource;
		document.setPageSize(PageSize.A4.rotate());
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
		FicheSt ficheSt = null;
		for(int i=0;i<listFicheSt.size();i++){
			ficheSt = listFicheSt.get(i);
			String generaleInformation = createGeneralInformation(ficheSt);
			String conditionsparticulieres = createCondition(ficheSt);
			String prestations = createPresation(ficheSt);
			String informationscomplementaires = createInformationComplementaires(ficheSt);
			String cautionFournies = createCautionGrid(ficheSt.getCautionFournies());

			//Parameters for gestional report
			List<String> listSrcGestion = new ArrayList<String>();
			List<String> listSumGroupGestion = new ArrayList<String>();
			String alltotalgestion = createGestion(ficheSt.getGestions(), listSrcGestion, listSumGroupGestion);	
			String summary = createGestionSummary(ficheSt.getGestions());
			String budget = createBudget(ficheSt);
			
			//Parameters for report process
			List<String> listSrcProcess = new ArrayList<String>();
			String sumProcesses = createProgress(ficheSt.getProgresses(), listSrcProcess);
			String grid2_report = createDetailDesRetenuesAppliquies(ficheSt.getGestions(), ficheSt.getDeductions(), ficheSt.getPenalties(),ficheSt);
			String grid3_report = createProgessGrird3Report(ficheSt);
			
			//Deducation
			String chantierNom = ficheSt.getConductor() != null ? ficheSt.getConductor().getChantier().getNom() : "";
			String conductorSiTravaux = ficheSt.getConductor() != null ? ficheSt.getConductor().getIdSiTravaux() : "";
			String information = chantierNom + Constants.SEPRATE + conductorSiTravaux + Constants.SEPRATE + ficheSt.getSociete();
			String[] arrDeduction = createDeductionGrid(ficheSt);
			String[] arrPenalty = createPenalite(ficheSt.getPenalties());
			
			this.generaleInformation = generaleInformation;
			this.conditionsparticulieres = conditionsparticulieres;
			this.prestations = prestations;
			this.informationscomplementaires = informationscomplementaires;
			this.cautionFournies = cautionFournies;
			this.gestions = listSrcGestion;
			this.totalgestions = listSumGroupGestion;
			this.alltotalgestion = alltotalgestion;
			this.summary = summary;
			this.budget = budget;
			
			this.processes = listSrcProcess.get(0);
			this.totalsituation = sumProcesses;
			this.grid2 = grid2_report;
			this.grid3 = grid3_report;
			this.etatAvancement = calculateEtatAvancement(ficheSt.getGestions(),ficheSt.getProgresses());
			
			this.information = information;
			this.deductions = arrDeduction[0];
			this.totaldeduction = arrDeduction[1];
			this.penaltys = arrPenalty[0];
			this.amount = arrPenalty[1];
			this.commentaire = ficheSt.getAcptCommentaires();
			this.internalCommentaire = ficheSt.getAcptCommentairesInternes();
			
			
			addAllFichestDto();
		}		
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
		//String [] messages = {"Chantier : ","Lot : ","Conducteur de travaux : ","Société : ","Type de lot : ","Montant objectif:"};
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
				5,
				25,
				5, 
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
		if(hasGestion != null && hasGestion.equals("true")){
			addGestionDto();
			lineBreak();
		}
		
		addProcessDto();
		lineBreak();
		
		if(hasGestion != null && hasGestion.equals("true")){
			addDeductionDto();
		}
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
	
	//Add Gestion Report
	private void addGestionDto() throws DocumentException {
		
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);

		PdfPCell headerCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.gestion", null, localtion), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		addContent(headerTable);
	
		//Add Group Header
		PdfPCell[] groupHeaderCell = new PdfPCell[6];
		groupHeaderCell[0] = new PdfPCell();
		groupHeaderCell[0].setBorder(Rectangle.NO_BORDER);
		groupHeaderCell[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.traite", null, localtion), contentBoldFont));
		groupHeaderCell[1].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		groupHeaderCell[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.atraite", null, localtion), contentBoldFont));
		groupHeaderCell[2].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		groupHeaderCell[3] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.budgetconforme", null, localtion), contentBoldFont));
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
				PdfPCell pdfPCell = null;
				for(int i=0;i < arr.length;i++){
					tmp = arr[i] ;//== null ? "" : arr[i]; 
					pdfPCell = new PdfPCell(new Phrase(tmp.equals("null") ? "" : tmp, contentNormalFont));
					pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
					gestionDtoTable.addCell(pdfPCell);
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
		gestionDtoCell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
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
		
		PdfPCell gestionDtoCell = new PdfPCell(new Phrase("Montant REX ", contentBoldFont));
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
				36,	 //CR 3 
				5,	// Niveau de risque 5
				18,	// Phase 
				5,	 //Statut 
				5,	// Date de création 
				5,	// Date de levée 
				5,
				7,	// Cout UO 
				5,	// Cout ST 
				17,	// Vu
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
	
	//Add Process Report
	private void addProcessDto() throws DocumentException {		

		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		PdfPCell headerCell = new PdfPCell(new Phrase("SUVI DES AVANCEMENTS", contentBoldFont));
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
		headerCell = new PdfPCell(new Phrase("Etat d’avancement: ", contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		
		headerCell = new PdfPCell(new Phrase(etatAvancement + " % AVANCEMENT / MARCHE", contentNormalFont));
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
		if(processes !=null && processes.length() > 0)
		{
			String arr[] = processes.split(Constants.SEPRATE);
			for(int i=0;i < arr.length;i++){
				tmp = arr[i]; 
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp.equals("null") ? "" : tmp, contentNormalFont)));
			}
			processDtoTable.setWidthPercentage(70f);
			processDtoTable.setWidths(wf);
			processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
	
			cellLeft = new PdfPCell(processDtoTable);
			parent.addCell(cellLeft);
			
			cellMiddle = new PdfPCell();
			cellMiddle.setBorder(Rectangle.NO_BORDER);
			parent.addCell(cellMiddle);
			
		
			PdfPTable commanttaire = addProcessCommantaire("");
			commanttaire.setWidthPercentage(70f);
			
			cellRight = new PdfPCell(commanttaire);
			cellRight.setBorder(Rectangle.NO_BORDER);
			cellRight.enableBorderSide(Rectangle.RIGHT); 
			cellRight.enableBorderSide(Rectangle.LEFT); 
			parent.addCell(cellRight);
		}
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
		
		
		cellLeft = new PdfPCell(new Phrase("DETAIL DES RETENUES APPLIQUES",contentBoldFont));
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
		processDtoTable.addCell(new PdfPCell(new Phrase("TOTAL SITUATION", contentBoldFont)));
		
		String[] tmp = totalsituation.split(Constants.SEPRATE);
		if(tmp != null && tmp.length > 3){
			processDtoTable.addCell(new PdfPCell(new Phrase(tmp[0], contentNormalFont)));
			processDtoTable.addCell(new PdfPCell(new Phrase(tmp[1] != null ? tmp[1] : "", contentNormalFont)));
			processDtoTable.addCell(new PdfPCell(new Phrase(tmp[2] != null ? tmp[2] : "", contentNormalFont)));
			processDtoTable.addCell(new PdfPCell(new Phrase(tmp[3] != null ? tmp[3] : "", contentNormalFont)));
		}else{
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
	
	/**
	 * DETAIL DES RETENUES APPLIQUES
	 * @param grid2
	 * @return
	 */
	private PdfPTable addGrid2(String grid2){
		//create table header
		PdfPCell[] grid2TableHeader = new PdfPCell[3];
		
		grid2TableHeader[0] = new PdfPCell(new Phrase("", contentBoldFont));

		grid2TableHeader[1] = new PdfPCell(new Phrase("Montant", contentBoldFont));
		grid2TableHeader[1].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));

		grid2TableHeader[2] = new PdfPCell(new Phrase("Pourcentage du régularisé", contentBoldFont));
		grid2TableHeader[2].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		for (PdfPCell pdfPCell : grid2TableHeader) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		
		PdfPTable grid2Table = new PdfPTable(3);
		PdfPCell grid2Cell;
		
		addCellsToTable(grid2Table, grid2TableHeader);
		String[] tmp = grid2.split(Constants.SEPRATE);
		String[] messages = {"Refacturations" ,"Prorata" ,"Refacturations dont prorata","Refacturations dont prorata et autre", "Pénalités"};
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
	
	private PdfPTable addGrid3(String grid3){
		//create table header
		PdfPCell[] grid3TableHeader = new PdfPCell[8];
		   
		grid3TableHeader[0] = new PdfPCell(new Phrase("", contentBoldFont));
		
		grid3TableHeader[1] = new PdfPCell(new Phrase("Canto", contentBoldFont));
		grid3TableHeader[1].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		grid3TableHeader[2] = new PdfPCell(new Phrase("Badge", contentBoldFont));
		grid3TableHeader[2].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));

		grid3TableHeader[3] = new PdfPCell(new Phrase("Grue", contentBoldFont));
		grid3TableHeader[3].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		grid3TableHeader[4] = new PdfPCell(new Phrase("Lift", contentBoldFont));
		grid3TableHeader[4].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		grid3TableHeader[5] = new PdfPCell(new Phrase("Benne", contentBoldFont));
		grid3TableHeader[5].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		grid3TableHeader[6] = new PdfPCell(new Phrase("Nettoyage", contentBoldFont));
		grid3TableHeader[6].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		grid3TableHeader[7] = new PdfPCell(new Phrase("Autres", contentBoldFont));
		grid3TableHeader[7].setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
		
		
		for (PdfPCell pdfPCell : grid3TableHeader) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		
		PdfPTable grid3Table = new PdfPTable(8);
		PdfPCell grid3Cell;
		
		addCellsToTable(grid3Table, grid3TableHeader);
			
		String[] tmp = grid3.split(Constants.SEPRATE);
		
		String[] messages = {"Nombre","Montant"};
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
	
	private PdfPTable addProcessCommantaire(String commantaire){
		//create table header
		PdfPTable commantaireTable = new PdfPTable(1);
		
		PdfPCell pdfPCell = new PdfPCell(new Phrase(commantaire, contentNormalFont));
		pdfPCell.setBorder(Rectangle.NO_BORDER);
		commantaireTable.addCell(pdfPCell);	
		float[] wf = {
				15
			};
		try {
			commantaireTable.setWidths(wf);
			commantaireTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
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
		leftCell = new PdfPCell(new Phrase("PENALTIES",contentBoldFont));
		leftCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(leftCell);
		
		middleCell = new PdfPCell();
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);
		
		rightCell = new PdfPCell(new Phrase("Commentaires",contentBoldFont));
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
	
	private PdfPTable addInformationTable() throws DocumentException {
		
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
	}
	
	private PdfPTable addDeductionTable() throws DocumentException {
		//Title
		PdfPCell[] groupTitleCell = new PdfPCell[1];
		groupTitleCell[0] = new PdfPCell(new Phrase("RETENUES EFFECTUEES SUR BON D'ACOMPTE",contentBoldFont));	
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
		
		String[] arrPrestations = prestations.split(Constants.SEPRATE);
		//create table header
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
		String[] tmp = deductions.split(Constants.SEPRATE);
		for(int i=0;i<tmp.length;i++){
			deductionTable.addCell(new PdfPCell(new Phrase(tmp[i], contentNormalFont)));
		}

		float[] wf = {
				10,  //Libellé risque codification 
				10,	 //Origine de détection 
				10,	 //CR 3 
				10,	// Niveau de risque 5
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
				totalDeduction.addCell(new PdfPCell(new Phrase("Total", contentBoldFont)));
			}
			totalDeduction.addCell(new PdfPCell(new Phrase(tmp[i], contentBoldFont)));
		}
		float[] wf = {
				10,  //Libellé risque codification 
				10,	 //Origine de détection 
				10,	 //CR 3 
				10,	// Niveau de risque 5
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
		
		penaltys = penaltys.replace("?"," ");
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
				amountCell = new PdfPCell(new Phrase("Total", contentBoldFont));
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
	
	private PdfPTable addDeductionCommantaire(){
		//create table header
		PdfPCell[] commantaireTableHeader = new PdfPCell[1];
		commantaireTableHeader[0] = new PdfPCell(new Phrase("Commentaires / Informations : ", contentBoldFont));
		

		for (PdfPCell pdfPCell : commantaireTableHeader) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
	
		PdfPTable commantaireTable = new PdfPTable(1);
		
		//addCellsToTable(commantaireTable, commantaireTableHeader);
		PdfPCell pdfPCell = new PdfPCell(new Phrase(commentaire + "\n" + internalCommentaire, contentNormalFont));
		pdfPCell.setBorder(Rectangle.NO_BORDER);
		commantaireTable.addCell(pdfPCell);	
		float[] wf = {
				15
			};
		try {
			commantaireTable.setWidths(wf);
			commantaireTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return commantaireTable;
	}
		
	/**
	 * 
	 * @return
	 */
	private String createGeneralInformation(FicheSt ficheSt){

		String chantierName = ficheSt.getConductor() !=null ? ficheSt.getConductor().getChantier().getNom() : "";
		String lotName = ficheSt.getLot() != null ? ficheSt.getLot().getName() : "";
		String lotTypeName = ficheSt.getLotType() != null ? ficheSt.getLotType().getName() : "";
		/*String srcGeneralInformation = chantierName + 
		Constants.SEPRATE + ficheSt.getLot().getName() + Constants.SEPRATE + 
		ficheSt.getIdSiTravaux() + Constants.SEPRATE + ficheSt.getSociete() + Constants.SEPRATE + ficheSt.getLotType().getName() + 
		Constants.SEPRATE + ficheSt.getObjectif();*/
		String srcGeneralInformation = append(chantierName,lotName,ficheSt.getIdSiTravaux(),ficheSt.getSociete(),lotTypeName,ficheSt.getObjectif());
		return srcGeneralInformation;
	}
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private String createCondition(FicheSt ficheSt){
		/*String conditionsparticulieres = ficheSt.getPaymentMode().getLabel() + Constants.SEPRATE + ficheSt.getRg() + Constants.SEPRATE + ficheSt.getRefDecenale().getLabel() + Constants.SEPRATE + 
		ficheSt.getRefDdeAgrement().getLabel() + Constants.SEPRATE + ficheSt.getRefDgdPresente().getLabel() + Constants.SEPRATE + ficheSt.getDateDgdPresente();*/
		String conditionsparticulieres = append(ficheSt.getPaymentMode().getLabel(),ficheSt.getRg(),ficheSt.getRefDecenale().getLabel(),ficheSt.getRefDdeAgrement().getLabel(),
				ficheSt.getRefDgdPresente().getLabel(),ficheSt.getDateDgdPresente());
		return conditionsparticulieres;
	}
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private String createPresation(FicheSt ficheSt){
		/*String prestations = ficheSt.getPrestaPilotage() + Constants.SEPRATE + ficheSt.getPrestaAssurances() + Constants.SEPRATE + ficheSt.getPrestaProrata()  + Constants.SEPRATE + 
		ficheSt.getPrestaCanto() + Constants.SEPRATE + ficheSt.getPrestaBadge() + Constants.SEPRATE + ficheSt.getPrestaGrue() + Constants.SEPRATE + 
		ficheSt.getPrestaLift() + Constants.SEPRATE + ficheSt.getPrestaBenne() + Constants.SEPRATE + ficheSt.getPrestaNettoyage();*/
		String prestations = append(ficheSt.getPrestaPilotage(),ficheSt.getPrestaAssurances(),ficheSt.getPrestaProrata(),ficheSt.getPrestaCanto(),ficheSt.getPrestaBadge(),ficheSt.getPrestaGrue(), 
		ficheSt.getPrestaLift(),ficheSt.getPrestaBenne(),ficheSt.getPrestaNettoyage());
		return prestations;
	}
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private String createInformationComplementaires(FicheSt ficheSt){
		String conductorName = ficheSt.getConductor() != null ? ficheSt.getConductor().getName() : "";
		/*String informationscomplementaires = conductorName + Constants.SEPRATE + ficheSt.getDateMarcheBase();*/
		String informationscomplementaires = append(conductorName,ficheSt.getDateMarcheBase());
		return informationscomplementaires;
	}
	
	/**
	 * 
	 * @param listCautionFournie
	 * @return
	 */
	private String createCautionGrid(List<CautionFournie> listCautionFournie){
		String srcCautionFournies = "";
		//String caution_date = "";
		for(CautionFournie cautionFournie : listCautionFournie){
			//caution_date = cautionFournieDto.getDate() != null ? dateTimeFormat.format(cautionFournieDto.getDate()) : ";
			/*srcCautionFournies += cautionFournie.getDate() + Constants.SEPRATE + cautionFournie.getAmount() + Constants.SEPRATE;*/
			srcCautionFournies += append(cautionFournie.getDate(),cautionFournie.getAmount());
		}
		return srcCautionFournies;
	}
	
	/**
	 * Create Key Gestion
	 * @param listGestion
	 * @return
	 */
	private List<String> createKeyGestion(List<Gestion> listGestion){
		Gestion gestion = null;
		List<String> listKeyGestion = new ArrayList<String>();
		for (int i = 0; i < listGestion.size(); i++) {
			gestion = listGestion.get(i);
			if(listGestion.get(i).getMarche()!=null && !listKeyGestion.contains(listGestion.get(i).getMarche().getLabel())){
				listKeyGestion.add(gestion.getMarche().getLabel());
		}
		}
		return listKeyGestion;
	}
	
	/**
	 * Create Grid Gestion
	 * @param listGestion
	 * @param listSrcGestion
	 * @param listSumGroupGestion
	 * @return
	 */
	private String createGestion(List<Gestion> listGestion,List<String> listSrcGestion,List<String> listSumGroupGestion){
		List<String> listKeyGestion = createKeyGestion(listGestion);
		//List<String> list = new ArrayList<String>();
		Gestion gestion = null;
		String sumGestion = "";
		//Calculate Sum Gestion
		double sumAmount = 0.0;
		double sumAvenants = 0.0;
		double sumArrete = 0.0;
		double sumNonarrete = 0.0;
		double sumProvision = 0.0;
		double sumDevisrefuse = 0.0;
		double sumReelactivitive = 0.0;
		double sumAmount2 = 0.0;
		double sumTotalecart = 0.0;
		double sumTotalfdc = 0.0;
		for(int i = 0;i<listKeyGestion.size();i++){
			String srcGestion = "";
			String sumGroupGestion = "";
			double amount = 0.0;
			double avenants = 0.0;
			double arrete = 0.0;
			double nonarrete = 0.0;
			double provision = 0.0;
			double devisrefuse = 0.0;
			double reelactivitive = 0.0;
			double amount2 = 0.0;
			double ecartSum = 0.0;
			double totalfdcSum = 0.0;
			for(int j =0 ; j < listGestion.size() ; j++){
				gestion = listGestion.get(j);
				if(gestion.getMarche().getLabel().equalsIgnoreCase(listKeyGestion.get(i))){
					String tmp = gestion.getMarche().getLabel() + "                                          " + gestion.getTraite();
					String gestionComment = gestion.getComment() != null ? gestion.getComment() : "";
					String statutLabel = gestion.getStatut() != null ? gestion.getStatut().getLabel() : ""; 
					
					/*srcGestion += gestion.getDevis() + Constants.SEPRATE + statutLabel + Constants.SEPRATE +
					gestion.getLabel() + Constants.SEPRATE + gestionComment + Constants.SEPRATE + gestion.getAmount() + Constants.SEPRATE +
					tmp + Constants.SEPRATE + gestion.getArrete() + Constants.SEPRATE + 
					gestion.getNonArrete() + Constants.SEPRATE + gestion.getProvision() + Constants.SEPRATE +
					gestion.getDevisRefuse() + Constants.SEPRATE + calculateTotalFdc(gestion) + Constants.SEPRATE +
					gestion.getReelActivitive() + Constants.SEPRATE +
					gestion.getType().getLabel() + Constants.SEPRATE +
					gestion.getLabel2() + Constants.SEPRATE +
					gestion.getAmount2() + Constants.SEPRATE +
					calculateEcart(gestion) + Constants.SEPRATE;*/
					srcGestion += append(gestion.getDevis(),statutLabel,gestion.getLabel(),gestionComment,gestion.getAmount(),tmp,gestion.getArrete(),gestion.getNonArrete(),
									gestion.getProvision(),gestion.getDevisRefuse(),calculateTotalFdc(gestion),gestion.getReelActivitive(),gestion.getType().getLabel(),
									gestion.getLabel2(),gestion.getAmount2(),calculateEcart(gestion));
					//Group
					amount += gestion.getAmount();
					avenants += gestion.getTraite();
					arrete += gestion.getArrete();
					nonarrete += gestion.getNonArrete();
					provision += gestion.getProvision();
					devisrefuse += gestion.getDevisRefuse();
					reelactivitive += gestion.getReelActivitive();
					amount2 += gestion.getAmount2();
					ecartSum += calculateEcart(gestion);
					totalfdcSum += calculateTotalFdc(gestion);
				}
			}
			listSrcGestion.add(srcGestion);
			//Calculate Group Sum Gestion
			/*sumGroupGestion += amount + Constants.SEPRATE + avenants + Constants.SEPRATE + arrete + Constants.SEPRATE + nonarrete + Constants.SEPRATE + 
			provision + Constants.SEPRATE + devisrefuse + Constants.SEPRATE + totalfdcSum + Constants.SEPRATE + reelactivitive + Constants.SEPRATE + amount2 + Constants.SEPRATE + 
			ecartSum + Constants.SEPRATE;*/
			sumGroupGestion += append(amount,avenants,arrete,nonarrete,provision,devisrefuse,totalfdcSum,reelactivitive,amount2,ecartSum);
			listSumGroupGestion.add(sumGroupGestion);
			//Calculate all gestion
			
			//Sum gestion
			sumAmount += amount;
			sumAvenants += avenants;
			sumArrete += arrete;
			sumNonarrete += nonarrete;
			sumProvision += provision;
			sumDevisrefuse += devisrefuse;
			sumReelactivitive += reelactivitive;
			sumAmount2 += amount2;
			sumTotalecart += ecartSum;
			sumTotalfdc += totalfdcSum;
		}
		/*sumGestion = sumAmount +  Constants.SEPRATE + sumAvenants +  Constants.SEPRATE + sumArrete +  Constants.SEPRATE + 
		sumNonarrete + Constants.SEPRATE + sumProvision +  Constants.SEPRATE + sumDevisrefuse +  Constants.SEPRATE + 
		sumTotalfdc +  Constants.SEPRATE + sumReelactivitive + Constants.SEPRATE + sumAmount2 +  Constants.SEPRATE + sumTotalecart +  Constants.SEPRATE;*/
		sumGestion = append(sumAmount,sumAvenants,sumArrete,sumNonarrete,sumProvision,sumDevisrefuse,sumTotalfdc,sumReelactivitive,sumAmount2,sumTotalecart);
		return sumGestion;
	}
	
	/**
	 * 
	 * @param listGestion
	 * @return
	 */
	private String createGestionSummary(List<Gestion> listGestion){	
		double totalObj = 0.0;
		double totalTF = 0.0;
		double totalTS = 0.0;
		double totalRD = 0.0;
		for( Gestion gestion : listGestion ) {
			Integer typeId = gestion.getType()!=null ? gestion.getType().getId() : -1;
			double value = gestion.getAmount2();
			switch( typeId ) {
				case 1 :
					totalObj += value;
					break;
				case 2 :
					totalTF += value;
					break;
				case 3 :
					totalTS += value;
					break;
				case 4 :
					totalRD += value;
					break;
				default :
					break;
			}
		}
		/*String summary = totalObj + Constants.SEPRATE + totalTF + Constants.SEPRATE + totalTS + Constants.SEPRATE + totalRD;*/
		String summary = append(totalObj,totalTF,totalTS,totalRD);
		return summary;
	}
	
	/**
	 * 
	 * @return
	 */
	private String createBudget(FicheSt ficheSt){	
		/*String budget = ficheSt.getGestBudgetInitial() + Constants.SEPRATE + ficheSt.getEcartDernierPoint() + Constants.SEPRATE + ficheSt.getGestDateDernierPt();*/
		String budget = append(ficheSt.getGestBudgetInitial(),ficheSt.getEcartDernierPoint(),ficheSt.getGestDateDernierPt());
		
		return budget;
	}
	/**
	 * Calucalte TotalFDC
	 * @param item
	 * @return
	 */
	private Double calculateTotalFdc(Gestion item) {
		Double avenant = item.getTraite();
		Double arrete = item.getArrete();
		Double nonArrete = item.getNonArrete();
		Double provision = item.getProvision();	
		double calculatedValue = avenant + arrete + nonArrete + provision;
		return calculatedValue;
	}
	
	/**
	 * Calculate Ecart
	 * @param item
	 * @return
	 */
	private Double calculateEcart(Gestion item) {
		if( item == null ) {
			return 0.0;
		}
		Double amount2 = item.getAmount2();
		Double totalFdc = calculateTotalFdc(item);
		return amount2 - totalFdc;
	}
	
	/**
	 * 
	 * @param listProcess
	 * @param listSrcProcess
	 * @return
	 */
	private String createProgress(List<Progress> listProcess,List<String> listSrcProcess){
		Progress previousProgress = null;
		Progress progress = null; 
		String srcProgress = "";
		String totalProcess = "";
		double mois = 0.0;
		double mois2;
		for (int j = 0; j < listProcess.size(); j++) {		
			progress = listProcess.get(j);
			if(j > 0){
				previousProgress = listProcess.get(j-1);
				mois = progress.getCumule() - previousProgress.getCumule();
				mois2 = progress.getCumule2() - previousProgress.getCumule2();
			}else{
				mois = progress.getCumule();
				mois2 = progress.getCumule2();
			}
			srcProgress += append((j+1),progress.getLabel(),progress.getDate(),progress.getCumule(),mois,progress.getCumule2(),mois2);
			totalProcess = append(progress.getCumule(),mois,progress.getCumule2(),mois2);
		}
		listSrcProcess.add(srcProgress);
		return totalProcess;
	}
	/**
	 * 
	 * @param gestionDtoList
	 * @param deductionDtoList
	 * @param penaltyDtoList
	 * @return
	 */
	public String createDetailDesRetenuesAppliquies(List<Gestion> listGestion, List<Deduction> listDeduction,
			List<Penalty> listPenalty,FicheSt ficheSt) {
		double totalAvenants = 0.0;
		for( Gestion gestion : listGestion ) {
			totalAvenants += gestion.getTraite();
		}
		int cantoQuantity = 0;
		int badgeQuantity = 0;
		int grueQuantity = 0;
		int liftQuantity = 0;
		int benneQuantity = 0;
		int nettoyageQuantity = 0;
		double totalProrata = 0.0;
		double autres = 0.0;
		for( Deduction deduction : listDeduction ) {
			cantoQuantity += deduction.getCanto();
			badgeQuantity += deduction.getBadge();
			grueQuantity += deduction.getGrue();
			liftQuantity += deduction.getLift();
			benneQuantity += deduction.getBenne();
			nettoyageQuantity += deduction.getNettoyage();
			totalProrata += deduction.getProrata();
			autres += deduction.getAutres();
		}
		double totalRefacturation = cantoQuantity * ficheSt.getPrestaCanto() + badgeQuantity * ficheSt.getPrestaBadge() + 
		grueQuantity * ficheSt.getPrestaGrue() + liftQuantity * ficheSt.getPrestaLift() + benneQuantity * ficheSt.getPrestaBenne() + nettoyageQuantity * ficheSt.getPrestaNettoyage();
		double refacturationPercentage = 0.0;
		if( totalAvenants != 0 ) {
			refacturationPercentage = totalRefacturation / totalAvenants;
		}

		double prorataPercentage = 0.0;
		if( totalAvenants != 0 ) {
			prorataPercentage = totalProrata / totalAvenants;
		}

		double totalRefacturationDontProrata = totalRefacturation + totalProrata;
		double refacturationDontProrataPercentage = 0.0;
		if( totalAvenants != 0 ) {
			refacturationDontProrataPercentage = totalRefacturationDontProrata / totalAvenants;
		}

		double refacturationsDontProrataEtAutre = totalRefacturationDontProrata + autres;
		double refacturationsDontProrataEtAutrePercentage = 0.0;
		if( totalAvenants != 0 ) {
			refacturationsDontProrataEtAutrePercentage = refacturationsDontProrataEtAutre / totalAvenants;
		}

		double totalPenalty = 0.0;
		for( Penalty penalty : listPenalty ) {
			totalPenalty += penalty.getAmount();
		}

		double penaltyPercentage = 0.0;
		if( totalAvenants != 0 ) {
			penaltyPercentage = totalPenalty / totalAvenants;
		}

		String[] column1s = { "messages.refacturations()", "messages.prorata2()", "messages.refacturationsDontProrata()", "messages.refacturationsDontProrataEtAutre()", "messages.penalites()" };
		double[] column2s = { totalRefacturation, totalProrata, totalRefacturationDontProrata, refacturationsDontProrataEtAutre, totalPenalty };
		double[] column3s = { refacturationPercentage, prorataPercentage, refacturationDontProrataPercentage, refacturationsDontProrataEtAutrePercentage, penaltyPercentage };
		//For report
		String srcDetailDesRetenuesAppliques ="";
		numberFormat = NumberFormat.getInstance(Locale.FRENCH);
		numberFormat.setMaximumFractionDigits(1);
		for( int i = 0 ; i < 5 ; i++ ) {
			//For report
			/*srcDetailDesRetenuesAppliques += column2s[i] + Constants.SEPRATE + (column3s[i] * 100) + "%" + Constants.SEPRATE;*/
			srcDetailDesRetenuesAppliques += numberFormat.format(column2s[i]) + Constants.SEPRATE + (numberFormat.format(column3s[i] * 100)) + "%" + Constants.SEPRATE;
		}
		//For Report
		if(srcDetailDesRetenuesAppliques != null && srcDetailDesRetenuesAppliques.length() > 0){
			srcDetailDesRetenuesAppliques = srcDetailDesRetenuesAppliques.substring(0,srcDetailDesRetenuesAppliques.length() - Constants.SEPRATE.length());
		}
		return srcDetailDesRetenuesAppliques;
	}
	
	/**
	 * 
	 * @param deductionDtoList
	 * @return
	 */
	private String createProgessGrird3Report(FicheSt ficheSt){
		List<Deduction> listDeduction = ficheSt.getDeductions();
		String srcProgressGrid3Report = "";
		int cantoQuantity = 0;
		int badgeQuantity = 0;
		int grueQuantity = 0;
		int liftQuantity = 0;
		int benneQuantity = 0;
		int nettoyageQuantity = 0;
		double autres = 0.0;
		for( Deduction deduction : listDeduction ) {
			cantoQuantity += deduction.getCanto();
			badgeQuantity += deduction.getBadge();
			grueQuantity += deduction.getGrue();
			liftQuantity += deduction.getLift();
			benneQuantity += deduction.getBenne();
			nettoyageQuantity += deduction.getNettoyage();
			autres += deduction.getAutres();
		}
		double[] column2s = { cantoQuantity, (cantoQuantity * ficheSt.getPrestaCanto()) };
		double[] column3s = { badgeQuantity, (badgeQuantity * ficheSt.getPrestaBadge()) };
		double[] column4s = { grueQuantity, (grueQuantity * ficheSt.getPrestaGrue()) };
		double[] column5s = { liftQuantity, (liftQuantity * ficheSt.getPrestaLift()) };
		double[] column6s = { benneQuantity, (benneQuantity * ficheSt.getPrestaBenne()) };
		double[] column7s = { nettoyageQuantity,(nettoyageQuantity * ficheSt.getPrestaNettoyage()) };
		double[] column8s = { 0, autres };
		for( int i = 0 ; i < 2 ; i++ ) {
			/*srcProgressGrid3Report +=  column2s[i] + Constants.SEPRATE + column3s[i] + Constants.SEPRATE + column4s[i] + 
			Constants.SEPRATE + column5s[i] + Constants.SEPRATE + column6s[i] + Constants.SEPRATE + column7s[i] + Constants.SEPRATE + column8s[i] + Constants.SEPRATE;*/
			srcProgressGrid3Report += append(column2s[i],column3s[i],column4s[i],column5s[i],column6s[i],column7s[i],column8s[i]);
		}
		return srcProgressGrid3Report;
	}
	/**
	 * 
	 * @param listDeduction
	 * @return
	 */
	private String [] createDeductionGrid(FicheSt ficheSt){
		List<Deduction> listDeduction = ficheSt.getDeductions();
		Deduction deduction = null;
		String srcDeduction = "";
		String srcSumDeduction = "";
		double canto = 0.0;
		double badge = 0.0;
		double grue = 0.0;
		double lift = 0.0;
		double benne = 0.0;
		double nettoyage = 0.0;
		double autres = 0.0; // khong co nhan
		double prorata = 0.0;
		double refacturations = 0.0;//khong co nhan
		
		for (int j = 0; j < listDeduction.size(); j++) {					
			deduction = listDeduction.get(j);
			/*srcDeduction += deduction.getDate() + Constants.SEPRATE + deduction.getCanto() + Constants.SEPRATE +
			deduction.getBadge() + Constants.SEPRATE + deduction.getGrue() + Constants.SEPRATE + 
			deduction.getLift() + Constants.SEPRATE + deduction.getBenne() + Constants.SEPRATE + deduction.getNettoyage() + Constants.SEPRATE +
			deduction.getAutres() + Constants.SEPRATE + deduction.getProrata() + Constants.SEPRATE + deduction.getRefacturations() + Constants.SEPRATE;*/
			srcDeduction += append(deduction.getDate(),deduction.getCanto(),deduction.getBadge(),deduction.getGrue(),deduction.getLift(),
					deduction.getBenne(),deduction.getNettoyage(),deduction.getAutres(),deduction.getProrata(),deduction.getRefacturations());
			
			canto += deduction.getCanto();
			badge += deduction.getBadge();
			grue += deduction.getGrue();
			lift += deduction.getLift();
			benne += deduction.getBenne();
			nettoyage += deduction.getNettoyage();
			autres += deduction.getAutres();
			prorata += deduction.getProrata();
			refacturations += deduction.getRefacturations();
		}
		canto = canto * ficheSt.getPrestaCanto();
		badge = badge * ficheSt.getPrestaBadge();
		grue = grue * ficheSt.getPrestaGrue();
		lift = lift * ficheSt.getPrestaLift();
		benne = benne * ficheSt.getPrestaBenne();
		nettoyage = nettoyage * ficheSt.getPrestaNettoyage();
		prorata = prorata * ficheSt.getPrestaProrata();
		/*srcSumDeduction = canto + Constants.SEPRATE + badge + Constants.SEPRATE + grue + Constants.SEPRATE + lift + Constants.SEPRATE + benne + Constants.SEPRATE + nettoyage + Constants.SEPRATE +
		autres + Constants.SEPRATE + prorata + Constants.SEPRATE + refacturations;*/
		srcSumDeduction = append(canto,badge,grue,lift,benne,nettoyage,autres,prorata,refacturations);
		String [] arr = {srcDeduction,srcSumDeduction};
		return arr;
	}
	
	/**
	 * 
	 * @param listPenalty
	 * @return
	 */
	private String [] createPenalite(List<Penalty> listPenalty){
		Penalty penalty = null;
		String srcPenalty = "";
		double amount = 0.0;
		for (int j = 0; j < listPenalty.size(); j++) {					
			penalty = listPenalty.get(j);
			/*srcPenalty += penalty.getDate() + Constants.SEPRATE + penalty.getAmount() + Constants.SEPRATE + penalty.getComment() + Constants.SEPRATE;*/
			srcPenalty += append(penalty.getDate(),penalty.getAmount(),penalty.getComment());
			amount += penalty.getAmount();
		}
		String [] arr = {srcPenalty,amount + ""};
		return arr;
	}

	public void setListFicheSt(List<FicheSt> listFicheSt) {
		this.listFicheSt = listFicheSt;
	}

	public List<FicheSt> getListFicheSt() {
		return listFicheSt;
	}
	
	private String calculateEtatAvancement(List<Gestion> listGestion,List<Progress> listProcess){
		double cumuleSum = 0.0;
		double totalTraite = 0.0;
		double etatAvancement = 0.0;
		if(listGestion != null && listGestion.size() > 0 ) {
			Gestion gestion = null;
			for( int i = 0 ; i < listGestion.size() ; i++ ) {
				gestion = listGestion.get(i);
				totalTraite += gestion.getTraite();
			}
		}
		if( listProcess != null && listProcess.size() > 0 ) {
			Progress progress = null;
			for( int i = 0 ; i < listProcess.size() ; i++ ) {
				progress = listProcess.get(i);
				cumuleSum = progress.getCumule();
			}
		}

		if( totalTraite == 0 ) {
			etatAvancement = 0.0;
		}
		else {
			etatAvancement = (cumuleSum / totalTraite) * 100;
		}
		numberFormat = NumberFormat.getInstance(Locale.FRENCH);
		numberFormat.setMaximumFractionDigits(1);
		return numberFormat.format(etatAvancement);
	}
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	private String append(Object... params){
		numberFormat = NumberFormat.getInstance(Locale.FRENCH);
		numberFormat.setMaximumFractionDigits(1);	
		dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
		Format formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		String srcParam = "";
		for(Object param : params){
			if(param != null){
				if(param instanceof String){
					srcParam += param;
				}
				if(param instanceof Double){
					srcParam += numberFormat.format(param);
				}
				if(param instanceof Float){
					srcParam += numberFormat.format(param);
				}
				if(param instanceof Date){
					srcParam += formatter.format(param);
				}
			} else{
				srcParam += "null";
			}
			srcParam += Constants.SEPRATE;
		}
		return srcParam;
	}

	public void setHasGestion(String hasGestion) {
		this.hasGestion = hasGestion;
	}

	public String getHasGestion() {
		return hasGestion;
	}

	public void setInternalCommentaire(String internalCommentaire) {
		this.internalCommentaire = internalCommentaire;
	}

	public String getInternalCommentaire() {
		return internalCommentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getCommentaire() {
		return commentaire;
	}
}
