// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Between expression.
 *
 */
public class BetweenExpression implements Criterion {

    private final String _property;
    private final Object _lo;
    private final Object _hi;

    /**
     * Create new between expresion.
     *
     * @param property property
     * @param lo lower value
     * @param hi higher value
     */
    protected BetweenExpression(String property, Object lo, Object hi) {
        _property = property;
        _lo = lo;
        _hi = hi;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        criteriaQuery.setParam(_lo);
        criteriaQuery.setParam(_hi);
        return criteriaQuery.getPropertyName(_property, criteria) + " between ? and ?";
    }
}
