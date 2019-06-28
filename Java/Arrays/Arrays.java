/**
 * Cody Lewis
 * 30-04-2017
 * Program to show various operations with arrays
 */
import java.util.*;

public class Arrays{

	static Scanner console = new Scanner(System.in);
	private static int input;
	private static int[] array1 = new int[1];
	private static int[] array2 = new int[1];


	public static void main(String[] args){
		Arrays example = new Arrays();
		example.run();
	}
	public void run(){
		do{
			System.out.println("Add information to array(0), Resize array(1), Delete array(2), Exit(9)");
			input = console.nextInt();
			switch(input){
				case 0: fillArray(array1,array1.length); printArray(array1,array1.length); break;
				case 1: resizeArray(array1,array2,array1.length); printArray(array2,array2.length); break;
				case 9: System.out.println("Exiting program"); break;
				}
		}while(9!=input);
	}
	public void printArray(int[] list, int noOfElements){
		int index;

		for(index = 0; index < noOfElements; index++)
			System.out.print(list[index] + " ");
		System.out.println();

	}
	public void fillArray(int[] list,int noOfElements){
		int index;
		System.out.print("input "+noOfElements+" values for the array: ");
		for(index=0; index<noOfElements; index++){
			list[index] = console.nextInt();
		}
	}
	public void resizeArray(int[] list1, int[] list2, int noOfElements){
		int index;
		int length;
		System.out.print("Input new length: "); length = console.nextInt();
		list2 = new int[length];
		for(index=0; index<noOfElements; index++)
			list2[index] = list1[index];
	}
}
