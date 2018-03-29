import java.util.*;


public class SwitchErrors
{ 
  
    /*SwitchErrors.java - Lab 3
    * Modified by: Cody Lewis
    * Student No. 3283349
    * Date: 16-03-2017
    * Description:
    * Program to evaluate a colour based on a number that is input
    */
  
 public static void main(String[] args)
 {
  int number; 
   
  Scanner keyboard = new Scanner(System.in);

  System.out.println("Key: 1=blue, 2=red, 3=green");
  System.out.print("Enter an number and I'll return ");
  System.out.print(" the corresponding color. ");
  number = keyboard.nextInt();

  switch(number)
  {
   case 1:
    System.out.println("You choose red!");
    break;
   case 2:
    System.out.println("You choose blue!");
    break;
   case 3:
    System.out.println("You choose green!");
    break;
   default:
    System.out.println("Colour not available!");
    break;
  }

 }
}