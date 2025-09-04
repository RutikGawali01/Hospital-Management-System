package com.springboot.jpa.hospitalManagement.Controller;

import com.springboot.jpa.hospitalManagement.Service.InsuranceService;
import com.springboot.jpa.hospitalManagement.dto.InsuranceRequest;
import com.springboot.jpa.hospitalManagement.dto.InsuranceResponse;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    //Add insurance policy
    @PostMapping("/insurance")
    public ResponseEntity<InsuranceResponse> assignNewInsurrance(@RequestBody InsuranceRequest insuranceRequest){
        return ResponseEntity.ok(insuranceService.assigninnsuranceToPatient(insuranceRequest));
    }

    //get all insurance
    @GetMapping("/All/insurance")
    public ResponseEntity<List<InsuranceResponse>> getAllInsurance(
            @RequestParam (value = "page",defaultValue = "0")Integer pageNumber ,
            @RequestParam(value = "size",defaultValue = "10") Integer pageSize
    ){
        return ResponseEntity.ok(insuranceService.getAllInsurance(pageNumber ,  pageSize));
    }

}
