public class Map<K extends Comparable<K>, V> {
  private Tree<Pair<K, V>> contents;
  public Map() {
    contents = new Tree<>();
  }
  public void set(K key, V value) {
    Pair<K, V> add = new Pair<>(key, value);
    contents.add(add);
  }
  public V get(K key) {
    Pair<K, V> find = new Pair<>(key, null);
    return contents.find(find).getValue();
  }
}
