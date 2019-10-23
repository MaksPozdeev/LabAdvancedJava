package com.maksim.pozdeev.thread_task1;

import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import com.maksim.pozdeev.thread_task1.service.Producer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static final Integer MAX_SIZE_OF_QUEUE = 25;
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
            logger.info("Запустили новый поток: " + thread.getName());
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            logger.error("Произошло исключения в главном птоке во время ожидания выполнения потоков" + e);
//            e.printStackTrace();
        }

//        System.out.println(myQueue.get());

    }

}
