package com.maksim.pozdeev.thread_task1_with_BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();

    }

}