/**
 * <h1>Point.java - Assignment 2</h1>
 * Defines the Point class to be in Planar Shapes
 *
 * @author Cody Lewis - c3283349
 * @version 1.0
 * @since 02-APR-2018
 */
public class Point{
  // Members
  private double x;
  private double y;
  // Constructors

  /**
   * Default Constructor
   */
  public Point(){
    x = 0;
    y = 0;
  }
  /**
   * Input Constructor
   * @param x the x component for the point
   * @param y the y component for the point
   */
  public Point(double x,double y){
    this.x = x;
    this.y = y;
  }
  // Queries

  /**
   * Get the x value
   * @return the x component
   */
  public double getX(){
    return x;
  }
  /**
   * Get the y value
   * @return the y component
   */
  public double getY(){
    return y;
  }
  /**
   * Find the distance from the origin
   * @return a double representing the distance from the origin
   */
  public double distFromOrigin(){
    double a = x*x; // c = sqrt(a^2 + b^2)
    double b = y*y; // squaring gets rid of possible -ves
    return Math.sqrt(a + b);
  }
  /**
   * Get a String representing the point
   * @return a String showing the point
   */
  public String toString(){
    return String.format("(%4.2f,%4.2f)",x,y);
  }
}
