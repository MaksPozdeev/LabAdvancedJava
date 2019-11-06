package com.maksim.pozdeev.thread_task1;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.executor.ExecutorConsumers;
import com.maksim.pozdeev.thread_task1.executor.ExecutorProducers;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import com.maksim.pozdeev.thread_task1.queue.MyQueue3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static final Integer REQUEST_LIMIT = 15;

    public static void main(String[] args) {
        logger.info("Start application!");

        MyQueue<HotelBookingRequest> myQueue = new MyQueue3<>();

        ExecutorProducers executorProducers = new ExecutorProducers(myQueue);
        ExecutorConsumers executorConsumers = new ExecutorConsumers(myQueue);

        try {
            executorProducers.start();
            executorConsumers.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("Ошибка в Application.main(): ", e);
        }

         System.out.println("Конец main()");
    }
}
