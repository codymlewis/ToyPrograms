import java.util.*;
public class recursiveMass{
	static Scanner console = new Scanner(System.in);
	public static void main(String[] args){
		recursiveMass rMass = new recursiveMass();
			rMass.run();

	}
	private void run(){
		System.out.print("Input yo' Mama's weight: ");
		double input = console.nextInt();
		try{
			calculateMass(input);
		}catch(StackOverflowError e){
			System.err.println("Yo' Mama's so fat the recursive function computing her mass just caused a stack overflow");
		}
		
	}
	private double calculateMass(double input){
			input = calculateMass(input)/(9.81*10E-300);
			return input;
	}
}
