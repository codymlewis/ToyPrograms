public class MapList<K,V>{
  protected MNode<K,V> head,tail,current;
  private boolean isEmpty;
  public MapList(){
    head = null;
    tail = null;
    current = null;
    isEmpty = true;
  }
  private void firstAdd(K key,V data){
    head = new MNode<K,V>(key,data,null,null);
    tail = head;
    current = head;
  }
  public void insert(K key,V data){
  // insert node in front of head
    if(isEmpty){
      firstAdd(key,data);
      isEmpty = false;
    } else {
      MNode<K,V> temp = new MNode<K,V>(key,data,null,head);
      head.setPrev(temp);
      head = temp;
      temp = null;
    }
  }
  public MNode<K,V> first(){
    return head;
  }
}
