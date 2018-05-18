import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
/**
 * <h1>LinkedList.java - Assignment 2</h1>
 * A generic circular LinkedList
 * Modified: 03-MAY-2018
 *
 * @author Cody Lewis - c3283349
 * @version 1.0
 * @since 03-APR-2018
 */
public class LinkedList<T> implements Iterable<T>{
  /**
   * The start of the list (allows for direct access to head and tail)
   */
  protected Node<T> sentinal;
  /**
   * The query variable used for finding whether the list is empty
   */
  protected boolean isEmpty;
  private int modCount; // Count of modifications
  /**
   * Default Constructor
   */
  public LinkedList(){
    isEmpty = true;
    sentinal = new Node<T>();
    sentinal.setPrev(sentinal);
    sentinal.setNext(sentinal);
    modCount = 0;
  }
  /**
   * Iterator inner Object call function
   * @return An Iterator
   */
  @Override
  public Iterator<T> iterator(){ 
    return new ListIter();
  }
  private class ListIter implements Iterator<T>{
    // Member variables
    private Node<T> current;
    private int expectModCount;
    private int pos;

    // Constructor
    private ListIter(){
      current = sentinal;
      expectModCount = modCount;
      pos = 0;
    }
    /**
     * Check whether there is a node after the iterator
     * @return true if there is a next Node else false
     */
    @Override
    public boolean hasNext(){
      if(modCount != expectModCount){
        throw new ConcurrentModificationException("This object has been modified concurrently");
      }
      return (!isEmpty && current.getNext() != sentinal);
    }
    /**
     * Iterate to the next Node
     * @return the value in the next Node
     */
    @Override
    public T next(){
      if(modCount != expectModCount){
        throw new ConcurrentModificationException("This object has been modified concurrently");
      }
      if(hasNext()){
        current = current.getNext();
        pos++;
        return current.getData();
      } else {
        throw new NoSuchElementException("There are no more elements");
      }
    }
    /**
     * Remove the current Node
     */
    @Override
    public void remove(){
      if(modCount != expectModCount){
        throw new ConcurrentModificationException("This object has been modified concurrently");
      }
      if(isEmpty){
        return;
      } else {
        Node<T> temp = current.getNext();
        current.getPrev().setNext(temp);
        temp.setPrev(current.getPrev());
        current = temp;
        temp = null;
        modCount++;
        if(sentinal.getNext() == sentinal){
          isEmpty = true;
        }
      }
      expectModCount++;
    }
  }
  /**
   * Push onto the head of the List
   * @param data the value to be in the new Node
   */
  public void prepend(T data){ 
    Node<T> n = new Node<T>(data,sentinal,sentinal.getNext());
    sentinal.getNext().setPrev(n);
    sentinal.setNext(n);
    modCount++;
    if(isEmpty){
      isEmpty = false;
    }
  }
  /**
   * Push onto the tail of the List
   * @param data the value to be in the new Node
   */
  public void append(T data){
    Node<T> n = new Node<T>(data,sentinal.getPrev(),sentinal);
    sentinal.getPrev().setNext(n);
    sentinal.setPrev(n);
    modCount++;
    if(isEmpty){
      isEmpty = false;
    }
  }
  /**
   * Look at the data at the head of the List
   * @return the data from the node at the head of the List
   */
  public T peek(){
    return isEmpty ? null : sentinal.getNext().getData();
  }
  /**
   * Retrieve the data from the head of the List and delete it
   * @return the data formerly stored at the head of the List
   */
  public T popHead(){
    if(isEmpty){
      return null;
    } else {
      Node<T> head = sentinal.getNext();
      T obj = head.getData();
      Node<T> temp = head.getNext();
      temp.setPrev(head.getPrev());
      head.getPrev().setNext(temp);
      temp = null;
      head = null;
      if(sentinal.getNext() == sentinal){
        isEmpty = true;
      }
      modCount++;
      return obj;
    }
  }
}
