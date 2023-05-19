package com.example.DoctorAuthentication.controller;

import com.example.DoctorAuthentication.model.Appointment;
import com.example.DoctorAuthentication.model.Doctor;
import com.example.DoctorAuthentication.service.AppointmentService;
import com.example.DoctorAuthentication.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doc) {

       Doctor doctor= doctorService.addDoctor(doc);
       return new ResponseEntity<>("doctor saved",HttpStatus.OK);
    }

    @GetMapping("{docId}/appointments")
    public ResponseEntity<List<Appointment>> getDocMyAppointments(@PathVariable Long docId) {
        List<Appointment> myAppointment=null;
        HttpStatus status;
        try{
        myAppointment = doctorService.getMyAppointment(docId);
            if(myAppointment.isEmpty()){
                 status= HttpStatus.NO_CONTENT;
            }
            else{
                status=HttpStatus.OK;
            }
        } catch (Exception e) {
            System.out.println("the doc id is not valid");
            status=HttpStatus.BAD_REQUEST;
        }
return new ResponseEntity<List<Appointment>>(myAppointment,status);
    }
}

