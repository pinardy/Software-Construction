package Homework_Qn_4;

/** Homework Question 4 */

public class SynchronizedAccount {

    private static int balance;

    public SynchronizedAccount() {
        balance = 1000;
    }

    public static void main(String[] args) throws Exception{
        SynchronizedAccount account = new SynchronizedAccount();

        Thread thread1 = new AccountThread(account, "withdraw", 200);
        Thread thread2 = new AccountThread(account, "withdraw", 300);
        Thread thread3 = new AccountThread(account, "deposit", 600);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public synchronized void deposit(double amount) {
        checkBalance();
        balance += amount;
        System.out.println(amount + " has been deposited.");
        checkBalance();
        System.out.println();
    }


    public synchronized void withdraw(double amount) {
        checkBalance();

        if (amount > balance){
            System.out.println("Amount to withdraw too large. Withdrawal failed.");
        } else {
            balance -= amount;
            System.out.println(amount + " has been withdrawn.");
            checkBalance();
        }
        System.out.println();
    }

    public void checkBalance(){
        System.out.println(" Current balance: " + this.balance);
    }
}
