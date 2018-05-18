/**
 * <h1>SemiCircle.java - Assignment 2</h1>
 * The SemiCircle object extension of PlanarShape
 * Modified 27-APR-2018
 * @see PlanarShape
 *
 * @author Cody Lewis - c3283349
 * @version 1.0
 * @since 03-APR-2018
 */
public class SemiCircle extends PlanarShape{
  // Member
  private double radius;
  // Constructors

  /**
   * Default Constructor
   */
  public SemiCircle(){
    super();
    radius = 0;
  }
  /**
   * Input Constructor
   * @param p the array of points to be input into this
   */
  public SemiCircle(Point p[]){
    super();
    if(p.length == 2){
      for(Point cp : p){
        addPoint(cp);
      }
      this.radius = calcRadius();
    }
  }
  // Queries

  private double calcRadius(){
    if(points.empty()){
      return 0;
    } else { // if points is not empty, its length has to be 2
      Point cp = points.dequeue();
      Point cp2 = points.dequeue();
      double a,b;
      if(cp.getX() > cp2.getX()){
        a = cp.getX() - cp2.getX();
      } else {
        a = cp2.getX() - cp.getX();
      }
      if(cp.getY() > cp2.getY()){
        b = cp.getY() - cp2.getY();
      } else {
        b = cp2.getY() - cp.getY();
      }
      points.enqueue(cp); // put points back into the Queue
      points.enqueue(cp2);
      return (Math.sqrt(a*a + b*b));
    }
  }
  /**
   * Output a String representing this object
   * @return a String representing this object
   */
  @Override
  public String toString(){
    if(points.empty()){
      return String.format("SEMI=[]:%5.2f",area());
    } else {
      String str = "SEMI=[";
      Point cp = points.dequeue();
      Point cp2 = points.dequeue();
      str = str + cp.toString() + cp2.toString() + "]:";
      points.enqueue(cp);
      points.enqueue(cp2);
      str = str + String.format("%5.2f",area());
      return str;
    }
  }
  /**
   * Calculate the area
   * @return a double representing the area
   */
  @Override
  public double area(){
    if(points.empty()){
      return 0;
    } else {
      return (Math.PI*radius*radius)/2;
    }
  }
  /**
   * Calculate the distance from the origin
   * @return a double representing the distance from the origin
   */
  @Override
  public double originDistance(){
    if(points.empty()){
      return 0;
    } else {
      Point cp[] = new Point[4];
      cp[0] = points.dequeue();
      cp[1] = points.dequeue();
      double gradient = Math.abs(cp[1].getY() - cp[0].getY()) / Math.abs(cp[1].getX() - cp[0].getX()); // change of between the two points
      double tangent = 1 / gradient; // perpendicular to the change
      // now use pythagoras thm to find points
      double a = tangent * radius; // change in y-axis
      a *= a; // square a
      double c = radius * radius; // change in both axes squared
      double b = Math.sqrt(c - a); // rearrangement of Pythagoras Thm
      cp[2] = new Point(cp[0].getX()+b, cp[0].getY()+a);
      cp[3] = new Point(cp[0].getX()-b, cp[0].getY()-a); // points of symmetry
      // find which one is closest to the origin
      double minima = cp[0].distFromOrigin();
      for(int i = 1; i < cp.length; i++){
        if(cp[i].distFromOrigin() < minima){
          minima = cp[i].distFromOrigin(); // find smallest distance
        }
      }
      points.enqueue(cp[0]);
      points.enqueue(cp[1]);
      return minima;
    }
  }
}
