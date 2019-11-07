package com.maksim.pozdeev.thread_task2.services;

import com.maksim.pozdeev.thread_task2.dto.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class AccountDeserializator {
    private static final Logger logger = LoggerFactory.getLogger(AccountDeserializator.class);

    private static final String PATH_TO_ACCOUNTS = "src/main/resources/accounts/";

    public static Account run(String fileName) throws FileNotFoundException {
        File accountsDirectory = new File(PATH_TO_ACCOUNTS);
        if (!accountsDirectory.exists()) {
            logger.error("Директория не найдена: ");
            throw new FileNotFoundException("Директория не найдена");
        }
        return readAccountFromFile(fileName);
    }

    private static Account readAccountFromFile(String fileName) {
        File fullFileName = new File(PATH_TO_ACCOUNTS + fileName);
        try (FileInputStream fiStream = new FileInputStream(fullFileName);
             ObjectInputStream oiStream = new ObjectInputStream(fiStream)) {
            return (Account) oiStream.readObject();
        } catch (ClassNotFoundException ex) {
            logger.error("Класс не определён", ex);
        } catch (FileNotFoundException ex) {
            logger.error("Файл не может быть создан: ", ex);
        } catch (InvalidClassException ex) {
            logger.error("Неверная версия класса: " + ex);
        } catch (IOException ex) {
            logger.error("IOException: ", ex);
        }
        return null;
    }

    private static File[] listPaths() {
        File myFolder = new File(PATH_TO_ACCOUNTS);
        return myFolder.listFiles();
    }

    public static List<Account> getAccounts() throws FileNotFoundException {
        File accountsDirectory = new File(PATH_TO_ACCOUNTS);
        if (!accountsDirectory.exists()) {
            logger.error("Директория не найдена: ");
            throw new FileNotFoundException("Директория не найдена");
        }
        File[] filesName = listPaths();
        if (filesName != null) {
            List<Account> accountList = new LinkedList<>();
            for (File fileName : filesName) {
                accountList.add(readAccountFromFile(fileName.getName()));
            }
            return accountList;
        }else{
            throw new FileNotFoundException("Файлы не обнаружены");
        }
    }

}
