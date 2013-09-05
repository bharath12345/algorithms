package queues;

import stdlib.StdIn;

/**
 * User: bharadwaj
 * Date: 05/09/13
 * Time: 2:03 PM
 */
public class Subset {

    /**
     * Returns 0 or 1 randomly
     * @return
     */
    private static int randomZeroOne() {
        return (Math.random() < 0.5) ? 0 : 1;
    }

    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        int k = Integer.parseInt(args[0]);
        //System.out.println("k = " + k);

        Deque<String> stringDeque = new Deque<String>();
        //RandomizedQueue<String> stringQueue = new RandomizedQueue<String>();

        int zeroOne;
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();

            // using Deque
            if (randomZeroOne() == 0) {
                //System.out.println("Adding first = " + string);
                stringDeque.addFirst(string);
            } else {
                //System.out.println("Adding last = " + string);
                stringDeque.addLast(string);
            }

            // using RandomizedQueue
            //stringQueue.enqueue(string);
        }

        // using Deque
        for (int i = 0; i < k; i++) {
            if (randomZeroOne() == 0) {
                //System.out.println("Removing first");
                System.out.println(stringDeque.removeFirst());
            } else {
                //System.out.println("Removing last");
                System.out.println(stringDeque.removeLast());
            }
        }

        // using RandomizedQueue
        for (int i = 0; i < k; i++) {
            //System.out.println(stringQueue.sample());
        }
    }
}
