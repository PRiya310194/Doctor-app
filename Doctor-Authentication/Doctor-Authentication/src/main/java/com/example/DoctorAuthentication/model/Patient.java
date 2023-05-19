package com.example.DoctorAuthentication.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String patientPassword;
    private String patientContact;

    public Patient(String patientFirstName, String patientLastName, String patientEmail, String patientPassword, String patientContact) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientEmail = patientEmail;
        this.patientPassword = patientPassword;
        this.patientContact = patientContact;
    }
    @OneToOne(mappedBy="patient")
    Appointment appointment;
}
