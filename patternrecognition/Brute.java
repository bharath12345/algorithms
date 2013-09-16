package patternrecognition;

import stdlib.In;

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

        for (int i = 0; i < pointArray.length; i++) {
            Point pointP = pointArray[i];
            //System.out.println("p = " + pointP);

            for (int j = i + 1; j < pointArray.length; j++) {
                Point pointQ = pointArray[j];
                //System.out.println("q = " + pointQ);

                double slopePQ = pointP.slopeTo(pointQ);
                //System.out.println("slope pq = " + slopePQ);

                for (int k = j + 1; k < pointArray.length; k++) {
                    Point pointR = pointArray[k];
                    //System.out.println("r = " + pointR);

                    double slopePR = pointP.slopeTo(pointR);
                    //System.out.println("slope pr = " + slopePR);
                    if (slopePQ != slopePR) {
                        continue;
                    }

                    for (int l = k + 1; l < pointArray.length; l++) {
                        Point pointS = pointArray[l];
                        //System.out.println("s = " + pointS);

                        double slopePS = pointP.slopeTo(pointS);
                        //System.out.println("slope ps = " + slopePS);
                        if (slopePQ != slopePS) {
                            continue;
                        }

                        System.out.println(pointP + " -> " + pointQ + " -> " + pointR + " -> " + pointS);

                    }
                }
            }
        }

        file.close();
    }
}
