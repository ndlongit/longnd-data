// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Property expression.
 *
 */
public class PropertyExpression implements Criterion {

    private final String _property;
    private final String _otherProperty;
    private final String _operator;

    /**
     * Create new property expresion.
     *
     * @param property property
     * @param otherProperty other property
     * @param operator operator
     */
    protected PropertyExpression(String property, String otherProperty, String operator) {
        _property = property;
        _otherProperty = otherProperty;
        _operator = operator;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return criteriaQuery.getPropertyName(_property, criteria) + _operator + criteriaQuery.getPropertyName(_otherProperty, criteria);
    }
}
