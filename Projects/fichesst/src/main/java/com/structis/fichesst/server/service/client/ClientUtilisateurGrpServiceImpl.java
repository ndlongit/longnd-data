package com.structis.fichesst.server.service.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.structis.fichesst.client.service.ClientUtilsateurGrpService;
import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.bean.domain.Rolepk;
import com.structis.fichesst.server.bean.domain.UtilisateurGrp;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.DomRoleService;
import com.structis.fichesst.server.service.domain.DomUtilisateurService;
import com.structis.fichesst.server.util.AppUtil;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.exception.DataConstraintException;

@Service("clientUtilisateurGrpService")
public class ClientUtilisateurGrpServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientUtilsateurGrpService {

	@Autowired
	DomUtilisateurService domUtilisateurService;

	@Autowired
	DomRoleService domRoleService;

	@SuppressWarnings("unchecked")
	@Override
	public List<UtilisateurGrpModel> findAll() {
		ManagerCallBack manager = new ManagerCallBack() {
			@Override
			public Object execute(Object... inputs) {
				List<UtilisateurGrp> findAll = domUtilisateurService.findAll();
				return findAll;
			}
		};

		List<UtilisateurGrpModel> finalList = (List<UtilisateurGrpModel>) callManager(manager);

		return finalList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UtilisateurGrpModel> findUserAdmin() {
		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				List<UtilisateurGrp> findAll = domUtilisateurService.findUserAdmin();
				return findAll;
			}
		};

		List<UtilisateurGrpModel> finalList = (List<UtilisateurGrpModel>) callManager(manager);

