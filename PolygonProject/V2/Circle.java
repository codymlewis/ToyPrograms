/**
 * <h1>Circle.java - Assignment 2</h1>
 * The Circle object extension of PlanarShape
 * @see PlanarShape
 *
 * @author Cody Lewis - c3283349
 * @version 1.0
 * @since 03-APR-2018
 */
public class Circle extends PlanarShape{
  // Member
  private double radius;
  // Constructors
  /**
   * Default Constructor
   */
  public Circle(){
    super();
    radius = 0;
  }
  /**
   * Input Constructor
   * @param p the array of points to be in this
   * @param radius the radius of this circle
   */
  public Circle(Point p[], double radius){
    super();
    addPoint(p[0]);
    this.radius = Math.abs(radius);
  }
  // Mutator
  /**
   * Change the radius
   * @param radius the value to stored as radius
   */
  public void setRadius(double radius){
    this.radius = Math.abs(radius);
  }
  // Queries
  /**
   * Get a String representing the values in this
   * @return a String representing this object
   */
  @Override
  public String toString(){
    if(points.empty()){
      return String.format("CIRC=[]:%5.2f",area());
    } else {
      return String.format("CIRC=[%s %4.2f]:%5.2f",points.peek().toString(),radius,area());
    }
  }
  /**
   * Calculate the area of this object
   * @return a double value of the area
   */
  @Override
  public double area(){
    if(points.empty()){
      return 0;
    } else {
      return Math.PI*radius*radius;
    }
  }
  /**
   * Calculate the distance this is from the origin
   * @return a double value of the distance from the origin
   */
  @Override
  public double originDistance(){
    if(points.empty()){
      return 0;
    } else {
      return (points.peek().distFromOrigin() - radius);
    }
  }
}
