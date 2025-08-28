package com.springboot.jpa.hospitalManagement.Repository;

import com.springboot.jpa.hospitalManagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
