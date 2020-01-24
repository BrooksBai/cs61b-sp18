public class LinkedListDeque<T> {
    private Node sentinel;  // the first item (if it exists) is at sentinel.next
    private int size; // the number of items in the deque

    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item) {
            this.item = item;
        }
    }

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Creates a deep copy of other. */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
//        Node p = other.sentinel.next;
//        Node p2 = sentinel;
//        while (p != sentinel) {
//            p2.next = new Node();
//            p2.next.prev = p2;
//            p2 = p2.next;
//            p2.item = p.item;
//            p = p.next;
//        }
//        p2.next = sentinel;
//        sentinel.prev = p2;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        Node node = new Node(item);
        node.next = sentinel.next;
        sentinel.next.prev = node;
        sentinel.next = node;
        node.prev = sentinel;
        size++;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        Node node = new Node(item);
        sentinel.prev.next = node;
        node.prev = sentinel.prev;
        node.next = sentinel;
        sentinel.prev = node;
        size++;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        if (p == sentinel) {
            return null;
        }
        return p.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    /** getRecursive helper method */
    private T getRecursive(Node node, int index) {
        if (node == sentinel) {
            return null;
        }
        if (index == 0) {
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }
}
