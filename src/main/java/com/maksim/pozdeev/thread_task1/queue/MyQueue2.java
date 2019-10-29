package com.maksim.pozdeev.thread_task1.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class MyQueue2<T> implements MyQueue<T> {
    private static final Logger logger = LogManager.getLogger(MyQueue2.class);

    private static final Integer MAX_SIZE_OF_QUEUE = 5;
    private static final Integer REQUEST_LIMIT = 15;

    private volatile int requestsReceived = 0;
    private int requestsReceivedTake = 0;

    private LinkedList<T> queue = new LinkedList<>();

//    @Override
//    public synchronized int size() {
//        return queue.size();
//    }
//
//    @Override
//    public synchronized boolean canGetRequest() {
//        logger.info("canGetRequest(): " + requestsReceived);
//        System.out.println("canGetRequest().requestsReceived= " + requestsReceived);
//        return requestsReceived <= REQUEST_LIMIT;
//    }
//
//    @Override
//    public synchronized int getRequestsReceived() {
//        return requestsReceived;
//    }
//
//    @Override
//    public synchronized boolean isEmpty() {
//        return queue.isEmpty();
//    }

    @Override
    public synchronized void put(T request) {
        logger.info("put() was init");

        while (queue.size() == MAX_SIZE_OF_QUEUE) {
            try {
                wait();
            } catch (InterruptedException e) {
//                e.printStackTrace();
                logger.error("MyQueue2.put(): ".concat(String.valueOf(e)));
                Thread.currentThread().interrupt();
            }
        }

        if (queue.isEmpty()) {
            notifyAll();
        }

        if (requestsReceived < REQUEST_LIMIT) {
            queue.add(request);
            requestsReceived++;
            logger.info("put(): Запрос был добавлен в очередь");
        }else{
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public synchronized T take() {

        while (queue.isEmpty() && requestsReceived < REQUEST_LIMIT) {
            try {
                wait();
            } catch (InterruptedException e) {
                logger.error("MyQueue2.take(): ".concat(String.valueOf(e)));
                Thread.currentThread().interrupt();
            }
        }

        if (queue.size() == MAX_SIZE_OF_QUEUE) {
            notifyAll();
        }

        if(requestsReceivedTake == REQUEST_LIMIT){
            Thread.currentThread().interrupt();
        }

        requestsReceivedTake++;
        logger.info("take(): Запрос был взят из очереди. requestsReceived = " + requestsReceivedTake);
        return queue.removeFirst();
    }


}
