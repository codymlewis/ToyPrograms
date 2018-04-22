/* MultiplicationSeries.java - Lab 3
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 21-03-2017
 * Description:
 * Program that creates a series that multiplies two numbers per increment up to the input number
 */

import javax.swing.*;
import java.util.*;

public class MultiplicationSeries {
	public static void main (String[] args){
		Scanner console = new Scanner(System.in);
		int numberIn,series,numberOut;
		
		series = 0;
		numberOut = 0;

		numberIn = Integer.parseInt(JOptionPane.showInputDialog("Please enter your number:"));

		if((numberIn/2)*2 == numberIn){
			do{
				series = series + 1;
				numberOut = numberOut + (series * (series + 2));
			  }while(series < numberIn);
		}
		else{
			do{
				
				series = series + 1;
				numberOut = numberOut + (series * (series + 2));
			}while(series < (numberIn-1));
		}

			JOptionPane.showMessageDialog(null,"The sum of the infinite addition series of n(n+2) up to " + numberIn + " is " + numberOut);
			System.exit (0);
	}
}




