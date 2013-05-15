package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.EntiteJuridiqueException;
import com.structis.vip.shared.model.EntiteJuridiqueModel;

@RemoteServiceRelativePath("springGwtServices/clientEntiteJuridiqueService")
public interface ClientEntiteJuridiqueService extends RemoteService {
	EntiteJuridiqueModel findById(Integer id);

	EntiteJuridiqueModel insert(EntiteJuridiqueModel model);

	EntiteJuridiqueModel update(EntiteJuridiqueModel model);

	List<EntiteJuridiqueModel> findByEntiteId(String entId);

	boolean delete(EntiteJuridiqueModel model) throws EntiteJuridiqueException;

	EntiteJuridiqueModel getDefaultByEntiteId(String entId);	
}
