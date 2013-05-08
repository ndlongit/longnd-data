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


public class ExportPdfSynthese extends ExportPdfManager {
	
	private String informationChantier;
	private String informationProrata;
	private List<String> grid1;
	private List<String> totalRowGrid1;
	private String sumTotalGrid1;
	private List<String> grid2;
	private List<String> totalRowGrid2;
	private String sumTotalGrid2;
	private String grid3;
	private String sumGrid3;
	private String grid4;
	private String sumGrid4;
	
	private ResourceBundleMessageSource messageSource;
	
	public ExportPdfSynthese(OutputStream stream,ResourceBundleMessageSource messageSource,Locale locale)
			throws DocumentException, IOException {
		super(stream, locale);
		this.messageSource = messageSource;
		//landscape
		document.setPageSize(PageSize.A4.rotate());
		//document.setMargins(15, 15, 130, 80);
		//document.setMargins(10,10,90, 72);
		this.messageSource = messageSource;
		contentBoldGrayFont.setSize(5);
		contentItalicFont.setSize(5);
		titleL1BoldFont.setSize(13);
		titleL2BoldFont.setSize(12);
		contentBoldFont.setSize(2.5f);
		contentNormalFont.setSize(2.5f);
		contentNormalGrayFont.setSize(8.5f);
	}
	
	@Override
	public void render() throws DocumentException {			
		//INFORMATION CHANTIER
		addInformationChantier();
		
		//SYNTHESE DES FICHES ST
		addSyntheseDesFichesST1();	
		addSyntheseDesFichesST2();
		
		lineBreak();
		
		addGrid3();
		addSumGrid3();
		
		lineBreak();
		
		addGrid4();
		addSumGrid4();	
	}
	
	/**
	 * Add Grid 1
	 * @throws DocumentException
	 */
	private void addInformationChantier() throws DocumentException {
		
		//Title
		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setWidthPercentage(100f);
		PdfPCell titleCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.title", null, localtion), contentBoldFont));
		titleCell.setBorder(Rectangle.NO_BORDER);
		titleCell.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		titleTable.addCell(titleCell);
		addContent(titleTable);
		PdfPTable informationChantierTable = new PdfPTable(1);
		informationChantierTable.setWidthPercentage(100f);
		PdfPCell informationChantierCell;
		informationChantierTable.setWidthPercentage(100f);
		informationChantierCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.nomduchantier", null, localtion) + Constants.SPACE + informationChantier, contentBoldFont));
		informationChantierCell.setColspan(1);
		informationChantierCell.setBorder(Rectangle.NO_BORDER);
		informationChantierTable.addCell(informationChantierCell);;
		addContent(informationChantierTable);
	}
	
	private void addInformationProrata() throws DocumentException{
		//Title
		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setWidthPercentage(100f);
		PdfPCell titleCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.synthesedesfichesst", null, localtion), contentBoldFont));
		titleCell.setBorder(Rectangle.NO_BORDER);
		titleCell.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		titleTable.addCell(titleCell);
		addContent(titleTable);
		
		PdfPTable informationChantierTable = new PdfPTable(1);
		PdfPCell informationChantierCell;
		informationChantierTable.setWidthPercentage(100f);
		informationChantierCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.proratatheorique", null, localtion) + Constants.SPACE + informationProrata, contentBoldFont));
		informationChantierCell.setBorder(Rectangle.NO_BORDER);
		informationChantierTable.addCell(informationChantierCell);
		addContent(informationChantierTable);
	}
	
