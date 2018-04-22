    /**
    * TriangleEvaluator.java - Lab 2
    * Author: Cody Lewis
    * Student No 3283349
    * Date: 16-03-2017
    * Description:
    * Program to determine the type of triangle from the sides
    */

import javax.swing.*;
import java.util.*;



public class TriangleEvaluator // class name
{
  

  
  public static void main (String[] args)
  {
   // Declares variables for the sides
   double side1, side2, side3;


   // Accepts the users inputs
   side1 = Double.parseDouble(JOptionPane.showInputDialog("Please enter length of side 1: "));
   side2 = Double.parseDouble(JOptionPane.showInputDialog("Please enter length of side 2: "));
   side3 = Double.parseDouble(JOptionPane.showInputDialog("Please enter length of side 3: "));
   
     
   // Makes an evauation of the inputs  
   if(side3 > (side1 + side2)){
     JOptionPane.showMessageDialog(null, "The triangle is not valid.");
   }
   
   else if(side2 > (side1 + side3)){
     JOptionPane.showMessageDialog(null, "The triangle is not valid.");
   }
   
   else if(side1 > (side2 + side3)){
     JOptionPane.showMessageDialog(null, "The triangle is not valid.");
   }
   
   else{
     if(side1==side2){
              if(side1==side3){
       JOptionPane.showMessageDialog(null, "The triangle is equilatoral.");
     }
       else{
       JOptionPane.showMessageDialog(null, "The triangle is isosceles."); 
       } 
     }
     
     else if(side1==side3){
             if(side2==side3){
       JOptionPane.showMessageDialog(null, "The triangle is equilatoral.");
     }
       else{
       JOptionPane.showMessageDialog(null, "The triangle is isosceles."); 
       }
     }
     else if(side2==side3){
       if(side1==side3){
       JOptionPane.showMessageDialog(null, "The triangle is equilatoral."); 
     }
       else{
       JOptionPane.showMessageDialog(null, "The triangle is isosceles."); 
       }
     }
    
      
      else{
        JOptionPane.showMessageDialog(null, "The triangle is scalene.");
      }
   }
     
   
  System.exit (0);
  }
}
