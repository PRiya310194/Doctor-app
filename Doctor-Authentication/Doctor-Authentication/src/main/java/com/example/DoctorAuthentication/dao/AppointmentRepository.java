package com.example.DoctorAuthentication.dao;

import com.example.DoctorAuthentication.model.Appointment;
import com.example.DoctorAuthentication.model.AppointmentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, AppointmentKey> {
}
