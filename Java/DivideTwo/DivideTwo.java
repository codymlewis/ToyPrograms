import java.util.*;

public class DivideTwo{
    /*Divide.java - Lab 3
    * Modified by: Cody Lewis
    * Student No. 3283349
    * Date: 16-03-2017
    * Description:
    * Program to calculate a division
    */
  
 public static void main(String[] args){
  Scanner keyboard = new Scanner(System.in);
  int numerator;
  int denominator;

  System.out.println("This program divides two numbers.");
  System.out.print("Enter the numerator: ");
  numerator = keyboard.nextInt();
  System.out.print("Enter the denominator: ");
  denominator = keyboard.nextInt();
  
  
  if (denominator == 0){
    
      do{
    System.out.println("Division by zero is undefined");
    System.out.println("Please try again");
    
      System.out.print("Enter the numerator: ");
      numerator = keyboard.nextInt();
      System.out.print("Enter the denominator: ");
      denominator = keyboard.nextInt();
    
      if(denominator != 0){
        System.out.print(numerator + "/" + denominator + " = ");
        System.out.println((double) numerator/denominator);
      }
      
    }while(denominator == 0);
  }
  else{
  System.out.print(numerator + "/" + denominator + " = ");
  System.out.println((double) numerator/denominator);
  }
  }
}
