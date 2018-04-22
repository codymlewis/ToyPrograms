public class VerticalLine extends Line{
  private double x;
  public VerticalLine(){
    x = 0;
  }
  public VerticalLine(double x){
    this.x = x;
  }
  public double findXInt(){
    return x;
  }
  public boolean biggerThan(Line l){
    return true; // gradient is always bigger than a normal line
  }
  public boolean biggerThan(VerticalLine vl){
  // returns true if this x-int is bigger than that x-int
    return (x > vl.findXInt());
  }
  public String toString(){
    return String.format("x = %f",x);
  }
}
