package com.maksim.pozdeev.thread_task1.queue;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;

public interface MyQueue<T> {

//    int size();

//    boolean isEmpty();

    void put(T t);

    T take();

//    boolean canGetRequest();

//    int getRequestsReceived();
}
