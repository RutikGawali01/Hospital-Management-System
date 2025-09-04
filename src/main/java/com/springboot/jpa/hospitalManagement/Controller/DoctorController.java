package com.springboot.jpa.hospitalManagement.Controller;

import com.springboot.jpa.hospitalManagement.Service.AppointmentService;
import com.springboot.jpa.hospitalManagement.Service.DoctorService;
import com.springboot.jpa.hospitalManagement.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    // Register a new doctor
    @PostMapping("/register")
    public ResponseEntity<DoctorResponseDto> registerNewDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.registerNewDoctor(doctorRequestDto));
    }


    //to find all the appointments 0f a paritcular doctor with its id
    @GetMapping("/appointments/{id}")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(id));
    }

    //Get Doctor by Id
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    //Update doctor details by id
    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(@PathVariable Long id , @RequestBody PatientRequestdto patientRequestdto){
        return ResponseEntity.ok(doctorService.updateDoctor(id , patientRequestdto));
    }


    //Get departments a doctor belongs to
    @GetMapping("/{id}/departments")
    public ResponseEntity<List<DepartmentResponseDto>> getDepartmentsOfDoctor(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDepartmentsOfDoctor(id));
    }

}