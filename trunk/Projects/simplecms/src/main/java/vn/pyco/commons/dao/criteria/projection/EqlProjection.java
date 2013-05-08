// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.projection;

import vn.pyco.commons.dao.criteria.Criteria;


/**
 * EQL projection.
 *
 */
public class EqlProjection extends AbstractProjection {

    private String _eql;

    private String _groupEql;

    /**
     * Create new EQL projection with select and group by clause.
     *
     * @param eql select clause
     * @param groupEql group by clase
     */
    protected EqlProjection(String eql, String groupEql) {
        _eql = eql;
        _groupEql = groupEql;
        if (groupEql != null) {
            _grouped = true;
        }
    }

    /**
     * Create new EQL projection with select clause.
     *
     * @param _eql select clause
     */
    protected EqlProjection(String eql) {
        this(eql, null);
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return _eql;
    }

    @Override
    public String toGroupSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        if (!_grouped) {
            return super.toGroupSqlString(criteria, criteriaQuery);
        } else {
            return _groupEql;
        }
    }
}
