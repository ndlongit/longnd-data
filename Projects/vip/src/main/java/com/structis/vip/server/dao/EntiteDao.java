package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.Entite;
import com.structis.vip.server.dao.support.GenericDao;

public interface EntiteDao extends GenericDao<Entite, String> {

    Boolean update(Entite entite);

    List<Entite> findByLanguageId(Integer languageId);

}
