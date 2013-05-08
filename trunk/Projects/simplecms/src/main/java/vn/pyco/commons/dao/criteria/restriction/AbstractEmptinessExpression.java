// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Abstract emptiness expression.
 *
 */
public abstract class AbstractEmptinessExpression implements Criterion {

    protected final String _property;

    /**
     * Create new emptiness expression.
     *
     * @param _property _property
     */
    protected AbstractEmptinessExpression(String property) {
        this._property = property;
    }

    /**
     * Check if exclude empty.
     *
     * @return true if exlude empry
     */
    protected abstract boolean excludeEmpty();

    @Override
    public final String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return (excludeEmpty() ? "exists" : "not exists") + " (select 1 from " + criteriaQuery.getPropertyName(_property, criteria) + ")";
    }
}
