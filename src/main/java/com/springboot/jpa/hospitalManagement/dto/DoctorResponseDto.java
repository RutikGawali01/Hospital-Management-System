package com.springboot.jpa.hospitalManagement.dto;

import com.springboot.jpa.hospitalManagement.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDto {
    private Long id;
    private String name;
    private String specialization;
    private String email;

    private int age;

    private String gender;

    private Long phone;

    private String experience;

    private String address;

    private String qualifications;

    private Set<Department> departments = new HashSet<>();
}
