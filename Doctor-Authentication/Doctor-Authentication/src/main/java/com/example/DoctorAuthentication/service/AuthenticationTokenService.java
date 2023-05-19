package com.example.DoctorAuthentication.service;

import com.example.DoctorAuthentication.dao.TokenRepository;
import com.example.DoctorAuthentication.model.AuthenticationToken;
import com.example.DoctorAuthentication.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {
    @Autowired
    TokenRepository tokenRepository;
    public void saveToken(AuthenticationToken token){
        tokenRepository.save(token);
    }

    public AuthenticationToken getToken(Patient patient) {
        return tokenRepository.findByPatient(patient);
    }

    public boolean authenticate(String userEmail, String token) {
        AuthenticationToken authenticationToken=tokenRepository.findFirstByToken(token);
        if(authenticationToken==null){
            return false;
        }
      String expectedEmail=  authenticationToken.getPatient().getPatientEmail();
        if(expectedEmail==null)
            return false;

      return expectedEmail.equals(userEmail);
    }
}
