// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.restriction;

import java.util.ArrayList;
import java.util.List;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Criterion;

/**
 * Junction expression.
 *
 */
public class Junction implements Criterion {

    private final List<Criterion> _criteria = new ArrayList<Criterion>();
    private final String _operator;

    /**
     * Create new junction.
     *
     * @param _operator _operator (and, or)
     */
    protected Junction(String operator) {
        this._operator = operator;
    }

    /**
     * Add criterion to junction.
     *
     * @param criterion criterion
     * @return junction
     */
    public Junction add(Criterion criterion) {
        _criteria.add(criterion);
        return this;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        if (_criteria.size() == 0) {
            return "1=1";
        }
        String sql = "(";

        for (Criterion criterion : _criteria) {
            if (sql.length() > 1) {
                sql += " " + _operator + " ";
            }
            sql += criterion.toSqlString(criteria, criteriaQuery);
        }

        sql = sql + ")";
        return sql;
    }
}
