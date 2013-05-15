package com.structis.vip.server.service.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.client.service.ClientFieldRuleService;
import com.structis.vip.server.bean.domain.FieldRule;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomFieldRuleService;
import com.structis.vip.shared.model.FieldRuleModel;

@Service("clientFieldRuleService")
public class ClientFieldRuleServiceImpl extends DependencyInjectionRemoteServiceServlet
		implements ClientFieldRuleService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DomFieldRuleService domFieldRuleService;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientFieldRuleServiceImpl.class);
		
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;


	@Override
	public List<FieldRuleModel> getRulesByDemGroup(final Integer group) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domFieldRuleService.getRulesByDemGroup(group);
			}
		};
		return (List<FieldRuleModel>) callManager(callBack);
	}


	@Override
	public List<FieldRuleModel> getRules(final String entId, final String ptyId,
			final Integer dnaId, final Integer langId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domFieldRuleService.getRules(entId, ptyId, dnaId, langId);
			}
		};
		return (List<FieldRuleModel>) callManager(callBack);
	}


	@Override
	public Boolean update(FieldRuleModel fieldRuleModel) {
		FieldRule fieldRule = (FieldRule) modelBeanMapper.map(fieldRuleModel);
		return domFieldRuleService.update(fieldRule);			
	}


	@Override
	public FieldRuleModel insert(FieldRuleModel fieldRuleModel) {
		FieldRule fieldRule = (FieldRule) modelBeanMapper.map(fieldRuleModel);
		fieldRule = domFieldRuleService.insert(fieldRule);
		return (FieldRuleModel) modelBeanMapper.map(fieldRule);
	}


	@Transactional
	public List<FieldRuleModel> insertList(List<FieldRuleModel> list) {
		List<FieldRuleModel> ret = new ArrayList<FieldRuleModel>();
		if (list != null && list.size() > 0) {
			for (FieldRuleModel fieldRuleModel : list) {
				FieldRule fieldRule = (FieldRule) modelBeanMapper.map(fieldRuleModel);
				fieldRule = domFieldRuleService.insert(fieldRule);
				ret.add((FieldRuleModel) modelBeanMapper.map(fieldRule));
			}
		}
		return ret;
	}

	@Override
	public Boolean deleteByGroup(Integer group) {
		return domFieldRuleService.deleteByGroup(group);
	}


	@Transactional
	public List<FieldRuleModel> updateList(List<FieldRuleModel> list) {
		List<FieldRuleModel> ret = new ArrayList<FieldRuleModel>();
		if (list != null && list.size() > 0) {
			for (FieldRuleModel fieldRuleModel : list) {
				update(fieldRuleModel);
			}
		}
		return ret;
	}
}
