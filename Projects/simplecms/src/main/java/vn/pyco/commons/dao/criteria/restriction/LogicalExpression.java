// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Logical expression.
 *
 */
public class LogicalExpression implements Criterion {

    private final Criterion _lhs;
    private final Criterion _rhs;
    private final String _operator;

    /**
     * Create new logical expression.
     *
     * @param lhs left critetion
     * @param rhs right criterion
     * @param operator operator
     */
    protected LogicalExpression(Criterion lhs, Criterion rhs, String operator) {
        this._lhs = lhs;
        this._rhs = rhs;
        this._operator = operator;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return "(" + _lhs.toSqlString(criteria, criteriaQuery) + " " + _operator + " " + _rhs.toSqlString(criteria, criteriaQuery) + ")";
    }
}
