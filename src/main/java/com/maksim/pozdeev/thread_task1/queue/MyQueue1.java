package com.maksim.pozdeev.thread_task1.queue;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class MyQueue1 {
    /**
     * Состоит из:
     * хранилища данных (FIFO)
     * информация о хранилище:
     * емкость хранилища (capacity)
     * кол-во записанных объектов (size)
     * переменная хранящая кол-во полученных запросов
     * методы для работы с хранилищем:
     * принимать данные - записывать в хранилище (put)
     * отдавать данные - отдавать данные из хранилища (get)
     * Доп.функционла:
     * логирование
     */
    private static final Integer REQUEST_LIMIT = 15;
    private static final Integer MAX_SIZE_OF_QUEUE = 5;

    private static final Logger logger = LoggerFactory.getLogger(MyQueue1.class);
    private volatile int countOfReceivedRequests = 0;

    private LinkedList<HotelBookingRequest> queue = new LinkedList<>();

    public synchronized void put(HotelBookingRequest request) {
        logger.info("Поступил " + countOfReceivedRequests + " request с id: " + request.getIdRequests());

//        Проверка на лимит выполненных запросов
        if (getCountOfReceivedRequests() < REQUEST_LIMIT) {
            logger.info("Лимит не достигнут - продолжаем...");

//        Проверяем на заполнение очереди
            while (queue.size() >= MAX_SIZE_OF_QUEUE) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    logger.error("Возникло исключение во время wait(): " + ex);
                    Thread.currentThread().interrupt();
                }
            }
            logger.info("Очередь не заполнена, add запрос");
            queue.add(request);
            logger.info("Поток " + Thread.currentThread().getName() + " добавил запрос id: " + request.getIdRequests());
            countOfReceivedRequests++;

            logger.info("Принят новый запрос. Всего принято: " + countOfReceivedRequests);
//            notify();
        } else {
            logger.info("Достигнут лимит по обработке запросов: " + countOfReceivedRequests + " Закрываю поток: " + Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        }
        notifyAll();
    }//put

    public synchronized HotelBookingRequest get() {

        boolean isCountRequestNotLimit = countOfReceivedRequests < REQUEST_LIMIT;

        while (queue.isEmpty() && isCountRequestNotLimit) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                logger.error("Возникло исключение во время wait(): " + ex);
            }
        }
        HotelBookingRequest request = queue.removeLast();
//        queueSize--;
        logger.info("Запрос был отправлен на исполнение.");
//        notifyAll();
        return request;
    } //get


    public int getQueueSize() {
        return queue.size();
    }

    public synchronized int getCountOfReceivedRequests() {
        return countOfReceivedRequests;
    }
}
