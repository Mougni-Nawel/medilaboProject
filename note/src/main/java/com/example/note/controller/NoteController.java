package com.example.note.controller;

import com.example.note.entity.Note;
import com.example.note.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
@Slf4j
public class NoteController {

    @Autowired
    private NoteService service;


    @GetMapping()
    public List<Note> findAllNotes() {

        List<Note> notes = service.getAllNote();

        if (notes != null) {
            return notes;
        } else {
            log.error("findAllNotes returned null");
            return Collections.emptyList();
        }

    }
    @GetMapping("/{id}")
    public List<Note> getPatient(@PathVariable("id") int id) {

        return service.getNotesByPatient(id);

    }

    @PostMapping("/add")
    public Note addNote(@RequestBody Note note) {

        return service.saveNote(note);

    }

    @PostMapping("/{id}/update")
    public Note updateNote(@RequestBody Note note, @PathVariable("id") int id) {

        note.setPatId(id);
        return service.updateNote(note);

    }

    @PostMapping("/{id}/delete")
    public void deleteNote(@PathVariable("id") int id) {

        service.deleteNote(id);

    }

    @GetMapping("/{id}/symptoms")
    public Collection<String> getSymptomsFromNotesOfPatient(@PathVariable("id") int id) {

        return service.getSymptomsFromNotesOfPatient(id);

    }



}
