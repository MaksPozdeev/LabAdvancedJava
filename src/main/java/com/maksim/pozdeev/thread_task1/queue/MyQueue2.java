package com.maksim.pozdeev.thread_task1.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class MyQueue2<T> implements MyQueue<T> {

    private static final Integer MAX_SIZE_OF_QUEUE = 5;

    private static final Logger logger = LogManager.getLogger(MyQueue1.class);

    private LinkedList<T> queue = new LinkedList<>();

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void put(T request) {
        queue.add(request);
    }

    @Override
    public T get(int i) {
        return queue.get(i);
    }

    @Override
    public T removeFirst() {
        return queue.removeFirst();
    }


}
