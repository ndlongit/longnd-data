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


public class ExportPdfProcessDto extends ExportPdfManager {
	
	private static final int COL = 7;
	
	private String generaleInformation;
	private String processes;
	private String totalsituation;
	private String grid2;
	private String grid3;
	private String commentaire;
	private String etatAvancement;
	
	private ResourceBundleMessageSource messageSource;
	
	public ExportPdfProcessDto(OutputStream stream,ResourceBundleMessageSource messageSource,Locale locale)
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
		addProcessDto();
	}
	
	private void addProcessDto() throws DocumentException {		
		//Title
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		
		PdfPCell headerCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.suividesavancementssusoustraitant", null, localtion), contentBoldFont));
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
		
		headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		headerTable.setSpacingAfter(15f);
		headerCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.suvidesavancements", null, localtion), contentBoldFont));
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
		
		headerCell = new PdfPCell(new Phrase(etatAvancement + Constants.SPACE + messageSource.getMessage("pdf.process.titlebox.percentavancementmarche", null, localtion), contentNormalFont));
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

		//Add Group Header
		PdfPCell[] groupHeaderCell = new PdfPCell[3];
		groupHeaderCell[0] = new PdfPCell();
		groupHeaderCell[0].setBorder(Rectangle.NO_BORDER);
		
		groupHeaderCell[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.advancement", null, localtion), contentBoldFont));
		groupHeaderCell[1].setBackgroundColor(new Color(181,181,181));

		groupHeaderCell[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.process.titlebox.retenues", null, localtion), contentBoldFont));
		groupHeaderCell[2].setBackgroundColor(new Color(181,181,181));
		
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
		
		cellRight = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.comment2", null, localtion) + " : ", contentBoldFont));
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
			pdfPCell.setBackgroundColor(new Color(181,181,181));
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		//End
		PdfPTable processDtoTable = new PdfPTable(COL);
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
		processDtoTable = new PdfPTable(COL);
		if(processes!=null && processes.length() > 0){
			String arr[] = processes.split(Constants.SEPRATE);
			for(int i=0;i < arr.length;i++){
				tmp = arr[i]; 
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp.equals("null") ? "" : tmp, contentNormalFont)));
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
	
	private PdfPTable addtotalsituation(String totalsituation){
		PdfPTable processDtoTable = new PdfPTable(5);
		processDtoTable.addCell(new PdfPCell(new Phrase(messageSource.getMessage("FicheST.totalSituation",null, localtion), contentBoldFont)));
		boolean isEmpty = true;
		if(totalsituation!=null && totalsituation.length() > 0){
			String[] tmp = totalsituation.split(Constants.SEPRATE);
			if(tmp != null && tmp.length > 3){
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp[0], contentNormalFont)));
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp[1], contentNormalFont)));
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp[2], contentNormalFont)));
				processDtoTable.addCell(new PdfPCell(new Phrase(tmp[3], contentNormalFont)));
				isEmpty = false;
			}
		}
		if(isEmpty){
			processDtoTable.addCell(new PdfPCell(new Phrase("0", contentNormalFont)));
			processDtoTable.addCell(new PdfPCell(new Phrase("0", contentNormalFont)));
			processDtoTable.addCell(new PdfPCell(new Phrase("0", contentNormalFont)));
			processDtoTable.addCell(new PdfPCell(new Phrase("0", contentNormalFont)));
		}

		float[] wf = {
				25,
				10, 
				10, 
				10,
				10
			};
		try {
			processDtoTable.setWidths(wf);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return processDtoTable;
	}
	
	private PdfPTable addGrid2(String grid2) throws DocumentException{
		//create table header
		PdfPCell[] grid2TableHeader = new PdfPCell[3];
		
		grid2TableHeader[0] = new PdfPCell(new Phrase("", contentBoldFont));

		grid2TableHeader[1] = new PdfPCell(new Phrase("Montant", contentBoldFont));
		grid2TableHeader[1].setBackgroundColor(new Color(181,181,181));

		grid2TableHeader[2] = new PdfPCell(new Phrase("Pourcentage du régularisé", contentBoldFont));
		grid2TableHeader[2].setBackgroundColor(new Color(181,181,181));
		
		for (PdfPCell pdfPCell : grid2TableHeader) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		
		PdfPTable grid2Table = new PdfPTable(3);
		PdfPCell grid2Cell;
		
		addCellsToTable(grid2Table, grid2TableHeader);
		String[] tmp = grid2.split(Constants.SEPRATE);
		String[] messages = {messageSource.getMessage("FicheST.Refacturations", null, localtion) ,messageSource.getMessage("FicheST.Prorata", null, localtion),
				messageSource.getMessage("FicheST.RefacturationsDontProrata", null, localtion),messageSource.getMessage("FicheST.RefacturationsDontProrataEtAutre", null, localtion), 
				messageSource.getMessage("pdf.process.titlebox.penalites", null, localtion)};
		
		for(int i=0; i < tmp.length ; i++){
			switch (i) {
			case 0:
				grid2Cell = new PdfPCell(new Phrase(messages[0], contentBoldFont));
				grid2Cell.setBackgroundColor(new Color(181,181,181));
				grid2Table.addCell(grid2Cell);
				break;
			case 2:
				grid2Cell = new PdfPCell(new Phrase(messages[1], contentBoldFont));
				grid2Cell.setBackgroundColor(new Color(181,181,181));
				grid2Table.addCell(grid2Cell);
				break;
			case 4:
				grid2Cell = new PdfPCell(new Phrase(messages[2], contentBoldFont));
				grid2Cell.setBackgroundColor(new Color(181,181,181));
				grid2Table.addCell(grid2Cell);
				break;
			case 6:
				grid2Cell = new PdfPCell(new Phrase(messages[3], contentBoldFont));
				grid2Cell.setBackgroundColor(new Color(181,181,181));
				grid2Table.addCell(grid2Cell);
				break;
			case 8:
				grid2Cell = new PdfPCell(new Phrase(messages[4], contentBoldFont));
				grid2Cell.setBackgroundColor(new Color(181,181,181));
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
		grid2Table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		grid2Table.setWidths(wf);
		return grid2Table;
	}
	
	private PdfPTable addGrid3(String grid3) throws DocumentException{
		//create table header
		PdfPCell[] grid3TableHeader = new PdfPCell[8];
		   
		grid3TableHeader[0] = new PdfPCell(new Phrase("", contentBoldFont));

		grid3TableHeader[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.canto",null, localtion), contentBoldFont));
		grid3TableHeader[1].setBackgroundColor(new Color(181,181,181));
		
		grid3TableHeader[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.badge",null, localtion), contentBoldFont));
		grid3TableHeader[2].setBackgroundColor(new Color(181,181,181));

		grid3TableHeader[3] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.grue",null, localtion), contentBoldFont));
		grid3TableHeader[3].setBackgroundColor(new Color(181,181,181));
		
		grid3TableHeader[4] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.lift",null, localtion), contentBoldFont));
		grid3TableHeader[4].setBackgroundColor(new Color(181,181,181));
		
		grid3TableHeader[5] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.benne",null, localtion), contentBoldFont));
		grid3TableHeader[5].setBackgroundColor(new Color(181,181,181));
		
		grid3TableHeader[6] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.allfichest.netoyage",null, localtion), contentBoldFont));
		grid3TableHeader[6].setBackgroundColor(new Color(181,181,181));
		
		grid3TableHeader[7] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.autres",null, localtion), contentBoldFont));
		grid3TableHeader[7].setBackgroundColor(new Color(181,181,181));
		
		
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
				grid3Cell.setBackgroundColor(new Color(181,181,181));
				grid3Table.addCell(grid3Cell);
				break;
			case 7:
				grid3Cell = new PdfPCell(new Phrase(messages[1], contentBoldFont));
				grid3Cell.setBackgroundColor(new Color(181,181,181));
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
		grid3Table.setWidths(wf);
		grid3Table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		return grid3Table;
	}
	
	private PdfPTable addProcessCommantaire() throws DocumentException{
		//create table header
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
	
	public void setGeneraleInformation(String generaleInformation) {
		this.generaleInformation = generaleInformation;
	}

	public String getGeneraleInformation() {
		return generaleInformation;
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

	public void setEtatAvancement(String etatAvancement) {
		this.etatAvancement = etatAvancement;
	}

	public String getEtatAvancement() {
		return etatAvancement;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getCommentaire() {
		return commentaire;
	}
}
