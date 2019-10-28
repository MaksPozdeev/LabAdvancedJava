package com.maksim.pozdeev.thread_task1.service;

import com.maksim.pozdeev.thread_task1.Application;
import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Producer implements Runnable {
    private static final Logger logger = LogManager.getLogger(Application.class);

    private MyQueue<HotelBookingRequest> myQueue;

    public Producer(MyQueue<HotelBookingRequest> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        RequestGenerator requestGenerator = new RequestGenerator();
        HotelBookingRequest hotelBookingRequest = requestGenerator.generationRequest();
        logger.info("Producer.run(): received requestID: " + hotelBookingRequest.getIdRequests());

//        Возможно тут будет проверка на:
//        1. boolean (количество запросов ещё не максимально)
//        генерируем запрос
//            иначе -> {закрываем поток()}
//        2. boolean(очередь свободна)
//        if (1 && 2){  всё ок -> put}
//        else {ожидаем()}
        myQueue.put(hotelBookingRequest);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Произошла ошибка во время сна" + e);
        }
//        Thread.currentThread().interrupt();
    }
}
