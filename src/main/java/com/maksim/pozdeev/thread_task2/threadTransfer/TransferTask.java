package com.maksim.pozdeev.thread_task2.threadTransfer;

import com.maksim.pozdeev.thread_task2.dto.Account;
import com.maksim.pozdeev.thread_task2.exception.NotEnoughFundsToTranslateException;
import com.maksim.pozdeev.thread_task2.services.AccountsServices;
import com.maksim.pozdeev.thread_task2.services.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class TransferTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TransferTask.class);

    private static final Long MAX_TRANSFER_AMOUNT = 15000L;
    private static AtomicInteger numberCompleteTransactions = new AtomicInteger(1);
    private static AtomicInteger numberFailedTransactions = new AtomicInteger(0);

    private AccountsServices accountList;

    public TransferTask(AccountsServices accountList) {
        this.accountList = accountList;
    }

    @Override
    public void run() {
        int idTransaction = numberCompleteTransactions.getAndIncrement();
        boolean isNoMoney = false;

        Account sender = getRandomAccount();
        Account recipient = getRandomAccount();

        if (sender.getIdAccount() == recipient.getIdAccount()) {
            logger.info("TRANS#{} Невозможен перевод с одного счёта на этот-же", idTransaction);
            numberFailedTransactions.getAndIncrement();
            run();
        } else {
            if (sender.getIdAccount() < recipient.getIdAccount()) {
                sender.lockObject();
                recipient.lockObject();
            } else {
                recipient.lockObject();
                sender.lockObject();
            }
            TransferService transferService = new TransferService();
            try {
                if (transferService.doTransfer(idTransaction, sender, recipient, getRandomAmount())) {
                    logger.info("TRANS#{} Перевод: успех!", idTransaction);
                }
            } catch (NotEnoughFundsToTranslateException ex) {
                logger.info("TRANS#{} Недостаточно средств для перевода!", idTransaction);
//                int countFailedTransactions = numberFailedTransactions.getAndIncrement();
                numberFailedTransactions.getAndIncrement();
                isNoMoney = true;
            } catch (IllegalArgumentException ex) {
                logger.error("TRANS#{} TransferTask.run(). Что-то пошло не так. ", idTransaction);
                System.exit(0);
            } finally {
                recipient.unlockObject();
                sender.unlockObject();
                if(isNoMoney){
                    run();
                }
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private Account getRandomAccount() {
        return accountList.getAccountById((int) (Math.random() * accountList.getSize()) + 1);
    }

    private long getRandomAmount() {
        return ((long) (Math.random() * MAX_TRANSFER_AMOUNT) + 1);
    }

    public static AtomicInteger getNumberFailedTransactions() {
        return numberFailedTransactions;
    }
}
