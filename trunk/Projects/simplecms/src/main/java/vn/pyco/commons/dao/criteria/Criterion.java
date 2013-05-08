// [LICENCE-HEADER]
//
package vn.pyco.commons.dao.criteria;


/**
 * Criterion used for manipulating where clauses.
 *
 */
public interface Criterion {
    /**
     * Generate part of EQL where clause with given criteria.
     *
     * @param criteria criteria used in criterion
     * @param criteriaQuery current query
     * @return part of select clause
     */
    String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery);
}
