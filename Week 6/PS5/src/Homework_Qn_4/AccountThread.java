package Homework_Qn_4;

/** Homework Question 4 */

public class AccountThread extends Thread{

    SynchronizedAccount account;
    String command;
    int amount;


    public AccountThread(SynchronizedAccount account, String command, int amount){
        this.account = account;
        this.command = command;
        this.amount = amount;
    }


    @Override
    public void run(){
        if (amount < 0){
            System.out.println("Amount cannot be negative!");
        } else {
            if (command.equals("deposit")) {
                account.deposit(amount);
            } else if (command.equals("withdraw")) {
                account.withdraw(amount);
            } else {
                System.out.println("Invalid command");
            }
        }
    }

}
