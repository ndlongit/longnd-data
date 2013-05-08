package com.structis.fichesst.server.service.exportpdf;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.structis.fichesst.shared.util.Constants;


public class ExportPdfAccomptes extends ExportPdfManager {
	
	private String generaleInformation;
	private String deductions;
	private String prestation;
	private String totaldeduction;
	private String penaltys;
	private String amount;
	private String information;
	private String commentaire;
	private String internalCommentaire;
	private ResourceBundleMessageSource messageSource;
	public ExportPdfAccomptes(OutputStream stream,ResourceBundleMessageSource messageSource,Locale locale)
			throws DocumentException, IOException {
		super(stream, locale);
		this.messageSource = messageSource;
		//landscape
		document.setPageSize(PageSize.A4.rotate());
		//document.setMargins(15, 15, 130, 80);
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
		addDeductionDto();
	}
	
	private void addDeductionDto() throws DocumentException {
		//Title
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		
		PdfPCell headerCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.suivideavancementsdusoustraitant",null ,localtion), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		headerTable.addCell(headerCell);
		
		addContent(headerTable);
		
		//Information Generals
		headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		headerCell = new PdfPCell(new Phrase("INFORMATIONS GENERALS", contentBoldFont));
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
		
		headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		headerTable.setSpacingAfter(15f);
		headerCell = new PdfPCell(new Phrase("SUVI DES ACOMPTES", contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		headerTable.setSpacingAfter(0f);
		addContent(headerTable);
		
		PdfPTable informationTable = addInformationTable();
		addContent(informationTable);
		lineBreak();
		
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
				70,
				5, 
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
	
	/**
	 * 
	 * @return
	 * @throws DocumentException
	 */
	private PdfPTable addInformationTable() throws DocumentException {
		
		PdfPTable informationTable = new PdfPTable(6);
		PdfPCell informationCell = new PdfPCell();
		
		//Add Content
		String[] tmp = information.split(Constants.SEPRATE);
		String [] messages = {messageSource.getMessage("FicheST.chantier", null,localtion) + " : ",
				messageSource.getMessage("FicheST.Responsable", null, localtion) + " : ",messageSource.getMessage("pdf.synthese.societe", null, localtion) + " : "};
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
				3, 
				10, 
				5, 
				10,
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
		groupTitleCell[0] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.retenuesEffectuees",null,localtion),contentBoldFont));	
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
		
		groupHeaderCell[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.accomptes.quante", null,localtion), contentBoldFont));
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
		PdfPCell[] deductionTableHeader = new PdfPCell[10];
		
		String[] arrPrestations = prestation.split(Constants.SEPRATE);
		
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
	
	private PdfPTable addDeductionCommantaire() throws DocumentException {
		//create table header
		PdfPCell[] commantaireTableHeader = new PdfPCell[1];
		commantaireTableHeader[0] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.comment2", null, localtion) + " : ", contentBoldFont));
		for (PdfPCell pdfPCell : commantaireTableHeader) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
	
		PdfPTable commantaireTable = new PdfPTable(1);
		commentaire = commentaire.equals("null") ? "" : commentaire; 
		internalCommentaire = internalCommentaire.equals("null") ? "" : internalCommentaire; 
		PdfPCell pdfPCell = new PdfPCell(new Phrase(commentaire + "\n" + internalCommentaire, contentNormalFont));
		pdfPCell.setBorder(Rectangle.NO_BORDER);
		commantaireTable.addCell(pdfPCell);	
		float[] wf = {
				15
			};
		commantaireTable.setWidths(wf);
		commantaireTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		return commantaireTable;
	}

	public void setGeneraleInformation(String generaleInformation) {
		this.generaleInformation = generaleInformation;
	}

	public String getGeneraleInformation() {
		return generaleInformation;
	}

	public void setDeductions(String deductions) {
		this.deductions = deductions;
	}

	public String getDeductions() {
		return deductions;
	}

	public void setTotaldeduction(String totaldeduction) {
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

	public void setInternalCommentaire(String internalCommentaire) {
		this.internalCommentaire = internalCommentaire;
	}

	public String getInternalCommentaire() {
		return internalCommentaire;
	}

	public void setPrestation(String prestation) {
		this.prestation = prestation;
	}

	public String getPrestation() {
		return prestation;
	}
}
