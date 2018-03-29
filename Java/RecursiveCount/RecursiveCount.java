/* RecursiveCount.java - Knowledge Summary
 * Author: Cody Lewis
 * Date: 01/06/2017
 * Description:
 * Program that uses recursion to print the numbers contained in a closed interval.
 */
import java.util.*;
public class RecursiveCount{
	public static void main(String[] args){
		try{
			System.out.println("This program prints the numbers in the closed interval you specify");
			Scanner console = new Scanner(System.in);
			System.out.print("Input your starting number: ");
			int start = console.nextInt(); 
			System.out.print("Input your ending number: ");
			int end = console.nextInt();
			if(start>end){
				int temp = start;
				start = end; end = temp;
				System.out.println("Your first number was bigger than your second number so they were swapped");
			}
			System.out.println("The closed interval from "+start+" to "+end+" is:");
			recursCount(start,end);
		}catch(Exception e){
			System.out.println("Sorry, something went wrong.\nEnding program");
		}
	}
	public static void recursCount(int start,int end){
		if(start!=end)
			System.out.print(start+", ");
		else
			System.out.println(start);
		if(start==end) return;
		else	       recursCount(start+1,end);
	}
}
