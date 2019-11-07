package com.maksim.pozdeev.thread_task2;

import com.maksim.pozdeev.thread_task2.dto.Account;
import com.maksim.pozdeev.thread_task2.services.AccountsGenerator;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {

//        План:
//        1. Генерируем данные
//        2. Сериализуем аккаунты на диске в папке resources/accounts
//        === Данные готовы для использования
//        3. Считываем файлы из папке (десериализуем)
//        - при считывании узнаём общий баланс системы (сумму всех счетов)
//        4. Производим переводы
//        5. Производим проверку общего баланса системы (должен быть как и старый)
//        6. Сериализуем объекты

        List<Account> accounts = AccountsGenerator.generate();
        for (Account a : accounts){
            System.out.println(a.toString());
        }

    }

}
