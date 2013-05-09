package com.structis.fichesst.server.service.exportpdf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

import com.lowagie.text.DocumentException;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.TransfertPpSummaryDto;
import com.structis.fichesst.shared.util.Constants;

public class ExportPdfSynthese extends ExportPdfManager {

	private String informationChantier;
	private String informationProrata = "";
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
	List<FicheStDto> ficheStList1;
	List<FicheStDto> ficheStList2;
	List<FicheStDto> ficheStList4;
	ChantierModel chantier;
	List<TransfertPpSummaryDto> listTransfertPpGrid;

	public ExportPdfSynthese(ChantierModel chantier, List<FicheStDto> ficheStList1, List<FicheStDto> ficheStList2, List<FicheStDto> ficheStList4,
			List<TransfertPpSummaryDto> listTransfertPpGrid, ResourceBundleMessageSource messageSource, Locale clientLocale)
			throws DocumentException, IOException {
		super(clientLocale);
		this.chantier = chantier;
		this.ficheStList1 = ficheStList1;
		this.ficheStList2 = ficheStList2;
		this.ficheStList4 = ficheStList4;
		this.listTransfertPpGrid = listTransfertPpGrid;
		this.messageSource = messageSource;
		contentBoldGrayFont.setSize(5);
		contentItalicFont.setSize(5);
		contentBoldFont.setSize(2.5f);
		contentNormalFont.setSize(2.5f);
	}

	@Override
	public File render() throws Exception {
		prepareData();
		// INFORMATION CHANTIER
		addInformationChantier(informationChantier);
		addInformationProrata(informationProrata);

		// SYNTHESE DES FICHES ST
		addSyntheseDesFichesST(grid1, sumTotalGrid1, totalRowGrid1);
		// addSyntheseDesFichesST(grid2, sumTotalGrid2, null);
		addSyntheseDesFichesST2(grid2, sumTotalGrid2);

		lineBreak();

		addSyntheseGrid3(grid3);
		addSumGrid3(sumGrid3);

		lineBreak();

		addGrid4();
		addSumGrid4(sumGrid4);

		return pdfFile;
	}

	private void prepareData() {
		// thuc thi nhu export pdf synthese servlet
		informationChantier = chantier.getNom();
		informationProrata = chantier.getProrataTheorique().toString();
		// Synthese des fiches st
		// + Grid 1
		List<String> listSrcGrid1 = new ArrayList<String>();
		List<String> listSrcTotalGroupGrid1 = new ArrayList<String>();
		String totalSumGrid1 = createGridReport(ficheStList1, listSrcGrid1, listSrcTotalGroupGrid1);
		this.grid1 = listSrcGrid1;
		this.totalRowGrid1 = listSrcTotalGroupGrid1;
		this.sumTotalGrid1 = totalSumGrid1;
		// +grid2
		List<String> listSrcGrid2 = new ArrayList<String>();
		List<String> listSrcTotalGroupGrid2 = new ArrayList<String>();
		String totalSumGrid2 = createGridReport(ficheStList2, listSrcGrid2, listSrcTotalGroupGrid2);
		this.grid2 = listSrcGrid2;
		this.totalRowGrid2 = listSrcTotalGroupGrid2;
		this.sumTotalGrid2 = totalSumGrid2;
		// Synthese des transpert pp
		TransfertPpSummaryDto transfertPpSummaryDto = null;
		String grid3 = "";
		double sumColumnObjectif = 0.0;
		double sumColumnObj = 0.0;
		double sumColumnTransfert = 0.0;
		double sumColumnRd = 0.0;
		double sumColumnTs = 0.0;
		double sumRow = 0.0;
		double sumSumRow = 0.0;
		for (int i = 0; i < listTransfertPpGrid.size(); i++) {
			transfertPpSummaryDto = listTransfertPpGrid.get(i);
			sumColumnObjectif += transfertPpSummaryDto.getObjective();
			sumColumnObj += transfertPpSummaryDto.getObj();
			sumColumnTransfert += transfertPpSummaryDto.getDevers();
			sumColumnRd += transfertPpSummaryDto.getRd();
			sumColumnTs += transfertPpSummaryDto.getTs();
			sumRow = transfertPpSummaryDto.getObj() + transfertPpSummaryDto.getDevers() + transfertPpSummaryDto.getRd()
					+ transfertPpSummaryDto.getTs();
			sumSumRow += sumRow;
			grid3 += createTotalSumReport(transfertPpSummaryDto.getLabel(), transfertPpSummaryDto.getObjective(), transfertPpSummaryDto.getObj(),
					transfertPpSummaryDto.getDevers(), transfertPpSummaryDto.getRd(), transfertPpSummaryDto.getTs(), sumRow);

		}
		this.sumGrid3 = createTotalSumReport("Total PP", sumColumnObjectif, sumColumnObj, sumColumnTransfert, sumColumnRd, sumColumnTs, sumSumRow);
		this.grid3 = grid3;
		// ficheStSummaryGrid
		// +Grid 4
		String[] arr = createGridReportSummary(ficheStList4);
		this.grid4 = arr[0];
		this.sumGrid4 = arr[1];
	}

