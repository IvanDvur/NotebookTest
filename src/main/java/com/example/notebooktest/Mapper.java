package com.example.notebooktest;

import com.example.notebooktest.api.Dto.NoteDto;
import com.example.notebooktest.api.Dto.NotebookDto;
import com.example.notebooktest.model.Note;
import com.example.notebooktest.model.Notebook;
import com.example.notebooktest.repos.NotebookRepository;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Mapper {
    private NotebookRepository notebookRepository;

    public Mapper(NotebookRepository notebookRepository){
        this.notebookRepository = notebookRepository;
    }

    public NoteDto convertToNoteDto(Note entity){
        NoteDto dto = new NoteDto();
        dto.setTitle(entity.getTitle());
        dto.setText(entity.getText());
        dto.setId(entity.getId().toString());
        dto.setNotebookId(entity.getNotebook().getId().toString());
        dto.setLastModified(entity.getLastModified());

        return dto;
    }

    public Note convertToNoteEntity(NoteDto dto){
        var notebook = this.notebookRepository.findById(UUID.fromString(dto.getNotebookId())).get();
        return new Note(dto.getId(),dto.getTitle(),dto.getText(),notebook);
    }

    public NotebookDto convertToNotebookDto(Notebook entity){
        NotebookDto dto = new NotebookDto();
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        dto.setNbNotes(entity.getNotes().size());

        return dto;
    }

    public Notebook convertToNotebookEntity(NotebookDto dto){

        return new Notebook(dto.getId(),dto.getName());
    }
}
