/**
 * <h1>Node.java - Assignment 2</h1> 
 * The Node generic class
 *
 * @author Cody Lewis - c3283349
 * @version 1.0
 * @since 02-APR-2018
 */
public class Node<T>{
  // Members
  private Node<T> prev;
  private Node<T> next;
  private T data;

  // Constructors
  /**
   * Default Constructor
   */
  public Node(){
    data = null;
    prev = null;
    next = null;
  }

  /**
   * Input Constructor
   * @param data the value to be stored in the Node
   */
  public Node(T data){
    this.data = data;
    prev = null;
    next = null;
  }

  /**
   * Input and Assignment Constructor
   * @param data the value to be stored in this
   * @param prev the reference to the node to be before this
   * @param next the reference to the node to be after this
   */
  public Node(T data,Node<T> prev,Node<T> next){
    this.data = data;
    this.prev = prev;
    this.next = next;
  }

  // Mutators

  /**
   * Set the previous reference
   * @param prev the reference to the node to be before this
   */
  public void setPrev(Node<T> prev){
    this.prev = prev;
  } 
  /**
   * Set the next reference
   * @param next the reference to the node to be after this
   */
  public void setNext(Node<T> next){
    this.next = next;
  }
  /**
   * Set the value stored in this
   * @param data the value to be store in this
   */
  public void setData(T data){
    this.data = data;
  }

  // Queries

  /**
   * Get the previous reference
   * @return the reference to the previous Node
   */
  public Node<T> getPrev(){
    return prev;
  }
  /**
   * Get the next reference
   * @return the reference to the next Node
   */
  public Node<T> getNext(){
    return next;
  }
  /**
   * Get the data in this
   * @return the value stored in this Node
   */
  public T getData(){
    return data;
  }
}
