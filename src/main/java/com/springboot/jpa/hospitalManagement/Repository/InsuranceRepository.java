package com.springboot.jpa.hospitalManagement.Repository;

import com.springboot.jpa.hospitalManagement.dto.InsuranceResponse;
import com.springboot.jpa.hospitalManagement.entity.Insurance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
    @Query(value = "select * from insurance" , nativeQuery = true)
    Page<Insurance> getAllInsurance(Pageable page);
}
