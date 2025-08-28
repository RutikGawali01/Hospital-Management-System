package com.springboot.jpa.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(precision = 500)
    private String reason;

    @ManyToOne // owning side  -> Many appointment to one patient
    @JoinColumn(name = "patient_id" , nullable = false)// nullable = false  is bcz  Patient is required in every  appointment
    @ToString.Exclude
    private Patient patient;

    @ManyToOne // many appointments one doctor
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Doctor doctor;

}
