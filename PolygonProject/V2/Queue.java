/**
 * <h1>Queue.java - Assignment 2</h1>
 * The Queue generic class
 *
 * @author Cody Lewis - c3283349
 * @version 1.0 
 * @since 02-APR-2018
 */
public class Queue<T>{
  // Members
  private Node<T> head;
  private Node<T> tail;
  private boolean isEmpty;
  // Constructors
  
  /**
   * Default Constructor
   */
  public Queue(){
    isEmpty = true;
  }
  // Mutators

  /**
   * Add to the tail
   * @param data the value to be stored at the tail
   */
  public void enqueue(T data){
    if(isEmpty){
      head = new Node<T>(data);
      tail = head;
      isEmpty = false;
    } else {
      Node<T> temp = new Node<T>(data,tail,null);
      tail.setNext(temp);
      tail = temp;
    }
  }
  /**
   * Remove the head
   * @return the value formally stored at the head
   */
  public T dequeue(){
    T obj = head.getData();
    if(head == tail){
      head = null;
      tail = null;
      isEmpty = true;
    } else {
      Node<T> temp = head.getNext();
      head.setNext(null);
      temp.setPrev(null);
      head = temp;
    }
    return obj;
  }
  // Queries
  /**
   * Get the value from head
   * @return the value currently stored at the head
   */
  public T peek(){
    if(head != null){
      return head.getData();
    } else {
      return null;
    }
  }
  /**
   * Check whether the Queue is empty
   * @return true if empty else false
   */
  public boolean empty(){
    return isEmpty;
  }
}
