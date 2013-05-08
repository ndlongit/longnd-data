// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * In expression.
 *
 */
public class InExpression implements Criterion {

    private final String _property;
    private final Object[] _values;

    /**
     * Create new in expression.
     *
     * @param _property _property
     * @param _values _values
     */
    protected InExpression(String property, Object[] values) {
        this._property = property;
        this._values = values;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        String sql = criteriaQuery.getPropertyName(_property, criteria) + " in (";

        for (Object v : _values) {
            criteriaQuery.setParam(v);
            sql += "?,";
        }

        return sql.substring(0, sql.length() - 1) + ")";
    }
}
