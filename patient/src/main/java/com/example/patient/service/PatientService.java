package com.example.patient.service;

import com.example.patient.model.Patient;
import com.example.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientService {

    @Autowired
    PatientRepository repository;

    public List<Patient> findAllPatient(){

        List<Patient> patients = repository.findAll();
        log.info("Patients from repository: {}", patients);
        return patients;

    }

    public Optional<Patient> findPatient(int id) {

        if(id != 0){
            return repository.findById(id);
        }

        return Optional.empty();
    }

    public Patient savePatient(Patient patient){

        return repository.save(patient);

    }

    public Patient updatePatient(Patient patient){

        return repository.save(patient);

    }

    public void deletePatient(int id){

        Optional<Patient> patientToDelete = this.findPatient(id);

        repository.delete(patientToDelete.get());


    }


}
