package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.CollaborateurType;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomCollaborateurTypeService extends GenericEntityService<CollaborateurType, Integer> {
	public List<CollaborateurType> getAllCollaborateurType();
	public List<CollaborateurType> getCollaborateurTypeByEntite(String entiteId);
	public CollaborateurType insert(CollaborateurType doc);
	public CollaborateurType update(CollaborateurType doc);
}
