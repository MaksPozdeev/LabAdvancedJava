package com.maksim.pozdeev.thread_task2.services;

import com.maksim.pozdeev.thread_task2.threadTransfer.TransferTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TransactionLauncher {
    private static final Logger logger = LoggerFactory.getLogger(TransactionLauncher.class);

    private static final int NUMBER_OF_TRANSACTIONS = 1000;
    private static final int NUMBER_OF_THREADS = 20;

    public static void run() throws FileNotFoundException {
        AccountsServices accountsList = new AccountsServices();
        long totalBefore = accountsList.getTotalBalance();
        logger.info("Общий баланс аккаунтов до транзакций: {}", totalBefore);

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        IntStream.range(0, NUMBER_OF_TRANSACTIONS)
                .forEach(x -> executorService.submit(new TransferTask(accountsList)));
        executorService.shutdown();

        try {
            executorService.awaitTermination(30, TimeUnit.SECONDS);
            if (executorService.isShutdown()) {
                logger.info("Общий баланс аккаунтов до:{} и после транзакций: {}", totalBefore, accountsList.getTotalBalance());

                System.out.println("Общий баланс аккаунтов после транзакций: " + accountsList.getTotalBalance());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Произошла ошибка во время ожидания executorService");
        }
    }

}
