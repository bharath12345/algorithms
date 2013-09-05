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
     * returns a random within the given range
     * @param range
     * @return
     */
    private static int randomInRange(int range) {
        return (int) (Math.random() * range);
    }

    /**
     * unit testing addFirst and removeFirst
     */
    private static void unitTestDequeFirst() {
        Deque<String> stringDeque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            stringDeque.addFirst(string);
        }

        System.out.println("size = " + stringDeque.size());

        for (String string : stringDeque) {
            System.out.println("string = " + string);
        }

        int len = stringDeque.size();
        for (int i = 0; i < len; i++) {
            System.out.println("removed item = " + stringDeque.removeFirst());
        }
    }

    /**
     * unit testing addLast and removeLast
     */
    private static void unitTestDequeLast() {
        Deque<String> stringDeque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            stringDeque.addLast(string);
        }

        System.out.println("size = " + stringDeque.size());

        for (String string : stringDeque) {
            System.out.println("string = " + string);
        }

        int len = stringDeque.size();
        for (int i = 0; i < len; i++) {
            System.out.println("removed item = " + stringDeque.removeLast());
        }
    }

    /**
     *
     * @param k
     */
    private static void randomUsingDeque(final int k) {
        Deque<String> stringDeque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            if (randomZeroOne() == 0) {
                //System.out.println("Adding first = " + string);
                stringDeque.addFirst(string);
            } else {
                //System.out.println("Adding last = " + string);
                stringDeque.addLast(string);
            }
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

    }

    /**
     *
     * @param k
     */
    private static void randomUsingQueue(final int k) {
        RandomizedQueue<String> stringQueue = new RandomizedQueue<String>();

        int zeroOne;
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            stringQueue.enqueue(string);
        }

        // using RandomizedQueue
        for (int i = 0; i < k; i++) {
            //System.out.println(stringQueue.sample());
        }
    }

    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        int k = Integer.parseInt(args[0]);
        //System.out.println("k = " + k);

        unitTestDequeFirst();
        unitTestDequeLast();

        randomUsingDeque(k);



    }
}