	public List<FicheStDto> getFicheStList2() {
		return ficheStList2;
	}

	public void setFicheStList2(List<FicheStDto> ficheStList2) {
		this.ficheStList2 = ficheStList2;
	}

	public List<FicheStDto> getFicheStList4() {
		return ficheStList4;
	}

	public void setFicheStList4(List<FicheStDto> ficheStList4) {
		this.ficheStList4 = ficheStList4;
	}

	public List<TransfertPpSummaryDto> getListTransfertPpGrid() {
		return listTransfertPpGrid;
	}

	public void setListTransfertPpGrid(List<TransfertPpSummaryDto> listTransfertPpGrid) {
		this.listTransfertPpGrid = listTransfertPpGrid;
	}

	public List<FicheStDto> getFicheStList1() {
		return ficheStList1;
	}

	public void setFicheStList1(List<FicheStDto> ficheStList1) {
		this.ficheStList1 = ficheStList1;
	}

	public ChantierModel getChantier() {
		return chantier;
	}

	public void setChantier(ChantierModel chantier) {
		this.chantier = chantier;
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

	public String createTotalSumReport(String name, Double... params) {
		String totalSumGrid = name + Constants.SEPRATE;
		for (Double param : params) {
			if (param != null) {
				totalSumGrid += numberFormat2.format(param);
			} else
				totalSumGrid += "null";
			totalSumGrid += Constants.SEPRATE;
		}
		return totalSumGrid;
	}

	private List<String> createKeyGrid1(List<FicheStDto> listFicheSt) {
		List<FicheStDto> fichestGrid1 = listFicheSt;
		List<String> lstKeyGrid1 = new ArrayList<String>();
		for (int i = 0; i < fichestGrid1.size(); i++) {
			if (fichestGrid1.get(i).getLotType() != null && !lstKeyGrid1.contains(fichestGrid1.get(i).getLotType().getName())) {
				lstKeyGrid1.add(fichestGrid1.get(i).getLotType().getName());
			}
		}
		return lstKeyGrid1;
	}

	private String createGridReport(List<FicheStDto> grid, List<String> listSrcGrid, List<String> listSrcTotalGroupGrid) {

		List<String> lstKeyGrid = createKeyGrid1(grid);
		FicheStDto ficheSt = null;
		double sumtotalObjectif = 0.0;
		double sumtotalObj = 0.0;
		double sumtotalTransfert = 0.0;
		double sumtotalRd = 0.0;
		double sumtotalTs = 0.0;
		double sumtotaltotal = 0.0;
		double sumtotalTraite = 0.0;
		double sumtotalArrete = 0.0;
		double sumtotalNonArete = 0.0;
		double sumtotalProvision = 0.0;
		double sumtotalTotalFinal = 0.0;
		double sumtotalDevisRefuse = 0.0;
		double sumtotalEcartM = 0.0;
		double sumtotalEcratM1 = 0.0;
		double sumtotalVariationM = 0.0;
		double sumtotalEcartDernierPoint = 0.0;
		double sumtotalVariationEcartPoint = 0.0;
		double summontantBA = 0.0;
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
		double sumtotalMarcheRestantATraiter = 0.0;
		double sumtotalVariationDuAuxTransferts = 0.0;
		double sumtotalManqueAGagnerStsansProrata = 0.0;

		double cellts = 0.0;
		double celltotlafinal = 0.0;

		for (int i = 0; i < lstKeyGrid.size(); i++) {
			String lotName = "";
			String grid1 = "";
			String sumTotalGroup1 = "";
			double totalObjectif = 0.0;
			double totalObj = 0.0;
			double totalTransfert = 0.0;
			double totalRd = 0.0;
			double totalTs = 0.0;
			double totaltotal = 0.0;
			double totalTraite = 0.0;
			double totalArrete = 0.0;
			double totalNonArete = 0.0;
			double totalProvision = 0.0;
			double totalTotalFinal = 0.0;
			double totalDevisRefuse = 0.0;
			double totalEcartM = 0.0;
			double totalEcratM1 = 0.0;
			double totalVariationM = 0.0;
			double totalEcartDernierPoint = 0.0;
			double totalVariationEcartPoint = 0.0;
			double montantBA = 0.0;
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
			for (int j = 0; j < grid.size(); j++) {
				ficheSt = grid.get(j);
				if (ficheSt.getId() == null || ficheSt.getId() <= 0) {
					continue;
				}
				if (lstKeyGrid.get(i).equalsIgnoreCase(ficheSt.getLotType().getName())) {
					cellts = ficheSt.getObj() + ficheSt.getTransferts() + ficheSt.getRd() + ficheSt.getTs();
					celltotlafinal = ficheSt.getTraite() + ficheSt.getArrete() + ficheSt.getNonArrete() + ficheSt.getProvision();
					lotName = ficheSt.getLotType().getName();
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
					totalDevisRefuse += ficheSt.getDevisRefuse();
					totalEcartM += getEcardM(ficheSt);
					totalEcratM1 += ficheSt.getEcartM1();
					totalVariationM += getVariationM(ficheSt);
					totalEcartDernierPoint += ficheSt.getGestEcartDernierPt();
					totalVariationEcartPoint += getVariationEcartPointM(ficheSt);
					getAvctBAPercentage(ficheSt);
					montantBA += ficheSt.getTotalCumule();

					getAvctReelPercentage(ficheSt);
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
					// Create row report
					grid1 += createRowReport(ficheSt, cellts, celltotlafinal);
				}

			}
			// Calculate sum group 1
			sumTotalGroup1 += createTotalSumReport("Total type de lot:" + lotName, totalObjectif, null, totalObj, totalTransfert, totalRd, totalTs,
					totaltotal, totalTraite, totalArrete, totalNonArete, totalProvision, totalTotalFinal, totalDevisRefuse, totalEcartM,
					totalEcratM1, totalVariationM, totalEcartDernierPoint, totalVariationEcartPoint, null, montantBA, null, montantReel, totalCanto,
					totalBadge, totalGrue, totlaLift, totalBenne, totalNettoy, totalAutres, totalProrata, totalTotal, totalPrestationTraite,
					totalPenaliteFacture, totalProrataAppliqueSt, totalProrataMarche, totalProrataSuRad, totalMarcheRestantATraiterPercentage,
					totalMarcheRestantATraiter, totalVariationDuAuxTransferts, totalManqueAGagnerStsansProrata);

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
			summontantBA += montantBA;
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
			sumtotalMarcheRestantATraiter += totalMarcheRestantATraiter;
			sumtotalVariationDuAuxTransferts += totalVariationDuAuxTransferts;
			sumtotalManqueAGagnerStsansProrata += totalManqueAGagnerStsansProrata;
		}
		String totalSumGrid = createTotalSumReport("TOTAL ST", sumtotalObjectif, null, sumtotalObj, sumtotalTransfert, sumtotalRd, sumtotalTs,
				sumtotaltotal, sumtotalTraite, sumtotalArrete, sumtotalNonArete, sumtotalProvision, sumtotalTotalFinal, sumtotalDevisRefuse,
				sumtotalEcartM, sumtotalEcratM1, sumtotalVariationM, sumtotalEcartDernierPoint, sumtotalVariationEcartPoint, null, summontantBA,
				null, summontantReel, sumtotalCanto, sumtotalBadge, sumtotalGrue, sumtotlaLift, sumtotalBenne, sumtotalNettoy, sumtotalAutres,
				sumtotalProrata, sumtotalTotal, sumtotalPrestationTraite, sumtotalPenaliteFacture, sumtotalProrataAppliqueSt, sumtotalProrataMarche,
				sumtotalProrataSuRad, null, sumtotalMarcheRestantATraiter, sumtotalVariationDuAuxTransferts, sumtotalManqueAGagnerStsansProrata);
		return totalSumGrid;
	}

	private String createRowReport(FicheStDto ficheSt, double cellts, double celltotlafinal) {
		String row = append(ficheSt.getLot().getName(), ficheSt.getObjectif(), ficheSt.getSociete(), ficheSt.getObj(), ficheSt.getTransferts(),
				ficheSt.getRd(), ficheSt.getTs(), cellts, ficheSt.getTraite(), ficheSt.getArrete(), ficheSt.getNonArrete(), ficheSt.getProvision(),
				celltotlafinal, ficheSt.getDevisRefuse(), getEcardM(ficheSt), ficheSt.getEcartM1(), getVariationM(ficheSt),
				ficheSt.getGestEcartDernierPt(), getVariationEcartPointM(ficheSt), getAvctBAPercentage(ficheSt), ficheSt.getTotalCumule(),
				getAvctReelPercentage(ficheSt), ficheSt.getTraite(), ficheSt.getTotalCanto(), ficheSt.getTotalBadge(), ficheSt.getTotalGrue(),
				ficheSt.getTotalLift(), ficheSt.getTotalBenne(), ficheSt.getTotalNettoy(), ficheSt.getTotalAutres(), ficheSt.getTotalProrata(),
				getTotal2(ficheSt), getPrestationTraitePercentage(ficheSt), ficheSt.getTotalPenalty(), ficheSt.getPrestaProrata(),
				getProrataMarche(ficheSt), getProrataSurRAD(ficheSt), getMarchesRestantATraiterPercentage(ficheSt),
				getMarchesRestantATraiter(ficheSt), getVariationDuAuxTransferts(ficheSt), getManqueAGagnerSTSansProrata(ficheSt));
		return row;
	}

	private Double getAvctReelPercentage(FicheStDto ficheSt) {
		double result;
		Double totalReelActivitive = ficheSt.getTotalReelActivitive();
		Double totalTraite = ficheSt.getTraite();
		if (totalTraite == 0) {
			result = 0.0;
		} else {
			result = totalReelActivitive / totalTraite * 100;
		}
		return Math.round(result) * 1.0;
	}

	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	public Double getPrestationTraitePercentage(FicheStDto ficheSt) {
		double result;
		double traite = ficheSt.getTraite();
		double total = getTotal(ficheSt);
		if (total == 0) {
			result = 0.0;
		} else {
			result = traite / total * 100;
		}
		return Math.round(result) * 1.0;
	}

	// Calculated columns - Begin
	/**
	 * 
	 */
	private Double getTotal(FicheStDto ficheSt) {
		return ficheSt.getObj() + ficheSt.getTransferts() + ficheSt.getRd() + ficheSt.getTs();
	}

	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getTotal2(FicheStDto ficheSt) {
		return ficheSt.getTotalCanto() + ficheSt.getTotalBadge() + ficheSt.getTotalGrue() + ficheSt.getTotalLift() + ficheSt.getTotalBenne()
				+ ficheSt.getTotalNettoy() + ficheSt.getTotalAutres() + ficheSt.getTotalProrata();
	}

	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getMarchesRestantATraiterPercentage(FicheStDto ficheSt) {
		double prorataAppliqueST = ficheSt.getPrestaProrata();

		double traite = ficheSt.getTraite();
		if (prorataAppliqueST == 0 && traite == 0) {
			return getProrataTheorique(ficheSt) * 1.0;
		} else {
			return 0.0;
		}
	}

	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getProrataMarche(FicheStDto ficheSt) {
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
	private Integer getProrataTheorique(FicheStDto ficheSt) {
		if (ficheSt != null && ficheSt.getLot() != null && ficheSt.getLot().getChantier() != null
				&& ficheSt.getLot().getChantier().getProrataTheorique() != null)
			return ficheSt.getLot().getChantier().getProrataTheorique();
		return 0;
	}

	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getProrataSurRAD(FicheStDto ficheSt) {
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
	private Double getMarchesRestantATraiter(FicheStDto ficheSt) {
		return ficheSt.getTotalCumule() * getProrataTheorique(ficheSt);
	}

	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getVariationDuAuxTransferts(FicheStDto ficheSt) {
		double marchesRestantATraiterPercentage = getMarchesRestantATraiterPercentage(ficheSt);
		double prorataAppliqueST = ficheSt.getPrestaProrata();
		double transferts = ficheSt.getTransferts(); // +/-
		if (marchesRestantATraiterPercentage > 0 || prorataAppliqueST > 0) {
			return transferts * (marchesRestantATraiterPercentage + prorataAppliqueST);
		} else {
			return transferts * getProrataTheorique(ficheSt);
		}
	}

	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	public Double getManqueAGagnerSTSansProrata(FicheStDto ficheSt) { // PRESTA_PRORATA
		double marchesRestantATraiterPercentage = getMarchesRestantATraiterPercentage(ficheSt);
		double prorataAppliqueST = ficheSt.getPrestaProrata();
		if (marchesRestantATraiterPercentage == 0 || prorataAppliqueST == 0) {
			return getFinalTotal(ficheSt) * getProrataTheorique(ficheSt);
		} else {
			return 0.0;
		}
	}

	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private double getFinalTotal(FicheStDto ficheSt) {
		return ficheSt.getTraite() + ficheSt.getArrete() + ficheSt.getNonArrete() + ficheSt.getProvision();
	}

	/**
	 * 
	 * @param ficheSt
	 * @return
	 */
	private Double getVariationEcartPointM(FicheStDto ficheSt) {
		return getEcardM(ficheSt) - ficheSt.getGestEcartDernierPt();

	}

	private Double getAvctBAPercentage(FicheStDto ficheSt) {
		double totalCumule = ficheSt.getTotalCumule();
		double totalMch = ficheSt.getTraite();
		double result;
		if (totalMch == 0) {
			result = 0.0;
		} else {
			if (totalCumule <= 0) {
				result = 0.0;
			} else {
				result = totalCumule * 1.0 / totalMch * 100;
			}
		}
		return Math.round(result) * 1.0;
	}

	private Double getVariationM(FicheStDto ficheSt) {
		return getEcardM(ficheSt) - ficheSt.getEcartM1();
	}

	private double getEcardM(FicheStDto ficheSt) {
		double finalTotal = getFinalTotal(ficheSt);
		double total = getTotal(ficheSt);
		return finalTotal - total;
	}

	private String[] createGridReportSummary(List<FicheStDto> grid) {

		FicheStDto ficheStDto = null;
		double cellts = 0.0;
		double celltotlafinal = 0.0;
		double totalObjectif = 0.0;
		double totalObj = 0.0;
		double totalTransfert = 0.0;
		double totalRd = 0.0;
		double totalTs = 0.0;
		double totaltotal = 0.0;
		double totalTraite = 0.0;
		double totalArrete = 0.0;
		double totalNonArete = 0.0;
		double totalProvision = 0.0;
		double totalTotalFinal = 0.0;
		double totalDevisRefuse = 0.0;
		double totalEcartM = 0.0;
		double totalEcratM1 = 0.0;
		double totalVariationM = 0.0;
		double totalEcartDernierPoint = 0.0;
		double totalVariationEcartPoint = 0.0;
		double totalAvctBA = 0.0;
		double montantBA = 0.0;
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

		for (int i = 0; i < grid.size(); i++) {
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
			totalDevisRefuse += ficheStDto.getDevisRefuse();
			totalEcartM += ficheStDto.getEcartM();
			totalEcratM1 += ficheStDto.getEcartM1();
			totalVariationM += ficheStDto.getVariationM();
			totalEcartDernierPoint += ficheStDto.getGestEcartDernierPt();
			totalVariationEcartPoint += ficheStDto.getVariationEcartPointM();
			totalAvctBA += ficheStDto.getAvctBAPercentage();
			montantBA += ficheStDto.getTotalCumule();
			ficheStDto.getAvctReelPercentage();
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
			grid1 += createRowReport(ficheStDto, cellts, celltotlafinal);
		}

		String totalSumGrid = createTotalSumReport("TOTAL CHANTIER", totalObjectif, null, totalObj, totalTransfert, totalRd, totalTs, totaltotal,
				totalTraite, totalArrete, totalNonArete, totalProvision, totalTotalFinal, totalDevisRefuse, totalEcartM, totalEcratM1,
				totalVariationM, totalEcartDernierPoint, totalVariationEcartPoint, totalAvctBA, montantBA, null, montantReel, totalCanto, totalBadge,
				totalGrue, totlaLift, totalBenne, totalNettoy, totalAutres, totalProrata, totalTotal, totalPrestationTraite, totalPenaliteFacture,
				totalProrataAppliqueSt, totalProrataMarche, totalProrataSuRad, totalMarcheRestantATraiterPercentage, totalMarcheRestantATraiter,
				totalVariationDuAuxTransferts, totalManqueAGagnerStsansProrata);

		String[] arr = new String[2];
		arr[0] = grid1;
		arr[1] = totalSumGrid;
		return arr;
	}
}
