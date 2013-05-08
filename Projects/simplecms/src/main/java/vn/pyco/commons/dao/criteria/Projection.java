// [LICENCE-HEADER]
//
package vn.pyco.commons.dao.criteria;

/**
 * Projection used for manipulating select and group by clauses.
 *
 */
public interface Projection {
    /**
     * Generate part of EQL select clause with given criteria.
     *
     * @param criteria criteria used in projection
     * @param criteriaQuery current query
     * @return part of select clause
     */
    String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery);

    /**
     * Generate part of EQL group by clause with given criteria.
     *
     * @param criteria criteria used in projection
     * @param criteriaQuery current query
     * @return part of group by clause
     * @see #isGrouped()
     */
    String toGroupSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery);

    /**
     * Check if projection is grouping one.
     *
     * @return true when projection is grouping one
     */
    boolean isGrouped();
}
