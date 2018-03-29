/**
 * Modified by: Cody Lewis
 * Student No: 3283349
 * Date Modified: 27-04-2017
 * Description:
 * Does various operations with arrays
 */

import java.util.*;

public class ArrayExample
{
    static Scanner console = new Scanner(System.in);
//    static final int ARRAY_SIZE=10;
//    static int arraySize;
    static int arraySizeA,arraySizeB,fNumber,sNumber;

    public static void main(String[] args) 
    {
        ArrayExample example = new ArrayExample();
        example.run();
    }
    
    public void run ()
    {

//	System.out.print("Enter the length you want the arrays to be: ");
//	arraySize = console.nextInt();

	System.out.print("Enter the length you want the first array to be: ");
	arraySizeA = console.nextInt();

	System.out.print("Enter the length you want the second array to be: ");
	arraySizeB = console.nextInt();

//	if(arraySizeB < arraySizeA){
//		do{
//			System.out.println("Error the length you entered for the second array is less than the first array");
//			System.out.print("Enter the length you want the second array to be: ");
//			arraySizeB = console.nextInt();
//		}while(arraySizeB < arraySizeA);
//	}

	
//        int[] listA = new int[arraySize];
//        int[] listB = new int[arraySize];
	int[] listA = new int[arraySizeA];
	int[] listB = new int[arraySizeB];	/**Without the prior loop nested in the if statement,
						   the code will cause an error if the second array is smaller than the first,
						   which is fixed by placing the user in a loop until the input is 
						   greater than or equal to the first input*/
	int[] listC = new int[arraySizeA];	//Used for inverting the elements of list A

   
        System.out.print("listA elements: ");
        printArray(listA, listA.length);
        System.out.println();

        System.out.print("Enter " + listA.length + " integers: ");
        fillArray(listA, listA.length);
        System.out.println();

        System.out.println("After filling "+ "listA, the elements are:" + "\n");
        printArray(listA, listA.length);
        System.out.println("\n");

        System.out.println("Sum of the "+"elements of listA is: "+sumArray(listA, listA.length)+"\n");

        System.out.println("Location of "+"the largest element in "+"listA is: "+(indexLargestElement(listA,listA.length)+1)+"\n");
        System.out.println("Largest element in "+"listA is: "+listA[indexLargestElement(listA, listA.length)]+"\n");

        System.out.println("Location of "+"the smallest element in "+"listA is: "+(findLow(listA,listA.length)+1)+"\n");
        System.out.println("Smallest element in "+"listA is: "+listA[findLow(listA, listA.length)]+"\n");

        copyArray(listA, listB, listB.length);
        System.out.print("Line 16: After copying the "+"elements of listA into listB\n"+"listB elements are: ");
        printArray(listB, listB.length);                    
        System.out.println();

        invertArray(listA, listC, listA.length);
        System.out.print("\nAfter inverting the "+"elements of listA into listC\n"+"listC elements are: ");
        printArray(listC, listC.length);                    
        System.out.println();  

	System.out.println("Choose a number from listA");
	fNumber = console.nextInt();
	System.out.println("Choose another number from listA");
	sNumber = console.nextInt();
	System.out.print("The quantity of numbers between the numbers "+fNumber+" and "+sNumber+" from listA is " + deltaAtoB(listA, listA.length, fNumber, sNumber) + "\n");
	System.out.println("\nThere are "+findSimilarity(listA,listB,listA.length,listB.length)+" numbers that are the same in listA and listB");
	System.out.println("\nThe Euclidean distance between the arrays listA and listC is  "+eucDist(listA,listC,listA.length,listC.length));
    }
    
     //Method to input data and store in an array
     public void fillArray(int[] list, int noOfElements) 
    {
         int index;

         for(index = 0; index < noOfElements; index++)
         {
            list[index] = console.nextInt();
         }
    }

    //Method to print the array
    public void printArray(int[] list, int noOfElements)
    {
        int index;

        for(index = 0; index < noOfElements; index++)
            System.out.print(list[index] + " ");
    }
    //Method to find and return the sum of an array
    public int sumArray(int[] list, int noOfElements)
    {
        int index;
        int sum = 0;

        for(index = 0; index < noOfElements; index++)
            sum = sum + list[index];

        return sum;
    }

    //Method to find and return the index of the
    //largest element of an array
    public int indexLargestElement(int[] list, int noOfElements)
    {
        int index;
        int maxIndex = 0; //Assume first element is the largest

        for(index = 1; index < noOfElements; index++)
            if(list[maxIndex] < list[index])
                 maxIndex = index;

        return maxIndex;
    }
    
    //Method to find the lowest number in an array
    public int findLow(int[] list, int noOfElements)
    {
	    int index;
	    int LowestNo;
	    int indexNo;
	    LowestNo = 0;

	    for (index=0; index<noOfElements; index++)
		    if(list[index] < list[LowestNo]) LowestNo = index;
	    return LowestNo;
    }
    public int findSimilarity(int[] list1,int[] list2, int noOfElements1,int noOfElements2)
    {
	    int index1,index2,simCount;
	    simCount=0;

	    for(index1=0; index1<noOfElements1; index1++)
		    for(index2=0; index2<noOfElements2; index2++)
		    	if(list1[index1]==list2[index2]) simCount++;
	    return simCount;
    }

    //Method to copy one array into another array
    public void copyArray(int[] list1, int[] list2,int noOfElements)
    {
        int index;

          for(index = 0; index < noOfElements; index++)
            list2[index] = list1[index];
    }
    //Method to copy one array into another array
    public void invertArray(int[] list1, int[] list2, int noOfElements)
    {
        int index;

          for(index = 0; index < noOfElements; index++)
            list2[index] = list1[noOfElements - index - 1];
    }
    public int deltaAtoB(int[] list, int noOfElements, int A, int B)
    {
	    int index;
	    int indexA=0;
	    int indexB=0;
	    int delta;

	    for(index = 0; index < noOfElements; index++)
		    if(list[index] == A) indexA = index;
	    
	    for(index = 0; index < noOfElements; index++)
		    if(list[index] == B) indexB = index;

	    delta = Math.abs(indexB - indexA) - 1;

	    return delta;
    }

    public double eucDist(int[] list1,int[] list2,int noOfElements1,int noOfElements2)
    {
	    int index,noOfElements;
	    double distSquare,distStream,dist;
	    if(noOfElements1<noOfElements2) noOfElements = noOfElements1;
	    else noOfElements = noOfElements2;

	    distSquare = 0;
	    distStream = 0;

	    for(index=0; index<noOfElements; index++){
		    distSquare = list1[index]-list2[index];
		    distSquare*=distSquare;
		    distStream+=distSquare;
	    }
	    dist = Math.sqrt(distStream);

	    return dist;
    }
}
