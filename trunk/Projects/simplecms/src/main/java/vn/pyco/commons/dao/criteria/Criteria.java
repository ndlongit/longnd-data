// [LICENCE-HEADER]
//
package vn.pyco.commons.dao.criteria;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import vn.pyco.commons.dao.criteria.projection.Projections;
import vn.pyco.commons.model.Identifiable;

/**
 * Critera used to build EQL queries.
 *
 */
public class Criteria {
    /**
     * Join types which can be used to <code>addAlias</code> and <code>createCriteria</code> methods.
     */ 
    public enum JoinType {

        /**
         * Inner join. Default one.
         */ 
        INNER_JOIN("inner join"), 
        
        /**
         * Left outer join.
         */ 
        LEFT_JOIN("left outer join");
        
        private String _sql;
        
        private JoinType(String sql) {
            _sql = sql;
        }

        /**
         * Generate EQL version of given join.
         */ 
        public String toSqlString() {
            return _sql;
        }
    }
    
    private int _aliasNumber;

    private String _name;

    private String _alias;

    private Criteria _parent;

    private JoinType _joinType;

    private Projection _projection;

    private Criteria _projectionCriteria;

    private List<CriterionEntry> _criterionList = new ArrayList<CriterionEntry>();

    private List<OrderEntry> _orderList = new ArrayList<OrderEntry>();

    private List<Subcriteria> _subcriteriaList = new ArrayList<Subcriteria>();

    private Integer _maxResults;

    private Integer _firstResult;

    /**
     * Create new criteria for specified <code>Identifiable</code> implementation.
     */
    public static Criteria forClass(Class clazz) {
        return new Criteria(getEntityName(clazz), "this", null, null);
    }

    protected Criteria(String name, String alias, JoinType joinType, Criteria parent) {
        _name = name;
        _alias = alias;
        _joinType = joinType;
        _parent = parent;
    }

    /**
     * Get name.
     *
     * @return name
     */
    protected String getName() {
        return _name;
    }

    /**
     * Get parent criteria. If this is not an instance of <code>Subcriteria</code> method will return null.
     *
     * @return parent name
     */
    protected Criteria getParent() {
        return _parent;
    }

    /**
     * Get criteria alias.
     *
     * @return alias
     */
    protected String getAlias() {
        return _alias;
    }

    /**
     * Get join type.
     *
     * @return join type
     */
    protected JoinType getJoinType() {
        return _joinType;
    }

    /**
     * Specify that the query results will be a _projection. The individual components contained within the given
     * <code>Projection</code> determines the overall "shape" of the query result.
     *
     * @param projection projection used in query
     * @return criteria object
     * @see Projection
     * @see Projections
     */
    public Criteria setProjection(Projection projection) {
        this._projection = projection;
        this._projectionCriteria = this;
        return this;
    }

    /**
     * Add a <code>Criterion</code> to constrain the results to be retrieved.
     *
     * @param criterion new restriction
     * @return criteria object
     */
    public Criteria add(Criterion criterion) {
        _criterionList.add(new CriterionEntry(criterion, this));
        return this;
    }

    /**
     * Add an <code>Order</code> to the result set.
     *
     * @param order new ordering
     * @return criteria object
     */
    public Criteria addOrder(Order order) {
        _orderList.add(new OrderEntry(order, this));
        return this;
    }

    /**
     * Create a new <code>Criteria</code> joined using "inner join".
     *
     * @param name criteria entity name
     * @return subcriteria
     */
    public Criteria createCriteria(String name) {
        return new Subcriteria(name, createAlias(name), JoinType.INNER_JOIN, this);
    }

    /**
     * Create a new <code>Criteria</code>.
     *
     * @param name criteria entity name
     * @param joinType join type
     * @return subcriteria
     */
    public Criteria createCriteria(String name, JoinType joinType) {
        return new Subcriteria(name, createAlias(name), joinType, this);
    }

    /**
     * Create a new alias joined using "inner join".
     *
     * @param name criteria entity name
     * @param alias alias
     * @return criteria
     */
    
    public Criteria createAlias(String name, String alias) {
        new Subcriteria(name, alias, JoinType.INNER_JOIN, this);
        return this;
    }

    /**
     * Create a new alias.
     *
     * @param name criteria entity name
     * @param alias alias
     * @param joinType join type
     * @return criteria
     */
    
