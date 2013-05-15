package com.structis.vip.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.DelegationFilter;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.model.DelegationDelegataireModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;

@RemoteServiceRelativePath("springGwtServices/clientDelegationService")
public interface ClientDelegationService extends RemoteService {
	List<DelegationModel> getAllDelegations();
	DelegationModel insert(DelegationModel delegationModel) throws DelegationException;
	DelegationModel insertRenew(DelegationModel delegationModel) throws DelegationException;
	Boolean update(DelegationModel delegationModel) throws DelegationException;
	Boolean updateStatusAuto(DelegationModel delegationModel) throws DelegationException;
	List<DelegationModel> updateStatus() throws DelegationException;
	DelegationModel findById(Integer id);	
	List<DelegationModel> getValidDelegations(DelegationFilter filter);	
	List<DelegationModel> getValidDelegationsByEntite(DelegationFilter filter);	
	Boolean delete(DelegationModel dl) throws DelegationException;;	
	Boolean hasDelegation (DelegationNatureModel dn, PerimetreModel p);
	Boolean hasOtherDelegation (DelegationModel dl, DelegationNatureModel dn, PerimetreModel p, EntiteModel en);
	DelegationModel renewDelegation(DelegationModel delegationModel);
	List<DelegationModel> findByPerimetre(String perimetreId);
	PagingLoadResult<DelegationModel> getValidDelegationsByEntiteByPaging(
			DelegationFilter filter);
	List<DelegationDelegataireModel> findDelegataires(Integer delId,String perId, String entId);
	String getDelegataires(Integer delId);
}