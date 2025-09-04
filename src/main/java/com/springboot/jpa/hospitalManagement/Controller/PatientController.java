package com.springboot.jpa.hospitalManagement.Controller;

import com.springboot.jpa.hospitalManagement.Service.AppointmentService;
import com.springboot.jpa.hospitalManagement.Service.PatientService;
import com.springboot.jpa.hospitalManagement.dto.*;
import com.springboot.jpa.hospitalManagement.entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;


    // register new patient
    @PostMapping("/register")
    private ResponseEntity<PatientResponseDto> registerNewPatient(@RequestBody PatientRequestdto patientRequestdto){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.registerNewPatient(patientRequestdto));
    }

    //get patient by id
    @GetMapping("/profile/{id}")
    private ResponseEntity<PatientResponseDto> getPatientProfile(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    //update patient details by id
    @PutMapping("/update/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable long id , @RequestBody PatientRequestdto patientRequestdto){
        return ResponseEntity.ok(patientService.updatePatient(id , patientRequestdto));
    }

    // delete patient
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long id){
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }

    //Get all appointments for a patient
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getAllAppointments(id));
    }

    //Get patient’s insurance details
    @GetMapping("/{id}/insurance")
    public ResponseEntity<InsuranceResponse> getPatientsInsurance(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientsInsurance(id));
    }




}
