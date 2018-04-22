public class LinesArr{
  private Line lines[];
  private int length;
  public LinesArr(){
    length = 0;
  }
  public void addLine(Line l){
    if(length > 0){
      Line cp[] = new Line[length+1];
      boolean added = false;
      for(int index = 0; index < length+1; index++){
        if(added){
          cp[index] = lines[index-1];
        } else {
          if(index == length){
            cp[index] = l;
          } else if(!l.biggerThan(lines[index])){
            cp[index] = lines[index];
          } else {
            cp[index] = l; // need to shift now
            added = true;
          }
        }
      }
      length++;
      lines = cp;
    } else {
      length++;
      lines = new Line[length];
      lines[0] = l;
    }
  }
  public String toString(){
    String str = "";
    for(int i = 0; i < length; i++){
      str = str + String.format("(%s), ",lines[i].toString());
    }
    return str;
  }
}
