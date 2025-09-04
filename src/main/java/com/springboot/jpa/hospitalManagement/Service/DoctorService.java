package com.springboot.jpa.hospitalManagement.Service;

import com.springboot.jpa.hospitalManagement.Repository.DoctorRepository;
import com.springboot.jpa.hospitalManagement.dto.DepartmentResponseDto;
import com.springboot.jpa.hospitalManagement.dto.DoctorRequestDto;
import com.springboot.jpa.hospitalManagement.dto.DoctorResponseDto;
import com.springboot.jpa.hospitalManagement.dto.PatientRequestdto;
import com.springboot.jpa.hospitalManagement.entity.Doctor;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    // get all doctors
    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }


    public DoctorResponseDto registerNewDoctor(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = modelMapper.map(doctorRequestDto , Doctor.class);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(savedDoctor , DoctorResponseDto.class);
    }

    public DoctorResponseDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("doctor not exist with id " + id));
        return modelMapper.map(doctor , DoctorResponseDto.class);
    }

    public DoctorResponseDto updateDoctor(Long id, PatientRequestdto patientRequestdto) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow( () ->
                new EntityNotFoundException(" doctor not exist with id " + id));

        modelMapper.map(patientRequestdto , doctor);


        doctor = doctorRepository.save(doctor);

        return modelMapper.map(doctor , DoctorResponseDto.class);
    }

    public List<DepartmentResponseDto> getDepartmentsOfDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Doctor not exist with id " + id));

        List<DepartmentResponseDto> departments = doctor.getDepartments()
                .stream()
                .map(department -> modelMapper.map(department , DepartmentResponseDto.class))
                .collect(Collectors.toList());
        return departments;
    }
}
