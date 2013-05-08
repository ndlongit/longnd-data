package com.structis.fichesst.server.service.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.server.bean.domain.CautionFournie;
import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.server.bean.domain.Deduction;
import com.structis.fichesst.server.bean.domain.FicheSt;
import com.structis.fichesst.server.bean.domain.FicheTransfertpp;
import com.structis.fichesst.server.bean.domain.FicheTransfertppPk;
import com.structis.fichesst.server.bean.domain.Gestion;
import com.structis.fichesst.server.bean.domain.Lot;
import com.structis.fichesst.server.bean.domain.Penalty;
import com.structis.fichesst.server.bean.domain.Progress;
import com.structis.fichesst.server.bean.domain.RefTransfertPP;
import com.structis.fichesst.server.dao.CautionFournieDao;
import com.structis.fichesst.server.dao.ChantierDao;
import com.structis.fichesst.server.dao.DeductionDao;
import com.structis.fichesst.server.dao.FicheStDao;
import com.structis.fichesst.server.dao.FicheTransfertppDao;
import com.structis.fichesst.server.dao.GestionDao;
import com.structis.fichesst.server.dao.LotDao;
import com.structis.fichesst.server.dao.PenaltyDao;
import com.structis.fichesst.server.dao.ProgressDao;
import com.structis.fichesst.server.dao.SocieteDao;
import com.structis.fichesst.server.util.AppUtil;
import com.structis.fichesst.shared.exception.DataConstraintException;

@Service("ficheStService")
public class FicheStServiceImpl extends BasicServiceImpl<FicheSt, Integer, FicheStDao> implements FicheStService {

	private static final Logger LOGGER = Logger.getLogger(FicheStServiceImpl.class);
	
	@Autowired
	private CautionFournieDao cautionFournieDao;

	@Autowired
	private GestionDao gestionDao;

	@Autowired
	private DeductionDao deductionDao;

	@Autowired
	private ProgressDao progressDao;

	@Autowired
	private PenaltyDao penaltyDao;

	@Autowired
	private LotDao lotDao;

	@Autowired
	private SocieteDao societeDao;
	
	@Autowired
	private FicheStDao ficheStDao;
	
	@Autowired
	private ChantierDao chantierDao;
	
	@Autowired
	private FicheTransfertppDao ficheTransfertppDao;

	@Override
	@Transactional
	public FicheSt save(FicheSt ficheSt) throws DataConstraintException, Exception {
		updateRelations(ficheSt);
		return super.save(ficheSt);
	}

	@Override
	@Transactional
	public FicheSt update(FicheSt ficheSt) throws DataConstraintException, Exception {
		if( ficheSt != null ) {
			FicheSt persistence = find(ficheSt.getId());
			
			if (persistence != null) {
				List<CautionFournie> cautionFournieList = persistence.getCautionFournies();
				if (cautionFournieList != null) {
					for (Iterator<CautionFournie> iterator = cautionFournieList.iterator(); iterator.hasNext();) {
						CautionFournie cautionFournie = (CautionFournie) iterator.next();
						cautionFournie.setFicheSt(null);
						iterator.remove();
					}
				}

				List<Gestion> gestionList = persistence.getGestions();
				if (gestionList != null) {
					for (Iterator<Gestion> iterator = gestionList.iterator(); iterator.hasNext();) {
						Gestion gestion = iterator.next();
						gestion.setFicheSt(null);
						iterator.remove();
					}
				}

				List<Deduction> deductionList = persistence.getDeductions();
				if (deductionList != null) {
					for (Iterator<Deduction> iterator = deductionList.iterator(); iterator.hasNext();) {
						Deduction deduction = iterator.next();
						deduction.setFicheSt(null);
						iterator.remove();
					}
				}

				List<Progress> progressList = persistence.getProgresses();
				if (progressList != null) {
					for (Iterator<Progress> iterator = progressList.iterator(); iterator.hasNext();) {
						Progress progress = iterator.next();
						progress.setFicheSt(null);
						iterator.remove();
					}
				}

				List<Penalty> penaltyList = persistence.getPenalties();
				if (penaltyList != null) {
					for (Iterator<Penalty> iterator = penaltyList.iterator(); iterator.hasNext();) {
						Penalty penalty = iterator.next();
						penalty.setFicheSt(null);
						iterator.remove();
					}
				}
			}
			
			updateRelations(ficheSt);
			
			ficheStDao.update(persistence);
			return ficheStDao.update(ficheSt);
		}

		return null;
	}

	private void updateRelations(FicheSt ficheSt) throws DataConstraintException, Exception {
		if( ficheSt == null ) {
			return;
		}

//		Societe societe = ficheSt.getSociete();
//		String societeName = societe.getNom();
//		List<Societe> results = societeDao.findByProperty(Societe.PROP_NAME, Arrays.asList(societeName));
//		if( AppUtil.isNullOrEmpty(results) ) {
//			societeDao.save(societe);
//		}
//		else {
//			societe.setId(results.get(0).getId());
//		}
		Lot lot = ficheSt.getLot();
//		lot.setChantier(societe.getChantier());
		String lotName = lot.getName();
		List<Lot> results2 = lotDao.findByProperty(Lot.PROP_NAME, Arrays.asList(lotName));
		if( AppUtil.isNullOrEmpty(results2) ) {
			lotDao.save(lot);
		}
		else {
			lot.setId(results2.get(0).getId());
		}

		List<CautionFournie> cautionFournies = ficheSt.getCautionFournies();
		if( cautionFournies != null ) {
			for( CautionFournie cautionFournie : cautionFournies ) {
				cautionFournie.setFicheSt(ficheSt);
			}
		}
		
		List<Gestion> gestionList = ficheSt.getGestions();
		if( gestionList != null ) {
			for( Gestion gestion : gestionList ) {
				gestion.setFicheSt(ficheSt);
			}
		}

		List<Deduction> deductionList = ficheSt.getDeductions();
		if( deductionList != null ) {
			for( Deduction deduction : deductionList ) {
				deduction.setFicheSt(ficheSt);
			}
		}

		List<Progress> progressList = ficheSt.getProgresses();
		if( progressList != null ) {
			for( Progress progress : progressList ) {
				progress.setFicheSt(ficheSt);
			}
		}

		List<Penalty> penaltyList = ficheSt.getPenalties();
		if( penaltyList != null ) {
			for( Penalty penalty : penaltyList ) {
				penalty.setFicheSt(ficheSt);
			}
		}
	}

