// [LICENCE-HEADER]
//

package vn.pyco.commons.dao.criteria.projection;

import vn.pyco.commons.dao.criteria.Projection;


/**
 * Projections builder.
 *
 */
public final class Projections {

    private Projections() {
    }

    /**
     * Create a distinct projection from a projection.
     *
     * @param projection projection
     * @return distincted projection
     */
    public static Projection distinct(Projection projection) {
        return new Distinct(projection);
    }

    /**
     * Create a new projection list.
     *
     * @return projection list
     */
    public static ProjectionList projectionList() {
        return new ProjectionList();
    }

    /**
     * The query row count, ie. <code>count(*)</code>.
     *
     * @return row count projection
     */
    public static Projection rowCount() {
        return new RowCountProjection();
    }

    /**
     * A _property value count.
     *
     * @param _property projection which will be counted
     * @return count projection
     */
    public static Projection count(String property) {
        return new CountProjection(property);
    }

    /**
     * A distinct _property value count
     *
     * @param _property projection which will be counted
     * @return count projection
     */
    public static Projection countDistinct(String property) {
        return new CountProjection(property).setDistinct();
    }

    /**
     * A _property maximum value.
     *
     * @param _property _property
     * @return maximum projection
     */
    public static Projection max(String property) {
        return new AggregateProjection("max", property);
    }

    /**
     * A _property minimum value.
     *
     * @param _property _property
     * @return minimum projection
     */
    public static Projection min(String property) {
        return new AggregateProjection("min", property);
    }

    /**
     * A _property average value
     *
     * @param _property _property
     * @return average projection
     */
    public static Projection avg(String property) {
        return new AggregateProjection("avg", property);
    }

    /**
     * A _property value sum
     *
     * @param _property _property
     * @return sum projection
     */
    public static Projection sum(String property) {
        return new AggregateProjection("sum", property);
    }

    /**
     * A grouping _property value
     *
     * @param _property _property
     * @return _property projection with grouping
     */
    public static Projection groupProperty(String property) {
        return new PropertyProjection(property, true);
    }

    /**
     * A projected _property value
     *
     * @param _property _property
     * @return _property projection
     */
    public static Projection property(String property) {
        return new PropertyProjection(property);
    }

    /**
     * A grouping EQL projection, specifying both select clause and group by clause fragments
     *
     * @param eql select clause
     * @param groupEql group by clause
     * @return eql projection
     */
    public static Projection groupEql(String eql, String groupEql) {
        return new EqlProjection(eql, groupEql);
    }

    /**
     * A EQL projection, a typed select clause fragment
     *
     * @param eql select clause
     * @return eql projection
     */
    public static Projection eql(String eql) {
        return new EqlProjection(eql);
    }

    /**
     * A projected identifier value.
     *
     * @return identifier projection
     */
    public static Projection id() {
        return new IdentifierProjection();
    }
}
