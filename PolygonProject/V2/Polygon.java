/**
 * <h1>Polygon.java - Assignment 2</h1>
 * The Polygon object extension of PlanarShape
 * Modified: 17-APR-2018
 *
 * @author Cody Lewis - c3283349
 * @version 1.0
 * @since 02-APR-2018
 */
public class Polygon extends PlanarShape{
  /**
   * Default Constructor
   */
  public Polygon(){
    super();
  }
  /**
   * Input Constructor
   * @param p the array of points to be in this object
   */
  public Polygon(Point p[]){
    super();
    for(Point cp : p){
      addPoint(cp);
    }
  }
  /**
   * Get a String representing the Polygon
   * @return A String representing the Polygon
   */
  @Override
  public String toString(){
    String str = "POLY=[";
    if(!points.empty()){
      Queue<Point> cp = new Queue<Point>();
      while(!points.empty()){
        str = str + points.peek().toString();
        cp.enqueue(points.dequeue());
      }
      points = cp;
    }
    str = str + "]:" + String.format("%5.2f",area());
    return str;
  }
  /**
   * Calculate the area of the Polygon
   * @return the area of the Polygon as a double
   */
  @Override
  public double area(){
    if(points.empty()){
      return 0;
    } else {
      Queue<Point> cp = new Queue<Point>();
		  Point i,nextI;
			double A = 0;
      while(!points.empty()){
      	// (1/2)*ABS(SIGMA(i=0)(n-2)(x_i+1 + x_i)*(y_i+1 - y_i))
				i = points.dequeue();
        nextI = (points.empty() && !cp.empty()) ? cp.peek() : points.peek();
        if(nextI == null){ // case of single point
          return 0;
        }
        A += (nextI.getX() + i.getX())*(nextI.getY() - i.getY());
        cp.enqueue(i);
      }
      points = cp;
      A = 0.5*Math.abs(A);
			return A;
    }
  }
  /**
   * Find how far from the origin this Polygon is
   * @return the distance from the origin as a double
   */
  @Override
  public double originDistance(){
    if(points.empty()){
      return 0;
    } else {
      Queue<Point> cp = new Queue<Point>();
      double smallVal = points.peek().distFromOrigin();
      cp.enqueue(points.dequeue());
      double runVal;
      while(!points.empty()){
        runVal = points.peek().distFromOrigin();
        if(runVal < smallVal){
          smallVal = runVal;
        }
        cp.enqueue(points.dequeue());
      }
      points = cp;
      return smallVal;
    }
  }
}
