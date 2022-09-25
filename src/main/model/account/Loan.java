package src.main.model.account;

public class Loan extends Account{
    private static final  Double INTREST = 0.02;
    private static final Double Max_debt  = 10000.0;

   public Loan(String id , String Name , double Balance){
    super(id, Name, Balance);
   } 

   public Loan(Loan source){
    super(source);
   }

   @Override
public Account clone() {
    return new Loan(this);
} 


@Override
public void deposit(double amount) {
    super.setBalance(super.round(super.getBalance() -amount ));
 
    
}

@Override
public boolean withdraw(double amount) {
    if(super.getBalance()+amount > Max_debt){
        return false ; 
    }
    super.setBalance(super.round(super.getBalance() + amount+(amount*INTREST)));
    return false;    
}


}
