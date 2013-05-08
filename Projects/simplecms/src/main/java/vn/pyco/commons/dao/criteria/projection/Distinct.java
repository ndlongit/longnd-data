// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.projection;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Projection;


/**
 * Distinct projection.
 *
 */
public class Distinct implements Projection {

    private final Projection _projection;

    /**
     * Create new distincted projection using given one.
     * @param projection projection
     */
    public Distinct(Projection projection) {
        _projection = projection;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return "distinct " + _projection.toSqlString(criteria, criteriaQuery);
    }

    @Override
    public String toGroupSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return _projection.toGroupSqlString(criteria, criteriaQuery);
    }

    @Override
    public boolean isGrouped() {
        return _projection.isGrouped();
    }
}
