package com.example.patient.controller;

import com.example.patient.model.Patient;
import com.example.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@Slf4j
public class PatientController {

    @Autowired
    PatientService service;

    @GetMapping()
    public List<Patient> findAllPatients() {

        List<Patient> patients = service.findAllPatient();

        if (patients != null) {
            return patients;
        } else {
            log.error("findAllPatient returned null");
            return Collections.emptyList();
        }

    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatient(@PathVariable("id") int id) {

        return service.findPatient(id);

    }

    @PostMapping("/add")
    public Patient addPatient(@RequestBody Patient patient) {

        return service.savePatient(patient);

    }

    @PostMapping("/{id}/update")
    public Patient updatePatient(@RequestBody Patient patient, @PathVariable("id") int id) {

        patient.setId(id);
        return service.updatePatient(patient);

    }

    @PostMapping("/{id}/delete")
    public void deletePatient(@PathVariable("id") int id) {

        service.deletePatient(id);

    }




}
