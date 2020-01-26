public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;  // just picked nextFirst and nextLast arbitrarily
        nextLast = 1;
    }

    /**
     * Creates a deep copy of other.
     */
//    public ArrayDeque(ArrayDeque other) {
//        items = (T[]) new Object[other.items.length];
//        System.arraycopy(other.items, 0, items, 0, other.items.length);
//    }

    /**
     * Resizes the underlying array to the target capacity.
     */
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int i = nextFirst + 1;
        while (size > 0) {
            temp[i % temp.length] = items[i % items.length];

            size--;
        }
        nextLast = (i + 1) % temp.length;
        items = temp;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextFirst--] = item;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        T item = items[nextFirst];
        items[nextFirst] = null;
        if (size < items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        T item = items[nextLast];
        items[nextLast] = null;
        if (size < items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        return item;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Gets the item at the given index.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        return items[index];
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        int i = nextFirst + 1;
        while (size > 0) {
            System.out.print(items[i % items.length] + " ");
            i++;
            size--;
        }
        System.out.println();
    }
}
