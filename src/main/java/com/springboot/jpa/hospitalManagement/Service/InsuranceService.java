package com.springboot.jpa.hospitalManagement.Service;

import com.springboot.jpa.hospitalManagement.Repository.InsuranceRepository;
import com.springboot.jpa.hospitalManagement.Repository.PatientRepository;
import com.springboot.jpa.hospitalManagement.entity.Insurance;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;


    @Transactional
    public Patient assigninnsuranceToPatient(Insurance insurance , Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("patient not found with id : " + patientId));

        patient.setInsurance(insurance);// without saving it will just bcz of cascading

        insurance.setPatient(patient); // this is just for maintaining bidirectional consistency.

        return patient;
    }

    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("patient not found with id : " + patientId));

        patient.setInsurance(null); // insurance will be deleted from given patient

        return patient;
    }

}
