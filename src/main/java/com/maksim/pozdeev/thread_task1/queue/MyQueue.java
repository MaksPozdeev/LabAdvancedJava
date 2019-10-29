package com.maksim.pozdeev.thread_task1.queue;

public interface MyQueue<T> {

    int size();

    boolean isEmpty();

    void put(T t);

    T get(int i);

    T take();
}
