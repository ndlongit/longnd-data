package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.User;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;
import com.structis.vip.server.util.Encryptor;

@Repository("userDao")
public class UserDaoImpl extends HibernateGenericDao<User, Integer> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findUsersByEntite(String entiteId) {
        String sql = " from User u where u.entite.entId = :idEntite and u.id <> 1 order by u.userName ";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("idEntite", entiteId);
        List<User> resultList = query.getResultList();
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public User findUserByUserName(String userName, String domain) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from User u where u.userName = :userName ");
        if (domain != null) {
            sql.append(" and u.domain.name = :domain ");
        } else {
            sql.append(" and u.domain is null ");
        }
        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userName", userName);
        if (domain != null) {
            query.setParameter("domain", domain);
        }
        List<User> resultList = query.getResultList();
        if ((resultList != null) && (resultList.size() != 0)) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public User insert(User user) {
        try {
            this.save(user);
            return user;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public User update(User user) {
        EntityManager em = this.getEntityManager();
        try {
            User jpa = this.get(user);
            if (jpa != null && jpa.getId() != null) {
                DataCopier.copyNotIdFields(user, jpa);
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
    public User getAuthorization(String name, Integer domainId, String password) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from User u where u.userName = :userName ");
        if (domainId != null) {
            sql.append(" and u.domain.id = :domain ");
        } else {
            sql.append(" and u.domain is null ");
        }
        if (password != null && password.length() > 0) {
            sql.append(" and u.password = :password ");
        } else {
            sql.append(" and u.password is null ");
        }

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userName", name);
        if (domainId != null) {
            query.setParameter("domain", domainId);
        }
        if (password != null && password.length() > 0) {
            query.setParameter("password", Encryptor.encrypt(password));
        }

        List<User> resultList = query.getResultList();
        if ((resultList != null) && (resultList.size() > 0)) {
            return resultList.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findByPerimetre(String perimetreId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from User u where u.perimetre.perId = :perimetreId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("perimetreId", perimetreId);

        return query.getResultList();
    }

    @Override
    public List<User> findUsersByEntiteAndUser(String entiteId, Integer userId) {
        String sql = " from User u where u.entite.entId = :idEntite and u.id <> 1 order by u.userName ";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("idEntite", entiteId);
        List<User> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public User findUserByNameDomainAndEntite(String userName, String domain, String entId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from User u where u.userName = :userName and u.entite.entId = :entId");
        if (domain != null) {
            sql.append(" and u.domain.name = :domain ");
        } else {
            sql.append(" and u.domain is null ");
        }
        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userName", userName);
        query.setParameter("entId", entId);
        if (domain != null) {
            query.setParameter("domain", domain);
        }
        List<User> resultList = query.getResultList();
        if ((resultList != null) && (resultList.size() != 0)) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean checkBreakIntegrityData(User user) {
        // check any reference to the user
        String sql = "select count(*) from Perimetre p where p.createdBy.id = :userId ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("userId", user.getId());

        Long result = (Long) query.getSingleResult();

        return result > 0;
    }
}
