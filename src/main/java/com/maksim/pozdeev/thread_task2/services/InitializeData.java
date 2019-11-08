package com.maksim.pozdeev.thread_task2.services;

import com.maksim.pozdeev.thread_task2.dto.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class InitializeData {
    private static final Logger logger = LoggerFactory.getLogger(InitializeData.class);

    private static final String PATH_TO_ACCOUNTS = "src/main/resources/accounts/";

    public static void run() {
        File accountsDirectory = new File(PATH_TO_ACCOUNTS);
        boolean isFilesFound = false;
        if (accountsDirectory.exists()) {
            File[] files = accountsDirectory.listFiles();
            if(files != null && files.length != 0){
                isFilesFound = true;
            }
        }

        if (!isFilesFound) {
            logger.info("Файлы аккаунтов не найдены: будут сгенерированны новые.");
            List<Account> accounts = AccountsGenerator.generate();
            for (Account a : accounts) {
                AccountSerializator.run(a);
            }
        }else{
            logger.info("Найдены файлы аккаунтов! Будут использоваться они.");
        }

    }

}
