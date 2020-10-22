package io.github.liambloom.softwareEngineering.chapter13.polygonComparable;

/**
 * 
 * Polygon is the base class for Chap_9_ShapesPolygon_Interface_ArrayList project.
 * 
 * (1) Write the Polygon_Comparable interface that has 4 abstract methods in it. 
 *     calculateArea() - used for calculating the area.  (returns void)
 *     getMyArea() - used to get the area.  (returns a double)
 *     getMyType() - used to get the type of Shape int the subclasses(returns String)
 *                      Ex. Isosceles, Square, Hexagon, Circle, etc
 *     getMyCategory() - used to get the category of whatever shape. (returns String)
 *                        Ex. Triangles, Quadrilaterals, Agon, Rounds
 *     
 *     (2) This Interface will "extend Comparable" as one Interface "extend" 's another
 *         and does NOT "implement" another. You will have to override 'compareTo()' in
 *         your subclasses.
 *      Note: compareTo() - used to compare to Shapes area.  (returns an int / takes in an Object)
 *     
 */

// You said that it can't be the raw type Comparable. You can, as long as you're
// okay with unchecked casing in the compareTo method.
public interface Polygon_Comparable extends Comparable<Polygon_Comparable> {
    void calculateArea();
    double getMyArea();
    default String getMyType() {
        return getClass().getSimpleName();
    }
    String getMyCategory();

    @Override
    public default int compareTo(Polygon_Comparable o) {
        final double cmp = this.getMyArea() - o.getMyArea();
        return cmp < 0 ? -1 : cmp > 0 ? 1 : 0;
    }
}