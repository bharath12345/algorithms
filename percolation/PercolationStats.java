package percolation;

/**
 * User: bharadwaj
 * Date: 27/08/13
 * Time: 10:44 PM
 */
public class PercolationStats {

    private static int gridN, computeT;
    private static double [] results;

    /**
     * perform T independent computational experiments on an N-by-N grid
     *
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) throws Exception {
        for (int i = 0; i < T; i++) {
            int j = 1;
            Percolation percolation = new Percolation(N);
            while (!percolation.percolates()) {
                j++;
                if(j > (N*N)) {
                    throw new Exception("Experiment number = " + i + " FAILED. Did not percolate at all. Implementation is broken.");
                }

                int row = (int) (Math.random() * N);
                int col = (int) (Math.random() * N);

                if(percolation.isOpen(row, col)) {
                    // this was already open
                    continue;
                }

                percolation.open(row, col);
            }
            double fraction = (j/(N*N));
            results[i] = fraction;
            System.out.println("Experiment number = " + i + ". Fraction of sites open when grid percolated = " + fraction);
        }
    }

    /**
     * sample mean of percolation threshold
     *
     * @return
     */
    public double mean() {
        double mean = 0;
        for (int i = 0; i < computeT; i++) {
            mean += results[i];
        }
        mean = (mean/computeT);
        return mean;
    }

    /**
     * sample standard deviation of percolation threshold
     *
     * @return
     */
    public double stddev() {
        double mean = mean();
        double sumOfSquares = 0;
        for (int i = 0; i < computeT; i++) {
            double meanDiff = results[i]-mean;
            sumOfSquares += Math.pow(meanDiff, 2);
        }
        double variance = sumOfSquares/computeT;
        double stdDev = Math.sqrt(variance);
        return stdDev;
    }

    /**
     * returns lower bound of the 95% confidence interval
     *
     * @return
     */
    public double confidenceLo() {
        return (mean() - (1.96 * stddev()));
    }

    /**
     * returns upper bound of the 95% confidence interval
     *
     * @return
     */
    public double confidenceHi() {
        return (mean() + (1.96 * stddev()));
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Exactly 2 params required");
            return;
        }

        gridN = Integer.parseInt(args[0]);
        computeT = Integer.parseInt(args[1]);
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
