package com.springboot.jpa.hospitalManagement;

import com.springboot.jpa.hospitalManagement.Repository.PatientRepository;
import com.springboot.jpa.hospitalManagement.Service.PatientService;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){
        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
    }

    @Test
    public void testTransacctionMethods(){
        Patient patient = patientService.getPatientByUd(1L);

        System.out.println(patient);
    }



}
