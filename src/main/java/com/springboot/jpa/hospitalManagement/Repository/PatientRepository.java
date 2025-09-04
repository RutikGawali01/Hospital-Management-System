package com.springboot.jpa.hospitalManagement.Repository;

import com.springboot.jpa.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.springboot.jpa.hospitalManagement.entity.Patient;
import com.springboot.jpa.hospitalManagement.entity.type.BloodGroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
@Component
public interface PatientRepository extends JpaRepository<Patient , Long> {

    // Query method
    Patient findByName(String name);

    List<Patient> findByBirthDateOrEmail(LocalDate birthDate , String email);

    List<Patient> findByBirthDateBetween(LocalDate startDate , LocalDate endDate);

    List<Patient> findByNameContaining(String query);
    //JPQL
    @Query("SELECT p FROM Patient p where p.bloodGroup = ?1")
    //List<Patient> findByBloodGroup( @Param("bloodGroup") String bloodGroup); // this will not run . throw error should be of Bloodgroup type
    List<Patient> findByBloodGroup( @Param("bloodGroup") BloodGroupType bloodGroup);
    //JPQL
    /*@Query("SELECT p FROM Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);*/

    //JPQL
     //@Query("select new com.springboot.jpa.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup ,"+ " Count(p)) from Patient p group by p.bloodGroup")
    //List<Object[]> countEachBloodGroupType(/*@Param("bloodGroup") BloodGroupType bloodGroupType)*/);
   // List<Object[]> countEachBloodGroupType();
    // List<BloodGroupCountResponseEntity> countEachBloodGroupType();


    //SQL
    @Query(value = "select * from patient",nativeQuery = true )
    List<Patient> findAllPatient();


    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name , @Param("id") Long id);



    // pagination inn spring data jpa
   /* @Query(value = "select * from patient",nativeQuery = true )
    Page<Patient> findAllPatient(Pageable pageable);*/

    //jpql
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    List<Patient> findAllPatientWithAppointment();

    /*@Query("SELECT DISTINCT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    List<Patient> findAllPatientWithAppointment();*/


    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

}
