package com.structis.fichesst.server.service.exportpdf;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.structis.fichesst.server.util.AppUtil;
import com.structis.fichesst.shared.dto.CautionFournieDto;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.ProgressDto;
import com.structis.fichesst.shared.util.Constants;

public class ExportPdfFichest extends ExportPdfManager {

	// Grid 1
	private String conditionsparticulieres;

	private String prestations;

	private String complementairesInfo;

	private String cautionFournies;

	// Grid 2
	private List<String> gestions;

	private List<String> totalgestions;

	private String alltotalgestion;

	private String summary;

	private String budget;

	// Grid 3
	private String processes;

	private String totalsituation;

	private String grid2;

	private String grid3;

	String avancementCommentaire;

	public String getAvancementCommentaire() {
		return avancementCommentaire;
	}

	public void setAvancementCommentaire(String avancementCommentaire) {
		this.avancementCommentaire = avancementCommentaire;
	}

	private String commentaire;

	private String etatAvancement;

	private String deductions;

	private String totaldeduction;

	private String penaltys;

	private String amount;

	private String information;

	private String deductionComment1;

	private String deductionComment2;

	private final FicheStDto ficheStDto;

	private final List<GestionDto> listGestion;

	private final List<ProgressDto> listProcess;

	private final List<CautionFournieDto> listCautionFournieDto;

	private final List<DeductionDto> deductionList;

	private final List<PenaltyDto> listPenalty;

	public ExportPdfFichest(ResourceBundleMessageSource messageSource, Locale locale, FicheStDto ficheStDto,
			List<CautionFournieDto> listCautionFournieDto, List<GestionDto> listGestion, List<ProgressDto> listProcess,
			List<DeductionDto> deductionList, List<PenaltyDto> listPenalty, String totaldeduction, String detailRetenues, String grid3Str,
			String etatAvancement, String totalsituation) throws DocumentException, IOException {
		super(locale);

		this.ficheStDto = ficheStDto;
		this.listCautionFournieDto = listCautionFournieDto;
		this.listGestion = listGestion;
		this.listProcess = listProcess;
		this.deductionList = deductionList;
		this.listPenalty = listPenalty;
		this.totalsituation = totalsituation + "";
		this.messageSource = messageSource;

		setGrid2(detailRetenues + "");
		String sumReport = createProgessGrird3Report(ficheStDto);
		String[] arrDeduction = createDeductionGrid(ficheStDto);
		setGrid3(sumReport);
		setEtatAvancement(etatAvancement);
		setTotaldeduction(arrDeduction[1]);
		contentBoldFont = getFont("CALIBRIB.TTF", 7f);
		contentNormalFont = getFont("CALIBRI.TTF", 7f);

	}

	@Override
	public File render() throws Exception {
		prepareData();
		addAllFichestDto();
		return pdfFile;
	}

	private void prepareData() {
		setGeneraleInformation(buildGeneralInfo(ficheStDto));
		String conditionsparticulieres;
		if (ficheStDto != null) {
			// Add Conditions Particilifers
			conditionsparticulieres = append(ficheStDto.getPaymentMode().getLabel(), ficheStDto.getRg(), ficheStDto.getRefDecenale().getLabel(),
					ficheStDto.getRefDdeAgrement().getLabel(), ficheStDto.getRefDgdPresente().getLabel(), ficheStDto.getDateDgdPresente());
		} else {
			conditionsparticulieres = "";
		}

		setConditionsparticulieres(processNullValue(conditionsparticulieres));

		// Add Prestations
		String prestations = append(ficheStDto.getPrestaPilotage(), ficheStDto.getPrestaAssurances(), ficheStDto.getPrestaProrata(),
				ficheStDto.getPrestaCanto(), ficheStDto.getPrestaBadge(), ficheStDto.getPrestaGrue(), ficheStDto.getPrestaLift(),
				ficheStDto.getPrestaBenne(), ficheStDto.getPrestaNettoyage());
		setPrestations(processNullValue(prestations));

		// Add Informationa Complementaires
		String date_of_market = "null";
		if (ficheStDto.getDateMarcheBase() != null) {
			date_of_market = dateFormat.format(ficheStDto.getDateMarcheBase());
		}
		String informationscomplementaires = ficheStDto.getIdSiTravaux() + Constants.SEPRATE + date_of_market;
		setInformationscomplementaires(informationscomplementaires);
		this.avancementCommentaire = processNullValue(ficheStDto.getAvctCommentaires());
		String cautionFournies = "";
		if (listCautionFournieDto != null && listCautionFournieDto.size() > 0) {
			for (CautionFournieDto cautionFournieDto : listCautionFournieDto) {
				cautionFournies += append(cautionFournieDto.getDate(), cautionFournieDto.getAmount());
			}
		}
		setCautionFournies(processNullValue(cautionFournies));

		// Add Gestion
		addGestionReport(listGestion);
		// addProcessReport(listProcess);
		addDeductionReport(deductionList);
	}

