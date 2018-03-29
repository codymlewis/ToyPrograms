/* DivisionSquareSeries.java - Lab 3
 * Author - Cody Lewis
 * Student No. 3283349
 * Date: 21-03-2017
 * Description
 * Program that create an addition series
 */

import javax.swing.*;
import java.util.*;

public class DivisionSquareSeries {
	public static void main (String[] args)
	{
		Scanner console = new Scanner(System.in);
		double numberIn,series,numberOut;

		numberOut = 0;
		series = 1;

		numberIn = Integer.parseInt(JOptionPane.showInputDialog("Please enter your number: "));
		
		do{
			series = series + 1;
			numberOut = numberOut + (((series-1)/(series))*((series-1)/(series)));
		}while(series<numberIn);

		JOptionPane.showMessageDialog(null,"The infinite sum incrementing by 1 where a fraction is squared, it goes up to the number " + numberIn + " and the sum is " + numberOut);
		System.exit(0);
	}
}
