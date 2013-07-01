package com.structis.vip.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.DelegationDelegataire;
import com.structis.vip.server.core.DelegationConstants;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;
import com.structis.vip.shared.SharedConstant;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

@Repository("delegationDao")
public class DelegationDaoImpl extends HibernateGenericDao<Delegation, Integer> implements DelegationDao {

    @Autowired
    PerimetreDao perimetreDao;
    @Autowired
    CollaborateurDao collaborateurDao;

    public DelegationDaoImpl() {
        super(Delegation.class);
    }

    /**
     * get delegations by criteria
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Delegation> getValidDelegations(String perId, Integer natureId, Integer typeId, Integer statusId, Integer delegantId,
            Integer delegataireId, Date startDate, Date endDate, Boolean sep, Boolean conjointe, Boolean isDisplayAllLevel) {
        StringBuffer sb = new StringBuffer();

        sb.append(" from Delegation p where ")
                .append(" p.isSigned = 0 ")
                .append(" and ((:perId is not null and :perId != '' and p.perimeter.id = :perId) or :perId is null or :perId = '') ")
                .append(" and ((:natureId is not null and :natureId != '' and p.delegationNature.id = :natureId) or :natureId is null or :natureId = '') ")
                .append(" and ((:typeId is not null and :typeId != '' and p.delegationType.id = :typeId) or :typeId is null or :typeId = '') ")
                .append(" and ((:statusId is not null and :statusId != '' and p.delegationStatus.id = :statusId) or :statusId is null or :statusId = '') ")
                .append(" and ((:delegantId is not null and :delegantId != '' and p.delegant.id = :delegantId) or :delegantId is null or :delegantId = '') ")
                .append(" and ((:delegataireId is not null and :delegataireId != '' and p.delegataire.id = :delegataireId) or :delegataireId is null or :delegataireId = '') ");

        // check whether the delegation is still valid
        if (endDate != null) {
            sb.append(" and ((:endDate is not null and p.startDate <= :endDate) or :endDate is null) ");
        }
        if (startDate != null) {
            sb.append(" and ((:startDate is not null and p.endDate >= :startDate) or :startDate is null) ");
        }

        /*
         * TODO : add checking conditions for SEP, isDisplayAllLevel, conjointe
         */

