package com.structis.fichesst.server.service.exportpdf;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.ProgressDto;
import com.structis.fichesst.shared.util.Constants;

public class ExportPdfProgresses extends ExportPdfManager {

	private String processes;

	private String totalsituation;

	private String grid2;

	private String grid3;

	private String commentaire;

	private String etatAvancement;

	private final FicheStDto ficheStDto;

	private final List<ProgressDto> progressDtoList;

	private final List<GestionDto> gestionDtoList;

	private final List<DeductionDto> deductionDtoList;

	private final List<PenaltyDto> penaltyDtoList;

	public ExportPdfProgresses(ResourceBundleMessageSource messageSource, Locale locale, FicheStDto ficheStDto, List<ProgressDto> progressDtoList,
			List<GestionDto> gestionDtoList, List<DeductionDto> deductionDtoList, List<PenaltyDto> penaltyDtoList) throws DocumentException,
			IOException {
		super(locale);
		this.ficheStDto = ficheStDto;
		this.progressDtoList = progressDtoList;
		this.gestionDtoList = gestionDtoList;
		this.deductionDtoList = deductionDtoList;
		this.penaltyDtoList = penaltyDtoList;
		this.messageSource = messageSource;
	}

	@Override
	public File render() throws Exception {
		prepareData();
		addProcessDto();
		return pdfFile;
	}

	private void prepareData() {
		ProgressDto progressDto = null;
		String process = "";
		String totalsituation = "";

		String cumule = "";

		String mois;
		double sumMois = 0.0;
		double sumMois2 = 0.0;

		for (int j = 0; j < progressDtoList.size(); j++) {

			progressDto = progressDtoList.get(j);
			process += append((j + 1), progressDto.getLabel(), progressDto.getDate(), progressDto.getCumule(), progressDto.getMois(),
					progressDto.getCumule2(), progressDto.getMois2());
			sumMois += progressDto.getMois();
			sumMois2 += progressDto.getMois2();
			cumule = progressDto.getCumule() + Constants.SEPRATE + progressDto.getCumule2();
		}
		mois = sumMois + Constants.SEPRATE + sumMois2;
		setProcesses(processNullValue(process));
		setGeneraleInformation(processNullValue(buildGeneralInfo(ficheStDto)));

		if ((cumule != null && cumule.length() > 0) && (mois != null && mois.length() > 0)) {
			String[] arrCumule = cumule.split(Constants.SEPRATE);
			String[] arrMois = mois.split(Constants.SEPRATE);
			totalsituation = numberFormat2.format(new Double(arrCumule[0])) + Constants.SEPRATE + numberFormat2.format(new Double(arrMois[0]))
					+ Constants.SEPRATE + numberFormat2.format(new Double(arrCumule[1])) + Constants.SEPRATE
					+ numberFormat2.format(new Double(arrMois[1]));
			setTotalsituation(processNullValue(totalsituation));
		}
		// Grid 2
		double totalAvenants = 0.0;
		if (gestionDtoList != null) {
			for (GestionDto gestionDto : gestionDtoList) {
				totalAvenants += gestionDto.getTraite();
			}
		}
		int cantoQuantity = 0;
		int badgeQuantity = 0;
		int grueQuantity = 0;
		int liftQuantity = 0;
		int benneQuantity = 0;
		int nettoyageQuantity = 0;
		double totalProrata = 0.0;
		double autres = 0.0;
		if (deductionDtoList != null) {
			for (DeductionDto deductionDto : deductionDtoList) {
				cantoQuantity += deductionDto.getCanto();
				badgeQuantity += deductionDto.getBadge();
				grueQuantity += deductionDto.getGrue();
				liftQuantity += deductionDto.getLift();
				benneQuantity += deductionDto.getBenne();
				nettoyageQuantity += deductionDto.getNettoyage();
				totalProrata += deductionDto.getProrata();
				autres += deductionDto.getAutres();
			}
		}
		double totalRefacturation = cantoQuantity * ficheStDto.getPrestaCanto() + badgeQuantity * ficheStDto.getPrestaBadge() + grueQuantity
				* ficheStDto.getPrestaGrue() + liftQuantity * ficheStDto.getPrestaLift() + benneQuantity * ficheStDto.getPrestaBenne()
				+ nettoyageQuantity * ficheStDto.getPrestaNettoyage();
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
		if (penaltyDtoList != null) {
			for (PenaltyDto penaltyDto : penaltyDtoList) {
				totalPenalty += penaltyDto.getAmount();
			}
		}
		double penaltyPercentage = 0.0;
		if (totalAvenants != 0) {
			penaltyPercentage = totalPenalty / totalAvenants;
		}

		double[] retenuesColumn2s = { totalRefacturation, totalProrata, totalRefacturationDontProrata, refacturationsDontProrataEtAutre, totalPenalty };
		double[] retenuesColumn3s = { refacturationPercentage, prorataPercentage, refacturationDontProrataPercentage,
				refacturationsDontProrataEtAutrePercentage, penaltyPercentage };

		String detail_des_retenues_appliques = "";
		for (int i = 0; i < 5; i++) {
			detail_des_retenues_appliques += numberFormat2.format(retenuesColumn2s[i]) + Constants.SEPRATE
					+ (numberFormat2.format(retenuesColumn3s[i] * 100) + " %") + Constants.SEPRATE;
		}

		setGrid2(processNullValue(detail_des_retenues_appliques));

		double cantoValue = 0.0;
		double badgeValue = 0.0;
		double grueValue = 0.0;
		double liftValue = 0.0;
		double benneValue = 0.0;
		double netoyageValue = 0.0;
		cantoValue = ficheStDto.getPrestaCanto();
		badgeValue = ficheStDto.getPrestaBadge();
		grueValue = ficheStDto.getPrestaGrue();
		liftValue = ficheStDto.getPrestaLift();
		benneValue = ficheStDto.getPrestaBenne();
		netoyageValue = ficheStDto.getPrestaNettoyage();
		double[] column2s = { cantoQuantity, (cantoQuantity * cantoValue) };
		double[] column3s = { badgeQuantity, (badgeQuantity * badgeValue) };
		double[] column4s = { grueQuantity, (grueQuantity * grueValue) };
		double[] column5s = { liftQuantity, (liftQuantity * liftValue) };
		double[] column6s = { benneQuantity, (benneQuantity * benneValue) };
		double[] column7s = { nettoyageQuantity, (nettoyageQuantity * netoyageValue) };
		double[] column8s = { 0, autres };
		String grid_3_report = "";
		for (int i = 0; i < 2; i++) {
			grid_3_report += append(column2s[i], column3s[i], column4s[i], column5s[i], column6s[i], column7s[i], column8s[i]);
		}
		setGrid3(processNullValue(grid_3_report));
		this.etatAvancement = calculateEtatAvancement(gestionDtoList, progressDtoList);
		Logger logger = Logger.getLogger("fichesst");
		logger.log(Level.SEVERE, "etatavantcement" + etatAvancement);

		setEtatAvancement(etatAvancement);
		setCommentaire(processNullValue(ficheStDto.getAvctCommentaires()));
	}

