package com.turkcell;

public class Account implements Transaction {
    private String accountNo;
    private String owner;
    protected double balance;
    
    // Constructor Method
    public Account(String accountNo, String owner, double balance) {
        this.accountNo = accountNo;
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(owner + " hesabına " + amount + " TL yatırıldı.");
        }
        else {
            System.out.println("Geçersiz tutar, lütfen doğru tuşlama yapınız!");
        }
    }

    public void witdraw(double amount) {
        if (balance >= amount && amount > 0) {
            balance -= amount;
            System.out.println(owner + " hesabından" + amount + " TL çekildi.");
        } 
        else if (amount <= 0) {
            System.out.println("Geçersiz tutar, lütfen doğru tuşlama yapınız!");
        }
        else {
            System.out.println("Bakiye yetersiz!");
        }
    }

    public void transfer(Account targetAccount, double amount) {
        double fee = 5.0; // Standart hesap işlem ücreti
        double totalCost = amount + fee;

        if (balance >= totalCost && amount > 0) {
            balance -= totalCost; // Kendi bakiyesinden parayı ve ücreti düş
            targetAccount.deposit(amount); // Karşı tarafa yalnızca parayı yatır
            System.out.println(owner + " => " + targetAccount.getOwner() + " | " + amount + " TL gönderildi. İşlem ücreti: " + fee + " TL." );
        }
        else {
            System.out.println("Transfer başarısız! Bakiye yetersiz.");
        }
    }

    public void showBalance() {
        System.out.println(owner + " | Güncel Bakiye: " + balance + " TL.");
    }

    // Encapsulation Method
    public String getOwner() {
        return owner;
    }

    public String getAccountNo() {
        return accountNo;
    }
}
