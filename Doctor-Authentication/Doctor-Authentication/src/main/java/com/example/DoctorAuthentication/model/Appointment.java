package com.example.DoctorAuthentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Appointment {
    @Id
    @EmbeddedId
    private AppointmentKey id;
    @ManyToOne
    @JoinColumn(name="fk_doctorId")
    private Doctor doctor;

     @OneToOne
    private Patient patient;

}
