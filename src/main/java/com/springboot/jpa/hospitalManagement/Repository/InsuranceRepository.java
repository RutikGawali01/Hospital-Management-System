package com.springboot.jpa.hospitalManagement.Repository;

import com.springboot.jpa.hospitalManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
}
