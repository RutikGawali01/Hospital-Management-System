package com.springboot.jpa.hospitalManagement.Controller;

import com.springboot.jpa.hospitalManagement.Service.DepartmentService;
import com.springboot.jpa.hospitalManagement.dto.DepartmentCreateRequestDto;
import com.springboot.jpa.hospitalManagement.dto.DepartmentResponseDto;
import com.springboot.jpa.hospitalManagement.dto.DoctorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hospital")
public class DepartmentController {

    private final DepartmentService departmentService;

    // Add a department
    @PostMapping("/department")
    public ResponseEntity<DepartmentResponseDto>  addDepartment(
            @RequestBody DepartmentCreateRequestDto departmentCreateRequestDto)
    {
        return ResponseEntity.ok(departmentService.addDepartment(departmentCreateRequestDto));
    }

    // get all departments
    @GetMapping("/all/departments")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments(
            @RequestParam(value = "page" , defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size" , defaultValue = "10") Integer pagesize
    ){
        return ResponseEntity.ok(departmentService.getAllDepartments(pageNumber , pagesize));
    }

    // Get  all  doctors in a department
    @GetMapping("departments/{id}/doctors")
    public ResponseEntity<List<DoctorResponseDto>> getDoctorsFromDepartments(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.getDoctorsFromDepartments(id));
    }
}
