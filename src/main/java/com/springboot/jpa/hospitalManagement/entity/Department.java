package com.springboot.jpa.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @OneToOne
    private Doctor headDoctor;

    private String location;

    private String email;

    @Column(nullable = false)
    private int phone;

    private int budget;

    private String description;

    @ManyToMany// this will create another joint table with fk , and pk
    @JoinTable(
            name = "my_dept_doctors",
            joinColumns = @JoinColumn(name = "dept_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctors =new HashSet<>();
}
