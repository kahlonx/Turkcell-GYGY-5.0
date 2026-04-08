package com.turkcell;

public interface Transaction {
    void deposit(double amount);
    void withdraw(double amount);
    void transfer(Account targetAccount, double amount);
    void showBalance();
}
