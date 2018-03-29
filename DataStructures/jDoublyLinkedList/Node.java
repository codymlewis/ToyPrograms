/**
 * Node.java - jDataStructures
 * Author: Cody Lewis
 * SN: 3283349
 * Date: 05-MAR-2018
 * Mod.: 05-MAR-2018
 * Description:
 * Defines the node class for use in a number of linear data structures
 */
public class Node<obj>{
  // Member variables
  private obj data;
  private Node<obj> prev;
  private Node<obj> next;
  
  // Constructors
  public Node(){
    data = null;
    prev = null;
    next = null;
  }
  public Node(obj data){
    this.data = data;
    prev = null;
    next = null;
  }
  public Node(obj data,Node<obj> prev,Node<obj> next){
    this.data = data;
    this.prev = prev;
    this.next = next;
  }
  // Mutators
  public void setData(obj data){
    this.data = data;
  }
  public void setPrev(Node<obj> prev){
    this.prev = prev;
  }
  public void setNext(Node<obj> next){
    this.next = next;
  }

  // Queries
  public obj getData(){
    return data;
  }
  public Node<obj> getPrev(){
    return prev;
  }
  public Node<obj> getNext(){
    return next;
  }
}
