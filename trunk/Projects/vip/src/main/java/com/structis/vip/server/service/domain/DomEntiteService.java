package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.Entite;
import com.structis.vip.server.service.domain.core.GenericEntityService;
import com.structis.vip.shared.model.UserModel;

public interface DomEntiteService extends GenericEntityService<Entite, String> {
	
	public  List<Entite> getAllEntites();

	public Entite getEntityByUser(Integer userId);
	public  Entite getEntityByUser(UserModel user);

	public Boolean insert(Entite entite);

	public Boolean update(Entite entite);

	public Entite findById(String id);

	public List<Entite> findByLanguageId(Integer languageId);
}
