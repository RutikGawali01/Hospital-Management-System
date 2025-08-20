package com.springboot.jpa.hospitalManagement.Service;

import com.springboot.jpa.hospitalManagement.Repository.PatientRepository;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientByUd(Long id){
        Patient p1 =  patientRepository.findById(id).orElseThrow();

        Patient p2 = patientRepository.findById(id).orElseThrow();

        System.out.println(p1 == p2);

        p1.setName("yoyo");

        return p1;
    }
}
