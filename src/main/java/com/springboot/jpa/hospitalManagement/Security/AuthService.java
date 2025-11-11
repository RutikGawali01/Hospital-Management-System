package com.springboot.jpa.hospitalManagement.Security;

import com.springboot.jpa.hospitalManagement.Repository.HospitalRepository;
import com.springboot.jpa.hospitalManagement.dto.HospitalResponseDto;
import com.springboot.jpa.hospitalManagement.dto.HospitalSignUpDto;
import com.springboot.jpa.hospitalManagement.dto.LoginRequestDto;
import com.springboot.jpa.hospitalManagement.dto.LoginResponseDto;
import com.springboot.jpa.hospitalManagement.entity.Hospital;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class    AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final HospitalRepository hospitalRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    //Purpose: Authenticate a user and return a JWT token with user info.
    public LoginResponseDto login(LoginRequestDto loginRequestDto){

        Hospital hospital = hospitalRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));

        if(!passwordEncoder.matches(loginRequestDto.getPassword() , hospital.getPassword())){
            throw new IllegalArgumentException("Invalid username or password");
        }

        //jwt token
        // for generate token Jwts dependencies are required
        //Generate JWT token
        String token = authUtil.generateAccessToken(hospital);

        return new LoginResponseDto(token , hospital.getId());
    }

    /*public Hospital signUpInternal(HospitalSignUpDto signupRequestDto, AuthProviderType authProviderType, String providerId) {
        Hospital user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if(user != null) throw new IllegalArgumentException("User already exists");

        user = Hospital.builder()
                .username(signupRequestDto.getUsername())
                .providerId(providerId)
                .providerType(authProviderType)
                .build();

        if(authProviderType == AuthProviderType.EMAIL) {
            user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        }

        return userRepository.save(user);
    }

    // sign up
    public HospitalResponseDto signup(HospitalSignUpDto hospitalSignUpDto) {
        Hospital hospital = signUpInternal(hospitalSignUpDto, AuthProviderType.EMAIL, null);
        return new HospitalResponseDto(hospital.getId(), hospital.getUsername());
    }*/


    // create new user and store it in databsse
    public HospitalResponseDto signup(HospitalSignUpDto  hospitalSignUpDto) {
        Hospital hospital = hospitalRepository.findByUsername(hospitalSignUpDto.getUsername()).orElse(null);

        // to check if the hospital is already present or  not
        // generally hospital should not present before
        if(hospital != null){
            throw new IllegalArgumentException("hospital already exists");
        }
        if (hospitalRepository.findByEmail(hospitalSignUpDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }


        // to crate new hospital if it is not exist
        hospital = modelMapper.map(hospitalSignUpDto , Hospital.class);
        //set password
        hospital.setPassword(passwordEncoder.encode(hospitalSignUpDto.getPassword()));

        hospital = hospitalRepository.save(hospital);

        // below return statement can be done by using model mapper
        /*return new HospitalResponseDto(hospital.getId() , hospital.getUsername());*/
        return modelMapper.map(hospital , HospitalResponseDto.class);
    }

    // for handleOAuth2LoginRequest method
    // fetch providerType and providerId
    // save the providerType and Provider id info with user
    //is the user have an acoountt -- directly login
    // otherwise first signup

    /*@Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdfromOAuth2User(oAuth2User, registrationId);

        Hospital user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);
        String email = oAuth2User.getAttribute("email");

        Hospital emailUser = userRepository.findByUsername(email).orElse(null);

        if(user == null && emailUser == null) {
            // signup flow:
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            user = signUpInternal(new LoginRequestDto(username, null), providerType, providerId);
        } else if(user != null) {
            if(email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered with provider "+emailUser.getProviderType());
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }*/
}