    public Criteria createAlias(String name, String alias, JoinType joinType) {
        new Subcriteria(name, alias, joinType, this);
        return this;
    }

    /**
     * Set a limit upon the number of objects to be retrieved.
     *
     * @param maxResults number of objects to be retrieved
     * @return criteria object
     */
    public Criteria setMaxResults(int maxResults) {
        this._maxResults = new Integer(maxResults);
        return this;
    }

    /**
     * Set the first result to be retrieved.
     *
     * @param firstResult first result to be retrieved
     * @return criteria object
     */
    public Criteria setFirstResult(int firstResult) {
        this._firstResult = new Integer(firstResult);
        return this;
    }

    /**
     * Get the results.
     *
     * @param entityManager entity manager
     * @return list of retrieved objects
     */
    public List list(EntityManager entityManager) {
        return prepareQuery(entityManager).getResultList();
    }

    /**
     * Convenience method to return a single instance that matches the query.
     *
     * @param entityManager entity manager
     * @return retrieved object
     * @throws NoResultException - if there is no result
     * @throws NonUniqueResultException - if more than one result
     */
    public Object uniqueResult(EntityManager entityManager) throws NonUniqueResultException, NoResultException {
        return prepareQuery(entityManager).getSingleResult();
    }

    @Override
    public String toString() {
        CriteriaQuery criteriaQuery = new CriteriaQuery();

        String result = prepateEql(criteriaQuery);

        if (criteriaQuery.getParams().size() > 0) {
            result += " [" + criteriaQuery.getParams() + "]";
        }

        return result;
    }

    protected String createAlias(String name) {
        return name + "_" + _aliasNumber++;
    }

    private String prepateEql(CriteriaQuery criteriaQuery) {
        String sql = "from " + _name + " as " + _alias + " ";
        criteriaQuery.registerAlias(_alias);

        for (Criteria subcriteria : _subcriteriaList) {
            sql += subcriteria.getJoinType().toSqlString() + " ";
            sql += criteriaQuery.getPropertyName(subcriteria.getName(), subcriteria.getParent());
            sql += " as " + subcriteria.getAlias() + " ";
            criteriaQuery.registerAlias(subcriteria.getAlias());
        }

        if (_projection != null) {
            String projectionSql = _projection.toSqlString(_projectionCriteria, criteriaQuery);
            if (projectionSql.length() > 0) {
                sql = "select " + projectionSql + " " + sql;
            } else {
                sql = "select this " + sql;
            }
        } else {
            sql = "select this " + sql;
        }

        String criterionSql = "";

        for (CriterionEntry criterion : _criterionList) {
            if (criterionSql.length() > 0) {
                criterionSql += " and ";
            }
            criterionSql += criterion.getCriterion().toSqlString(criterion.getCriteria(), criteriaQuery);
        }

        if (criterionSql.length() > 0) {
            sql += "where " + criterionSql + " ";
        }

        if (_projection != null) {
            if (_projection.isGrouped()) {
                String groupBySql = _projection.toGroupSqlString(_projectionCriteria, criteriaQuery);
                if (groupBySql.length() > 0) {
                    sql += "group by " + groupBySql + " ";
                }
            }
        }

        String orderSql = "";

        for (OrderEntry order : _orderList) {
            if (orderSql.length() > 0) {
                orderSql += ",";
            }
            orderSql += order.getOrder().toSqlString(order.getCriteria(), criteriaQuery);
        }

        if (orderSql.length() > 1) {
            sql += "order by " + orderSql + " ";
        }

        return sql.trim();
    }

    private Query prepareQuery(EntityManager entityManager) {
        CriteriaQuery criteriaQuery = new CriteriaQuery();

        String sql = prepateEql(criteriaQuery);

        Query query = entityManager.createQuery(sql);

        if (_firstResult != null) {
            query.setFirstResult(_firstResult);
        }

        if (_maxResults != null) {
            query.setMaxResults(_maxResults);
        }

        int i = 1;

        for (Object property : criteriaQuery.getParams()) {
            query.setParameter(i++, property);
        }

        return query;
    }

    @SuppressWarnings("unchecked")
    private static String getEntityName(Class clazz) {        
        Entity entity = (Entity) clazz.getAnnotation(Entity.class);

        if (entity == null || entity.name() == null || entity.name().length() == 0) {
            return clazz.getSimpleName();
        } else {
            return entity.name();
        }
    }

