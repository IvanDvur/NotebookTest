package com.example.notebooktest.api;

import com.example.notebooktest.Mapper;
import com.example.notebooktest.api.Dto.NotebookDto;
import com.example.notebooktest.model.Notebook;
import com.example.notebooktest.repos.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/notebooks")
@CrossOrigin
public class NotebookController {
    private NotebookRepository notebookRepository;
    private Mapper mapper;

    @Autowired
    public NotebookController(NotebookRepository notebookRepository, Mapper mapper) {
        this.notebookRepository = notebookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<Notebook> all() {
        var allNotebooks = this.notebookRepository.findAll();
        return allNotebooks;
    }

    @PostMapping
    public Notebook save(@RequestBody NotebookDto notebookDto, BindingResult bindingResult)
            throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Binding error");
        }
        Notebook notebook = mapper.convertToNotebookEntity(notebookDto);
        this.notebookRepository.save(notebook);

        return notebook;
    }

    @DeleteMapping("{/id}")
    public void deleteNotebook(@PathVariable String id){
        this.notebookRepository.deleteById(UUID.fromString(id));
    }
}
