/**
 * An implementation of the pair data structure in java
 * @author Cody Lewis
 */
public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
  // Member variables
  private K key;
  private V value;
  /**
   * Default constructor
   */
  public Pair() {
    key = null;
    value = null;
  }
  /**
   * Input constructor
   * @param key The key to be set in this
   * @param value The value to be set in this
   */
  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }
  /**
   * Query for the key
   */
  public K getKey(){ return key; }
  /**
   * Query for the value
   */
  public V getValue(){ return value; }
  /**
   * Store a new key
   * @param key The key to be stored in this
   * @return true on completion
   */
  public boolean setKey(K key) {
    this.key = key;
    return true;
  }
  /**
   * Store a new value
   * @param value The value to be stored in this
   * @return true on completion
   */
  public boolean setValue(V value) {
    this.value = value;
    return true;
  }
  @Override
  public int compareTo(Pair<K, V> o) {
    return key.compareTo(o.getKey());
  }
  public String toString() { return String.format("Pair<%s, %s>", key.toString(), value.toString()); }
}
