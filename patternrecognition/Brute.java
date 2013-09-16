package patternrecognition;

import stdlib.In;

import java.util.Arrays;

/**
 * User: bharadwaj
 * Date: 16/09/13
 * Time: 3:17 PM
 */
public class Brute {

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

        //System.out.println("num of points = " + pointArray.length);
        //for(Point point: pointArray) {
        //    System.out.println(point);
        //}

        for (int i = 0; i < pointArray.length; i++) {
            Point pointP = pointArray[i];
            //System.out.println("p = " + pointP);

            for (int j = i + 1; j < pointArray.length; j++) {
                if (pointP.equals(pointArray[j])) {
                    continue;
                }

                Point pointQ = pointArray[j];
                //System.out.println("q = " + pointQ);

                double slopePQ = pointP.slopeTo(pointQ);
                //System.out.println("slope pq = " + slopePQ);

                for (int k = j + 1; k < pointArray.length; k++) {
                    if (pointP.equals(pointArray[k]) || pointQ.equals(pointArray[k])) {
                        continue;
                    }

                    Point pointR = pointArray[k];
                    //System.out.println("r = " + pointR);

                    double slopePR = pointP.slopeTo(pointR);
                    //System.out.println("slope pr = " + slopePR);
                    if (slopePQ != slopePR) {
                        continue;
                    }

                    for (int l = k + 1; l < pointArray.length; l++) {
                        if (pointP.equals(pointArray[l]) || pointQ.equals(pointArray[l]) || pointR.equals(pointArray[l])) {
                            continue;
                        }

                        Point [] fourPoints = new Point[4];
                        fourPoints[0] = pointP;
                        fourPoints[1] = pointQ;
                        fourPoints[2] = pointR;

                        Point pointS = pointArray[l];
                        fourPoints[3] = pointS;
                        //System.out.println("s = " + pointS);

                        double slopePS = pointP.slopeTo(pointS);
                        //System.out.println("slope ps = " + slopePS);
                        if (slopePQ != slopePS) {
                            continue;
                        }

                        //System.out.println("Before => " + fourPoints[0] + " -> " + fourPoints[1] + " -> " + fourPoints[2] + " -> " + fourPoints[3]);

                        Arrays.sort(fourPoints);

                        System.out.println(fourPoints[0] + " -> " + fourPoints[1] + " -> " + fourPoints[2] + " -> " + fourPoints[3]);

                    }
                }
            }
        }

        file.close();
    }
}
