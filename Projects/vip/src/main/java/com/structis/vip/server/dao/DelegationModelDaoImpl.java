package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DelegationMdl;
import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;
import com.structis.vip.shared.model.DelegationModel;

@Repository("delegationModelDao")
public class DelegationModelDaoImpl extends HibernateGenericDao<DelegationMdl, Integer> implements DelegationModelDao {

	public DelegationModelDaoImpl() {
		super(DelegationMdl.class);
	}

	@Override
	public List<DocumentMdl> getDocumentModels(Integer langId, String ptyId, Integer natureId, Integer collaboraterType) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select d.documentMdl from DelegationMdl d where d.language.id = :langId and ((d.perimetreType.id is null) or (d.perimetreType.id = :ptyId)) and "
				+ " d.delegationNature.id = :natureId and ((d.collaborateurType is null) or (d.collaborateurType.id =:collaboraterType))");

		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("langId", langId);
		query.setParameter("ptyId", ptyId);
		query.setParameter("natureId", natureId);
		query.setParameter("collaboraterType", collaboraterType);

		List<DocumentMdl> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<DocumentMdl> getDocumentModels(Integer langId, String ptyId, Integer natureId, String entId,
			Integer collaboraterType) {
		StringBuffer sql = new StringBuffer();
		sql.append("select d.documentMdl from DelegationMdl d where d.language.id = :langId and ((d.perimetreType.id = :ptyId) or (d.perimetreType.id is null)) and "
				+ " d.delegationNature.id = :natureId and ((d.collaborateurType is null) or (d.collaborateurType.id = :collaboraterType)) and d.entite.id = :entId ");

		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("langId", langId);
		query.setParameter("ptyId", ptyId);
		query.setParameter("natureId", natureId);
		query.setParameter("collaboraterType", collaboraterType);
		query.setParameter("entId", entId);
		List<DocumentMdl> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getGroup(Integer langId, String ptyId, Integer natureId, Integer collaboraterType) {
		Integer group = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" from DelegationMdl d where d.language.id = :langId and ((d.perimetreType.id is null) or (d.perimetreType.id = :ptyId)) and "
				+ " d.delegationNature.id = :natureId and ((d.collaborateurType is null) or (d.collaborateurType.id =:collaboraterType))");

		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("langId", langId);
		query.setParameter("ptyId", ptyId);
		query.setParameter("natureId", natureId);
		query.setParameter("collaboraterType", collaboraterType);

		List<DelegationMdl> resultList = query.getResultList();
		if (resultList.size() != 0) {
			if (collaboraterType != null) {
				for (DelegationMdl result : resultList) {
					if (collaboraterType.equals(result.getCollaborateurType().getId())) {
						group = result.getGroup();
					}
				}
			} else {
				group = resultList.get(0).getGroup();
			}
		} else {
			sql = new StringBuffer();
			sql.append(" from DelegationMdl d where d.language.id = :langId and ((d.perimetreType.id is null) or (d.perimetreType.id = :ptyId)) and "
					+ " d.delegationNature.id = :natureId");

			query = getEntityManager().createQuery(sql.toString());
			query.setParameter("langId", langId);
			query.setParameter("ptyId", ptyId);
			query.setParameter("natureId", natureId);

			resultList = query.getResultList();
			if (resultList.size() !=0) {
				group = resultList.get(0).getGroup();
			} else {
				group = 0;
			}
		}
		return group;
	}

	@Override
	public Integer getGroup(Integer langId, String ptyId, Integer natureId, String entId, Integer collaboraterType) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from DelegationMdl d where d.language.id = :langId and  d.delegationNature.id = :natureId  ");
		if (ptyId == null) {
			sql.append(" and (d.perimetreType.id is null)");			
		} else {
			sql.append(" and (d.perimetreType.id = :ptyId)");
		}
		if (collaboraterType == null) {
			sql.append(" and (d.collaborateurType.id is null)");			
		} else {
			sql.append(" and (d.collaborateurType.id = :collaboraterType)");
		}
		sql.append(" and d.entite.id = :entId ");

		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("langId", langId);
		if (ptyId != null) {
			query.setParameter("ptyId", ptyId);
		}
		query.setParameter("natureId", natureId);
		if (collaboraterType == null) {
			query.setParameter("collaboraterType", collaboraterType);
		}
		query.setParameter("entId", entId);

		List resultList = query.getResultList();
		if (resultList.size() != 0) {
			return (Integer )resultList.get(0);
		} else {
			return 0;
		}
	}
