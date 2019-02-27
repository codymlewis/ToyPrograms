/* AddTwoSeries.java - Lab 3
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 20-03-2017
 * Description:
 * Program that creates an addition series up to the input number
 */

import javax.swing.*;
import java.util.*;

public class AddTwoSeries {
	public static void main (String[] args){
		Scanner console = new Scanner(System.in);
		int numberIn,series,numberOut;

		series = -2;
		numberOut = 0;
		numberIn = Integer.parseInt(JOptionPane.showInputDialog("Please enter your number: "));

		if((numberIn/2)*2 == numberIn){
			do{

				series = series + 2;
				numberOut = numberOut + series;
			}while(series < numberIn);
		}
		else{
			do{
				series = series + 2;
				numberOut = numberOut + series;
			}while(series < (numberIn - 1));
		}
		JOptionPane.showMessageDialog(null,"The infinite sum incrementing by 2 up to " + numberIn + " is " + numberOut);
		System.exit (0);
	}
}


