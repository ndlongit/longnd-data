package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.CollaborateurType;
import com.structis.vip.server.dao.support.GenericDao;

public interface CollaborateurTypeDao extends GenericDao<CollaborateurType, Integer> {

    public List<CollaborateurType> getAllCollaborateurs();

    public List<CollaborateurType> getCollaborateurTypeByEntite(String entiteId);

    public CollaborateurType insert(CollaborateurType doc);

    public CollaborateurType update(CollaborateurType doc);
}
