/**
 * ListExample.java - LinkedList
 * Author: Cody Lewis
 * Date: 21/07/2017
 * Last Modified: 21/07/2017
 * Description:
 * Program that shows the basic operations with linked lists in java
 */
import java.util.*;
public class ListExample{
	static Scanner console = new Scanner(System.in);
	static int initialCapacity = 20;	//declares the original size of the list
	/* Description:
	 * Goes through the operations with the ArrayList class 
	 * Pre-conditions:
	 * None
	 * Post-conditions:
	 * The program is terminated
	 */
	public static void main(String[] args){
		ArrayList<Double> frstList = new ArrayList<Double>(initialCapacity);
		Double temp; int cTemp;
		//Loop places the natural numbers from the closed interval [1,20] into the list
		for(int counter = 0; counter<initialCapacity; counter++){
			cTemp = counter+1;		
			temp = new Double(cTemp);
			frstList.add(temp);
		}
		printList(frstList);
		System.out.print("Another entry will now be added ");
		frstList.add(frstList.size()+1.0);	//A list automatically expands past its intial parameters
		printList(frstList);
		System.out.print("Now you add one: ");
		temp = console.nextDouble();
		frstList.add(temp);
		printList(frstList);
		System.out.print("Now you remove one: ");
		cTemp = console.nextInt()-1;
		frstList.remove(cTemp);
		printList(frstList);
	}
	/* Description:
	 * Method that prints the entries in a list of Doubles
	 * Pre-conditions:
	 * An ArrayList must be completely declared and contain data
	 * Post-conditions:
	 * None
	 */
	private static void printList(ArrayList<Double> rList){
		System.out.println("The list currently contains: ");
		for(int counter = 0; counter<rList.size(); counter++)
			System.out.print(rList.get(counter)+", ");
		System.out.println();
	}
}
