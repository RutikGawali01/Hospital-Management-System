package com.springboot.jpa.hospitalManagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponseDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private String status;
    private Long patientid;
    private Long doctorid;
//  private PatientResponseDto patient;

}
