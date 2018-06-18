public class Staff implements Comparable<Staff>{
  private String staffID;
  public Staff(String staffID){
    this.staffID = staffID;
  }
  public String getID(){ return staffID; }
  @Override
  public int compareTo(Staff o){
    return staffID.compareTo(o.getID());
  }
  public String toString(){ return staffID; }
}
