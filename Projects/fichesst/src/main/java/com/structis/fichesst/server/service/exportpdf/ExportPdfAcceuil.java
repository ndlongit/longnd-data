package com.structis.fichesst.server.service.exportpdf;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
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
import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.server.bean.domain.FicheSt;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.TransfertPpSummaryDto;
import com.structis.fichesst.shared.util.Constants;


public class ExportPdfAcceuil extends ExportPdfManager {
	
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
	
	private List<FicheSt> ficheStGrid1;
	private List<FicheSt> ficheStGrid2;
	private List<TransfertPpSummaryDto> listTransfertPpSummaryDto;
	private List<FicheStDto> listSummary;
	private Chantier chantier;
	
	private ResourceBundleMessageSource messageSource;
	
	public ExportPdfAcceuil(OutputStream stream,ResourceBundleMessageSource messageSource,Locale locale)
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
		//Information Chantier
		String chantierName = chantier.getNom();
		Integer prorata = chantier.getProrataTheorique();
		//Synthese des fiches st
		//+ Grid 1		
		List<String> listSrcGrid1 = new ArrayList<String>();
		List<String> listSrcTotalGroupGrid1 = new ArrayList<String>();
		String totalSumGrid1 = createGridReport(ficheStGrid1,listSrcGrid1,listSrcTotalGroupGrid1);
		
		//+Grid 2
		List<String> listSrcGrid2 = new ArrayList<String>();
		List<String> listSrcTotalGroupGrid2 = new ArrayList<String>();
		String totalSumGrid2 = createGridReport(ficheStGrid2, listSrcGrid2, listSrcTotalGroupGrid2);
		
