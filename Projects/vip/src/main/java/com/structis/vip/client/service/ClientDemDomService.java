package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.DemDomModel;

@RemoteServiceRelativePath("springGwtServices/clientDemDomService")
public interface ClientDemDomService extends RemoteService {
	List<DemDomModel> getAllDemDomsByDemGroup(Integer group);

	DemDomModel insert(DemDomModel demDom);

	Integer insert(List<DemDomModel> demDoms, Integer group);
	
	Boolean deleteByGroup(Integer group);
}