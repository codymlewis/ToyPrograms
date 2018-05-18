import java.util.Iterator;
/**
 * <h1>SortedList.java - Assignment 2</h1>
 * A sorted extension of LinkedList
 * Modified: 03-MAY-2018
 *
 * @author Cody Lewis - c3283349
 * @version 1.0
 * @since 17-APR-2018
 */
public class SortedList<T extends Comparable<T>> extends LinkedList<T>{
  /**
   * Perform an insertion sort on the List
   * @param o The List the copy data from
   */
  public void insertInOrder(LinkedList<T> o){
    Iterator<T> it = o.iterator();
    if(!it.hasNext()){ // if is empty
      return;
    }
    while(it.hasNext()){
      T data = it.next(); // iterate
      if(isEmpty){ // for the first addition
        append(data);
      } else {
        Node<T> current = sentinal.getNext();
        boolean noAdd = true;
        while(current != sentinal){ // from head to tail
          int comp = data.compareTo(current.getData());
          if(comp != 1){ // if the current node is smaller than the iterated then put data in a node behind current
            Node<T> temp = new Node<T>(data,current.getPrev(),current);
            current.getPrev().setNext(temp);
            current.setPrev(temp);
            temp = null;
            noAdd = false;
            break;
          }
          current = current.getNext();
        }
        if(noAdd){ // case where the data is bigger than everything else
          append(data);
        }
      }
    }
  }
}
