package com.example.demo.Repositery;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Model.appointment.Appointment;

public interface AppointmentRepo extends MongoRepository<Appointment, String> {
    public Appointment findByAID(String aID);
}
