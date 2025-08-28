package com.springboot.jpa.hospitalManagement.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String PolicyNumber;

    @Column(nullable = false , length = 100)
    private String Provider;

    @Column(nullable = false)
    private LocalDate ValidUntil;

    @CreationTimestamp
    @Column(nullable = false , updatable = false)
    private LocalDateTime CreatedAt;

    @OneToOne(mappedBy = "insurance")// inverse side
    private Patient patient;

}