	private void addSyntheseDesFichesST1() throws DocumentException {
		addInformationProrata();
		float[] wf = {
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,
				2.5f
			};
		
		//Header
		PdfPCell[] headerCell = new PdfPCell[41];
		headerCell[0] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.lot", null, localtion), contentBoldFont));
		headerCell[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.objecif", null, localtion), contentBoldFont));
		headerCell[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.societe", null, localtion), contentBoldFont));
		headerCell[3] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.obj", null, localtion), contentBoldFont));
		headerCell[4] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.+-", null, localtion), contentBoldFont));
		headerCell[5] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.rd", null, localtion), contentBoldFont));
		headerCell[6] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ts", null, localtion), contentBoldFont));
		headerCell[7] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.total", null, localtion), contentBoldFont));
		headerCell[8] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.traite", null, localtion), contentBoldFont));
		headerCell[9] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.arrete", null, localtion), contentBoldFont));
		headerCell[10] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.atraiter", null, localtion), contentBoldFont));
		headerCell[11] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.provision", null, localtion), contentBoldFont));
		headerCell[12] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.totalfinal", null, localtion), contentBoldFont));		
		headerCell[13] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.devisrefuse", null, localtion), contentBoldFont));		
		headerCell[14] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartm", null, localtion), contentBoldFont));
		headerCell[15] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartm-1", null, localtion), contentBoldFont));
		headerCell[16] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.variation", null, localtion), contentBoldFont));
		headerCell[17] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartdernierpoint", null, localtion), contentBoldFont));
		headerCell[18] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartdernierpoint/m", null, localtion), contentBoldFont));		
		headerCell[19] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.%avctba", null, localtion), contentBoldFont));
		headerCell[20] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.montnatba", null, localtion), contentBoldFont));		
		headerCell[21] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.%avctreel", null, localtion), contentBoldFont));
		headerCell[22] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.montantreel", null, localtion), contentBoldFont));
		headerCell[23] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.canto", null, localtion), contentBoldFont));
		headerCell[24] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.badge", null, localtion), contentBoldFont));
		headerCell[25] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.grue", null, localtion), contentBoldFont));
		headerCell[26] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.lift", null, localtion), contentBoldFont));
		headerCell[27] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.benne", null, localtion), contentBoldFont));
		headerCell[28] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.nettoy", null, localtion), contentBoldFont));
		headerCell[29] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.autres", null, localtion), contentBoldFont));
		headerCell[30] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.prorata", null, localtion), contentBoldFont));
		headerCell[31] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.total", null, localtion), contentBoldFont));
		headerCell[32] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.prestation/traite", null, localtion), contentBoldFont));
		headerCell[33] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.penalitefacture", null, localtion), contentBoldFont));
		headerCell[34] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.prorataappliqusest", null, localtion), contentBoldFont));
		headerCell[35] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.proratamarche", null, localtion), contentBoldFont));
		headerCell[36] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.proratasurrad", null, localtion), contentBoldFont));
		headerCell[37] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.marchesrestantatraiter%", null, localtion), contentBoldFont));
		headerCell[38] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.marchesrestantatraiter", null, localtion), contentBoldFont));
		headerCell[39] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.variationsuauxtransferts", null, localtion), contentBoldFont));
		headerCell[40] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.manqueagagnerstsansprorata", null, localtion), contentBoldFont));
		for (PdfPCell pdfPCell : headerCell) {
			pdfPCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		PdfPTable headerTable = new PdfPTable(41);
		headerTable.setWidthPercentage(100f);
		headerTable.setWidths(wf);
		addCellsToTable(headerTable,headerCell);
		addContent(headerTable);
		
		//Content
		PdfPTable grid1Table = new PdfPTable(41);
		String tmp;
		for (int j = 0 ; j < grid1.size();j++) {
			grid1Table = new PdfPTable(41);
			String param = grid1.get(j);
			if(param != null && param.length() > 0){
				String arr[] = param.split(Constants.SEPRATE);
				for(int i=0;i < arr.length;i++){
					tmp = arr[i];
					grid1Table.addCell(new PdfPCell(new Phrase(tmp.equals("null") ? "" : tmp , contentNormalFont)));
				}
			}
			grid1Table.setWidthPercentage(100f);
			grid1Table.setWidths(wf);
			grid1Table.setSpacingAfter(3f);
			addContent(grid1Table);
			//Sum
			addSumGroupGrid1(totalRowGrid1.get(j),wf);			
		}
		addSumGrid1();
	}
	
	private void addSumGroupGrid1(String sumGroup,float[] wf) throws DocumentException{
		PdfPTable table = new PdfPTable(41);
		PdfPCell cell ;//= new PdfPCell(new Phrase("Sous - Total du Marché /", contentBoldFont));
		if(sumGroup != null && sumGroup.length() > 0){
			String[] tmp = sumGroup.split(Constants.SEPRATE);
			for(int i=0;i<tmp.length;i++){
				cell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : (tmp[i] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion)), contentNormalFont));
				cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
				table.addCell(cell);
			}
			
		}
		table.setWidthPercentage(100f);
		table.setWidths(wf);
		table.setSpacingAfter(3f);
		addContent(table);
	}
	