    /**
     * Information about current query, for example parameters.
     */
    public final class CriteriaQuery {

        private List<Object> _params = new ArrayList<Object>();

        private Set<String> _aliases = new HashSet<String>();

        private CriteriaQuery() {
        }

        /**
         * Get name of property in given criteria context.
         *
         * @param name property's name
         * @param criteria criteria
         * @return proper name which can be used in EQL
         */
        public String getPropertyName(String name, Criteria criteria) {
            int pos = name.indexOf(".");

            if (pos == -1) {
                return criteria.getAlias() + "." + name;
            } else {
                if (_aliases.contains(name.substring(0, pos))) {
                    return name;
                } else {
                    return criteria.getAlias() + "." + name;
                }
            }
        }

        /**
         * Set query's param.
         *
         * @param param param value
         */
        public void setParam(Object param) {
            _params.add(param);
        }

        /**
         * Register alias.
         *
         * @param alias alias
         */
        private void registerAlias(String alias) {
            _aliases.add(alias);
        }

        /**
         * Get all query's _params.
         *
         * @return list of query's params
         */
        private List<Object> getParams() {
            return _params;
        }
    }

    /**
     * Subcritera associated with root criteria.
     */
    public final class Subcriteria<T extends Identifiable> extends Criteria {

        private Subcriteria(String name, String alias, JoinType joinType, Criteria parent) {
            super(name, alias, joinType, parent);
            Criteria.this._subcriteriaList.add(this);
        }

        @Override
        public Criteria add(Criterion criterion) {
            Criteria.this._criterionList.add(new CriterionEntry(criterion, this));
            return Subcriteria.this;
        }

        @Override
        public Criteria addOrder(Order order) {
            Criteria.this._orderList.add(new OrderEntry(order, this));
            return Subcriteria.this;
        }

        @Override
        public Criteria createCriteria(String name) {
            return new Subcriteria(name, Subcriteria.this.createAlias(name), JoinType.INNER_JOIN, Subcriteria.this);
        }

        @Override
        public Criteria createCriteria(String name, JoinType joinType) {
            return new Subcriteria(name, Subcriteria.this.createAlias(name), joinType, Subcriteria.this);
        }

        @Override
        public Criteria createAlias(String name, String alias) {
            new Subcriteria(name, alias, JoinType.INNER_JOIN, Subcriteria.this);
            return Subcriteria.this;
        }

        @Override
        public Criteria createAlias(String name, String alias, JoinType joinType) {
            new Subcriteria(name, alias, joinType, Subcriteria.this);
            return Subcriteria.this;
        }
       
        @Override
        public List list(EntityManager entityManager) {
            return (List) Criteria.this.list(entityManager);
        }
        
        @Override
        public Object uniqueResult(EntityManager entityManager) throws NonUniqueResultException, NoResultException {
            return Criteria.this.uniqueResult(entityManager);
        }

        @Override
        public Criteria setFirstResult(int firstResult) {
            Criteria.this.setFirstResult(firstResult);
            return Subcriteria.this;
        }

        @Override
        public Criteria setMaxResults(int maxResults) {
            Criteria.this.setMaxResults(maxResults);
            return Subcriteria.this;
        }

        @Override
        public Criteria setProjection(Projection projection) {
            Criteria.this._projection = projection;
            Criteria.this._projectionCriteria = this;
            return Subcriteria.this;
        }
    }

    /**
     * Criteria entry.
     */
    private final class CriterionEntry<T extends Identifiable> {

        private final Criterion _criterion;

        private final Criteria _criteria;

        private CriterionEntry(Criterion criterion, Criteria criteria) {
            this._criteria = criteria;
            this._criterion = criterion;
        }

        protected Criterion getCriterion() {
            return _criterion;
        }

        protected Criteria getCriteria() {
            return _criteria;
        }
    }

    /**
     * Order entry
     */
    private final class OrderEntry<T extends Identifiable> {

        private final Order _order;

        private final Criteria _criteria;

        private OrderEntry(Order order, Criteria criteria) {
            this._criteria = criteria;
            this._order = order;
        }

        protected Order getOrder() {
            return _order;
        }

        protected Criteria getCriteria() {
            return _criteria;
        }
    }
}
