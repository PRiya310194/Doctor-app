package com.example.DoctorAuthentication.service;

import com.example.DoctorAuthentication.dao.DoctorRepository;
import com.example.DoctorAuthentication.model.Appointment;
import com.example.DoctorAuthentication.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;




    public List<Doctor> getAllDoctor() {
        List<Doctor>allDoctor=doctorRepository.findAll();
        return allDoctor;
    }



    public List<Appointment> getMyAppointment(Long docId) {

       Doctor myDoc=doctorRepository.findByDoctorId(docId);
         if(myDoc==null){
       throw new IllegalStateException("doctor does not exist");
}
        return myDoc.getAppointments();
    }

    public Doctor addDoctor(Doctor doc) {
        return doctorRepository.save(doc);
    }
}
