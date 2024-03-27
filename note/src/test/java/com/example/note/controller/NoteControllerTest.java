package com.example.note.controller;

import com.example.note.entity.Note;
import com.example.note.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest(classes = NoteController.class)
@Slf4j
@WithMockUser
@ExtendWith(MockitoExtension.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService service;

    @InjectMocks
    private NoteController noteController;

    @Test
    public void testFindAllNotes() {
        // Arrange
        List<Note> expectedNotes = Arrays.asList(
                new Note(1, "John", "Note 1"),
                new Note(2, "Jane", "Note 2")
        );
        when(service.getAllNote()).thenReturn(expectedNotes);

        // Act
        List<Note> actualNotes = noteController.findAllNotes();

        // Assert
        Assertions.assertEquals(expectedNotes, actualNotes);
    }

    @Test
    public void testGetPatient() {
        // Arrange
        int patientId = 1;
        List<Note> expectedNotes = Arrays.asList(
                new Note(patientId, "John", "Note 1"),
                new Note(patientId, "Jane", "Note 2")
        );
        when(service.getNotesByPatient(patientId)).thenReturn(expectedNotes);

        // Act
        List<Note> actualNotes = noteController.getPatient(patientId);

        // Assert
        assertEquals(expectedNotes, actualNotes);
    }

    @Test
    public void testAddNote() {
        // Arrange
        Note noteToAdd = new Note(1, "John", "New note");
        when(service.saveNote(noteToAdd)).thenReturn(noteToAdd);

        // Act
        Note addedNote = noteController.addNote(noteToAdd);

        // Assert
        assertEquals(noteToAdd, addedNote);
    }

    @Test
    public void testUpdateNote() {
        // Arrange
        int noteId = 1;
        Note noteToUpdate = new Note(noteId, "John", "Updated note");
        when(service.updateNote(noteToUpdate)).thenReturn(noteToUpdate);

        // Act
        Note updatedNote = noteController.updateNote(noteToUpdate, noteId);

        // Assert
        assertEquals(noteToUpdate, updatedNote);
    }

    @Test
    public void testDeleteNote() {
        // Arrange
        int noteId = 1;

        // Act
        noteController.deleteNote(noteId);

        // Assert
        verify(service, times(1)).deleteNote(noteId);
    }

    @Test
    public void testGetSymptomsFromNotesOfPatient() {
        // Arrange
        int patientId = 1;
        Collection<String> expectedSymptoms = Arrays.asList("Fever", "Cough");
        when(service.getSymptomsFromNotesOfPatient(patientId)).thenReturn((List<String>) expectedSymptoms);

        // Act
        Collection<String> actualSymptoms = noteController.getSymptomsFromNotesOfPatient(patientId);

        // Assert
        assertEquals(expectedSymptoms, actualSymptoms);
    }

}
