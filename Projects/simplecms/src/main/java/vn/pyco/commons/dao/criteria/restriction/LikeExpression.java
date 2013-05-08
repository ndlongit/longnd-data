// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Like expression.
 *
 */
public class LikeExpression implements Criterion {

    private final String _property;
    private final Object _value;
    private final Character _escapeChar;
    private final boolean _ignoreCase;

    /**
     * Create new like expression.
     *
     * @param property property
     * @param value value
     * @param escapeChar escape char
     * @param ignoreCase set case sensitive
     */
    protected LikeExpression(String property, Object value, Character escapeChar, boolean ignoreCase) {
        _property = property;
        _value = value;
        _escapeChar = escapeChar;
        _ignoreCase = ignoreCase;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        criteriaQuery.setParam(_ignoreCase ? _value.toString().toLowerCase() : _value);
        String lhs = _ignoreCase ? "lower(" + criteriaQuery.getPropertyName(_property, criteria) + ")" : criteriaQuery.getPropertyName(_property, criteria);
        return lhs + " like ?" + (_escapeChar == null ? "" : " escape \'" + _escapeChar + "\'");
    }
}
