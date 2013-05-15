package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.FieldRuleModel;

@RemoteServiceRelativePath("springGwtServices/clientFieldRuleService")
public interface ClientFieldRuleService extends RemoteService {	
		List<FieldRuleModel> getRulesByDemGroup(Integer group);		
		List<FieldRuleModel> getRules(String entId, String ptyId, Integer dnaId, Integer langId);
		Boolean update(FieldRuleModel fieldRuleModel);
		FieldRuleModel insert(FieldRuleModel fieldRuleModel);
		List<FieldRuleModel> insertList(List<FieldRuleModel> list);
		Boolean deleteByGroup(Integer group);
		List<FieldRuleModel> updateList(List<FieldRuleModel> list);
}
