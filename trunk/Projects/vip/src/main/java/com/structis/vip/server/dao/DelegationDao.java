package com.structis.vip.server.dao;

import java.util.Date;
import java.util.List;

import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.DelegationDelegataire;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public interface DelegationDao extends GenericDao<Delegation, Integer> {

    List<Delegation> getValidDelegations(String perId, Integer natureId, Integer typeId, Integer statusId, Integer delegantId, Integer delegataireId,
            Date startDate, Date endDate, Boolean sep, Boolean conjointe, Boolean isDisplayAllLevel);

    Delegation update(Delegation entity);

    Delegation insert(Delegation entity);

    List<Delegation> getValidDelegations(String enId, String perId, Integer natureId, Integer typeId, Integer statusId, Integer delegantId,
            Integer delegataireId, Date startDate, Date endDate, Boolean sep, Boolean conjointe, Boolean isDisplayAllLevel);

    Boolean hasDelegation(Integer natureId, String perimetreId);

    List<Delegation> getValidDelegations(String enId, String perId, List<Integer> natureIds, List<Integer> typeIds, List<Integer> statusIds,
            List<Integer> delegantIds, List<Integer> delegataireId, Date startDate, Date endDate, Boolean sep, Boolean conjointe,
            Boolean isDisplayAllLevel);

    Boolean hasOtherDelegation(Integer delegationId, Integer natureId, String perimetreId, String entiteId);

    Boolean hasRenewDelegation(Integer delegationId);

    Boolean delegantIsDelegataireOfDelegation(Integer delegantId, Integer delegationId);

    Integer subDelegantIsDelegataireOfParent(Integer delId);

    List<Integer> getDelegationIds(String enId, String perId, List<Integer> natureIds, List<Integer> typeIds, List<Integer> statusIds,
            List<Integer> delegantIds, List<Integer> delegataireIds, Date startDate, Date endDate, Boolean sep, Boolean conjointe,
            Boolean isDisplayAllLevel, PerimetreTreeModel perimetreTreeModel, List<UserRoleModel> userRoles);

    List<Delegation> getAllDelegationById(List<Integer> ids);

    List<Delegation> getDelegationWithStatus(Integer delStatusVenir);

    List<Delegation> findByPerimetre(String perimetreId);

    List<Delegation> findByCollaborateur(Integer colId);

    List<Delegation> getChildrenById(Integer delId);

    List<Delegation> findByEntiteJuriId(Integer entiteJuriId);

    List<Delegation> findByNatureId(Integer natureId);

    List<DelegationDelegataire> findDelegataires(Integer delId, String perId, String entId);

    String findDelegataireNames(Integer delId);
}
