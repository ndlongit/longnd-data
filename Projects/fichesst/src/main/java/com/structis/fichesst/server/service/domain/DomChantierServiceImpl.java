package com.structis.fichesst.server.service.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.server.bean.domain.Conductor;
import com.structis.fichesst.server.bean.domain.FicheTransfertpp;
import com.structis.fichesst.server.bean.domain.Lot;
import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.bean.domain.Rolepk;
import com.structis.fichesst.server.bean.domain.Societe;
import com.structis.fichesst.server.dao.ChantierDao;
import com.structis.fichesst.server.dao.ConductorDao;
import com.structis.fichesst.server.dao.FicheTransfertppDao;
import com.structis.fichesst.server.dao.LotDao;
import com.structis.fichesst.server.dao.RoleDao;
import com.structis.fichesst.server.dao.SocieteDao;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.exception.DataConstraintException;

@Service("domChantierService")
public class DomChantierServiceImpl extends
		BasicServiceImpl<Chantier, Integer, ChantierDao> implements
		DomChantierService {
	@Autowired
	FicheTransfertppDao ficheTransfertppDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	ChantierDao chantierDao;
	@Autowired
	LotDao lotDao;
	@Autowired 
	ConductorDao conducteurDao;
	@Autowired 
	SocieteDao societeDao;
	@Override
	public List<Chantier> findChantierByUser(Integer idUser) {
		return chantierDao.findChantierByUser(idUser);
	}

	@Override
	@Transactional(readOnly = true)
	public void delete(Chantier chantier) {
		chantier.setRoles(null);
	}

	@Override
	@Transactional
	public void deleteChantier(Integer idChantier) {
		Chantier chantier = chantierDao.find(idChantier);
		List<Role> roles = chantier.getRoles();
		if (roles.size() > 0) {
			for (Role role : roles) {
				Integer idUser = role.getId().getIdUtilisateurGrp();
				role.setId(new Rolepk(idUser, idChantier));
				role.setChantier(null);
				role.setUtilisateurGrp(null);
				roleDao.delete(role);

			}
		}
		List<FicheTransfertpp> fiches = chantier.getFicheTransfertPps();
		if (fiches.size()>0) {
			for (FicheTransfertpp ficheTransfertpp : fiches) {
				Integer idRefTransfertpp = ficheTransfertpp.getId()
						.getIdTransfertPp();
				ficheTransfertpp.setChantier(null);
				ficheTransfertpp.setLigTransfertPps(null);
				ficheTransfertpp.setLigTransfertPps(null);
				ficheTransfertppDao.delete(ficheTransfertpp);
			}
		}
		
		List<Lot> lots=chantier.getLots();
		if (lots.size()>0) {
			for (Lot lot : lots) {
				lot.setChantier(null);
				lotDao.delete(lot);
			}
		}
		List<Conductor> conductors=chantier.getConducteurs();
		if (conductors.size()>0) {
			for (Conductor conductor : conductors) {
				conductor.setChantier(null);
				conducteurDao.delete(conductor);
			}
		}
		List<Societe> societes=chantier.getSocietes();
		if (societes.size()>0) {
			for (Societe societe : societes) {
				societe.setChantier(null);
				societeDao.delete(societe);
			}
		}
		chantierDao.delete(chantier);

		// chantierDao.deleteChantier(idChantier);
	}
}
