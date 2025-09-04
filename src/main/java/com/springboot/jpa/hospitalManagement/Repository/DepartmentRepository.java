package com.springboot.jpa.hospitalManagement.Repository;

import com.springboot.jpa.hospitalManagement.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Query(value = "select * from department" , nativeQuery = true)
    Page<Department> getAllDepartments(Pageable pageable);
}
