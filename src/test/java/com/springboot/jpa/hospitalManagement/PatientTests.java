package com.springboot.jpa.hospitalManagement;

import com.springboot.jpa.hospitalManagement.Repository.PatientRepository;
import com.springboot.jpa.hospitalManagement.Service.PatientService;
import com.springboot.jpa.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import com.springboot.jpa.hospitalManagement.entity.type.BloodGroupType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;


    @Test
    public void testPatientRepository(){
        //List<Patient> patientList = patientRepository.findAll();
        List<Patient> patientList = patientRepository.findAllPatientWithAppointment();

        System.out.println(patientList);
    }

    @Test
    public void testTransacctionMethods(){
       /* Patient patient = patientService.getPatientByUd(1L);

        System.out.println(patient);

        //Patient patient = patientRepository.findByName("Aarav Sharma");

        List<Patient> patientList = patientRepository.findByBirthDateOrEmail(
                LocalDate.of(1990, 5 , 10),
                "aarav.sharma@example.com"
        );

        /*System.out.println(patient);*/

/*
        List<Patient> patientList = patientRepository.findByNameContaining("Di");
*/


        //List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroupType.A_POSSITIVE);

        //List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(1988 , 3 , 15));

        /*List<Patient> patientList = patientRepository.findAllPatient();

        for(Patient patient : patientList){
            System.out.println(patient);
        }*/

        /*List<Object[]> bloodGroupList = patientRepository.countEachBloodGroupType();

        for(Object[] objects : bloodGroupList){
            System.out.println(objects[0] +" " + objects[1]);
        }*/
        /*
        int rowsUpdated = patientRepository.updateNameWithId("rutik",1L);
        System.out.println(rowsUpdated);*/

        /*List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
        for(BloodGroupCountResponseEntity bloodGroupCountResponse : bloodGroupList){
            System.out.println(bloodGroupCountResponse);
        }*/

        /*Page<Patient> patientlist = patientRepository.findAllPatient(PageRequest.of(0,2));
        for(Patient patient : patientlist){
            System.out.println(patient);
        }*/
    }
}
