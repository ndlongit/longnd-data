package com.structis.vip.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;
import com.structis.vip.shared.Constants;
import com.structis.vip.shared.model.PerimetreModel;

@Repository("perimetreDao")
public class PerimetreDaoImpl extends HibernateGenericDao<Perimetre, String> implements PerimetreDao {

    // add BYTP
    // private static final String PERIMETRE_ROOT_ENTITY = " p.perId != 'ETDE' AND p.perId != 'BYEFE'";
    private static final String PERIMETRE_ROOT_ENTITY = " p.perId != 'ETDE' AND p.perId != 'BYEFE' AND p.perId != 'BYTP' ";
    private static final String P_AND = " AND";

    public PerimetreDaoImpl() {
        super(Perimetre.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Perimetre> findPerimetreByEntite(String entiteId) {
        String sql = " from Perimetre p where  p.entite.entId = :idEntite " + P_AND + PERIMETRE_ROOT_ENTITY + " order by p.name ";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("idEntite", entiteId);
        List<Perimetre> resultList = query.getResultList();
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Perimetre> findFirstLevelPerimetreByEntite(String entiteId) {
        // String sql = " from Perimetre p where p.entite.entId = :idEntite and p.parent is null order by p.name";
        String sql = " from Perimetre p where p.entite.entId = :idEntite and p.parent is null " + P_AND + PERIMETRE_ROOT_ENTITY + " order by p.name";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("idEntite", entiteId);

        List<Perimetre> resultList = query.getResultList();
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Perimetre> findFirstLevelPerimetre() {
        String sql = " from Perimetre p where p.parent is null order by p.name";

        Query query = this.getEntityManager().createQuery(sql);

        List<Perimetre> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PerimetreModel> getTreeModel(String entiteId, String perimetreId) {
        String sql = " from Perimetre p " + " where p.entite.entId = :idEntite AND p.parent.perId = :idPerimetre " + P_AND + PERIMETRE_ROOT_ENTITY
                + " order by p.name";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("idEntite", entiteId);
        query.setParameter("idPerimetre", perimetreId);
        List<PerimetreModel> resultList = query.getResultList();

        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PerimetreModel> getTreeModel(PerimetreModel perimetreModel) {
        String sql = " from Perimetre p " + " where p.parent.perId = :idPerimetre " + P_AND + PERIMETRE_ROOT_ENTITY + " order by p.name";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("idPerimetre", perimetreModel.getId());
        List<PerimetreModel> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public Boolean update(Perimetre per) {
        EntityManager em = this.getEntityManager();

        try {
            Perimetre jpa = this.get(per);
            if (jpa != null && jpa.getPerId() != null) {
                DataCopier.copyNotIdFields(per, jpa, "perId");
                em.merge(jpa);
                em.flush();
            }
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    @SuppressWarnings("rawtypes")
    @Transactional
    public Integer getNewIndex() {
        StringBuffer sql = new StringBuffer();
        sql.append("select max(p.perId) as m from Perimetre p where p.perId not like '%____________%' " + P_AND + PERIMETRE_ROOT_ENTITY);
        // sql.append("select count(*) as m from Perimetre p");
        Query query = this.getEntityManager().createQuery(sql.toString());

        try {
            List resultList = query.getResultList();
            if (resultList.size() != 0) {
                Integer result = Integer.parseInt((String) resultList.get(0));
                return result + 1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Perimetre> getTreeModelByParent(String entiteId, String perimetreId) {
        List<Perimetre> resultList = new ArrayList<Perimetre>();

        if (perimetreId == null) {
            String sql = " from Perimetre p where p.entite.entId = :idEntite and p.parent is null " + P_AND + PERIMETRE_ROOT_ENTITY
                    + " order by p.name";
            Query query = this.getEntityManager().createQuery(sql);
            query.setParameter("idEntite", entiteId);
            resultList = query.getResultList();
        } else {
            String sql = " from Perimetre p " + " where p.entite.entId = :idEntite AND p.parent.perId = :idPerimetre " + " order by p.name";
            Query query = this.getEntityManager().createQuery(sql);
            query.setParameter("idEntite", entiteId);
            query.setParameter("idPerimetre", perimetreId);
            resultList = query.getResultList();
        }
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Perimetre> findPerimetreByEntiteJuri(Integer entiteJuriId) {
        String sql = " from Perimetre p where p.entiteJuridique.id = :entiteJuriId " + P_AND + PERIMETRE_ROOT_ENTITY + " order by p.name";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("entiteJuriId", entiteJuriId);

        List<Perimetre> resultList = query.getResultList();
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Perimetre> findPerimetreByLanguage(Integer languageId) {
        String sql = " from Perimetre p where p.language.id = :languageId " + P_AND + PERIMETRE_ROOT_ENTITY + " order by p.name";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("languageId", languageId);

        List<Perimetre> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<Perimetre> findByChantierTypeId(Integer chantierTypeId) {
        String sql = " from Perimetre p where p.chantierType.id = :chantierTypeId " + P_AND + PERIMETRE_ROOT_ENTITY + " order by p.name";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("chantierTypeId", chantierTypeId);

        List<Perimetre> resultList = query.getResultList();
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isPerimetreChildOf(String compareId, String parentPer) {
        try {
            Query query = this.getEntityManager().createNativeQuery("select dbo.ufn_GetIsPerimetreChildOf(?,?)");
            query.setParameter(1, compareId);
            query.setParameter(2, parentPer);

            Integer ret = (Integer) query.getSingleResult();
            return (ret == 1);
        } catch (NoResultException ne) {
            ne.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Perimetre> findByPerimetreTypeId(String ptyId) {
        String sql = " from Perimetre p where p.type.ptyId = :ptyId " + P_AND + PERIMETRE_ROOT_ENTITY + " order by p.name";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("ptyId", ptyId);

        List<Perimetre> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public boolean isBreakRuleClassiqueToParfait(Perimetre perimetre) {

        String sql = "select count(*) from Delegation d where d.perimeter.perId = :perId and d.delegationStatus.id != 9 ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("perId", perimetre.getPerId());

        Long result = (Long) query.getSingleResult();

        return result > 0;
    }

    @Override
    public Integer hasReferenceInDelegationOrControlOrPerimetre(String perId) {
        Integer ret = this.hasReferenceInDelegation(perId);
        ret = ret | this.hasReferenceInControl(perId);
        ret = ret | this.hasReferenceInPerimetre(perId);
        return ret;
    }

    private Integer hasReferenceInPerimetre(String perId) {
        String sql = "select count(*) from Perimetre c where c.parent.perId = :perId ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("perId", perId);

        Long result = (Long) query.getSingleResult();

        return result > 0 ? Constants.REFER_PERIMETRE : 0;
    }

    private Integer hasReferenceInDelegation(String perId) {
        String sql = "select count(*) from Delegation d where d.perimeter.perId = :perId ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("perId", perId);

        Long result = (Long) query.getSingleResult();

        return result > 0 ? Constants.REFER_DELEGATION : 0;
    }

    @Override
    public Integer hasReferenceInUserCollaborateur(String perId) {
        Integer ret = this.hasReferenceInUser(perId);
        ret = ret | this.hasReferenceInCollaborateur(perId);
        return ret;
    }

    // 28 Feb
    private Integer hasReferenceInControl(String perId) {
        String sql = "select count(*) from Control c where c.perimetre.perId = :perId ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("perId", perId);

        Long result = (Long) query.getSingleResult();

        return result > 0 ? Constants.REFER_CONTROL : 0;
    }

    private Integer hasReferenceInCollaborateur(String perId) {
        String sql = "select count(*) from Collaborateur c where c.perimetreDelegant.perId = :perId or c.perimetreDelegataire.perId = :perId";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("perId", perId);

        Long result = (Long) query.getSingleResult();
        if (result > 0)
            return 2;

        String sql1 = "select count(*) from DelegantPerimetre dp where dp.perimetre.perId = :perId";
        Query query1 = this.getEntityManager().createQuery(sql1);
        query1.setParameter("perId", perId);

        Long result1 = (Long) query1.getSingleResult();
        if (result1 > 0)
            return 2;
        String sql2 = "select count(*) from DelegantPerimetre dp where dp.perimetre.perId = :perId";
        Query query2 = this.getEntityManager().createQuery(sql2);
        query2.setParameter("perId", perId);

        Long result2 = (Long) query2.getSingleResult();

        return result2 > 0 ? Constants.REFER_COLLABORATEUR : 0;
    }

    private Integer hasReferenceInUser(String perId) {
        String sql = "select count(*) from User u where u.perimetre.perId = :perId ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("perId", perId);

        Long result = (Long) query.getSingleResult();
        if (result > 0)
            return Constants.REFER_USER;
        String sql2 = "select count(*) from UserRole u where u.perimetre.perId = :perId ";
        Query query2 = this.getEntityManager().createQuery(sql2);
        query2.setParameter("perId", perId);

        Long result2 = (Long) query2.getSingleResult();

        return result2 > 0 ? Constants.REFER_USER : 0;
    }

    @Override
    @Transactional
    public void clearReferenceToPerimetreInUserCollaborateur(String perId) {
        this.clearReferenceToPerimetreInUser(perId);
        this.clearReferenceToPerimetreInCollaborateur(perId);
    }

    private void clearReferenceToPerimetreInCollaborateur(String perId) {
        String sql = "update Collaborateur c set c.perimetreDelegant = null where c.perimetreDelegant.perId = :perId ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("perId", perId);
        query.executeUpdate();

        String sql2 = "update Collaborateur c set c.perimetreDelegataire = null where c.perimetreDelegataire.perId = :perId ";
        Query query2 = this.getEntityManager().createQuery(sql2);
        query2.setParameter("perId", perId);
        query2.executeUpdate();

        String sql3 = "delete from DelegantPerimetre c where c.perimetre.perId = :perId ";
        Query query3 = this.getEntityManager().createQuery(sql3);
        query3.setParameter("perId", perId);
        query3.executeUpdate();

        String sql4 = "delete from DelegatairePerimetre c where c.perimetre.perId = :perId ";
        Query query4 = this.getEntityManager().createQuery(sql4);
        query4.setParameter("perId", perId);
        query4.executeUpdate();
    }

    private void clearReferenceToPerimetreInUser(String perId) {
        String sql = "update User u set u.perimetre = null where u.perimetre.perId = :perId ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("perId", perId);
        query.executeUpdate();
        String sql2 = "update UserRole u set u.perimetre = null where u.perimetre.perId = :perId ";
        Query query2 = this.getEntityManager().createQuery(sql2);
        query2.setParameter("perId", perId);
        query2.executeUpdate();
    }

    @Override
    @Transactional
    public List<Perimetre> findAndDeleteOrphanPerimetresNotInArgosByEntite(String entiteId, String parentId, List<String> idsInArgos) {
        // currently, donnot delete perimetre not exist in rubis.
        // deleteOrphanPerimetresNotInArgosByEntite(entiteId, idsInArgos);
        return this.findUsedPerimetresNotInArgosByEntite(entiteId, parentId, idsInArgos);
    }

    private List<String> getAllChildrenIdFrom(String entiteId, String perimetreId) {
        List<String> finalResultList = new ArrayList<String>();
        List<String> resultList = new ArrayList<String>();

        String sql = " select p.perId from Perimetre p " + " where p.entite.entId = :idEntite AND p.parent.perId = :idPerimetre ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("idEntite", entiteId);
        query.setParameter("idPerimetre", perimetreId);
        resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            finalResultList.addAll(resultList);
            for (String id : resultList) {
                finalResultList.addAll(this.getAllChildrenIdFrom(entiteId, id));
            }
        }
        return resultList;

    }

    private List<Perimetre> findUsedPerimetresNotInArgosByEntite(String entiteId, String parentId, List<String> idsInArgos) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select p from Perimetre p where (p.entite.entId = :entId) AND (p.argosName is not null)");
        List<String> ids = null;
        if (parentId != null) {
            ids = this.getAllChildrenIdFrom(entiteId, parentId);
            if (ids != null && ids.size() > 0) {
                sql.append(" AND p.perId in (:idsChildren) ");
            } else {
                sql.append(" AND p.perId in (NULL) ");
            }
        }
        sql.append(" AND (p.perId in (select distinct u.perimetre.perId from User u where u.entite.entId = :entId and u.perimetre is not null)")
                .append(" OR p.perId in (select distinct r.perimetre.perId from UserRole r where r.perimetre is not null)")
                .append(" OR p.perId in (select distinct d.perimeter.perId from Delegation d where d.entite.entId = :entId and d.perimeter is not null)")
                .append(" OR p.perId in (select distinct c.perimetre.perId from Control c where c.perimetre is not null)")
                .append(" OR p.perId in (select distinct e.perimetreDelegant.perId from Collaborateur e where e.entite.entId = :entId and e.perimetreDelegant is not null)")
                .append(" OR p.perId in (select distinct f.perimetreDelegataire.perId from Collaborateur f where f.entite.entId = :entId and f.perimetreDelegataire is not null)")
                .append(" OR p.perId in (select distinct g.perimetre.perId from DelegatairePerimetre g)")
                .append(" OR p.perId in (select distinct h.perimetre.perId from DelegantPerimetre h)")
                .append(" OR p.perId in (select distinct p1.parent.perId from Perimetre p1 where p1.parent is not null))");

        if (idsInArgos != null && idsInArgos.size() > 0) {
            sql.append(" AND p.perId not in (:perIds) order by p.name");
        }
        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("entId", entiteId);

        if (idsInArgos != null && idsInArgos.size() > 0) {
            query.setParameter("perIds", idsInArgos);
        }
        if (parentId != null) {
            if (ids != null && ids.size() > 0) {
                query.setParameter("idsChildren", ids);
            }
        }
        List<Perimetre> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<Perimetre> findByPerimetreParent(String perId) {
        new ArrayList<String>();
        new ArrayList<String>();

        String sql = " from Perimetre p " + " where p.parent.perId = :idPerimetre ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("idPerimetre", perId);
        return query.getResultList();
    }
}
