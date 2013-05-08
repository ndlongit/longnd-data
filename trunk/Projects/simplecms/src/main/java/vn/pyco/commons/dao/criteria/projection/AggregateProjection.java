// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.projection;

import vn.pyco.commons.dao.criteria.Criteria;


/**
 * Aggregate projection.
 * 
 */
public class AggregateProjection extends AbstractProjection {
    protected final String _property;

    private final String _aggregate;

    /**
     * Create new aggregate projection using given function and _property.
     * 
     * @param aggregate
     *            aggregate function
     * @param property
     *            aggregated _property
     */
    protected AggregateProjection(String aggregate, String property) {
        _aggregate = aggregate;
        _property = property;
    }

    @Override
    public String toSqlString(Criteria criteria,
                    Criteria.CriteriaQuery criteriaQuery) {
        return _aggregate + "("
                        + criteriaQuery.getPropertyName(_property, criteria)
                        + ")";
    }
}
