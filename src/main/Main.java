package src.main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import src.main.model.Bank;
import src.main.model.Transaction;
import src.main.model.account.Account;


public class Main {

   static String ACCOUNTS_FILE = "src/main/data/accounts.txt";            
   static String TRANSACTIONS_FILE = "src/main/data/transactions.txt";
   static Bank bank = new Bank() ; 

    
    public static void main(String[] args) throws FileNotFoundException {

      try {
          ArrayList<Account> accounts = returnAccounts();
          loadAccounts(accounts);

          ArrayList<Transaction> transactions = returnTransaction();
          runTransactions(transactions);
          bank.deductTaxes();
          for (Account account : accounts) {
              System.out.println("\n\t\t\t\t\t ACCOUNT\n\n\t"+account+"\n\n");
              transactionhistory(account.getId());
          }
          
       } catch (Exception e) {
          System.out.println(e.getMessage());
      }
  }


    public static Account createAccount(String[] values) throws Exception{
        try{
            return (Account)Class.forName("src.main.model.account." + values[0])
            .getConstructor(String.class, String.class, double.class)
            .newInstance(values[1], values[2], Double.parseDouble(values[3]));
        }catch(Exception e){
          System.err.println(e.getMessage());
          return null;
        }
        
    }

   
     public static void wait(int milliseconds) {
         try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
         } catch (InterruptedException e) {
             System.out.println(e.getMessage());
         }
     }

     public static void loadAccounts(ArrayList<Account> accounts){
        for(Account account : accounts){
            bank.addAcoount(account);
        }
     }

     public static ArrayList<Account> returnAccounts() throws Exception{
        FileInputStream fis = new FileInputStream(ACCOUNTS_FILE);
        Scanner sc =new Scanner(fis);
        ArrayList<Account> accounts = new ArrayList<Account>();
        while(sc.hasNextLine()){
            accounts.add(createAccount(sc.nextLine().split(",")));  
        }

        sc.close();
        return accounts ;
     }

     public static ArrayList<Transaction> returnTransaction() throws FileNotFoundException{
      FileInputStream fis = new FileInputStream(TRANSACTIONS_FILE);
      Scanner sc = new Scanner(fis);
      ArrayList<Transaction> transactions = new ArrayList<Transaction>();
      while(sc.hasNextLine()){
        String[] values = sc.nextLine().split(",");
        transactions.add(new Transaction(Transaction.Type.valueOf(values[1]), Long.parseLong(values[0] ),values[2] , Double.parseDouble(values[3] )));
        
      }
      sc.close();
      Collections.sort(transactions);
      return transactions ; 

     }

     public static void runTransactions(ArrayList<Transaction> transactions){
      for(Transaction transaction : transactions){
        bank.executeTransaction(transaction);
      }
     }


    
     public static void transactionhistory (String id){

      System.out.println(" \t\t\t\t   TRANSACTION HISTORY\n\t");
      for(Transaction transaction : bank.getTransactions(id)){
          wait(300);
          System.out.println("\t"+transaction+"\n");
      }

      System.out.println(" \n\t\t\t\t\tAFTER TAX\n");
      System.out.println( "\t" + bank.getAccount(id) +"\n\n\n\n");
     }
}
