package com.springboot.jpa.hospitalManagement.dto;

import com.springboot.jpa.hospitalManagement.entity.Department;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DoctorRequestDto {
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
