/**
 * CDLList.java - jDataStructures
 * Author: Cody Lewis
 * SN: 3283349
 * Date: 05-MAR-2018
 * Mod.: 05-MAR-2018
 * Description:
 * Defines the circular doubly linked list class
 */
public class CDLList<obj>{
  // Member Variables
  private Node<obj> sentinal,current;
  public boolean isEmpty;

  // Constructor
  public CDLList(){
    isEmpty = true;
    sentinal = new Node<obj>(null);
    sentinal.setPrev(sentinal);
    sentinal.setNext(sentinal);
    current = sentinal;
  }

  // Mutators
  public void push(obj data){
    if(isEmpty){
      sentinal.setData(data);
      isEmpty = false;
    } else {
      current = new Node<obj>(data,sentinal,sentinal.getNext());
      sentinal.getNext().setPrev(current);
      sentinal.setNext(current);
    }
  }
 public obj pop(){
    obj data = sentinal.getData();
    current = sentinal;
    sentinal = sentinal.getNext();
    sentinal.setPrev(current.getPrev());
    nullify(current);
    current = sentinal;
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
    for(current = sentinal.getNext();current != sentinal; current = current.getNext()){
      str = String.format("%s%d, ",str,current.getData());
    }
    return str;
  }
}
