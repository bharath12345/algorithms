package queues;

import java.util.Iterator;

/**
 * User: bharadwaj
 * Date: 05/09/13
 * Time: 2:03 PM
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
    }

    /**
     * is the queue empty?
     * @return
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * return the number of items on the queue
     * @return
     */
    public int size() {
        return 0;
    }

    /**
     * add the item
     * @param item
     */
    public void enqueue(final Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("trying to add null item");
        }

    }

    /**
     * delete and return a random item
     * @return
     */
    public Item dequeue() {
        if (size() == 0) {
            throw new java.util.NoSuchElementException("trying to remove element from an empty deque");
        }
        return null;
    }

    /**
     * return (but do not delete) a random item
     * @return
     */
    public Item sample() {
        if (size() == 0) {
            throw new java.util.NoSuchElementException("trying to remove element from an empty deque");
        }
        return null;
    }

    /**
     * return an independent iterator over items in random order
     * @return
     */
    public Iterator<Item> iterator() {
        return null;
    }
}
