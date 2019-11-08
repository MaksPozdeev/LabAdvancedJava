package com.maksim.pozdeev.thread_task2;

import com.maksim.pozdeev.thread_task2.services.InitializeData;
import com.maksim.pozdeev.thread_task2.services.TransactionLauncher;
import com.maksim.pozdeev.thread_task2.threadTransfer.TransferTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws FileNotFoundException {
        logger.info(" -=== Start Application ===-");
        InitializeData.run();
        TransactionLauncher.run();
        System.out.println("Failed transactions: " + TransferTask.getNumberFailedTransactions());
        logger.info(" -=== Finish Application ===-");
    }

}
