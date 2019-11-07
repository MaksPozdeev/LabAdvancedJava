package com.maksim.pozdeev.thread_task2.services;

import com.maksim.pozdeev.thread_task2.dto.Account;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountsGenerator {

    private static final int NUMBER_OF_ACCOUNTS = 15;
    private static final int NUMBER_OF_CLIENTS = 10;
    private static final Long MAXIMUM_BALANCE = 20000L;

    public static List<Account> generate() {
        return IntStream.rangeClosed(1, NUMBER_OF_ACCOUNTS)
                .mapToObj(i -> new Account(
                        i,
                        (int) (Math.random() * ((NUMBER_OF_CLIENTS - 1) + 1)) + 1,
                        (long) (Math.random() * ((MAXIMUM_BALANCE - 1) + 1)) + 1))
                .collect(Collectors.toList());
    }

}
