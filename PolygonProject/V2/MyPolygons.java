/**
 * <h1>MyPolygons.java - Assignment 2</h1>
 * A container class for PlanarShapes
 * Modified 03-MAY-2018
 *
 * @author Cody Lewis - c3283349
 * @version 1.0
 * @since 17-APR-2018
 */
public class MyPolygons{
  protected SortedList<PlanarShape> shapes;
  /**
   * Default Constructor
   */
  public MyPolygons(){
    shapes = new SortedList<PlanarShape>();
  }
  /**
   * Add a shape to the container
   * @param newShape the shape to be added to the container
   */
  public void addShape(PlanarShape newShape){
    shapes.append(newShape); // append to retain order
  }
  /**
   * Get a String representation of the shapes in this container
   * @return <code>toString</code>s of the contents of this container
   */
  public String toString(){
    String str = "{";
    if(shapes.peek() != null){
      for(PlanarShape ps : shapes){
        str = str + ps.toString() + ", "; 
      }
      str = str.substring(0,str.lastIndexOf(",")); // remove trailing comma
    }
    return str+"}";
  }
  /**
   * Sort the shapes in this container
   */
  public void sort(){
    SortedList<PlanarShape> newShapes = new SortedList<PlanarShape>();
    newShapes.insertInOrder(shapes);
    shapes = newShapes;
  }
}
