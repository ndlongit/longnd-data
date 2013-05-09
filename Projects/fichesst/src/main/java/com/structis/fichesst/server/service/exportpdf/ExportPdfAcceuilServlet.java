package com.structis.fichesst.server.service.exportpdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.server.bean.domain.FicheSt;
import com.structis.fichesst.server.bean.domain.FicheTransfertpp;
import com.structis.fichesst.server.bean.domain.LigTransfertPP;
import com.structis.fichesst.server.bean.domain.LotType;
import com.structis.fichesst.server.bean.domain.RefTransfertPP;
import com.structis.fichesst.server.core.SpringGetter;
import com.structis.fichesst.server.service.domain.DomChantierService;
import com.structis.fichesst.server.service.domain.DomLigTransfertppService;
import com.structis.fichesst.server.service.domain.FicheStService;
import com.structis.fichesst.server.service.domain.FicheTransfertppService;
import com.structis.fichesst.server.service.domain.RefTransfertppService;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.TransfertPpSummaryDto;
import com.structis.fichesst.shared.util.Constants;

@SuppressWarnings("serial")
public class ExportPdfAcceuilServlet extends ExportPdfServlet {

	public final static String FILE_NAME = "list_of_synthese.pdf";

	@Autowired
	FicheStService ficheStService;

	@Autowired
	RefTransfertppService refTransfertppService;

	@Autowired
	FicheTransfertppService ficheTransfertppService;

	@Autowired
	DomChantierService domChantierService;

