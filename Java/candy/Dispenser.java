//class Dispenser

 

public class Dispenser
{
    private static int total;   //variable to show the total number of candies
    
    private int numberOfItems;   //variable to store the number of
                                 //items in the dispenser
    private int cost;    //variable to store the cost of an item


      //Constructor with parameters to set the cost and number
      //of items in the dispenser specified by the user
      //Postcondition: numberOfItems = setNoOfItems;
      //                    cost = setCost;
    public Dispenser(int setNoOfItems, int setCost)
    {
         if(setNoOfItems >= 0)
               numberOfItems = setNoOfItems;
         else
               numberOfItems = 50;

         if(setCost >= 0)
               cost = setCost;
         else
             cost = 50;
    }

      //Default constructor to set the cost and number of
      //items to the default values
      //Postcondition: numberOfItems = 50; cost = 50;
    public Dispenser()
    {
         numberOfItems = 50;
         cost = 50;
         total = 0;
    }

      //Method to show the number of items in the dispenser
      //Postcondition: The value of the instance variable
      //                numberOfItems is returned
    public int getCount()
    {
         return numberOfItems;
    }
    public static int getTotal()
    {
        return total;
    }

      //Method to show the cost of the item
      //Postcondition: The value of the instance
      //                variable cost is returned
    public int getProductCost()
    {
         return cost;
    }

      //Method to reduce the number of items by 1
      //Postcondition: numberOfItems = numberOfItems - 1;
    public void makeSale()
    {
          numberOfItems--;
          total++;
    }
}
