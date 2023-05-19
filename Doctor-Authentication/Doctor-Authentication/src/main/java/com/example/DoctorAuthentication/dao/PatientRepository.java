package com.example.DoctorAuthentication.dao;

import com.example.DoctorAuthentication.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findFirstByPatientEmail(String userEmail);
}
