import java.util.*;

public class SMSCalc{

 public static void main(String[] args)
 {
  Scanner console = new Scanner(System.in);

  // Declarations for the program 
  int numSms;
  double cost = 0.22;
 
  // Declares double average
  System.out.print("Please Enter number of messages: ");
  numSms = console.nextInt();

  double totalCost = (numSms * cost) + 10;
  // Prints the processes of the code
  System.out.print("The total cost for " + numSms + " messages is $");
  System.out.println(totalCost);
 }
}
