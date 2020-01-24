public class ArrayDeque<T> {
    T[] items;
    int size;
    int nextFirst;
    int nextLast;


    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Creates a deep copy of other. */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.items.length);
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size++;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        nextFirst = (nextFirst + 1) % items.length;
        T item = items[nextFirst];
        items[nextFirst] = null;
        if (size < items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        return item;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        T item = items[nextLast];
        items[nextLast] = null;
        if (size < items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        return item;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Gets the item at the given index.
     *  If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        return items[index];
    }
}