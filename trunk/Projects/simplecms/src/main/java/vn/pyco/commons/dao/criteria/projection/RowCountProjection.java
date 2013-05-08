// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.projection;

import vn.pyco.commons.dao.criteria.Criteria;


/**
 * Row count projection.
 *
 */

public class RowCountProjection  extends AbstractProjection {
    
    /**
     * Create new row count projection.
     */
    protected RowCountProjection() {}

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return "count(*)";
    }

}
