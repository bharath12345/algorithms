package queues;

import java.util.Iterator;

/**.
 * User: bharadwaj
 * Date: 05/09/13
 * Time: 2:02 PM
 */
public class Deque<Item> implements Iterable<Item> {

    private class Node {
        /** data item */
        private Item item;

        /** pointer to the topmost item in the deque */
        private Node first;

        /** pointer to the next item, i.e., topmost to bottom */
        private Node next;

        /** pointer to the previous item, i.e., from bottom to topmost  */
        private Node previous;

        /** pointer to the bottommost item in the deque */
        private Node last;
    }

    /**
     * pointer to the topmost in the deque
     */
    private Node topmost = null;

    /**
     * pointer to the bottommost in the deque
     */
    private Node bottommost = null;

    /**
     * length of the deque
     */
    private int length = 0;

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
        if (topmost == null || bottommost == null) {
            return true;
        }
        return false;
    }

    /**
     * return the number of items on the deque
     * @return
     */
    public int size() {
        return length;
    }

    /**
     * add when the topmost is null
     * @param item
     */
    private void add(final Item item) {
        topmost = new Node();
        topmost.item = item;

        topmost.first = topmost;
        topmost.last = topmost;

        topmost.next = null;
        topmost.previous = null;

        bottommost = topmost;
    }

    /**
     * insert the item at the front
     * @param item
     */
    public void addFirst(final Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("trying to add null item");
        }

        length++;

        if (topmost == null) {
            add(item);
            return;
        }

        Node newNode = new Node();
        newNode.item = item;

        newNode.first = newNode;
        newNode.last = bottommost;

        newNode.next = topmost;
        newNode.previous = null;

        topmost.previous = newNode;
        topmost = newNode;
    }

    /**
     * insert the item at the end
     * @param item
     */
    public void addLast(final Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("trying to add null item");
        }

        length++;

        if (bottommost == null) {
            add(item);
            return;
        }

        Node newNode = new Node();
        newNode.item = item;

        newNode.last = newNode;
        newNode.first = topmost;

        newNode.next = null;
        newNode.previous = bottommost;

        bottommost.next = newNode;
        bottommost = newNode;
    }

    /**
     * delete and return the item at the front
     * @return
     */
    public Item removeFirst() {
        if (size() == 0) {
           throw new java.util.NoSuchElementException("trying to remove element from an empty deque");
        }

        length--;

        Node temp = topmost;
        topmost = topmost.next;

        Item value = temp.item;
        temp.first = null;
        temp.last = null;
        temp.next = null;
        temp.previous = null;
        temp = null;
        return value;
    }

    /**
     * delete and return the item at the end
     * @return
     */
    public Item removeLast() {
        if (size() == 0) {
            throw new java.util.NoSuchElementException("trying to remove element from an empty deque");
        }

        length--;

        Node temp = bottommost;
        bottommost = bottommost.previous;

        Item value = temp.item;
        temp.first = null;
        temp.last = null;
        temp.next = null;
        temp.previous = null;
        temp = null;
        return value;
    }

    /**
     * return an iterator over items in order from front to end
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