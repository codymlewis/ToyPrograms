/**
Displays a two-dimensional table showing how
interest rates affect bank balances.
*/
public class InterestTable
{
    static int rowTotal,columnTotal,fullTotal;
    public static void main (String [] args)
    {
        int [][] table = new int [11] [7];      
                     
        for (int row = 0 ; row < 10 ; row++)                          
            for (int column = 0 ; column < 6 ; column++)            
                table [row] [column] = getBalance (1000.00, row + 1, (5 + 0.5 * column));
	 
	rowTotal = 0;
	columnTotal = 0;
	fullTotal = 0;

	for(int row = 0; row<10; row++){
		for(int column = 0; column<6; column++){
			rowTotal+=table[row][column];
		}
		table[row][6] = rowTotal/10;
		fullTotal+=table[row][6];
		rowTotal = 0;
	}
	for(int column = 0; column<6; column++){
		for(int row = 0; row<10; row++){
			columnTotal+=table[row][column];
		}
		table[10][column] = columnTotal/6;
		fullTotal+=table[10][column];
		columnTotal = 0;
	}
	table[10][6] = fullTotal/16;
 
        System.out.println ("Balances for Various Interest Rates " +"Compounded Annually");
        System.out.println ("(Rounded to Whole Dollar Amounts)");
        System.out.println ();
        System.out.println ("Years 5.00% 5.50% 6.00% 6.50% 7.00% 7.50% Total");
 
        for (int row = 0 ; row < 11 ; row++)                        
        {     
	    if(row<9)		
            	System.out.print ((row + 1) + "     ");
	    else if(row<10)
		   System.out.print((row+1) + "    "); 
	    else
		System.out.print("Total ");	    
            for (int column = 0 ; column < 7 ; column++)            
                System.out.print ("$" + table [row] [column] + " ");
            System.out.println ();                                  
        }                                                           
    }
 
 
    /**
    Returns the balance in an account after a given number of years
    and interest rate with an initial balance of startBalance
    Interest is compounded annually The balance is rounded
    to a whole number.
    */
    public static int getBalance (double startBalance, int years, double rate)
    {
        double runningBalance = startBalance;
        for (int count = 1 ; count <= years ; count++)
            runningBalance = runningBalance * (1 + rate / 100);
        return (int) (Math.round (runningBalance));
    }
}
