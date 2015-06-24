package com.sedex.appexch.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sedex.appexch.dao.base.AbstractDao;
import com.sedex.appexch.model.Group;
import com.sedex.appexch.model.User;
import com.sedex.appexch.search.UserSearch;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    @Override
    public User getByLoginName(String loginName) {
        User result = super.findUniqueByProperty(User.PROP_LOGIN_NAME, loginName);
        return result;
    }

    @Override
    public List<User> search(UserSearch searchModel) {
        boolean jpa = false;
        jpa = true;

        if (jpa) {
            return searchJpa(searchModel);
        } else {
            return searchHibernate(searchModel);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<User> searchJpa(UserSearch searchModel) {

        User user = searchModel.getUser();
        Group group = searchModel.getGroup();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery().distinct(true);
        List<Predicate> allConditions = new ArrayList<Predicate>();

        List<Predicate> userConditions = null;
        List<Predicate> groupConditions = null;

        Root rootEntity = cq.from(User.class);
        rootEntity.fetch(User.PROP_GROUPS, JoinType.LEFT); // eager loading
        cq.select(rootEntity);

        if (user != null) {
            userConditions = new ArrayList<Predicate>();

            addLikeExpression(userConditions, cb, rootEntity.get(User.PROP_LOGIN_NAME), user.getLoginName());
            addLikeExpression(userConditions, cb, rootEntity.get(User.PROP_LAST_NAME), user.getLastName());
            addLikeExpression(userConditions, cb, rootEntity.get(User.PROP_FIRST_NAME), user.getFirstName());
            addLikeExpression(userConditions, cb, rootEntity.get(User.PROP_EMAIL), user.getEmail());
        }

        if (group != null) {
            String groupName = group.getName();
            String groupCode = group.getCode();

            if (!isNullOrEmpty(Arrays.asList(groupName, groupCode))) {
                groupConditions = new ArrayList<Predicate>();
                Join groupJoin = rootEntity.join(User.PROP_GROUPS, JoinType.LEFT);
                if (!isNullOrEmpty(groupName)) {
                    groupConditions.add(buildLikeExpression(cb, groupJoin.get(Group.PROP_NAME), groupName));
                }
                if (!isNullOrEmpty(groupCode)) {
                    groupConditions.add(buildLikeExpression(cb, groupJoin.get(Group.PROP_CODE), groupCode));
                }
            }
        }

        if (!isNullOrEmpty(userConditions)) {
            allConditions.addAll(userConditions);
        }

        if (!isNullOrEmpty(groupConditions)) {
            allConditions.addAll(groupConditions);
        }

        if (!isNullOrEmpty(allConditions)) {
            cq.where(allConditions.toArray(new Predicate[0]));
        }

        Query query = entityManager.createQuery(cq);
        List<User> results = query.getResultList();

        return results;
    }

    @SuppressWarnings("unchecked")
    public List<User> searchHibernate(UserSearch searchModel) {

        User user = searchModel.getUser();
        Group group = searchModel.getGroup();

        List<User> results = null;
        Session session = (Session) entityManager.getDelegate();
        Criteria cr = session.createCriteria(User.class);
        cr.createAlias(User.PROP_GROUPS, "group");
        // cr.createAlias(User.PROP_GROUPS, "group", Criteria.LEFT_JOIN);
        if (user != null) {
            String loginName = user.getLoginName();
            if (!isNullOrEmpty(loginName)) {
                cr.add(Restrictions.like(User.PROP_LOGIN_NAME, "%" + loginName.trim() + "%"));
            }

            String firstName = user.getFirstName();
            if (!isNullOrEmpty(firstName)) {
                cr.add(Restrictions.like(User.PROP_FIRST_NAME, "%" + firstName.trim() + "%"));
            }
        }

        if (group != null) {
            String groupName = group.getName();
            if (!isNullOrEmpty(groupName)) {
                cr.add(Restrictions.like("group." + Group.PROP_NAME, "%" + groupName.trim() + "%"));
            }
            String groupCode = group.getCode();
            if (!isNullOrEmpty(groupCode)) {
                cr.add(Restrictions.like("group." + Group.PROP_CODE, "%" + groupCode.trim() + "%"));
            }
        }

        // ProjectionList columns = Projections.projectionList().add(Projections.property(User.PROP_ID), "user.id")
        // .add(Projections.property(User.PROP_LOGIN_NAME), "user.loginName");
        // cr.setProjection(columns);
        // cr.setResultTransformer(Transformers.aliasToBean(User.class));

        results = cr.list();
        return results;
    }
}
