package com.maksim.pozdeev.thread_task1.service;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;

/**
 * TO-DO
 * Метод ArrayBlockingQueue.put() - будет ждать пока место в очереди не освободится
 * */
public class Producer implements Runnable{

    private MyQueue myQueue;

    public Producer(MyQueue myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        RequestGenerator requestGenerator = new RequestGenerator();
        HotelBookingRequest hotelBookingRequest = requestGenerator.generationRequest();
        myQueue.put(hotelBookingRequest);
    }
}
