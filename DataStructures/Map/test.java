public class test{
  public static void main(String args[]){
    /*LinkedList<Integer> l = new LinkedList<>();
    for(int i = 0; i < 10; i++)
      l.insert(new Integer(i));
    System.out.println(String.format("At the front of the list is %d",l.first().getData())); */
    Map<Staff,String> m = new Map<>();
    Staff s[] = new Staff[3];
    s[0] = new Staff("abc123");
    s[1] = new Staff("aaa000");
    s[2] = new Staff("zzz999");
    int i = 0;
    for(Staff cp : s){
      m.set(cp,"" + i);
      i++;
    }
    for(Staff cp : s)
      System.out.println(String.format("The map has at key '%s': %s",cp.getID(),m.get(cp)));
    System.out.println("s[0] and s[1] compare as " + s[0].compareTo(s[1]));
  }
}
