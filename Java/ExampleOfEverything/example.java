import java.util.*;
public class example extends suExample{
	static Scanner console = new Scanner(System.in);
	public static void main(String[] args){
		example rnTime = new example();
		try{
			rnTime.run();
		} catch(Exception e){
			System.err.println("Something went wrong exiting program");
		}
	}
	public void run(){
		System.out.print("Starting program \nHere is the factorial of 5: "+factorial(5));
		System.out.print("\nNow try your own factorial: "); int input = console.nextInt();
		System.out.println("Here is the factorial of "+input+": "+factorial(input));
		int[][][] plane = new int [10][10][10];
		for(int z=0; z<10; z++)
			for(int y=0; y<10; y++)
				for(int x = 0; x<10; x++)
					plane[x][y][z]=x;
		System.out.println("The distance between (1,1,1) and (5,5,5) is: "+Math.sqrt((plane[5][5][5]-plane[1][1][1])*(plane[5][5][5]-plane[1][1][1])));
		System.out.println("End of program");
		System.out.println(super.inher());
		System.out.print("Now input a word for a rhyme: "); String inStr = console.next();
		System.out.println(super.inherRhyme(inStr));
	}
	private int factorial(int i){
		if(i>0){
			switch(i){
				case 1: return 1;
				default: return i*factorial(i-1);
			}
		} else if(i==0){
			return 1;
		} else{
			System.out.print("A negative a number was input, there is no defined way of finding that factorial ");
			return 0;
		}

	}
}
