// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Not null expression.
 *
 */
public class NotNullExpression implements Criterion {

    private final String _property;

    /**
     * Create new not null expression.
     *
     * @param property property
     */
    protected NotNullExpression(String property) {
        _property = property;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return criteriaQuery.getPropertyName(_property, criteria) + " is not null";
    }
}
