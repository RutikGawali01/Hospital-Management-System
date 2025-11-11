package com.springboot.jpa.hospitalManagement.Repository;

import com.springboot.jpa.hospitalManagement.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital , Long > {

    Optional<Hospital> findByUsername(String username);

    Optional<Object> findByEmail(String email);
}
