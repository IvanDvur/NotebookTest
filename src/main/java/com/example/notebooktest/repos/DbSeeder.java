package com.example.notebooktest.repos;

import com.example.notebooktest.model.Note;
import com.example.notebooktest.model.Notebook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "notebookTest.db.create.default", havingValue = "true")
public class DbSeeder implements CommandLineRunner {

    private NotebookRepository notebookRepository;
    private NoteRepository noteRepository;

    public DbSeeder(NoteRepository noteRepository, NotebookRepository notebookRepository){
        this.notebookRepository = notebookRepository;
        this.noteRepository = noteRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        this.noteRepository.deleteAll();
        this.noteRepository.deleteAll();

//        Creating a default notebook
        Notebook defaultNotebook = new Notebook("Click Me!");
        this.notebookRepository.save(defaultNotebook);
        var welcomeNote = new Note("Hello","Welcome to NotebookTest",defaultNotebook);
        this.noteRepository.save(welcomeNote);

    }
}
