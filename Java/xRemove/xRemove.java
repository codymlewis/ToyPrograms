/**
 * xRemove.java - Exam Revision
 * Author: Cody Lewis
 * Last Modified: 09-04-2017
 * Description:
 * Finds if one of the first letters is an x and removes it
 */
import java.util.*;
public class xRemove{
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		String wordIn,wordProcess,wordOut;
		int wLength,locate,locate2;
		System.out.print("Please input a string: ");
		wordIn = console.next();
		wLength = wordIn.length();
		wordProcess = wordIn.substring(0,2);
		locate = wordProcess.indexOf(120);
		if(locate == 0 ) wordProcess = wordProcess.substring(1,2);
		else if(locate == 1) wordProcess = wordProcess.substring(0,1);

		if(wordProcess.length() == 1){
			locate = wordProcess.indexOf(120);
			if(locate == 0) wordProcess = "";
		}

		wordOut = wordProcess + wordIn.substring(2,wLength);
		System.out.println("The new word is "+wordOut);
	}
}
