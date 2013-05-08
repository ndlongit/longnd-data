// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;
import vn.pyco.commons.model.Identifiable;

/**
 * Identifier expression.
 *
 */
public class IdentifierEqExpression implements Criterion {

    private final Object _value;

    /**
     * Create new identifier expression.
     *
     * @param value _value
     */
    protected IdentifierEqExpression(Object value) {
        this._value = value;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        criteriaQuery.setParam(_value);
        return criteriaQuery.getPropertyName(Identifiable.PROP_ID, criteria) + " = ?";
    }
}
