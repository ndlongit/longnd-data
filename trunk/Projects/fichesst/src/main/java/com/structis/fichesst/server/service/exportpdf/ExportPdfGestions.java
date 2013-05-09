package com.structis.fichesst.server.service.exportpdf;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.structis.fichesst.server.util.AppUtil;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.util.Constants;

public class ExportPdfGestions extends ExportPdfManager {

	private static final Logger LOGGER = Logger.getLogger(ExportPdfGestions.class);

	private final FicheStDto ficheSt;

	private final List<GestionDto> gestionList;

	private List<String> gestions;

	private List<String> totalgestions;

	private String alltotalgestion;

	private String summary;

	private String budget;

	public ExportPdfGestions(FicheStDto ficheSt, List<GestionDto> gestionList, ResourceBundleMessageSource messageSource, Locale locale)
			throws DocumentException, IOException {
		super(locale);

		this.ficheSt = ficheSt;
		this.gestionList = gestionList;
		this.messageSource = messageSource;
	}

	@Override
	public File render() throws Exception {
		prepareData();
		addGestionDto();
		return pdfFile;
	}

	private void prepareData() {
		setGeneraleInformation(buildGeneralInfo(ficheSt));
		double totalObj = 0.0;
		double totalTF = 0.0;
		double totalTS = 0.0;
		double totalRD = 0.0;

		Double budgetInitial = ficheSt.getGestBudgetInitial();
		Double dernierPoint = ficheSt.getGestEcartDernierPt();
		Date dateDernier = ficheSt.getGestDateDernierPt();

		List<GestionDto> list = this.gestionList;
		Map<String, String> listgestion = new HashMap<String, String>();
		Map<String, String> listtotalgestion = new HashMap<String, String>();
		List<String> lstKeys = new ArrayList<String>();
		String keys = "";
		for (int i = 0; i < list.size(); i++) {
			totalObj = 0.0;
			totalTF = 0.0;
			totalTS = 0.0;
			totalRD = 0.0;
			for (GestionDto gestionDto : list) {
				Integer typeId = gestionDto.getType() != null ? gestionDto.getType().getId() : -1;
				double value = gestionDto.getAmount2();
				switch (typeId) {
				case 1:
					totalObj += value;
					break;
				case 2:
					totalTF += value;
					break;
				case 3:
					totalTS += value;
					break;
				case 4:
					totalRD += value;
					break;
				default:
					break;
				}
			}

			if (list.get(i).getMarche() != null && !lstKeys.contains(list.get(i).getMarche().getLabel())) {
				lstKeys.add(list.get(i).getMarche().getLabel());
				keys += list.get(i).getMarche().getLabel() + Constants.SEPRATE;
			}
		}
		GestionDto gestionDto = null;
		String alltotalgestion = "";

		double allamount = 0.0;
		double allavenants = 0.0;
		double allarrete = 0.0;
		double allnonarrete = 0.0;
		double allprovision = 0.0;
		double alldevisrefuse = 0.0;
		double allreelactivitive = 0.0;
		double allamount2 = 0.0;
		double alltotalfdc = 0.0;
		double allTotalecart = 0.0;

		String summary = "";
		String budget = "";
		for (int i = 0; i < lstKeys.size(); i++) {
			String gestion = "";
			String totalgestion = "";
			double amount = 0.0;
			double avenants = 0.0;
			double arrete = 0.0;
			double nonarrete = 0.0;
			double provision = 0.0;
			double devisrefuse = 0.0;
			double reelactivitive = 0.0;
			double amount2 = 0.0;
			String gestionComment = "";
			double ecartSum = 0.0;
			double totalfdcSum = 0.0;

			for (int j = 0; j < list.size(); j++) {
				gestionDto = list.get(j);
				if (lstKeys.get(i).equalsIgnoreCase(gestionDto.getMarche().getLabel())) {

					String tmp = gestionDto.getMarche().getLabel() + "                                               "
							+ numberFormat2.format(gestionDto.getTraite());
					gestionComment = gestionDto.getComment() != null ? gestionDto.getComment() : "";
					gestion += append(gestionDto.getDevis(), gestionDto.getStatut().getLabel(), gestionDto.getLabel(), gestionComment,
							gestionDto.getAmount(), tmp, gestionDto.getArrete(), gestionDto.getNonArrete(), gestionDto.getProvision(),
							gestionDto.getDevisRefuse(), gestionDto.getTotalFdc(), gestionDto.getReelActivitive(), gestionDto.getType().getLabel(),
							gestionDto.getLabel2(), gestionDto.getAmount2(), gestionDto.getEcart());

					// Sum Group
					amount += gestionDto.getAmount();
					avenants += gestionDto.getTraite();
					arrete += gestionDto.getArrete();
					nonarrete += gestionDto.getNonArrete();
					provision += gestionDto.getProvision();
					devisrefuse += gestionDto.getDevisRefuse();
					reelactivitive += gestionDto.getReelActivitive();
					amount2 += gestionDto.getAmount2();
					ecartSum += gestionDto.getEcart();
					totalfdcSum += gestionDto.getTotalFdc();
				}
			}
			// Sum Gestion
			allamount += amount;
			allavenants += avenants;
			allarrete += arrete;
			allnonarrete += nonarrete;
			allprovision += provision;
			alldevisrefuse += devisrefuse;
			allreelactivitive += reelactivitive;
			allamount2 += amount2;
			allTotalecart += ecartSum;
			alltotalfdc += totalfdcSum;
			listgestion.put(lstKeys.get(i), gestion);
			totalgestion += append(amount, avenants, arrete, nonarrete, provision, devisrefuse, totalfdcSum, reelactivitive, amount2, ecartSum);
			listtotalgestion.put(lstKeys.get(i), totalgestion);
		}
		if (keys.length() > 0) {
			keys = keys.substring(0, keys.length() - (Constants.SEPRATE.length()));
		}
		alltotalgestion += append(allamount, allavenants, allarrete, allnonarrete, allprovision, alldevisrefuse, alltotalfdc, allreelactivitive,
				allamount2, allTotalecart);

		setGeneraleInformation(ExportPdfManager.buildGeneralInfo(ficheSt));
		summary += append(totalObj, totalTF, totalTS, totalRD);
		setSummary(processNullValue(summary));
		budget += append(budgetInitial, dernierPoint, dateDernier);
		setBudget(processNullValue(budget));
		String gestionStrings = listgestion.toString();

		String totalgestion = listtotalgestion.toString();
		setAlltotalgestion(processNullValue(alltotalgestion));
		Properties props = new Properties();
		try {
			props.load(new StringReader(gestionStrings.substring(1, gestionStrings.length() - 1).replace(", ", "\n")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Map<String, String> mapGestion = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			mapGestion.put((String) e.getKey(), (String) e.getValue());
		}

		props = new Properties();
		try {
			props.load(new StringReader(totalgestion.substring(1, totalgestion.length() - 1).replace(", ", "\n")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Map<String, String> mapTotalGestion = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			mapTotalGestion.put((String) e.getKey(), (String) e.getValue());
		}

		try {
			List<String> listTotalGestion = new ArrayList<String>();
			List<String> listGestion = new ArrayList<String>();
			String value = "";
			String key[] = keys.split(Constants.SEPRATE);

			if( key != null && key.length >= 1 ) {
				for( int i = 0 ; i < key.length ; i++ ) {
					value = mapGestion.get(key[i]);
					if( value != null && value.endsWith(Constants.SEPRATE) ) {
						value = value.substring(0, value.length() - Constants.SEPRATE.length());
					}

					listGestion.add(value);
					value = mapTotalGestion.get(key[i]);
					if( value != null && value.endsWith(Constants.SEPRATE) ) {
						value = value.substring(0, value.length() - Constants.SEPRATE.length());
					}

					listTotalGestion.add(value);
				}
			}

			setGestions(listGestion);
			setTotalgestions(listTotalGestion);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	protected void addGestionDto() throws DocumentException {

		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);

		PdfPCell headerCell = new PdfPCell(new Phrase(getMessage("pdf.gestiondto.suividegestiondusoustraitant"), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		headerTable.addCell(headerCell);
		addContent(headerTable);
		lineBreak();

		headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		headerCell = new PdfPCell(new Phrase(getMessage("FicheST.generalInformation"), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		headerTable.addCell(headerCell);
		addContent(headerTable);

		PdfPTable generaleInfoTable = buildGeneralInfoTable();
		addContent(generaleInfoTable);

		// Header
		headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		headerCell = new PdfPCell(new Phrase(getMessage("FicheST.gestion"), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		addContent(headerTable);

		// Add Group Header
		PdfPCell[] groupHeaderCell = new PdfPCell[6];
		groupHeaderCell[0] = createEmptyCell();
		groupHeaderCell[1] = createHeaderCell("TRAITE");
		groupHeaderCell[2] = createHeaderCell("A TRAITER");
		groupHeaderCell[3] = createHeaderCell("BUDGET Conforme");
		groupHeaderCell[4] = createEmptyCell();
		groupHeaderCell[5] = createEmptyCell();

		float[] wfGroupHeader = { 41, 18, 10, 22, 17, 7 };
		for (PdfPCell pdfPCell : groupHeaderCell) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		PdfPTable groupHeaderTable = new PdfPTable(6);
		groupHeaderTable.setWidthPercentage(100f);
		groupHeaderTable.setWidths(wfGroupHeader);
		addCellsToTable(groupHeaderTable, groupHeaderCell);
		addContent(groupHeaderTable);

		// Content Header
		PdfPCell[] riskTableHeader = new PdfPCell[16];
		String[] resourceKeys = { "FicheST.devis", "FicheST.status", "pdf.gestiondto.content.libelle", "pdf.gestiondto.content.commentaires",
				"pdf.gestiondto.content.montant", "pdf.gestiondto.content.marche_avenants", "pdf.gestiondto.content.arrete",
				"pdf.gestiondto.content.nonarrete", "pdf.gestiondto.content.provision", "pdf.gestiondto.content.devisrefuse",
				"pdf.gestiondto.content.totalfdc", "pdf.gestiondto.content.activitereel", "FicheST.type", "pdf.gestiondto.content.libelle_",
				"pdf.gestiondto.content.montant", "pdf.gestiondto.content.ecart" };

		for (int i = 0; i < riskTableHeader.length; i++) {
			riskTableHeader[i] = createHeaderCell(getMessage(resourceKeys[i]));
			riskTableHeader[i].setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}
		// End

		PdfPTable gestionDtoTable = new PdfPTable(16);
		gestionDtoTable.setWidthPercentage(100f);
		addCellsToTable(gestionDtoTable, riskTableHeader);
		float[] wf = { 7, 7, 7, 13, 7, 18, 5, 5, 5, 5, 7, 5, 5, 7, 5, 7 };

		gestionDtoTable.setWidthPercentage(100f);
		gestionDtoTable.setWidths(wf);

		// Add Header
		addContent(gestionDtoTable);

//		gestionDtoTable.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

		if (!AppUtil.isNullOrEmpty(gestions)) {
			for (int j = 0; j < gestions.size(); j++) {
				gestionDtoTable = new PdfPTable(16);
				String param = gestions.get(j);
				if (param != null && param.length() > 0) {
					String arr[] = param.split(Constants.SEPRATE);
					for (int i = 0; i < arr.length; i++) {
						PdfPCell cell = new PdfPCell(new Phrase(processNullValue(arr[i]), contentNormalFont));
						setCellAlignment2(cell, i);
						gestionDtoTable.addCell(cell);
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
		}

		PdfPTable alltotalSumTable = addalltotalSum(processNullValue(alltotalgestion));
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
		this.alltotalgestion = processNullValue(alltotalgestion);
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
}
