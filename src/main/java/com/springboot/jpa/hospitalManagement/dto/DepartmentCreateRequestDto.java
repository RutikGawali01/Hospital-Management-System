package com.springboot.jpa.hospitalManagement.dto;

import com.springboot.jpa.hospitalManagement.entity.Doctor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DepartmentCreateRequestDto {

    private String name;

    private Long headDoctorId;

    private Set<Long> doctorIds = new HashSet<>();
}
