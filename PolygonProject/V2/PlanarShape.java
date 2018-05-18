/**
 * <h1>PlanarShape.java - Assignment 2</h1> 
 * Defines the abstract class for a planar shape
 * Modified: 18-APR-2018
 *
 * @author Cody Lewis - c3283349
 * @version 1.0
 * @since 02-APR-2018
 */
public abstract class PlanarShape implements Comparable<PlanarShape>{
  /**
   * The collection of points
   */
  protected Queue<Point> points;
  /**
   * The Default Constructor
   */
  public PlanarShape(){
    points = new Queue<Point>();
  }
  /**
   * Get a String representing the shape
   * @return A String representing the shape
   */
  public abstract String toString();
  /**
   * Calculate the area of the shape
   * @return a double representing the area
   */
  public abstract double area();
  /**
   * Find the distance of shape from the origin
   * @return a double representing the distance from the origin
   */
  public abstract double originDistance();
  /**
   * Add a point to the shape
   * @param p the point to be added to the shape
   */
  final public void addPoint(Point p){
    points.enqueue(p);
  }
  /**
   * Compare the shape to another 
   * @param o the other shape
   * @return 1 if this bigger than the other, -1 if smaller and 0 if equal
   */
  @Override
  public int compareTo(PlanarShape o){
  // if this is bigger than that return 1 else return -1
  // if equal, then one with closer originDistance is prioritized
    double A = area(); // store the area for a faster calculations
    final double ran = 0.0005; // 0.05%
    double minima = A - A*ran;
    double maxima = A + A*ran;
    double oA = o.area();
    double oMinima = oA - oA*ran;
    double oMaxima = oA + oA*ran;
    if((oA>=minima && oA<=maxima) || (A>=oMinima && A<=oMaxima)){
      return originDistance() > o.originDistance() ? 1 : originDistance() == o.originDistance() ? 0 : -1;
    } else { // they are never equal here
      return A > oA ? 1 : -1;
    }
  }
}
