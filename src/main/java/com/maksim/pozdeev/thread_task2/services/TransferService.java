package com.maksim.pozdeev.thread_task2.services;

import com.maksim.pozdeev.thread_task2.dto.Account;
import com.maksim.pozdeev.thread_task2.exception.NotEnoughFundsToTranslateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferService {
    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    public boolean doTransfer(int idOperation, Account sender, Account recipient, long transferAmount) {
        boolean flag = false;
        logger.info("TRANS#{} SND_ID:{}, REC_ID:{}, AMM: {}", idOperation, sender.getIdAccount(), recipient.getIdAccount(), transferAmount);

        if (transferAmount <= 0) {
            throw new IllegalArgumentException("Некорректная сумма перевода: <=0");
        } else {
            long senderBalance = sender.getBalanceAccount();
            try {
                if (senderBalance < transferAmount) {
                    throw new NotEnoughFundsToTranslateException("Недостаточно средств для перевода!");
                } else {
                    long recipientBalance = recipient.getBalanceAccount();
                    sender.setBalanceAccount(senderBalance - transferAmount);
                    recipient.setBalanceAccount(recipientBalance + transferAmount);
                    flag = true;
                }
            } catch (NotEnoughFundsToTranslateException ex) {
                logger.info("TRANS#{} Недостаточно средств для перевода!", idOperation);
            }
        }
        return flag;
    }

}
