// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Not expression.
 *
 */
public class NotExpression implements Criterion {

    private Criterion _criterion;

    /**
     * Create new not expression from given one.
     *
     * @param criterion criterion
     */
    protected NotExpression(Criterion criterion) {
        _criterion = criterion;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return "not (" + _criterion.toSqlString(criteria, criteriaQuery) + ')';
    }
}
