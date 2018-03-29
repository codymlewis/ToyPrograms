import java.util.*;

public class BankAccountManager {

static Scanner console = new Scanner(System.in);

public static void main (String[] args) 
{
    BankAccount account = new BankAccount();
    int option;
    double amount;
    String name;
    String newName;
    String idNumber;
    String newID;
    
    System.out.println("Please create an account name");
    newName = console.next();
    changeName(account,newName);
    
    System.out.println("Please create account ID");
    newID = console.next();
    changeID(account,newID);

    do {
        System.out.println("Welcome "+account.getName());
        System.out.println("Balance(0), Deposit(1), Withdrawal(2), Change name(3), Exit (9): ");
        option = console.nextInt();
        switch(option)
        {
            case 0: checkBalance(account);
                    break;
            case 1: System.out.println("Amount: ");
                    amount = console.nextDouble();
                    makeDeposit(account,amount);
                    break;
            case 2: System.out.println("Amount: ");
                    amount = console.nextDouble();
                    makeWithdrawal(account,amount);
                    break;
            case 3: System.out.println("New name: ");
                    newName = console.next();
                    changeName(account,newName);
                    break;
            case 9: break;
            default: System.out.println("invalid option");
        }
    }
        while(option!=9);
}
   private static void changeName (BankAccount account, String newName)
   {
      account.setName(newName);
      System.out.println("The new name is "+account.getName());
   }
   private static void changeID (BankAccount account, String newID)
   {
      account.setID(newID);
      System.out.println("The new ID is "+account.getID());
   }
   private static void checkBalance(BankAccount account)
   {
      System.out.println("balance = "+account.getBalance());
   }
   private static void makeDeposit(BankAccount account, double amount)
   {
   
     String result = account.deposit(amount);
     System.out.println(result);
   }

   private static void makeWithdrawal(BankAccount account, double amount)
   {
      String result = account.withdraw(amount);
      System.out.println(result);
   }
   
}
