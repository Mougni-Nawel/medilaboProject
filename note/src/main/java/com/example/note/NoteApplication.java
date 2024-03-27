package com.example.note;

import com.example.note.entity.Note;
import com.example.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class NoteApplication {


	public static void main(String[] args) {
		SpringApplication.run(NoteApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(NoteService noteService) {
		return args -> {
			// Inserting notes
			noteService.insertNotes();
		};
	}


}