	private void addSumGrid1() throws DocumentException{
		float[] wf = {
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,
				2.5f
			};
		PdfPTable table = new PdfPTable(41);
		table.setWidthPercentage(100f);
		PdfPCell cell;
		if(sumTotalGrid1 != null && sumTotalGrid1.length() > 0){
			String[] tmp = sumTotalGrid1.split(Constants.SEPRATE);
			for(int i=0;i<tmp.length;i++){
				cell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : (tmp[i] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion)), contentNormalFont));
				cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
				table.addCell(cell);
			}
		}
		table.setWidths(wf);
		table.setWidthPercentage(100f);
		addContent(table);
	}
	
	private void addSyntheseDesFichesST2() throws DocumentException {
		
		float[] wf = {
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,
				2.5f
			};
		
		//Header
		PdfPCell[] headerCell = new PdfPCell[41];
		headerCell[0] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.lot", null, localtion), contentBoldFont));
		headerCell[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.objecif", null, localtion), contentBoldFont));
		headerCell[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.societe", null, localtion), contentBoldFont));
		headerCell[3] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.obj", null, localtion), contentBoldFont));
		headerCell[4] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.+-", null, localtion), contentBoldFont));
		headerCell[5] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.rd", null, localtion), contentBoldFont));
		headerCell[6] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ts", null, localtion), contentBoldFont));
		headerCell[7] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.total", null, localtion), contentBoldFont));
		headerCell[8] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.traite", null, localtion), contentBoldFont));
		headerCell[9] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.arrete", null, localtion), contentBoldFont));
		headerCell[10] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.atraiter", null, localtion), contentBoldFont));
		headerCell[11] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.provision", null, localtion), contentBoldFont));
		headerCell[12] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.totalfinal", null, localtion), contentBoldFont));		
		headerCell[13] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.devisrefuse", null, localtion), contentBoldFont));		
		headerCell[14] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartm", null, localtion), contentBoldFont));
		headerCell[15] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartm-1", null, localtion), contentBoldFont));
		headerCell[16] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.variation", null, localtion), contentBoldFont));
		headerCell[17] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartdernierpoint", null, localtion), contentBoldFont));
		headerCell[18] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartdernierpoint/m", null, localtion), contentBoldFont));		
		headerCell[19] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.%avctba", null, localtion), contentBoldFont));
		headerCell[20] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.montnatba", null, localtion), contentBoldFont));		
		headerCell[21] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.%avctreel", null, localtion), contentBoldFont));
		headerCell[22] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.montantreel", null, localtion), contentBoldFont));
		headerCell[23] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.canto", null, localtion), contentBoldFont));
		headerCell[24] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.badge", null, localtion), contentBoldFont));
		headerCell[25] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.grue", null, localtion), contentBoldFont));
		headerCell[26] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.lift", null, localtion), contentBoldFont));
		headerCell[27] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.benne", null, localtion), contentBoldFont));
		headerCell[28] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.nettoy", null, localtion), contentBoldFont));
		headerCell[29] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.autres", null, localtion), contentBoldFont));
		headerCell[30] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.prorata", null, localtion), contentBoldFont));
		headerCell[31] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.total", null, localtion), contentBoldFont));
		headerCell[32] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.prestation/traite", null, localtion), contentBoldFont));
		headerCell[33] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.penalitefacture", null, localtion), contentBoldFont));
		headerCell[34] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.prorataappliqusest", null, localtion), contentBoldFont));
		headerCell[35] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.proratamarche", null, localtion), contentBoldFont));
		headerCell[36] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.proratasurrad", null, localtion), contentBoldFont));
		headerCell[37] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.marchesrestantatraiter%", null, localtion), contentBoldFont));
		headerCell[38] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.marchesrestantatraiter", null, localtion), contentBoldFont));
		headerCell[39] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.variationsuauxtransferts", null, localtion), contentBoldFont));
		headerCell[40] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.manqueagagnerstsansprorata", null, localtion), contentBoldFont));
		for (PdfPCell pdfPCell : headerCell) {
			pdfPCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		PdfPTable headerTable = new PdfPTable(41);
		headerTable.setWidthPercentage(100f);
		headerTable.setWidths(wf);
		addCellsToTable(headerTable,headerCell);
		addContent(headerTable);
		
		//Content
		PdfPTable grid2Table = new PdfPTable(41);
		String tmp;
		for (int j = 0 ; j < grid2.size();j++) {
			grid2Table = new PdfPTable(41);
			String param = grid2.get(j);
			if(param != null && param.length() > 0){
				String arr[] = param.split(Constants.SEPRATE);
				for(int i=0;i < arr.length;i++){
					tmp = arr[i];
					grid2Table.addCell(new PdfPCell(new Phrase(tmp.equals("null") ? "" : tmp , contentNormalFont)));
				}
			}
			grid2Table.setWidthPercentage(100f);
			grid2Table.setWidths(wf);
			grid2Table.setSpacingAfter(3f);
			addContent(grid2Table);
			//Sum
			addSumGroupGrid2(totalRowGrid2.get(j),wf);			
		}
		addSumGrid2();
	}
	
	private void addSumGroupGrid2(String sumGroup,float[] wf) throws DocumentException{
		PdfPTable table = new PdfPTable(41);
		PdfPCell cell;
		if(sumGroup != null && sumGroup.length() > 0){
			String[] tmp = sumGroup.split(Constants.SEPRATE);
			for(int i=0 ;i<tmp.length;i++){
				cell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : (tmp[i] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion)), contentNormalFont));
				cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
				table.addCell(cell);
			}
		}
		table.setWidths(wf);
		table.setWidthPercentage(100f);
		table.setSpacingAfter(3f);
		addContent(table);
	}
	
	private void addSumGrid2() throws DocumentException{
		float[] wf = {
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,
				2.5f
			};
		PdfPTable table = new PdfPTable(41);
		table.setWidthPercentage(100f);
		PdfPCell cell = new PdfPCell(new Phrase("HONORAIRES", contentNormalFont));
		table.addCell(cell);
		if(sumTotalGrid2 != null && sumTotalGrid2.length() > 0){
			String[] tmp = sumTotalGrid2.split(Constants.SEPRATE);
			for(int i=1;i<tmp.length;i++){
				cell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : (tmp[i] + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion)), contentNormalFont));
				cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
				table.addCell(cell);
			}
		}
		table.setWidths(wf);
		table.setSpacingAfter(3f);
		addContent(table);
	}
	
	private void addGrid3() throws DocumentException{	
		//Title
		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setWidthPercentage(100f);
		PdfPCell titleCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.synthesedestranfpertpp", null, localtion), contentBoldFont));
		titleCell.setBorder(Rectangle.NO_BORDER);
		titleCell.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		titleTable.addCell(titleCell);
		addContent(titleTable);
		//Header
		PdfPTable headerTable = new PdfPTable(7);
		headerTable.setWidthPercentage(100f);
		PdfPCell[] headerCell = new PdfPCell[7];
		headerCell[0] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.libelle", null, localtion), contentBoldFont));
		headerCell[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.objecif", null, localtion), contentBoldFont));
		headerCell[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.obj", null, localtion), contentBoldFont));
		headerCell[3] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.+-", null, localtion), contentBoldFont));
		headerCell[4] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.rd", null, localtion), contentBoldFont));
		headerCell[5] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ts", null, localtion), contentBoldFont));
		headerCell[6] = new PdfPCell(new Phrase("Total", contentBoldFont));
		for (PdfPCell pdfPCell : headerCell) {
			pdfPCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		addCellsToTable(headerTable, headerCell);
		addContent(headerTable);
		
		//Content
		PdfPTable grid3Table = new PdfPTable(7);
		grid3Table.setWidthPercentage(100f);
		String[] arrGrid3 = grid3.split(Constants.SEPRATE);
		String tmp;
		for (int i=0;i<arrGrid3.length;i++) {
			tmp = arrGrid3[i];
			grid3Table.addCell(new PdfPCell(new Phrase(tmp.equals("null") ? "" : tmp , contentNormalFont)));
			grid3Table.setWidthPercentage(100f);		
		}
		addContent(grid3Table);		
	}
	
	private void addSumGrid3() throws DocumentException{
		//Content
		PdfPTable sumGrid3Table = new PdfPTable(7);
		String[] arrSumGrid3 = sumGrid3.split(Constants.SEPRATE);
		String tmp;
		for (int i=0;i<arrSumGrid3.length;i++) {
			tmp = arrSumGrid3[i];
			sumGrid3Table.addCell(new PdfPCell(new Phrase((tmp.equals("null") ? "" : tmp) + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion), contentNormalFont)));	
		}
		sumGrid3Table.setWidthPercentage(100f);
		addContent(sumGrid3Table);	
	}
	
	private void addGrid4() throws DocumentException{
		//Title
		PdfPTable titleTable = new PdfPTable(1);
		titleTable.setWidthPercentage(100f); 
		PdfPCell titleCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.totalchantier", null, localtion), contentBoldFont));
		titleCell.setBorder(Rectangle.NO_BORDER);
		titleCell.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		titleTable.addCell(titleCell);
		addContent(titleTable);
		float[] wf = {
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,	
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,
				2.5f,	
				2.5f,	
				2.5f,	  
				2.5f,	
				2.5f,	
				2.5f,
				2.5f
			};
		
		//Header
		PdfPCell[] headerCell = new PdfPCell[41];
		headerCell[0] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.lot", null, localtion), contentBoldFont));
		headerCell[1] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.objecif", null, localtion), contentBoldFont));
		headerCell[2] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.societe", null, localtion), contentBoldFont));
		headerCell[3] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.obj", null, localtion), contentBoldFont));
		headerCell[4] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.+-", null, localtion), contentBoldFont));
		headerCell[5] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.rd", null, localtion), contentBoldFont));
		headerCell[6] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ts", null, localtion), contentBoldFont));
		headerCell[7] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.total", null, localtion), contentBoldFont));
		headerCell[8] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.traite", null, localtion), contentBoldFont));
		headerCell[9] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.arrete", null, localtion), contentBoldFont));
		headerCell[10] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.atraiter", null, localtion), contentBoldFont));
		headerCell[11] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.provision", null, localtion), contentBoldFont));
		headerCell[12] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.totalfinal", null, localtion), contentBoldFont));		
		headerCell[13] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.devisrefuse", null, localtion), contentBoldFont));		
		headerCell[14] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartm", null, localtion), contentBoldFont));
		headerCell[15] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartm-1", null, localtion), contentBoldFont));
		headerCell[16] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.variation", null, localtion), contentBoldFont));
		headerCell[17] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartdernierpoint", null, localtion), contentBoldFont));
		headerCell[18] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.ecartdernierpoint/m", null, localtion), contentBoldFont));		
		headerCell[19] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.%avctba", null, localtion), contentBoldFont));
		headerCell[20] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.montnatba", null, localtion), contentBoldFont));		
		headerCell[21] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.%avctreel", null, localtion), contentBoldFont));
		headerCell[22] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.montantreel", null, localtion), contentBoldFont));
		headerCell[23] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.canto", null, localtion), contentBoldFont));
		headerCell[24] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.badge", null, localtion), contentBoldFont));
		headerCell[25] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.grue", null, localtion), contentBoldFont));
		headerCell[26] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.lift", null, localtion), contentBoldFont));
		headerCell[27] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.benne", null, localtion), contentBoldFont));
		headerCell[28] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.nettoy", null, localtion), contentBoldFont));
		headerCell[29] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.autres", null, localtion), contentBoldFont));
		headerCell[30] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.prorata", null, localtion), contentBoldFont));
		headerCell[31] = new PdfPCell(new Phrase(messageSource.getMessage("FicheST.total", null, localtion), contentBoldFont));
		headerCell[32] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.prestation/traite", null, localtion), contentBoldFont));
		headerCell[33] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.penalitefacture", null, localtion), contentBoldFont));
		headerCell[34] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.prorataappliqusest", null, localtion), contentBoldFont));
		headerCell[35] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.proratamarche", null, localtion), contentBoldFont));
		headerCell[36] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.proratasurrad", null, localtion), contentBoldFont));
		headerCell[37] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.marchesrestantatraiter%", null, localtion), contentBoldFont));
		headerCell[38] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.marchesrestantatraiter", null, localtion), contentBoldFont));
		headerCell[39] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.variationsuauxtransferts", null, localtion), contentBoldFont));
		headerCell[40] = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.manqueagagnerstsansprorata", null, localtion), contentBoldFont));
		for (PdfPCell pdfPCell : headerCell) {
			pdfPCell.setBackgroundColor(new Color(Constants.RED,Constants.GREEN,Constants.BLUE));
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		PdfPTable headerTable = new PdfPTable(41);
		headerTable.setWidthPercentage(100f);
		headerTable.setWidths(wf);
		addCellsToTable(headerTable,headerCell);
		addContent(headerTable);
		
		//Content
		PdfPTable grid4Table = new PdfPTable(41);
		String[] arrGrid4 = grid4.split(Constants.SEPRATE);
		String tmp;
		for (int i=0;i<arrGrid4.length;i++) {
			tmp = arrGrid4[i];
			grid4Table.addCell(new PdfPCell(new Phrase((tmp.equals("null") ? "" : tmp), contentNormalFont)));	
		}
		grid4Table.setWidthPercentage(100f);
		addContent(grid4Table);	
		
	}
	private void addSumGrid4() throws DocumentException {
		//Content
		PdfPTable sumGrid4Table = new PdfPTable(41);
		String[] arrSumGrid4 = sumGrid4.split(Constants.SEPRATE);
		String tmp;
		for (int i=0;i<arrSumGrid4.length;i++) {
			tmp = arrSumGrid4[i];
			sumGrid4Table.addCell(new PdfPCell(new Phrase(tmp.equals("null") ? "" : (tmp + messageSource.getMessage("pdf.gestiondto.content.euro", null, localtion)), contentNormalFont)));	
		}
		sumGrid4Table.setWidthPercentage(100f);
		addContent(sumGrid4Table);	
	}

	public void setInformationChantier(String informationChantier) {
		this.informationChantier = informationChantier;
	}

	public String getInformationChantier() {
		return informationChantier;
	}

	public void setGrid1(List<String> grid1) {
		this.grid1 = grid1;
	}

	public List<String> getGrid1() {
		return grid1;
	}

	public void setGrid2(List<String> grid2) {
		this.grid2 = grid2;
	}

	public List<String> getGrid2() {
		return grid2;
	}

	public void setGrid3(String grid3) {
		this.grid3 = grid3;
	}

	public String getGrid3() {
		return grid3;
	}

	public void setGrid4(String grid4) {
		this.grid4 = grid4;
	}

	public String getGrid4() {
		return grid4;
	}

	public void setTotalRowGrid1(List<String> totalRowGrid1) {
		this.totalRowGrid1 = totalRowGrid1;
	}

	public List<String> getTotalRowGrid1() {
		return totalRowGrid1;
	}

	public void setTotalRowGrid2(List<String> totalRowGrid2) {
		this.totalRowGrid2 = totalRowGrid2;
	}

	public List<String> getTotalRowGrid2() {
		return totalRowGrid2;
	}

	public void setSumTotalGrid1(String sumTotalGrid1) {
		this.sumTotalGrid1 = sumTotalGrid1;
	}

	public String getSumTotalGrid1() {
		return sumTotalGrid1;
	}

	public void setSumTotalGrid2(String sumTotalGrid2) {
		this.sumTotalGrid2 = sumTotalGrid2;
	}

	public String getSumTotalGrid2() {
		return sumTotalGrid2;
	}

	public void setSumGrid3(String sumGrid3) {
		this.sumGrid3 = sumGrid3;
	}

	public String getSumGrid3() {
		return sumGrid3;
	}

	public void setSumGrid4(String sumGrid4) {
		this.sumGrid4 = sumGrid4;
	}

	public String getSumGrid4() {
		return sumGrid4;
	}

	public void setInformationProrata(String informationProrata) {
		this.informationProrata = informationProrata;
	}

	public String getInformationProrata() {
		return informationProrata;
	}


}
