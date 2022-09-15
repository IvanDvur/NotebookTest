package com.example.notebooktest.api.Dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class NoteDto {

    private String id;

    @NotNull
    private String title;

    @NotNull
    private String text;

    @NotNull
    private Date lastModified;

    @NotNull
    private String notebookId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(String notebookId) {
        this.notebookId = notebookId;
    }
}
