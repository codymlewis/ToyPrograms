/**
 * <h1>IntersectingStorage - Production Line</h1>
 * Storage for when the split of the fork is pointing away from the beginning
 * Modeified: 18-MAY-2018
 *
 * @author Cody Lewis
 * @since 09-MAY-2018
 */
public class IntersectingStorage extends Storage{
  /**
   * Input Constructor
   * @param qmax the maximum possible size of the Storage queue
   */
  IntersectingStorage(String id, int qmax){
    super(id, qmax);
    prev = new Stage[1];
    next = new Stage[2]; // Always intersects into 2
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
  public void setNext(Stage next){
    if(this.next[0] == null)
      this.next[0] = next;
    else
      this.next[1] = next;
  }
}
