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
        if(i >= arrayDimension || i < 0) {
            throw new java.lang.IndexOutOfBoundsException("index = " + i + " is invalid. i should be between [1, " + arrayDimension + "]");
        }
        if(j >= arrayDimension || j < 0) {
            throw new java.lang.IndexOutOfBoundsException("index = " + j + " is invalid. i should be between [1, " + arrayDimension + "]");
        }
    }

    private int getCell(int i, int j) {
        //i -= 1; // since i is always between [1, N] and array is between [0, N-1]
        //j -= 1; // since j is always between [1, N] and array is between [0, N-1]
        return (i * arrayDimension) + j;
    }

    // PUBLIC methods here on...

    /**
     * create N-by-N grid, with all sites blocked.
     * Since java initializes integer array with 0, no need to explicity mark 0 for each array element
     * @param N dimension of the two-dimensional array. Both dimensions are equal
     */
    public Percolation(int N) throws Exception {
        if(N < 2) {
            throw new Exception("The API specifies that valid row and column indices are between 1 and N where N > 1.");
        }
        connArray = new int[N*N];
        arrayDimension = N;
        weightedQuickUnionUF = new WeightedQuickUnionUF(N*N);
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
        //weightedQuickUnionUF.union(i, j);

        int adjacent;
        if(i != 0) {
            adjacent = getCell(i-1, j);
            if(isOpen(i-1, j)) {
                weightedQuickUnionUF.union(cell, adjacent);
            }
        }
        if(i != (arrayDimension-1)) {
            adjacent = getCell(i+1, j);
            if(isOpen(i+1, j)) {
                weightedQuickUnionUF.union(cell, adjacent);
            }
        }

        if(j != 0) {
            adjacent = getCell(i, j-1);
            if(isOpen(i, j-1)) {
                weightedQuickUnionUF.union(cell, adjacent);
            }
        }
        if(j != (arrayDimension-1)) {
            adjacent = getCell(i, j+1);
            if(isOpen(i, j+1)) {
                weightedQuickUnionUF.union(cell, adjacent);
            }
        }
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

        int cell = getCell(i,j);
        if(cell < arrayDimension) {
            // A full site is an open site that can be connected to an open site in the
            //          top row via a chain of neighboring (left, right, up, down) open sites.
            // By the above definition, open cells on the top row are always full
            return true;
        }

        for(int x=0; x<arrayDimension; x++) {
            boolean status = weightedQuickUnionUF.connected(x, cell);
            if(status == true) {
                return true;
            }
        }
        return false;
    }

    /**
     * does the system percolate?
     * @return true if system percolates; false otherwise
     */
    public boolean percolates() {
        // just check if any of the elements in the bottom most row is full
        for(int x=0; x<(arrayDimension-1); x++) {
            if(isFull((arrayDimension-1), x) == true ) {
                return true;
            }
        }
        return false;
    }
}
