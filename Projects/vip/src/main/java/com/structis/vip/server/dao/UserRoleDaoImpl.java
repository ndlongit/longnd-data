package com.structis.vip.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.UserRole;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends HibernateGenericDao<UserRole, Integer> implements UserRoleDao {

	public UserRoleDaoImpl() {
		super(UserRole.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findByUserId(Integer userId) {
		String sql = " from UserRole u where u.user.id = :userId";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("userId", userId);
		List<UserRole> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findBy(Integer userId, Integer roleId, String perimetreId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from UserRole u where u.user.id = :userId ").append(" and u.role.id = :roleId ");
		if (perimetreId != null) {
			sb.append(" and u.perimetre.id = :perimetreId ");
		} else {
			sb.append(" and u.perimetre.id is null ");
		}
		Query query = getEntityManager().createQuery(sb.toString());
		query.setParameter("userId", userId);
		query.setParameter("roleId", roleId);
		if (perimetreId != null) {
			query.setParameter("perimetreId", perimetreId);
		}
		List<UserRole> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public UserRole insert(UserRole userRole) {
		Integer userId = userRole.getUser().getId();
		Integer roleId = userRole.getRole().getId();
		String perimetreId = null;
		if (userRole.getPerimetre() != null) {
			perimetreId = userRole.getPerimetre().getPerId();
		}
		List<UserRole> resultList = findBy(userId, roleId, perimetreId);
		if ((resultList == null) || (resultList.size() == 0)) {
			this.save(userRole);
			return userRole;
		}
		return null;
	}

	@Transactional
	public List<UserRole> insert(List<UserRole> userRoles) {
		List<UserRole> ret = new ArrayList<UserRole>();
		for (UserRole userRole : userRoles) {
			if (insert(userRole) != null) {
				ret.add(userRole);
			}
		}
		return ret;
	}

	@Transactional
	public Boolean deleteByUserId(Integer userId) {
		if ((userId != null) && (userId != 0)) {
			StringBuffer sql = new StringBuffer();
			sql.append("delete from UserRole u where u.user.id = :userId");

			Query query = getEntityManager().createQuery(sql.toString());
			query.setParameter("userId", userId);

			query.executeUpdate();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findByPerimetre(String perimetreId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from UserRole u where u.perimetre.perId = :perimetreId");		

		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("perimetreId", perimetreId);

		return query.getResultList();
	}
}
