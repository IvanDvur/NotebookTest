package com.example.notebooktest.repos;

import com.example.notebooktest.model.Note;
import com.example.notebooktest.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface NotebookRepository extends JpaRepository<Notebook, UUID> {}

