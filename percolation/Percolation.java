package percolation;

import algs4.WeightedQuickUnionUF;

/**
 * User: bharadwaj
 * Date: 27/08/13
 * Time: 10:44 PM
 */
public class Percolation {

    private int [] connArray;
    private int arrayDimension;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    private enum STATE {
        BLOCKED, OPEN;

        public int value() {
            switch (this) {
                case BLOCKED:
                    return 0;
                case OPEN:
                    return 1;
            }
            return 0;
        }
    }

    // Just checks dimensions and throws IndexOutOfBoundsException if out of range
    private void checkDimensions(int i, int j) {
        if(i > arrayDimension) {
            throw new java.lang.IndexOutOfBoundsException("index = " + i + " greater than dimension = " + arrayDimension);
        }
        if(j > arrayDimension) {
            throw new java.lang.IndexOutOfBoundsException("index = " + j + " greater than dimension = " + arrayDimension);
        }
    }

    private int getCell(int i, int j) {
        return (j * arrayDimension) + i;
    }

    // PUBLIC methods here on...

    /**
     * create N-by-N grid, with all sites blocked.
     * Since java initializes integer array with 0, no need to explicity mark 0 for each array element
     * @param N dimension of the two-dimensional array. Both dimensions are equal
     */
    public Percolation(int N) throws Exception {
        if(N <= 0) {
            throw new Exception("Grid size should be greater than 0");
        }
        connArray = new int[N*N];
        arrayDimension = N;

        weightedQuickUnionUF = new WeightedQuickUnionUF(N);
    }

    /**
     * open site (row i, column j) if it is not already
     * @param i row number
     * @param j column number
     */
    public void open(int i, int j) {
        checkDimensions(i,j);
        int cell = getCell(i,j);
        connArray[cell] = STATE.OPEN.value();
    }

    /**
     * is site (row i, column j) open?
     * @param i row number
     * @param j column number
     * @return true if site is open; false otherwise
     */
    public boolean isOpen(int i, int j) {
        checkDimensions(i,j);
        int cell = getCell(i,j);
        if(connArray[cell] == STATE.OPEN.value()) {
            return true;
        }
        return false;
    }

    /**
     * is site (row i, column j) full?
     * @param i row number
     * @param j column number
     * @return true if i/j belong to a full site; false otherwise
     */
    public boolean isFull(int i, int j) {
        checkDimensions(i,j);
        if(isOpen(i,j) == false) {
            return false;
        }

        return false;
    }

    /**
     * does the system percolate?
     * @return true if system percolates; false otherwise
     */
    public boolean percolates() {
        return false;
    }
}
