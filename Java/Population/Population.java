/*
 * Population.java - Knowledge Summary
 * Author: Cody Lewis
 * Date: 29-03-2017
 * Description:
 * Program that calculates and prints the population of a country in the beggining of two years, every year the population increases by x%,
 *  if year 1 is less than year 2 then the values are swapped.
 */

import java.util.*;
import javax.swing.*;

public class Population{

	public static void main (String[] args){
		double firstYear,secondYear,change;		
		String invalid = "";		// Displays error messages

		Scanner console = new Scanner(System.in);

		do{
		firstYear = Double.parseDouble(JOptionPane.showInputDialog(invalid+"Please enter the population for the first year: "));
		invalid = "Error, you can not enter a population less than 0 \n";
		}while(firstYear < 0);

		invalid = "";

		do{
		change = Double.parseDouble(JOptionPane.showInputDialog(invalid+"Please enter the increase in population in percentage form: "));
		invalid = "Error, you can not enter a percentage less than -100% \n";
		}while(change < -100);

		change = change/100 + 1;		// Calculation for the population change quotient
		secondYear = change * firstYear;
		
		if(secondYear > firstYear)
		JOptionPane.showMessageDialog(null, "The population increased from "+firstYear+" to "+secondYear);
		else
		JOptionPane.showMessageDialog(null, "The population increased from "+secondYear+" to "+firstYear);

		System.exit(0);

	}
}
		
