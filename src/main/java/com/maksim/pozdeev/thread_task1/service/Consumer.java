package com.maksim.pozdeev.thread_task1.service;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;

/**
 * To-DO
 * пока количество обработанных заданий не достигнуто предела и очередь пуста - ждём()
 * аналог ArrayBlockingQueue.take()
 */
public class Consumer implements Runnable {

    private MyQueue<HotelBookingRequest> myQueue;

    public Consumer(MyQueue<HotelBookingRequest> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        HotelBookingRequest hbr;
//        Пока в очереди есть задания бери на выполнение
        while (myQueue.size() > 0) {
            hbr = myQueue.removeFirst();
//            Сообщение из задания
//            В очереди освободилось место - как дать знать консумеру...
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
