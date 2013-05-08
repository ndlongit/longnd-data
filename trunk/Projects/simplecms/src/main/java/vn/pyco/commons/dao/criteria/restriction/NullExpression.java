// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Null expression.
 *
 */
public class NullExpression implements Criterion {

    private final String _property;

    /**
     * Create new null expression.
     *
     * @param property property
     */
    protected NullExpression(String property) {
        _property = property;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return criteriaQuery.getPropertyName(_property, criteria) + " is null";
    }
}
