package com.example.patient.service;

import com.example.patient.model.Gender;
import com.example.patient.model.Patient;
import com.example.patient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = PatientService.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @MockBean
    private PatientRepository repository;

    @InjectMocks
    PatientService service;

    @Test
    public void testFindAllPatient() throws ParseException {

        // Arrange
        List<Patient> expectedPatients = Arrays.asList(
                new Patient(1, "John", "Doe", "02/02/1982", Gender.M, "Address", "1234567890"),
                new Patient(2, "Jane", "Doe", "01/01/1981", Gender.F, "Address", "0987654321")
        );
        when(repository.findAll()).thenReturn(expectedPatients);

        // Act
        List<Patient> actualPatients = service.findAllPatient();

        // Assert
        assertEquals(expectedPatients, actualPatients);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testFindPatient() throws ParseException {
        // Arrange
        int patientId = 1;
        Patient expectedPatient = new Patient(1, "John", "Doe", "02/02/1982", Gender.M, "Address", "1234567890");
        when(repository.findById(patientId)).thenReturn(Optional.of(expectedPatient));

        // Act
        Optional<Patient> actualPatient = service.findPatient(patientId);

        // Assert
        assertEquals(Optional.of(expectedPatient), actualPatient);
        verify(repository, times(1)).findById(patientId);
    }

    @Test
    public void testSavePatient() throws ParseException {
        // Arrange
        Patient patientToSave = new Patient(3, "Jerry", "Doe", "02/02/1988", Gender.M, "Address", "123456000");
        when(repository.save(patientToSave)).thenReturn(patientToSave);

        // Act
        Patient savedPatient = service.savePatient(patientToSave);

        // Assert
        assertEquals(patientToSave, savedPatient);
        verify(repository, times(1)).save(patientToSave);
    }

    @Test
    public void testUpdatePatient() throws ParseException {
        // Arrange
        Patient patientToUpdate = new Patient(1, "John", "Doe", "02/02/1982", Gender.M, "Address 2", "1234567890");
        when(repository.save(patientToUpdate)).thenReturn(patientToUpdate);

        // Act
        Patient updatedPatient = service.updatePatient(patientToUpdate);

        // Assert
        assertEquals(patientToUpdate, updatedPatient);
        verify(repository, times(1)).save(patientToUpdate);
    }

    @Test
    public void testDeletePatient() throws ParseException {
        // Arrange
        int patientId = 1;
        Patient patientToDelete = new Patient(3, "Jerry", "Doe", "02/02/1988", Gender.M, "Address", "123456000");
        when(repository.findById(patientId)).thenReturn(Optional.of(patientToDelete));

        // Act
        service.deletePatient(patientId);

        // Assert
        verify(repository, times(1)).delete(patientToDelete);
    }
}
