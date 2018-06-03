import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
/**
 * <h1>Storage - Production Line</h1>
 * The storages between stages in the Production Line
 * Modified: 09-MAY-2018
 *
 * @author Cody Lewis - c3283349
 * @since 04-MAY-2018
 */
public abstract class Storage{
  private String id;
  private int qmax, size;
  private Queue<Item> items;
  private double avgStorageTime, avgNumItems, eventTime;
  private int itemNum;
  protected Stage prev[];
  protected Stage next[];
  /**
   * Default Constructor, makes max number of items infinite,
   * Used at either end of the Production line.
   */
  public Storage(String id){
    this.id = id;
    qmax = -1;
    items = new LinkedList<Item>();
    size = 0;
    avgStorageTime = 0;
    itemNum = 0;
    avgNumItems = 0;
    eventTime = 0;
  }
  /**
   * Value Constructor assigns max number of items to the parameter
   * @param qmax The maximum number of items that can be in the storage
   */
  public Storage(String id, int qmax){
    this(id);
    this.qmax = qmax;
  }
  /**
   * Set the Stage before this
   * @param prev the Stage to be before this
   */
  public abstract void setPrev(Stage prev);
  /**
   * Set the Stage after this
   * @param next the Stage to be after this
   */
  public abstract void setNext(Stage next);
  // Queries
  /**
   * Check Whether the Storage is full
   * @return true if full else false
   */
  public boolean full(){ return (qmax == -1 ? false : size == qmax); }
  /**
   * Check whether the storage is empty
   * @return true if empty else false
   */
  public boolean empty(){ return qmax == -1 ? false : size == 0; }
  /**
   * Iterate to the previous stage
   * @return the stage before this
   */
  public Stage[] prev(){ return prev; };
  /**
   * Iterate to the next stage
   * @return the stage after this
   */
  public Stage[] next(){ return next; };
  // Mutators
  /**
   * Give something the item at the head of the Storage
   * @return former head of the Storage queue
   */
  public Item give(double curTime){ 
    if(qmax == -1){ // when this is the first storage return a new Item
      return new Item();
    }
    if(size == 0){
      return null;
    } else {
      size--;
      updateAverageItemNum(curTime);
      Item it = items.remove();
      it.unstore(curTime);
      avgStorageTime += it.storageTime();
      return it;
    }
  }
  /**
   * Take an item from something
   * @param it Item to be stored at the tail of the Storage queue
   * @return true if item is added else false
   */
  public boolean take(Item it, double curTime){ 
    if(it == null){
      return false;
    }
    it.store(curTime);
    size++;
    itemNum++;
    updateAverageItemNum(curTime);
    return items.add(it);
  }
  // calculate the running average based on the specified step
  private void updateAverageItemNum(double curTime){
    avgNumItems += (size * (curTime - eventTime)) / TimeKeep.TIME_LIMIT;
    eventTime = curTime;
  }
  /**
   * Unblock the Stage before this
   * @param curTime the current time
   * @return true if unblocked else false
   */
  public boolean unblock(double curTime){
    if(prev[0] == null){
      return false;
    }
    boolean unblock[] = new boolean[prev.length]; // each piece starts as null
    for(int i = 0; i < prev.length; i++){
      if(prev[i] != null){
        unblock[i] = prev[i].unblock(curTime);
      }
    }
    for(boolean b : unblock){ // generic or statement
      if(b){
        return true;
      }
    }
    return false;
  }
  /**
   * Unstarve the Stage after this
   * @param curTime the current time
   * @return true if Unstarved else false
   */
  public boolean unstarve(double curTime){
    if(next[0] == null){
      return false;
    }
    boolean unstarve[] = new boolean[next.length]; // each piece starts as null
    for(int i = 0; i < next.length; i++){
      if(next[i] != null){
        unstarve[i] = next[i].unstarve(curTime);
      }
    }
    for(boolean b : unstarve){ // generic or statement
      if(b){
        return true;
      }
    }
    return false;     
  }
  /**
   * Get the stats about the Storage during runtime
   * @return a String in tabular form of the Storage stats
   */
  public String stats(){
    avgStorageTime /= itemNum;
    updateAverageItemNum(TimeKeep.TIME_LIMIT);
    return String.format("%s\t%f\t%f", id, avgStorageTime, avgNumItems);
  }
  /**
   * Get statistics on the items held in this
   * @return a String containing the statistics
   */
  public String itemStats(){
    String str = "Total number of Items held by each path\n";
    Map<String, Integer> pathNums = new HashMap<>();
    for(Item cp : items){
      if(pathNums.containsKey(cp.path())){
        pathNums.put(cp.path(), pathNums.get(cp.path()) + 1);
      } else {
        pathNums.put(cp.path(), new Integer(1));
      } 
    }
    for(Map.Entry<String, Integer> entry : pathNums.entrySet()){
      str += entry.getKey() + " : " + entry.getValue() + "\n";
    }
    return str;
  }
}
