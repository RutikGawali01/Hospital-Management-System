package com.springboot.jpa.hospitalManagement.dto;

import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@RequiredArgsConstructor
public class DoctorForDepartment {
    private Long id;
    private String name;
    private String specialization;
    private String email;
}
