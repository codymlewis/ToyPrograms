import java.util.*;

public class Salary
{
    /*Salary.java - Lab 2
    *Author: Regina Baretta
    * Modified by: Cody Lewis
    * Student No. 3283349
    * Date: 16-03-2017
    * Description:
    * Program to calculate weekly salary from total number of hours worked
    * modified to include minimum retainer pay of 200 dollars per week
    * modified to include bonus to the total salary
    */
    
  // Declares constants for hourly pay
  private static int NORMALPAY = 10;
  private static int EXTRAPAY = 15;
  private static int BONUS = 200;
  
    // Constants for bonus factors
    private static double RAISEDBONUS = 1.1;
    private static double LOWEREDBONUS = 1.05;

  public static void main (String[] args)
  {
  Scanner console = new Scanner(System.in);
    // Declaration of variable for hours total earnings
    double normal1,extra1,total1,normal2,extra2,total2,grandTotal;
    

    

    
  // Allows the user to input hours and creates loop until a valid value is input
    
  do{
  System.out.println("Please enter number of normal hours in week 1: ");
  normal1 = console.nextDouble();
   }while (normal1 < 0);
    
   
   do{
   System.out.println("Please enter number of extra hours in week 1: ");
  extra1 = console.nextDouble();
   }while (extra1 < 0);
  
  // Saves the variable total as the product of 10 and "normal" added with the product of 15 and "extra" added with 200
  total1 = NORMALPAY*normal1+extra1*EXTRAPAY+BONUS;
  
  do{
  System.out.println("Please enter number of normal hours in week 2: ");
  normal2 = console.nextDouble();
  }while (normal2 < 0);
  
  do{
   System.out.println("Please enter number of extra hours in week 2: ");
  extra2 = console.nextDouble();
  }while (extra2 < 0);
  
  total2 = NORMALPAY*normal2+EXTRAPAY*extra2+BONUS;
  
  // Calculates and adds a bonus to the total bonus
  if (total1 < 500) {
   total1 = total1*RAISEDBONUS;
  }
  else if (total1<1000){
     total1 = total1*LOWEREDBONUS;
    
  }
  
   if (total2 < 500) {
   total2 = total2*RAISEDBONUS;
  }
  else if (total2<1000){
     total2 = total2*LOWEREDBONUS;
    
  }
  // Adds the totals
  grandTotal = total1+total2;
  
  // Outputs a line involving the variable "grandTotal"
  System.out.println("Total salary for weeks 1 and 2 is: $"+grandTotal+"\n");
   }
}