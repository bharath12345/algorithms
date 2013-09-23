package puzzle8;

/**.
 * User: bharadwaj
 * Date: 19/09/13
 * Time: 7:22 PM
 */
public final class Board {

    /**
     * construct a board from an N-by-N array of blocks (where blocks[i][j] = block in row i, column j)
     * @param blocks
     */
    public Board(final int[][] blocks) {

    }

    /**
     * board dimension N
     * @return
     */
    public int dimension(){
        return 0;
    }

    /**
     * number of blocks out of place
     * @return
     */
    public int hamming() {
        return 0;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     * @return
     */
    public int manhattan(){
        return 0;
    }

    /**
     * is this board the goal board?
     * @return
     */
    public boolean isGoal() {
        return false;
    }

    /**
     * a board obtained by exchanging two adjacent blocks in the same row
     * @return
     */
    public Board twin() {
        return null;
    }

    /**
     * does this board equal y?
     * @param y
     * @return
     */
    public boolean equals(final Object y) {
        return false;
    }

    /**
     *
     * @return
     */
    public int hashCode() {
        return 0;
    }

    /**
     * all neighboring boards
     * @return
     */
    public Iterable<Board> neighbors() {
        return null;
    }

    /**
     * string representation of the board (in the output format specified below)
     * @return
     */
    public String toString() {
        return null;
    }
}
