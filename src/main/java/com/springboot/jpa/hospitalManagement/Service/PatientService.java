package com.springboot.jpa.hospitalManagement.Service;

import com.springboot.jpa.hospitalManagement.Repository.PatientRepository;
import com.springboot.jpa.hospitalManagement.dto.AppointmentResponseDto;
import com.springboot.jpa.hospitalManagement.dto.InsuranceResponse;
import com.springboot.jpa.hospitalManagement.dto.PatientRequestdto;
import com.springboot.jpa.hospitalManagement.dto.PatientResponseDto;
import com.springboot.jpa.hospitalManagement.entity.Appointment;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
/*import org.springframework.data.domain.PageRequest;*/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {


    private final PatientRepository patientRepository;

    private final ModelMapper modelMapper;


  /*  @Transactional
    public Patient getPatientByUd(Long id){
        Patient p1 =  patientRepository.findById(id).orElseThrow();


        Patient p2 = patientRepository.findById(id).orElseThrow();


        System.out.println(p1 == p2);

        p1.setName("yoyo");

        return p1;
    }*/


    @Transactional
    public PatientResponseDto getPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient Not " +
                "Found with id: " + patientId));
        return modelMapper.map(patient, PatientResponseDto.class);
    }

    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return patientRepository.findAllPatients(pageable)  //Calls the repository to fetch patients with pagination (based on page number & size). and  return patients with metadata
                .getContent()      //Extracts only the list of Patient entities from that Page object (ignores metadata for now).
                .stream()   //Converts that list into a Java Stream so we can process each patient one by one.
                .map(patient -> modelMapper.map(patient, PatientResponseDto.class))     //For every Patient entity, it converts (maps) it into a PatientResponseDto.
                .collect(Collectors.toList());  //Collects all those mapped DTOs into a new List<PatientResponseDto>.
    }

    /*this is useful for small dataset
    public List<PatientResponseDto> getAllPatients(){
        return patientRepository.findAll()
                .stream()
                .map(patient -> modelMapper.map(patient , PatientResponseDto.class))
                .toList();
    }
*/

    public PatientResponseDto registerNewPatient(PatientRequestdto patientRequestdto) {
        Patient patient = modelMapper.map(patientRequestdto, Patient.class);

        patient.setCreatedAt(LocalDateTime.now());
        Patient savedpatient = patientRepository.save(patient);

        return modelMapper.map(savedpatient , PatientResponseDto.class);

    }

    public PatientResponseDto updatePatient(long id, PatientRequestdto patientRequestdto) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Patient not exist with id" + id));

        modelMapper.map(patientRequestdto , patient);// copies matching fields from patientRequest → patient.

        patient = patientRepository.save(patient);

        return modelMapper.map(patient , PatientResponseDto.class);

    }

    public void deletePatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("patient not found with id" + id));
        patientRepository.delete(patient);
        return;
    }

    public List<AppointmentResponseDto> getAllAppointments(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("patient not exist with id " + id));

        List<Appointment> appointments= patient.getAppointments();
        return appointments.stream()
                .map(appointment ->  modelMapper.map(appointment , AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }

    public InsuranceResponse getPatientsInsurance(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("patient not found with id " + id));

        return modelMapper.map(patient.getInsurance() , InsuranceResponse.class);

    }
}
