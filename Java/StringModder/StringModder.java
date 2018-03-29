/**
 * StringModder.java - Exam Revision
 * Author: Cody Lewis
 * Last modified: 09-04-2017
 * Description:
 * Gets a string with an odd number of characters and makes a string out of the 3 middle characters
 */
import java.util.*;
public class StringModder{
	public static void main (String[] args){
		String wordIn,wordOut;
		int wLength,start,end;
		Scanner console = new Scanner(System.in);

		System.out.print("Please input a word with an odd number of letters: ");
		wordIn = console.next();
		wLength = wordIn.length();

		if(wLength%2 == 0){
			do{
				System.out.println("Error, word input had an even amount of letters");
				System.out.print("Please input a word with an odd number of letters: ");
				wordIn = console.next();
				wLength = wordIn.length();
			}while(wLength%2 == 0);
		}

		start = (wLength/2)-1; end = start+3;
		wordOut = wordIn.substring(start,end);
		System.out.println("Your new word is "+wordOut);
		
	}
}
