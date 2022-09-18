import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Notebook} from "./model/notebook";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
  notebooks: Notebook[]=[];
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getAllNotebooks();
  }

  public getAllNotebooks(){
    let url = "http://localhost:8080/api/notebooks/all";
    this.http.get<Notebook[]>(url).subscribe({
      next: res=>{
        this.notebooks = res;
      },
      error : err => {
        alert("An error has occured")
      }
    })
  }
}
