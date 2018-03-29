import java.util.*;

public class AgencyInterface 
{
    public static void main (String[] args) 
    {
       Scanner console = new Scanner(System.in);
       Couple c1 = new Couple();// null
       Couple c2 = new Couple();
       int      herAge,hisAge,end;
       String   herName,hisName;
       
       do {
           do{
           System.out.println("Couple 1");
           System.out.print("her name: "); 
           c1.addData(2,hisName,hisAge);
           System.out.println("********************");
           System.out.println(c1.test());           
           System.out.println("********************");
        }while(        
           System.out.println("Couple 2");
           System.out.print("her name: "); 
           herName = console.next();
           System.out.print("her age: "); 
           herAge = console.nextInt();
           System.out.print("his name: "); 
           hisName = console.next();
           System.out.print("his age: "); 
           hisAge = console.nextInt();
           c2.addData(1,herName,herAge);
           c2.addData(2,hisName,hisAge);
           System.out.println("********************");
           System.out.println(c2.test());           
           System.out.println("********************");
           System.out.print("Quit? (0)yes (1)no: "); 
           end = console.nextInt();
           }
       while (end!=0);
       }
}