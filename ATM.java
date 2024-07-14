package org.example.Task3;

import java.util.Scanner;
public class ATM {
    private BankAccount account;
    public ATM(BankAccount account) {
        this.account = account;
    }
    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw Cash");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction(int choice) {
        switch (choice) {
            case 1:
                withdrawCash();
                break;
            case 2:
                depositCash();
                break;
            case 3:
                checkBalance();
                break;
            case 4:
                System.out.println("Thank you for using the ATM!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void withdrawCash() {
        System.out.print("Enter amount to withdraw: ");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Please collect your cash.");
        } else {
            System.out.println("Insufficient funds. Please try a lower amount.");
        }
    }

    private void depositCash() {
        System.out.print("Enter amount to deposit: ");
        Scanner scanner = new Scanner(System.in);
        double amount = scanner.nextDouble();

        account.deposit(amount);
        System.out.println("Deposit successful. Your new balance is: " + account.getBalance());
    }

    private void checkBalance() {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00);
        ATM atm = new ATM(account);

        while (true) {
            atm.displayMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            atm.performTransaction(choice);

            if (choice == 4) {
                break;
            }
        }

        System.out.println("Exiting ATM. Goodbye!");
    }
}

class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    public double getBalance() {
        return balance;
    }
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
    public void deposit(double amount) {
        balance += amount;
    }
}
