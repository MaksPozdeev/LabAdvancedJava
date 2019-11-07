package com.maksim.pozdeev.thread_task2.services;

import com.maksim.pozdeev.thread_task2.dto.Account;

import java.util.List;

public class InitializeData {

    public static void run(){
        List<Account> accounts = AccountsGenerator.generate();
        for  (Account a : accounts){
            AccountSerializator.run(a);
        }
    }

}
