import java.util.LinkedList;
/**
 * <h1>Item - Production Line</h1>
 * The items produced in the Production line
 *
 * @author Cody Lewis - c3283349
 * @since 30-APR-2018
 */
public class Item{
  private double storeTime, unStoreTime;
  private String entries;
  // Constructors
  /**
   * Default Constructor
   */
  public Item(){
    storeTime = 0;
    unStoreTime = 0;
    entries = "";
  }
  // Mutators
  public void enter(String id){ 
    entries += id + " -> ";
  }
  public void store(Double curTime){
    storeTime = curTime;
  }
  public void unstore(Double curTime){
    unStoreTime = curTime;
  }
  public double storageTime(){
    return unStoreTime != 0 ? (unStoreTime - storeTime) : (TimeKeep.TIME_LIMIT - storeTime);
  }
  public String path(){ return entries.substring(0, entries.length() - 4); } // cut off final arrow
}
