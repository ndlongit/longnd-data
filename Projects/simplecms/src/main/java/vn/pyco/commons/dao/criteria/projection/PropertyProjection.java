// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.projection;

import vn.pyco.commons.dao.criteria.Criteria;


/**
 * Property projection.
 *
 */

public class PropertyProjection extends AbstractProjection {

    private String _propertyName;

    /**
     * Create new _property projection.
     *
     * @param property property
     * @param grouped set projection as grouping one
     */
    protected PropertyProjection(String property, boolean grouped) {
        _propertyName = property;
        _grouped = grouped;
    }

    /**
     * Create new _property projection without grouping.
     *
     * @param _property _property
     */
    protected PropertyProjection(String property) {
        this(property, false);
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return criteriaQuery.getPropertyName(_propertyName, criteria);
    }

    @Override
    public String toGroupSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        if (!_grouped) {
            return super.toGroupSqlString(criteria, criteriaQuery);
        } else {
            return criteriaQuery.getPropertyName(_propertyName, criteria);
        }
    }
}
