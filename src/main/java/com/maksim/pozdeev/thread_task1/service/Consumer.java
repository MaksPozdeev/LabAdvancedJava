package com.maksim.pozdeev.thread_task1.service;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Consumer implements Runnable {

    private static final Logger logger = LogManager.getLogger(Consumer.class);

    private MyQueue<HotelBookingRequest> myQueue;

    public Consumer(MyQueue<HotelBookingRequest> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
//        logger.info("Consumer.run(): init");
        HotelBookingRequest hbr = myQueue.take();
        logger.info("booker #" + Thread.currentThread().getName() + ": обработал запрос " + hbr.getIdRequests());
        System.out.println("Запрос ID: " + hbr.getIdRequests() + " получен из очереди");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}