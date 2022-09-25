package src.main.model.account;

public class Savings extends Account {
    private static final  Double withdraw_fee = 5.00 ;
     public Savings(String id , String Name , double Balance){
        super(id, Name, Balance);
     }

     public Savings (Savings source) {
         super(source);       
     }

     @Override
     public Account clone() {
    
         return new Savings(this);
     }
    @Override
    public void deposit(double amount){ 
        super.setBalance(super.round(super.getBalance()+amount));
        
    }

    @Override
    public boolean withdraw(double amount) {
        super.setBalance(super.round(super.getBalance() - amount - withdraw_fee));
        return true;  
    }

  

    


}
