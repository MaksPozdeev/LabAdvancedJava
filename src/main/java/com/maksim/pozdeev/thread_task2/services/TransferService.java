package com.maksim.pozdeev.thread_task2.services;
import com.maksim.pozdeev.thread_task2.dto.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    public void doTransfer(Account sender, Account recipient, long transferAmount) {
        logger.info("Отправитель ID:{}, получатель ID:{}, сумма: {}",sender.getIdAccount(),recipient.getIdAccount(), transferAmount);

        if (transferAmount <= 0) {
            throw new IllegalArgumentException("Некорректная сумма перевода: <=0");
        }

        long senderBalance = sender.getBalanceAccount();
        if (senderBalance < transferAmount){
            logger.error("Недостаточно средств для перевода!");
            throw new IllegalArgumentException("У отправителья с ID:" + sender.getIdAccount() + " недостаточно средств для перевода!");
        }

        long recipientBalance = recipient.getBalanceAccount();

        sender.setBalanceAccount(senderBalance - transferAmount);
        recipient.setBalanceAccount(recipientBalance + transferAmount);

        logger.info("Перевод: успех!");
    }

}
