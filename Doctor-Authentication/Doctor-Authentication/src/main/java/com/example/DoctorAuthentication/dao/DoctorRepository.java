package com.example.DoctorAuthentication.dao;

import com.example.DoctorAuthentication.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Doctor findByDoctorId(Long docId);
}
