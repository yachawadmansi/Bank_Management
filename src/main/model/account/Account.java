package src.main.model.account ;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public abstract class Account{
    private String id ;
    private String Name ; 
    private double Balance ;


    public Account(String id, String Name, double Balance) {
        if(id==null || id.isBlank()|| Name == null|| Name.isBlank()){
             throw new IllegalArgumentException("Invalid parameters");
        }
        this.id = id;
        this.Name = Name;
        this.Balance = Balance;
    }
      public Account(Account source) {
        this.id = source.id;
        this.Name = source.Name;
        this.Balance = source.Balance;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        if(id == null || id.isBlank()){
        throw new IllegalArgumentException("Invalid id") ; 
        }
        this.id = id;
    }

    public String getName() {
        
        return this.Name;
    }

    public void setName(String Name) {
        if(Name == null || Name.isBlank()){
            throw new IllegalArgumentException("Invalid name");
        }
        this.Name = Name;
    }

    public double getBalance() {
        return this.Balance;
    }

    public void setBalance(double Balance) {
        this.Balance = Balance;
    }


    public abstract void deposit(double amount) ; 
    public abstract boolean withdraw(double amount);
    public abstract Account clone() ; 

    protected double round(double amount)  {
        DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
        return Double.parseDouble(formatter.format(amount));
    }

    @Override
    public String toString() {
     
        return (this.getClass().getSimpleName())+" "+
        "\t"+this.getId()+" "+
        "\t"+this.getName()+" "+
        "\t"+this.getBalance()+" ";

    }
    

    
}