package com.springboot.jpa.hospitalManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HospitalResponseDto {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String licenseNo;
    private String ownerName;
}
