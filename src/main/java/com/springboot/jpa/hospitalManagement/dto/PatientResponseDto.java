package com.springboot.jpa.hospitalManagement.dto;

import com.springboot.jpa.hospitalManagement.entity.Insurance;
import com.springboot.jpa.hospitalManagement.entity.type.BloodGroupType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private String email;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
    private LocalDateTime createdAt;
}
