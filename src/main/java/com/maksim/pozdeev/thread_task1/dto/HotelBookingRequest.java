package com.maksim.pozdeev.thread_task1.dto;

import java.util.Date;

public class HotelBookingRequest {

    private static int count = 0;

    private int idRequests;
    private Date dayOfArrival;
    private Date dayOfDeparture;
    private String nameOfHotel;

    public HotelBookingRequest(Date dayOfArrival, Date dayOfDeparture, String nameOfHotel) {
        this.idRequests = ++count;
        this.dayOfArrival = dayOfArrival;
        this.dayOfDeparture = dayOfDeparture;
        this.nameOfHotel = nameOfHotel;
    }

    public int getIdRequests() {
        return idRequests;
    }

    @Override
    public String toString() {
        return "HotelBookingRequests{" +
                "id= " + idRequests +
                ", dayOfArrival= " + dayOfArrival +
                ", dayOfDeparture= " + dayOfDeparture +
                ", nameOfHotel='" + nameOfHotel + '\'' +
                '}';
    }
}
