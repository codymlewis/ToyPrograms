/**
 * DLList.java - jDataStructures
 * Author: Cody Lewis
 * SN: 3283349
 * Date: 05-MAR-2018
 * Mod.: 05-MAR-2018
 * Description:
 * Defines the doubly linked list class
 */
public class DLList<obj>{
  // Member Variables
  private Node<obj> head,tail,current;
  public boolean isEmpty;
  
  // Constructor
  public DLList(){
    isEmpty = true;
    head = new Node<obj>();
    tail = head;
    current = tail;
  }
  
  // Mutators
  public void pushHead(obj data){
    if(isEmpty){
      head.setData(data);
      isEmpty = false;
    } else {
      current = head;
      head = new Node<obj>(data,null,current);
      current.setPrev(head);
      current = head;
    }
  }
  public void pushTail(obj data){
    if(isEmpty){
      head.setData(data);
      isEmpty = false;
    } else {
      current = tail;
      tail = new Node<obj>(data,current,null);
      current.setNext(tail);
      current = tail;
    }
  }
  public obj popHead(){
    obj data = head.getData();
    current = head;
    head = head.getNext();
    nullify(current);
    head.setPrev(null);
    current = head;
    return data;
  }
  public obj popTail(){
    obj data = tail.getData();
    current = tail;
    tail = tail.getPrev();
    nullify(current);
    tail.setNext(null);
    current = tail;
    return data;
  }
  private void nullify(Node<obj> n){
    n.setData(null);
    n.setPrev(null);
    n.setNext(null);
  }
  // Queries
  public String toString(){
    String str = "";
    for(current = head;current != tail; current = current.getNext()){
      str = String.format("%s%d, ",str,current.getData());
    }
    return str;
  }
}
