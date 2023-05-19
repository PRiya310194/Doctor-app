package com.example.DoctorAuthentication.controller;

import com.example.DoctorAuthentication.dto.SignInInput;
import com.example.DoctorAuthentication.dto.SignInOutput;
import com.example.DoctorAuthentication.dto.SignUpInput;
import com.example.DoctorAuthentication.dto.SignUpOutput;
import com.example.DoctorAuthentication.model.Doctor;
import com.example.DoctorAuthentication.service.AuthenticationService;
import com.example.DoctorAuthentication.service.AuthenticationTokenService;
import com.example.DoctorAuthentication.service.DoctorService;
import com.example.DoctorAuthentication.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    // sign up-user will pass details , will return sign up output
    @PostMapping("/signup")
    public SignUpOutput signup(@RequestBody SignUpInput signUpDto){
        return patientService.signUp(signUpDto);
    }

    // sign in

    @PostMapping("/signin")
    public SignInOutput signup(@RequestBody SignInInput signInDto){
        return patientService.signIn(signInDto);
    }
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>>getAllDoctor(@RequestParam String userEmail, @RequestParam String token){
        // authenticate
        HttpStatus status;
        List<Doctor>allDoctors=null;
        if(authenticationTokenService.authenticate(userEmail,token)){
            allDoctors=patientService.getAllDoctor();
            status=HttpStatus.OK;
        }
      else{
          status=HttpStatus.FORBIDDEN;
        }
return new ResponseEntity<List<Doctor>>(allDoctors,status);
    }

}
