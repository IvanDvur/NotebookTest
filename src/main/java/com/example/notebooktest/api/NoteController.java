package com.example.notebooktest.api;

import com.example.notebooktest.repos.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
    NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }
}
