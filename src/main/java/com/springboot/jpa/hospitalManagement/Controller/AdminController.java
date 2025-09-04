package com.springboot.jpa.hospitalManagement.Controller;

import com.springboot.jpa.hospitalManagement.Service.PatientService;
import com.springboot.jpa.hospitalManagement.dto.PatientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    /*@Autowired*/
    private final PatientService patientService;

    //private final PatientResponseDto patientResponseDto;

    // to get all the patients presents in the hospital
    // can be valid for large database
    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(patientService.getAllPatients(pageNumber, pageSize));
    }


    // this methos is not suggested for large scale
    // only for small dataset
   /* @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }*/
}