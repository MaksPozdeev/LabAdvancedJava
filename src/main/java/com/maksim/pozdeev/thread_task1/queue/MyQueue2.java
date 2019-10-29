package com.maksim.pozdeev.thread_task1.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class MyQueue2<T> implements MyQueue<T> {
    private static final Logger logger = LogManager.getLogger(MyQueue2.class);

    private static final Integer MAX_SIZE_OF_QUEUE = 5;

    private LinkedList<T> queue = new LinkedList<>();

    @Override
    public synchronized int size() {
        return queue.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public synchronized void put(T request) {
        logger.info("put() ");
        while (queue.size() == MAX_SIZE_OF_QUEUE) {
            try {
                wait();
            } catch (InterruptedException e) {
//                e.printStackTrace();
                logger.error("MyQueue2.put(): ".concat(String.valueOf(e)));

            }
        }
        if (queue.isEmpty()) {
            notifyAll();
        }
        queue.add(request);
        logger.info("Request was added in queue");
    }

    @Override
    public synchronized T get(int i) {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
//                e.printStackTrace();
                logger.error("MyQueue2.get(): ".concat(String.valueOf(e)));
            }
        }
        if (queue.size() == MAX_SIZE_OF_QUEUE) {
            notifyAll();
        }
        return queue.get(i);
    }

    @Override
    public synchronized T take() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                logger.error("MyQueue2.take(): ".concat(String.valueOf(e)));
            }
        }
        if (queue.size() == MAX_SIZE_OF_QUEUE) {
            notifyAll();
        }
        return queue.removeFirst();
    }

}
