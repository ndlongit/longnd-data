// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.projection;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Projection;


/**
 * Abstract projection.
 *
 */
public abstract class AbstractProjection implements Projection {

    /**
     * Group flag, true when this projection is grouping one.
     */
    protected boolean _grouped;

    @Override
    public String toGroupSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        throw new UnsupportedOperationException("not a grouping projection");
    }

    @Override
    public boolean isGrouped() {
        return _grouped;
    }
}
