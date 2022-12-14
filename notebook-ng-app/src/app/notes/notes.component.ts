import {Component, OnInit} from '@angular/core';
import {Notebook} from "./model/notebook";
import {ApiService} from "../shared/api.service";
import {Note} from "./model/note";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
  notebooks: Notebook[] = [];
  notes: Note[] = [];
  selectedNotebook: Notebook = null!;
  searchText: string = null!;

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.getAllNotebooks();
    this.getAllNotes();
  }

  public getAllNotebooks() {
    this.apiService.getAllNotebooks().subscribe({
      next: res => {
        this.notebooks = res;
      },
      error: err => {
        alert("An error has occured...")
      }
    });
  }

  public getAllNotes() {
    this.apiService.getAllNotes().subscribe({
      next: res => {
        this.notes = res;
      },
      error: err => {
        alert("Could not get your notes...")
      }
    });
  }

  createNotebook() {
    let newNotebook: Notebook = {
      name: 'New Notebook',
      id: null!,
      nbOfNotes: 0,
    }
    this.apiService.postNotebook(newNotebook).subscribe({
      next: res => {
        newNotebook.id = res.id;
        this.notebooks.push(newNotebook);
      },
      error: err => {
        alert("Error posting notebook");
      }
    })
  }

  createNote(notebookId: string) {
    let newNote: Note = {
      title: 'New Note',
      id: null!,
      text: 'Write something here',
      notebookId: notebookId,
      lastModified: null!
    };
    this.apiService.postNote(newNote).subscribe({
      next: res => {
        newNote.id = res.id;
        this.notes.push(newNote);
      },
      error: err => {
        alert("Error posting note...");
      }
    })
  }

  updateNotebook(updatedNotebook: Notebook) {
    this.apiService.postNotebook(updatedNotebook).subscribe({
      next: res => {
      },
      error: err => {
        alert("Error posting notebook");
      }
    })
  }

  deleteNotebook(notebook: Notebook) {
    if (confirm("Are you sure? This will wipe notebook and all notes it contains")) {
      this.apiService.deleteNotebook(notebook.id).subscribe({
        next: res => {
          let indexOfNotebook = this.notebooks.indexOf(notebook);
          this.notebooks.splice(indexOfNotebook, 1)
        },
        error: err => {
          alert("Could not delete this notebook")
        }
      })
    }
  }

  deleteNote(note: Note) {
    if (confirm("Are you sure?")) {
      this.apiService.deleteNote(note.id).subscribe({
        next: res => {
          let indexOfNote = this.notes.indexOf(note);
          this.notes.splice(indexOfNote, 1)
        },
      })
    }
  }

  selectNotebook(notebook: Notebook) {
    this.selectedNotebook = notebook;
    this.apiService.getNotesByNotebook(notebook.id).subscribe({
      next: res => {
        this.notes = res;
      },
      error: err => {
        alert("Error has occured...")
      }
    })

  }

  updateNote(updatedNote: Note) {
    this.apiService.postNote(updatedNote).subscribe({
        next: res => {

        },
        error: err => {
          alert("Error updating note")
        }
      }
    )
  }
}
