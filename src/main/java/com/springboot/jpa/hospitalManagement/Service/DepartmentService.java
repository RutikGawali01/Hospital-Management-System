package com.springboot.jpa.hospitalManagement.Service;

import com.springboot.jpa.hospitalManagement.Repository.DepartmentRepository;
import com.springboot.jpa.hospitalManagement.Repository.DoctorRepository;
import com.springboot.jpa.hospitalManagement.dto.DepartmentCreateRequestDto;
import com.springboot.jpa.hospitalManagement.dto.DepartmentResponseDto;
import com.springboot.jpa.hospitalManagement.dto.DoctorForDepartment;
import com.springboot.jpa.hospitalManagement.dto.DoctorResponseDto;
import com.springboot.jpa.hospitalManagement.entity.Department;
import com.springboot.jpa.hospitalManagement.entity.Doctor;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    private final DoctorRepository doctorRepository;


    public DepartmentResponseDto addDepartment(DepartmentCreateRequestDto departmentCreateRequestDto) {
        Department department = new Department();
        department.setName(departmentCreateRequestDto.getName());

        Long headDoctorId = departmentCreateRequestDto.getHeadDoctorId();
        //set headDoctor to department
        department.setHeadDoctor(
                doctorRepository.findById(headDoctorId).orElseThrow(() -> new EntityNotFoundException("doctor not found with id" + headDoctorId))
        );

        Set<Doctor> doctors = departmentCreateRequestDto.getDoctorIds().stream()
                .map(id -> doctorRepository.findById(id).orElseThrow(()->
                        new EntityNotFoundException("doctor not exitst")))
                .collect(Collectors.toSet());
        department.setDoctors(doctors);

        Department savedDepartment = departmentRepository.save(department);

        // Manually map to DTO
        DepartmentResponseDto responseDto = new DepartmentResponseDto();
        responseDto.setId(savedDepartment.getId());
        responseDto.setName(savedDepartment.getName());

        // Map head doctor
        DoctorForDepartment headDoctorDto = new DoctorForDepartment(
                savedDepartment.getHeadDoctor().getId(),
                savedDepartment.getHeadDoctor().getName(),
                savedDepartment.getHeadDoctor().getSpecialization(),
                savedDepartment.getHeadDoctor().getEmail()
        );
        responseDto.setHeadDoctor(headDoctorDto);

        // Map doctor list
        List<DoctorForDepartment> doctorDtos = savedDepartment.getDoctors().stream()
                .map(d -> new DoctorForDepartment(d.getId(), d.getName() , d.getSpecialization() , d.getEmail()))
                .collect(Collectors.toList());
        responseDto.setDoctors(doctorDtos);

        return responseDto;
    }

    public List<DepartmentResponseDto> getAllDepartments(Integer pageNumber, Integer pagesize) {
        Pageable pageable = PageRequest.of(pageNumber , pagesize);

        return departmentRepository.getAllDepartments(pageable)
                .getContent()
                .stream()
                .map(department -> modelMapper.map(department , DepartmentResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<DoctorResponseDto> getDoctorsFromDepartments(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("department not exist with id" + id));

        DepartmentResponseDto dto =  modelMapper.map(department , DepartmentResponseDto.class);

        List<DoctorForDepartment> doctors = dto.getDoctors();

        return doctors
                .stream()
                .map(doctorForDepartment ->
                        modelMapper.map(doctorForDepartment , DoctorResponseDto.class))
                .collect(Collectors.toList());

    }
}
