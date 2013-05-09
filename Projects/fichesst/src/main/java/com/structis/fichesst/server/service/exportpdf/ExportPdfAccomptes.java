package com.structis.fichesst.server.service.exportpdf;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.structis.fichesst.server.util.AppUtil;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.util.Constants;

public class ExportPdfAccomptes extends ExportPdfManager {

	private String deductions;

	private String prestation;

	private String totaldeduction;

	private String penaltys;

	private Double amount;

	private String information;

	private String commentaire;

	private String internalCommentaire;

	private final FicheStDto ficheStDto;

	private final List<DeductionDto> listDeduction;

	private final List<PenaltyDto> listPenalty;

	public ExportPdfAccomptes(ResourceBundleMessageSource messageSource, Locale locale, FicheStDto ficheSt, List<DeductionDto> listDeduction,
			List<PenaltyDto> listPenalty) throws DocumentException, IOException {
		super(locale);

		this.ficheStDto = ficheSt;
		this.listDeduction = listDeduction;
		this.listPenalty = listPenalty;
		this.messageSource = messageSource;
	}

	@Override
	public File render() throws Exception {
		prepareData();
		addDeductionDto();
		return pdfFile;
	}

	private void prepareData() {
		DeductionDto deductionDto = null;
		String deductions = "";
		double amount = 0.0;
		String information = "";
		// Grid 1
		double canto = 0.0;
		double badge = 0.0;
		double grue = 0.0;
		double lift = 0.0;
		double benne = 0.0;
		double nettoyage = 0.0;
		double autres = 0.0; // khong co nhan (X 1)
		double prorata = 0.0;
		double refacturations = 0.0;// khong co nhan (X 1)
		if (!AppUtil.isNullOrEmpty(listDeduction)) {
			for (int j = 0; j < listDeduction.size(); j++) {
				deductionDto = listDeduction.get(j);

				deductions += append(deductionDto.getDate(), deductionDto.getCanto(), deductionDto.getBadge(), deductionDto.getGrue(),
						deductionDto.getLift(), deductionDto.getBenne(), deductionDto.getNettoyage(), deductionDto.getAutres(),
						deductionDto.getProrata(), deductionDto.getRefacturations());

				canto += deductionDto.getCanto();
				badge += deductionDto.getBadge();
				grue += deductionDto.getGrue();
				lift += deductionDto.getLift();
				benne += deductionDto.getBenne();
				nettoyage += deductionDto.getNettoyage();
				autres += deductionDto.getAutres();
				prorata += deductionDto.getProrata();
				refacturations += deductionDto.getRefacturations();
			}
		}
		Double[] ratioList = new Double[9];
		ratioList[6] = 1.0;
		ratioList[8] = 1.0;
		ratioList[0] = ficheStDto.getPrestaCanto();
		ratioList[1] = ficheStDto.getPrestaBadge();
		ratioList[2] = ficheStDto.getPrestaGrue();
		ratioList[3] = ficheStDto.getPrestaLift();
		ratioList[4] = ficheStDto.getPrestaBenne();
		ratioList[5] = ficheStDto.getPrestaNettoyage();
		ratioList[7] = ficheStDto.getPrestaProrata();

		canto = canto * ratioList[0];
		badge = badge * ratioList[1];
		grue = grue * ratioList[2];
		lift = lift * ratioList[3];
		benne = benne * ratioList[4];
		nettoyage = nettoyage * ratioList[5];
		autres = autres * ratioList[6];
		// prorata = prorata * ratioList[7]; //do not multiply
		refacturations = refacturations * ratioList[8];
		String prestations_ = append(ficheStDto.getPrestaPilotage(), ficheStDto.getPrestaAssurances(), ficheStDto.getPrestaProrata(),
				ficheStDto.getPrestaCanto(), ficheStDto.getPrestaBadge(), ficheStDto.getPrestaGrue(), ficheStDto.getPrestaLift(),
				ficheStDto.getPrestaBenne(), ficheStDto.getPrestaNettoyage());

		// prestation = append((Object[]) ratioList);
		information += ficheStDto.getLot().getChantier().getNom() + Constants.SEPRATE + ficheStDto.getIdSiTravaux() + Constants.SEPRATE
				+ ficheStDto.getSociete();
		setInformation(information);
		super.generaleInformation = ExportPdfManager.buildGeneralInfo(ficheStDto);
		totaldeduction = append(canto, badge, grue, lift, benne, nettoyage, autres, prorata, refacturations);
		setTotaldeduction(totaldeduction);
		PenaltyDto penaltyDto = null;
		String penaltys = "";
		// Grid 1
		if (!AppUtil.isNullOrEmpty(listPenalty)) {
			for (int j = 0; j < listPenalty.size(); j++) {
				penaltyDto = listPenalty.get(j);
				penaltys += append(penaltyDto.getDate(), penaltyDto.getAmount(), penaltyDto.getComment());
				amount += penaltyDto.getAmount();
			}
		}

		setDeductions(deductions);
		setPenaltys(penaltys);
		setAmount(amount);
		setCommentaire(ficheStDto.getAcptCommentaires());
		setInternalCommentaire(ficheStDto.getAcptCommentairesInternes());
		setPrestation(prestations_);
	}

