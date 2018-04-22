/* Author: Cody Lewis
 * Date:   18-APR-2018
 */
public class MNode<K,V>{
  // Members
  private V data;
  private K key;
  private MNode<K,V> prev;
  private MNode<K,V> next;
  // Constructors
  public MNode(){
    data = null;
    prev = null;
    next = null;
  }
  public MNode(K key,V data,MNode<K,V> prev,MNode<K,V> next){
    this.key = key;
    this.data = data;
    this.prev = prev;
    this.next = next;
  }
  // Mutators
  public void setKey(K key){ this.key = key; }
  public void setData(V data){ this.data = data; }
  public void setPrev(MNode<K,V> prev){ this.prev = prev; }
  public void setNext(MNode<K,V> next){ this.next = next; }
  // Queries
  public K getKey(){ return key; }
  public V getData(){ return data; }
  public MNode<K,V> getPrev(){ return prev; }
  public MNode<K,V> getNext(){ return next; }
}
