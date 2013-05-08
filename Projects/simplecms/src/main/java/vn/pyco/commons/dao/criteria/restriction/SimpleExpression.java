// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Simple expression.
 *
 */
public class SimpleExpression implements Criterion {

    private final String _property;
    private final Object _value;
    private final String _operator;

    /**
     * Create new simple expression.
     *
     * @param property property
     * @param value value
     * @param operator operator
     */
    protected SimpleExpression(String property, Object value, String operator) {
        _property = property;
        _value = value;
        _operator = operator;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        criteriaQuery.setParam(_value);
        return criteriaQuery.getPropertyName(_property, criteria) + _operator + "?";
    }
}
