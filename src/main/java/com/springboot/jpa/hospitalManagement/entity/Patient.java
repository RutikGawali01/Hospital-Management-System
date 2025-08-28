package com.springboot.jpa.hospitalManagement.entity;

import com.springboot.jpa.hospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Table(
        /*name = "Patient_tbl"*/
        name = "patient"
        /*uniqueConstraints = {
                 //@UniqueConstraint(name = "unique_patient_email" , columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthDate" , columnNames = {"name","birth_Date"})
        }*//*,
        indexes = {
                @Index(name = "idx_patient_birth_date" ,columnList = "birthDate")
        }*/
)//explore table in detaail like indexes() , uniqueConstraints and manny more

public class Patient {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    /*@Column(name = "patient_name", nullable = false , length = 30)
    private String name;*/ // this will create new column with patient_namae and original name column will not change

   /* @Column(nullable = false , length = 30)*/
    private String name;

   /* @ToString.Exclude*/
   @Column(name = "birth_date")
     private LocalDate birthDate;

/*@Column(unique = true , nullable = false) // this is equivalent to this in table   @UniqueConstraint(name = "unique_patient_email" , columnNames = {"email"}),*/
    private String email;

    private String gender;


    @CreationTimestamp //
    @Column(updatable = false)//creation time  can not change
    private LocalDateTime createdAt;

    /*private String bloodGroup;*/

    @Enumerated(EnumType.STRING)// only certain blood group is possible
    private BloodGroupType bloodGroup;// by this only valid bloof group is possible

    //@OneToOne(cascade = {CascadeType.MERGE , CascadeType.PERSIST})// owning side
    /*@ToString.Exclude*/
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id")// this will not create automatic column name creation (<fieldName>_id)
    private Insurance insurance;


    // inverse side
    @OneToMany(mappedBy = "patient" , cascade = {CascadeType.REMOVE} , orphanRemoval = true  , fetch =  FetchType.EAGER) // one patient to many appointment
    /*@ToString.Exclude*/   // this is bcz it will throw error if i want to print patient with its insurance
    private List<Appointment> appointments = new ArrayList<>(); // list id bcz there can be multiple appointmnet for single patient
}
