package com.example.notebooktest.api;

import com.example.notebooktest.Mapper;
import com.example.notebooktest.api.Dto.NoteDto;
import com.example.notebooktest.model.Note;
import com.example.notebooktest.repos.NoteRepository;
import com.example.notebooktest.repos.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    NoteRepository noteRepository;
    NotebookRepository notebookRepository;
    Mapper mapper;

    @Autowired
    public NoteController(NoteRepository noteRepository, NotebookRepository notebookRepository, Mapper mapper) {
        this.noteRepository = noteRepository;
        this.notebookRepository = notebookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<NoteDto> all() {

        var notes = this.noteRepository.findAll();

        var notesDto = notes.stream().map(note -> mapper.convertToNoteDto(note)).
                collect(Collectors.toList());

        return notesDto;
    }

    @GetMapping("/byId/{id}")
    public NoteDto findById(@PathVariable String id) {
        var note = this.noteRepository.findById(UUID.fromString(id)).orElse(null);
        if (note == null) {
            throw new EntityNotFoundException();
        }

        var noteDto = this.mapper.convertToNoteDto(note);
        return noteDto;
    }

    @GetMapping("/byNotebook/{notebookId}")
    public List<NoteDto> notesByNotebook(@PathVariable String notebookId) {
        List<Note> notes = new ArrayList<>();
        var notebook = this.notebookRepository.findById(UUID.fromString(notebookId));

        if (notebook.isPresent()) {
            notes = this.noteRepository.findAllByNotebook(notebook.get());
        }
        var notesDto = notes.stream().map(note -> this.mapper.convertToNoteDto(note)).
                collect(Collectors.toList());

        return notesDto;
    }

    @PostMapping
    public Note save(@RequestBody NoteDto noteDto, BindingResult bindingResult) throws ValidationException {
        if(bindingResult.hasErrors()){
            throw new ValidationException("Validation Exeption");
        }

        var noteEntity = this.mapper.convertToNoteEntity(noteDto);

        this.noteRepository.save(noteEntity);
        return noteEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.noteRepository.deleteById(UUID.fromString(id));
    }
}
