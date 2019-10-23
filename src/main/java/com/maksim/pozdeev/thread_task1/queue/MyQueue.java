package com.maksim.pozdeev.thread_task1.queue;

import com.maksim.pozdeev.thread_task1.Application;
import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

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

/**
 * TO-DO:
 * 1. перепутал логику работы get и put...
 *
 *
 * */

    private static final Logger logger = LogManager.getLogger(Application.class);
    private static int countOfReceivedRequests = 0;

    private LinkedList<HotelBookingRequest> queue = new LinkedList<>();

    private volatile int queueSize = 0;

    public synchronized void put(HotelBookingRequest request){
        logger.info("Поступил "+ countOfReceivedRequests+ " request");
        while (queue.size() >= Application.MAX_SIZE_OF_QUEUE){
            try {
                wait();
            } catch (InterruptedException ex) {
                logger.error("Возникло исключение во время wait(): " + ex);
//                Thread.currentThread().interrupt();
            }
        }
        queue. add(request);
        logger.info("Поток " + Thread.currentThread().getName() + " добавил запрос id: " +request.getIdRequests());
        queueSize++;
        countOfReceivedRequests++;
        logger.info("Принят новй запрос. Всего принято: " + countOfReceivedRequests);
//        notifyAll();
    }//put

    public synchronized HotelBookingRequest get(){

        boolean isCountRequestNotLimit = countOfReceivedRequests < Application.REQUEST_LIMIT;

        while (queue.isEmpty() && isCountRequestNotLimit){
            try {
                wait();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
//                Thread.currentThread().interrupt();
            }
        }
        HotelBookingRequest request = queue.removeLast();
        queueSize--;
        logger.info("Запрос был отправлен на исполнение.");
//        notifyAll();
        return request;
    } //get

    public synchronized int getQueueSize() {
        return queueSize;
    }
}
