package com.example.note.repository;

import com.example.note.entity.Note;
import com.example.note.entity.Symptoms;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, Integer> {

    List<Note> findByPatId(final int patId);

    @Query(value = "{'patId': ?0, 'note': {$regex: ?1}}", fields = "{patId:  1, patient:  1, note:  1}")
    List<Note> findPatientAndSymptomFromNote(int patId, String symptom);

}
