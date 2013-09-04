package percolation;

/**.
 * User: bharadwaj
 * Date: 27/08/13
 * Time: 10:44 PM
 */
public class PercolationStats {

    private static int gridN, computeT;
    private static double [] results;

    private int [][] testArray = {{0,1},{0,0},{1,1}};

    /**
     * perform T independent computational experiments on an N-by-N grid
     * @param N size of the square grid. The API specifies that valid indices are between 1 and N where N > 1.
     * @param T number of compute experiments. This count should be greater than 0.
     */
    public PercolationStats(final int N, final int T) throws Exception {
        for (int i = 0; i < T; i++) {
            int j = 0;
            Percolation percolation = new Percolation(N); // the API specifies that valid row and column indices are between 1 and N.
            System.out.print("[Experiment num: " + (i+1) + "][ Opening = ");
            while (!percolation.percolates()) {

                int row = (int) (Math.random() * N);
                int col = (int) (Math.random() * N);

                //int row = testArray[j][0];
                //int col = testArray[j][1];
                System.out.print("(" + row + "," + col + ") ");

                if (percolation.isOpen(row, col)) {
                    // this was already open
                    continue;
                }

                j++;
                if (j > (N * N)) {
                    throw new Exception("Experiment number = " + i + " FAILED. Did not percolate at all. Implementation is broken.");
                }

                percolation.open(row, col);
            }
            System.out.println(" ]");
            double fraction = (double)j / (N * N);
            results[i] = fraction;
            System.out.println("Unique opens = " + j + ". Fraction of sites open when grid percolated = " + fraction);
        }
    }

    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean() {
        double mean = 0;
        for (int i = 0; i < computeT; i++) {
            mean += results[i];
        }
        mean = (mean / computeT);
        return mean;
    }

    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        double mean = mean();
        double sumOfSquares = 0;
        for (int i = 0; i < computeT; i++) {
            double meanDiff = results[i] - mean;
            sumOfSquares += Math.pow(meanDiff, 2);
        }
        double variance = sumOfSquares / computeT;
        double stdDev = Math.sqrt(variance);
        return stdDev;
    }

    /**
     * returns lower bound of the 95% confidence interval
     * @return
     */
    public double confidenceLo() {
        return (mean() - (1.96 * stddev()));
    }

    /**
     * returns upper bound of the 95% confidence interval
     * @return
     */
    public double confidenceHi() {
        return (mean() + (1.96 * stddev()));
    }

    /**
     * Runs tests to find the probability that a system percolates by finding mean, stddev and confidence interval
     * @param args
     *      First parameter is the size of the square grid. The API specifies that valid indices are between 1 and N where N > 1.
     *      Second parameter is the number of compute experiments. This count should be greater than 0.
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception("Exactly 2 params required");
        }

        gridN = Integer.parseInt(args[0]);
        if (gridN < 2) {
            throw new Exception("Grid size should be greater than 1");
        }
        System.out.println("N = " + gridN);

        computeT = Integer.parseInt(args[1]);
        if (computeT <= 0) {
            throw new Exception("Number of computes should be greater than 0");
        }
        System.out.println("T = " + computeT);

        results = new double[computeT];

        PercolationStats ps = null;
        try {
            ps = new PercolationStats(gridN, computeT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("mean\t\t\t\t\t\t= " + ps.mean());
        System.out.println("stddev\t\t\t\t\t\t= " + ps.stddev());
        System.out.println("95% confidence interval\t\t= " + ps.confidenceHi() + ", " + ps.confidenceLo());
    }
}
