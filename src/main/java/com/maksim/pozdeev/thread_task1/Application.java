package com.maksim.pozdeev.thread_task1;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import com.maksim.pozdeev.thread_task1.service.Producer;
import com.maksim.pozdeev.thread_task1.service.RequestGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static final Integer MAX_SIZE_OF_QUEUE = 35;
    public static final Integer REQUEST_LIMIT = 15;
    public static final Integer NUMBER_OF_PRODUCERS = 3;
    public static final Integer NUMBER_OF_CONSUMERS = 6;

    public static void main(String[] args) {
        logger.info("Start application!");

        MyQueue myQueue = new MyQueue();

        Thread thread;
        for (int i = 0; i < NUMBER_OF_PRODUCERS; i++) {
            thread = new Thread(new Producer(myQueue));
            logger.info("Создали новый поток");
            thread.start();
            logger.info("Запустили новый поток");
        }

        while (myQueue.getQueueSize() != 0) {
            System.out.println(myQueue.get());
        }


    }

}
