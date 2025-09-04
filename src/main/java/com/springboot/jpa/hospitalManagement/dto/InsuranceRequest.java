package com.springboot.jpa.hospitalManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class InsuranceRequest {

    private Long patientId;

    private String policyNumber;
    private String provider;

    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private LocalDate validUntil;
}
