// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.projection;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.model.Identifiable;


/**
 * Identifier projection.
 * 
 */
public class IdentifierProjection extends AbstractProjection {

    /**
     * Create new identifier projection.
     *
     * @param grouped set projection as grouping one
     */
    protected IdentifierProjection(boolean grouped) {
        _grouped = grouped;
    }

    /**
     * * Create new identifier projection without grouping.
     */
    protected IdentifierProjection() {
        this(false);
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return criteriaQuery.getPropertyName(Identifiable.PROP_ID, criteria);
    }

    @Override
    public String toGroupSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        if (!_grouped) {
            return super.toGroupSqlString(criteria, criteriaQuery);
        } else {
            return criteriaQuery.getPropertyName(Identifiable.PROP_ID, criteria);
        }
    }
}
