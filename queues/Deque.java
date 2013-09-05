package queues;

import java.util.*;

/**.
 * User: bharadwaj
 * Date: 05/09/13
 * Time: 2:02 PM
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * construct an empty deque
     */
    public Deque() {

    }

    /**
     * is the deque empty?
     * @return
     */
    public boolean isEmpty() {

    }

    /**
     * return the number of items on the deque
     * @return
     */
    public int size() {

    }

    /**
     * insert the item at the front
     * @param item
     */
    public void addFirst(final Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("trying to add null item");
        }

    }

    /**
     * insert the item at the end
     * @param item
     */
    public void addLast(final Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("trying to add null item");
        }
    }

    /**
     * delete and return the item at the front
     * @return
     */
    public Item removeFirst() {
        if (size() == 0) {
           throw new java.util.NoSuchElementException("trying to remove element from an empty deque");
        }

    }

    /**
     * delete and return the item at the end
     * @return
     */
    public Item removeLast() {
        if (size() == 0) {
            throw new java.util.NoSuchElementException("trying to remove element from an empty deque");
        }
    }

    /**
     * return an iterator over items in order from front to end
     * @return
     */
    public Iterator<Item> iterator() {

    }
}