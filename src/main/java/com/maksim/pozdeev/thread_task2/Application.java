package com.maksim.pozdeev.thread_task2;

import com.maksim.pozdeev.thread_task2.dto.Account;
import com.maksim.pozdeev.thread_task2.services.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import com.maksim.pozdeev.thread_task2.threadTransfer.TransferTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static final int NUMBER_OF_TRANSACTIONS = 100;
    private static final int NUMBER_OF_THREADS = 5;

    public static void main(String[] args) throws FileNotFoundException {

//        План:
//        +1. Генерируем данные
//        +2. Сериализуем аккаунты на диске в папке resources/accounts
//        === Данные готовы для использования
//        +3. Считываем файлы из папке (десериализуем)
//        +- при считывании узнаём общий баланс системы (сумму всех счетов)
//        4. Производим переводы
//        5. Производим проверку общего баланса системы (должен быть как и старый)
//        6. Сериализуем объекты

        InitializeData.run();

//        List<Account> accountList = AccountDeserializator.getAccounts();
        AccountsServices accountsList = new AccountsServices();
        accountsList.doPrintAll();
        System.out.println("Total balance before transactions: " + accountsList.getTotalBalance());

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        IntStream.range(0, NUMBER_OF_TRANSACTIONS)
                .forEach(x -> executorService.submit(new TransferTask(accountsList)));
        executorService.shutdown();
        System.out.println("Total balance after transactions: " + accountsList.getTotalBalance());

    }

}
