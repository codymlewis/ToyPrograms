import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;
/**
 * <h1>ProductionLine - Production Line</h1>
 * The collection of Stages and Storages, manages the events
 * of the Production Line
 *
 * @author Cody Lewis
 * @since 09-MAY-2018
 */
public class ProductionLine{
  private Storage head, tail; // Store the first and the last storage
  private TimeKeep tk; // Store the time
  private PriorityQueue<Event> events; // Store a priority queue of the events
  /**
   * Default Constructor
   * @param filename the name of the input file
   * @param mean the mean time for the Stage Production
   * @param range the range time for the Stage production
   * @param qmax the maximum size of a Storage queue
   */
  public ProductionLine(String filename, int mean, int range, int qmax){
    // Construct members
    tk = new TimeKeep();
    events = new PriorityQueue<Event>(Comparator.naturalOrder()); // Ordered in increasing time
    Construct(filename, mean, range, qmax);
  }
  // Construct the ProductionLine
  // param filename: the file that will be used for input
  // param mean: the average time for the stage processing an item
  // param range: the range of time for the stage processing an item
  // param qmax: the maximum size of a Storage queue
  private void Construct(String filename, int mean, int range, int qmax){
    try{
      Scanner fstream = new Scanner(new File(filename));
      Stage[] current = null;
      while(fstream.hasNextLine()){
        String line = fstream.nextLine();
        if(line.contains("Q")){ // Start at a Storage
          Storage curStorage = StageFactory(line, current, mean, range, qmax);
          if(head == null){ // set up the head
            head = curStorage;
          }
          if(current != null){
            for(int i = 0; i < current.length; i++){
              if(current[i] != null){
                current[i].setNext(curStorage);
                curStorage.setPrev(current[i]);
              }
            }
          }
          current = curStorage.next();
        }
      }
      if(current != null){
        for(int i = 0; i < current.length; i++){
          if(current[i] != null){
            tail = new LinearStorage("tail"); // The end is an infinite Storage
            current[i].setNext(tail);
            tail.setPrev(current[i]);
          }
        }
      }
    } catch(FileNotFoundException fnfe){
      System.out.println(String.format("Sorry the file %s was not found, constructing a single Stage Production Line",filename));
      head = new LinearStorage("head");
      tail = new LinearStorage("tail");
      Stage current = new Stage("S00", head, tail, mean, range);
    }
  }
  // Factory method for building stages of the production line
  // param input: the piece of input for the factory
  // param current: the array Stages to be before the Storage created by this
  // param mean: the mean time of the Stage production
  // param range: the range time of the Stage Production
  // param qmax: the maximum size of a Storage queue
  // return: An initialized Storage object pointing to a Stage
  private Storage StageFactory(String input, Stage current[], int mean, int range, int qmax){
    Storage result;
    Scanner sstream = new Scanner(input);
    String id = sstream.next();
    Stage next = new Stage(sstream.next(), null, null, sstream.nextDouble()*mean, sstream.nextDouble()*range);
    if(current == null){
      result = new LinearStorage(id);
    } else {
      if(sstream.hasNext()){ // case of stage linking to 2 Storages
        result = new IntersectingStorage(id, qmax);
        result.setNext(new Stage(sstream.next(), result, null, sstream.nextDouble()*mean, sstream.nextDouble()*range));
      } else if(current.length > 1){
        result = new JoiningStorage(id, qmax); // only 1 Stage after this
      } else { 
        result = new LinearStorage(id, qmax);
      }
      for(int i = 0; i < current.length; i++){
        if(current[i] != null){
          current[i].setNext(result);
          result.setPrev(current[i]);
        }
      }
    }
    result.setNext(next);
    next.setPrev(result);
    return result;
  }
  /**
   * ProductionLine running method,
   * runs the line until the time limit in TimeKeep is reached
   * @return A String of the run-time statistics
   */
  public String run(){
    // Loop through each of the Stages checking for events
    // add events to priority queue, at each event check again
    // unstarve first stage to start loop
    double temp = 0;
    head.unstarve(tk.getTime()); // start up the Production Line
    //head.next()[0].unblock(tk.getTime());
    for(int i = 0; i < head.next().length; i++) // set up first events into queue
      events.add(new Event(head.next()[i], tk.getTime())); // initialize the first event
    double sent = 0;
    double P = events.peek().eventTime();
    while(tk.step(P)){ // progress through the events
      if(events.peek() != null){
        events.poll().call();
      }
      // if Stage canProcess and !isProcessing then add event to Queue
      for(Storage current = head; current != tail; current = current.next()[0].next()){
        for(int i = 0; i < current.next().length; i++){
          if((current.next()[i] != null) && current.next()[i].isEmpty() && !(current.empty())){
            current.next()[i].unstarve(tk.getTime());
          }
          if((current.next()[i] != null) && current.next()[i].canProcess() && !current.next()[i].isProcessing()){
            events.add(new Event(current.next()[i], tk.getTime()));
          } else if(current.next()[i] != null && current.next()[i].processed() && current.next()[i].canPass()){
            events.add(new Event(current.next()[i], tk.getTime()));
          }
        }
      }
      if(events.peek() == null){ // case of no events left to happen
        break;
      }
      P = events.peek().eventTime();
    }
    return stats();
  }
  private String stats(){
    String str = "\nProduction Line Stats:\n";
    String storageStr = "ISQ\tAVG(t)\t\tAVG(Items)\n";
    String stageStr = "Stage\tProd(%)\tStarve(t)\tBlock(t)\n";
    for(Storage current = head; current != tail; current = current.next()[0].next()){
      if(head != current){ // Storage stats
        storageStr += current.stats()+ "\n";
      }
      for(int i = 0; i < current.next().length; i++){ // Stage stats
        stageStr += current.next()[i].stats() + "\n";
      }
    }
    str += stageStr + "\n" + storageStr + "\n" + tail.itemStats();
    return str;
  }
}