	@Autowired
	DomLigTransfertppService domLigTransfertppService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ficheStService = (FicheStService) SpringGetter.getBean(config.getServletContext(), "ficheStService");
		refTransfertppService = (RefTransfertppService) SpringGetter.getBean(config.getServletContext(), "refTransfertppService");
		ficheTransfertppService = (FicheTransfertppService) SpringGetter.getBean(config.getServletContext(), "ficheTransfertppService");
		domChantierService = (DomChantierService) SpringGetter.getBean(config.getServletContext(), "domChantierService");
		domLigTransfertppService = (DomLigTransfertppService) SpringGetter.getBean(config.getServletContext(), "domLigTransfertppService");
	}

	@Override
	@Transactional(readOnly = true)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String filename = sdf.format(Calendar.getInstance().getTime()) + "_" + FILE_NAME;
		String chantierId = request.getParameter("chantierId");
		Chantier chantier = domChantierService.find(new Integer(chantierId));
		List<FicheSt> listFicheSt = ficheStService.findByChantierId(chantier.getId());
		List<FicheSt> list1 = new ArrayList<FicheSt>();
		List<FicheSt> list2 = new ArrayList<FicheSt>();
		createGrid(listFicheSt, list1, list2);
		List<TransfertPpSummaryDto> listTransfertPpSummaryDto = createTransferGrid(chantier);
		List<FicheStDto> listSummary = createSummaryGrid(listFicheSt, listTransfertPpSummaryDto);
		/*
		 * String localeString = request.getParameter("locale"); Locale clientLocale = Locale.FRENCH; if(localeString != null){ clientLocale = new
		 * Locale(localeString); }
		 */
		try {
			// response.setContentType("application/pdf" );
			response.setHeader("Content-Disposition", "inline;filename=" + filename);

			ExportPdfAcceuil pdf = new ExportPdfAcceuil(response.getOutputStream(), messageSource, clientLocale);
			pdf.setChantier(chantier);
			pdf.setFicheStGrid1(list1);
			pdf.setFicheStGrid2(list2);
			pdf.setListTransfertPpSummaryDto(listTransfertPpSummaryDto);
			pdf.setListSummary(listSummary);
			// process export
			pdf.process();

		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param results
	 * @param list1
	 * @param list2
	 */
	private void createGrid(List<FicheSt> results, List<FicheSt> list1, List<FicheSt> list2) {
		Map<Integer, String> mapPrestations = new HashMap<Integer, String>();
		Map<Integer, String> mapConducteurdetravaux = new HashMap<Integer, String>();
		Map<Integer, String> mapSociete = new HashMap<Integer, String>();
		String prestationValue = "";
		for (FicheSt ficheSt : results) {
			mapConducteurdetravaux.put(ficheSt.getId(), ficheSt.getIdSiTravaux());
			mapSociete.put(ficheSt.getId(), ficheSt.getSociete());
			prestationValue = ficheSt.getPrestaCanto() + Constants.SEPRATE + ficheSt.getPrestaBadge() + Constants.SEPRATE + ficheSt.getPrestaGrue()
					+ Constants.SEPRATE + ficheSt.getPrestaLift() + Constants.SEPRATE + ficheSt.getPrestaBenne() + Constants.SEPRATE
					+ ficheSt.getPrestaNettoyage() + Constants.SEPRATE + ficheSt.getPrestaProrata();
			mapPrestations.put(ficheSt.getId(), prestationValue);
			LotType lotType = ficheSt.getLotType();
			if (lotType != null && "Honoraires".equalsIgnoreCase(lotType.getName())) {
				list2.add(ficheSt);
			} else {
				list1.add(ficheSt);
			}
		}
	}

	/**
	 * 
	 * @param chantier
	 * @return
	 */
	private List<TransfertPpSummaryDto> createTransferGrid(Chantier chantier) {
		List<RefTransfertPP> refTransfertPpList = refTransfertppService.findAll();
		Collections.sort(refTransfertPpList, new Comparator<RefTransfertPP>() {
			@Override
			public int compare(RefTransfertPP o1, RefTransfertPP o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		Map<FicheTransfertpp, List<LigTransfertPP>> map = new HashMap<FicheTransfertpp, List<LigTransfertPP>>();
		for (RefTransfertPP refTransfertPp : refTransfertPpList) {
			FicheTransfertpp ficheTransfertPp = new FicheTransfertpp();
			ficheTransfertPp.setRefTransfertPp(refTransfertPp);
			ficheTransfertPp.setChantier(chantier);
			map.put(ficheTransfertPp, new ArrayList<LigTransfertPP>());
		}
		List<FicheTransfertpp> ligTransfertPpList = ficheTransfertppService.findByChantierId(chantier.getId());

		for (FicheTransfertpp ficheTransfertpp : ligTransfertPpList) {
			map.remove(ficheTransfertpp);
			map.put(ficheTransfertpp, ficheTransfertpp.getLigTransfertPps());
		}

		List<TransfertPpSummaryDto> transfertPpSummaryList = new ArrayList<TransfertPpSummaryDto>();
		Set<FicheTransfertpp> keys = map.keySet();
		Iterator<FicheTransfertpp> iter = keys.iterator();
		while (iter.hasNext()) {
			FicheTransfertpp ficheTransfertpp = iter.next();
			// ficheTransfertpp.setLigTransfertPps(ligTransfertPps);
			TransfertPpSummaryDto transfertPpSummary = new TransfertPpSummaryDto(ficheTransfertpp.getChantier().getId(), ficheTransfertpp
					.getRefTransfertPp().getId(), ficheTransfertpp.getRefTransfertPp().getLabel());
			transfertPpSummary.setObjective(ficheTransfertpp.getObjectif());
			// transfertPpSummary.setLigTransfertPps(map.get(ficheTransfertpp));
			calculateValues(map.get(ficheTransfertpp), transfertPpSummary);
			transfertPpSummaryList.add(transfertPpSummary);
		}
		// List<LigTransfertPP> ligTransfertPps = domLigTransfertppService.findByChantierId(chantier.getId());
		while (iter.hasNext()) {
			FicheTransfertpp ficheTransfertpp = iter.next();
			// ficheTransfertpp.setLigTransfertPps(ligTransfertPps);
			TransfertPpSummaryDto transfertPpSummary = new TransfertPpSummaryDto(ficheTransfertpp.getChantier().getId(), ficheTransfertpp
					.getRefTransfertPp().getId(), ficheTransfertpp.getRefTransfertPp().getLabel());
			transfertPpSummary.setObjective(ficheTransfertpp.getObjectif());
			// transfertPpSummary.setLigTransfertPps(map.get(ficheTransfertpp));
			calculateValues(map.get(ficheTransfertpp), transfertPpSummary);
			transfertPpSummaryList.add(transfertPpSummary);
		}
		Collections.sort(transfertPpSummaryList, new Comparator<TransfertPpSummaryDto>() {
			@Override
			public int compare(TransfertPpSummaryDto o1, TransfertPpSummaryDto o2) {
				return o1.getRefTransfertPpId().compareTo(o2.getRefTransfertPpId());
			}
		});
		return transfertPpSummaryList;
	}

	/**
	 * 
	 * @param ligModels
	 * @param transfertPpSummary
	 */
	private void calculateValues(List<LigTransfertPP> ligModels, TransfertPpSummaryDto transfertPpSummary) {
		double totalObjPositive = 0.0;
		double totalObjNegative = 0.0;
		double amountObj = 0.0;
		double totalDeversPositive = 0.0;
		double totalDeversNegative = 0.0;
		double amountDevers = 0.0;
		double totalTsPositive = 0.0;
		double totalTsNegative = 0.0;
		double amountTs = 0.0;
		double totalRDPositive = 0.0;
		double totalRDNegative = 0.0;
		double amountRD = 0.0;

		if (ligModels != null) {
			for (LigTransfertPP lig : ligModels) {

				Integer typeId = lig.getRefTypeBudjConf().getId();
				Integer quantity = lig.getQuantite();
				Double pu = lig.getPu();
				String deVers = lig.getDeVers();

				if (typeId == 1 && deVers.equalsIgnoreCase("vers")) {
					totalObjNegative += pu * quantity * (-1);
				}
				if (typeId == 1 && deVers.equalsIgnoreCase("de")) {
					totalObjPositive += pu * quantity;
				}
				if (typeId == 2 && deVers.equalsIgnoreCase("vers")) {
					totalDeversNegative += pu * quantity * (-1);
				}
				if (typeId == 2 && deVers.equalsIgnoreCase("de")) {
					totalDeversPositive += pu * quantity;
				}
				if (typeId == 3 && deVers.equalsIgnoreCase("vers")) {
					totalRDNegative += pu * quantity * (-1);
				}
				if (typeId == 3 && deVers.equalsIgnoreCase("de")) {
					totalRDPositive += pu * quantity;
				}
				if (typeId == 4 && deVers.equalsIgnoreCase("vers")) {
					totalTsNegative += pu * quantity * (-1);
				}
				if (typeId == 4 && deVers.equalsIgnoreCase("de")) {
					totalTsPositive += pu * quantity;
				}
			}
		}
		amountObj = totalObjNegative + totalObjPositive;
		amountDevers = totalDeversNegative + totalDeversPositive;
		amountRD = totalRDNegative + totalRDPositive;
		amountTs = totalTsNegative + totalTsPositive;

		transfertPpSummary.setObj(amountObj);
		transfertPpSummary.setDevers(amountDevers);
		transfertPpSummary.setTs(amountTs);
		transfertPpSummary.setRd(amountRD);
	}

	/**
	 * 
	 * @param listFicheSt
	 * @param transfertPpSummaryList
	 * @return
	 */
	private List<FicheStDto> createSummaryGrid(List<FicheSt> listFicheSt, List<TransfertPpSummaryDto> transfertPpSummaryList) {
		List<FicheStDto> ficheStSummaryGrid = new ArrayList<FicheStDto>();
		double totalObjective = 0.0;
		double totalObj = 0.0;
		double totalTransferts = 0.0;
		double totalRd = 0.0;
		double totalTs = 0.0;

		double totalTraite = 0.0;
		double totalArrete = 0.0;
		double totalNonArrete = 0.0;
		double totalProvision = 0.0;
		double totalDevisRefuse = 0.0;

		double totalEcartM1 = 0.0;
		double totalEcartDernierPoint = 0.0;
		double totalCumule = 0.0;

		double totalCanto = 0.0;
		double totalBadge = 0.0;
		double totalGrue = 0.0;
		double totalLift = 0.0;
		double totalBenne = 0.0;
		double totalNettoy = 0.0;
		double totalAutres = 0.0;
		double totalPenalty = 0.0;
		double totalProrataAppliqueST = 0.0;
		for (FicheSt ficheSt : listFicheSt) {
			totalObjective += ficheSt.getObjectif();
			totalObj += ficheSt.getObj();
			totalTransferts += ficheSt.getTransferts();
			totalRd += ficheSt.getRd();
			totalTs += ficheSt.getTs();

			totalTraite += ficheSt.getTraite();
			totalArrete += ficheSt.getArrete();
			totalNonArrete += ficheSt.getNonArrete();
			totalProvision += ficheSt.getProvision();
			totalDevisRefuse += ficheSt.getDevisRefuse();
			totalEcartM1 += ficheSt.getEcartM1();
			if (ficheSt.getEcartDernierPoint() != null) {
				totalEcartDernierPoint += ficheSt.getEcartDernierPoint();
			}

			totalCumule += ficheSt.getTotalCumule();
			totalCanto += ficheSt.getTotalCanto();
			totalBadge += ficheSt.getTotalBadge();
			totalGrue += ficheSt.getTotalGrue();

			totalLift += ficheSt.getTotalLift();
			totalBenne += ficheSt.getTotalBenne();
			totalNettoy += ficheSt.getTotalNettoy();
			totalAutres += ficheSt.getTotalAutres();
			totalPenalty += ficheSt.getTotalPenalty();
			totalProrataAppliqueST += ficheSt.getPrestaProrata();
		}

		for (TransfertPpSummaryDto transfertPpSummaryDto : transfertPpSummaryList) {
			totalObjective += transfertPpSummaryDto.getObjective();
		}
		FicheStDto model = new FicheStDto();
		model.setObjectif(totalObjective);
		model.setObj(totalObj);
		model.setTransferts(totalTransferts);
		model.setRd(totalRd);
		model.setTs(totalTs);

		model.setTraite(totalTraite);
		model.setArrete(totalArrete);
		model.setNonArrete(totalNonArrete);
		model.setProvision(totalProvision);
		model.setDevisRefuse(totalDevisRefuse);
		model.setEcartM1(totalEcartM1);

		model.setGestEcartDernierPt(totalEcartDernierPoint);
		model.setTotalCumule(totalCumule);

		model.setTotalCanto(totalCanto);
		model.setTotalBadge(totalBadge);
		model.setTotalGrue(totalGrue);
		model.setTotalLift(totalLift);
		model.setTotalBenne(totalBenne);
		model.setTotalNettoy(totalNettoy);
		model.setTotalAutres(totalAutres);
		model.setTotalPenalty(totalPenalty);

		model.setPrestaProrata(totalProrataAppliqueST);
		ficheStSummaryGrid.add(model);
		return ficheStSummaryGrid;
	}
}
