// [LICENCE-HEADER]
//
package vn.pyco.commons.dao.criteria;

/**
 * Order used for manipulating order by clauses.
 *
 */
public final class Order {
    private boolean _ascending;
    private String _property;

    private Order(String property, boolean ascending) {
        _property = property;
        _ascending = ascending;
    }

    /**
     * Generate part of EQL order by clause with given criteria.
     *
     * @param criteria criteria used in order
     * @param criteriaQuery current query
     * @return part of order by clause
     */
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return criteriaQuery.getPropertyName(_property, criteria) + (_ascending ? " asc" : " desc");
    }

    /**
     * Create new _ascending order with given _property.
     *
     * @param _property _property used in order
     * @return new query object
     */
    public static Order asc(String property) {
        return new Order(property, true);
    }

    /**
     * Create new descending order with given _property.
     *
     * @param _property _property used in order
     * @return new query object
     */
    public static Order desc(String property) {
        return new Order(property, false);
    }
}
