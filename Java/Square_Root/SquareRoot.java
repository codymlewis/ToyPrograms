/*SquareRoot.java
 * Author: Cody Lewis
 * Student No. 3283349
 * Date: 20-03-2017
 * Description:
 * Program to calculate square roots
 */

import java.util.*;

public class SquareRoot
{
	
public static void main (String[] args)
 {
	 Scanner keyboard = new Scanner(System.in);
	 double numberIn;
	 double squaredNumber;

	 System.out.println("This program square roots a number.");
	 System.out.print("Enter a number: ");
	 numberIn = keyboard.nextDouble();

	 if (numberIn <0){
		numberIn = numberIn/(-1);
		squaredNumber = Math.sqrt(numberIn);
		System.out.println("The square root of -" + numberIn + " is " + squaredNumber + "i");
	 }
	 else{
		squaredNumber = Math.sqrt(numberIn);
		System.out.println("The square root of " + numberIn + " is " + squaredNumber);
	 }
 }
}
