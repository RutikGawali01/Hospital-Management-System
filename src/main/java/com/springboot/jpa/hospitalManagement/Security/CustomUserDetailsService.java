package com.springboot.jpa.hospitalManagement.Security;

import com.springboot.jpa.hospitalManagement.Repository.HospitalRepository;
import com.springboot.jpa.hospitalManagement.entity.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final HospitalRepository hospitalRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return hospitalRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Hospital not found: " + username));
    }
}
