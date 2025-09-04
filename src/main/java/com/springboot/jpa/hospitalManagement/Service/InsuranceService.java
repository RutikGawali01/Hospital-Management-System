package com.springboot.jpa.hospitalManagement.Service;

import com.springboot.jpa.hospitalManagement.Repository.InsuranceRepository;
import com.springboot.jpa.hospitalManagement.Repository.PatientRepository;
import com.springboot.jpa.hospitalManagement.dto.InsuranceRequest;
import com.springboot.jpa.hospitalManagement.dto.InsuranceResponse;
import com.springboot.jpa.hospitalManagement.entity.Insurance;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;



    @Transactional
    public InsuranceResponse assigninnsuranceToPatient(InsuranceRequest insuranceRequest){
        Long patientId = insuranceRequest.getPatientId();
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("patient not found with id : " + patientId));



        Insurance insurance = modelMapper.map(insuranceRequest , Insurance.class);
        insurance.setId(null);
        insurance.setCreatedAt(LocalDateTime.now());
        patient.setInsurance(insurance);// without saving it will just bcz of cascading
        insurance.setPatient(patient);
        patientRepository.save(patient);

        return modelMapper.map(insurance , InsuranceResponse.class);
    }

    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("patient not found with id : " + patientId));

        patient.setInsurance(null); // insurance will be deleted from given patient

        return patient;
    }

    public List<InsuranceResponse> getAllInsurance(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber , pageSize);
        return insuranceRepository.getAllInsurance(pageable)
                .getContent()
                .stream()
                .map(insurance -> modelMapper.map(insurance , InsuranceResponse.class))
                .collect(Collectors.toList());
    }
}
