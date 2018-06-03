/**
 * <h1>LinearStorage - Production Line</h1>
 * A linear version of the storage line
 *
 * @author Cody Lewis - c3283349
 * @since 09-MAY-2018
 */
public class LinearStorage extends Storage{
  /**
   * Default constructor
   */
  public LinearStorage(String id){
    super(id);
    prev = new Stage[1];
    next = new Stage[1];
  }
  /**
   * Input constructor
   * @param qmax the maximum amount of items that can be stored
   */
  public LinearStorage(String id, int qmax){
    super(id, qmax);
    prev = new Stage[1];
    next = new Stage[1];
  }
  /**
   * Set the previous Stage
   * @param prev the Stage to be before this
   */
  @Override 
  public void setPrev(Stage prev){ this.prev[0] = prev; }
  /**
   * Set the next Stage
   * @param next the Stage to be after this
   */
  @Override
  public void setNext(Stage next){ this.next[0] = next; }
}
