package com.maksim.pozdeev.thread_task1.service;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Consumer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private MyQueue<HotelBookingRequest> myQueue;

    public Consumer(MyQueue<HotelBookingRequest> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        HotelBookingRequest hbr = myQueue.take();
        logger.info("booker #" + Thread.currentThread().getName() + ": обработал запрос " + hbr.getIdRequests());
        System.out.println("Запрос ID: " + hbr.getIdRequests() + " получен из очереди");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("Ошибка во время простоя: ", e);
            e.printStackTrace();
        }
    }
}