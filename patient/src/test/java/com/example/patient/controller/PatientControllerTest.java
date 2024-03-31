package com.example.patient.controller;

import com.example.patient.model.Gender;
import com.example.patient.model.Patient;
import com.example.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest(classes = PatientController.class)
@Slf4j
@WithMockUser
@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService service;

    @InjectMocks
    private PatientController patientController;

    @Test
    public void testFindAllPatientsNotEmpty() throws ParseException {
        // Arrange
        List<Patient> expectedPatients = List.of(
                new Patient(1, "John", "Doe", "02/02/1982", Gender.M, "Address", "1234567890"),
                new Patient(2, "Jane", "Doe", "01/01/1981", Gender.F, "Address", "0987654321")
        );
        when(service.findAllPatient()).thenReturn(expectedPatients);

        // Act
        List<Patient> actualPatients = patientController.findAllPatients();

        // Assert
        assertEquals(expectedPatients, actualPatients);
        verify(service, times(1)).findAllPatient();
    }

    @Test
    public void testGetPatient() throws ParseException {
        // Arrange
        int patientId = 1;
        Patient expectedPatient = new Patient(1, "John", "Doe", "02/02/1982", Gender.M, "Address", "1234567890");

        when(service.findPatient(patientId)).thenReturn(Optional.of(expectedPatient));

        // Act
        Optional<Patient> response = patientController.getPatient(patientId);

        assertEquals(Optional.of(expectedPatient), response);
        verify(service, times(1)).findPatient(patientId);
    }


    @Test
    public void testAddPatient() throws ParseException {
        // Arrange
        Patient patientToAdd = new Patient(1, "John", "Doe", "02/02/1982", Gender.M, "Address", "1234567890");
        when(service.savePatient(patientToAdd)).thenReturn(patientToAdd);

        // Act
        Patient addedPatient = patientController.addPatient(patientToAdd);

        // Assert
        assertEquals(patientToAdd, addedPatient);
        verify(service, times(1)).savePatient(patientToAdd);
    }

    @Test
    public void testUpdatePatient() throws ParseException {
        // Arrange
        int patientId = 1;
        Patient patientToUpdate = new Patient(1, "John", "Doe", "02/02/1982", Gender.M, "Address", "1234567890");
        patientToUpdate.setId(patientId);
        when(service.updatePatient(patientToUpdate)).thenReturn(patientToUpdate);

        // Act
        Patient updatedPatient = patientController.updatePatient(patientToUpdate, patientId);

        // Assert
        assertEquals(patientToUpdate, updatedPatient);
        verify(service, times(1)).updatePatient(patientToUpdate);
    }


    @Test
    public void testDeletePatient() {
        // Arrange
        int patientId = 1;

        // Act
        patientController.deletePatient(patientId);

        // Assert
        verify(service, times(1)).deletePatient(patientId);
    }

}