	private void addGestionReport(List<GestionDto> listGestion) {
		List<GestionDto> list = listGestion;
		Map<String, String> listgestion = new HashMap<String, String>();
		Map<String, String> listtotalgestion = new HashMap<String, String>();
		List<String> lstKeys = new ArrayList<String>();
		String key_gestion = "";
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMarche() != null && !lstKeys.contains(list.get(i).getMarche().getLabel())) {
				lstKeys.add(list.get(i).getMarche().getLabel());
				key_gestion += list.get(i).getMarche().getLabel() + Constants.SEPRATE;
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
		double alltotalecart = 0.0;
		double alltotalfdc = 0.0;

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
			double totalfdcSum = 0.0;
			double ecartSum = 0.0;

			for (int j = 0; j < list.size(); j++) {
				gestionDto = list.get(j);
				if (lstKeys.get(i).equalsIgnoreCase(gestionDto.getMarche().getLabel())) {
					String tmp = gestionDto.getMarche().getLabel() + "                                          "
							+ numberFormat2.format(gestionDto.getTraite());
					gestion += append(gestionDto.getDevis(), gestionDto.getStatut().getLabel(), gestionDto.getLabel(), gestionDto.getComment(),
							gestionDto.getAmount(), tmp, gestionDto.getArrete(), gestionDto.getNonArrete(), gestionDto.getProvision(),
							gestionDto.getDevisRefuse(), calculateTotalFdc(gestionDto), gestionDto.getReelActivitive(), gestionDto.getType()
									.getLabel(), gestionDto.getLabel2(), gestionDto.getAmount2(), calculateEcart(gestionDto));
					// Sum Group
					amount += gestionDto.getAmount();
					avenants += gestionDto.getTraite();
					arrete += gestionDto.getArrete();
					nonarrete += gestionDto.getNonArrete();
					provision += gestionDto.getProvision();
					devisrefuse += gestionDto.getDevisRefuse();
					reelactivitive += gestionDto.getReelActivitive();
					amount2 += gestionDto.getAmount2();
					totalfdcSum += calculateTotalFdc(gestionDto);
					ecartSum += calculateEcart(gestionDto);
				}
			}
			allamount += amount;
			allavenants += avenants;
			allarrete += arrete;
			allnonarrete += nonarrete;
			allprovision += provision;
			alldevisrefuse += devisrefuse;
			allreelactivitive += reelactivitive;
			allamount2 += amount2;
			alltotalecart += ecartSum;
			alltotalfdc += totalfdcSum;

			if (gestion != null && gestion.length() > 0) {
				listgestion.put(lstKeys.get(i), gestion);
			}
			totalgestion += append(amount, avenants, arrete, nonarrete, provision, devisrefuse, totalfdcSum, reelactivitive, amount2, ecartSum);
			listtotalgestion.put(lstKeys.get(i), totalgestion);
		}

		if (key_gestion.length() > 0) {
			key_gestion = key_gestion.substring(0, key_gestion.length() - Constants.SEPRATE.length());
		}

