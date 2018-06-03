import java.util.Comparator;
/**
 * Event - Production Line
 * An object actioning the events within the production line
 * Modified: 18-MAY-2018
 *
 * @author Cody Lewis
 * @since 10-MAY-2018
 */
public class Event implements Comparable<Event>, Comparator<Event>{
  private double time; // the time of the event
  private Stage action; // The Stage that will do something
  /**
   * Default constuctor
   * @param action The Stage that will peform the processing
   */
  public Event(Stage action, double curTime){
    this.action = action;
    time = curTime + action.timeToProcess();
    action.startProcessing(curTime);
  }
  /**
   * Get the time of this event
   * @return the time of this event
   */
  public double eventTime(){
    return time;
  }
  /**
   * Call the event
   * @return true on completion of event
   */
  public boolean call(){
    if(action.isProcessing()){
      action.finishProcessing(time);
    }
    action.pass(time);
    return true;
  }
  /**
   * Comparable Override function
   * @param o the event to compare this to
   * @return -1 if this is less than o else if they are equal 0 else 1
   */
  @Override
  public int compareTo(Event o){
    return time < o.eventTime() ? -1 : time == o.eventTime() ? 0 : 1;
  }
  /**
   * Comparator Override function
   * @param a the Event that will be the this in compareTo
   * @param b the Event that will be the that in compareTo
   * @return value of a.compareTo(b)
   */
  @Override 
  public int compare(Event a, Event b){
    return a.compareTo(b);
  }
}
