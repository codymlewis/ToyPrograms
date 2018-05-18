import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * <h1>PA2b.java - Assignment 2</h1>
 * The main thread of the SENG2200 Assignment 2
 * Modified: 02-MAY-2018
 *
 * @author Cody Lewis - c3283349
 * @version 1.0
 * @since 26-APR-2018
 */
public class PA2{  
  public static void main(String args[]){
    PA2 intFace = new PA2();
    intFace.run(args); // this allows for the main functions to not be static
  }
  // the runtime function (interface of the program)
  private void run(String args[]){
    System.out.println("Started PA2");
    MyPolygons mp = new MyPolygons();
    if(args.length == 0){
      // default interface
      Scanner console = new Scanner(System.in);
      boolean exit = false;
      while(!exit){
        System.out.println("\nPlease choose an option:");
        System.out.println("Add Planar Shapes(1) View Planar Shapes(2) View Sorted Planar Shapes(3) Exit(9)");
        int choice = console.nextInt();
        switch(choice){
          case 1: 
            System.out.print("Input your shape: ");
            console.nextLine();
            String line = console.nextLine();
            mp = addShape(mp,line);
            break;
          case 2:
            System.out.println(String.format("The shapes are:\n%s",mp.toString()));
            break;
          case 3:
            mp.sort();
            System.out.println(String.format("The sorted shapes are:\n%s",mp.toString()));
            break;
          case 9:
            exit = true;
            break;
          default:
            System.out.println("That input was invalid, please try again.");
            break;
        }
      }
    } else {
      // File reading interface
      Scanner fstream;
      for(int i = 0; i < args.length; i++){
        try{
        // file reading
          fstream = new Scanner(new File(args[i]));
          while(fstream.hasNextLine()){
            String line = fstream.nextLine();
            mp = addShape(mp,line);
          }
        } catch(FileNotFoundException fnfe){
          System.out.println("The file " + args[i] + " was not found");
          if(args.length == 1){
            return;
          } else {
            continue;
          }
        }
        fstream.close();
      }
      System.out.println(String.format("The planar shapes are:\n%s",mp.toString()));
      mp.sort();
      System.out.println(String.format("The Planar shapes in order is\n%s",mp.toString()));
    }
  }

  // the shape adding function
  private MyPolygons addShape(MyPolygons mp,String line){
    String shapeLetters = "PCS"; // the possible starting letters for a shape input
    String someShapesLeft = "([" + shapeLetters + "].+)";
    if(line.matches(someShapesLeft)){ // regex matching, regex is easier to extend
      String regexStr = "(["+shapeLetters+"].+?[^"+shapeLetters+"]??)"; // to match from instance of letter then points, but not the next part
      while(line.matches(regexStr)){
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()){
          int begin = matcher.start();
          int end = matcher.find() ? matcher.start() : line.length(); // if next find then end = start of next match, else end = end of line
          String shapeText = line.substring(begin,end);
          PlanarShape ps = ShapeFactory(shapeText);
          mp.addShape(ps);
          line = line.substring(end);
        } else {
          break;
        }
      }
    }
    return mp;
  }

  // The factory function
  private PlanarShape ShapeFactory(String input){
    PlanarShape shape;
    String id = input.substring(0,1);
    Point points[];
    Scanner sstream;
    switch(id){
      case "P":
        sstream = new Scanner(input.substring(1));
        int vertices = sstream.nextInt();
        points = new Point[vertices];
        for(int i = 0; i < vertices; i++){
          double x = sstream.nextDouble();
          double y = sstream.nextDouble();
          points[i] = new Point(x,y);
        }
        shape = new Polygon(points);
        break;
      case "C":
        sstream = new Scanner(input.substring(1));
        points = new Point[1];
        for(int i = 0; i < 1; i++){
          double x = sstream.nextDouble();
          double y = sstream.nextDouble();
          points[i] = new Point(x,y);
        }
        double radius = sstream.nextDouble();
        shape = new Circle(points,radius);
        break;
      case "S":
        sstream = new Scanner(input.substring(1));
        // semi-circle input
        points = new Point[2];
        for(int i = 0; i < 2; i++){
          double x = sstream.nextDouble();
          double y = sstream.nextDouble();
          points[i] = new Point(x,y);
        }
        shape = new SemiCircle(points);
        break;
      default:
        System.out.println("Input invalid");
        return null;
    }
    return shape;
  }
}
