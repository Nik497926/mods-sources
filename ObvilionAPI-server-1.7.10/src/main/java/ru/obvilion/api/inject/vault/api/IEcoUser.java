package ru.obvilion.api.inject.vault.api;

public interface IEcoUser {
    double getMoney(double amount);

    boolean hasMoney(double amount);

    boolean withdraw(double amount);

    boolean deposit(double amount);
}
