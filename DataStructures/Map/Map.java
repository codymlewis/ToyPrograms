import java.lang.Comparable.*;
public class Map<K extends Comparable<K>,V> extends MapList<K,V>{
  public Map(){
    super();
  }
  public void set(K key,V value){
    super.insert(key,value);
  }
  public V get(K key){
    current = head;
    while(current.getNext() != null){
      if(current.getKey().compareTo(key) == 0){
        return current.getData();
      } else {
        current = current.getNext();
      }
    }
    return null;
  }
}
