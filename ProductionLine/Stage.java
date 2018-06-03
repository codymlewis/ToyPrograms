import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
/**
 * <h1>Stage - Production Line</h1>
 * The production stages which process items from the
 * preceeding storage
 *
 * @author Cody Lewis - c3283349
 * @since 04-MAY-2018
 */
public class Stage{
  private String id;
  private Item produce; // The item currently being processed
  private boolean processing, processed, blocked, starved; // status booleans
  private Storage predecessor, successor; // A stage only links to one storage on either side
  private double mean, range; // refers to the time calculation
  private Queue<Double> starveTimes, blockTimes;
  /**
   * Input Constructor
   * @param id the id of this Stage
   * @param predecessor Storage object before this
   * @param successor Storage object after this
   * @param mean The average process time
   * @param range The range process time
   */
  public Stage(String id, Storage predecessor, Storage successor, double mean, double range){
    this.id = id;
    produce = null;
    processing = false;
    processed = false;
    blocked = false; // start unblocked and starved as there is no flow yet
    starved = true;
    this.predecessor = predecessor;
    if(this.predecessor != null)
      this.predecessor.setNext(this);
    this.successor = successor;
    if(this.successor != null)
      this.successor.setPrev(this);
    this.mean = mean;
    this.range = range;
    starveTimes = new LinkedList<>();
    starveTimes.add(new Double(0)); // is starved when created
    blockTimes = new LinkedList<>();
  }
  // Item handling
  /**
   * Calculate the amount of time the current held item will take to Process
   * @return the time to process the item
   */
  public double timeToProcess(){
    Random r = new Random();
    double d = r.nextDouble();
    final double EPSILON = 0.5; // a small value in the equation
	  return (mean + (range * (d - EPSILON)));
  }
  /**
   * Query whether this is empty
   * @return true if empty else false
   */
  public boolean isEmpty(){ return produce == null; }
  /**
   * Start Processing the held item
   * @return true if starting to process else false
   */
  public boolean startProcessing(double curTime){
    if(processing || produce == null){
      return false;
    }
    processed = false;
    processing = true;
    return true;
  }
  /**
   * Query whether an Item is currently being processed
   * @return true if processing else false
   */
  public boolean isProcessing(){
    return processing;
  }
  /**
   * Query whether this can currently process an item
   * @return true if there is a held item else false
   */
  public boolean canProcess(){ return produce != null; }
  /**
   * Query whether this is able to pass it's Item
   * @return true if it can pass else false
   */
  public boolean canPass(){ return !successor.full(); }
  /**
   * Finish the processing of an Item
   * @return true if processing is finished else false
   */
  public boolean finishProcessing(double curTime){
    if(processing && !processed){
      processing = false;
      processed = true;
      return true;
    }
    return false;
  }
  /**
   * Query whether the held Item has been processed
   * @return true if processed else false
   */
  public boolean processed(){ return processed; }
  /**
   * Put processed Item into successor storage
   * @param curTime the current time
   * @return true if Item is passed else false
   */
  public boolean pass(double curTime){
    if(processed){
      if(successor.full()){ // if next storage is full block this
        if(!blocked){
          blocked = true;
          blockTimes.add(new Double(curTime));
        }
        return false;
      } else {
        processed = false; // no longer a processed item here
        // put Item into next storage
        successor.take(produce, curTime);
        produce = null;
        // unblock previous stage
        predecessor.unblock(curTime);
        // unstarve next stage
        successor.unstarve(curTime);
        take(curTime);
      }
      return true;
    } else {
      return false;
    }
  }
  /**
   * Take an Item from the storage before this
   * @param curTime The current time
   * @return true if an item is taken else false
   */
  public boolean take(double curTime){
    if(predecessor.empty() || produce != null){
      if(produce == null && !starved){
        starved = true;
        starveTimes.add(new Double(curTime));
      }
      return false;
    } else {
      produce = predecessor.give(curTime);
      produce.enter(id);
      return true;
    }
  }
  // Block and Starve
  /**
   * Allow this to pass its item
   * @param curTime the current time
   * @return true when completed
   */
  public boolean unblock(double curTime){
    if(blocked){
      blocked = false;
      blockTimes.add(new Double(curTime));
      pass(curTime); // now pass on the Item
    }
    return true;
  }
  /**
   * Allow this to take items
   * @param curTime the current time
   * @return true when completed
   */
  public boolean unstarve(double curTime){
    if(starved){
      starved = false;
      starveTimes.add(new Double(curTime));
      take(curTime); // now take an Item
    }
    return true;
  }
  // Graph traversal/mutating methods
  /**
   * Get the Storage before this
   * @return the previous Storage
   */
  public Storage prev(){ return predecessor; }
  /**
   * Get the Storage after this
   * @return the next Storage
   */
  public Storage next(){ return successor; }
  /**
   * Set the Storage before this
   * @param prev the Storage to be before this
   * @return true upon method completion
   */
  public boolean setPrev(Storage prev){
    predecessor = prev;
    return true;
  }
  /**
   * Set the Storage after this
   * @param next the Storage to be after this
   * @return true upon method completion
   */
  public boolean setNext(Storage next){
    successor = next;
    return true;
  }
  // Stat methods
  /**
   * Get statistics of this Stage
   * @return a String of stats on this Stage
   */
  public String stats(){
    String str = id + " stats:\n";
    Double timeStarved, timeBlocked, timeProcessing;
    timeStarved = timeSpent(starveTimes);
    timeBlocked = timeSpent(blockTimes);
    timeProcessing = TimeKeep.TIME_LIMIT - (timeStarved + timeBlocked); // if it isn't blocked or starving then it is processing
    return String.format("%s\t%5.2f%%\t%f\t%f", id, (timeProcessing / TimeKeep.TIME_LIMIT)*100, timeStarved, timeBlocked);
  }
  // Calculate the times spent doing a thing based on the Queue sent in
  private Double timeSpent(Queue<Double> timeQueue){
    Double time = new Double(0);
    while(timeQueue.size() != 0){
      Double inTime = timeQueue.remove();
      if(timeQueue.size() != 0){
        Double outTime = timeQueue.remove();
        time += outTime - inTime;
      } else {
        time += TimeKeep.TIME_LIMIT - inTime;
      }
    }
    return time;
  }
}
