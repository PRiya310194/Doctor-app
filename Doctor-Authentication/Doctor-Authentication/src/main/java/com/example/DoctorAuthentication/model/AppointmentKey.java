package com.example.DoctorAuthentication.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class AppointmentKey implements Serializable {  // serializable bytes
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long appointmentId;
    public LocalDateTime time;


}
