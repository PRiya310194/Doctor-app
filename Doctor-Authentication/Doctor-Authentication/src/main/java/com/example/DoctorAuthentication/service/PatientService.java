package com.example.DoctorAuthentication.service;

import com.example.DoctorAuthentication.dao.PatientRepository;
import com.example.DoctorAuthentication.dto.SignInInput;
import com.example.DoctorAuthentication.dto.SignInOutput;
import com.example.DoctorAuthentication.dto.SignUpInput;
import com.example.DoctorAuthentication.dto.SignUpOutput;
import com.example.DoctorAuthentication.model.AuthenticationToken;
import com.example.DoctorAuthentication.model.Doctor;
import com.example.DoctorAuthentication.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @Autowired
    DoctorService doctorService;

    public SignUpOutput signUp(SignUpInput signUpDto) {
        // check if user exist or not based on email
        Patient patient=patientRepository.findFirstByPatientEmail(signUpDto.getUserEmail());
        if(patient!=null){
            throw new IllegalStateException("Patient already exist !!!....sign in instead");
        }
        String encryptedPassword=null;
        try {
            encryptedPassword=encryptPassword(signUpDto.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // save the user

        patient =new Patient(signUpDto.getUserLastName(), signUpDto.getUserFirstName(),
                signUpDto.getUserEmail(), encryptedPassword, signUpDto.getUserContact());
        patientRepository.save(patient);

        // token creation and saving
        AuthenticationToken token=new AuthenticationToken(patient);
        authenticationTokenService.saveToken(token);
        return new SignUpOutput("patient registered","patient created successfully");
    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5= MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested=md5.digest();

        String hash= DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signIn(SignInInput signInDto) {
        // get email
        // encrypt the password
        // match it with database encrypted password
        // figure out the token
        // set up output response
        Patient patient=patientRepository.findFirstByPatientEmail(signInDto.getPatientEmail());
        if(patient==null){
            throw new IllegalStateException("user invalid !!!....sign up instead");

        }
        String encryptedPassword=null;
        try {
            encryptedPassword=encryptPassword(signInDto.getPatientPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
       boolean isPasswordValid=encryptedPassword.equals(patient.getPatientPassword());
        if(!isPasswordValid){
            throw new IllegalStateException("user invalid!!!.....sign up instead");
        }
        // figue out the token
        AuthenticationToken authToken=authenticationTokenService.getToken(patient);
        return new SignInOutput("Authentication successful",authToken.getToken());
    }

    public List<Doctor> getAllDoctor() {

        return doctorService.getAllDoctor();
    }
}
