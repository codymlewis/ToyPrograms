import java.util.Scanner;

public class PA3{
  public static void main(String args[]){
    if(args.length == 3){
      String argv = "";
      for(String cp : args){
        argv += cp + " ";
      }
      Scanner astream = new Scanner(argv);
      String filename = "PLine.dat";
      int mean = astream.nextInt();
      int range = astream.nextInt();
      int qmax = astream.nextInt();
      System.out.println(String.format("Constructing the Production Line in accordance to %s, with the mean = %d, range = %d and max queue size = %d.", filename, mean, range, qmax));
      ProductionLine pl = new ProductionLine(filename, mean, range, qmax);
      System.out.println(pl.run());
    } else {
      System.out.println("Not enough arguments provided, please run this again with mean, range then max size of queue specified.");
    }
    System.exit(0);
  }
}
