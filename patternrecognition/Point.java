package patternrecognition;

import stdlib.StdDraw;

import java.util.Comparator;

/**
 * .
 * User: bharadwaj
 * Date: 16/09/13
 * Time: 2:30 PM
 */
public class Point implements Comparable<Point> {

    /**
     * compare points by slope to this point
     */
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

        @Override
        public int compare(final Point o1, final Point o2) {

            double o1o2 = o1.slopeTo(o2);
            if (o1o2 == Double.POSITIVE_INFINITY || o1o2 == Double.NEGATIVE_INFINITY || o1o2 == 0) {
                return 0;
            }

            double withO1 = slopeTo(o1);
            double withO2 = slopeTo(o2);

            //System.out.println("o1 = " + o1 + " o2 = " + o2);
            //System.out.println("witho1 = " + withO1 + " witho2 = " + withO2);

            if (withO1 > withO2) {
                return 1;
            } else if (withO1 < withO2) {
                return -1;
            }
            return 0;
        }
    };

    /**
     * x coordinate
     */
    private final int x;

    /**
     * y coordinate
     */
    private final int y;

    /**
     * create the point (x, y)
     */
    public Point(final int x, final int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * plot this point to standard drawing
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * draw line between this point and that point to standard drawing
     */
    public void drawTo(final Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * string representation
     *
     * @return
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * is this point lexicographically smaller than that point?
     *
     * @param that
     * @return
     */
    public int compareTo(final Point that) {
        if (this.y > that.y) {
            return 1;
        }

        if ((this.y == that.y) && (this.x > that.x)) {
            return 1;
        }

        if ((this.y == that.y) && (this.x == that.x)) {
            return 0;
        }

        return -1;
    }

    /**
     * the slope between this point and that point
     *
     * @param that
     * @return
     */
    public double slopeTo(final Point that) {
        if (this.y == that.y) {
            return 0;
        }

        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }

        if ((this.x == that.x) && (this.y == that.y)) {
            return Double.NEGATIVE_INFINITY;
        }

        double ydiff = (that.y - this.y);
        double xdiff = (that.x - this.x);
        double slope = ydiff/xdiff;

        return slope;
    }

    /*public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object object) {
        Point point = (Point) object;
        if (point.getX() == x && point.getY() == y) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x * y;
    }*/
}
