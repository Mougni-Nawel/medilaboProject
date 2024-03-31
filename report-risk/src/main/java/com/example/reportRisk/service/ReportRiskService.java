package com.example.reportRisk.service;

import com.example.reportRisk.bean.PatientBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class ReportRiskService {

    @Autowired
    NoteProxyService noteProxyService;

    @Autowired
    PatientProxyService patientProxyService;

    public int getAge(Date birthdate) {
        if (birthdate == null) {
            return 0;
        }
        LocalDate today = LocalDate.now();
        LocalDate birthLocalDate = new java.sql.Date(birthdate.getTime()).toLocalDate();
        return Period.between(birthLocalDate, today).getYears();
    }

    public String determineRisks(int id) {
        Optional<PatientBean> patientOptional = patientProxyService.fetchPatientFromId(id);

        if (patientOptional.isPresent()) {
            PatientBean patientBean = patientOptional.get();

            String gender = patientBean.getGender();

            Collection<String> symptomsOfPatient = noteProxyService.fetchSymptomsFromNote(id);

            int age = getAge(patientBean.getBirthdate());

            // None
            if ((symptomsOfPatient.isEmpty()) || (symptomsOfPatient.size() < 2)) {
                return "None";
            }

            // Borderline
            if ((symptomsOfPatient.size() >= 2) && (symptomsOfPatient.size() <= 5) && (age >= 30)) {
                return "Borderline";
            }
            // In Danger
            if (("M".equals(gender) && (age < 30) && (symptomsOfPatient.size() == 3)) ||
                    ("F".equals(gender) && (age < 30) && (symptomsOfPatient.size() == 4)) ||
                    ((age >= 30) && ((symptomsOfPatient.size() >= 6) && (symptomsOfPatient.size() <= 7)))) {
                return "In Danger";
            }
            // Early Onset
            if (("M".equals(gender) && (age < 30) && (symptomsOfPatient.size() >= 5)) ||
                    ("F".equals(gender) && (age < 30) && (symptomsOfPatient.size() >= 7)) ||
                    ((age >= 30) && (symptomsOfPatient.size() >= 8))) {
                return "Early onset";
            }

        } else {
            throw new NoSuchElementException("The patient with ID " + id + " was not found.");
        }

        return "None";

    }





}
