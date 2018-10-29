import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
/**
 * IndirectHeap.java - Comp2230A1
 * A binary min indirect heap implementation
 *
 * @author Cody Lewis: c3283349
 * @since 2018-10-01
 */
public class IndirectHeap<T extends Comparable<T>> {
    private ArrayList<T> key; // data in the heap
    private ArrayList<Integer> into, outof; // heap controllers
    private int size; // indicate the size of the heap
    private HashMap<String, Integer> memoize; // map the objects to their index in the key
    /**
     * Default constructor
     */
    public IndirectHeap() {
        key = new ArrayList<>();
        into = new ArrayList<>(); // states the index from the key to the heap
        outof = new ArrayList<>(); // states the index from the heap to the key
        size = 0;
        memoize = new HashMap<>();
    }
    /**
     * Sift the specified index down the heap
     * @param i The specified index
     */
    protected void siftdown(int i) {
        int child = 2 * (i + 1); // maintain nice child properties
        while(child <= size) {
            if(child < size && key.get(outof.get(child - 1)).compareTo(key.get(outof.get(child))) > 0) {
                child++; // get the smaller child
            }
            if(key.get(outof.get(child - 1)).compareTo(key.get(outof.get(i))) < 0) {
                swap(i, child - 1); // swap if the child is smaller than the parent
            } else {
                break; // otherwise the heap is sufficiently sorted
            }
            i = child - 1;
            child *= 2;
        }
    }
    /**
     * Remove the head of the heap
     */
    protected void delete() { delete(0); }
    /**
     * Remove an item from the heap (references heap indexing)
     * @param i The index of the item to remove
     */
    protected void delete(int i) {
        if(size > 0) {
            swap(i, size - 1); // swap places with the last index
            size--;
            siftdown(i);
        }
    }
    /**
     * Increase the priority of an item
     * @param rmItem The item to update from
     * @param newItem The item to update to
     */
    protected void increase(T rmItem, T newItem) {
        Integer index = indexOf(rmItem);
        if(index != null) {
            key.set(outof.get(index), newItem);
            siftup(index);
        } else {
            insert(newItem);
        }
    }
    /**
     * Find the heap(outof) index of a particular item
     * @param item The particular item
     * @return The heap index of the item
     */
    protected Integer indexOf(T item) {
        Integer keyIndex = memoize.get(item.toString());
        if(keyIndex == null) {
            return null;
        }
        return into.get(keyIndex);
    }
    /**
     * Insert an element into the heap
     * @param val The value of the element to insert
     */
    protected void insert(T val) {
        int addIndex = size;
        if(key.size() > size) {
            key.set(outof.get(size), val); // into and outof already correctly point to this
            memoize.put(val.toString(), outof.get(size));
        } else {
            key.add(val);
            memoize.put(val.toString(), size);
            outof.add(size, size);
            into.add(size, size);
        }
        siftup(size);
        size++;
    }
    /**
     * Move a value up the heap to its correct place
     * @param i The heap index of the value
     */
    private void siftup(int i) {
        int parent = (i + 1) / 2 - 1, child = i;
        while(parent >= 0) {
            if(key.get(outof.get(child)).compareTo(key.get(outof.get(parent))) > 0) {
                break; // Already sufficiently setup
            }
            swap(parent, child); // move the child up
            child = parent;
            parent = (parent + 1) / 2 - 1; // This ugly thing maintains the indexing and heap properties together
        }
    }
    /**
     * Swap two nodes within the heap (swapping based on outof)
     * @param i Index of the one element to swap
     * @param j Index of the other element to swap
     */
    private void swap(int i, int j) {
        int tmp = outof.get(i);
        int tmp2 = outof.get(j);
        outof.set(i, outof.get(j));
        outof.set(j, tmp);
        int intoTmp = into.get(tmp);
        into.set(tmp, into.get(tmp2));
        into.set(tmp2, intoTmp);
    }
    /**
     * Get the smallest element in the heap
     * @return The head of the heap
     */
    protected T smallest() { return key.get(outof.get(0)); }
    /**
     * Check whether the heap is empty
     * @return true if empty else false
     */
    public boolean isEmpty() { return size == 0; }
}