		//Synthese des transpert pp
		TransfertPpSummaryDto transfertPpSummaryDto = null;
		String grid3 = "";
		double sumColumnObjectif = 0.0;
		double sumColumnObj = 0.0;
		double sumColumnTransfert = 0.0;
		double sumColumnRd = 0.0;
		double sumColumnTs = 0.0;
		double sumRow = 0.0;
		double sumSumRow = 0.0;
		NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRENCH);
		for(int i = 0;i< listTransfertPpSummaryDto.size();i++){
			transfertPpSummaryDto = listTransfertPpSummaryDto.get(i);
			sumColumnObjectif += transfertPpSummaryDto.getObjective();
			sumColumnObj += transfertPpSummaryDto.getObj();
			sumColumnTransfert += transfertPpSummaryDto.getDevers();
			sumColumnRd += transfertPpSummaryDto.getRd();
			sumColumnTs += transfertPpSummaryDto.getTs();	
			sumRow = transfertPpSummaryDto.getObj() + transfertPpSummaryDto.getDevers() + transfertPpSummaryDto.getRd() + transfertPpSummaryDto.getTs();
			sumSumRow += sumRow;
			grid3 += createTotalSumReport(numberFormat,transfertPpSummaryDto.getLabel(),transfertPpSummaryDto.getObjective(),transfertPpSummaryDto.getObj(),transfertPpSummaryDto.getDevers(),
					 transfertPpSummaryDto.getRd(),transfertPpSummaryDto.getTs(),sumRow);
			
		}
		String totalSumGrid3= createTotalSumReport(numberFormat,"Total PP",sumColumnObjectif,sumColumnObj,sumColumnTransfert,sumColumnRd,sumColumnTs,sumSumRow);
		
		//ficheStSummaryGrid
		//+Grid 4
		String[] arr = createGridReportSummary(listSummary);		
		
		this.informationChantier = chantierName;
		this.informationProrata = prorata + "";
		this.grid1 = listSrcGrid1;
		this.totalRowGrid1 = listSrcTotalGroupGrid1;
		this.sumTotalGrid1 = totalSumGrid1;
		
		this.grid2 = listSrcGrid2;
		this.totalRowGrid2 = listSrcTotalGroupGrid2;
		this.sumTotalGrid2 = totalSumGrid2;
		
		this.grid3 = grid3;
		this.sumGrid3 = totalSumGrid3;
		
		this.grid4 = arr[0];
		this.sumGrid4 = arr[1];
		
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
		informationChantierCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.nomduchantier", null, localtion) + Constants.SPACE + 
				(informationChantier.equals("null") ? "" : informationChantier), contentBoldFont));
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
		informationChantierCell = new PdfPCell(new Phrase(messageSource.getMessage("pdf.synthese.proratatheorique", null, localtion) + Constants.SPACE + 
				(informationProrata.equals("null") ? "" : informationProrata), contentBoldFont));
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

	public void setFicheStGrid1(List<FicheSt> ficheStGrid1) {
		this.ficheStGrid1 = ficheStGrid1;
	}

	public List<FicheSt> getFicheStGrid1() {
		return ficheStGrid1;
	}

	public void setFicheStGrid2(List<FicheSt> ficheStGrid2) {
		this.ficheStGrid2 = ficheStGrid2;
	}

	public List<FicheSt> getFicheStGrid2() {
		return ficheStGrid2;
	}
	
	private List<String> createKeyGrid1(List<FicheSt> listFicheSt){
		List<FicheSt> fichestGrid1 = ficheStGrid1;
		List<String> lstKeyGrid1 = new ArrayList<String>();
		for (int i = 0; i < fichestGrid1.size(); i++) {
			if(fichestGrid1.get(i).getLotType()!=null && !lstKeyGrid1.contains(fichestGrid1.get(i).getLotType().getName())){
				lstKeyGrid1.add(fichestGrid1.get(i).getLotType().getName());
			}
		}
		return lstKeyGrid1;
	}
	
	private String createGridReport(List<FicheSt> grid,List<String> listSrcGrid,List<String> listSrcTotalGroupGrid){
		
		List<String> lstKeyGrid = createKeyGrid1(grid);
		FicheSt ficheSt = null;	
		double sumtotalObjectif = 0.0;
		double sumtotalObj = 0.0;
		double sumtotalTransfert = 0.0;
		double sumtotalRd = 0.0;
		double sumtotalTs = 0.0;
		double sumtotaltotal =0.0;
		double sumtotalTraite = 0.0;
		double sumtotalArrete = 0.0;
		double sumtotalNonArete = 0.0;
		double sumtotalProvision = 0.0;
		double sumtotalTotalFinal = 0.0;
		double sumtotalDevisRefuse =0.0;
		double sumtotalEcartM = 0.0;
		double sumtotalEcratM1 = 0.0;
		double sumtotalVariationM = 0.0;
		double sumtotalEcartDernierPoint = 0.0;
		double sumtotalVariationEcartPoint = 0.0;
		double sumtotalAvctBA = 0.0;
		double summontantBA = 0.0;
		double sumtotalAvctReel = 0.0;
		double summontantReel = 0.0;
		double sumtotalCanto = 0.0;
		double sumtotalBadge = 0.0;
		double sumtotalGrue = 0.0;
		double sumtotlaLift = 0.0;
		double sumtotalBenne = 0.0;
		double sumtotalNettoy = 0.0;
		double sumtotalAutres = 0.0;
		double sumtotalProrata = 0.0;
		double sumtotalTotal = 0.0;
		double sumtotalPrestationTraite = 0.0;
		double sumtotalPenaliteFacture = 0.0;
		double sumtotalProrataAppliqueSt = 0.0;
		double sumtotalProrataMarche = 0.0;
		double sumtotalProrataSuRad = 0.0;
		double sumtotalMarcheRestantATraiterPercentage = 0.0;
		double sumtotalMarcheRestantATraiter = 0.0;
		double sumtotalVariationDuAuxTransferts = 0.0;
		double sumtotalManqueAGagnerStsansProrata = 0.0;
		
		double cellts = 0.0;
		double celltotlafinal = 0.0;
		
		java.text.NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRENCH);
		numberFormat.setMaximumFractionDigits(1);	
		for(int i=0;i< lstKeyGrid.size();i++){
			String grid1 = "";
			String sumTotalGroup1 = "";
			double totalObjectif = 0.0;
			double totalObj = 0.0;
			double totalTransfert = 0.0;
			double totalRd = 0.0;
			double totalTs = 0.0;
			double totaltotal =0.0;
			double totalTraite = 0.0;
			double totalArrete = 0.0;
			double totalNonArete = 0.0;
			double totalProvision = 0.0;
			double totalTotalFinal = 0.0;
			double totalDevisRefuse =0.0;
			double totalEcartM = 0.0;
			double totalEcratM1 = 0.0;
			double totalVariationM = 0.0;
			double totalEcartDernierPoint = 0.0;
			double totalVariationEcartPoint = 0.0;
			double totalAvctBA = 0.0;
			double montantBA = 0.0;
			double totalAvctReel = 0.0;
			double montantReel = 0.0;
			double totalCanto = 0.0;
			double totalBadge = 0.0;
			double totalGrue = 0.0;
			double totlaLift = 0.0;
			double totalBenne = 0.0;
			double totalNettoy = 0.0;
			double totalAutres = 0.0;
			double totalProrata = 0.0;
			double totalTotal = 0.0;
			double totalPrestationTraite = 0.0;
			double totalPenaliteFacture = 0.0;
			double totalProrataAppliqueSt = 0.0;
			double totalProrataMarche = 0.0;
			double totalProrataSuRad = 0.0;
			double totalMarcheRestantATraiterPercentage = 0.0;
			double totalMarcheRestantATraiter = 0.0;
			double totalVariationDuAuxTransferts = 0.0;
			double totalManqueAGagnerStsansProrata = 0.0;
			for(int j=0;j<grid.size();j++){
				ficheSt = grid.get(j);
				if(lstKeyGrid.get(i).equalsIgnoreCase(ficheSt.getLotType().getName())){
					cellts = ficheSt.getObj() + ficheSt.getTransferts() + ficheSt.getRd() + ficheSt.getTs();
					celltotlafinal = ficheSt.getTraite() + ficheSt.getArrete() + ficheSt.getNonArrete() + ficheSt.getProvision();
					totalObjectif += ficheSt.getObjectif();
					totalObj += ficheSt.getObj();
					totalTransfert += ficheSt.getTransferts();
					totalRd += ficheSt.getRd();
					totalTs += ficheSt.getTs();
					totaltotal += cellts;
					totalTraite += ficheSt.getTraite();
					totalArrete += ficheSt.getArrete();
					totalNonArete += ficheSt.getNonArrete();
					totalProvision += ficheSt.getProvision();
					totalTotalFinal += celltotlafinal;
					totalDevisRefuse +=ficheSt.getDevisRefuse();
					totalEcartM += getEcardM(ficheSt);
					totalEcratM1 += ficheSt.getEcartM1();
					totalVariationM += getVariationM(ficheSt);
					totalEcartDernierPoint += ficheSt.getEcartDernierPoint();
					totalVariationEcartPoint += getVariationEcartPointM(ficheSt);
					totalAvctBA += getAvctBAPercentage(ficheSt);
					montantBA += ficheSt.getTotalCumule();
	
					totalAvctReel += getAvctReelPercentage(ficheSt);
					montantReel += ficheSt.getTraite();
					totalCanto += ficheSt.getTotalCanto();
					totalBadge += ficheSt.getTotalBadge();
					totalGrue += ficheSt.getTotalGrue();
					totlaLift += ficheSt.getTotalLift();
					totalBenne += ficheSt.getTotalBenne();
					totalNettoy += ficheSt.getTotalNettoy();
					totalAutres += ficheSt.getTotalAutres();
					totalProrata += ficheSt.getTotalProrata();
					totalTotal += getTotal2(ficheSt);
					totalPrestationTraite += getPrestationTraitePercentage(ficheSt);
					totalPenaliteFacture += ficheSt.getTotalPenalty();
					totalProrataAppliqueSt += ficheSt.getPrestaProrata();
					totalProrataMarche += getProrataMarche(ficheSt);
					totalProrataSuRad += getProrataSurRAD(ficheSt);
					totalMarcheRestantATraiterPercentage += getMarchesRestantATraiterPercentage(ficheSt);
					totalMarcheRestantATraiter += getMarchesRestantATraiter(ficheSt);
					totalVariationDuAuxTransferts += getVariationDuAuxTransferts(ficheSt);
					totalManqueAGagnerStsansProrata += getManqueAGagnerSTSansProrata(ficheSt);
					//Create row report
					grid1 += createRowReport(ficheSt,cellts,celltotlafinal,numberFormat);	
				}
				
			}				
			//Calculate sum group 1	
			sumTotalGroup1 += createTotalSumReport(numberFormat,"null",totalObjectif,null,totalObj, totalTransfert,totalRd,totalTs,totaltotal,totalTraite,totalArrete,totalNonArete,totalProvision,totalTotalFinal, 
					totalDevisRefuse,totalEcartM,totalEcratM1,totalVariationM,totalEcartDernierPoint,totalVariationEcartPoint,null,montantBA,null,montantReel,totalCanto,totalBadge,totalGrue,totlaLift,totalBenne,
					totalNettoy,totalAutres,totalProrata,totalTotal,totalPrestationTraite,totalPenaliteFacture,totalProrataAppliqueSt,totalProrataMarche,totalProrataSuRad,totalMarcheRestantATraiterPercentage,
					totalMarcheRestantATraiter,totalVariationDuAuxTransferts,totalManqueAGagnerStsansProrata);

			listSrcGrid.add(grid1);
			listSrcTotalGroupGrid.add(sumTotalGroup1);
			
			sumtotalObjectif += totalObjectif;
			sumtotalObj += totalObj;
			sumtotalTransfert += totalTransfert;
			sumtotalRd += totalRd;
			sumtotalTs += totalTs;
			sumtotaltotal += totaltotal;
			sumtotalTraite += totalTraite;
			sumtotalArrete += totalArrete;
			sumtotalNonArete += totalNonArete;
			sumtotalProvision += totalProvision;
			sumtotalTotalFinal += totalTotalFinal;
			sumtotalDevisRefuse += totalDevisRefuse;
			sumtotalEcartM += totalEcartM;
			sumtotalEcratM1 += totalEcratM1;
			sumtotalVariationM += totalVariationM;
			sumtotalEcartDernierPoint += totalEcartDernierPoint;
			sumtotalVariationEcartPoint += totalVariationEcartPoint;
			sumtotalAvctBA += totalAvctBA;
			summontantBA += montantBA;
			sumtotalAvctReel += totalAvctReel;
			summontantReel += montantReel;
			sumtotalCanto += totalCanto;
			sumtotalBadge += totalBadge;
			sumtotalGrue += totalGrue;
			sumtotlaLift += totlaLift;
			sumtotalBenne += totalBenne;
			sumtotalNettoy += totalNettoy;
			sumtotalAutres += totalAutres;
			sumtotalProrata += totalProrata;
			sumtotalTotal += totalTotal;
			sumtotalPrestationTraite += totalPrestationTraite;
			sumtotalPenaliteFacture += totalPenaliteFacture;
			sumtotalProrataAppliqueSt += totalProrataAppliqueSt;
			sumtotalProrataMarche += totalProrataMarche;
			sumtotalProrataSuRad += totalProrataSuRad;
			sumtotalMarcheRestantATraiterPercentage += totalMarcheRestantATraiterPercentage;
			sumtotalMarcheRestantATraiter += totalMarcheRestantATraiter;
			sumtotalVariationDuAuxTransferts += totalVariationDuAuxTransferts;
			sumtotalManqueAGagnerStsansProrata += totalManqueAGagnerStsansProrata;
		}
		String totalSumGrid = createTotalSumReport(numberFormat,"TOTAL ST",sumtotalObjectif,null,sumtotalObj,sumtotalTransfert,sumtotalRd,sumtotalTs,sumtotaltotal,sumtotalTraite,sumtotalArrete,sumtotalNonArete,
				sumtotalProvision,sumtotalTotalFinal,sumtotalDevisRefuse,sumtotalEcartM,sumtotalEcratM1,sumtotalVariationM,sumtotalEcartDernierPoint,sumtotalVariationEcartPoint, 
				null,summontantBA,null,summontantReel,sumtotalCanto,sumtotalBadge,sumtotalGrue,sumtotlaLift,sumtotalBenne, 
				sumtotalNettoy,sumtotalAutres,sumtotalProrata,sumtotalTotal,sumtotalPrestationTraite,sumtotalPenaliteFacture,sumtotalProrataAppliqueSt,sumtotalProrataMarche,sumtotalProrataSuRad,
				null,sumtotalMarcheRestantATraiter,sumtotalVariationDuAuxTransferts,sumtotalManqueAGagnerStsansProrata);
		return totalSumGrid;
	}
	
	private String createRowReport(FicheSt ficheSt,double cellts,double celltotlafinal,NumberFormat numberFormat){
		String row = append(ficheSt.getLot().getName(),ficheSt.getObjectif(),ficheSt.getSociete(),ficheSt.getObj(),ficheSt.getTransferts(),ficheSt.getRd(),ficheSt.getTs(), 
		cellts,ficheSt.getTraite(),ficheSt.getArrete(),ficheSt.getNonArrete(),ficheSt.getProvision(),celltotlafinal,ficheSt.getDevisRefuse(),getEcardM(ficheSt),ficheSt.getEcartM1(),
		getVariationM(ficheSt),ficheSt.getEcartDernierPoint(),getVariationEcartPointM(ficheSt),getAvctBAPercentage(ficheSt),ficheSt.getTotalCumule(),getAvctReelPercentage(ficheSt),ficheSt.getTraite(), 
		ficheSt.getTotalCanto(),ficheSt.getTotalBadge(),ficheSt.getTotalGrue(),ficheSt.getTotalLift(),ficheSt.getTotalBenne(),ficheSt.getTotalNettoy(),ficheSt.getTotalAutres(),ficheSt.getTotalProrata(), 
		getTotal2(ficheSt),getPrestationTraitePercentage(ficheSt),ficheSt.getTotalPenalty(),ficheSt.getPrestaProrata(),getProrataMarche(ficheSt),getProrataSurRAD(ficheSt),
		getMarchesRestantATraiterPercentage(ficheSt),getMarchesRestantATraiter(ficheSt),getVariationDuAuxTransferts(ficheSt),getManqueAGagnerSTSansProrata(ficheSt));
		return row;
	}
	
	private String createRowReport(FicheStDto ficheSt,double cellts,double celltotlafinal,NumberFormat numberFormat){
		/*String row = ficheSt.getLot().getName() + Constants.SEPRATE + numberFormat.format(ficheSt.getObjectif()) + Constants.SEPRATE + ficheSt.getSociete() + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getObj()) + Constants.SEPRATE + numberFormat.format(ficheSt.getTransferts()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getRd()) + Constants.SEPRATE + numberFormat.format(ficheSt.getTs()) + Constants.SEPRATE + 
		numberFormat.format(cellts) + Constants.SEPRATE + numberFormat.format(ficheSt.getTraite()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getArrete()) + Constants.SEPRATE + numberFormat.format(ficheSt.getNonArrete()) + 
		Constants.SEPRATE + numberFormat.format(ficheSt.getProvision()) + Constants.SEPRATE + numberFormat.format(celltotlafinal) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getDevisRefuse()) + Constants.SEPRATE + numberFormat.format(ficheSt.getEcartM()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getEcartM1()) + Constants.SEPRATE + numberFormat.format(ficheSt.getVariationM()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getEcartDernierPoint()) + Constants.SEPRATE + numberFormat.format(ficheSt.getVariationEcartPointM()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getAvctBAPercentage()) + Constants.SEPRATE + numberFormat.format(ficheSt.getTotalCumule()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getAvctReelPercentage()) + Constants.SEPRATE + numberFormat.format(ficheSt.getTraite()) + 
		Constants.SEPRATE + numberFormat.format(ficheSt.getTotalCanto()) + Constants.SEPRATE + numberFormat.format(ficheSt.getTotalBadge()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getTotalGrue()) + Constants.SEPRATE + numberFormat.format(ficheSt.getTotalLift()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getTotalBenne()) + Constants.SEPRATE + numberFormat.format(ficheSt.getTotalNettoy()) + 
		Constants.SEPRATE + numberFormat.format(ficheSt.getTotalAutres()) + Constants.SEPRATE + numberFormat.format(ficheSt.getTotalProrata()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getTotal2()) + Constants.SEPRATE + numberFormat.format(ficheSt.getPrestationTraitePercentage()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getTotalPenalty()) + Constants.SEPRATE + numberFormat.format(ficheSt.getPrestaProrata()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getProrataMarche()) + Constants.SEPRATE + numberFormat.format(ficheSt.getProrataSurRAD()) + Constants.SEPRATE +
		numberFormat.format(ficheSt.getMarchesRestantATraiterPercentage()) + Constants.SEPRATE + numberFormat.format(ficheSt.getMarchesRestantATraiter()) + Constants.SEPRATE + 
		numberFormat.format(ficheSt.getVariationDuAuxTransferts()) + Constants.SEPRATE + numberFormat.format(ficheSt.getManqueAGagnerSTSansProrata()) + Constants.SEPRATE;*/
		String row = append(ficheSt.getLot().getName(),ficheSt.getObjectif(),ficheSt.getSociete(),ficheSt.getObj(),ficheSt.getTransferts(), 
		ficheSt.getRd(),ficheSt.getTs(),cellts,ficheSt.getTraite(),ficheSt.getArrete(),ficheSt.getNonArrete(),ficheSt.getProvision(),celltotlafinal,ficheSt.getDevisRefuse(),
		ficheSt.getEcartM(),ficheSt.getEcartM1(),ficheSt.getVariationM(),ficheSt.getEcartDernierPoint(),ficheSt.getVariationEcartPointM(),ficheSt.getAvctBAPercentage(),ficheSt.getTotalCumule(),
		ficheSt.getAvctReelPercentage(),ficheSt.getTraite(),ficheSt.getTotalCanto(),ficheSt.getTotalBadge(),ficheSt.getTotalGrue(),ficheSt.getTotalLift(),ficheSt.getTotalBenne(),ficheSt.getTotalNettoy() + 
		ficheSt.getTotalAutres(),ficheSt.getTotalProrata(),ficheSt.getTotal2(),ficheSt.getPrestationTraitePercentage(),ficheSt.getTotalPenalty(),ficheSt.getPrestaProrata(), 
		ficheSt.getProrataMarche(),ficheSt.getProrataSurRAD(),ficheSt.getMarchesRestantATraiterPercentage(),ficheSt.getMarchesRestantATraiter(),ficheSt.getVariationDuAuxTransferts(),ficheSt.getManqueAGagnerSTSansProrata());
		return row;
	}
	/**
	 * Creat Total Sum Report
	 * @param numberFormat
	 * @param name
	 * @param params
	 * @return
	 */
	private String createTotalSumReport(NumberFormat numberFormat,String name,Double... params){
		String totalSumGrid = name + Constants.SEPRATE; 
		for(Double param : params){
			if(param !=null){
				totalSumGrid += numberFormat.format(param);
			}
			else
				totalSumGrid += "null";
			totalSumGrid += Constants.SEPRATE;
		}
		return totalSumGrid;
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getAvctReelPercentage(FicheSt ficheSt) {
		double result;
		Double totalReelActivitive = ficheSt.getTotalReelActivitive();
		Double totalTraite = ficheSt.getTraite();
		if( totalTraite == 0 ) {
			result = 0.0;
		}
		else {
			result = totalReelActivitive / totalTraite * 100;
		}
		return Math.round(result) * 1.0;
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	public Double getPrestationTraitePercentage(FicheSt ficheSt) {
		double result;
		double traite = ficheSt.getTraite();
		double total = getTotal(ficheSt);
		if( total == 0 ) {
			result = 0.0;
		}
		else {
			result = traite / total * 100;
		}
		return Math.round(result) * 1.0;
	}
	
	//Calculated columns - Begin
	/**
	 * 
	 */
	private Double getTotal(FicheSt ficheSt) {
		return ficheSt.getObj() + ficheSt.getTransferts() + ficheSt.getRd() + ficheSt.getTs();
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getTotal2(FicheSt ficheSt) {
		return ficheSt.getTotalCanto() + ficheSt.getTotalBadge() + ficheSt.getTotalGrue() + ficheSt.getTotalLift() + 
		ficheSt.getTotalBenne() + ficheSt.getTotalNettoy() + ficheSt.getTotalAutres() + ficheSt.getTotalProrata();
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getMarchesRestantATraiterPercentage(FicheSt ficheSt) {
		double prorataAppliqueST = ficheSt.getPrestaProrata();
		
		double traite = ficheSt.getTraite();
		if( prorataAppliqueST == 0 && traite == 0 ) {
			return getProrataTheorique(ficheSt) * 1.0;
		}
		else {
			return 0.0;
		}
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getProrataMarche(FicheSt ficheSt) {
		double prestaProrata = ficheSt.getPrestaProrata();
		double totalTraite = ficheSt.getTraite();
		double arrete = ficheSt.getArrete();

		return prestaProrata * (totalTraite + arrete);
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Integer getProrataTheorique(FicheSt ficheSt) {
		if(ficheSt !=null && ficheSt.getLot() != null && ficheSt.getLot().getChantier()!=null && ficheSt.getLot().getChantier().getProrataTheorique()!=null)
			return ficheSt.getLot().getChantier().getProrataTheorique();
		return 0;
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getProrataSurRAD(FicheSt ficheSt) {
		double prestaProrata = ficheSt.getPrestaProrata();
		Double nonArrete = ficheSt.getNonArrete();
		Double provision = ficheSt.getProvision();
		return prestaProrata * (nonArrete + provision);
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getMarchesRestantATraiter(FicheSt ficheSt) {
		return ficheSt.getTotalCumule() * 
		getProrataTheorique(ficheSt);
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getVariationDuAuxTransferts(FicheSt ficheSt) {
		double marchesRestantATraiterPercentage = getMarchesRestantATraiterPercentage(ficheSt);
		double prorataAppliqueST = ficheSt.getPrestaProrata();
		double transferts = ficheSt.getTransferts(); // +/-
		if( marchesRestantATraiterPercentage > 0 || prorataAppliqueST > 0 ) {
			return transferts * (marchesRestantATraiterPercentage + prorataAppliqueST);
		}
		else {
			return transferts * getProrataTheorique(ficheSt);
		}
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	public Double getManqueAGagnerSTSansProrata(FicheSt ficheSt) { //PRESTA_PRORATA
		double marchesRestantATraiterPercentage = getMarchesRestantATraiterPercentage(ficheSt);
		double prorataAppliqueST = ficheSt.getPrestaProrata();
		if( marchesRestantATraiterPercentage == 0 || prorataAppliqueST == 0 ) {
			return getFinalTotal(ficheSt) * getProrataTheorique(ficheSt);
		}
		else {
			return 0.0;
		}
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private double getFinalTotal(FicheSt ficheSt) {
		return ficheSt.getTraite() + ficheSt.getArrete() + ficheSt.getNonArrete() + ficheSt.getProvision();
	}
	
	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getVariationEcartPointM(FicheSt ficheSt) {
		return getEcardM(ficheSt) - ficheSt.getEcartDernierPoint();
	}
	
	private Double getAvctBAPercentage(FicheSt ficheSt) {
		double totalCumule = ficheSt.getTotalCumule();
		double totalMch = ficheSt.getTraite();
		double result;
		if( totalMch == 0 ) {
			result = 0.0;
		}
		else {
			if( totalCumule <= 0 ) {
				result = 0.0;
			}
			else {
				result = totalCumule * 1.0 / totalMch * 100;
			}
		}
		return Math.round(result) * 1.0;
	}
	
	private Double getVariationM(FicheSt ficheSt) {
		return getEcardM(ficheSt) - ficheSt.getEcartM1();
	}
	
	private double getEcardM(FicheSt ficheSt) {
		double finalTotal = getFinalTotal(ficheSt);
		double total = getTotal(ficheSt);
		return finalTotal - total;
	}

	public void setListTransfertPpSummaryDto(List<TransfertPpSummaryDto> listTransfertPpSummaryDto) {
		this.listTransfertPpSummaryDto = listTransfertPpSummaryDto;
	}

	public List<TransfertPpSummaryDto> getListTransfertPpSummaryDto() {
		return listTransfertPpSummaryDto;
	}

	public void setListSummary(List<FicheStDto> listSummary) {
		this.listSummary = listSummary;
	}

	public List<FicheStDto> getListSummary() {
		return listSummary;
	}
	
	private String[] createGridReportSummary(List<FicheStDto> grid){
		
		FicheStDto ficheStDto = null;		
		double cellts = 0.0;
		double celltotlafinal = 0.0;
		double totalObjectif = 0.0;
		double totalObj = 0.0;
		double totalTransfert = 0.0;
		double totalRd = 0.0;
		double totalTs = 0.0;
		double totaltotal =0.0;
		double totalTraite = 0.0;
		double totalArrete = 0.0;
		double totalNonArete = 0.0;
		double totalProvision = 0.0;
		double totalTotalFinal = 0.0;
		double totalDevisRefuse =0.0;
		double totalEcartM = 0.0;
		double totalEcratM1 = 0.0;
		double totalVariationM = 0.0;
		double totalEcartDernierPoint = 0.0;
		double totalVariationEcartPoint = 0.0;
		double totalAvctBA = 0.0;
		double montantBA = 0.0;
		double totalAvctReel = 0.0;
		double montantReel = 0.0;
		double totalCanto = 0.0;
		double totalBadge = 0.0;
		double totalGrue = 0.0;
		double totlaLift = 0.0;
		double totalBenne = 0.0;
		double totalNettoy = 0.0;
		double totalAutres = 0.0;
		double totalProrata = 0.0;
		double totalTotal = 0.0;
		double totalPrestationTraite = 0.0;
		double totalPenaliteFacture = 0.0;
		double totalProrataAppliqueSt = 0.0;
		double totalProrataMarche = 0.0;
		double totalProrataSuRad = 0.0;
		double totalMarcheRestantATraiterPercentage = 0.0;
		double totalMarcheRestantATraiter = 0.0;
		double totalVariationDuAuxTransferts = 0.0;
		double totalManqueAGagnerStsansProrata = 0.0;
		String grid1 = "";
		NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRENCH);
		for(int i=0;i<grid.size();i++){
			ficheStDto = grid.get(i);
			cellts = ficheStDto.getObj() + ficheStDto.getTransferts() + ficheStDto.getRd() + ficheStDto.getTs();
			celltotlafinal = ficheStDto.getTraite() + ficheStDto.getArrete() + ficheStDto.getNonArrete() + ficheStDto.getProvision();
			totalObjectif += ficheStDto.getObjectif();
			totalObj += ficheStDto.getObj();
			totalTransfert += ficheStDto.getTransferts();
			totalRd += ficheStDto.getRd();
			totalTs += ficheStDto.getTs();
			totaltotal += cellts;
			totalTraite += ficheStDto.getTraite();
			totalArrete += ficheStDto.getArrete();
			totalNonArete += ficheStDto.getNonArrete();
			totalProvision += ficheStDto.getProvision();
			totalTotalFinal += celltotlafinal;
			totalDevisRefuse +=ficheStDto.getDevisRefuse();
			totalEcartM += ficheStDto.getEcartM();
			totalEcratM1 += ficheStDto.getEcartM1();
			totalVariationM += ficheStDto.getVariationM();
			totalEcartDernierPoint += ficheStDto.getEcartDernierPoint();
			totalVariationEcartPoint += ficheStDto.getVariationEcartPointM();
			totalAvctBA += ficheStDto.getAvctBAPercentage();
			montantBA += ficheStDto.getTotalCumule();
			totalAvctReel += ficheStDto.getAvctReelPercentage();
			montantReel += ficheStDto.getTraite();
			totalCanto += ficheStDto.getTotalCanto();
			totalBadge += ficheStDto.getTotalBadge();
			totalGrue += ficheStDto.getTotalGrue();
			totlaLift += ficheStDto.getTotalLift();
			totalBenne += ficheStDto.getTotalBenne();
			totalNettoy += ficheStDto.getTotalNettoy();
			totalAutres += ficheStDto.getTotalAutres();
			totalProrata += ficheStDto.getTotalProrata();
			totalTotal += ficheStDto.getTotal2();
			totalPrestationTraite += ficheStDto.getPrestationTraitePercentage();
			totalPenaliteFacture += ficheStDto.getTotalPenalty();
			totalProrataAppliqueSt += ficheStDto.getProrataAppliqueST();
			totalProrataMarche += ficheStDto.getProrataMarche();
			totalProrataSuRad += ficheStDto.getProrataSurRAD();
			totalMarcheRestantATraiterPercentage += ficheStDto.getMarchesRestantATraiterPercentage();
			totalMarcheRestantATraiter += ficheStDto.getMarchesRestantATraiter();
			totalVariationDuAuxTransferts += ficheStDto.getVariationDuAuxTransferts();
			totalManqueAGagnerStsansProrata += ficheStDto.getManqueAGagnerSTSansProrata();
			grid1 += createRowReport(ficheStDto, cellts, celltotlafinal, numberFormat);
			
		}
		String totalSumGrid = createTotalSumReport(numberFormat,"TOTAL CHANTIER",totalObjectif,null,totalObj,totalTransfert,totalRd,totalTs,totaltotal,totalTraite,totalArrete,totalNonArete,
				totalProvision,totalTotalFinal,totalDevisRefuse,totalEcartM, totalEcratM1,totalVariationM,totalEcartDernierPoint,totalVariationEcartPoint,totalAvctBA,montantBA,null,montantReel,
				totalCanto,totalBadge, totalGrue,totlaLift,totalBenne, totalNettoy,totalAutres,totalProrata,totalTotal,totalPrestationTraite,totalPenaliteFacture,totalProrataAppliqueSt,
				totalProrataMarche,totalProrataSuRad,totalMarcheRestantATraiterPercentage,totalMarcheRestantATraiter,totalVariationDuAuxTransferts,totalManqueAGagnerStsansProrata);
		
		String[] arr = new String[2];
		arr[0] = grid1;
		arr[1] = totalSumGrid;
		return arr;
	}

	public void setChantier(Chantier chantier) {
		this.chantier = chantier;
	}

	public Chantier getChantier() {
		return chantier;
	}
	/**
	 * 
	 * @param params
	 * @return
	 */
	private String append(Object... params){
		NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRENCH);
		numberFormat.setMaximumFractionDigits(1);
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
	
	
}
