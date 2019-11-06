package com.maksim.pozdeev.thread_task1.queue;

public interface MyQueue<T> {

    void put(T t);
    T take();

}
