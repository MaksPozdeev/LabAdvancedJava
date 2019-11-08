package com.maksim.pozdeev.thread_task2.services;

import com.maksim.pozdeev.thread_task2.dto.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class AccountSerializator {
    private static final Logger logger = LoggerFactory.getLogger(AccountSerializator.class);

    private static final String PATH_TO_ACCOUNTS = "src/main/resources/accounts/";

    static boolean run(Account account) {
        File accountsDirectory = new File(PATH_TO_ACCOUNTS);
        if (!accountsDirectory.exists()) {
            accountsDirectory.mkdir();
        }

        boolean flag = false;
        String fileName = PATH_TO_ACCOUNTS + "accountID_" + account.getIdAccount();
        File serializableFile = new File(fileName);
        try (FileOutputStream foStream = new FileOutputStream(serializableFile);
             ObjectOutputStream ooStream = new ObjectOutputStream(foStream)) {
            ooStream.writeObject(account);
            flag = true;
        } catch (FileNotFoundException ex) {
            logger.error("Файл не может быть создан: ", ex);
        } catch (NotSerializableException ex) {
            logger.error("Класс не поддерживает сериализацию: ", ex);
        } catch (IOException ex) {
            logger.error("IOException: ", ex);
        }
        return flag;
    }

}
