package com.structis.vip.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.core.business.KeyValueVM;
import com.structis.vip.server.core.Constants;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.CommonUtils;
import com.structis.vip.server.util.DataCopier;

@Repository("collaborateurDao")
public class CollaborateurDaoImpl extends HibernateGenericDao<Collaborateur, Integer> implements CollaborateurDao {

	public CollaborateurDaoImpl() {
		super(Collaborateur.class);
	}
	
	@Override
	public List<Collaborateur> getAllCollaborateurs() {
		StringBuffer sb = new StringBuffer();

		sb.append(" from Collaborateur c order by c.lastname, c.firstname");

		Query query = getEntityManager().createQuery(sb.toString());

		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<Collaborateur> getAllCollaborateursByEntiteId(String entiteId, Boolean sortie) {
		StringBuffer sb = new StringBuffer();
		String subSortie = "";
		if (!sortie) {
			subSortie = " AND ( c.sorti is null or c.sorti = 'N' or ( c.sorti = 'O' AND (c.dateSortie is null or c.dateSortie >= GETDATE())))";
		} 
		sb.append(" from Collaborateur c where c.entite.entId = :entiteId")
		  .append(subSortie)
		  .append(" order by c.lastname, c.firstname");

		Query query = getEntityManager().createQuery(sb.toString());
		query.setParameter("entiteId", entiteId);

		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();

		return resultList;
	}
	
	@Override
	public List<Collaborateur> getAllCollaborateursByEntiteId(String entiteId, Boolean sortie, String sortedField,Integer sortDir, Integer start, Integer pageSize) {
		StringBuffer sb = new StringBuffer();
		String subSortie = "";
		if (sortedField == null) {
			sortedField = "fullnameNoSeparater";
		}
		String subSortField = "";		
		if (sortedField.equalsIgnoreCase("fullnameNoSeparater")) {
			subSortField = " ORDER BY c.col_lastname, c.col_firstname";
		} else if (sortedField.equalsIgnoreCase("isDelegant")) {
			subSortField = " ORDER BY c.col_isDelegant";
		} else if (sortedField.equalsIgnoreCase("isDelegataire")) {
			subSortField = " ORDER BY c.col_isDelegataire";
		} else if (sortedField.equalsIgnoreCase("delegantPerimetreNames")) {
			subSortField = " ORDER BY dbo.ufn_GetPerimetreNameForDelegant(c.col_id)";
		}else if (sortedField.equalsIgnoreCase("delegatairesPerimetreNames")) {
			subSortField = " ORDER BY dbo.ufn_GetPerimetreNameForDelegataire(c.col_id)";
		}
		String subSortDir = "";
		if (sortDir == 0) {
			subSortDir = " ASC";
		} else {
			subSortDir = " DESC";
		}
		if (!sortie) {
			subSortie = " AND ( c.col_sorti is null or c.col_sorti = 'N' or ( c.col_sorti = 'O' AND (c.col_dateSortie is null or c.col_dateSortie >= GETDATE())))";
		} 
		sb.append("select d.* from ( select ROW_NUMBER() OVER (")
			.append(subSortField).append(subSortDir)
			.append(") AS RowNum, c.col_id, c.col_firstname, c.col_lastname, c.col_isDelegant, c.col_isDelegataire,c.col_sorti,dbo.ufn_GetPerimetreNameForDelegant(c.col_id) as delegantPerimetreNames,dbo.ufn_GetPerimetreNameForDelegataire(c.col_id) as delegatairesPerimetreNames from COL_COLLABORATEUR c where c.ent_id = :entiteId")
		  .append(subSortie).append(" ) as d WHERE d.RowNum BETWEEN :startOffset AND :endOffset");
				
		//tdo 30 Nov
		  
		 //.append(" order by c.lastname, c.firstname");		
		//fullnameNoSeparater, isDelegant, isDelegataire, delegantPerimetreNames, delegatairesPerimetreNames
		

		Query query = getEntityManager().createNativeQuery(sb.toString());
		unwrapTypes(query);

		query.setParameter("entiteId", entiteId);
		query.setParameter("startOffset", start);
		query.setParameter("endOffset", start+pageSize-1);
		
//		query.setFirstResult(start);
//		if(start == 0) 	query.setMaxResults(pageSize);
//		else query.setMaxResults(start*pageSize);
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		List<Collaborateur> ret = new ArrayList<Collaborateur>();
		for (Object[] o: resultList) {
			Collaborateur col = new Collaborateur();
			col.setId((Integer)o[1]);
			col.setFirstname((String)o[2]);
			col.setLastname((String)o[3]);
			col.setIsDelegant((Integer)o[4]);
			col.setIsDelegataire((Integer)o[5]);
			col.setSorti((String)o[6]);
			String perimetreNames = (String)o[7];
			if (perimetreNames!=null) {
				perimetreNames = perimetreNames.replaceAll(",", "<br>");
			}
			
			col.setDelegantPerimetreNames(perimetreNames);
			String pNames = (String)o[8];
			if (pNames!=null) {
				pNames = pNames.replaceAll(",", "<br>");
			}	
			col.setDelegatairesPerimetreNames(pNames);			
			ret.add(col);
		}
		return ret;
}

	@Override
	public List<Collaborateur> getAllDelegantsByEntiteId(String entiteId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Collaborateur c where c.entite.entId = :entiteId");
		sb.append(" and c.isDelegant = 1");
		sb.append(" AND ( c.sorti is null or c.sorti = 'N' or ( c.sorti = 'O' AND (c.dateSortie is null or c.dateSortie >= GETDATE())))");
		Query query = getEntityManager().createQuery(sb.toString());
		query.setParameter("entiteId", entiteId);

		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();

		return resultList;
	}

	@Override
	public List<Collaborateur> getAllDelegatairesByEntiteId(String entiteId) {
		StringBuffer sb = new StringBuffer();

		sb.append(" from Collaborateur c where c.entite.entId = :entiteId").append(" and c.isDelegataire = 1");
		sb.append(" AND ( c.sorti is null or c.sorti = 'N' or ( c.sorti = 'O' AND (c.dateSortie is null or c.dateSortie >= GETDATE())))");
		Query query = getEntityManager().createQuery(sb.toString());
		query.setParameter("entiteId", entiteId);

		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();

		return resultList;
	}

	@Override
	public Collaborateur findByIdBycn(String idBycn) {
		String sql = " from Collaborateur c where c.idBycn = :idBycn";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idBycn", idBycn);
		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();
		
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public Collaborateur update(Collaborateur dl) {
		EntityManager em = getEntityManager();
		try {					
			Collaborateur jpa = get(dl);		
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

	@Transactional
	public Collaborateur insert(Collaborateur collaborateur) {
		String idBycn = CommonUtils.randomString(16);
		if (collaborateur != null && (collaborateur.getIdBycn() == null || "".equals(collaborateur.getIdBycn()))) {
			collaborateur.setIdBycn(idBycn);
		}
		this.save(collaborateur);
		return collaborateur;
	}

	@Override
	public List<Collaborateur> findByName(String name, String entityId, Boolean sortie) {
		//String sql = " FROM Collaborateur c WHERE c.lastname LIKE :name AND c.entite.entId = :entityId AND (c.dateSortie is null or c.dateSortie > GETDATE()) ORDER BY c.lastname, c.firstname";
		String subSortie = "";
		if (!sortie) {
			subSortie = " AND ( c.sorti is null or c.sorti = 'N' or ( c.sorti = 'O' AND (c.dateSortie is null or c.dateSortie >= GETDATE())))";
		}
		String sql = " FROM Collaborateur c WHERE c.lastname + ', ' + c.firstname LIKE :name AND c.entite.entId = :entityId "+subSortie+" ORDER BY c.lastname, c.firstname";
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("name", "%" + name + "%");
		query.setParameter("entityId", entityId);
		
		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();
		return resultList;
	}
	
	@Override
	public List<Collaborateur> findByName(String name, String entityId, Boolean sortie, String sortedField, Integer sortDir, Integer start, Integer pageSize) {
		//String sql = " FROM Collaborateur c WHERE c.lastname LIKE :name AND c.entite.entId = :entityId AND (c.dateSortie is null or c.dateSortie > GETDATE()) ORDER BY c.lastname, c.firstname";
		String subSortie = "";
		if (!sortie) {
			subSortie = " AND ( c.col_sorti is null or c.col_sorti = 'N' or ( c.col_sorti = 'O' AND (c.col_dateSortie is null or c.col_dateSortie >= GETDATE())))";
		}
		StringBuffer sql = new StringBuffer();
//		(Integer id, String firstname, String lastname,
//				Integer isDelegant, Integer isDelegataire, String sorti,
//				String delegantPerimetreNames, String delegatairesPerimetreNames)
		//tdo 30 Nov
		//fullnameNoSeparater, isDelegant, isDelegataire, delegantPerimetreNames, delegatairesPerimetreNames
		String subSortField = "";
		if (sortedField == null) {
			sortedField = "fullnameNoSeparater";
		}
		if (sortedField.equalsIgnoreCase("fullnameNoSeparater")) {
			subSortField = " ORDER BY c.col_lastname, c.col_firstname";
		} else if (sortedField.equalsIgnoreCase("isDelegant")) {
			subSortField = " ORDER BY c.col_isDelegant";
		} else if (sortedField.equalsIgnoreCase("isDelegataire")) {
			subSortField = " ORDER BY c.col_isDelegataire";
		} else if (sortedField.equalsIgnoreCase("delegantPerimetreNames")) {
			subSortField = " ORDER BY dbo.ufn_GetPerimetreNameForDelegant(c.col_id)";
		}else if (sortedField.equalsIgnoreCase("delegatairesPerimetreNames")) {
			subSortField = " ORDER BY dbo.ufn_GetPerimetreNameForDelegataire(c.col_id)";
		}
		String subSortDir = "";
		if (sortDir == 0) {
			subSortDir = " ASC";
		} else {
			subSortDir = " DESC";
		}
		
		sql.append("select d.* from ( select ROW_NUMBER() OVER (")
			.append(subSortField).append(subSortDir)
			.append(") AS RowNum, c.col_id, c.col_firstname, c.col_lastname, c.col_isDelegant, c.col_isDelegataire,c.col_sorti,dbo.ufn_GetPerimetreNameForDelegant(c.col_id) as delegantPerimetreNames,dbo.ufn_GetPerimetreNameForDelegataire(c.col_id) as delegatairesPerimetreNames")
			.append(" FROM COL_COLLABORATEUR c WHERE c.col_lastname + ', ' + c.col_firstname LIKE :name AND c.ent_id = :entityId "+subSortie+") as d ")
			.append(" WHERE d.RowNum BETWEEN :startOffset AND :endOffset");
		
		
		Query query = getEntityManager().createNativeQuery(sql.toString());
		unwrapTypes(query);
		query.setParameter("name", "%" + name + "%");
		query.setParameter("entityId", entityId);
		query.setParameter("startOffset", start);
//		query.setFirstResult(start);
		query.setParameter("endOffset", start+pageSize-1);
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		List<Collaborateur> ret = new ArrayList<Collaborateur>();
		for (Object[] o: resultList) {
			Collaborateur col = new Collaborateur();
			col.setId((Integer)o[1]);
			col.setFirstname((String)o[2]);
			col.setLastname((String)o[3]);
			col.setIsDelegant((Integer)o[4]);
			col.setIsDelegataire((Integer)o[5]);
			col.setSorti((String)o[6]);
			String perimetreNames = (String)o[7];
			if (perimetreNames!=null) {
				perimetreNames = perimetreNames.replaceAll(",", "<br>");
			}
			
			col.setDelegantPerimetreNames(perimetreNames);
			String pNames = (String)o[8];
			if (pNames != null) {
				pNames = pNames.replaceAll(",", "<br>");
			}
			col.setDelegatairesPerimetreNames(pNames);			
			ret.add(col);
		}
		return ret;
	}
	
	@Override
	public List<Collaborateur> getAllDelegantsByPerimeter(String perId, String entiteId) {
		//String sql = " FROM Collaborateur c where (c.perimetreDelegant.perId = :perId OR c.perimetreDelegant.perId is null) and c.isDelegant = 1 and c.entite.entId = :entiteId";
		String sql = " select distinct c.delegant FROM DelegantPerimetre c inner join c.delegant where (c.perimetre.perId = :perId OR c.perimetre.perId = '"+getRootPerimetre(entiteId)+"') and c.delegant.isDelegant = 1 and c.delegant.entite.entId = :entiteId";
//		String sql = " FROM Collaborateur c where (c.perimetreDelegant.perId = :perId OR c.perimetreDelegant.perId is null OR c.perimetreDelegant.perId = '"+getRootPerimetre(entiteId)+"') and c.isDelegant = 1 and c.entite.entId = :entiteId";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("perId", perId);
		query.setParameter("entiteId", entiteId);
		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Collaborateur> getAllDelegatairesByPerimeter(String perId, String entityId) {
		//String sql = " FROM Collaborateur c where (c.perimetreDelegataire.perId = :perId OR c.perimetreDelegataire.perId is null) and c.isDelegataire = 1 and c.entite.entId = :entiteId";
//		String sql = " FROM Collaborateur c where (c.perimetreDelegataire.perId = :perId OR c.perimetreDelegant.perId = '"+getRootPerimetre(entityId)+"') and c.isDelegataire = 1 and c.entite.entId = :entiteId";
		String sql = " select distinct c.delegataire FROM DelegatairePerimetre c where (c.perimetre.perId = :perId OR c.perimetre.perId = '"+getRootPerimetre(entityId)+"') and c.delegataire.isDelegataire = 1 and c.delegataire.entite.entId = :entiteId";
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("perId", perId);
		query.setParameter("entiteId", entityId);
		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Collaborateur> getAllDelegantsByParentPerimeter(String perId, String entityId) {
		String sql = " select distinct c.delegant FROM DelegantPerimetre c inner join c.delegant where c.perimetre.perId = :perId  and c.delegant.isDelegant = 1 and c.delegant.entite.entId = :entiteId";
		//String sql = " FROM Collaborateur c where c.perimetreDelegant.perId = :perId and c.isDelegant = 1 and c.entite.entId = :entiteId";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("perId", perId);
		query.setParameter("entiteId", entityId);
		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Collaborateur> getAllDelegatairesByParentPerimeter(String perId, String entityId) {
		//String sql = " FROM Collaborateur c where c.perimetreDelegataire.perId = :perId and c.isDelegataire = 1 and c.entite.entId = :entiteId";
		String sql = " select distinct c.delegataire FROM DelegatairePerimetre c inner join c.delegataire where c.perimetre.perId = :perId and c.delegataire.isDelegataire = 1 and c.delegataire.entite.entId = :entiteId";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("perId", perId);
		query.setParameter("entiteId", entityId);
		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Collaborateur> findByPerimetre(String perimetreId) {
		StringBuffer sql = new StringBuffer();
//		sql.append(" select distinct c.delegataire FROM DelegatairePerimetre c where c.perimetre.perId = :perimetreId1");
//		sql.append(" union select distinct c2.delegant FROM DelegantPerimetre c2 where c2.perimetre.perId = :perimetreId2");
////		sql.append(" FROM Collaborateur c where c.perimetreDelegant.perId = :perimetreId1 or c.perimetreDelegataire.perId = :perimetreId2");				
		sql.append(" select c from Collaborateur c where c.id in (select c2.delegataire.id from DelegatairePerimetre c2 where c2.perimetre.perId = :perimetreId2 ) ");
		sql.append(" or c.id in (select c1.delegant.id from DelegantPerimetre c1 where c1.perimetre.perId = :perimetreId1 ) ");

		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("perimetreId1", perimetreId);
		query.setParameter("perimetreId2", perimetreId);
		List<Collaborateur> ret = query.getResultList();;
		Query query2 = getEntityManager().createQuery(" FROM Collaborateur c where c.perimetreDelegant.perId = :perimetreId1 or c.perimetreDelegataire.perId = :perimetreId2");
		query2.setParameter("perimetreId1", perimetreId);
		query2.setParameter("perimetreId2", perimetreId);
		List<Collaborateur> ret2 = query2.getResultList();;
		if (ret != null) {
			if (ret2 != null) {
				ret.addAll(ret2);				
			} 
			return ret;
		} else {
			return ret2;
		}		
	}
	
	private String getRootPerimetre(String entId) {
		if (entId == null) return null;
		if (Constants.ENTITE_ID_BYEFE.equals(entId)) {
			return Constants.ENTITE_BYEFE;
		} else if (Constants.ENTITE_ID_ETDE.equals(entId)) {
			return Constants.ENTITE_ETDE;
		} else if (Constants.ENTITE_ID_BYTP.equals(entId)) {
			return Constants.ENTITE_BYTP;
		}
		return "";
	}

	@Override
	public List<Collaborateur> getDelegantsByNatureAndPerimetre(String perId, String ptyId, 
			String entId, Integer natureId) {		
//		String subSql = "";
//		//if (entId.equals(Constants.ENTITE_ID_BYEFE)) {
//		subSql = "and c.delegant.type = d.collaborateurType and (c.perimetre.perId = :perId OR c.perimetre.perId = '"+getRootPerimetre(entId)+"') "; 
		//}, DelegationMdl d 
		//tdo 12 Dec
		String allPerimetreParentsAndCurrent = getAllParentPerimetreAndCurrent(entId, perId);
		String sql = " select distinct dg from DelegantPerimetre c inner join c.delegant dg where dg.type.id in (select d.collaborateurType from DelegationMdl d where d.delegationNature.id = :natureId and d.entite.id = :entId) and (c.perimetre.perId in (" + allPerimetreParentsAndCurrent +
			") OR c.perimetre.perId = '"+getRootPerimetre(entId)+"')  and dg.isDelegant = 1 and dg.entite.entId = :entId order by dg.lastname, dg.firstname ";		
//		String sql = " select distinct dg from DelegantPerimetre c inner join c.delegant dg where dg.type.id in (select d.collaborateurType from DelegationMdl d where d.delegationNature.id = :natureId and d.entite.id = :entId) and (c.perimetre.perId = :perId OR c.perimetre.perId = '"+getRootPerimetre(entId)+"')  and dg.isDelegant = 1 and dg.entite.entId = :entId order by dg.lastname, dg.firstname ";
		//String sql = " select distinct c.delegant from DelegantPerimetre c inner join c.delegant, DelegationMdl d where d.delegationNature.id = :natureId and d.entite.id = :entId and c.delegant.isDelegant = 1 and c.delegant.entite.entId = :entId "+subSql+"order by c.delegant.lastname, c.delegant.firstname ";
		
		Query query = getEntityManager().createQuery(sql);
//		if (entId.equals(Constants.ENTITE_ID_BYEFE)) {
//			query.setParameter("perId", perId);
//		}
		query.setParameter("entId", entId);		
		query.setParameter("natureId", natureId);
//		query.setParameter("ptyId",  ptyId);
		@SuppressWarnings("unchecked")
		List<Collaborateur> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<KeyValueVM> getAllKeyValueCollaborateursByEntiteId(
			String entiteId, boolean sortie) {
		StringBuffer sb = new StringBuffer();
		String subSortie = "";
		
		if (!sortie) {
			subSortie = " AND ( c.sorti is null or c.sorti = 'N' or ( c.sorti = 'O' AND (c.dateSortie is null or c.dateSortie >= GETDATE())))";
		} 
		sb.append(" select new com.structis.vip.server.bean.domain.core.business.KeyValueVM(c.firstname, c.lastname + ', '+ c.firstname,'0') from Collaborateur c where c.entite.entId = :entiteId")
		  .append(subSortie)
		  .append(" order by c.lastname, c.firstname");

		Query query = getEntityManager().createQuery(sb.toString());
		query.setParameter("entiteId", entiteId);

		@SuppressWarnings("unchecked")
		List<KeyValueVM> resultList = query.getResultList();

		return resultList;
	}

	@Override
	public Long countByName(String name, String entId, Boolean sortie) {
		String subSortie = "";
		if (!sortie) {
			subSortie = " AND ( c.sorti is null or c.sorti = 'N' or ( c.sorti = 'O' AND (c.dateSortie is null or c.dateSortie >= GETDATE())))";
		}
		String sql = " select count(*) FROM Collaborateur c WHERE c.lastname + ', ' + c.firstname LIKE :name AND c.entite.entId = :entityId "+subSortie;
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("name", "%" + name + "%");
		query.setParameter("entityId", entId);
		
		Long resultList = (Long)query.getSingleResult();
		return resultList;
	}

	@Override
	public Long countAllCollaborateursByEntiteId(String entId, Boolean sortie) {
		StringBuffer sb = new StringBuffer();
		String subSortie = "";
		if (!sortie) {
			subSortie = " AND ( c.sorti is null or c.sorti = 'N' or ( c.sorti = 'O' AND (c.dateSortie is null or c.dateSortie >= GETDATE())))";
		} 
		sb.append(" select count(*) from Collaborateur c where c.entite.entId = :entiteId")
		  .append(subSortie);

		Query query = getEntityManager().createQuery(sb.toString());
		query.setParameter("entiteId", entId);

		Long resultList = (Long)query.getSingleResult();

		return resultList;
	}
	
	@SuppressWarnings("deprecation")
	public String getAllParentPerimetreAndCurrent(String entId, String perId) {
		 Query query = getEntityManager().createNativeQuery("select dbo.ufn_GetAllParentsAndCurrent(?,?) as parents");
		 query.unwrap(SQLQuery.class).addScalar("parents", Hibernate.STRING);		
         query.setParameter(1, perId);
         query.setParameter(2, entId);
         
         String ret = (String) query.getSingleResult();
         return ret;         
	}

//	private static String buildSortieCondition(Boolean sortie, String alias) {
//		String subSortie = "";
//		if( !sortie ) {
//			if( alias != null && alias.trim().length() > 0 ) {
//				alias = alias + ".";
//			}
//			else {
//				alias = "";
//			}
//			subSortie = " AND ( " + alias + "sorti is null or " + alias + "sorti = 'N' or ( " + alias + "sorti = 'O' AND (" + alias + "dateSortie is null or " + alias + "dateSortie >= GETDATE())))";
//		}
//		return subSortie;
//	}

	@SuppressWarnings("deprecation")
	private static void unwrapTypes(Query query) {
		query.unwrap(SQLQuery.class).addScalar("RowNum", Hibernate.INTEGER);
		query.unwrap(SQLQuery.class).addScalar("col_id", Hibernate.INTEGER);		
		query.unwrap(SQLQuery.class).addScalar("col_firstname", Hibernate.STRING);
		query.unwrap(SQLQuery.class).addScalar("col_lastname", Hibernate.STRING);
		query.unwrap(SQLQuery.class).addScalar("col_isDelegant", Hibernate.INTEGER);
		query.unwrap(SQLQuery.class).addScalar("col_isDelegataire", Hibernate.INTEGER);
		query.unwrap(SQLQuery.class).addScalar("col_sorti", Hibernate.STRING);
		query.unwrap(SQLQuery.class).addScalar("delegantPerimetreNames", Hibernate.STRING);
		query.unwrap(SQLQuery.class).addScalar("delegatairesPerimetreNames", Hibernate.STRING);
	}	
}
