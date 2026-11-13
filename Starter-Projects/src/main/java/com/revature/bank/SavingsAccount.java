package com.revature.bank;

public class SavingsAccount extends BankAccount {
    public SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount < 100) {
            return false;
        } else {
            return super.withdraw(amount);
        }
    }

    public static void main(String[] args) {
        BankAccount account = new SavingsAccount(100);

        System.out.printf("Current Balance: $%.2f", account.getBalance());
        System.out.println("\n");

        System.out.println("Withdrawing $200"); // Should Fail
        account.withdraw(200);

        System.out.printf("Current Balance: $%.2f", account.getBalance());
        System.out.println("\n");

        System.out.println("Withdrawing $50"); // Should Fail
        if (account.withdraw(50)) {
            System.out.printf("Current Balance: $%.2f", account.getBalance());
        } else {
            System.out.println("Insufficient Funds");
            System.out.printf("Current Balance: $%.2f", account.getBalance());
        }
    }
}
