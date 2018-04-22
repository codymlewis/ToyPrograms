/* Author: Cody Lewis
 * Date:   18-APR-2018
 */
public class Node<T>{
  // Members
  private T data;
  private Node<T> prev;
  private Node<T> next;
  // Constructors
  public Node(){
    data = null;
    prev = null;
    next = null;
  }
  public Node(T data,Node<T> prev,Node<T> next){
    this.data = data;
    this.prev = prev;
    this.next = next;
  }
  // Mutators
  public void setData(T data){ this.data = data; }
  public void setPrev(Node<T> prev){ this.prev = prev; }
  public void setNext(Node<T> next){ this.next = next; }
  // Queries
  public T getData(){ return data; }
  public Node<T> getPrev(){ return prev; }
  public Node<T> getNext(){ return next; }
}
