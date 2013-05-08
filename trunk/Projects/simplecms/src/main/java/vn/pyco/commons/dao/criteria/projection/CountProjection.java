// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.projection;

import vn.pyco.commons.dao.criteria.Criteria;


/**
 * Count projection.
 *
 */
public class CountProjection extends AggregateProjection {

    private boolean _distinct;

    /**
     * Create new count projection.
     *
     * @param property counted property
     */
    protected CountProjection(String property) {
        super("count", property);
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        String sql = "count(";
        if (_distinct) {
            sql += "distinct ";
        }
        sql = sql + criteriaQuery.getPropertyName(_property, criteria) + ")";
        return sql;
    }

    /**
     * Set projection as distincted.
     *
     * @return projection
     */
    public CountProjection setDistinct() {
        _distinct = true;
        return this;
    }
}
