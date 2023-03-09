package com.example.demo.Services.Repositery;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Services.Model.Appointment;

public interface AppointmentRepo extends MongoRepository<Appointment, String> {
public Appointment findByAID(String aID);
}
