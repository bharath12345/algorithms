package queues;

import java.util.Iterator;

/**
 * User: bharadwaj
 * Date: 05/09/13
 * Time: 2:03 PM
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private class Node {
        /** data item */
        private Item item;

        /** pointer to the next item, i.e., topmost to bottom */
        private Node next;

        /** pointer to the previous item, i.e., from bottom to topmost  */
        private Node previous;
    }

    /**
     * pointer to the topmost in the deque
     */
    private Node topmost = null;

    /**
     * length of the deque
     */
    private int length = 0;

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
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
     * is the queue empty?
     * @return
     */
    public boolean isEmpty() {
        if (topmost == null) {
            return true;
        }
        return false;
    }

    /**
     * return the number of items on the queue
     * @return
     */
    public int size() {
        return length;
    }

    /**
     * add the item
     * @param item
     */
    public void enqueue(final Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("trying to add null item");
        }

        length++;

        if (topmost == null) {
            topmost = new Node();
            topmost.item = item;
            topmost.next = null;
            topmost.previous = null;
            return;
        }

        Node newNode = new Node();
        newNode.item = item;
        newNode.previous = null;
        newNode.next = topmost;

        topmost.previous = newNode;
        topmost = newNode;
    }

    /**
     * delete and return a random item
     * @return
     */
    public Item dequeue() {
        if (size() == 0) {
            throw new java.util.NoSuchElementException("trying to remove element from an empty deque");
        }

        int random = randomInRange(size());
        length--;

        System.out.println("removing random index = " + random);
        Node randomNode = topmost;
        for (int i = 0; i < random; i++) {
            randomNode = randomNode.next;
        }

        if (random == 0) {
            topmost = topmost.next;
        }

        Node previous = randomNode.previous;
        Node next = randomNode.next;

        if (previous != null) {
            previous.next = next;
        }

        if (next != null) {
            next.previous = previous;
        }

        randomNode.next = null;
        randomNode.previous = null;
        Item item = randomNode.item;
        randomNode = null;

        return item;
    }

    /**
     * return (but do not delete) a random item
     * @return
     */
    public Item sample() {
        if (size() == 0) {
            throw new java.util.NoSuchElementException("trying to remove element from an empty deque");
        }

        int random = randomInRange(size());

        Node randomNode = topmost;
        for (int i = 0; i < random; i++) {
            randomNode = randomNode.next;
        }
        return randomNode.item;
    }

    /**
     * return an independent iterator over items in random order
     * @return
     */
    public Iterator<Item> iterator() {
        Iterator<Item> it = new Iterator<Item>() {

            private Node iteratingNode = topmost;

            @Override
            public boolean hasNext() {
                if (iteratingNode != null) {
                    return true;
                }
                return false;
            }

            @Override
            public Item next() {
                Node temp = iteratingNode;
                iteratingNode = iteratingNode.next;
                return temp.item;
            }

            @Override
            public void remove() {
                throw new java.lang.UnsupportedOperationException("You cannot call remove using a iterator");
            }
        };
        return it;
    }
}
