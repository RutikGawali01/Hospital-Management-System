package com.springboot.jpa.hospitalManagement.Repository;

import com.springboot.jpa.hospitalManagement.dto.AppointmentResponseDto;
import com.springboot.jpa.hospitalManagement.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment , Long> {


    //List<AppointmentResponseDto> findAllAppointments(Pageable pageable);

    @Query(value = "select * from appointment", nativeQuery = true)
    Page<Appointment> findAllAppointments(Pageable pageable);
}
