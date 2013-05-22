package com.structis.vip.server.service.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientEntiteService;
import com.structis.vip.server.bean.domain.Entite;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomEntiteService;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserModel;
import com.structis.vip.shared.model.UserRoleModel;

@Service("clientEntiteService")
public class ClientEntiteServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientEntiteService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientEntiteServiceImpl.class);

    @Autowired
    private DomEntiteService domEntiteService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<EntiteModel> getAllEntites() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientEntiteServiceImpl.this.domEntiteService.find();
            }
        };
        return (List<EntiteModel>) this.callManager(callBack);
    }

    @Override
    public EntiteModel getEntiteByUser(final UserModel user) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientEntiteServiceImpl.this.domEntiteService.getEntityByUser(user);
            }
        };
        return (EntiteModel) this.callManager(callBack);
    }

    @Override
    public Boolean insert(EntiteModel delegationModel) {
        Entite entite = (Entite) this.modelBeanMapper.map(delegationModel);
        return this.domEntiteService.insert(entite);
    }

    @Override
    public Boolean update(EntiteModel entiteModel) {
        Entite entite = (Entite) this.modelBeanMapper.map(entiteModel);
        return this.domEntiteService.update(entite);
    }

    @Override
    public EntiteModel findById(String id) {
        Entite entite = this.domEntiteService.findById(id);
        return (EntiteModel) this.modelBeanMapper.map(entite);
    }

    @Override
    public List<PerimetreTreeModel> getTreeModelById(String id, List<UserRoleModel> userRoles) {
        List<PerimetreTreeModel> lstResult = new ArrayList<PerimetreTreeModel>();
        PerimetreTreeModel perimetreTree = new PerimetreTreeModel();
        Entite entite = this.domEntiteService.findById(id);
        perimetreTree.setName(entite.getName());
        perimetreTree.setEntiteId(entite.getEntId());
        perimetreTree.setPath(entite.getName());
        perimetreTree.setUoname(entite.getName());
        perimetreTree.setLevel(1);
        perimetreTree.setType("");
        perimetreTree.setTypeName("");
        perimetreTree.setIsParent(true);
        perimetreTree.setIsEntite(true);
        for (UserRoleModel userRole : userRoles) {
            if (userRole.getRole().isApplicationAdmin()) {
                perimetreTree.setPermissionByRole(userRole.getRole());
            }
        }
        lstResult.add(perimetreTree);
        return lstResult;
    }
}
