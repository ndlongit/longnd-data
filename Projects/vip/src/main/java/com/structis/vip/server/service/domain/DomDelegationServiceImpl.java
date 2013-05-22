package com.structis.vip.server.service.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.DelegationDelegataire;
import com.structis.vip.server.bean.domain.DelegationStatus;
import com.structis.vip.server.core.Constants;
import com.structis.vip.server.core.DelegationConstants;
import com.structis.vip.server.dao.DelegationDao;
import com.structis.vip.server.dao.DelegationDelegataireDao;
import com.structis.vip.server.dao.DelegationModelDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;
import com.structis.vip.server.util.DataCopier;
import com.structis.vip.shared.model.DelegationDelegataireModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

@Service("domDelegationService")
public class DomDelegationServiceImpl extends GenericEntityServiceImpl<Delegation, Integer> implements DomDelegationService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomDelegationServiceImpl.class);

    @Autowired
    @Qualifier("delegationDao")
    private DelegationDao delegationDao;

    @Autowired
    @Qualifier("delegationDelegataireDao")
    private DelegationDelegataireDao delegationDelegataireDao;

    @Autowired
    @Qualifier("delegationModelDao")
    private DelegationModelDao delegationModelDao;

    @Override
    public GenericDao<Delegation, Integer> getDao() {
        return this.delegationDao;
    }

    @Override
    public Delegation getNew() {
        return new Delegation();
    }

    @Override
    public Delegation getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public List<Delegation> getAllDelegations() {
        return this.find();
    }

    @Override
    public Delegation insert(Delegation delegation) {
        Delegation dl = this.delegationDao.insert(delegation);
        return dl;
    }

    @Override
    public List<Delegation> getValidDelegations(String perId, Integer natureId, Integer typeId, Integer statusId, Integer delegantId,
            Integer delegataireId, Date startDate, Date endDate, Boolean sep, Boolean conjointe, Boolean isDisplayAllLevel) {
        return this.delegationDao.getValidDelegations(perId, natureId, typeId, statusId, delegantId, delegataireId, startDate, endDate, sep,
                conjointe, isDisplayAllLevel);
    }

    @Override
    public List<Delegation> getValidDelegationsByEntite(String enId, String perId, Integer natureId, Integer typeId, Integer statusId,
            Integer delegantId, Integer delegataireId, Date startDate, Date endDate, Boolean sep, Boolean conjointe, Boolean isDisplayAllLevel) {
        return this.delegationDao.getValidDelegations(enId, perId, natureId, typeId, statusId, delegantId, delegataireId, startDate, endDate, sep,
                conjointe, isDisplayAllLevel);
    }

    @Override
    public List<Delegation> getValidDelegationsByEntite(String enId, String perId, List<Integer> natureIds, List<Integer> typeIds,
            List<Integer> statusIds, List<Integer> delegantIds, List<Integer> delegataireIds, Date startDate, Date endDate, Boolean sep,
            Boolean conjointe, Boolean isDisplayAllLevel) {
        return this.delegationDao.getValidDelegations(enId, perId, natureIds, typeIds, statusIds, delegantIds, delegataireIds, startDate, endDate,
                sep, conjointe, isDisplayAllLevel);
    }

    @Override
    @Transactional
    public Boolean update(Delegation dl) {
        this.delegationDao.update(dl);
        return true;
    }

    @Override
    public Delegation findById(Integer id) {
        return this.getByPrimaryKey(id);
    }

    @Override
    public Boolean hasDelegation(Integer natureId, String perimetreId) {
        return this.delegationDao.hasDelegation(natureId, perimetreId);
    }

    @Override
    public Boolean hasOtherDelegation(Integer delId, Integer dnId, String perId, String enId) {
        // check rule whether the delegation model accept multiple delegation
        boolean isMultiDelegation = this.delegationModelDao.hasMultipleDelegation(dnId, enId, perId);
        if (isMultiDelegation)
            return false;
        return this.delegationDao.hasOtherDelegation(delId, dnId, perId, enId);
    }

    @Override
    public Boolean delegantIsDelegataireOfDelegation(Integer delegantId, Integer delegationId) {
        return this.delegationDao.delegantIsDelegataireOfDelegation(delegantId, delegationId);
    }

    @Override
    public Integer subDelegantIsDelegataireOfParent(Integer delId) {
        return this.delegationDao.subDelegantIsDelegataireOfParent(delId);
    }

    @Override
    public List<Delegation> getDelegationsByEntite(String enId, String perimetreId, List<Integer> natureIds, List<Integer> typeIds,
            List<Integer> statusIds, List<Integer> delegantIds, List<Integer> delegataireIds, Date startDate, Date endDate, Boolean sep,
            Boolean conjointe, Boolean isDisplayAllLevel, PerimetreTreeModel perimetreTreeModel, List<UserRoleModel> userRoles) {
        return this.delegationDao.getDelegations(false, enId, perimetreId, natureIds, typeIds, statusIds, delegantIds, delegataireIds, startDate,
                endDate, sep, conjointe, isDisplayAllLevel, perimetreTreeModel, userRoles);
    }

    @Override
    public List<Delegation> getAllDelegationById(List<Integer> ids) {
        return this.delegationDao.getAllDelegationById(ids);
    }

    @Override
    @Transactional
    public Delegation renewDelegation(Delegation delegation) {

        DelegationStatus oldStatus = new DelegationStatus();
        oldStatus.setId(DelegationConstants.DEL_STATUS_OBSOLETE);
        delegation.setDelegationStatus(oldStatus);
        this.delegationDao.update(delegation);

        Delegation newDel = new Delegation();
        // new delegation
        try {
            DataCopier.copyNotIdFields(delegation, newDel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        newDel.setId(null);
        Calendar cal = Calendar.getInstance();
        cal.setTime(newDel.getEndDate());
        cal.add(Calendar.DATE, 1);
        newDel.setStartDate(new Date());
        newDel.setEndDate(cal.getTime());
        DelegationStatus status = new DelegationStatus();
        status.setId(DelegationConstants.DEL_STATUS_ESTABLISH);
        newDel.setDelegationStatus(status);

        return this.delegationDao.insert(newDel);

    }

    @Override
    public List<Delegation> updateStatus() {
        List<Delegation> delegations = this.delegationDao.getDelegationWithStatus(DelegationConstants.DEL_STATUS_VENIR);
        List<Delegation> ret = new ArrayList<Delegation>();
        if (delegations.size() > 0) {
            DelegationStatus status = new DelegationStatus();
            status.setId(DelegationConstants.DEL_STATUS_SIGNEE);
            for (Delegation delegation : delegations) {
                delegation.setDelegationStatus(status);
                this.update(delegation);

                ret.add(delegation);
            }
        }
        return ret;
    }

    @Override
    public List<Delegation> findByPerimetre(String perimetreId) {
        return this.delegationDao.findByPerimetre(perimetreId);
    }

    @Override
    public List<Delegation> getChildrenById(Integer delId) {
        return this.delegationDao.getChildrenById(delId);
    }

    @Override
    public List<Delegation> findByCollaborateur(Integer colId) {
        return this.delegationDao.findByCollaborateur(colId);
    }

    @Override
    public List<Delegation> findByEntiteJuriId(Integer entiteJuriId) {
        return this.delegationDao.findByEntiteJuriId(entiteJuriId);
    }

    @Override
    public List<Delegation> findByNatureId(Integer natureId) {
        return this.delegationDao.findByNatureId(natureId);
    }

    @Override
    public List<DelegationDelegataire> findDelegataires(Integer delId, String perId, String entId) {
        return this.delegationDao.findDelegataires(delId, perId, entId);
    }

    @Override
    @Transactional
    public Delegation insert(Delegation delegation, List<DelegationDelegataireModel> dds) {
        Delegation dl = this.delegationDao.insert(delegation);
        if (!dl.getEntite().getEntId().equals(Constants.ENTITE_ID_ETDE) && dds != null) {
            for (DelegationDelegataireModel d : dds) {
                DelegationDelegataire d1 = new DelegationDelegataire();
                d1.setColId(d.getColId());
                d1.setDelId(dl.getId());
                this.delegationDelegataireDao.insert(d1);
            }
        }
        return dl;
    }

    @Override
    @Transactional
    public boolean update(Delegation delegation, List<DelegationDelegataireModel> dds) {
        this.delegationDao.update(delegation);

        if (!delegation.getEntite().getEntId().equals(Constants.ENTITE_ID_ETDE) && dds != null) {
            this.delegationDelegataireDao.deleteByDelegation(delegation.getId());
            for (DelegationDelegataireModel d : dds) {
                DelegationDelegataire d1 = new DelegationDelegataire();
                d1.setColId(d.getColId());
                d1.setDelId(delegation.getId());
                this.delegationDelegataireDao.insert(d1);
            }
        }
        return true;
    }

    @Override
    public String findDelegataireNames(Integer delId) {
        return this.delegationDao.findDelegataireNames(delId);
    }

    @Override
    public List<Collaborateur> findDelegatairesByDelegation(int delId) {
        return this.delegationDelegataireDao.findDelegatairesByDelegation(delId);
    }
}
