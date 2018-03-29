import javax.swing.*;
import java.util.*;

public class SalaryGUI {
  
  
    /*SalaryGUI.java - Lab 2
    * Author: Cody Lewis
    * Student No. 3283349
    * Date: 16-03-2017
    * Description:
    * Program to calculate weekly salary from total number of hours worked
    * Modified to have a GUI
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
    // to read use: JOptionPane.showInputDialog("message");
    // to write use: JOptionPane.showMessageDialog(null,str,"message body",JOptionPane.INFORMATION_MESSAGE);    
    Scanner console = new Scanner(System.in);
    // Declaration of variable for hours total earnings
    double normal1,extra1,total1,normal2,extra2,total2,grandTotal;
    
        
    do{
   normal1 = Double.parseDouble(JOptionPane.showInputDialog("Please enter number of normal hours in week 1: "));
    }while (normal1 < 0);
    
    do{
   extra1 = Double.parseDouble(JOptionPane.showInputDialog("Please enter number of extra hours in week 1: "));
    }while (extra1 < 0);
    
    total1 = NORMALPAY*normal1+EXTRAPAY*extra1+BONUS;
    
    
    
    do{
   normal2 = Double.parseDouble(JOptionPane.showInputDialog("Please enter number of normal hours in week 2: "));
    }while (normal2 < 0);
    
    do{
   extra2 = Double.parseDouble(JOptionPane.showInputDialog("Please enter number of extra hours in week 2: "));
    }while (extra2 < 0);
    
    total2 = NORMALPAY*normal2+EXTRAPAY*extra2+BONUS;
   
   
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
  grandTotal = total1+total2;
  
  JOptionPane.showMessageDialog(null, "Total salary for weeks 1 and 2 is: $"+grandTotal+"\n");
  
   }
}
