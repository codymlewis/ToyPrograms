/* FactorialSum.java - Lab 3
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 22-03-2017
 * Last Modified: 28-03-2017
 * Description:
 * Creates an infinite sum of factorials up to the specified number
 */

import javax.swing.*;
import java.util.*;

public class FactorialSum {
	public static void main (String[] args){
		Scanner console = new Scanner(System.in);
		int numberIn,factorialCount,factorial,series,numberOut,seriesCount;	// Declares integers for the input, two for the factorial and one for the output
		factorial = 1;
		numberOut = 0;
		int seriesTrack = 1;	// Tracks whether the factorial will be added or subtracted

		numberIn = Integer.parseInt(JOptionPane.showInputDialog("Please enter your number"));

		// Creates a nested loop which manages the creation of the series involve a factorial the initial if-else statement checks whether the input number is odd or even
		if(numberIn%2 == 0){
			for (series = 1; series < numberIn+1; series+=2){
				for(factorialCount = 1; factorialCount<series+1; factorialCount++){
					factorial = factorial * factorialCount;
				}
			if (seriesTrack%2 == 0){
				numberOut = numberOut - factorial;
			}
			else{
				numberOut = numberOut + factorial;
			}
			seriesTrack++;
			factorial = 1;
		 }
		}
		else{
			for (series = 1; series < numberIn+1; series+=2){
				for(factorialCount = 1; factorialCount<series+1; factorialCount++){
					factorial = factorial * factorialCount;
				}
			if (seriesTrack%2 == 0){
				numberOut = numberOut - factorial;
			}
			else{
				numberOut = numberOut + factorial;
			}
			seriesTrack++;
			factorial = 1;
		 }
		}
		// Output the data then exits when the user is ready
		JOptionPane.showMessageDialog(null,"The sum of the series holding the equation of n! up to " + numberIn + " is " + numberOut);
		System.exit(0);
		}
	}
