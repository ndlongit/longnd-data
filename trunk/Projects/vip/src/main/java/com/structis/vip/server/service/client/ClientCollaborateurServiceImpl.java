package com.structis.vip.server.service.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.structis.vip.client.service.ClientCollaborateurService;
import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.DelegantPerimetre;
import com.structis.vip.server.bean.domain.DelegatairePerimetre;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomCollaborateurService;
import com.structis.vip.server.service.domain.DomDelegationService;
import com.structis.vip.server.service.domain.DomExternControllerService;
import com.structis.vip.shared.exception.CollaborateurException;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.CollaborateurFormationModel;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegantPerimetreModel;
import com.structis.vip.shared.model.DelegatairePerimetreModel;
import com.structis.vip.shared.model.KeyValueModel;

@Service("clientCollaborateurService")
public class ClientCollaborateurServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientCollaborateurService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientCollaborateurServiceImpl.class);

    @Autowired
    private DomCollaborateurService domCollaborateurService;

    @Autowired
    private DomDelegationService domDelegationService;

    @Autowired
    private DomExternControllerService domExternControllerService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurModel> getAllCollaborateurs() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.getAllCollaborateurs();
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurModel> getAllCollaborateursByEntiteId(final String entiteId, final Boolean sortie) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.getAllCollaborateursByEntiteId(entiteId, sortie);
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurModel> getAllCollaborateursByEntiteId(final String entiteId, final Boolean sortie, final String sortedField,
            final Integer sortDir, final Integer start, final Integer pageSize) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.getAllCollaborateursByEntiteId(entiteId, sortie, sortedField,
                        sortDir, start, pageSize);
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurModel> getAllDelegantsByEntiteId(final String entiteId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.getAllDelegantsByEntiteId(entiteId);
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurModel> getAllDelegatairesByEntiteId(final String entiteId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.getAllDelegatairesByEntiteId(entiteId);
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

    @Override
    public CollaborateurModel update(CollaborateurModel model) {
        // domExtControlService.deleteByControl(model.getId());
        //
        // if (model.getExternControllers() != null && model.getExternControllers().size() > 0) {
        // List<ExtControllerControlModel> eccs = model.getExternControllers();
        // clientExtControlService.insert(eccs);
        // }
        // String fileName = FilenameUtils.getName(model.getRapport());
        // model.setRapport(fileName);
        // Control doc = (Control) modelBeanMapper.map(model);
        // doc = domControlService.update(doc);
        // return (ControlModel) modelBeanMapper.map(doc);
        if (model.isChangeDelegantPerimetres() == 2) {
            this.domCollaborateurService.deleteDelegantPerimetres(model.getId());
        }
        if (model.isChangeDelegantPerimetres() > 0) {
            if (model.getDelegantPerimetres() != null && model.getDelegantPerimetres().size() > 0) {
                List<DelegantPerimetreModel> items = model.getDelegantPerimetres();
                this.insertDelegantPerimetres(items);
            }
        }
        if (model.isChangeDelegatairePerimetres() == 2) {
            this.domCollaborateurService.deleteDelegatairePerimetres(model.getId());
        }
        if (model.isChangeDelegatairePerimetres() > 0) {
            if (model.getDelegatairePerimetres() != null && model.getDelegatairePerimetres().size() > 0) {
                List<DelegatairePerimetreModel> items = model.getDelegatairePerimetres();
                this.insertDelegatairePerimetres(items);
            }
        }

        Collaborateur col = (Collaborateur) this.modelBeanMapper.map(model);
        col = this.domCollaborateurService.update(col);
        return (CollaborateurModel) this.modelBeanMapper.map(col);
    }

    private void insertDelegatairePerimetres(List<DelegatairePerimetreModel> items) {
        for (DelegatairePerimetreModel item : items) {
            DelegatairePerimetre doc = (DelegatairePerimetre) this.modelBeanMapper.map(item);
            this.domCollaborateurService.insertDelegatairePerimetre(doc);
        }
    }

    private void insertDelegantPerimetres(List<DelegantPerimetreModel> items) {
        for (DelegantPerimetreModel item : items) {
            DelegantPerimetre doc = (DelegantPerimetre) this.modelBeanMapper.map(item);
            this.domCollaborateurService.insertDelegantPerimetre(doc);
        }
    }

    @Override
    public CollaborateurModel updateAndFormation(CollaborateurModel model) {
        Collaborateur col = (Collaborateur) this.modelBeanMapper.map(model);
        if (model.isChangeDelegantPerimetres() == 2) {
            this.domCollaborateurService.deleteDelegantPerimetres(model.getId());
        }
        if (model.isChangeDelegantPerimetres() > 0) {
            if (model.getDelegantPerimetres() != null && model.getDelegantPerimetres().size() > 0) {
                List<DelegantPerimetreModel> items = model.getDelegantPerimetres();
                this.insertDelegantPerimetres(items);
            }
        }
        if (model.isChangeDelegatairePerimetres() == 2) {
            this.domCollaborateurService.deleteDelegatairePerimetres(model.getId());
        }
        if (model.isChangeDelegatairePerimetres() > 0) {
            if (model.getDelegatairePerimetres() != null && model.getDelegatairePerimetres().size() > 0) {
                List<DelegatairePerimetreModel> items = model.getDelegatairePerimetres();
                this.insertDelegatairePerimetres(items);
            }
        }
        col = this.domCollaborateurService.updateAndFormation(col);
        return (CollaborateurModel) this.modelBeanMapper.map(col);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurModel> findByName(final String name, final String entityId, final Boolean sortie) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.findByName(name, entityId, sortie);
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurModel> findByName(final String name, final String entityId, final Boolean sortie, final String sortedField,
            final Integer sortDir, final Integer start, final Integer pageSize) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.findByName(name, entityId, sortie, sortedField, sortDir, start,
                        pageSize);
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

    @Override
    public Boolean delete(CollaborateurModel model) throws CollaborateurException {
        Collaborateur dm = (Collaborateur) this.modelBeanMapper.map(model);
        List<Delegation> lstDelegations = this.domDelegationService.findByCollaborateur(model.getId());
        if ((lstDelegations != null) && (lstDelegations.size() != 0)) {
            throw new CollaborateurException(ExceptionType.COLLABORATEUR_USED_IN_DELEGATION);
        }
        this.domCollaborateurService.delete(dm);
        this.domCollaborateurService.deleteFormation(model.getId());
        return true;
    }

    @Override
    public CollaborateurModel insert(CollaborateurModel model) {
        Collaborateur doc = (Collaborateur) this.modelBeanMapper.map(model);
        doc = this.domCollaborateurService.insert(doc);
        if (model.getDelegantPerimetres() != null && model.getDelegantPerimetres().size() > 0) {
            List<DelegantPerimetreModel> items = model.getDelegantPerimetres();
            this.insertDelegantPerimetres(doc.getId(), items);
        }
        if (model.getDelegatairePerimetres() != null && model.getDelegatairePerimetres().size() > 0) {
            List<DelegatairePerimetreModel> items = model.getDelegatairePerimetres();
            this.insertDelegatairePerimetres(doc.getId(), items);
        }
        return (CollaborateurModel) this.modelBeanMapper.map(doc);
    }

    private void insertDelegatairePerimetres(Integer id, List<DelegatairePerimetreModel> items) {
        for (DelegatairePerimetreModel item : items) {
            DelegatairePerimetre doc = (DelegatairePerimetre) this.modelBeanMapper.map(item);
            Collaborateur col = new Collaborateur();
            col.setId(id);
            doc.setDelegataire(col);
            this.domCollaborateurService.insertDelegatairePerimetre(doc);
        }
    }

    private void insertDelegantPerimetres(Integer id, List<DelegantPerimetreModel> items) {
        for (DelegantPerimetreModel item : items) {
            DelegantPerimetre doc = (DelegantPerimetre) this.modelBeanMapper.map(item);
            Collaborateur col = new Collaborateur();
            col.setId(id);
            doc.setDelegant(col);
            this.domCollaborateurService.insertDelegantPerimetre(doc);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurFormationModel> findByCollaborateurId(final Integer colId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.findByCollaborateurId(colId);
            }
        };
        return (List<CollaborateurFormationModel>) this.callManager(callBack);
    }

    @Override
    public CollaborateurModel updatePerimetreDelegant(final Integer colId, final String perId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.updatePerimetreDelegant(colId, perId);
            }
        };
        return (CollaborateurModel) this.callManager(callBack);
    }

    @Override
    public CollaborateurModel updatePerimetreDelegataire(final Integer colId, final String perId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.updatePerimetreDelegataire(colId, perId);
            }
        };
        return (CollaborateurModel) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurModel> getAllDelegantsByPerimeter(final String perId, final String entiteId,final Boolean recusion) {
        if (perId == null) {
            return null;
        }
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.getAllDelegantsByPerimeter(perId, entiteId, recusion);
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurModel> getAllDelegatairesByPerimeter(final String perId, final String entiteId,final Boolean recusion) {
        if (perId == null) {
            return null;
        }
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.getAllDelegatairesByPerimeter(perId, entiteId,recusion);
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

    @Override
    public List<CollaborateurFormationModel> findByFormationId(final Integer forId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.findByFormationId(forId);
            }
        };
        return (List<CollaborateurFormationModel>) this.callManager(callBack);
    }

    @Override
    public PagingLoadResult<CollaborateurModel> getAllCollaborateursByEntiteIdPaging(PagingLoadConfig config, String entId) {
        String sortedField = null;
        if (config.getSortInfo().getSortField() != null) {
            sortedField = config.getSortInfo().getSortField();
        }
        Integer sortDir = 0;
        if (config.getSortInfo().getSortDir() != null) {
            if (config.getSortInfo().getSortDir().equals(Style.SortDir.DESC)) {
                sortDir = 1;
            }
        }
        int start = config.getOffset();
        int limit = config.getLimit();
        long total = 0;
        List<CollaborateurModel> all = new ArrayList<CollaborateurModel>();
        Boolean sortie = config.get("displaySortie");
        if (sortie == null) {
            sortie = false;
        }
        if (config.get("filterName") != null) {
            all = this.findByName(config.get("filterName").toString(), entId, sortie, sortedField, sortDir, start, limit);
            total = this.countByName(config.get("filterName").toString(), entId, sortie);
        } else {
            all = this.getAllCollaborateursByEntiteId(entId, sortie, sortedField, sortDir, start, limit);
            total = this.countAllCollaborateursByEntiteId(entId, sortie);
        }

        // for (CollaborateurModel item: all) {
        // List<DelegantPerimetre> dps = domCollaborateurService.findPerimetresByDelegant(item.getId());
        // StringBuffer extNames = new StringBuffer();
        // for (DelegantPerimetre e: dps) {
        // extNames.append("<br>").append(e.getPerimetre().getName());
        // }
        // if (extNames != null && extNames.length() > 0) {
        // item.setDelegantPerimetreNames(extNames.toString().substring(4));
        // }
        // List<DelegatairePerimetre> drs = domCollaborateurService.findPerimetresByDelegataire(item.getId());
        // StringBuffer names = new StringBuffer();
        // for (DelegatairePerimetre e: drs) {
        // names.append("<br>").append(e.getPerimetre().getName());
        // }
        // if (names != null && names.length() > 0) {
        // item.setDelegatairePerimetreNames(names.toString().substring(4));
        // }
        // }
        /*
         * if (config.getSortInfo().getSortField() != null) { final String sortField = config.getSortInfo().getSortField(); if (sortField != null) {
         * Collections.sort(all, config.getSortInfo().getSortDir().comparator(new Comparator<CollaborateurModel>() { public int
         * compare(CollaborateurModel p1, CollaborateurModel p2) { if (sortField.equals("fullnameNoSeparater")) { return
         * p1.getFullnameNoSeparater().compareTo(p2.getFullnameNoSeparater()); } else if (sortField.equals("isDelegant")) { if (p1.getIsDelegant() ==
         * null) { if (p2.getIsDelegant() == null) { return 0; } else { return -1; } } else { if (p2.getIsDelegant() == null) { return 1; } else {
         * return p1.getIsDelegant().compareTo(p2.getIsDelegant()); } } } else if (sortField.equals("isDelegataire")) { if (p1.getIsDelegataire() ==
         * null) { if (p2.getIsDelegataire() == null) { return 0; } else { return -1; } } else { if (p2.getIsDelegataire() == null) { return 1; } else
         * { return p1.getIsDelegataire().compareTo(p2.getIsDelegataire()); } } } else if (sortField.equals("delegantPerimetreNames") ) {
         * System.out.println("p1 :" + p1.getDelegantPerimetreNames() + "; p2:"+ p2.getDelegantPerimetreNames()); if (p1.getDelegantPerimetreNames()
         * == null) { if (p2.getDelegantPerimetreNames() == null) { return 0; } else { return -1; } } else { if (p2.getDelegantPerimetreNames() ==
         * null) { return 1; } else { return p1.getDelegantPerimetreNames().compareTo(p2.getDelegantPerimetreNames()); } } } else if
         * (sortField.equals("delegatairesPerimetreNames")) { if (p1.getDelegatairePerimetreNames() == null) { if (p2.getDelegatairePerimetreNames()
         * == null) { return 0; } else { return -1; } } else { if (p2.getDelegatairePerimetreNames() == null) { return 1; } else { return
         * p1.getDelegatairePerimetreNames().compareTo(p2.getDelegatairePerimetreNames()); } } } return 0; } })); } } ArrayList<CollaborateurModel>
         * sublist = new ArrayList<CollaborateurModel>(); int start = config.getOffset(); int limit = all.size(); if (config.getLimit() > 0) { limit =
         * Math.min(start + config.getLimit(), limit); } for (int i = config.getOffset(); i < limit; i++) { CollaborateurModel item = all.get(i); //
         * List<DelegantPerimetre> dps = domCollaborateurService.findPerimetresByDelegant(item.getId()); // StringBuffer extNames = new
         * StringBuffer(); // for (DelegantPerimetre e: dps) { // extNames.append("<br>").append(e.getPerimetre().getName()); // } // if (extNames !=
         * null && extNames.length() > 0) { // item.setDelegantPerimetreNames(extNames.toString().substring(4)); // } // List<DelegatairePerimetre>
         * drs = domCollaborateurService.findPerimetresByDelegataire(item.getId()); // StringBuffer names = new StringBuffer(); // for
         * (DelegatairePerimetre e: drs) { // names.append("<br>").append(e.getPerimetre().getName()); // } // if (names != null && names.length() >
         * 0) { // item.setDelegatairePerimetreNames(names.toString().substring(4)); // } sublist.add(item); }
         */
        return new BasePagingLoadResult<CollaborateurModel>(all, config.getOffset(), (int) total);
    }

    private long countAllCollaborateursByEntiteId(final String entId, final Boolean sortie) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.countAllCollaborateursByEntiteId(entId, sortie);
            }
        };
        return (Long) this.callManager(callBack);
    }

    private long countByName(final String name, final String entId, final Boolean sortie) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.countByName(name, entId, sortie);
            }
        };
        return (Long) this.callManager(callBack);
    }

    /*
     * @Override public PagingLoadResult<CollaborateurModel> getAllCollaborateursByEntiteIdPaging( PagingLoadConfig config, String entId) {
     * List<CollaborateurModel> all = new ArrayList<CollaborateurModel>(); Boolean sortie = config.get("displaySortie"); if (sortie == null) { sortie
     * = false; } if (config.get("filterName") != null) { all = findByName(config.get("filterName").toString(), entId, sortie); } else { all =
     * getAllCollaborateursByEntiteId(entId, sortie); } for (CollaborateurModel item: all) { List<DelegantPerimetre> dps =
     * domCollaborateurService.findPerimetresByDelegant(item.getId()); StringBuffer extNames = new StringBuffer(); for (DelegantPerimetre e: dps) {
     * extNames.append("<br>").append(e.getPerimetre().getName()); } if (extNames != null && extNames.length() > 0) {
     * item.setDelegantPerimetreNames(extNames.toString().substring(4)); } List<DelegatairePerimetre> drs =
     * domCollaborateurService.findPerimetresByDelegataire(item.getId()); StringBuffer names = new StringBuffer(); for (DelegatairePerimetre e: drs) {
     * names.append("<br>").append(e.getPerimetre().getName()); } if (names != null && names.length() > 0) {
     * item.setDelegatairePerimetreNames(names.toString().substring(4)); } } if (config.getSortInfo().getSortField() != null) { final String sortField
     * = config.getSortInfo().getSortField(); if (sortField != null) { Collections.sort(all, config.getSortInfo().getSortDir().comparator(new
     * Comparator<CollaborateurModel>() { public int compare(CollaborateurModel p1, CollaborateurModel p2) { if
     * (sortField.equals("fullnameNoSeparater")) { return p1.getFullnameNoSeparater().compareTo(p2.getFullnameNoSeparater()); } else if
     * (sortField.equals("isDelegant")) { if (p1.getIsDelegant() == null) { if (p2.getIsDelegant() == null) { return 0; } else { return -1; } } else {
     * if (p2.getIsDelegant() == null) { return 1; } else { return p1.getIsDelegant().compareTo(p2.getIsDelegant()); } } } else if
     * (sortField.equals("isDelegataire")) { if (p1.getIsDelegataire() == null) { if (p2.getIsDelegataire() == null) { return 0; } else { return -1; }
     * } else { if (p2.getIsDelegataire() == null) { return 1; } else { return p1.getIsDelegataire().compareTo(p2.getIsDelegataire()); } } } else if
     * (sortField.equals("delegantPerimetreNames") ) { System.out.println("p1 :" + p1.getDelegantPerimetreNames() + "; p2:"+
     * p2.getDelegantPerimetreNames()); if (p1.getDelegantPerimetreNames() == null) { if (p2.getDelegantPerimetreNames() == null) { return 0; } else {
     * return -1; } } else { if (p2.getDelegantPerimetreNames() == null) { return 1; } else { return
     * p1.getDelegantPerimetreNames().compareTo(p2.getDelegantPerimetreNames()); } } } else if (sortField.equals("delegatairesPerimetreNames")) { if
     * (p1.getDelegatairePerimetreNames() == null) { if (p2.getDelegatairePerimetreNames() == null) { return 0; } else { return -1; } } else { if
     * (p2.getDelegatairePerimetreNames() == null) { return 1; } else { return
     * p1.getDelegatairePerimetreNames().compareTo(p2.getDelegatairePerimetreNames()); } } } return 0; } })); } } ArrayList<CollaborateurModel>
     * sublist = new ArrayList<CollaborateurModel>(); int start = config.getOffset(); int limit = all.size(); if (config.getLimit() > 0) { limit =
     * Math.min(start + config.getLimit(), limit); } for (int i = config.getOffset(); i < limit; i++) { CollaborateurModel item = all.get(i);
     * sublist.add(item); } return new BasePagingLoadResult<CollaborateurModel>(sublist, config.getOffset(), all.size()); }
     */

    @Override
    public List<CollaborateurModel> getDelegantsByNatureAndPerimetre(final String perId, final String ptyId, final String entId,
            final Integer natureId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.getDelegantsByNatureAndPerimetre(perId, ptyId, entId, natureId);
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<KeyValueModel> getAllControleursByEntiteId(final String entiteId) {
        List<KeyValueModel> rets = new ArrayList<KeyValueModel>();

        ManagerCallBack callBack2 = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domExternControllerService.getDelegatairesKeyValueByEntiteId(entiteId);
            }
        };
        rets.addAll((List<KeyValueModel>) this.callManager(callBack2));
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.getDelegatairesKeyValueByEntiteId(entiteId);
            }
        };
        rets.addAll((List<KeyValueModel>) this.callManager(callBack));
        Collections.sort(rets, new Comparator<KeyValueModel>() {

            @Override
            public int compare(KeyValueModel p1, KeyValueModel p2) {
                if (p1.getValue() == null) {
                    return -1;
                } else if (p2.getValue() == null) {
                    return 1;
                } else {
                    return p1.getValue().compareTo(p2.getValue());
                }
            }
        });
        return rets;
    }

    @Override
    public CollaborateurModel findById(Integer colId) {
        Collaborateur col = this.domCollaborateurService.findById(colId);
        if (col.getIsDelegant() != null && col.getIsDelegant() > 0) {
            List<DelegantPerimetre> dps = this.domCollaborateurService.findPerimetresByDelegant(colId);
            StringBuffer extNames = new StringBuffer();
            for (DelegantPerimetre e : dps) {
                extNames.append("<br>").append(e.getPerimetre().getName());
            }
            if (extNames != null && extNames.length() > 0) {
                col.setDelegantPerimetreNames(extNames.toString().substring(4));
            }
        }
        if (col.getIsDelegataire() != null && col.getIsDelegataire() > 0) {
            List<DelegatairePerimetre> drs = this.domCollaborateurService.findPerimetresByDelegataire(colId);
            StringBuffer names = new StringBuffer();
            for (DelegatairePerimetre e : drs) {
                names.append("<br>").append(e.getPerimetre().getName());
            }
            if (names != null && names.length() > 0) {
                col.setDelegatairesPerimetreNames(names.toString().substring(4));
            }
        }
        return (CollaborateurModel) this.modelBeanMapper.map(col);
    }

    @Override
    public CollaborateurModel getAndUpdate(CollaborateurModel model) {
        Collaborateur col = this.domCollaborateurService.findById(model.getId());
        col.setIsDelegant(model.getIsDelegant());
        col.setIsDelegataire(model.getIsDelegataire());
        if (model.getIsDelegant() == null || model.getIsDelegant() == 0) {
            col.setPerimetreDelegant(null);
        } else {
            if (model.getPerimetreDelegant() != null) {
                Perimetre p = new Perimetre();
                p.setPerId(model.getPerimetreDelegant().getPerId());
                col.setPerimetreDelegant(p);
            }
        }
        if (model.getIsDelegataire() == null || model.getIsDelegataire() == 0) {
            col.setPerimetreDelegataire(null);
        } else {
            if (model.getPerimetreDelegataire() != null) {
                Perimetre p = new Perimetre();
                p.setPerId(model.getPerimetreDelegataire().getPerId());
                col.setPerimetreDelegataire(p);
            }
        }
        col = this.domCollaborateurService.update(col);

        if (model.isChangeDelegantPerimetres() == 2) {
            this.domCollaborateurService.deleteDelegantPerimetres(model.getId());
        }
        if (model.isChangeDelegantPerimetres() > 0) {
            if (model.getDelegantPerimetres() != null && model.getDelegantPerimetres().size() > 0) {
                List<DelegantPerimetreModel> items = model.getDelegantPerimetres();
                this.insertDelegantPerimetres(items);
            }
            List<DelegantPerimetre> delegants = this.domCollaborateurService.findPerimetresByDelegant(col.getId());
            // update names
            StringBuffer extNames = new StringBuffer();
            for (DelegantPerimetre e : delegants) {
                extNames.append("<br>").append(e.getPerimetre().getName());
            }
            if (extNames.length() >= 4) {
                col.setDelegantPerimetreNames(extNames.substring(4));// (arg0.getPerimetreDelegant());
            } else {
                col.setDelegantPerimetreNames(null);
            }
        }
        if (model.isChangeDelegatairePerimetres() == 2) {
            this.domCollaborateurService.deleteDelegatairePerimetres(model.getId());
        }
        if (model.isChangeDelegatairePerimetres() > 0) {
            if (model.getDelegatairePerimetres() != null && model.getDelegatairePerimetres().size() > 0) {
                List<DelegatairePerimetreModel> items = model.getDelegatairePerimetres();
                this.insertDelegatairePerimetres(items);
            }
            List<DelegatairePerimetre> delegataires = this.domCollaborateurService.findPerimetresByDelegataire(col.getId());
            // update names
            StringBuffer deNames = new StringBuffer();
            for (DelegatairePerimetre e : delegataires) {
                deNames.append("<br>").append(e.getPerimetre().getName());
            }
            if (deNames.length() >= 4) {
                col.setDelegatairesPerimetreNames(deNames.substring(4));// (arg0.getPerimetreDelegant());
            } else {
                col.setDelegatairesPerimetreNames(null);
            }
        }
        return (CollaborateurModel) this.modelBeanMapper.map(col);
    }

    @Override
    public List<CollaborateurModel> findByPerimetre(final String perId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurServiceImpl.this.domCollaborateurService.findByPerimetre(perId);
            }
        };
        return (List<CollaborateurModel>) this.callManager(callBack);
    }

}
