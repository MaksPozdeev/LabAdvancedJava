package com.maksim.pozdeev.thread_task2.threadTransfer;

import com.maksim.pozdeev.calculator.exceptions.NotCorrectExpressionException;
import com.maksim.pozdeev.thread_task2.dto.Account;
import com.maksim.pozdeev.thread_task2.services.AccountsServices;
import com.maksim.pozdeev.thread_task2.services.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TransferTask.class);

    private static final Long MAX_TRANSFER_AMOUNT = 15000L;

    private AccountsServices accountList;

    public TransferTask(AccountsServices accountList) {
        this.accountList = accountList;
    }

    @Override
    public void run() {
        Account sender = getRandomAccount();
        Account recipient = getRandomAccount();

        if (sender.getIdAccount() == recipient.getIdAccount()) {
            logger.info("Невозможен перевод с одного счёта на этот-же");
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
                if (transferService.doTransfer(sender, recipient, getRandomAmount())) {
                    logger.info("Перевод: успех!");
                }
            } catch (IllegalArgumentException ex) {
                logger.error("TransferTask.run(). Что-то пошло не так. ");
                System.exit(0);
            } finally {
                recipient.unlockObject();
                sender.unlockObject();
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
}
