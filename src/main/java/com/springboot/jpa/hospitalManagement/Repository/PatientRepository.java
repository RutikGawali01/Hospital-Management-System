package com.springboot.jpa.hospitalManagement.Repository;

import com.springboot.jpa.hospitalManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient , Long> {


}