//	public Integer getGroup(Integer langId, String ptyId, Integer natureId, String entId, Integer collaboraterType) {
//		StringBuffer sql = new StringBuffer();
//		sql.append(" from DelegationMdl d where d.language.id = :langId and ((d.perimetreType.id = :ptyId) or (d.perimetreType.id is null)) and "
//				+ " d.delegationNature.id = :natureId and ((d.collaborateurType is null) or (d.collaborateurType.id = :collaboraterType)) and d.entite.id = :entId ");
//
//		Query query = getEntityManager().createQuery(sql.toString());
//		query.setParameter("langId", langId);
//		query.setParameter("ptyId", ptyId);
//		query.setParameter("natureId", natureId);
//		query.setParameter("collaboraterType", collaboraterType);
//		query.setParameter("entId", entId);
//
//		List resultList = query.getResultList();
//		if (resultList.size() != 0) {
//			return (Integer )resultList.get(0);
//		} else {
//			return 0;
//		}
//	}
	
	@Override
	public List<DelegationMdl> getDelegationModels(String entId, Integer natureId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from DelegationMdl d where d.entite.entId = :entId and d.delegationNature.id = :natureId");
		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("entId", entId);
		query.setParameter("natureId", natureId);

		List<DelegationMdl> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<DelegationMdl> getDelegationModelsByEntite(String entId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from DelegationMdl d where d.entite.entId = :entId");
		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("entId", entId);

		List<DelegationMdl> resultList = query.getResultList();
		return resultList;
	}
	
	@Transactional
	public DelegationMdl update(DelegationMdl dm) {
		EntityManager em = getEntityManager();
		try {
			DelegationMdl jpa = get(dm);
			if (jpa != null && jpa.getId() != null) {
				DataCopier.copyNotIdFields(dm, jpa);
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

	@Transactional
	public DelegationMdl insert(DelegationMdl dm) {
		this.save(dm);
		return dm;
	}

	@Override
	public void updateBatch(List<DelegationMdl> dms) {

	}

	@Override
	public void insertBatch(List<DelegationMdl> dms) {

		Iterable<DelegationMdl> entities = (Iterable<DelegationMdl>) dms.iterator();
		super.save(entities);
	}

	@Override
	public Boolean existOtherDelegationModel(Integer demId, String entId, Integer natureId, String ptyId,
			Integer delegantType, Integer langId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from DelegationMdl d where d.entite.entId = :entId and d.delegationNature.id = :natureId");
		if (demId != null) {
			sql.append(" and d.id != :demId");
		}
		if (ptyId != null) {
			sql.append(" and (d.perimetreType.ptyId= :ptyId or d.perimetreType is null)");
		} else {
			sql.append(" and d.perimetreType is null ");
		}
		if (delegantType != null) {
			sql.append(" and (d.collaborateurType.id = :delegantType or d.collaborateurType is null)");
		} else {
			sql.append(" and d.collaborateurType is null ");
		}
		sql.append(" and d.language.id = :langId ");

		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("entId", entId);
		query.setParameter("natureId", natureId);
		if (demId != null) {
			query.setParameter("demId", demId);
		}

		if (ptyId != null) {
			query.setParameter("ptyId", ptyId);
		}
		if (delegantType != null) {
			query.setParameter("delegantType", delegantType);
		}
		query.setParameter("langId", langId);

		List<DelegationMdl> resultList = query.getResultList();
		return (resultList != null && resultList.size() > 0);
	}

	@Transactional
	public DelegationMdl insert(DelegationMdl dm, Integer group) {
		dm.setGroup(group);
		this.save(dm);
		return dm;
	}

	@Transactional
	public Integer getLastGroup() {
		StringBuffer sql = new StringBuffer();
		sql.append("select max(d.group) as m from DelegationMdl d");
		Query query = getEntityManager().createQuery(sql.toString());

		try {
			List resultList = query.getResultList();
			if (resultList.size() != 0) {
				Integer result = (Integer) resultList.get(0);
				return result + 1;
			} else {
				return 1;
			}
		} catch (Exception e) {
			return 1;
		}
	}

	@Transactional
	public Boolean deleteByGroup(Integer group) {
		if ((group != null) && (group != 0)) {
			StringBuffer sql = new StringBuffer();
			sql.append("delete from DelegationMdl d where d.group = :group");
	
			Query query = getEntityManager().createQuery(sql.toString());
			query.setParameter("group", group);
	
			query.executeUpdate();
		}
		return true;
	}

	@Override
	public List<DelegationMdl> getDelegationModelsByGroup(Integer group) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from DelegationMdl d where d.group = :group");
		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("group", group);

		List<DelegationMdl> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<DelegationMdl> findByLanguageId(Integer languageId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from DelegationMdl d where d.language.id = :languageId");
		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("languageId", languageId);

		List<DelegationMdl> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<DelegationMdl> findByNatureId(Integer natureId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from DelegationMdl d where d.delegationNature.id = :natureId");
		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("natureId", natureId);

		List<DelegationMdl> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public Boolean hasMultipleDelegation(Integer dnId, String enId, String perId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from DelegationMdl d where d.entite.entId = :entId and d.delegationNature.id = :natureId");		
		sql.append(" and d.perimetreType.ptyId in (select p.type.id from Perimetre p where p.perId=:perId) ");		
		sql.append(" and d.language.id = 1 and d.hasMultipleDelegation=1");

		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("entId", enId);
		query.setParameter("natureId", dnId);
		query.setParameter("perId", perId);		

		List<DelegationMdl> resultList = query.getResultList();
		return (resultList != null && resultList.size() > 0);
	}

	@Override
	public Boolean getHasMutiDelegataire(Integer group) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from DelegationMdl d where d.group = :group");					
		sql.append(" and d.hasMultipleDelegataire=1");

		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("group", group);		

		List<DelegationMdl> resultList = query.getResultList();
		return (resultList != null && resultList.size() > 0);
	}
}
