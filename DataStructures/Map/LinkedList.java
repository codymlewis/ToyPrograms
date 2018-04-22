public class LinkedList<T>{
  protected Node<T> head,tail,current;
  private boolean isEmpty;
  public LinkedList(){
    head = null;
    tail = null;
    current = null;
    isEmpty = true;
  }
  private void firstAdd(T data){
    head = new Node<T>(data,null,null);
    tail = head;
    current = head;
  }
  public void insert(T data){
  // insert node in front of head
    if(isEmpty){
      firstAdd(data);
      isEmpty = false;
    } else {
      Node<T> temp = new Node<T>(data,null,head);
      head.setPrev(temp);
      head = temp;
      temp = null;
    }
  }
  public Node<T> first(){
    return head;
  }
}
