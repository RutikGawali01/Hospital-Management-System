package com.springboot.jpa.hospitalManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateAppointmentRequest {

    private LocalDateTime appointmentTime;
    private String reason;
}