	@Override
    public List<FicheSt> findByChantierId(Integer chantierId) {
		List<FicheSt> results = ficheStDao.findByChantierId(chantierId);
		if( !AppUtil.isNullOrEmpty(results) ) {
			for( FicheSt ficheSt : results ) {
				if( ficheSt == null ) {
					continue;
				}
				ficheSt.calculateValues();
			}
		}
		
		return results; 
    }
	
	@Override
	public FicheSt find(Integer id) {
		FicheSt result = ficheStDao.find(id);
		if(result != null) {
			result.calculateValues();
		}
		
		return result;
	}

	@Override
	@Transactional
	public Object updateSynthese(Chantier chantier, List<FicheSt> list1, List<FicheTransfertpp> list2) {
		try {
			Chantier c = chantierDao.find(chantier.getId());
			c.setNom(chantier.getNom());
			c.setProrataTheorique(chantier.getProrataTheorique());

			//Update changes to DB
			chantierDao.update(c);

			setIds(list2);

			List<FicheSt> l1 = new ArrayList<FicheSt>();
			for( FicheSt ficheSt : list1 ) {
				try {
					l1.add(ficheStDao.find(ficheSt.getId()));
				}
				catch( Exception e ) {
					LOGGER.error(e.getMessage(), e);
				}
			}

			List<FicheTransfertpp> l2 = new ArrayList<FicheTransfertpp>();
			List<FicheTransfertppPk> ficheTransfertppPkList = new ArrayList<FicheTransfertppPk>();
			for( FicheTransfertpp ficheTransfertpp : list2 ) {
				try {
					FicheTransfertpp ficheTransfertppDb = ficheTransfertppDao.find(ficheTransfertpp.getId());
					if( ficheTransfertppDb != null ) {
						l2.add(ficheTransfertppDb);
						ficheTransfertppPkList.add(ficheTransfertppDb.getId());
					}
				}
				catch( Exception e ) {
					LOGGER.error(e.getMessage(), e);
				}
			}

			for( FicheSt ficheSt : list1 ) {
				for( FicheSt fSt : l1 ) {
					if( ficheSt.getId() != null && ficheSt.getId().equals(fSt.getId()) ) {

						// Update values
						fSt.setObjectif(ficheSt.getObjectif());
						fSt.setEcartM1(ficheSt.getEcartM1());
						fSt.setEcartDernierPoint(ficheSt.getEcartDernierPoint());
						continue;
					}
				}
			}

			//Newly added FicheTransfertpp(s)
			List<FicheTransfertpp> addedList = new ArrayList<FicheTransfertpp>();
			for( FicheTransfertpp ficheTransfertpp : list2 ) {
				if( !ficheTransfertppPkList.contains(ficheTransfertpp.getId()) ) {
					
					//If Objectif value is set.
					if( ficheTransfertpp.getObjectif() != null && ficheTransfertpp.getObjectif() > 0 ) {
						addedList.add(ficheTransfertpp);
					}
				}
			}

			for( FicheTransfertpp ficheTransfertpp : list2 ) {
				for( FicheTransfertpp fTp : l2 ) {
					if( ficheTransfertpp.getId() != null && ficheTransfertpp.getId().equals(fTp.getId()) ) {

						// Update value
						fTp.setObjectif(ficheTransfertpp.getObjectif());
						continue;
					}
				}
			}

			//Update changes to DB
			for( FicheSt fSt : l1 ) {
				ficheStDao.update(fSt);
			}
			for( FicheTransfertpp fTp : l2 ) {
				ficheTransfertppDao.update(fTp);
			}

			//Insert new FicheTransfertpp(s)
			for( FicheTransfertpp ficheTransfertpp : addedList ) {
				ficheTransfertppDao.save(ficheTransfertpp);
			}

		}
		catch( DataConstraintException e ) {
			LOGGER.error(e.getMessage(), e);
		}
		catch( Exception e ) {
			LOGGER.error(e.getMessage(), e);
		}

		return true;
	}

	private static void setIds(List<FicheTransfertpp> list) {
		try {
			for( FicheTransfertpp ficheTransfertpp : list ) {
				Chantier chantier = ficheTransfertpp.getChantier();
				RefTransfertPP refTransfertPp = ficheTransfertpp.getRefTransfertPp();

				FicheTransfertppPk ficheTransfertppPk = new FicheTransfertppPk(chantier.getId(), refTransfertPp.getId());
				ficheTransfertpp.setId(ficheTransfertppPk);
			}
		}
		catch( Exception e ) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
