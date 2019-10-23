package com.maksim.pozdeev.thread_task1.service;

import com.maksim.pozdeev.thread_task1.queue.MyQueue;

/**
 * To-DO
 * пока количество обработанных заданий не достигнуто предела и очередь пуста - ждём()
 * аналог ArrayBlockingQueue.take()
 * */
public class Consumer implements Runnable {

    private MyQueue myQueue;

    public Consumer(MyQueue myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        while (myQueue.getQueueSize() > 0){
            myQueue.get();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
