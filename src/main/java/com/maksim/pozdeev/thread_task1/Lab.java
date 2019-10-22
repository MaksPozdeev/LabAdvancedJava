package com.maksim.pozdeev.thread_task1;

import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.service.RequestGenerator;

import java.util.concurrent.ArrayBlockingQueue;

public class Lab {
    public static void main(String[] args) {
        RequestGenerator requestGenerator = new RequestGenerator();

        HotelBookingRequest hotelBookingRequest;
        for (int i = 0; i < 15; i++) {
            hotelBookingRequest = requestGenerator.generationRequest();
            System.out.println(hotelBookingRequest.toString());
        }




//        ArrayBlockingQueue
    }
}
