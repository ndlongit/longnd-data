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
public class ClientFieldRuleServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientFieldRuleService {

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

            @Override
            public Object execute(Object... inputs) {
                return ClientFieldRuleServiceImpl.this.domFieldRuleService.getRulesByDemGroup(group);
            }
        };
        return (List<FieldRuleModel>) this.callManager(callBack);
    }

    @Override
    public List<FieldRuleModel> getRules(final String entId, final String ptyId, final Integer dnaId, final Integer langId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientFieldRuleServiceImpl.this.domFieldRuleService.getRules(entId, ptyId, dnaId, langId);
            }
        };
        return (List<FieldRuleModel>) this.callManager(callBack);
    }

    @Override
    public Boolean update(FieldRuleModel fieldRuleModel) {
        FieldRule fieldRule = (FieldRule) this.modelBeanMapper.map(fieldRuleModel);
        return this.domFieldRuleService.update(fieldRule);
    }

    @Override
    public FieldRuleModel insert(FieldRuleModel fieldRuleModel) {
        FieldRule fieldRule = (FieldRule) this.modelBeanMapper.map(fieldRuleModel);
        fieldRule = this.domFieldRuleService.insert(fieldRule);
        return (FieldRuleModel) this.modelBeanMapper.map(fieldRule);
    }

    @Override
    @Transactional
    public List<FieldRuleModel> insertList(List<FieldRuleModel> list) {
        List<FieldRuleModel> ret = new ArrayList<FieldRuleModel>();
        if (list != null && list.size() > 0) {
            for (FieldRuleModel fieldRuleModel : list) {
                FieldRule fieldRule = (FieldRule) this.modelBeanMapper.map(fieldRuleModel);
                fieldRule = this.domFieldRuleService.insert(fieldRule);
                ret.add((FieldRuleModel) this.modelBeanMapper.map(fieldRule));
            }
        }
        return ret;
    }

    @Override
    public Boolean deleteByGroup(Integer group) {
        return this.domFieldRuleService.deleteByGroup(group);
    }

    @Override
    @Transactional
    public List<FieldRuleModel> updateList(List<FieldRuleModel> list) {
        List<FieldRuleModel> ret = new ArrayList<FieldRuleModel>();
        if (list != null && list.size() > 0) {
            for (FieldRuleModel fieldRuleModel : list) {
                this.update(fieldRuleModel);
            }
        }
        return ret;
    }
}
