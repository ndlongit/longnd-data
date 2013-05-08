// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;


import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Empty expression.
 *
 */
public class EmptyExpression extends AbstractEmptinessExpression implements Criterion {

    /**
     * Create new empty expression.
     *
     * @param property _property
     */
    protected EmptyExpression(String property) {
        super(property);
    }

    @Override
    protected boolean excludeEmpty() {
        return false;
    }
}
