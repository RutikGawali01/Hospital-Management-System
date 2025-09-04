package com.springboot.jpa.hospitalManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
public class InsuranceResponse {

    private Long patientId;
    private String policyNumber;

    private String provider;

    private LocalDate validUntil;
    private LocalDate createdAt;

}
