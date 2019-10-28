package com.maksim.pozdeev.thread_task1.executor;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;

public class ExecutorConsumers {

    public static final Integer NUMBER_OF_CONSUMERS = 6;

    private MyQueue<HotelBookingRequest> myQueue;

    public ExecutorConsumers(MyQueue<HotelBookingRequest> myQueue) {
        this.myQueue = myQueue;
    }

    public void start(){
//        ...
    }


}
