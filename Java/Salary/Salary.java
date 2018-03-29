import java.util.*;

public class Salary
{
    /*Salary.java - Lab 1
    *Author: Regina Baretta
    * Modified by: Cody Lewis
    * Student No. 3283349
    * Date: 09-03-2017
    * Description:
    * Program to calculate weekly salary from total number of hours worked
    * modified to include minimum retainer pay of 200 dollars per week
    */
    

  public static void main (String[] args)
  {
// Adds a new scanner
  Scanner console = new Scanner(System.in);
// Declares three doubles
    double normal,extra,total;
// Outputs text and moves to a new line
  System.out.println("Please Enter number of normal hours: ");
// Saves the next input of a double from the keyboard as the variable "normal"
  normal = console.nextDouble();
// Outputs text and moves to a new line
   System.out.println("Please Enter number of extra hours: ");
// Saves the next input of a double from the keyboard as the variable "extra"
  extra = console.nextDouble();
// Saves the variable total as the product of 10 and "normal" added with the product of 15 and "extra" added with 200
  total = 10*normal+15*extra+200;
// Outputs a line involving the variable "total"
  System.out.println("Total salary is: "+total+"\n");
   }
}
