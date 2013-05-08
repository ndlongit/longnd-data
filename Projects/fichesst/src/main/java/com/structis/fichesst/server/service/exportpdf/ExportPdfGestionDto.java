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


public class ExportPdfGestionDto extends ExportPdfManager {
	
	private String generaleInformation;
	private List<String> gestions;
	private List<String> totalgestions;
	private String alltotalgestion;
	private String summary;
	private String budget;
	
	private ResourceBundleMessageSource messageSource;
	
	public ExportPdfGestionDto(OutputStream stream,ResourceBundleMessageSource messageSource,Locale locale)
			throws DocumentException, IOException {
		super(stream, locale);
		this.messageSource = messageSource;
		//landscape
		document.setPageSize(PageSize.A4.rotate());
		//document.setMargins(15, 15, 130, 80);
		//document.setMargins(10,10,90, 72);
		this.messageSource = messageSource;
		contentBoldGrayFont.setSize(12);
		contentItalicFont.setSize(12);
		titleL1BoldFont.setSize(13);
		titleL2BoldFont.setSize(12);
		contentBoldFont.setSize(9);
		contentNormalFont.setSize(7f);
		contentNormalGrayFont.setSize(8.5f);
	}
	
	@Override
	public void render() throws DocumentException {
		addGestionDto();
	}
	
	protected void addGestionDto() throws DocumentException {
		
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		
		PdfPCell headerCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.gestiondto.suividegestiondusoustraitant", null, localtion), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		headerTable.addCell(headerCell);
		addContent(headerTable);
		lineBreak();
		
		headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		headerCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.generalInformation", null, localtion), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		headerTable.addCell(headerCell);
		addContent(headerTable);
		
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
		//Set space
		generaleInformationTable.setSpacingAfter(15f);
		addContent(generaleInformationTable);
		
		//Header
		headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		headerCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.gestion", null,localtion), contentBoldFont));
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
					tmp = arr[i];// == null ? "" : arr[i]; 
					gestionDtoTable.addCell(new PdfPCell(new Phrase(tmp.equals("null") ? "" : tmp , contentNormalFont)));
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
		sumarryTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
		sumarryTable.setSpacingAfter(3f);
		addContent(sumarryTable);
		
		PdfPTable budgetTable = addBudget(budget);
		budgetTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
		addContent(budgetTable);
	}
	
	private PdfPTable addtotalSum(String totalgestions) throws DocumentException {
		PdfPTable gestionDtoTable = new PdfPTable(11);
		PdfPCell gestionDtoCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.gestiondto.soustotaldumarche",null, localtion), contentBoldFont));
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
		}

		return gestionDtoTable;
	}
	
	private PdfPTable addalltotalSum(String alltotalgestions) throws DocumentException {
		PdfPTable gestionDtoTable = new PdfPTable(11);
		
		PdfPCell gestionDtoCell = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.rexAmount", null, localtion), contentBoldFont));
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
	
	private PdfPTable addSummary(String sumarry) throws DocumentException{
		
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
		gestionDtoTable.setWidthPercentage(100f);
		gestionDtoTable.setWidths(wf);
		return gestionDtoTable;
	}
	
	private PdfPTable addBudget(String budget) throws DocumentException{
		PdfPTable budgetTable = new PdfPTable(2);
		PdfPCell budgetCell;
		String[] tmp = budget.split(Constants.SEPRATE);
		float[] wf = {
				12,
				17,
			};
		budgetTable.setWidths(wf);
	
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
		gestionDtoTable.setWidthPercentage(100f);
		gestionDtoTable.setWidths(wf1);
		return gestionDtoTable;
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

	public void setGeneraleInformation(String generaleInformation) {
		this.generaleInformation = generaleInformation;
	}

	public String getGeneraleInformation() {
		return generaleInformation;
	}
}
