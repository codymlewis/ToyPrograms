/**
 * <h1>JoiningStorage - Production Line</h1>
 * Storage for when the split of the fork points towards the beginning
 * Modified: 15-MAY-2018
 *
 * @author Cody Lewis
 * @since 09-MAY-2018
 */
public class JoiningStorage extends Storage{
  /**
   * Input Constructor
   * @param qmax the maximum possible size of the Storage queue
   */
  JoiningStorage(String id, int qmax){
    super(id, qmax);
    prev = new Stage[2]; // Always joins 2
    next = new Stage[1];
  }
  /**
   * Set the previous Stage
   * @param prev a Stage to be before this
   */
  @Override
  public void setPrev(Stage prev){
    if(this.prev[0] == null){
      this.prev[0] = prev;
    } else {
      this.prev[1] = prev;
    }
  }
  /**
   * Set the next Stage
   * @param next the Stage to be after this
   */
  @Override
  public void setNext(Stage next){ this.next[0] = next; }
}
