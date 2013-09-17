package patternrecognition;

import stdlib.In;

import java.util.*;

/**
 * User: bharadwaj
 * Date: 16/09/13
 * Time: 3:20 PM
 */
public class Fast {

    public static void main(final String[] args) {

        String filename = args[0];
        In file = new In(filename);
        int lines = 0, segments = 0;
        Point [] pointArray = null;

        while (file.hasNextLine()) {
            String line = file.readLine().trim();
            if (lines == 0) {
                segments = Integer.parseInt(line);
                pointArray = new Point[segments];
                lines++;
                continue;
            }

            if (line.length() == 0) {
                continue;
            }

            String [] point = line.split("\\s+");
            int pointOne = Integer.parseInt(point[0]);
            int pointTwo = Integer.parseInt(point[1]);

            pointArray[lines - 1] = new Point(pointOne, pointTwo);
            lines++;
        }

        Map<Double, Set<Point>> sameSlopeMap = new HashMap<Double, Set<Point>>();
        for (int i = 0; i < pointArray.length; i++) {
            Point pointP = pointArray[i];
            //System.out.println("p = " + pointP);

            Point [] slopeArray = new Point[pointArray.length - (i + 1)];

            for (int j = i + 1, k = 0; j < pointArray.length; j++) {
                Point pointQ = pointArray[j];
                //System.out.println("q = " + pointQ);

                if (pointP.equals(pointQ)) {
                   //System.out.println(pointP + " and " + pointQ + " are equal");
                   continue;
                }

                //double slopePQ = pointP.slopeTo(pointQ);
                //if (sameSlopeMap.containsKey(slopePQ)) {
                //    continue;
                //}

                slopeArray[k++] = pointQ;
            }

            if (slopeArray.length < 3) {
                continue;
            }

            Arrays.sort(slopeArray, pointP.SLOPE_ORDER);

            //for (Point sortPoint: slopeArray) {
            //    System.out.println(sortPoint);
            //}
            //System.out.println("---------");

            Set<Point> equalSlopedSet = new HashSet<Point>();

            double slope = 0;
            boolean slopeConsidered = false;
            for (int j = 0; j < slopeArray.length - 2; j++) {
                double slopePQ = pointP.slopeTo(slopeArray[j]);
                if (sameSlopeMap.containsKey(slopePQ)) {
                    slopeConsidered = true;
                    break;
                }

                double slopePR = pointP.slopeTo(slopeArray[j + 1]);
                double slopePS = pointP.slopeTo(slopeArray[j + 2]);

                if ((slopePQ == slopePR) && (slopePQ == slopePS)) {
                    equalSlopedSet.add(pointP);
                    equalSlopedSet.add(slopeArray[j]);
                    equalSlopedSet.add(slopeArray[j + 1]);
                    equalSlopedSet.add(slopeArray[j + 2]);

                    slope = slopePQ;
                }
            }

            if (slopeConsidered) {
                //System.out.println("slope = " + slope + " already considered");
                continue;
            }

            if (equalSlopedSet.size() > 3) {
                sameSlopeMap.put(slope, equalSlopedSet);
            }
        }

        Point [] fourPoints = new Point[4];
        Set<Double> slopes = sameSlopeMap.keySet();
        for (Double slope: slopes) {
            Set<Point> pointSet = sameSlopeMap.get(slope);
            //System.out.println("slope = " + slope + " set size = " + pointSet.size());

            Point [] points = pointSet.toArray(new Point[pointSet.size()]);
            for (int i = 0; i < points.length; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    for (int k = j + 1; k < points.length; k++) {
                        for (int l = k + 1; l < points.length; l++) {
                            fourPoints[0] = points[i];
                            fourPoints[1] = points[j];
                            fourPoints[2] = points[k];
                            fourPoints[3] = points[l];

                            Arrays.sort(fourPoints);
                            System.out.println(fourPoints[0] + " -> " + fourPoints[1] + " -> " + fourPoints[2] + " -> " + fourPoints[3]);
                        }
                    }
                }
            }
        }
    }
}