		alltotalgestion += append(allamount, allavenants, allarrete, allnonarrete, allprovision, alldevisrefuse, alltotalfdc, allreelactivitive,
				allamount2, alltotalecart);
		double totalObj = 0.0;
		double totalTF = 0.0;
		double totalTS = 0.0;
		double totalRD = 0.0;
		for (GestionDto gestion : listGestion) {
			Integer typeId = gestion.getType().getId();
			double value = gestion.getAmount2();
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

		summary += append(totalObj, totalTF, totalTS, totalRD);
		setSummary(summary);

		budget += append(ficheStDto.getGestBudgetInitial(), ficheStDto.getGestEcartDernierPt(), ficheStDto.getGestDateDernierPt());
		setBudget(budget);

		String gestions = listgestion.toString();
		String totalgestion = listtotalgestion.toString();
		setAlltotalgestion(alltotalgestion);
		Properties props = new Properties();
		try {
			props.load(new StringReader(gestions.substring(1, gestions.length() - 1).replace(", ", "\n")));
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		Map<String, String> mapGestion = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			mapGestion.put((String) e.getKey(), (String) e.getValue());
		}
		props = new Properties();
		try {
			props.load(new StringReader(totalgestion.substring(1, totalgestion.length() - 1).replace(", ", "\n")));
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		Map<String, String> mapTotalGestion = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			mapTotalGestion.put((String) e.getKey(), (String) e.getValue());
		}
		List<String> listTotalGestion = new ArrayList<String>();
		List<String> listGestionStr = new ArrayList<String>();
		String value = "";
		String key[] = key_gestion.split(Constants.SEPRATE);
		if( key_gestion != null && key_gestion.trim() != "" ) {

			for( int i = 0 ; i < key.length ; i++ ) {
				value = mapGestion.get(key[i]);
				if( value != null && value.endsWith(Constants.SEPRATE) ) {
					value = value.substring(0, value.length() - Constants.SEPRATE.length());
				}

				listGestionStr.add(value);
				value = mapTotalGestion.get(key[i]);
				if( value != null && value.endsWith(Constants.SEPRATE) ) {
					value = value.substring(0, value.length() - Constants.SEPRATE.length());
				}

				listTotalGestion.add(value);
			}
		}
		setGestions(listGestionStr);
		setTotalgestions(listTotalGestion);
	}

	private void addDeductionReport(List<DeductionDto> listDeduction) {
		DeductionDto deductionDto = null;
		String deductions = "";
		double amount = 0.0;
		// Grid 1

		if (!AppUtil.isNullOrEmpty(listDeduction)) {
			for (int j = 0; j < listDeduction.size(); j++) {
				deductionDto = listDeduction.get(j);
				deductions += append(deductionDto.getDate(), deductionDto.getCanto(), deductionDto.getBadge(), deductionDto.getGrue(),
						deductionDto.getLift(), deductionDto.getBenne(), deductionDto.getNettoyage(), deductionDto.getAutres(),
						deductionDto.getProrata(), deductionDto.getRefacturations());
			}
		}

		setDeductions(deductions);

		PenaltyDto penaltyDto = null;
		String penaltyStr = "";
		// Grid 1
		if (!AppUtil.isNullOrEmpty(listPenalty)) {
			for (int j = 0; j < listPenalty.size(); j++) {
				penaltyDto = listPenalty.get(j);
				penaltyStr += append(penaltyDto.getDate(), penaltyDto.getAmount(), penaltyDto.getComment());
				amount += penaltyDto.getAmount();
			}
		}

		setPenaltys(penaltyStr);
		setAmount(numberFormat2.format(amount));
		setDeductionComment1(processNullValue(ficheStDto.getAcptCommentaires()));
		setDeductionComment2(processNullValue(ficheStDto.getAcptCommentairesInternes()));
	}

	private double calculateEcart(GestionDto item) {
		if (item == null) {
			return 0.0;
		}
		Double amount2 = item.getAmount2();
		Double totalFdc = calculateTotalFdc(item);
		return amount2 - totalFdc;
	}