		return finalList;
	}

	@Override
	public UtilisateurGrpModel saveUser(UtilisateurGrpModel user) {
		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				return domUtilisateurService.saveUser((UtilisateurGrp) inputs[0]);
			}
		};
		return (UtilisateurGrpModel) callManager(manager, user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UtilisateurGrpModel> saveListUser() {
		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				List<UtilisateurGrp> listuser = domUtilisateurService.saveListUser((List<UtilisateurGrp>) inputs[0]);
				return listuser;
			}
		};
		List<UtilisateurGrpModel> listModel = (List<UtilisateurGrpModel>) callManager(manager);
		return listModel;

	}

	@Override
	public List<UtilisateurGrpModel> findAllUserByChantier(Integer idChantier) {
		List<Role> listRole = new ArrayList<Role>();

		List<UtilisateurGrp> list = domUtilisateurService.findUserByChantier(idChantier);

		List<UtilisateurGrpModel> finalResult = new ArrayList<UtilisateurGrpModel>();

		for (int i = 0; i < list.size(); i++) {
			UtilisateurGrp user = new UtilisateurGrp();
			user = list.get(i);
			listRole = user.getRoles();
			UtilisateurGrpModel userRoleModel = new UtilisateurGrpModel();
			userRoleModel.setIdentifiant(user.getIdentifiant());
			userRoleModel.setId(user.getId());
			userRoleModel.setIdChantier(idChantier);
			for (Role role : listRole) {
				if (role.getId().getIdChantier().equals(idChantier)) {
					userRoleModel.setBcontributeur(role.getBcontributeur());
					userRoleModel.setBlecteur(role.getBlecteur());
				}
			}

			finalResult.add(userRoleModel);

		}
		return finalResult;
	}

	@Override
	public UtilisateurGrpModel checkUserByKerberosSSO() {

		UtilisateurGrpModel userModel = new UtilisateurGrpModel();
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();

//		if (request.getUserPrincipal() != null && !"".equalsIgnoreCase(request.getUserPrincipal().getName())) {
//			String userLogin = request.getUserPrincipal().getName();
			String userLogin = "long.nguyen@bouygues-construction.com";
			if (userLogin != null) {
				userLogin = userLogin.toLowerCase();
				final UtilisateurGrp user = domUtilisateurService.findUserByIdentifiant(AppUtil.getLogonedUserNameSSOKerberos(userLogin));
				if (user != null) {
					userLogin = userLogin.toLowerCase();
					userModel.setId(user.getId());
					userModel.setIdentifiant(user.getIdentifiant());
					if (user.getBadmin() == null) {
						userModel.setBadmin(false);
					} else if (user.getBadmin() == false) {
						userModel.setBadmin(false);
					} else {
						userModel.setBadmin(true);
					}
					return userModel;
				}
			}
//		}
		return null;
	}

	@Override
	public void updateListUser(List<UtilisateurGrpModel> users, List<UtilisateurGrpModel> usersDelete, List<UtilisateurGrpModel> userModel,
			List<UtilisateurGrpModel> usersDeleteByChantier, Integer idChantier) {
		List<UtilisateurGrp> list = domUtilisateurService.findAll();
		List<UtilisateurGrp> listUserAdmin = domUtilisateurService.findUserAdmin();
		List<String> listIdentifiant = new ArrayList<String>();
		List<String> listIdentifiantAdmin = new ArrayList<String>();
		for (UtilisateurGrp user : listUserAdmin) {

			listIdentifiantAdmin.add(user.getIdentifiant());
		}
		if (usersDelete.size() > 0) {
			for (UtilisateurGrpModel userModel1 : usersDelete) {

				if (isExistIdentifiant(userModel1.getIdentifiant(), listIdentifiantAdmin) == true) {
					UtilisateurGrp u = domUtilisateurService.findUserByIdentifiant(userModel1.getIdentifiant());
					domUtilisateurService.updateUser(u, null);
				}
			}
		}
		for (UtilisateurGrp user : list) {
			listIdentifiant.add(user.getIdentifiant());
		}
		for (UtilisateurGrpModel usermodel : users) {
			if (isExistIdentifiant(usermodel.getIdentifiant(), listIdentifiant) == false) {
				UtilisateurGrp u = new UtilisateurGrp();
				u.setIdentifiant(usermodel.getIdentifiant());
				u.setBadmin(true);
				try {
					domUtilisateurService.save(u);
				} catch (DataConstraintException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				UtilisateurGrp u = domUtilisateurService.findUserByIdentifiant(usermodel.getIdentifiant());

				if (u.getBadmin() == null || u.getBadmin() == false) {
					domUtilisateurService.updateUser(u, true);
				}
			}
		}
		List<UtilisateurGrp> newlist = domUtilisateurService.findAll();
		List<UtilisateurGrp> listUserInchantier = domUtilisateurService.findUserByChantier(idChantier);
		List<String> listIdentifiantUser = new ArrayList<String>();
		List<String> newlistIdentifiant = new ArrayList<String>();

		for (UtilisateurGrp user : newlist) {
			newlistIdentifiant.add(user.getIdentifiant());
		}
		for (UtilisateurGrp user : listUserInchantier) {
			listIdentifiantUser.add(user.getIdentifiant());
		}
		for (UtilisateurGrpModel userC : usersDeleteByChantier) {
			if (isExistIdentifiant(userC.getIdentifiant(), listIdentifiantUser) == true) {
				UtilisateurGrp uC = domUtilisateurService.findUserByIdentifiant(userC.getIdentifiant());
				uC.getRoles();
				domRoleService.delete(new Rolepk(uC.getId(), idChantier));
			}
		}
		for (UtilisateurGrpModel userInchantier : userModel) {
			if (isExistIdentifiant(userInchantier.getIdentifiant(), newlistIdentifiant) == false) {
				UtilisateurGrp u = new UtilisateurGrp();
				Chantier c = new Chantier();
				c.setId(idChantier);
				u.setIdentifiant(userInchantier.getIdentifiant());
				try {
					domUtilisateurService.save(u);
				} catch (DataConstraintException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				List<Role> result = domRoleService.findRoleById(idChantier, u.getId());
				if (result.size() == 0) {
					Rolepk rolePK = new Rolepk(u.getId(), idChantier);
					Role r = new Role();
					r.setId(rolePK);
					r.setBcontributeur(userInchantier.getBcontributeur());
					r.setBlecteur(userInchantier.getBlecteur());
					try {
						domRoleService.save(r);
					} catch (DataConstraintException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				UtilisateurGrp u1 = domUtilisateurService.findUserByIdentifiant((userInchantier.getIdentifiant()));
				new Chantier();
				Rolepk rolePK = new Rolepk(u1.getId(), idChantier);
				Role r = new Role();
				r.setId(rolePK);
				r.setBcontributeur(userInchantier.getBcontributeur());
				r.setBlecteur(userInchantier.getBlecteur());
				try {
					domRoleService.update(r);
				} catch (DataConstraintException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		domUtilisateurService.deleteUser();
	}

	public boolean isExistIdentifiant(String identifiant, List<String> identifiants) {
		boolean isExist = false;
		if (identifiants.contains(identifiant)) {
			isExist = true;
		} else {
			isExist = false;
		}
		return isExist;
	}

	@Override
	public boolean checkIdentifiant(String identifiant) {
		return false;
	}

}
