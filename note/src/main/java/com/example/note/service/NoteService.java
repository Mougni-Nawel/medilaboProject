package com.example.note.service;

import com.example.note.entity.Note;
import com.example.note.entity.Symptoms;
import com.example.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    NoteRepository repository;

    public NoteService() {
    }


    public List<Note> getAllNote() {

        return repository.findAll();

    }

    public List<Note> getNotesByPatient(int id) {

        return repository.findByPatId(id);

    }

    public Note saveNote(Note note) {

        return repository.save(note);

    }

    public Note updateNote(Note note) {

        return repository.save(note);

    }

    public void deleteNote(int id) {

        repository.deleteById(id);

    }

    public List<String> getSymptomsFromNotesOfPatient(int id) {
        List<String> nbSymptoms = new ArrayList<>();

        for (Symptoms symptom : Symptoms.values()) {
            List<Note> notes = repository.findPatientAndSymptomFromNote(id, symptom.toString());
            if (!notes.isEmpty()) {
                nbSymptoms.add(symptom.toString());
            } else {
                String lowercaseSymptom = symptom.toString().toLowerCase();
                List<Note> lowercaseNotes = repository.findPatientAndSymptomFromNote(id, lowercaseSymptom);
                if (!lowercaseNotes.isEmpty()) {
                    nbSymptoms.add(lowercaseSymptom.toString());
                }
            }
        }

        return nbSymptoms;
    }

}
