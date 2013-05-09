package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientChantierService;
import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.bean.domain.Rolepk;
import com.structis.fichesst.server.bean.domain.UtilisateurGrp;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.DomChantierService;
import com.structis.fichesst.server.service.domain.DomRoleService;
import com.structis.fichesst.server.service.domain.DomUtilisateurService;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.exception.DataConstraintException;

@Service("clientChantierService")
public class ClientChantierServiceImpl extends
		DependencyInjectionRemoteServiceServlet implements
		ClientChantierService {
	@Autowired
	DomRoleService domRoleService;
	@Autowired
	DomUtilisateurService domUtilisateurService;
	@Autowired
	DomChantierService domChantierService;
	@SuppressWarnings("unchecked")
	@Override
	public List<ChantierModel> findAll() {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				List<Chantier> findAll = domChantierService.findAll();
				return findAll;
			}
		};

		List<ChantierModel> finalList = (List<ChantierModel>) callManager(manager);

		return finalList;
	}

	@Override
	public boolean createChantier(ChantierModel chantierModel, Integer idUser) {
		boolean added = false;
		Chantier chantier = new Chantier();
		chantier.setNom(chantierModel.getNom());
		/*chantier.setId(chantierModel.getId());*/

		try {
			domChantierService.save(chantier);
			added = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilisateurGrp user=domUtilisateurService.find(idUser);
		Rolepk rolePk = new Rolepk(idUser, chantier.getId());
		Role r = new Role();
		r.setId(rolePk);
//		r.setChantier(chantier);
//		r.setUtilisateurGrp(user);

		r.setBcontributeur(true);
	
			/*daoRole.createRole(r);*/
		try {
			domRoleService.save(r);
		} catch (DataConstraintException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void deleteChantier(Integer idChantier) {
		/*dao.deleteChantier(idChantier);*/
		ManagerCallBack manager = new ManagerCallBack() {
			@Override
			public Object execute(Object... inputs) throws Exception {
				try {
					domChantierService.deleteChantier((Integer) inputs[0]);
					return null;
				}
				catch( Exception e ) {
					throw e;
				}
			}
		};

		callManager(manager, idChantier);
//		domChantierService.deleteChantier(idChantier);
	}

	@Override
	public List<ChantierModel> findChantierByUser(final Integer idUser) {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				List<Chantier> findAll = domChantierService.findChantierByUser(idUser);
				return findAll;
			}
		};

		List<ChantierModel> finalList = (List<ChantierModel>) callManager(manager);
		return finalList;
	}

	@Override
	public ChantierModel findChantierById(final Integer idChantier) {
		ManagerCallBack manager=new ManagerCallBack() {
			
			@Override
			public Object execute(Object... inputs) throws Exception {
				Chantier findChantier=domChantierService.find(idChantier);
				return findChantier;
			}
		};
		ChantierModel finalChantier=(ChantierModel)callManager(manager);
		return finalChantier;
	}

}
