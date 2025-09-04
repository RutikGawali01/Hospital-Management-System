package com.springboot.jpa.hospitalManagement.Controller;

import com.springboot.jpa.hospitalManagement.Service.AppointmentService;
import com.springboot.jpa.hospitalManagement.Service.PatientService;
import com.springboot.jpa.hospitalManagement.dto.AppointmentResponseDto;
import com.springboot.jpa.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.springboot.jpa.hospitalManagement.dto.UpdateAppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class AppointmentController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    //Book an appointment
    // to create new appointment
    @PostMapping("/appointment")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    // get all the Appointments
    @GetMapping("/All/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize)
    {
        return ResponseEntity.ok(appointmentService.getAllAppointments(pageNumber , pageSize));
    }

    // Update appointment (time, reason, status) -> id = appointment id
    @PutMapping("/appointments/{id}/update")
    public ResponseEntity<AppointmentResponseDto> updateAppointments(@PathVariable Long id , @RequestBody UpdateAppointmentRequest updateAppointmentRequest){
        return ResponseEntity.ok(appointmentService.updateAppointment(id,updateAppointmentRequest));
    }

}
