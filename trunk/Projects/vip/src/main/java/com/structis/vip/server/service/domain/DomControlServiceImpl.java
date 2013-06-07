package com.structis.vip.server.service.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Control;
import com.structis.vip.server.dao.ControlDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

@Service("domControlService")
public class DomControlServiceImpl extends GenericEntityServiceImpl<Control, Integer> implements DomControlService {

    @Autowired
    @Qualifier("controlDao")
    private ControlDao controlDao;

    @Override
    public GenericDao<Control, Integer> getDao() {
        return this.controlDao;
    }

    @Override
    public Control getNew() {
        return new Control();
    }

    @Override
    public Control getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public Control insert(Control nature) {
        return this.controlDao.insert(nature);
    }

    @Override
    public Control update(Control nature) {
        return this.controlDao.update(nature);
    }

    @Override
    public List<Control> findAll() {
        return this.find();
    }

    @Override
    public List<Control> getControlsByEntite(String enId, String perimetreId, List<Integer> keyList, Date startDate, Date endDate, String codeProjet,
            List<String> caracteres, String controllerName, PerimetreTreeModel perimetreTreeModel, List<UserRoleModel> userRoles) {
        return this.controlDao.getControlsByEntite(enId, perimetreId, keyList, startDate, endDate, codeProjet, caracteres, controllerName,
                perimetreTreeModel, userRoles);
    }

    @Override
    public List<Control> findByPerimetre(String perId) {
        return this.controlDao.getControlsByPerimetre(perId);
    }

    @Override
    public List<Integer> getControlIdsByEntite(String enId, String perimetreId, List<Integer> keyList, Date startDate, Date endDate,
            String codeProject, List<String> keys, String controllerName, PerimetreTreeModel perimetreTreeModel, List<UserRoleModel> userRoles) {
        return controlDao.getControlIdsByEntite(enId, perimetreId, keyList, startDate, endDate, codeProject, keys, controllerName,
                perimetreTreeModel, userRoles);
    }

    @Override
    public List<Control> getControlsByIds(ArrayList<Integer> Ids) {
        List<Control> results = controlDao.getControlsByIds(Ids);
        return results;
    }
}
