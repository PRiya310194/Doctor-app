package com.example.DoctorAuthentication.dao;

import com.example.DoctorAuthentication.model.AuthenticationToken;
import com.example.DoctorAuthentication.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findByPatient(Patient patient);

    AuthenticationToken findFirstByToken(String token);
}
