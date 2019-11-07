package com.maksim.pozdeev.thread_task2.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Account.class);

    private int idAccount;
    private int idClient;
    private Long balanceAccount;

    private Lock lock = new ReentrantLock();

    public Account(int idAccount, int idClient, Long balanceAccount) {
        this.idAccount = idAccount;
        this.idClient = idClient;
        this.balanceAccount = balanceAccount;
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
