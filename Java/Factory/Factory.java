import java.util.Scanner;
public class Factory{
  public static void main(String args[]){
    Scanner console = new Scanner(System.in);
    int lines;
    System.out.print("How many lines do you want? ");
    lines = console.nextInt();
    LinesArr la = new LinesArr();
    for(int i = 0; i < lines; i++){
      double x1,y1,x2,y2;
      System.out.print("Input the first x then y: ");
      x1 = console.nextDouble();
      y1 = console.nextDouble();
      System.out.print("Input the second x then y: ");
      x2 = console.nextDouble();
      y2 = console.nextDouble();
      Line l;
      if(y1 == y2){
        l = new VerticalLine(x1);
      } else {
        double m = Math.abs(y2 - y1)/Math.abs(x2 - x1);
        double b = y1 - (m*x1);
        l = new Line(m,b);
      }
      la.addLine(l);
    }
    System.out.println(la.toString());
  }
}
