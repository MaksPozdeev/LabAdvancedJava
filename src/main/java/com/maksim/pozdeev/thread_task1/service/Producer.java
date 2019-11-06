package com.maksim.pozdeev.thread_task1.service;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Producer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
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
