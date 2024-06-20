public class Account {
    private double balance=0;
    private double interest= 0.02;
    private int accountNumber;
    private static int numberofaccounts= 1000000;

    Account(){
        accountNumber = numberofaccounts++;
    }


    public double getBalance() {
        return balance;
    }

    public double getInterest() {
        return interest * 100;
    }

    public int getAccountNumber() {
        return accountNumber;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
    public void withdraw(double amount){
        if(amount + 5> balance){
            System.out.println("Insufficient Funds");
            return;
        }
        balance -= amount +5;
        checkInterest(0);
        System.out.println("Withdrawn"+ amount + "with a fee of 5 dollar");
        System.out.println("Balance"+ balance);

    }
    public void deposit(double amount){
        if(amount<=0){
            System.out.println("You have no money to deposit");
            return;
        }
        checkInterest(amount);
        amount = amount + amount * interest;
        balance += amount;
        System.out.println("Deposit" + amount + "with a plus of 5 dollar"+(interest*100)+"%");
        System.out.println("Balance"+ balance);
    }
    public void checkInterest(double amount){
        if(balance+ amount >10000){
            interest = 0.05;
        }
        else{
            interest = 0.02;
        }
    }
}
