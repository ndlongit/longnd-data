package com.structis.fichesst.server.service.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientLigTransfertppService;
import com.structis.fichesst.server.bean.domain.LigTransfertPP;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.DomLigTransfertppService;
import com.structis.fichesst.server.service.domain.DomRefTypeBudjService;
import com.structis.fichesst.server.service.domain.FicheTransfertppService;
import com.structis.fichesst.shared.dto.LigTransfertppModel;

@Service("clientLigTransfertppService")
public class ClientLigTransfertppImpl extends
		DependencyInjectionRemoteServiceServlet implements
		ClientLigTransfertppService {
	@Autowired
	FicheTransfertppService ficheTransfertppService;
	@Autowired
	DomLigTransfertppService domLigTransfertppService;
	@Autowired
	DomRefTypeBudjService domRefTypeBudjService;
	double totalObj;
	double totalDevers;
	double totalTs;
	double totalRd;

	@Override
	public List<LigTransfertppModel> findByID(final Integer idTransfertpp,
			final Integer idChantier) {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				List<LigTransfertPP> findAll = domLigTransfertppService
						.findAllByID(idTransfertpp, idChantier);
				return findAll;
			}
		};

		List<LigTransfertppModel> finalList = (List<LigTransfertppModel>) callManager(
				manager, idChantier, idTransfertpp);
		return finalList;

	}

	@Override
	public void saveAllLig(List<LigTransfertppModel> ligModels,
			Integer idChantier, Integer idTransfertpp) {

		if (ficheTransfertppService.findbyId(idChantier, idTransfertpp).size() == 0) {
			ficheTransfertppService.createFicheRelation(idChantier, idTransfertpp);
		}
		for (LigTransfertppModel ligModel : ligModels) {
			LigTransfertPP lig = new LigTransfertPP();
			lig.setCommentaires(ligModel.getCommentaires());
			lig.setLot1(ligModel.getLot1());
			lig.setLot2(ligModel.getLot2());
			lig.setDeVers(ligModel.getDeVers());
			lig.setPu(ligModel.getPu());
			lig.setQuantite(ligModel.getQuantite());
			lig.setIdChantier(idChantier);
			lig.setIdTransfertpp(idTransfertpp);
			lig.setIdBudj(ligModel.getRefTypeBudjConf().getId());
				domLigTransfertppService.createLig(lig);
//			try {
//				domLigTransfertppService.save(lig);
//			} catch (DataConstraintException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
	}

	@Override
	public void deleteLig(List<LigTransfertppModel> ligModels,
			Integer idChantier, Integer idTransfertpp) {
		List<LigTransfertPP> ligs = new ArrayList<LigTransfertPP>();
		for (LigTransfertppModel ligModel : ligModels) {
			LigTransfertPP lig = new LigTransfertPP();
			lig.setId(ligModel.getId());
			lig.setCommentaires(ligModel.getCommentaires());
			lig.setLot1(ligModel.getLot1());
			lig.setLot2(ligModel.getLot2());
			lig.setDeVers(ligModel.getDeVers());
			lig.setPu(ligModel.getPu());
			lig.setQuantite(ligModel.getQuantite());
			lig.setIdChantier(idChantier);
			lig.setIdTransfertpp(idTransfertpp);
			lig.setIdBudj(ligModel.getRefTypeBudjConf().getId());
			ligs.add(lig);
		}
		for (LigTransfertPP lig : ligs) {
			domLigTransfertppService.deleteLig(lig);
//			domLigTransfertppService.delete(lig);
		}
	}

	@Override
	public LigTransfertppModel sumByType() {
		LigTransfertppModel lig = new LigTransfertppModel();
		lig.set(LigTransfertppModel.TotalDeVers, totalDevers);
		lig.set(LigTransfertppModel.TotalObj, totalObj);
		lig.set(LigTransfertppModel.TotalRD, totalRd);
		lig.set(LigTransfertppModel.TotalTS, totalTs);
		totalDevers = 0;
		totalObj = 0;
		totalTs = 0;
		totalRd = 0;
		return lig;
	}
}
