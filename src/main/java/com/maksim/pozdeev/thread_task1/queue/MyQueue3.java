package com.maksim.pozdeev.thread_task1.queue;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue3<T extends HotelBookingRequest> implements MyQueue<T> {

    private static final Logger logger = LogManager.getLogger(MyQueue3.class);

    private static final Integer MAX_SIZE_OF_QUEUE = 5;
    private static final Integer REQUEST_LIMIT = 15;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private LinkedList<T> queue = new LinkedList<>();

    @Override
    public synchronized void put(T request) {
//        logger.info("put() was init");

        while (queue.size() >= MAX_SIZE_OF_QUEUE) {
            try {
                wait();
            } catch (InterruptedException e) {
//                logger.error("MyQueue2.put(): ".concat(String.valueOf(e)));
                Thread.currentThread().interrupt();
            }
        }

        if (queue.isEmpty()) {
            notifyAll();
        }

        if (atomicInteger.get() < REQUEST_LIMIT) {
            queue.add(request);
            logger.info("booker #" + Thread.currentThread().getName() + ": принял запрос " + request.getIdRequests());
            atomicInteger.incrementAndGet();
        }else{
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public synchronized T take() {

        while (queue.isEmpty() && atomicInteger.get() < REQUEST_LIMIT) {
            try {
                wait();
            } catch (InterruptedException e) {
//                logger.error("MyQueue2.take(): ".concat(String.valueOf(e)));
                Thread.currentThread().interrupt();
            }
        }

        if (queue.size() == MAX_SIZE_OF_QUEUE) {
            notifyAll();
        }
        return queue.removeFirst();
    }

}
