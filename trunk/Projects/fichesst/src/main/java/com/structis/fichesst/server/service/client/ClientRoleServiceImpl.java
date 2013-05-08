package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientRoleService;
import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.DomRoleService;
import com.structis.fichesst.server.service.domain.DomUtilisateurService;
import com.structis.fichesst.shared.dto.RoleModel;

@Service("clientRoleService")
public class ClientRoleServiceImpl extends
		DependencyInjectionRemoteServiceServlet implements ClientRoleService {

	@Autowired
	DomUtilisateurService domUtilisateurService;
	@Autowired
	DomRoleService domRoleService;

	@Override
	public RoleModel findRoleById(final Integer idChantier, final Integer idUser) {
		List<Role> roles = domRoleService.findRoleById(idChantier, idUser);

		if (roles.size() > 0) {
			Role role = roles.get(0);
			RoleModel roleModel = new RoleModel();
			roleModel.setIdChantier(role.getId().getIdChantier());
			roleModel.setIdUtilisateurGrp(role.getId().getIdUtilisateurGrp());
			if (role.getBcontributeur() == null) {
				roleModel.setBcontributeur(false);
			} else if (role.getBcontributeur() == false) {
				roleModel.setBcontributeur(false);

			} else {
				roleModel.setBcontributeur(true);
			}
			if (role.getBlecteur() == null) {
				roleModel.setBlecteur(false);
			} else if (role.getBlecteur() == false) {
				roleModel.setBlecteur(false);

			} else {
				roleModel.setBlecteur(true);
			}
			return roleModel;
		}
		return null;
		
	}

	@Override
	public List<RoleModel> findRolesByIdUser(final Integer idUser) {
		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) throws Exception {
				List<Role> roles = domRoleService.findRolesByIdUser(idUser);

				return roles;
			}
		};
		List<RoleModel> roleModels = (List<RoleModel>) callManager(manager);
		return roleModels;
	}

}
