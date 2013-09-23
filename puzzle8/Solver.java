package puzzle8;

import algs4.MinPQ;

import java.util.Iterator;

/**
 * User: bharadwaj
 * Date: 19/09/13
 * Time: 7:29 PM
 */
public class Solver {

    private class SearchNode implements Comparable<SearchNode> {

        /**
         * @param board
         * @param moves
         * @param previousNode
         */
        SearchNode(final Board board, final int moves, final SearchNode previousNode) {
            this.board = board;
            this.moves = moves;
            this.previousNode = previousNode;
        }

        /**
         *
         */
        private Board board = null;

        /**
         *
         */
        private int moves = 0;

        /**
         *
         */
        private SearchNode previousNode = null;

        /**
         * only used during the traversal for the print
         */
        private SearchNode nextNode = null;

        @Override
        public int compareTo(final SearchNode o) {
            int currentNodePriority = board.manhattan() + moves;
            int searchNodePriority = o.board.manhattan() + o.moves;
            return currentNodePriority - searchNodePriority;
        }
    };

    /**
     *
     */
    private MinPQ<SearchNode> searchBoardPQ = new MinPQ();

    /**
     *
     */
    private MinPQ<SearchNode> swappedBoardPQ = new MinPQ();

    /**
     *
     */
    private boolean isSolvable = false;

    /**
     *
     */
    private SearchNode solution = null;


    /**
     * find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(final Board initial) {

        SearchNode searchNode = new SearchNode(initial, 0, null);
        SearchNode swappedNode = new SearchNode(initial.twin(), 0, null);

        searchBoardPQ.insert(searchNode);
        swappedBoardPQ.insert(swappedNode);

        while (true) {
            SearchNode minPriorityNode = searchBoardPQ.delMin();
            SearchNode minSwappedPriorityNode = swappedBoardPQ.delMin();

            if (minPriorityNode.board.isGoal()) {
                isSolvable = true;
                solution = minPriorityNode;
                break;
            }

            if (minSwappedPriorityNode.board.isGoal()) {
                isSolvable = false;
                solution = null;
                break;
            }

            for (Board neighbor : minPriorityNode.board.neighbors()) {
                if (minPriorityNode.previousNode != null) {
                   if (minPriorityNode.previousNode.equals(neighbor)) {
                       continue;
                   }
                }
                SearchNode neighborNode = new SearchNode(neighbor, (minPriorityNode.moves + 1), minPriorityNode);
                searchBoardPQ.insert(neighborNode);
            }

            for (Board neighbor: minSwappedPriorityNode.board.neighbors()) {
                if (minSwappedPriorityNode.previousNode != null) {
                    if (minSwappedPriorityNode.previousNode.equals(neighbor)) {
                        continue;
                    }
                }
                SearchNode neighborNode = new SearchNode(neighbor, (minSwappedPriorityNode.moves + 1), minSwappedPriorityNode);
                swappedBoardPQ.insert(neighborNode);
            }
        }
    }

    /**
     * is the initial board solvable?
     * @return
     */
    public boolean isSolvable() {
        return isSolvable;
    }

    /**
     * min number of moves to solve initial board; -1 if no solution
     * @return
     */
    public int moves() {
        if (!isSolvable) {
            return -1;
        }

        return solution.moves;
    }

    /**
     *
     * @return
     */
    public SearchNode findFirstInSolution() {
        SearchNode finder = solution;
        finder.nextNode = null;
        while (finder.previousNode != null) {
            SearchNode temp = finder;
            finder = finder.previousNode;
            finder.nextNode = temp;
        }
        return finder;
    }

    /**
     * sequence of boards in a shortest solution; null if no solution
     * @return
     */
    public Iterable<Board> solution() {
        Iterable<Board> iterable = new Iterable<Board>() {

            @Override
            public Iterator<Board> iterator() {
                Iterator<Board> iterator = new Iterator<Board>() {

                    private SearchNode iteratingSearchNode = findFirstInSolution();

                    @Override
                    public boolean hasNext() {
                        if (iteratingSearchNode != null) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public Board next() {
                        SearchNode temp = iteratingSearchNode;
                        iteratingSearchNode = iteratingSearchNode.nextNode;
                        return temp.board;
                    }

                    @Override
                    public void remove() {
                        throw new java.lang.UnsupportedOperationException("You cannot call remove using a iterator");
                    }
                };
                return iterator;
            }
        };
        return iterable;
    }

    /**
     * solve a slider puzzle (given below)
     * @param args
     */
    public static void main(final String[] args) {

    }
}