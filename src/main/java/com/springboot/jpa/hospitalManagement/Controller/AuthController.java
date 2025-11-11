package com.springboot.jpa.hospitalManagement.Controller;

import com.springboot.jpa.hospitalManagement.Security.AuthService;
import com.springboot.jpa.hospitalManagement.dto.HospitalResponseDto;
import com.springboot.jpa.hospitalManagement.dto.HospitalSignUpDto;
import com.springboot.jpa.hospitalManagement.dto.LoginRequestDto;
import com.springboot.jpa.hospitalManagement.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    // login api
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }


    // sign up api
    @PostMapping("/signup")
    public ResponseEntity<HospitalResponseDto> signup(@RequestBody HospitalSignUpDto hospitalSignUpDto){
        return ResponseEntity.ok(authService.signup(hospitalSignUpDto));
    }

}
