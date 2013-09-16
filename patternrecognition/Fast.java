package patternrecognition;

import stdlib.In;

import java.util.Arrays;

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

        for (int i = 0; i < pointArray.length; i++) {
            Point pointP = pointArray[i];
            //System.out.println("p = " + pointP);

            Point [] slopeArray = new Point[pointArray.length - (i + 1)];
            for (int j = i + 1, k = 0; j < pointArray.length; j++, k++) {
                Point pointQ = pointArray[j];
                //System.out.println("q = " + pointQ);
                slopeArray[k] = pointQ;
            }

            Arrays.sort(slopeArray, pointP.SLOPE_ORDER);
            for (Point sortPoint: slopeArray) {
                System.out.println(sortPoint);
            }

            for (int j = 0; j < slopeArray.length - 2; j++) {
                Point [] fourPoints = new Point[4];
                fourPoints[0] = pointP;

                fourPoints[1] = slopeArray[j];
                double slopePQ = pointP.slopeTo(fourPoints[1]);

                fourPoints[2] = slopeArray[j + 1];
                double slopePR = pointP.slopeTo(fourPoints[2]);

                fourPoints[3] = slopeArray[j + 2];
                double slopePS = pointP.slopeTo(fourPoints[3]);

                if ((slopePQ == slopePR) && (slopePQ == slopePS)) {
                    Arrays.sort(fourPoints);

                    System.out.println(fourPoints[0] + " -> " + fourPoints[1] + " -> " + fourPoints[2] + " -> " + fourPoints[3]);
                }
            }
        }
    }
}
