package com.springboot.jpa.hospitalManagement;

import com.springboot.jpa.hospitalManagement.Service.AppointmentService;
import com.springboot.jpa.hospitalManagement.entity.Appointment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentService;


    //@Test
    /*public void testCreateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,5,12,0,0))
                .reason("cancer")
                .build();

        var newAppointment = appointmentService.createNewAppointment(appointment,2L,3L);
        System.out.println(newAppointment);

        var updateAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 1L);

        System.out.println(updateAppointment);
    }*/


}
