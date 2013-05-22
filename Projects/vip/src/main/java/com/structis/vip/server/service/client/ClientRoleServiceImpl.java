package com.structis.vip.server.service.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientRoleService;
import com.structis.vip.server.bean.domain.Role;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomRoleService;
import com.structis.vip.shared.model.RoleModel;
import com.structis.vip.shared.model.UserModel;

@Service("clientRoleService")
public class ClientRoleServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientRoleService {

    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientRoleServiceImpl.class);

    @Autowired
    private DomRoleService domRoleService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @Override
    public List<RoleModel> getRoles(UserModel userModel) {
        List<RoleModel> lstResult = new ArrayList<RoleModel>();
        for (Role role : this.domRoleService.getRoles()) {
            RoleModel roleModel = (RoleModel) this.modelBeanMapper.map(role);
            if (userModel.isSuperUser()) {
                if (!roleModel.isSuperAdmin()) {
                    lstResult.add(roleModel);
                }
            } else if (userModel.isApplicationAdmin()) {
                if (!roleModel.isApplicationAdmin()) {
                    lstResult.add(roleModel);
                }
            } else if (userModel.isUoAdmin()) {
                if (!roleModel.isUoAdmin()) {
                    lstResult.add(roleModel);
                }
            }
        }
        return lstResult;
    }
}
