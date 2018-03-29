//Candy Machine Program

import java.util.*;

public class CandyMachine
{
    static Scanner console = new Scanner(System.in);
    static int change = 0;
              

    public static void main(String[] args) 
    {
        CashRegister  cashRegister = new CashRegister();      //Step 1
        Dispenser candy = new Dispenser(100,50);              //Step 2
        Dispenser chips = new Dispenser(100,65);              //Step 2
        Dispenser gum = new Dispenser(75,45);                 //Step 2
        Dispenser cookies = new Dispenser(100,85);            //Step 2

        int choice;  //variable to hold the selection         //Step 3

        showSelection();                                      //Step 4
        choice = console.nextInt();       //Step 5

        while(choice != 9)                                    //Step 6
        {
            switch(choice)                                    //Step 6a
             {
             case 1: sellProduct(candy, cashRegister);
                     break;
             case 2: sellProduct(chips, cashRegister);
                     break;
             case 3: sellProduct(gum, cashRegister);
                     break;
             case 4: sellProduct(cookies, cashRegister);
                     break;
             default: System.out.println("Invalid Selection");
             }//end switch

            showSelection();                                  //Step 6b
             choice = console.nextInt();            //Step 6c
        }//end while
    }//end main

    public static void showSelection()
    {
          System.out.println("*** Welcome to One Stop Candy Shop ***");
          System.out.println("You have purchased a total of "+Dispenser.getTotal()+" candies.");
          System.out.println("To select an item, enter ");
          System.out.println("1 for Candy");
          System.out.println("2 for Chips");
          System.out.println("3 for Gum");
          System.out.println("4 for Cookies");
          System.out.println("9 to exit");
    }//end showSelection

    public static void sellProduct(Dispenser product, CashRegister cRegister)

    {
        int price;            //variable to hold the product price
        int coinsInserted;  //variable to hold the amount entered
        int coinsRequired;  //variable to show the extra amount needed

        if(product.getCount() > 0)                       //Step 1
        {
            price = product.getProductCost();          //Step 1a
            coinsRequired = price;                     //Step 1b
            coinsInserted = 0;                         //Step 1c
       
            
            while(coinsRequired > 0)                  //Step 1d
            {

                System.out.println("Please deposit "
                            + coinsRequired + " cents");   //Step 1d.i

                               //Step 1d.ii
                coinsInserted = coinsInserted
                                + console.nextInt();
                if (coinsInserted>coinsRequired)
                    change = coinsInserted - price;


                coinsRequired = price - coinsInserted; //Step 1d.iii
            }
           // if(coinsRequired < 0)   change = change+Math.abs(coinsRequired);

            cRegister.acceptAmount(coinsInserted);    //Step 1e
            product.makeSale();                       //Step 1f
            
            if(change>0)
            System.out.println("Please retreive your change of "+change+" cents");
            System.out.println("Collect your item at the "
                              + "bottom and enjoy.");     //Step 1g
        }
        else
            System.out.println("Sorry this item is sold out.");
    }//end sellProduct
}


