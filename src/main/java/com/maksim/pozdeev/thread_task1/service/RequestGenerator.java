package com.maksim.pozdeev.thread_task1.service;

import com.maksim.pozdeev.thread_task1.Application;
import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RequestGenerator {
    private static final Logger logger = LoggerFactory.getLogger(RequestGenerator.class);
    private static Random rnd = new Random();

    public HotelBookingRequest generationRequest() {
        String[] stringsNmeHotel = {
                "Ambazzador",
                "The Vennitzian",
                "Sherdaton",
                "Palazio",
                "Red Mirage",
                "Edem",
                "MMM Grand Toure",
                "TownCityCenter",
                "Ricchi",
                "Rediska"};

        List<String> hotelNameList = new ArrayList<>(Arrays.asList(stringsNmeHotel));
        int intDayOfArrival = 1 + rnd.nextInt(15);
        int intDayOfDeparture = 1 + rnd.nextInt(15) + intDayOfArrival;
        int hotelRandom = rnd.nextInt(hotelNameList.size() - 1) + 1;

        String strDayOfArrival = intDayOfArrival + "/10/2019";
        String strDayOfDeparture = intDayOfDeparture + "/10/2019";

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dateOfArrival = format.parse(strDayOfArrival);
            Date dateOfDeparture = format.parse(strDayOfDeparture);
            return new HotelBookingRequest(dateOfArrival, dateOfDeparture, hotelNameList.get(hotelRandom));
        } catch (ParseException e) {
            logger.error("Ошибка в RequestGenerator: ", e);
        }
        return null;
    }
}
