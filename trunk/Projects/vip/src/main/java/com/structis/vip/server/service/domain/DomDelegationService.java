package com.structis.vip.server.service.domain;

import java.util.Date;
import java.util.List;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.DelegationDelegataire;
import com.structis.vip.server.service.domain.core.GenericEntityService;
import com.structis.vip.shared.model.DelegationDelegataireModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public interface DomDelegationService extends GenericEntityService<Delegation, Integer> {
	public List<Delegation> getAllDelegations();
	public Delegation insert(Delegation delegationModel);	
	public Delegation findById(Integer id);
	public List<Delegation> getValidDelegations(String perId, Integer natureId,
			Integer typeId, Integer statusId, Integer delegantId,
			Integer delegataireId, Date startDate, Date endDate,
			Boolean sep, Boolean conjointe, Boolean isDisplayAllLevel);
	
	public List<Delegation> getValidDelegationsByEntite(String enId, String perId, Integer natureId,
			Integer typeId, Integer statusId, Integer delegantId,
			Integer delegataireId, Date startDate, Date endDate,
			Boolean sep, Boolean conjointe, Boolean isDisplayAllLevel);
	Boolean update(Delegation delegation);
	public Boolean hasDelegation(Integer id, String id2);
	List<Delegation> getValidDelegationsByEntite(String enId, String perId,
			List<Integer> natureIds, List<Integer> typeIds,
			List<Integer> statusIds, List<Integer> delegantIds,
			List<Integer> delegataireIds, Date startDate, Date endDate,
			Boolean sep, Boolean conjointe, Boolean isDisplayAllLevel);
	public Boolean hasOtherDelegation(Integer delId, Integer dnId, String perId, String enId);
	public Boolean delegantIsDelegataireOfDelegation(Integer delegantId, Integer delegationId);
	public Integer subDelegantIsDelegataireOfParent(Integer delId);
	public List<Delegation> getAllDelegationById(List<Integer> ids); 
	public List<Delegation> getDelegationsByEntite(String enId, String perimetreId,
			List<Integer> keyList, List<Integer> keyList2,
			List<Integer> keyList3, List<Integer> keyList4,
			List<Integer> keyList5, Date startDate, Date endDate, Boolean sep,
			Boolean conjointe, Boolean isDisplayAllLevel, PerimetreTreeModel perimetreTreeModel, List<UserRoleModel> userRoles);
	public Delegation renewDelegation(Delegation delegation);
	public List<Delegation> updateStatus();
	public List<Delegation> findByPerimetre(String perimetreId);
	public List<Delegation> findByCollaborateur(Integer colId);
	public List<Delegation> getChildrenById(Integer delId);
	public List<Delegation> findByEntiteJuriId(Integer entiteJuriId);
	public List<Delegation> findByNatureId(Integer natureId);	
	public List<DelegationDelegataire> findDelegataires(Integer delId, String perId, String entId);
	public Delegation insert(Delegation delegation,
			List<DelegationDelegataireModel> dds);
	public boolean update(Delegation delegation,
			List<DelegationDelegataireModel> dds);
	public String findDelegataireNames(Integer delId);
	public List<Collaborateur> findDelegatairesByDelegation(int parseInt);
}
