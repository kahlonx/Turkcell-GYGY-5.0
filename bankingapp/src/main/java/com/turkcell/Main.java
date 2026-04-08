package com.turkcell;

public class Main {
    public static void main(String[] args) {
        // Hesapları oluşturuyoruz
        Account standartHesap = new Account("111", "Deniz", 500.0);
        SalaryAccount salaryHesap = new SalaryAccount("999", "Halil", 500.0);

        System.out.println("\n---- Başlangıç Bakiyeleri ----");
        standartHesap.showBalance();
        salaryHesap.showBalance();
        System.out.println("\n*---------------------------------*\n");

        System.out.println("Senaryo 1: Standart hesap para transferi yapıyor ( İşlem ücreti 5 TL.).");
        standartHesap.transfer(salaryHesap, 100.0);
        standartHesap.showBalance();
        salaryHesap.showBalance();

        System.out.println("\nSenaryo 2: Maaş hesabı para transferi yapıyor ( İşlem ücreti yok.).");
        salaryHesap.transfer(standartHesap, 150.0);
        standartHesap.showBalance();
        salaryHesap.showBalance();
      
        System.out.println("\nSenaryo 3: Para çekme ve para yatırma işlemleri.");
        standartHesap.withdraw(50.0);
        salaryHesap.deposit(200.0);
        standartHesap.showBalance();
        salaryHesap.showBalance();
    }
}