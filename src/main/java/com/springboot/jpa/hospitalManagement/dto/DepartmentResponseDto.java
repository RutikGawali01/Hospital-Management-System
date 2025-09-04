package com.springboot.jpa.hospitalManagement.dto;


import com.springboot.jpa.hospitalManagement.entity.Doctor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class DepartmentResponseDto {

    private Long id;
    private String name;


    private DoctorForDepartment headDoctor;
    private List<DoctorForDepartment> doctors =new ArrayList<>();
}
