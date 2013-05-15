package com.structis.vip.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.DelegationFilter;
import com.structis.vip.shared.model.DelegationDelegataireModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;

public interface ClientDelegationServiceAsync {
	public static class Util {
		private static ClientDelegationServiceAsync instance = GWT.create(ClientDelegationService.class);

		public static ClientDelegationServiceAsync getInstance() {
			return instance;
		}
	}

	void getAllDelegations(AsyncCallback<List<DelegationModel>> callback);
	void insert(DelegationModel delegationModel, AsyncCallback<DelegationModel> callback);	
	void update(DelegationModel delegationModel, AsyncCallback<Boolean> callback);	
	void findById(Integer id, AsyncCallback<DelegationModel> callback);	
	void getValidDelegations(DelegationFilter filter, AsyncCallback<List<DelegationModel>> callback);
	void getValidDelegationsByEntite(DelegationFilter filter, AsyncCallback<List<DelegationModel>> callback);
	void delete(DelegationModel dl, AsyncCallback<Boolean> callback);
		
	void hasDelegation (DelegationNatureModel dn, PerimetreModel p, AsyncCallback<Boolean> callback);
	void hasOtherDelegation (DelegationModel dm, DelegationNatureModel dn, PerimetreModel p, EntiteModel en, AsyncCallback<Boolean> callback);
	
	void renewDelegation(DelegationModel delegationModel, AsyncCallback<DelegationModel> callback);
	void updateStatus(AsyncCallback<List<DelegationModel>> callback);
	void insertRenew(DelegationModel delegationModel,
			AsyncCallback<DelegationModel> callback);
	void updateStatusAuto(DelegationModel delegationModel,
			AsyncCallback<Boolean> callback);
	void findByPerimetre(String perimetreId, AsyncCallback<List<DelegationModel>> callback);
	void getValidDelegationsByEntiteByPaging(DelegationFilter filter,
			AsyncCallback<PagingLoadResult<DelegationModel>> callback);	
	void findDelegataires(Integer delId, String perId, String entId,
			AsyncCallback<List<DelegationDelegataireModel>> asyncCallback);
	void getDelegataires(Integer delId, AsyncCallback<String> asyncCallback);
}