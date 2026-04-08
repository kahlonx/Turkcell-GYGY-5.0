package com.turkcell;

public class SalaryAccount extends Account{

    public SalaryAccount(String accountNo, String owner, double balance) {
        super(accountNo, owner, balance);
    }

    public void transfer(Account targeAccount, double amount) {
        if(balance >= amount && amount > 0) {
            balance -= amount; // Ücret kesintisi yok, sadece tutarı düş
            targeAccount.deposit(amount);
            System.out.println("[VIP] " + getOwner() + " => " + targeAccount.getOwner() + " | " + amount + " TL işlem ücreti alınmadan gönderildi.");
        }
        else {
            System.out.println("VIP Transfer başarısız! Bakiye yetersiz veya geçersiz tutar.");
        }
    }

}
