// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.projection;

import java.util.ArrayList;
import java.util.List;

import vn.pyco.commons.dao.criteria.Criteria;
import vn.pyco.commons.dao.criteria.Projection;

/**
 * Projection list.
 *
 */
public class ProjectionList implements Projection {

    private List<Projection> _elements = new ArrayList<Projection>();

    /**
     * Create new projection list.
     */
    protected ProjectionList() {
    }

    /**
     * Create new projection list.
     *
     * @return projection list
     */
    public ProjectionList create() {
        return new ProjectionList();
    }

    /**
     * Add projection to list.
     *
     * @param projection
     * @return projection list
     */
    public ProjectionList add(Projection projection) {
        _elements.add(projection);
        return this;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        String sql = "";
        for (Projection projection : _elements) {
            if (sql.length() > 0) {
                sql += ", ";
            }
            sql += projection.toSqlString(criteria, criteriaQuery);
        }
        return sql;
    }

    @Override
    public String toGroupSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        String sql = "";
        for (Projection projection : _elements) {
            if (projection.isGrouped()) {
                if (sql.length() > 0) {
                    sql += ", ";
                }
                sql += projection.toGroupSqlString(criteria, criteriaQuery);
            }
        }
        return sql;
    }

    @Override
    public boolean isGrouped() {
        for (Projection projection : _elements) {
            if (projection.isGrouped()) {
                return true;
            }
        }
        return false;
    }
}
