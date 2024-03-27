package com.example.note.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    @NotNull()
    private int patId;
    private String patient;
    private String note;

    public Note(int patId, String patient, String note) {
        this.patId = patId;
        this.patient = patient;
        this.note = note;
    }
}
