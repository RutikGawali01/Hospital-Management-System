package com.springboot.jpa.hospitalManagement;

import com.springboot.jpa.hospitalManagement.Service.InsuranceService;
import com.springboot.jpa.hospitalManagement.entity.Insurance;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    public InsuranceService insuranceService;

    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
               // .PolicyNumber("HDFC_101")
               // .Provider("HDFC")
               // .ValidUntil(LocalDate.of(2025,12,12))
                .build();



        // without cascase this will not run
        //Patient patient = insuranceService.assigninnsuranceToPatient(insurance,1L);
        /*System.out.println(patient);*/

        //var newPatient = insuranceService.disaccociateInsuranceFromPatient(patient.getId());

       // System.out.println(newPatient);
    }
}
