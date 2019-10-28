package com.maksim.pozdeev.thread_task1;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.executor.ExecutorConsumers;
import com.maksim.pozdeev.thread_task1.executor.ExecutorProducers;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import com.maksim.pozdeev.thread_task1.queue.MyQueue2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Start application!");

        MyQueue<HotelBookingRequest> myQueue = new MyQueue2<>();

        ExecutorProducers executorProducers = new ExecutorProducers(myQueue);
        ExecutorConsumers executorConsumers = new ExecutorConsumers(myQueue);

        try {
            executorProducers.start();
            executorConsumers.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("Application.main(): " + e);
        }

//        Вывод "для себя" в конце его не будет
//        for (int i = 0; i < myQueue.size(); i++) {
//            System.out.println(myQueue.get(i));
//        }




    }
}
