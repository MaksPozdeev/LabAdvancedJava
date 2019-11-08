package com.maksim.pozdeev.thread_task2.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Serializable {

    private int idAccount;
    private int idClient;
    private Long balanceAccount;
    private Lock lock;

    public Account(int idAccount, int idClient, Long balanceAccount) {
        this.idAccount = idAccount;
        this.idClient = idClient;
        this.balanceAccount = balanceAccount;
        this.lock = new ReentrantLock();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return idAccount == account.idAccount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAccount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", idClient=" + idClient +
                ", balanceAccount=" + balanceAccount +
                '}';
    }

    public int getIdAccount() {
        return idAccount;
    }

    public int getIdClient() {
        return idClient;
    }

    public Long getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(Long balanceAccount) {
        this.balanceAccount = balanceAccount;
    }

    public void lockObject() {
        lock.lock();
    }

    public void unlockObject() {
        lock.unlock();
    }
}
