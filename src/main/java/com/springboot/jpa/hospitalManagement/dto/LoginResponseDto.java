package com.springboot.jpa.hospitalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    String jwt;//token
    Long userId;
}
// this is for patient add for doctor and hospitals