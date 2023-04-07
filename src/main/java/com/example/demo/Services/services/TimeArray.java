package com.example.demo.Services.services;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeArray {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public Map<String, List<String>> createTimeArray(
            int appointmentTime,
            String servStartTime,
            String servEndTime,
            String breakStartTime,
            String breakEndTime,
            Map<String, List<String>> bkd) {
        List<String> allTimeSlots = new ArrayList<String>();
        allTimeSlots.addAll(timeArray(appointmentTime, servStartTime, breakStartTime));
        allTimeSlots.addAll(timeArray(appointmentTime, breakEndTime, servEndTime));

        LocalDate today = LocalDate.now();
        Map<String, List<String>> obj = new HashMap<String, List<String>>();
        for (int i = 0; i < 7; i++) {
            today = today.plusDays(1);
            String dt = today.toString();
            List<String> slots = new ArrayList<String>();
            if (!bkd.containsKey(dt)) {
                slots.addAll(allTimeSlots);
            } else {
                for (String f : allTimeSlots) {
                    if (!bkd.get(dt).contains(f)) {
                        slots.add(f);
                    }
                }
            }
            obj.put(dt, slots);
        }
        return obj;
    }

    private List<String> timeArray(int x, String startTim, String endTim) {
        LocalTime startTime = LocalTime.parse(startTim, formatter);
        LocalTime endTime = LocalTime.parse(endTim, formatter);
        List<String> timeStops = new ArrayList<String>();
        String tp2, tp;
        while (true) {
            tp = startTime.format(formatter);
            startTime = startTime.plusMinutes(x);
            tp2 = startTime.format(formatter);
            if (startTime.isAfter(endTime))
                break;
            timeStops.add(tp + "-" + tp2);
        }
        return timeStops;
    }

    // public static void main(String[] args) {
    //     Map<String, List<String>> bkd = new HashMap<String, List<String>>();
    //     bkd =
    // }
}