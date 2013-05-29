package com.structis.vip.server.service.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.argos.ws.WsOrganisationVIPType;
import com.structis.vip.client.service.ClientPerimetreService;
import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.Entite;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.bean.domain.PerimetreType;
import com.structis.vip.server.bean.domain.User;
import com.structis.vip.server.bean.domain.UserRole;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomCollaborateurService;
import com.structis.vip.server.service.domain.DomDelegationService;
import com.structis.vip.server.service.domain.DomEntiteJuridiqueService;
import com.structis.vip.server.service.domain.DomEntiteService;
import com.structis.vip.server.service.domain.DomPerimetreService;
import com.structis.vip.server.service.domain.DomUserRoleService;
import com.structis.vip.server.service.domain.DomUserService;
import com.structis.vip.server.util.ArgosUtil;
import com.structis.vip.shared.SharedConstant;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.exception.PerimetreException;
import com.structis.vip.shared.exception.SynchronizationException;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.PerimetreTypeModel;
import com.structis.vip.shared.model.UserRoleModel;

@Service("clientPerimetreService")
public class ClientPerimetreServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientPerimetreService {

    private static final long serialVersionUID = 8827164663509859578L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientPerimetreServiceImpl.class);

    @Autowired
    private DomEntiteService domEntiteService;

    @Autowired
    private DomPerimetreService domPerimetreService;

    @Autowired
    private DomEntiteJuridiqueService domEntiteJuridiqueService;

    @Autowired
    private DomDelegationService domDelegationService;

    @Autowired
    private DomCollaborateurService domCollaborateurService;

    @Autowired
    private DomUserRoleService domUserRoleService;

    @Autowired
    private DomUserService domUserService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    /**
     * Get list of perimetre for an entite
     * 
     * @param entiteModel
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<PerimetreModel> findPerimetreByEntite(final String entiteId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientPerimetreServiceImpl.this.domPerimetreService.findPerimetreByEntite(entiteId);
            }
        };
        return (List<PerimetreModel>) this.callManager(callBack);
    }

    /**
     * Get all 1st level perimetre
     * 
     * @param entiteModel
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PerimetreModel> findFirstLevelPerimetreByEntite(final String emId) {
        // ManagerCallBack callBack = new ManagerCallBack() {
        // public Object execute(Object... inputs) {
        // List<Perimetre> l = domPerimetreService.findFirstLevelPerimetreByEntite(emId);
        // for (Perimetre p : l) {
        // System.out.println("pppppp domain in client before casting: " + p.getId());
        // }
        // return l;
        // }
        // };
        // List<PerimetreModel> ls = (List<PerimetreModel>) callManager(callBack);
        // return ls;
        List<Perimetre> l = this.domPerimetreService.findFirstLevelPerimetreByEntite(emId);
        List<PerimetreModel> ls = (List<PerimetreModel>) this.modelBeanMapper.map(l);
        return ls;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PerimetreModel> findFirstLevelPerimetre() {
        List<Perimetre> l = this.domPerimetreService.findFirstLevelPerimetre();
        List<PerimetreModel> ls = (List<PerimetreModel>) this.modelBeanMapper.map(l);
        return ls;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PerimetreModel> getTreeModel(final String entiteId, final String perimetreId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientPerimetreServiceImpl.this.domPerimetreService.getTreeModel(entiteId, perimetreId);
            }
        };
        return (List<PerimetreModel>) this.callManager(callBack);
    }

    @Override
    public Boolean insert(PerimetreModel perimetreModel) {
        Perimetre perimetre = (Perimetre) this.modelBeanMapper.map(perimetreModel);
        return this.domPerimetreService.insert(perimetre);
    }

    @Override
    public String insert(String perimetreParentId, PerimetreModel perimetreModel) {
        Perimetre perimetre = (Perimetre) this.modelBeanMapper.map(perimetreModel);
        Perimetre perimetreParent = this.domPerimetreService.findById(perimetreParentId);
        Integer perId = this.domPerimetreService.getNewIndex();
        perimetre.setParent(perimetreParent);
        perimetre.setPerId(this.buildCorrectId(perId));
        if (this.domPerimetreService.insert(perimetre)) {
            return this.buildCorrectId(perId);
        } else {
            return "";
        }
    }

    private String buildCorrectId(Integer id) {
        String result = id.toString();
        for (int i = 0; i < 10 - id.toString().length(); i++) {
            result = "0" + result;
        }
        return result;
    }

    @Override
    public Boolean update(PerimetreModel perimetreModel) {
        Perimetre perimetre = (Perimetre) this.modelBeanMapper.map(perimetreModel);
        return this.domPerimetreService.update(perimetre);
    }

    @Override
    public PerimetreModel findById(String id) {
        Perimetre perimetre = this.domPerimetreService.findById(id);
        return (PerimetreModel) this.modelBeanMapper.map(perimetre);
    }

    @Override
    public Boolean deleteById(String id) throws PerimetreException {
        Perimetre perimetre = this.domPerimetreService.findById(id);
        List<Perimetre> lstCheck = this.domPerimetreService.getTreeModelByParent(perimetre.getEntite().getEntId(), id);
        if ((lstCheck != null) && (lstCheck.size() != 0)) {
            throw new PerimetreException(ExceptionType.PERIMETRE_HAS_CHILDREN);
        } else {
            List<Delegation> lstDelegation = this.domDelegationService.findByPerimetre(id);
            if ((lstDelegation != null) && (lstDelegation.size() != 0)) {
                throw new PerimetreException(ExceptionType.PERIMETRE_USED_IN_DELEGATION);
            } else {
                List<Collaborateur> lstCollaborateurs = this.domCollaborateurService.findByPerimetre(id);
                if ((lstCollaborateurs != null) && (lstCollaborateurs.size() != 0)) {
                    throw new PerimetreException(ExceptionType.PERIMETRE_USED_IN_COLLABORATEUR);
                } else {
                    List<UserRole> lstUserRoles = this.domUserRoleService.findByPerimetre(id);
                    if ((lstUserRoles != null) && (lstUserRoles.size() != 0)) {
                        throw new PerimetreException(ExceptionType.PERIMETRE_USED_IN_ROLE);
                    } else {
                        List<User> lstUsers = this.domUserService.findByPerimetre(id);
                        if ((lstUsers != null) && (lstUsers.size() != 0)) {
                            throw new PerimetreException(ExceptionType.PERIMETRE_USED_IN_USER);
                        } else {
                            this.domPerimetreService.delete(perimetre);
                        }
                    }
                }
            }
        }
        return true;
    }

    public class Holder {

        WsOrganisationVIPType value;
        PerimetreTypeModel type;

        public Holder(WsOrganisationVIPType value, PerimetreTypeModel type) {
            super();
            this.value = value;
            this.type = type;
        }

        public WsOrganisationVIPType getValue() {
            return this.value;
        }

        public void setValue(WsOrganisationVIPType value) {
            this.value = value;
        }

        public PerimetreTypeModel getType() {
            return this.type;
        }

        public void setType(PerimetreTypeModel type) {
            this.type = type;
        }
    }

    @Override
    public Map<String, List<PerimetreModel>> sync(String entiteId, String parentId, List<PerimetreTypeModel> types) throws SynchronizationException {
        List<PerimetreModel> lstReturn = new ArrayList<PerimetreModel>();
        List<String> lstArgosPerimetres = new ArrayList<String>();
        Perimetre perimetreTopParent = this.domPerimetreService.findById(parentId);

        List<Holder> lstSolve = new ArrayList<Holder>();
        for (PerimetreTypeModel type : types) {
            try {
                // Get data from web service
                WsOrganisationVIPType[] result = ArgosUtil.getData(type.getName(), parentId);
                for (WsOrganisationVIPType arg0 : result) {
                    lstSolve.add(new Holder(arg0, type));
                }
            } catch (Exception e) {
                throw new SynchronizationException(ExceptionType.PERIMETRE_SYNC_FAILED);
            }
        }

        List<List<Holder>> lstSolved = ArgosUtil.getSolvedData(parentId, lstSolve);
        for (List<Holder> arg0 : lstSolved) {
            for (Holder arg : arg0) {
                try {
                    PerimetreType typeInsert;
                    if (arg.getValue().getIdTypeOrg() != null) {
                        typeInsert = (PerimetreType) this.modelBeanMapper.map(arg.getType());
                    } else {
                        typeInsert = new PerimetreType();
                        typeInsert.setPtyId("0000000032");
                    }
                    // Find existing perimetre
                    Perimetre perimetreFound = this.domPerimetreService.findById(arg.getValue().getIdArgos());
                    // Find existing parent perimetre
                    Perimetre perimetreParent = this.domPerimetreService.findById(arg.getValue().getIdOrgParente());
                    if (perimetreParent == null) {
                        perimetreParent = perimetreTopParent;
                    }
                    // Setup entite
                    Entite entite = this.domEntiteService.findById(entiteId);
                    if (perimetreFound == null) {
                        // Setup data
                        Perimetre perimetreNew = new Perimetre();
                        perimetreNew.setPerId(arg.getValue().getIdArgos());
                        perimetreNew.setName(ArgosUtil.transcodification(arg.getValue().getDesignation()));
                        perimetreNew.setType(typeInsert);
                        perimetreNew.setParent(perimetreParent);
                        perimetreNew.setEntite(entite);
                        perimetreNew.setLanguage(entite.getLanguage());
                        perimetreNew.setEntiteJuridique(this.domEntiteJuridiqueService.getDefaultByEntiteId(entiteId));
                        perimetreNew.setArgosName(arg.getValue().getDesignation());

                        // Insert to database
                        this.domPerimetreService.insert(perimetreNew);
                        lstReturn.add((PerimetreModel) this.modelBeanMapper.map(perimetreNew));
                        lstArgosPerimetres.add(perimetreNew.getPerId());
                    } else {
                        if ((!typeInsert.getPtyId().equals(perimetreFound.getType().getPtyId()))
                                || (!arg.getValue().getDesignation().equals(perimetreFound.getArgosName()))
                                || (((perimetreParent == null) && (perimetreFound.getParent() != null))
                                        || ((perimetreParent != null) && (perimetreFound.getParent() == null)) || ((perimetreParent != null)
                                        && (perimetreFound.getParent() != null) && (!perimetreParent.getPerId().equals(
                                        perimetreFound.getParent().getPerId()))))) {
                            // Setup data
                            perimetreFound.setParent(perimetreParent);
                            perimetreFound.setType(typeInsert);
                            perimetreFound.setArgosName(arg.getValue().getDesignation());

                            // Update to database
                            this.domPerimetreService.update(perimetreFound);
                            lstReturn.add((PerimetreModel) this.modelBeanMapper.map(perimetreFound));
                            lstArgosPerimetres.add(perimetreFound.getPerId());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Map<String, List<PerimetreModel>> results = new HashMap<String, List<PerimetreModel>>();

        if (lstReturn == null) {
            results.put(SharedConstant.SUCCESS_LIST, new ArrayList<PerimetreModel>());
        } else {
            results.put(SharedConstant.SUCCESS_LIST, lstReturn);
        }

        List<PerimetreModel> perimetresCanNotDeletedNotInArgos = this.findAndDeleteOrphanPerimetresNotInArgosByEntite(entiteId, parentId,
                lstArgosPerimetres);

        if (perimetresCanNotDeletedNotInArgos == null) {
            results.put(SharedConstant.ERROR_LIST, new ArrayList<PerimetreModel>());
        } else {
            results.put(SharedConstant.ERROR_LIST, perimetresCanNotDeletedNotInArgos);
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    private List<PerimetreModel> findAndDeleteOrphanPerimetresNotInArgosByEntite(final String entiteId, final String parentId,
            final List<String> idsInArgos) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientPerimetreServiceImpl.this.domPerimetreService.findAndDeleteOrphanPerimetresNotInArgosByEntite(entiteId, parentId,
                        idsInArgos);
            }
        };
        return (List<PerimetreModel>) this.callManager(callBack);
    }

    @Override
    public List<PerimetreTreeModel> getTreeModelByParent(String entiteId, List<UserRoleModel> userRoles, PerimetreTreeModel perimetreTree) {
        List<PerimetreTreeModel> lstResult = new ArrayList<PerimetreTreeModel>();
        List<Perimetre> lstSolve = new ArrayList<Perimetre>();
        if ((perimetreTree == null) || (perimetreTree.getIsEntite())) {
            lstSolve = this.domPerimetreService.getTreeModelByParent(entiteId, null);
        } else {
            lstSolve = this.domPerimetreService.getTreeModelByParent(entiteId, perimetreTree.getPerId());
        }
        for (Perimetre perimetre : lstSolve) {
            PerimetreTreeModel perimetreTreeAdd = new PerimetreTreeModel();
            perimetreTreeAdd.setPerId(perimetre.getPerId());
            perimetreTreeAdd.setName(perimetre.getName());
            perimetreTreeAdd.setEntiteId(entiteId);
            if (perimetreTree != null) {
                perimetreTreeAdd.setPath(perimetreTree.getPath() + " > " + perimetre.getName());
                perimetreTreeAdd.setLevel(perimetreTree.getLevel() + 1);
                perimetreTreeAdd.setParent(perimetreTree.getPerId());
                perimetreTreeAdd.setParentName(perimetreTree.getName());
            } else {
                perimetreTreeAdd.setPath(perimetre.getName());
                perimetreTreeAdd.setLevel(1);
                perimetreTreeAdd.setParent(null);
            }
            if (perimetre.getType() != null) {
                perimetreTreeAdd.setType(perimetre.getType().getPtyId());
                perimetreTreeAdd.setTypeName(perimetre.getType().getName());
            }
            List<Perimetre> lstCheck = this.domPerimetreService.getTreeModelByParent(entiteId, perimetre.getPerId());
            if ((lstCheck != null) && (lstCheck.size() != 0)) {
                perimetreTreeAdd.setIsParent(true);
            } else {
                perimetreTreeAdd.setIsParent(false);
            }
            perimetreTreeAdd.setIsEntite(false);
            for (UserRoleModel userRole : userRoles) {
                if (userRole.getRole().isApplicationAdmin()) {
                    perimetreTreeAdd.setPermissionByRole(userRole.getRole());
                } else if ((userRole.getPerimetre() != null) && (userRole.getPerimetre().getPerId().equals(perimetre.getPerId()))) {
                    perimetreTreeAdd.setPermissionByRole(userRole.getRole());
                }
            }
            if (perimetreTree != null) {
                perimetreTreeAdd.setPermissionByParent(perimetreTree);
            }
            lstResult.add(perimetreTreeAdd);
        }
        return lstResult;
    }

    @Override
    public List<PerimetreModel> findFirstLevelPerimetreByUserRoles(String emId, boolean isAdmin, List<UserRoleModel> userRoles) {
        List<PerimetreModel> listPerimetres = new ArrayList<PerimetreModel>();
        if ((userRoles != null) && (userRoles.size() != 0)) {
            for (UserRoleModel userRole : userRoles) {
                if (((isAdmin) && (userRole.getRole().isUoAdmin())) || (!isAdmin)) {
                    if (userRole.getRole().isApplicationAdmin()) {
                        return this.findFirstLevelPerimetreByEntite(emId);
                    } else {
                        if (userRole.getPerimetre() != null) {
                            boolean isAdd = true;
                            if (listPerimetres.size() != 0) {
                                List<PerimetreModel> listRun = new ArrayList<PerimetreModel>();
                                listRun.addAll(listPerimetres);
                                for (PerimetreModel perimetre : listRun) {
                                    if (this.isParent(userRole.getPerimetre().getPerId(), perimetre.getPerId())) {
                                        listPerimetres.remove(perimetre);
                                    }
                                    if (this.isParent(perimetre.getPerId(), userRole.getPerimetre().getPerId())) {
                                        isAdd = false;
                                    }
                                    if (userRole.getPerimetre().getPerId().equals(perimetre.getPerId())) {
                                        isAdd = false;
                                    }
                                }
                            }
                            if (isAdd) {
                                listPerimetres.add(userRole.getPerimetre());
                            }
                        }
                    }
                }
            }
        }
        return listPerimetres;
    }

    private boolean isParent(String parentId, String childId) {
        List<Perimetre> parents = this.domPerimetreService.findParents(childId);
        for (Perimetre perimetre : parents) {
            if (perimetre.getPerId().equals(parentId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<PerimetreTreeModel> getTreeModelById(String perimetreId, List<UserRoleModel> userRoles) {
        List<PerimetreTreeModel> lstReturn = new ArrayList<PerimetreTreeModel>();

        Perimetre find = this.domPerimetreService.findById(perimetreId);
        if (find != null) {
            PerimetreModel perimetre = (PerimetreModel) this.modelBeanMapper.map(find);
            PerimetreTreeModel perimetreTreeAdd = new PerimetreTreeModel(perimetre, userRoles);
            perimetreTreeAdd.setEntiteId(perimetre.getEntite().getEntId());
            perimetreTreeAdd.setPath(perimetre.getName());
            perimetreTreeAdd.setLevel(1);
            perimetreTreeAdd.setParent(null);

            if (perimetre.getType() != null) {
                perimetreTreeAdd.setType(perimetre.getType().getPtyId());
                perimetreTreeAdd.setTypeName(perimetre.getType().getName());
            }
            List<Perimetre> lstCheck = this.domPerimetreService.getTreeModelByParent(perimetre.getEntite().getEntId(), perimetre.getPerId());
            if ((lstCheck != null) && (lstCheck.size() != 0)) {
                perimetreTreeAdd.setIsParent(true);
            } else {
                perimetreTreeAdd.setIsParent(false);
            }
            perimetreTreeAdd.setIsEntite(false);

            lstReturn.add(perimetreTreeAdd);
        }
        return lstReturn;
    }

    @Override
    public Integer hasReferenceInDelegationOrControlOrPerimetre(String perId) {
        return this.domPerimetreService.hasReferenceInDelegationOrControlOrPerimetre(perId);
    }

    @Override
    public Integer hasReferenceInUserCollaborateur(String perId) {
        return this.domPerimetreService.hasReferenceInUserCollaborateur(perId);
    }

    @Override
    public void clearReferenceToPerimetreInUserCollaborateur(String perId) {
        this.domPerimetreService.clearReferenceToPerimetreInUserCollaborateur(perId);
    }

    @Override
    public List<PerimetreModel> findByPerimetreParent(final String perId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientPerimetreServiceImpl.this.domPerimetreService.findByPerimetreParent(perId);
            }
        };
        return (List<PerimetreModel>) this.callManager(callBack);
    }
}
