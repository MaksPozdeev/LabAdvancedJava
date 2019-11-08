package com.maksim.pozdeev.thread_task2.services;

import com.maksim.pozdeev.thread_task2.dto.Account;

import java.io.FileNotFoundException;
import java.util.List;

public class AccountsServices {

    private List<Account> accountList;

    public AccountsServices() throws FileNotFoundException {
        this.accountList = AccountDeserializator.getAccounts();
    }

    public long getTotalBalance() {
        return accountList.stream()
                .mapToLong(Account::getBalanceAccount)
                .sum();
    }

    public void doPrintAll() {
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    public Account getAccountById(int id) {

        if (id > 0 && id <= accountList.size()) {
            for (Account account : accountList) {
                if (account.getIdAccount() == id){
                    return account;
                }
            }
        }else {
            throw new IllegalArgumentException("Введён некорректный id");
        }
        return null;
    }

    public int getSize(){
        return accountList.size();
    }
}
