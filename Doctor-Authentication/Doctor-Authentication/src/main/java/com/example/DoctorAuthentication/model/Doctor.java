package com.example.DoctorAuthentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorName;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
@OneToMany(mappedBy="doctor")
    List<Appointment> appointments;


}
// localhost:8080/patient/doctor?userEmail=pk059257@gmail.com&token=81DC9BDB52D04DC20036DBD8313ED055