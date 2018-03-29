import java.util.*;

		


public class LambdaTest{
	public static void main(String[] args){
		Integer z = (int x,int y) ->  (x*y); 
		System.out.println(z.apply(2,5));
	}

}
