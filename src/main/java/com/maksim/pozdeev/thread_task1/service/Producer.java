package com.maksim.pozdeev.thread_task1.service;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TO-DO
 * Метод ArrayBlockingQueue.put() - будет ждать пока место в очереди не освободится
 */
public class Producer implements Runnable {
    private static final Logger logger = LogManager.getLogger(Producer.class);

    private MyQueue myQueue;

    public Producer(MyQueue myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        RequestGenerator requestGenerator = new RequestGenerator();
        HotelBookingRequest hotelBookingRequest;
        logger.info("");
        hotelBookingRequest = requestGenerator.generationRequest();
        myQueue.put(hotelBookingRequest);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("Произошла ошибка во время сна" + e);
        }
        Thread.currentThread().interrupt();
    }
}
