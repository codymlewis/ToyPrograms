    /*SalaryGUI.java - Lab 3
    * Author: Cody Lewis
    * Student No. 3283349
    * Date: 23-03-2017
    * Description:
    * Program to calculate weekly salary from total number of hours worked
    * Modified to have a GUI
    * Modified to calculate the salary after 5 weeks and holds a set rate
    */

import javax.swing.*;
import java.util.*;

public class SalaryGUI {
  
  
       
   // Declares constants for hourly pay
   private static int NORMALPAY = 10;
   private static int EXTRAPAY = 15;
   private static int BONUS = 200;
  
    // Constants for bonus factors
    private static double RAISEDBONUS = 1.1;
    private static double LOWEREDBONUS = 1.05;
    private static double LOWESTBONUS = 1.01;
  
   public static void main (String[] args) 
  { 
    Scanner console = new Scanner(System.in);
    // Declaration of variable for hours total earnings
    double total1,total2,total3,total4,total5,grandTotal;
    double week1,week2,week3,week4,week5; 	// Declares the variable for the weekly hours
    String name;
    String error = "";

    name = JOptionPane.showInputDialog("Please enter employee's name");
    
        
    do{
   	week1 = Double.parseDouble(JOptionPane.showInputDialog(error + "Please enter number of hours in week 1: "));
	
   	error = "Error, entry was invalid, please enter a positive real number. \n";
    }while (week1 < 0 || week1 > 168);
    
    error = "";

    do{
  	 week2 = Double.parseDouble(JOptionPane.showInputDialog(error + "Please enter number of hours in week 2: "));

   	 error = "Error, entry was invalid, please enter a positive real number. \n";
    }while (week2 < 0 || week2 > 168);

    error = "";

    do{
   	week3 = Double.parseDouble(JOptionPane.showInputDialog(error + "Please enter number of hours in week 3: "));

   	error = "Error, entry was invalid, please enter a positive real number. \n";
    }while (week3 < 0 || week3 > 168);

    error = "";

    do{
  	 week4 = Double.parseDouble(JOptionPane.showInputDialog(error + "Please enter number of hours in week 4: "));

   	 error = "Error, entry was invalid, please enter a positive real number. \n";
    }while (week4 < 0 || week4 > 168);
   
    error = "";

    do{
  	 week5 = Double.parseDouble(JOptionPane.showInputDialog(error + "Please enter number of hours in week 5: "));

   	 error = "Error, entry was invalid, please enter a positive real number. \n";
    }while (week5 < 0 || week5 > 168);
  
    error = "";
   
   if (week1 > 40) {
	week1 -=40;
   	total1 = week1 * EXTRAPAY + 40 * NORMALPAY;
  }
  else{
     total1 = week1 * NORMALPAY;
    
   }
  
   if (week2 > 40) {
	week2 -= 40;
   	total2 = week2 * EXTRAPAY + 40 * NORMALPAY;
 }
  else{
     	total2 = week2 * NORMALPAY;
    
  }

   if (week3 > 40) {
	week3 -=40;
   	total3 = week3 * EXTRAPAY + 40 * NORMALPAY;
  }
  else{
     total3 = week3 * NORMALPAY;
    
   }
  
   if (week4 > 40) {
	week4 -= 40;
   	total4 = week4 * EXTRAPAY + 40 * NORMALPAY;
 }
  else{
     	total4 = week4 * NORMALPAY;
    
  }
   if (week5 > 40) {
	week5 -= 40;
   	total5 = week5 * EXTRAPAY + 40 * NORMALPAY;
 }
  else{
     	total5 = week5 * NORMALPAY;
    
  }
  grandTotal = total1+total2+total3+total4+total5;

//  JOptionPane.showMessageDialog(null, "The salary of " + name + " is $"+grandTotal+" (no bonus)");
  if (grandTotal <= 1000)
	  grandTotal*=RAISEDBONUS;
  else if (grandTotal <= 2000)
	  grandTotal*=LOWEREDBONUS;
  else if (grandTotal <= 3000)
	  grandTotal*=LOWESTBONUS;

  
  JOptionPane.showMessageDialog(null, "The salary of " + name + " is $"+grandTotal+"\n");
  System.exit(0);
   }
}