	private void addDeductionDto() throws DocumentException {
		// Title
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);

		PdfPCell headerCell = new PdfPCell(new Phrase(getMessage("pdf.process.accomptes.suivideavancementsdusoustraitant"), contentBoldFont));
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
		PdfPTable generaleInfoTable = buildGeneralInfoTable();
		addContent(generaleInfoTable);
		lineBreak();

		headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100f);
		headerTable.setSpacingAfter(15f);
		headerCell = new PdfPCell(new Phrase("SUIVI DES ACOMPTES", contentBoldFont));
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(headerCell);
		headerTable.setSpacingAfter(0f);
		addContent(headerTable);

		PdfPTable informationTable = addInformationTable();
		addContent(informationTable);
		lineBreak();

		// Add deduction
		PdfPTable deductionTable = addDeductionTable(prestation, deductions);
		addContent(deductionTable);
		// Add total deduction
		PdfPTable totalTable = addTotalDeduction(totaldeduction);
		addContent(totalTable);
		lineBreak();

		PdfPCell leftCell, middleCell, rightCell;
		PdfPTable parent = new PdfPTable(3);
		parent.setSpacingBefore(0f);
		parent.setWidthPercentage(100f);
		float[] wfParent = { 70, 5, 25 };
		parent.setWidths(wfParent);
		// Add Penalties
		leftCell = new PdfPCell(new Phrase(getMessage("eport.pdf.penalites"), contentBoldFont));
		leftCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(leftCell);

		middleCell = new PdfPCell();
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);

		rightCell = new PdfPCell(new Phrase("Commentaires", contentBoldFont));
		rightCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(rightCell);

		PdfPTable penaltyTable = addPenaltiesTable(penaltys);
		leftCell = new PdfPCell(penaltyTable);
		parent.addCell(leftCell);

		middleCell = new PdfPCell();
		middleCell.setBorder(Rectangle.NO_BORDER);
		parent.addCell(middleCell);

		PdfPTable commentairePdfPTable = addDeductionCommantaire(commentaire, internalCommentaire);
		rightCell = new PdfPCell(commentairePdfPTable);
		parent.addCell(rightCell);

		leftCell = new PdfPCell(addAmountTable(numberFormat2.format(amount)));
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
	 * @return
	 * @throws DocumentException
	 */
	private PdfPTable addInformationTable() throws DocumentException {

		PdfPTable informationTable = new PdfPTable(6);
		PdfPCell informationCell = new PdfPCell();

		// Add Content
		String[] tmp = information.split(Constants.SEPRATE);
		String[] messages = { getMessage("FicheST.chantier") + " : ", getMessage("FicheST.Responsable") + " : ",
				getMessage("pdf.synthese.societe") + " : " };
		for (int i = 0; i < tmp.length; i++) {
			informationCell = new PdfPCell(new Phrase(messages[i], contentBoldFont));
			informationCell.setBorder(Rectangle.NO_BORDER);
			informationTable.addCell(informationCell);

			informationCell = new PdfPCell(new Phrase(tmp[i], contentNormalFont));
			informationCell.setBorder(Rectangle.NO_BORDER);
			informationTable.addCell(informationCell);
		}
		float[] wf = { 3, 10, 5, 10, 2, 10 };
		informationTable.setWidthPercentage(100f);
		informationTable.setWidths(wf);
		return informationTable;
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

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {
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

	public void setInternalCommentaire(String internalCommentaire) {
		this.internalCommentaire = internalCommentaire;
	}

	public String getInternalCommentaire() {
		return processNullValue(internalCommentaire);
	}

	public void setPrestation(String prestation) {
		this.prestation = prestation;
	}

	public String getPrestation() {
		return processNullValue(prestation);
	}
}
