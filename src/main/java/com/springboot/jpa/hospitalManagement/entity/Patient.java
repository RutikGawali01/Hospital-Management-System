package com.springboot.jpa.hospitalManagement.entity;

import com.springboot.jpa.hospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@Table(
        /*name = "Patient_tbl"*/
        name = "patient",
        uniqueConstraints = {
                 //@UniqueConstraint(name = "unique_patient_email" , columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthDate" , columnNames = {"name","birthDate"})
        },
        indexes = {
                @Index(name = "idx_patient_birth_date" ,columnList = "birthDate")
        }
)//explore table in detaail like indexes() , uniqueConstraints and manny more

public class Patient {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    /*@Column(name = "patient_name", nullable = false , length = 30)
    private String name;*/ // this will create new column with patient_namae and original name column will not change

    @Column(nullable = false , length = 30)
    private String name;

    @ToString.Exclude
     private LocalDate birthDate;

    @Column(unique = true , nullable = false) // this is equivalent to this in table   @UniqueConstraint(name = "unique_patient_email" , columnNames = {"email"}),
    private String email;

    private String gender;


    @CreationTimestamp //
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /*private String bloodGroup;*/

    @Enumerated(EnumType.STRING)// only certain blood group is possible
    private BloodGroupType bloodGroup;// by this only valid bloof group is possible



}
