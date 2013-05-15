package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.NatureException;
import com.structis.vip.shared.model.DelegationNatureModel;

@RemoteServiceRelativePath("springGwtServices/clientDelegationNatureService")
public interface ClientDelegationNatureService extends RemoteService {
	
	List<DelegationNatureModel> findNatureByEntite(String entiteId);
	List<DelegationNatureModel> findNatureByEntiteAndPerimetreType(String entiteId, String ptyId, Boolean isSub);
	Boolean delete(DelegationNatureModel model) throws NatureException;	
	DelegationNatureModel insert(DelegationNatureModel model);
	DelegationNatureModel update(DelegationNatureModel model);
	
	List<DelegationNatureModel> findNatureForNew(String perId, String entId);
	List<DelegationNatureModel> findNatureForNew(String perId, String entId, String ptyId,Boolean isSub);
}