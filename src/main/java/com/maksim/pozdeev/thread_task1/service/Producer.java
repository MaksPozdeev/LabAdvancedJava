package com.maksim.pozdeev.thread_task1.service;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Producer implements Runnable {

    private static final Logger logger = LogManager.getLogger(Producer.class);

    private MyQueue<HotelBookingRequest> myQueue;

    public Producer(MyQueue<HotelBookingRequest> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        HotelBookingRequest hotelBookingRequest = new RequestGenerator().generationRequest();
        myQueue.put(hotelBookingRequest);
        System.out.println("Запрос ID: " + hotelBookingRequest.getIdRequests() + " отправлен в очередь");
        logger.info("producer #" + Thread.currentThread().getName() + ": отправил запрос " + hotelBookingRequest.getIdRequests());
    }
}
