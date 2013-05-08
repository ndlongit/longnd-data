package com.structis.fichesst.server.service.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientChantierService;
import com.structis.fichesst.client.service.ClientRoleService;
import com.structis.fichesst.client.service.ClientUtilsateurGrpService;
import com.structis.fichesst.client.service.LoadAppliService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.shared.config.ApplicationContext;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

@Service("loadappli")
public class LoadAppliServiceImpl extends DependencyInjectionRemoteServiceServlet implements LoadAppliService{
	@Autowired
	ClientChantierService serviceChantier;
	@Autowired
	ClientUtilsateurGrpService serviceUser;
	@Autowired
	ClientRoleService serviceRole;
	@Override
	public ApplicationContext loadappli() {
		ApplicationContext context=new ApplicationContext();
		List<ChantierModel> chantierModels=serviceChantier.findAll();
		Map<String,ChantierModel> mapChantier=new HashMap<String, ChantierModel>();
		for (ChantierModel chantierModel : chantierModels) {
			mapChantier.put(chantierModel.getNom(), chantierModel);
		}
		context.setMapChantier((HashMap<String,ChantierModel>) mapChantier);
		return context;
	}

}
