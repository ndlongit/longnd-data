// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import vn.pyco.commons.dao.criteria.Criterion;


/**
 * Not empty expression.
 *
 */
public class NotEmptyExpression extends AbstractEmptinessExpression implements Criterion {

    /**
     * Create new not empty expression.
     *
     * @param property _property
     */
    protected NotEmptyExpression(String property) {
        super(property);
    }

    @Override
    protected boolean excludeEmpty() {
        return true;
    }
}
