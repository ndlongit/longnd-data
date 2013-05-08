// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.restriction.Restrictions;
import vn.pyco.commons.dao.impl.GenericDaoImpl;
import vn.pyco.tinycms.dao.NodeDao;
import vn.pyco.tinycms.model.Node;
import vn.pyco.tinycms.model.Page;
import vn.pyco.tinycms.model.Site;
import edu.emory.mathcs.backport.java.util.Collections;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Repository(NodeDao.SERVICE_ID)
public class NodeDaoImpl extends GenericDaoImpl<Node, Integer> implements NodeDao {
    private static final Logger logger = LoggerFactory.getLogger(NodeDaoImpl.class);
    
    /*
     * @see vn.pyco.tinycms.dao.NodeDao#getDefaultPage(java.lang.String)
     */
    @Override
    public Page getDefaultPage(String parentPath) {
        Node node = getByPath(parentPath, Node.class);
        if (node == null) {
            return null;
        }
        
        Page result = null;
        try {
            Criteria criteria = Criteria.forClass(Page.class);
            criteria.add(Restrictions.eq(Page.PROP_PARENT, node));
            criteria.add(Restrictions.eq(Page.PROP_DEFAULT, true));
            result = (Page) findUniqueByCriteria(criteria);
        } catch (NoResultException e) {
            // no-op
        } catch (Exception e) {
            logger.error("Cound not get default page from path=" + parentPath, e);
            result = null;
        }
        
        return result;
    }
    
    /*
     * @see vn.pyco.tinycms.dao.NodeDao#getFirstPage(java.lang.String)
     */
    @Override
    public Page getFirstPage(String parentPath) {
        Node node = getByPath(parentPath, Node.class);
        if (node == null) {
            return null;
        }
        
        Page result = null;
        try {
            Criteria criteria = Criteria.forClass(Page.class);
            criteria.add(Restrictions.eq(Page.PROP_PARENT, node));
            criteria.setMaxResults(1);
            result = (Page) findByCriteria(criteria).get(0);
        } catch (Exception e) {
            logger.error("Cound not get first page from path=" + parentPath, e);
            result = null;
        }
        
        return result;
    }
    
    /*
     * @see vn.pyco.tinycms.dao.NodeDao#getByPath(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Node> T getByPath(String path, Class<T> type) {
        T result = null;
        try {
            Criteria criteria = Criteria.forClass(type);
            criteria.add(Restrictions.eq(Node.PROP_PATH, path));
            result = (T) findUniqueByCriteria(criteria);
        } catch (Exception e) {
            return null;
        }
        
        return result;
    }
    
    /*
     * @see vn.pyco.tinycms.dao.NodeDao#getByAlias(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Node> T getByAlias(String parentPath, String alias, Class<T> type) {
        Node parentNode = null;
        if (parentPath != null) {
            parentNode = getByPath(parentPath, Node.class);
        }
        
        T result = null;
        try {
            Criteria criteria = Criteria.forClass(type);
            criteria.add(Restrictions.eq(Node.PROP_ALIAS, alias));
            if (parentNode == null) {
                criteria.add(Restrictions.isNull(Node.PROP_PARENT));
            } else {
                criteria.add(Restrictions.eq(Node.PROP_PARENT, parentNode));
            }
            
            result = (T) findUniqueByCriteria(criteria);
            
        } catch (Exception e) {
            logger.error("Cound not get a node from alias=" + alias + " and parentPath=" + parentPath, e);
            result = null;
        }
        
        return result;
    }
    
    /*
     * @see vn.pyco.tinycms.dao.NodeDao#getAllChilds(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Node> List<T> getAllChilds(String parentPath) {
        Node parentNode = null;
        if (parentPath != null) {
            parentNode = getByPath(parentPath, Node.class);
        }
        
        List<T> result = null;
        try {
            Criteria criteria = createCriteria();
            if (parentNode == null) {
                criteria.add(Restrictions.isNull(Site.PROP_PARENT));
            } else {
                criteria.add(Restrictions.eq(Site.PROP_PARENT, parentNode));
            }
            result = findByCriteria(criteria);
            
        } catch (Exception e) {
            logger.error("Cound not get all child nodes from parentPath=" + parentPath, e);
            result = Collections.emptyList();
        }
        
        return result;
    }
}
