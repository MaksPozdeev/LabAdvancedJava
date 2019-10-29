package com.maksim.pozdeev.thread_task1.executor;

import com.maksim.pozdeev.thread_task1.Application;
import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import com.maksim.pozdeev.thread_task1.service.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorConsumers {

    private static final Logger logger = LogManager.getLogger(ExecutorConsumers.class);

    private static final Integer NUMBER_OF_CONSUMERS = 6;

    private MyQueue<HotelBookingRequest> myQueue;

    public ExecutorConsumers(MyQueue<HotelBookingRequest> myQueue) {
        this.myQueue = myQueue;
    }

    public void start() throws InterruptedException {
        logger.info("ExecutorConsumer.start()");
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_CONSUMERS);

        int i =0;
        while (i <= Application.REQUEST_LIMIT) {
            Consumer consumer = new Consumer(myQueue);
            executorService.submit(consumer);
            i++;
        }

        executorService.shutdown();
//        executorService.awaitTermination(2, TimeUnit.SECONDS);
    }


}
