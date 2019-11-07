package com.maksim.pozdeev.thread_task2.threadTransfer;

import com.maksim.pozdeev.thread_task2.dto.Account;
import com.maksim.pozdeev.thread_task2.services.AccountsServices;
import com.maksim.pozdeev.thread_task2.services.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TransferTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TransferTask.class);
    private static final Long MAX_TRANSFER_AMOUNT = 15000L;

    private AccountsServices accountList;
    private TransferService transferService;

    public TransferTask(AccountsServices accountList) {
        this.accountList = accountList;
    }

    @Override
    public void run() {
        Account sender = getRandomAccount();
        Account recipient = getRandomAccount();
        if (sender.getIdAccount() == recipient.getIdAccount()) {
            logger.error("ОШИБКА: Невозможен перевод с одного счёта на этот-же");
        } else {
            sender.lockObject();
            recipient.lockObject();
            transferService = new TransferService();
            try {
                transferService.doTransfer(sender, recipient, getRandomAmount());
            }catch (IllegalArgumentException ex){
                logger.error("TransferTask.run(). Что-то пошло не так");
            }finally {
                recipient.unlockObject();
                sender.unlockObject();
            }
        }

    }

    private Account getRandomAccount() {
        return accountList.getAccountById((int) (Math.random() * accountList.getSize()) + 1);
    }

    private long getRandomAmount() {
        return ((long) (Math.random() * MAX_TRANSFER_AMOUNT) + 1);
    }
}
