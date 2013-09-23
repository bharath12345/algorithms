package puzzle8;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 * User: bharadwaj
 * Date: 19/09/13
 * Time: 7:22 PM
 */
public final class Board {

    /**
     * block array
     */
    private final int[][] blocks;

    /**
     * dimension
     */
    private int dimension;

    /**
     * first n prime numbers where n = dimension
     */
    private int[] primes;

    /**
     * construct a board from an N-by-N array of blocks (where blocks[i][j] = block in row i, column j)
     * @param blocks
     */
    public Board(final int[][] blocks) {
        this.blocks = blocks;
        this.dimension = blocks.length;
        if (dimension < 2) {
            throw new java.lang.UnsupportedOperationException("You cannot have dimension less than 2");
        }

        primes = new int[dimension];
        int number = 2;
        for (int i = 0; i < dimension; i++){
            if (isPrime(number)){
                // found a prime number... add it to the array
                primes[i] = number;
            }
            number++;
        }
    }

    /**
     * finds if the passed number is prime
     * @param number
     * @return
     */
    private boolean isPrime(final int number){
        for (int i = 2; i < number; i++){
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * board dimension N
     * @return
     */
    public int dimension() {
        return dimension;
    }

    /**
     * number of blocks out of place
     * @return
     */
    public int hamming() {
        int priority = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != ((i * dimension) + j + 1)) {
                    priority++;
                }
            }
        }
        return priority;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     * @return
     */
    public int manhattan() {
        int priority = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] == ((i * dimension) + j + 1)) {
                    // its in proper position
                } else {
                    int properRow = blocks[i][j] / dimension;
                    int properCol = blocks[i][j] % dimension;
                    priority += Math.abs(properRow - i);
                    priority += Math.abs(properCol - j);
                }
            }
        }
        return priority;
    }

    /**
     * is this board the goal board?
     * @return
     */
    public boolean isGoal() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int value = (i * dimension) + (j + 1);
                if (blocks[i][j] != value) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * a board obtained by exchanging two adjacent blocks in the same row - 7th and 8th blocks i.e. 1st and 2nd blocks in row 3
     * @return
     */
    public Board twin() {
        return new Board(swap(blocks, 0, 1));
    }

    /**
     *
     * @param blocks
     * @param positionOne
     * @param positionTwo
     * @return
     */
    private int[][] swap(final int [][] blocks, final int positionOne, final int positionTwo) {
        int[][] twinBlocks = new int[dimension][dimension];

        for (int i = 0; i< dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                twinBlocks[i][j] = blocks[i][j];
            }
        }

        int colOne = positionOne % dimension;
        int rowOne = positionOne / dimension;

        int colTwo = positionTwo % dimension;
        int rowTwo = positionTwo / dimension;

        if (((colOne - colTwo) > 1) || (colOne - colTwo) < 1) {
            System.out.println("cannot swap columns which are not adjacent");
        }

        if (((rowOne - rowTwo) > 1) || (rowOne - rowTwo) < 1) {
            System.out.println("cannot swap rows which are not adjacent");
        }

        int valueOne = twinBlocks[rowOne][colOne];
        int valueTwo = twinBlocks[rowTwo][colTwo];

        twinBlocks[rowTwo][colTwo] = valueOne;
        twinBlocks[rowOne][colOne] = valueTwo;
        return twinBlocks;
    }

    /**
     * does this board equal y?
     * @param y
     * @return
     */
    public boolean equals(final Object y) {
        Board toCompare = (Board) y;
        if (this.toString().equals(y.toString())) {
            return true;
        }
        return false;
    }

    /**
     * multiply by primes
     * @return
     */
    public int hashCode() {
        long hash = 0;
        for (int i = 0; i < dimension; i++) {
            hash += ((i + 1) * primes[i]);
        }
        return (int) hash;
    }

    /**
     * all neighboring boards
     * @return
     */
    public Iterable<Board> neighbors() {
        /* in a dimension 3 grid -
             - 2 neighbor boards each for 0 in four corners
             - 3 neighbor boards each for 0 in four edge centers
             - 4 neighbor boards for 0 in the center

             The same can be extrapolated to other dimensions...
             - the corners always have only 2 neighbors
             - edge center have 3 neighbors
             - all other blocks have 4 neighbors
         */

        int i = 0, j = 0;
        for (i = 0; i < dimension; i++) {
            for (j = 0; j < dimension; j++) {
                if (blocks[i][j] == 0) {
                    break;
                }
            }
        }

        // now [i, j] hold the position of 0

        List<Board> neighbors = new ArrayList<Board>();
        if (((i == 0) && (j == 0))
                || (((i == 0) && (j == (dimension - 1))))
                || (((i == (dimension - 1)) && (j == 0)))
                || (((i == (dimension - 1)) && (j == (dimension - 1))))) {

            if (((i == 0) && (j == 0))) {
                int positionZero = 0;
                neighbors.add(new Board(swap(blocks, positionZero, positionZero + 1))); // swap positions 0 and 1
                neighbors.add(new Board(swap(blocks, positionZero, positionZero + dimension)));

            } else if ((i == 0) && (j == (dimension - 1))) {
                int positionZero = dimension - 1;
                neighbors.add(new Board(swap(blocks, positionZero, positionZero - 1))); // swap positions dimension - 2, dimension - 1
                neighbors.add(new Board(swap(blocks, positionZero, positionZero + dimension)));

            } else if (((i == (dimension - 1)) && (j == 0))) {
                int positionZero = (i * dimension);
                neighbors.add(new Board(swap(blocks, positionZero, positionZero + 1))); // swap positions
                neighbors.add(new Board(swap(blocks, positionZero, positionZero - dimension)));

            } else {
                int positionZero = (dimension * dimension) - 1;
                neighbors.add(new Board(swap(blocks, positionZero, positionZero - 1))); // swap positions
                neighbors.add(new Board(swap(blocks, positionZero, positionZero - dimension)));
            }

        } else if ((i == 0) || (j == 0) || (i == (dimension - 1)) || (j == (dimension - 1)))  {

            if (i == 0) {
                int positionZero = j;
                neighbors.add(new Board(swap(blocks, positionZero, positionZero - 1)));
                neighbors.add(new Board(swap(blocks, positionZero, positionZero + 1)));
                neighbors.add(new Board(swap(blocks, positionZero, positionZero + dimension)));

            } else if (i == (dimension - 1)) {
                int positionZero = (i * dimension) + j;
                neighbors.add(new Board(swap(blocks, positionZero, positionZero - 1)));
                neighbors.add(new Board(swap(blocks, positionZero, positionZero + 1)));
                neighbors.add(new Board(swap(blocks, positionZero, positionZero - dimension)));

            } else if (j == 0) {
                int positionZero = (i * dimension);
                neighbors.add(new Board(swap(blocks, positionZero, positionZero + 1)));
                neighbors.add(new Board(swap(blocks, positionZero, positionZero + dimension)));
                neighbors.add(new Board(swap(blocks, positionZero, positionZero - dimension)));

            } else {
                int positionZero = (i * dimension) + j;
                neighbors.add(new Board(swap(blocks, positionZero, positionZero - 1)));
                neighbors.add(new Board(swap(blocks, positionZero, positionZero + dimension)));
                neighbors.add(new Board(swap(blocks, positionZero, positionZero - dimension)));
            }

        } else {
            int positionZero = (i * dimension) + j;
            neighbors.add(new Board(swap(blocks, positionZero, positionZero - 1)));
            neighbors.add(new Board(swap(blocks, positionZero, positionZero + 1)));
            neighbors.add(new Board(swap(blocks, positionZero, positionZero + dimension)));
            neighbors.add(new Board(swap(blocks, positionZero, positionZero - dimension)));
        }

        return neighbors;
    }

    /**
     * string representation of the board (in the output format specified below)
     * @return
     */
    public String toString() {
        String newline = "\n";
        String singleSpace = " ";

        StringBuilder sb = new StringBuilder(dimension + newline);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sb.append(singleSpace + blocks[i][j] + singleSpace);
            }
            sb.append(newline);
        }
        sb.append(newline);

        return sb.toString();
    }
}