        Query query = this.getEntityManager().createQuery(sb.toString());
        query.setParameter("perId", perId);
        query.setParameter("natureId", natureId);
        query.setParameter("typeId", typeId);
        query.setParameter("statusId", statusId);
        query.setParameter("delegantId", delegantId);
        query.setParameter("delegataireId", delegataireId);

        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }

        List<Delegation> resultList = query.getResultList();

        return resultList;
    }

    /**
     * get delegations by criteria for 1 entite
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Delegation> getValidDelegations(String enId, String perId, Integer natureId, Integer typeId, Integer statusId, Integer delegantId,
            Integer delegataireId, Date startDate, Date endDate, Boolean sep, Boolean conjointe, Boolean isDisplayAllLevel) {
        StringBuffer sb = new StringBuffer();

        sb.append(" from Delegation p where ").append(" p.perimeter.entite.id = :enId ");
        // checking whether after
        sb.append(" and ((:perId is not null and :perId != '' and p.perimeter.id = :perId) or :perId is null or :perId = '') ")
                .append(" and ((:natureId is not null and :natureId != '' and p.delegationNature.id = :natureId) or :natureId is null or :natureId = '') ")
                .append(" and ((:typeId is not null and :typeId != '' and p.delegationType.id = :typeId) or :typeId is null or :typeId = '') ")
                .append(" and ((:statusId is not null and :statusId != '' and p.delegationStatus.id = :statusId) or :statusId is null or :statusId = '') ")
                .append(" and ((:delegantId is not null and :delegantId != '' and p.delegant.id = :delegantId) or :delegantId is null or :delegantId = '') ")
                .append(" and ((:delegataireId is not null and :delegataireId != '' and p.delegataire.id = :delegataireId) or :delegataireId is null or :delegataireId = '') ");
        // check whether the delegation is still valid
        if (endDate != null) {
            sb.append(" and ((:endDate is not null and p.startDate <= :endDate) or :endDate is null) ");
        }
        if (startDate != null) {
            sb.append(" and ((:startDate is not null and p.endDate >= :startDate) or :startDate is null) ");
        }
        /*
         * TODO : add checking conditions for SEP, isDisplayAllLevel, conjointe
         */

        Query query = this.getEntityManager().createQuery(sb.toString());
        query.setParameter("enId", enId);
        query.setParameter("perId", perId);
        query.setParameter("natureId", natureId);
        query.setParameter("typeId", typeId);
        query.setParameter("statusId", statusId);
        query.setParameter("delegantId", delegantId);
        query.setParameter("delegataireId", delegataireId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }

        List<Delegation> resultList = query.getResultList();

        return resultList;
    }

    private String genListKeySqlClause(List<Integer> ids) {
        StringBuffer sb = new StringBuffer("");
        for (Integer id : ids) {
            sb.append(",").append(id);
        }
        String sbr = " (" + sb.substring(1) + ") ";
        return sbr;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Delegation> getValidDelegations(String enId, String perId, List<Integer> natureIds, List<Integer> typeIds, List<Integer> statusIds,
            List<Integer> delegantIds, List<Integer> delegataireIds, Date startDate, Date endDate, Boolean sep, Boolean conjointe,
            Boolean isDisplayAllLevel) {
        StringBuffer sb = new StringBuffer();

        sb.append(" from Delegation p where ").append(" p.perimeter.entite.id = :enId ")
                .append(" and ((:perId is not null and :perId != '' and p.perimeter.id = :perId) or :perId is null or :perId = '') ");
        if (natureIds != null && natureIds.size() > 0) {
            sb.append(" and p.delegationNature.id in " + this.genListKeySqlClause(natureIds));
        } else {
            
            // get all nature of entity
        }
        if (typeIds != null && typeIds.size() > 0) {
            sb.append(" and p.delegationType.id  in " + this.genListKeySqlClause(typeIds));
        }
        if (typeIds != null && statusIds.size() > 0) {
            sb.append(" and p.delegationStatus.id in " + this.genListKeySqlClause(statusIds));
        }
        if (delegantIds != null && delegantIds.size() > 0) {
            sb.append(" and p.delegant.id in " + this.genListKeySqlClause(delegantIds));
        }
        if (delegataireIds != null && delegataireIds.size() > 0) {
            sb.append(" and p.delegataire.id in " + this.genListKeySqlClause(delegataireIds));
        }
        // check whether the delegation is still valid
        if (endDate != null) {
            sb.append(" and ((:endDate is not null and p.startDate <= :endDate) or :endDate is null) ");
        }
        if (startDate != null) {
            sb.append(" and ((:startDate is not null and p.endDate >= :startDate) or :startDate is null) ");
        }
        /*
         * TODO : add checking conditions for SEP, isDisplayAllLevel, conjointe
         */
        if (conjointe != null && conjointe) {
            sb.append(" and p.delegationConjointe = 1 ");
        }

        if (sep != null && sep) {
            sb.append(" and p.perimeter.chantierSEP = 1 ");
        }

        Query query = this.getEntityManager().createQuery(sb.toString());
        query.setParameter("enId", enId);
        query.setParameter("perId", perId);

        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }

        List<Delegation> resultList = query.getResultList();
        return resultList;
    }

    /**
     * get Delegations by criteria for 1 Entite
     */
    @Override
    public List<Integer> getDelegationIds(String enId, String parentPerId, List<Integer> natureIds, List<Integer> typeIds, List<Integer> statusIds,
            List<Integer> delegantIds, List<Integer> delegataireIds, Date startDate, Date endDate, Boolean sep, Boolean conjointe,
            Boolean isDisplayAllLevel, PerimetreTreeModel perimetreTreeModel, List<UserRoleModel> userRoles) {

        List<String> perimetreIds = this.perimetreDao.getAllHierarchicalPerimetreIds(enId, parentPerId);
        StringBuffer sb = new StringBuffer();
        
        sb.append("select p.id from Delegation p where p.perimeter.entite.id = :enId ");
        if (perimetreIds != null && perimetreIds.size() > 0) {
            sb.append(" and (p.perimeter.id IN (:perIds)) ");
        }
        if (natureIds != null && natureIds.size() > 0) {
            sb.append(" and p.delegationNature.id in " + this.genListKeySqlClause(natureIds));
        }
        if (typeIds != null && typeIds.size() > 0) {
            sb.append(" and p.delegationType.id  in " + this.genListKeySqlClause(typeIds));
        }
        if (typeIds != null && statusIds.size() > 0) {
            sb.append(" and p.delegationStatus.id in " + this.genListKeySqlClause(statusIds));
        }
        if (delegantIds != null && delegantIds.size() > 0) {
            sb.append(" and p.delegant.id in " + this.genListKeySqlClause(delegantIds));
        }
        if (delegataireIds != null && delegataireIds.size() > 0) {
            if (SharedConstant.ENTITE_ID_ETDE.equals(enId)) { /* For ETDE */
                sb.append(" and p.delegataire.id in " + this.genListKeySqlClause(delegataireIds));
            } else { /* For BYEFE: 015, 016*/
                sb.append(" and p.id in (select distinct d.delId from DelegationDelegataire d where  d.colId in "
                        + this.genListKeySqlClause(delegataireIds) + ")");
            }
        }
        // check whether the delegation is still valid
        if (endDate != null) {
            // sb.append(" and ((:endDate is not null and p.startDate <= :endDate) or :endDate is null) " );
            sb.append(" and (p.startDate <= :endDate or p.startDate is null) ");
        }
        if (startDate != null) {
            sb.append(" and ( p.endDate >= :startDate or (p.endDate is null)) ");
            // sb.append(" and ((:startDate is not null and p.endDate >= :startDate) or :startDate is null) " );
        }
        /*
         * TODO : add checking conditions for SEP, isDisplayAllLevel, conjointe
         */
        if (conjointe != null && conjointe) {
            sb.append(" and p.delegationConjointe = 1 ");
        }
        
        if (sep != null && sep) {
            sb.append(" and p.perimeter.chantierSEP = 1 ");
        }
        
        Query query = this.getEntityManager().createQuery(sb.toString());
        query.setParameter("enId", enId);
        if (perimetreIds != null && perimetreIds.size() > 0) {
            query.setParameter("perIds", perimetreIds);
        }
        
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }
        
        @SuppressWarnings("unchecked")
        List<Integer> resultList = query.getResultList();
        
        return resultList;
    }

    @Override
    @Transactional
    public Delegation update(Delegation dl) {
        EntityManager em = this.getEntityManager();
        try {
            Delegation jpa = this.get(dl);
            if (jpa != null && jpa.getId() != null) {
                DataCopier.copyNotIdFields(dl, jpa);
                em.merge(jpa);
                return jpa;
            }
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Boolean hasDelegation(Integer natureId, String perimetreId) {
        String sql = " from Delegation p "
                + " where p.perimeter.id = :perimetreId AND p.delegationNature.id = :natureId and p.delegationType.id = :type";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("natureId", natureId);
        query.setParameter("perimetreId", perimetreId);
        query.setParameter("type", DelegationConstants.DEL_TYPE_PRINCIPLE);
        List<Delegation> resultList = query.getResultList();

        return (resultList != null && resultList.size() > 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Boolean hasOtherDelegation(Integer delegationId, Integer natureId, String perimetreId, String entiteId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from Delegation p where ");
        if (delegationId != null) {
            sql.append("p.id != :delegationId ");
        } else {
            sql.append("p is not null ");
        }
        if (entiteId != null) {
            sql.append(" and (p.perimeter.entite.id = :entiteId ) ");
        }
        sql.append("and p.perimeter.id = :perimetreId AND p.delegationNature.id = :natureId and p.delegationType.id = :type and p.delegationStatus.id != 9");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("natureId", natureId);
        query.setParameter("perimetreId", perimetreId);
        if (delegationId != null) {
            query.setParameter("delegationId", delegationId);
        }
        if (entiteId != null) {
            query.setParameter("entiteId", entiteId);
        }
        query.setParameter("type", DelegationConstants.DEL_TYPE_PRINCIPLE);
        List<Delegation> resultList = query.getResultList();

        return (resultList != null && resultList.size() > 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Boolean hasRenewDelegation(Integer delegationId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from Delegation p where ");
        sql.append(" p.parent.id = :delegationId ");
        sql.append(" and p.delegationType.id = :type");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delegationId", delegationId);
        query.setParameter("type", DelegationConstants.DEL_TYPE_PRINCIPLE);
        List<Delegation> resultList = query.getResultList();

        return (resultList != null && resultList.size() > 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Boolean delegantIsDelegataireOfDelegation(Integer delegantId, Integer delegationId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from Delegation p where ");
        sql.append("p.id = :delegationId AND p.delegataire.id = :delegantId");
        Query query = this.getEntityManager().createQuery(sql.toString());

        query.setParameter("delegationId", delegationId);
        query.setParameter("delegantId", delegantId);

        List<Delegation> resultList = query.getResultList();

        return (resultList != null && resultList.size() > 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Integer subDelegantIsDelegataireOfParent(Integer delId) {
        if (delId == null) { // new delegation not in db
            return null;
        }
        StringBuffer sql = new StringBuffer();
        sql.append(" from Delegation p where ");
        sql.append("p.id = :delegationId AND p.parent.delegataire.id = p.delegant.id");
        Query query = this.getEntityManager().createQuery(sql.toString());

        query.setParameter("delegationId", delId);

        List<Delegation> resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            Collaborateur delegant = resultList.get(0).getDelegant();
            return (delegant == null) ? null : delegant.getId();
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public Delegation insert(Delegation entity) {

        this.save(entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Delegation> getAllDelegationById(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<Delegation>();
        }
        
        StringBuffer sql = new StringBuffer();
        sql.append("from Delegation p where p.id in (:ids)");
        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Delegation> getDelegationWithStatus(Integer statusId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from Delegation p where p.delegationStatus.id = :statusId and p.startDate = GETDATE() ");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("statusId", statusId);

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Delegation> findByPerimetre(String perimetreId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from Delegation p where p.perimeter.perId = :perimetreId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("perimetreId", perimetreId);

        return query.getResultList();
    }

    @Override
    public List<Delegation> getChildrenById(Integer delId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from Delegation p where p.parent.id = :delId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delId", delId);

        return query.getResultList();
    }

    @Override
    public List<Delegation> findByCollaborateur(Integer colId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from Delegation p where p.delegant.id = :colId1 or p.delegataire.id = :colId2 or p.demandeur.id = :colId3");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("colId1", colId);
        query.setParameter("colId2", colId);
        query.setParameter("colId3", colId);

        return query.getResultList();
    }

    @Override
    public List<Delegation> findByEntiteJuriId(Integer entiteJuriId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from Delegation p where p.entiteJuridique.id = :entiteJuriId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("entiteJuriId", entiteJuriId);

        return query.getResultList();
    }

    @Override
    public List<Delegation> findByNatureId(Integer natureId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from Delegation p where p.delegationNature.id = :natureId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("natureId", natureId);

        return query.getResultList();
    }

    private List<DelegationDelegataire> findDelegatairesByEntiteAndPerimetre(String perId, String entId) {
        StringBuffer sql = new StringBuffer();
        String allPerimetreParentsAndCurrent = this.collaborateurDao.getAllParentPerimetreAndCurrent(entId, perId);
        sql.append("select distinct new com.structis.vip.server.bean.domain.DelegationDelegataire(0, 0, c.id, c.lastname +', '+ c.firstname)")
                .append(" from DelegatairePerimetre d inner join d.delegataire c where d.perimetre.perId in (" + allPerimetreParentsAndCurrent
                        + ") AND c.entite.entId = :entId ");

        Query query = this.getEntityManager().createQuery(sql.toString());

        query.setParameter("entId", entId);

        return query.getResultList();
    }

    private List<DelegationDelegataire> findDelegatairesByEntiteAndPerimetre(List<Integer> colIds, String perId, String entId) {
        StringBuffer sql = new StringBuffer();
        String allPerimetreParentsAndCurrent = this.collaborateurDao.getAllParentPerimetreAndCurrent(entId, perId);
        sql.append("select distinct new com.structis.vip.server.bean.domain.DelegationDelegataire(0, 0 , c.id,  c.lastname +', '+ c.firstname)")
                .append(" from DelegatairePerimetre d inner join d.delegataire c where d.perimetre.perId in (" + allPerimetreParentsAndCurrent
                        + ") AND c.entite.entId = :entId");
        if (colIds == null || colIds.isEmpty()) {
            Query query = this.getEntityManager().createQuery(sql.toString());
            query.setParameter("entId", entId);
            return query.getResultList();
        } else {

            sql.append(" and c.id not in (:colIds)");

            Query query = this.getEntityManager().createQuery(sql.toString());
            query.setParameter("entId", entId);
            query.setParameter("colIds", colIds);

            return query.getResultList();
        }
    }

    private List<Integer> getIds(List<DelegationDelegataire> existedDelegataires) {
        if (existedDelegataires == null || existedDelegataires.isEmpty()) {
            return null;
        } else {
            List<Integer> ids = new ArrayList<Integer>();
            for (DelegationDelegataire d : existedDelegataires) {
                ids.add(d.getColId());
            }
            return ids;
        }
    }

    @Override
    public List<DelegationDelegataire> findDelegataires(Integer delId, String perId, String entId) {
        StringBuffer sql = new StringBuffer();
        if (delId != null && delId > 0) {
            sql.append("select new com.structis.vip.server.bean.domain.DelegationDelegataire(p.id, p.delId, p.colId, c.lastname +', '+ c.firstname)")
                    .append(" from DelegationDelegataire p, Collaborateur c where p.delId = :delId and p.colId = c.id");
            Query query = this.getEntityManager().createQuery(sql.toString());
            query.setParameter("delId", delId);
            List<DelegationDelegataire> existedDelegataires = query.getResultList();
            List<DelegationDelegataire> remainDelegataires = this
                    .findDelegatairesByEntiteAndPerimetre(this.getIds(existedDelegataires), perId, entId);
            if (remainDelegataires != null) {
                existedDelegataires.addAll(remainDelegataires);
            }
            return existedDelegataires;
        } else {
            return this.findDelegatairesByEntiteAndPerimetre(perId, entId);
        }

    }

    @Override
    public String findDelegataireNames(Integer delId) {
        Query query = this.getEntityManager().createNativeQuery("select dbo.ufn_GetDelegataireNames(:delId) as delNames");
        query.unwrap(SQLQuery.class).addScalar("delNames", Hibernate.STRING);
        query.setParameter("delId", delId);

        String existedDelegataires = (String) query.getSingleResult();
        return existedDelegataires;
    }
}
