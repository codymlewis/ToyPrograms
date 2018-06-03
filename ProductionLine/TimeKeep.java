/**
 * <h1>TimeKeep - Production Line</h1>
 * The keeper of the total time
 * Modified 11-MAY-2018
 *
 * @author Cody Lewis - c3283349
 * @since 30-APR-2018
 */
class TimeKeep{
  public static final double TIME_LIMIT = 10000000; // The time limit of the simulation, write protected but should be accessible
  private double time;
  /**
   * Default constructor
   */
  public TimeKeep(){
    time = 0;
  }
  // Mutator
  /**
   * Step ahead with the time
   * @param P The time gap between the current time and the next event
   * @return True if the time can be advanced else false
   */
  public boolean step(double P){
    if(P <= TIME_LIMIT){
      time = P;
      return true;
    } else {
      time = TIME_LIMIT;
      return false;
    }
  }
  // Query
  /**
   * Get the current time of the simulation
   * @return The current time
   */
  public double getTime(){
    return time;
  }
}
