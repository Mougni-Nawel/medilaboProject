package com.example.note.config;

import com.example.note.entity.Note;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
@Slf4j
public class MongoInitializer {

    @Autowired
    private MongoClient mongoClient;

    private final MongoTemplate mongoTemplate;


    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    public MongoInitializer(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void initialize() {
        // Check if data already exists
        if (!dataExists()) {
            // Insert initial data
            insertNotes();
            System.out.println("Initial data inserted into MongoDB");
        } else {
            System.out.println("Data already exists in MongoDB");
        }
    }

    private boolean dataExists() {
        // Check if any data exists in the collection
        return mongoTemplate.count(new Query(), Note.class) > 0;
    }

    public void insertNotes() {
        // Inserting notes
        mongoTemplate.save(new Note(1, "TestNone", "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"));
        mongoTemplate.save(new Note(2, "TestBorderline", "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement"));
        mongoTemplate.save(new Note(2, "TestBorderline", "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale"));
        mongoTemplate.save(new Note(3, "TestInDanger", "Le patient déclare qu'il fume depuis peu"));
        mongoTemplate.save(new Note(3, "TestInDanger", "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));
        mongoTemplate.save(new Note(4, "TestEarlyOnset", "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments"));
        mongoTemplate.save(new Note(4, "TestEarlyOnset", "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"));
        mongoTemplate.save(new Note(4, "TestEarlyOnset", "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé"));
        mongoTemplate.save(new Note(4, "TestEarlyOnset", "Taille, Poids, Cholestérol, Vertige et Réaction"));
    }
}
