package src.main.model.account;

import src.main.model.account.impl.Taxable;

public class Chequing extends Account implements Taxable{
  
  private static final  Double OVER_DRAFT_FEE  = 5.50; 
  private static final  Double OVER_DRAFT_LIMIT = -200.0 ; 
  private static final  Double taxable_imcome  = 3000.0;
  private static final  Double  tax_rate = 0.15; 


      public Chequing(String id , String Name ,double Balance ){
        super(id, Name , Balance);
      }
    
      public Chequing(Chequing source){
        super(source);
      }

      
      @Override
      public Account clone() {
        return new Chequing(this);
      }

      @Override
      public void deposit(double amount) {
        super.setBalance(super.round(super.getBalance()+amount));
        
      }

      @Override
      public boolean withdraw(double amount) {
        if(super.getBalance() -amount < OVER_DRAFT_LIMIT ){
         return false ; 
        }
       else if(super.getBalance()-amount < 0 ){
          super.setBalance(super.round(super.getBalance() - amount - OVER_DRAFT_FEE));
          
        }else{
          super.setBalance(super.round(getBalance()-amount));
       
        }
        return true ; 
        
      }

      @Override
      public void tax(double income) {
        double tax = Math.max(0, income - taxable_imcome)*tax_rate;
        super.setBalance(super.round(super.getBalance()-tax));
      }

}
