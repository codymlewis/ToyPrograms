// Bank account class

public class BankAccount 
{
     private String name, idNumber;
     private double balance;

    //Hold the various methods for operations from the bank account
    // default constructor

   public String getName() 
   {
       return name;
    }
   public String getID()
   {
       return idNumber;
    }
   public void setName(String newName) 
   {
       name = newName;
    }
   public void setID(String newID) 
   {
       idNumber = newID;
    }
   public BankAccount()
   {
      name = "John";
      idNumber = "0000";
      balance = 0;
   }
   
  //Retrieves the calculated value of the balance
   public double getBalance()
   {
      return balance;
   }
    //Adds the input amount to the balance
    public String deposit(double amount) 
    {
        if (amount < 0) 
        {
            return "Deposit amount can�t be negative";
        } 
        else 
        {
            balance = balance + amount;
            return "balance = "+balance;
        }
    }
    //Removes the input amount from the balance
    public String withdraw(double amount) 
    {
        if (amount < 0) 
        {
           return "Withdrawal amount can�t be negative";
        } 
        else 
           if (amount > balance) 
           {
               return "Cannot withdraw more than the balance";
           } 
           else 
           {
               balance = balance - amount;
               return "balance = "+balance;
           }
     }
}
