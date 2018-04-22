public class Line{
  private double m;
  private double b;
  public Line(){
    m = 0;
    b = 0;
  }
  public Line(double m,double b){
    this.m = m;
    this.b = b;
  }
  public void setM(double m){
    this.m = m;
  }
  public void setB(double b){
    this.b = b;
  }
  public double findY(double x){
    return (m*x + b);
  }
  public double getGradient(){
    return m;
  }
  public double findYInt(){
    return b;
  }
  public boolean biggerThan(Line l){
  // return true if gradient is bigger
  // if gradient is equal +/- 0.00005 then return true if y-int is bigger
    final double equality = 0.00005;
    double minima = m - equality;
    double maxima = m + equality;
    double rhsM = l.getGradient();
    if(rhsM >= minima && rhsM <= maxima){
      return (b > l.findYInt());
    } else {
      return (m > rhsM);
    }
  }
  public String toString(){
    return String.format("y = %fx + %f",m,b);
  }
}
