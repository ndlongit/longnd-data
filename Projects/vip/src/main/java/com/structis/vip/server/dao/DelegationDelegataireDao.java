package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.DelegationDelegataire;
import com.structis.vip.server.dao.support.GenericDao;

public interface DelegationDelegataireDao extends GenericDao<DelegationDelegataire, Integer> {

    DelegationDelegataire update(DelegationDelegataire entity);

    DelegationDelegataire insert(DelegationDelegataire entity);

    void deleteByDelegation(Integer delId);

    List<Collaborateur> findDelegatairesByDelegation(int delId);
}
