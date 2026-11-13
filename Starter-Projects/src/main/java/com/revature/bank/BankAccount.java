package com.revature.bank;

public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;

            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;

            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(100);

        System.out.printf("Current Balance: $%.2f", account.getBalance());
        System.out.println("\n");

        System.out.println("Depositing $50");
        account.deposit(50);

        System.out.printf("Current Balance: $%.2f", account.getBalance());
        System.out.println("\n");

        System.out.println("Withdrawing $25");
        account.withdraw(25);

        System.out.printf("Current Balance: $%.2f", account.getBalance());
        System.out.println("\n");

        System.out.println("Withdrawing $200"); // Should Fail
        account.withdraw(200);

        System.out.printf("Current Balance: $%.2f", account.getBalance());
        System.out.println("\n");

        System.out.println("Depositing $-50"); // Should Fail
        account.deposit(-50);

        System.out.printf("Current Balance: $%.2f", account.getBalance());
    }
}
