/**
 * PriorityQueue.java - Comp2230A1
 * A priority queue implementation
 *
 * @author Cody Lewis: c3283349
 * @since 2018-10-01
 */
public class PriorityQueue<T extends Comparable<T>> extends IndirectHeap<T> {
    /**
     * Default Constructor
     */
    public PriorityQueue() {
        super();
    }
    /**
     * See the first element of this PriorityQueue
     * @return The highest priority element of the queue
     */
    public T peek() { return smallest(); }
    /**
     * Take the highest priority element out of the queue
     * @return The highest priority element of the queue
     */
    public T poll() {
        T temp = smallest();
        delete();
        return temp;
    }
    /**
     * Add an element into the queue
     * @param val The value of the element to add to the queue
     */
    public void add(T val) {
        insert(val);
    }
    /**
     * Update the priority of a specified item to a new one
     * @param ogItem The item to update
     * @param update The item to update to
     */
    public void updatePriority(T ogItem, T update) {
        increase(ogItem, update);
    }
}
