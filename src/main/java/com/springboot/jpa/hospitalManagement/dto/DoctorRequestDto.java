package com.springboot.jpa.hospitalManagement.dto;

import com.springboot.jpa.hospitalManagement.entity.Department;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DoctorRequestDto {
    private String name;

    private String specialization;

    private String email;

    private Set<Department> departments = new HashSet<>();

}
