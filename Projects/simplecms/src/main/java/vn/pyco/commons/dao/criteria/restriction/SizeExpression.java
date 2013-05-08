// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Size expression.
 *
 */
public class SizeExpression implements Criterion {

    private final String _property;
    private final long _size;
    private final String _operator;

    /**
     * Create new size expression.
     *
     * @param property property
     * @param size size
     * @param operator operator
     */
    protected SizeExpression(String property, long size, String operator) {
        _property = property;
        _size = size;
        _operator = operator;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        criteriaQuery.setParam(_size);
        return "? " + _operator + " (select count(*) from " + criteriaQuery.getPropertyName(_property, criteria) + ")";
    }
}