	private void addProcessDto() throws DocumentException {
		// Title
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);

		PdfPCell headerCell = new PdfPCell(new Phrase(getMessage("pdf.process.titlebox.suividesavancementssusoustraitant"), contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		headerTable.addCell(headerCell);
		addContent(headerTable);

		// Information Details
		PdfPTable generaleInformationTable = buildGeneralInfoTable();
		addContent(generaleInformationTable);
		lineBreak();

		PdfPTable headerTable_ = new PdfPTable(1);
		headerTable_.setWidthPercentage(100f);
		headerCell = new PdfPCell(new Phrase("SUIVI DES AVANCEMENTS", contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		headerTable.setSpacingAfter(0f);
		addContent(headerTable);

		headerTable = new PdfPTable(2);
		headerTable.setWidthPercentage(40f);
		headerTable.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
		headerTable.setSpacingAfter(15f);
		float[] wf1 = { 5, 10 };
		headerCell = new PdfPCell(new Phrase(getMessage("pdf.process.titlebox.etatdavancement") + ": ", contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);

		headerCell = new PdfPCell(new Phrase(etatAvancement + " " + getMessage("pdf.process.titlebox.percentavancementmarche"), contentNormalFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		headerTable.setSpacingAfter(0f);
		headerTable.setWidths(wf1);
		addContent(headerTable);

		PdfPTable parent = new PdfPTable(3);
		parent.setSpacingBefore(0f);
		parent.setWidthPercentage(100f);
		float[] wfParent = { 70, 5, 25 };
		parent.setWidths(wfParent);
		PdfPCell cellLeft, cellMiddle, cellRight;

		// ///////
		// Add Group Header
		PdfPCell[] groupHeaderCell = new PdfPCell[3];
		groupHeaderCell[0] = createEmptyCell();
		groupHeaderCell[0].setBorder(Rectangle.NO_BORDER);
		groupHeaderCell[1] = createHeaderCell("AVANCEMENT");
		groupHeaderCell[2] = createHeaderCell("RETENUES");

		float[] wfGroupHeader = { 25, 20, 20 };
		for (PdfPCell pdfPCell : groupHeaderCell) {
			pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		PdfPTable groupHeaderTable = new PdfPTable(3);
		groupHeaderTable.setWidthPercentage(100f);
		groupHeaderTable.setWidths(wfGroupHeader);
		addCellsToTable(groupHeaderTable, groupHeaderCell);

		cellLeft = new PdfPCell(groupHeaderTable);
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);

		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);

		cellRight = new PdfPCell(new Phrase("Commentaires / Informations : ", contentBoldFont));
		cellRight.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellRight);

		// Header
		PdfPCell[] processTableHeader = new PdfPCell[7];

		String[] resourceKeys = { "FicheST.number", "pdf.process.titlebox.libelle", "pdf.process.titlebox.date", "FicheST.cumule", "FicheST.mois",
				"FicheST.cumule2", "FicheST.mois" };

		for (int i = 0; i < processTableHeader.length; i++) {
			processTableHeader[i] = createHeaderCell(getMessage(resourceKeys[i]));
			if (i <= 2) {
				processTableHeader[i].setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			} else {
				processTableHeader[i].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			}
		}

		// End
		PdfPTable processDtoTable = new PdfPTable(7);
		processDtoTable.setWidthPercentage(100f);
		addCellsToTable(processDtoTable, processTableHeader);

		float[] wf = { 5, // No 3
				10, // Libellé risque codification
				10, // Origine de détection
				10, // CR 3
				10, // Niveau de risque 5
				10, 10 };
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

		processDtoTable = new PdfPTable(7);
		if (processes != null && processes.length() > 0) {
			String arr[] = processes.split(Constants.SEPRATE);
			for (int i = 0; i < arr.length; i++) {
				PdfPCell cell = createContentCell(arr[i]);
				if (i % 7 <= 2) {
					cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				} else {
					cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
				}
				processDtoTable.addCell(cell);
			}
			processDtoTable.setWidthPercentage(70f);
			processDtoTable.setWidths(wf);

			cellLeft = new PdfPCell(processDtoTable);
			parent.addCell(cellLeft);

			cellMiddle = new PdfPCell();
			cellMiddle.setBorder(Rectangle.NO_BORDER);
			parent.addCell(cellMiddle);

			PdfPTable commanttaire = addProcessCommantaire(commentaire);
			commanttaire.setWidthPercentage(70f);

			cellRight = new PdfPCell(commanttaire);
			cellRight.setBorder(Rectangle.NO_BORDER);
			cellRight.enableBorderSide(Rectangle.RIGHT);
			cellRight.enableBorderSide(Rectangle.LEFT);
			parent.addCell(cellRight);
		}
		// Total Situation
		processDtoTable = addtotalsituation(processNullValue(totalsituation));
		processDtoTable.setWidthPercentage(70f);
		processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		cellLeft = new PdfPCell(processDtoTable);
		parent.addCell(cellLeft);

		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		// Space
		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);
		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);
		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);
		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);
		// End

		cellLeft = new PdfPCell(new Phrase("DETAIL DES RETENUES APPLIQUES", contentBoldFont));
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);

		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		processDtoTable = addGrid2(grid2);
		processDtoTable.setWidthPercentage(70f);
		processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		cellLeft = new PdfPCell(processDtoTable);
		cellLeft.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellLeft);

		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);

		cellMiddle = new PdfPCell();
		cellMiddle.setBorder(Rectangle.NO_BORDER);
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);
		// Add Sapce
		// Space
		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);

		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);
		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);

		cellLeft = createEmptyCell();
		parent.addCell(cellLeft);
		cellMiddle = createEmptyCell();
		parent.addCell(cellMiddle);
		cellRight = createEmptyCell();
		cellRight.enableBorderSide(Rectangle.RIGHT);
		cellRight.enableBorderSide(Rectangle.LEFT);
		parent.addCell(cellRight);
		// End

		// End
		processDtoTable = addGrid3(grid3);
		processDtoTable.setWidthPercentage(70f);
		processDtoTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
		cellLeft = new PdfPCell(processDtoTable);
		cellLeft.setBorder(Rectangle.NO_BORDER);
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

	public void setProcesses(String processes) {
		this.processes = processes;
	}

	public String getProcesses() {
		return processNullValue(processes);
	}

	public void setTotalsituation(String totalsituation) {
		this.totalsituation = totalsituation;
	}

	public String getTotalsituation() {
		return processNullValue(totalsituation);
	}

	public void setGrid2(String grid2) {
		this.grid2 = grid2;
	}

	public String getGrid2() {
		return processNullValue(grid2);
	}

	public void setGrid3(String grid3) {
		this.grid3 = grid3;
	}

	public String getGrid3() {
		return processNullValue(grid3);
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
		return processNullValue(commentaire);
	}

	public String createProgress(List<ProgressDto> listProcess, List<String> listSrcProcess) {
		ProgressDto previousProgress = null;
		ProgressDto progress = null;
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

	public String calculateEtatAvancement(List<GestionDto> listGestion, List<ProgressDto> listProcess) {
		double cumuleSum = 0.0;
		double totalTraite = 0.0;
		double etatAvancement = 0.0;
		if (listGestion != null && listGestion.size() > 0) {
			GestionDto gestion = null;
			for (int i = 0; i < listGestion.size(); i++) {
				gestion = listGestion.get(i);
				totalTraite += gestion.getTraite();
			}
		}
		if (listProcess != null && listProcess.size() > 0) {
			ProgressDto progress = null;
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
}
