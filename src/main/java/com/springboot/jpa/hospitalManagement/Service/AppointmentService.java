package com.springboot.jpa.hospitalManagement.Service;

import com.springboot.jpa.hospitalManagement.Repository.AppointmentRepository;
import com.springboot.jpa.hospitalManagement.Repository.DoctorRepository;
import com.springboot.jpa.hospitalManagement.Repository.PatientRepository;
import com.springboot.jpa.hospitalManagement.dto.AppointmentResponseDto;
import com.springboot.jpa.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.springboot.jpa.hospitalManagement.dto.UpdateAppointmentRequest;
import com.springboot.jpa.hospitalManagement.entity.Appointment;
import com.springboot.jpa.hospitalManagement.entity.Doctor;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    /*@Transactional
    public Appointment createNewAppointment(Appointment appointment , long DoctorId , Long PatientId){
        Doctor doctor = doctorRepository.findById(DoctorId).orElseThrow();
        Patient patient = patientRepository.findById(PatientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have id ");

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        patient.getAppointments().add(appointment);     // to maintain bi-directional consistency.

        return appointmentRepository.save(appointment);
    }
*/

    @Transactional
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto) {
        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorId));

        Appointment appointment = Appointment.builder()
                .reason(createAppointmentRequestDto.getReason())
                .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
                .build();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        patient.getAppointments().add(appointment); // to maintain consistency

        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId , Long doctorId){

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); // this will automatically call the update ,bcz it is dirty

        doctor.getAppointments().add(appointment); // just for bidirectional counsistency

        return appointment;
    }


    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("doctor not found with id" + id));

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }


    public List<AppointmentResponseDto> getAllAppointments(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber , pageSize);
         return appointmentRepository.findAllAppointments(pageable)
                 .getContent()
                 .stream()
                 .map(appointment -> modelMapper.map(appointment , AppointmentResponseDto.class))
                 .collect(Collectors.toList());
    }

    public AppointmentResponseDto updateAppointment(Long id, UpdateAppointmentRequest updateAppointmentRequest) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow( ()->
                new EntityNotFoundException("appointment not exist with id" + id) );

        modelMapper.map(updateAppointmentRequest , appointment );

        appointment = appointmentRepository.save(appointment);

        return modelMapper.map(appointment , AppointmentResponseDto.class);

    }
}
