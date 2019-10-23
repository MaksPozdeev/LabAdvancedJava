package com.maksim.pozdeev.thread_task1.queue;

import com.maksim.pozdeev.thread_task1.Application;
import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class MyQueue {
    /*
     * Состоит из:
     *   хранилища данных (FIFO)
     *   информация о хранилище:
     *       емкость хранилища (capacity)
     *       кол-во записанных объектов (size)
     *       переменная хранящая кол-во полученных запросов
     *   методы для работы с хранилищем:
     *       принимать данные - записывать в хранилище (put)
     *       отдавать данные - отдавать данные из хранилища (get)
     *   Доп.функционла:
     *       логирование
     * */

    private static final Logger logger = LogManager.getLogger(MyQueue.class);
    private volatile int countOfReceivedRequests = 0;

    private LinkedList<HotelBookingRequest> queue = new LinkedList<>();

    public synchronized void put(HotelBookingRequest request) {
        logger.info("Поступил " + countOfReceivedRequests + " request с id: " + request.getIdRequests());

//        Проверка на лимит выполненных запросов
        if (getCountOfReceivedRequests() < Application.REQUEST_LIMIT) {
            logger.info("Лимит не достигнут - продолжаем...");

//        Проверяем на заполнение очереди
            while (queue.size() >= Application.MAX_SIZE_OF_QUEUE) {
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

        boolean isCountRequestNotLimit = countOfReceivedRequests < Application.REQUEST_LIMIT;

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
