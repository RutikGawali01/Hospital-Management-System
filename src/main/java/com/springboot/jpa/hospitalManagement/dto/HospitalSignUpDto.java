package com.springboot.jpa.hospitalManagement.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalSignUpDto {

    private String username;
    private String name;
    private String email;
    private String password;
    private String licenseNo;
    private String ownerName;
}