	private double calculateTotalFdc(GestionDto item) {
		Double avenant = item.getTraite();
		Double arrete = item.getArrete();
		Double nonArrete = item.getNonArrete();
		Double provision = item.getProvision();
		double calculatedValue = avenant + arrete + nonArrete + provision;
		return calculatedValue;
	}

	private void addAllFichestDto() throws DocumentException {
		// Title
		preData();
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);

		PdfPCell headerCell = new PdfPCell(new Phrase(getMessage("pdf.allfichest.fichedesuividusoustraitant"), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		headerTable.addCell(headerCell);

		addContent(headerTable);

		// Information Generals
		headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		headerCell = new PdfPCell(new Phrase(getMessage("FicheST.generalInformation"), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		headerTable.addCell(headerCell);

		addContent(headerTable);

		// Information Details
		PdfPTable generaleInformationTable = buildGeneralInfoTable();
		addContent(generaleInformationTable);
		lineBreak();

		PdfPCell leftCell, spaceCell1, middleCell, spaceCell2, rightCell;
		PdfPTable parent = new PdfPTable(5);
		parent.setSpacingBefore(0f);
		parent.setWidthPercentage(100f);
		float[] wfParent = { 25, 5,// Libellé risque codification
				25, 5, // Origine de détection
				40 };
		parent.setWidths(wfParent);

		// Add Condition
		leftCell = new PdfPCell(new Phrase(getMessage("FicheST.conditions"), contentBoldFont));
		leftCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(leftCell);

		spaceCell1 = createEmptyCell();
		parent.addCell(spaceCell1);

		middleCell = new PdfPCell(new Phrase(getMessage("FicheST.prestations"), contentBoldFont));
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);

		spaceCell2 = createEmptyCell();
		parent.addCell(spaceCell2);

		rightCell = new PdfPCell(new Phrase(getMessage("FicheST.informationComplementaries"), contentBoldFont));
		rightCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(rightCell);

		// Add content
		PdfPTable conditionTable = addConditions();
		leftCell = new PdfPCell(conditionTable);
		parent.addCell(leftCell);

		spaceCell1 = createEmptyCell();
		parent.addCell(spaceCell1);

		PdfPTable prestations_ = addPrestations();
		middleCell = new PdfPCell(prestations_);
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);

		spaceCell2 = createEmptyCell();
		parent.addCell(spaceCell2);

		PdfPTable caution = addCaution(complementairesInfo, cautionFournies);
		rightCell = new PdfPCell(caution);
		rightCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(rightCell);

		addContent(parent);
		lineBreak();

		addGestionDto(gestions, totalgestions, alltotalgestion, summary, budget);
		lineBreak();
		addProcessDto(processes, totalsituation, commentaire, grid2, grid3, etatAvancement);
		lineBreak();

		addDeductionDto(deductions, prestations, totaldeduction, penaltys, amount, deductionComment1, deductionComment2);
	}

	private PdfPTable addConditions() throws DocumentException {

		PdfPTable conditionsTable = new PdfPTable(2);
		PdfPCell conditionsCell = new PdfPCell();

		// Add Content
		String[] tmp = conditionsparticulieres.split(Constants.SEPRATE);
		String[] messages = { getMessage("pdf.allfichest.payment") + " : ", getMessage("FicheST.rg") + " : ",
				getMessage("pdf.allfichest.decenalenecessaire") + " : ", getMessage("pdf.allfichest.demandedagrement") + " : ",
				getMessage("pdf.allfichest.dgdpresente") + " : ", getMessage("FicheST.date") + " : " };

		for (int i = 0; i < tmp.length; i++) {
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
				PdfPTable table = new PdfPTable(4);
				// conditionsCell = new PdfPCell(new Phrase(messages[4], contentBoldFont));
				conditionsCell = new PdfPCell(table);

				PdfPCell cell = new PdfPCell(new Phrase(messages[4], contentBoldFont));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setColspan(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(tmp[4].equals("null") ? "" : tmp[4], contentNormalFont));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(messages[5], contentBoldFont));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setColspan(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(tmp[5].equals("null") ? "" : tmp[5], contentNormalFont));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				// float[] wf = { 5, 7, 3, 5 };
				// table.setWidths(wf);
				conditionsCell.setBorder(Rectangle.NO_BORDER);
				conditionsTable.addCell(conditionsCell);
				break;
			}
			if (i != 4) {
				conditionsCell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : tmp[i], contentNormalFont));
				conditionsCell.setBorder(Rectangle.NO_BORDER);
				conditionsTable.addCell(conditionsCell);
			}
		}
		float[] wf = { 10, 10 };
		conditionsTable.setWidthPercentage(30f);
		conditionsTable.setWidths(wf);
		return conditionsTable;
	}

	private PdfPTable addPrestations() throws DocumentException {

		PdfPTable prestationsTable = new PdfPTable(2);
		PdfPCell prestationsCell = new PdfPCell();

		// Add Content
		String[] tmp = prestations.split(Constants.SEPRATE);
		String[] messages = { getMessage("pdf.allfichest.prestations") + " : ", getMessage("pdf.allfichest.assurances") + " : ",
				getMessage("pdf.allfichest.prodata") + " : ", getMessage("FicheST.canto") + "  (\u20AC): ",
				getMessage("pdf.allfichest.badge") + " (\u20AC): ", getMessage("pdf.allfichest.grue") + " (\u20AC): ",
				getMessage("pdf.allfichest.lift") + " (\u20AC): ", getMessage("pdf.allfichest.benne") + " (\u20AC): ",
				getMessage("pdf.allfichest.netoyage") + " (\u20AC): " };
		for (int i = 0; i < tmp.length; i++) {
			prestationsCell = new PdfPCell(new Phrase(messages[i], contentBoldFont));
			prestationsCell.setBorder(Rectangle.NO_BORDER);
			prestationsCell.enableBorderSide(Rectangle.LEFT);
			switch (i) {
			case 0:
				prestationsCell.enableBorderSide(Rectangle.TOP);
				break;
			case 8:
				prestationsCell.enableBorderSide(Rectangle.BOTTOM);
				break;
			}
			prestationsTable.addCell(prestationsCell);

			prestationsCell = setCellAlign(tmp[i].equals("null") ? "" : tmp[i], contentNormalFont);
			prestationsCell.setBorder(Rectangle.NO_BORDER);
			if (i == 0)
				prestationsCell.enableBorderSide(Rectangle.TOP);
			if (i == (tmp.length - 1))
				prestationsCell.enableBorderSide(Rectangle.BOTTOM);
			prestationsCell.enableBorderSide(Rectangle.RIGHT);
			prestationsTable.addCell(prestationsCell);
		}
		float[] wf = { 10, 10 };
		prestationsTable.setWidthPercentage(30f);
		prestationsTable.setWidths(wf);
		return prestationsTable;
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

	public void setInformationscomplementaires(String informationscomplementaires) {
		this.complementairesInfo = informationscomplementaires;
	}

	public String getInformationscomplementaires() {
		return complementairesInfo;
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
		return processNullValue(commentaire);
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

	public void preData() {
		setCommentaire(processNullValue(ficheStDto.getAvctCommentaires()));
		ProgressDto progressDto = null;
		String process = "";

		for (int j = 0; j < listProcess.size(); j++) {
			progressDto = listProcess.get(j);
			process += append((j + 1), progressDto.getLabel(), progressDto.getDate(), progressDto.getCumule(), progressDto.getMois(),
					progressDto.getCumule2(), progressDto.getMois2());
		}

		setProcesses(process);
	}

	private String createProgessGrird3Report(FicheStDto ficheSt) {
		List<DeductionDto> listDeduction = ficheSt.getDeductions();
		String srcProgressGrid3Report = "";
		int cantoQuantity = 0;
		int badgeQuantity = 0;
		int grueQuantity = 0;
		int liftQuantity = 0;
		int benneQuantity = 0;
		int nettoyageQuantity = 0;
		double autres = 0.0;
		if (!AppUtil.isNullOrEmpty(listDeduction)) {
			for (DeductionDto deduction : listDeduction) {
				cantoQuantity += deduction.getCanto();
				badgeQuantity += deduction.getBadge();
				grueQuantity += deduction.getGrue();
				liftQuantity += deduction.getLift();
				benneQuantity += deduction.getBenne();
				nettoyageQuantity += deduction.getNettoyage();
				autres += deduction.getAutres();
			}
		}
		double[] column2s = { cantoQuantity, (cantoQuantity * ficheSt.getPrestaCanto()) };
		double[] column3s = { badgeQuantity, (badgeQuantity * ficheSt.getPrestaBadge()) };
		double[] column4s = { grueQuantity, (grueQuantity * ficheSt.getPrestaGrue()) };
		double[] column5s = { liftQuantity, (liftQuantity * ficheSt.getPrestaLift()) };
		double[] column6s = { benneQuantity, (benneQuantity * ficheSt.getPrestaBenne()) };
		double[] column7s = { nettoyageQuantity, (nettoyageQuantity * ficheSt.getPrestaNettoyage()) };
		double[] column8s = { 0, autres };
		for (int i = 0; i < 2; i++) {
			srcProgressGrid3Report += append(column2s[i], column3s[i], column4s[i], column5s[i], column6s[i], column7s[i], column8s[i]);
		}
		return srcProgressGrid3Report;
	}

	String[] createDeductionGrid(FicheStDto ficheSt) {
		// retenues effectues sur le bon d'accomptes
		List<DeductionDto> listDeduction = ficheSt.getDeductions();
		DeductionDto deduction = null;
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
		double refacturations = 0.0;// khong co nhan
		double canto_q = 0.0;
		double badge_q = 0.0;
		double grue_q = 0.0;
		double lift_q = 0.0;
		double benne_q = 0.0;
		double nettoyage_q = 0.0;
		double autres_q = 0.0; // khong co nhan
		double prorata_q = 0.0;
		double refacturations_q = 0.0;
		if (!AppUtil.isNullOrEmpty(listDeduction)) {
			for (int j = 0; j < listDeduction.size(); j++) {
				deduction = listDeduction.get(j);
				canto += deduction.getCanto();
				badge += deduction.getBadge();
				grue += deduction.getGrue();
				lift += deduction.getLift();
				benne += deduction.getBenne();
				nettoyage += deduction.getNettoyage();
				autres += deduction.getAutres();
				prorata += deduction.getProrata();
				refacturations += deduction.getRefacturations();
				canto_q = deduction.getCanto();
				badge_q = deduction.getBadge();
				grue_q = deduction.getGrue();
				lift_q = deduction.getLift();
				benne_q = deduction.getBenne();
				nettoyage_q = deduction.getNettoyage();
				autres_q = deduction.getAutres(); // khong co nhan
				prorata_q = deduction.getProrata();
				refacturations_q = deduction.getRefacturations();//
				srcDeduction += append(deduction.getDate(), canto_q, badge_q, grue_q, lift_q, benne_q, nettoyage_q, autres_q, prorata_q,
						refacturations_q);
			}
		}
		double[] ratioList = new double[9];
		ratioList[6] = 1.0;
		ratioList[8] = 1.0;
		ratioList[0] = ficheSt.getPrestaCanto();
		ratioList[1] = ficheSt.getPrestaBadge();
		ratioList[2] = ficheSt.getPrestaGrue();
		ratioList[3] = ficheSt.getPrestaLift();
		ratioList[4] = ficheSt.getPrestaBenne();
		ratioList[5] = ficheSt.getPrestaNettoyage();
		ratioList[7] = ficheSt.getPrestaProrata();
		canto = canto * ratioList[0];
		badge = badge * ratioList[1];
		grue = grue * ratioList[2];
		lift = lift * ratioList[3];
		benne = benne * ratioList[4];
		nettoyage = nettoyage * ratioList[5];
		// prorata = prorata * ratioList[7];//do not multiply

		srcSumDeduction = append(canto, badge, grue, lift, benne, nettoyage, autres, prorata, refacturations);
		String[] arr = { srcDeduction, srcSumDeduction };
		return arr;
	}
}
