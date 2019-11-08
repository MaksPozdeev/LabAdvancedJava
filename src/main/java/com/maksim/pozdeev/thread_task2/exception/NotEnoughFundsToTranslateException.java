package com.maksim.pozdeev.thread_task2.exception;

public class NotEnoughFundsToTranslateException extends IllegalArgumentException  {
    public NotEnoughFundsToTranslateException(String msg) {
        super(msg);
    }
}
