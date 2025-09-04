package com.springboot.jpa.hospitalManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.jpa.hospitalManagement.entity.type.BloodGroupType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientRequestdto {
    private String name;
    private String gender;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")// We use @JsonFormat to tell Jackson how to convert between JSON and Java object
    // by using above line string in converted into localdate
    private LocalDate birthDate;

    private BloodGroupType bloodGroup;
}



