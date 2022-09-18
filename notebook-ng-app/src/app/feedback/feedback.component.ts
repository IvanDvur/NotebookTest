import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  model: FeedbackDto = {
    name:'',
    email:'',
    feedback:''
  };

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
  }

  sendFeedback(): void{
    let url = "http://localhost:8080/api/feedback";
    this.http.post(url, this.model).subscribe({
      next: (res)=>{
        location.reload();
      },
      error: (err)=>{
        alert("An error has occured")
      }
    })
  }
}

export interface FeedbackDto {
  name: string;
  email: string;
  feedback: string;
}
