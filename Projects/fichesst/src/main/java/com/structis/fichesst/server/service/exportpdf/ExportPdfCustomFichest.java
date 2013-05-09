package com.structis.fichesst.server.service.exportpdf;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.lowagie.text.DocumentException;
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

	// Grid 1
	private String conditionsparticulieres;

	private String prestations;

	private String informationscomplementaires;

	private String cautionFournies;

	// Gestion
	private List<String> gestions;

	private List<String> totalgestions;

	private String alltotalgestion;

	private String summary;

	private String budget;

	// Progress
	private String processes;

	private String totalsituation;

	private String grid2;

	private String grid3;

	private String etatAvancement;
	private String commentaireAvantcement;

	public String getCommentaireAvantcement() {
		return processNullValue(commentaireAvantcement);
	}

	public void setCommentaireAvantcement(String commentaireAvantcement) {
		this.commentaireAvantcement = commentaireAvantcement;
	}

	// deduction
	private String deductions;

	private String totaldeduction;

	private String penaltys;

	private String amount;

	private String commentaire;

	private String internalCommentaire;

	private String hasGestion;

	public ExportPdfCustomFichest(OutputStream stream, ResourceBundleMessageSource messageSource, Locale locale)
			throws DocumentException, IOException {
		super(locale);
		setStream(stream);
		this.messageSource = messageSource;
		contentBoldFont = getFont("CALIBRIB.TTF", 7f);
		contentNormalFont = getFont("CALIBRI.TTF", 7f);
	}

	@Override
	public File render() throws Exception {
		FicheSt ficheSt = null;
		for (int i = 0; i < listFicheSt.size(); i++) {
			ficheSt = listFicheSt.get(i);
			if(ficheSt.getId() == null || ficheSt.getId() <= 0) {
				continue;
			}
			String generaleInformation = createGeneralInformation(ficheSt);
			String conditionsparticulieres = createCondition(ficheSt);
			String prestations = createPresation(ficheSt);
			String informationscomplementaires = createInformationComplementaires(ficheSt);
			String cautionFournies = createCautionGrid(ficheSt.getCautionFournies());
			// Parameters for gestional report
			List<String> listSrcGestion = new ArrayList<String>();
			List<String> listSumGroupGestion = new ArrayList<String>();
			String alltotalgestion = createGestion(ficheSt.getGestions(), listSrcGestion, listSumGroupGestion);
			String summary = createGestionSummary(ficheSt.getGestions());
			String budget = createBudget(ficheSt);

			// Parameters for report process
			List<String> listSrcProcess = new ArrayList<String>();
			String sumProcesses = createProgress(ficheSt.getProgresses(), listSrcProcess);
			String grid2_report = createDetailDesRetenuesAppliquies(ficheSt.getGestions(), ficheSt.getDeductions(), ficheSt.getPenalties(), ficheSt);
			String grid3_report = createProgessGrird3Report(ficheSt);

			// Deducation
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
			this.etatAvancement = calculateEtatAvancement(ficheSt.getGestions(), ficheSt.getProgresses());
			this.commentaireAvantcement = ficheSt.getAvctCommentaires();
			this.deductions = arrDeduction[0];
			this.totaldeduction = arrDeduction[1];
			this.penaltys = arrPenalty[0];
			this.amount = arrPenalty[1];
			if (ficheSt.getAcptCommentaires() != null) {
				this.commentaire = ficheSt.getAcptCommentaires();
			} else {
				this.commentaire = "";
			}
			if (ficheSt.getAcptCommentairesInternes() != null) {
				this.internalCommentaire = ficheSt.getAcptCommentairesInternes();
			} else {
				this.internalCommentaire = "";
			}

			addAllFichestDto();
			newPage();

		}

		return pdfFile;
	}

	private void addAllFichestDto() throws DocumentException {
		// Title
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
		String arrGeneraleInformation[] = generaleInformation.split(Constants.SEPRATE);
		PdfPTable generaleInformationTable = new PdfPTable(6);
		PdfPCell generaleInformationCell;
		generaleInformationTable.setWidthPercentage(100f);
		String[] messages = { getMessage("FicheST.chantier") + " : ", getMessage("FicheST.lot") + " : ", getMessage("FicheST.foreman") + " : ",
				getMessage("pdf.synthese.societe") + " : ", getMessage("FicheST.lotType") + " : ",
				getMessage("FicheST.montant") + " : " };

		for (int i = 0; i < arrGeneraleInformation.length; i++) {
			generaleInformationCell = new PdfPCell(new Phrase(messages[i], contentBoldFont));
			generaleInformationCell.setBorder(Rectangle.NO_BORDER);
			switch (i) {
			case 0:
				generaleInformationCell.enableBorderSide(Rectangle.TOP);
				generaleInformationCell.enableBorderSide(Rectangle.LEFT);
				break;
			case 1:
			case 2:
				generaleInformationCell.enableBorderSide(Rectangle.TOP);
				break;
			case 3:
				generaleInformationCell.enableBorderSide(Rectangle.BOTTOM);
				generaleInformationCell.enableBorderSide(Rectangle.LEFT);
				break;
			case 4:
			case 5:
				generaleInformationCell.enableBorderSide(Rectangle.BOTTOM);
				break;
			}

			generaleInformationTable.addCell(generaleInformationCell);
			generaleInformationCell = new PdfPCell(new Phrase(arrGeneraleInformation[i].equals("null") ? "" : arrGeneraleInformation[i],
					contentNormalFont));
			if (i == 0 || i == 1 || i == 2) {
				generaleInformationCell.setBorder(Rectangle.NO_BORDER);
				generaleInformationCell.enableBorderSide(Rectangle.TOP);
				if (i == 2)
					generaleInformationCell.enableBorderSide(Rectangle.RIGHT);
				generaleInformationTable.addCell(generaleInformationCell);
			}
			if (i == 3 || i == 4 || i == 5) {
				generaleInformationCell.setBorder(Rectangle.NO_BORDER);
				generaleInformationCell.enableBorderSide(Rectangle.BOTTOM);
				if (i == 5)
					generaleInformationCell.enableBorderSide(Rectangle.RIGHT);
				generaleInformationTable.addCell(generaleInformationCell);
			}
		}
		float[] wfGeneraleInformation = { 3, 6, 3, 6, 7, 6 };
		generaleInformationTable.setWidths(wfGeneraleInformation);
		addContent(generaleInformationTable);
		lineBreak();

		PdfPCell leftCell, spaceCell1, middleCell, spaceCell2, rightCell;
		PdfPTable parent = new PdfPTable(5);
		parent.setSpacingBefore(0f);
		parent.setWidthPercentage(100f);
		float[] wfParent = { 25, 5, 25, 5, 40 };
		parent.setWidths(wfParent);

		// Add Condition
		leftCell = new PdfPCell(new Phrase(getMessage("FicheST.conditions"), contentBoldFont));
		leftCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(leftCell);

		spaceCell1 = new PdfPCell();
		spaceCell1.setBorder(Rectangle.NO_BORDER);
		parent.addCell(spaceCell1);

		middleCell = new PdfPCell(new Phrase(getMessage("FicheST.prestations"), contentBoldFont));
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);

		spaceCell2 = new PdfPCell();
		spaceCell2.setBorder(Rectangle.NO_BORDER);
		parent.addCell(spaceCell2);

		rightCell = new PdfPCell(new Phrase(getMessage("FicheST.informationComplementaries"), contentBoldFont));
		rightCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(rightCell);

		// Add content
		PdfPTable conditionTable = addConditions();
		leftCell = new PdfPCell(conditionTable);
		parent.addCell(leftCell);

		spaceCell1 = new PdfPCell();
		spaceCell1.setBorder(Rectangle.NO_BORDER);
		parent.addCell(spaceCell1);

		PdfPTable prestations_ = addPrestations();
		middleCell = new PdfPCell(prestations_);
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);

		spaceCell2 = new PdfPCell();
		spaceCell2.setBorder(Rectangle.NO_BORDER);
		parent.addCell(spaceCell2);

		PdfPTable caution = addCaution(informationscomplementaires, cautionFournies);
		rightCell = new PdfPCell(caution);
		rightCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(rightCell);
		addContent(parent);
		lineBreak();
		if ("true".equals(hasGestion)) {
			addGestionDto(gestions, totalgestions, alltotalgestion, summary, budget);
			lineBreak();
		}

		addProcessDto(processes, totalsituation, commentaireAvantcement, grid2, grid3, etatAvancement);
		lineBreak();

		if ("true".equals(hasGestion)) {
			addDeductionDto(deductions, prestations, totaldeduction, penaltys, amount, commentaire, internalCommentaire);
		}
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
				getMessage("pdf.allfichest.prodata") + " : ", getMessage("FicheST.canto") + " (\u20AC): ",
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
			
			prestationsCell = new PdfPCell(new Phrase(tmp[i].equals("null") ? "" : tmp[i], contentNormalFont));
			prestationsCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
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

	/**
	 * @return
	 */
	private String createGeneralInformation(FicheSt ficheSt) {
		String chantierName = "";
		try {
			chantierName = ficheSt.getLot().getChantier().getNom();
		} catch (Exception e) {
		}

		String lotName = ficheSt.getLot() != null ? ficheSt.getLot().getName() : "";
		String lotTypeName = ficheSt.getLotType() != null ? ficheSt.getLotType().getName() : "";
		String srcGeneralInformation = append(chantierName, lotName, ficheSt.getIdSiTravaux(), ficheSt.getSociete(), lotTypeName,
				ficheSt.getObjectif());
		return srcGeneralInformation;
	}

	/**
	 * @param ficheSt
	 * @return
	 */
	private String createCondition(FicheSt ficheSt) {
		String conditionsparticulieres = append(ficheSt.getPaymentMode().getLabel(), ficheSt.getRg(), ficheSt.getRefDecenale().getLabel(), ficheSt
				.getRefDdeAgrement().getLabel(), ficheSt.getRefDgdPresente().getLabel(), ficheSt.getDateDgdPresente());
		return conditionsparticulieres;
	}

	/**
	 * @param ficheSt
	 * @return
	 */
	private String createPresation(FicheSt ficheSt) {
		String prestations = append(ficheSt.getPrestaPilotage(), ficheSt.getPrestaAssurances(), ficheSt.getPrestaProrata(), ficheSt.getPrestaCanto(),
				ficheSt.getPrestaBadge(), ficheSt.getPrestaGrue(), ficheSt.getPrestaLift(), ficheSt.getPrestaBenne(), ficheSt.getPrestaNettoyage());
		return prestations;
	}

	/**
	 * @param ficheSt
	 * @return
	 */
	private String createInformationComplementaires(FicheSt ficheSt) {
		String conductorName = ficheSt.getIdSiTravaux() != null ? ficheSt.getIdSiTravaux() : "";
		String informationscomplementaires = append(conductorName, ficheSt.getDateMarcheBase());
		return informationscomplementaires;
	}

	/**
	 * @param listCautionFournie
	 * @return
	 */
	private String createCautionGrid(List<CautionFournie> listCautionFournie) {
		String srcCautionFournies = "";
		for (CautionFournie cautionFournie : listCautionFournie) {
			srcCautionFournies += append(cautionFournie.getDate(), cautionFournie.getAmount());
		}
		return srcCautionFournies;
	}

	/**
	 * Create Key Gestion
	 * 
	 * @param listGestion
	 * @return
	 */
	private List<String> createKeyGestion(List<Gestion> listGestion) {
		Gestion gestion = null;
		List<String> listKeyGestion = new ArrayList<String>();
		for (int i = 0; i < listGestion.size(); i++) {
			gestion = listGestion.get(i);
			if (listGestion.get(i).getMarche() != null && !listKeyGestion.contains(listGestion.get(i).getMarche().getLabel())) {
				listKeyGestion.add(gestion.getMarche().getLabel());
			}
		}
		return listKeyGestion;
	}

	/**
	 * Create Grid Gestion
	 * 
	 * @param listGestion
	 * @param listSrcGestion
	 * @param listSumGroupGestion
	 * @return
	 */
	private String createGestion(List<Gestion> listGestion, List<String> listSrcGestion, List<String> listSumGroupGestion) {
		List<String> listKeyGestion = createKeyGestion(listGestion);
		Gestion gestion = null;
		String sumGestion = "";
		// Calculate Sum Gestion
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
		for (int i = 0; i < listKeyGestion.size(); i++) {
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
			for (int j = 0; j < listGestion.size(); j++) {
				gestion = listGestion.get(j);
				if (gestion.getMarche().getLabel().equalsIgnoreCase(listKeyGestion.get(i))) {
					String tmp = gestion.getMarche().getLabel() + "                                          " + numberFormat2.format(gestion.getTraite());
					String gestionComment = gestion.getComment() != null ? gestion.getComment() : "";
					String statutLabel = gestion.getStatut() != null ? gestion.getStatut().getLabel() : "";
					srcGestion += append(gestion.getDevis(), statutLabel, gestion.getLabel(), gestionComment, gestion.getAmount(), tmp,
							gestion.getArrete(), gestion.getNonArrete(), gestion.getProvision(), gestion.getDevisRefuse(),
							calculateTotalFdc(gestion), gestion.getReelActivitive(), gestion.getType().getLabel(), gestion.getLabel2(),
							gestion.getAmount2(), calculateEcart(gestion));
					// Group
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
			// Calculate Group Sum Gestion
			sumGroupGestion += append(amount, avenants, arrete, nonarrete, provision, devisrefuse, totalfdcSum, reelactivitive, amount2, ecartSum);
			listSumGroupGestion.add(sumGroupGestion);
			// Calculate all gestion

			// Sum gestion
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
		
		sumGestion = append(sumAmount, sumAvenants, sumArrete, sumNonarrete, sumProvision, sumDevisrefuse, sumTotalfdc, sumReelactivitive,
				sumAmount2, sumTotalecart);
		return sumGestion;
	}

	/**
	 * @param listGestion
	 * @return
	 */
	private String createGestionSummary(List<Gestion> listGestion) {
		double totalObj = 0.0;
		double totalTF = 0.0;
		double totalTS = 0.0;
		double totalRD = 0.0;
		for (Gestion gestion : listGestion) {
			Integer typeId = gestion.getType() != null ? gestion.getType().getId() : -1;
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

		String summary = append(totalObj, totalTF, totalTS, totalRD);
		return summary;
	}

	/**
	 * Calucalte TotalFDC
	 * 
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
	 * 
	 * @param item
	 * @return
	 */
	private Double calculateEcart(Gestion item) {
		if (item == null) {
			return 0.0;
		}
		Double amount2 = item.getAmount2();
		Double totalFdc = calculateTotalFdc(item);
		return amount2 - totalFdc;
	}

	/**
	 * @param listProcess
	 * @param listSrcProcess
	 * @return
	 */
	private String createProgress(List<Progress> listProcess, List<String> listSrcProcess) {
		Progress previousProgress = null;
		Progress progress = null;
		String srcProgress = "";
		String totalProcess = "";
		double mois = 0.0;
		double mois2;
		for (int j = 0; j < listProcess.size(); j++) {
			progress = listProcess.get(j);
			if (j > 0) {
				previousProgress = listProcess.get(j - 1);
				mois = progress.getCumule() - previousProgress.getCumule();
				mois2 = progress.getCumule2() - previousProgress.getCumule2();
			} else {
				mois = progress.getCumule();
				mois2 = progress.getCumule2();
			}
			srcProgress += append((j + 1), progress.getLabel(), progress.getDate(), progress.getCumule(), mois, progress.getCumule2(), mois2);
			totalProcess = append(progress.getCumule(), mois, progress.getCumule2(), mois2);
		}
		listSrcProcess.add(srcProgress);
		return totalProcess;
	}

	/**
	 * @param gestionDtoList
	 * @param deductionDtoList
	 * @param penaltyDtoList
	 * @return
	 */
	public String createDetailDesRetenuesAppliquies(List<Gestion> listGestion, List<Deduction> listDeduction, List<Penalty> listPenalty,
			FicheSt ficheSt) {
		double totalAvenants = 0.0;
		for (Gestion gestion : listGestion) {
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
		for (Deduction deduction : listDeduction) {
			cantoQuantity += deduction.getCanto();
			badgeQuantity += deduction.getBadge();
			grueQuantity += deduction.getGrue();
			liftQuantity += deduction.getLift();
			benneQuantity += deduction.getBenne();
			nettoyageQuantity += deduction.getNettoyage();
			totalProrata += deduction.getProrata();
			autres += deduction.getAutres();
		}
		double totalRefacturation = cantoQuantity * ficheSt.getPrestaCanto() + badgeQuantity * ficheSt.getPrestaBadge() + grueQuantity
				* ficheSt.getPrestaGrue() + liftQuantity * ficheSt.getPrestaLift() + benneQuantity * ficheSt.getPrestaBenne() + nettoyageQuantity
				* ficheSt.getPrestaNettoyage();
		double refacturationPercentage = 0.0;
		if (totalAvenants != 0) {
			refacturationPercentage = totalRefacturation / totalAvenants;
		}

		double prorataPercentage = 0.0;
		if (totalAvenants != 0) {
			prorataPercentage = totalProrata / totalAvenants;
		}

		double totalRefacturationDontProrata = totalRefacturation + totalProrata;
		double refacturationDontProrataPercentage = 0.0;
		if (totalAvenants != 0) {
			refacturationDontProrataPercentage = totalRefacturationDontProrata / totalAvenants;
		}

		double refacturationsDontProrataEtAutre = totalRefacturationDontProrata + autres;
		double refacturationsDontProrataEtAutrePercentage = 0.0;
		if (totalAvenants != 0) {
			refacturationsDontProrataEtAutrePercentage = refacturationsDontProrataEtAutre / totalAvenants;
		}

		double totalPenalty = 0.0;
		for (Penalty penalty : listPenalty) {
			totalPenalty += penalty.getAmount();
		}

		double penaltyPercentage = 0.0;
		if (totalAvenants != 0) {
			penaltyPercentage = totalPenalty / totalAvenants;
		}

		double[] column2s = { totalRefacturation, totalProrata, totalRefacturationDontProrata, refacturationsDontProrataEtAutre, totalPenalty };
		double[] column3s = { refacturationPercentage, prorataPercentage, refacturationDontProrataPercentage, refacturationsDontProrataEtAutrePercentage, penaltyPercentage };
		// For report
		String srcDetailDesRetenuesAppliques = "";
		for (int i = 0; i < 5; i++) {
			srcDetailDesRetenuesAppliques += numberFormat2.format(column2s[i]) + Constants.SEPRATE + (numberFormat2.format(column3s[i] * 100)) + "%" + Constants.SEPRATE;
		}
		// For Report
		if (srcDetailDesRetenuesAppliques != null && srcDetailDesRetenuesAppliques.length() > 0) {
			srcDetailDesRetenuesAppliques = srcDetailDesRetenuesAppliques.substring(0,
					srcDetailDesRetenuesAppliques.length() - Constants.SEPRATE.length());
		}
		return srcDetailDesRetenuesAppliques;
	}

	/**
	 * @param deductionDtoList
	 * @return
	 */
	private String createProgessGrird3Report(FicheSt ficheSt) {
		List<Deduction> listDeduction = ficheSt.getDeductions();
		String srcProgressGrid3Report = "";
		int cantoQuantity = 0;
		int badgeQuantity = 0;
		int grueQuantity = 0;
		int liftQuantity = 0;
		int benneQuantity = 0;
		int nettoyageQuantity = 0;
		double autres = 0.0;
		for (Deduction deduction : listDeduction) {
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
		double[] column7s = { nettoyageQuantity, (nettoyageQuantity * ficheSt.getPrestaNettoyage()) };
		double[] column8s = { 0, autres };
		for (int i = 0; i < 2; i++) {
			srcProgressGrid3Report += append(column2s[i], column3s[i], column4s[i], column5s[i], column6s[i], column7s[i], column8s[i]);

		}
		return srcProgressGrid3Report;
	}

	/**
	 * @param listDeduction
	 * @return
	 */
	private String[] createDeductionGrid(FicheSt ficheSt) {
		// retenues effectues sur le bon d'accomptes
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
		double refacturations = 0.0;// khong co nhan
		double canto_q = 0.0;
		double badge_q = 0.0;
		double grue_q = 0.0;
		double lift_q = 0.0;
		double benne_q = 0.0;
		double nettoyage_q = 0.0;
		double autres_q = 0.0; // khong co nhan
		double prorata_q = 0.0;
		double refacturations_q = 0.0;//
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
			srcDeduction += append(deduction.getDate(), canto_q, badge_q, grue_q, lift_q, benne_q, nettoyage_q, autres_q, prorata_q, refacturations_q);
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
//		prorata = prorata * ratioList[7];//do not multiply
		srcSumDeduction = append(canto, badge, grue, lift, benne, nettoyage, autres, prorata, refacturations);
		String[] arr = { srcDeduction, srcSumDeduction };
		return arr;
	}
	
	/**
	 * @param listPenalty
	 * @return
	 */
	private String[] createPenalite(List<Penalty> listPenalty) {
		Penalty penalty = null;
		String srcPenalty = "";
		double amount = 0.0;
		for (int j = 0; j < listPenalty.size(); j++) {
			penalty = listPenalty.get(j);
			srcPenalty += append(penalty.getDate(), penalty.getAmount(), penalty.getComment());
			amount += penalty.getAmount();
		}
		String[] arr = { srcPenalty, numberFormat2.format(amount)};
		return arr;
	}

	public void setListFicheSt(List<FicheSt> listFicheSt) {
		this.listFicheSt = listFicheSt;
	}

	public List<FicheSt> getListFicheSt() {
		return listFicheSt;
	}

	private String calculateEtatAvancement(List<Gestion> listGestion, List<Progress> listProcess) {
		double cumuleSum = 0.0;
		double totalTraite = 0.0;
		double etatAvancement = 0.0;
		if (listGestion != null && listGestion.size() > 0) {
			Gestion gestion = null;
			for (int i = 0; i < listGestion.size(); i++) {
				gestion = listGestion.get(i);
				totalTraite += gestion.getTraite();
			}
		}
		if (listProcess != null && listProcess.size() > 0) {
			Progress progress = null;
			for (int i = 0; i < listProcess.size(); i++) {
				progress = listProcess.get(i);
				cumuleSum = progress.getCumule();
			}
		}

		if (totalTraite == 0) {
			etatAvancement = 0.0;
		} else {
			etatAvancement = (cumuleSum / totalTraite) * 100;
		}
		return numberFormat2.format(etatAvancement);
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
