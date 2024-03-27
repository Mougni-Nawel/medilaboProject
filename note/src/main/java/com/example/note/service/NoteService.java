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

    private final MongoTemplate mongoTemplate;

    public void insertNotes() {
        // Inserting notes
        mongoTemplate.insert(new Note(1, "TestNone", "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"));
        mongoTemplate.insert(new Note(2, "TestBorderline", "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement"));
        mongoTemplate.insert(new Note(2, "TestBorderline", "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale"));
        mongoTemplate.insert(new Note(3, "TestInDanger", "Le patient déclare qu'il fume depuis peu"));
        mongoTemplate.insert(new Note(3, "TestInDanger", "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));
        mongoTemplate.insert(new Note(4, "TestEarlyOnset", "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments"));
        mongoTemplate.insert(new Note(4, "TestEarlyOnset", "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"));
        mongoTemplate.insert(new Note(4, "TestEarlyOnset", "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé"));
        mongoTemplate.insert(new Note(4, "TestEarlyOnset", "Taille, Poids, Cholestérol, Vertige et Réaction"));
    }

    @Autowired
    public NoteService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
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
            List<Note> notes = repository.findPatientAndSymptomFromNote(id, symptom);
            if (!notes.isEmpty()) {
                nbSymptoms.add(symptom.toString());
            }
        }

        return nbSymptoms;
    }

}
