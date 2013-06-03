package com.structis.vip.server.service.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.structis.vip.client.service.ClientControlService;
import com.structis.vip.client.service.ClientExternControllerControlService;
import com.structis.vip.server.bean.domain.Control;
import com.structis.vip.server.bean.domain.ExtControllerControl;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomControlService;
import com.structis.vip.server.service.domain.DomExternControllerControlService;
import com.structis.vip.shared.ControlFilter;
import com.structis.vip.shared.model.BaseModelDataActivable;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ExtControllerControlModel;
import com.structis.vip.shared.model.KeyValueModel;

@Service("clientControlService")
public class ClientControlServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientControlService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientControlServiceImpl.class);

    @Autowired
    private DomControlService domControlService;

    @Autowired
    private DomExternControllerControlService domExtControlService;

    @Autowired
    private ClientExternControllerControlService clientExtControlService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @Override
    public Boolean delete(ControlModel model) {
        Control dm = (Control) this.modelBeanMapper.map(model);
        this.domControlService.delete(dm);
        return true;
    }

    @Override
    public ControlModel insert(ControlModel model) {
        String fileName = FilenameUtils.getName(model.getRapport());
        model.setRapport(fileName);
        Control doc = (Control) this.modelBeanMapper.map(model);
        doc = this.domControlService.insert(doc);
        List<ExtControllerControlModel> eccs = model.getExternControllers();
        if (eccs != null && eccs.size() > 0) {
            this.clientExtControlService.insert(doc.getId(), eccs);
        }
        return (ControlModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public ControlModel update(ControlModel model) {

        this.domExtControlService.deleteByControl(model.getId());

        if (model.getExternControllers() != null && model.getExternControllers().size() > 0) {
            List<ExtControllerControlModel> eccs = model.getExternControllers();
            this.clientExtControlService.insert(eccs);
        }
        String fileName = FilenameUtils.getName(model.getRapport());
        model.setRapport(fileName);
        Control doc = (Control) this.modelBeanMapper.map(model);
        doc = this.domControlService.update(doc);
        return (ControlModel) this.modelBeanMapper.map(doc);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ControlModel> findAll() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return domControlService.findAll();
            }
        };
        return (List<ControlModel>) this.callManager(callBack);
    }

    @Override
    public PagingLoadResult<ControlModel> getControlsWithPaging(ControlFilter config) {
        List<ControlModel> all = new ArrayList<ControlModel>();
        all = this.getValidControls(config);

        List<Integer> allIds = this.getControlIdsByEntite(config);

        int limit = allIds.size();
        if (config.getLimit() > 0) {
            limit = Math.min(config.getLimit(), limit);
        }

        ArrayList<Integer> subListIds = new ArrayList<Integer>();
        for (int i = config.getOffset(); i < limit; i++) {
            subListIds.add(allIds.get(i));
        }
        
        List<ControlModel> pageResult = this.getControlsByIds(subListIds);

        if (config.getSortInfo().getSortField() != null) {
            final String sortField = config.getSortInfo().getSortField();
            if (sortField != null) {

                Collections.sort(all, config.getSortInfo().getSortDir().comparator(new Comparator<ControlModel>() {

                    @Override
                    public int compare(ControlModel p1, ControlModel p2) {
                        if (sortField.equals("controlType.label")) {
                            if (p1.getControlType() == null) {
                                return -1;
                            } else if (p2.getControlType() == null) {
                                return 1;
                            } else {
                                return p1.getControlType().getLabel().compareTo(p2.getControlType().getLabel());
                            }
                        } else if (sortField.equals("date")) {
                            if (p1.getDate() == null) {
                                return -1;
                            } else if (p2.getDate() == null) {
                                return 1;
                            } else {
                                return p1.getDate().compareTo(p2.getDate());
                            }
                        } else if (sortField.equals("collaborateur.fullname")) {
                            if (p1.getCollaborateur() == null) {
                                return -1;
                            } else if (p2.getCollaborateur() == null) {
                                return 1;
                            } else {
                                return p1.getCollaborateur().getFullname().compareTo(p2.getCollaborateur().getFullname());
                            }
                        } else if (sortField.equals("externController")) {
                            if (p1.getExtControlNames() == null) {
                                return -1;
                            } else if (p2.getExtControlNames() == null) {
                                return 1;
                            } else {
                                return p1.getExtControlNames().compareTo(p2.getExtControlNames());
                            }
                        }
                        return 0;
                    }
                }));
            }
        }

        return new BasePagingLoadResult<ControlModel>(pageResult, config.getOffset(), all.size());
    }

    private List<Integer> getControlIdsByEntite(ControlFilter filter) {
        String enId = filter.getEntite().getEntId();
        String perimetreId = (filter.getPerimetre() != null) ? filter.getPerimetre().getPerId() : null;
        return domControlService.getControlIdsByEntite(enId, perimetreId, getKeyList(filter.getTypes()), filter.getStartDate(),
                filter.getEndDate(), filter.getCodeProject(), getKeys(filter.getCaracteres()), filter.getControllerName(),
                filter.getPerimetreTreeModel(), filter.getUserRoles());
    }

    @Override
    public List<ControlModel> getControls(ControlFilter config) {
        List<ControlModel> all = this.getValidControls(config);
        return all;
    }

    @SuppressWarnings("unchecked")
    private List<ControlModel> getValidControls(final ControlFilter filter) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                String enId = filter.getEntite().getEntId();
                String perimetreId = (filter.getPerimetre() != null) ? filter.getPerimetre().getPerId() : null;

                List<Control> ret = domControlService.getControlsByEntite(enId, perimetreId, getKeyList(filter.getTypes()), filter.getStartDate(),
                        filter.getEndDate(), filter.getCodeProject(), getKeys(filter.getCaracteres()), filter.getControllerName(),
                        filter.getPerimetreTreeModel(), filter.getUserRoles());
                for (Control c : ret) {
                    List<ExtControllerControl> exts = domExtControlService.findByControl(c.getId());
                    StringBuffer extNames = new StringBuffer();
                    for (ExtControllerControl e : exts) {
                        extNames.append("<br>").append(e.getExternalController().getName());
                    }
                    if (extNames != null && extNames.length() > 0) {
                        c.setExtControlNames(extNames.toString().substring(4));
                    }
                }
                return ret;
            }
        };
        List<ControlModel> rets = (List<ControlModel>) this.callManager(callBack);

        return rets;
    }

    private <E> List<Integer> getKeyList(List<E> list) {
        List<Integer> keys = new ArrayList<Integer>();
        for (E l : list) {
            keys.add((((BaseModelDataActivable) l).getId()));
        }
        return keys;
    }

    private List<String> getKeys(List<KeyValueModel> list) {
        List<String> keys = new ArrayList<String>();
        for (KeyValueModel l : list) {
            keys.add(l.getKey());
        }
        return keys;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ControlModel> findByPerimetre(final String perId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return domControlService.findByPerimetre(perId);
            }
        };
        return (List<ControlModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    private List<ControlModel> getControlsByIds(final ArrayList<Integer> subListIds) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return domControlService.getControlsByIds(subListIds);
            }
        };
        
        return (List<ControlModel>) this.callManager(callBack);
    }
}
